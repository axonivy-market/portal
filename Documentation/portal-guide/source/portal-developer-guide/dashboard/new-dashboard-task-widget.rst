.. _configure-new-dashboard-task-widget:

Configure Task Widget
=====================

The task widget displays an interactive, customizable list of tasks with advanced filtering, sorting, and search capabilities. It supports custom columns, pre-configured filters, quick search across multiple fields, and multi-language support for custom string fields via CMS.

Define Task Widget
------------------

The task widget displays tasks with full control over columns, filters, sorting, and layout. Refer to :ref:`Task List Widget <new-dashboard-task-list-widget>` for widget behavior details.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a task widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "task",
      "id": "task-widget",
      "names": [
         {
            "locale": "en",
            "value": "My Tasks"
         },
         {
            "locale": "de",
            "value": "Meine Aufgaben"
         }
      ],
      "layout": {
         "x": 0,
         "y": 0,
         "w": 10,
         "h": 9,
         "style": "color: red;",
         "styleClass": "your-widget-class"
      },
      "sortField": "name",
      "sortDescending": false,
      "rowsPerPage": 20,
      "showWidgetInfo": true,
      "showFullscreenMode": true,
      "filterTasksByCurrentCaseOwner": false,
      "canWorkOn": false,
      "enableQuickSearch": true,
      "columns": [
         {
            "field": "start"
         },
         {
            "field": "priority",
            "visible": "true"
         },
         {
            "field": "id"
         },
         {
            "field": "name",
            "quickSearch": "true"
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
      ],
      "filters": [
         {
            "field": "state",
            "values": ["OPEN", "IN_PROGRESS"],
            "operator": "in",
            "type": "standard"
         }
      ]
   }

..

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Required Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``type``
     - string
     - Widget type. Must be ``"task"`` for task widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)
   * - ``columns``
     - array
     - Column configurations (see Columns section below)

**Layout Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``x``
     - number
     - Column position in 12-column grid (0-11). CSS left = ``x / 12 * 100%``
   * - ``y``
     - number
     - Row position. CSS top = ``y / 12 * 100%``
   * - ``w``
     - number
     - Width in grid columns (1-12). Pixel width = ``60 * w + 20 * (w - 1)``
   * - ``h``
     - number
     - Height in grid rows (min 5). Pixel height = ``60 * h + 20 * (h - 1)``
   * - ``styleClass``
     - string
     - *(Optional)* CSS classes for custom styling
   * - ``style``
     - string
     - *(Optional)* Inline CSS styles

.. tip::
   **Recommended task widget size:** Width 8-12 columns, Height 8-12 rows for optimal table display with pagination.

**Display & Behavior Properties**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``sortField``
     - string
     - *(none)*
     - Default column for sorting (e.g., ``"name"``, ``"priority"``, ``"startTimestamp"``)
   * - ``sortDescending``
     - boolean
     - ``false``
     - Sort direction. ``false`` = ascending, ``true`` = descending
   * - ``rowsPerPage``
     - number
     - ``10``
     - Number of tasks displayed per page
   * - ``showWidgetInfo``
     - boolean
     - ``true``
     - Show/hide widget information icon
   * - ``showFullscreenMode``
     - boolean
     - ``true``
     - Show/hide fullscreen mode icon
   * - ``enableQuickSearch``
     - boolean
     - ``false``
     - Enable quick search text box
   * - ``canWorkOn``
     - boolean
     - ``false``
     - Filter only tasks the current user can work on
   * - ``filterTasksByCurrentCaseOwner``
     - boolean
     - ``false``
     - Filter only tasks where current user is case owner (requires ``Portal.Cases.EnableOwner`` setting)
   * - ``isTopMenu``
     - boolean
     - ``false``
     - ``true`` = top-level nav item, ``false`` = under Dashboard menu

**Columns Configuration**

Each column object in the ``columns`` array:

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``field``
     - string
     - *(required)*
     - Column field name (see Standard Columns below)
   * - ``visible``
     - string
     - ``"true"``
     - Column visibility: ``"true"`` or ``"false"``
   * - ``quickSearch``
     - string
     - ``"false"``
     - Include in quick search: ``"true"`` or ``"false"``
   * - ``headers``
     - array
     - *(none)*
     - Multilingual column headers: ``[{"locale": "en", "value": "Header"}]``
   * - ``type``
     - string
     - ``"STANDARD"``
     - Column type: ``"STANDARD"`` or ``"CUSTOM"``
   * - ``style``
     - string
     - *(none)*
     - Inline CSS for custom columns (e.g., ``"width: 110px"``)

**Standard Column Fields**

- ``start`` - Start button to begin task execution
- ``Pin`` - Pin button for task
- ``priority`` - Task priority
- ``id`` - Task ID
- ``name`` - Task name
- ``description`` - Task description
- ``activator`` - Task activator
- ``state`` - Task business state
- ``startTimestamp`` - Creation date and time
- ``endTimestamp`` - End date and time
- ``expiryTimestamp`` - Expiry date and time
- ``application`` - Application name
- ``actions`` - Action buttons (details, reset, delegate, reserve, destroy, etc.)

**Filters Configuration**

The ``filters`` array defines pre-configured filter conditions:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``field``
     - string
     - Column field name to filter
   * - ``values``
     - array
     - Filter values (format depends on field type)
   * - ``operator``
     - string
     - Filter operator (see Filter Conditions section)
   * - ``type``
     - string
     - ``"standard"`` or ``"custom"``

.. note::
   For detailed filter configuration, see the :ref:`Filter Conditions <configure-new-dashboard-task-widget-filter-structure>` section below.


.. _configure-new-dashboard-task-widget-custom-columns:

Custom Columns
--------------

Axon Ivy supports custom fields for tasks. You can show them in the Task widget
as a column.

You can predefine which column to show, and other attributes such as filter, format, and style. Below is a standard JSON of a custom column.

.. code-block:: javascript
   
   {
      ...

      "columns": [
            {
               "type": "CUSTOM",
               "field": "HIDE",
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
   Refer to :dev-url:`Custom Fields Meta Information </doc/12.0/designer-guide/how-to/workflow/custom-fields.html#meta-information>` for more information.

Filter Conditions
-----------------

You can predefine filter conditions for most columns of the task widget. Each
column has different requirements: some accept only a list, some accept only a
string, and others require a string in a specific format, such as date-time.
Please refer to :ref:`Complex Filter <complex-filter>` for more details.

Base structure of filter json:

   .. code-block:: javascript

         {
            ...

            "columns" : [
               {
                  "field" : "description"
               }
            ],
            "filters" : [
                  {
                     "field" : "description",
                     "values" : [ "Leave Request" ],
                     "operator" : "contains",
                     "type" : "standard"
                  }
            ]
         }

   ..

..  _configure-new-dashboard-task-widget-filter-structure:

   - ``field``: filter field name corresponding with column name
   - ``values``: filter value, could be a list, a string or a number
   - ``operator``: filter operator, operators can be difference depend on each field type.

      - **String column**: is, is_not, empty, not_empty, contains, not_contains, start_with, not_start_with, end_with, not_end_with

      - **Number column**: between, not_between, empty, not_empty, equal, not_equal, less, less_or_equal, greater, greater_or_equal

      - **Date column**: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty

   - ``type``: ``standard`` for standard column or ``custom`` for custom column

   - Date type additional field:

      - ``periodType``: string value. E.g.: ``YEAR``, ``MONTH``, ``WEEK``, ``DAY``

      - ``from``: string value. E.g.: "04/04/2024"

      - ``to``: string value. E.g.: "05/05/2024"

There are additional fields dependent on the operator and many specific filters
for each field type. Below is the list of filterable columns and their
corresponding filter conditions.

.. tip::
   We encourage utilizing dashboard configurations to edit widgets and then leveraging the export dashboard feature to ensure better expectations when customizing these widgets.

Standard Column:

   - ``activator``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "activator"
               }
            ],
            "filters": [
               {
                  "field": "activator",
                  "values": [ "backendDev2" ],
                  "operator": "not_in",
                  "type": "standard"
               }
            ]
         }

      ..

      This column only accepts a list of role names or usernames as filter conditions
      for the task's responsible username. The available filter operators are ``in``, ``not_in`` and ``current_user``.
      The ``current_user`` operator does not require value field.

   - ``name``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "name"
               }
            ],
            "filters" : [
               {
                  "field": "name",
                  "values": [ "Task", "Leave Request" ],
                  "operator": "contains",
                  "type": "standard"
               }
            ]
         }
      ..

      This column accepts all operators available for String column.
      Additionally, it accepts ``value`` as a list of string.

   - ``description``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "description"
               }
            ],
            "filters": [
               {
                  "field": "description",
                  "values": [
                  "leave request"
                  ],
                  "operator": "contains",
                  "type": "standard"
               }
            ]
         }

      ..

      This column accepts all operators available for String column. Additionally, it accepts ``value`` as a list of string.

   - ``priority``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "priority"
               }
            ],
            "filters": [
               {
                  "field": "priority",
                  "values": [ "HIGH", "NORMAL", "LOW" ],
                  "operator": "in",
                  "type": "standard"
               }
            ]
         }

      ..

      This column only accepts a list of priorities' names as the filter
      condition. The available filter operator is ``in``.

      Refer to :dev-url:`Task Priority </doc/12.0/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>` for
      available task priorities.

   - ``state``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "state"
               },
            ],
            "filters": [
               {
                  "field": "state",
                  "value": [ "DELAYED", "DESTROYED" ],
                  "operator" : "in",
                  "type" : "standard"
               }
            ]
         }
      ..

      This column only accepts a list of task business state names as its filter
      condition. The available filter operator is ``in``. 

      Refer to :dev-url:`Task Business States </doc/12.0/public-api/ch/ivyteam/ivy/workflow/TaskBusinessState.html>` for
      available task business states.


   - ``startTimestamp`` and ``expiryTimestamp`` : created date and finished date of the Task

      .. code-block:: javascript

         {
            ...
      
            "columns": [
               {
                  "field": "startTimestamp"
               }
            ],
            "filters" : [
               {
                  "field": "startTimestamp",
                  "operator": "today",
                  "type" : "standard"
               },
               {
                  "field" : "startTimestamp",
                  "from" : "04/04/2024",
                  "operator" : "before",
                  "type" : "standard"
               },
               {
                  "field" : "expiryTimestamp",
                  "from" : "04/04/2024",
                  "to" : "04/06/2024",
                  "operator" : "between",
                  "type" : "standard"
               },
               {
                  "field" : "expiryTimestamp",
                  "operator" : "last",
                  "periods" : 1,
                  "periodType" : "YEAR",
                  "type" : "standard"
               }       
            ]
         }

      ..

      These columns accept all operators available for Date column. Fields may
      vary depending on the operator. The JSON example above covers most use
      cases for the Date field. Acceptable date formats: ``dd.MM.yyyy``, ``dd.MM.yyyy HH:mm``, ``MM/dd/yyyy`` and
      ``MM/dd/yyyy HH:mm``.

   - ``application``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "application"
               }
            ],
            "filters": [
               {
               "field" : "application",
               "values" : [ "designer" ],
               "operator" : "in",
               "type" : "standard"
               }
            ]
         }

Custom Field Column :

   -  :ref:`configure-new-dashboard-task-widget-custom-columns` are using the
      same operator as :ref:`Standard Column
      <configure-new-dashboard-task-widget-filter-structure>`.

   - ``type`` field must be ``custom`` for Custom Field and ``custom_case`` for Custom Case Field.

   .. code-block:: javascript
      
         {
            ...

            "columns": [
               {
                  "field" : "CustomerName"
               }
            ],
            "filters": [
               {
                  "field" : "CustomerName",
                  "operator" : "not_empty",
                  "type" : "custom"
               }
            ]
         }
   
   ..

Quick Search
------------

The quick search is a useful function for users to quickly search the tasks within the task widget.
The configuration of the quick search has two attributes:
   
   * ``enableQuickSearch``: enables the quick search feature for the widget.

   * ``quickSearch``: indicates that a column is searchable using the quick search feature.

If you set the ``enableQuickSearch`` attribute to ``false``, the quick search feature will be disabled,
regardless of the ``quickSearch`` attribute's value.

Conversely, if you set the ``enableQuickSearch`` attribute to ``true``, the quick search feature will
search within the values of all columns that have the ``quickSearch`` attribute set to ``true``.
If you haven't assigned the ``quickSearch`` attribute to any column in the task widget,
the quick search feature will default to searching the name and description fields.

Below are the definition of these attributes:

   * ``enableQuickSearch``: to enable/disable the quick search feature, set the
     ``enableQuickSearch`` field of the Task widget as shown below.

      .. code-block:: javascript

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

      .. code-block:: javascript

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

Multi-Language Support for Custom String Fields
-----------------------------------------------

With the **Multi-Language Support for Custom Fields** feature, we enable the provision of values in a custom field in multiple languages.
The field content is displayed based on the language selected in the custom field.

.. important::
   Portal only supports Multi-Language for Custom Fields with ``TYPE: STRING``.

Enabling Multi-Language Support
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To allow a custom field to retrieve multilingual values from the CMS, set the ``HasCmsValue`` attribute to ``true`` in the corresponding ``custom-field`` yaml file configuration.
Otherwise, the logic remains unchanged, and the custom field uses the static value.
Additionally, the value must be entered and maintained in the CMS in multiple languages.

Following this path to add your custom field values: ``/CustomFields/Tasks/{fieldName}/Values/{value}``
Please follow this :dev-url:`Localize Label, Description, Category
and Values </doc/12.0/designer-guide/configuration/custom-fields.html#localize-label-description-category-and-values>`
to get more information.

Example YAML Configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. code-block:: yaml

   DriversVehicle:
      Label: Drivers Vehicle
      Description: Vehicle they use for delivery
      HasCmsValues: true
      TYPE: STRING

In your CMS, the path should be
``/CustomFields/Tasks/DriversVehicle/Values/Bike``,
``/CustomFields/Tasks/DriversVehicle/Values/Car`` or
``/CustomFields/Tasks/DriversVehicle/Values/MonsterTruck``.

|task-field-cms|

Result
^^^^^^

- If ``HasCmsValues`` is set with ``true``, the values from the CMS are used.
- If the values are entered and translated in the CMS, then the custom field is displayed based on the selected language.
- If the translation for a value is missing in the CMS, the static value from the entry is used instead.

Sorting, Filtering, and Searching with Multi-Language Custom Fields
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Sorting
^^^^^^^

1. ``HasCmsValues = false`` or not set. Sorting is performed as usual based on the stored values.
2. ``HasCmsValues = true``. Sorting is performed based on values translated in the currently selected language.

.. note::

   If ``HasCmsValues = true`` but a translation is missing in a specific language, only the translated values are sorted.


Searching
^^^^^^^^^

1. ``HasCmsValues = false`` or not set. The search is performed as usual based on the stored value.
2. ``HasCmsValues = true``. The search is conducted in both the CMS translations and the original value.

Example: When language is German, a user can search for **Fahrrad** or the original value **Bike**.

Filtering
^^^^^^^^^

1. ``HasCmsValues = false`` or not set. Filtering is performed as usual based on the stored value.
2. ``HasCmsValues = true``. The filter is conducted in both the CMS translations and the original value.

.. important::

   When enabling the Multi-Language for Custom Fields, only accepted ``CONTAINS`` operator.

.. tip::

   If a value needs to be filtered in multiple languages, all corresponding translations must be in the filter.

Conclusion
^^^^^^^^^^

With Multi-Language Support for Custom Fields, we enable flexible and dynamic
provision of field content in multiple languages. The functionalities for
searching, sorting, and filtering have been adapted accordingly to ensure
consistent behavior for multilingual content.


.. |task-field-cms| image:: images/new-dashboard-task-widget/cms-structure-task-field.png
