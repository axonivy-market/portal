.. _customization-new-dashboard-task-widget:

Customize Task widget
=====================

Define Task widget
-------------------

Task widget of Portal dashboard is an interactable task list. Please refer
to (link to task widget of dashboard) for more details.

Below is a standard JSON definition of task widget in Portal dashboard

.. code-block:: html

   {
      "type": "task",
      "id": "task-widget",
      "layout": {
         "x": 0,
         "y": 0,
         "w": 10,
         "h": 9,
         "style": "color: red;",
         "styleClass": "your-widget-class"
      },
      "sortField": "name",
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

Basic structure of JSON of Task widget

   ``type``: type of widget. Please use ``task`` to mark that this widget is Task widget

   ``id``: ID of your widget

   ``layout``: layout definition of task widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of Task widget

      ``style`` (optional): add inline style to HTML DOM of Task widget

   ``sortField``: default sort field for task widget

   ``columns``: column configurations for each columns in Task widget. You can predifine
   filters, styles, visibility,... of columns and define custom columns also

      ``field``: field name of column
         
         For standard column, ``field`` must be one of these columns
         
            - ``start``: column which contains start button to start the task directly.
  
            - ``priority``: task priority

            - ``id``: task ID

            - ``name``: task name

            - ``description``: task description

            - ``activator``: task activator

            - ``state``: task state

            - ``startTimestamp``: created date and time of task

            - ``expiryTimestamp``: expiry date and time of task

         For custom column, ``field`` is the name of a task custom field.
         Portal will use value of ``field`` attribute to get value of column.

      ``visible``: visibility of a column. Default value is "true".
      Set to "false" to hide column.

      ``header``: header text of the column. You can input a string, or can use
      CMS by using prefix ``cms:`` before your CMS URI to define header
      in multi languages.

Custom columns
--------------

Axon Ivy supports custom fields for task.
You can show them on Task widget in form of a column.

More, you can predefine which column to show, and other attributes such as header,
filter, format. Below is a standard JSON of a custom column.

.. code-block:: html

   {
      "format": "string",
      "header": "cms:columns/custom/supplier",
      "field": "supplier",
      "style": "width: 110px"
   }

..

Beside attributes explained in previous section, custom column has two differences:

   - ``format``: Type of custom column. There are four formats ``string``, ``text``, ``number``, and ``timestamp``.

   - ``field``: this attribute is the name of task's custom field which will be used to get data for column.

Filter conditions
-----------------

You can predefined filter conditions for most columns of task widget.
Each column have different conditions, some columns only accept a list, some only accept
a string, and some only accept a string in special format such as date time.

Below are the list of filterable columns and its corresponding filter conditions.

   - ``activator``

      .. code-block:: html

         {
            "field": "activator",
            "filterList": ["PO","#daniel"]
         },

      ..

      This column only accept a list of role names or username
      (if you want to filter by username, put a hashtag before the name)
      as filter condition for task's responsible username.
      If you define a string such as "#peter", Task widget will show tasks which
      responsible's username is "peter".

   - ``name``

      .. code-block:: html

         {
            "field": "name",
            "filter": "Task name"
         },

      ..

      This column  only accept a string as filter condition for task name.
      If you define a string such as "request", Task widget will show tasks which
      contain "request" word in its name.

   - ``description``

      .. code-block:: html

         {
            "field": "description",
            "filter": "Task description"
         },

      ..

      This column only accept a string as filter condition for task description.
      If you define a string such as "request", Task widget will show tasks which
      contain "request" word in its description.

   - ``priority``

      .. code-block:: html

         {
            "field": "priority",
            "filterList": ["LOW","NORMAL"]
         },

      ..

      This column only accept a list of priorities' name as filter condition.
      If you define list of priorities in ``filterList``, Task widget will show tasks have
      priority listed in ``filterList``.

      Refer to `Task Priority <https://developer.axonivy.com/doc/9.2/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>`_ for
      avaiable task priorities.

   - ``state``

      .. code-block:: html

         {
            "field": "state",
            "filterList": ["CREATED","DONE"]
         },

      ..

      This column  only accept a list of task states' name as filter condition.
      If you define list of states in ``filterList``, Task widget will show tasks have
      states listed in ``filterList``. 

      Refer to `Task States <https://developer.axonivy.com/doc/9.2/public-api/ch/ivyteam/ivy/workflow/TaskState.html>`_ for
      avaiable task states.


   - ``startTimestamp``

      .. code-block:: html

         {
            "field": "startTimestamp",
            "filterFrom": "04/11/2021",
            "filterTo": "05/28/2021"
         },

      ..

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      Task widget will show tasks have created date between the dates defined.

   - ``expiryTimestamp``

      .. code-block:: html

         {
            "field": "expiryTimestamp",
            "filterFrom": "04/11/2021",
            "filterTo": "05/28/2021"
         },

      ..

      This column accepts 2 filter conditions ``filterFrom`` and ``filterTo`` as boundaries
      of a range of dates. If you define dates for ``filterFrom`` and ``filterTo``,
      Task widget will show tasks have expiry date between the dates defined.
