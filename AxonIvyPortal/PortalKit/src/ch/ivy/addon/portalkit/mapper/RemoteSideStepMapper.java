package ch.ivy.addon.portalkit.mapper;

import java.net.URI;
import java.net.URISyntaxException;

import ch.ivy.addon.portalkit.bo.RemoteSideStep;
import ch.ivy.addon.portalkit.util.UrlValidator;
import ch.ivy.ws.addon.IvySideStep;
import ch.ivyteam.ivy.scripting.objects.List;

public class RemoteSideStepMapper {

  protected static RemoteSideStep mapSideStep(IvySideStep sideStep, String host) throws URISyntaxException {
    RemoteSideStep result = new RemoteSideStep();
    result.setName(sideStep.getName());
    String requestUri = sideStep.getStartRequestUri();
    String requestUrl = UrlValidator.isValidUrl(requestUri) ? requestUri : host + requestUri;
    result.setStartRequestUri(new URI(requestUrl));
    return result;
  }

  public static List<RemoteSideStep> mapSideSteps(List<IvySideStep> ivySideSteps, String host)
      throws URISyntaxException {
    List<RemoteSideStep> sideSteps = List.create(RemoteSideStep.class);

    for (IvySideStep ivySideStep : ivySideSteps) {
      RemoteSideStep sideStep = mapSideStep(ivySideStep, host);

      if (null != sideStep) {
        sideSteps.add(sideStep);
      }
    }
    return sideSteps;
  }
}
