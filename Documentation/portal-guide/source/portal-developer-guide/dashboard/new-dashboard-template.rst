.. _configure-new-dashboard-template:

Configure Dashboard Template
============================

JSON Definition
---------------

Below is a sample JSON definition of a Portal dashboard template.

.. code-block:: html

   [
      {
         "id": "template-id"
         "title": "cms:/your_state_header_cms",
         "description": "Create dashboard with 1 Task list, 1 Case list, and 1 Process list",
         "icon": "si-cog-double-2",
         "dashboard": {
            "id": "dashboard_id",
            "templateId": "template-id",
            "title": "Default dashboard",
            "permissions": ["Everybody"]
         }
      }
   ]

..

The structure of JSON of dashboard template

   ``id``: ID to identify the template, it is a **mandatory** field and has to
   be **unique**.

   ``title``: Title for the template in dialog :guilabel:`Select your template`.
   You can enter a string, or create a multilingual title by prefixing your CMS
   URI with ``cms:``.

   ``description``: Description of the template in the :guilabel:`Select your
   template` dialog. You can enter a string, or create a multilingual
   description by prefixing your CMS URI with ``cms:``.

   ``icon``: Icon of the template item in dialog :guilabel:`Select your
   template`. Portal only supports `Streamline icons
   <https://dev.demo.ivyteam.io/demo-app/faces/view/html-dialog-demos$1/icons.xhtml>`_.

   ``dashboard``: Details of the dashboard will be used as the template. Refer
   to :ref:`Configure dashboard <customization-new-dashboard>` for details
   related to dashboard configurations.