package ch.ivy.addon.portalkit.util;

public class DefaultTaskListDashboardUtils {

  public static final String DASHBOARD_TASK_LIST_DASHBOARD_JSON = """
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
                "locale": "fr",
                "value": "Your Tasks"
              },
              {
                "locale": "de",
                "value": "Your Tasks"
              },
              {
                "locale": "es",
                "value": "Your Tasks"
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
                "width": "50"
              },
              {
                "field": "priority",
                "width": "86"
              },
              {
                "field": "id",
                "quickSearch": true,
                "width": "115"
              },
              {
                "field": "name",
                "quickSearch": true,
                "width": "315"
              },
              {
                "field": "description",
                "quickSearch": true,
                "width": "190"
              },
              {
                "field": "activator",
                "width": "139"
              },
              {
                "field": "state",
                "width": "85"
              },
              {
                "field": "startTimestamp",
                "width": "90"
              },
              {
                "field": "expiryTimestamp",
                "width": "120"
              },
              {
                "field": "category",
                "width": "101"
              },
          {
                "field": "actions",
                "width": "85"
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
}
