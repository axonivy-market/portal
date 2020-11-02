.. _full-process-list:

Full Process List
*****************

The :guilabel:`Full Process List` page gives you an overview on all the
processes in the application that are accessible to you. You can reach
the page by either using the **Processes** link in the
Axon.ivy Portal menu or the quick link **Show all processes** in your
dashboard.

|navigate-to-full-process-list|

On the :guilabel:`Full Process List` page, you see a list of all processes. They
are sorted alphabetically and grouped by their initial character. 
Express workflows are described in detail in :ref:`axon-ivy-express`.

|portal-full-process-list-page|

   1. Search for a process, input your keyword here
   2. Express workfow icon
   3. External link icon

If you’re looking for a specific process, you can search your keyword in search field 
marked in the image above. The search will look up your keyword in process names 
and process descriptions then filter the process list accordingly.

.. warning:: 
   Don’t confuse the process search
   with the global Axon.ivy Portal 
   search which you find in the    
   header area of the Portal.      

When you select a process from the list, a new case of this process is started.
Please be aware, that after you finished your activities in the case you will be
re-directed to the :guilabel:`Dashboard`  and not the :guilabel:`Full Process
List`.

HowTo: Add an external link
--------------------------------------------

1. Select **Add external link** which is in Process list header.

2. The dialog :guilabel:`Add new external link` is opened.

3. For the **Process name**, define the best name for it.

4. For the **Start link**, add the URL of your external link.

5. This step is only for a user granted ``CreatePublicExternalLink`` Portal permission. 
   This user can set this link as a public link by selecting ``All users``.
   By default, 'Visibility' field is not displayed and this new external link is a private link.

6. Confirm your configuration with the button **Add**.

|how-to-add-a-new-external-link|

|add-external-link-dialog|


.. hint:: 
   Starting processes from the Full Process List page is fine if you require
   this process only on a rare basis. For all processes which you use on a
   regular basis we highly recommend configuring them on your personal
   dashboard. See :ref:`howto-add-a-process-as-a-user-favorite` for an instruction.

.. include:: ../includes/_common-icon.rst


.. |navigate-to-full-process-list| image:: ../../screenshots/process/navigate-to-full-process-list.png
.. |portal-full-process-list-page| image:: ../../screenshots/process/portal-full-process-list-page.png
.. |how-to-add-a-new-external-link| image:: ../../screenshots/process/how-to-add-a-new-external-link.png
.. |add-external-link-dialog| image:: ../../screenshots/process/add-external-link-dialog.png