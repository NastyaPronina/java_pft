package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Peter", "Miller", "Jackson", "New York", "1111111111", "2222222222", "3333333333", "peter@mail.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
