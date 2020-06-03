package ch.lucbu.m151.webshop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
