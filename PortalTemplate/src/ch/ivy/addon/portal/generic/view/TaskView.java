package ch.ivy.addon.portal.generic.view;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

public class TaskView {
  
  public static class Builder{
    
    private String pageTitle = StringUtils.EMPTY;
    private TaskLazyDataModel dataModel;
    private String keyword = StringUtils.EMPTY;
    private long remoteTaskId  = -1L;
    private long serverId = -1L;
    private MainMenuNode category;
    
    public Builder pageTitle(String pageTitle){
      this.pageTitle = pageTitle;
      return this;
    }
    
    public Builder dataModel(TaskLazyDataModel taskLazyDataModel){
      this.dataModel = taskLazyDataModel;
      return this;
    }
    
    public Builder keyword(String keyword){
      this.keyword = keyword;
      return this;
    }
    
    public Builder remoteTaskId(long taskId){
      this.remoteTaskId = taskId;
      return this;
    }
    
    public Builder serverId(long serverId){
      this.serverId = serverId;
      return this;
    }
    
    public Builder category(MainMenuNode category){
      this.category = category;
      return this;
    }
    
    public TaskView createNewTaskView(){
      return new TaskView(this);
    }
  }
  
  private final String pageTitle;
  private final TaskLazyDataModel dataModel;
  private final String keyword;
  private final long remoteTaskId;
  private final long serverId;
  private final MainMenuNode category;
  
  private TaskView(Builder builder) {
    pageTitle = builder.pageTitle;
    dataModel = builder.dataModel;
    keyword = builder.keyword;
    remoteTaskId = builder.remoteTaskId;
    serverId = builder.serverId;
    category = builder.category;
  }
  
  public static Builder create(){
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

  public MainMenuNode getCategory() {
    return category;
  }
}
