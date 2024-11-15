package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class DefaultDashboardUtils {

  private static Dashboard defaultTaskListDashboard;
  private static Dashboard defaultCaseListDashboard;

  public static Dashboard getDefaultTaskListDashboard() {
    if (defaultTaskListDashboard == null) {
      String json = """
          {
            "id": "default-task-list-dashboard",
            "version": "11.4.0",
            "templateId": "create-from-scratch",
            "titles": [
              {
                "locale": "en",
                "value": "Tasks"
              },
              {
                "locale": "fr",
                "value": "Tâches"
              },
              {
                "locale": "de",
                "value": "Aufgaben"
              },
              {
                "locale": "es",
                "value": "Tareas"
              }
            ],
            "icon": "si-task-list-edit",
            "description": "Default Task List Dashboard",
            "widgets": [
              {
                "type": "task",
                "id": "default_task_list_dashboard_task_1",
                "names": [
                  {
                    "locale": "en",
                    "value": "Your Tasks"
                  },
                  {
                    "locale": "de",
                    "value": "Ihre Aufgaben"
                  },
                  {
                    "locale": "fr",
                    "value": "Vos tâches"
                  },
                  {
                    "locale": "es",
                    "value": "Sus tareas"
                  }
                ],
                "layout": {
                  "w": 12,
                  "h": 8,
                  "x": 0,
                  "y": 0
                },
                "enableQuickSearch": true,
                "columns": [
                  {
                    "field": "start",
                    "width": "75"
                  },
                  {
                    "field": "priority",
                    "width": "70"
                  },
                  {
                    "field": "id",
                    "quickSearch": true,
                    "width": "90"
                  },
                  {
                    "field": "name",
                    "quickSearch": true,
                    "width": "280"
                  },
                  {
                    "field": "description",
                    "quickSearch": true,
                    "width": "280"
                  },
                  {
                    "field": "activator",
                    "width": "120"
                  },
                  {
                    "field": "state",
                    "width": "80"
                  },
                  {
                    "field": "startTimestamp",
                    "width": "95"
                  },
                  {
                    "field": "expiryTimestamp",
                    "width": "95"
                  },
                  {
                    "field": "category",
                    "width": "105"
                  },
                  {
                    "field": "actions",
                    "width": "95"
                  }
                ],
                "canWorkOn": false,
                "sortField": "id",
                "sortDescending": true
              }
            ],
            "permissions": [
              "Everybody"
            ],
            "isTopMenu": true
          }
          """;
      defaultTaskListDashboard = DashboardUtils.jsonToDashboard(json);
    }
    return defaultTaskListDashboard;
  }

  public static Dashboard getDefaultCaseListDashboard() {
    if (defaultCaseListDashboard == null) {
      String json = """
          {
            "id": "default-case-list-dashboard",
            "version": "11.4.0",
            "templateId": "create-from-scratch",
            "titles": [
              {
                "locale": "en",
                "value": "Cases"
              },
              {
                "locale": "fr",
                "value": "Dossiers"
              },
              {
                "locale": "de",
                "value": "Vorgänge"
              },
              {
                "locale": "es",
                "value": "Casos"
              }
            ],
            "icon": "si-layout-bullets",
            "description": "Default Case List Dashboard",
            "widgets": [
              {
                "type": "case",
                "id": "default_case_list_dashboard_case_1",
                "names": [
                  {
                    "locale": "en",
                    "value": "Your Cases"
                  },
                  {
                    "locale": "de",
                    "value": "Ihre Vorgänge"
                  },
                  {
                    "locale": "fr",
                    "value": "Vos affaires"
                  },
                  {
                    "locale": "es",
                    "value": "Sus casos"
                  }
                ],
                "layout": {
                  "w": 12,
                  "h": 8,
                  "x": 0,
                  "y": 0
                },
                "sortDescending": true,
                "sortField": "id",
                "enableQuickSearch": true,
                "columns": [
                  {
                    "field": "id",
                    "quickSearch": true,
                    "width": "80"
                  },
                  {
                    "field": "name",
                    "quickSearch": true,
                    "width": "300"
                  },
                  {
                    "field": "description",
                    "quickSearch": true,
                    "width": "300"
                  },
                  {
                    "field": "creator",
                    "width": "120"
                  },
                  {
                    "field": "startTimestamp",
                    "width": "95"
                  },
                  {
                    "field": "endTimestamp",
                    "width": "95"
                  },
                  {
                    "field": "state",
                    "width": "80"
                  },
                  {
                    "field": "category",
                    "width": "105"
                  },
                  {
                    "field": "actions",
                    "width": "95"
                  }
                ]
              }
            ],
            "permissions": [
              "Everybody"
            ],
            "isTopMenu": true
          }
          """;
      defaultCaseListDashboard = DashboardUtils.jsonToDashboard(json);
    }
    return defaultCaseListDashboard;
  }


  public static void invalidateDefaultTaskCaseListDashboardIfNeeded(String dashboardId) {
    if (DEFAULT_TASK_LIST_DASHBOARD.equals(dashboardId)) {
      defaultTaskListDashboard = null;
    } else if (DEFAULT_CASE_LIST_DASHBOARD.equals(dashboardId)) {
      defaultCaseListDashboard = null;
    }
  }

}
