.. _customization-express-endpage:

Express end page (Deprecated)
=============================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

.. _customization-express-endpage-introduction:

Introduction
------------

When the last task of Axon Express finish, express end page will be
displayed. You can customize this by provide your own page.

.. _customization-express-endpage-customization:

Customization
-------------

#. Create a new UI and start link of the new end page.

   |new-end-page|

#. Create a callable subprocess in your project with the
   ``handleEndPage()`` signature, make sure this signature is unique in
   your application. It must return start link of new end page you
   define in step 1.

   |handle-express-end-page|

.. |new-end-page| image:: images/express-end-page/new-end-page.png
.. |handle-express-end-page| image:: images/express-end-page/handle-express-end-page.png

