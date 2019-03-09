package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.DbHelper;
import ru.stqa.pft.mantis.appmanager.HelperBase;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app
      = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  protected final HelperBase hb = new HelperBase(app);
  protected DbHelper db = new DbHelper();

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"),
//        "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
//    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }
}
