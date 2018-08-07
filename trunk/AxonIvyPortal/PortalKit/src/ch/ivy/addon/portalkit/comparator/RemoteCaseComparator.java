package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;
import java.util.function.Function;

import ch.ivy.addon.portalkit.bo.RemoteCase;

public final class RemoteCaseComparator {


  public static <U extends Comparable<? super U>> Comparator<? super RemoteCase> naturalOrderNullsFirst(
      Function<RemoteCase, U> function) {
    return Comparator.comparing(function, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

}
