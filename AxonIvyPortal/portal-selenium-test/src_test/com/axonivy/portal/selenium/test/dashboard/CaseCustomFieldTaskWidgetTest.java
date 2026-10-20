package com.axonivy.portal.selenium.test.dashboard;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.util.CaseQueryUtils;

import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.exporter.TaskDashboardExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

/** Tests the widget/export value paths in the engine without opening a browser. */
@IvyTest
class CaseCustomFieldTaskWidgetTest {

  private static final String STRATEGY_VARIABLE = "Portal.Tasks.RelatedCaseQueryStrategy";
  private static final String FIELD = "CustomerName";
  private static final List<String> STRATEGIES = List.of("LEGACY", "", "   ", "SPLIT");
  private static final List<DashboardColumnType> TYPES = List.of(DashboardColumnType.CUSTOM_CASE,
      DashboardColumnType.CUSTOM_BUSINESS_CASE, DashboardColumnType.CUSTOM);
  private static final List<DashboardColumnFormat> FORMATS = List.of(DashboardColumnFormat.STRING,
      DashboardColumnFormat.TEXT, DashboardColumnFormat.NUMBER, DashboardColumnFormat.TIMESTAMP);

  private String originalStrategy;

  @BeforeEach
  void rememberStrategy() {
    originalStrategy = Ivy.var().get(STRATEGY_VARIABLE);
  }

  @AfterEach
  void restoreStrategy() {
    Ivy.var().set(STRATEGY_VARIABLE, originalStrategy);
  }

  @Test
  void defaultQueryRemainsLegacy() {
    assertEquals("LEGACY", Ivy.var().get(STRATEGY_VARIABLE));
    assertEquals(CaseQuery.create().toString(),
        CaseQueryUtils.initCaseQuery(DashboardColumnType.CUSTOM_CASE).toString());
  }

  @Test
  void widgetAndExportRespectSelectedStrategy() {
    for (String strategy : STRATEGIES) {
      Ivy.var().set(STRATEGY_VARIABLE, strategy);
      for (DashboardColumnFormat format : FORMATS) {
        for (DashboardColumnType type : TYPES) {
          for (boolean mainCase : List.of(true, false)) {
            assertCustomFieldValue(strategy, format, type, mainCase);
          }
        }
      }
    }
  }

  private void assertCustomFieldValue(String strategy, DashboardColumnFormat format,
      DashboardColumnType type, boolean mainCase) {
    ITask task = task(format, mainCase);
    Object expected = switch (type) {
      case CUSTOM_CASE -> "SPLIT".equals(strategy) && mainCase ? null : value(format, mainCase ? 1 : 2);
      case CUSTOM_BUSINESS_CASE -> value(format, 1);
      default -> value(format, 3);
    };
    Object displayExpected = expected instanceof Number number ? Long.toString(number.longValue()) : expected;

    String scenario = strategy + ": " + format + " " + type + ", main case=" + mainCase;
    assertAll(scenario,
        () -> assertEquals(displayExpected, column(format, type, FIELD).display(task), "widget value"),
        () -> assertEquals(expected, export(format, type, FIELD, task), "export value"));
  }

  @Test
  void missingValuesStayBlankInBothPaths() {
    for (String strategy : STRATEGIES) {
      Ivy.var().set(STRATEGY_VARIABLE, strategy);
      for (boolean mainCase : List.of(true, false)) {
        for (DashboardColumnType type : TYPES) {
          ITask task = task(DashboardColumnFormat.STRING, mainCase);
          assertEquals(null, column(DashboardColumnFormat.STRING, type, "missing").display(task));
          assertEquals(null, export(DashboardColumnFormat.STRING, type, "missing", task));
        }
      }
    }
  }

  @Test
  void queryScopesAreUnchanged() {
    for (String strategy : STRATEGIES) {
      Ivy.var().set(STRATEGY_VARIABLE, strategy);
      for (DashboardColumnType type : List.of(DashboardColumnType.CUSTOM_CASE, DashboardColumnType.CUSTOM_BUSINESS_CASE)) {
        CaseQuery expected = !"SPLIT".equals(strategy) ? CaseQuery.create()
            : type == DashboardColumnType.CUSTOM_CASE ? CaseQuery.subCases() : CaseQuery.businessCases();
        assertEquals(expected.toString(), CaseQueryUtils.initCaseQuery(type).toString());
      }
    }
  }

  private static TaskColumnModel column(DashboardColumnFormat format, DashboardColumnType type, String field) {
    TaskColumnModel column = new TaskColumnModel();
    column.setType(type);
    column.setField(field);
    column.setFormat(format);
    column.setPattern("0");
    return column;
  }

  private static Object export(DashboardColumnFormat format, DashboardColumnType type, String field, ITask task) {
    String key = format.name() + "__" + field + "__Customer__" + type.name();
    return new TaskDashboardExporter(List.of(key), List.of(), "Your Tasks").getColumnValue(key, task);
  }

  private static Object value(DashboardColumnFormat format, int scope) {
    return switch (format) {
      case NUMBER -> 40.0 + scope;
      case TIMESTAMP -> new Date(1000L * scope);
      default -> "customer-" + scope;
    };
  }

  private static ITask task(DashboardColumnFormat format, boolean mainCase) {
    ICustomFields mainFields = customFields(value(format, 1));
    IBusinessCase businessCase = fake(IBusinessCase.class, (proxy, method, args) -> switch (method.getName()) {
      case "isBusinessCase" -> true;
      case "getBusinessCase" -> proxy;
      case "customFields" -> mainFields;
      default -> throw new UnsupportedOperationException(method.toString());
    });
    ICase taskCase = mainCase ? businessCase : fake(ICase.class, (proxy, method, args) -> switch (method.getName()) {
      case "isBusinessCase" -> false;
      case "getBusinessCase" -> businessCase;
      case "customFields" -> customFields(value(format, 2));
      default -> throw new UnsupportedOperationException(method.toString());
    });
    return fake(ITask.class, (proxy, method, args) -> switch (method.getName()) {
      case "getCase" -> taskCase;
      case "customFields" -> customFields(value(format, 3));
      default -> throw new UnsupportedOperationException(method.toString());
    });
  }

  private static ICustomFields customFields(Object value) {
    Map<String, Object> fields = Map.of(FIELD, value);
    return fake(ICustomFields.class, (proxy, method, args) -> switch (method.getName()) {
      case "stringField", "textField", "numberField", "timestampField" ->
        fake(method.getReturnType(), (field, getter, arguments) -> {
          if ("getOrNull".equals(getter.getName())) {
            return fields.get(args[0]);
          }
          throw new UnsupportedOperationException(getter.toString());
        });
      default -> throw new UnsupportedOperationException(method.toString());
    });
  }

  private static <T> T fake(Class<T> type, InvocationHandler handler) {
    return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, handler));
  }
}
