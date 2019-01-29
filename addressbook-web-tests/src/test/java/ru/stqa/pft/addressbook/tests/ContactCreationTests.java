package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("Adam", "Miller", "Fox", "test1", "New York",
            "111111", "222222", "3333333", "email@email.com"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
