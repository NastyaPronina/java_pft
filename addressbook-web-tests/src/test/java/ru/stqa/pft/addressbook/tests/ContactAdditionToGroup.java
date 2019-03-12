package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class ContactAdditionToGroup extends TestBase {

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
  public void testAdditionContactToGroup () {
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    String name = group.getName();
    app.group().selectGroup(name);
    if (app.db().isThereAContact(contact.getId(), group)) {
      app.contact().deleteContactInGroups(contact);
    }
    app.contact().addToGroup(group,contact);
    app.goTo().currentGroupPage(group);
    assertTrue(app.contact().isThereAContact(contact.getId()));
  }
}
