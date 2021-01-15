.. _customization-default-custom-field:

Default statistic custom field
==============================

.. _customization-default-custom-field-introduction:

Introduction
------------

In Portal statistic, when user add new chart, statistic filter will
displays default 5 custom string fields ``CustomVarCharField1`` to
``CustomVarCharField5`` , you can customize them by your own custom
string fields.

|default-custom-field|

.. _customization-default-custom-field-customization:

Customization
-------------

Create an override which overrides sub process
``createDefaultStatisticCustomField()`` in ``DefaultStatisticCustomField.mod`` process in PortalKit. This sub process
return a list of custom string fields. Follow instruction to create
default custom fields.

|custom-field|

.. |custom-field| image:: images/default-statistic-custom-field/custom-field.png
.. |default-custom-field| image:: ../../screenshots/statistic/chart-creation-page.png