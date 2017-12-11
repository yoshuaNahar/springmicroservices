package com.itemsharing.userservice;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class UserserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserserviceApplication.class, args);
  }

  @Bean
  public CommandLineRunner clr(UserService userService) {
    return args -> {
      User user1 = new User();
      user1.setFirstName("John");
      user1.setLastName("Adams");
      user1.setUsername("jadams");
      user1.setPassword("password");
      user1.setEmail("jadams@gmail.com");

      Set<UserRole> userRoles = new HashSet<>();
      Role role1 = new Role();
      role1.setRoleId(1);
      role1.setName("ROLE_USER");
      userRoles.add(new UserRole(user1, role1));

      // I need to add 2 roles manually...

      userService.createUser(user1);

    };
  }

}

@RestController
class UserServiceController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service-instances/{applicationName}") // userservice
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    System.out.println(discoveryClient.description());
    System.out.println(discoveryClient.getServices());
    ServiceInstance instance = discoveryClient.getLocalServiceInstance();
    System.out.println(
        instance.getHost() + ", " + instance.getPort() + ", " + instance.getUri() + ", "
            + instance.getServiceId() + ", " + instance.getMetadata());
    return this.discoveryClient.getInstances(applicationName);
  }

}
