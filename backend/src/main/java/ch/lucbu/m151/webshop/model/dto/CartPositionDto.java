package ch.lucbu.m151.webshop.model.dto;

import ch.lucbu.m151.webshop.model.CartPosition;

public class CartPositionDto extends Dto {

  private ProductDto product;

  private Integer count;

  public CartPositionDto() {
  }

  public CartPositionDto(CartPosition cartPosition) {
    this.product = new ProductDto(cartPosition.getProduct(), false);
    this.count = cartPosition.getCount();
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
}
