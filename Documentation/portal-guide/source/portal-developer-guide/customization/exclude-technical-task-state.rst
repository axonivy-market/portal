.. _exclude-technical-task-state:

Exlude technical task state
===========================

.. _side-step-introduction:

Introduction
------------

User can exclude some specific technical task states 
:dev-url:`Task state </doc/12.0/public-api/ch/ivyteam/ivy/workflow/TaskState.html>` out of task widget by a configuration in json.

How to Use
----------

Put ``"excludeTechnicalTaskStates": ["CREATED"]`` to exclude all tasks with state ``CREATED`` out of the widget.

.. code-block:: javascript

  {
    "version": "12.0.17",
    "layout": {
      "w": 8,
      "h": 6,
      "x": 0,
      "y": 2
    },
    "enableQuickSearch": true,
    "showWidgetInfo": true,
    "showFullscreenMode": false,
    "showPinnedToggle": true,
    "excludeTechnicalTaskStates": ["CREATED"]
  }
..

