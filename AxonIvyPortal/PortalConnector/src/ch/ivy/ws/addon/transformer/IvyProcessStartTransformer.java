package ch.ivy.ws.addon.transformer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import ch.ivy.ws.addon.transformer.exception.IvyTransformerException;
import ch.ivy.ws.addon.types.IvyProcessStart;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Transform a IProcessStart object to a IvyProcessStart object
 * 
 * @author mde
 *
 */
public class IvyProcessStartTransformer {
  private String serverUrl;

  public IvyProcessStartTransformer(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public IvyProcessStart transform(IProcessStart start) {
    IvyProcessStart result = new IvyProcessStart();
    try {
      result.setDescription(start.getDescription());
      result.setFullRequestPath(removeApplicationFromPath(start.getFullRequestPath()));
      result.setFullUserFriendlyRequestPath(removeApplicationFromPath(start.getFullUserFriendlyRequestPath()));
      result.setId(start.getId());
      if (start.getName() != null && start.getName().length() > 0) {
        result.setName(start.getName());
      } else {
        result.setName(removeApplicationFromPath(result.getFullUserFriendlyRequestPath()));
      }
      result.setStartLink(getStartLink(start));
      result.setServerAdress(serverUrl);
      result.setApplicationName(start.getProcessModelVersion().getApplication().getName());
      result.setProcessElementId(start.getProcessElementId());

    } catch (Exception exception) {
      Ivy.log().error(exception);
      throw new IvyTransformerException(exception);
    }
    return result;
  }


  public List<IvyProcessStart> transform(List<IProcessStart> starts) {
    List<IvyProcessStart> result = new ArrayList<IvyProcessStart>();
    for (IProcessStart start : starts) {
      result.add(transform(start));
    }
    return result;
  }

  private String removeApplicationFromPath(String path) {
    return path.substring(path.indexOf("/") + 1);
  }

  private String getStartLink(IProcessStart process) {
    IApplicationConfigurationManager appConfigManager = ServerFactory.getServer().getApplicationConfigurationManager();
    URI processUri = RequestUriFactory.createProcessStartUri(appConfigManager, process);

    String processStartURLFormat = "${serverUrl}${processPath}";
    Map<String, String> processStartURLParams = new HashMap<String, String>();
    processStartURLParams.put("serverUrl", serverUrl);
    processStartURLParams.put("processPath", processUri.toString());
    StrSubstitutor strSubstitutor = new StrSubstitutor(processStartURLParams);
    String processStartLink = strSubstitutor.replace(processStartURLFormat);

    return processStartLink;
  }
}
