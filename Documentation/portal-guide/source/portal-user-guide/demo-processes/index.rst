.. _demo-processes:

Demo processes
**************

When you start engine in demo mode, you will see the project :guilabel:`portal-user-examples`.
In this project, we provide some simple examples and you can play around with them
to have the feeling how ivy and Portal work.

.. hint:: 
   You can use these accounts to login into Portal in demo mode:

  +---------------------+---------------------+-------------------------+
  | Username            | Password            | Role                    |
  +=====================+=====================+=========================+
  | admin               | admin               | Portal admin            |
  +---------------------+---------------------+-------------------------+
  | demo                | demo                | Normal user             |
  +---------------------+---------------------+-------------------------+
  | guest               | guest               | Normal user             |
  +---------------------+---------------------+-------------------------+

Leave request
-------------

1. Login to Portal.

2. Select User example guide to open overview page.

|user-example-guide-link|

3. At example overview page, start leave request

|example-overview-leave-request|

4. You will go directly to the first task **Create leave request for: <user>**.

|leave-request-creation|

5. Input your data and submit leave request. Base on your selected approver, next task will be assigned to that user.

6. The 2nd task is **Leave request approval**, and after approver finishes his task, summary task will be assigned back to requester.

7. After requester finishes **summary task**, the case is Done.

Lending (Case Map)
------------------

1. Login to Portal

2. Select User example guide to open overview page.

|user-example-guide-link|

3. At example overview page, start Lending case

|example-overview-lending-case|

4. You will go directly to the first task **Collect Personal Data**

|lending-casemap-collect-personal-data|

By default we'll have dummy data on all fields, or you can input your data and submit the request.

5. You will go to task **Verify Personal Data** to verify your information.

6. In **Internal Solvency Check** task, you can proceed to next task or start **External Solvency Service** sidestep by clicking **External solvency service**.

|lending-casemap-external-solvency-service|

7. After finishing Internal Solvency, you will have 1 or 2 approval levels which are depended on Amount and Salary fields. If Amount is greater than or equal to 20% of Salary, you will have 2 approval levels.

|lending-casemap-approval-task|

8. In the approval task, you can approve to complete task and go to the last step which is created a contract. Now **Create Contract** task is finished, the case is Done. If we reject task, process will end.


.. |example-overview-leave-request| image:: ../../screenshots/demo-processes/example-overview-leave-request.png
.. |leave-request-creation| image:: ../../screenshots/demo-processes/leave-request-creation.png
.. |user-example-guide-link| image:: ../../screenshots/demo-processes/user-example-guide-link.png
.. |example-overview-lending-case| image:: ../../screenshots/demo-processes/example-overview-lending-case.png
.. |lending-casemap-collect-personal-data| image:: ../../screenshots/demo-processes/lending-casemap-collect-personal-data.png
.. |lending-casemap-external-solvency-service| image:: ../../screenshots/demo-processes/lending-casemap-external-solvency-service.png
.. |lending-casemap-approval-task| image:: ../../screenshots/demo-processes/lending-casemap-approval-task.png