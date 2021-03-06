package ch.lucbu.m151.webshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.lucbu.m151.webshop.model.CartPosition;

@Repository
public interface CartPositionRepository extends JpaRepository<CartPosition, UUID> {

}
