# Axon Ivy Portal
Das **Axon Ivy Portal** ist dein zentraler Einstiegspunkt für die Arbeit mit Geschäftsanwendungen, die auf der **Axon Ivy Platform** entwickelt wurden.

Das Portal wurde sowohl für Endanwender als auch für Fachabteilungen konzipiert und vereinfacht deine täglichen Abläufe durch eine intuitive, webbasierte Oberfläche, die nahtlos auf Desktop- und Mobilgeräten funktioniert.

Mit dem Portal kannst du:

* **Neue Geschäftsprozesse starten** – schnell über das **Process List Widget**, Workflows mit nur wenigen Klicks anstoßen.
* **Cases verwalten** – über das **Case List Widget** alle Prozessinstanzen, an denen du beteiligt bist, anzeigen, verfolgen und nachbearbeiten.
* **An deinen Tasks arbeiten** – mit dem **Task List Widget** offene Aufgaben, Fristen und Prioritäten einfach im Blick behalten.
* **Deine Arbeitslast analysieren** – über das **Statistics Widget** die Leistung überwachen und Trends mit Diagrammen und Kennzahlen erkennen.
* **Deinen Arbeitsbereich anpassen** – Dashboards und Widgets so konfigurieren, dass sie zu deiner Arbeitsweise und deinen Vorlieben passen.

Das **Axon Ivy Portal** wurde mit Blick auf Erweiterbarkeit und Anpassbarkeit entwickelt. Von personalisierten Dashboards bis hin zur tiefen Integration in deine Geschäftslogik kannst du das Portal an die Bedürfnisse von Einzelpersonen, Teams und Abteilungen anpassen.

![Portal](images/portal.png)

## Demo

### Axon Ivy Portal Dashboard
Um das **Axon Ivy Portal** zu nutzen, verwende den Link, den dir dein Administrator zur Verfügung gestellt hat.
Nach dem Aufruf des Links fragt dich das **Axon Ivy Portal** nach deinen Anmeldedaten.

![login-screen](images/login-screen.png)


Nach erfolgreicher Anmeldung siehst du die **Axon Ivy Portal Homepage** und dein persönliches Dashboard.
Alle deine Aktivitäten kannst du von diesem zentralen Einstiegspunkt aus verwalten.
Im Einzelnen sind dies:

* Der **Axon Ivy Portal Header** oben ist immer sichtbar und bietet dir eine globale Suche, wichtige Informationen über das Portal sowie Optionen zur Konfiguration deines persönlichen Benutzerkontos.
Details findest du unter **Portal Header**.

![axon-ivy-portal-header](images/axon-ivy-portal-header.png)

* Die **Sidebar** auf der linken Seite bietet dir Zugriff auf die Seiten **Processes**, **Cases**, **Tasks** und **Portal Configuration**.
Administratoren können für die Sidebar eines von drei Verhalten konfigurieren: **Hover**, **Click** oder **Stick**.
Im Standardmodus **Hover** hat die Sidebar zwei Zustände:
  * **Minimiert**: Bewege den Mauszeiger über die Sidebar, um sie vorübergehend aufzuklappen.
  * **Angeheftet**: Klicke auf das Pin-Symbol oben links, um die Sidebar dauerhaft aufgeklappt zu lassen.

* Der **Hauptinhaltsbereich** in der Mitte zeigt **dein persönliches Dashboard** mit deinen **Prozessen**, **Cases**, **Tasks** und Leistungskennzahlen.

![Portal](images/portal.png)

### Beispiel-Dashboards
Im **Demo-Modus der Axon Ivy Engine** stellt das Portal zwei Beispiel-Dashboards in der Sidebar bereit:

* **User Guide Dashboard**: stellt die Standard-Dashboard-Widgets (Welcome, Process, Case, Task und Statistic Widgets) jeweils mit einer kurzen Erklärung vor.
Details findest du unter **Portal Guide > User Guide > Dashboard**.
* **KPI Procurement Overview**: demonstriert die Custom Statistics Widgets, die KPIs von Beschaffungsprozessen vollständig anpassbar visualisieren.
Details findest du unter **Portal Guide > User Guide > Statistic Chart > Sample: KPI Procurement Overview**.

## Setup

Dieser Abschnitt beschreibt die Erstinstallation. Wir empfehlen, die ausführliche Dokumentation im **Portal Guide** im Abschnitt **Developer Guide > Installation** zu lesen.

### Projektmodule

Die Anwendung besteht aus 2 Prozessmodulen. Detaillierte Informationen zu jedem Modul findest du im **Portal Guide**.

* portal-components
* portal

### Die Standardbenutzer

Die Standardbenutzer zur Demonstration des Portals.

| Benutzername | Beschreibung                                                                                  |
| ------------ | --------------------------------------------------------------------------------------------- |
| admin        | Dieser Benutzer hat alle Portal-Berechtigungen und kann auf die Portal Admin Settings zugreifen |
| demo         | Dieser Benutzer hat die Berechtigung, Abwesenheiten von Benutzern zu verwalten                |
| guest        | Standardbenutzer des Portals                                                                  |


### Hinweise

Wir empfehlen, die ausführliche Dokumentation im **Portal Guide** im Abschnitt **Developer Guide** zu lesen, um weitere Informationen zur Installation des **Portals** in deinen Workspaces zu erhalten.
