package ch.lucbu.m151.webshop.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.exception.WebshopException;
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
    user.orElseThrow(() -> new UsernameNotFoundException(
        "Es wurde kein Benutzer mit der E-Mail Adresse '" + username + "' gefunden!"));
    return user.get();
  }

  public void newUser(UserDto userDto) throws Exception {
    userDto.validate();
    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
      throw new WebshopException(
          "Die E-Mail Adresse '" + userDto.getEmail() + "' wird bereits für einen anderen Account verwendet!");
    }
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    this.userRepository.save(new User(userDto));
  }

  public UserDto getInfo(Authentication auth) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(auth.getName());
    user.orElseThrow(() -> new UsernameNotFoundException(
        "Es wurde kein Benutzer mit der E-Mail Adresse '" + auth.getName() + "' gefunden!"));
    return new UserDto(user.get());
  }

}
