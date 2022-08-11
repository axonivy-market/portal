.. _demo-processes:

Demo Processes
**************

When you start the Axon Ivy Engine in demo mode, you will see **portal-user-examples**.
In this project, we provide some simple examples.
You can play with them to have the feeling how Axon Ivy and Portal work.

.. hint:: 
   You can use these accounts to log in to Portal in demo mode:

  +---------------------+---------------------+-------------------------+
  | Username            | Password            | Role                    |
  +=====================+=====================+=========================+
  | admin               | admin               | Portal admin            |
  +---------------------+---------------------+-------------------------+
  | demo                | demo                | Normal user             |
  +---------------------+---------------------+-------------------------+
  | guest               | guest               | Normal user             |
  +---------------------+---------------------+-------------------------+

Leave Request
-------------

#. Login to Portal.

#. Select :guilabel:`User example guide` to open the overview page.

   |user-example-guide-link|

#. On the example overview page, start the leave request

   |example-overview-leave-request|

#. You will go directly to the first task **Create leave request for: <user>**.

   |leave-request-creation|

#. Input your data and submit your leave request. Based on your selected
   approver, the next task will be assigned to that user.

#. The second task is **Leave request approval**. After the approver finishes
   his task, the summary task will be assigned back to requester.

#. After the requester finishes the **summary task**, the case ends.


Lending (Case Map)
------------------

#. Login to Portal

#. Select :guilabel:`User example guide` to open the overview page.

   |user-example-guide-link|

#. On the example overview page, start the lending case

   |example-overview-lending-case|

#. You will go directly to the first task **Collect Personal Data**

   |lending-casemap-collect-personal-data|

   By default, we supply dummy data for all fields. You can input your data and
   submit the request.

#. You will go to task **Verify Personal Data** to verify your information.

#. In the **Internal Solvency Check** task, you can proceed to the next task or
   start the **External Solvency Service** sidestep by clicking **External
   solvency service**.

   |lending-casemap-external-solvency-service|

#. After finishing Internal Solvency, you will have 1 or 2 approval levels which
   depend on Amount and Salary fields. If Amount is greater than or equal
   to 20% of Salary, you will have 2 approval levels.

   |lending-casemap-approval-task|

#. In the approval task, approve to complete the task and go to the last
   step: create a contract. Once the **Create Contract** task is finished,
   the case is Done. If we reject task, the case will end in status Rejected.


.. |example-overview-leave-request| image:: ../../screenshots/demo-processes/example-overview-leave-request.png
.. |leave-request-creation| image:: ../../screenshots/demo-processes/leave-request-creation.png
.. |user-example-guide-link| image:: ../../screenshots/demo-processes/user-example-guide-link.png
.. |example-overview-lending-case| image:: ../../screenshots/demo-processes/example-overview-lending-case.png
.. |lending-casemap-collect-personal-data| image:: ../../screenshots/demo-processes/lending-casemap-collect-personal-data.png
.. |lending-casemap-external-solvency-service| image:: ../../screenshots/demo-processes/lending-casemap-external-solvency-service.png
.. |lending-casemap-approval-task| image:: ../../screenshots/demo-processes/lending-casemap-approval-task.png