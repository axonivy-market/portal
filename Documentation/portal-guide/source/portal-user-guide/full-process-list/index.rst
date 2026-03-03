.. _full-process-list:

Full Process List
*****************

The **Full Process List** page gives you an overview of all the processes in the
application available to you. To open the page, click :guilabel:`Processes` in
the Axon Ivy Portal menu.

.. important::
   **Access Requirement**: Only users with the :ref:`🔑AccessFullProcessList <AccessFullProcessList>` 
   permission can see the :guilabel:`Processes` menu item.

|navigate-to-full-process-list|

Process List 
============

Overview
--------

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

.. table::
   :widths: 25 75

   +-------------------+----------------------------------------------------------------+
   | Display Mode      | Description                                                    |
   +===================+================================================================+
   | **Image Mode**    | Shows processes with large icons and images (default)          |
   +-------------------+----------------------------------------------------------------+
   | **Grid Mode**     | Displays processes in a compact grid layout                    |
   +-------------------+----------------------------------------------------------------+
   | **Compact Mode**  | Lists processes in a dense, text-focused format                |
   +-------------------+----------------------------------------------------------------+

Image Mode
^^^^^^^^^^

|portal-process-image-view-page|

.. tip:: 
   To change the default image of processes, change the Portal setting **Portal.Processes.DefaultImage**. 
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

.. note:: 
   The process search is distinct from the global Axon Ivy Portal search in the header area.

To start a new instance of a process, select the process in the list. We call
this instance a **case**. After you finish your activities in the case, you will
be re-directed to the **Dashboard**, not to the :guilabel:`Full Process List`.

.. _values_of_default_image:

Values of Portal Setting Portal.Processes.DefaultImage
------------------------------------------------------

#. You could refer to project ``portal-developer-examples`` for examples.
#. Start Process ``PhotoLibraryOfDefaultProcessImageExample``

|process-default-image-values|

HowTo: Add an external link
---------------------------

.. note::
   Creating public external links (visible to all users) requires :ref:`🔑CreatePublicExternalLink <CreatePublicExternalLink>` permission. Private links can be created by any user.

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

#. For the :guilabel:`Visibility`, select :guilabel:`All users` to create a public link (requires CreatePublicExternalLink permission), 
   or leave as default for a private link.

#. For the :guilabel:`Icon`, change the best icon for it.

#. For the :guilabel:`Image`, drag and drop an image, or click on ``Upload one here`` to upload an image for the process.

#. Confirm your configuration with the button :guilabel:`Add`.

|how-to-add-a-new-external-link|

|add-external-link-dialog|

.. tip:: 
   For frequently used processes, configure them on your personal dashboard instead of accessing them from the Full Process List.

.. include:: ../includes/_common-icon.rst

HowTo: Edit Process Icon in Image Mode
---------------------------------------

#. Click on ``More menu item`` icon in the process card item.

#. Click on |edit-icon| :guilabel:`Edit` menu item.

#. The dialog **Edit process information** is opened.

#. You can change the current icon using the available icon list, or open the :guilabel:`Selecting icon dialog` to get more new icons.

#. Press on :guilabel:`Save` button, and process icon is changed.

|edit-process-menu-item|

|edit-process-icon-dialog|

Process Information
===================

The **Process Information** page shows you information about the selected process.
Besides basic information such as process name and description, you can see
advanced information like process steps and more.

Process steps are a list of steps you will perform once you start the process.
These process steps are defined by the process developer.

Basic Information
------------------

The **Process Information** page shows you basic information of the selected process such as process name and description, and detailed information in the form of process steps.

**To reach this page:**

- Click on ``More action item`` icon of a process in image mode or grid mode, then choose ``More Information``

  |more-information-link|

- Click on link ``Show process overview`` on the Case details page of a case started by this process

  |process-overview-link|

Process Steps
-------------

|process-information|

At the top of the **Process Information** page, you see basic process information like name and description.

.. note::
   Process steps are defined by developers and provide a clear picture of the process flow to help you work more efficiently.

Permission Reference
====================

Different process operations require specific permissions:

.. table::
   :widths: 40 60

   +--------------------------------------+--------------------------------------------------------+
   | Action                               | Required Permission                                    |
   +======================================+========================================================+
   | **Create public external link**      | :ref:`🔑CreatePublicExternalLink                       |
   |                                      | <CreatePublicExternalLink>`                            |
   +--------------------------------------+--------------------------------------------------------+


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
