package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class PasswordChangeHelper extends HelperBase {
  public PasswordChangeHelper(ApplicationManager app) {
    super(app);
  }

  public void goToUsersList() {
    click(By.xpath("//a[contains(text(), 'Manage Users')]"));
  }

  public void chooseUser(UserData user){
    click(By.linkText(user.getUsername()));
  }

  public void resetPassword(){
    wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
  }

  public void confirmChangePassword(String resetPasswordLink, String password) {
    wd.get(resetPasswordLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void loginUi(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    type(By.name("password"),password);
    click(By.cssSelector("input[type='submit']"));
}
}
