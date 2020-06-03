package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ch.lucbu.m151.webshop.model.Cart;
import ch.lucbu.m151.webshop.model.CartPosition;

public class CartDto extends Dto {

  private List<CartPositionDto> positions = new ArrayList<CartPositionDto>();
  private BigDecimal total = new BigDecimal(0);

  public CartDto() {
  }

  public CartDto(Cart cart) {
    for (CartPosition position : cart.getPositions()) {
      positions.add(new CartPositionDto(position));
    }
    positions.stream().forEach(pos -> this.total = this.total.add(pos.getTotal()));
  }

  public void setPositions(List<CartPositionDto> positions) {
    this.positions = positions;
  }

  public List<CartPositionDto> getPositions() {
    return positions;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getTotal() {
    return total;
  }
}
