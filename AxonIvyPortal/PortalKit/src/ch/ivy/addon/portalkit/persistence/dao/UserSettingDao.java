package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.UserSetting;
import ch.ivyteam.ivy.application.IApplication;

public class UserSettingDao extends AbstractDao<UserSetting> {
  public UserSettingDao() {
    super();
  }

  public UserSettingDao(IApplication application) {
    super(application);
  }

  @SuppressWarnings("unchecked")
  @ExecuteAsSystem
  public UserSetting findByUserName(String userName) {
    repo = Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.USER_NAME.toString())
	.build(long.class, UserSetting.class).init(findAll());

    List<UserSetting> userSetting = repo.query(ObjectFilter.eq(EntityProperty.USER_NAME.toString(), userName));

    if (userSetting.size() == 0) {
      return null;
    }
    return userSetting.get(0);
  }
}
