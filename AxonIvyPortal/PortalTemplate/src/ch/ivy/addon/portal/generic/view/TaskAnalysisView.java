package ch.ivy.addon.portal.generic.view;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskAnalysisView {

  public static class Builder {

    private String pageTitle = StringUtils.EMPTY;
    private TaskAnalysisLazyDataModel dataModel;
    private String keyword = StringUtils.EMPTY;
    private long remoteTaskId = -1L;
    private boolean canLinkBackCaseDetail = Boolean.FALSE;
    private String caseName = StringUtils.EMPTY;
    private boolean hideTaskFilter = Boolean.FALSE;
    private boolean showHeaderToolbar = Boolean.TRUE;
    private String noTaskFoundMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/noTask");
    private boolean compactMode = Boolean.TRUE;
    private Integer chunkSize = new Integer(10);

    private MainMenuNode category;

    public Builder pageTitle(String pageTitle) {
      this.pageTitle = pageTitle;
      return this;
    }

    public Builder caseName(String caseName) {
      this.caseName = caseName;
      return this;
    }

    public Builder dataModel(TaskAnalysisLazyDataModel taskAnalysisLazyDataModel) {
      this.dataModel = taskAnalysisLazyDataModel;
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
    
    public Builder compactMode(boolean compactMode) {
      this.compactMode = compactMode;
      return this;
    }

    public Builder chunkSize(Integer chunkSize) {
      this.chunkSize = chunkSize;
      return this;
    }

    public TaskAnalysisView createNewTaskView() {
      return new TaskAnalysisView(this);
    }
  }

  private final String pageTitle;
  private TaskAnalysisLazyDataModel dataModel;
  private final String keyword;
  private final long remoteTaskId;
  private final String caseName;
  private final boolean canLinkBackCaseDetail;
  private final MainMenuNode category;
  private final boolean hideTaskFilter;
  private final boolean showHeaderToolbar;
  private final String noTaskFoundMessage;
  private final boolean compactMode;
  private final Integer chunkSize;

  private TaskAnalysisView(Builder builder) {
    pageTitle = builder.pageTitle;
    dataModel = builder.dataModel;
    keyword = builder.keyword;
    remoteTaskId = builder.remoteTaskId;
    category = builder.category;
    canLinkBackCaseDetail = builder.canLinkBackCaseDetail;
    caseName = builder.caseName;
    hideTaskFilter = builder.hideTaskFilter;
    showHeaderToolbar = builder.showHeaderToolbar;
    noTaskFoundMessage = builder.noTaskFoundMessage;
    compactMode = builder.compactMode;
    chunkSize = builder.chunkSize;
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

  public TaskAnalysisLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(TaskAnalysisLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String getKeyword() {
    return keyword;
  }

  public long getRemoteTaskId() {
    return remoteTaskId;
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

  public boolean isHideTaskFilter() {
    return hideTaskFilter;
  }

  public boolean isShowHeaderToolbar() {
    return showHeaderToolbar;
  }

  public boolean isCompactMode() {
    return compactMode;
  }

  public Integer getChunkSize() {
    return chunkSize;
  }
  
}
