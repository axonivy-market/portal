package ch.ivy.addon.portalkit.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.enums.GlobalVariable.Option;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.vars.Variable;

public class GlobalSettingService {

  private static GlobalSettingService instance;

  public static GlobalSettingService getInstance() {
    if (instance == null) {
      instance = new GlobalSettingService();
    }
    return instance;
  }

  public String findGlobalSettingValue(GlobalVariable variable) {
    String key = variable.getKey();
    return Ivy.var().get(key);
  }

  /**
   * @param name
   * @return global setting value
   * @deprecated use {@link #findGlobalSettingValue(GlobalVariable)} instead
   */
  @Deprecated(forRemoval = true, since = "9.3")
  public String findGlobalSettingValue(String name) {
    return findGlobalSettingValue(GlobalVariable.valueOf(name));
  }

  public GlobalSetting findGlobalSettingByGlobalVariable(GlobalVariable variable) {
    return new GlobalSetting(variable.getKey(), findGlobalSettingValue(variable));
  }

  public Boolean findGlobalSettingValueAsBoolean(GlobalVariable variable) {
    return Boolean.valueOf(findGlobalSettingValue(variable));
  }

  public void resetGlobalSetting(String key) {
    Ivy.var().reset(key);
  }

  public boolean findHideSystemTasksFromHistorySettingValue() {
    GlobalVariable globalVariable =
        PermissionUtils.isSessionUserHasAdminRole() ? GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR
            : GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY;
    return findGlobalSettingValueAsBoolean(globalVariable);
  }
  
  public boolean findHideSystemNotesFromHistorySettingValue() {
    GlobalVariable globalVariable =
        PermissionUtils.isSessionUserHasAdminRole() ? GlobalVariable.HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR
            : GlobalVariable.HIDE_SYSTEM_NOTES_FROM_HISTORY;
    return findGlobalSettingValueAsBoolean(globalVariable);
  }
  
  public boolean isCaseOwnerEnabled() {
    return findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_CASE_OWNER);
  }
  
  public GlobalSetting save(GlobalSetting entity) {
    if (entity.getIsPublic()) {
      Ivy.var().set(entity.getKey(), entity.getValue());
    } else {
      sessionUser().setProperty(entity.getKey(), entity.getValue());
    }
    return entity;
  }

  public GlobalSetting updateRelatedGlobalSettingByGlobalSetting(GlobalSetting entity) {
    GlobalSetting relatedSetting = null;
    String relatedSettingValue = null;
    if (GlobalVariable.SHOW_LEGACY_UI.getKey().equals(entity.getKey())) {
      relatedSettingValue =
          Option.TRUE.toString().equals(entity.getValue()) ? ProcessMode.COMPACT.name() : ProcessMode.IMAGE.name();
      relatedSetting = new GlobalSetting(GlobalVariable.DEFAULT_PROCESS_MODE.getKey(), relatedSettingValue);
    }
    if (relatedSetting != null) {
      relatedSetting = save(relatedSetting);
    }
    return relatedSetting;
  }

  public List<GlobalSetting> findAll() {
    return getPortalVariableStream().sorted((v1, v2) -> StringUtils.compareIgnoreCase(v1.name(), v2.name()))
        .map(v -> new GlobalSetting(v.name(), v.value())).collect(toList());
  }

  private Stream<Variable> getPortalVariableStream() {
    List<String> names =
        Stream.of(GlobalVariable.values()).map(v -> v.getKey()).collect(Collectors.toList());
    return Ivy.var().all().stream()
        .filter(v -> v.name().startsWith("Portal.") && names.contains(v.name()));
  }

  public void resetAll() {
    getPortalVariableStream().forEach(v -> Ivy.var().reset(v.name()));
  }

  public void deletePrivateConfig(GlobalVariable variable) {
    sessionUser().removeProperty(variable.getKey());
  }

  private IUser sessionUser() {
    return Ivy.session().getSessionUser();
  }
}
