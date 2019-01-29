package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("Jack", "Simpson", "Gibson", "test1", "Detroit",
              "44444", "55555", "77777", "jack@mail.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
