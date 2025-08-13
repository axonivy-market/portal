.. _static-page-ja:

Static Pages
************

.. _static-page-introduction-ja:

Introduction
============

Static pages in Axon Ivy Portal allow developers to create custom JSF pages that can be integrated into the portal's navigation system. These pages are useful for displaying static content, documentation, help pages, or any custom content that doesn't require dynamic process execution.

Static pages are JSF (JavaServer Faces) pages that use the portal's layout templates and styling, providing a consistent user experience while allowing for custom content.

.. _static-page-creation-ja:

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

.. _static-page-integration-ja:

Integrating Static Pages into Portal
====================================

There are several ways to integrate static pages into the portal navigation system.

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
            "link": "app/faces/view/portal-components-examples/static.xhtml",
            "label": "Static Page Example",
            "icon": "si si-task-list-edit",
            "index": 0,
            "version": "12.0.0"
        }
    ]

Parameters:

- **menuKind**: Must be set to `"STATIC_PAGE"`
- **link**: Path to your static page relative to the view directory
- **label**: Display name in the menu
- **icon**: Icon class
- **index**: Menu position (optional, defaults to 0)
- **version**: Portal version

Programmatic Method
-------------------

You can also add static pages programmatically using a callable subprocess:

.. code-block:: javascript

    import com.axonivy.portal.components.configuration.CustomSubMenuItem;

    CustomSubMenuItem staticPage = new CustomSubMenuItem();
    staticPage.setMenuKind(MenuKind.STATIC_PAGE);
    staticPage.setIcon("si si-task-list-edit");
    staticPage.setLabel("Static Page Example");
    staticPage.setLink("app/faces/view/portal-components-examples/static.xhtml");
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
            "url": "app/faces/view/portal-components-examples/static.xhtml"
        }
    ]

Parameters:

- **id**: Unique identifier for the menu item
- **menuKind**: Must be set to `"STATIC_PAGE"`
- **titles**: Multilingual titles
- **permissions**: Array of roles or users who can see this item
- **url**: Path to your static page

.. _static-page-url-conversion-ja:

URL Conversion
--------------

Portal automatically converts static page links to the proper format when used in menus. The conversion process:

1. **Input**: `app/faces/view/portal-components-examples/static.xhtml`
2. **Conversion**: Uses `StaticPageUtils.buildUrl()` method
3. **Output**: Full portal URL with iframe wrapper

The conversion formula:

.. code-block:: text

    {baseUrl}?relativeUrl=/{applicationName}/faces/view/{PM}/{staticPage}

.. _static-page-examples-ja:

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

.. _static-page-best-practices-ja:

Best Practices
==============

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

.. _static-page-references-ja:

References
==========

- `Static JSF Pages Documentation <https://developer.axonivy.com/doc/12/designer-guide/user-interface/static-jsf-pages.html#static-jsf-pages>`_
