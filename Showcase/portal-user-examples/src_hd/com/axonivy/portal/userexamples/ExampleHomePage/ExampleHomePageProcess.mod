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
Hs0 @UserDialog f9 '' #zField
Hs0 @UdProcessEnd f11 '' #zField
Hs0 @PushWFArc f12 '' #zField
Hs0 @UdMethod f13 '' #zField
Hs0 @Alternative f14 '' #zField
Hs0 @PushWFArc f15 '' #zField
Hs0 @PushWFArc f16 '' #zField
Hs0 @UserDialog f8 '' #zField
Hs0 @PushWFArc f10 '' #zField
Hs0 @PushWFArc f17 '' #zField
Hs0 @PushWFArc f18 '' #zField
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
Hs0 f1 403 51 26 26 0 12 #rect
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
Hs0 f4 251 147 26 26 0 12 #rect
Hs0 f4 @|UdExitEndIcon #fIcon
Hs0 f5 109 160 251 160 #arcP
Hs0 f6 actionTable 'out=in;
' #txt
Hs0 f6 actionCode 'import ch.ivyteam.ivy.workflow.start.IWebStartable;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector collector = new ProcessStartCollector();

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
procurementProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/comingSoon"));
in.processes.add(procurementProcess);

UserProcess contractNegotiationProcess = new UserProcess();
contractNegotiationProcess.setProcessName(ivy.cms.co("/Processes/ContractNegotiation/name"));
contractNegotiationProcess.setIcon("/Images/business-contract-handshake-sign");
contractNegotiationProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/comingSoon"));
in.processes.add(contractNegotiationProcess);

UserProcess marketingProcess = new UserProcess();
marketingProcess.setProcessName(ivy.cms.co("/Processes/MarketingCampaign/name"));
marketingProcess.setIcon("/Images/advertising-megaphone");
marketingProcess.setDescription(ivy.cms.co("/Processes/UserExampleGuide/comingSoon"));
in.processes.add(marketingProcess);



' #txt
Hs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
    </language>
</elementInfo>
' #txt
Hs0 f6 208 42 112 44 -35 -8 #rect
Hs0 f6 @|StepIcon #fIcon
Hs0 f7 109 64 208 64 #arcP
Hs0 f2 320 64 403 64 #arcP
Hs0 f9 dialogId com.axonivy.portal.userexamples.leaverequest.LeaveRequestOverview #txt
Hs0 f9 startMethod start(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Hs0 f9 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param;' #txt
Hs0 f9 requestMappingAction 'param.userProcess=in.process;
' #txt
Hs0 f9 responseMappingAction 'out=in;
' #txt
Hs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave Request&#13;
Overview</name>
    </language>
</elementInfo>
' #txt
Hs0 f9 408 234 128 44 -40 -16 #rect
Hs0 f9 @|UserDialogIcon #fIcon
Hs0 f11 659 339 26 26 0 12 #rect
Hs0 f11 @|UdProcessEndIcon #fIcon
Hs0 f12 536 256 672 339 #arcP
Hs0 f12 1 672 256 #addKink
Hs0 f12 0 0.7256302308797495 0 0 #arcLabel
Hs0 f13 guid 1725039A6FEBEA96 #txt
Hs0 f13 method navigate(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Hs0 f13 inParameterDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess process> param;' #txt
Hs0 f13 inParameterMapAction 'out.process=param.process;
' #txt
Hs0 f13 outParameterDecl '<> result;' #txt
Hs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate(UserProcess)</name>
    </language>
</elementInfo>
' #txt
Hs0 f13 83 339 26 26 -25 15 #rect
Hs0 f13 @|UdMethodIcon #fIcon
Hs0 f14 272 336 32 32 0 16 #rect
Hs0 f14 @|AlternativeIcon #fIcon
Hs0 f15 expr in #txt
Hs0 f15 outCond ivy.cms.co("/Processes/LeaveRequest/name").equals(in.process.processName) #txt
Hs0 f15 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave request</name>
    </language>
</elementInfo>
' #txt
Hs0 f15 288 336 408 256 #arcP
Hs0 f15 1 288 256 #addKink
Hs0 f15 1 0.35833333333333334 0 -11 #arcLabel
Hs0 f16 109 352 272 352 #arcP
Hs0 f8 dialogId com.axonivy.portal.userexamples.credit.LendingOverview #txt
Hs0 f8 startMethod start(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Hs0 f8 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param;' #txt
Hs0 f8 requestMappingAction 'param.userProcess=in.process;
' #txt
Hs0 f8 responseMappingAction 'out=in;
' #txt
Hs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Lending overview</name>
    </language>
</elementInfo>
' #txt
Hs0 f8 408 330 112 44 -47 -8 #rect
Hs0 f8 @|UserDialogIcon #fIcon
Hs0 f10 expr in #txt
Hs0 f10 outCond ivy.cms.co("/Processes/CaseMap/name").equals(in.process.processName) #txt
Hs0 f10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>lending</name>
    </language>
</elementInfo>
' #txt
Hs0 f10 304 352 408 352 #arcP
Hs0 f10 0 0.38461538461538464 0 -10 #arcLabel
Hs0 f17 520 352 659 352 #arcP
Hs0 f17 0 0.31403006125915683 0 0 #arcLabel
Hs0 f18 expr in #txt
Hs0 f18 288 368 672 365 #arcP
Hs0 f18 1 288 464 #addKink
Hs0 f18 2 672 464 #addKink
Hs0 f18 1 0.6127782473457252 0 0 #arcLabel
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
Hs0 f9 mainOut f12 tail #connect
Hs0 f12 head f11 mainIn #connect
Hs0 f14 out f15 tail #connect
Hs0 f15 head f9 mainIn #connect
Hs0 f13 mainOut f16 tail #connect
Hs0 f16 head f14 in #connect
Hs0 f14 out f10 tail #connect
Hs0 f10 head f8 mainIn #connect
Hs0 f8 mainOut f17 tail #connect
Hs0 f17 head f11 mainIn #connect
Hs0 f14 out f18 tail #connect
Hs0 f18 head f11 mainIn #connect
