package com.axonivy.portal.enums;

import java.util.Arrays;
import java.util.Optional;

import com.axonivy.portal.dto.jsondefinition.PortalCustomStatistic;
import com.axonivy.portal.dto.jsondefinition.PortalDashboard;
import com.axonivy.portal.dto.jsondefinition.PortalDashboardTemplate;

import ch.ivy.addon.portalkit.enums.PortalVariable;

public enum PortalSchema {
  DASHBOARD(PortalVariable.DASHBOARD, "portal-dashboard-schema.json", PortalDashboard.class),
  DASHBOARD_TEMPLATES(PortalVariable.DASHBOARD_TEMPLATES, "portal-dashboard-template-schema.json", PortalDashboardTemplate.class),
  CUSTOM_STATISTIC(PortalVariable.CUSTOM_STATISTIC, "portal-custom-statistic-schema.json", PortalCustomStatistic.class);

  private final PortalVariable variable;
  private final String fileName;
  private final Class<?> rootType;

  PortalSchema(PortalVariable variable, String fileName, Class<?> rootType) {
    this.variable = variable;
    this.fileName = fileName;
    this.rootType = rootType;
  }

  public PortalVariable getVariable() {
    return variable;
  }

  public String getFileName() {
    return fileName;
  }

  public Class<?> getRootType() {
    return rootType;
  }

  public static Optional<PortalSchema> fromVariableKey(String variableKey) {
    if (variableKey == null) {
      return Optional.empty();
    }
    return Arrays.stream(values())
        .filter(schema -> schema.variable.key.equals(variableKey))
        .findFirst();
  }
}
