.. _customization-user-menu:

User menu
================

.. _customization-user-menu-introduction:

Introduction
------------

The user menu is configurable, user can add, remove and edit user item.

Available styles of user item:

- Open external link

- Start Ivy Process

- Start the Portal Express process

You can predefine user menu items, and details of each item
by configuring Global Variable **Portal.UserMenu**.

.. _customization-user-menu-definition:

Define your own user menu
--------------------------

Portal support multiple configurable user item, they will be displayed below the default user item.

You can predefine user item id, title, permissions to see, and URL
for each user item.

|user-menu-configuration|

Below is a JSON example for the configuration of user items.

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

Structure of JSON for each user item:

    ``id``: ID for identifying user item

    ``title``: title of user item. You can input a string as user item
    title, or can use CMS by using prefix ``cms:`` before your CMS URI
    to define user item title in multilingual

    ``permissions``: users and roles can see the user item.

    .. tip::
       If you don't define ``permissions`` for a user item, every user can see it.

    ``url``: the URL of the external webpage or Ivy process start or the name Portal Express process you want to redirect.

    .. tip::
        URL Ivy process starts: relative link to the ivy process.

          - e.g: ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp``

        URL Express process starts: the name of Portal Express process.

          - e.g: ``Portal Express process``

    ``params``: parameters for Ivy process above, each parameter can be defined key, value as follows:

      - key : name of the parameter for Ivy process described in attribute ``url``.

      - value: predefined value for the parameter.

    .. tip::
       Params are not required in case the URL of an external webpage or Express process.

.. |user-menu-configuration| image:: ../../screenshots/settings/user-menu-configuration.png