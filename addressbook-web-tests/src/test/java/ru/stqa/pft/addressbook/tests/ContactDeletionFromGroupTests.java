package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactDeletionFormGroup () {
    Groups groups = app.db().groups();
    GroupData modifiedGroup = groups.iterator().next();
    app.wd.get("http://localhost/addressbook/?group=" + modifiedGroup.getId());
    while (! app.contact().isThereAContact()) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("Nick 0").withLastname("Simpson 0").withHomePhone("11110")
          .withMobilePhone("22220").withWorkPhone("33330").withEmail1("0@mail.com").withEmail2("0@mail.com")
          .withEmail3("0@mail.com").withAddress("New York 0").inGroup(modifiedGroup));
    }
    app.wd.get("http://localhost/addressbook/?group=" + modifiedGroup.getId());
    ContactData deletedContact = app.contact().all().iterator().next();
    app.contact().deleteFromGroup(deletedContact);
    Assert.assertFalse(app.contact().isThereTheContact(deletedContact.getId()));
    }
  }
