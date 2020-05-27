package ch.lucbu.m151.webshop.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.lucbu.m151.webshop.model.Cart;
import ch.lucbu.m151.webshop.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
  Optional<Cart> findByUser(User user);
}
