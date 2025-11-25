.. _customization-process-image:

Process Image
=============

.. _customization-process-image-introduction:

Introduction
------------

The Process Image customization allows you to assign custom visual icons to your processes in Portal's process list and cards.
By defining a CMS image object in the process start's custom fields, you can replace the default process icons with branded images that help users quickly identify and distinguish between different processes.

.. _customization-process-image-customization:

Define Your Own Process Image
------------------------------

#. Create a CMS object with type image in your project.

#. Define a custom field ``processImage`` in :guilabel:`Custom Fields` of the process start.
   The value of this custom field is the CMS object which you created above.

   |define-process-image|

#. Go to the full process list page and search your customized process.

   |image-process-list|

.. |define-process-image| image:: images/process-image/define-process-image.png
.. |image-process-list| image:: ../../screenshots/process-image/customization/image-process-list.png