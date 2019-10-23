.. _axonivyportal.customization.expressexternaldataprovider:

Express external data provider
==============================

.. _axonivyportal.customization.expressexternaldataprovider.introduction:

Introduction
------------

In portal express we can provide the external data for checkbox values
when creating form, like a product catalogue can be maintained in the
background and the process calls this data provider.

.. _axonivyportal.customization.expressexternaldataprovider.customization:

Customization
-------------

1. Create a callable subprocess that has signature
   "portalExpressDataProvider"

   |image0|

   The subprocess return the list of String:

   |image1|

   Below is an example that use Database element to read the data from
   DB as a data provider.

   In the DB tab:

   -  Kind of Query: choose "Read query"

   -  Database: select the database name

   -  Table: select the table name

   -  Fields: choose "Specified Fields" and tick one column that use for
      data provider.

   |image2|

   In the Output tab, set the value for the output variable:

   ``out.items = recordset.getColumn("[name of the column]")``

   |image3|

2. Create new Express Workflow

   In the form creation, choose Checkbox and select the data provider in
   the dropdown list. Then fill the label of check box and press Create
   button. You can drag and drop the checkbox element to the Placement
   of form elements and preview the values of the checkbox.

   Note: The first item in the dropdown list is "Special static data
   provider", it means that user will provide the values of the checkbox
   manually.

   |image4|

.. |image0| image:: images/ExpressExternaldataprovider/CallableSubProcess.png
.. |image1| image:: images/ExpressExternaldataprovider/CallableSubProcessResult.png
.. |image2| image:: images/ExpressExternaldataprovider/Database.png
.. |image3| image:: images/ExpressExternaldataprovider/DatabaseOutput.png
.. |image4| image:: images/ExpressExternaldataprovider/ExpressWorkflow.png

