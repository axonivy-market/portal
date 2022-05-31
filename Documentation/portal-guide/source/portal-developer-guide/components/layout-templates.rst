.. _components-layout-templates:

Layout templates
================

.. _components-layout-templates-templates-for-development:

Templates for development
-------------------------

Your Portal project is dependent on PortalTemplate project, in which there are 7 templates that can be used directly without IFrame. 
In addition 3 templates are described to be used with IFrame.

Templates without IFrame:

1. :ref:`Basic template <components-layout-templates-basic-template>`

2. :ref:`Task template 8 <components-layout-templates-task-template-8>`

3. :ref:`Deprecated task template 7 (Deprecated) <components-layout-templates-task-template-7>`

4. :ref:`Two column template (Deprecated) <components-layout-templates-two-column-template>`

5. :ref:`Task list template <components-layout-templates-task-list-template>`

6. :ref:`Case list template <components-layout-templates-case-list-template>`

7. :ref:`Default homepage template <components-layout-templates-default-homepage-template>`

Templates to use with IFrame:

1. :ref:`IFrame Task template <components-layout-templates-iframe-task-template>` (Template used internally by portal to configure IFrame)

2. :ref:`Task template (Deprecated) <components-layout-templates-task-template>`

3. frame-8 template (Provided by core, use Serenity theme)

These templates have the same header, which is a menu of applications
that you configure in Administration page. Since version 8.0, Portal
officially supports responsiveness, every templates has its default
responsiveness, you can refer to
:ref:`Responsiveness <components-layout-templates-responsiveness>`
to override it. Besides, there are user settings like: Absences, Email,
Language Settings and Administration (for admin only). Details about
user settings can be found in
:ref:`Settings <settings>`.

|portal-header|

.. _components-layout-templates-basic-template:

Basic template
--------------

Basic template provides basic layout where user can put their custom
content. It lacks Portal menu and Case details. We recommend to use task
template for your process.

.. _components-layout-templates-basic-template-how-to-use-basic-template:

How to use Basic template
^^^^^^^^^^^^^^^^^^^^^^^^^

1. Create a new HTML User Dialog and then use ``ui:composition`` to
   define the template inside and reuse the default responsiveness
   behavior. To override it, please use ``pageContent`` instead of
   ``simplePageContent`` and
   :ref:`Responsiveness <components-layout-templates-responsiveness>`.

   .. code-block:: html
   
      <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="title">Sample Page</ui:define>
      <ui:define name="simplePageContent">
      This is sample content.
      </ui:define>
      </ui:composition>

2. See the result after using Basic template for example:

  |basic-template|

.. _components-layout-templates-iframe-task-template:

IFrame Task template
--------------------

IFrame Task Template is used for displaying task functionality (e.g. process chain) and related case information to support completing the task. 
It renders your task UI inside IFrame (refer to :ref:`IFrame in Portal <iframe-in-portal>`).

The template is prepared to receive some params passed by JavaScript. 
Inside your UI, you can configure these parameters which will be rendered by the template automatically:

::

	<script>
		// Follow one of these formats to set process steps:
		window.processSteps = ["Create Investment Request", "Approve Investment Request"];
		window.processSteps = "Create Investment Request,Approve Investment Request";

		// If process steps are set in HTML dialog logic or java code, convert it to one of above formats by jstl (following code) or java code
		// Include this namespace xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions" to the "html" tag
		// Use this code if process steps are a java String list
		window.processSteps = "#{fn:join(data.steps.toArray(), ',')}";
		// Use this code if process steps are a java String array
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

		// Use this code to show case details of a case different from current case of working task.
		// by send the case ID of that case.
		window.caseId = 123456;
	</script>

.. note::
      Do not use or refer to this template in your Dialog. This will be done automatically by Portal if you use the mentioned IFrame Approach.
      You can refer to IFrameTaskTemplate.xhtml in PortalTemplate to see more detailed information about how to use and pass params.

      When define param processSteps, please make sure that you added jsp function tag to your XHTML file:
      ``xmlns:fn="http://xmlns.jcp.org/jsp/jstl/functions``
	
In case your project has navigation button without finishing a task, e.g Cancel, to 

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorInFrameAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrameAPI``.
-  A specific url: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrameAPI``.

.. _components-layout-templates-task-template:

Task template (Deprecated)
--------------------------

Task template is used for displaying task functionality and related
information to support completing the task. There are a lot of regions
to be filled with your custom content

This template is dedicated for iFrame and Modena theme, so all Modena styles need to be provided in your project.

.. warning::
	There should be no encouragement to stay on modena - unless you want to follow a dead path on purpose.

.. important::
	This template must be used inside IFrame. Refer to :ref:`IFrame in Portal <iframe-in-portal>`.

.. _components-layout-templates-task-template-how-to-use-task-template:

How to use TaskTemplate
^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to  define template which you use inside.

  .. code-block:: html
  
     <ui:composition template="/layouts/TaskTemplate.xhtml">

  |task-name-template|

In case your project has navigation button without finishing a task, e.g Cancel, to 

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorInFrameAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrameAPI``.
-  A specific url: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrameAPI``.

.. _components-layout-templates-task-template-how-to-migrate-task-template:

How to migrate TaskTemplate
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Your style customization in PortalStyle hasn't effected anymore, you should include it yourself in your HTML dialog

+----------------------------------------------+------------------------------------------------------+
| Pros                                         | Cons                                                 |
+==============================================+======================================================+
|  - Keep the old look&feel since Portal 7     |  - Modena is out of support by Primefaces            |
|  - Less migration effort                     |  - Modena was highly customized by Portal            |
|                                              |  - Different look&feel between Portal and UIs        |
+----------------------------------------------+------------------------------------------------------+

.. _components-layout-templates-task-template-8:

TaskTemplate-8
--------------

Task template 8 is new template with Serenity theme introduced since Portal 8. 
There is no TabView, you have to define it if needed.

.. warning::
	Portal styles are included, your HTML dialogs are also effected. Therefore, it could spend some migration effort in future.
	It's highly recommended to use :ref:`IFrame in Portal <iframe-in-portal>`.
	
.. important::
	This template must not be used inside IFrame.

.. _components-layout-templates-task-template-how-to-use-task-template-8:

How to use TaskTemplate-8
^^^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

  <ui:composition template="/layouts/TaskTemplate-8.xhtml">

Refer to ``TaskTemplate-8.xhtml`` for params and template areas.

In case your project has navigation button without finishing a task, e.g Cancel, to 

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorAPI``.

How to migrate TaskTemplate-8
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you migrate Portal since previous versions to TaskTemplate-8, please follow below steps to migrate your content:

- The ``<ui:define name="taskForm" />`` to define the content inside the Request tab, TabView is removed and ``<ui:define name="taskForm" />`` is DEPRECATED, use ``<ui:define name="content" />`` instead.

- The ``<ui:define name="dynamicTabs" />`` is removed, design your TabView if needed.

Refer to ``TaskTemplate-8.xhtml`` for params and template areas.

+----------------------------------------+-------------------------------+
| Pros                                   | Cons                          |
+========================================+===============================+
| - Use Serenity theme                   | - Some migration effort       |
| - Usage improvements are considered    |                               |
| - Consistent look&feel with Portal     |                               |
| - UI styles are taken over from Portal |                               |
+----------------------------------------+-------------------------------+

External case information
^^^^^^^^^^^^^^^^^^^^^^^^^

By default, TaskTemplate-8 will show business case details of the working task in the Case Information dialog.
But you can modify it to show details of another case instead by using parameter ``caseId``.
The parameter ``caseId`` only accept case ID of an existing case, if Portal cannot find the case it will show
business case details of the working task instead. Please refer to example below.

.. code-block:: html

   <ui:param name="caseId" value="123456" /> 

.. _components-layout-templates-task-template-7:

TaskTemplate-7 (Deprecated)
---------------------------

Task template 7 is old task template with Serenity theme. If your project wants to apply new theme, 
but keep the TabView (e.g. Case Information, dynamic tabs), you can use this template.

.. warning::
	Portal styles are included, your HTML dialogs are also effected. Therefore, it could spend some migration effort in future.
	It's highly recommended to use :ref:`IFrame in Portal <iframe-in-portal>`.
	
.. important::
	This template must not be used inside IFrame.

.. _components-layout-templates-task-template-how-to-use-task-template-7:

How to use TaskTemplate-7
^^^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

      <ui:composition template="/layouts/TaskTemplate-7.xhtml">

In case your project has navigation button without finishing a task, e.g Cancel, to 

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorAPI``.

How to migrate TaskTemplate-7
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Adapt the ``ui:composition`` template to this template.

+--------------------------------------------+------------------------------------------------+
| Pros                                       | Cons                                           |
+============================================+================================================+
| - Keep the old structure for UIs (TabView) | - Template is deprecated (will remove in v9.0) |
| - Use Serenity theme from Portal           | - Few migration effort                         |
| - Consistent look&feel with Portal         |                                                |
| - UI styles are taken over from Portal     |                                                |
+--------------------------------------------+------------------------------------------------+

.. _components-layout-templates-two-column-template:

Two column template (Deprecated)
--------------------------------

Two column template inherits Basic Template. It has 2 columns which user
can customize their contents. Normally, the first column is for
navigation, the second for displaying corresponding content.

.. _components-layout-templates-two-column-template-how-to-use-two-columntemplate:

How to use Two column template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Create a HTML User Dialog, define template in ``ui:composition`` and
   insert content of second column and third column using ``ui:define``.

  .. code-block:: html
     :linenos:
     :emphasize-lines: 4,7

     <ui:composition template="/layouts/TwoColumnTemplate.xhtml">
     <ui:define name="title">Sample Page</ui:define>
     <ui:define name="navigationRegion">
     Navigation Region
     </ui:define>
     <ui:define name="contentRegion">
     Content Region
     </ui:define>
     </ui:composition>

2. See the result after using Two column template for example:

  |two-column-template|

.. _components-layout-templates-task-list-template:

Task list template
------------------

Task list template is used to display task list where user can see tasks
and their details.

|task-list-template|

.. _components-layout-templates-task-list-template-how-to-use-task-list-template:

How to use task list template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Create a new HTML User Dialog and then use ``ui:composition`` to
   define template.

  .. code-block:: html
  
      <ui:composition template="/layouts/PortalTasksTemplate.xhtml">
      </ui:composition>

2. Data class of this dialog should have an attribute named ``taskView``
   with type ``ch.ivy.addon.portal.generic.view.TaskView``. By changing
   this attribute, user can modify title of the task list widget,
   collected tasks (through ``dataModel``) and more. The following is a
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

Case list template
------------------

Case list template is used to display case list where user can see cases
and their details.

|case-list-template|

.. _components-layout-templates-case-list-template-how-to-use-case-list-template:

How to use case list template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Create a new HTML User Dialog and then use ``ui:composition`` to
   define template.

  .. code-block:: html
 
     <ui:composition template="/layouts/PortalCasesTemplate.xhtml>
     </ui:composition>

2. Data class of this dialog should have an attribute named ``caseView``
   with type ``ch.ivy.addon.portal.generic.view.CaseView``. By changing
   this attribute, user can modify title of the case list widget,
   collected cases (through ``dataModel``) and more. The following is an
   example to build a caseView.

  .. code-block:: java
  
      import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
      import ch.ivy.addon.portal.generic.view.CaseView;
      CaseLazyDataModel dataModel = new CaseLazyDataModel();  
      out.caseView = CaseView.create().dataModel(dataModel).withTitle("My Cases").buildNewView();

.. _components-layout-templates-handle-required-login-in-templates:

Handle required login in templates
----------------------------------

All templates require login to access by default. But templates also
provide functionality to access page without login by adding the
``isNotRequiredLogin`` parameter.

.. _components-layout-templates-handle-required-login-in-templates-how-to-handle-required-login-in-template:

How to handle required login in template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Create a new **HTML User Dialog** and then use ``ui:param`` to define
   the template inside

  .. code-block:: html
  
     <ui:composition template="/layouts/BasicTemplate.xhtml">
     <ui:param name="isNotRequiredLogin" value="#{data.isNotRequiredLogin}" />
     <ui:define name="pageContent">
     This is sample content.
     </ui:define>
     </ui:composition>

2. Result after using template for example (All user settings and
   application menus will not visible).


.. _components-layout-templates-default-homepage-template:

Default homepage template
-------------------------

Default homepage template is used to create pages that have the look as
default homepage of Portal. Besides, users can customize it by disabling
default widgets, add new widgets, change position of widgets. For more
details including basic and advanced customization, refer to
:ref:`Portal home <customization-portal-home>`

.. _components-layout-templates-default-homepage-template-how-to-use-default-homepage-template:

How to use default homepage template
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">

..    

.. _components-layout-templates-responsiveness:

Responsiveness
--------------

Since version 8.0, Portal has simplified ResponsiveToolKit and now
Portal supports various screen resolution, not fit 3 screen widths as
before.

To apply your styles for the specific resolution, you can add your own
media query css:

.. code-block:: css

    @media screen and (max-width: 1365px) {/*.....*/}

In Portal's new design, the main container's width should be changed
according to menu state (expand/colapse).

To adapt the change, you need to initialize the ``ResponsiveToolkit``
JavaScript object and introduce 1 object to handle screen resolutions
and your object has to implement the ``updateMainContainer`` method.

Portal templates define their own responsiveness, you can redefine the
footer section to override:

E.g. Initialize ``ResponsiveToolkit`` for TaskList page.

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

.. |basic-template| image:: images/layout-templates/basic-template.png
.. |case-list-template| image:: images/layout-templates/case-list-template.png
.. |portal-header| image:: images/layout-templates/portal-header.png
.. |process-chain-shape| image:: images/layout-templates/process-chain-shape.png
.. |task-list-template| image:: images/layout-templates/task-list-template.png
.. |task-name-template| image:: images/layout-templates/task-name-template.png
.. |two-column-template| image:: images/layout-templates/two-column-template.png


