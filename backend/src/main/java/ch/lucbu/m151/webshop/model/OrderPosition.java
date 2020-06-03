package ch.lucbu.m151.webshop.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "customer_order_position")
public class OrderPosition {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  private Order order;

  @Column(nullable = false)
  private UUID productId;

  @Column(nullable = false)
  private Long productNumber;

  @Column(nullable = false)
  private String productName;

  @Column(nullable = false)
  private BigDecimal productPrice;

  @Column(nullable = false)
  private Integer count;

  public OrderPosition() {
  }

  public OrderPosition(Order order, Product product, Integer count) {
    this.order = order;
    this.count = count;
    this.productId = product.getId();
    this.productNumber = product.getNumber();
    this.productName = product.getName();
    this.productPrice = product.getPrice();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
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
}
