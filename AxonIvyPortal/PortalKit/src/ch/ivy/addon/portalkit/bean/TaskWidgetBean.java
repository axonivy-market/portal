package ch.ivy.addon.portalkit.bean;

import static org.owasp.html.Sanitizers.BLOCKS;
import static org.owasp.html.Sanitizers.FORMATTING;
import static org.owasp.html.Sanitizers.IMAGES;
import static org.owasp.html.Sanitizers.LINKS;
import static org.owasp.html.Sanitizers.STYLES;
import static org.owasp.html.Sanitizers.TABLES;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.HTMLDetector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean
@ViewScoped
public class TaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long expandedTaskId;
  private TaskLazyDataModel dataModel;

  public TaskWidgetBean() {
    expandedTaskId = -1L;
    dataModel = new TaskLazyDataModel();
    dataModel.setCompactMode(true);
  }

  public Long getExpandedTaskId() {
    return expandedTaskId;
  }

  public void setExpandedTaskId(Long expandedTaskId, boolean alreadyExpanded) {
    if (alreadyExpanded) {
      this.expandedTaskId = 0L;
    } else {
      this.expandedTaskId = expandedTaskId;
    }
  }

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String sanitizeHTML(String text) {
    String sanitizedText =
        Optional.ofNullable(text)
            .map(t -> BLOCKS.and(FORMATTING).and(LINKS).and(STYLES).and(IMAGES).and(TABLES).sanitize(t)).orElse("");
    if (StringUtils.isEmpty(sanitizedText.trim())) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskDescriptionNotAvailable");
    }
    return sanitizedText;
  }

  public String createTaskDescriptionInTaskStart(String text) {
    if (containsHTML(text)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/descriptionInHTML");
    }
    if (Optional.ofNullable(text).map(t -> t.trim().equals("")).orElse(true)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskDescriptionNotAvailable");
    }
    return text;
  }
  
  private boolean containsHTML(String text) {
    return Optional.ofNullable(text).map(t -> HTMLDetector.isHtml(t)).orElse(false);
  }
  
  public String createAdhocLink(ITask task) throws Exception{
	  if(Objects.isNull(task)){
			return StringUtils.EMPTY;
		}
		ICase currentCase = task.getCase();
		if(Objects.isNull(currentCase)){
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
  
  public boolean hasSelfService() throws Exception{
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String adhocUrl = processStartCollector.findACMLink();
    return !adhocUrl.isEmpty();
  }
  
  public boolean hasPermissionToAddAdhoc(ITask task){
	  PermissionCheckerService permissionService = new PermissionCheckerService();
	  boolean isAdmin =  permissionService.hasPermission(IPermission.TASK_READ_ALL);
	 
	  return isAdmin && (TaskState.SUSPENDED.equals(task.getState()) || TaskState.RESUMED.equals(task.getState()) ||  TaskState.PARKED.equals(task.getState()));
  }
  
}
