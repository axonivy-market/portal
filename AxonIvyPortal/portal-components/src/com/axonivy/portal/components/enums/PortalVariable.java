package com.axonivy.portal.components.enums;

public enum PortalVariable {
    PASSWORD_VALIDATION("Portal.PasswordValidation"),
    ANNOUNCEMENT("Portal.Announcement"),
    THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
    WIDGET_FILTER("Portal.Dashboard.WidgetFilters"),
    EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
    TASK_DETAIL("Portal.TaskDetails"),
    CASE_DETAIL("Portal.CaseDetails"),
    DASHBOARD("Portal.Dashboard"),
    DASHBOARD_ORDER("Portal.Dashboard.Order"),
    DASHBOARD_TEMPLATES("Portal.DashboardTemplates"),
    USER_MENU("Portal.UserMenu"),
    SESSION_CACHE_TIMEOUT("Portal.SessionCacheTimeout");

    public String key;

    private PortalVariable(String key) {
        this.key = key;
    }

}
