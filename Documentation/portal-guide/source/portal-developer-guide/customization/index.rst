.. _customization:

Customization
*************

Portal provides extensive customization capabilities allowing you to tailor the user interface, workflows, authentication, and business logic to match your organization's requirements.
Through callable subprocesses, custom widgets, and configuration variables, you can extend Portal without modifying its core code.

**What You Can Customize:**

- **Visual Appearance**: Logos, colors, themes, and date patterns
- **Authentication**: Password validation, reset flows, and logout behavior
- **Navigation**: Custom menu items, page headers/footers, and navigation logic
- **Task & Case Details**: Custom widgets, layouts, and information panels
- **Document Management**: Integration with external DMS systems
- **Delegation Rules**: Custom logic for determining who can delegate tasks
- **Process Display**: Custom images and information pages for processes

**Customization Approach:**

Portal customization follows a **callable subprocess pattern**: you implement subprocesses with specific signatures, and Portal automatically detects and uses them.
This approach ensures upgrades don't break your customizations.

**Getting Started:**

#. Review the :ref:`architecture` to understand Portal's multi-app structure
#. Download ``portal-developer-examples`` from the `Portal download page <https://market.axonivy.com/portal>`_
#. Import examples into your designer under ``Ivy Projects``
#. Copy and modify example subprocesses for your needs

**Example Projects:**

The ``portal-developer-examples`` project provides working examples of all customization points with:

- Complete subprocess implementations
- Sample JSON configurations
- Integration examples
- Best practice patterns

.. tip::
   Start with ``portal-developer-examples`` before building custom implementations. The examples demonstrate correct subprocess signatures, parameter handling, and return value formats.

.. important::
   **Testing Your Customizations:**
   
   - Test customizations in a development environment first
   - Verify subprocess signatures match Portal's expectations exactly
   - Check that custom processes don't interfere with Portal's core functionality
   - Document your customizations for future maintenance

.. toctree::
   :maxdepth: 1

   portal-style-customization-logos-colors-date-patterns
   forgot-password
   menu
   page-header-footer-process
   task-delegation
   task-item-details
   case-item-details
   change-password-process
   logout-process
   navigate-back
   hide-technical-stuffs
   business-details-page
   document-processes
   process-image
   process-information
