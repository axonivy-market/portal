package ch.ivy.addon.portalkit.enums;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardDisplayType {
    SUB_MENU, TOP_MENU, HIDDEN;

    private static final List<String> TYPE_LIST = Arrays.stream(values()).map(item -> item.getLabel()).collect(Collectors.toList());

    public String getLabel() {
        return name();
    }
    
    @JsonValue
    public String getDashboardDisplayType() {
        return this.name().toLowerCase();
    }

    public static List<String> getTypeList() {
        return TYPE_LIST;
    }
    
    public static String getDisplayLabel(DashboardDisplayType type) {
      String label = Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardDisplayType/%s", type.name()));
      return StringUtils.isBlank(label) ? type.name() : label;
  }
}