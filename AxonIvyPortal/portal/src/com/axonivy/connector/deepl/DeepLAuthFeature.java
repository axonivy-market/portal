package com.axonivy.connector.deepl;

import java.io.IOException;

import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import ch.ivyteam.ivy.rest.client.FeatureConfig;

public class DeepLAuthFeature implements Feature {

  @Override
  public boolean configure(FeatureContext context) {
    context.register(new AuthFilter(), Priorities.AUTHENTICATION);
    return true;
  }

  private static class AuthFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext ctxt) throws IOException {
      var config = new FeatureConfig(ctxt.getConfiguration(), DeepLAuthFeature.class);
      String key = config.readMandatory("AUTH.deepLKey");
      ctxt.getHeaders().putSingle("Authorization", "DeepL-Auth-Key "+key);
    }

  }

}
