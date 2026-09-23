package ch.ivy.addon.portalkit.fixture;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Builds a stub of an interface from a map of method name to result, because this project has no
 * mocking framework. A {@link Supplier} stub is invoked per call, a {@link Function} stub receives
 * the single argument the method was called with, and any other value is returned as is. A method
 * the map does not mention throws, so a test never passes on a silent default.
 */
public final class Fakes {

  private Fakes() {
  }

  public static <T> T fake(Class<T> type, Map<String, Object> stubs) {
    return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, (proxy, method, args) -> {
      switch (method.getName()) {
        case "equals":
          return proxy == args[0];
        case "hashCode":
          return System.identityHashCode(proxy);
        case "toString":
          return type.getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
        default:
          break;
      }
      Object stub = stubs.get(method.getName());
      if (stub instanceof Supplier<?> supplier) {
        return supplier.get();
      }
      if (stub instanceof Function<?, ?> && args != null && args.length == 1) {
        @SuppressWarnings("unchecked")
        Function<Object, Object> function = (Function<Object, Object>) stub;
        return function.apply(args[0]);
      }
      if (stubs.containsKey(method.getName())) {
        return stub;
      }
      throw new UnsupportedOperationException(type.getSimpleName() + "#" + method.getName());
    }));
  }
}
