.. _iframe-in-portal:

IFrame In Portal
****************

Do you want to decouple your project and the **Portal** when starting a task to reduce your migration efforts?
If the answer is yes, this chapter will help you.

Since **Portal** 8, we introduced a new feature that a process/task can be started inside an IFrame This means that you can design
your HTML dialog independent of the  **Portal**. It is rendered automatically inside an IFrame by default.

.. _iframe-usage:

Templates to use with IFrame:

#. frame-8 template (Provided by core, uses Serenity theme)

#. frame-10 template (Provided by core, uses Freya theme)

These templates have the same header, which is a menu of applications that you
configure on the Administration page. Since version 8.0, Portal officially
supports responsiveness. Every template has its default responsiveness. Refer to
:ref:`Responsiveness <components-layout-templates-responsiveness>` to override
it.

Additionally, there are user settings like My Profile, Absences, Email, and
Administration (for Administrators only). For details about user settings, refer
to :ref:`Settings <settings>`.

.. note::

      The frame-8 and frame-10 templates do not contain any content from the Portal.
      Therefore, if you want to reuse some Portal content, you have to add
      it manually to your HTML file.

      For example, if you want to use the customized layout in Portal, add the code below:
      ``<h:outputStylesheet library="css" name="template.css" />``

|portal-header|

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
from ``portal`` to your project, you have to follow some important
steps to ensure that your processes/tasks can be rendered inside an IFrame:

  1. Make sure your own portal project depends on ``portal``

     - E.g: ``CustomizedPortal`` depends on the ``portal``

  2. Create a business project that contains all business processes.

     a. E.g: A project name ``BusinessProject``

     b. If your business project ``BusinessProject`` needs some data or
        resources from the Portal project ``CustomizedPortal`` then create a
        dependency between these two projects. If not, please **skip step 2.b**.

       * E.g. ``BusinessProject`` depends on ``CustomizedPortal``

  3. Change the ``DefaultPages`` in ``StandardProcesses`` to your customized
     portal project library id. For details, refer to :dev-url:`Standard Processes
     </doc/|version|/engine-guide/deployment/advanced/index.html>`.

     - E.g: DefaultPages: ``CustomizedPortal`` ID.

Now you can develop your own processes inside the ``BusinessProject`` and the dialogs will be rendered automatically using IFrames.

.. important:: 
    We have to  create two projects: ``CustomizedPortal`` and ``BusinessProject``. 
    Create your process start in ``BusinessProject``, not in ``CustomizedPortal``.

    Because you copied ``PortalStart.p.json``, this contains the
    ``DefaultFramePage.ivp`` start. So every process start which is in the same
    **PMV** as the ``DefaultFramePage.ivp`` is not opened in the IFrame, to
    avoid recursion. This means that the IFrame Dialog itself is **not** opened
    again in an IFrame and so on.


.. |task-embedInFrame| image:: images/task-embedInFrame.png
.. |case-embedInFrame| image:: images/case-embedInFrame.png

configuration Parameters
--------------------

Inside IFrame, you can configure these parameters as follows; they will be rendered by the template automatically:

::

   <script>
      // Follow one of these formats to set process steps:
      window.processSteps = ["Create Investment Request", "Approve Investment Request"];
      window.processSteps = "Create Investment Request,Approve Investment Request";

      // If process steps are set in HTML dialog logic or Java code, convert it to one of above formats by jstl (following code) or Java code
      // Include this namespace xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions" to the "html" tag
      // Use this code if process steps are a Java String list
      window.processSteps = "#{fn:join(data.steps.toArray(), ',')}";
      // Use this code if process steps are a Java String array
      window.processSteps = "#{fn:join(data.steps, ',')}";

      // Current process step could be a number or String:
      window.currentProcessStep = 0;
      window.currentProcessStep = #{data.currentProcessStep};
      window.currentProcessStep = "#{data.currentProcessStep}";
      window.isShowAllSteps = true;
      window.isHideTaskName= false;
      window.isHideTaskAction = false;
      window.isHideCaseInfo = false;
      window.isWorkingOnATask = false;
      window.processChainDirection = "VERTICAL";
      window.processChainShape = "LINE";
      window.announcementInvisible = false;
      window.viewName = "TASK_DETAIL";
      window.taskName = "Your New Task Name";

      // Use this code to show case details of a case different from current case of working task.
      // by send the case ID of that case.
      window.caseId = 123456;

      // Display content of the IFrame inside a card style, true or false (default)
      window.isCardFrame = true;
   </script>

.. note::

       When you define parameter processSteps, please make sure that you add the jsp function tag to your XHTML file:
       ``xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions``

In case your project has a navigation button that does not complete a task, e.g Cancel, to

-  One of the default pages (application home, task list, process list, etc.): in your HTMLDialog, redirect to the page you want to display.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrameAPI``.
-  A specific URL: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrameAPI``.

Responsiveness
--------------

Since version 8.0, Portal has a simplified ResponsiveToolKit. Now, the Portal
supports various screen resolutions, not just the fixed three screen widths as before.

To apply your styles for the specific resolution, you can add your own
media query CSS:

.. code-block:: css

    @media screen and (max-width: 1365px) {/*.....*/}

In the Portal's new design, the width of the main container should be changed
according to menu state (expand/collapse).

To adapt to the change, you need to initialize the ``ResponsiveToolkit``
JavaScript object and introduce one object to handle screen resolutions.
Your object has to implement the ``updateMainContainer`` method.

Portal templates define their own responsiveness, you can redefine the
footer section to override:

E.g. Initialize ``ResponsiveToolkit`` for a TaskList page.

.. code-block:: html

      <ui:define name="footer">
      <script type="text/javascript">
      $(function(){
      var simpleScreen = new TaskListScreenHandler();
      var responsiveToolkit = ResponsiveToolkit(simpleScreen);
      Portal.init(responsiveToolkit);
      });
      </script>
      </ui:define>

.. |basic-template| image:: ../../screenshots/layout-template/basic-template.png
.. |case-list-template| image:: ../../screenshots/case/case-key-information.png
.. |portal-header| image:: ../../screenshots/settings/user-settings.png
.. |task-list-template| image:: ../../screenshots/task/task-key-information.png
.. |task-name-template| image:: ../../screenshots/layout-template/task-template.png