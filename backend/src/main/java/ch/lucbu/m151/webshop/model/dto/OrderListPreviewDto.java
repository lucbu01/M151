package ch.lucbu.m151.webshop.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ch.lucbu.m151.webshop.model.Order;
import ch.lucbu.m151.webshop.model.OrderStatus;

public class OrderListPreviewDto {
  private Long number;
  private OrderStatus status;
  private LocalDateTime updated;
  private BigDecimal total = new BigDecimal(0);

  public OrderListPreviewDto() {
  }

  public OrderListPreviewDto(Order order) {
    this.number = order.getNumber();
    this.status = order.getStatus();
    this.updated = order.getUpdated();
    order.getPositions().stream()
        .forEach(pos -> this.total = pos.getProductPrice().multiply(new BigDecimal(pos.getCount())));
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
