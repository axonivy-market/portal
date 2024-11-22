.. _customization-businessdetailspage:

Business Details Page
=====================

.. _customization-additionalcasedetailspage.introduction:

Introduction
------------

The default business details page shows all custom fields of a case. Clicking on
``Business details`` in the case details page to open.

Portal supports customizing this page for each case.

.. _customization-additionalcasedetailspage.customization:

How to customize the business details page
------------------------------------------

#. Create the business details process. In this process, define a request start event that accepts the ``uuid`` parameter. 
   When the ``Business details`` button on the case details page is clicked, Portal will call this process and pass the case UUID as 
   the ``uuid`` parameter. Design and implement the user interface for the business details page within this process.

   |customization-business-details-page-start-request|

#. Store the :dev-url:`IWebStartable ID </doc/|version|/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html#getId()>` of the 
   process to the string custom field ``businessDetails`` of the case. There are 2 ways to perform this:
   
   * Use the ``SetBusinessDetailsPage.p.json`` callable process, and pass the IWebStartable ID of the business details process as the 
     parameter. To make it more flexible, Portal supports passing the end part of IWebStartable ID as the parameter 
     but you need to ensure that only one process in the security context has the IWebStartable ID that ends with the parameter.

      |set-business-details-page-callable-process|

   * Use public API ``ch.ivy.addon.portalkit.publicapi.BusinessDetailsAPI.create(String)`` or ``ch.ivy.addon.portalkit.publicapi.BusinessDetailsAPI.create(BusinessDetailsDTO)``. 
     See the Public API section for more details.

      |customize-business-details-with-public-api|

      .. tip:: 
         The business details page also supports external links in case the business details site is outside of |ivy|.
         You can replace the path with any URL. Portal will take care of the rest. E.g., ``BusinessDetailsAPI.create("https://google.com")``

Customization
-------------
-  If your custom Business Details page uses an IFrame, you could configure the UI accordingly. Refer to :ref:`iframe-configure-template` for more information.

-  Using the Public API, you can control whether the business details page will start inside IFrame or not by setting the ``BusinessDetailsDTO.builder().isEmbedInFrame(Boolean)`` value to 
   
   	- ``true``: start inside IFrame (default value)
   	- ``false``: not start inside IFrame

-  You can also customize the ``ICase`` value. By default, the API will get ``ICase`` from ``Ivy.wfCase()``. Modify it using ``BusinessDetailsDTO.builder().iCase(ICase)``

   |start-business-details-page-iframe|

- Behind the scenes, the API will set the IWebStartable ID to the string custom field ``businessDetails``. If you do a deep customization, 
  you could find IWebStartable ID of the business details process, add URL query string then set it to the string custom 
  field ``businessDetails`` of the case.

.. note::

   - Ensure the new UI aligns with the overall design and functionality requirements of your application.

   - Test the integration thoroughly to confirm that the case information is accurately passed and displayed.

   - When using external links, verify that the links are secure and accessible from your application environment.

Permission Setting
------------------

Configure permissions in the :dev-url:`Engine Cockpit
</doc/|version|/engine-guide/reference/engine-cockpit/security.html>`. In the security area, open PortalPermissions -> PortalCasePermissions -> ShowCaseDetails.

Or search :bdg-ref-warning:`🔑ShowCaseDetails <ShowCaseDetails>` in the permissions search bar. By default, this permission is set to ``true`` for the role ``Everybody``.


.. |start-business-details-page-iframe| image:: images/business-details-page/start-business-details-page-iframe.png
.. |set-business-details-page-callable-process| image:: images/business-details-page/set-business-details-page-callable-process.png
.. |customize-business-details-with-public-api| image:: images/business-details-page/customize-business-details-with-public-api.png
.. |customization-business-details-page-start-request| image:: images/business-details-page/customization-business-details-page-start-request.png