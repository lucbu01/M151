package ch.lucbu.m151.webshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import ch.lucbu.m151.webshop.exception.WebshopException;
import ch.lucbu.m151.webshop.model.Product;
import ch.lucbu.m151.webshop.model.dto.ProductDto;
import ch.lucbu.m151.webshop.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<ProductDto> getAll() {
    return productRepository.findAll(Sort.by(Order.asc("name"))).stream().map(product -> new ProductDto(product, false))
        .collect(Collectors.toList());
  }

  private Product getProductByNumber(Long number) {
    Optional<Product> productOpt = productRepository.findByNumber(number);
    productOpt.orElseThrow(() -> new WebshopException("product not found"));
    return productOpt.get();
  }

  public ProductDto getByNumber(Long number) {
    return new ProductDto(getProductByNumber(number), true);
  }

  public Long create(ProductDto dto) {
    dto.validate();
    Product product = new Product(dto);
    Optional<Product> highestNumberedProduct = productRepository.findTopByOrderByNumberDesc();
    Long number = highestNumberedProduct.isPresent() ? highestNumberedProduct.get().getNumber() + 1 : 1;
    product.setNumber(number);
    productRepository.saveAndFlush(product);
    return number;
  }

  public void update(Long number, ProductDto dto) {
    Product product = getProductByNumber(number);
    ProductDto productDto = new ProductDto(product, true);
    productDto.expand(dto);
    productDto.validate();
    if (dto.getName() != null && !dto.getName().isBlank()) {
      product.setName(dto.getName());
    }
    if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
      product.setDescription(dto.getDescription());
    }
    if (dto.getPrice() != null) {
      product.setPrice(dto.getPrice());
    }
    productRepository.save(product);
  }
}
