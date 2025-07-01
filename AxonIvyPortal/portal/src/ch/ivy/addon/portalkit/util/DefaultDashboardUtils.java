package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class DefaultDashboardUtils {

  private static String DEFAULT_TASK_LIST_DASHBOARD_JSON = """
      {
        "id": "default-task-list-dashboard",
        "version": "13.1.0",
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
          },
          {
            "locale": "ja",
            "value": "タスク"
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
              },
              {
                "locale": "ja",
                "value": "タスク"
              }
            ],
            "layout": {
              "w": 12,
              "h": 12,
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
                "field": "pin",
                "width": "70"
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
                "width": "145"
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
                "field": "endTimestamp",
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
        "dashboardDisplayType": "top_menu"
      }
      """;

  private static String DEFAULT_CASE_LIST_DASHBOARD_JSON = """
      {
        "id": "default-case-list-dashboard",
        "version": "13.1.0",
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
          },
          {
            "locale": "ja",
            "value": "ケース"
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
              },
              {
                "locale": "ja",
                "value": "ケース"
              }
            ],
            "layout": {
              "w": 12,
              "h": 12,
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
                "field": "pin",
                "width": "70"
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
        "dashboardDisplayType": "top_menu"
      }
      """;

  private static final String DEFAULT_USER_GUIDE_DASHBOARD =
      """
{
  "id" : "default-user-guide-dashboard",
  "version" : "13.2.0",
  "templateId" : "create-from-scratch",
  "titles" : [ {
    "locale" : "en",
    "value" : "User Guide Dashboard"
  }, {
    "locale" : "fr",
    "value" : "Tableau de bord du guide utilisateur"
  }, {
    "locale" : "de",
    "value" : "Benutzerhandbuch-Dashboard"
  }, {
    "locale" : "ja",
    "value" : "ユーザーガイドダッシュボード"
  }, {
    "locale" : "es",
    "value" : "Panel de guía del usuario"
  } ],
            "icon" : "si si-bulb",
  "widgets" : [ {
    "type" : "welcome",
    "id" : "welcome_b1f1f7799bfc48008f49919385b30e0d",
    "names" : [ {
      "locale" : "en",
      "value" : "Your Welcome Widget"
    }, {
      "locale" : "fr",
      "value" : "Your Welcome Widget"
    }, {
      "locale" : "de",
      "value" : "Your Welcome Widget"
    }, {
      "locale" : "ja",
      "value" : "Your Welcome Widget"
    }, {
      "locale" : "es",
      "value" : "Your Welcome Widget"
    } ],
    "layout" : {
      "w" : 12,
      "h" : 3,
      "x" : 0,
      "y" : 0
    },
    "welcomeTextPosition" : "TOP_LEFT",
    "welcomeTextSize" : "HEADING_2",
    "welcomeTextColor" : "#ffffff",
    "welcomeTextColorDarkMode" : "#ffffff",
    "welcomeTexts" : [ {
      "locale" : "en",
      "value" : "Welcome"
    }, {
      "locale" : "fr",
      "value" : "Bienvenue"
    }, {
      "locale" : "de",
      "value" : "Willkommen"
    }, {
      "locale" : "ja",
      "value" : "ようこそ"
    }, {
      "locale" : "es",
      "value" : "Bienvenido"
    } ],
    "greeting" : false
  }, {
    "type" : "compact-process",
    "id" : "process_5d855205465c49ca8cf58d6308488f43",
    "names" : [ {
      "locale" : "en",
      "value" : "The process Widget"
    }, {
      "locale" : "fr",
      "value" : "Le widget de processus"
    }, {
      "locale" : "de",
      "value" : "Das Prozess-Widget"
    }, {
      "locale" : "ja",
      "value" : "このプロセス"
    }, {
      "locale" : "es",
      "value" : "El widget de procesos"
    } ],
    "layout" : {
      "w" : 3,
      "h" : 7,
      "x" : 4,
      "y" : 3
    },
    "showFullscreenMode" : true,
    "showWidgetInfo" : true,
    "sorting" : "BY_CUSTOM_ORDER",
    "enableQuickSearch" : false
  }, {
    "type" : "custom",
    "id" : "custom_d48812f9e8c84d47b5d18420db048724",
    "names" : [ {
      "locale" : "en",
      "value" : "Processes Widget"
    }, {
      "locale" : "fr",
      "value" : "Widget de processus"
    }, {
      "locale" : "de",
      "value" : "Prozess-Widget"
    }, {
      "locale" : "ja",
      "value" : "プロセスウィジェット"
    }, {
      "locale" : "es",
      "value" : "Widget de procesos"
    } ],
    "layout" : {
      "w" : 4,
      "h" : 7,
      "x" : 0,
      "y" : 3
    },
    "data" : {
      "processPath" : "designer/portal-user-examples/Start Processes/UserExampleGuide/widgetNote.ivp",
      "params" : [ {
        "name" : "noteDE",
        "type" : "STRING",
        "value" : "<p>Das <strong>Process List Widget</strong> zeigt alle verfügbaren Prozesse an, die Sie im Portal starten können.</p><p>Ein <strong>Prozess</strong> ist eine vordefinierte Abfolge von Arbeitsschritten, die aus mehreren zusammenhängenden Aufgaben bestehen und ein bestimmtes Ziel verfolgen.</p><p>Mit diesem Widget können Sie ganz einfach einen neuen Geschäftsprozess mit nur einem Klick starten. Dadurch wird automatisch ein neuer Fall für diesen Prozess erstellt.</p><hr><p><strong>Anzeigemodi:</strong></p><p>Das Widget unterstützt vier Anzeigemodi für unterschiedliche Anwendungsfälle und Vorlieben:</p><ul><li><strong>Kombinierter Modus</strong> – Zeigt den ausgewählten Prozess zusammen mit den zugehörigen Fällen und Aufgaben in einer Ansicht. Nützlich, um alles zu einem Prozess zu verfolgen.</li><li><strong>Kompaktmodus</strong> – Listet alle Prozessstarts in einem kompakten Format. Sortierung nach Name, Nutzung, Index oder benutzerdefiniert. Schnellsuche nach Prozessnamen ist möglich.</li><li><strong>Vollmodus</strong> – Zeigt einen einzelnen Prozess in Kartenform mit Symbol und Beschreibung.</li><li><strong>Bildmodus</strong> – Wie der Vollmodus, aber mit Bild zur visuellen Darstellung des Prozesses.</li></ul><p>In der Widget-Konfiguration können Sie den gewünschten Modus auswählen und entscheiden, ob zusätzliche Informationen und Vollbildsymbole angezeigt werden sollen.</p>"
      }, {
        "name" : "noteEN",
        "type" : "STRING",
        "value" : "<p>The <strong>Process List Widget</strong> displays all available processes you can start in the portal.</p><p>A <strong>process</strong> is a predefined sequence of work steps, usually consisting of multiple related tasks, designed to achieve a specific goal.</p><p>In this widget, you can easily start a new business process with just a click. Starting a process automatically creates a new case for that process.</p><hr>  <p><strong>Display Modes:</strong></p>  <p>The widget supports four display modes to fit different use cases and preferences:</p>  <ul>    <li><strong>Combined Mode</strong> – Displays the selected process along with its related cases and tasks in a single view. Useful for tracking everything related to one process.</li>    <li><strong>Compact Mode</strong> – Lists all process starts in a compact format. You can sort them alphabetically, by usage, index, or in a custom order. Quick search by process name is also available.</li>    <li><strong>Full Mode</strong> – Shows a single process in a card layout with an icon and description.</li>    <li><strong>Image Mode</strong> – Similar to Full Mode but includes an image to visually represent the process.</li>  </ul>  <p>In the widget configuration, you can select the desired display mode, and choose whether to show or hide additional widget information and fullscreen icons.</p>"
      }, {
        "name" : "noteES",
        "type" : "STRING",
        "value" : "<p>El <strong>Process List Widget</strong> muestra todos los procesos disponibles que puedes iniciar en el portal.</p><p>Un <strong>proceso</strong> es una secuencia predefinida de pasos de trabajo, usualmente compuesta por múltiples tareas relacionadas, diseñada para lograr un objetivo específico.</p><p>Con este widget, puedes iniciar fácilmente un nuevo proceso de negocio con solo un clic. Iniciar un proceso crea automáticamente un nuevo caso para ese proceso.</p><hr><p><strong>Modos de visualización:</strong></p><p>El widget admite cuatro modos de visualización según el caso de uso y las preferencias:</p><ul><li><strong>Modo combinado</strong> – Muestra el proceso seleccionado junto con sus casos y tareas relacionadas en una sola vista. Útil para rastrear todo lo relacionado con un proceso.</li><li><strong>Modo compacto</strong> – Lista todos los procesos en formato compacto. Se pueden ordenar por nombre, uso, índice o de forma personalizada. Búsqueda rápida disponible.</li><li><strong>Modo completo</strong> – Muestra un solo proceso en formato de tarjeta con icono y descripción.</li><li><strong>Modo imagen</strong> – Similar al modo completo pero incluye una imagen representativa del proceso.</li></ul><p>En la configuración del widget puedes seleccionar el modo deseado y elegir si se muestran u ocultan información adicional y los iconos de pantalla completa.</p>"
      }, {
        "name" : "noteFR",
        "type" : "STRING",
        "value" : "<p>Le <strong>Process List Widget</strong> affiche tous les processus disponibles que vous pouvez lancer dans le portail.</p><p>Un <strong>processus</strong> est une séquence prédéfinie d'étapes de travail, généralement composée de plusieurs tâches liées, visant un objectif spécifique.</p><p>Grâce à ce widget, vous pouvez facilement lancer un nouveau processus métier en un seul clic. Cela crée automatiquement un nouveau cas pour ce processus.</p><hr><p><strong>Modes d’affichage :</strong></p><p>Le widget prend en charge quatre modes d'affichage pour s'adapter aux différents besoins et préférences :</p><ul><li><strong>Mode combiné</strong> – Affiche le processus sélectionné avec ses cas et tâches associés dans une seule vue. Utile pour suivre tout ce qui concerne un processus.</li><li><strong>Mode compact</strong> – Liste tous les processus disponibles dans un format compact. Tri par nom, utilisation, index ou ordre personnalisé. Recherche rapide disponible.</li><li><strong>Mode complet</strong> – Affiche un processus unique sous forme de carte avec icône et description.</li><li><strong>Mode image</strong> – Similaire au mode complet mais inclut une image représentant visuellement le processus.</li></ul><p>Dans la configuration du widget, vous pouvez choisir le mode d'affichage souhaité et décider d’afficher ou non des informations supplémentaires et les icônes plein écran.</p>"
      }, {
        "name" : "noteJP",
        "type" : "STRING",
        "value" : "<p><strong>プロセスリストウィジェット</strong>は、ポータルで開始可能なすべてのプロセスを表示します。</p><p><strong>プロセス</strong>とは、特定の目標を達成するために設計された、複数の関連タスクから成る定義済みの作業ステップの流れです。</p><p>このウィジェットを使えば、ワンクリックで簡単に新しいビジネスプロセスを開始できます。プロセスを開始すると、自動的に新しいケースが作成されます。</p><hr><p><strong>表示モード：</strong></p><p>このウィジェットは、用途や好みに応じて4つの表示モードをサポートしています：</p><ul><li><strong>結合モード</strong> – 選択したプロセスと関連するケースやタスクを1つのビューで表示。プロセスに関する全体を追跡するのに便利です。</li><li><strong>コンパクトモード</strong> – すべてのプロセスをコンパクトな形式で表示。名前、使用頻度、インデックス、カスタム順で並べ替え可能。プロセス名でのクイック検索も可能。</li><li><strong>フルモード</strong> – アイコンと説明付きで単一のプロセスをカード形式で表示。</li><li><strong>イメージモード</strong> – フルモードに似ていますが、プロセスを視覚的に表す画像を含みます。</li></ul><p>ウィジェットの設定で、希望する表示モードの選択や、追加情報および全画面"
      } ]
    },
    "showFullscreenMode" : true
  }, {
    "type" : "case",
    "id" : "case_bf0f154a9403498fb5979ce36540549b",
    "names" : [ {
      "locale" : "en",
      "value" : "Your Cases"
    }, {
      "locale" : "fr",
      "value" : "Your Cases"
    }, {
      "locale" : "de",
      "value" : "Your Cases"
    }, {
      "locale" : "ja",
      "value" : "Your Cases"
    }, {
      "locale" : "es",
      "value" : "Your Cases"
    } ],
    "layout" : {
      "w" : 8,
      "h" : 6,
      "x" : 4,
      "y" : 10
    },
    "enableQuickSearch" : false,
    "showWidgetInfo" : true,
    "showFullscreenMode" : true,
    "showPinnedToggle" : true,
    "columns" : [ {
      "field" : "id",
      "quickSearch" : false
    }, {
      "field" : "name",
      "quickSearch" : true
    }, {
      "field" : "description",
      "quickSearch" : true
    }, {
      "field" : "state"
    }, {
      "field" : "creator",
      "quickSearch" : false
    }, {
      "field" : "startTimestamp"
    }, {
      "field" : "endTimestamp"
    }, {
      "field" : "owner"
    }, {
      "field" : "category",
      "quickSearch" : false,
      "width" : "100"
    }, {
      "field" : "application",
      "styleClass" : "dashboard-tasks__priority text-center",
      "quickSearch" : false
    }, {
      "field" : "actions",
      "width" : "100"
    } ],
    "sortField" : "ID",
    "sortDescending" : true
  }, {
    "type" : "custom",
    "id" : "custom_e0861bc1bfe247a988e033bd57909719",
    "names" : [ {
      "locale" : "en",
      "value" : "Case List Widget"
    }, {
      "locale" : "fr",
      "value" : "Widget de liste des cas"
    }, {
      "locale" : "de",
      "value" : "Falllisten-Widget"
    }, {
      "locale" : "ja",
      "value" : "ケースリストウィジェット"
    }, {
      "locale" : "es",
      "value" : "Widget de lista de casos"
    } ],
    "layout" : {
      "w" : 4,
      "h" : 6,
      "x" : 0,
      "y" : 10
    },
    "data" : {
      "processPath" : "designer/portal-user-examples/Start Processes/UserExampleGuide/widgetNote.ivp",
      "params" : [ {
        "name" : "noteDE",
        "type" : "STRING",
        "value" : "<p>Das <strong>Case List Widget</strong> bietet einen Überblick über Ihre Fälle, bei denen es sich um einzelne Prozessinstanzen handelt, die bereits gestartet wurden.</p><p>Ein <strong>Fall</strong> stellt einen laufenden oder abgeschlossenen Prozess dar, der aus mehreren Schritten besteht und typischerweise über das Process Widget gestartet wurde.</p><p>Dieses Widget ermöglicht es Ihnen, den Status Ihrer Fälle zu überprüfen und nach bestimmten Instanzen zu suchen. Sie können auch Detailinformationen zu einzelnen Fällen anzeigen, um deren Fortschritt zu verfolgen.</p><p><strong>Jeder Fall in der Liste zeigt folgende Schlüsselinformationen:</strong></p><ul><li><strong>Fall-ID</strong></li><li><strong>Name</strong></li><li><strong>Beschreibung</strong></li><li><strong>Ersteller</strong></li><li><strong>Erstellungsdatum</strong></li><li><strong>Abschlussdatum</strong></li><li><strong>Status</strong></li><li><strong>Kategorie</strong></li></ul><p>Um weitere Details anzuzeigen oder Aktionen durchzuführen, nutzen Sie die Schaltfläche <strong>Aktionen</strong> am Ende jeder Zeile.</p><p>Sie können auch auf <strong>Name</strong> oder <strong>Beschreibung</strong> klicken, um auf die vollständigen Daten des Falls zuzugreifen.</p>"
      }, {
        "name" : "noteEN",
        "type" : "STRING",
        "value" : "<p>The <strong>Case List Widget</strong> provides an overview of your cases, which are individual process instances that have already been started.</p><p>A <strong>case</strong> represents an ongoing or completed process made up of several steps, typically initiated by starting a process via the Process Widget.</p><p>This widget allows you to check the status of your cases and search for specific instances. You can also view detailed information on individual cases to monitor their progress.</p><p><strong>Each case in the list shows the following key information:</strong></p><ul>  <li><strong>Case ID</strong></li>  <li><strong>Name</strong></li>  <li><strong>Description</strong></li>  <li><strong>Creator</strong></li>  <li><strong>Creation Date</strong></li>  <li><strong>Finished Date</strong></li>  <li><strong>State</strong></li>  <li><strong>Category</strong></li></ul><p>To view more details or take action on a case, use the <strong>Actions</strong> button found at the end of each row.</p><p>You can also click on the <strong>Name</strong> or <strong>Description</strong> to access the full set of data for that case.</p>"
      }, {
        "name" : "noteES",
        "type" : "STRING",
        "value" : "<p>El <strong>Case List Widget</strong> proporciona una vista general de tus casos, que son instancias de procesos individuales que ya han sido iniciadas.</p><p>Un <strong>caso</strong> representa un proceso en curso o completado compuesto por varios pasos, típicamente iniciado mediante el Process Widget.</p><p>Este widget te permite comprobar el estado de tus casos y buscar instancias específicas. También puedes ver información detallada sobre casos individuales para supervisar su progreso.</p><p><strong>Cada caso en la lista muestra la siguiente información clave:</strong></p><ul><li><strong>ID del caso</strong></li><li><strong>Nombre</strong></li><li><strong>Descripción</strong></li><li><strong>Creador</strong></li><li><strong>Fecha de creación</strong></li><li><strong>Fecha de finalización</strong></li><li><strong>Estado</strong></li><li><strong>Categoría</strong></li></ul><p>Para ver más detalles o realizar acciones sobre un caso, usa el botón <strong>Acciones</strong> al final de cada fila.</p><p>También puedes hacer clic en <strong>Nombre</strong> o <strong>Descripción</strong> para acceder al conjunto completo de datos de ese caso.</p>"
      }, {
        "name" : "noteFR",
        "type" : "STRING",
        "value" : "<p>Le <strong>Case List Widget</strong> fournit un aperçu de vos cas, qui sont des instances de processus individuelles déjà démarrées.</p><p>Un <strong>cas</strong> représente un processus en cours ou terminé composé de plusieurs étapes, généralement lancé via le Process Widget.</p><p>Ce widget vous permet de vérifier l’état de vos cas et de rechercher des instances spécifiques. Vous pouvez également afficher des informations détaillées sur chaque cas pour suivre leur progression.</p><p><strong>Chaque cas de la liste affiche les informations clés suivantes :</strong></p><ul><li><strong>ID du cas</strong></li><li><strong>Nom</strong></li><li><strong>Description</strong></li><li><strong>Créateur</strong></li><li><strong>Date de création</strong></li><li><strong>Date de fin</strong></li><li><strong>État</strong></li><li><strong>Catégorie</strong></li></ul><p>Pour voir plus de détails ou effectuer une action sur un cas, utilisez le bouton <strong>Actions</strong> à la fin de chaque ligne.</p><p>Vous pouvez également cliquer sur le <strong>Nom</strong> ou la <strong>Description</strong> pour accéder à l’ensemble complet des données du cas.</p>"
      }, {
        "name" : "noteJP",
        "type" : "STRING",
        "value" : "<p><strong>ケースリストウィジェット</strong>は、開始済みの個別プロセスインスタンスであるケースの概要を表示します。</p><p><strong>ケース</strong>とは、複数のステップで構成される進行中または完了済みのプロセスを指し、通常はプロセスウィジェットから開始されます。</p><p>このウィジェットでは、自分のケースのステータスを確認したり、特定のインスタンスを検索したりできます。また、個々のケースの詳細情報を表示して進捗を確認することも可能です。</p><p><strong>リスト内の各ケースには次の主要情報が表示されます：</strong></p><ul><li><strong>ケースID</strong></li><li><strong>名前</strong></li><li><strong>説明</strong></li><li><strong>作成者</strong></li><li><strong>作成日</strong></li><li><strong>完了日</strong></li><li><strong>状態</strong></li><li><strong>カテゴリ</strong></li></ul><p>ケースの詳細を表示したり操作したりするには、各行の最後にある<strong>アクション</strong>ボタンを使用します。</p><p"
      } ]
    },
    "showFullscreenMode" : true
  }, {
    "type" : "task",
    "id" : "task_754455ebfc124a09a6dfe993b44ffed6",
    "names" : [ {
      "locale" : "en",
      "value" : "Your Tasks"
    }, {
      "locale" : "fr",
      "value" : "Your Tasks"
    }, {
      "locale" : "de",
      "value" : "Your Tasks"
    }, {
      "locale" : "ja",
      "value" : "Your Tasks"
    }, {
      "locale" : "es",
      "value" : "Your Tasks"
    } ],
    "layout" : {
      "w" : 8,
      "h" : 8,
      "x" : 4,
      "y" : 16
    },
    "enableQuickSearch" : false,
    "showWidgetInfo" : true,
    "showFullscreenMode" : true,
    "showPinnedToggle" : true,
    "columns" : [ {
      "field" : "start"
    }, {
      "field" : "priority"
    }, {
      "field" : "id",
      "quickSearch" : false
    }, {
      "field" : "name",
      "quickSearch" : true
    }, {
      "field" : "description",
      "quickSearch" : true
    }, {
      "field" : "activator",
      "quickSearch" : false
    }, {
      "field" : "state"
    }, {
      "field" : "startTimestamp"
    }, {
      "field" : "expiryTimestamp"
    }, {
      "field" : "category",
      "quickSearch" : false
    }, {
      "field" : "application",
      "styleClass" : "dashboard-tasks__priority text-center widget-column",
      "quickSearch" : false
    }, {
      "field" : "actions"
    } ],
    "canWorkOn" : false,
    "sortField" : "ID",
    "sortDescending" : true
  }, {
    "type" : "custom",
    "id" : "custom_1babc4cdc40a40f5b1415f17f4320884",
    "names" : [ {
      "locale" : "en",
      "value" : "Task List Widget"
    }, {
      "locale" : "fr",
      "value" : "Widget de liste des tâches"
    }, {
      "locale" : "de",
      "value" : "Aufgabenlisten-Widget"
    }, {
      "locale" : "ja",
      "value" : "タスクリストウィジェット"
    }, {
      "locale" : "es",
      "value" : "Widget de lista de tareas"
    } ],
    "layout" : {
      "w" : 4,
      "h" : 8,
      "x" : 0,
      "y" : 16
    },
    "data" : {
      "processPath" : "designer/portal-user-examples/Start Processes/UserExampleGuide/widgetNote.ivp",
      "params" : [ {
        "name" : "noteDE",
        "type" : "STRING",
        "value" : "<p>Das <strong>Task List Widget</strong> bietet einen Überblick über die Ihnen zugewiesenen Aufgaben. Eine <strong>Aufgabe</strong> stellt einen einzelnen Schritt in einem Prozess dar und wird einem bestimmten Benutzer oder Team zugewiesen.</p><p>Dieses Widget hilft Ihnen, ausstehende Aufgaben schnell zu identifizieren und sie nach Priorität oder Fälligkeitsdatum zu sortieren oder zu filtern. Sie können eine Aufgabe direkt aus der Liste öffnen, um daran zu arbeiten.</p><p><strong>Jede Aufgabe in der Liste enthält folgende Schlüsselinformationen:</strong></p><ul><li><strong>Priorität</strong></li><li><strong>Aufgaben-ID</strong></li><li><strong>Name</strong></li><li><strong>Beschreibung</strong></li><li><strong>Verantwortlicher Benutzer oder Rolle</strong></li><li><strong>Status</strong></li><li><strong>Erstellungsdatum</strong></li><li><strong>Ablaufdatum</strong></li><li><strong>Kategorie</strong></li><li><strong>Anwendung</strong> (nicht standardmäßig angezeigt)</li></ul><p>Am Ende jeder Aufgabenzeile finden Sie ein <strong>Aktionsmenü</strong> mit Funktionen zur Aufgabenverwaltung:</p><ul><li><strong>Details</strong></li><li><strong>Aufgabe zurücksetzen</strong></li><li><strong>Aufgabe delegieren</strong></li><li><strong>Reservieren</strong></li><li><strong>Löschen</strong></li><li><strong>Eskalation auslösen</strong></li><li><strong>Workflow-Ereignisse</strong></li><li><strong>Prozessanzeige</strong></li></ul><p>Auf vollständige Aufgabendaten greifen Sie über das <strong>Aktionsmenü</strong> zu, indem Sie <strong>Details</strong> auswählen.</p>"
      }, {
        "name" : "noteEN",
        "type" : "STRING",
        "value" : "<p>The <strong>Task List Widget</strong> offers an overview of tasks assigned to you. A <strong>task</strong> represents an individual step within a process and is assigned to a specific user or team.</p><p>This widget helps you quickly identify which tasks are pending and allows you to sort or filter them by priority or due date. You can open a task directly from the list to begin working on it.</p><p><strong>Each task in the list includes the following key information:</strong></p><ul>  <li><strong>Priority</strong></li>  <li><strong>Task ID</strong></li>  <li><strong>Name</strong></li>  <li><strong>Description</strong></li>  <li><strong>Responsible user or role</strong></li>  <li><strong>State</strong></li>  <li><strong>Creation Date</strong></li>  <li><strong>Expiry Date</strong></li>  <li><strong>Category</strong></li>  <li><strong>Application</strong> (not displayed by default)</li></ul><p>At the end of each task row, you’ll find an <strong>Actions</strong> menu offering features to manage the task:</p><ul>  <li><strong>Details</strong></li>  <li><strong>Reset Task</strong></li>  <li><strong>Delegate Task</strong></li>  <li><strong>Reserve</strong></li>  <li><strong>Destroy</strong></li>  <li><strong>Trigger Escalation</strong></li>  <li><strong>Workflow Events</strong></li>  <li><strong>Process Viewer</strong></li></ul><p>You can access the full task data by clicking <strong>Actions</strong> and then selecting the <strong>Details</strong> menu item.</p>"
      }, {
        "name" : "noteES",
        "type" : "STRING",
        "value" : "<p>El <strong>Task List Widget</strong> ofrece una vista general de las tareas asignadas a ti. Una <strong>tarea</strong> representa un paso individual dentro de un proceso y se asigna a un usuario o equipo específico.</p><p>Este widget te ayuda a identificar rápidamente qué tareas están pendientes y te permite ordenarlas o filtrarlas por prioridad o fecha de vencimiento. Puedes abrir una tarea directamente desde la lista para comenzar a trabajar en ella.</p><p><strong>Cada tarea en la lista incluye la siguiente información clave:</strong></p><ul><li><strong>Prioridad</strong></li><li><strong>ID de la tarea</strong></li><li><strong>Nombre</strong></li><li><strong>Descripción</strong></li><li><strong>Usuario o rol responsable</strong></li><li><strong>Estado</strong></li><li><strong>Fecha de creación</strong></li><li><strong>Fecha de vencimiento</strong></li><li><strong>Categoría</strong></li><li><strong>Aplicación</strong> (no visible por defecto)</li></ul><p>Al final de cada fila de tarea encontrarás un menú de <strong>Acciones</strong> que ofrece funciones para gestionar la tarea:</p><ul><li><strong>Detalles</strong></li><li><strong>Reiniciar tarea</strong></li><li><strong>Delegar tarea</strong></li><li><strong>Reservar</strong></li><li><strong>Eliminar</strong></li><li><strong>Disparar escalada</strong></li><li><strong>Eventos de flujo de trabajo</strong></li><li><strong>Visor de procesos</strong></li></ul><p>Para acceder a todos los datos de la tarea, haz clic en <strong>Acciones</strong> y selecciona la opción <strong>Detalles</strong>.</p>"
      }, {
        "name" : "noteFR",
        "type" : "STRING",
        "value" : "<p>Le <strong>Task List Widget</strong> fournit un aperçu des tâches qui vous sont attribuées. Une <strong>tâche</strong> représente une étape individuelle dans un processus et est assignée à un utilisateur ou une équipe spécifique.</p><p>Ce widget vous aide à identifier rapidement les tâches en attente et vous permet de les trier ou filtrer par priorité ou date d’échéance. Vous pouvez ouvrir une tâche directement depuis la liste pour commencer à la traiter.</p><p><strong>Chaque tâche de la liste contient les informations clés suivantes :</strong></p><ul><li><strong>Priorité</strong></li><li><strong>ID de la tâche</strong></li><li><strong>Nom</strong></li><li><strong>Description</strong></li><li><strong>Utilisateur ou rôle responsable</strong></li><li><strong>État</strong></li><li><strong>Date de création</strong></li><li><strong>Date d’expiration</strong></li><li><strong>Catégorie</strong></li><li><strong>Application</strong> (non affichée par défaut)</li></ul><p>À la fin de chaque ligne de tâche, vous trouverez un menu <strong>Actions</strong> proposant des fonctions pour gérer la tâche :</p><ul><li><strong>Détails</strong></li><li><strong>Réinitialiser la tâche</strong></li><li><strong>Déléguer la tâche</strong></li><li><strong>Réserver</strong></li><li><strong>Supprimer</strong></li><li><strong>Déclencher une escalade</strong></li><li><strong>Événements de workflow</strong></li><li><strong>Visionneuse de processus</strong></li></ul><p>Vous pouvez accéder à toutes les données de la tâche en cliquant sur <strong>Actions</strong> puis en sélectionnant <strong>Détails</strong>.</p>"
      }, {
        "name" : "noteJP",
        "type" : "STRING",
        "value" : "<p><strong>タスクリストウィジェット</strong>は、自分に割り当てられたタスクの概要を提供します。<strong>タスク</strong>はプロセス内の個々のステップを表し、特定のユーザーまたはチームに割り当てられます。</p><p>このウィジェットは、保留中のタスクをすばやく特定し、優先度や期限で並べ替えたりフィルターしたりするのに役立ちます。リストから直接タスクを開いて作業を開始できます。</p><p><strong>リスト内の各タスクには次の重要情報が含まれます：</strong></p><ul><li><strong>優先度</strong></li><li><strong>タスクID</strong></li><li><strong>名前</strong></li><li><strong>説明</strong></li><li><strong>担当ユーザーまたは役割</strong></li><li><strong>状態</strong></li><li><strong>作成日</strong></li><li><strong>期限日</strong></li><li><strong>カテゴリ</strong></li><li><strong>アプリケーション</strong>（デフォルトでは非表示）</li></ul><p>各タスク行の末尾には、タスクを管理するための<strong>アクション</strong>メニューがあります：</p><ul><li><strong>詳細</strong></li><li><strong>タスクのリセット</strong></li><li><strong>タスクの委任</strong></li><li><strong>予約</strong></li><li><strong>削除</strong></li><li><strong>エスカレーションをトリガー</strong></li><li><strong>ワークフローイベント</strong></li><li><strong>プロセスビューア</strong></li></ul><p><strong>アクション</strong>をクリックし、<strong>詳細</strong>を選択すると、タスクのすべてのデータにアクセスできます。</p>"
      } ]
    },
    "showFullscreenMode" : true
  }, {
    "type" : "custom",
    "id" : "custom_7e93ca6ac08243fb9bf95e676c607175",
    "names" : [ {
      "locale" : "en",
      "value" : "Statistic Widget"
    }, {
      "locale" : "fr",
      "value" : "Widget de statistiques"
    }, {
      "locale" : "de",
      "value" : "Statistik-Widget"
    }, {
      "locale" : "ja",
      "value" : "ダッシュボード統計ウィジェット"
    }, {
      "locale" : "es",
      "value" : "Widget de estadísticas"
    } ],
    "layout" : {
      "w" : 4,
      "h" : 7,
      "x" : 0,
      "y" : 24
    },
    "data" : {
      "processPath" : "designer/portal-user-examples/Start Processes/UserExampleGuide/widgetNote.ivp",
      "params" : [ {
        "name" : "noteDE",
        "type" : "STRING",
        "value" : "<p>Das <strong>Statistics Widget</strong> zeigt statistische Analysen in klaren Diagrammen und bietet schnelle Einblicke in wichtige Kennzahlen zu Ihren Aufgaben und Fällen.</p><p>Dieses Widget wird typischerweise verwendet, um Ihre <strong>Arbeitslast</strong> und <strong>Prozesseffizienz</strong> zu analysieren. Die visuelle Darstellung hilft Ihnen, Trends und Muster schnell zu erkennen.</p><p>Beispielsweise können angezeigt werden:</p><ul><li><strong>Wie viele Aufgaben mit hoher Priorität</strong> derzeit offen sind</li><li><strong>Wie viele Fälle</strong> in den letzten Tagen gestartet wurden</li></ul><p><strong>Verfügbare Diagrammtypen und Einblicke umfassen:</strong></p><ul><li><strong>Aufgaben nach Priorität (Kreis)</strong> – zeigt Aufgaben gruppiert nach Priorität</li><li><strong>Top-Priorität (Balken)</strong> – Aufgaben, die in den nächsten 3 Tagen ablaufen</li><li><strong>Aufgaben nach Priorität (Balken)</strong> – gleiche Gruppierung im Balkendiagramm</li><li><strong>Durchschnittliche Laufzeit nach Fallkategorie (Balken)</strong> – durchschnittliche Bearbeitungszeit nach Kategorie</li><li><strong>Neue Fälle pro Tag (Linie)</strong> – Anzahl neu gestarteter Fälle in den letzten 5 Tagen</li><li><strong>Abgeschlossene Fälle pro Tag (Linie)</strong> – Anzahl abgeschlossener Fälle in den letzten 5 Tagen</li><li><strong>Laufende Fälle (Anzahl)</strong> – aktuell aktive Fälle mit Benutzerbeteiligung</li><li><strong>Diese Woche auslaufende Aufgaben (Anzahl)</strong> – Aufgaben, die vor Ende der Woche ablaufen</li><li><strong>Aufgaben nach Priorität (Anzahl)</strong> – Aufgabenanzahl je Priorität</li><li><strong>Offene Aufgaben (Anzahl)</strong> – Gesamtanzahl der dem Benutzer verfügbaren Aufgaben</li><li><strong>Heute fällige Aufgaben (Anzahl)</strong> – Aufgaben, die heute ablaufen</li></ul><p>Sie können diese Diagramme über die <strong>Dashboard-Konfiguration</strong> hinzufügen und konfigurieren, indem Sie aus verschiedenen Widgets im Bereich <strong>Statistik-Widgets</strong> wählen.</p>"
      }, {
        "name" : "noteEN",
        "type" : "STRING",
        "value" : "<p>The <strong>Statistics Widget</strong> presents statistical analyses through clear diagrams, offering quick insights into important metrics regarding your tasks and cases.</p><p>This widget is typically used to analyze your <strong>workload</strong> and <strong>process efficiency</strong>. The visual representation helps you quickly identify trends and patterns.</p><p>For example, it may display:</p><ul>  <li><strong>How many high-priority tasks</strong> are currently pending</li>  <li><strong>How many cases</strong> were initiated over recent days</li></ul><p><strong>Available chart types and insights include:</strong></p><ul>  <li><strong>Tasks By Priority (Pie)</strong> – displays tasks grouped by priority</li>  <li><strong>Top Priority (Bar)</strong> – tasks expiring within the next 3 days</li>  <li><strong>Tasks By Priority (Bar)</strong> – same grouping in a bar layout</li>  <li><strong>Case Category Avg. Runtime (Bar)</strong> – average processing time of cases by category</li>  <li><strong>New Cases Per Day (Line)</strong> – number of new cases started over the last 5 days</li>  <li><strong>Completed Cases Per Day (Line)</strong> – number of completed cases in the last 5 days</li>  <li><strong>Running Cases (Number)</strong> – count of currently active cases involving the user</li>  <li><strong>Tasks That Expire This Week (Number)</strong> – tasks expiring before the week ends</li>  <li><strong>Tasks By Priority (Number)</strong> – task count by priority</li>  <li><strong>Open Tasks (Number)</strong> – total tasks available to the user</li>  <li><strong>Tasks Due Today (Number)</strong> – tasks that expire today</li></ul><p>You can add and configure these charts through the <strong>Dashboard Configuration</strong>, choosing from a variety of widgets under the <strong>Statistic Widgets</strong> section.</p>"
      }, {
        "name" : "noteES",
        "type" : "STRING",
        "value" : "<p>El <strong>Statistics Widget</strong> presenta análisis estadísticos a través de diagramas claros, ofreciendo información rápida sobre métricas importantes relacionadas con tus tareas y casos.</p><p>Este widget se utiliza generalmente para analizar tu <strong>carga de trabajo</strong> y la <strong>eficiencia de los procesos</strong>. La representación visual te ayuda a identificar rápidamente tendencias y patrones.</p><p>Por ejemplo, puede mostrar:</p><ul><li><strong>Cuántas tareas de alta prioridad</strong> están pendientes actualmente</li><li><strong>Cuántos casos</strong> se iniciaron en los últimos días</li></ul><p><strong>Tipos de gráficos e insights disponibles:</strong></p><ul><li><strong>Tareas por Prioridad (Torta)</strong> – muestra tareas agrupadas por prioridad</li><li><strong>Prioridad Alta (Barra)</strong> – tareas que vencen en los próximos 3 días</li><li><strong>Tareas por Prioridad (Barra)</strong> – misma agrupación en formato de barras</li><li><strong>Promedio de Tiempo por Categoría de Caso (Barra)</strong> – tiempo promedio de procesamiento por categoría</li><li><strong>Nuevos Casos por Día (Línea)</strong> – número de casos iniciados en los últimos 5 días</li><li><strong>Casos Completados por Día (Línea)</strong> – número de casos finalizados en los últimos 5 días</li><li><strong>Casos Activos (Número)</strong> – cantidad de casos activos actualmente</li><li><strong>Tareas que Vencen Esta Semana (Número)</strong> – tareas con vencimiento esta semana</li><li><strong>Tareas por Prioridad (Número)</strong> – cantidad de tareas por prioridad</li><li><strong>Tareas Abiertas (Número)</strong> – total de tareas disponibles para el usuario</li><li><strong>Tareas para Hoy (Número)</strong> – tareas que vencen hoy</li></ul><p>Puedes añadir y configurar estos gráficos desde la <strong>Configuración del Panel</strong>, eligiendo entre varios widgets en la sección <strong>Statistic Widgets</strong>.</p>"
      }, {
        "name" : "noteFR",
        "type" : "STRING",
        "value" : "<p>Le <strong>Statistics Widget</strong> présente des analyses statistiques sous forme de diagrammes clairs, offrant un aperçu rapide des indicateurs clés liés à vos tâches et dossiers.</p><p>Ce widget est généralement utilisé pour analyser votre <strong>charge de travail</strong> et <strong>l’efficacité des processus</strong>. La représentation visuelle vous aide à identifier rapidement les tendances et les modèles.</p><p>Par exemple, il peut afficher :</p><ul><li><strong>Combien de tâches prioritaires</strong> sont en attente</li><li><strong>Combien de dossiers</strong> ont été lancés récemment</li></ul><p><strong>Types de graphiques et aperçus disponibles :</strong></p><ul><li><strong>Tâches par Priorité (Camembert)</strong> – affiche les tâches regroupées par priorité</li><li><strong>Priorité Élevée (Barres)</strong> – tâches expirant dans les 3 prochains jours</li><li><strong>Tâches par Priorité (Barres)</strong> – même regroupement en format barre</li><li><strong>Durée Moyenne par Catégorie de Dossier (Barres)</strong> – temps moyen de traitement par catégorie</li><li><strong>Nouveaux Dossiers par Jour (Ligne)</strong> – nombre de nouveaux dossiers au cours des 5 derniers jours</li><li><strong>Dossiers Complétés par Jour (Ligne)</strong> – nombre de dossiers terminés au cours des 5 derniers jours</li><li><strong>Dossiers en Cours (Nombre)</strong> – nombre de dossiers actuellement actifs</li><li><strong>Tâches Expirant Cette Semaine (Nombre)</strong> – tâches expirant avant la fin de la semaine</li><li><strong>Tâches par Priorité (Nombre)</strong> – nombre de tâches par priorité</li><li><strong>Tâches Ouvertes (Nombre)</strong> – total des tâches disponibles pour l’utilisateur</li><li><strong>Tâches à Rendre Aujourd’hui (Nombre)</strong> – tâches arrivant à échéance aujourd’hui</li></ul><p>Vous pouvez ajouter et configurer ces graphiques via la <strong>Configuration du Tableau de Bord</strong>, en choisissant parmi plusieurs widgets dans la section <strong>Statistic Widgets</strong>.</p>"
      }, {
        "name" : "noteJP",
        "type" : "STRING",
        "value" : "<p><strong>Statisticsウィジェット</strong>は、わかりやすい図表で統計分析を表示し、タスクやケースに関する重要な指標を迅速に把握できます。</p><p>このウィジェットは、主に<strong>作業負荷</strong>や<strong>プロセスの効率</strong>を分析するために使用され、視覚的な表示によって傾向やパターンを素早く特定できます。</p><p>たとえば、以下の情報が表示されることがあります：</p><ul><li><strong>現在保留中の高優先度タスク数</strong></li><li><strong>直近数日間で開始されたケース数</strong></li></ul><p><strong>利用可能なチャートの種類とインサイト：</strong></p><ul><li><strong>優先度別タスク（円グラフ）</strong> – 優先度でグループ化されたタスクを表示</li><li><strong>最優先タスク（棒グラフ）</strong> – 次の3日以内に期限切れとなるタスク</li><li><strong>優先度別タスク（棒グラフ）</strong> – 棒グラフ形式での同様の分類</li><li><strong>ケースカテゴリ別平均実行時間（棒グラフ）</strong> – カテゴリごとの平均処理時間</li><li><strong>1日あたりの新規ケース数（折れ線）</strong> – 過去5日間の開始件数</li><li><strong>1日あたりの完了ケース数（折れ線）</strong> – 過去5日間の完了件数</li><li><strong>実行中のケース数（数値）</strong> – 現在アクティブなケース数</li><li><strong>今週期限切れのタスク数（数値）</strong> – 今週中に期限を迎えるタスク</li><li><strong>優先度別タスク数（数値）</strong> – 優先度ごとのタスク数</li><li><strong>未処理タスク数（数値）</strong> – ユーザーが利用可能なタスク総数</li><li><strong>本日期限のタスク（数値）</strong> – 本日期限を迎えるタスク</li></ul><p><strong>ダッシュボード設定</strong>でこれらのチャートを追加・設定でき、<strong>Statistic Widgets</strong>セクションからさまざまなウィジェットを選択できます。</p>"
      } ]
    },
    "showFullscreenMode" : true
  }, {
    "type" : "client-statistic",
    "id" : "client_statistic_db60d71f81b44662964226ae3588c4ec",
    "layout" : {
      "w" : 2,
      "h" : 7,
      "x" : 4,
      "y" : 24
    },
    "chartId" : "1",
    "showFullscreenMode" : true
  }, {
    "type" : "client-statistic",
    "id" : "client_statistic_090824eb9a8b444c8888b7760306522e",
    "layout" : {
      "w" : 2,
      "h" : 7,
      "x" : 10,
      "y" : 24
    },
    "chartId" : "10",
    "showFullscreenMode" : true
  }, {
    "type" : "client-statistic",
    "id" : "client_statistic_47ee7264b70f4ffab93ae75c7d101a90",
    "layout" : {
      "w" : 4,
      "h" : 7,
      "x" : 6,
      "y" : 24
    },
    "chartId" : "4",
    "showFullscreenMode" : true
  }, {
    "type" : "custom",
    "id" : "custom_2ff4b189c4df450581e6956d6522e7f6",
    "names" : [ {
      "locale" : "en",
      "value" : "Axon Ivy Portal Documentation"
    }, {
      "locale" : "fr",
      "value" : "Documentation Axon Ivy Portal"
    }, {
      "locale" : "de",
      "value" : "Axon Ivy Portal Dokumentation"
    }, {
      "locale" : "ja",
      "value" : "Axon Ivy Portal ドキュメント"
    }, {
      "locale" : "es",
      "value" : "Documentación de Axon Ivy Portal"
    } ],
    "layout" : {
      "w" : 7,
      "h" : 7,
      "x" : 5,
      "y" : 31
    },
    "data" : {
      "url" : "https://market.axonivy.com/portal/dev/doc"
    },
    "showFullscreenMode" : true
  }, {
    "type" : "combined-process",
    "id" : "process_fc892608928a4b05b84c72cb035aaafb",
    "names" : [ {
      "locale" : "en",
      "value" : "Leave Request"
    }, {
      "locale" : "fr",
      "value" : "Leave Request"
    }, {
      "locale" : "de",
      "value" : "Leave Request"
    }, {
      "locale" : "ja",
      "value" : "Leave Request"
    }, {
      "locale" : "es",
      "value" : "Leave Request"
    } ],
    "layout" : {
      "w" : 5,
      "h" : 7,
      "x" : 7,
      "y" : 3
    },
    "showFullscreenMode" : true,
    "showWidgetInfo" : true,
    "processPath" : "portal-user-examples/Start Processes/LeaveRequest/start.ivp",
    "rowsPerPage" : 5
  }, {
    "type" : "custom",
    "id" : "custom_641e9800adb34b72a2694a3f32780c59",
    "names" : [ {
      "locale" : "en",
      "value" : "About Axon Ivy Portal"
    }, {
      "locale" : "fr",
      "value" : "Documentation"
    }, {
      "locale" : "de",
      "value" : "Documentation"
    }, {
      "locale" : "ja",
      "value" : "Documentation"
    }, {
      "locale" : "es",
      "value" : "Documentation"
    } ],
    "layout" : {
      "w" : 5,
      "h" : 7,
      "x" : 0,
      "y" : 31
    },
    "data" : {
      "processPath" : "designer/portal-user-examples/Start Processes/UserExampleGuide/widgetNote.ivp",
      "params" : [ {
        "name" : "noteDE",
        "type" : "STRING",
        "value" : "<body><p><strong>Axon Ivy Portal</strong> ist Ihr zentraler Einstiegspunkt für die Arbeit mit Geschäftsapplikationen, die auf der Axon Ivy Plattform basieren.</p><p>Das Portal wurde sowohl für Endbenutzer als auch für Geschäftsteams entwickelt und vereinfacht Ihre täglichen Abläufe durch eine intuitive, webbasierte Oberfläche, die nahtlos auf Desktop- und Mobilgeräten funktioniert.</p><p>Mit dem Portal können Sie:</p><ul><li><strong>Neue Geschäftsprozesse starten</strong> über das Process List Widget — Workflows mit nur wenigen Klicks starten.</li><li><strong>Fälle verwalten</strong> mit dem Case List Widget — alle Prozessinstanzen einsehen, verfolgen und nachverfolgen.</li><li><strong>Aufgaben bearbeiten</strong> über das Task List Widget — anstehende Aufgaben, Fristen und Prioritäten leicht einsehen.</li><li><strong>Ihre Arbeitslast analysieren</strong> mit dem Statistics Widget — Leistung überwachen und Trends mit Diagrammen und Kennzahlen erkennen.</li><li><strong>Ihren Arbeitsbereich anpassen</strong> — Dashboards und Widgets nach Ihren Vorlieben konfigurieren.</li></ul><p>Das Axon Ivy Portal wurde mit Blick auf Erweiterbarkeit und Anpassung entwickelt. Von personalisierten Dashboards bis hin zu tiefer Integration in Ihre Geschäftslogik – Sie können das Portal auf die Bedürfnisse von Einzelpersonen, Teams und Abteilungen zuschneiden.</p><p>Benötigen Sie Hilfe, um das Beste herauszuholen? Besuchen Sie unsere <strong>Dokumentationsseite</strong> auf dem <strong>Axon Ivy Market</strong> für umfassende Anleitungen, Beispiele und Best Practices.</p></body>"
      }, {
        "name" : "noteEN",
        "type" : "STRING",
        "value" : "<body>  <p><strong>Axon Ivy Portal</strong> is your centralized entry point for working with business applications built on the Axon Ivy Platform.</p><p>Designed for both end users and business teams, the Portal streamlines your daily operations through an intuitive, web-based interface that runs seamlessly across desktop and mobile devices.</p>  <p>With the Portal, you can:</p>  <ul>    <li><strong>Start new business processes</strong> quickly through the Process List Widget — launch workflows with just a few clicks.</li>    <li><strong>Manage cases</strong> through the Case List Widget — view, track, and follow up on all process instances you are involved in.</li>    <li><strong>Work on your tasks</strong> using the Task List Widget — easily view pending assignments, deadlines, and priorities.</li>    <li><strong>Analyze your workload</strong> via the Statistics Widget — monitor performance and uncover trends with charts and metrics.</li>    <li><strong>Customize your workspace</strong> — configure dashboards and widgets to fit your working style and preferences.</li>  </ul>  <p>The Axon Ivy Portal is built with extensibility and customization in mind. From personalized dashboards to deep integration with your business logic, you can tailor the Portal to meet the needs of individuals, teams, and departments.</p><p>Need help getting the most out of it? Visit our <strong>documentation page</strong> available on the <strong>Axon Ivy Market</strong> for comprehensive guides, examples, and best practices.</p></body>"
      }, {
        "name" : "noteES",
        "type" : "STRING",
        "value" : "<body><p><strong>Axon Ivy Portal</strong> es tu punto de entrada centralizado para trabajar con aplicaciones empresariales desarrolladas en la plataforma Axon Ivy.</p><p>Diseñado para usuarios finales y equipos de negocio, el Portal optimiza tus operaciones diarias mediante una interfaz web intuitiva que funciona perfectamente en dispositivos de escritorio y móviles.</p><p>Con el Portal puedes:</p><ul><li><strong>Iniciar nuevos procesos empresariales</strong> rápidamente con el widget Process List — inicia flujos de trabajo con solo unos clics.</li><li><strong>Gestionar casos</strong> con el widget Case List — visualiza, haz seguimiento y da seguimiento a todas las instancias de procesos en las que participas.</li><li><strong>Trabajar en tus tareas</strong> usando el widget Task List — visualiza fácilmente tareas pendientes, fechas límite y prioridades.</li><li><strong>Analizar tu carga de trabajo</strong> con el widget Statistics — supervisa el rendimiento y detecta tendencias con gráficos y métricas.</li><li><strong>Personalizar tu espacio de trabajo</strong> — configura tableros y widgets según tu estilo y preferencias.</li></ul><p>El Portal de Axon Ivy está diseñado pensando en la extensibilidad y la personalización. Desde tableros personalizados hasta integraciones profundas con la lógica empresarial, puedes adaptar el Portal a las necesidades de individuos, equipos y departamentos.</p><p>¿Necesitas ayuda para sacarle el máximo provecho? Visita nuestra <strong>página de documentación</strong> disponible en el <strong>Axon Ivy Market</strong> para guías completas, ejemplos y buenas prácticas.</p></body>"
      }, {
        "name" : "noteFR",
        "type" : "STRING",
        "value" : "<body><p><strong>Axon Ivy Portal</strong> est votre point d’entrée centralisé pour travailler avec des applications métier basées sur la plateforme Axon Ivy.</p><p>Conçu pour les utilisateurs finaux et les équipes métier, le Portail simplifie vos opérations quotidiennes grâce à une interface web intuitive qui fonctionne parfaitement sur ordinateurs et appareils mobiles.</p><p>Avec le Portail, vous pouvez :</p><ul><li><strong>Lancer de nouveaux processus métier</strong> rapidement avec le widget Process List — démarrez des workflows en quelques clics.</li><li><strong>Gérer les dossiers</strong> avec le widget Case List — visualisez, suivez et intervenez sur toutes les instances de processus impliquant votre participation.</li><li><strong>Travailler sur vos tâches</strong> grâce au widget Task List — visualisez facilement les tâches en attente, les délais et les priorités.</li><li><strong>Analyser votre charge de travail</strong> via le widget Statistics — surveillez les performances et identifiez les tendances avec des graphiques et des métriques.</li><li><strong>Personnaliser votre espace de travail</strong> — configurez les tableaux de bord et widgets selon votre style et vos préférences.</li></ul><p>Le Portail Axon Ivy est conçu pour être extensible et personnalisable. Des tableaux de bord personnalisés à une intégration poussée avec votre logique métier, vous pouvez adapter le Portail aux besoins des individus, des équipes et des départements.</p><p>Besoin d’aide pour en tirer le meilleur parti ? Consultez notre <strong>page de documentation</strong> disponible sur le <strong>Marché Axon Ivy</strong> pour des guides complets, des exemples et des meilleures pratiques.</p></body>"
      }, {
        "name" : "noteJP",
        "type" : "STRING",
        "value" : "<body><p><strong>Axon Ivyポータル</strong>は、Axon Ivyプラットフォーム上に構築された業務アプリケーションを操作するための集中エントリーポイントです。</p><p>エンドユーザーおよびビジネスチームの両方のために設計されたこのポータルは、直感的なWebベースのインターフェースを通じて、デスクトップとモバイルの両方でシームレスに日常業務を効率化します。</p><p>このポータルでは、次のことができます：</p><ul><li><strong>新しい業務プロセスを開始</strong> — Process Listウィジェットから数クリックでワークフローを開始できます。</li><li><strong>ケースを管理</strong> — Case Listウィジェットを使用して、関与しているすべてのプロセスインスタンスを表示・追跡・フォローアップ。</li><li><strong>タスクを処理</strong> — Task Listウィジェットで、保留中のタスク、締切、優先順位を簡単に確認。</li><li><strong>作業負荷を分析</strong> — Statisticsウィジェットで、パフォーマンスを監視し、グラフや指標を使って傾向を把握。</li><li><strong>作業スペースをカスタマイズ</strong> — ダッシュボードやウィジェットを自分のスタイルに合わせて設定。</li></ul><p>Axon Ivyポータルは、拡張性とカスタマイズ性を重視して構築されています。個人、チーム、部署のニーズに応じて、ポータルを柔軟に調整できます。</p><p>もっと活用したいですか？<strong>Axon Ivy Market</strong>にある<strong>ドキュメントページ</strong>を訪れて、詳細なガイド、サンプル、ベストプラクティスをご覧ください。</p></body>"
      } ]
    },
    "showFullscreenMode" : true
  } ],
  "permissions" : [ "Everybody" ],
  "dashboardDisplayType" : "top_menu"
}
          """;
  public static Dashboard getDefaultTaskListDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_TASK_LIST_DASHBOARD_JSON);
  }

  public static Dashboard getDefaultCaseListDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_CASE_LIST_DASHBOARD_JSON);
  }

  public static Dashboard getDefaultUserExampleDashboard() {
    return DashboardUtils.jsonToDashboard(DEFAULT_USER_GUIDE_DASHBOARD);
  }
}
