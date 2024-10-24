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
  SELECTED_DASHBOARD_ID, // selected dashboard as sub menu
  SELECTED_DASHBOARD_OR_DASHBOARD_AS_MENU_ID, // last selected dashboard, could be as sub menu or menu
  PREV_SELECTED_DASHBOARD_ID, // use for navigate back to dashboard if last tab is dashboard menu item;
  ;
}