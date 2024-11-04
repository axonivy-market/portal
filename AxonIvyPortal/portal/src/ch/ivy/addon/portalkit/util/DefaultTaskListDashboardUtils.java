package ch.ivy.addon.portalkit.util;

public class DefaultTaskListDashboardUtils {

  public static final String DASHBOARD_TASK_TEMPLATE_JSON = """
      {
        "id": "default-task-list-dashboard",
        "version": "11.4.0",
        "templateId": "create-from-scratch",
        "titles": [
          { "locale": "en", "value": "Tasks" },
          { "locale": "fr", "value": "Tâches" },
          { "locale": "de", "value": "Aufgaben" },
          { "locale": "es", "value": "Tareas" }
        ],
        "icon": "si-task-list-edit",
        "description": "Default Task List Dashboard",
        "widgets": [
          {
            "type": "task",
            "id": "task_e16cbee90a244f4a857d0a8c875b7b34",
            "names": [
              { "locale": "en", "value": "Your Tasks" },
              { "locale": "fr", "value": "Vos Tâches" },
              { "locale": "de", "value": "Ihre Aufgaben" },
              { "locale": "es", "value": "Tus Tareas" }
            ],
            "layout": { "w": 12, "h": 8, "x": 0, "y": 0 },
            "rowsPerPage": 5,
            "enableQuickSearch": true,
            "showWidgetInfo": true,
            "showFullscreenMode": true,
            "columns": [
              { "field": "start", "width": "50" },
              { "field": "priority", "width": "86" },
              { "field": "id", "width": "115" },
              { "field": "name", "quickSearch": true, "width": "315" },
              { "field": "description", "quickSearch": true, "width": "190" },
              { "field": "activator", "width": "139" },
              { "field": "state", "width": "85" },
              { "field": "startTimestamp", "width": "90" },
              { "field": "expiryTimestamp", "width": "120" },
              { "field": "category", "width": "101" },
              { "field": "actions", "width": "85" }
            ],
            "canWorkOn": false,
            "sortField": "id",
            "sortDescending": true
          }
        ],
        "permissions": [ "Everybody" ],
        "isTopMenu": true
      }
      """;
}
