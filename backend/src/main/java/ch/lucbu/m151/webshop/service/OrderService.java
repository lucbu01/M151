package ch.lucbu.m151.webshop.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.exception.NotFoundException;
import ch.lucbu.m151.webshop.exception.UnauthorizedException;
import ch.lucbu.m151.webshop.exception.WebshopException;
import ch.lucbu.m151.webshop.model.Cart;
import ch.lucbu.m151.webshop.model.CartPosition;
import ch.lucbu.m151.webshop.model.Order;
import ch.lucbu.m151.webshop.model.OrderPosition;
import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.UserRole;
import ch.lucbu.m151.webshop.model.dto.OrderDto;
import ch.lucbu.m151.webshop.model.dto.OrderListPreviewDto;
import ch.lucbu.m151.webshop.repository.OrderPositionRepository;
import ch.lucbu.m151.webshop.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrderPositionRepository orderPositionRepository;

  @Autowired
  CartService cartService;

  public OrderDto orderPreview(User user) {
    Optional<Order> currentOrderPreview = orderRepository.findByUserAndOrderedIsNull(user);
    if (currentOrderPreview.isPresent()) {
      orderRepository.delete(currentOrderPreview.get());
    }

    Cart cart = cartService.getCart(user);
    Optional<Order> highestNumberedOrder = orderRepository.findTopByOrderByNumberDesc();
    Long number = highestNumberedOrder.isPresent() ? highestNumberedOrder.get().getNumber() + 1 : 1;
    Order order = new Order(user, number);
    orderRepository.saveAndFlush(order);
    for (CartPosition position : cart.getPositions()) {
      orderPositionRepository.save(new OrderPosition(order, position.getProduct(), position.getCount()));
    }
    orderPositionRepository.flush();
    return new OrderDto(orderRepository.findByUserAndOrderedIsNull(user).get());
  }

  public OrderDto order(User user) {
    Order currentOrderPreview = orderRepository.findByUserAndOrderedIsNull(user)
        .orElseThrow(() -> new WebshopException("Es ist keine Bestellungsvorschau vorhanden!"));
    if (LocalDateTime.now().minus(10, ChronoUnit.MINUTES).compareTo(currentOrderPreview.getCreated()) > 0) {
      this.orderRepository.delete(currentOrderPreview);
      throw new WebshopException("Die Bestellungsvorschau von 10 Minuten ist abgelaufen!");
    }

    currentOrderPreview.order();
    orderRepository.save(currentOrderPreview);
    return new OrderDto(currentOrderPreview);
  }

  public List<OrderListPreviewDto> myOpenOrders(User user) {
    return orderRepository.findByUserAndOrderedIsNotNullAndSentIsNullOrderByOrdered(user).stream()
        .map(order -> new OrderListPreviewDto(order)).collect(Collectors.toList());
  }

  public List<OrderListPreviewDto> mySentOrders(User user) {
    return orderRepository.findByUserAndOrderedIsNotNullAndSentIsNotNullOrderBySent(user).stream()
        .map(order -> new OrderListPreviewDto(order)).collect(Collectors.toList());
  }

  public List<OrderListPreviewDto> openOrders() {
    return orderRepository.findByOrderedIsNotNullAndSentIsNullOrderByOrdered().stream()
        .map(order -> new OrderListPreviewDto(order)).collect(Collectors.toList());
  }

  public OrderDto details(Long number, User user) {
    Order order = orderRepository.findByNumber(number).orElseThrow(() -> new NotFoundException());
    if (user.getRole() == UserRole.ADMIN || order.getUser().getId().equals(user.getId())) {
      return new OrderDto(order);
    }
    throw new UnauthorizedException();
  }

  public OrderDto setSent(Long number) {
    Order order = orderRepository.findByNumber(number).orElseThrow(() -> new NotFoundException());
    order.sent();
    orderRepository.save(order);
    return new OrderDto(order);
  }
}
