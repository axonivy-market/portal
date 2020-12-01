package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames.Result;

@ManagedBean
@ViewScoped
public class ColumnManagementBean {
  private TaskDashboardWidget widget;
  private List<ColumnModel> columnsBeforeSave;
  private List<DashboardColumnType> fieldTypes;
  private DashboardColumnType selectedFieldType;
  private List<CustomFieldType> customFieldTypes;
  private CustomFieldType selectedCustomFieldType;
  private List<String> fields;
  private String selectedField;
  private String numberFieldPattern;
  private String fieldDisplayName;
  private List<String> standardFields;
  
  @PostConstruct
  public void init() {
    this.fieldTypes = Arrays.asList(DashboardColumnType.STANDARD, DashboardColumnType.CUSTOM);
    this.selectedFieldType = DashboardColumnType.STANDARD;
    this.customFieldTypes = Arrays.asList(CustomFieldType.STRING, CustomFieldType.TEXT, CustomFieldType.NUMBER, CustomFieldType.TIMESTAMP);
    this.selectedCustomFieldType = CustomFieldType.STRING;
    this.standardFields = standardFields();
  }
  
  public void preRender(TaskDashboardWidget widget) {
    this.widget = widget;
    this.columnsBeforeSave = new ArrayList<>(this.widget.getColumns());
    fetchFields();
  }
  
  public void save() {
    this.widget.setColumns(columnsBeforeSave);
  }
  
  public void remove(ColumnModel col) {
    this.columnsBeforeSave.remove(col);
  }
  
  private List<String> standardFields() {
    List<String> standardFields = new ArrayList<>();
    for (DashboardStandardTaskColumn col : DashboardStandardTaskColumn.values()) {
      standardFields.add(col.getField());
    }
    standardFields.sort(StringUtils::compare);
    return standardFields;
  }
  
  public void add() {
    ColumnModel columnModel = new ColumnModel();
    columnModel.setType(this.selectedFieldType);
    columnModel.setHeader(this.fieldDisplayName);
    columnModel.setField(this.selectedField);
    if (this.selectedFieldType == DashboardColumnType.CUSTOM) {
      columnModel.setFormat(DashboardColumnFormat.valueOf(this.selectedCustomFieldType.name()));
      columnModel.setPattern(numberFieldPattern);
    }
    this.columnsBeforeSave.add(columnModel);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The " + columnModel.getField() + " field is added", null);
    FacesContext.getCurrentInstance().addMessage("field-msg", msg);
  }
  
  public void fetchFields() {
    if (this.selectedFieldType == DashboardColumnType.STANDARD) {
      this.fields = this.standardFields;
    } else {
      List<Result> customFieldNames = ICustomFieldNames.tasks().type(selectedCustomFieldType).executor().results();
      this.fields = customFieldNames.stream().filter(this::doesNotExist).map(Result::name).sorted(StringUtils::compareIgnoreCase).collect(Collectors.toList());
    }
  }
  
  public List<String> completeCustomFields(String query) {
    return this.fields.stream().filter(f -> StringUtils.containsIgnoreCase(f, query)).collect(Collectors.toList());
  }
  
  private boolean doesNotExist(Result customFieldName) {
    return !this.widget.getColumns().stream().map(ColumnModel::getField).anyMatch(f -> StringUtils.equals(f, customFieldName.name()));
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
}
