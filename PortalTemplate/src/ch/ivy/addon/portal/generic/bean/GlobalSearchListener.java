package ch.ivy.addon.portal.generic.bean;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import ch.ivy.addon.portal.generic.events.GlobalSearchEvent;
import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.event.ISystemEventListener;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.service.ServiceException;

public class GlobalSearchListener extends AbstractProcessStartEventBean implements ISystemEventListener {

  public GlobalSearchListener() {
    super("GlobalSearchListener","A listener listening to creation event of Search task process");
  }
  
  @Override
  @PublicAPI
  public void start(IProgressMonitor monitor) throws ServiceException {
    super.start(monitor);
    disablePolling();
    subscribeToSystemEvents();
  }

  private void subscribeToSystemEvents() {
    this.getEventBeanRuntime().getProcessModelVersion().getApplication()
        .addSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
  }

  private void disablePolling() {
    this.getEventBeanRuntime().setPollTimeInterval(0L);
  }

  @Override
  @PublicAPI
  public void poll() {
    // This method should do nothing.
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public void handleSystemEvent(SystemEvent<?> event) {
    if (GlobalSearchEvent.hasCorrectSignature(event)) {
      GlobalSearchEvent adhocProcessCreationEvent = (GlobalSearchEvent) event.getParameter();
      startProcessForCreatingSearchTask(adhocProcessCreationEvent);
    }
  }

  private void startProcessForCreatingSearchTask(GlobalSearchEvent globalSearchEvent) {
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("keyword", globalSearchEvent.getKeyword());
      parameters.put("username", globalSearchEvent.getUsername());
      try {
        this.getEventBeanRuntime().fireProcessStartEventRequest(null, "Search processes, tasks from all servers", parameters);
      } catch (RequestException e) {
         throw new ServiceException(e);
      }
  }

  @Override
  @PublicAPI
  public void stop(IProgressMonitor monitor) throws ServiceException {
    unsubscribeToSystemEvents();
    super.stop(monitor);
  }

  private void unsubscribeToSystemEvents() {
    this.getEventBeanRuntime().getProcessModelVersion().getApplication()
        .removeSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
  }
  
}
