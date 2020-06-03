package ch.lucbu.m151.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.dto.OrderDto;
import ch.lucbu.m151.webshop.service.OrderService;

@RestController
@RequestMapping(path = "/api/checkout")
public class CheckoutController {
  @Autowired
  OrderService orderService;

  @GetMapping("/preview")
  public OrderDto orderPreview(Authentication auth) {
    return orderService.orderPreview((User) auth.getPrincipal());
  }

  @PostMapping("/order")
  public OrderDto order(Authentication auth) {
    return orderService.order((User) auth.getPrincipal());
  }
}
