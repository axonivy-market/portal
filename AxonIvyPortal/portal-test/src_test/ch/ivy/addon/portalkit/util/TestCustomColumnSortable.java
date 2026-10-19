package ch.ivy.addon.portalkit.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldValues;

@IvyTest
class TestCustomColumnSortable {

  private ICustomFieldMeta meta(String name, CustomFieldType type, boolean customAction) {
    return new ICustomFieldMeta() {
      @Override
      public String name() {
        return name;
      }

      @Override
      public String label() {
        return name;
      }

      @Override
      public String description() {
        return "";
      }

      @Override
      public String category() {
        return "";
      }

      @Override
      public CustomFieldType type() {
        return type;
      }

      @Override
      public boolean isHidden() {
        return false;
      }

      @Override
      public String attribute(String attributeName) {
        return "IsCustomAction".equals(attributeName) ? String.valueOf(customAction) : null;
      }

      @Override
      public Iterable<String> attributeNames() {
        return Set.of();
      }

      @Override
      public <T> ICustomFieldValues<T> values() {
        return null;
      }
    };
  }

  private TaskColumnModel buildCustom(String field, CustomFieldType type, boolean customAction) {
    TaskColumnModel column = new TaskColumnModel();
    column.setField(field);
    column.setType(DashboardColumnType.CUSTOM);
    column.initDefaultValue();
    DashboardWidgetUtils.buildCustomColumn(Set.of(meta(field, type, customAction)), column, field);
    return column;
  }

  private CaseColumnModel buildCustomCase(String field, CustomFieldType type, boolean customAction) {
    CaseColumnModel column = new CaseColumnModel();
    column.setField(field);
    column.setType(DashboardColumnType.CUSTOM);
    column.initDefaultValue();
    DashboardWidgetUtils.buildCustomColumn(Set.of(meta(field, type, customAction)), column, field);
    return column;
  }

  @ParameterizedTest
  @EnumSource(value = CustomFieldType.class, names = {"STRING", "NUMBER", "TIMESTAMP"})
  void caseOrderableCustomFieldTypesStaySortable(CustomFieldType type) {
    assertThat(buildCustomCase("Field_" + type.name(), type, false).getSortable()).isTrue();
  }

  @Test
  void caseTextCustomFieldIsNotSortable() {
    assertThat(buildCustomCase("Remark", CustomFieldType.TEXT, false).getSortable()).isFalse();
  }

  @Test
  void caseHideMarkerFieldIsNotSortable() {
    assertThat(buildCustomCase("HIDE", CustomFieldType.STRING, false).getSortable()).isFalse();
  }

  @Test
  void caseCustomActionFieldIsNotSortable() {
    assertThat(buildCustomCase("DestroyCaseAction", CustomFieldType.STRING, true).getSortable()).isFalse();
  }

  @Test
  void caseDeprecatedCustomFieldWithoutMetaIsNotSortable() {
    CaseColumnModel column = new CaseColumnModel();
    column.setField("GoneField");
    column.setType(DashboardColumnType.CUSTOM);
    column.initDefaultValue();
    DashboardWidgetUtils.buildCustomColumn(Set.of(), column, "GoneField");

    assertThat(column.getSortable()).isFalse();
  }

  @ParameterizedTest
  @EnumSource(value = CustomFieldType.class, names = {"STRING", "NUMBER", "TIMESTAMP"})
  void orderableCustomFieldTypesStaySortable(CustomFieldType type) {
    assertThat(buildCustom("Field_" + type.name(), type, false).getSortable()).isTrue();
  }

  @Test
  void textCustomFieldIsNotSortable() {
    assertThat(buildCustom("Remark", CustomFieldType.TEXT, false).getSortable()).isFalse();
  }

  @Test
  void hideMarkerFieldIsNotSortable() {
    assertThat(buildCustom("HIDE", CustomFieldType.STRING, false).getSortable()).isFalse();
  }

  @Test
  void customActionFieldIsNotSortable() {
    assertThat(buildCustom("DoIt", CustomFieldType.STRING, true).getSortable()).isFalse();
  }

  @Test
  void deprecatedCustomFieldWithoutMetaIsNotSortable() {
    TaskColumnModel column = new TaskColumnModel();
    column.setField("GoneField");
    column.setType(DashboardColumnType.CUSTOM);
    column.initDefaultValue();
    DashboardWidgetUtils.buildCustomColumn(Set.of(), column, "GoneField");

    assertThat(column.getSortable()).isFalse();
  }
}
