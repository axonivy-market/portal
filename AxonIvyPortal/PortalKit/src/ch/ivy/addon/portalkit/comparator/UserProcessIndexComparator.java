package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;
import java.util.function.Function;

import ch.ivy.addon.portalkit.configuration.UserProcess;

public class UserProcessIndexComparator {

  private UserProcessIndexComparator() {}

  public static <U extends Comparable<? super U>> Comparator<UserProcess> comparatorNullsLast(
      Function<UserProcess, U> function) {
    return Comparator.comparing(function, Comparator.nullsLast(Comparator.naturalOrder()));
  }

}
