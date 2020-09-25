[Ivy]
17032234FD387594 9.2.0 #module
>Proto >Proto Collection #zClass
Cn0 ContractCreation Big #zClass
Cn0 B #cInfo
Cn0 #process
Cn0 @TextInP .type .type #zField
Cn0 @TextInP .processKind .processKind #zField
Cn0 @AnnotationInP-0n ai ai #zField
Cn0 @MessageFlowInP-0n messageIn messageIn #zField
Cn0 @MessageFlowOutP-0n messageOut messageOut #zField
Cn0 @TextInP .xml .xml #zField
Cn0 @TextInP .responsibility .responsibility #zField
Cn0 @StartRequest f0 '' #zField
Cn0 @EndTask f1 '' #zField
Cn0 @UserDialog f3 '' #zField
Cn0 @PushWFArc f4 '' #zField
Cn0 @GridStep f28 '' #zField
Cn0 @PushWFArc f2 '' #zField
Cn0 @PushWFArc f5 '' #zField
>Proto Cn0 Cn0 ContractCreation #zField
Cn0 f0 outLink start.ivp #txt
Cn0 f0 inParamDecl '<> param;' #txt
Cn0 f0 requestEnabled true #txt
Cn0 f0 triggerEnabled false #txt
Cn0 f0 callSignature start() #txt
Cn0 f0 persist false #txt
Cn0 f0 startName 'Create Contract ' #txt
Cn0 f0 taskData 'TaskTriggered.EXP=new Duration("4h")
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/createContract")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
Cn0 f0 caseData businessCase.attach=true #txt
Cn0 f0 showInStartList 0 #txt
Cn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cn0 f0 @C|.responsibility Everybody #txt
Cn0 f0 81 49 30 30 -21 17 #rect
Cn0 f0 @|StartRequestIcon #fIcon
Cn0 f1 497 49 30 30 0 15 #rect
Cn0 f1 @|EndIcon #fIcon
Cn0 f3 dialogId com.axonivy.portal.userexamples.credit.ContractCreation #txt
Cn0 f3 startMethod start() #txt
Cn0 f3 requestActionDecl '<> param;' #txt
Cn0 f3 responseMappingAction 'out=in;
' #txt
Cn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ContractCreation</name>
    </language>
</elementInfo>
' #txt
Cn0 f3 168 42 112 44 -46 -8 #rect
Cn0 f3 @|UserDialogIcon #fIcon
Cn0 f4 expr out #txt
Cn0 f4 111 64 168 64 #arcP
Cn0 f28 actionTable 'out=in;
' #txt
Cn0 f28 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
import java.util.Map;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import java.util.Arrays;
import ch.ivy.addon.portalkit.service.IvyAdapterService;

Map x = IvyAdapterService.startSubProcess("handleUserExamplesEndPage()", null, Arrays.asList(PortalLibrary.AXON_EXPRESS.getValue()));
String callbackUrl = x.get("callbackUrl") as String;
ivy.task.customFields().stringField(CustomFields.EXPRESS_END_PAGE_URL.toString()).set(callbackUrl);

' #txt
Cn0 f28 security system #txt
Cn0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set end page</name>
    </language>
</elementInfo>
' #txt
Cn0 f28 344 42 112 44 -34 -8 #rect
Cn0 f28 @|StepIcon #fIcon
Cn0 f2 280 64 344 64 #arcP
Cn0 f5 456 64 497 64 #arcP
>Proto Cn0 .type com.axonivy.portal.userexamples.Data #txt
>Proto Cn0 .processKind NORMAL #txt
>Proto Cn0 0 0 32 24 18 0 #rect
>Proto Cn0 @|BIcon #fIcon
Cn0 f0 mainOut f4 tail #connect
Cn0 f4 head f3 mainIn #connect
Cn0 f3 mainOut f2 tail #connect
Cn0 f2 head f28 mainIn #connect
Cn0 f28 mainOut f5 tail #connect
Cn0 f5 head f1 mainIn #connect
