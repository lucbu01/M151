package ch.lucbu.m151.webshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "customer_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true, nullable = false)
  private Long number;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @OneToMany(mappedBy = "order")
  private List<OrderPosition> positions = new ArrayList<OrderPosition>();

  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();

  private LocalDateTime ordered;

  private LocalDateTime sent;

  public Order() {
  }

  public Order(User user, Long number) {
    this.user = user;
    this.number = number;
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
    return sent != null ? OrderStatus.SENT : ordered != null ? OrderStatus.ORDERED : OrderStatus.OPEN;
  }

  public List<OrderPosition> getPositions() {
    return positions;
  }

  public void setPositions(List<OrderPosition> positions) {
    this.positions = positions;
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

  public void order() {
    this.ordered = LocalDateTime.now();
  }

  public LocalDateTime getSent() {
    return sent;
  }

  public void sent() {
    this.sent = LocalDateTime.now();
  }

  public LocalDateTime getUpdated() {
    return sent != null ? sent : ordered != null ? ordered : created;
  }
}
