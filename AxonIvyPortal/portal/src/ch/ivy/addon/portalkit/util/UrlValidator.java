package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.enums.Protocol;

public class UrlValidator {

  private UrlValidator() {}
  
	public static boolean isValidUrl(String url) {
		for (Protocol protocol: Protocol.values()) {
			if (url.startsWith(protocol.getValue())) {
				return true;
			}
		}
		return false;
	}
}
