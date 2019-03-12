package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    if(app.db().contacts().size() ==0) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("Nick 0").withLastname("Simpson 0").withHomePhone("11110")
          .withMobilePhone("22220").withWorkPhone("33330").withEmail1("0@mail.com").withEmail2("0@mail.com")
          .withEmail3("0@mail.com").withAddress("New York 0"));
    }
  }

  @Test
  public void testContactDeletionFromGroup() {
    Groups groups = app.db().groups();
    GroupData modifiedGroup = groups.iterator().next();
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    app.goTo().currentGroupPage(modifiedGroup);
    if (!app.db().isThereAContact(contact.getId(), modifiedGroup)) {
      app.contact().addToGroup(modifiedGroup, contact);
      app.goTo().currentGroupPage(modifiedGroup);
    }
    app.contact().deleteFromGroup(contact);
    app.goTo().currentGroupPage(modifiedGroup);
    Assert.assertFalse(app.contact().isThereAContact(contact.getId()));
    }
  }
