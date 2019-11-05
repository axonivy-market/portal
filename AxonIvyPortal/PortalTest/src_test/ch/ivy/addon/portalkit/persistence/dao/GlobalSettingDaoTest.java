package ch.ivy.addon.portalkit.persistence.dao;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;

@RunWith(PowerMockRunner.class)
public class GlobalSettingDaoTest {

  private GlobalSettingDao globalSettingDao;

  @Before
  public void setup() {
    globalSettingDao = PowerMockito.mock(GlobalSettingDao.class);
  }

  @Test
  public void testFindGlobalSettingValueReturnValue() {
    String globalSettingName = "GLOBAL_SETTING_TEST_NAME";
    String expectedGlobalSettingValue = "GLOBAL_SETTING_TEST_VALUE";
    GlobalSetting globalSetting = new GlobalSetting();
    globalSetting.setKey(globalSettingName);
    globalSetting.setValue(expectedGlobalSettingValue);
    List<GlobalSetting> globalSettings = Arrays.asList(globalSetting);

    PowerMockito.when(globalSettingDao.findAll()).thenReturn(globalSettings);
    PowerMockito.when(globalSettingDao.findGlobalSettingValue(globalSettingName)).thenCallRealMethod();

    String actualGlobalSettingValue = globalSettingDao.findGlobalSettingValue(globalSettingName);

    Assert.assertEquals(expectedGlobalSettingValue, actualGlobalSettingValue);
  }

  @Test
  public void testFindGlobalSettingValueReturnEmpty() {
    String globalSettingName = "NO_FOUND_GLOBAL_SETTING";
    GlobalSetting globalSetting = new GlobalSetting();
    List<GlobalSetting> globalSettings = Arrays.asList(globalSetting);

    PowerMockito.when(globalSettingDao.findAll()).thenReturn(globalSettings);
    PowerMockito.when(globalSettingDao.findGlobalSettingValue(globalSettingName)).thenCallRealMethod();

    String actualGlobalSettingValue = globalSettingDao.findGlobalSettingValue(globalSettingName);

    Assert.assertEquals(StringUtils.EMPTY, actualGlobalSettingValue);
  }
}
