package ch.ivy.addon.portalkit.util;

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
      Cookie cookie = null;

      Cookie[] userCookies = request.getCookies();
      if (userCookies != null && userCookies.length > 0) {
        for (int i = 0; i < userCookies.length; i++) {
          if (userCookies[i].getName().equals(name)) {
            cookie = userCookies[i];
            break;
          }
        }
      }
      if (cookie != null) {
        cookie.setValue(value);
      } else {
        cookie = new Cookie(name, value);
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
      Cookie cookie = null;

      Cookie[] userCookies = request.getCookies();
      if (userCookies != null && userCookies.length > 0) {
        for (int i = 0; i < userCookies.length; i++) {
          if (userCookies[i].getName().equals(name)) {
            cookie = userCookies[i];
            return cookie;
          }
        }
      }
    }
    return null;
  }
}
