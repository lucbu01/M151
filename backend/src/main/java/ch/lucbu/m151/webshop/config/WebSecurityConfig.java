package ch.lucbu.m151.webshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.lucbu.m151.webshop.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().authorizeRequests() //
        .antMatchers("/api/user/create", "/api/product/get/**", "/api/product/list").permitAll() //
        .antMatchers("/api/product/**", "/api/order/admin/**").hasRole("ADMIN") //
        .antMatchers("/api/**").authenticated().and() //
        .logout().logoutUrl("/api/user/logout") //
        .invalidateHttpSession(true) //
        .deleteCookies("JSESSIONID").logoutSuccessUrl("/login").and() //
        .cors().disable().csrf().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }
}
