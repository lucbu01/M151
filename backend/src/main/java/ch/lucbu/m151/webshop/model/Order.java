package ch.lucbu.m151.webshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true, nullable = false)
  private Long number;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private OrderStatus status;

  @OneToMany(mappedBy = "order")
  private List<OrderPosition> positions = new ArrayList<OrderPosition>();

  public Order() {
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

  public List<OrderPosition> getPositions() {
    return positions;
  }

  public void setPositions(List<OrderPosition> positions) {
    this.positions = positions;
  }
}
