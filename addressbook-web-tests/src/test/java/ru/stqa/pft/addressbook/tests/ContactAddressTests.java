package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test(enabled = true)
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm));
  }

//  private String mergeAddress(ContactData contact) {
//    String[] addressParts = contact.getAddress().split(" ");
//    return Arrays.asList(addressParts).stream().
//        //.map(ContactAddressTests::cleaned);
//  }
//
//  public static String cleaned(String email) {
//    return email.replaceAll("\\s", "")
//        //.replaceAll("[,]", "");
//  }
}
