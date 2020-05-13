package ch.lucbu.m151.webshop.model.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import ch.lucbu.m151.webshop.model.UserRole;

public class UserDto extends Dto {

  private UUID id;

  @NotBlank
  @Email
  private String email;

  private UserRole role = UserRole.USER;

  @NotBlank
  @Length(min = 8, max = 50)
  private String password;

  @NotBlank
  @Length(max = 50)
  private String firstName;

  @NotBlank
  @Length(max = 50)
  private String lastName;

  @NotBlank
  @Length(max = 50)
  private String street;

  @NotBlank
  @Length(min = 4, max = 4)
  private String postalCode;

  @NotBlank
  @Length(max = 50)
  private String city;

  public UserDto() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
