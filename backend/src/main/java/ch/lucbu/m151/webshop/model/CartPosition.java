package ch.lucbu.m151.webshop.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartPosition {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  private Cart cart;

  @ManyToOne
  private Product product;

  @Column(nullable = false)
  private Integer count;

  public CartPosition() {
  }

  public CartPosition(Cart cart, Product product, Integer count) {
    this.cart = cart;
    this.product = product;
    this.count = count;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public void increase() {
    this.count++;
  }

  public void decrease() {
    if (this.count > 0) {
      this.count--;
    }
  }

}
