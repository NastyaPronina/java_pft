package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String address;
  private final String homeTelephone;
  private final String mobileTelephone;
  private final String workTelephone;
  private final String email;

  public ContactData(String firstName, String middleName, String lastName, String address, String homeTelephone, String mobileTelephone, String workTelephone, String email) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.homeTelephone = homeTelephone;
    this.mobileTelephone = mobileTelephone;
    this.workTelephone = workTelephone;
    this.email = email;
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
}
