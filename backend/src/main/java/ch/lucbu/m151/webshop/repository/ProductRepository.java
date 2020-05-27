package ch.lucbu.m151.webshop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.lucbu.m151.webshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findByNumber(Long number);

  List<Product> findByDeletedIsNullOrderByNameAsc();

  Optional<Product> findTopByOrderByNumberDesc();
}
