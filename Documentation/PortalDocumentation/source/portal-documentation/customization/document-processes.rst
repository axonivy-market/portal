.. _customization-document-processes:

Document processes
==================

.. _customization-document-processes-introduction:

Introduction
------------

When you upload document, but want to manage them outside ivy, for
example: in Document Management System (DMS), you should follow this
section to override document functions of Portal.

.. _customization-document-processes-customization:

Customization
-------------

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. This process is new home page and administrator should
   register this link by global

3. Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

4. Override 4 ``Document sub processes`` described in table below to
   customize document functions.

.. table:: Document sub processes

   +-----------------------------------+-----------------------------------+
   | Sub process                       | Description                       |
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
   | UploadDocument                    | Override this sub process to      |
   |                                   | upload your file. This sub        |
   |                                   | process also contains some        |
   |                                   | validations, so if you override   |
   |                                   | it, you have to implement         |
   |                                   | validation by your own.           |
   +-----------------------------------+-----------------------------------+
   | DownloadDocument                  | Override this subprocess to       |
   |                                   | download file from DMS.           |
   +-----------------------------------+-----------------------------------+
   | DeleteDocument                    | Override this sub process to      |
   |                                   | delete file in DMS                |
   +-----------------------------------+-----------------------------------+
