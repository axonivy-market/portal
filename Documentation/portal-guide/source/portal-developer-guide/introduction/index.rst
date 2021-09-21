.. _introduction:

Introduction
************

Portal Developer Guide provides information to set up, configure, customize 
Portal so that it could fit customer need.

More UX for Axon Ivy Portal 9.2, refer to :ref:`introduction-new-and-note-worthy-9.2`.

- Enhanced forgot password features
- Simplified tasks and cases export
- Redesigned process list
- Additional drag and drop widgets in task and case detail pages
- Advanced user specific settings 

New & Noteworthy 9.3
--------------------

Customization global growl message for task using IFrame
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

A new API is introduced to support customizing global growl message for task using IFrame.

Enhance task filter
^^^^^^^^^^^^^^^^^^^

Portal admin can query for tasks which have unavailable activator. Those tasks could have no responsible, responsible is disabled or not exists.
|task-filter-missing-activator|

New Dashboard and support legacy mode
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Portal provides new look and feel Dashboard, refer to :ref:`customization-new-dashboard`. And also supports ``Portal.ShowLegacyUI`` option in Admin Settings to switch back to legacy Portal home.

.. _introduction-new-and-note-worthy:

.. _introduction-new-and-note-worthy-9.2:

New & Noteworthy 9.2
--------------------

.. _introduction-new-and-note-worthy-task-case-details-configuration:

Task/Case details configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Task/Case details are well structured, user can drag and drop the widgets and they can be configured via Global variable (JSON file).

Enhanced forgot password features
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Users can reset their passwords easily using the standard password reset feature integrated in Axon Ivy Portal
|login-screen|
|send-email-screen|

Simplified tasks and cases export
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
We have listened to you! Business users requested to export specific lists from Axon Ivy Portal to perform further analysis in Microsoft Excel. 
Now they have the possibility to export any list with one mouse click.
|task-key-information|

Redesigned process list
^^^^^^^^^^^^^^^^^^^^^^^
You don’t see the forest for the trees? Not anymore! Switch to the new grid view to display your process list in a more user-friendly way.

|portal-process-grid-view-page|

Additional drag and drop widgets in task and case detail pages
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Only you know the details and therefore, any detail page for tasks or cases can be restyled using drag and drop widgets. Create your own detail page with your specific content and without the need of programming.
|detailed-task-information|

|case-details|

Advanced user specific settings
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Make it your own way with the Axon Ivy Portal. Users can optimize process work and customize the Axon Ivy Portal to their own taste.

|my-profile-save|

New override approach
^^^^^^^^^^^^^^^^^^^^^

Portal uses HTML Dialog Override instead of Sub Process Override for customization.

User and role selection component
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you need user and role selection, use Portal component instead of implementing yourself. For more details refer to :ref:`components-additional-components-user-selection` 
and :ref:`components-additional-components-role-selection`.

New & Noteworthy 9.1
--------------------

Overlay guide
^^^^^^^^^^^^^

At first login, an introduction will be shown to introduce all main features of Portal.

|overlay-guide|

My profile 
^^^^^^^^^^

My profile replaces for language setting and email setting. Also, introduce the general setting for you can configure the Portal according to your requirements

|my-profile|

New absence management
^^^^^^^^^^^^^^^^^^^^^^

New UI and user can choose deputy as permanent or temporary for personal task.

|absence|


.. _introduction-new-and-note-worthy-further-improvement:

Further improvements
^^^^^^^^^^^^^^^^^^^^

Filter case by owner in case list and task analysis if case owner is enabled.


.. |overlay-guide| image:: ../../screenshots/dashboard/overlay-guide.png
.. |my-profile| image:: ../../screenshots/my-profile/my-profile.png
.. |absence| image:: ../../screenshots/settings/absence.png
.. |login-screen| image:: ../../screenshots/login/login-form.png
.. |send-email-screen| image:: ../../screenshots/forgot-password/send-email-screen.png
.. |task-key-information| image:: ../../screenshots/task/task-key-information.png
.. |portal-process-grid-view-page| image:: ../../screenshots/process/portal-process-grid-view-page.png
.. |detailed-task-information| image:: ../../screenshots/task-detail/detailed-task-information.png
.. |case-details| image:: ../../screenshots/case-detail/case-details.png
.. |my-profile-save| image:: ../../screenshots/my-profile/my-profile.png
.. |task-filter-missing-activator| image:: ../../screenshots/task/task-filter-missing-activator.png