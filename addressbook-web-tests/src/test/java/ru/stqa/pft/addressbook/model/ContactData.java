package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "firstname")

  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Column(name = "email")
  @Type(type = "text")
  private String email1;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
      joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    return new File (photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getEmail1() {
    return email1;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public int getId() { return id; }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public ContactData withFirstname(String firstName) {
    this.firstname = firstName;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() { return workPhone; }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
        Objects.equals(firstname, that.firstname) &&
        Objects.equals(lastname, that.lastname) &&
        Objects.equals(homePhone, that.homePhone) &&
        Objects.equals(mobilePhone, that.mobilePhone) &&
        Objects.equals(workPhone, that.workPhone) &&
        Objects.equals(email1, that.email1) &&
        Objects.equals(email2, that.email2) &&
        Objects.equals(email3, that.email3) &&
        Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname, lastname, homePhone, mobilePhone, workPhone, email1, email2, email3, address);
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", homePhone='" + homePhone + '\'' +
        ", mobilePhone='" + mobilePhone + '\'' +
        ", workPhone='" + workPhone + '\'' +
        ", email1='" + email1 + '\'' +
        ", email2='" + email2 + '\'' +
        ", email3='" + email3 + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
