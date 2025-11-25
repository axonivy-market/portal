.. _qr_code:

QR Code
*******

The Portal QR code feature enables quick connection between the Axon Ivy Portal and the Axon Ivy mobile application. By scanning the QR code, the mobile app automatically obtains the login URL and username.

QR Code Features
================

The Portal provides two types of QR codes:

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | QR Code Type              | Purpose                                                       |
   +===========================+===============================================================+
   | **Mobile App Download**   | Download the Axon Ivy mobile app from iOS or Google Play      |
   +---------------------------+---------------------------------------------------------------+
   | **Portal Connection**     | Connect to Portal with auto-configured URL and username       |
   +---------------------------+---------------------------------------------------------------+

Configuration
=============

HowTo: Activate the QR Code Feature
------------------------------------

Enable the QR code menu item in the user menu:

- Open :ref:`Admin Settings <admin-settings>`
- Set :guilabel:`Portal.UserMenu.ShowQRCode` to true

See :ref:`Update Portal settings <update-portal-settings>`.

HowTo: Configure Base Portal URL for Mobile Application
--------------------------------------------------------

Customize the Portal URL used by the mobile application:

- Open :ref:`Admin Settings <admin-settings>`
- Set :guilabel:`Portal.UserMenu.BaseQRCodeUrl` to your Portal base URL

See :ref:`Update Portal settings <update-portal-settings>`.

Using QR Codes
==============

HowTo: Download the Axon Ivy Mobile Application
------------------------------------------------

#. Select the :guilabel:`Mobile app` user menu item.

   |mobile-app-menu|

#. Choose your mobile device platform:
   
   - **iOS**: For Apple devices (iPhone, iPad)
   - **Google Play**: For Android devices

   |platform-qr-code|
   
#. Open your mobile device camera and scan the QR code to download the app.

HowTo: Connect to Portal via Mobile Application
------------------------------------------------

#. Select the :guilabel:`Mobile app` user menu item.

   |mobile-app-menu|

#. A dialog with a QR code will be displayed.

   |qr-code|

#. Open the Axon Ivy mobile application on your device.

#. Scan the QR code to automatically configure:
   
   - Portal connection URL
   - Your username

.. tip::
   The QR code provides a secure and convenient way to connect without manually entering the Portal URL and username.

.. |qr-code| image:: ../../screenshots/mobile/qr-code.png
  :alt: Mobile app QR code
.. |mobile-app-menu| image:: ../../screenshots/mobile/mobile-app-menu.png
  :alt: Mobile app menu
.. |platform-qr-code| image:: ../../screenshots/mobile/platform-qr-code.png
  :alt: Mobile app QR code