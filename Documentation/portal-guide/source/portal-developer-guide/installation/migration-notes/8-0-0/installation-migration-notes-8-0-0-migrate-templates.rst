.. _installation-migration-notes-8-0-0-migrate-templates:

How to migrate TaskTemplate
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Since Portal 8, we introduce :ref:`IFrame <iframe-in-portal>` so that customer project could be decoupled from Portal in order to reduce migration effort.

It's ``highly recommended`` to use this IFrame feature with your own template for new feature availability and prevent migration pain in future. Refer to these steps:

1. Create a dialog with the frame-8 template in designer, or use your own template to separate your css styles from Portal

2. In HTML dialogs, use this template: adapt ``ui:composition``, ``ui:params``, ``ui:define``, etc.

3. A task is started inside IFrame as default, you can configure it, refer to :ref:`here <iframe-configuration>`.

+----------------------------------------------+------------------------------------------------------+
| Pros                                         | Cons                                                 |
+==============================================+======================================================+
| - Portal and project styles are independent  | - Highest migration effort expected for old projects |
| - Less migration effort in future            |                                                      |
+----------------------------------------------+------------------------------------------------------+

Besides this migration scenario, you can pick one of these scenarios for less migration effort but it will take more in future, so it's not recommended:

- :ref:`TaskTemplate (Deprecated) <components-layout-templates-task-template>`: include Modena Ivy theme and must be rendered inside :ref:`IFrame <iframe-in-portal>`

- :ref:`TaskTemplate-8 <components-layout-templates-task-template-8>`: include Serenity theme and no tab view predefined

- :ref:`TaskTemplate-7 (Deprecated) <components-layout-templates-task-template-7>`: keep your old template UI (including tab view) with Serenity theme

If your project has the ``<ui:insert name="content">`` tag, rename ``content`` to something else. This specific name may cause error since it is already used in Portal template.

.. _installation-migration-notes-8-0-0-basic-template:

How to migrate BasicTemplate
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

There are some changes in BasicTemplate. In the past, if you overwritten BasicTemplate ``footer`` section and copied responsive js in old BasicTemplate,
please copy the latest js in ``footer`` section of BasicTemplate.