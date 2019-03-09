package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.appmanager.PasswordChangeHelper;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

  private PasswordChangeHelper passwordChangeHelper = new PasswordChangeHelper(hb);

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange () throws IOException, MessagingException {
    HttpSession session = app.newSession();
    passwordChangeHelper.enterLogin("administrator");
    passwordChangeHelper.enterPassword("root");
    passwordChangeHelper.goToUsersList();

    List<UserData> allUsers = db.getAllUsersExceptAdmin();
    int index = ThreadLocalRandom.current().nextInt(0, allUsers.size() + 1);

    UserData user = allUsers.get(index);
    passwordChangeHelper.selectUserFromTheList(user);

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findconfirmationLink(mailMessages, user.getEmail());
    passwordChangeHelper.getConfirmationLink(confirmationLink);

    passwordChangeHelper.resetPassword("12345", "12345");

    assertTrue(session.login(user.getUsername(), "12345", user.getRealname()));
    assertTrue(session.isLoggedInAs(user.getUsername(),user.getRealname()));
  }

  private String findconfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m)-> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
