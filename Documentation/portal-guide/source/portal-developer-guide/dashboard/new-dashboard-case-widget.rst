.. _configure-new-dashboard-case-widget:

Configure Case Widget
=====================

Define Case Widget
------------------

The case widget of the Portal dashboard is an interactive case list. Refer
to (link to case widget of the dashboard) for details.

Below is a sample JSON definition of the case widget in the Portal dashboard.

.. code-block:: html

   {
      "type": "case",
      "id": "case-widget",
      "name": "Case Widget",
      "layout": {
         "x": 0, "y": 0, "w": 10, "h": 9,
         "style": "text-color: blue;",
         "styleClass": "your-widget-class"
      },
      "sortField": "name",
      "rowsPerPage": 20,
      "columns": [
         {
            "field": "start"
         },
         {
            "field": "priority",
            "visible": "false"
         },
         {
            "field": "id"
         },
         {
            "field": "name"
         },
         {
            "field": "state",
            "header": "cms:/your_state_header_cms"
         },
         {
            "field": "startTimestamp"
         },
         {
            "field": "actions"
         }
      ]
   }
..

The basic JSON structure of the case widget
-  ``type``: type of the widget. Use ``case`` for a case widget

-  ``id``: ID of the widget

-  ``name``: Name of the widget on the UI

-  ``layout``: layout definition of the case widget

   -  ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

   -  ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

   -  ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

   -  ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

   -  ``styleClass`` (optional): add CSS classes to HTML DOM of the Case widget

   -  ``style`` (optional): add inline style to HTML DOM of the Case widget

-  ``sortField``: default sort field for the Case widget

-  ``sortDescending``: default sort direction of the default sort field. The
   default value is "false" (sort direction is ascending)

-  ``rowsPerPage``: maximum number of cases that are displayed on one page of
   the case widget. The default is 10 rows per page

-  ``columns``: column configurations for each column in the case widget. You
   can predefine filters, styles, visibility,... of columns and define custom
   columns, too.

   -  ``field``: field name of the column
         
         For a standard column, ``field`` must be one of these:

            - ``id``: case ID

            - ``name``: case name

            - ``description``: case description

            - ``state``: case state

            - ``creator``: case creator

            - ``startTimestamp``: creation date and time of the case

            - ``endTimestamp``: end date and time of the case

            - ``owner``: case owner

            - ``actions``: for further actions like ``access case details``,
              ``case business details``, ``destroy case``

         For custom columns, ``field`` is the name of a case custom field.
         Portal will use the value of the ``field`` attribute to get the value
         of the column.

   -  ``visible``: visibility of a column. The default value is "true". Set to
      "false" to hide the column.

   -  ``header``: header text of the column. You can input a string, or you can
      use the CMS by setting prefix ``cms:`` before your CMS URI to define a
      multilingual column header.

Custom Columns
--------------

The Portal supports custom fields for a case. You can show them on the case widget
as a column.

You can predefine which column to show, and other attributes such as filter, format, and style. Below is a standard JSON of a custom column.

.. code-block:: html

   {
      ...

      "columns": [
         {
            "type": "CUSTOM",
            "field": "supplier",
            "style": "width: 110px"
         }
      ]
   }

..

Besides the attributes explained in the previous section, a custom column has
two differences:

   - ``type``: type of the widget column. There are two options: ``STANDARD`` and ``CUSTOM``.

   - ``field``: this attribute is the name of the case's custom field which will
     be used to get data for the column.

.. important::
   Portal only displays custom fields declared in the ``custom-fields.yaml`` file.
   Refer to :dev-url:`Custom Fields Meta Information </doc/nightly/designer-guide/how-to/workflow/custom-fields.html#meta-information>` for more information.

Filter Conditions
-----------------

You can predefined filter conditions for most columns of the case widget. Each
column has different conditions. Some columns only accept a list, some only a
string, and some only accept a string in a special format such as date-time.

Below is the list of filterable columns and their corresponding filter
conditions.

   - ``name``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "name",
                  "filter": "request"
               }
            ]
         }

      ..

      This column only accepts a string as the filter condition for the case
      name. If you define a string such as "request", the case widget will only
      show cases that contain the word "request" in their name.

   - ``description``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "description",
                  "filter": "request"
               }
            ]
         }

      ..

      This column only accepts a string as the filter condition for the case description.
      If you define a string such as "request", the case widget will only show cases that
      contain the word "request" in their description.

   - ``state``: Case state

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "state",
                  "filterList": ["CREATED","DONE"]
               }
            ]
         }
      ..

      This column only accepts a list of case state names as the filter condition.
      If you define a list of states in ``filterList``, the case widget will only show cases that have
      states listed in ``filterList``. 

      Refer to :dev-url:`Case States </doc/nightly/public-api/ch/ivyteam/ivy/workflow/CaseState.html>` for
      available case states.

   - ``creator``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "creator",
                  "filterList": ["PO","#peter"]
               }
            ]
         }

      ..

      This column only accepts a list of role names or usernames (if you want to
      filter by username, put a hashtag (#) before the name) as filter conditions
      for the case creator's username. If you define a string such as
      "#peter", the case widget will show cases that have been created by "peter".

   - ``startTimestamp``: Case's created date

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "startTimestamp",
                  "filterFrom": "04/11/2021",
                  "filterTo": "05/28/2021"
               }
            ]
         }

      ..

      This column accepts two filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      the case widget will show cases with a created date between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.

   - ``expiryTimestamp``: Case's expiry date

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "expiryTimestamp",
                  "filterFrom": "04/11/2021",
                  "filterTo": "05/28/2021"
               }
            ]
         }

      ..

      This column accepts two filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      case widget will show cases with an expiry date between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.
