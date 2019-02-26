package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

public class HttpSession {

  private CloseableHttpClient httpclient;
  private ApplicationManager app;
  private final Properties properties;
  public WebDriver wd;
  private String browser;


  public HttpSession(ApplicationManager app) {
    this.app = app;
    properties = new Properties();
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login(String username, String password) throws IOException {
    HttpPost post1 = new HttpPost("http://localhost/mantisbt-2.19.0/login_page.php");
    HttpPost post2 = new HttpPost("http://localhost/mantisbt-2.19.0/login_password_page.php");
    List<NameValuePair> params1 = new ArrayList<>();
    List<NameValuePair> params2 = new ArrayList<>();
    params1.add(new BasicNameValuePair("username", username));
    params2.add(new BasicNameValuePair("password", password));
   // params.add(new BasicNameValuePair("secure_session", "on"));
    params1.add(new BasicNameValuePair("return", "index.php"));
    post1.setEntity(new UrlEncodedFormEntity(params1));
    CloseableHttpResponse response1 = httpclient.execute(post1);
    CloseableHttpResponse response2 = httpclient.execute(post2);
    String body = getTextFrom(response1);
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedInAs (String username) throws IOException {
    HttpGet get = new HttpGet ("http://localhost/mantisbt-2.19.0/index.php");
    CloseableHttpResponse response = httpclient.execute(get);
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

//  public HttpSession newSession() {
//    return new HttpSession(this);
//  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

}
