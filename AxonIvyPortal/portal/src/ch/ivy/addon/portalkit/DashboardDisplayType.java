package ch.ivy.addon.portalkit;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DashboardDisplayType {
    SUB_MENU, TOP_MENU, HIDDEN;

    private static final List<String> TYPE_LIST = Arrays.stream(values()).map(item -> item.getLabel()).collect(Collectors.toList());

    public String getLabel() {
        return name();
    }
    
    @JsonValue
    public String getDashboardDisplayType() {
        return this.name();
    }

    public static List<String> getTypeList() {
        return TYPE_LIST;
    }
    
    public static String getDisplayLabel(DashboardDisplayType type) {
      switch(type) {
          case TOP_MENU:
              return "Top Menu";
          case SUB_MENU:
              return "Sub Menu";
          case HIDDEN:
              return "Hidden";
          default:
              return type.name();
      }
  }
}