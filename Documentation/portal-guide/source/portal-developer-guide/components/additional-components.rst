.. _components-additional-component:

Additional Components
=====================

Portal provides additional UI components and features that can be configured through the Admin Settings interface.

.. note::

   Most Portal features are now configured through :ref:`Admin Settings <admin-settings>` rather than requiring custom component integration. This provides a centralized, user-friendly way to manage Portal behavior.

.. _components-additional-component-global-growl:

Global Growl
------------

Portal includes a global growl component in BasicTemplate for displaying notification messages to users.

Usage
^^^^^

To display messages using the Portal global growl in your custom dialogs:

.. code-block:: html

    <p:growl id="portal-global-growl" widgetVar="portal-global-growl" 
             for="portal-global-growl-message" escape="false" showDetail="true" />

Configuration
^^^^^^^^^^^^^

The global growl behavior is controlled by the Portal setting ``Portal.DisplayMessageAfterFinishTask``, which can be configured in :ref:`Admin Settings <update-portal-settings>`.

**When Enabled:**

- Displays a growl message after a task is finished
- Shows a notification when a user cancels a task

**Examples:**

**Growl After Finishing a Task**

|example-global-growl-finished-task|

**Growl After Leaving a Task**

|example-global-growl-cancelled-task|

.. tip::

   Configure Portal behavior through :ref:`Admin Settings <admin-settings>` for easier management without code changes.

.. |example-global-growl-finished-task| image:: ../../screenshots/components/example-global-growl-finished-task.png
.. |example-global-growl-cancelled-task| image:: ../../screenshots/components/example-global-growl-cancelled-task.png
