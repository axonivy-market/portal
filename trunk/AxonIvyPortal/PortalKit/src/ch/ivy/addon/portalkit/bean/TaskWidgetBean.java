package ch.ivy.addon.portalkit.bean;

import static org.owasp.html.Sanitizers.BLOCKS;
import static org.owasp.html.Sanitizers.FORMATTING;
import static org.owasp.html.Sanitizers.IMAGES;
import static org.owasp.html.Sanitizers.LINKS;
import static org.owasp.html.Sanitizers.STYLES;
import static org.owasp.html.Sanitizers.TABLES;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

@ManagedBean
@ViewScoped
public class TaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long expandedTaskId;
  private TaskLazyDataModel dataModel;

  public TaskWidgetBean() {
    expandedTaskId = -1L;
    dataModel = new TaskLazyDataModel();
  }

  public Long getExpandedTaskId() {
    return expandedTaskId;
  }

  public void setExpandedTaskId(Long expandedCaseId, boolean alreadyExpanded) {
    if (alreadyExpanded) {
      this.expandedTaskId = 0L;
    } else {
      this.expandedTaskId = expandedCaseId;
    }
  }

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String sanitizeHTML(String html) {
    return BLOCKS.and(FORMATTING).and(LINKS).and(STYLES).and(IMAGES).and(TABLES).sanitize(html);
  }
}
