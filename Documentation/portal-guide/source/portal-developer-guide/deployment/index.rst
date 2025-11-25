.. _deployment:

Deployment
**********

This guide provides step-by-step instructions for deploying Portal to Axon Ivy Engine, including configuration artifacts, environment setup, and best practices.

Overview
========

.. table::
   :widths: 30 70

   +----------------------------+----------------------------------------------------------------+
   | Deployment Aspect          | Description                                                    |
   +============================+================================================================+
   | **Packaging Format**       | .iar (Ivy Archive) or .zip (includes configuration)            |
   +----------------------------+----------------------------------------------------------------+
   | **Configuration Methods**  | Deployment files, Engine Cockpit UI, or Portal import          |
   +----------------------------+----------------------------------------------------------------+
   | **Architecture**           | Multi-application setup with shared security context           |
   +----------------------------+----------------------------------------------------------------+

For comprehensive deployment information, refer to the :dev-url:`Axon Ivy deployment guidelines </doc/12.0/engine-guide/deployment/index.html>`.

Deployment Architecture
=======================

The recommended deployment setup separates Portal from business applications:

**Sample Setup:**

- **Portal Application**: portal-components + portal
- **Business Application(s)**: portal-components (optional) + your projects

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

Before deploying Portal, ensure you have:

.. table::
   :widths: 30 70

   +----------------------------+----------------------------------------------------------------+
   | Requirement                | Details                                                        |
   +============================+================================================================+
   | **Axon Ivy Engine**        | Installed and operational                                      |
   +----------------------------+----------------------------------------------------------------+
   | **Database**               | Configured and accessible (e.g., PostgreSQL)                   |
   +----------------------------+----------------------------------------------------------------+
   | **Security System**        | Created or use default Axon Ivy security system                |
   +----------------------------+----------------------------------------------------------------+
   | **Deployment Packages**    | Portal .iar/.zip files prepared                                |
   +----------------------------+----------------------------------------------------------------+

Deployment Process
==================

Packaging Formats
-----------------

Axon Ivy projects can be packaged in two formats:

.. table::
   :widths: 20 40 40

   +----------------+--------------------------------+----------------------------------------+
   | Format         | Contents                       | Use Case                               |
   +================+================================+========================================+
   | **.iar**       | Project archive only           | Simple deployments                     |
   +----------------+--------------------------------+----------------------------------------+
   | **.zip**       | Projects + configuration       | Recommended for production             |
   |                | (branding, variables,          | (includes all configuration)           |
   |                | databases, REST clients)       |                                        |
   +----------------+--------------------------------+----------------------------------------+

.. tip::
   Use .zip files for production deployments. They can include :dev-url:`branding </doc/12.0/designer-guide/user-interface/branding/branding-engine.html>`, variables, databases, and REST clients :dev-url:`configuration </doc/12.0/engine-guide/deployment/prepare/application-configuration/index.html>`.

Prepare Deployment Packages
---------------------------

Create two separate zip files:

**Portal Application Package**

Contains:

- portal-components.iar
- portal.iar  
- Branding and variables configuration

**Business Application Package** (e.g., Accounting)

Contains:

- portal-components.iar (only if using Portal UI components)
- accounting.iar (your project)
- Branding, variables, database, REST clients configuration

Deploy to Engine
----------------

#. **Create or Select Security System**

   Create a new security system or use the default one provided by Axon Ivy. This security system will be shared across all applications.

#. **Create Applications**

   - One application for Portal (e.g., named "Portal")
   - One or more applications for business projects (e.g., "Accounting")

#. **Deploy Packages**

   - Deploy the Portal application package to the Portal application
   - Deploy business application packages to their corresponding applications

#. **Verify Deployment**

   - Check that all applications are in the same security context
   - Verify all .iar files are properly deployed

Configuration
=============

Configuration can be done through :dev-url:`deployment files </doc/12.0/engine-guide/deployment/prepare/application-configuration/index.html>` or the :dev-url:`Engine Cockpit UI </doc/12.0/engine-guide/reference/engine-cockpit>`.

Dashboard Configuration
-----------------------

There are three methods to configure Portal dashboards:

.. table::
   :widths: 30 70

   +--------------------------------+--------------------------------------------------------+
   | Method                         | Description                                            |
   +================================+========================================================+
   | **Deployment Files**           | Include Portal.Dashboard.json in deployment package    |
   +--------------------------------+--------------------------------------------------------+
   | **Engine Cockpit UI**          | Configure through Engine Cockpit variables             |
   +--------------------------------+--------------------------------------------------------+
   | **Portal Import Feature**      | Import dashboards directly in Portal UI                |
   +--------------------------------+--------------------------------------------------------+

**Method 1: Deployment Files**

Include the dashboard configuration in your deployment package.

Place the ``Portal.Dashboard.json`` file in the ``config/variables/`` directory:

.. code-block:: text

   your-app.zip
   ├── config
   │   ├── app.yaml
   │   └── variables
   │       └── Portal.Dashboard.json
   ├── portal.iar
   └── portal-components.iar

Alternatively, copy directly to the engine: ``<engine>/configuration/applications/<application>/variables.Portal.Dashboard.json``

**Method 2: Engine Cockpit UI**

#. Log in to the Engine Cockpit UI

#. Navigate to **Configuration** → **Variables**

#. Locate the ``Portal.Dashboard`` entry

#. Edit the configuration directly in the UI

**Method 3: Portal Import Feature**

#. Open the Portal application

#. Select **Add new Dashboard** button

#. Click the **Import** icon

#. In the **Import Dashboard** dialog, upload the JSON file

#. Edit the title and description

#. Click **Create Dashboard**

.. tip::
   For detailed dashboard configuration, see :ref:`Dashboard Configuration <dashboard-configuration>`.

Additional Configuration
------------------------

**Variables and Settings**

Configure through deployment files or Engine Cockpit:

- Portal variables (see :ref:`Admin Settings <portal-available-settings>`)
- Database connections
- REST client configurations

**User Roles and Permissions**

Set up appropriate roles and permissions for Portal features. See :ref:`Role Management <admin-settings>`.

Verification
============

After deployment, verify the setup:

#. **Start Applications**

   Start all deployed applications in the Engine

#. **Check Logs**

   Review engine logs for any errors or warnings

#. **Test Portal Access**

   - Log in to Portal
   - Verify dashboard displays correctly
   - Test task and case lists

#. **Verify Multi-App Integration**

   - Check that tasks from all applications appear in Portal
   - Verify security context is shared correctly

#. **Test User Permissions**

   Ensure users have appropriate access based on their roles

