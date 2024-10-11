package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivyteam.ivy.workflow.ICase;

@ViewScoped
@ManagedBean
public class DashboardCaseWidgetBean implements Serializable {

  private static final long serialVersionUID = 4733478835925712413L;

  private CaseEmptyMessage noCasesMessage;

  public void navigateToSelectedCaseDetails(SelectEvent<Object> event) {
    String uuid = ((ICase) event.getObject()).uuid();
    PortalNavigator.navigateToPortalCaseDetails(uuid);
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
