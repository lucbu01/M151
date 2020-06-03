package ch.lucbu.m151.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.dto.OrderDto;
import ch.lucbu.m151.webshop.model.dto.OrderListPreviewDto;
import ch.lucbu.m151.webshop.service.OrderService;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {
  @Autowired
  OrderService orderService;

  @GetMapping("/admin/open")
  List<OrderListPreviewDto> getAllOpenOrders() {
    return orderService.openOrders();
  }

  @PutMapping("/admin/sent/{number}")
  OrderDto setSent(@PathVariable Long number) {
    return orderService.setSent(number);
  }

  @GetMapping("/open")
  List<OrderListPreviewDto> getOpenOrders(Authentication auth) {
    return orderService.openOrders((User) auth.getPrincipal());
  }

  @GetMapping("/sent")
  List<OrderListPreviewDto> getSentOrders(Authentication auth) {
    return orderService.sentOrders((User) auth.getPrincipal());
  }

  @GetMapping("/details/{number}")
  OrderDto details(@PathVariable Long number, Authentication auth) {
    return orderService.details(number, (User) auth.getPrincipal());
  }
}
