.. _customization-additionalcasedetailspage:

Additional case detail page
============================

.. _customization-additionalcasedetailspage.introduction:

Introduction
------------

The additional case detail page shows all custom fields of a case by
clicking on ``Show business details`` link in case detail.

You can customize this page for each case by providing a relative URL to
case.

.. _customization-additionalcasedetailspage.customization:

Customization
-------------

1. Create a new additional case detail UI and a start process which
   will display the new UI.

   |customization-additional-case-details-page|

2. When create task, store the URL of start process in
   ``CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE`` custom fields of case. You
   could use ``SetAdditonalCaseDetailPage.mod`` callable process, and input the
   friendly URL of process as parameter.

   |set-additonal-case-detail-page-callable-process|


3. Only if your custom additional case page uses iFrame, then you need some more settings
Window property in your page 

- window.isHideCaseInfo = true;
- window.isHideTaskAction = true;
- window.isHideTaskName = true;
- window.isWorkingOnATask = false;

|customization-additional-case-details-page-iframe|

In your custom star process, create a custom string field name for case with name : ``embedInFrame``, value ``true``

|start-case-details-page-iframe|


.. |start-case-details-page-iframe| image:: images/additional-case-details-page/start-case-details-page-iframe.png
.. |customization-additional-case-details-page-iframe| image:: images/additional-case-details-page/customization-additional-case-details-page-iframe.png
.. |customization-additional-case-details-page| image:: images/additional-case-details-page/customization-additional-case-details-page.png
.. |set-additonal-case-detail-page-callable-process| image:: images/additional-case-details-page/set-additonal-case-detail-page-callable-process.png
