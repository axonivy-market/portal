.. _full-process-list:

Full Process List
*****************

The **Full Process List** page gives you an overview of all the processes in the
application available to you. To open the page, click :guilabel:`Processes` in
the |ivy| Portal menu.

.. note::

   Grant permission :bdg-ref-warning:`🔑AccessFullProcessList <AccessFullProcessList>` to allow a user to see the full process list (:guilabel:`Processes`) in the |ivy| Portal menu.


|navigate-to-full-process-list|

On the **Full Process List** page, you see a list of all processes. They
are sorted alphabetically and grouped by their initial character. 

|portal-full-process-list-page|

   #. Search for a process, input your keyword here
   #. Image process icon
   #. Change view mode
   #. Process more menu item icon

Process Display Mode
--------------------

The **Full Process List** provides three modes to display the process list. By
default, **Image mode** is activated. To change the default process display mode, access :ref:`process-list-configuration` in ``My Profile``.

Image Mode
^^^^^^^^^^

|portal-process-image-view-page|

.. hint:: 
   To change the default image of processes, change
   the Portal setting **Portal.Processes.DefaultImage**.
   
   See :ref:`values_of_default_image`.

Grid Mode
^^^^^^^^^

|portal-process-grid-view-page|


Compact Mode
^^^^^^^^^^^^

|portal-process-list-view-page|

To find a specific process, search by keywords in the search field (top left
below the home icon in the image above). The search will look in process names,
process descriptions and process categories, then filter the process list
accordingly.

.. warning:: 
   Don’t confuse the process search with the global |ivy| Portal 
   search which you find in the header area of the Portal.

To start a new instance of a process, select the process in the list. We call
this instance a **case**. After you finish your activities in the case, you will
be re-directed to the **Dashboard**, not to the :guilabel:`Full Process List`.

.. _values_of_default_image:

Values of Portal Setting **Portal.Processes.DefaultImage**
----------------------------------------------------------
#. You could refer to project ``portal-developer-examples`` for examples.
#. Start Process ``PhotoLibraryOfDefaultProcessImageExample``

|process-default-image-values|

HowTo: Add an external link
---------------------------

#. Click on :guilabel:`Add external link` at the top of the page next to the search text box.

#. The dialog :guilabel:`Add external link` is opened.

#. For the :guilabel:`Process name`, define the best name for it.

#. At the end of the :guilabel:`Process name` input, you will see a translate
   icon. Click the icon to open the ``Setting Multiple Languages`` dialog. You
   can specify the name in multiple languages by filling in the various language
   fields. If you don’t fill in a language field, the default language name will
   be used.

#. For the :guilabel:`Start link`, add the URL of your external link.

#. For the :guilabel:`Description`, add the description of your external link.

#. At the end of :guilabel:`Description` input, you will see a translate icon.
   Click the icon to open the ``Setting Multiple Languages`` dialog. You can
   specify the description in multiple languages by filling in the various
   language fields. If you don't fill in a language field, the default language
   description will be used.

#. Grant permission :bdg-ref-warning:`🔑CreatePublicExternalLink <CreatePublicExternalLink>` to allow a user to create public links to external sites. 
   This user can set this link as a public link by selecting :guilabel:`All users` .
   By default, :guilabel:`Visibility` field is not displayed and this new external link is a private link.

#. For the :guilabel:`Icon`, change the best icon for it.

#. For the :guilabel:`Image`, drag and drop an image, or click on ``Upload one here`` to upload an image for the process.

#. Confirm your configuration with the button :guilabel:`Add`.

|how-to-add-a-new-external-link|

|add-external-link-dialog|


.. hint:: 
   Starting processes from the Full Process List page is fine if you require
   this process only on a rare basis. For all processes which you use on a
   regular basis we highly recommend configuring them on your personal
   dashboard.

.. include:: ../includes/_common-icon.rst

HowTo: Edit process icon of External link in Image mode
-------------------------------------------------------
#. Click on ``More menu item`` icon in the process card item.
#. Click on |edit-icon| :guilabel:`Edit` menu item.
#. The dialog **Edit process information** is opened.
#. You can change the current icon using the available icon list, or open the :guilabel:`Selecting icon dialog` to get more new icons.
#. Press on :guilabel:`Save` button, and process icon is changed

   |edit-process-menu-item|
   |edit-process-icon-dialog|

Process Information
-------------------

The **Process Information** page shows you information about the selected process.
Besides basic information such as process name and description, you can see
advanced information like process steps and more.

Process steps are a list of steps you will perform once you start the process.
These process steps are defined by the process developer.

Basic information
^^^^^^^^^^^^^^^^^

The **Process Information** page not only shows you basic information of the
selected process such as process name and description, but also detailed
information in the form of process steps.

To reach this page:

- click on ``More action item`` icon of a process in image mode or grid mode, then choose ``More Information``.

|more-information-link|

- click on link ``Show process overview`` on the Case details page of a case
  started by this process.

|process-overview-link|

Process steps
^^^^^^^^^^^^^

|process-information|

At the top of the **Process Information** page, you see basic process
information like name and description.

Depending on the process, you can see process steps that are defined by its
developers. These process steps give you a clear picture of the flow of the
process so you can work more efficiently with it.


.. |navigate-to-full-process-list| image:: ../../screenshots/process/navigate-to-full-process-list.png
   :alt: Full process list menu
.. |portal-full-process-list-page| image:: ../../screenshots/process/portal-full-process-list-page.png
   :alt: Full process list page
.. |how-to-add-a-new-external-link| image:: ../../screenshots/process/how-to-add-a-new-external-link.png
   :alt: How to add new external link
.. |add-external-link-dialog| image:: ../../screenshots/process/add-external-link-dialog.png
   :alt: Add new external link dialog
.. |portal-process-image-view-page| image:: ../../screenshots/process/portal-process-image-view-page.png
   :alt: Full process list image mode
.. |portal-process-grid-view-page| image:: ../../screenshots/process/portal-process-grid-view-page.png
   :alt: Full process list grid mode
.. |portal-process-list-view-page| image:: ../../screenshots/process/portal-process-list-view-page.png
   :alt: Full process list compact mode
.. |edit-process-menu-item| image:: ../../screenshots/process/edit-process-menu-item.png
   :alt: Edit process menu item
.. |edit-process-icon-dialog| image:: ../../screenshots/process/edit-process-dialog.png
   :alt: Edit process dialog
.. |process-default-image-values| image:: images/process-default-image-values.png
   :alt: Photos of default process images
.. |more-information-link| image:: ../../screenshots/process/information/more-information-link.png
   :alt: Process more information link
.. |process-overview-link| image:: ../../screenshots/process/information/process-overview-link.png
   :alt: Process overview link
.. |process-information| image:: ../../screenshots/process/information/process-information.png
   :alt: Process information page