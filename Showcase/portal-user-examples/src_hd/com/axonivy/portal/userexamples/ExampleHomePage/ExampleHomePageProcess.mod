[Ivy]
1723A6F0283D003D 7.5.0 #module
>Proto >Proto Collection #zClass
Hs0 ExampleHomePageProcess Big #zClass
Hs0 RD #cInfo
Hs0 #process
Hs0 @TextInP .type .type #zField
Hs0 @TextInP .processKind .processKind #zField
Hs0 @TextInP .xml .xml #zField
Hs0 @TextInP .responsibility .responsibility #zField
Hs0 @UdInit f0 '' #zField
Hs0 @UdProcessEnd f1 '' #zField
Hs0 @UdEvent f3 '' #zField
Hs0 @UdExitEnd f4 '' #zField
Hs0 @PushWFArc f5 '' #zField
Hs0 @GridStep f6 '' #zField
Hs0 @PushWFArc f7 '' #zField
Hs0 @PushWFArc f2 '' #zField
>Proto Hs0 Hs0 ExampleHomePageProcess #zField
Hs0 f0 guid 1723A6F02AC488D2 #txt
Hs0 f0 method start() #txt
Hs0 f0 inParameterDecl '<> param;' #txt
Hs0 f0 outParameterDecl '<> result;' #txt
Hs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Hs0 f0 83 51 26 26 -16 15 #rect
Hs0 f0 @|UdInitIcon #fIcon
Hs0 f1 339 51 26 26 0 12 #rect
Hs0 f1 @|UdProcessEndIcon #fIcon
Hs0 f3 guid 1723A6F02C055ED2 #txt
Hs0 f3 actionTable 'out=in;
' #txt
Hs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Hs0 f3 83 147 26 26 -15 15 #rect
Hs0 f3 @|UdEventIcon #fIcon
Hs0 f4 211 147 26 26 0 12 #rect
Hs0 f4 @|UdExitEndIcon #fIcon
Hs0 f5 109 160 211 160 #arcP
Hs0 f6 actionTable 'out=in;
' #txt
Hs0 f6 actionCode 'import ch.ivyteam.ivy.workflow.start.IWebStartable;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());

String leaveRequestStartLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/LeaveRequest/start.ivp");
if (!StringUtils.isEmpty(leaveRequestStartLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(leaveRequestStartLink);
	userProcess.setProcessName(ivy.cms.co("/Processes/LeaveRequest/leaveRequest"));
	userProcess.setIcon("/Images/user-logout");
	userProcess.setDescription(ivy.cms.co("/Processes/LeaveRequest/description"));
	in.processes.add(userProcess);
}

List<IWebStartable> startableLinks = ivy.wf.getStartables(ivy.session.getSessionUser());
IWebStartable lendingCaseMap = null;
for(IWebStartable link : startableLinks) {
	if(link.getDisplayName().equals("Lending (Case Map)")) {
		lendingCaseMap = link;
	}
}

if (lendingCaseMap != null){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(lendingCaseMap.getLink().getRelativeEncoded());
	userProcess.setProcessName(ivy.cms.co("/Processes/CaseMap/name"));
	userProcess.setIcon("/Images/monetization-approve");
	userProcess.setDescription(ivy.cms.co("/Processes/CaseMap/description"));
	in.processes.add(userProcess);
}

UserProcess procurementProcess = new UserProcess();
procurementProcess.setProcessName(ivy.cms.co("/Processes/Procurement/name"));
procurementProcess.setIcon("/Images/shopping-cart-check");
procurementProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/commingSoon"));
in.processes.add(procurementProcess);

UserProcess contractNegotiationProcess = new UserProcess();
contractNegotiationProcess.setProcessName(ivy.cms.co("/Processes/ContractNegotiation/name"));
contractNegotiationProcess.setIcon("/Images/business-contract-handshake-sign");
contractNegotiationProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/commingSoon"));
in.processes.add(contractNegotiationProcess);

UserProcess marketingProcess = new UserProcess();
marketingProcess.setProcessName(ivy.cms.co("/Processes/MarketingCampaign/name"));
marketingProcess.setIcon("/Images/advertising-megaphone");
marketingProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/commingSoon"));
in.processes.add(marketingProcess);


' #txt
Hs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
    </language>
</elementInfo>
' #txt
Hs0 f6 168 42 112 44 -35 -8 #rect
Hs0 f6 @|StepIcon #fIcon
Hs0 f7 109 64 168 64 #arcP
Hs0 f2 280 64 339 64 #arcP
>Proto Hs0 .type com.axonivy.portal.userexamples.ExampleHomePage.ExampleHomePageData #txt
>Proto Hs0 .processKind HTML_DIALOG #txt
>Proto Hs0 -8 -8 16 16 16 26 #rect
>Proto Hs0 '' #fIcon
Hs0 f3 mainOut f5 tail #connect
Hs0 f5 head f4 mainIn #connect
Hs0 f0 mainOut f7 tail #connect
Hs0 f7 head f6 mainIn #connect
Hs0 f6 mainOut f2 tail #connect
Hs0 f2 head f1 mainIn #connect
