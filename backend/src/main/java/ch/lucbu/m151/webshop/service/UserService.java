package ch.lucbu.m151.webshop.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.model.User;
import ch.lucbu.m151.webshop.model.dto.UserDto;
import ch.lucbu.m151.webshop.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(username);
    user.orElseThrow(() -> new UsernameNotFoundException("no user with email '" + username + "'"));
    return user.get();
  }

  public void newUser(UserDto userDto) throws Exception {
    userDto.validate();
    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
      throw new Exception("email already exists");
    }
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    this.userRepository.save(new User(userDto));
  }

}
