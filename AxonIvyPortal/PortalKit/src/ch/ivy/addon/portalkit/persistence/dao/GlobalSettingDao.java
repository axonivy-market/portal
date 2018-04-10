package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivyteam.ivy.application.IApplication;

public class GlobalSettingDao extends AbstractDao<GlobalSetting> {

  public GlobalSettingDao() {
    super();
  }

  public GlobalSettingDao(IApplication application) {
    super(application);
  }

  @SuppressWarnings("unchecked")
  @ExecuteAsSystem
  public String findGlobalSettingValue(String variableName) {
    repo =
        Repos.builder().primaryKey(EntityProperty.KEY.toString()).build(String.class, GlobalSetting.class)
            .init(findAll());
    List<GlobalSetting> globalSettings = repo.query(ObjectFilter.eq(EntityProperty.KEY.toString(), variableName));
    GlobalSetting globalSetting = globalSettings.get(0);
    if(globalSetting == null){
      return StringUtils.EMPTY;
    }else{
      return globalSetting.getValue();
    }
    
  }
  
  @SuppressWarnings("unchecked")
  @ExecuteAsSystem
  public boolean isGlobalSettingAvailable(String variableName) {
    repo =
        Repos.builder().primaryKey(EntityProperty.KEY.toString()).build(String.class, GlobalSetting.class)
            .init(findAll());
    List<GlobalSetting> globalSettings = repo.query(ObjectFilter.eq(EntityProperty.KEY.toString(), variableName));
    GlobalSetting globalSetting = globalSettings.get(0);
    return globalSetting != null;
    
  }
}
