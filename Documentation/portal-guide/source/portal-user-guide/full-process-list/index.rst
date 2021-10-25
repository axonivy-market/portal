.. _full-process-list:

Full Process List
*****************

The **Full Process List** page gives you an overview of all the
processes in the application that are accessible to you. You can reach
the page by using the :guilabel:`Processes` link in the
Axon Ivy Portal menu.

|navigate-to-full-process-list|

On the **Full Process List** page, you see a list of all processes. They
are sorted alphabetically and grouped by their initial character. 
Express workflows are described in detail in :ref:`axon-ivy-express`.

|portal-full-process-list-page|

   #. Search for a process, input your keyword here
   #. Express workflow icon
   #. External link icon
   #. Change view mode

Process mode
------------

In the **Full Process List**, we provided three modes for displaying the process list.
By default, **Image mode** is activated. You can change the default process mode in :guilabel:`My Profile` page.

Image mode
^^^^^^^^^^

|portal-process-image-view-page|

.. hint:: 
   You can change process default image by changing
   value of Portal Setting **Portal.Processes.DefaultImage**.
   
   See :ref:`values_of_default_image`.

Grid mode
^^^^^^^^^

|portal-process-grid-view-page|


Compact Mode
^^^^^^^^^^^^

|portal-process-list-view-page|


If you’re looking for a specific process, you can search your keyword in search field 
marked in the image above. The search will look up your keyword in process names, 
process descriptions and process categories, then filter the process list accordingly.

.. warning:: 
   Don’t confuse the process search
   with the global Axon Ivy Portal 
   search which you find in the    
   header area of the Portal.      

When you select a process from the list, a new case of this process is started.
Please be aware, that after you finished your activities in the case you will be
re-directed to the **Dashboard**  and not the :guilabel:`Full Process
List`.

.. _values_of_default_image:

Values of Portal Setting **Portal.Processes.DefaultImage**
----------------------------------------------------------
#. You could refer to project ``portal-developer-examples`` for examples.
#. Start Process ``PhotoLibraryOfDefaultProcessImageExample``

|process-default-image-values|

HowTo: Edit process icon in Image mode
--------------------------------------
#. Click on more menu item in the process card item.
#. Click on |edit-icon| :guilabel:`Edit` menu item.
#. The dialog **Edit process information** is opened.
#. Click on :guilabel:`Change` link, besides the current icon.
#. The dialog :guilabel:`Selecting icon` is opened, and choose new icon.
#. Press on :guilabel:`Save` button, and process icon is changed

   |edit-process-menu-item|
   |edit-process-icon-dialog|

HowTo: Add an external link
---------------------------

#. Click on :guilabel:`Add external link` which is in Process list header.

#. The dialog :guilabel:`Add external link` is opened.

#. For the :guilabel:`Process name`, define the best name for it.

#. For the :guilabel:`Start link`, add the URL of your external link.

#. This step is only for a user granted ``CreatePublicExternalLink`` Portal permission. 
   This user can set this link as a public link by selecting :guilabel:`All users` .
   By default, :guilabel:`Visibility` field is not displayed and this new external link is a private link.

#. For the :guilabel:`Icon`, change the best icon for it.

#. Confirm your configuration with the button :guilabel:`Add`.

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
.. |portal-process-image-view-page| image:: ../../screenshots/process/portal-process-image-view-page.png
.. |portal-process-grid-view-page| image:: ../../screenshots/process/portal-process-grid-view-page.png
.. |portal-process-list-view-page| image:: ../../screenshots/process/portal-process-list-view-page.png
.. |edit-process-menu-item| image:: ../../screenshots/process/edit-process-menu-item.png
.. |edit-process-icon-dialog| image:: ../../screenshots/process/edit-process-dialog.png
.. |process-default-image-values| image:: images/process-default-image-values.png