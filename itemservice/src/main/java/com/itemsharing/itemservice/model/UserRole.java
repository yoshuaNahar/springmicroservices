package com.itemsharing.itemservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userw_role")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userRoleId;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Role role;

  public UserRole() {
  }

  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
  }

  public long getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(long userRoleId) {
    this.userRoleId = userRoleId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
