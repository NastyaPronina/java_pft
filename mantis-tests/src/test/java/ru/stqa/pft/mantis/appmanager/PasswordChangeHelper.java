package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class PasswordChangeHelper {

  private HelperBase hb;
  public PasswordChangeHelper(HelperBase hb) {
    this.hb = hb;
  }

  public void enterPassword(String password) {
    hb.type(By.name("password"), password);
    hb.click(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]"));
  }

  public void enterLogin(String username) {
    hb.type(By.name("username"), username);
    hb.click(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]"));
  }

  public void goToUsersList() {
    hb.click(By.xpath("//*[@id=\"sidebar\"]/ul/li[7]/a"));
    hb.click(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a"));
  }

  public void selectUserFromTheList(UserData user) {
    hb.click(By.xpath("//a[text()='" + user.getUsername() + "']"));
    hb.click(By.xpath("//*[@id=\"manage-user-reset-form\"]/fieldset/span/input"));
  }

  public void getConfirmationLink(String confirmationLink) {
    hb.goTo(confirmationLink);
  }

  public void resetPassword(String password, String password_confirm) {
    hb.type(By.name("password"), password);
    hb.type(By.name("password_confirm"), password_confirm);
    hb.click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span"));
  }
}
