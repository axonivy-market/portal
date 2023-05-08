package com.axonivy.portal.components.publicapi;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.security.ISecurityContext;

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
		Ivy.session().getSessionUser().getLanguage();
		Locale currentUserLocale = getCurrentLocale();
		return Ivy.cms().coLocale(cmsURI, currentUserLocale);
	}

	private static Locale getCurrentLocale() {
		Locale userLanguage = Ivy.session().getSessionUser().getLanguage();
		if (userLanguage != null) {
			return userLanguage;
		}
		return new LanguageConfigurator(ISecurityContext.current()).content();
	}
}