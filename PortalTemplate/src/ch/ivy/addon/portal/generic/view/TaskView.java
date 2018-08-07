package ch.ivy.addon.portal.generic.view;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskView {

  public static class Builder {

    private String pageTitle = StringUtils.EMPTY;
    private TaskLazyDataModel dataModel;
    private String keyword = StringUtils.EMPTY;
    private long remoteTaskId = -1L;
    private long serverId = -1L;
    private boolean canLinkBackCaseDetail = Boolean.FALSE;
    private String caseName = StringUtils.EMPTY;
    private long caseId = -1L;
    private boolean hideTaskFilter = Boolean.FALSE;
    private boolean showHeaderToolbar = Boolean.TRUE;
    private String noTaskFoundMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/noTask");

    private MainMenuNode category;

    public Builder pageTitle(String pageTitle) {
      this.pageTitle = pageTitle;
      return this;
    }

    public Builder caseName(String caseName) {
      this.caseName = caseName;
      return this;
    }

    public Builder caseId(long caseId) {
      this.caseId = caseId;
      return this;
    }

    public Builder dataModel(TaskLazyDataModel taskLazyDataModel) {
      this.dataModel = taskLazyDataModel;
      return this;
    }

    public Builder keyword(String keyword) {
      this.keyword = keyword;
      return this;
    }

    public Builder remoteTaskId(long taskId) {
      this.remoteTaskId = taskId;
      return this;
    }

    public Builder serverId(long serverId) {
      this.serverId = serverId;
      return this;
    }

    public Builder canLinkBackCaseDetail(boolean canLinkBackCaseDetail) {
      this.canLinkBackCaseDetail = canLinkBackCaseDetail;
      return this;
    }

    public Builder category(MainMenuNode category) {
      this.category = category;
      return this;
    }
    
    public Builder hideTaskFilter(boolean hideTaskFilter) {
      this.hideTaskFilter = hideTaskFilter;
      return this;
    }
    
    public Builder showHeaderToolbar(boolean showHeaderToolbar) {
      this.showHeaderToolbar = showHeaderToolbar;
      return this;
    }
    
    public Builder noTaskFoundMessage(String noTaskFoundMessage) {
      this.noTaskFoundMessage = noTaskFoundMessage;
      return this;
    }

    public TaskView createNewTaskView() {
      return new TaskView(this);
    }
  }

  private final String pageTitle;
  private final TaskLazyDataModel dataModel;
  private final String keyword;
  private final long remoteTaskId;
  private final long serverId;
  private final String caseName;
  private final long caseId;
  private final boolean canLinkBackCaseDetail;
  private final MainMenuNode category;
  private final boolean hideTaskFilter;
  private final boolean showHeaderToolbar;
  private final String noTaskFoundMessage;

  private TaskView(Builder builder) {
    pageTitle = builder.pageTitle;
    dataModel = builder.dataModel;
    keyword = builder.keyword;
    remoteTaskId = builder.remoteTaskId;
    serverId = builder.serverId;
    category = builder.category;
    canLinkBackCaseDetail = builder.canLinkBackCaseDetail;
    caseName = builder.caseName;
    caseId = builder.caseId;
    hideTaskFilter = builder.hideTaskFilter;
    showHeaderToolbar = builder.showHeaderToolbar;
    noTaskFoundMessage = builder.noTaskFoundMessage;
  }

  public String getNoTaskFoundMessage() {
    return noTaskFoundMessage;
  }

  public static Builder create() {
    return new Builder();
  }

  public String getPageTitle() {
    return pageTitle;
  }

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public String getKeyword() {
    return keyword;
  }

  public long getRemoteTaskId() {
    return remoteTaskId;
  }

  public long getServerId() {
    return serverId;
  }

  public boolean canLinkBackCaseDetail() {
    return canLinkBackCaseDetail;
  }

  public MainMenuNode getCategory() {
    return category;
  }

  public String getCaseName() {
    return caseName;
  }

  public long getCaseId() {
    return caseId;
  }

  public boolean isHideTaskFilter() {
    return hideTaskFilter;
  }

  public boolean isShowHeaderToolbar() {
    return showHeaderToolbar;
  }
}
