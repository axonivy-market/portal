.. _axonivyportal.customization.expressendpage:

Express end page
================

.. _axonivyportal.customization.expressendpage.introduction:

Introduction
------------

When the last task of Axon Express finish, express end page will be
displayed. You can customize this by provide your own page.

.. _axonivyportal.customization.expressendpage.customization:

Customization
-------------

1. Create a new UI and start link of the new end page.

   |image0|

2. Create a callable sub process in your project with the
   ``handleEndPage()`` signature, make sure this signature is unique in
   your application. It must return start link of new end page you
   define in step 1.

   |image1|

.. |image0| image:: images/ExpressEndPage/NewEndPage.png
.. |image1| image:: images/ExpressEndPage/HandleExpressEndPage.png

