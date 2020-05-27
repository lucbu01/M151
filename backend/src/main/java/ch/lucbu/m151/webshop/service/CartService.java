package ch.lucbu.m151.webshop.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  ProductService productService;

  public CartDto add(Long productNumber, User user) {
    Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
    cartRepository.saveAndFlush(cart);
    CartPosition position;
    try {
      position = cart.getPositions().stream().filter(pos -> pos.getProduct().getNumber() == productNumber).findFirst()
          .get();
      position.setCount(position.getCount() + 1);
    } catch (NoSuchElementException e) {
      Product product = productService.getProductByNumber(productNumber);
      position = new CartPosition(cart, product, 1);
    }
    cartPositionRepository.save(position);
    return new CartDto(cart);
  }

  public CartDto set(Long productNumber, Integer count, User user) {
    Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
    cartRepository.saveAndFlush(cart);
    CartPosition position;
    try {
      position = cart.getPositions().stream().filter(pos -> pos.getProduct().getNumber() == productNumber).findFirst()
          .get();
      position.setCount(count);
    } catch (NoSuchElementException e) {
      Product product = productService.getProductByNumber(productNumber);
      position = new CartPosition(cart, product, count);
    }
    cartPositionRepository.save(position);
    return new CartDto(cart);
  }

}
