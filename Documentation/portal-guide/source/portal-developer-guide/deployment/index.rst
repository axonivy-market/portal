.. _deployment:

Deployment
**********

Portal deployment to Axon Ivy Engine involves deploying .iar archives or .zip packages containing projects and configuration artifacts. The recommended architecture separates Portal from business applications while sharing a common security system, enabling centralized task and case management across multiple applications.

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

For comprehensive deployment information, refer to the :doc-url:`Axon Ivy deployment guidelines </engine-guide/deployment/index.html>`.

Deployment Architecture
=======================

The recommended deployment setup separates Portal from business applications within a shared security system.

**Sample Setup:**

- **Portal Application**: portal-components + portal
- **Business Application(s)**: portal-components (optional) + your projects

.. code-block:: text

   +----------------------------------------------------+
   |                   Security System                  |
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
   | **.iar**       | Project archive only           | Development, simple deployments        |
   +----------------+--------------------------------+----------------------------------------+
   | **.zip**       | Projects + configuration       | Production deployments                 |
   |                | (branding, variables,          | (includes all configuration artifacts) |
   |                | databases, REST clients)       |                                        |
   +----------------+--------------------------------+----------------------------------------+

.. tip::
   Use .zip files for production deployments. They bundle :doc-url:`branding </designer-guide/user-interface/branding/branding-engine.html>`, variables, databases, and REST clients :doc-url:`configuration </engine-guide/deployment/prepare/application-configuration/index.html>` with your projects.

Prepare Deployment Packages
---------------------------

Create separate deployment packages for each application.

**Portal Application Package**

Contains:

- ``portal-components.iar``
- ``portal.iar``
- Branding configuration (optional)
- Variables configuration (optional)

**Example Structure:**

.. code-block:: text

   portal-app.zip
   ├── config
   │   ├── app.yaml
   │   ├── branding
   │   │   └── logo.png
   │   └── variables
   │       └── Portal.Dashboard.json
   ├── portal-components.iar
   └── portal.iar

**Business Application Package** (e.g., Accounting)

Contains:

- ``portal-components.iar`` (only if using Portal UI components)
- ``accounting.iar`` (your project)
- Branding, variables, database, REST clients configuration

**Example Structure:**

.. code-block:: text

   accounting-app.zip
   ├── config
   │   ├── app.yaml
   │   ├── databases
   │   │   └── accounting-db.yaml
   │   ├── rest-clients
   │   │   └── external-api.yaml
   │   └── variables
   │       └── Accounting.Settings.json
   ├── portal-components.iar
   └── accounting.iar

Deploy to Engine
----------------

**Step 1: Create or Select Security System**

Create a new security system or use the default one provided by Axon Ivy. This security system will be shared across all applications.

In Engine Cockpit:

- Navigate to **Security Systems**
- Create a new security system or select the default ``Axon Ivy``
- Note the security system name for application creation

**Step 2: Create Applications**

Create separate applications within the security system:

- One application for Portal (e.g., named ``Portal``)
- One or more applications for business projects (e.g., ``Accounting``, ``HR``)

In Engine Cockpit:

- Navigate to **Applications**
- Click **New Application**
- Select the security system created in Step 1
- Provide application name and settings
- Repeat for each application

**Step 3: Deploy Packages**

Deploy the prepared .zip packages to their corresponding applications:

- Deploy the Portal application package to the Portal application
- Deploy business application packages to their corresponding applications

**Deployment Methods:**

Via Engine Cockpit UI:
   Navigate to **Applications** → Select Application → **Deploy** → Upload .zip file

Via File System:
   Copy .zip file to ``<engine>/deploy/`` directory (auto-deployed)

Via Command Line:
   Use Axon Ivy CLI deployment commands

**Step 4: Verify Deployment**

After deployment, verify the setup:

- All applications show ``RUNNING`` status in Engine Cockpit
- All applications are in the same security system
- All .iar files are properly deployed and visible in application details
- No errors in engine logs (``<engine>/logs/``)

Configuration
=============

Portal configuration can be managed through multiple methods.

Configuration can be done through :doc-url:`deployment files </engine-guide/deployment/prepare/application-configuration/index.html>` or the :doc-url:`Engine Cockpit UI </engine-guide/reference/engine-cockpit>`.

Dashboard Configuration
-----------------------

There are three methods to configure Portal dashboards:

.. table::
   :widths: 35 65

   +---------------------------------+---------------------------------------------------------+
   | Method                          | Description                                             |
   +=================================+=========================================================+
   | **Deployment Files**            | Include Portal.Dashboard.json in deployment package     |
   +---------------------------------+---------------------------------------------------------+
   | **Engine Cockpit UI**           | Configure through Engine Cockpit variables              |
   +---------------------------------+---------------------------------------------------------+
   | **Portal Import Feature**       | Import dashboards directly in Portal UI                 |
   +---------------------------------+---------------------------------------------------------+

Method 1: Deployment Files
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Include the dashboard configuration in your deployment package.

**Package Structure:**

Place the ``Portal.Dashboard.json`` file in the ``config/variables/`` directory:

.. code-block:: text

   your-app.zip
   ├── config
   │   ├── app.yaml
   │   └── variables
   │       └── Portal.Dashboard.json
   ├── portal.iar
   └── portal-components.iar

**Alternative: Direct File Copy**

Copy directly to the engine configuration directory:

``<engine>/configuration/applications/<application>/variables.Portal.Dashboard.json``

Method 2: Engine Cockpit UI
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Configure dashboards through the Engine Cockpit web interface.

**Steps:**

- Log in to the Engine Cockpit UI.
- Navigate to **Configuration** → **Variables**.
- Locate the ``Portal.Dashboard`` entry.
- Edit the configuration directly in the UI.
- Save changes.

Method 3: Portal Import Feature
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Import dashboard configurations directly in the Portal user interface.

**Steps:**

- Open the Portal application.
- Select **Add new Dashboard** button.
- Click the **Import** icon.
- In the **Import Dashboard** dialog, upload the JSON file.
- Edit the title and description.
- Click **Create Dashboard**.

.. tip::
   For detailed dashboard configuration including widget types, layouts, and properties, see :ref:`Dashboard Configuration <dashboard-configuration>`.

Additional Configuration
------------------------

Variables and Settings
^^^^^^^^^^^^^^^^^^^^^^

Configure Portal variables and settings through deployment files or Engine Cockpit.

**Configuration Options:**

Portal Variables
   Application-specific settings. See :ref:`Admin Settings <portal-available-settings>`.

Database Connections
   Configure in ``config/databases/`` directory.

REST Client Configurations
   Configure in ``config/rest-clients/`` directory.

**Configuration Methods:**

Via Deployment Files:
   Include configuration files in ``config/`` directory of your .zip package

Via Engine Cockpit:
   Navigate to **Configuration** → **Variables** / **Databases** / **REST Clients**

User Roles and Permissions
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Set up appropriate roles and permissions for Portal features.

Portal uses Axon Ivy's built-in security system. Users, roles, and permissions are managed at the security system level.

**Configuration Areas:**

- User role assignments
- Permission mappings for Portal features
- Task and case visibility rules
- Dashboard access controls

See :ref:`Role Management <admin-settings>` for detailed permission configuration.

Verification
============

After deployment, perform verification to ensure Portal and business applications are properly configured.

Post-Deployment Checklist
--------------------------

**1. Start Applications**

- Navigate to **Applications** in Engine Cockpit
- Verify all applications show ``RUNNING`` status
- Check application startup time

**2. Check Logs**

Review engine logs for any errors or warnings:

- Engine logs: ``<engine>/logs/ivy.log``
- Application logs: ``<engine>/logs/<application>/``
- Look for deployment errors, configuration issues, or startup warnings

**3. Test Portal Access**

- Navigate to Portal URL (e.g., ``http://localhost:8080/portal``)
- Log in with a valid user account
- Verify dashboard displays correctly with configured widgets
- Test task and case lists load without errors
- Verify navigation and menu items are accessible

**4. Verify Multi-App Integration**

- Tasks from all applications appear in Portal task list
- Cases from all applications appear in Portal case list
- Security context is shared correctly
- Users see only tasks/cases they have permission to access
- Processes can be started from Portal

**5. Test User Permissions**

- Log in as different user roles
- Verify role-specific feature access
- Test permission-based task visibility
- Verify dashboard and widget access controls

Troubleshooting Common Issues
------------------------------

**Applications Not Starting**

- Check for dependency issues in logs
- Verify all required .iar files are deployed
- Ensure database connections are configured correctly

**Tasks Not Appearing**

- Verify applications are in the same security system
- Check user role assignments and permissions
- Review task visibility rules in business applications

**Configuration Not Applied**

- Verify configuration files are in correct directory
- Check variable names match expected format (case-sensitive)
- Restart applications after configuration changes

