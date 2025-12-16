package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.OAuthProvider;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.auth.AuthProvider;

public class OAuthProviderService {
  private static OAuthProviderService instance;

  public static OAuthProviderService getInstance() {
    if (instance == null) {
      instance = new OAuthProviderService();
    }
    return instance;
  }

  public List<OAuthProvider> getOAuthProviders(String callbackUrl) {
    var securityContext = ISecurityContext.current();
    return securityContext.authProviders().stream().map((provider) -> toOAuthPovider(provider, callbackUrl)).toList();
  }

  private OAuthProvider toOAuthPovider(AuthProvider provider, String callbackUrl) {
    return new OAuthProvider(provider.displayName(), provider.logo(), provider.url(callbackUrl));
  }
}