.. _static-page:

Static Pages
************

Create custom JSF pages integrated into Portal's navigation system for displaying documentation, help pages, or any static content.

.. _static-page-introduction:

Introduction
============

Static pages in Axon Ivy Portal allow developers to create custom JSF (JavaServer Faces) pages that integrate into the portal's navigation system. These pages are ideal for:

- **Documentation** - User guides, API documentation, or technical references
- **Help Pages** - Contextual help, FAQs, or tutorials
- **Static Content** - Company policies, announcements, or informational pages
- **Custom Views** - Any content that doesn't require dynamic process execution

**Key Benefits:**

- Consistent styling and layout with Portal theme
- No process execution overhead
- Easy integration into Portal navigation
- Support for multilingual content
- Role-based access control

.. _static-page-creation:

Creating Static Pages
=====================

**HowTo: Create a Static Page**

#. Create XHTML file in ``webContent/view/`` directory
#. Choose appropriate Portal layout template
#. Add your content within the template's content area
#. Configure menu integration (see integration sections below)
#. Test page access and styling

File Structure
--------------

Static pages should be organized in the ``webContent/view/`` directory of your project or custom application.

**Recommended Structure:**

.. code-block:: text

    webContent/
    ├── view/
    │   ├── help/
    │   │   ├── user-guide.xhtml
    │   │   └── faq.xhtml
    │   ├── documentation/
    │   │   ├── api-reference.xhtml
    │   │   └── tutorials.xhtml
    │   └── static.xhtml
    ├── layouts/
    │   ├── frame-10.xhtml              # Standard layout with sidebar
    │   ├── frame-10-full-width.xhtml   # Full-width layout
    │   └── basic-10.xhtml              # Minimal layout
    └── resources/
        ├── css/                         # Custom stylesheets
        │   └── custom-static.css
        └── js/                          # Custom JavaScript
            └── custom-static.js

.. tip::
   Organize related pages in subdirectories (e.g., ``help/``, ``documentation/``) to maintain a clean structure.

Basic Static Page Template
==========================

Here's a complete template demonstrating best practices for creating a static page:

.. code-block:: xml

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://xmlns.jcp.org/jsf/html"
        xmlns:f="http://xmlns.jcp.org/jsf/core"
        xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
        xmlns:p="http://primefaces.org/ui">

    <h:head>
        <title>Your Page Title</title>
        <style>
            /* Custom CSS styles for this page */
            .custom-content {
                padding: 20px;
            }
            .info-box {
                background-color: #f0f8ff;
                border-left: 4px solid var(--ivy-primary-color);
                padding: 15px;
                margin: 20px 0;
            }
        </style>
    </h:head>

    <h:body>
        <!-- Use Portal layout template for consistent styling -->
        <ui:composition template="/layouts/frame-10-full-width.xhtml">
            <ui:define name="content">
                <div class="card custom-content">
                    <h1>Your Content Title</h1>
                    
                    <div class="info-box">
                        <p>Important information or notice.</p>
                    </div>
                    
                    <p>Your static content goes here.</p>
                    
                    <!-- Add more content sections as needed -->
                    <h2>Section Title</h2>
                    <p>Additional content...</p>
                </div>
            </ui:define>
        </ui:composition>
    </h:body>

    </html>


.. _static-page-integration:

Integrating Static Pages into Portal
====================================

Static pages can be integrated into Portal navigation through two primary methods: Main Menu integration and User Menu integration.

Main Menu Integration
=====================

Add static pages to the main left-side navigation menu using the ``Portal.CustomMenuItems`` variable configuration.

**HowTo: Add Static Page to Main Menu**

#. Navigate to Engine Cockpit > Configuration > Variables
#. Find or create the ``Portal.CustomMenuItems`` variable
#. Add your static page configuration (see JSON example below)
#. Save the configuration
#. Static page appears in the main menu

Configuration Method
--------------------

Add the following JSON configuration to the ``Portal.CustomMenuItems`` variable:

.. code-block:: json

    [
        {
            "menuKind": "STATIC_PAGE",
            "link": "portal-components-examples/faces/view/help/user-guide.xhtml",
            "label": "User Guide",
            "icon": "si si-book-open-1",
            "index": 0,
            "version": "12.0.0"
        },
        {
            "menuKind": "STATIC_PAGE",
            "link": "portal-components-examples/faces/view/documentation/api-reference.xhtml",
            "label": "API Reference",
            "icon": "si si-common-file-text",
            "index": 1,
            "version": "12.0.0"
        }
    ]

**Configuration Properties:**

    ``menuKind`` (string, required)
        Must be set to ``"STATIC_PAGE"`` for static page menu items

    ``link`` (string, required)
        Relative path to your static page from the application view directory
        
        - Format: ``{pmv-name}/faces/view/{path-to-file}.xhtml``
        - Example: ``portal-components-examples/faces/view/help/user-guide.xhtml``

    ``label`` (string, required)
        Display name shown in the main menu
        
        - Use clear, descriptive names
        - Keep labels concise (2-3 words recommended)

    ``icon`` (string, optional)
        Icon class for menu item
        
        - Use Streamline Icons: ``si si-icon-name``
        - Example: ``si si-book-open-1``, ``si si-common-file-text``
        - Browse available icons in Portal UI or Streamline documentation

    ``index`` (number, optional)
        Menu position order (lower numbers appear first)
        
        - Default: 0
        - Use for controlling menu item order

    ``version`` (string, optional)
        Portal version for compatibility tracking
        
        - Recommended: Current Portal version (e.g., ``"12.0.0"``)

Programmatic Method
-------------------

For dynamic menu generation, add static pages programmatically using Java code in a callable subprocess:

.. code-block:: java

    import com.axonivy.portal.components.configuration.CustomSubMenuItem;
    import com.axonivy.portal.components.enums.MenuKind;

    // Create new static page menu item
    CustomSubMenuItem staticPage = new CustomSubMenuItem();
    staticPage.setMenuKind(MenuKind.STATIC_PAGE);
    staticPage.setIcon("si si-book-open-1");
    staticPage.setLabel("User Guide");
    staticPage.setLink("portal-components-examples/faces/view/help/user-guide.xhtml");
    staticPage.setIndex(0);

    // Add to menu items collection
    in.subMenuItems.add(staticPage);

.. tip::
   **When to Use Programmatic Method:**
   
   - Dynamic menu items based on user roles or permissions
   - Conditional menu display based on business logic
   - Runtime configuration changes
   - Integration with external systems for menu configuration

User Menu Integration
=====================

Add static pages to the user menu (accessed via user avatar in top-right corner) using the ``Portal.UserMenu`` variable.

**HowTo: Add Static Page to User Menu**

#. Navigate to Engine Cockpit > Configuration > Variables
#. Find or create the ``Portal.UserMenu`` variable
#. Add your static page configuration (see JSON example below)
#. Save the configuration
#. Static page appears in the user menu dropdown

**Configuration Example:**

.. code-block:: json

    [
        {
            "id": "userGuide",
            "menuKind": "STATIC_PAGE",
            "titles": [
                {
                    "locale": "en",
                    "value": "User Guide"
                },
                {
                    "locale": "de",
                    "value": "Benutzerhandbuch"
                },
                {
                    "locale": "fr",
                    "value": "Guide d'utilisateur"
                }
            ],
            "permissions": ["Everybody"],
            "url": "portal-components-examples/faces/view/help/user-guide.xhtml"
        },
        {
            "id": "apiReference",
            "menuKind": "STATIC_PAGE",
            "title": "API Reference",
            "permissions": ["Developer", "AXONIVY_PORTAL_ADMIN"],
            "url": "portal-components-examples/faces/view/documentation/api-reference.xhtml"
        }
    ]

**Configuration Properties:**

    ``id`` (string, required)
        Unique identifier for the menu item
        
        - Must be unique across all user menu items
        - Use camelCase naming convention

    ``menuKind`` (string, required)
        Must be set to ``"STATIC_PAGE"`` for static page menu items

    ``title`` or ``titles`` (required)
        Menu item display text
        
        - **title**: Single-language string (e.g., ``"title": "User Guide"``)
        - **titles**: Array of locale-value pairs for multilingual support

    ``permissions`` (array, optional)
        Roles or users who can see this menu item
        
        - Roles: ``["Employee", "Manager", "AXONIVY_PORTAL_ADMIN"]``
        - Users: Prefix with ``#`` (e.g., ``["#john.doe"]``)
        - Default: If omitted, all users can see the item

    ``url`` (string, required)
        Relative path to your static page
        
        - Format: ``{pmv-name}/faces/view/{path-to-file}.xhtml``
        - Example: ``portal-components-examples/faces/view/help/user-guide.xhtml``

.. _static-page-url-conversion:

URL Conversion and Access
-------------------------

Portal automatically converts static page links to the proper format when displaying them through the menu system.

**Conversion Process:**

#. **Input Format**: Relative path from application view directory
   
   - Example: ``portal-components-examples/faces/view/help/user-guide.xhtml``

#. **Conversion**: Portal uses ``StaticPageUtils.buildUrl()`` method
   
   - Wraps page in Portal's iframe container
   - Applies Portal styling and navigation

#. **Output Format**: Full Portal URL with proper context
   
   - Template: ``{baseUrl}?relativeUrl=/{applicationName}/faces/view/{PM}/{staticPage}``
   - Example: ``https://server/ivy/pro/portal/?relativeUrl=/portal-components-examples/faces/view/help/user-guide.xhtml``


.. _static-page-examples:

Examples
========

Simple Information Page
-----------------------

Create a simple information page with custom styling:

.. code-block:: xml

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html 
        PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

    <html xmlns="http://www.w3.org/1999/xhtml"
          xmlns:h="http://xmlns.jcp.org/jsf/html">
    <h:head>
        <title>Welcome - Axon Ivy</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <style type="text/css">
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                margin: 0;
                background-color: #f7f9fb;
            }
            .header {
                background-color: var(--ivy-primary-color);
                color: var(--ivy-primary-text-color);
                padding: 20px;
                text-align: center;
            }
            .header h1 {
                margin: 0;
                font-size: 1.8em;
                font-weight: normal;
            }
            .content {
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 60px 20px;
            }
            .card {
                background: white;
                padding: 40px;
                max-width: 500px;
                width: 100%;
                text-align: center;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            }
            .card h2 {
                color: var(--ivy-primary-color);
                margin-bottom: 10px;
            }
            .card p {
                color: #555;
                margin-bottom: 25px;
            }
            .button {
                display: inline-block;
                background-color: var(--ivy-primary-color);
                color: var(--ivy-primary-text-color);
                padding: 12px 30px;
                border-radius: 25px;
                text-decoration: none;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }
            .button:hover {
                background-color: var(--ivy-primary-dark-color);
            }
        </style>
    </h:head>
    <h:body>
        <div class="header">
            <h1>Axon Ivy Portal</h1>
        </div>

        <div class="content">
            <div class="card">
                <h2>Welcome</h2>
                <p>Your journey with Axon Ivy starts here.<br />
                   Let's explore powerful workflows and process automation together.</p>
                <a href="https://developer.axonivy.com/" class="button">Get Started</a>
            </div>
        </div>
    </h:body>
    </html>

.. _static-page-best-practices:

Best Practices
==============

File Organization
-----------------

**Directory Structure:**

- Place all static pages in ``webContent/view/`` directory
- Use descriptive, kebab-case filenames (e.g., ``user-guide.xhtml``, ``api-reference.xhtml``)
- Organize related pages in subdirectories (e.g., ``help/``, ``documentation/``, ``policies/``)
- Keep one page per file for maintainability

Security Considerations
-----------------------

**Access Control:**

- Always define appropriate ``permissions`` in menu configuration
- Use role-based access control for sensitive content
- Test page access with different user roles
- Document permission requirements

**Input Validation:**

- Validate all user inputs if forms are included
- Sanitize any dynamic content before display
- Use CSRF tokens for form submissions
- Follow OWASP security guidelines

**Content Security:**

- Avoid storing sensitive data in static pages
- Use HTTPS for external resource links
- Implement proper authentication checks
- Follow Portal security guidelines

.. _static-page-references:

References
==========

- `Static JSF Pages Documentation <https://developer.axonivy.com/doc/12/designer-guide/user-interface/static-jsf-pages.html#static-jsf-pages>`_
