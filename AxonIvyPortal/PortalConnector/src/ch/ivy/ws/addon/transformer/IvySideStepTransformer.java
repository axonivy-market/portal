package ch.ivy.ws.addon.transformer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

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
      result.setStartRequestUri(getStartLink(sideStep));
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

  private String getStartLink(ISideStepProcess sideStep) {
    URI sideStepUri = sideStep.getStartRequestUri();
    if (!isUrlBuiltFromSystemProperties) {
      return sideStepUri.toString();
    }

    String specifiedServerURL = ServerUrlUtils.buildUrlFromSystemProperties();
    String sideStepURLFormat = "${serverUrl}${processPath}";
    Map<String, String> sideStepURLParams = new HashMap<>();
    sideStepURLParams.put("serverUrl", specifiedServerURL);
    sideStepURLParams.put("processPath", sideStepUri.toString());
    StrSubstitutor strSubstitutor = new StrSubstitutor(sideStepURLParams);
    return strSubstitutor.replace(sideStepURLFormat);
  }
}
