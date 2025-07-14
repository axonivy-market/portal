.. _static-page:

Static Pages
************

.. _static-page-introduction:

Introduction
============

Static pages in Axon Ivy Portal allow developers to create custom JSF pages that can be integrated into the portal's navigation system. These pages are useful for displaying static content, documentation, help pages, or any custom content that doesn't require dynamic process execution.

Static pages are JSF (JavaServer Faces) pages that use the portal's layout templates and styling, providing a consistent user experience while allowing for custom content.

.. _static-page-creation:

Creating Static Pages
=====================

File Structure
--------------

Static pages should be placed in the `webContent/view/` directory of your project. The recommended structure is:

.. code-block:: text

    webContent/
    ├── view/
    │   └── static.xhtml
    ├── layouts/
    │   ├── frame-10.xhtml
    │   ├── frame-10-full-width.xhtml
    │   └── basic-10.xhtml
    └── resources/
        ├── css/
        └── js/

Basic Static Page Template
==========================

Here's a basic template for creating a static page:

.. code-block:: xml

    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://xmlns.jcp.org/jsf/html"
        xmlns:f="http://xmlns.jcp.org/jsf/core"
        xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
        xmlns:p="http://primefaces.org/ui">

    <h:head>
        <title>Your Page Title</title>
        <style>
            /* Your custom CSS styles */
            .card {
                max-width: 600px;
                margin: 2rem auto;
                padding: 1.5rem;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                border-radius: 8px;
                background-color: #fff;
                font-family: Arial, sans-serif;
            }
        </style>
    </h:head>

    <h:body>
        <ui:composition template="/layouts/frame-10-full-width.xhtml">
            <ui:define name="content">
                <div class="card">
                    <h1>Your Content Title</h1>
                    <p>Your static content goes here.</p>
                </div>
            </ui:define>
        </ui:composition>
    </h:body>

    </html>

Layout Templates
----------------

Portal provides several layout templates you can use:

- **frame-10.xhtml**: Standard portal layout with sidebar
- **frame-10-full-width.xhtml**: Full-width layout without sidebar
- **basic-10.xhtml**: Basic layout for simple pages

Choose the appropriate template based on your content needs.

.. _static-page-integration:

Integrating Static Pages into Portal
===================================

There are several ways to integrate static pages into the portal navigation system.

Standard Link Format
--------------------

Static pages can be accessed directly using the standard portal URL format:

.. code-block:: text

    /designer/pro/portal/1549F58C18A6C562/DefaultFramePage.ivp?relativeUrl=/designer/faces/view/portal-components-examples/static.xhtml

Where:
- `portal-components-examples` is your application name
- `static.xhtml` is your static page file

Main Menu Integration
=====================

You can add static pages to the main menu using the `Portal.CustomMenuItems` configuration.

Configuration Method
--------------------

Add the following JSON configuration to the `Portal.CustomMenuItems` variable:

.. code-block:: json

    [
        {
            "menuKind": "STATIC_PAGE",
            "link": "portal-components-examples/static.xhtml",
            "label": "Static Page Example",
            "icon": "si si-document",
            "index": 0,
            "version": "12.0.0"
        }
    ]

Parameters:
- **menuKind**: Must be set to `"STATIC_PAGE"`
- **link**: Path to your static page relative to the view directory
- **label**: Display name in the menu
- **icon**: Icon class (optional)
- **index**: Menu position (optional)
- **version**: Portal version (optional)

Programmatic Method
-------------------

You can also add static pages programmatically using a callable subprocess:

.. code-block:: javascript

    import com.axonivy.portal.components.configuration.CustomSubMenuItem;

    CustomSubMenuItem staticPage = new CustomSubMenuItem();
    staticPage.setMenuKind(MenuKind.STATIC_PAGE);
    staticPage.setIcon("si si-document");
    staticPage.setLabel("Static Page Example");
    staticPage.setLink("portal-components-examples/static.xhtml");
    staticPage.setIndex(0);

    in.subMenuItems.add(staticPage);

User Menu Integration
=====================

Static pages can also be added to the user menu using the `Portal.UserMenu` configuration:

.. code-block:: json

    [
        {
            "id": "staticPageExample",
            "menuKind": "STATIC_PAGE",
            "titles": [
                {
                    "locale": "en",
                    "value": "Static Page Example"
                },
                {
                    "locale": "de",
                    "value": "Statische Seite Beispiel"
                }
            ],
            "permissions": ["Everybody"],
            "url": "portal-components-examples/static.xhtml"
        }
    ]

Parameters:
- **id**: Unique identifier for the menu item
- **menuKind**: Must be set to `"STATIC_PAGE"`
- **titles**: Multilingual titles
- **permissions**: Array of roles or users who can see this item
- **url**: Path to your static page

.. _static-page-url-conversion:

URL Conversion
--------------

Portal automatically converts static page links to the proper format when used in menus. The conversion process:

1. **Input**: `portal-components-examples/static.xhtml`
2. **Conversion**: Uses `StaticPageUtils.buildUrl()` method
3. **Output**: Full portal URL with iframe wrapper

The conversion formula:
.. code-block:: text

    /designer/pro/portal/{processId}/DefaultFramePage.ivp?relativeUrl=/{applicationName}/faces/view/{staticPagePath}

.. _static-page-examples:

Examples
========

Simple Information Page
-----------------------

Create a simple information page with custom styling:

.. code-block:: xml

    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://xmlns.jcp.org/jsf/html"
        xmlns:f="http://xmlns.jcp.org/jsf/core"
        xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
        xmlns:p="http://primefaces.org/ui">

    <h:head>
        <title>Help Documentation</title>
        <style>
            .help-container {
                max-width: 800px;
                margin: 2rem auto;
                padding: 2rem;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            }
            .help-section {
                margin-bottom: 2rem;
                padding: 1rem;
                border-left: 4px solid #007bff;
                background: #f8f9fa;
            }
            .help-section h2 {
                color: #007bff;
                margin-bottom: 1rem;
            }
        </style>
    </h:head>

    <h:body>
        <ui:composition template="/layouts/frame-10-full-width.xhtml">
            <ui:define name="content">
                <div class="help-container">
                    <h1>Help Documentation</h1>
                    
                    <div class="help-section">
                        <h2>Getting Started</h2>
                        <p>Welcome to the portal. This guide will help you get started with the basic features.</p>
                    </div>
                    
                    <div class="help-section">
                        <h2>Process Management</h2>
                        <p>Learn how to start and manage processes within the portal.</p>
                    </div>
                    
                    <div class="help-section">
                        <h2>Task Management</h2>
                        <p>Understand how to view and complete tasks assigned to you.</p>
                    </div>
                </div>
            </ui:define>
        </ui:composition>
    </h:body>

    </html>

.. _static-page-best-practices:

Best Practices
===============

File Organization
-----------------

- Place static pages in `webContent/view/` directory
- Use descriptive file names (e.g., `help-documentation.xhtml`, `user-guide.xhtml`)
- Organize related pages in subdirectories if needed


Content Guidelines
------------------

- Keep content focused and relevant
- Use consistent styling with the portal theme
- Ensure responsive design for different screen sizes
- Include proper navigation breadcrumbs when appropriate

Security Considerations
-----------------------

- Validate all user inputs if forms are included
- Use proper access controls through menu permissions
- Sanitize any dynamic content
- Follow portal security guidelines

.. _static-page-references:

References
==========

- `Static JSF Pages Documentation <https://developer.axonivy.com//doc/latest/designer-guide/user-interface/static-jsf-pages.html#static-jsf-pages>`_
- `Portal Menu Configuration <customization-menu.html>`_
- `User Menu Configuration <usermenu/index.html>`_
- `PrimeFaces Documentation <https://www.primefaces.org/documentation/>`_
