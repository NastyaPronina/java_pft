package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import org.testng.annotations.Test;


public class GeoIpServiceTests {

  @Test
  public void testMyIp () {
    GeoIPService ipService = new GeoIPService();
    GeoIPServiceSoap geoIPServiceSoap = ipService.getGeoIPServiceSoap();
    String geoIp = geoIPServiceSoap.getIpLocation("92.38.78.184");
    //assertEquals(geoIp, "BY");
    System.out.println(geoIp);
  }

  @Test
  public void testInvalidIp () {
    GeoIPService ipService = new GeoIPService();
    GeoIPServiceSoap geoIPServiceSoap = ipService.getGeoIPServiceSoap();
    String geoIp = geoIPServiceSoap.getIpLocation("92.38.78.xxx");
    //assertEquals(geoIp, "BY");
    System.out.println(geoIp);
  }
}
