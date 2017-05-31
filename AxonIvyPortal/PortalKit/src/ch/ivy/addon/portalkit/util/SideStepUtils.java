package ch.ivy.addon.portalkit.util;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public final class SideStepUtils {

  private SideStepUtils() {}

  public static String createAdhocLink(ITask task) throws Exception {
    if (Objects.isNull(task)) {
      return StringUtils.EMPTY;
    }
    ICase currentCase = task.getCase();
    if (Objects.isNull(currentCase)) {
      return StringUtils.EMPTY;
    }
    Long businessCaseId = currentCase.getId();
    String adhocUrl = StringUtils.EMPTY;

    String host = RequestUriFactory.createServerUri((IHttpRequest) Ivy.request()).toString();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    adhocUrl = processStartCollector.findACMLink();
    adhocUrl = adhocUrl + "?businessCaseId=" + businessCaseId + "&originalTaskId=" + task.getId();

    return host + adhocUrl;
  }

  public static boolean hasSelfService() throws Exception {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String adhocUrl = processStartCollector.findACMLink();
    return !adhocUrl.isEmpty();
  }


}
