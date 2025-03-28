.. _deployment:

Deployment
************

This section provides a step-by-step guide for deploying the Portal for the first time.
It is written with beginners in mind and includes prerequisites, a quick start guide, and detailed instructions.

Prerequisites
=============
Before you begin, ensure you have:
- Java 21 and Maven 3.9.8 installed.
- A properly configured database (e.g., PostgreSQL) ready for connection.
- An available Axon Ivy Engine package (IAR or ZIP).

==================
Project Modules
---------------

The application consists of essential process modules required for deployment. These include:

- portal-components
- portal
- your application module (e.g., your-application)

Ensure that these modules are prepared and ready for deployment.

Installation
------------

Preparation
^^^^^^^^^^^

Before deployment, ensure the following:

- The Axon Ivy project is available as an ivy-archive (IAR) or a packed zip-archive (ZIP).
- The application zip contains all required dependencies to ensure a smooth deployment process.
- A properly configured database, such as PostgreSQL, is available and ready to be connected.

Deployment
^^^^^^^^^^

- Deploy the application by placing the prepared archive into the designated deployment directory of the engine.
- Ensure the necessary configurations are applied, including database connection settings and user access controls.

Configuration
=============

Dashboard Configuration
------------------------

There are two ways to configure the Portal Dashboard: manually or using the Engine Cockpit UI.

**Manual Configuration**
^^^^^^^^^^^^^^^^^^^^^^^^

To configure the Portal Dashboard manually, include the configuration file in the deployment package.

- Place the `Portal.Dashboard.json` file within the `config/variables/` directory inside the application zip.
  
  .. code-block::

     app.zip
     ├── config
     │   └── app.yaml
     |   └── variables
     |       └── Portal.Dashboard.json
     ├── portal.iar
     ├── portal-components.iar

- Alternatively, copy the configuration file directly into the engine’s application folder:
  ``<engine>/configuration/applications/<application>/variables.Portal.Dashboard.json``

**Using Engine Cockpit UI**
^^^^^^^^^^^^^^^^^^^^^^^^^^^

To configure the Portal Dashboard using the Engine Cockpit UI:

1. Log in to the Engine Cockpit UI.
2. Navigate to the **Configuration** section.
3. Select **Variables** and locate the `Portal.Dashboard.json` entry.
4. Upload or edit the configuration file directly within the UI.
5. Save changes and restart the application if necessary.

.. hint:: 
   Please reference to the Cockpit guide for more details on how to configure using the Engine Cockpit UI. 
   :dev-url:`Cockpit </doc/|version|/engine-guide/reference/engine-cockpit>`.


**Using Portal Import Dashboard Feature**
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To import dashboards using the Portal interface:

1. Open the Portal application.
2. Select the **Add new Dashboard** button.
3. Click on the **Import** icon.
4. In the **Import Dashboard** dialog, drag and drop or upload the JSON file containing the dashboard configuration.
5. Edit the mandatory title and optional description for the dashboard.
6. Click **Create Dashboard** to finalize the import.
7. Configure the dashboard as needed and ensure it appears in the dashboard list.

Database Configuration
----------------------

- Ensure the database connection details are properly set in the configuration files.
- Verify that the database schema is up to date before starting the application.
- Configure necessary user roles and permissions within the database.

Multi-Application Setup
-----------------------

.. important::

   This setup enables common task lists across multiple applications.

- Deploy the Portal (portal, portal-components) to a main application.
- Deploy additional projects separately while ensuring all applications share the same security context.
- Only one instance of the Portal is required for multiple applications. One Portal per one security context.

Final Steps
===========

- Start the engine and verify successful deployment.
- Check logs for potential issues and resolve any errors.
- Validate that the Portal functions correctly and that all users have appropriate access.

Following these best practices ensures a smooth and efficient Portal deployment.

