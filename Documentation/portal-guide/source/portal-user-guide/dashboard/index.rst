.. _dashboard:

Dashboard
*********

The Axon Ivy Portal Dashboard is the first page you encounter after
successful login. You can always get back to the dashboard using the 
**Dashboard** link in the Axon Ivy Portal menu. Alternatively, you can click on
the Axon Ivy logo in the header.

.. figure:: images/dash-board.png

The dashboard itself is separated into 3 sections:

#. Manage quick links to the processes you use most often in your daily work. The
   section Processes is described in `Process Favorites`_.

#. The **Tasks** section in the center. Here you see all the open tasks
   assigned to you or your organizational unit. These tasks require
   your personal attention. The section is described in `Personal Tasks`_.


#. The **Statistics** section on the right side. The charts in this
   section provide you with basic information on the performance of your
   cases and tasks. This section is described in `Statistics`_.

Process Favorites
-----------------

The  **Process favorites** you find under the heading :guilabel:`Processes` on the
left side of your dashboard. They provide you with a quick and easy access to
the processes you need most in your daily work. Therefore, the process favorites
are a key feature to enhance your Axon Ivy Portal experience and increase your
efficiency. To make full use of this feature the Axon Ivy Portal offers you an
easy way to configure the process favorites to your personal needs. 

.. figure:: images/process-favorites.png

When you have a look at the process favorites you see two areas:

-  On the top you find the :guilabel:`User Favorites` which you need to
   configure before you can use them. You may add processes here as well
   as external links.

-  Below you find the :guilabel:`Application Favorites`. They are centrally
   configured by your administrator. Therefore, you may use but not
   change them.

Furthermore, next to the heading you find a link :guilabel:`Show all processes`.
This quick link routes you the **Full Process List** page of the
Axon Ivy Portal. Please refer to :ref:`full-process-list` for more detail information.

.. _howto-add-a-process-as-a-user-favorite:

HowTo: Add a process as a user favorite
---------------------------------------

#. Click on the link :guilabel:`Add new process`  which you find next to the
   heading :guilabel:`User Favorites`.

#. The dialog :guilabel:`Add new user process` is opened.

#. For the :guilabel:`Selected process`  use the dropdown menu to select the process you want to add.

#. The :guilabel:`Display name`  is default process name, to add multilingual for favorite process, use :guilabel:`Add languages` button

#. The **Start link** is automatically filled by the Axon Ivy Portal.

#. You may change the :guilabel:`Icon` for the process by using the link
   |change-icon| :guilabel:`Change` and selecting your preferred icon.

#. Confirm your configuration with the button :guilabel:`Add`.

.. figure:: images/how-to-add-process-favorite.png

.. hint:: 
   -  The list of processes in the dropdown menu shows only the processes which are
      accessible to you depending on your roles.
                                    
      Furthermore, the list shows only processes which are not added to your user
      favorites so far. You cannot add a process multiple time.

   -  For multiple languages of favorite process display name, you need to
      create the "AppInfo/SupportedLanguages" CMS which defines how many
      languages your application supports. See the below "Language
      settings" for more details.

HowTo: Delete a process as a user favorite
------------------------------------------

#. Click on the link :guilabel:`Edit processes`  next to the heading :guilabel:`User Favorites`.

#. Click on the |trash-icon| :guilabel:`Delete`  symbol next to process / processes

#. Confirm the removal with the link :guilabel:`Save`  next to the heading :guilabel:`User Favorites`.

.. figure:: images/how-to-delete-process-favorites-1.png

.. figure:: images/how-to-delete-process-favorites-2.png

HowTo: Reorder user favorites
-----------------------------

There are two ways to order the processes in your :guilabel:`User Favorites`.

Sort by name 
^^^^^^^^^^^^^^^

To order your processes and external links in an alphabetical order
you can use the link :guilabel:`Sort by name`  next to the heading :guilabel:`User Favorites`.

.. figure:: images/how-to-order-process-favorites-by-name.png

Edit processes
^^^^^^^^^^^^^^^^^^

#. To order your processes in an individual order, click on the link
   :guilabel:`Edit processes`  next to the heading :guilabel:`User Favorites`.

#. To change the order of your processes, drag and drop processes.

#. Confirm the reorder with the link :guilabel:`Save`  next to the heading :guilabel:`User Favorites`.

   .. figure:: images/how-to-delete-process-favorites-1.png

   .. figure:: images/how-to-order-process-favorites-individually.png

Personal Tasks
--------------

The personal tasks you find under the heading :guilabel:`Tasks` in the center of
your dashboard. You see here all tasks that are assigned to you or any of the
roles you hold within the application. Therefore, this section is key in
understanding your workload within the application. You may pick any task
from the task list and start working on it.

.. figure:: images/personal-tasks.png

   

At first glance, you see the number of tasks right next to the heading
:guilabel:`Tasks`. Further to the right you find the link :guilabel:`Show full task
list`. This quick link routes you to the **Task List** page of the
Axon Ivy Portal. Please refer to :ref:`full-task-list` for more
detailed information.

Right below the heading :guilabel:`Task` you find the following features which
support you in finding the correct tasks:

#. The Axon Ivy Portal searches for your keyword in the task ID, name and
   description of the tasks in your task list.
#. On the right side of the Tasks section you can find the sort by :guilabel:`Creation Date` :guilabel:`Expiry` :guilabel:`Priority`  

   .. figure:: images/personal-tasks-sort-and-search-features.png

  

Each task in your task list has an own entry. The entry provides you
with key information about the task allowing you an easy identification.
The following information can be found in the task entries:

#. The **Task Priority**

#. The **Task Name**

#. The **Task ID**

#. The **Creation Date**

#. The **Expiry Date** which is the due date until when the task should be completed.

#. The **Task Description**

   .. figure:: images/personal-tasks-key-information.png


Statistics
----------

The statistics you find under the heading :guilabel:`Statistics` on the
right-hand side of your dashboard. The charts in this section allow you to grasp
the overall situation at a glance.

.. figure:: images/dashboard-statistics-section.png


Next to the heading you find a link :guilabel:`Show all charts`. This quick link
routes you the **Statistics** page of the Axon Ivy Portal. Refer
to :ref:`full-statistic-list`  for more detailed information.

Within header of the chart, you find two navigation buttons
(|pre-icon|, |next-icon|) allowing you to toggle between the available
charts. Each chart then provides you with the following information:

#. The **Chart Title**  helping you to understand the content of the
   chart

#. The **Chart** itself, which gives you a graphical representation of
   the data. Furthermore, you see the exact figures in the chart as
   well.

#. The **Legend** explains the different elements of the charts.

.. figure:: images/statistics-key-information.png

If you require more or different charts, you need to create them first.
Chapter :ref:`howto-create-chart` explains in detail how to create new
charts.

.. include:: ../includes/_common-icon.rst