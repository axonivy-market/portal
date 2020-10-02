[Ivy]
1657E93190721001 7.5.0 #module
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
Ds0 f0 @|StartSubIcon #fIcon
Ds0 f1 51 339 26 26 14 0 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;


ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());

String createAlphaLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp");
if (!StringUtils.isEmpty(createAlphaLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(createAlphaLink);
	userProcess.setProcessName("Alpha Company Task");
	userProcess.setIcon("fa-th");
	userProcess.setIndex(1);
	in.defaultUserProcesses.add(userProcess);
}

String createBetaLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createBetaCompany.ivp");
if (!StringUtils.isEmpty(createBetaLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(createBetaLink);
	userProcess.setProcessName("Beta Company Task");
	userProcess.setIcon("fa-gavel");
	userProcess.setIndex(2);
	in.defaultUserProcesses.add(userProcess);
}

String viewAlphaHistoryLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp");
if (!StringUtils.isEmpty(viewAlphaHistoryLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(viewAlphaHistoryLink);
	userProcess.setProcessName("View Alpha Process History");
	userProcess.setIcon("fa-magic");
	userProcess.setIndex(3);
	in.defaultUserProcesses.add(userProcess);
}

String viewBetaHistoryLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfBetaCompany.ivp");
if (!StringUtils.isEmpty(viewBetaHistoryLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(viewBetaHistoryLink);
	userProcess.setProcessName("View Beta Process History");
	userProcess.setIcon("fa-asterisk");
	userProcess.setIndex(4);
	in.defaultUserProcesses.add(userProcess);
}' #txt
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
Ds0 f3 @|StepIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 64 109 64 196 #arcP
Ds0 f2 expr out #txt
Ds0 f2 64 220 64 339 #arcP
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides DefaultUserProcess in Portal Kit. 
It add 5 application favorites processes and determines their order using the setIndex method of UserProcess.</name>
        <nameStyle>167,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f5 220 152 648 80 -301 -16 #rect
Ds0 f5 @|IBIcon #fIcon
>Proto Ds0 .type _ch.ivyteam.ivy.project.portal.examples.DefaultUserProcessOverrideData #txt
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
