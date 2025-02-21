.. _iframe-in-portal:

IFrame In Portal
****************

Do you want to decouple your project and the **Portal** when starting a task to reduce your migration efforts?
If the answer is yes, this chapter will help you.

Since **Portal** 8, we introduced a new feature that a process/task can be started inside an IFrame This means that you can design
your HTML dialog independent of the  **Portal**. It is rendered automatically inside an IFrame by default.

.. _iframe-usage:

Templates to Use with IFrame
============================

Use the ``frame-10`` templates (provided by the core and using the Freya theme).  
These templates fully support responsive designs.

How To Use
==========

.. important::
	By default, i.e. if there is no configuration, a process/task is started inside an IFrame.

Follow these steps to use the IFrame approach:
 
#. **HTML Dialog Independence**

   Ensure your HTML User Dialog is independent of the **Portal**. You can use the ``frame-_x_`` 
   template in the designer or your own custom template. **Portal** will automatically render it in an IFrame.

#. **Configuration Levels**  
   If you want custom behavior, configure at one of the following levels to open your tasks in an IFrame:

   - **Task Level**: Set the ``embedInFrame`` field in Task custom fields to:

     - ``true``: Start inside IFrame.
     - ``false``: Do not start inside IFrame.
     - Leave unset to use case or engine-level configuration.

     |task-embedInFrame|

   - **Case Level**: Set the ``embedInFrame`` String field in Case custom fields to:

     - ``true``: Start inside IFrame.
     - ``false``: Do not start inside IFrame.
     - Leave unset to use engine-level configuration.

     |case-embedInFrame|

   - **Engine Level**:

     The **Portal Administrator** can globally configure all tasks to start inside IFrames by setting the ``Portal.EmbedInFrame`` value in the Portal settings.  
     Refer to :ref:`update-portal-settings`.

.. _iframe-configure-template:

Configure Template
==================

The **Portal** allows configuration of templates with the following options:

#. Name and icon for the working task.  
#. Process chain configuration.  
   (See: :ref:`components-portal-components-process-chain`).  
#. Show/hide case details and other actions.


Template Parameters
-------------------

The following parameters are available for this template:

**Task Details**

- ``taskName``: Custom text for the task name.
- ``taskIcon``: Icon for the task, using Streamline or Awesome font.
- ``isHideTaskName``: Hides the task name when true.

**Case Information**

- ``caseId``: ID of the case to be displayed in the case information dialog.
- ``isHideCaseInfo``: Hides the ``Show Information`` button when true.

**Process Steps**

- ``currentProcessStep``: Current step in the process, either as an index or step name.
- ``processSteps``: List of process steps or JSON.
- ``isShowAllSteps``: Displays all steps on large screens when true.
- ``processChainDirection``: Orientation of the process chain, either ``VERTICAL`` or ``HORIZONTAL``.
- ``processChainShape``: Shape of the process chain, either ``LINE`` or ``CIRCLE``.

**Task Actions**

- ``isHideTaskAction``: Hides the task action button when true.
- ``isWorkingOnATask``: Indicates if a task is currently being worked on, 
  useful for displaying a warning when leaving the page.

**Miscellaneous**

- ``announcementInvisible``: Makes the announcement invisible.
- ``isCardFrame``: Displays content of the IFrame inside a card-style container.
- ``viewName``: Defines a custom breadcrumb view.

Configuration Methods
---------------------

You can configure the UI in one of two ways:

.. note::
       We recommend to sanitize your parameters before passing them.

#. **Using ``IFrameTaskConfig`` Component** (Recommended)

   .. code-block:: xml

      <h:body>
         <ui:composition template="/layouts/frame-10.xhtml">
            ...
            <ic:com.axonivy.portal.components.IFrameTaskConfig 
               taskName="Approve Investment" 
               taskIcon="si si-bulb"
               isHideTaskName="false"
               caseId="123456"
               isHideCaseInfo="false"
               currentProcessStep="0"
               processSteps='["Create Investment Request", "Approve Investment Request"]'
               isShowAllSteps="true"
               processChainDirection="VERTICAL"
               processChainShape="LINE"
               isHideTaskAction="true"
               isWorkingOnATask="false"
               announcementInvisible="false"
               isCardFrame="true"
               viewName="TASK_DETAIL"
            />
            ...
         </ui:composition>
      </h:body>

#. **Using JavaScript**

   .. code-block:: xml

      <h:body>
         <ui:composition template="/layouts/frame-10.xhtml">
            ...
            <script>
               window.taskName = "Approve Investment";
               window.taskIcon = "si si-bulb";
               window.isHideTaskName = false;
               window.caseId = "123456";
               window.isHideCaseInfo = false;
               window.currentProcessStep = 0;
               window.processSteps = ["Create Investment Request", "Approve Investment Request"];
               // Convert Java List of steps to JSON format if needed:
               window.processSteps = #{portalComponentUtilsBean.convertToJSON(data.steps)};
               window.isShowAllSteps = true;
               window.processChainDirection = "VERTICAL";
               window.processChainShape = "LINE";
               window.isHideTaskAction = true;
               window.isWorkingOnATask = false;
               window.announcementInvisible = false;
               window.isCardFrame = true;
               window.viewName = "TASK_DETAIL";
            </script>
            ...
         </ui:composition>
      </h:body>

Configure Task Details
----------------------
You can customize task details, such as the task name and icon. 

**Parameters:**
   - ``taskName``: Custom text for the task name.
   - ``taskIcon``: Using Streamline or Awesome font (e.g., `si si-arrow-right`).
   - ``isHideTaskName``: Set to ``true`` to hide the task name. Default is ``false``.

**Example:**

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig
            taskName="Approve Investment"
            taskIcon="si si-bulb"
            isHideTaskName="false"
         />
         ...
      </ui:composition>
   </h:body>


Configure Case Information
--------------------------
Customize how case details are displayed and whether to show the "Show Information" button.

**Parameters:**
   - ``caseId``: The ID of the case to display in the information dialog.
   - ``isHideCaseInfo``: Set to ``true`` to hide the "Show Information" button.
     Default is ``false``.

**Example:**

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig
            caseId="123456"
            isHideCaseInfo="false"
         />
         ...
      </ui:composition>
   </h:body>

Configure Task Actions
----------------------
Control the visibility and behavior of task-related buttons and actions.

**Parameters:**
   - ``isHideTaskAction``: Set to ``true`` to hide the task action button. 
     Default is ``false``.
   - ``isWorkingOnATask``: Indicates if the task is active. 
     Useful for displaying a warning when leaving the page. Default is ``true``.

**Example:**

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig
            isHideTaskAction="true"
            isWorkingOnATask="true"
         />
         ...
      </ui:composition>
   </h:body>


Configure Miscellaneous Options
-------------------------------
Additional settings can influence the layout and visibility of elements.

**Parameters:**
   - ``announcementInvisible``: Set to ``true`` to hide announcements. 
     Default is ``false``.
   - ``isCardFrame``: Set to ``true`` to display the IFrame 
     content inside a card-style container.
   - ``viewName``: Custom breadcrumb view. Possible values are: 
     ``HOME, PROCESS, TASK, TASK_DETAIL, CASE_DETAIL, CASE, TECHNICAL_CASE, RELATED_TASK, 
     USER_PROFILE, ABSENCES_MANAGEMENT, DASHBOARD_CONFIGURATION, EDIT_DASHBOARD_DETAILS, 
     PROCESS_VIEWER, PORTAL_MANAGEMENT, NOTIFICATION``.

**Example:**   

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig
            announcementInvisible="false"
            isCardFrame="true"
            viewName="TASK_DETAIL"
         />
         ...
      </ui:composition>
   </h:body>

Configure Show Information
--------------------------

When you click the ``Show Information`` button, **Portal** will show  details of the
running case in a modal dialog.

Options for ``Show Information``

   - ``caseId``: Case ID of the case you want to show in the modal dialog.
   - ``isHideCaseInfo``: Hide the ``Show Information`` button

.. csv-table::
  :file: documents/available_show_information_options.csv
  :widths: 20 50
  :header-rows: 1
  :class: longtable

Example using IFrameTaskConfig:

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig 
            caseId="123456"
         />
         ...
      </ui:composition>
   </h:body>

Configure Process steps
-----------------------

``Process steps`` have many options to be configured from the list of steps to layout and shape.

Options for ``Process steps``

.. csv-table::
  :file: documents/available_process_steps_options.csv
  :widths: 20 50
  :header-rows: 1
  :class: longtable


.. note::

       When defining parameter ``processSteps``, please make sure that you add this JSP function tag to your HTML dialog:
       ``xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions"``

Example using IFrameTaskConfig:

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig 
            currentProcessStep="0"
            processSteps='["Create Investment Request", "Approve Investment Request"]'
            processChainDirection="VERTICAL"
            processChainShape="LINE"
         />
         ...
      </ui:composition>
   </h:body>

Other options
-------------

Various options can affect functions and layout.

.. csv-table::
  :file: documents/available_other_options.csv
  :widths: 20 50
  :header-rows: 1
  :class: longtable

Example using IFrameTaskConfig:

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig 
            isHideTaskAction="true"
            isWorkingOnATask="false"
            announcementInvisible="false"
            isCardFrame="true"
         />
         ...
      </ui:composition>
   </h:body>

Developer tips
==============

If your project has a navigation button that does not complete a task (e.g.,
Cancel), redirect the user to the desired page (e.g., the application home, task
list, process list, etc.) in the HTML dialog.

.. |task-embedInFrame| image:: images/task-embedInFrame.png
.. |case-embedInFrame| image:: images/case-embedInFrame.png
.. |case-list-template| image:: ../../screenshots/case/case-key-information.png
.. |task-list-template| image:: ../../screenshots/task/task-key-information.png
.. |task-name-template| image:: ../../screenshots/layout-template/task-template.png
