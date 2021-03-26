package ch.ivy.addon.portalkit.enums;

public enum DashboardWidgetType {
	TASK, CASE, PROCESS, STATISTIC, NEW;

  public static DashboardWidgetType typeOf(String typeName) {
		for (DashboardWidgetType type : DashboardWidgetType.values()) {
			if (type.name().equalsIgnoreCase(typeName)) {
				return type;
			}
		}
		return null;
	}
}
