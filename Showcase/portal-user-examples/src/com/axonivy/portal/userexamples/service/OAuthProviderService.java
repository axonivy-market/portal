package com.axonivy.portal.userexamples.service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.axonivy.portal.userexamples.dto.OAuthProvider;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.identity.core.auth.oauth2.OAuth2Url;
import ch.ivyteam.ivy.security.identity.spi.IdentityProvider;
import ch.ivyteam.ivy.security.identity.spi.auth.oauth2.OAuth2Authenticator;
import ch.ivyteam.ivy.security.restricted.ISecurityContextInternal;

public class OAuthProviderService {
  private static OAuthProviderService instance;

  public static OAuthProviderService getInstance() {
    if (instance == null) {
      instance = new OAuthProviderService();
    }
    return instance;
  }

  public List<OAuthProvider> getOAuthProviders(String callbackUrl) {
    var securityContext = (ISecurityContextInternal) ISecurityContext.current();
    return securityContext.identityProviders().stream().filter(p -> p.authenticator() instanceof OAuth2Authenticator)
        .map(p -> toOAuthPovider(p, callbackUrl)).collect(Collectors.toList());
  }

  private OAuthProvider toOAuthPovider(IdentityProvider provider, String callbackUrl) {
    var initUri = OAuth2Url.initUri(ISecurityContext.current(), provider, callbackUrl);
    return new OAuthProvider(provider.displayName(), loadResource(provider.logo()), initUri);
  }

  private String loadResource(URI uri) {
    try {
      return IOUtils.toString(uri, StandardCharsets.UTF_8);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}