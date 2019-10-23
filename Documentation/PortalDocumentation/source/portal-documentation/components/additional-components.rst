.. _axonivyportal.components.additionalcomponent:

Additional Components
=====================

.. _axonivyportal.components.additionalcomponent.processhistory:

Process history
---------------

.. _axonivyportal.components.additionalcomponent.processhistory.introduction:

Introduction
~~~~~~~~~~~~

This component is a lazy loading list which displays all business cases
of a business entity in your application. You can include this component
everywhere:

In a page

|image0|

In a dialog

|image1|

.. _axonivyportal.components.additionalcomponent.processhistory.howtouse:

How to use
~~~~~~~~~~

First you need to link the cases to the business entity. Call the
subprocess ``SetBusinessEntityId`` in the process which need to be
linked and input an identifier unique to your business entity. The
subprocess will set the id to the additional property
"CASE_BUSINESS_ENTITY_PROPERTY" of the business case.

|image2|

Include the process history component into your page:

``<ic:ch.ivy.addon.portal.component.ProcessHistory businessEntityId="resourceA247" >``

The value of the attribute ``businessEntityId`` must match the id input
into the subprocess in the first step.

By default the component will load 20 cases at a time. You can change
this by setting the attribute ``chunkSize`` to the number you want . You
should use this attribute alongside with the attribute ``scrollHeight``
to configure the scroll bar of the list.

   .. note:: 

      If you use this component in a dialog, you must run this script
      ``processHistory.setup();`` when the dialog is shown. For example:
      ``<p:dialog widgetVar="process-history-dialog"``
      ``id="process-history-dialog" width="800" height="500"``
      ``header="Process history of Resource A247"``
      ``onShow="processHistory.setup();">``
      ``<ic:ch.ivy.addon.portal.component.ProcessHistory``
      ``businessEntityId="resourceA247"``
      ``chunkSize="6" scrollHeight="400" />``
      ``</p:dialog>``

..

   .. important:: 
   
      If your process has a Trigger component or sends a signal to start
      another process with the option "Attach to Business Case that
      triggered this process" selected, the current case of the process
      will become a technical case and will not be loaded into the process
      history list. In this case You need to call the
      SetBusinessEntityId
      subprocess after the first Trigger or signal sending step.

.. _axonivyportal.components.additionalcomponent.taskanalysis:

Task Analysis
-------------

.. _axonivyportal.components.additionalcomponent.taskanalysis.introduction:

Introduction
~~~~~~~~~~~~

Task Analysis component of Portal provides features for users to analyze
not only tasks but also cases. These features are:

1. Set of filters for both tasks and cases which allow user to filter
   and to find tasks, cases more better. More, user can create and
   manage their own filter set for future usage.

2. Dynamic result table with lots of information for both task and case.

3. Support export result as Excel files (currently we only support .xlsx
   extension).

|image3|

.. _axonivyportal.components.additionalcomponent.taskanalysis.howtouse:

How to use
~~~~~~~~~~

Task Analysis component is integrated into Statistic widget. You can use
this component directly when open Statistic widget. If you want to use
this component, you only have to redirect to Task Analysis component
with following code:

``import javax.faces.context.FacesContext;``

``String taskAnalysisUrl = ivy.html.startref("Start Processes/TaskAnalysis/start.ivp");``

``FacesContext.getCurrentInstance().getExternalContext().redirect(taskAnalysisUrl);``

.. _axonivyportal.components.additionalcomponent.processchain:

Process Chain
-------------

.. _axonivyportal.components.additionalcomponent.processchain.introduction:

Introduction
~~~~~~~~~~~~

Process Chain component of Portal provides features for users to know
status of all steps in a process: the step's working, these steps are
done, these steps is not done. These features are:

1. Support to display all working steps or display only helpful steps as
   begin, last, current, previous current, next current steps.

2. Support to change the sharp of process chain: circle or line.

3. Support to change direction of process chain: horizontal or vertical.

|image4|

.. _axonivyportal.components.additionalcomponent.processchain.howtouse:

How to use
~~~~~~~~~~

Process Chain component can be integrated in any widget by including
this component into a page. In order to use this component in a page,
include this component to this page with following code:

``<ic:ch.ivy.addon.portalkit.singleapp.process.ProcessChain id="process-chain-circle-horizontal"``

``componentId="component-circle-horizontal" shape="CIRCLE" direction="HORIZONTAL"``

``isShowAllSteps="FALSE" actualStepIndex="#{data.actualCurrentIndex}" steps="#{data.steps}" />``

1. Must to set value for ``actualStepIndex`` parameter. This is current
   step index.

2. Must to set value for ``steps`` parameter. This is list of working
   steps.

3. Can change ``shape`` parameter to ``CIRCLE`` or ``LINE`` based on the
   requirement. Default value of this is ``CIRCLE``.

4. Can change ``direction`` parameter to ``HORIZONTAL`` or ``VERTICAL``
   based on the requirement. Default value of this is ``HORIZONTAL``.

5. Can change ``isShowAllSteps`` parameter to ``TRUE`` or ``FALSE``
   based on the requirement. Default value of this is ``FALSE``.

.. _axonivyportal.components.additionalcomponent.globalgrowl:

Global growl
------------

.. _axonivyportal.components.additionalcomponent.globalgrowl.introduction:

Introduction
~~~~~~~~~~~~

This component is a global growl introduced in BasicTemplate, you can
use it to display your messages in Portal.

|image5|

|image6|

.. _axonivyportal.components.additionalcomponent.globalgrowl.growlafterfinishtask:

Display growl after finish task
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

After a task is finished, growl message appears as default via the
``DISPLAY_MESSAGE_AFTER_FINISH_TASK`` Portal variable.

For each task, you can turn it off or override it. Firstly, when you
submit form to finish task, you need to put the ``overridePortalGrowl``
key to flash object with any value

::

   Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
   flash.put("overridePortalGrowl", true);
   flash.setRedirect(true);

It's enough if you want to turn it off. To override the message, add
``facesMessage`` to this component

::

   import javax.faces.context.Flash;
   import javax.faces.context.FacesContext;
   import javax.faces.application.FacesMessage;

   FacesMessage message = new FacesMessage("Task is done successfully");
   FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

   Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
   flash.put("overridePortalGrowl", true);
   flash.setRedirect(true);
   flash.setKeepMessages(true);

.. _axonivyportal.components.additionalcomponent.documenttable:

Document table
--------------

.. _axonivyportal.components.additionalcomponent.documenttable.introduction:

Introduction
~~~~~~~~~~~~

This component is case document table with the features: upload,
download and delete.

|image7|

You can override the ``GetDocumentList``, ``UploadDocument``,
``DeleteDocument``, ``DownloadDocument`` sub processes to extend these
features, and add more columns, remove default columns in document
table. Refer to the ``DocumentTableComponent`` process in PortalExamples
project

.. |image0| image:: images/AdditionalComponent/processHistoryExample.png
.. |image1| image:: images/AdditionalComponent/processHistoryDialogExample.png
.. |image2| image:: images/AdditionalComponent/setBusinessEntityIdSubProcess.png
.. |image3| image:: images/AdditionalComponent/taskAnalysis.png
.. |image4| image:: images/AdditionalComponent/processChain.png
.. |image5| image:: images/AdditionalComponent/globalGrowl.png
.. |image6| image:: images/AdditionalComponent/exampleGlobalGrowl.png
.. |image7| image:: images/AdditionalComponent/documentTable.png

