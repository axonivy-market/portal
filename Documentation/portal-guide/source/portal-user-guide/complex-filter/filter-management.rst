Restricting Filter Fields And Operators
---------------------------------------

Portal provides a two-level system to control which filter operators are available to users
in the complex filter panel.

Global configuration (Admin)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Administrators can configure which operators are available globally via the
``Portal.ComplexFilter.Operators`` setting in `Admin Settings > Settings`.

- All operators are enabled by default.
- Any operator unchecked here is disabled globally.
- Globally disabled operators cannot be re-enabled at the widget level.

.. note::

   **Example:** If the admin disables ``Contains``, no widget configuration or user filter
   can use ``Contains``.

Widget-level configuration (Column Management)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

It is possible to further restrict which fields and operators are available per filterable column in
the Column Management dialog.
See :ref:`Task list column management <task-widget-column-management>` and :ref:`Case list column management <case-widget-column-management>`
for how to open this dialog.

|task-list-widget-table-configuration|

- Directly uncheck `Filter` checkbox of a field to prevent user from using it.
- Operators disabled globally by the admin are greyed out and cannot be selected.
- If all operators for a field are globally disabled, the `Filter` checkbox for
  that field is automatically disabled and cannot be turned on.
- This configuration can only narrow the global setting — it cannot re-enable a globally
  disabled operator.

.. note::

   When field and operator settings change after filters have already been saved or applied, Portal
   automatically removes affected filters.

.. |task-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-table-configuration.png
   :alt: Dashboard task widget's table configuration
