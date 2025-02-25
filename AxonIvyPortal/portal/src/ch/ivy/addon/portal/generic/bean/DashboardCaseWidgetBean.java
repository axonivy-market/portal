package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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
}
