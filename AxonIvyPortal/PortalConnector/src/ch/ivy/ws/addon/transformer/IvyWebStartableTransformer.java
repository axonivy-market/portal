package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.transformer.exception.IvyTransformerException;
import ch.ivy.ws.addon.types.IvyWebStartable;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * Transform a IWebStartable object to a IvyWebStartable object
 */
public class IvyWebStartableTransformer {

  private IvyWebStartableTransformer() {}

  public static IvyWebStartable transform(IWebStartable start, Boolean isUrlBuiltFromSystemProperties) {
    IvyWebStartable result = new IvyWebStartable();
    try {
      result.setName(start.getName());
      result.setDisplayName(start.getDisplayName());
      result.setDescription(start.getDescription());
      result.setLink(ServerUrlUtils.getStartLink(start.getLink().getRelativeEncoded(), isUrlBuiltFromSystemProperties));
      result.setActivatorDisplayName(start.getActivator().getDisplayName());
      result.setActivatorMemberName(start.getActivator().getMemberName());
    } catch (Exception exception) {
      Ivy.log().error(exception);
      throw new IvyTransformerException(exception);
    }
    return result;
  }


  public static List<IvyWebStartable> transform(List<IWebStartable> starts, Boolean isUrlBuiltFromSystemProperties) {
    List<IvyWebStartable> result = new ArrayList<>();
    for (IWebStartable start : starts) {
      result.add(transform(start, isUrlBuiltFromSystemProperties));
    }
    return result;
  }
}
