package ch.ivy.addon.portalkit.support;

import java.net.MalformedURLException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivyteam.ivy.environment.Ivy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Ivy.class)
public class UrlDetectorTest {

  @Test
  public void testGetBaseURLReturnURL() throws MalformedURLException {
    String scheme = "http";
    String serverName = "localhost";
    int serverPort = 8080;
    String contextPath = "/ivy";
    FacesContext facesContext = mockBaseUrl(scheme, serverName, serverPort, contextPath);
    String expectedBaseUrl = scheme + "://" + serverName + ":" + serverPort + contextPath;

    UrlDetector urlDetector = new UrlDetector();
    String actualBaseUrl = urlDetector.getBaseURL(facesContext);

    Assert.assertEquals(expectedBaseUrl, actualBaseUrl);
  }

  @Test(expected = MalformedURLException.class)
  public void testGetBaseURLThrowsException() throws MalformedURLException {
    FacesContext facesContext = mockBaseUrl(StringUtils.EMPTY, StringUtils.EMPTY, 0, StringUtils.EMPTY);

    UrlDetector urlDetector = new UrlDetector();
    urlDetector.getBaseURL(facesContext);
  }

  @Test
  public void testGetHost() throws MalformedURLException {
    UrlDetector detector = new UrlDetector();
    Assert.assertEquals("http://google.com", detector.getHost("http://google.com"));
    Assert.assertEquals("http://www.google.com", detector.getHost("http://www.google.com/ivy/designer"));
    Assert.assertEquals("http://google.com:8081", detector.getHost("http://google.com:8081"));
    Assert.assertEquals("http://google.com:8081", detector.getHost("http://google.com:8081/ivy/designer"));
    Assert.assertEquals("http://www.google.com", detector.getHost("http://www.google.com:80/ivy/designer"));
    Assert.assertEquals("https://www.google.com", detector.getHost("https://www.google.com:443/ivy/designer"));
  }
  
  private FacesContext mockBaseUrl(String scheme, String serverName, int serverPort, String contextPath) {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getScheme()).thenReturn(scheme);
    Mockito.when(request.getServerName()).thenReturn(serverName);
    Mockito.when(request.getServerPort()).thenReturn(serverPort);
    Mockito.when(request.getContextPath()).thenReturn(contextPath);

    ExternalContext externalContext = Mockito.mock(ExternalContext.class);
    Mockito.when(externalContext.getRequest()).thenReturn(request);

    FacesContext facesContext = Mockito.mock(FacesContext.class);
    Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);

    return facesContext;
  }
}
