package com.axonivy.portal.components.publicapi;

import static com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.BusinessDetailsDTO;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

@IvyTest
class TestBusinessDetailsAPI {

  @Test
  void createWithDto_setsBusinessDetailsValueOnCase() {
    FieldStorage storage = new FieldStorage();
    ICase iCase = createCase(storage);
    BusinessDetailsDTO dto = BusinessDetailsDTO.builder().iCase(iCase).path("https://example.com/path").build();

    BusinessDetailsAPI.create(dto);

    assertThat(storage.get(BUSINESS_DETAILS)).isEqualTo("https://example.com/path");
  }

  @Test
  void createWithStringLinkAndParameters_doesNotThrow() {
    assertThatCode(() -> BusinessDetailsAPI.create("https://example.com/path", Map.of("foo", "bar")))
        .doesNotThrowAnyException();
  }

  private static ICase createCase(FieldStorage storage) {
    return (ICase) Proxy.newProxyInstance(ICase.class.getClassLoader(), new Class<?>[] {ICase.class},
        (proxy, method, args) -> switch (method.getName()) {
          case "customFields" -> createCustomFields(storage);
          default -> defaultValue(method.getReturnType());
        });
  }

  private static ICustomFields createCustomFields(FieldStorage storage) {
    return (ICustomFields) Proxy.newProxyInstance(ICustomFields.class.getClassLoader(), new Class<?>[] {ICustomFields.class},
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
          case "set" -> {
            storage.put(name, (String) args[0]);
            yield null;
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
  }
}
