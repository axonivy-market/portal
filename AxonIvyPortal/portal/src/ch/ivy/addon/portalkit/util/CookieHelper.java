package ch.ivy.addon.portalkit.util;

import java.util.Optional;
import java.util.stream.Stream;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * The functionalities to store and get cookie
 * 
 * @author lptchi
 *
 */
public class CookieHelper {

  /**
   * set cookie from server
   * 
   * @param name String name of cookie item stored in cookie
   * @param value String value stored in cookie
   * 
   */
  public void setCookie(String name, String value) {
    FacesContext facesContext = FacesContext.getCurrentInstance();

    if (facesContext != null) {
      HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
      Cookie[] userCookies = request.getCookies();
      Cookie cookie = new Cookie(name, value);
      if (userCookies != null && userCookies.length > 0) {
        Optional<Cookie> cookieOpt = Stream.of(userCookies).filter(c -> c.getName().equals(name)).findFirst();
        if (cookieOpt.isPresent()) {
          cookie = cookieOpt.get();
          cookie.setValue(value);
        }
      }
      cookie.setPath("/");
      HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
      response.addCookie(cookie);
    }
  }

  /**
   * get cookie by name, call from server.
   * 
   * @param name String of cookie item stored in cookie
   * @return Cookie Cookie object stored in cookie
   */
  public Cookie getCookie(String name) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (facesContext != null) {
      HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
      Cookie[] userCookies = request.getCookies();
      return  Stream.of(userCookies).filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
    return null;
  }
}
