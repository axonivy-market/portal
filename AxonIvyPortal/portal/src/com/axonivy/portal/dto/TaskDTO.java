package com.axonivy.portal.dto;

import java.util.Date;

import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class TaskDTO {

  private long id;
  private String uuid;
  private String name;
  private WorkflowPriority priority;
  private TaskBusinessState businessState;
  private TaskState state;
  private IBusinessCase businessCase;
  private ICase technicalCase;
  private IUser workerUser;
  private String category;
  private Date startTimestamp;
  private Date expiryTimestamp;
  private Date endTimestamp;
  private Date delayTimestamp;
  private String description;
  private ISession workerSession;
  private WebLink startLink;
  private ICustomFields customFields;
  private String contextPath;
  private String applicationName;

  public TaskDTO() {
  }

  public TaskDTO(long taskId) {
    this(TaskUtils.findTaskById(taskId));
  }

  public TaskDTO(ITask task) {
    this.id = task.getId();
    this.uuid = task.uuid();
    this.name = task.names().current();
    this.priority = task.getPriority();
    this.state = task.getState();
    this.businessState = task.getBusinessState();
    this.businessCase = task.getCase().getBusinessCase();
    this.technicalCase = task.getCase();
    this.workerUser = task.getWorkerUser();
    this.category = task.getCategory().getName();
    this.startTimestamp = task.getStartTimestamp();
    this.expiryTimestamp = task.getExpiryTimestamp();
    this.endTimestamp = task.getEndTimestamp();
    this.delayTimestamp = task.getDelayTimestamp();
    this.description = task.getDescription();
    this.startLink = task.getStartLink();
    this.customFields = task.customFields();
    this.workerSession = task.getWorkerSession();
    this.contextPath = task.getApplication().getContextPath();
    this.applicationName = task.getApplication().getName();
  }

  public long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public WorkflowPriority getPriority() {
    return priority;
  }

  public TaskState getState() {
    return state;
  }

  public TaskBusinessState getBusinessState() {
    return businessState;
  }

  public IBusinessCase getBusinessCase() {
    return businessCase;
  }

  public ICase getCase() {
    return technicalCase;
  }

  public IUser getWorkerUser() {
    return workerUser;
  }

  public Date getStartTimestamp() {
    return startTimestamp;
  }

  public Date getExpiryTimestamp() {
    return expiryTimestamp;
  }

  public Date getEndTimestamp() {
    return endTimestamp;
  }

  public String getDescription() {
    return description;
  }

  public WebLink getStartLink() {
    return startLink;
  }

  public ICustomFields getCustomFields() {
    return customFields;
  }

  public void setCustomFields(ICustomFields customFields) {
    this.customFields = customFields;
  }

  public String getCategory() {
    return category;
  }

  public Date getDelayTimestamp() {
    return delayTimestamp;
  }

  public ISession getWorkerSession() {
    return this.workerSession;
  }

  public String getContextPath() {
    return contextPath;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }
}
