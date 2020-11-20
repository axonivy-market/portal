package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldNames.Result;

@ManagedBean
@ViewScoped
public class TaskWidgetConfigurationBean {
  private List<TaskState> states;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> responsibles;
  private CheckboxTreeNode categoryTree;
  private CheckboxTreeNode[] categoryNodes;
  private TaskDashboardWidget widget;
  
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
    this.states = Arrays.asList(TaskState.values());
    this.priorities = Arrays.asList(WorkflowPriority.values());
    this.responsibles = new ArrayList<>();
    this.fieldTypes = Arrays.asList(DashboardColumnType.STANDARD, DashboardColumnType.CUSTOM);
    this.selectedFieldType = DashboardColumnType.STANDARD;
    this.customFieldTypes = Arrays.asList(CustomFieldType.STRING, CustomFieldType.TEXT, CustomFieldType.NUMBER, CustomFieldType.TIMESTAMP);
    this.selectedCustomFieldType = CustomFieldType.STRING;
  }
  
  public void preRender(TaskDashboardWidget widget) {
    this.widget = widget;
    fetchFields();
    buildCategoryTree();
  }
  
  private List<String> standardFields() {
    List<String> standardFields = new ArrayList<>();
    for (DashboardStandardTaskColumn col : DashboardStandardTaskColumn.values()) {
      standardFields.add(col.getField());
    }
    return standardFields;
  }
  
  public void addCustomField() {
    ColumnModel columnModel = new ColumnModel();
    columnModel.setType(this.selectedFieldType);
    columnModel.setHeader(this.fieldDisplayName);
    columnModel.setField(this.selectedField);
    columnModel.setFormat(DashboardColumnFormat.valueOf(this.selectedCustomFieldType.name()));
    columnModel.setPattern(numberFieldPattern);
    this.widget.getColumns().add(columnModel);
  }
  
  public void fetchFields() {
    Ivy.log().error("aa {0}", this.selectedFieldType);
    if (this.selectedFieldType == DashboardColumnType.STANDARD) {
      this.fields = standardFields();
    } else {
      List<Result> customFieldNames = ICustomFieldNames.tasks().type(selectedCustomFieldType).executor().results();
      this.fields = customFieldNames.stream().filter(this::doesNotExist).map(Result::name).collect(Collectors.toList());
    }
  }
  
  private boolean doesNotExist(Result customFieldName) {
    return !this.widget.getColumns().stream().map(ColumnModel::getField).anyMatch(f -> StringUtils.equals(f, customFieldName.name()));
  }
  
  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    this.widget.setCategories(CategoryUtils.getCategoryPaths(categoryNodes));
  }
  
  private void buildCategoryTree() {
    this.categoryTree = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
    this.categoryNodes = CategoryUtils.recoverSelectedCategories(this.categoryTree, this.widget.getCategories());
  }

  public String formatName(SecurityMemberDTO responsible) {
    String responsibleName = "";
    if (responsible != null) {
      if (StringUtils.isBlank(responsible.getDisplayName())) {
        responsibleName = responsible.getName();
      } else {
        responsibleName = responsible.getDisplayName() + " (" + responsible.getName() + ")";
      }
      return responsible.isEnabled()? responsibleName : Ivy.cms().co("/Labels/disabledUserPrefix") + " " + responsibleName;
    }
    return responsibleName;
  }
  
  public List<SecurityMemberDTO> completeResponsibles(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState" + state.toString());
  }

  public String getUserFriendlyTaskPriority(WorkflowPriority priority) {
    if (priority == null) {
      return StringUtils.EMPTY;
    }
    switch (priority) {
      case LOW:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/LOW_LOWERCASE");
      case NORMAL:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/NORMAL_LOWERCASE");
      case HIGH:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/HIGH_LOWERCASE");
      case EXCEPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/EXCEPTION_LOWERCASE");
      default:
        return StringUtils.EMPTY;
    }
  }

  public List<TaskState> getStates() {
    return states;
  }

  public void setStates(List<TaskState> states) {
    this.states = states;
  }

  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<SecurityMemberDTO> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<SecurityMemberDTO> responsibles) {
    this.responsibles = responsibles;
  }
  
  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }
  
  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
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
