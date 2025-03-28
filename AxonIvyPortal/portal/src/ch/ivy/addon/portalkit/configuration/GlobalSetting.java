package ch.ivy.addon.portalkit.configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.GlobalSearchScopeCategory;
import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.enums.ThemeMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInCaseList;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.DelegationAppendOption;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.enums.GlobalVariableType;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.enums.SortDirection;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GlobalSetting extends AbstractConfiguration {
  private String key;
  private String value;
  
  public GlobalSetting(String key, String value) {
    this.key = key;
    this.value = value;
    setIsPublic(true);
  }
  
  public GlobalSetting() {
    setIsPublic(true);
  }
  
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  @JsonIgnore
  public String getDefaultValue() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    String defaultValue = variable.getDefaultValue();
    if (variable.getType() == GlobalVariableType.SELECTION) {
      if (variable.getOptions() instanceof Option[]) {
        return GlobalVariable.Option.valueOf(StringUtils.upperCase(defaultValue)).translate();
      }
      return defaultValue;
    } else if (variable == GlobalVariable.DEFAULT_HOMEPAGE) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    } else if (variable.getType() == GlobalVariableType.EXTERNAL_SELECTION && MapUtils.isNotEmpty(variable.getExternalOptions())) {
      Object object = variable.getExternalOptions().get(defaultValue);
      return getDisplayValue(object);
    } else if (variable.getType() == GlobalVariableType.MULTI_EXTERNAL_SELECTIONS && MapUtils.isNotEmpty(variable.getDefaultValues())) {
      return getDisplayValueForMultiExternalSelections(variable.getDefaultValues());
    }
    return defaultValue;
  }
  
  @JsonIgnore
  public String getDisplayValue() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    if (variable.getType() == GlobalVariableType.SELECTION) {
      if (variable.getOptions() instanceof Option[]) {
        return GlobalVariable.Option.valueOf(StringUtils.upperCase(value)).translate();
      }
      return value;
    } else if (variable == GlobalVariable.DEFAULT_HOMEPAGE) {
      return HomepageUtils.findDefaultHomepage().getLabel();
    } else if (variable.getType() == GlobalVariableType.EXTERNAL_SELECTION && MapUtils.isNotEmpty(variable.getExternalOptions())) {
      Object object = variable.getExternalOptions().get(value);
      return getDisplayValue(object);
    } else if (variable.getType() == GlobalVariableType.MULTI_EXTERNAL_SELECTIONS && MapUtils.isNotEmpty(variable.getExternalOptions())) {
      if (StringUtils.isBlank(value)) {
        return value;
      }
      LinkedHashMap<String, Object> valueMap = new LinkedHashMap<>();
      for (String val : Arrays.asList(value.split(","))) {
        if (variable.getExternalOptions().get(val) != null) {
          valueMap.put(val, variable.getExternalOptions().get(val));
        }
      }
      return getDisplayValueForMultiExternalSelections(valueMap);
    } else if (GlobalVariableType.PASSWORD.equals(variable.getType()) && StringUtils.isNoneBlank(value)) {
      return "******";
    }
    return value;
  }

  private String getDisplayValue(Object object) {
    return switch (object) {
      case TaskSortField castObject -> castObject.getLabel();
      case CaseSortField castObject -> castObject.getLabel();
      case SortDirection castObject -> castObject.getLabel();
      case ProcessMode castObject -> castObject.getLabel();
      case BehaviourWhenClickingOnLineInTaskList castObject -> castObject.getLabel();
      case BehaviourWhenClickingOnLineInCaseList castObject -> castObject.getLabel();
      case DefaultImage castObject -> castObject.getLabel();
      case ThemeMode castObject -> castObject.getLabel();
      case SearchScopeTaskField castObject -> castObject.getLabel();
      case SearchScopeCaseField castObject -> castObject.getLabel();
      case GlobalSearchScopeCategory castObject -> castObject.getLabel();
      case DelegationAppendOption castObject -> castObject.getLabel();
      default -> (String) object;
    };
  }

  private String getDisplayValueForMultiExternalSelections(Map<String, Object> optionMap) {
    if (optionMap.isEmpty()) {
      return "";
    }
    return String.join(",", optionMap.values()
        .stream().map(o -> getDisplayValue(o)).collect(Collectors.toList()));
  }

  public void setValueToDefault() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    if (variable.getType() == GlobalVariableType.MULTI_EXTERNAL_SELECTIONS) {
      value =  String.join(",", variable.getDefaultValues().keySet());
    } else {
      value = variable.getDefaultValue();
    }
  }

  @JsonIgnore
  public String getNote() {
    return GlobalVariable.valueOfKey(key).getNote();
  }

  @Override
  public String toString() {
    return "GlobalSetting [key=" + key + ", value=" + value + ", id=" + getId() + "]";
  }
}
