package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("Adam").withLastname("Yessular").
          withGroup("test1").withHomePhone("55555").withMobilePhone("77777").
          withWorkPhone("99999"));
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Adam").
        withLastname("Simpson").withHomePhone("11111").withMobilePhone("22222").withWorkPhone("33333").withEmail1("mail@gmail.com")
        .withEmail2("mail@yahoo.com").withEmail3("mail@mail.ru").withAddress("Huston");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    app.wd.navigate().refresh();
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
