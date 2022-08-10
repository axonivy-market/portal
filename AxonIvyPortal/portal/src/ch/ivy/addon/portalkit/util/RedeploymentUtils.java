package ch.ivy.addon.portalkit.util;

import java.util.Collection;
import java.util.Iterator;

public final class RedeploymentUtils {

  private RedeploymentUtils() {}

  public static <T> void filterObjectOfCurrentClassLoader(Collection<T> list, Class<T> clazz) {
    Iterator<T> itr = list.iterator();
    while (itr.hasNext()) {
      if (!clazz.isInstance(itr.next())) {
        itr.remove();
      }
    }
  }
}
