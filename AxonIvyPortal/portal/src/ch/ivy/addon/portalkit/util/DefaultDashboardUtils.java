package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class DefaultDashboardUtils {

  private static String DEFAULT_DASHBOARD_JSON = """
      {
          "id": "1",
          "version": "12.0.0",
          "templateId": "default-portal-dashboard-template",
          "titles": [
              {
            "locale": "en",
            "value": "Dashboard"
              },
              {
            "locale": "fr",
            "value": "Tableau de bord"
              },
              {
            "locale": "de",
            "value": "Dashboard"
              },
              {
            "locale": "es",
            "value": "Tablero de mandos"
              }
          ],
          "icon": "si-pie-line-graph",
          "widgets": [
              {
            "type": "task",
            "id": "task_1",
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
                      "w": 8,
                      "h": 6,
                      "x": 0,
                      "y": 2
            },
            "enableQuickSearch": true,
            "columns": [
                      {
                          "field": "start",
                          "width": "65"
                      },
                      {
                          "field": "priority",
                          "width": "65"
                      },
                      {
                          "field": "id",
                          "visible": false
                      },
                      {
                          "field": "name",
                          "width": "315"
                      },
                      {
                          "field": "description",
                          "visible": false
                      },
                      {
                          "field": "activator",
                          "width": "120"
                      },
                      {
                          "field": "state",
                          "visible": false
                      },
                      {
                          "field": "startTimestamp",
                          "visible": false
                      },
                      {
                          "field": "expiryTimestamp"
                      },
                      {
                          "field": "category",
                          "visible": false
                      },
                      {
                          "field": "actions",
                          "width": "78"
                      }
            ],
            "sortDescending": true,
            "sortField": "startTimestamp",
            "canWorkOn": true,
            "filters": [
                      {
                          "field": "state",
                          "values": [
                              "DONE",
                              "IN_PROGRESS",
                              "OPEN"
                          ],
                          "operator": "in",
                          "type": "standard"
                      }
            ]
              },
              {
            "type": "case",
            "id": "case_1",
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
                      "w": 9,
                      "h": 6,
                      "x": 3,
                      "y": 8
            },
            "enableQuickSearch": true,
            "columns": [
                      {
                          "field": "id",
                          "width": "60"
                      },
                      {
                          "field": "name",
                          "width": "320"
                      },
                      {
                          "field": "description",
                          "visible": false
                      },
                      {
                          "field": "state",
                          "width": "60"
                      },
                      {
                          "field": "creator",
                          "width": "100"
                      },
                      {
                          "field": "startTimestamp",
                          "width": "100"
                      },
                      {
                          "field": "endTimestamp",
                          "visible": false
                      },
                      {
                          "field": "category",
                          "visible": false
                      },
                      {
                          "field": "actions",
                          "width": "60"
                      }
            ],
            "sortDescending": true,
            "sortField": "startTimestamp",
            "filters": [
                      {
                          "field": "state",
                          "values": [
                              "OPEN"
                          ],
                          "operator": "in",
                          "type": "standard"
                      }
            ]
              },
              {
            "type": "compact-process",
            "id": "process_1",
            "names": [
                      {
                          "locale": "en",
                          "value": "Your Processes"
                      },
                      {
                          "locale": "de",
                          "value": "Ihre Prozesse"
                      },
                      {
                          "locale": "fr",
                          "value": "Vos processus"
                      },
                      {
                          "locale": "es",
                          "value": "Sus procesos"
                      }
            ],
            "layout": {
                      "w": 4,
                      "h": 4,
                      "x": 8,
                      "y": 4
            }
              },
              {
            "type": "welcome",
            "id": "welcome_1",
            "names": [
                      {
                          "locale": "en",
                          "value": "Your Welcome Widget"
                      }
            ],
            "layout": {
                      "w": 12,
                      "h": 2,
                      "x": 0,
                      "y": 0
            },
            "welcomeTextPosition": "TOP_LEFT",
            "welcomeTextSize": "HEADING_3",
            "welcomeTextColor": "ffffff",
            "welcomeTexts": [
                      {
                          "locale": "en",
                          "value": ", welcome to the Axon Ivy Portal!"
                      },
                      {
                          "locale": "fr",
                          "value": ", bienvenue sur le portail Axon Ivy!"
                      },
                      {
                          "locale": "de",
                          "value": ", willkommen im Axon Ivy Portal!"
                      },
                      {
                          "locale": "es",
                          "value": ", bienvenido al portal Axon Ivy!"
                      }
                  ],
            "welcomeImageFit": "COVER",
            "greeting": true
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_1",
            "layout": {
                      "w": 2,
                      "h": 2,
                      "x": 8,
                      "y": 2
            },
            "chartId": "10"
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_2",
            "layout": {
                      "w": 2,
                      "h": 2,
                      "x": 10,
                      "y": 2
            },
            "chartId": "11"
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_3",
            "layout": {
                      "w": 3,
                      "h": 3,
                      "x": 0,
                      "y": 11
            },
            "chartId": "7"
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_4",
            "layout": {
                      "w": 3,
                      "h": 3,
                      "x": 0,
                      "y": 8
            },
            "chartId": "3"
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_5",
            "layout": {
                      "w": 3,
                      "h": 3,
                      "x": 0,
                      "y": 14
            },
            "chartId": "5"
              },
              {
            "type": "client-statistic",
            "id": "client_statistic_6",
            "layout": {
                      "w": 9,
                      "h": 3,
                      "x": 3,
                      "y": 14
            },
            "chartId": "4"
              }
          ],
          "permissions": [
              "Everybody"
          ]
      }
      """;

  private static String DEFAULT_TASK_LIST_DASHBOARD_JSON = """
      {
        "id": "default-task-list-dashboard",
        "version": "12.0.0",
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

  private static String DEFAULT_CASE_LIST_DASHBOARD_JSON = """
      {
        "id": "default-case-list-dashboard",
        "version": "12.0.0",
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

  public static Dashboard getDefaultTaskListDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_TASK_LIST_DASHBOARD_JSON);
  }

  public static Dashboard getDefaultCaseListDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_CASE_LIST_DASHBOARD_JSON);
  }

  public static Dashboard getDefaultDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_DASHBOARD_JSON);
  }
}
