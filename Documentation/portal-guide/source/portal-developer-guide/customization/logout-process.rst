.. _customization-logout:

Logout Process
==============

Introduction
------------

The Logout Process customization allows you to execute custom logic when users log out from Portal and customize the post-logout page displayed to users.
This enables you to implement session cleanup operations, audit logging, external system sign-outs, or redirect users to custom landing pages that match your organization's security requirements.

Portal allows you to customize:

   - Logout function when the user logs out from Portal.
   - The page that is shown to the user after he has logged out using the default logout process.

.. tip::

   Refer to process ``CustomLogoutPage`` in project ``portal-developer-examples``
   for an example of how to customize the logout process.

Customize the logout function
-----------------------------

Create a callable subprocess with

**Signature**: portalLogout

**Parameters:**

``isTaskReserve`` (java.lang.String)
   Indicates whether the task should remain reserved during logout.

``task`` (java.lang.String)
   The task identifier if logging out while working on a task.

Customize the logout page
-------------------------

Create a callable subprocess with 

**Signature**: portalGetLogoutPage

**Result:**

``logoutPage`` (java.lang.String)
   URL or path to the custom logout page to display after logout.