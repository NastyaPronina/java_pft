package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test (enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Adam").withLastname("Simpson").
        withGroup("test1").withHomePhone("55555").withMobilePhone("77777").
        withWorkPhone("99999");
    app.goTo().addNewContactPage();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Set<ContactData> after = app.contact().all();

    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = true)
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Adam'").withLastname("Simpson").
        withGroup("test1").withHomePhone("55555").withMobilePhone("77777").
        withWorkPhone("99999");
    app.goTo().addNewContactPage();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Set<ContactData> after = app.contact().all();


    assertThat(after, equalTo(before));
  }
}
