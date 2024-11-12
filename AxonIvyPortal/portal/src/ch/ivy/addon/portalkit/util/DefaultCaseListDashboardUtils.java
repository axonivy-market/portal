package ch.ivy.addon.portalkit.util;

public class DefaultCaseListDashboardUtils {

  public static final String DASHBOARD_CASE_LIST_DASHBOARD_JSON = """
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
                "field": "name",
                "quickSearch": true,
                "width": "325"
              },
              {
                "field": "description",
                "quickSearch": true,
                "width": "200"
              },
              {
                "field": "id",
                "quickSearch": true,
                "width": "115"
              },
          {
                "field": "creator",
                "width": "139"
              },
          {
                "field": "startTimestamp",
                "width": "90"
          },
          {
                "field": "endTimestamp",
                "width": "90"
          },
          {
                "field": "state",
                "width": "85"
          },
          {
                "field": "category",
                "width": "101"
              },
          {
                "field": "actions",
                "width": "85"
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
}
