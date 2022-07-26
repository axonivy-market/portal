.. _configure-new-dashboard-task-widget:

Configure Task widget
=====================

Define Task widget
------------------

Task widget of Portal dashboard is an interactable task list. Refer
to (link to task widget of the dashboard) for more details.

Below is a sample JSON definition of the task widget in the Portal dashboard

.. code-block:: html

   {
      "type": "task",
      "id": "task-widget",
      "name": "Task Widget",
      "layout": {
         "x": 0, "y": 0, "w": 10, "h": 9,
         "style": "color: red;",
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
         }
      ]
   }
..

The basic structure of JSON of Task widget

   ``type``: type of widget. Use ``task`` to mark that this widget is a task widget

   ``id``: ID of the widget

   ``name``: Name of the widget on UI

   ``layout``: layout definition of task widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of Task widget

      ``style`` (optional): add inline style to HTML DOM of Task widget

   ``sortField``: default sort field for task widget

   ``sortDescending``: sort direction of the default sort field is descending or not. The default value is "false"

   ``rowsPerPage``: the number of tasks can be displayed on one page of the task widget. 
   The default value is 10 rows per page

   ``columns``: column configurations for each column in the task widget. You can predefine
   filters, styles, visibility,... of columns and define custom columns also

      ``field``: the field name of the column
         
         For standard column, ``field`` must be one of these columns
         
            - ``start``: column which contains start button to start the task directly.
  
            - ``priority``: task priority

            - ``id``: task ID

            - ``name``: task name

            - ``description``: task description

            - ``activator``: task activator

            - ``state``: task state

            - ``startTimestamp``: created date and time of the task

            - ``expiryTimestamp``: expiry date and time of the task

         For custom column, ``field`` is the name of a task custom field.
         Portal will use the value of ``field`` attribute to get the value of the column.

      ``canWorkOn``: filter only tasks that the current user can work on. The default value is "false".

      ``visible``: visibility of a column. The default value is "true".
      Set to "false" to hide the column.

      ``header``: header text of the column. You can input a string, or can use
      CMS by using prefix ``cms:`` before your CMS URI to define header
      in multilingual.

Custom columns
--------------

Axon Ivy supports custom fields for the task.
You can show them on the Task widget in form of a column.

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

   - ``field``: this attribute is the name of the task's custom field which will be used to get data for the column.

Filter conditions
-----------------

You can predefined filter conditions for most columns of the task widget.
Each column has different conditions, some columns only accept a list, some only accept
a string, and some only accept a string in a special format such as date-time.

Below is the list of filterable columns and their corresponding filter conditions.

   - ``activator``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "activator",
                  "filterList": ["PO","#peter"]
               }
            ]
         }

      ..

      This column only accepts a list of role names or usernames
      (if you want to filter by username, put a hashtag before the name)
      as filter conditions for the task's responsible username.
      If you define a string such as "#peter", the task widget will show tasks which
      responsible's username is "peter".

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

      This column only accepts a string as the filter condition for the task name.
      If you define a string such as "request", the task widget will show tasks that
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

      This column only accepts a string as the filter condition for the task description.
      If you define a string such as "request", the task widget will show tasks that
      contain the "request" word in its description.

   - ``priority``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "priority",
                  "filterList": ["LOW","NORMAL"]
               }
            ]
         }

      ..

      This column only accepts a list of priorities' names as the filter condition.
      If you define a list of priorities in ``filterList``, the task widget will show
      tasks that have priority listed in ``filterList``.

      Refer to :dev-url:`Task Priority </doc/9.3.5/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>` for
      available task priorities.

   - ``state``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "state",
                  "filterList": ["CREATED","DONE"]
               },
            ]
         }
      ..

      This column  only accepts a list of task states' names as the filter condition.
      If you define a list of states in ``filterList``, the task widget will show
      tasks that have states listed in ``filterList``. 

      Refer to :dev-url:`Task States </doc/9.3.5/public-api/ch/ivyteam/ivy/workflow/TaskState.html>` for
      available task states.


   - ``startTimestamp``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "startTimestamp",
                  "filterFrom": "04/11/2021",
                  "filterTo": "05/28/2021"
               },
            ]
         }

      ..

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      the task widget will show tasks have created dates between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.

   - ``expiryTimestamp``

      .. code-block:: html

         {
            ...
      
            "columns": [
               {
                  "field": "expiryTimestamp",
                  "filterFrom": "04/11/2021",
                  "filterTo": "05/28/2021"
               },
            ]
         }

      ..

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      the task widget will show tasks have expiry dates between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.
