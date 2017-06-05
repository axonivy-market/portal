package ch.ivy.addon.portalkit.mapper;

import java.net.URI;
import java.net.URISyntaxException;

import ch.ivy.addon.portalkit.bo.RemoteSideStep;
import ch.ivy.addon.portalkit.util.UrlValidator;
import ch.ivy.ws.addon.IvySideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.List;

public class RemoteSideStepMapper {

  protected static RemoteSideStep mapSideStep(IvySideStep sideStep, String host) throws URISyntaxException {
    RemoteSideStep result = new RemoteSideStep();
    result.setName(sideStep.getName());
    final String startLink = sideStep.getStartLink();
    Ivy.log().warn("mapper {0}", startLink);
    Ivy.log().warn("host {0}", host);
    result.setStartLink(UrlValidator.isValidUrl(startLink) ? startLink : host + startLink);
    Ivy.log().warn("link {0}", result.getStartLink());
    result.setStartRequestUri(new URI(sideStep.getStartLink()));
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
