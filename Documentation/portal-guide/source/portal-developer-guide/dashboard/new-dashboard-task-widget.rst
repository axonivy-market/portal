.. _configure-new-dashboard-task-widget:

Configure Task Widget
=====================

Define Task Widget
------------------

The Task widget of the Portal dashboard is an interactive task list. Refer
to (link to task widget of the dashboard) for details.

Below is a sample JSON definition of a task widget in the Portal dashboard

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
         },
         {
            "field": "actions"
         }
      ]
   }
..

The basic JSON structure of a Task widget

   ``type``: type of the widget. Use ``task`` for a task widget

   ``id``: ID of the widget

   ``name``: Name of the widget on the UI

   ``layout``: layout definition of the widget

      ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``sortField``: default sort field for the widget

   ``sortDescending``: sort direction of the default sort field. The default value is "false" (sort ascending)

   ``rowsPerPage``: maximum number of tasks can be displayed on one page of the task widget. 
   The default value is 10 rows per page

   ``columns``: column configurations for each of the columns in the widget. You
   can predefine filters, styles, visibility,... of columns and define custom
   columns, too:

      ``field``: the field name of the column
         
         For standard columns, ``field`` must be one of these:
         
            - ``start``: column which contains start button to start the task directly.
  
            - ``priority``: task priority

            - ``id``: task ID

            - ``name``: task name

            - ``description``: task description

            - ``activator``: task activator

            - ``state``: task state

            - ``startTimestamp``: created date and time of the task

            - ``expiryTimestamp``: expiry date and time of the task
            
            - ``actions``: for further actions: access task details, reset task, delegate task, reserve, destroy task, trigger escalation task and add Ad-hoc task

         For custom columns, ``field`` is the name of a task custom field.
         Portal will use the value of ``field`` to get the value of the column.

      ``canWorkOn``: filter only tasks that the current user can work on. The default value is "false".

      ``visible``: visibility of a column. The default value is "true".
      Set to "false" to hide the column.

      ``header``: header text of the column. You can enter a string, or use
      CMS by adding prefix ``cms:`` in front of your CMS URI to define a multilingual header.

Custom Columns
--------------

Axon Ivy supports custom fields for tasks. You can show them in the Task widget
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

Besides attributes explained in the previous section, a custom column has two
differences:

   - ``type``: type of the widget column. There are two options: ``STANDARD`` and ``CUSTOM``.

   - ``field``: this attribute is the name of the task's custom field which will
     be used to get data for the column.

.. important::
   Portal only displays custom fields declared in the ``custom-fields.yaml`` file.
   Refer to :dev-url:`Custom Fields Meta Information </doc/nightly/designer-guide/how-to/workflow/custom-fields.html#meta-information>` for more information.

Filter Conditions
-----------------

You can predefine filter conditions for most columns of the task widget. Each
column has different conditions, some columns only accept a list, some only a
string, and some only a string in a special format such as date-time.

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

      This column only accepts a list of role names or usernames (if you want to
      filter by username, put a hashtag before the name) as filter conditions
      for the task's responsible username. If you define a string such as
      "#peter", the task widget will show tasks that have been created by "peter".

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

      Refer to :dev-url:`Task Priority </doc/nightly/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>` for
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

      This column only accepts a list of task state names as its filter condition.
      If you define a list of states in ``filterList``, the task widget will show
      tasks that are in one of the states listed in ``filterList``. 

      Refer to :dev-url:`Task States </doc/nightly/public-api/ch/ivyteam/ivy/workflow/TaskState.html>` for
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

      This column accepts two filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      the task widget will show tasks that have been created between the dates defined.

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

      This column accepts two filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      the task widget will show tasks that have expiry dates between the dates defined.

      Acceptable date formats: ``dd.MM.yyyy`` and ``MM/dd/yyyy``.
