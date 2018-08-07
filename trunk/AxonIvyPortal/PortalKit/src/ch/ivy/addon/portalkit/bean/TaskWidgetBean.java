package ch.ivy.addon.portalkit.bean;

import static org.owasp.html.Sanitizers.BLOCKS;
import static org.owasp.html.Sanitizers.FORMATTING;
import static org.owasp.html.Sanitizers.IMAGES;
import static org.owasp.html.Sanitizers.LINKS;
import static org.owasp.html.Sanitizers.STYLES;
import static org.owasp.html.Sanitizers.TABLES;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.util.HTMLDetector;
import ch.ivyteam.ivy.environment.Ivy;

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
}
