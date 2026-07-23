package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.axonivy.portal.util.BusinessDetailsUtils;
import com.axonivy.portal.util.CaseBehaviorUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivyteam.ivy.workflow.ICase;

@ViewScoped
@ManagedBean
public class DashboardCaseWidgetBean implements Serializable {

  private static final long serialVersionUID = 4733478835925712413L;

  private CaseEmptyMessage noCasesMessage;
  private boolean canAccessBusinessDetails;

  @PostConstruct
  public void init() {
    canAccessBusinessDetails = CaseBehaviorUtils.canAccessBusinessDetails();
  }

  public void handleRowSelectEventOnCaseWidget(SelectEvent<Object> event) throws IOException {
    ICase icase = ((ICase) event.getObject());
    if (canAccessBusinessDetails) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(BusinessDetailsUtils.getAdditionalCaseDetailsPageUri(icase));
    } else {
      PortalNavigator.navigateToPortalCaseDetails(icase.uuid());
    }
  }

  public CaseEmptyMessage getNoCasesMessage() {
    if (noCasesMessage == null) {
      List<CaseEmptyMessage> messages = Stream.of(CaseEmptyMessage.values())
          .collect(Collectors.toList());
      Random random = new Random();
      int index = random.ints(0, messages.size()).findFirst().getAsInt();
      noCasesMessage = messages.get(index);
    }
    return noCasesMessage;
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  private static final String DISPLAY_CACHE_KEY = "portalDashboardCaseDisplayCache";

  /**
   * Returns {@code column.display(caze)}, memoized for the duration of the current request.
   * <p>
   * A dashboard cell binds the same value twice - once for the cell text and once for its
   * (eagerly rendered) tooltip - so without memoization {@code display()}, and the per-row
   * {@code customFields()} work it performs, runs once per binding. The cache lives in the JSF
   * request map, so it is scoped to a single render pass and discarded between requests; there is
   * no cross-render staleness.
   */
  public Object display(CaseColumnModel column, ICase caze) {
    if (column == null || caze == null) {
      return null;
    }
    Map<String, Object> cache = displayCache();
    String key = System.identityHashCode(column) + ":" + caze.getId();
    if (cache.containsKey(key)) {
      return cache.get(key);
    }
    Object value = column.display(caze);
    cache.put(key, value);
    return value;
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> displayCache() {
    Map<String, Object> requestMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    return (Map<String, Object>) requestMap.computeIfAbsent(DISPLAY_CACHE_KEY, k -> new HashMap<>());
  }
}
