package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange () throws IOException, MessagingException {
    app.resetPassword().loginUi(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.resetPassword().goToUsersList();

    UserData user = app.db().users().iterator().next();
    app.resetPassword().chooseUser(user);

    String username = user.getUsername();
    String email = user.getEmail();
    String password = "password";

    app.resetPassword().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);

    app.resetPassword().confirmChangePassword(confirmationLink, password);

    HttpSession session = app.newSession();
    assertTrue(session.login(username, password));
    assertTrue(session.isLoggedInAs(username));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
