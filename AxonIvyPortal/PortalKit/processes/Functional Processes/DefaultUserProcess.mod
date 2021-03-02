[Ivy]
1538898FE5F1C3BA 9.2.0 #module
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
Ds0 f0 51 83 26 26 -54 -39 #rect
Ds0 f0 @|StartSubIcon #fIcon
Ds0 f1 51 339 26 26 14 0 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f3 actionTable 'out=in;
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
Ds0 f3 @|StepIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 64 109 64 196 #arcP
Ds0 f2 expr out #txt
Ds0 f2 64 220 64 339 #arcP
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO CREATE A DEFAULT USER PROCESS:&#13;
&#13;
UserProcess userProcess = new UserProcess();&#13;
userProcess.setLink(&lt;PROCESS_LINK&gt;); //Absolute path or relative path starts with: /&lt;CONTEXT_PATH&gt;/pro/...&#13;
userProcess.setProcessName(&lt;PROCESS_NAME&gt;);  // Use a CMS for defining multilingual process name&#13;
userProcess.setIcon(&lt;PROCESS_ICON&gt;); //Icons in Font Awesome&#13;
userProcess.setIndex(1); // Set the index of the process on the default process list&#13;
&#13;
in.defaultUserProcesses.add(userProcess);&#13;
&#13;
OUT: defaultUserProcesses: List&lt;UserProcess&gt;&#13;
&#13;
HINT: how to get a process url&#13;
- Use IProcessStart#getLink() to get absolute/relative path (Refer to Axon.ivy Public API document)&#13;
- The default processes are sorted by their index attribute. If this attribute is not set, the process will be put at the bottom of the list.&#13;
- We provide method to get startable link by user friendly request path (If user don''t have permission to start this link, the method will return empty string)&#13;
ch.ivy.addon.portalkit.publicapi.ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(...)&#13;
Example: &#13;
String newEmployeeLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/Employee/NewEmployee.ivp");&#13;
&#13;
HINT: how to apply broken link warning to a default user process&#13;
- We provide 3 methods to initiate a default user process from processes (existing in your application or database), this default user process will store processId and processType that will be used to check if it is broken or not.&#13;
 + For Ivy Process: ch.ivy.addon.portalkit.publicapi.ProcessStartAPI.initUserProcessByUserFriendlyRequestPath(String, String)&#13;
 + For Express Process: ch.ivy.addon.portalkit.publicapi.ProcessStartAPI.initUserProcessByExpressProcessName(String, String)&#13;
 + For External Link: ch.ivy.addon.portalkit.publicapi.ProcessStartAPI.initUserProcessByExternalLinkName(String, String)&#13;
Example:&#13;
UserProcess ivyProcess = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Business Processes/testCaseMaps/testProcesses/ApproveLeave/Appraisal.ivp", "Appraisal");&#13;
UserProcess expressProcess = ProcessStartAPI.initUserProcessByExpressProcessName("Your Express Process Name", "Favorite Process Displayed Name");&#13;
UserProcess externalLink = ProcessStartAPI.initUserProcessByExternalLinkName("Your External Link Name", "Favorite Process Displayed Name");</name>
    </language>
</elementInfo>
' #txt
Ds0 f5 200 66 1232 476 -610 -232 #rect
Ds0 f5 @|IBIcon #fIcon
>Proto Ds0 .type ch.ivy.add.portalkit.DefaultUserProcessData #txt
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
