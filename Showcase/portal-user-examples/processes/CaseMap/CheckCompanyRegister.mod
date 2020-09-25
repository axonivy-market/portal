[Ivy]
1703222D60B87C28 9.2.0 #module
>Proto >Proto Collection #zClass
Cr0 CheckCompanyRegister Big #zClass
Cr0 B #cInfo
Cr0 #process
Cr0 @TextInP .type .type #zField
Cr0 @TextInP .processKind .processKind #zField
Cr0 @AnnotationInP-0n ai ai #zField
Cr0 @MessageFlowInP-0n messageIn messageIn #zField
Cr0 @MessageFlowOutP-0n messageOut messageOut #zField
Cr0 @TextInP .xml .xml #zField
Cr0 @TextInP .responsibility .responsibility #zField
Cr0 @StartRequest f0 '' #zField
Cr0 @EndTask f1 '' #zField
Cr0 @GridStep f3 '' #zField
Cr0 @PushWFArc f2 '' #zField
Cr0 @PushWFArc f4 '' #zField
>Proto Cr0 Cr0 CheckCompanyRegister #zField
Cr0 f0 outLink start.ivp #txt
Cr0 f0 inParamDecl '<> param;' #txt
Cr0 f0 requestEnabled true #txt
Cr0 f0 triggerEnabled true #txt
Cr0 f0 callSignature start() #txt
Cr0 f0 persist false #txt
Cr0 f0 startName 'Check Company Register' #txt
Cr0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=System task \: <%\=ivy.cms.co("/Processes/CaseMap/checkCompanyRegister")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Cr0 f0 caseData businessCase.attach=true #txt
Cr0 f0 showInStartList 0 #txt
Cr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Cr0 f0 @C|.responsibility Everybody #txt
Cr0 f0 81 49 30 30 -21 17 #rect
Cr0 f0 @|StartRequestIcon #fIcon
Cr0 f1 433 49 30 30 0 15 #rect
Cr0 f1 @|EndIcon #fIcon
Cr0 f3 actionTable 'out=in;
' #txt
Cr0 f3 actionCode 'ivy.log.info("Business Process is in Stage " + ivy.case.getBusinessCase().getStage().getName());' #txt
Cr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log stage</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cr0 f3 217 42 112 44 -25 -8 #rect
Cr0 f3 @|StepIcon #fIcon
Cr0 f2 expr out #txt
Cr0 f2 111 64 217 64 #arcP
Cr0 f4 expr out #txt
Cr0 f4 329 64 433 64 #arcP
>Proto Cr0 .type com.axonivy.portal.userexamples.Data #txt
>Proto Cr0 .processKind NORMAL #txt
>Proto Cr0 0 0 32 24 18 0 #rect
>Proto Cr0 @|BIcon #fIcon
Cr0 f0 mainOut f2 tail #connect
Cr0 f2 head f3 mainIn #connect
Cr0 f3 mainOut f4 tail #connect
Cr0 f4 head f1 mainIn #connect
