package ch.lucbu.m151.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.service.OrderService;

@RestController
@RequestMapping(path = "/api/checkout")
public class CheckoutController {
  @Autowired
  OrderService orderService;

}
