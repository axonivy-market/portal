.. _configure-new-dashboard-process-viewer-widget:

Configure Process Viewer Widget
===============================

The process viewer widget provides a visual representation of a process flow diagram, enabling users to view process structures, steps, and workflow logic directly from the dashboard without opening the Designer.

Define Process Viewer Widget
----------------------------

The process viewer widget displays an interactive process flow diagram for a specified process.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a process viewer widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "process-viewer",
      "id": "process-viewer-widget",
      "names": [
         {
            "locale": "en",
            "value": "Payment Process Viewer"
         },
         {
            "locale": "de",
            "value": "Zahlungsprozess-Viewer"
         }
      ],
      "layout": {
         "x": 0,
         "y": 0,
         "w": 12,
         "h": 8
      },
      "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
      "showFullscreenMode": true
   }

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
     - Widget type. Must be ``"process-viewer"`` for process viewer widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)
   * - ``processPath``
     - string
     - :dev-url:`IWebStartable </doc/12.0/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the process to display

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
     - Height in grid rows (min 6). Pixel height = ``60 * h + 20 * (h - 1)``
   * - ``styleClass``
     - string
     - *(Optional)* CSS classes for custom styling
   * - ``style``
     - string
     - *(Optional)* Inline CSS styles

.. tip::
   **Recommended process viewer widget size:** Width 8-12 columns, Height 8-12 rows for optimal process diagram visibility.

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

.. note::
   The process viewer displays the process diagram in read-only mode. Users can zoom and pan to explore the process flow but cannot edit the process structure.
