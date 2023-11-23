package com.axonivy.portal.response;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskData {
  private long id;
  private String name;
  private String priority;
  private String link;

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public TaskData() {
  }

  public TaskData(ITask task) {
    this.id = task.getId();
    this.name = StringUtils.defaultIfBlank(task.getName(),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable"));
    this.link = PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID(task.uuid());
    this.priority = task.getPriority().name();
  }

}
