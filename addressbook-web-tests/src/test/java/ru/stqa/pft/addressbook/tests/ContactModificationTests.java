package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification () {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Jack", "Simpson", "Gibson", "test1", "Detroit",
              "44444", "55555", "77777", "jack@mail.com"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Helen", "Trish", "Yessular", null, "Huston", "55555", "77777", "99999", "helen@mail.com"), false);
    app.getContactHelper().updateContactModification();
    app.getNavigationHelper().goToHomePage();
  }
}
