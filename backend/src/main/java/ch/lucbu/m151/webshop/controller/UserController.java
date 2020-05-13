package ch.lucbu.m151.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.model.dto.UserDto;
import ch.lucbu.m151.webshop.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/create")
  @ResponseStatus(code = HttpStatus.OK)
  public void create(@RequestBody UserDto userDto) throws Exception {
    userService.newUser(userDto);
  }

  @GetMapping("/info")
  public UserDto info(Authentication auth) {
    return userService.getInfo(auth);
  }
}
