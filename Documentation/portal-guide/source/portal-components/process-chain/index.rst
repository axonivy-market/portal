.. _components-portal-components-process-chain:

Process Chain
*************

.. _components-portal-components-process-chain-introduction:

Introduction
^^^^^^^^^^^^

The Process Chain component provides status information for all steps in a
process: the currently executable steps, done steps, and open steps. Its features are:

#. Display all currently executable steps, or display only helpful steps like
   begin, last, current, previous, and next steps.

#. Change the shape of the process chain: circle or line.

#. Change the orientation of the process chain: horizontal or vertical.

|process-chain|

.. _components-portal-components-process-chain-how-to-use:

How to Use
^^^^^^^^^^

You can integrate the Process Chain component in any widget by including
the component on a page with following code:

.. code-block:: html

		<ic:com.axonivy.portal.components.ProcessChain id="process-chain-circle-horizontal" componentId="component-circle-horizontal" shape="CIRCLE" direction="HORIZONTAL"
         isShowAllSteps="false" actualStepIndex="0" steps="#{['Step 1','Step 2','Step 3','Step 4','Step 5','Step 6','Step 7','Step 8','Step 9']}" />

#. You have to set the parameters

   -  ``actualStepIndex``. This is the index of the current step.
   -  ``steps``. This is list of working steps.

#. You may change the parameters

   -  ``shape`` to ``CIRCLE`` or ``LINE`` according to your requirements. Default is ``CIRCLE``.
   -  ``direction`` to ``HORIZONTAL`` or ``VERTICAL`` . Default is ``HORIZONTAL``.
   -  ``isShowAllSteps`` to ``TRUE`` or ``FALSE`` . Default is ``FALSE``.

.. csv-table::
  :file: ../documents/process_chain_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3


If you want to customize its style,
please refer to :ref:`components-portal-components-style-customization`.

.. _components-portal-components-migrate-from-old-process-chain:

Migrate from Deprecated Process Chain
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Replace code in HTML files: replace ``ch.ivy.addon.portalkit.singleapp.process.ProcessChain`` with ``com.axonivy.portal.components.ProcessChain``.

.. |process-chain| image:: ../../screenshots/components/process-chain.png