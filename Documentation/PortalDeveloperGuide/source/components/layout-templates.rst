.. _components-layout-templates:

Layout templates
================

.. _components-layout-templates-templates-for-development:

Templates for development
-------------------------

Your Portal Project is dependent on PortalTemplate project, in which
there are 7 templates that can be used directly.

1. :ref:`Basic template <components-layout-templates-basic-template>`

2. :ref:`Two column template <components-layout-templates-two-column-template>`

3. :ref:`Task template <components-layout-templates-task-template>`

4. :ref:`Task list template <components-layout-templates-task-list-template>`

5. :ref:`Case list template <components-layout-templates-case-list-template>`

6. :ref:`Default homepage template <components-layout-templates-default-homepage-template>`

7. :ref:`Deprecated task template 7 <components-layout-templates-deprecated-task-template-7>`

8. :ref:`Task template 8 <components-layout-templates-task-template-8>`

These templates have the same header, which is a menu of applications
that you configure in Administration page. Since version 8.0, Portal
officially supports responsiveness, every templates has its default
responsiveness, you can refer to
:ref:`Responsiveness <components-layout-templates-reponsiveness>`
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
-------------------------

1. Create a new HTML User Dialog and then use ``ui:composition`` to
   define the template inside and reuse the default responsiveness
   behavior. To override it, please use ``pageContent`` instead of
   ``simplePageContent`` and
   :ref:`Responsiveness <components-layout-templates-reponsiveness>`.

   .. code-block:: html
   
      <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="title">Sample Page</ui:define>
      <ui:define name="simplePageContent">
      This is sample content.
      </ui:define>
      </ui:composition>

2. See the result after using Basic template for example:

  |basic-template|

.. _components-layout-templates-two-column-template:

Two column template
-------------------

Two column template inherits Basic Template. It has 2 columns which user
can customize their contents. Normally, the first column is for
navigation, the second for displaying corresponding content.

.. _components-layout-templates-two-column-template-how-to-use-two-columntemplate:

How to use Two column template
------------------------------

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

.. _components-layout-templates-task-template:

Task template
-------------

Task template is used for displaying task functionality and related
information to support completing the task. There are a lot of regions
to be filled with your custom content:

-  Header name (task name)

-  Process chain

-  Content

-  Case Details

This template uses iFrame, so all Modena css styles need to be provided in your project since Portal now use Serenity.

.. _components-layout-templates-task-template-how-to-use-task-template:

How to use template TaskTemplate.xhtml
--------------------------------------

1.  Create a new HTML User Dialog and then use ``ui:composition`` to
    define template which you use inside.

  .. code-block:: html
  
     <ui:composition template="/layouts/TaskTemplate.xhtml">


2.  Refer to ``TaskTemplate.xhtml`` for params and template areas.

  |task-name-template|

In case your project has navigation button like e.g Cancel, if you need this button to navigate 

-  To home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorInFrame`` in ``PortalTemplate``.
-  To previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrame`` in ``PortalTemplate``.

.. _components-layout-templates-task-list-template:

Task list template
------------------

Task list template is used to display task list where user can see tasks
and their details.

|task-list-template|

.. _components-layout-templates-task-list-template-how-to-use-task-list-template:

How to use task list template
-----------------------------

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
-----------------------------

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

Handle required Login in templates
----------------------------------

All templates require login to access by default. But templates also
provide functionality to access page without login by adding the
``isNotRequiredLogin`` parameter.

.. _components-layout-templates-handle-required-login-in-templates-how-to-handle-required-login-in-template:

How to handle required login in template
----------------------------------------

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
------------------------------------

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">

..    

.. _components-layout-templates-deprecated-task-template-7:

DeprecatedTaskTemplate-7
-------------------------

Deprecated task template 7 is old task template with Serenity theme. If your project want to apply new theme, you can change to this template. This template doesn’t use iFrame.

.. _components-layout-templates-default-homepage-template-how-to-use-deprecated-task-template-7:

How to use DeprecatedTaskTemplate-7
------------------------------------

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

      <ui:composition template="/layouts/DeprecatedTaskTemplate-7.xhtml">

..    

This template doesn't use iFrame. You need to set case custom string field ``embedInFrame`` to ``false`` or configure no iFrame multi applications.

In case your project has navigation button like e.g Cancel, if you need this button to navigate 

-  To home page: call ``navigateToPortalHome()`` from class ``PortalNavigator`` in ``PortalTemplate``.
-  To previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigator`` in ``PortalTemplate``.

.. _components-layout-templates-task-template-8:

TaskTemplate-8
-------------------------

Task template 8 is new template with Serenity theme introduced since Portal 8. In this template there is no tab view, you have to define it. This template doesn't use iFrame.

.. _components-layout-templates-default-homepage-template-how-to-use-task-template-8:

How to use TaskTemplate-8
------------------------------------

Create a new HTML User Dialog and then use ``ui:composition`` to define
template.

.. code-block:: html

      <ui:composition template="/layouts/TaskTemplate-8.xhtml">

..    

This template doesn't use iFrame. You need to set case custom string field ``embedInFrame`` to ``false`` or configure no iFrame multi applications.

In case your project has navigation button like e.g Cancel, if you need this button to navigate 

-  To home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorInFrame`` in ``PortalTemplate``.
-  To previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrame`` in ``PortalTemplate``.

.. _components-layout-templates-reponsiveness:

Responsiveness
--------------

Since version 8.0, Portal has simplified ResponsiveToolKit and now
Portal supports various screen solutions, not fit to 3 screen widths as
before.

To apply your styles for the specific resolution, you can add your own
media query css:

.. code-block:: css

    @media screen and (max-width: 1365px) {/*.....*/}

In Portal's new design, the main container's width should be changed
according to menu state (expand/colapse).

To adapt the change, you need to initialize the ``ResponsiveToolkit``
Javascript object and introduce 1 object to handle screen resolutions
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
.. |task-template-case-info| image:: images/layout-templates/task-template-case-info.png
.. |task-template-process-chain| image:: images/layout-templates/task-template-process-chain.png
.. |task-template-task-form| image:: images/layout-templates/task-template-task-form.png
.. |two-column-template| image:: images/layout-templates/two-column-template.png


