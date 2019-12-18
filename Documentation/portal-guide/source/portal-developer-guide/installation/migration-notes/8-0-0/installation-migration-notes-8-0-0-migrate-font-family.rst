.. _migrate-font-family:

Migrate font family
^^^^^^^^^^^^^^^^^^^
Roboto font usage is changed since we upgraded to Serenity theme. Therefore we need to adapt ``font-family`` declarations.

.. table:: 

   +----------------------------------+----------------------------------+
   | 7.x                              | 8.0.0                            |
   +==================================+==================================+
   | font-family: "robotolight";      | font-family: "Roboto";           |
   |                                  |                                  |
   |                                  | font-style: normal;              |
   |                                  |                                  |
   |                                  | font-weight: 300;                |
   +----------------------------------+----------------------------------+
   | font-family: "robotoregular";    | font-family: "Roboto";           |
   |                                  |                                  |
   |                                  | font-style: normal;              |
   |                                  |                                  |
   |                                  | font-weight: 400;                |
   +----------------------------------+----------------------------------+
   | font-family: "robotolight";      | font-family: "Roboto";           |
   |                                  |                                  |
   |                                  | font-style: normal;              |
   |                                  |                                  |
   |                                  | font-weight: 300;                |
   +----------------------------------+----------------------------------+
   | font-family: "robotomedium";     | font-family: "Roboto";           |
   |                                  |                                  |
   |                                  | font-style: normal;              |
   | font-family: "robotobold";       |                                  |
   |                                  | font-weight: 700;                |
   +----------------------------------+----------------------------------+


.. important:: 

   If you want to synchronize font with Portal, please use variables defined in variables.scss. 
   
   For example:

   - font-family: $portalFontFamily

   - font-weight: $defaultFontWeight

   - font-style: $defaultFontStyle