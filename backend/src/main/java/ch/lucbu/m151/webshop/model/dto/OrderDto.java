package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.lucbu.m151.webshop.model.Order;
import ch.lucbu.m151.webshop.model.OrderPosition;
import ch.lucbu.m151.webshop.model.OrderStatus;
import ch.lucbu.m151.webshop.model.User;

public class OrderDto extends Dto {
  private UUID id;

  private Long number;

  private User user;

  private OrderStatus status;

  private List<OrderPositionDto> positions = new ArrayList<OrderPositionDto>();

  private BigDecimal total;

  public OrderDto() {
  }

  public OrderDto(Order order) {
    for (OrderPosition position : order.getPositions()) {
      this.positions.add(new OrderPositionDto(position));
    }
    positions.stream().forEach(pos -> this.total = this.total.add(pos.getTotal()));
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<OrderPositionDto> getPositions() {
    return positions;
  }

  public void setPositions(List<OrderPositionDto> positions) {
    this.positions = positions;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
