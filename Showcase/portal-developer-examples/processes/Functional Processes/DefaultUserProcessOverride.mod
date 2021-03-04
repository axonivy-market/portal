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
Ds0 f0 @|StartSubIcon #fIcon
Ds0 f1 51 339 26 26 14 0 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

UserProcess ivyProcess = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/AlphaCompany/name"));
ivyProcess.setIcon("fa-building");
ivyProcess.setIndex(1);
in.defaultUserProcesses.add(ivyProcess);

UserProcess ivyProcess2 = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompany/name"));
ivyProcess.setIcon("fa-list-alt");
ivyProcess.setIndex(2);
in.defaultUserProcesses.add(ivyProcess);

UserProcess ivyProcess3 = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompanyInDialog.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompanyInDialog/name"));
ivyProcess.setIcon("fa-asterisk");
ivyProcess.setIndex(3);
in.defaultUserProcesses.add(ivyProcess);

UserProcess expressProcess = ProcessStartAPI.initUserProcessByExpressProcessName("Your Express Process Name", "Favorite Process Display Name");
expressProcess.setIndex(4);
in.defaultUserProcesses.add(expressProcess);

UserProcess externalLink = ProcessStartAPI.initUserProcessByExternalLinkName("Your External Link Name", "Favorite Process Display Name");
externalLink.setIndex(5);
in.defaultUserProcesses.add(externalLink);

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
        <name>This process overrides DefaultUserProcess in Portal Kit. &#13;
It add several application favorites processes and determines their order using the setIndex method of UserProcess.&#13;
&#13;
Code Example:&#13;
&#13;
UserProcess ivyProcess = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/AlphaCompany/name"));&#13;
ivyProcess.setIcon("fa-building");&#13;
ivyProcess.setIndex(1);&#13;
in.defaultUserProcesses.add(ivyProcess);&#13;
&#13;
UserProcess ivyProcess2 = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompany/name"));&#13;
ivyProcess.setIcon("fa-list-alt");&#13;
ivyProcess.setIndex(2);&#13;
in.defaultUserProcesses.add(ivyProcess);&#13;
&#13;
UserProcess ivyProcess3 = ProcessStartAPI.initUserProcessByUserFriendlyRequestPath("Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompanyInDialog.ivp", ivy.cms.co("/Processes/ProcessHistoryComponent/ProcessHistoryOfAlphaCompanyInDialog/name"));&#13;
ivyProcess.setIcon("fa-asterisk");&#13;
ivyProcess.setIndex(3);&#13;
in.defaultUserProcesses.add(ivyProcess);&#13;
&#13;
UserProcess expressProcess = ProcessStartAPI.initUserProcessByExpressProcessName("Your Express Process Name", "Favorite Process Display Name");&#13;
expressProcess.setIndex(4);&#13;
in.defaultUserProcesses.add(expressProcess);&#13;
&#13;
UserProcess externalLink = ProcessStartAPI.initUserProcessByExternalLinkName("Your External Link Name", "Favorite Process Display Name");&#13;
externalLink.setIndex(5);&#13;
in.defaultUserProcesses.add(externalLink);</name>
    </language>
</elementInfo>
' #txt
Ds0 f5 256 82 1584 444 -789 -216 #rect
Ds0 f5 @|IBIcon #fIcon
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
