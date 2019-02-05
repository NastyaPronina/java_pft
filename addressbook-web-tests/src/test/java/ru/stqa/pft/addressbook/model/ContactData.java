package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id = 0;
  private String firstName;
  private String middleName;
  private String lastName;
  private String group;
  private String address;
  private String homeTelephone;
  private String mobileTelephone;
  private String workTelephone;
  private String email;

  public int getId() { return id; }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomeTelephone(String homeTelephone) {
    this.homeTelephone = homeTelephone;
    return this;
  }

  public ContactData withMobileTelephone(String mobileTelephone) {
    this.mobileTelephone = mobileTelephone;
    return this;
  }

  public ContactData withWorkTelephone(String workTelephone) {
    this.workTelephone = workTelephone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

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
    return id == that.id &&
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
