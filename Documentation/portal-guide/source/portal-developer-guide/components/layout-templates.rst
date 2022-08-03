.. _components-layout-templates:

Layout Templates
================

.. _components-layout-templates-templates-for-development:

Templates for Development
-------------------------

Your Portal project depends on the PortalTemplate project, which offers several templates that can be used directly withoutIFrames.
Additionally, two templates are described to be used with IFrames.

Templates without IFrames:

#. :ref:`Basic template <components-layout-templates-basic-template>`

#. :ref:`Task template 8 <components-layout-templates-task-template-8>`

#. :ref:`Task list template <components-layout-templates-task-list-template>`

#. :ref:`Case list template <components-layout-templates-case-list-template>`

#. :ref:`Default homepage template <components-layout-templates-default-homepage-template>`

Templates to use with IFrame:

#. :ref:`IFrame Task template <components-layout-templates-iframe-task-template>` (Template used internally by Portal to configure IFrames)

#. frame-8 template (Provided by core, use Serenity theme)

#. frame-10 template (Provided by core, use Freya theme)

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

      For example, if you want to use the customize Serenity layout in Portal, add the code below:
      ``<h:outputStylesheet library="css" name="template.css" />``

|portal-header|

.. _components-layout-templates-basic-template:

Basic Template
--------------

The Basic template provides a basic layout so you as a developer can put your
custom contents. It lacks the Portal menu and Case details. We recommend to use
the task template for your process.

.. _components-layout-templates-basic-template-how-to-use-basic-template:

How to Use Basic Template
^^^^^^^^^^^^^^^^^^^^^^^^^

#. Create a new HTML User Dialog and then use ``ui:composition`` to define the
   template inside and reuse the default responsive behavior. Define the
   ``pageContent`` section and :ref:`Responsiveness
   <components-layout-templates-responsiveness>`.

   .. code-block:: html

      <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="title">Sample Page</ui:define>
      <ui:define name="pageContent">
      This is sample content.
      </ui:define>
      </ui:composition>

#. See the result after using the Basic template, e.g.:

  |basic-template|

.. _components-layout-templates-iframe-task-template:

IFrame Task Template
--------------------

The IFrame Task Template is used to display task functionality (e.g. the process chain) and related case information to help complete the task.
It renders your task UI inside an IFrame (refer to :ref:`IFrame in Portal <iframe-in-portal>`).

The template expects to receive some parameters passed by JavaScript.
Inside your UI, you can configure these parameters as follows; they will be rendered by the template automatically:

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

      // Use this code to show case details of a case different from current case of working task.
      // by send the case ID of that case.
      window.caseId = 123456;
   </script>

.. note::
       Do not use or refer to this template in your Dialog. This will be done automatically by Portal if you use our IFrame Approach.
       Refer to IFrameTaskTemplate.xhtml in PortalTemplate to see detailed information about how to use and pass parameters.

       When you define parameter processSteps, please make sure that you add the jsp function tag to your XHTML file:
       ``xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions``

In case your project has a navigation button that does not complete a task, e.g Cancel, to

-  One of the default pages (application home, task list, process list, etc.): in your HTMLDialog, redirect to the page you want to display.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrameAPI``.
-  A specific URL: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrameAPI``.

.. _components-layout-templates-task-template-8:

TaskTemplate-8
--------------

Task Template 8 is a new template using the Serenity theme introduced in Portal 8.
There is no TabView, you have to define that if needed.

.. warning::
	Portal Styles are included. Therefore, your HTML dialogs are also affected. You might have some migration efforts in a future release.
	We recommend to use :ref:`IFrame in Portal <iframe-in-portal>`.

.. important::
	This template must not be used inside an iFrame.

.. _components-layout-templates-task-template-how-to-use-task-template-8:

How to use TaskTemplate-8
^^^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to define the
template.

.. code-block:: html

  <ui:composition template="/layouts/TaskTemplate-8.xhtml">

Refer to ``TaskTemplate-8.xhtml`` for parameters and template areas.

In case your project has a navigation button that does not complete a task, e.g Cancel, to redirect to

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorAPI``.

How to migrate TaskTemplate-8
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you migrate your Portal from previous versions and use ``<ui:define name="taskForm" />`` to define the content inside the Request tab,
TabView is removed and ``<ui:define name="taskForm" />`` is DEPRECATED, use ``<ui:define name="content" />`` instead.

``<ui:define name="dynamicTabs" />`` is removed, design your TabView if needed.

Refer to ``TaskTemplate-8.xhtml`` for parameters and template areas.

+----------------------------------------+-------------------------------+
| Pros                                   | Cons                          |
+========================================+===============================+
| - Use Serenity theme                   | - Some migration effort       |
| - Usage improvements are considered    |                               |
| - Consistent look and feel with Portal |                               |
| - UI styles are taken over from Portal |                               |
+----------------------------------------+-------------------------------+

.. _components-layout-templates-task-list-template:

External Case Information
^^^^^^^^^^^^^^^^^^^^^^^^^

By default, TaskTemplate-8 will show business case details of the working task in the Case Information dialog.
But you can modify it to show details of another case instead by using parameter ``caseId``.
The parameter ``caseId`` only accepts case ID of an existing case. If Portal cannot find the case it will show
business case details of the working task instead. Please refer to the example below.

.. code-block:: html

   <ui:param name="caseId" value="123456" />

Task List Template
------------------

Task list template is used to display a task list so the end user can see tasks
and their details.

|task-list-template|

.. _components-layout-templates-task-list-template-how-to-use-task-list-template:

How to Use Task List Template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Create a new HTML User Dialog and then use ``ui:composition`` to
   define the template.

   .. code-block:: html

      <ui:composition template="/layouts/PortalTasksTemplate.xhtml">
      </ui:composition>

#. The data class of this dialog should have an attribute named ``taskView``
   with type ``ch.ivy.addon.portal.generic.view.TaskView``. By changing
   this attribute, you can modify the title of the task list widget,
   which tasks are collected (through ``dataModel``) and more. The following is a
   sample to build a taskView.

   .. code-block:: java

      import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
      import ch.ivy.addon.portal.generic.view.TaskView;
      TaskLazyDataModel dataModel = new TaskLazyDataModel();
      dataModel.setAdminQuery(true);
      dataModel.setSortField(ch.ivy.addon.portalkit.enums.TaskSortField.PRIORITY.toString(), true);
      category.setValue("My Task List");
      out.taskView = TaskView.create().dataModel(dataModel).pageTitle("My Task List").hideTaskFilter(true)
      .showHeaderToolbar(false).createNewTaskView();

.. _components-layout-templates-case-list-template:

Case List Template
------------------

The Case list template is used to display a case list with the end user's cases
and their details.

|case-list-template|

.. _components-layout-templates-case-list-template-how-to-use-case-list-template:

How To Use Case List Template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Create a new HTML User Dialog and then use ``ui:composition`` to
   define the template.

   .. code-block:: html

     <ui:composition template="/layouts/PortalCasesTemplate.xhtml>
     </ui:composition>

#. The data class of this dialog should have an attribute named ``caseView``
   with type ``ch.ivy.addon.portal.generic.view.CaseView``. By changing
   this attribute, you can modify the title of the case list widget,
   the cases collected (through ``dataModel``) and more. The following is an
   example to build a caseView.

   .. code-block:: java

      import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
      import ch.ivy.addon.portal.generic.view.CaseView;
      CaseLazyDataModel dataModel = new CaseLazyDataModel();
      out.caseView = CaseView.create().dataModel(dataModel).withTitle("My Cases").buildNewView();

.. _components-layout-templates-handle-required-login-in-templates:

Handle Required Login In Templates
----------------------------------

All templates require login to access by default. But templates also allow to
access the page without login by adding the ``isNotRequiredLogin`` parameter.

.. _components-layout-templates-handle-required-login-in-templates-how-to-handle-required-login-in-template:

How To Handle Required Login In Template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Create a new **HTML User Dialog** and then use ``ui:param`` to define
   the template inside

   .. code-block:: html

     <ui:composition template="/layouts/BasicTemplate.xhtml">
     <ui:param name="isNotRequiredLogin" value="#{data.isNotRequiredLogin}" />
     <ui:define name="pageContent">
     This is sample content.
     </ui:define>
     </ui:composition>

#. The result of using the above template (All user settings and
   application menus will not visible).


.. _components-layout-templates-default-homepage-template:

Default Homepage Template
-------------------------

The Default Homepage template is used to create pages that look like the default
homepage of the Portal. You can customize it by disabling the default widgets,
adding new widgets, and changing the position of widgets. For details
including basic and advanced customization, refer to :ref:`Portal home
<customization-portal-home>`

.. _components-layout-templates-default-homepage-template-how-to-use-default-homepage-template:

How To Use The Default Homepage Template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to define the
template.

.. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">

..

.. _components-layout-templates-responsiveness:

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



