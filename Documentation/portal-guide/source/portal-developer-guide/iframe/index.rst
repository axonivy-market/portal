.. _iframe-in-portal:

IFrame In Portal
****************

Do you want to decouple your project and the **Portal** when starting a task to reduce your migration efforts?
If the answer is yes, this chapter will help you.

Since **Portal** 8, we introduced a new feature that a process/task can be started inside an IFrame This means that you can design
your HTML dialog independent of the  **Portal**. It is rendered automatically inside an IFrame by default.

.. _iframe-usage:

How To Use
==========

.. important::
	By default, i.e. if there is no configuration, a process/task is started inside an IFrame.

Follow these steps to use the IFrame approach:
 
#. Your HTML User Dialog has to be independent of the **Portal**. You can use
   the ``frame-_x_`` template in designer, or your own template. **Portal** will
   render it automatically in an IFrame.
	
#. To pass some supported params into the IFrame such as process steps, refer to
   :ref:`IFrameTaskTemplate <components-layout-templates-iframe-task-template>`

#. If you don't want to use the default configuration, apply one of the following three
   levels to open your task(s) in an IFrame:

   - Task level: in Task custom fields, set the ``embedInFrame`` String field to
   
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


Customization
=============

If you have built your own portal and **had copied the process** ``PortalStart``
from the ``<PortalTemplate>`` to your project, you have to follow some important
steps to ensure that your processes/tasks can be rendered inside an IFrame:

  1. Make sure your own portal project depends on the ``<PortalTemplate>``

     - E.g: ``<CustomizedPortal>`` depends on the ``<PortalTemplate>``

  2. Create a business project that contains all business processes.

     a. E.g: A project name ``<BusinessProject>``

     b. If your business project (``<BusinessProject>``) needs some data or
        resources from the Portal project (``<CustomizedPortal>``) then create a
        dependency between these two projects. If not, please **skip step 2.b**.

       * E.g. ``<BusinessProject>`` depends on ``<CustomizedPortal>``

  3. Change the ``DefaultPages`` in ``StandardProcesses`` to your customized
     portal project library id. For details, refer to :dev-url:`Standard Processes
     </doc/nightly/engine-guide/deployment/advanced/index.html>`.

     - E.g: DefaultPages: ``<CustomizedPortal>`` ID.

Now you can develop your own processes inside the ``<BusinessProject>`` and the dialogs will be rendered automatically using IFrames.

.. important:: 
    We have to  create two projects: ``<CustomizedPortal>`` and ``<BusinessProject>``. 
    Create your process start in ``<BusinessProject>``, not in ``<CustomizedPortal>``.

    Because you copied ``PortalStart.p.json``, this contains the
    ``DefaultFramePage.ivp`` start. So every process start which is in the same
    **PMV** as the ``DefaultFramePage.ivp`` is not opened in the IFrame, to
    avoid recursion. This means that the IFrame Dialog itself is **not** opened
    again in an IFrame and so on.


.. |task-embedInFrame| image:: images/task-embedInFrame.png
.. |case-embedInFrame| image:: images/case-embedInFrame.png
