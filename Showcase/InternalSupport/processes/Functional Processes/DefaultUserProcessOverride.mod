[Ivy]
[>Created: Tue Mar 22 13:37:09 ICT 2016]
15397721425F9AF9 3.18 #module
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
Ds0 @GridStep f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @InfoButton f5 '' #zField
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
Ds0 f3 actionDecl 'internaltest.DefaultUserProcessOverrideData out;
' #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

UserProcess leaveProcess = new UserProcess();
leaveProcess.setLink("/pro/Portal/internalSupport/1470062B2127AF92/LeaveRequest.ivp");
leaveProcess.setProcessName("Employee Leave Request (Default)");
leaveProcess.setIcon("fa-group");

in.defaultUserProcesses.add(leaveProcess);

UserProcess supportTicketProcess = new UserProcess();
supportTicketProcess.setLink("/pro/Portal/internalSupport/14B2FC03D2E87141/CreateSupportTicket.ivp");
supportTicketProcess.setProcessName("Support Ticket (Default)");
supportTicketProcess.setIcon("fa-ticket");

in.defaultUserProcesses.add(supportTicketProcess);' #txt
Ds0 f3 type internaltest.DefaultUserProcessOverrideData #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create default
processes</name>
        <nameStyle>24
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
        <name>HOW TO CREATE A DEFAULT USER PROCESS:

UserProcess userProcess = new UserProcess();
userProcess.setLink(&lt;PROCESS_LINK&gt;); //Full link or relative link starts with: /pro/...
userProcess.setProcessName(&lt;PROCESS_NAME&gt;);
userProcess.setIcon(&lt;PROCESS_ICON&gt;); //Icons in Font Awesome

in.defaultUserProcesses.add(userProcess);

OUT: defaultUserProcesses: List&lt;UserProcess&gt;</name>
        <nameStyle>365
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f5 160 130 464 172 -229 -80 #rect
Ds0 f5 @|IBIcon #fIcon
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
