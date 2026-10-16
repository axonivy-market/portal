package ch.ivy.addon.portalkit.fixture;

import static ch.ivy.addon.portalkit.fixture.Fakes.fake;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

/**
 * Stubs of the workflow API, each built from {@link Fakes#fake}. Custom fields are given as a map
 * of field name to value; a field the map does not mention reads as null, the way an unset custom
 * field does.
 */
public final class WorkflowFakes {

  private WorkflowFakes() {
  }

  public static ITask task(ICase taskCase, Map<String, String> taskFields) {
    return fake(ITask.class, Map.of("getCase", taskCase, "customFields", customFields(taskFields)));
  }

  public static IBusinessCase businessCase(Map<String, String> fields) {
    Map<String, Object> stubs = new HashMap<>();
    stubs.put("isBusinessCase", true);
    stubs.put("customFields", customFields(fields));
    IBusinessCase caze = fake(IBusinessCase.class, stubs);
    stubs.put("getBusinessCase", caze);
    return caze;
  }

  public static ICase subCaseOf(IBusinessCase businessCase, Map<String, String> fields) {
    return fake(ICase.class, Map.of("isBusinessCase", false, "customFields", customFields(fields),
        "getBusinessCase", businessCase));
  }

  private static ICustomFields customFields(Map<String, String> values) {
    Function<Object, Object> byName = name -> stringField(values.get(name));
    return fake(ICustomFields.class, Map.of("stringField", byName, "textField", byName));
  }

  private static ICustomStringField stringField(String value) {
    Map<String, Object> stubs = new HashMap<>();
    stubs.put("getOrNull", value);
    return fake(ICustomStringField.class, stubs);
  }
}
