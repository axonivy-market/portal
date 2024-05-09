package ch.ivy.addon.portalkit.util;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public final class ListUtilities {
  private ListUtilities() {}
  
  public static <T, R> List<R> transformList(List<T> list, Function<? super T,? extends R> mapper) {
    return CollectionUtils.emptyIfNull(list)
          .stream()
          .map(mapper)
          .collect(Collectors.toList());
  }
  
  public static <T> List<T> filterList(List<T> list, Predicate<? super T> predicate) {
    return CollectionUtils.emptyIfNull(list)
          .stream()
          .filter(predicate)
          .collect(Collectors.toList());
  }
}
