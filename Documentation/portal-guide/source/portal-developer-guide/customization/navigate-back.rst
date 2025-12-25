.. _customization-navigateback:

Navigate Back
=============

Introduction
------------

The Navigate Back customization enables you to control navigation behavior when tasks are completed or cancelled.
Portal automatically returns users to their previous location (homepage, task list, etc.),
but you can implement custom navigation logic for specific scenarios using the PortalNavigatorInFrameAPI to redirect users to designated URLs or custom pages.

**Default Behavior:**

When a task is completed, Portal navigates back to the previous page. For example:

- If a task is started from the homepage, it redirects to the homepage
- If a task is started from a task list, it redirects to that task list after the task is finished

**Custom Navigation:**

If your project has a navigation button that does not finish a task (e.g., Cancel), you have to implement one of the following:

-  **Previous page**: Use ``PortalNavigatorInFrameAPI.navigateToPortalEndPage()``
-  **A specific URL**: Use ``PortalNavigatorInFrameAPI.navigateToUrl(String url)``
