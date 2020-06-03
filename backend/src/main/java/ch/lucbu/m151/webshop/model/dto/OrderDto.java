package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.lucbu.m151.webshop.model.Order;
import ch.lucbu.m151.webshop.model.OrderPosition;
import ch.lucbu.m151.webshop.model.OrderStatus;

public class OrderDto extends Dto {
  private UUID id;

  private Long number;

  private LocalDateTime created;

  private LocalDateTime ordered;

  private LocalDateTime sent;

  private UserDto user;

  private OrderStatus status;

  private List<OrderPositionDto> positions = new ArrayList<OrderPositionDto>();

  private BigDecimal total = new BigDecimal(0);

  public OrderDto() {
  }

  public OrderDto(Order order) {
    for (OrderPosition position : order.getPositions()) {
      this.positions.add(new OrderPositionDto(position));
    }
    positions.stream().forEach(pos -> this.total = this.total.add(pos.getTotal()));
    this.id = order.getId();
    this.number = order.getNumber();
    this.created = order.getCreated();
    this.ordered = order.getOrdered();
    this.sent = order.getSent();
    this.user = new UserDto(order.getUser());
    this.status = order.getStatus();
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

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getOrdered() {
    return ordered;
  }

  public void setOrdered(LocalDateTime ordered) {
    this.ordered = ordered;
  }

  public LocalDateTime getSent() {
    return sent;
  }

  public void setSent(LocalDateTime sent) {
    this.sent = sent;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
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
