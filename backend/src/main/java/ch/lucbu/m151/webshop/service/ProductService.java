package ch.lucbu.m151.webshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.model.Product;
import ch.lucbu.m151.webshop.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAll() {
    return productRepository.findAll(Sort.by(Order.asc("name")));
  }
}
