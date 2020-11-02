.. _customization-navigateback:

Navigate back
=============

.. _customization-navigateback.introduction:

When a task finishes, Portal will navigate back to previous page. For
example if a task is started from homepage, go back to homepage.
In case task is started from task list, go back to task list
after finish.

In case your project has navigation button without finishing a task, e.g Cancel, navigate

In IFrame:

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorInFrame``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrame``.
-  A specific url: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrame``.

Not in IFrame:

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigator``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigator``.

   |navigate-back|


.. |navigate-back| image:: images/navigate-back/navigate-back.png

