package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private String group;
  private final String address;
  private final String homeTelephone;
  private final String mobileTelephone;
  private final String workTelephone;
  private final String email;

  public ContactData(int id, String firstName, String middleName, String lastName, String group, String address, String homeTelephone, String mobileTelephone, String workTelephone, String email) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.group = group;
    this.address = address;
    this.homeTelephone = homeTelephone;
    this.mobileTelephone = mobileTelephone;
    this.workTelephone = workTelephone;
    this.email = email;
  }

  public ContactData(String firstName, String middleName, String lastName, String group, String address, String homeTelephone, String mobileTelephone, String workTelephone, String email) {
    this.id = 0;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.group = group;
    this.address = address;
    this.homeTelephone = homeTelephone;
    this.mobileTelephone = mobileTelephone;
    this.workTelephone = workTelephone;
    this.email = email;
  }

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomeTelephone() {
    return homeTelephone;
  }

  public String getMobileTelephone() {
    return mobileTelephone;
  }

  public String getWorkTelephone() {
    return workTelephone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", homeTelephone='" + homeTelephone + '\'' +
            ", mobileTelephone='" + mobileTelephone + '\'' +
            ", workTelephone='" + workTelephone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(middleName, that.middleName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homeTelephone, that.homeTelephone) &&
            Objects.equals(mobileTelephone, that.mobileTelephone) &&
            Objects.equals(workTelephone, that.workTelephone) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, middleName, lastName, address, homeTelephone, mobileTelephone, workTelephone, email);
  }
}
