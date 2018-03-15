[Ivy]
161D026618AB5F95 3.20 #module
>Proto >Proto Collection #zClass
Ds0 DefaultUserProcess Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartSub f0 '' #zField
Ds0 @EndSub f1 '' #zField
Ds0 @InfoButton f5 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @GridStep f3 '' #zField
>Proto Ds0 Ds0 DefaultUserProcess #zField
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> defaultUserProcesses> result;
' #txt
Ds0 f0 outParamTable 'result.defaultUserProcesses=in.defaultUserProcesses;
' #txt
Ds0 f0 actionDecl 'internaltest.DefaultUserProcessOverrideData out;
' #txt
Ds0 f0 callSignature createDefaultUserProcesses() #txt
Ds0 f0 type internaltest.DefaultUserProcessOverrideData #txt
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
Ds0 f1 type internaltest.DefaultUserProcessOverrideData #txt
Ds0 f1 51 339 26 26 14 0 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO CREATE A DEFAULT USER PROCESS:

UserProcess userProcess = new UserProcess();
userProcess.setLink(&lt;PROCESS_LINK&gt;); //Absolute path or relative path starts with: /&lt;CONTEXT_PATH&gt;/pro/...
userProcess.setProcessName(&lt;PROCESS_NAME&gt;);
userProcess.setIcon(&lt;PROCESS_ICON&gt;); //Icons in Font Awesome

in.defaultUserProcesses.add(userProcess);

OUT: defaultUserProcesses: List&lt;UserProcess&gt;

HINT: how to build a process url
- Absolute path: ivy.html.startref(...)
- Relative path: RequestUriFactory.createProcessStartUri(...)
- We provide method to get startable link by UserFriendlyRequestPath (If user don''t have permission to start this link, the method will return empty string)
ProcessStartCollector.findStartableLinkByUserFriendlyRequestPath(...) 
Example: 
ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());
String newEmployeeLink = collector.findStartableLinkByUserFriendlyRequestPath("Start Processes/Employee/NewEmployee.ivp");
</name>
        <nameStyle>521,7
451,7
1,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f5 248 58 832 332 -413 -160 #rect
Ds0 f5 @|IBIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 64 220 64 339 #arcP
Ds0 f4 expr out #txt
Ds0 f4 64 109 64 196 #arcP
Ds0 f3 actionDecl 'internaltest.DefaultUserProcessOverrideData out;
' #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;


ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());

String acmLink = collector.findACMLink();
if (!StringUtils.isEmpty(acmLink)) {
	UserProcess acmProcess = new UserProcess();
	acmProcess.setLink(acmLink);
	acmProcess.setProcessName("Axon.ivy Selfservice");
	acmProcess.setIcon("fa-code-fork");
	acmProcess.setIndex(3);
	in.defaultUserProcesses.add(acmProcess);
}

String saleLink = collector.findStartableLinkByUserFriendlyRequestPath("Business Processes/testProcesses/TestTaskFlow/SaleDepartment.ivp");
if (!StringUtils.isEmpty(saleLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(saleLink);
	userProcess.setProcessName("Sale Information");
	userProcess.setIcon("fa-th");
	userProcess.setIndex(1);
	in.defaultUserProcesses.add(userProcess);
}

String inspectResourceLink = collector.findStartableLinkByUserFriendlyRequestPath("Business Processes/testProcesses/ProcessHistoryTest/InspectResource.ivp");
if (!StringUtils.isEmpty(inspectResourceLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(inspectResourceLink);
	userProcess.setProcessName("Inspect Resource");
	userProcess.setIcon("fa-gavel");
	userProcess.setIndex(1);
	in.defaultUserProcesses.add(userProcess);
}

String viewResourceCasesLink = collector.findStartableLinkByUserFriendlyRequestPath("Business Processes/testProcesses/ProcessHistoryTest/viewProcessHistoryOfResource.ivp");
if (!StringUtils.isEmpty(viewResourceCasesLink)){	
	UserProcess userProcess = new UserProcess();
	userProcess.setLink(viewResourceCasesLink);
	userProcess.setProcessName("View Resource");
	userProcess.setIcon("fa-magic");
	userProcess.setIndex(2);
	in.defaultUserProcesses.add(userProcess);
}' #txt
Ds0 f3 type internaltest.DefaultUserProcessOverrideData #txt
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
>Proto Ds0 .type internaltest.DefaultUserProcessOverrideData #txt
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
