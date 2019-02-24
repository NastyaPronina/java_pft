package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    ContactData newContact = new ContactData().withFirstname("Nick 0").withLastname("Simpson 0").withHomePhone("11110")
        .withMobilePhone("22220").withWorkPhone("33330").withEmail1("0@mail.com").withEmail2("0@mail.com").withEmail3("0@mail.com")
        .withAddress("New York 0").inGroup(groups.iterator().next());
    app.goTo().homePage();

    //if contact was added to group
    Assert.assertTrue(newContact.getGroups().size()==1);

    Contacts before = app.db().contacts();
    app.contact().initContactCreation();
    app.contact().create(newContact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
        before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}



