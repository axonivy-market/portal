package com.axonivy.portal.components.jsf;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.faces.context.FacesContext;

import com.axonivy.portal.components.service.exception.PortalException;
import ch.ivyteam.ivy.security.internal.SecurityManager;

public class ManagedBeans {

  private ManagedBeans() {}

  /**
   * Returns the instance of the specified {@code beanName} from the current {@code FacesContext}.
   * If the bean cannot be obtained, {@code RuntimeException} will be thrown.
   * @param beanName 
   * 
   * @return instance of the bean associating with {@code beanName}. Never returns {@code null}.
   * @throws NoSuchElementException if the bean is not present at the time of this invocation
   * @see #find(String)
   * 
   */
  public static <T> T get(final String beanName) {
    Optional<T> beanInstance = find(beanName);

    // The reason why it throws exception here is because, in Ivy Script,
    // if get() returns `null`, the Ivy Script Engine will **automatically**
    // initialize a new instance of the bean class, thus creating a **false sense**
    // that the bean exists. Throwing RuntimeException will signal the client
    // that the bean cannot be obtained.
    return beanInstance.orElseThrow(() -> new NoSuchElementException("The bean " + beanName + " does not exist yet"));
  }

  /**
   * Returns an {@code Optional} instance of the given {@code beanName}. Unlike {@linkplain #get},
   * this method will not throw exception if the bean is not present. However, the client of this
   * method should check for present of the bean via the returned {@code Optional}, otherwise,
   * you'll get {@link NoSuchElementException}
   * @param beanName 
   *
   * @return an instance of {@code Optional}. Never returns {@code null}
   */
  public static <T> Optional<T> find(final String beanName) {
    try {
      return SecurityManager.getSecurityManager().executeAsSystem(
          () -> {
            FacesContext context = FacesContext.getCurrentInstance();

            @SuppressWarnings("unchecked")
            T beanInstance =
                (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);

            return Optional.ofNullable(beanInstance);
          });
    } catch (Exception failToFindBean) {
      throw new PortalException("Unable to find bean " + beanName, failToFindBean);
    }
  }

}
