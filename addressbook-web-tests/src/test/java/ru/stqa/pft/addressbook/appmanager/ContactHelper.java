package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public void initContactCreation() {
    click(By.xpath("//a[@href='edit.php']"));
  }

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if(contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent (By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.xpath("//input[@id='" + id + "']")).click();
  }

  public void initContactModification (int id) {
   // wd.findElements(By.xpath("//*[@src='icons/pencil.png']")).get(id).click();
    click(By.xpath("//input[@id='" + id + "']/parent::td/following-sibling::td[7]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmContactDeletion () {
    wd.switchTo().alert().accept();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void updateContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

   public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    updateContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    confirmContactDeletion();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact () {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    List<WebElement> elements = wd.findElements(By.xpath("//input[@name='selected[]']"));
    return  elements.size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if(contactCache != null) {
      return  new Contacts(contactCache);
    }
    contactCache = new Contacts();
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement element : rows) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
          .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
        .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail1(email1)
        .withEmail2(email2).withEmail3(email3).withAddress(address);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
  }
}
