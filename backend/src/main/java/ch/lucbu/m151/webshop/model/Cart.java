package ch.lucbu.m151.webshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(unique = true, nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart")
  private List<CartPosition> positions = new ArrayList<CartPosition>();

  public Cart() {
  }

  public Cart(User user) {
    this.user = user;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartPosition> getPositions() {
    return positions;
  }

  public void setPositions(List<CartPosition> positions) {
    this.positions = positions;
  }
}
