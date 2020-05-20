package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import ch.lucbu.m151.webshop.model.Product;

public class ProductDto extends Dto {

  private UUID id;

  private Long number;

  @NotBlank
  @Length(max = 50)
  private String name;

  @NotBlank
  private String description;

  @Min(value = 0)
  private BigDecimal price;

  public ProductDto() {
  }

  public ProductDto(Product product, boolean withDescription) {
    this.id = product.getId();
    this.number = product.getNumber();
    this.name = product.getName();
    if (withDescription) {
      this.description = product.getDescription();
    }
    this.price = product.getPrice();
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
