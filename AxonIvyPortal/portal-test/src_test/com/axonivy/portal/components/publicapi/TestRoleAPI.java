package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.IRole;

@IvyTest
class TestRoleAPI {

  @Test
  void setAndRemoveRoleProperty_doNotThrowAndKeepStateConsistent() {
    RoleStorage storage = new RoleStorage();
    IRole role = createRole(storage);

    assertThatCode(() -> {
      RoleAPI.setProperty(role, "foo", "bar");
      RoleAPI.removeProperty(role, "foo");
    }).doesNotThrowAnyException();

    assertThat(storage.get("foo")).isNull();
  }

  private static IRole createRole(RoleStorage storage) {
    return (IRole) Proxy.newProxyInstance(IRole.class.getClassLoader(), new Class<?>[] {IRole.class},
        (proxy, method, args) -> switch (method.getName()) {
          case "setProperty" -> {
            storage.put((String) args[0], (String) args[1]);
            yield null;
          }
          case "removeProperty" -> {
            storage.remove((String) args[0]);
            yield null;
          }
          default -> null;
        });
  }

  private static final class RoleStorage {
    private final Map<String, String> values = new HashMap<>();

    String get(String name) {
      return values.get(name);
    }

    void put(String name, String value) {
      values.put(name, value);
    }

    void remove(String name) {
      values.remove(name);
    }
  }
}
