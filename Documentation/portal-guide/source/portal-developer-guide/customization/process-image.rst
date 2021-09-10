.. _customization-process-image:

Process image
=====================

.. _customization-process-image-introduction:

Introduction
------------

When you want to customize process image, you should follow this section
to override process image of Portal.

.. _customization-process-image-customization:

Define your own process image
-------------------------------------

#. Create a CMS object with image type in your project

#. Define a custom field ``processImage`` in :guilabel:`Custom Fields` of the process start. Value of this custom field is URI path of image CMS.

   |define-process-image|

#. Go to full process list page and search your customized process

   |image-process-list|

.. |define-process-image| image:: images/process-image/define-process-image.png
.. |image-process-list| image:: ../../screenshots/process-image/customization/image-process-list.png