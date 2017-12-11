package com.itemsharing.userservice.controller;

import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{username}")
  public User getUserByUsername(@PathVariable String username) {
    User user = userService.getUserByUsername(username);
    user.setUsername("notJohn");
    return user;
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

}
