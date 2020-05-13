package ch.lucbu.m151.webshop.model;

import org.springframework.security.core.GrantedAuthority;

public class StringAuthority implements GrantedAuthority {
  private static final long serialVersionUID = -6296088883263415485L;

  private final String authority;

  public StringAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}
