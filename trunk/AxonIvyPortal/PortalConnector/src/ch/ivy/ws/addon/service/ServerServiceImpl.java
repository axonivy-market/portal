package ch.ivy.ws.addon.service;

import ch.ivy.ws.addon.util.SystemProperties;

public class ServerServiceImpl implements IServerService {

  @Override
  public String getExternalHost() {
    return SystemProperties.getSystemPropertyValue(SystemProperties.EXTERNAL_HOST_NAME);
  }
}
