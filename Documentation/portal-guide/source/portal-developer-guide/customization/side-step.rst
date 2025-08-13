.. _side-step:

Integrate a side step process
=============================

.. _side-step-introduction:

Introduction
------------

Side-step processes let you attach one or more auxiliary workflow steps to an existing business process in Axon Ivy Portal without touching the core flow. 
At runtime the Portal reads a JSON definition from the custom field ``sideStepTask`` (task scope) or ``sideStepCase`` (case scope), shows the available side steps in the task action menu and launches them either

- Synchronously - the main task pauses until the side step finishes, or

- Asynchronously - the main task continues while the side step runs in parallel.

The Portal delivers the framework (public APIs, DTOs and UI hooks); you design and own the actual side-step BPMN, dialogs, signals and business logic.
  
|side-step-menu|

Side step configuration dialog

|side-step-config|

Benefits and Outcomes
---------------------

- Low integration effort – add or remove auxiliary steps without modifying the main process model.

- Modular architecture – encourages clean separation between core and optional logic.

- Greater agility – quickly provide customer-specific extensions (e.g. extra approvals, data collection forms) with minimal risk.

- Parallel work – optional tasks can run concurrently, shortening throughput time.

- Custom UX – you control the side-step dialogs, result views and security (assignee or group callables).


How to Use and Set Up
---------------------

#. Model the side-step process

    - Create a process that performs the additional work (for example “Ask for more details”).
    - Define a signal that starts this process (e.g. ``com:axonivy:portal:developerexample:sideStep:askMoreDetails`` ).
    |signal-process|

#. Define configuration for side step by building a list of ``SideStepProcessDTO`` objects. 

    .. code-block:: javascript

        Map params1 = new HashMap();
        params1.put("caseUuid", caze.uuid());
        params1.put("stepId", "1");
        params1.put("taskUuid", task.uuid());

        // First configuration option
        SideStepProcessDTO dto1 = SideStepProcessDTO.builder()
        .taskUuid(task.uuid())
        .caseUuid(ivy.case.uuid())
        .processName("Side step 1: Ask more details")
        .signal("com:axonivy:portal:developerexample:sideStep:askMoreDetails")
        .params(params1)
        .build();


        Map params2 = new HashMap();
        params1.put("caseUuid", ivy.case.uuid());
        params1.put("stepId", "2");
        params1.put("taskUuid", task.uuid());

        // Second configuration option
        SideStepProcessDTO dto2 = SideStepProcessDTO.builder()
        .taskUuid(task.uuid())
        .caseUuid(ivy.case.uuid())
        .processName("Side step 2: CEO Approval")
        .signal("com:axonivy:portal:developerexample:sideStep:CEOApproval")
        .params(params2)
        .build();

        // define side step process dto list
        List processes = new List();
        processes.add(dto1);
        processes.add(dto2);        
    
    ..


#. Create a ``SideStepDTO`` object of ``portal-components`` from this list created on the above code snippet. Convert it to JSON and write the value to a custom field

    - For a single task: ``sideStepTask`` on that task.
    - For every task in a case: ``sideStepCase`` on the case.

    .. code-block:: javascript

      // Create a SideStepDTO object of portal-components from this list created on the above code snippet
      // The custom parallel / switch step type set is optional
      SideStepDTO sideStepDto = SideStepDTO.builder()
        .processes(processes)
        .stepTypeParallelTitle("Your custom title for parallel task if needed") // Optional
        .stepTypeSwitchTitle("Your custom title for switch task if needed") // Optional
        .build();

      // Convert SideStepDTO to Json 
      String jsonValue = BusinessEntityConverter.entityToJsonValue(sideStepDto);

      // For side step task scope
      task.customFields().textField(CustomFields.SIDE_STEPS_TASK).set(jsonValue);
      // Or for side step case scope
      ivy.case.customFields().textField(CustomFields.SIDE_STEPS_PROCESS).set(jsonValue);

    ..
      
    The generated Json value will have structure like this

    .. code-block:: javascript

      [
        "processes": [
        {
          "signal": "com:axonivy:portal:developerexample:sideStep:askMoreDetails",
          "processName": "Side step: Ask for more details",
          "caseUuid": "7af73c93-3bb1-4a74-aa8f-366f33d8a489",
          "params": {
            "stepId": "1",
            "caseUuid": "7af73c93-3bb1-4a74-aa8f-366f33d8a489"
          }
        }
        ],
        "stepTypeParallelTitle": "This is customized parallel title",
        "stepTypeSwitchTitle": "This is customized switch title"
      ]
    ..


#. Build business case detail if needed

    Your process needs to call the subprocess ``SetBusinessDetailsPage`` of the ``portal-components``, with param ``linkToBusinessDetailsPage`` set to the link of the custom business detail page in your project.
    |business-case-detail-page|

#. Start of the main task

    When the user opens the task, the Portal reads the field and populates the task action menu with the configured side steps.

#. User triggers a side step

    The Portal launches your signal, passes the JSON parameters and, if synchronous, parks the original task until the side step completes in case user select ``SWITCH`` step type.

#. Handle completion

    On finish your side-step process must raise a done signal. The Portal reactivates the parked task, opens an optional Side-step result dialog that you can write output data back to the task or case as needed.


.. |signal-process| image:: images/side-step/signal-process.png
.. |side-step-sample-process| image:: images/side-step/side-step-sample-process.png
.. |business-case-detail-page| image:: images/side-step/business-case-detail-page.png
.. |side-step-menu| image:: ../../screenshots/side-step/side-step-menu.png
.. |side-step-config| image:: ../../screenshots/side-step/side-step-config.png

