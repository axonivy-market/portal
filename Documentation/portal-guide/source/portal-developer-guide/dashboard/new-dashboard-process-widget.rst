.. _configure-new-dashboard-proces-widget:

Configure Process Widget
========================

The process widget displays a list of available processes with four different display modes: compact (process list), combined (process with tasks/cases), full (process details), and image (process with visual representation). Each mode provides different levels of detail and interaction.

Define Process Widget
---------------------

The process widget displays processes in one of four modes. Refer to :ref:`Process List Widget <new-dashboard-process-list-widget>` for widget behavior details.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

The basic structure of the JSON of a process widget is as follows:

   .. code-block:: javascript

      {
         "type": "compact-process",
         "id": "process-widget",
         "names": [
            {
               "locale": "en",
               "value": "Process Widget"
            },
            {
               "locale": "de",
               "value": "Prozess-Widget"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 4,
            "h": 6
         },
         "showFullscreenMode": true,
         "showWidgetInfo": true
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
     - Widget type. Options: ``"compact-process"``, ``"combined-process"``, ``"full-process"``, ``"image-process"``
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)

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
     - Height in grid rows (min 4). Pixel height = ``60 * h + 20 * (h - 1)``
   * - ``styleClass``
     - string
     - *(Optional)* CSS classes for custom styling
   * - ``style``
     - string
     - *(Optional)* Inline CSS styles

**Display Properties**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``showFullscreenMode``
     - boolean
     - ``true``
     - Show/hide fullscreen mode icon
   * - ``showWidgetInfo``
     - boolean
     - ``true``
     - Show/hide widget information icon

**Process Widget Types**

The ``type`` property determines the display mode:

- ``compact-process`` - Compact list of processes with icons and names
- ``combined-process`` - Single process with its related tasks and cases
- ``full-process`` - Detailed view of a single process
- ``image-process`` - Visual representation of a single process with image

Each mode has specific configuration requirements. Refer to the sections below for mode-specific properties.

Compact Mode
------------

Compact mode displays a list of processes with icons and names, ideal for showing multiple processes in a compact space.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a standard JSON definition of a Process widget in compact mode:

   .. code-block:: javascript

      {
         "type": "compact-process",
         "id": "compact-process-widget",
         "names": [
            {
               "locale": "en",
               "value": "Available Processes"
            },
            {
               "locale": "de",
               "value": "Verfügbare Prozesse"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 4,
            "h": 6
         },
         "processPaths": [
            "designer/portal-developer-examples/Start Processes/Request/createNewRequest.ivp",
            "designer/portal-developer-examples/Start Processes/Request/collectDataRequest.ivp"
         ],
         "categories": ["/Categories/Showcase/Customized", "/Categories/Showcase/PortalDialogExample"],
         "sorting": "SORTING_INDEX",
         "enableQuickSearch": true,
         "showFullscreenMode": true,
         "showWidgetInfo": true
      }
   ..

Compact Mode Properties
^^^^^^^^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``processPaths``
     - array
     - *(all processes)*
     - :doc-url:`IWebStartable </public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifiers of specific processes to display
   * - ``categories``
     - array
     - *(all processes)*
     - :doc-url:`CMS URI </designer-guide/how-to/workflow/categories.html#workflow-categories>` of process categories. Shows all processes in these categories
   * - ``sorting``
     - string
     - ``"ALPHABETICALLY"``
     - Sort order: ``"ALPHABETICALLY"`` or ``"SORTING_INDEX"`` (requires custom field)
   * - ``enableQuickSearch``
     - boolean
     - ``true``
     - Enable quick search textbox for process names

.. note::
   - If both ``processPaths`` and ``categories`` are defined, ``processPaths`` takes precedence
   - If neither is defined, all available processes are displayed
   - For ``SORTING_INDEX`` mode, define custom field ``portalSortIndex`` with numeric value in process start

   |dashboard-process-sort-index|

.. tip::
   **Recommended compact mode size:** Width 3-5 columns, Height 5-8 rows for optimal process list display.

Combined Mode
-------------

Combined mode displays a single process along with its related tasks and cases, providing a comprehensive view of process execution status.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a standard JSON definition of a Process widget in combined mode:

   .. code-block:: javascript

      {
         "type": "combined-process",
         "id": "combined-process-widget",
         "names": [
            {
               "locale": "en",
               "value": "Payment Process Overview"
            },
            {
               "locale": "de",
               "value": "Zahlungsprozess-Übersicht"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 8,
            "h": 9
         },
         "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
         "rowsPerPage": 5,
         "showFullscreenMode": true,
         "showWidgetInfo": true
      }
   ..

Combined Mode Properties
^^^^^^^^^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``processPath``
     - string
     - *(required)*
     - :doc-url:`IWebStartable </public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the process to display with its tasks/cases
   * - ``rowsPerPage``
     - number
     - ``5``
     - Number of tasks/cases displayed per page

.. tip::
   **Recommended combined mode size:** Width 6-12 columns, Height 8-12 rows to accommodate process details and related items.

Full mode
---------

Full mode displays detailed information about a single process, including description, start options, and metadata.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a standard JSON definition of a Process widget in full mode:

   .. code-block:: javascript

      {
         "type": "full-process",
         "id": "full-process-widget",
         "names": [
            {
               "locale": "en",
               "value": "Process Details"
            },
            {
               "locale": "de",
               "value": "Prozessdetails"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 6,
            "h": 8
         },
         "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
         "showFullscreenMode": true,
         "showWidgetInfo": true
      }
   ..

Full Mode Properties
^^^^^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``processPath``
     - string
     - :doc-url:`IWebStartable </public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the process to display in detail

.. tip::
   **Recommended full mode size:** Width 5-8 columns, Height 6-10 rows for complete process information display.

Image Mode
----------

Image mode displays a process with its associated image/icon, providing a visual representation alongside process information.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a standard JSON definition of the Process widget in image mode:

   .. code-block:: javascript

      {
         "type": "image-process",
         "id": "image-process-widget",
         "names": [
            {
               "locale": "en",
               "value": "Featured Process"
            },
            {
               "locale": "de",
               "value": "Ausgewählter Prozess"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 4,
            "h": 6
         },
         "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
         "showFullscreenMode": true,
         "showWidgetInfo": true
      }
   ..

Image Mode Properties
^^^^^^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``processPath``
     - string
     - :doc-url:`IWebStartable </public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the process to display with its image

.. tip::
   **Recommended image mode size:** Width 3-5 columns, Height 4-7 rows for optimal process image and info display.

.. note::
   The process image is configured in the process properties within the Axon Ivy Designer.

.. |dashboard-process-sort-index| image:: images/new-dashboard-process-widget/process-sort-index.png