package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.interactions.SourceType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
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
    app.contact().initContactCreation();
    File photo = new File("src/test/resources/stru.png");
    app.contact().fillContactForm
        (new ContactData().withFirstname("Adam").withLastname("Simpson").withPhoto(photo), true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();
  }
}
