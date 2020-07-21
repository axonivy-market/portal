package ch.ivy.addon.portalkit.enums;

public enum SessionAttribute {

  SELECTED_APP,
  SELECTED_APP_DISPLAY_NAME,
  TASK_CATEGORY,
  TASK_END_INFO,
  PORTAL_START_PMV_ID,
  IS_TASK_STARTED_IN_DETAILS, // task status (for navigating back feature)
  NAVIGATE_FROM_RELATED_CASE, // use for navigate back after destroy case
  HELP_URL_LINK;
}