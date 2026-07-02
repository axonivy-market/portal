package com.axonivy.portal.components.publicapi;

import static com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.dto.AiResultDTO;
import com.axonivy.portal.components.dto.BusinessDetailsDTO;
import com.axonivy.portal.components.enums.AIState;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

@IvyTest
class TestAdditionalPublicApi {

  @Test
  void generateExecutableResult_wrapsTheLinkInExecuteTag() {
    assertThat(AiAssistantAPI.generateExecutableResult("/start/process.ivp"))
        .isEqualTo("<execute>/start/process.ivp</execute>");
  }

  @Test
  void generateErrorAiResult_setsErrorStateAndIncludesDetails() {
    AiResultDTO result = AiAssistantAPI.generateErrorAiResult(new IllegalStateException("boom"), "failed");

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).contains("failed").contains("boom");
  }

  @Test
  void createSomethingWentWrongError_usesCmsMessageAndErrorState() {
    AiResultDTO result = AiAssistantAPI.createSomethingWentWrongError();

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).isNotBlank();
  }

  @Test
  void findRelativeUrlByProcessStartFriendlyRequestPath_returnsPortalRelativeUrl() {
    String url = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(
        "Start Processes/PortalStart/DefaultApplicationHomePage.ivp");

    assertThat(url).isNotNull();
  }

  @Test
  void findStartableLinkByUserFriendlyRequestPath_returnsPortalRelativeUrl() {
    String url = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(
        "Start Processes/PortalStart/DefaultApplicationHomePage.ivp");

    assertThat(url).isNotNull();
  }

  @Test
  void getPasswordResetUrl_containsTokenAndUsernameParameters() {
    String url = PortalNavigatorAPI.getPasswordResetUrl("abc123", "demo");

    assertThat(url).contains("token=abc123").contains("username=demo");
  }

  @Test
  void buildPortalStaticPageUrl_containsDefaultFramePageReference() {
    String url = PortalNavigatorAPI.buildPortalStaticPageUrl("/some/page.xhtml");

    assertThat(url).isNotBlank();
    assertThat(url).contains("DefaultFramePage.ivp");
    assertThat(url).contains("relativeUrl");
  }

  @Test
  void buildUrlToPortalCaseDetailsPageById_returnsEmptyStringWhenCaseIsMissing() {
    String url = PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageById(999999L);

    assertThat(url).isEmpty();
  }

  @Test
  void buildUrlToPortalTaskDetailsPageById_returnsEmptyStringWhenTaskIsMissing() {
    String url = PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageById(999999L);

    assertThat(url).isEmpty();
  }

  @Test
  void buildUrlToPortalCaseDetailsPageByUUID_returnsEmptyForUnknownUuid() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID("does-not-exist"))
        .isEmpty();
  }

  @Test
  void buildUrlToPortalTaskDetailsPageByUUID_returnsEmptyForUnknownUuid() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID("does-not-exist"))
        .isEmpty();
  }

  @Test
  void createWithExternalLink_setsBusinessDetailsValueOnCase() {
    FieldStorage storage = new FieldStorage();
    ICase iCase = createCase(storage);
    BusinessDetailsDTO dto = BusinessDetailsDTO.builder().iCase(iCase).path("https://example.com/path").build();

    assertThatCode(() -> BusinessDetailsAPI.create(dto)).doesNotThrowAnyException();

    assertThat(storage.get(BUSINESS_DETAILS)).isEqualTo("https://example.com/path");
  }

  @Test
  void createSideStepTaskName_returnsNullWhenOriginalTaskIsMissing() {
    assertThat(SideStepAPI.createSideStepTaskName("missing-task", "some-cms-uri")).isNull();
  }

  @Test
  void finishSideStep_doesNotThrowWhenOriginalTaskIsMissing() {
    assertThatCode(() -> SideStepAPI.finishSideStep("missing-task", false)).doesNotThrowAnyException();
  }

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

  @Test
  void navigateToUrl_executesEscapedScriptForDecodedUrl() {
    AtomicReference<String> executed = new AtomicReference<>();
    PrimeFaces original = PrimeFaces.current();
    PrimeFaces.setCurrent(new PrimeFaces() {
      @Override
      public void executeScript(String script) {
        executed.set(script);
      }
    });

    try {
      PortalNavigatorInFrameAPI.navigateToUrl("https://example.com/a%20b");
    } finally {
      PrimeFaces.setCurrent(original);
    }

    assertThat(executed.get()).contains("parent.redirectToUrlCommand").contains("a b");
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
          case "set" -> {
            storage.put(name, (String) args[0]);
            yield null;
          }
          case "name" -> name;
          default -> defaultValue(method.getReturnType());
        });
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
