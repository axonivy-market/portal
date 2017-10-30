package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

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
    String sanitizedText = sanitize(text);
    if (StringUtils.isBlank(extractTextFromHtml(sanitizedText))) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskDescriptionNotAvailable");
    }
    return sanitizedText;
  }

  public String createTaskDescriptionInTaskStart(String text) {
    String extractedText = extractTextFromHtml(text);
    if(StringUtils.isBlank(extractedText)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskDescriptionNotAvailable");
    }
    return extractedText;
  }

  private String extractTextFromHtml(String text) {
    String sanitizedText = sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }

  private String sanitize(String text) {
    return Jsoup.clean(text, Whitelist.relaxed().addAttributes(":all", "style"));
  }

  public boolean isDeleteFilterEnabledFor(TaskFilterData taskFilterData) {
    if (FilterType.ONLY_ME.equals(taskFilterData.getType())) {
      return true;
    } else {
      boolean isOwnerOfFilter =
          Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(-1L)
              .equals(taskFilterData.getUserId());
      boolean isAdmin = new PermissionBean().hasAdminPermission();
      if (isOwnerOfFilter || isAdmin) {
        return true;
      } else {
        return false;
      }
    }
  }
}
