.. _configure-new-dashboard-task-widget:

Configure Task Widget
=====================

Define Task Widget
------------------

The Task widget of the Portal dashboard is an interactive task list. Refer
to :ref:`new-dashboard-task-list-widget` for details.

Below is a sample JSON definition of a task widget in the Portal dashboard

.. code-block:: html

   {
      "type": "task",
      "id": "task-widget",
      "names": [
         {
            "locale": "en",
            "value": "Task Widget"
         }
      ],
      "layout": {
         "x": 0, "y": 0, "w": 10, "h": 9,
         "style": "color: red;",
         "styleClass": "your-widget-class"
      },
      "sortField": "name",
      "rowsPerPage": 20,
      "showWidgetInfo": true,
      "showFullscreenMode": true,
      "columns": [
         {
            "field": "start"
         },
         {
            "field": "priority",
            "visible": "false",
            "width": "120"
         },
         {
            "field": "id"
         },
         {
            "field": "name"
            "width": "400"
         },
         {
            "field": "state",
            "headers": [
               {
               "locale": "en",
               "value": "State"
               },
               {
               "locale": "de",
               "value": "Status"
               }
            ]
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

   ``names``: multilingual name of the widget on the UI

   ``layout``: layout definition of the widget

      ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``sortField``: default sort field for the widget

   ``sortDescending``: sort direction of the default sort field. The default value is ``false`` (sort ascending)

   ``rowsPerPage``: maximum number of tasks can be displayed on one page of the task widget. 
   The default value is 10 rows per page

   ``showWidgetInfo``: visibility of the widget information icon. The default
   value is ``true``, set to ``false`` to hide the icon

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default
   value is ``true``, set to ``false`` to hide the icon

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

            - ``endTimestamp``: end date and time of the task

            - ``expiryTimestamp``: expiry date and time of the task
            
            - ``actions``: for further actions: access task details, reset task, delegate task, reserve, destroy task, trigger escalation task and add Ad-hoc task

         For custom columns, ``field`` is the name of a task custom field.
         Portal will use the value of ``field`` to get the value of the column.

      ``canWorkOn``: filter only tasks that the current user can work on. The default value is "false".

      ``visible``: visibility of a column. The default value is "true".
      Set to "false" to hide the column.

      ``quickSearch``: apply search condition for the column when using the quick search feature. The default value is ``false``.
      Set to ``true`` to apply search condition for the column.

      ``width``: the width of the column, measured in pixels.

   -  ``headers``: multilingual header of the column.

Custom Columns
--------------

|ivy| supports custom fields for tasks. You can show them in the Task widget
as a column.

You can predefine which column to show, and other attributes such as filter, format, and style. Below is a standard JSON of a custom column.

.. code-block:: html

   {
      ...
      
      "columns": [
         {
            "type": "CUSTOM",
            "field": "supplier",
            "style": "color: red",
            "width": "120"
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
   Refer to :dev-url:`Custom Fields Meta Information </doc/|version|/designer-guide/how-to/workflow/custom-fields.html#meta-information>` for more information.

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

      Refer to :dev-url:`Task Priority </doc/|version|/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>` for
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

      Refer to :dev-url:`Task States </doc/|version|/public-api/ch/ivyteam/ivy/workflow/TaskState.html>` for
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

Quick Search
------------

Quick search is a useful function for users to search quickly on the task widget.
There are two attributes:
   
   * ``enableQuickSearch``: enables the quick search feature for the widget.

   * ``quickSearch``: indicates that a column is searchable using the quick search feature.

If you set the ``enableQuickSearch`` attribute to ``false``, the quick search feature will be disabled,
regardless of the ``quickSearch`` attribute's value.

Conversely, if you set the ``enableQuickSearch`` attribute to ``true``, the quick search feature will
scan through the values of all columns that have the ``quickSearch`` attribute set to ``true``.
If you haven't assigned the ``quickSearch`` attribute to any column in the task widget,
the quick search feature will default to searching the name and description fields.

Below are the definition of these attributes:

   * ``enableQuickSearch``: to enable/disable the quick search feature, set the
     ``enableQuickSearch`` field of the Task widget as shown below.

      .. code-block:: html

         {
            ...
            "type": "task",
            "id": "task_98ae4fc1c83f4f22be5244c8027ecf40"
            ...
            "enableQuickSearch": "true",
            ...
         }

      ..

      Valid values:

      * ``true``: show the quick search text box.
      * ``false``: hide the quick search text box.
      * ``not defined``: hide the quick search text box.

   * ``quickSearch``: to choose which columns can be searched by the quick search
     feature, set the ``quickSearch`` field for each column as shown below.

      .. code-block:: html

         {
            ...
            "type": "task",
            "id": "task_98ae4fc1c83f4f22be5244c8027ecf40"
            ...
            "columns": [
               {
                  "field": "id",
                  "quickSearch": "false"
               },
               ...
            ]
            ...
         }

      ..

      Valid values:

      * ``true``: apply quick search for this column.
      * ``false``: do not apply quick search for this column.
      * ``not defined``: the ``name`` and ``description`` columns are ``true``, other columns are ``false`` by default.