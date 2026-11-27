.. _customization-add-new-language:

Add a New Language
******************

Portal ships with English (``en``), German (``de``), French (``fr``), Spanish (``es``) and Japanese (``ja``).
To add another language, for example Italian (``it``), add a CMS file for it to a customization project.

.. note::
   Do not edit the delivered Portal projects. Your changes would be lost on every Portal update.

HowTo: Add a New Language to Portal
===================================

#. Use a customization project that has the Portal project as a required project, or create one.

#. Create the file ``cms/cms_it.yaml`` in the ``cms/`` folder of that project (pattern: ``cms_<language code>.yaml``).
   Copy the keys you want to translate from ``cms_en.yaml`` of the Portal project, keep the same structure, and enter the translated values.

   .. code-block:: yaml

      ch.ivy.addon.portalkit.ui.jsf:
        AbsenceAndDeputy:
          absences: Assenze
          addAbsence: Aggiungi assenza

#. Deploy the project to the same application as Portal.

#. In Portal, click your avatar, open :guilabel:`My profile` and select the new language.

.. tip::
   Entries without a value in the new language are displayed in the default language.

Optional: Translate the Default Dashboards
==========================================

The default task list and case list dashboards are not CMS entries and stay in a fallback language for ``it``.
To translate them, add dashboards with the IDs ``default-task-list-dashboard`` and ``default-case-list-dashboard`` to the **Portal.Dashboard** variable of your customization project,
including an ``it`` entry in every ``titles`` and widget ``names`` array.
See :ref:`customization-dashboards-customization` for the JSON structure.
