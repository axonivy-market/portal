.. _side-step:

Integrate A Side Step Process
=============================

.. _side-step-introduction:

Introduction
------------

Side step processes let you attach one or more auxiliary workflow steps to an existing business process in Axon Ivy Portal without touching the core flow. 
At runtime Portal reads a JSON definition from the custom field ``sideStepTask`` (task level) or ``sideStepCase`` (case level), shows the available side steps in the task action menu and launches them either

- Synchronous - the main task pauses until the side step finishes, or

- Asynchronous - the main task continues while the side step runs in parallel.

Portal delivers the framework (public APIs, DTOs and UI hooks); you design and own the actual side step BPMN, dialogs, signals and business logic.
  
|side-step-menu|

Side step configuration dialog

|side-step-config|

Benefits and Outcomes
---------------------

- Low integration effort – add or remove auxiliary steps without modifying the main process model.

- Modular architecture – encourages clean separation between core and optional logic.

- Greater agility – quickly provide customer-specific extensions (e.g. extra approvals, data collection forms) with minimal risk.

- Parallel work – optional tasks can run concurrently, shortening throughput time.

- Custom UX – you control the side step dialog, result views and security (assignee or group callables).


How to Use and Set Up
---------------------

#. Model the side step process

   Create a process that performs the additional work (for example “Ask for more details”)
   and define a signal that starts this process (e.g. ``com:axonivy:portal:developerexample:sideStep:askMoreDetails`` ).
   
   |signal-process|


#. Define custom list of users and roles which the side step task can be assigned (optional)
   
   Create a process with no parameter which return 2 lists type ``ch.ivyteam.ivy.security.IRole`` with the following names

     - ``userRolesToDelegate`` : side step task can be assigned to all users who have role in this list
     - ``rolesToDelegate`` : side step task can be assigned to all children roles in this list

   If not defined, side step task can be assigned to all users and roles in the security context

#. Define configuration for side step by building a list of ``SideStepProcessDTO`` objects. 

    .. code-block:: javascript
      
        // First configuration option - using CMS path for multilingual process names
        SideStepProcessDTO dto1 = SideStepProcessDTO.builder()
        .processNameCmsPath("/Labels/SideStep/AskForMoreDetails")
        .signal("com:axonivy:portal:developerexample:sideStep:askMoreDetails")
        .build();

        // Second configuration option - with project-specific CMS path
        SideStepProcessDTO dto2 = SideStepProcessDTO.builder()
        .processNameCmsPath("/Labels/SideStep/CEOApproval")
        .cmsProjectName("portal-developer-examples")  // Optional - if not provided, uses current project context
        // Set signature name of the process which defines custom users and roles in the previous step
        .customSecurityMembersCallable("getCustomSecurityMemberForSideStep()")  // Optional
        .signal("com:axonivy:portal:developerexample:sideStep:CEOApproval")
        .build();

        // Define side step process dto list
        List processes = new List();
        processes.add(dto1);
        processes.add(dto2);        
    
    ..


#. Create a ``SideStepConfigurationDTO`` object of ``portal-components`` from this list created on the above code snippet. Convert it to JSON and write the value to a custom text field

    - For a single task: save to custom text field ``sideStepTask`` on that task.
    - For every task in a case: save to custom text field ``sideStepCase`` on that case.

    .. code-block:: javascript

      // Create a SideStepConfigurationDTO object using CMS paths for multilingual titles
      // If the isParallelSideStep value is not defined, on the UI you will see a drop down to select
      
      SideStepConfigurationDTO sideStepConfigurationDto = SideStepConfigurationDTO.builder()
        .processes(processes)
        .isParallelSideStep(true)
        .customParallelSideStepTitleCmsPath("/Labels/SideStep/CustomParallelTitle") // Optional
        .customSwitchSideStepTitleCmsPath("/Labels/SideStep/CustomSwitchTitle") // Optional
        .cmsProjectName("portal-developer-examples") // Optional - if not provided, uses current project context
        .build();

      // Convert SideStepConfigurationDTO to Json 
      String jsonValue = BusinessEntityConverter.entityToJsonValue(sideStepDto);

      // For side step task level
      task.customFields().textField(CustomFields.SIDE_STEPS_TASK).set(jsonValue);
      // Or for side step case level
      ivy.case.customFields().textField(CustomFields.SIDE_STEP_CASE).set(jsonValue);

    ..
      
    The generated Json value will have structure like this

    .. code-block:: javascript

      {
        "version": "12.0.0",
        "processes": [
          {
            "signal": "com:axonivy:portal:developerexample:sideStep:askMoreDetails",
            "processNames": [
              {
                "value": "/Labels/SideStep/AskForMoreDetails",
                "projectName": "portal-developer-examples"
              }
            ]
          },
          {
            "signal": "com:axonivy:portal:developerexample:sideStep:CEOApproval",
            "processNames": [
              {
                "value": "/Labels/SideStep/CEOApproval",
                "projectName": "portal-developer-examples"
              }
            ],
            "customSecurityMemberCallable": "getCustomSecurityMemberForSideStep()"
          },
          {
            "signal": "com:axonivy:portal:developerexample:sideStep:informCustomer",
            "processNames": [
              {
                "value": "/Labels/SideStep/InformCustomer",
                "projectName": "portal-developer-examples"
              }
            ]
          }
        ],
        "customParallelSideStepTitles": [
          {
            "value": "/Labels/SideStep/CustomParallelTitle",
            "projectName": "portal-developer-examples"
          }
        ],
        "customSwitchSideStepTitles": [
          {
            "value": "/Labels/SideStep/CustomSwitchTitle",
            "projectName": "portal-developer-examples"
          }
        ]
      }
    ..


#. Build business case detail if needed

   Your process needs to call the subprocess ``SetBusinessDetailsPage`` of the ``portal-components``, with param ``linkToBusinessDetailsPage`` set to the link of the custom business detail page in your project.
   You can also do this by using ``BusinessDetailsAPI``.

   |business-case-detail-page|

#. Start of the main task

   When the user opens the task, Portal reads the field and populates the task action menu with the configured side steps.

#. User triggers a side step

   Portal sends signal to your selected process, with the JSON parameters contain your input information like task uuid, case uuid, your comment. 
   In case user select ``SWITCH`` step type (synchronously), Portal will park and set ``HIDE`` property to the original task until the side step completes. Otherwise the side step will run parallel with the original task.

#. Handle data in the process which was triggered by Portal

   Process developer gets data from the signal as JSON string, parses it to class ``SideStepProcessParamDTO`` object of ``portal-components``. This object contains data send from Portal to use for the process.
      
    .. code-block:: javascript

      SideStepProcessParamDTO data = BusinessEntityConverter.jsonValueToEntity(signal.getSignalData() as String, SideStepProcessParamDTO.class) as SideStepProcessParamDTO;

    ..
      
#. Handle completion

   On finish your side step process must raise a done signal by calling API ``SideStepAPI.finishSideStep(String originalTaskUuid, boolean isParallelSideStep)``. 
   This API will reactivate and remove ``HIDE`` property from the parked task if the side step task is ``SWITCH`` step type (synchronously).


.. |signal-process| image:: images/side-step/signal-process.png
.. |side-step-sample-process| image:: images/side-step/side-step-sample-process.png
.. |business-case-detail-page| image:: images/side-step/business-case-detail-page.png
.. |side-step-menu| image:: ../../screenshots/side-step/side-step-menu.png
.. |side-step-config| image:: ../../screenshots/side-step/side-step-config.png

