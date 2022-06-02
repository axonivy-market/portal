.. _customization-user-menu:

User menu
================

.. _customization-user-menu-introduction:

Introduction
------------

The user menu is configurable, user can add, remove and edit it.

Available style of user menu :

- Open external link

- Start Ivy Process

- Start the Express process

You can predefine user menus, and details of each menu
by configuring Global Variable **Portal.UserMenu**.

.. _customization-user-menu-definition:

Define your own user menu
--------------------------

Portal support multiple configurable user menu, they will be displayed below the default user menu.

You can predefine user menus id, title, permissions to see, and URL
for each user menu.

Below is a JSON example for the configuration of user menus.

.. code-block:: html

  [
      {
          "id" : "axon-ivy",
          "title" : "Axon Ivy",
          "permissions": ["#demo"],
          "url" : "https://www.axonivy.com/"
      },
      {
          "id" : "express",
          "title" : "Portal Express process",
          "permissions": ["Everybody"],
          "url" : "Portal Express process"
      },
      {
          "id" : "re-order-dashboard",
          "title" : "cms:/ch.ivy.addon.portalkit.ui.jsf/dashboard/dashboardManagement/reorderDashboard",
          "permissions": ["Employee", "AXONIVY_PORTAL_ADMIN", "#daniel"],
          "url": "Start Processes/ExamplePortalStart/DashboardReorder.ivp",
          "params": {
              "isPublicDashboard":"false"
          }
      }
  ]

..

Structure of JSON for each user menu:

    ``id``: ID for identifying user menu

    ``title``: title of user menu. You can input a string as user menu
    title, or can use CMS by using prefix ``cms:`` before your CMS URI
    to define user menu title in multilingual

    ``permissions``: roles can see the user menu.

    .. tip::
       If you don't define ``permissions`` for a user menu, every user can see it.

    ``url``: the URL of the external webpage or Ivy process starts or the naming Express process you want to redirect.

    .. tip::
        URL Ivy process starts: relative link to the ivy process.

          - e.g: ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp``

        URL Express process starts: the naming of express process.

          - e.g: ``Portal Express process``

    ``params``: parameters for Ivy process above, each parameter can be defined key, value as follows:

      - key : name of the parameter for Ivy process described in attribute ``processStart``.

      - value: predefined value for the parameter.

    .. tip::
       Params are not required in case the URL of an external webpage or Express process.