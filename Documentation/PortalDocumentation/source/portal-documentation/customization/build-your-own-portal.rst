.. _axonivyportal.customization.buildyourownportal:

Build your own portal
=====================

.. _axonivyportal.customization.buildyourownportal.buildyourownportalusingportalkit:

Build your own portal using Portal kit
--------------------------------------

1. Create a new project that depends on ``PortalTemplate``.

2. Create new home page

   1. Create a new ``HTML Dialog`` for your home page and then use
      ``ui:composition`` to define template which you use inside. If you
      want to keep the look of default homepage, then choose
      ``DefaultHomePageTemplate``.

         **Tip**

         Find more information about templates at
         .

      ..

         **Important**

         Portal uses some template files in the folder
         "webContent/layouts"
         in Portal Template project. Do not create files with the same
         path and name in your project as they can override the Portal
         files.

   2. Create a new ``Start process`` and connect to ``User Dialog`` for
      your home page.

   3. Run your application, start your newly created process and see
      result.

         **Tip**

         Your new homepage is the default portal homepage. You can
         customize it. Reference at
         .

3. Set category for tasks

   To categorize tasks, set values for ``Category`` field. Task category
   can be multi-level if it is separated by slash ``/`` signs, as below.

4. How to use feature warning on closing browser/tab

   Sometimes when users are working on a task, if they close tab,
   browser or refresh page then they may lose their current work. It's a
   good idea to ask users to verify that they truly want to proceed with
   the action they just invoke. To use this feature, add
   ``WarnOnClosingBrowserTab`` component to the page you want to be
   affected.

   ``<ic:ch.ivy.addon.portalkit.feature.WarnOnClosingBrowserTab confirmMessage="You may lose your work in progress. Do you want to continue?" />``

   Depending on browser, content of ``confirmMessage`` and names of
   buttons may vary.

      **Tip**

      Normally, when using this component, actions that user invoked
      outside Portal area like closing tab/browser, refreshing page,
      clicking on a link on bookmark bar of browser will cause browser
      to display a confirmation dialog. That might cause the feature
      work incorrectly.

      Actions that lead to navigation that user invoke inside Portal
      area like clicking on a link/button, submitting a form will not
      display any confirmation popup.

      In some cases, user might use javascript to navigate to another
      page, for example: set value for ``window.location.href`` or call
      ``location.reload()``.

      If that happens, add this to your javascript function:
      ``showConfirmDialogBeforeUnload = false;``
