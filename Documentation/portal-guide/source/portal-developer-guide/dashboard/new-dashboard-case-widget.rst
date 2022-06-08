.. _configure-new-dashboard-case-widget:

Configure Case widget
=====================

Define Case widget
------------------

The case widget of the Portal dashboard is an interactable case list. Refer
to (link to case widget of the dashboard) for more details.

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

the basic structure of JSON of case widget

   ``type``: type of widget. Use ``case`` to mark that this widget is a case widget

   ``id``: ID of the widget

   ``name``: Name of the widget on UI

   ``layout``: layout definition of case widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of Case widget

      ``style`` (optional): add inline style to HTML DOM of Case widget

   ``sortField``: default sort field for Case widget

   ``sortDescending``: sort direction of the default sort field is descending or not. The default value is "false"

   ``rowsPerPage``: the number of cases can be displayed on one page of the case widget. 
   The default value is 10 rows per page

   ``columns``: column configurations for each column in the case widget. You can predefine
   filters, styles, visibility,... of columns and define custom columns also

      ``field``: field name of the column
         
         For standard column, ``field`` must be one of these columns

            - ``id``: case ID

            - ``name``: case name

            - ``description``: case description

            - ``state``: case state

            - ``creator``: case creator

            - ``startTimestamp``: created date and time of the case

            - ``endTimestamp``: end date and time of the case

            - ``owner``: case owner

            - ``actions``: for further actions: access case details, business details case, destroy case

         For custom column, ``field`` is the name of a case custom field.
         Portal will use the value of ``field`` attribute to get the value of the column.

      ``visible``: visibility of a column. The default value is "true".
      Set to "false" to hide the column.

      ``header``: header text of the column. You can input a string, or can use
      CMS by using prefix ``cms:`` before your CMS URI to define header
      in multilingual.

Custom columns
--------------

|axon-ivy| supports custom fields for a case.
You can show them on the case widget in form of a column.

More, you can predefine which column to show, and other attributes such as header,
filter, format. Below is a standard JSON of a custom column.

.. code-block:: html

   {
      ...

      "columns": [
         {
            "format": "string",
            "header": "cms:columns/custom/supplier",
            "field": "supplier",
            "style": "width: 110px"
         }
      ]
   }

..

Besides attributes explained in the previous section, a custom column has two differences:

   - ``format``: Type of custom column. There are four formats ``string``, ``text``, ``number``, and ``timestamp``.

   - ``field``: this attribute is the name of the case's custom field which will be used to get data for the column.

Filter conditions
-----------------

You can predefined filter conditions for most columns of the case widget.
Each column has different conditions, some columns only accept a list, some only accept
a string, and some only accept a string in a special format such as date-time.

Below is the list of filterable columns and their corresponding filter conditions.

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

      This column only accepts a string as the filter condition for the case name.
      If you define a string such as "request", the case widget will show cases that
      contain the "request" word in its name.

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
      If you define a string such as "request", the case widget will show cases that
      contain the "request" word in its description.

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

      This column only accepts a list of case states' names as the filter condition.
      If you define a list of states in ``filterList``, the case widget will show cases that have
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

      This column only accepts a list of role names or usernames
      (if you want to filter by username, put a hashtag before the name) as filter 
      conditions for the case's responsible username.
      If you define a string such as "#peter", the case widget will show cases that
      the creator's  username is "peter".

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

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      case widget will show cases have created date between the dates defined.

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

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      case widget will show cases have expiry date between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.
