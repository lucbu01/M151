package ch.lucbu.m151.webshop.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.exception.WebshopException;
import ch.lucbu.m151.webshop.model.Cart;
import ch.lucbu.m151.webshop.model.CartPosition;
import ch.lucbu.m151.webshop.model.Product;
import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.dto.CartDto;
import ch.lucbu.m151.webshop.repository.CartPositionRepository;
import ch.lucbu.m151.webshop.repository.CartRepository;

@Service
@Transactional
public class CartService {

  @Autowired
  CartRepository cartRepository;

  @Autowired
  CartPositionRepository cartPositionRepository;

  @Autowired
  EntityManager entityManager;

  @Autowired
  ProductService productService;

  private void set(Long productNumber, User user, Consumer<CartPosition> func) {
    Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
    cartRepository.saveAndFlush(cart);
    CartPosition position;
    try {
      position = cart.getPositions().stream().filter(pos -> pos.getProduct().getNumber() == productNumber).findFirst()
          .get();
      func.accept(position);
    } catch (NoSuchElementException e) {
      Product product = productService.getProductByNumber(productNumber);
      position = new CartPosition(cart, product, 0);
      func.accept(position);
    }
    if (position.getCount() > 0) {
      cartPositionRepository.save(position);
    } else {
      cartPositionRepository.delete(position);
    }
  }

  public void add(Long productNumber, User user) {
    set(productNumber, user, pos -> pos.increase());
  }

  public void remove(Long productNumber, User user) {
    set(productNumber, user, pos -> pos.decrease());
  }

  public void removeAll(Long productNumber, User user) {
    set(productNumber, user, pos -> pos.setCount(0));
  }

  public void set(Long productNumber, Integer count, User user) {
    set(productNumber, user, pos -> pos.setCount(count));
  }

  public CartDto get(User user) {
    Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
    for (CartPosition pos : cart.getPositions()) {
      if (!pos.getProduct().isActive()) {
        cartPositionRepository.delete(pos);
      }
    }
    cartPositionRepository.flush();
    entityManager.clear();
    return new CartDto(cartRepository.findByUser(user).orElse(new Cart(user)));
  }

  public Cart getCart(User user) {
    Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new WebshopException("Dein Warenkorb ist leer!"));
    if (cart.getPositions().isEmpty()) {
      throw new WebshopException("Dein Warenkorb ist leer!");
    }
    for (CartPosition pos : cart.getPositions()) {
      if (!pos.getProduct().isActive()) {
        cartPositionRepository.delete(pos);
      }
    }
    cartPositionRepository.flush();
    entityManager.clear();
    return cartRepository.findByUser(user).get();
  }

  public void clear(User user) {
    Optional<Cart> cart = cartRepository.findByUser(user);
    if (cart.isPresent()) {
      for (CartPosition pos : cart.get().getPositions()) {
        cartPositionRepository.delete(pos);
      }
      cartPositionRepository.flush();
      cartRepository.delete(cart.get());
      cartRepository.flush();
    }
  }
}
