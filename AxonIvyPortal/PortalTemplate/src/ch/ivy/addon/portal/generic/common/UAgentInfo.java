package ch.ivy.addon.portal.generic.common;

public class UAgentInfo {
  // User-Agent and Accept HTTP request headers

  private String userAgent = "";
  private String httpAccept = "";

  // Let's store values for quickly accessing the same info multiple times.
  private boolean initCompleted = false;
  private boolean isWebkit = false; // Stores the result of DetectWebkit()
  private boolean isMobilePhone = false; // Stores the result of DetectMobileQuick()
  private boolean isIphone = false; // Stores the result of DetectIphone()
  private boolean isAndroid = false; // Stores the result of DetectAndroid()
  private boolean isAndroidPhone = false; // Stores the result of DetectAndroidPhone()
  private boolean isTierTablet = false; // Stores the result of DetectTierTablet()
  private boolean isTierIphone = false; // Stores the result of DetectTierIphone()
  private boolean isTierRichCss = false; // Stores the result of DetectTierRichCss()
  private boolean isTierGenericMobile = false; // Stores the result of DetectTierOtherPhones()

  // Initialize some initial smartphone string variables.
  public static final String ENGINE_WEBKIT = "webkit";

  public static final String DEVICE_IPHONE = "iphone";
  public static final String DEVICE_IPOD = "ipod";
  public static final String DEVICE_IPAD = "ipad";
  public static final String DEVICE_MAC_PPC = "macintosh"; // Used for disambiguation

  public static final String DEVICE_ANDROID = "android";
  public static final String DEVICE_GOOGLE_TV = "googletv";

  public static final String DEVICE_WINPHONE_7 = "windows phone os 7";
  public static final String DEVICE_WINPHONE_8 = "windows phone 8";
  public static final String DEVICE_WINPHONE_10 = "windows phone 10";
  public static final String DEVICE_WINMOB = "windows ce";
  public static final String DEVICE_WINDOWS = "windows";
  public static final String DEVICE_IE_MOB = "iemobile";
  public static final String DEVICE_PPC = "ppc"; // Stands for PocketPC
  public static final String ENGINE_PIE = "wm5 pie"; // An old Windows Mobile

  public static final String DEVICE_BB = "blackberry";
  public static final String DEVICE_BB_10 = "bb10"; // For the new BB 10 OS
  public static final String VND_RIM = "vnd.rim"; // Detectable when BB devices emulate IE or Firefox
  public static final String DEVICE_BB_STORM = "blackberry95"; // Storm 1 and 2
  public static final String DEVICE_BB_BOLD = "blackberry97"; // Bold 97x0 (non-touch)
  public static final String DEVICE_BB_BOLD_TOUCH = "blackberry 99"; // Bold 99x0 (touchscreen)
  public static final String DEVICE_BB_TOUR = "blackberry96"; // Tour
  public static final String DEVICE_BB_CURVE = "blackberry89"; // Curve 2
  public static final String DEVICE_BB_CURVE_TOUCH = "blackberry 938"; // Curve Touch 9380
  public static final String DEVICE_BB_TORCH = "blackberry 98"; // Torch
  public static final String DEVICE_BB_PLAYBOOK = "playbook"; // PlayBook tablet

  public static final String DEVICE_SYMBIAN = "symbian";
  public static final String DEVICE_S60 = "series60";
  public static final String DEVICE_S70 = "series70";
  public static final String DEVICE_S80 = "series80";
  public static final String DEVICE_S90 = "series90";

  public static final String DEVICE_PALM = "palm";
  public static final String DEVICE_WEB_OS = "webos"; // For Palm devices
  public static final String DEVICE_WEB_OS_TV = "web0s"; // For LG TVs
  public static final String DEVICE_WEB_OS_HP = "hpwos"; // For HP's line of WebOS devices

  public static final String DEVICE_NUVIFONE = "nuvifone"; // Garmin Nuvifone
  public static final String DEVICE_BADA = "bada"; // Samsung's Bada OS
  public static final String DEVICE_TIZEN = "tizen"; // Tizen OS
  public static final String DEVICE_MEEGO = "meego"; // Meego OS
  public static final String DEVICE_SAILFISH = "sailfish"; // Sailfish OS
  public static final String DEVICE_UBUNTU = "ubuntu"; // Ubuntu Mobile OS

  public static final String DEVICE_KINDLE = "kindle"; // Amazon Kindle, eInk one
  public static final String ENGINE_SILK = "silk-accelerated"; // Amazon's accelerated Silk browser for Kindle Fire

  public static final String DEVICE_BLAZER = "blazer"; // Old Palm
  public static final String DEVICE_XIINO = "xiino"; // Another old Palm

  // Initialize variables for mobile-specific content.
  public static final String VND_WAP = "vnd.wap";
  public static final String WML = "wml";

  // Initialize variables for other random devices and mobile browsers.
  public static final String DEVICE_TABLET = "tablet"; // Generic term for slate and tablet devices
  public static final String DEVICE_BREW = "brew";
  public static final String DEVICE_DANGER = "danger";
  public static final String DEVICE_HIPTOP = "hiptop";
  public static final String DEVICE_PLAYSTATION = "playstation";
  public static final String DEVICE_PLAYSTATION_VITA = "vita";
  public static final String DEVICE_NINTENTDO_DS = "nitro";
  public static final String DEVICE_NINTENDO = "nintendo";
  public static final String DEVICE_WII = "wii";
  public static final String DEVICE_XBOX = "xbox";
  public static final String DEVICE_ARCHOS = "archos";

  public static final String ENGINE_FIREFOX = "firefox"; // For Firefox OS
  public static final String ENGINE_OPERA = "opera"; // Popular browser
  public static final String ENGINE_NETFRONT = "netfront"; // Common embedded OS browser
  public static final String ENGINE_UP_BROWSER = "up.browser"; // common on some phones
  public static final String ENGINE_OPENWEB = "openweb"; // Transcoding by OpenWave server
  public static final String DEVICE_MIDP = "midp"; // a mobile Java technology
  public static final String UPLINK = "up.link";
  public static final String ENGINE_TELECA_Q = "teleca q"; // a modern feature phone browser
  public static final String DEVICE_PDA = "pda"; // some devices report themselves as PDAs
  public static final String MINI = "mini"; // Some mobile browsers put "mini" in their names.
  public static final String MOBILE = "mobile"; // Some mobile browsers put "mobile" in their user agent strings.
  public static final String MOBI = "mobi"; // Some mobile browsers put "mobi" in their user agent strings.

  // Smart TV strings
  public static final String SMART_TV1 = "smart-tv"; // Samsung Tizen smart TVs
  public static final String SMART_TV2 = "smarttv"; // LG WebOS smart TVs

  // Use Maemo, Tablet, and Linux to test for Nokia"s Internet Tablets.
  public static final String MAEMO = "maemo";
  public static final String LINUX = "linux";
  public static final String QT_EMBEDDED = "qt embedded"; // for Sony Mylo
  public static final String MYLOCOM2 = "com2"; // for Sony Mylo also

  // In some UserAgents, the only clue is the manufacturer.
  public static final String MANU_SONY_ERICSSON = "sonyericsson";
  public static final String MANU_ERICSSON = "ericsson";
  public static final String MANU_SAMSUNG1 = "sec-sgh";
  public static final String MANU_SONY = "sony";
  public static final String MANU_HTC = "htc"; // Popular Android and WinMo manufacturer

  // In some UserAgents, the only clue is the operator.
  public static final String SVC_DOCOMO = "docomo";
  public static final String SVC_KDDI = "kddi";
  public static final String SVC_VODAFONE = "vodafone";

  // Disambiguation strings.
  public static final String DIS_UPDATE = "update"; // pda vs. update


  /**
   * Initialize the userAgent and httpAccept variables
   *
   * @param userAgent the User-Agent header
   * @param httpAccept the Accept header
   */
  public UAgentInfo(String userAgent, String httpAccept) {
    if (userAgent != null) {
      this.userAgent = userAgent.toLowerCase();
    }
    if (httpAccept != null) {
      this.httpAccept = httpAccept.toLowerCase();
    }

    // Intialize key stored values.
    initDeviceScan();
  }

  /**
   * Return the lower case HTTP_USER_AGENT
   * 
   * @return userAgent
   */
  public String getUserAgent() {
    return userAgent;
  }

  /**
   * Return the lower case HTTP_ACCEPT
   * 
   * @return httpAccept
   */
  public String getHttpAccept() {
    return httpAccept;
  }

  /**
   * Return whether the device is an Iphone or iPod Touch
   * 
   * @return isIphone
   */
  public boolean getIsIphone() {
    return isIphone;
  }

  /**
   * Return whether the device is in the Tablet Tier.
   * 
   * @return isTierTablet
   */
  public boolean getIsTierTablet() {
    return isTierTablet;
  }

  /**
   * Return whether the device is in the Iphone Tier.
   * 
   * @return isTierIphone
   */
  public boolean getIsTierIphone() {
    return isTierIphone;
  }

  /**
   * Return whether the device is in the 'Rich CSS' tier of mobile devices.
   * 
   * @return isTierRichCss
   */
  public boolean getIsTierRichCss() {
    return isTierRichCss;
  }

  /**
   * Return whether the device is a generic, less-capable mobile device.
   * 
   * @return isTierGenericMobile
   */
  public boolean getIsTierGenericMobile() {
    return isTierGenericMobile;
  }

  /**
   * Initialize Key Stored Values.
   */
  public void initDeviceScan() {
    // Save these properties to speed processing
    this.isWebkit = detectWebkit();
    this.isIphone = detectIphone();
    this.isAndroid = detectAndroid();
    this.isAndroidPhone = detectAndroidPhone();

    // Generally, these tiers are the most useful for web development
    this.isMobilePhone = detectMobileQuick();
    this.isTierTablet = detectTierTablet();
    this.isTierIphone = detectTierIphone();

    // Optional: Comment these out if you NEVER use them
    this.isTierRichCss = detectTierRichCss();
    this.isTierGenericMobile = detectTierOtherPhones();

    this.initCompleted = true;
  }

  /**
   * Detects if the current device is an iPhone.
   * 
   * @return detection of an iPhone
   */
  public boolean detectIphone() {
    // The iPad and iPod touch say they're an iPhone! So let's disambiguate.
    return userAgent.indexOf(DEVICE_IPHONE) != -1 && !detectIpad() && !detectIpod();
  }

  /**
   * Detects if the current device is an iPod Touch.
   * 
   * @return detection of an iPod Touch
   */
  public boolean detectIpod() {
    return userAgent.indexOf(DEVICE_IPOD) != -1;
  }

  /**
   * Detects if the current device is an iPad tablet.
   * 
   * @return detection of an iPad
   */
  public boolean detectIpad() {
    return userAgent.indexOf(DEVICE_IPAD) != -1 && detectWebkit();
  }

  /**
   * Detects if the current device is an iPhone or iPod Touch.
   * 
   * @return detection of an iPhone or iPod Touch
   */
  public boolean detectIphoneOrIpod() {
    // We repeat the searches here because some iPods may report themselves as an iPhone, which would be okay.
    return userAgent.indexOf(DEVICE_IPHONE) != -1 || userAgent.indexOf(DEVICE_IPOD) != -1;
  }

  /**
   * Detects *any* iOS device: iPhone, iPod Touch, iPad.
   * 
   * @return detection of an Apple iOS device
   */
  public boolean detectIos() {
    return detectIphoneOrIpod() || detectIpad();
  }


  /**
   * Detects *any* Android OS-based device: phone, tablet, and multi-media player. Also detects Google TV.
   * 
   * @return detection of an Android device
   */
  public boolean detectAndroid() {
    return userAgent.indexOf(DEVICE_ANDROID) != -1 || detectGoogleTV();
  }

  /**
   * Detects if the current device is a (small-ish) Android OS-based device used for calling and/or multi-media (like a
   * Samsung Galaxy Player). Google says these devices will have 'Android' AND 'mobile' in user agent. Ignores tablets
   * (Honeycomb and later).
   * 
   * @return detection of an Android phone
   */
  public boolean detectAndroidPhone() {
    // First, let's make sure we're on an Android device.
    if (!detectAndroid())
      return false;

    // If it's Android and has 'mobile' in it, Google says it's a phone.
    if (userAgent.indexOf(MOBILE) != -1)
      return true;

    // Special check for Android devices with Opera Mobile/Mini. They should report here.
    return detectOperaMobile();
  }

  /**
   * Detects if the current device is a (self-reported) Android tablet. Google says these devices will have 'Android'
   * and NOT 'mobile' in their user agent.
   * 
   * @return detection of an Android tablet
   */
  public boolean detectAndroidTablet() {
    // First, let's make sure we're on an Android device.
    if (!detectAndroid())
      return false;

    // Special check for Android devices with Opera Mobile/Mini. They should NOT report here.
    if (detectOperaMobile())
      return false;

    // Otherwise, if it's Android and does NOT have 'mobile' in it, Google says it's a tablet.
    return userAgent.indexOf(MOBILE) > -1;
  }

  /**
   * Detects if the current device is an Android OS-based device and the browser is based on WebKit.
   * 
   * @return detection of an Android WebKit browser
   */
  public boolean detectAndroidWebKit() {
    return detectAndroid() && detectWebkit();
  }

  /**
   * Detects if the current device is a GoogleTV.
   * 
   * @return detection of GoogleTV
   */
  public boolean detectGoogleTV() {
    return userAgent.indexOf(DEVICE_GOOGLE_TV) != -1;
  }

  /**
   * Detects if the current browser is based on WebKit.
   * 
   * @return detection of a WebKit browser
   */
  public boolean detectWebkit() {
    return userAgent.indexOf(ENGINE_WEBKIT) != -1;
  }

  /**
   * Detects if the current browser is the Symbian S60 Open Source Browser.
   * 
   * @return detection of Symbian S60 Browser
   */
  public boolean detectS60OssBrowser() {
    // First, test for WebKit, then make sure it's either Symbian or S60.
    return detectWebkit() && (userAgent.indexOf(DEVICE_SYMBIAN) != -1 || userAgent.indexOf(DEVICE_S60) != -1);
  }

  /**
   *
   * Detects if the current device is any Symbian OS-based device, including older S60, Series 70, Series 80, Series 90,
   * and UIQ, or other browsers running on these devices.
   * 
   * @return detection of SymbianOS
   */
  public boolean detectSymbianOS() {
    return userAgent.indexOf(DEVICE_SYMBIAN) != -1 || userAgent.indexOf(DEVICE_S60) != -1
        || userAgent.indexOf(DEVICE_S70) != -1 || userAgent.indexOf(DEVICE_S80) != -1
        || userAgent.indexOf(DEVICE_S90) != -1; // NOSONAR
  }

  /**
   * Detects if the current browser is a Windows Phone 7.x, 8, or 10 device
   * 
   * @return detection of Windows Phone 7.x OR 8
   */
  public boolean detectWindowsPhone() {
    return detectWindowsPhone7() || detectWindowsPhone8() || detectWindowsPhone10();
  }

  /**
   * Detects a Windows Phone 7 device (in mobile browsing mode).
   * 
   * @return detection of Windows Phone 7
   */
  public boolean detectWindowsPhone7() {
    return userAgent.indexOf(DEVICE_WINPHONE_7) != -1;
  }

  /**
   * Detects a Windows Phone 8 device (in mobile browsing mode).
   * 
   * @return detection of Windows Phone 8
   */
  public boolean detectWindowsPhone8() {
    return userAgent.indexOf(DEVICE_WINPHONE_8) != -1;
  }

  /**
   * Detects a Windows Phone 10 device (in mobile browsing mode).
   * 
   * @return detection of Windows Phone 10
   */
  public boolean detectWindowsPhone10() {
    return userAgent.indexOf(DEVICE_WINPHONE_10) != -1;
  }

  /**
   * Detects if the current browser is a Windows Mobile device. Excludes Windows Phone 7.x and 8 devices. Focuses on
   * Windows Mobile 6.xx and earlier.
   * 
   * @return detection of Windows Mobile
   */
  public boolean detectWindowsMobile() {
    if (detectWindowsPhone()) {
      return false;
    }
    // Most devices use 'Windows CE', but some report 'iemobile'
    // and some older ones report as 'PIE' for Pocket IE.
    // We also look for instances of HTC and Windows for many of their WinMo devices.
    if (userAgent.indexOf(DEVICE_WINMOB) != -1 || userAgent.indexOf(DEVICE_WINMOB) != -1
        || userAgent.indexOf(DEVICE_IE_MOB) != -1 || userAgent.indexOf(ENGINE_PIE) != -1
        || (userAgent.indexOf(MANU_HTC) != -1 && userAgent.indexOf(DEVICE_WINDOWS) != -1)
        || (detectWapWml() && userAgent.indexOf(DEVICE_WINDOWS) != -1)) { // NOSONAR
      return true;
    }

    // Test for Windows Mobile PPC but not old Macintosh PowerPC.
    return userAgent.indexOf(DEVICE_PPC) != -1 && !(userAgent.indexOf(DEVICE_MAC_PPC) != -1);
  }

  /**
   * Detects if the current browser is any BlackBerry. Includes BB10 OS, but excludes the PlayBook.
   * 
   * @return detection of Blackberry
   */
  public boolean detectBlackBerry() {
    if (userAgent.indexOf(DEVICE_BB) != -1 || httpAccept.indexOf(VND_RIM) != -1)
      return true;

    return detectBlackBerry10Phone();
  }

  /**
   * Detects if the current browser is a BlackBerry 10 OS phone. Excludes tablets.
   * 
   * @return detection of a Blackberry 10 device
   */
  public boolean detectBlackBerry10Phone() {
    return userAgent.indexOf(DEVICE_BB_10) != -1 && userAgent.indexOf(MOBILE) != -1;
  }

  /**
   * Detects if the current browser is on a BlackBerry tablet device. Example: PlayBook
   * 
   * @return detection of a Blackberry Tablet
   */
  public boolean detectBlackBerryTablet() {
    return userAgent.indexOf(DEVICE_BB_PLAYBOOK) != -1;
  }

  /**
   * Detects if the current browser is a BlackBerry device AND uses a WebKit-based browser. These are signatures for the
   * new BlackBerry OS 6. Examples: Torch. Includes the Playbook.
   * 
   * @return detection of a Blackberry device with WebKit browser
   */
  public boolean detectBlackBerryWebKit() {
    return detectBlackBerry() && userAgent.indexOf(ENGINE_WEBKIT) != -1;
  }

  /**
   * Detects if the current browser is a BlackBerry Touch device, such as the Storm, Torch, and Bold Touch. Excludes the
   * Playbook.
   * 
   * @return detection of a Blackberry touchscreen device
   */
  public boolean detectBlackBerryTouch() {
    return detectBlackBerry() && (userAgent.indexOf(DEVICE_BB_STORM) != -1 || userAgent.indexOf(DEVICE_BB_TORCH) != -1
        || userAgent.indexOf(DEVICE_BB_BOLD_TOUCH) != -1 || userAgent.indexOf(DEVICE_BB_CURVE_TOUCH) != -1); // NOSONAR
  }

  /**
   * Detects if the current browser is a BlackBerry device AND has a more capable recent browser. Excludes the Playbook.
   * Examples, Storm, Bold, Tour, Curve2 Excludes the new BlackBerry OS 6 and 7 browser!!
   * 
   * @return detection of a Blackberry device with a better browser
   */
  public boolean detectBlackBerryHigh() {
    // Disambiguate for BlackBerry OS 6 or 7 (WebKit) browser
    if (detectBlackBerryWebKit())
      return false;
    
    return detectBlackBerry() && (detectBlackBerryTouch() || userAgent.indexOf(DEVICE_BB_BOLD) != -1 || userAgent.indexOf(DEVICE_BB_TOUR) != -1
          || userAgent.indexOf(DEVICE_BB_CURVE) != -1); // NOSONAR
  }

  /**
   * Detects if the current browser is a BlackBerry device AND has an older, less capable browser. Examples: Pearl,
   * 8800, Curve1
   * 
   * @return detection of a Blackberry device with a poorer browser
   */
  public boolean detectBlackBerryLow() {
    if (detectBlackBerry()) {
      // Assume that if it's not in the High tier, then it's Low
      if (detectBlackBerryHigh() || detectBlackBerryWebKit()) { // NOSONAR
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * Detects if the current browser is on a PalmOS device.
   * 
   * @return detection of a PalmOS device
   */
  public boolean detectPalmOS() {
    // Most devices nowadays report as 'Palm', but some older ones reported as Blazer or Xiino.
    if (userAgent.indexOf(DEVICE_PALM) != -1 || userAgent.indexOf(DEVICE_BLAZER) != -1
        || userAgent.indexOf(DEVICE_XIINO) != -1) {
      // Make sure it's not WebOS first
      if (detectPalmWebOS()) { // NOSONAR
        return false;
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   * Detects if the current browser is on a Palm device running the new WebOS.
   * 
   * @return detection of a Palm WebOS device
   */
  public boolean detectPalmWebOS() {
    return userAgent.indexOf(DEVICE_WEB_OS) != -1;
  }

  /**
   * Detects if the current browser is on an HP tablet running WebOS.
   * 
   * @return detection of an HP WebOS tablet
   */
  public boolean detectWebOSTablet() {
    return userAgent.indexOf(DEVICE_WEB_OS_HP) != -1 && userAgent.indexOf(DEVICE_TABLET) != -1;
  }

  /**
   * Detects if the current browser is on a WebOS smart TV.
   * 
   * @return detection of a WebOS smart TV
   */
  public boolean detectWebOSTV() {
    return userAgent.indexOf(DEVICE_WEB_OS_TV) != -1 && userAgent.indexOf(SMART_TV2) != -1;
  }

  /**
   * Detects Opera Mobile or Opera Mini.
   * 
   * @return detection of an Opera browser for a mobile device
   */
  public boolean detectOperaMobile() {
    return userAgent.indexOf(ENGINE_OPERA) != -1 && (userAgent.indexOf(MINI) != -1 || userAgent.indexOf(MOBI) != -1);
  }

  /**
   * Detects if the current device is an Amazon Kindle (eInk devices only). Note: For the Kindle Fire, use the normal
   * Android methods.
   * 
   * @return detection of a Kindle
   */
  public boolean detectKindle() {
    return userAgent.indexOf(DEVICE_KINDLE) != -1 && !detectAndroid();
  }

  /**
   * Detects if the current Amazon device is using the Silk Browser. Note: Typically used by the the Kindle Fire.
   * 
   * @return detection of an Amazon Kindle Fire in Silk mode.
   */
  public boolean detectAmazonSilk() {
    return userAgent.indexOf(ENGINE_SILK) != -1;
  }

  /**
   * Detects if the current browser is a Garmin Nuvifone.
   * 
   * @return detection of a Garmin Nuvifone
   */
  public boolean detectGarminNuvifone() {
    return userAgent.indexOf(DEVICE_NUVIFONE) != -1;
  }

  /**
   * Detects a device running the Bada OS from Samsung.
   * 
   * @return detection of a Bada device
   */
  public boolean detectBada() {
    return userAgent.indexOf(DEVICE_BADA) != -1;
  }

  /**
   * Detects a device running the Tizen smartphone OS.
   * 
   * @return detection of a Tizen device
   */
  public boolean detectTizen() {
    return userAgent.indexOf(DEVICE_TIZEN) != -1 && userAgent.indexOf(MOBILE) != -1;
  }

  /**
   * Detects if the current browser is on a Tizen smart TV.
   * 
   * @return detection of a Tizen smart TV
   */
  public boolean detectTizenTV() {
    return userAgent.indexOf(DEVICE_TIZEN) != -1 && userAgent.indexOf(SMART_TV1) != -1;
  }

  /**
   * Detects a device running the Meego OS.
   * 
   * @return detection of a Meego device
   */
  public boolean detectMeego() {
    return userAgent.indexOf(DEVICE_MEEGO) != -1;
  }

  /**
   * Detects a phone running the Meego OS.
   * 
   * @return detection of a Meego phone
   */
  public boolean detectMeegoPhone() {
    return userAgent.indexOf(DEVICE_MEEGO) != -1 && userAgent.indexOf(MOBI) != -1;
  }

  /**
   * Detects a mobile device (probably) running the Firefox OS.
   * 
   * @return detection of a Firefox OS mobile device
   */
  public boolean detectFirefoxOS() {
    return detectFirefoxOSPhone() || detectFirefoxOSTablet();
  }

  /**
   * Detects a phone (probably) running the Firefox OS.
   * 
   * @return detection of a Firefox OS phone
   */
  public boolean detectFirefoxOSPhone() {
    // First, let's make sure we're NOT on another major mobile OS.
    if (detectIos() || detectAndroid() || detectSailfish())
      return false;

    return (userAgent.indexOf(ENGINE_FIREFOX) != -1) && (userAgent.indexOf(MOBILE) != -1);
  }

  /**
   * Detects a tablet (probably) running the Firefox OS.
   * 
   * @return detection of a Firefox OS tablet
   */
  public boolean detectFirefoxOSTablet() {
    // First, let's make sure we're NOT on another major mobile OS.
    if (detectIos() || detectAndroid() || detectSailfish())
      return false;

    return (userAgent.indexOf(ENGINE_FIREFOX) != -1) && (userAgent.indexOf(DEVICE_TABLET) != -1);
  }

  /**
   * Detects a device running the Sailfish OS.
   * 
   * @return detection of a Sailfish device
   */
  public boolean detectSailfish() {
    return userAgent.indexOf(DEVICE_SAILFISH) != -1;
  }

  /**
   * Detects a phone running the Sailfish OS.
   * 
   * @return detection of a Sailfish phone
   */
  public boolean detectSailfishPhone() {
    return detectSailfish() && (userAgent.indexOf(MOBILE) != -1);
  }

  /**
   * Detects a mobile device running the Ubuntu Mobile OS.
   * 
   * @return detection of an Ubuntu Mobile OS mobile device
   */
  public boolean detectUbuntu() {
    return detectUbuntuPhone() || detectUbuntuTablet();
  }

  /**
   * Detects a phone running the Ubuntu Mobile OS.
   * 
   * @return detection of an Ubuntu Mobile OS phone
   */
  public boolean detectUbuntuPhone() {
    return (userAgent.indexOf(DEVICE_UBUNTU) != -1) && (userAgent.indexOf(MOBILE) != -1);
  }

  /**
   * Detects a tablet running the Ubuntu Mobile OS.
   * 
   * @return detection of an Ubuntu Mobile OS tablet
   */
  public boolean detectUbuntuTablet() {
    return (userAgent.indexOf(DEVICE_UBUNTU) != -1) && (userAgent.indexOf(DEVICE_TABLET) != -1);
  }


  /**
   * Detects the Danger Hiptop device.
   * 
   * @return detection of a Danger Hiptop
   */
  public boolean detectDangerHiptop() {
    return userAgent.indexOf(DEVICE_DANGER) != -1 || userAgent.indexOf(DEVICE_HIPTOP) != -1;
  }

  /**
   * Detects if the current browser is a Sony Mylo device.
   * 
   * @return detection of a Sony Mylo device
   */
  public boolean detectSonyMylo() {
    return userAgent.indexOf(MANU_SONY) != -1 && (userAgent.indexOf(QT_EMBEDDED) != -1 || userAgent.indexOf(MYLOCOM2) != -1);
  }

  /**
   * Detects if the current device is on one of the Maemo-based Nokia Internet Tablets.
   * 
   * @return detection of a Maemo OS tablet
   */
  public boolean detectMaemoTablet() {
    if (userAgent.indexOf(MAEMO) != -1) {
      return true;
    } else if (userAgent.indexOf(LINUX) != -1 && userAgent.indexOf(DEVICE_TABLET) != -1 && !detectWebOSTablet()
        && !detectAndroid()) {
      return true;
    }
    return false;
  }

  /**
   * Detects if the current device is an Archos media player/Internet tablet.
   * 
   * @return detection of an Archos media player
   */
  public boolean detectArchos() {
    return userAgent.indexOf(DEVICE_ARCHOS) != -1;
  }

  /**
   * Detects if the current device is an Internet-capable game console. Includes many handheld consoles.
   * 
   * @return detection of any Game Console
   */
  public boolean detectGameConsole() {
    return detectSonyPlaystation() || detectNintendo() || detectXbox();
  }

  /**
   * Detects if the current device is a Sony Playstation.
   * 
   * @return detection of Sony Playstation
   */
  public boolean detectSonyPlaystation() {
    return userAgent.indexOf(DEVICE_PLAYSTATION) != -1;
  }

  /**
   * Detects if the current device is a handheld gaming device with a touchscreen and modern iPhone-class browser.
   * Includes the Playstation Vita.
   * 
   * @return detection of a handheld gaming device
   */
  public boolean detectGamingHandheld() {
    return (userAgent.indexOf(DEVICE_PLAYSTATION) != -1) && (userAgent.indexOf(DEVICE_PLAYSTATION_VITA) != -1);
  }

  /**
   * Detects if the current device is a Nintendo game device.
   * 
   * @return detection of Nintendo
   */
  public boolean detectNintendo() {
    return userAgent.indexOf(DEVICE_NINTENDO) != -1 || userAgent.indexOf(DEVICE_WII) != -1
        || userAgent.indexOf(DEVICE_NINTENTDO_DS) != -1;
  }

  /**
   * Detects if the current device is a Microsoft Xbox.
   * 
   * @return detection of Xbox
   */
  public boolean detectXbox() {
    return userAgent.indexOf(DEVICE_XBOX) != -1;
  }

  /**
   * Detects whether the device is a Brew-powered device.
   * 
   * @return detection of a Brew device
   */
  public boolean detectBrewDevice() {
    return userAgent.indexOf(DEVICE_BREW) != -1;
  }

  /**
   * Detects whether the device supports WAP or WML.
   * 
   * @return detection of a WAP- or WML-capable device
   */
  public boolean detectWapWml() {
    return httpAccept.indexOf(VND_WAP) != -1 || httpAccept.indexOf(WML) != -1;
  }

  /**
   * Detects if the current device supports MIDP, a mobile Java technology.
   * 
   * @return detection of a MIDP mobile Java-capable device
   */
  public boolean detectMidpCapable() {
    return userAgent.indexOf(DEVICE_MIDP) != -1 || httpAccept.indexOf(DEVICE_MIDP) != -1;
  }



  // *****************************
  // Device Classes
  // *****************************

  /**
   * Check to see whether the device is any device in the 'smartphone' category.
   * 
   * @return detection of a general smartphone device
   */
  public boolean detectSmartphone() {
    // Exclude duplicates from TierIphone
    return (detectTierIphone() || detectS60OssBrowser() || detectSymbianOS() || detectWindowsMobile()
        || detectBlackBerry() || detectMeegoPhone() || detectPalmOS()); // NOSONAR
  }

  /**
   * Detects if the current device is a mobile device. This method catches most of the popular modern devices. Excludes
   * Apple iPads and other modern tablets.
   * 
   * @return detection of any mobile device using the quicker method
   */
  public boolean detectMobileQuick() { // NOSONAR
    // Let's exclude tablets
    if (isTierTablet) {
      return false;
    }
    // Most mobile browsing is done on smartphones
    if (detectSmartphone()) {
      return true;
    }

    // Catch-all for many mobile devices
    if (userAgent.indexOf(MOBILE) != -1) {
      return true;
    }

    if (detectOperaMobile()) {
      return true;
    }

    // We also look for Kindle devices
    if (detectKindle() || detectAmazonSilk()) {
      return true;
    }

    if (detectWapWml() || detectMidpCapable() || detectBrewDevice()) {
      return true;
    }

    return (userAgent.indexOf(ENGINE_NETFRONT) != -1) || (userAgent.indexOf(ENGINE_UP_BROWSER) != -1);
  }

  /**
   * The longer and more thorough way to detect for a mobile device. Will probably detect most feature phones,
   * smartphone-class devices, Internet Tablets, Internet-enabled game consoles, etc. This ought to catch a lot of the
   * more obscure and older devices, also -- but no promises on thoroughness!
   * 
   * @return detection of any mobile device using the more thorough method
   */
  public boolean detectMobileLong() { // NOSONAR
    if (detectMobileQuick() || detectGameConsole()) {
      return true;
    }

    if (detectDangerHiptop() || detectMaemoTablet() || detectSonyMylo() || detectArchos()) {
      return true;
    }

    if ((userAgent.indexOf(DEVICE_PDA) != -1) && (userAgent.indexOf(DIS_UPDATE) < 0)) // no index found
    {
      return true;
    }

    // Detect older phones from certain manufacturers and operators.
    if ((userAgent.indexOf(UPLINK) != -1) || (userAgent.indexOf(ENGINE_OPENWEB) != -1)
        || (userAgent.indexOf(MANU_SAMSUNG1) != -1) || (userAgent.indexOf(MANU_SONY_ERICSSON) != -1)
        || (userAgent.indexOf(MANU_ERICSSON) != -1) || (userAgent.indexOf(SVC_DOCOMO) != -1)
        || (userAgent.indexOf(SVC_KDDI) != -1) || (userAgent.indexOf(SVC_VODAFONE) != -1)) { // NOSONAR
      return true;
    }

    return false;
  }

  // *****************************
  // For Mobile Web Site Design
  // *****************************

  /**
   * The quick way to detect for a tier of devices. This method detects for the new generation of HTML 5 capable, larger
   * screen tablets. Includes iPad, Android (e.g., Xoom), BB Playbook, WebOS, etc.
   * 
   * @return detection of any device in the Tablet Tier
   */
  public boolean detectTierTablet() {
    return detectIpad() || detectAndroidTablet() || detectBlackBerryTablet() || detectFirefoxOSTablet()
        || detectUbuntuTablet() || detectWebOSTablet(); // NOSONAR
  }

  /**
   * The quick way to detect for a tier of devices. This method detects for devices which can display iPhone-optimized
   * web content. Includes iPhone, iPod Touch, Android, Windows Phone 7 and 8, BB10, WebOS, Playstation Vita, etc.
   * 
   * @return detection of any device in the iPhone/Android/Windows Phone/BlackBerry/WebOS Tier
   */
  public boolean detectTierIphone() {
    return detectIphoneOrIpod() || detectAndroidPhone() || detectWindowsPhone() || detectBlackBerry10Phone()
        || (detectBlackBerryWebKit() && detectBlackBerryTouch()) || detectPalmWebOS() || detectBada() || detectTizen()
        || detectFirefoxOSPhone() || detectSailfishPhone() || detectUbuntuPhone() || detectGamingHandheld(); // NOSONAR
  }

  /**
   * The quick way to detect for a tier of devices. This method detects for devices which are likely to be capable of
   * viewing CSS content optimized for the iPhone, but may not necessarily support JavaScript. Excludes all iPhone Tier
   * devices.
   * 
   * @return detection of any device in the 'Rich CSS' Tier
   */
  public boolean detectTierRichCss() {
    boolean result = false;
    // The following devices are explicitly ok.
    // Note: 'High' BlackBerry devices ONLY
    if (detectMobileQuick()) {

      // Exclude iPhone Tier and e-Ink Kindle devices.
      if (!detectTierIphone() && !detectKindle()) { // NOSONAR

        // The following devices are explicitly ok.
        // Note: 'High' BlackBerry devices ONLY
        // Older Windows 'Mobile' isn't good enough for iPhone Tier.
        if (detectWebkit() || detectS60OssBrowser() || detectBlackBerryHigh() || detectWindowsMobile()
            || userAgent.indexOf(ENGINE_TELECA_Q) != -1) { // NOSONAR
          result = true;
        } // if detectWebkit()
      } // if !detectTierIphone()
    } // if detectMobileQuick()
    return result;
  }

  /**
   * The quick way to detect for a tier of devices. This method detects for all other types of phones, but excludes the
   * iPhone and RichCSS Tier devices.
   * 
   * @return detection of a mobile device in the less capable tier
   */
  public boolean detectTierOtherPhones() {
    // Exclude devices in the other 2 categories
    return detectMobileLong() && !detectTierIphone() && !detectTierRichCss();
  }

  public boolean isInitCompleted() {
    return initCompleted;
  }

  public boolean isWebkit() {
    return isWebkit;
  }

  public boolean isMobilePhone() {
    return isMobilePhone;
  }

  public boolean isAndroid() {
    return isAndroid;
  }

  public boolean isAndroidPhone() {
    return isAndroidPhone;
  }
}