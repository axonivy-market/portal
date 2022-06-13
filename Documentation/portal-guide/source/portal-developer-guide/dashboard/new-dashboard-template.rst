.. _configure-new-dashboard-template:

Configure Dashboard template
============================

JSON definition
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

   ``id``: ID to identifying template, it's **mandatory** field and must be **unique**.

   ``title``: Title for the template item in the :guilabel:`Select your template` dialog. You can input a string, or use CMS by using prefix ``cms:`` before your CMS URI to define title in multilingual.

   ``description``: Description of the template item in the :guilabel:`Select your template` dialog. You can input a string, or use CMS by using prefix ``cms:`` before your CMS URI to define description in multilingual.

   ``icon``: Icon of the template item in the :guilabel:`Select your template` dialog. Portal only supports `Streamline icons <https://dev.demo.ivyteam.io/demo-app/faces/view/html-dialog-demos$1/icons.xhtml>`_.

   ``dashboard``: Details of the dashboard will be used as the template. Refer to :ref:`Configure dashboard <customization-new-dashboard>` for more details related to dashboard configurations.