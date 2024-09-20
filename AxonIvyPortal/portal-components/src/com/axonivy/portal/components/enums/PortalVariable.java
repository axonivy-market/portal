package com.axonivy.portal.components.enums;

public enum PortalVariable {
    PASSWORD_VALIDATION("Portal.PasswordValidation"),
    ANNOUNCEMENT("Portal.Announcement"),
    THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
    WIDGET_FILTER("Portal.Dashboard.WidgetFilters"),
    EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
    EXPRESS_PROCESS("Portal.Processes.ExpressProcesses"),
    TASK_DETAIL("Portal.TaskDetails"),
    CASE_DETAIL("Portal.CaseDetails"),
    DASHBOARD("Portal.Dashboard"),
    DASHBOARD_ORDER("Portal.Dashboard.Order"),
    DASHBOARD_TEMPLATES("Portal.DashboardTemplates"),
    USER_MENU("Portal.UserMenu");

    public String key;

    private PortalVariable(String key) {
        this.key = key;
    }

}
