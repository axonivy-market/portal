package ch.ivy.addon.portalkit.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.restricted.EngineMode;

public class PortalSettingLoader {
  private static final String PORTAL_CONFIG = "/portal_config.properties";

  public void loadPortalSettings() {
    if (EngineMode.isEmbeddedInDesigner()) {
      Properties properties = new Properties();

      try {
        InputStream inputStream = PortalSettingLoader.class.getResourceAsStream(PORTAL_CONFIG);
        if (inputStream == null) {
          return;
        }
        properties.load(inputStream);
      } catch (FileNotFoundException e) {
        Ivy.log().error("Error when load property file", e);
        return;
      } catch (IOException e) {
        Ivy.log().error("Error when load property file", e);
        return;
      }

      GlobalSettingService globalSettingService = new GlobalSettingService();
      List<GlobalSetting> globalSettings = globalSettingService.findAllGlobalSetting();

      for (Entry<Object, Object> property : properties.entrySet()) {
        String key = Optional.ofNullable(GlobalVariable.valueOf(property.getKey().toString()).name()).orElse("");
        String value = Optional.ofNullable(property.getValue().toString()).orElse(null);

        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
          GlobalSetting setting = globalSettings.stream().filter(s -> StringUtils.equals(s.getKey(), key)).findFirst().get();
          setting.setValue(value);
          IvyCacheService.newInstance().cacheGlobalSetting(key, value);
        }
      }

      globalSettingService.saveAll(globalSettings);
    }
  }
}