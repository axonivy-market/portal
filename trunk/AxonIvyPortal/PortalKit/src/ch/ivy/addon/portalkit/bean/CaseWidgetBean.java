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

import ch.ivy.addon.portalkit.bo.RemoteCase;

@ManagedBean
@ViewScoped
public class CaseWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long expandedCaseId;
  private RemoteCase deletingCase;

  public CaseWidgetBean() {
    expandedCaseId = -1L;
  }

  public Long getExpandedCaseId() {
    return expandedCaseId;
  }

  public void setExpandedCaseId(Long expandedCaseId, boolean alreadyExpanded) {
    if (alreadyExpanded) {
      this.expandedCaseId = 0L;
    } else {
      this.expandedCaseId = expandedCaseId;
    }
  }

  public RemoteCase getDeletingCase() {
    return deletingCase;
  }

  public void setDeletingCase(RemoteCase deletingCase) {
    this.deletingCase = deletingCase;
  }

  public String sanitizeHTML(String html) {
    return BLOCKS.and(FORMATTING).and(LINKS).and(STYLES).and(IMAGES).and(TABLES).sanitize(html);
  }
}
