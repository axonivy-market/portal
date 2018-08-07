package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.transformer.exception.IvyTransformerException;
import ch.ivy.ws.addon.types.IvySideStep;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.casemap.runtime.ISideStepProcess;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Transform a {@link ISideStepProcess} object to a {@link IvySideStep} object
 */
public class IvySideStepTransformer {

  private boolean isUrlBuiltFromSystemProperties;

  public IvySideStepTransformer(boolean isUrlBuiltFromSystemProperties) {
    this.isUrlBuiltFromSystemProperties = isUrlBuiltFromSystemProperties;
  }

  public IvySideStep transform(ISideStepProcess sideStep) {
    IvySideStep result = new IvySideStep();
    try {
      result.setName(sideStep.getName());
      String sideStepUri = sideStep.getStartLink().getRelativeEncoded();
      result.setStartRequestUri(ServerUrlUtils.getStartLink(sideStepUri, isUrlBuiltFromSystemProperties));
      result.setIsAdhoc(false);
    } catch (Exception exception) {
      Ivy.log().error(exception);
      throw new IvyTransformerException(exception);
    }
    return result;
  }

  public List<IvySideStep> transform(List<ISideStepProcess> sideSteps) {
    List<IvySideStep> result = new ArrayList<>();
    for (ISideStepProcess sideStep : sideSteps) {
      result.add(transform(sideStep));
    }
    return result;
  }

}
