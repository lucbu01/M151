package ch.lucbu.m151.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/get")
  public CartDto get(Authentication auth) {
    return cartService.get((User) auth.getPrincipal());
  }

  @PutMapping("/add/{productNumber}")
  @ResponseStatus(code = HttpStatus.OK)
  public void add(@PathVariable Long productNumber, Authentication auth) {
    cartService.add(productNumber, (User) auth.getPrincipal());
  }

  @DeleteMapping("/remove/{productNumber}")
  @ResponseStatus(code = HttpStatus.OK)
  public void remove(@PathVariable Long productNumber, Authentication auth) {
    cartService.remove(productNumber, (User) auth.getPrincipal());
  }

  @DeleteMapping("/remove/{productNumber}/all")
  @ResponseStatus(code = HttpStatus.OK)
  public void removeAll(@PathVariable Long productNumber, Authentication auth) {
    cartService.removeAll(productNumber, (User) auth.getPrincipal());
  }

  @PutMapping("/set/{productNumber}/{count}")
  @ResponseStatus(code = HttpStatus.OK)
  public void set(@PathVariable Long productNumber, @PathVariable Integer count, Authentication auth) {
    cartService.set(productNumber, count, (User) auth.getPrincipal());
  }
}
