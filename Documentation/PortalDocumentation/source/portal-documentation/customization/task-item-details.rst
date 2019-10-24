.. _customization-task-item-details:

Task item details
=================

TaskItemDetails is a built-in component of Portal which contains the
case info which users can interact with. In order to show needed task's
information, Portal supports overriding concept for TaskItemDetails.

Each TaskItemDetails contains

-  showItemDetailsHeader

-  showItemDetailsNotes

-  showItemDetailDocuments

-  TaskItemDetail custom panel: taskItemDetailCustomPanelTop ,
   taskItemDetailCustomPanelBottom

|task-standard|

   **Important**

   Task Data and Description box always display, we cannot override the
   content or hidden/show (they stay where they are)

.. _customization-task-item-details-how-to-overide-ui:

How to custom Task item details UI
----------------------------------

Refer to ``PortalExamples`` project for examples.

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. To customize task item detail, you must customize Portal Home first.
   Refer to `Customize Portal
   home <#axonivyportal.customization.portalhome>`__ to set new home
   page.

3. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

4. Custom the Task item details:

   -  Introduce a new HTMLDialog which uses template
      ``/layouts/PortalTaskDetailsTemplate.xhtml``. You can take a look
      at ``PortalTaskDetails.xhtml`` to see how to customize it.

         **Tip**

         Highly recommend to copy the ``PortalTaskDetails`` HTMLDialog
         in PortalTemplate. Remove ``ui:remove`` and change the copied
         one's view.

   -  Use Axon.ivy Override to override the
      OpenPortalTaskDetail
      callable. The original implementation of this callable is calling
      PortalTaskDetails
      , change it to call the customized Page introduced in the step
      above. The signature of this callable is
      call(ITask)
      and customized page must receive this
      ITask
      instance, put in the dialog's
      Data
      with the exact name
      taskData
      .

5. After previous steps, you can override Task item details UI elements
   as shown/hidden element by keywords:

   To show/hide, please using ``showItemDetailsHeader``,
   ``showItemDetailsNotes``, ``showItemDetailDocuments`` code. For more
   details, please refer to `Show/Hide
   components <#customization-task-item-details-howtooverideui.showhiddenui>`__.

   And to add a new elements, please refer to `Add new Custom
   panel <#customization-task-item-details-howtooverideui.custombody>`__
   code

.. _customization-task-item-details-how-to-overide-ui-show-hidden-ui:

Show/Hide components by keywords
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Refer to the ``ui`` tag list in ``PortalTaskDetails.xhtml`` of
PortalTemplate. In case, we want to show/hide any elements on
TaskItemDetails, we should override value of ``ui:param``

List valid parameters:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show/hide Task header, by default it's true.

-  ``ui:param name="showItemDetailsNotes" value="true">``

   To show/hide Task Notes component, by default it's true.

-  ``ui:param name="showItemDetailDocuments" value="true"``

   To show/hide Task Documents component, by default it's true.

.. _customization-task-item-details-how-to-overide-ui-custom-body:

Add new Custom panel
~~~~~~~~~~~~~~~~~~~~

Refer to the ``taskItemDetailCustomPanel*`` section in
``PortalTaskDetails.xhtml`` of PortalTemplate.

-  We need to define the ``ui:define`` with the valid name such as
   ``taskItemDetailCustomPanelTop`` and
   ``taskItemDetailCustomPanelBottom``.

   The ``taskItemDetailCustomPanelTop``: will be shown on the top of the
   component

   The ``taskItemDetailCustomPanelBottom``: will be shown on the bottom
   of the component

-  Add your custom code into that tag

-  Finally, your custom panel will be displayed inside of
   `TaskItemDetails <#customization-task-item-details->`__
   page

-  Below is example code for override custom panel box of task details

   |custom-panel-example-code|

-  After applied above code to your custom page, the custom panel will
   display as below

   |custom-panel-override|

-  Finally, we have a custom Task details page

   |task-customized|

.. |task-standard| image:: images/task-item-details/task-standard.png
.. |custom-panel-example-code| image:: images/task-item-details/custom-panel-example-code.png
.. |custom-panel-override| image:: images/task-item-details/custom-panel-override.png
.. |task-customized| image:: images/task-item-details/task-customized.png

