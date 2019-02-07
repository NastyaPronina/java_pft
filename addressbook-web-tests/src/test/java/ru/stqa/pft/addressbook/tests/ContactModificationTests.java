package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstName("Adam").withMiddleName("Trish").withLastName("Yessular").
          withGroup("test1").withAddress("Huston").withHomeTelephone("55555").withMobileTelephone("77777").
          withWorkTelephone("99999").withEmail("helen@mail.com"));
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Adam").withMiddleName(null).
        withLastName("Simpson").withGroup(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null).
        withWorkTelephone(null).withEmail(null);
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
