package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;

import ch.lucbu.m151.webshop.model.CartPosition;

public class CartPositionDto extends Dto {

  private ProductDto product;

  private Integer count;

  private BigDecimal total;

  public CartPositionDto() {
  }

  public CartPositionDto(CartPosition cartPosition) {
    this.product = new ProductDto(cartPosition.getProduct(), false);
    this.count = cartPosition.getCount();
    this.total = this.product.getPrice().multiply(new BigDecimal(this.count));
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getTotal() {
    return total;
  }
}
