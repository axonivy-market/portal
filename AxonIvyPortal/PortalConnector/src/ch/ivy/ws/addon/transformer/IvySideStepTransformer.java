package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.transformer.exception.IvyTransformerException;
import ch.ivy.ws.addon.types.IvySideStep;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Transform a {@link IStartableSideStep} object to a {@link IvySideStep} object
 */
public class IvySideStepTransformer {

  private boolean isUrlBuiltFromSystemProperties;

  public IvySideStepTransformer(boolean isUrlBuiltFromSystemProperties) {
    this.isUrlBuiltFromSystemProperties = isUrlBuiltFromSystemProperties;
  }

  public IvySideStep transform(IStartableSideStep sideStep) {
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

  public List<IvySideStep> transform(List<IStartableSideStep> sideSteps) {
    List<IvySideStep> result = new ArrayList<>();
    for (IStartableSideStep sideStep : sideSteps) {
      result.add(transform(sideStep));
    }
    return result;
  }

}
