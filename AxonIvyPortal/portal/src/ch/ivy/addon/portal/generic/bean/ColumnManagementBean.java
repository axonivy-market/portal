package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
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
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

@ManagedBean
@ViewScoped
public class ColumnManagementBean implements Serializable {

  private static final long serialVersionUID = -4406460802168467529L;
  private static final String NO_CATEGORY_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/noCategory";

  private DashboardWidget widget;
  private List<ColumnModel> columnsBeforeSave;
  private List<DashboardColumnType> fieldTypes;
  private DashboardColumnType selectedFieldType;
  private List<String> customFieldCategories;
  private String selectedCustomFieldCategory;
  private Set<ICustomFieldMeta> customFieldNames;
  private ICustomFieldMeta selectedCustomFieldMeta;
  private CustomFieldType selectedCustomFieldType;
  private List<String> fields;
  private String selectedField;
  private String numberFieldPattern;
  private String fieldDisplayName;
  private String fieldDescription;

  public void init() {
    this.fieldTypes = Arrays.asList(DashboardColumnType.STANDARD, DashboardColumnType.CUSTOM);
    this.selectedFieldType = DashboardColumnType.STANDARD;
    this.selectedCustomFieldType = CustomFieldType.STRING;
    this.customFieldCategories = null;
    this.selectedCustomFieldCategory = null;
    this.customFieldNames = null;
  }

  public void preRender(DashboardWidget widget) {
    resetValues();
    init();
    this.widget = widget;
    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = (TaskDashboardWidget) this.widget; 
      this.columnsBeforeSave = new ArrayList<>(taskWidget.getColumns());
    }
    if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseDashboardWidget = (CaseDashboardWidget) this.widget;
      this.columnsBeforeSave = new ArrayList<>(caseDashboardWidget.getColumns());
    }
  }

  private void resetValues() {
    this.selectedField = null;
    this.fieldDisplayName = null;
    this.fieldDescription = null;
    this.numberFieldPattern = null;
  }

  public List<String> completeCategoriesSelection(String query) {
    return getCustomFieldCategories().stream().filter(cat -> StringUtils.containsIgnoreCase(cat, query))
        .collect(Collectors.toList());
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
    this.columnsBeforeSave.removeIf(column -> column.getField().equals(col.getField()));
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
    }
    if (widget.getType() == DashboardWidgetType.CASE) {
      columnModel = CaseColumnModel.constructColumn(this.selectedFieldType, this.selectedField);
    }
    columnModel.initDefaultValue();
    columnModel.setHeader(this.fieldDisplayName);
    columnModel.setField(this.selectedField);
    if (this.selectedFieldType == DashboardColumnType.CUSTOM) {
      columnModel.setType(selectedFieldType);
      columnModel.setFormat(DashboardColumnFormat.valueOf(selectedFieldType.name()));
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
    if (selectedFieldType == DashboardColumnType.STANDARD) {
      fields = standardFields();
    } else {
      resetValues();
    }
    List<String> existingFields = getExistingFields();
    this.fields = this.fields.stream().filter(isNotUsedIn(existingFields)).collect(Collectors.toList());
  }

  private Predicate<? super String> isNotUsedIn(List<String> existingFields) {
    return f -> CollectionUtils.isEmpty(existingFields) || !existingFields.contains(f);
  }

  public List<String> completeCustomFields(String query) {
    return getCustomFieldNames().stream()
          .filter(meta -> !meta.isHidden())
          .filter(filterCustomFieldByCategory())
          .map(ICustomFieldMeta::name).filter(isNotUsedIn(getExistingFields()))
          .sorted().filter(f -> StringUtils.containsIgnoreCase(f, query))
          .collect(Collectors.toList());
  }

  public void onSelectCustomField() {
    findCustomFieldMeta().ifPresent(meta -> {
      this.fieldDescription = meta.description();
      this.fieldDisplayName = meta.label();
      this.selectedCustomFieldType = meta.type();
    });
  }

  private Predicate<? super ICustomFieldMeta> filterCustomFieldByCategory() {
    return meta -> {
      if (StringUtils.isNoneBlank(selectedCustomFieldCategory)) {
        if (selectedCustomFieldCategory.equalsIgnoreCase(Ivy.cms().co(NO_CATEGORY_CMS))) {
          return StringUtils.equals(meta.category(), EMPTY);
        }
        return StringUtils.equals(meta.category(), selectedCustomFieldCategory);
      }
      return true;
    };
  }

  public List<String> getCustomFieldCategories() {
    if (CollectionUtils.isEmpty(customFieldCategories)) {
      if (widget.getType() == DashboardWidgetType.TASK) {
        customFieldCategories = ICustomFieldMeta.tasks().stream()
            .map(ICustomFieldMeta::category)
            .distinct()
            .sorted().collect(Collectors.toList());
      } else if (widget.getType() == DashboardWidgetType.CASE) {
        customFieldCategories = ICustomFieldMeta.cases().stream()
            .map(ICustomFieldMeta::category)
            .distinct()
            .sorted().collect(Collectors.toList());
      }
      if (customFieldCategories.contains(EMPTY)) {
        customFieldCategories.remove(EMPTY);
        customFieldCategories.add(Ivy.cms().co(NO_CATEGORY_CMS));
      }
    }
    return customFieldCategories;
  }

  private Set<ICustomFieldMeta> getCustomFieldNames() {
    if (CollectionUtils.isEmpty(customFieldNames)) {
      if (widget.getType() == DashboardWidgetType.TASK) {
        customFieldNames = ICustomFieldMeta.tasks();
      } else if (widget.getType() == DashboardWidgetType.CASE) {
        customFieldNames = ICustomFieldMeta.cases();
      }
    }
    return customFieldNames;
  }

  public Optional<ICustomFieldMeta> findCustomFieldMeta() {
    Optional<ICustomFieldMeta> metaData = Optional.empty();
    if (widget.getType() == DashboardWidgetType.TASK) {
      metaData = ICustomFieldMeta.tasks().stream().filter(meta -> meta.name().equals(selectedField)).findFirst();
    } else if (widget.getType() == DashboardWidgetType.CASE) {
      metaData = ICustomFieldMeta.cases().stream().filter(meta -> meta.name().equals(selectedField)).findFirst();
    }
    return metaData;
  }

  public String getFieldDescription() {
    return fieldDescription;
  }

  public void setFieldDescription(String fieldDescription) {
    this.fieldDescription = fieldDescription;
  }

  public ICustomFieldMeta getSelectedCustomFieldMeta() {
    return selectedCustomFieldMeta;
  }

  public void setSelectedCustomFieldMeta(ICustomFieldMeta selectedCustomFieldMeta) {
    this.selectedCustomFieldMeta = selectedCustomFieldMeta;
  }

  public String getSelectedCustomFieldCategory() {
    return selectedCustomFieldCategory;
  }

  public void setSelectedCustomFieldCategory(String selectedCustomFieldCategory) {
    this.selectedCustomFieldCategory = selectedCustomFieldCategory;
  }

  public CustomFieldType getSelectedCustomFieldType() {
    return selectedCustomFieldType;
  }

  public void setSelectedCustomFieldType(CustomFieldType selectedCustomFieldType) {
    this.selectedCustomFieldType = selectedCustomFieldType;
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
    column.setVisible(BooleanUtils.isFalse(column.getVisible()));
  }
}
