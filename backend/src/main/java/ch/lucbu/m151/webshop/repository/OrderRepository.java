package ch.lucbu.m151.webshop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.lucbu.m151.webshop.model.Order;
import ch.lucbu.m151.webshop.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
  Optional<Order> findByNumber(Long number);

  Optional<Order> findTopByNumberIsNotNullOrderByNumberDesc();

  Optional<Order> findByUserAndOrderedIsNull(User user);

  List<Order> findByUserAndOrderedIsNotNullAndSentIsNullOrderByOrdered(User user);

  List<Order> findByUserAndOrderedIsNotNullAndSentIsNotNullOrderBySent(User user);

  List<Order> findByOrderedIsNotNullAndSentIsNullOrderByOrdered();
}
