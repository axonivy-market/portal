package com.axonivy.portal.userexamples.utils;

public class TraningDashboardUtils {
  public static String traningDashboard = """
      [
  {
    "id": "training-dashboard",
    "version": "12.0.0",
    "templateId": "create-from-scratch",
    "titles": [
      { "locale": "en", "value": "🎓 Welcome to Axon Ivy Portal - Your Training Dashboard" },
      { "locale": "de", "value": "🎓 Willkommen im Axon Ivy Portal - Ihr Trainings-Dashboard" },
      { "locale": "fr", "value": "🎓 Bienvenue dans Axon Ivy Portal - Votre tableau de bord de formation" },
      { "locale": "es", "value": "🎓 Bienvenido a Axon Ivy Portal - Su panel de entrenamiento" },
      { "locale": "ja", "value": "🎓 Axon Ivy Portal へようこそ - トレーニング ダッシュボード" }
    ],
    "description": "Training dashboard for new Portal users",
    "icon": "si-learning",
    "widgets": [
      {
        "type": "welcome",
        "id": "training_welcome",
        "names": [
          { "locale": "en", "value": "Welcome to Your Training Dashboard!" },
          { "locale": "de", "value": "Willkommen in Ihrem Trainings-Dashboard!" },
          { "locale": "fr", "value": "Bienvenue dans votre tableau de bord de formation!" },
          { "locale": "es", "value": "¡Bienvenido a su panel de entrenamiento!" },
          { "locale": "ja", "value": "トレーニング ダッシュボードへようこそ!" }
        ],
        "layout": { "w": 12, "h": 4, "x": 0, "y": 0 },
        "welcomeTextPosition": "CENTER",
        "welcomeTextSize": "HEADING_1",
        "welcomeTextColor": "#ffffff",
        "welcomeTextColorDarkMode": "#ffffff",
        "welcomeTexts": [
          { "locale": "en", "value": "🎓 Welcome to Axon Ivy Portal Training!" },
          { "locale": "de", "value": "🎓 Willkommen beim Axon Ivy Portal Training!" },
          { "locale": "fr", "value": "🎓 Bienvenue à la formation Axon Ivy Portal!" },
          { "locale": "es", "value": "🎓 ¡Bienvenido al entrenamiento de Axon Ivy Portal!" },
          { "locale": "ja", "value": "🎓 Axon Ivy Portal トレーニングへようこそ!" }
        ],
        "imagePosition": "BACKGROUND",
        "imageContentUri": "/logos/portal-logo.svg",
        "backgroundColor": "#2563eb",
        "backgroundColorDarkMode": "#1d4ed8"
      },
      {
        "type": "custom",
        "id": "training_overview",
        "names": [
          { "locale": "en", "value": "Portal Overview & Key Features" },
          { "locale": "de", "value": "Portal-Übersicht und Hauptfunktionen" },
          { "locale": "fr", "value": "Aperçu du portail et fonctionnalités clés" },
          { "locale": "es", "value": "Descripción general del portal y características clave" },
          { "locale": "ja", "value": "ポータル概要と主要機能" }
        ],
        "layout": { "w": 6, "h": 8, "x": 0, "y": 4 },
        "data": {
          "processPath": "designer/portal-user-examples/Start Processes/UserExampleGuide/portalOverview.ivp",
          "params": []
        },
        "showFullscreenMode": true
      },
      {
        "type": "custom",
        "id": "training_guide",
        "names": [
          { "locale": "en", "value": "🚀 Getting Started Guide" },
          { "locale": "de", "value": "🚀 Erste Schritte Anleitung" },
          { "locale": "fr", "value": "🚀 Guide de démarrage" },
          { "locale": "es", "value": "🚀 Guía de inicio" },
          { "locale": "ja", "value": "🚀 はじめに ガイド" }
        ],
        "layout": { "w": 6, "h": 8, "x": 6, "y": 4 },
        "data": {
          "processPath": "designer/portal-user-examples/Start Processes/UserExampleGuide/gettingStarted.ivp",
          "params": []
        },
        "showFullscreenMode": true
      },
      {
        "type": "task",
        "id": "training_tasks",
        "names": [
          { "locale": "en", "value": "📋 Sample Tasks - Try Working with Tasks" },
          { "locale": "de", "value": "📋 Beispielaufgaben - Probieren Sie die Arbeit mit Aufgaben aus" },
          { "locale": "fr", "value": "📋 Tâches d'exemple - Essayez de travailler avec des tâches" },
          { "locale": "es", "value": "📋 Tareas de muestra - Pruebe trabajar con tareas" },
          { "locale": "ja", "value": "📋 サンプル タスク - タスクでの作業を試す" }
        ],
        "layout": { "w": 6, "h": 6, "x": 0, "y": 12 },
        "enableQuickSearch": true,
        "showWidgetInfo": true,
        "showFullscreenMode": true,
        "canWorkOn": true,
        "filters": [
          {
            "field": "state",
            "values": ["OPEN", "IN_PROGRESS"],
            "operator": "in",
            "type": "standard"
          }
        ],
        "columns": [
          { "field": "start" },
          { "field": "priority" },
          { "field": "name", "quickSearch": true },
          { "field": "description", "quickSearch": true },
          { "field": "activator" },
          { "field": "actions" }
        ],
        "sortDescending": false,
        "sortField": "priority"
      },
      {
        "type": "case",
        "id": "training_cases",
        "names": [
          { "locale": "en", "value": "📁 Sample Cases - Explore Case Management" },
          { "locale": "de", "value": "📁 Beispielfälle - Erkunden Sie das Case-Management" },
          { "locale": "fr", "value": "📁 Cas d'exemple - Explorez la gestion des cas" },
          { "locale": "es", "value": "📁 Casos de muestra - Explore la gestión de casos" },
          { "locale": "ja", "value": "📁 サンプル ケース - ケース管理を探索" }
        ],
        "layout": { "w": 6, "h": 6, "x": 6, "y": 12 },
        "enableQuickSearch": true,
        "showWidgetInfo": true,
        "showFullscreenMode": true,
        "filters": [
          {
            "field": "state",
            "values": ["OPEN", "RUNNING"],
            "operator": "in",
            "type": "standard"
          }
        ],
        "columns": [
          { "field": "id", "quickSearch": false },
          { "field": "name", "quickSearch": true },
          { "field": "description", "quickSearch": true },
          { "field": "state" },
          { "field": "creator" },
          { "field": "startTimestamp" },
          { "field": "actions" }
        ],
        "sortDescending": true,
        "sortField": "startTimestamp"
      },
      {
        "type": "custom",
        "id": "training_completion",
        "names": [
          { "locale": "en", "value": "✅ Complete Your Training" },
          { "locale": "de", "value": "✅ Schließen Sie Ihr Training ab" },
          { "locale": "fr", "value": "✅ Terminez votre formation" },
          { "locale": "es", "value": "✅ Complete su entrenamiento" },
          { "locale": "ja", "value": "✅ トレーニングを完了する" }
        ],
        "layout": { "w": 12, "h": 4, "x": 0, "y": 18 },
        "data": {
          "processPath": "designer/portal-user-examples/Start Processes/UserExampleGuide/completeTraining.ivp",
          "params": []
        },
        "showFullscreenMode": false
      }
    ],
    "permissions": ["Everybody"],
    "isTopMenu": false
  }
]
            """;
}
