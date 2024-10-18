package com.axonivy.portal.userexamples.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.userexamples.dto.UserProcess;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class ExampleHomeBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<IWebStartable> webStartables;
  private List<UserProcess> processes;

  @PostConstruct
  public void initData() {
    processes = new ArrayList<>();
    String leaveRequestStartLink =
        ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/LeaveRequest/start.ivp");
    if (!StringUtils.isEmpty(leaveRequestStartLink)) {
      UserProcess userProcess = new UserProcess();
      userProcess.setLink(leaveRequestStartLink);
      userProcess.setProcessName(Ivy.cms().co("/Processes/LeaveRequest/leaveRequest"));
      userProcess.setIcon("si si-user-logout");
      userProcess.setDescription(Ivy.cms().co("/Processes/LeaveRequest/description"));
      processes.add(userProcess);
    }

    getWebStartables().stream().filter(startable -> startable.getDisplayName().equals("Lending (Case Map)"))
        .findAny().ifPresent(lendingCaseMap -> {
          var userProcess = new UserProcess();
          userProcess.setLink(lendingCaseMap.getLink().getRelativeEncoded());
          userProcess.setProcessName(Ivy.cms().co("/Processes/CaseMap/name"));
          userProcess.setIcon("si si-monetization-approve");
          userProcess.setDescription(Ivy.cms().co("/Processes/CaseMap/description"));
          processes.add(userProcess);
        });

    var procurementProcess = new UserProcess();
    procurementProcess.setProcessName(Ivy.cms().co("/Processes/Procurement/name"));
    procurementProcess.setIcon("si si-shopping-cart-check");
    procurementProcess.setDescription(Ivy.cms().co("/Processes/UserExampleGuide/comingSoon"));
    processes.add(procurementProcess);

    var contractNegotiationProcess = new UserProcess();
    contractNegotiationProcess.setProcessName(Ivy.cms().co("/Processes/ContractNegotiation/name"));
    contractNegotiationProcess.setIcon("si si-business-contract-handshake-sign");
    contractNegotiationProcess.setDescription(Ivy.cms().co("/Processes/UserExampleGuide/comingSoon"));
    processes.add(contractNegotiationProcess);

    var marketingProcess = new UserProcess();
    marketingProcess.setProcessName(Ivy.cms().co("/Processes/MarketingCampaign/name"));
    marketingProcess.setIcon("si si-advertising-megaphone");
    marketingProcess.setDescription(Ivy.cms().co("/Processes/UserExampleGuide/comingSoon"));
    processes.add(marketingProcess);
  }

  private List<IWebStartable> getWebStartables() {
    if (CollectionUtils.isEmpty(webStartables)) {
      webStartables = Ivy.session().getAllStartables().toList();
    }
    return webStartables;
  }

  public List<UserProcess> getProcesses() {
    return processes;
  }

  public void setProcesses(List<UserProcess> processes) {
    this.processes = processes;
  }

}
