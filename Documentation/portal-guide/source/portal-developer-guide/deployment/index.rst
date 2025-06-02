.. _deployment:

Deployment
**********

This section provides a step-by-step guide for deploying the Portal for the first time.
It includes examples and best practices. For more comprehensive deployment information, refer to the
:dev-url:`|ivy| deployment guidelines </doc/|version|/engine-guide/deployment/index.html>`

The following is an overview of a sample deployment setup:

- The Portal application includes the portal-components and portal projects.
- The Business application includes the shared portal-components and customer-specific projects (e.g., accounting).

.. code-block:: text

  +----------------------------------------------------+
  |                   Security system                  |
  |                                                    |
  |  +---------------------+  +---------------------+  |
  |  | Portal              |  | Accounting          |  |
  |  |                     |  |                     |  |
  |  |   portal-components |  |   portal-components |  |
  |  |   portal            |  |   accounting        |  |
  |  +---------------------+  +---------------------+  |
  |                                                    |
  +----------------------------------------------------+

Prerequisites
=============

Before you begin, ensure you have:

- Properly configured databases (e.g., PostgreSQL) ready for connection.
- The Axon Ivy Engine is installed and operational.

Installation
============

Preparation
-----------

- Axon Ivy projects are packaged as either .iar (Ivy Archive) files or .zip files for deployment.
- We recommend using .zip files, as they can include:
  :dev-url:`branding </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>`, 
  variables, databases, REST clients :dev-url:`configuration </doc/|version|/engine-guide/deployment/prepare/application-configuration/index.html>`.

Recommended Deployment Setup (Sample)
-------------------------------------

Prepare two zip files for deployment:

- Portal Application Zip File, containing:

  - portal-components
  - portal
  - Branding, variables configuration
- Business Application Zip File (e.g., Accounting), containing:

  - portal-components
  - accounting
  - Branding, variables, database, REST clients configuration

Deployment
----------

- Create a security system, or use the default one provided by |ivy|. This security system will be shared across applications.
- Create applications:

  - One for the Portal, e.g., named Portal
  - One or more for business projects, e.g., named Accounting
- Deploy the application packages:

  - Deploy the Portal application zip file (or .iar files) to the Portal application.
  - Deploy the business application zip file (or .iar files) to the corresponding business application (e.g., Accounting).

Configuration
=============

Configuration can be done either through 
:dev-url:`deployment </doc/|version|/engine-guide/deployment/prepare/application-configuration/index.html>` 
or via the :dev-url:`Engine Cockpit UI </doc/|version|/engine-guide/reference/engine-cockpit>`.
This section highlights the most important configuration settings.

Dashboard Configuration
-----------------------

There are three ways to configure the Portal Dashboard:

- Deployment - using pre-configured .zip files
- Engine Cockpit UI - via the Axon Ivy Engine's administrative interface
- Portal Import Feature - using the built-in import functionality within the Portal

**Deployment Configuration**
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To configure the Portal Dashboard via deployment, include the configuration file in the deployment package:

- Place the `Portal.Dashboard.json` file within the `config/variables/` directory inside the application zip.
  
  .. code-block::

     your-app.zip
     ├── config
     │   └── app.yaml
     │   └── variables
     │       └── Portal.Dashboard.json
     ├── portal.iar
     ├── portal-components.iar

- Alternatively, copy the configuration file directly into the engine's application folder:
  ``<engine>/configuration/applications/<application>/variables.Portal.Dashboard.json``

**Using Engine Cockpit UI**
^^^^^^^^^^^^^^^^^^^^^^^^^^^

To configure the Portal Dashboard via the Engine Cockpit UI:

- Log in to the Engine Cockpit UI.
- Navigate to the **Configuration** section.
- Select **Variables** and locate the `Portal.Dashboard` entry.
- Edit the configuration in the UI.

**Using Portal Import Dashboard Feature**
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To import dashboards using the Portal interface:

- Open the Portal application.
- Select the **Add new Dashboard** button.
- Click on the **Import** icon.
- In the **Import Dashboard** dialog, drag and drop or upload the JSON file containing the dashboard configuration.
- Edit the mandatory title and optional description for the dashboard.
- Click **Create Dashboard** to finalize the import.
- Configure the dashboard as needed and ensure it appears in the dashboard list.

.. hint::
   For more details, refer to the Dashboard configuration: :ref:`Dashboard Configuration <dashboard-configuration>`.

Other Configurations
--------------------

- Configure the variables, databases, REST clients either through deployment or via the Cockpit UI.
- Configure necessary user roles and permissions.

Final Steps
===========

- Start the application and verify successful deployment.
- Check logs for potential issues and resolve any errors.
- Validate that the Portal functions correctly and that all users have appropriate access.

Following these best practices ensures a smooth and efficient Portal deployment.

