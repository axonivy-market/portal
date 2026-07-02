package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

@IvyTest
class TestCaseAPI {

  @Test
  void setHidePropertyToHideInPortal_setsHideFieldOnCase() {
    FieldStorage storage = new FieldStorage();
    ICase iCase = createCase(storage);

    CaseAPI.setHidePropertyToHideInPortal(iCase);

    assertThat(storage.get("HIDE")).isEqualTo("HIDE");
  }

  @Test
  void removeHidePropertyToDisplayInPortal_clearsHideFieldOnCase() {
    FieldStorage storage = new FieldStorage();
    ICase iCase = createCase(storage);

    assertThatCode(() -> {
      CaseAPI.setHidePropertyToHideInPortal(iCase);
      CaseAPI.removeHidePropertyToDisplayInPortal(iCase);
    }).doesNotThrowAnyException();

    assertThat(storage.get("HIDE")).isNull();
  }

  private static ICase createCase(FieldStorage storage) {
    return (ICase) Proxy.newProxyInstance(ICase.class.getClassLoader(), new Class<?>[] {ICase.class},
        (proxy, method, args) -> switch (method.getName()) {
          case "customFields" -> createCustomFields(storage);
          default -> defaultValue(method.getReturnType());
        });
  }

  private static ICustomFields createCustomFields(FieldStorage storage) {
    return (ICustomFields) Proxy.newProxyInstance(ICustomFields.class.getClassLoader(),
        new Class<?>[] {ICustomFields.class},
        (proxy, method, args) -> switch (method.getName()) {
          case "stringField" -> createStringField(storage, (String) args[0]);
          default -> defaultValue(method.getReturnType());
        });
  }

  private static ICustomStringField createStringField(FieldStorage storage, String name) {
    return (ICustomStringField) Proxy.newProxyInstance(ICustomStringField.class.getClassLoader(),
        new Class<?>[] {ICustomStringField.class},
        (proxy, method, args) -> switch (method.getName()) {
          case "get" -> storage.get(name);
          case "getOrNull" -> storage.get(name);
          case "getOrDefault" -> args == null || args.length == 0 ? null : args[0];
          case "set" -> {
            if (args == null || args.length == 0) {
              storage.remove(name);
            } else {
              storage.put(name, (String) args[0]);
            }
            yield null;
          }
          case "compareAndSet" -> {
            String oldValue = storage.get(name);
            if (oldValue == null || !oldValue.equals(args[0])) {
              storage.put(name, (String) args[1]);
              yield false;
            }
            yield true;
          }
          case "name" -> name;
          default -> defaultValue(method.getReturnType());
        });
  }

  private static Object defaultValue(Class<?> returnType) {
    if (returnType == boolean.class) {
      return false;
    }
    if (returnType == int.class) {
      return 0;
    }
    if (returnType == long.class) {
      return 0L;
    }
    if (returnType == double.class) {
      return 0D;
    }
    if (returnType.isPrimitive()) {
      return 0;
    }
    return null;
  }

  private static final class FieldStorage {
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
