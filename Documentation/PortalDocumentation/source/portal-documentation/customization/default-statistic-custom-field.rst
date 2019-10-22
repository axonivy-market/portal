.. _axonivyportal.customization.defaultcustomfield:

Default statistic custom field
==============================

.. _axonivyportal.customization.defaultcustomfield.introduction:

Introduction
------------

In Portal statistic, when user add new chart, statistic filter will
displays default 5 custom string fields ``CustomVarCharField1`` to
``CustomVarCharField5`` , you can customize them by your own custom
string fields.

.. _axonivyportal.customization.defaultcustomfield.customization:

Customization
-------------

Create an override which overrides sub process
``createDefaultStatisticCustomField()`` in Portal Kit. This sub process
return a list of custom string fields. Follow instruction to create
default custom fields.
