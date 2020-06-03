package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import ch.lucbu.m151.webshop.model.OrderPosition;

public class OrderPositionDto extends Dto {
  private UUID id;

  private UUID productId;

  private Long productNumber;

  private String productName;

  private BigDecimal productPrice;

  private Integer count;

  private BigDecimal total;

  public OrderPositionDto() {
  }

  public OrderPositionDto(OrderPosition orderPosition) {
    this.id = orderPosition.getId();
    this.productId = orderPosition.getProductId();
    this.productNumber = orderPosition.getProductNumber();
    this.productName = orderPosition.getProductName();
    this.productPrice = orderPosition.getProductPrice();
    this.count = orderPosition.getCount();
    this.total = productPrice.multiply(new BigDecimal(count));
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public Long getProductNumber() {
    return productNumber;
  }

  public void setProductNumber(Long productNumber) {
    this.productNumber = productNumber;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
