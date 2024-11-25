.. raw:: html

    <style>
      .wy-nav-content {
         max-width: 1000px;
      }
    </style>

.. _complex-filter:

Complex Filter 
**************

Introduction
------------
For easier viewing, Portal offers an intuitive and robust filtering system, allowing you to customize your view based on specific criteria.
This feature is available for the Case Widget and the Task Widget in Dashboard.

How to use
----------

- The Filters dialog can be accessed by clicking on the :guilabel:`Filters` button within the Task/Case Widget on the Dashboard, or by clicking on the :guilabel:`Filter` button within the Edit Widget Configuration panel on the Edit Dashboard. 
  Please refer to :ref:`new-dashboard` for more details. 

- To add a filter, select ``Add filter``, then select the column and operator, and input value if needed.  
    
    |filter-operator-dropdown|

- Portal allows you to combine filters, these filters connect to each other using the **AND** operator.

    |case-task-widget-filter-combine|

- Click on |remove-filter-icon| button to remove a filter.
- Click on ``Apply`` button to apply filters.

.. tip::
    For example, within a case widget, these filters are applied:

      |complex-filter-example|

    This filter configuration finds all cases in state OPEN, with case name containing 'Mike', created today.

If the task/case widget has been pre-configured with filters in the dashboard configuration, these filters will be displayed as read-only filters.

For more information about filter operators, please refer to the table :ref:`filter-operators`.

Save A Filter Set 
-----------------

On dashboard task/case widget, filters that have been added can be saved to a filter set by following these steps:

- Click on ``Save filter`` button, then enter the filter set name and click ``Save``.

- Saved filter sets will be displayed on the left side of the filter dialog.

- Select a filter set to apply it, and deselect it to remove the filter set.

- If a filter set name is bold, it means that filter set is currently applied.

    |widget-save-filter|

Filter Set Management
---------------------

You can manage your saved filter set in the Widget Filter Management dialog.

- In the ``Available Filters`` dialog, select ``Manage filters``.

- To search for a filter set, type the search value into the input field at the top of every column.

- To delete filters, select the checkbox next to the filter set and then click :guilabel:`Remove selection` button

    |widget-filter-management|

.. _filter-operators:

Filter Operators
----------------

.. list-table::

 * - **Filter**
   - **Usage Guideline**
 * - In
   - | Valid for category, state, creator, application. Specify a single value or multiple values.
     | Results include only records where the data in the column match the value in the filter.
 * - Not In
   - | Valid for category, creator. Specify a single value or multiple values.  
     | Results include only records where the data in the column does not match the value in the filter.
 * - Contains
   - | Valid for category, ID and column that contains text. Specify a single value or multiple values.  
     | Results include only records where the data in the column contains the value in the filter.
 * - Doesn't Contains
   - | Valid for category, ID and column that contains text. Specify a single value or multiple values.  
     | Results include only records where the data in the column dóes not contain the value in the filter.
 * - Between
   - | Valid for a column that contains number, dates. Specify two values.  
     | Result include only records where the data in the column is between the two values in the filter.
 * - Not Between
   - | Valid for a column that contains number, dates. Specify two values.  
     | Result include only records where the data in the column is not between the two values in the filter.
 * - Equal
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column match the value in the filter.
 * - Not Equal
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column does not match the value in the filter.
 * - Less
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column is less than the value in the filter.
 * - Less or equal
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column is less than or the equal to the value in the filter.
 * - Greater
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column is greater than the value in the filter.
 * - Greater or equal
   - | Valid for a column that contains number. Specify a single value.  
     | Results include only records where the data in the column is greater than or equal to the value in the filter.
 * - Is Empty
   - | Valid for a column that contains text, numbers, or dates. Do not specify a value.
     | Results include only records that contain no data in the column.
 * - Is Not Empty
   - | Valid for a column that contains text, numbers, or dates. Do not specify a value.
     | Results include only records that contain data in the column.
 * - Is
   - | Valid for a column that contains text, dates. Specify a single value or multiple values.
     | Results include only records where the data in the column match the value in the filter.
 * - Is Not
   - | Valid for a column that contains text, dates. Specify a single value or multiple values.
     | Results include only records where the data in the column does not match the value in the filter.
 * - Today
   - | Valid for a column that contains dates. Do not specify a value.
     | Results include only records where the date in the column matches the current date.
 * - Yesterday
   - | Valid for a column that contains dates. Do not specify a value.
     | Results include only records where the date in the column matches the yesterday's date.
 * - Is Before
   - | Valid for a column that contains dates. Specify a single value.
     | Results include only records where the date in the column is before the date in the filter.
 * - Is After
   - | Valid for a column that contains dates. Specify a single value.
     | Results include only records where the date in the column is after the date in the filter.
 * - Current
   - | Valid for a column that contains dates. Select Year, Month or Week.
     | Results include only records where the date in the column falls within the range specified in the filter.
     | Specifically, it will be from the first day of the current year/month/week to the end of the last day of the current
     | year/month/week.
     | 
     | *Example*: Find all cases that were created in the current month.
     | :bdg-dark-line:`Created Date` :bdg-dark-line:`Current` :bdg-dark-line:`Month`
     |  
     | *Explanation*: Assuming today is ``2/27/2024 11:41:28``, the query will retrieve cases where the Created Date falls 
     | within the period from ``2/1/2024 00:00:00`` to ``2/29/2024 23:59:59``.
 * - Within the last
   - | Valid for a column that contains dates. Specify a single value then select Year(s), Month(s), Week(s) or Day(s).
     | Results include only records where the date in the column falls within the range specified in the filter.
     | Specifically, it will be from the beginning of the first day of the last <input_value> year(s)/month(s)/week(s) 
     | to the present moment.
     |
     | *Example*: Find all cases that were created within the last 2 years
     | :bdg-dark-line:`Created Date` :bdg-dark-line:`Within the last` :bdg-dark-line:`2` :bdg-dark-line:`Year(s)`
     |  
     | *Explanation*: Assuming today is ``2/27/2024 11:41:28``, the query will retrieve cases where the Created Date falls 
     | within the period from ``2/27/2022 00:00:00`` to ``2/27/2024 11:41:28``.
 * - Within the next
   - | Valid for a column that contains dates. Specify a single value then select Year(s), Month(s), Week(s) or Day(s).
     | Results include only records where the date in the column falls within the range specified in the filter.
     | Specifically, it will be from the present moment to the end of the day of the next <input_value> 
     | year(s)/month(s)/week(s).
     |
     | *Example*: Find all cases with Invoice Due Date within the next 3 weeks
     | :bdg-dark-line:`Invoice Due Date` :bdg-dark-line:`Within the next` :bdg-dark-line:`3` :bdg-dark-line:`Week(s)`
     |  
     | *Explanation*: Assuming today is ``2/27/2024 11:41:28``, the query will retrieve cases where the Invoice Due Date falls 
     | within the period from ``2/27/2024 11:41:28`` to ``3/19/2024 23:59:59``.
 * - Starts with
   - | Valid for a column that contains text. Specify a single value or multiple values.
     | Results include only records where the data in the column start with the value in the filter.
 * - Doesn't start with
   - | Valid for a column that contains text. Specify a single value or multiple values.
     | Results include only records where the data in the column does not start with the value in the filter.
 * - Ends with
   - | Valid for a column that contains text. Specify a single value or multiple values.
     | Results include only records where the data in the column end with the value in the filter.
 * - Doesn't end with
   - | Valid for a column that contains text. Specify a single value or multiple values.
     | Results include only records where the data in the column does not end with the value in the filter.
 * - No Category
   - | Valid for category. Do not specify a value.
     | Results include only records where the data in the column has no category.
 * - Current user
   - | Valid for creator. Do not specify a value.
     | Results include only records where the data in the column has been created by current user.

.. include:: ../includes/_common-icon.rst

.. |case-task-widget-filter-combine| image:: ../../screenshots/new-dashboard/case-task-widget-filter-combine.png
   :alt: Complex filters combination
.. |filter-operator-dropdown| image:: ../../screenshots/new-dashboard/filter-operator-dropdown.png
   :alt: Complex filters's operator selection
.. |widget-filter-management| image:: ../../screenshots/new-dashboard/widget-filter-management.png
   :alt: Widget filter management dialog
.. |widget-save-filter| image:: ../../screenshots/new-dashboard/widget-save-filter.png
   :alt: Save widget's complex filters
.. |complex-filter-example| image:: ../../screenshots/new-dashboard/complex-filter-example.png
   :alt: Complex filter example