.. _iframe-in-portal:

IFrame in Portal
****************

Do you want to decouple your project and Portal when start a task to reduce your migration effort?
If the answer is yes, this chapter will help you.

Since Portal 8, we introduce new feature that a process/task could be started inside IFrame, means that you can feel free to design
your html dialog independent from Portal, it is rendered inside IFrame in order to reduce migration effort.

.. _iframe-configuration:

How to configure
================

There are 4 levels:

- Task level: in Task custom fields, set the ``embedInFrame`` String field to

	- ``true``: start inside IFrame
	- ``false``: not start inside IFrame
	- Don't set to check case level
	
	|task-embedInFrame|

- Case level: in Case custom fields, set the ``embedInFrame`` String field to 

	- ``true``: start inside IFrame 
	- ``false``: not start inside IFrame 
	- Don't set to check application level
	
	|case-embedInFrame|

- Application level:

	- Portal Administrator could :ref:`register an application <settings-admin-settings>` and choose the option that all of the tasks in this application are started inside IFrame
	- Don't register an application to check engine level

- Engine level:

	- Portal Administrator could choose the option that all of the tasks in whole engine are started inside IFrame or not via the ``EMBED_IN_FRAME`` Portal variable in :ref:`Global settings <settings-admin-settings-global-settings>`
	
.. important::
	If there is no configuration, a process/task is started inside IFrame as default.

.. _iframe-usage:

How to use
==========

Follow the steps:

1. Configure as the above instruction to start your task inside IFrame
 
2. In your task, open a HTML User Dialog independent from Portal:

	- Create a dialog with the frame-8 template in designer, or use your own template to separate your css styles from Portal (highly recommend)
	- Or use the :ref:`TaskTemplate <components-layout-templates-task-template>` template (no encouragement - dead path)
	
	+------------------------------------------------------+----------------------------------------------+
	| Your own template                                    | TaskTemplate.xhtml - Modena ivy theme        |
	+======================================================+==============================================+
	| Pros:                                                | Pros:                                        |
	|                                                      |                                              |
	|  - Portal and project styles are independent         |  - Keep the old look&feel since Portal 7     |
	|  - Less migration effort in future                   |  - Less migration effort to Portal 8         |
	|                                                      |                                              |
	| Cons:                                                | Cons:                                        |
	|                                                      |                                              |
	|  - Highest migration effort expected for old projects|  - Modena is out of support by Primefaces    |
	|                                                      |  - Modena was highly customized by Portal    |
	|                                                      |  - Different look&feel between Portal and UIs|
	+------------------------------------------------------+----------------------------------------------+
	
3. To pass some supported params in IFrame such as process steps, refer to :ref:`IFrameTaskTemplate <components-layout-templates-iframe-task-template>`

.. |task-embedInFrame| image:: images/task-embedInFrame.png
.. |case-embedInFrame| image:: images/case-embedInFrame.png	
