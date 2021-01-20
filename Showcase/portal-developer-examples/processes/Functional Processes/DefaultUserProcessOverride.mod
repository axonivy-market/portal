[Ivy]
1657E93190721001 9.2.0 #module
>Proto >Proto Collection #zClass
Ds0 DefaultUserProcess Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartSub f0 '' #zField
Ds0 @EndSub f1 '' #zField
Ds0 @GridStep f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @InfoButton f5 '' #zField
>Proto Ds0 Ds0 DefaultUserProcess #zField
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> defaultUserProcesses> result;' #txt
Ds0 f0 outParamTable 'result.defaultUserProcesses=in.defaultUserProcesses;
' #txt
Ds0 f0 callSignature createDefaultUserProcesses() #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createDefaultUserProcesses()</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 51 83 26 26 14 0 #rect
Ds0 f1 51 339 26 26 14 0 #rect
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

String createAlphaLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp");
if (!StringUtils.isEmpty(createAlphaLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(createAlphaLink);
	userProcess.setProcessName(ivy.cms.co("/Processes/ProcessHistoryComponent/AlphaCompany/name"));
	userProcess.setIcon("fa-building");
	userProcess.setIndex(1);
	in.defaultUserProcesses.add(userProcess);
}

String viewAlphaHistoryLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp");
if (!StringUtils.isEmpty(viewAlphaHistoryLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(viewAlphaHistoryLink);
	userProcess.setProcessName(ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompany/name"));
	userProcess.setIcon("fa-list-alt");
	userProcess.setIndex(2);
	in.defaultUserProcesses.add(userProcess);
}

String categoriedLeaveRequestLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompanyInDialog.ivp");
if (!StringUtils.isEmpty(categoriedLeaveRequestLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(categoriedLeaveRequestLink);
	userProcess.setProcessName(ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompanyInDialog/name"));
	userProcess.setIcon("fa-asterisk");
	userProcess.setIndex(3);
	in.defaultUserProcesses.add(userProcess);
}

' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create default
processes</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f3 46 196 36 24 20 -2 #rect
Ds0 f4 expr out #txt
Ds0 f4 64 109 64 196 #arcP
Ds0 f2 expr out #txt
Ds0 f2 64 220 64 339 #arcP
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides DefaultUserProcess in Portal Kit. &#13;
It add several application favorites processes and determines their order using the setIndex method of UserProcess.&#13;
&#13;
Code Example:&#13;
&#13;
String createAlphaLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp");&#13;
if (!StringUtils.isEmpty(createAlphaLink)){	&#13;
	UserProcess userProcess = new UserProcess();&#13;
	userProcess.setLink(createAlphaLink);&#13;
	userProcess.setProcessName(ivy.cms.co("/Processes/ProcessHistoryComponent/AlphaCompany/name"));&#13;
	userProcess.setIcon("si si-buildings-1");&#13;
	userProcess.setIndex(1);&#13;
	in.defaultUserProcesses.add(userProcess);&#13;
}&#13;
&#13;
String viewAlphaHistoryLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp");&#13;
if (!StringUtils.isEmpty(viewAlphaHistoryLink)){	&#13;
	UserProcess userProcess = new UserProcess();&#13;
	userProcess.setLink(viewAlphaHistoryLink);&#13;
	userProcess.setProcessName(ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompany/name"));&#13;
	userProcess.setIcon("si si-task-list-approve");&#13;
	userProcess.setIndex(2);&#13;
	in.defaultUserProcesses.add(userProcess);&#13;
}&#13;
&#13;
String categoriedLeaveRequestLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/CreateTestData/CategoriedLeaveRequest.ivp");&#13;
if (!StringUtils.isEmpty(categoriedLeaveRequestLink)){	&#13;
	UserProcess userProcess = new UserProcess();&#13;
	userProcess.setLink(categoriedLeaveRequestLink);&#13;
	userProcess.setProcessName(ivy.cms.co("/Categories/LeaveRequest/name"));&#13;
	userProcess.setIcon("si si-calendar");&#13;
	userProcess.setIndex(3);&#13;
	in.defaultUserProcesses.add(userProcess);&#13;
}&#13;
&#13;
String createInvestmentLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/IFrameExample/CreateInvestment.ivp");&#13;
if (!StringUtils.isEmpty(createInvestmentLink)){	&#13;
	UserProcess userProcess = new UserProcess();&#13;
	userProcess.setLink(createInvestmentLink);&#13;
	userProcess.setProcessName(ivy.cms.co("/Processes/CreateInvestment/name"));&#13;
	userProcess.setIcon("si si-analytics-graph");&#13;
	userProcess.setIndex(4);&#13;
	in.defaultUserProcesses.add(userProcess);&#13;
}</name>
    </language>
</elementInfo>
' #txt
Ds0 f5 256 82 992 716 -493 -352 #rect
>Proto Ds0 .type _com.axonivy.portal.developerexamples.DefaultUserProcessOverrideData #txt
>Proto Ds0 .processKind CALLABLE_SUB #txt
>Proto Ds0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 mainIn #connect
Ds0 f3 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
