.. _iframe-in-portal:

IFrame In Portal
****************

Do you want to decouple your project and the **Portal** when starting a task to reduce your migration efforts?
If the answer is yes, this chapter will help you.

Since **Portal** 8, we introduced a new feature that a process/task can be started inside an IFrame This means that you can design
your HTML dialog independent of the  **Portal**. It is rendered automatically inside an IFrame by default.

.. _iframe-usage:

Templates to use with IFrame: frame-10 templates (Provided by core, uses Freya theme). This template fully supports responsiveness.

How To Use
==========

.. important::
	By default, i.e. if there is no configuration, a process/task is started inside an IFrame.

Follow these steps to use the IFrame approach:
 
#. Your HTML User Dialog has to be independent of the **Portal**. You can use
   the ``frame-_x_`` template in designer, or your own template. **Portal** will
   render it automatically in an IFrame.

#. If you don't want to use the default configuration, apply one of the following three
   levels to open your task(s) in an IFrame:

   - Task level: in Task custom fields, set the ``embedInFrame`` field to
   
   	- ``true``: start inside IFrame
   	- ``false``: not start inside IFrame
   	- Don't set if you want to use case or engine level
   	
   	|task-embedInFrame|
   
   - Case level: in Case custom fields, set the ``embedInFrame`` String field to 
   
   	- ``true``: start inside IFrame 
   	- ``false``: not start inside IFrame 
   	- Don't set if you want to use engine level
   	
   	|case-embedInFrame|
   
   - Engine level:
   
     - The **Portal Administrator** can define globally that all of the tasks
       running on the engine are started inside IFrames by using the
       ``Portal.EmbedInFrame`` Portal setting. refer to
       :ref:`update-portal-settings`

.. _iframe-configure-template:

Configure template
==================

**Portal** supports some configurations for templates like:

#. Name and icon of the working task.

#. Configuration of :ref:`components-portal-components-process-chain`.

#. Show/hide case details and other actions.

|task-name-template|

You could configure UI in either of these two ways:

#. Using the component IFrameTaskConfig (recommended)

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

#. Using Javascript

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
               window.currentProcessStep = #{data.currentProcessStep};
               // Set process steps directly as Array.
               window.processSteps = ["Create Investment Request", "Approve Investment Request"];
               // If process steps are set in HTML dialog logic or java code, convert it to JSON format
               // Use this code if process steps are a java String list
               window.processSteps = #{portalComponentUtilsBean.convertToJSON(data.steps)};
               window.isShowAllSteps = true;
               window.processChainDirection = "VERTICAL";
               window.processChainShape = "LINE";
               window.isHideTaskAction = true;
               window.isWorkingOnATask = false;
               window.announcementInvisible = false;
               window.isCardFrame = true;
               window.viewName = TASK_DETAIL;
            </script>
            ...
         </ui:composition>
      </h:body>

Configure Task name
-------------------

By default, **Portal** uses the name of the working task.

Options for ``Task name``

.. csv-table::
  :file: documents/available_task_options.csv
  :widths: 20 50
  :header-rows: 1
  :class: longtable

Example using IFrameTaskConfig:

.. code-block:: xml

   <h:body>
      <ui:composition template="/layouts/frame-10.xhtml">
         ...
         <ic:com.axonivy.portal.components.IFrameTaskConfig 
            taskName="Approve Investment"
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
