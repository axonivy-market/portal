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

#. **Deploy**

   Deploy your project together with Portal, then open :guilabel:`My profile` in Portal and select the new language to verify the translations.

.. warning::
   This guide covers CMS content only. Some user-visible texts are not CMS entries: the default dashboard titles and widgets are defined in the ``portal/config/variables/`` JSON files and in ``DefaultDashboardUtils.java``,
   which contain locale/value pairs only for ``en``, ``de``, ``fr``, ``es`` and ``ja``.
   Without further changes, users can select the new language but the default dashboards remain in a fallback language.
   To translate them too, follow all steps in :ref:`settings-language`.

.. tip::
   Content Objects without a value in the new language are displayed in the default language.
   Translate all entries that users see before you release the language.

.. note::
   Changes to CMS content may be cached. If the translations do not appear immediately, clear the engine cache or redeploy your project.
