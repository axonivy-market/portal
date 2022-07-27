package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames.Result;

@ManagedBean
@ViewScoped
public class ColumnManagementBean {
  private DashboardWidget widget;
  private List<ColumnModel> columnsBeforeSave;
  private List<DashboardColumnType> fieldTypes;
  private DashboardColumnType selectedFieldType;
  private List<CustomFieldType> customFieldTypes;
  private CustomFieldType selectedCustomFieldType;
  private List<String> fields;
  private String selectedField;
  private String numberFieldPattern;
  private String fieldDisplayName;
  
  @PostConstruct
  public void init() {
    this.fieldTypes = Arrays.asList(DashboardColumnType.STANDARD, DashboardColumnType.CUSTOM);
    this.selectedFieldType = DashboardColumnType.STANDARD;
    this.customFieldTypes = Arrays.asList(CustomFieldType.STRING, CustomFieldType.TEXT, CustomFieldType.NUMBER, CustomFieldType.TIMESTAMP);
    this.selectedCustomFieldType = CustomFieldType.STRING;
  }
  
  public void preRender(DashboardWidget widget) {
    this.widget = widget;
    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget; 
      this.columnsBeforeSave = new ArrayList<>(taskWidget.getColumns());
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseDashboardWidget = (CaseDashboardWidget) this.widget;
      this.columnsBeforeSave = new ArrayList<>(caseDashboardWidget.getColumns());
    }

    fetchFields();
  }

  private void resetValues() {
    this.selectedField = null;
    this.fieldDisplayName = null;
    this.numberFieldPattern = null;
  }
  
  public void save() {
    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget;
      List<TaskColumnModel> taskColumns = new ArrayList<>();
      columnsBeforeSave.forEach(column -> taskColumns.add((TaskColumnModel) column));
      taskWidget.setColumns(taskColumns);
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseDashboardWidget = (CaseDashboardWidget) this.widget;
      List<CaseColumnModel> caseColumns = new ArrayList<>();
      columnsBeforeSave.forEach(column -> caseColumns.add((CaseColumnModel) column));
      caseDashboardWidget.setColumns(caseColumns);
    }
    DashboardWidgetUtils.buildWidgetColumns(widget);
  }
  
  public void remove(ColumnModel col) {
    this.columnsBeforeSave.remove(col);
    fetchFields();
  }
  
  private List<String> standardFields() {
    List<String> standardFields = new ArrayList<>();
    if (widget.getType() == DashboardWidgetType.TASK) {
      for (DashboardStandardTaskColumn col : DashboardStandardTaskColumn.values()) {
        standardFields.add(col.getField());
      }
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      var enableCaseOwner = GlobalSettingService.getInstance().isCaseOwnerEnabled();
      for (DashboardStandardCaseColumn col : DashboardStandardCaseColumn.values()) {
        if (!enableCaseOwner && DashboardStandardCaseColumn.OWNER == col) {
          continue;
        }
        standardFields.add(col.getField());
      }
    }
    standardFields.sort(StringUtils::compare);
    return standardFields;
  }

  public void add() {
    ColumnModel columnModel = new ColumnModel();
    if (widget.getType() == DashboardWidgetType.TASK) {
      columnModel = TaskColumnModel.constructColumn(this.selectedFieldType, this.selectedField);
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      columnModel = CaseColumnModel.constructColumn(this.selectedFieldType, this.selectedField);
    }
    columnModel.setType(this.selectedFieldType);
    columnModel.setHeader(this.fieldDisplayName);
    columnModel.setField(this.selectedField);
    if (this.selectedFieldType == DashboardColumnType.CUSTOM) {
      columnModel.setFormat(DashboardColumnFormat.valueOf(this.selectedCustomFieldType.name()));
      columnModel.setPattern(numberFieldPattern);
    }
    this.columnsBeforeSave.add(columnModel);
    this.fields.remove(columnModel.getField());
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/fieldIsAdded", Arrays.asList(this.selectedField)), null);
    FacesContext.getCurrentInstance().addMessage("field-msg", msg);
    resetValues();
  }

  public void fetchFields() {
    if (this.selectedFieldType == DashboardColumnType.STANDARD) {
      this.fields = standardFields();
    } else {
      List<Result> customFieldNames = new ArrayList<>();
      if (widget.getType() == DashboardWidgetType.TASK) {
        customFieldNames = ICustomFieldNames.tasks().type(selectedCustomFieldType).executor().results();
      } else if (widget.getType() == DashboardWidgetType.CASE) {
        customFieldNames = ICustomFieldNames.cases().type(selectedCustomFieldType).executor().results();
      }
      this.fields = customFieldNames.stream().filter(this::doesNotExist).map(Result::name).sorted(StringUtils::compareIgnoreCase).collect(Collectors.toList());
    }
    List<String> existingFields = getExistingFields();
    this.fields = this.fields.stream().filter(isNotUsedIn(existingFields)).collect(Collectors.toList());
  }

  private Predicate<? super String> isNotUsedIn(List<String> existingFields) {
    return f -> CollectionUtils.isEmpty(existingFields) || !existingFields.contains(f);
  }
  
  public List<String> completeCustomFields(String query) {
    List<String> existingFields = getExistingFields();
    return this.fields.stream().filter(isNotUsedIn(existingFields)).filter(f -> StringUtils.containsIgnoreCase(f, query)).collect(Collectors.toList());
  }

  private boolean doesNotExist(Result customFieldName) {
    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget;
      return !taskWidget.getColumns().stream().map(ColumnModel::getField).anyMatch(field -> StringUtils.equals(field, customFieldName.name()));
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseDashboardWidget = (CaseDashboardWidget) this.widget;
      return !caseDashboardWidget.getColumns().stream().map(ColumnModel::getField).anyMatch(field -> StringUtils.equals(field, customFieldName.name()));
    }
    return false;
  }
  
  public List<ColumnModel> getColumnsBeforeSave() {
    return columnsBeforeSave;
  }
  
  public void setColumnsBeforeSave(List<ColumnModel> columnsBeforeSave) {
    this.columnsBeforeSave = columnsBeforeSave;
  }
  
  public List<DashboardColumnType> getFieldTypes() {
    return fieldTypes;
  }
  
  public void setFieldTypes(List<DashboardColumnType> fieldTypes) {
    this.fieldTypes = fieldTypes;
  }
  
  public DashboardColumnType getSelectedFieldType() {
    return selectedFieldType;
  }
  
  public void setSelectedFieldType(DashboardColumnType selectedFieldType) {
    this.selectedFieldType = selectedFieldType;
  }

  public List<CustomFieldType> getCustomFieldTypes() {
    return customFieldTypes;
  }

  public void setCustomFieldTypes(List<CustomFieldType> customFieldTypes) {
    this.customFieldTypes = customFieldTypes;
  }

  public CustomFieldType getSelectedCustomFieldType() {
    return selectedCustomFieldType;
  }

  public void setSelectedCustomFieldType(CustomFieldType selectedCustomFieldType) {
    this.selectedCustomFieldType = selectedCustomFieldType;
  }
  
  public List<String> getFields() {
    return fields;
  }
  
  public void setStandardFields(List<String> fields) {
    this.fields = fields;
  }
  
  public String getSelectedField() {
    return selectedField;
  }
  
  public void setSelectedField(String selectedField) {
    this.selectedField = selectedField;
  }
  
  public String getNumberFieldPattern() {
    return numberFieldPattern;
  }

  public void setNumberFieldPattern(String numberFieldPattern) {
    this.numberFieldPattern = numberFieldPattern;
  }
  
  public String getFieldDisplayName() {
    return fieldDisplayName;
  }
  
  public void setFieldDisplayName(String fieldDisplayName) {
    this.fieldDisplayName = fieldDisplayName;
  }
  
  public List<String> getExistingFields() {
    return this.columnsBeforeSave.stream().map(ColumnModel::getField).collect(Collectors.toList());
  }

  public void handleVisibility(ColumnModel column) {
    column.setVisible(!column.getVisible());
  }
}
