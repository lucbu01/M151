package ch.lucbu.m151.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.dto.CartDto;
import ch.lucbu.m151.webshop.service.CartService;

@RestController
@RequestMapping(path = "/api/cart")
public class CartController {
  @Autowired
  CartService cartService;

  @PutMapping("/add/{product}")
  @ResponseStatus(code = HttpStatus.OK)
  public CartDto add(@PathVariable Long productNumber, Authentication auth) {
    return cartService.add(productNumber, (User) auth.getPrincipal());
  }

  @PutMapping("/set/{product}/{count}")
  @ResponseStatus(code = HttpStatus.OK)
  public CartDto set(@PathVariable Long productNumber, @PathVariable Integer count, Authentication auth) {
    return cartService.set(productNumber, count, (User) auth.getPrincipal());
  }
}
