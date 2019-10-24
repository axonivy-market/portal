.. _customization-task-widget:

Task widget
===========

TaskWidget is a built-in component of Portal which contains the tasks
users can interact with. In order to show needed task's information,
Portal supports overriding concept for TaskWidget. Each TaskWidget
contains 2 parts:

1. `UI <#customization-task-widget-howtooverideui>`__:
   TaskHeader and TaskListHeader and TaskFilter

2. `Data
   query <#customization-task-widget-howtooverridedataquery>`__:
   display the tasks as you want

..

   **Important**

   1. Task header customization currently support responsive design.
      Refer to `this
      part <#customization-task-widget-responsivelayout>`__
      for more detail.

   2. Task header's buttons cannot be modified (they stay where they
      are)

.. _customization-task-widget-how-to-overide-ui:

How to override task widget's UI
--------------------------------

Refer to ``PortalExamples`` project for examples

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. To customize task widget, you must customize Portal Home first. Refer
   to `Customize Portal
   home <#axonivyportal.customization.portalhome>`__ to set new home
   page.

3. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

4. Override Task widget in: TaskList page, Task Search result.

   -  Introduce a new HTMLDialog which uses template
      ``/layouts/PortalTasksTemplate.xhtml`` (refer to
      `Responsiveness <#axonivyportal.components.layouttemplates.reponsiveness>`__
      to override responsiveness). You can take a look at
      ``PortalTasks.xhtml`` to see how to customize it.

         **Tip**

         Highly recommend to copy the ``PortalTasks`` HTMLDialog in
         PortalTemplate and change the copied one's view.

   -  Use Axon.ivy Override to override the
      OpenPortalTasks
      callable. The original implementation of this callable is calling
      PortalTasks
      , change it to call the customized Page introduced in the step
      above. The signature of this callable is
      useView(TaskView)
      and customized page must receive this
      TaskView
      instance, put in the dialog's
      Data
      with the exact name
      taskView
      .

5. After previous steps, you can override `TaskListHeader and
   TaskHeader <#customization-task-widget-howtooverideui.taskheader>`__
   and
   `TaskFilter <#customization-task-widget-howtooverideui.taskfilter>`__

.. _customization-task-widget-how-to-overide-ui-task-header:

Task List Header and Task header
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Refer to the ``taskListHeader`` and ``taskHeader`` sections in
``PortalTasks.xhtml`` of PortalTemplate. In case your task widget has
new columns, you should override TaskLazyDataModel to make the sort
function of these columns work:

-  Introduce a java class extends TaskLazyDataModel

-  Override the ``extendSort`` method and extend the sort function for
   the added columns (see the method's Javadoc comments)

-  Default taskList supports user to config display/hide column

   -  In case you have new columns, override method
      ``getDefaultColumns`` of the extended class from TaskLazyDataModel
      to display checkboxes in Config columns panel and display/hide
      sortFields (see the methods' Javadoc comments)

   -  To add cms for checkboxes's label, add new entries to folder
      ``/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/`` in
      ``PortalStyle`` or override method ``getColumnLabel``\ (see the
      methods' Javadoc comments)

   -  In ``taskListHeader`` section, use ``TaskColumnHeader`` component

   -  In ``taskHeader`` section, use ``TaskCustomField`` component for
      each additional columns. This component will handle display/hide
      new columns on task list.

      Currently, TaskCustomField only supports text field. If you want
      to create your own component, remember to add
      rendered="#{taskView.dataModel.isSelectedColumn('YOUR_CUSTOM_COLUMN')}"

      For example: Show custom field ``customer name`` which stored in
      ``task.customVarCharField5``

      ``<ic:ch.ivy.addon.portalkit.component.task.column.TaskCustomField id="customer-name-component" componentId="customer-name" column="customVarCharField5" dataModel="#{taskView.dataModel}" labelValue="#{task.customVarCharField5}" />``

-  Use Axon.ivy Override to override the ``InitializeTaskDataModel``
   callable and initialize data model by your customized one.

-  In your customized portal tasks HTMLDialog, the customized data model
   should be passed as a parameter to components (refer to
   ``PortalTasks.xhtml``).

.. _customization-task-widget-how-to-overide-ui-task-filter:

Task filter
~~~~~~~~~~~

-  Refer to the ``taskFilter`` section in ``PortalTasks.xhtml`` of
   PortalTemplate.

-  In order to introduce new filter, create a new java class extends
   TaskFilter and override its methods (see javadoc comments)

   |task-filter|

-  Introduce a java class extends TaskFilterContainer. This filter
   container contains your filters, you can reuse default filters, refer
   to ``DefaultTaskFilterContainer.java``

      **Tip**

      StateFilter is added as default to container. If you don't need
      it, use this code in constructor: ``filters.remove(stateFilter);``

-  Introduce a java class extends TaskLazyDataModel. Override the
   ``initFilterContainer`` method and initialize filter container (see
   javadoc comments)

-  Use Axon.ivy Override to override the ``InitializeTaskDataModel``
   callable and initialize data model by your customized one.

-  In your customized portal tasks HTMLDialog, the customized data model
   and filter container should be passed as parameters to components
   (refer to ``PortalTasks.xhtml``).

-  **Advanced usage:** Portal supports storing/restoring filters. Your
   filter class (extends ``TaskFilter``) is stored in business data.
   Properties stored user input values should be persisted, properties
   controlled logic should not be persisted to reduce persisted data
   size in business data. Use annotation ``@JsonIgnore`` to exclude
   properties. By default, Portal takes care storing/restoring filters.
   If you want to customize storing/restoring filter data, do it in your
   data model class (extends ``TaskLazyDataModel`` class).

   By default, filters are stored/restored in process model level. You
   can change this by setting the ui:param ``filterGroupId`` in
   ``PortalTasks.xhtml`` to a new Long value.

      **Tip**

      If you have multiple case lists in your project, you may want to
      set ``filterGroupId`` to an unique identifier for each of your
      ``PortalTasks.xhtml across your projects``

.. _customization-task-widget-how-to-override-data-query:

How to override task widget's data query
----------------------------------------

-  Override the
   BuildTaskQuery
   callable process of PortalKit and build your own query to effect the
   data of task widget, task categories and statistic widget.
-  If you want to apply a query for only Home page task list, not for
   Full mode task list, use attribute
   isQueryForHomePage
   in
   BuildTaskQuery
   callable process to specify the query for Home page task list, e.g.
   ::

      if (in.isQueryForHomePage) { // in home page
          in.taskQuery = TaskQuery.create().where().activatorUserId().isNotNull();
      }

-  Apply the following steps in case you would like to provide data for
   task list after navigating to task list from your page, e.g. clicking
   on a bar chart then opening the tasks of that bar:

   -  Use the ``OpenPortalTasks`` callable process with the ``TaskView``
      parameter. It is used to define which information are displayed in
      TaskWidget.

   -  Refer to TaskView, TaskSearchCriteria to build your TaskView

   ::

      TaskLazyDataModel dataModel = new TaskLazyDataModel();
      // Set your TaskQuery
      dataModel.getCriteria().setCustomTaskQuery(YOUR_TASK_QUERY); 
      // Display the tasks of all users
      dataModel.getCriteria().setAdminQuery(true); 
      out.taskView = TaskView.create().dataModel(dataModel)
      .showHeaderToolbar(false).createNewTaskView();

.. _customization-task-widget-custom-task-delegate:

Custom task delegate
--------------------

Portal allows to customize the list of users and roles that a task can
be delegated to. This can be done following these steps:

1. Introduce a Axon.ivy project which has ``PortalTemplate`` as a
   required library and its own ``PortalStart`` process. Refer to step
   1, 2, 3, 4 in `override task widget's
   UI <#customization-task-widget-howtooverideui>`__ guide.

2. In your project, override the callable subprocess
   ``CalculateTaskDelegate``

   |calculate-task-delegate|

3. The callable subprocess data contains the current user
   ``in.currentUser`` and the current task to be delegated ``in.task``.
   The lists ``in.users`` and ``in.roles`` contain all possible users
   and roles that the task can be delegated to. Modify those two to have
   your own delegate list.

.. _customization-task-widget-responsive-layout:

How to make reponsive task list
-------------------------------

If you have customized task list and want it responsive on different
screen sizes, please follow below steps.

You can refer to ``PortalExamples`` project for examples

1. Add responsiveStyleClass param (in case you're using Portal
   component), or styleClass (in case you're using Primefaces or JSF
   component) with the same responsive css class for both taskListHeader
   and taskHeader. You can find responsive class in `this
   part. <#axonivyportal.customization.responsivecss>`__

   |responsive-task-list-customization|

      **Tip**

      Hint: ``TaskCustomField`` component has default
      responsiveStyleClass is ``u-hidden-sm-down``

2. Responsiveness could be broken when you anchor left menu. In this
   case, to maintain the responsiveness, you could hide some columns by
   add ``js-hidden-when-expand-menu`` to responsiveStyleClass or
   styleClass param of taskListHeader and taskHeader.

   |responsive-task-list-hide-column|

      **Tip**

      Hint: The smallest browser width you can anchor the left menu is
      1025. So you could reduce width of browser to 1025 to test and
      decide which columns need to be hidden.

.. |task-filter| image:: images/-task-widget/task-filter.png
.. |calculate-task-delegate| image:: images/-task-widget/calculate-task-delegate.png
.. |responsive-task-list-customization| image:: images/-task-widget/responsive-task-list-customization.png
.. |responsive-task-list-hide-column| image:: images/-task-widget/responsive-task-list-hide-column.png

