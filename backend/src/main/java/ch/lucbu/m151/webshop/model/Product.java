package ch.lucbu.m151.webshop.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import ch.lucbu.m151.webshop.model.dto.ProductDto;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true)
  private Long number;

  @Column(nullable = false)
  private String name;

  @Lob
  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal price;

  public Product() {
  }

  public Product(ProductDto dto) {
    this.name = dto.getName();
    this.description = dto.getDescription();
    this.price = dto.getPrice();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
