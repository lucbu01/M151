package ch.lucbu.m151.webshop.model.dto;

import java.util.ArrayList;
import java.util.List;

import ch.lucbu.m151.webshop.model.Cart;
import ch.lucbu.m151.webshop.model.CartPosition;

public class CartDto extends Dto {

  private List<CartPositionDto> positions = new ArrayList<CartPositionDto>();

  public CartDto() {
  }

  public CartDto(Cart cart) {
    for (CartPosition position : cart.getPositions()) {
      positions.add(new CartPositionDto(position));
    }
  }

  public void setPositions(List<CartPositionDto> positions) {
    this.positions = positions;
  }

  public List<CartPositionDto> getPositions() {
    return positions;
  }
}
