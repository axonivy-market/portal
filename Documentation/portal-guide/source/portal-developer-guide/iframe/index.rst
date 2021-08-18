.. _iframe-in-portal:

IFrame in Portal
****************

Do you want to decouple your project and Portal when start a task to reduce your migration effort?
If the answer is yes, this chapter will help you.

Since Portal 8, we introduce new feature that a process/task could be started inside IFrame, means that you can feel free to design
your html dialog independent from Portal, it is rendered automatically inside IFrame as default in order to reduce migration effort.

.. _iframe-usage:

How to use
==========

.. important::
	If there is no configuration, a process/task is started inside IFrame as default.

Follow these steps:
 
#. Your HTML User Dialog must be independent from Portal, could use the ``frame-8`` template in designer, or your own template, Portal will render it automatically in IFrame
	
#. To pass some supported params in IFrame such as process steps, refer to :ref:`IFrameTaskTemplate <components-layout-templates-iframe-task-template>`

#. If you don't want to use the default configuration, follow one of these 3 levels to open your task(s) in IFrame:

   - Task level: in Task custom fields, set the ``embedInFrame`` String field to
   
   	- ``true``: start inside IFrame
   	- ``false``: not start inside IFrame
   	- Don't set to check case level
   	
   	|task-embedInFrame|
   
   - Case level: in Case custom fields, set the ``embedInFrame`` String field to 
   
   	- ``true``: start inside IFrame 
   	- ``false``: not start inside IFrame 
   	- Don't set to check engine level
   	
   	|case-embedInFrame|
   
   - Engine level:
   
   	- Portal Administrator could choose the option that all of the tasks in whole engine are started inside IFrame or not via the ``Portal.EmbedInFrame`` Portal settings, refer to :ref:`update-portal-settings`


Customization
=============

In case you had built your own portal and had copied the ``PortalStart`` process from ``PortalTemplate`` to your project.

You must do some important steps below to make your process/task could be started inside IFrame:

  1. Make sure your own project depends on the ``PortalTemplate``

     - E.g: ``CustomizedPortal`` depends on the ``PortalTemplate``

  2. Create another project that contains all business processes, this project depends on your portal customized project.

     - E.g: ``BusinessProject`` depends on ``CustomizedPortal``

  3. Change the ``DefaultPages`` in ``Standard Processes`` to your portal customized project library id, refer to `Standard Processes <https://developer.axonivy.com/doc/8.0/engine-guide/administration/standard-processes.html>`_ doc.

     - E.g: DefaultPages: ``CustomizedPortal`` id.

  4. Now, create your start process inside your second project. For example, it is ``BusinessProject`` and creates your HTML Dialog with template ``frame-8.xhtml``.

.. important:: 
    We must create 2 projects: ``CustomizedPortal`` and ``BusinessProject`` and create your process start in ``BusinessProject``, not in ``CustomizedPortal``.

    Because you copied ``PortalStart.mod`` and this mod contains the ``DefaultFramePage.ivp`` start.
    So every process start which is in the same **PMV** as the ``DefaultFramePage.ivp`` is not opened in the IFrame, to avoid recursion.
    This means that not the Frame Dialog itself is opened again in an IFrame and so on.


.. |task-embedInFrame| image:: images/task-embedInFrame.png
.. |case-embedInFrame| image:: images/case-embedInFrame.png
