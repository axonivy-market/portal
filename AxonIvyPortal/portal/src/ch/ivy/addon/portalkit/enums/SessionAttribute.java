package ch.ivy.addon.portalkit.enums;

public enum SessionAttribute {
  SESSION_IDENTIFIER,
  TASK_END_INFO,
  IS_TASK_FINISHED,
  PORTAL_START_PMV_ID,
  IS_TASK_STARTED_IN_DETAILS, // task status (for navigating back feature)
  NAVIGATE_FROM_RELATED_CASE, // use for navigate back after destroy case
  HELP_URL_LINK,
  PORTAL_IN_TEAMS,
  DEFAULT_PAGE_IN_TEAMS,
  SELECTED_DASHBOARD_ID,
  PREV_SELECTED_DASHBOARD_ID; // use for navigate back to dashboard if last tab is dashboard menu item;
}