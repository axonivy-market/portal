.. _installation-migration-notes-8-0-0-hidden-task-case:

Migrate hidden tasks, cases
^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you use hide task/case feature, you need to follow these steps:

1. Back up your Ivy system database so you can restore if something goes wrong.
2. Deploy this project :download:`MigrateHiddenTaskCaseTo8.iar <../../documents/MigrateHiddenTaskCaseTo8.iar>` 
   to all your portal applications.
3. In each application, run start process
   ``migrateHiddenTaskCaseFromCustomField.ivp`` if you are using version from 7.0.10 or later. For older version e.g 7.0.1, run ``migrateHiddenTaskCaseFromAdditionalProperty.ivp`` process.
4. It's optional to clean up redundant data. After migration finishes
   successfully, run start process ``removeHideCustomField.ivp`` version from 7.0.10 or later. For older version e.g 7.0.1, run ``removeHideAdditionalProperty.ivp`` process. It will delete HIDE
   information of all tasks and cases in current application, so be
   careful with it.