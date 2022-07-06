.. _customization-user-menu:

User menu
================

.. _customization-user-menu-introduction:

Introduction
------------

The user menu is configurable. The user can add, remove and edit user items.

These types of user items are available:

- Open external link

- Start Ivy Process

- Start the Portal Express process

You can predefine user menu items and details of each item
by configuring variable **Portal.UserMenu**.

.. _customization-user-menu-definition:

Define Your Own User Menu
-------------------------

Portal supports multiple configurable user items. They will be displayed below
the default user item.

You can predefine user item id, title, permissions to see, and URL for each user
item.

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

    ``id``: ID of the user item

    ``title``: title of user item. You can enter a string as a user item
    title, or can use CMS by adding prefix ``cms:`` before your CMS URI
    to define a multilingual user item title.

    ``permissions``: users and roles that can see the user item.

    .. tip::
       If you don't define ``permissions`` for a user item, every user can see it.

    ``url``: the URL of the external webpage, Ivy process start, or the name of the Portal Express process you want to redirect to.

    .. tip::
        Ivy process start URLs are a **relative link** to the process.

          - e.g: ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp``

        Express process start URLs are the **name** of the Portal Express process.

          - e.g: ``Portal Express process``

    ``params``: parameters for the Ivy process defined above. Each parameter can
    be defined as a key-value pair as follows:

      - key : name of the parameter for the Ivy process.
      - value: predefined value for the parameter.

    .. tip::
       Params are not required if the URL denotes an external webpage or an Express process.

.. |user-menu-configuration| image:: ../../screenshots/settings/user-menu-configuration.png