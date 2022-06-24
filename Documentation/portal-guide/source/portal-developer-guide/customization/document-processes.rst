.. _customization-document-processes:

Document processes
==================

.. _customization-document-processes-introduction:

Introduction
------------

When you upload document but want to manage them outside ivy, for
example: in Document Management System (DMS), you should follow this
section to override document functions of Portal.

.. _customization-document-processes-customization:

Customization
-------------

#. Introduce an Axon Ivy project which has ``PortalTemplate`` as a
   required library.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. This process is new home page and administrator should
   register this link by global

#. Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

#. Override 4 ``Document subprocesses`` described in table below to
   customize document functions.

   .. table::

    +-----------------------------------+-----------------------------------+
    | Subprocess                        | Description                       |
    +===================================+===================================+
    | GetDocumentList                   | After get document list from DMS, |
    |                                   | convert them into                 |
    |                                   | List<ch.ivy.addon.portalkit.ivyda |
    |                                   | ta.bo.IvyDocument>                |
    |                                   | Mandatory fields are:             |
    |                                   |                                   |
    |                                   | - id                              |
    |                                   | - name                            |
    |                                   | - contentType                     |
    +-----------------------------------+-----------------------------------+
    | UploadDocument                    | Override this subprocess to       |
    |                                   | upload your file. This sub        |
    |                                   | process also contains some        |
    |                                   | validations, so if you override   |
    |                                   | it, you have to implement         |
    |                                   | validation by your own.           |
    +-----------------------------------+-----------------------------------+
    | DownloadDocument                  | Override this subprocess to       |
    |                                   | download file from DMS.           |
    +-----------------------------------+-----------------------------------+
    | DeleteDocument                    | Override this subprocess to       |
    |                                   | delete file in DMS                |
    +-----------------------------------+-----------------------------------+
