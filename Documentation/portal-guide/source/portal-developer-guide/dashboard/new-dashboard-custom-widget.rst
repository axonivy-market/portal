.. _configure-new-dashboard-custom-widget:

Configure Custom Widget
=======================

Portal offers a variety of widgets, such as task, case, and process widgets. But what if you need a widget like a 
weather widget? Fortunately, Portal allows you to create custom widgets as well. These custom widgets utilize 
an IFrame to display content, which you can define. The content can be an Ivy process or the URL of an external 
webpage.

.. _define-an-ivy-process-for-the-custom-widget:

Define An Ivy Process For The Custom Widget
-------------------------------------------

The custom widget can display a predefined Ivy process. This solution allows
users to work directly on the Portal Dashboard instead of opening another page.

First, the developer needs to create a specific process for the Portal
Dashboard:

   Set custom field ``isDashboardProcess`` for the process to true.

   |dashboard-custom-field|

   Parameters for the process have to be a String variable and named in a special format: ``type__name``

   |dashboard-custom-params|

   ``type``: type of parameter. There are four supported types

      ``user``: username of an Ivy user.

      ``string``: type java.lang.String.

      ``boolean``: type java.lang.Boolean.

      ``date``: type java.util.Date.

   ``name``: name of the field that you want to show on the configuration dialog of the custom widget. The widget's header is hidden if the name is empty.

   The reason to define in the above format is to allow end-user to send parameters to the Ivy process in the proper way.

   For example, if you define parameter ``user__customer``, Portal will display a dropdown named "customer" 
   to select an Ivy user in the system in the configuration dialog of the custom widget.

   This is how the configuration dialog for the custom widget for the above process looks like.

   |dashboard-custom-widget-configuration|

Define A Custom Widget Using JSON
---------------------------------

A custom dashboard widget allows users to interact with an external webpage or
an Ivy process on the dashboard through iframes.

Below is a standard JSON definition of the custom widget in the Portal dashboard.

   .. code-block:: javascript

      {
         "type": "custom",
         "id": "custom-widget",
         "showFullscreenMode": true,
         "names": [
            {
               "locale": "en",
               "value": "Custom Widget"
            }
         ],
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "data": {
            ...
         }
      }
   ..

The basic JSON structure of the custom widget

   ``type``: type of the widget. Use ``custom`` to mark that this widget is a custom
   widget.

   ``id``: ID of the widget.

   ``names``: multilingual name of the widget on UI.

   ``data``: data for customization. Please refer to the following sections to
   understand how to use this field.

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon

Define Ivy Process
------------------

The Developer can predefine a custom widget by declaring it in a JSON file:

JSON structure

   .. code-block:: javascript

      {
         "type": "custom",
         "id": "custom-widget",
         "showFullscreenMode": true,
         "names": [
            {
               "locale": "en",
               "value": "Custom Widget"
            }
         ],
         "layout": {
            "x": 10, "y": 0, "w": 12, "h": 6
         },
         "data" : {
           "processPath": "designer/portal-user-examples/Start Processes/DashboardCustomWidgetExample/investmentList.ivp",
           "params": [
               {
                  "type": "user",
                  "name": "customer",
                  "value": "demo"
               },
               {
                  "type": "date",
                  "name": "startDate",
                  "value": "11/19/2021"
               },
               {
                  "type": "string",
                  "name": "note",
                  "value": "a short note for demo process"
               }
            ]
        }
      }
   ..

Before you continue, please read the section :ref:`Define An Ivy Process for the
Custom Widget<define-an-ivy-process-for-the-custom-widget>` above to understand
the relation between name and type.

Attributes explanation

   ``processPath``: the :dev-url:`|ivy| IWebStartable </doc/|version|/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the Ivy process that will be displayed in custom widget

   ``params``: parameters for the Ivy process above. Each parameter can be defined as follows:

      - name: name of the parameter

      - value: predefined value for the parameter.

      - type: The desired data type for the parameter to be shown in the
        configuration dialog of the custom widget. There are four types:
        ``string``, ``user``, ``date``, ``boolean``.

         - type ``string``: Marks the parameter as a normal String. In the
           configuration dialog, the user can edit this parameter in an input
           text field.

         .. code-block:: javascript

            {
               ...

               "params": [
                     {
                        "type": "string",
                        "name": "note",
                        "value": "a short note for demo process"
                     }
                  ]
            }
         ..

         - type ``user``: The parameter value is an |ivy| username. In the
           configuration dialog, the user can edit this parameter by choosing a
           user from a dropdown. Only Ivy usernames are accepted as values.

         .. code-block:: javascript

            {
               ...

               "params": [
                     {
                        "type": "user",
                        "name": "customer",
                        "value": "demo"
                     }
                  ]
            }
         ..

         - type ``date``: The parameter value is a date. In the configuration
           dialog, the user can edit this parameter with a date picker. This
           type only accepts date formats dd.MM.yyyy and MM/dd/yyyy.

         .. code-block:: javascript

            {
               ...

               "params": [
                     {
                        "type": "date",
                        "name": "startDate",
                        "value": "01/01/2024"
                     }
                  ]
            }
         ..

         - type ``boolean``: The parameter is a boolean. In the configuration
           dialog, this field will be rendered as a radio button: The user can
           choose between true or false by clicking the respective radio button.
           This type only accepts the values ``true`` or ``false``.

Please refer to JSON file ``variables.Portal.Dashboard.json`` and process
``DashboardCustomWidgetExample/investmentList.ivp`` in project
``portal-user-examples`` for details about how to define the Ivy process for
the custom widget.

.. |dashboard-custom-field| image:: images/new-dashboard-custom-widget/process-custom-field.png
.. |dashboard-custom-params| image:: images/new-dashboard-custom-widget/process-custom-params.png
.. |dashboard-custom-widget-configuration| image:: ../../screenshots/dashboard/process-custom-widget-configuration.png