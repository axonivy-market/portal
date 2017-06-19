package ch.ivy.addon.portalkit.mapper;

import java.net.URISyntaxException;

import ch.ivy.addon.portalkit.bo.RemoteSideStep;
import ch.ivy.addon.portalkit.util.UrlValidator;
import ch.ivy.ws.addon.IvySideStep;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.request.restricted.WebLinkFactory;
import ch.ivyteam.ivy.scripting.objects.List;

public class RemoteSideStepMapper {

  protected static RemoteSideStep mapSideStep(IvySideStep sideStep, String host) throws URISyntaxException {
    RemoteSideStep result = new RemoteSideStep();
    result.setName(sideStep.getName());
    String requestUri = sideStep.getStartRequestUri();
    WebLink webLink = UrlValidator.isValidUrl(requestUri) ? new WebLinkFactory().createFromContextRelative(requestUri) : new WebLink(host + requestUri);
    result.setStartLink(webLink);
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
