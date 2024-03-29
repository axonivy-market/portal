package com.axonivy.portal.components.publicapi;

import java.util.Locale;

import com.axonivy.portal.components.util.Locales;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Portal API for support multilingual menu
 *
 */
public final class ApplicationMultiLanguageAPI {
	private ApplicationMultiLanguageAPI() {
	}

	/**
	 * Get CMS value for specific URI based on current locale
	 * 
	 * @param cmsURI CMS uri
	 * @return value at CMS uri
	 */
	public static String getCmsValueByUserLocale(String cmsURI) {
		Locale currentUserLocale = new Locales().getCurrentLocale();
		return Ivy.cms().coLocale(cmsURI, currentUserLocale);
	}
}
