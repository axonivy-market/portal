.. _customization-add-new-language:

Add a New Language
******************

Portal ships with English (``en``), German (``de``), French (``fr``), Spanish (``es``) and Japanese (``ja``).
To add another language, for example Italian (``it``), provide the translated Portal CMS entries in your customization project.

.. note::
   Do not edit the delivered Portal projects. Your changes would be lost on every Portal update.

HowTo: Add a New Language to Portal
===================================

#. **Create a customization project**

   - Create an Axon Ivy project that has the Portal project as a required project.
   - If you already have a project for Portal customization, reuse it.

#. **Translate the Portal CMS entries**

   - Create a file ``cms/cms_it.yaml`` in the ``cms/`` folder of your customization project.
     The file name follows the pattern ``cms_<language code>.yaml``, like ``cms_de.yaml`` in Portal.
   - Copy the CMS keys you want to translate from ``cms_de.yaml`` or ``cms_en.yaml`` of the Portal project and enter the translated values.
     Keep the same nested structure and key names.

   .. code-block:: yaml

      ch.ivy.addon.portalkit.ui.jsf:
        AbsenceAndDeputy:
          absences: Assenze
          addAbsence: Aggiungi assenza

#. **Translate the default dashboards**

   The default task list and case list dashboards are not CMS entries. Portal creates them from built-in definitions with ``en``, ``de``, ``fr``, ``es`` and ``ja`` texts only,
   and only if the **Portal.Dashboard** variable has no dashboard with the ID ``default-task-list-dashboard`` or ``default-case-list-dashboard``.
   To provide ``it`` texts without editing Portal, define these dashboards yourself:

   - In the **Portal.Dashboard** variable of your customization project, add dashboards with the IDs ``default-task-list-dashboard`` and ``default-case-list-dashboard``.
   - Add an ``it`` entry to each ``titles`` array and to each widget ``names`` array.
     See :ref:`customization-dashboards-customization` for the JSON structure.

   .. code-block:: javascript

      {
        "id": "default-task-list-dashboard",
        "titles": [
          { "locale": "en", "value": "Tasks" },
          { "locale": "it", "value": "Attività" }
        ]
      }

   .. note::
      The example is shortened. Copy the complete default definition (columns, layout, widgets) and add the ``it`` values.
      Portal then uses your dashboards instead of the built-in ones.
      Other dashboards and widgets you define need ``it`` values in the same way.

#. **Deploy**

   Deploy your customization project to the **same application** as Portal. Portal reads CMS entries and variables only from projects of its own application.
   Then open :guilabel:`My profile` in Portal and select the new language to verify the translations.

.. tip::
   Content Objects without a value in the new language are displayed in the default language.
   Translate all entries that users see before you release the language.

.. note::
   Changes to CMS content may be cached. If the translations do not appear immediately, clear the engine cache or redeploy your project.
