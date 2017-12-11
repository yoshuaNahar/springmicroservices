package com.itemsharing.itemservice.client;

import com.itemsharing.itemservice.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface UserServiceFeignClient {

  @RequestMapping(value = "/v1/user/{username}", method = RequestMethod.GET, consumes = "application/json")
  User getUserByUsername(@PathVariable("username") String username);

}
