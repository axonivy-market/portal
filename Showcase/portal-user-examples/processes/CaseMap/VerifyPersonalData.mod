[Ivy]
1703223F7785EC9A 9.2.0 #module
>Proto >Proto Collection #zClass
Ca0 VerifyPersonalData Big #zClass
Ca0 B #cInfo
Ca0 #process
Ca0 @TextInP .type .type #zField
Ca0 @TextInP .processKind .processKind #zField
Ca0 @AnnotationInP-0n ai ai #zField
Ca0 @MessageFlowInP-0n messageIn messageIn #zField
Ca0 @MessageFlowOutP-0n messageOut messageOut #zField
Ca0 @TextInP .xml .xml #zField
Ca0 @TextInP .responsibility .responsibility #zField
Ca0 @StartRequest f0 '' #zField
Ca0 @EndTask f1 '' #zField
Ca0 @GridStep f3 '' #zField
Ca0 @PushWFArc f4 '' #zField
Ca0 @UserDialog f5 '' #zField
Ca0 @PushWFArc f6 '' #zField
Ca0 @PushWFArc f2 '' #zField
>Proto Ca0 Ca0 VerifyPersonalData #zField
Ca0 f0 outLink start.ivp #txt
Ca0 f0 inParamDecl '<> param;' #txt
Ca0 f0 requestEnabled true #txt
Ca0 f0 triggerEnabled false #txt
Ca0 f0 callSignature start() #txt
Ca0 f0 persist false #txt
Ca0 f0 startName 'Verify Personal Data' #txt
Ca0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/verifyPersonalData")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
Ca0 f0 caseData businessCase.attach=true #txt
Ca0 f0 showInStartList 0 #txt
Ca0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f0 @C|.responsibility Everybody #txt
Ca0 f0 81 49 30 30 -21 17 #rect
Ca0 f0 @|StartRequestIcon #fIcon
Ca0 f1 505 49 30 30 0 15 #rect
Ca0 f1 @|EndIcon #fIcon
Ca0 f3 actionTable 'out=in;
' #txt
Ca0 f3 actionCode 'ivy.log.info("Business Process is in Stage " + ivy.case.getBusinessCase().getStage().getName());
' #txt
Ca0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log stage</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f3 328 42 112 44 -25 -8 #rect
Ca0 f3 @|StepIcon #fIcon
Ca0 f4 expr out #txt
Ca0 f4 440 64 505 64 #arcP
Ca0 f5 dialogId com.axonivy.portal.userexamples.credit.VerifyPersonalData #txt
Ca0 f5 startMethod start() #txt
Ca0 f5 requestActionDecl '<> param;' #txt
Ca0 f5 responseMappingAction 'out=in;
' #txt
Ca0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>VerifyPersonalData</name>
    </language>
</elementInfo>
' #txt
Ca0 f5 168 42 112 44 -52 -8 #rect
Ca0 f5 @|UserDialogIcon #fIcon
Ca0 f6 111 64 168 64 #arcP
Ca0 f2 280 64 328 64 #arcP
>Proto Ca0 .type com.axonivy.portal.userexamples.Data #txt
>Proto Ca0 .processKind NORMAL #txt
>Proto Ca0 0 0 32 24 18 0 #rect
>Proto Ca0 @|BIcon #fIcon
Ca0 f3 mainOut f4 tail #connect
Ca0 f4 head f1 mainIn #connect
Ca0 f0 mainOut f6 tail #connect
Ca0 f6 head f5 mainIn #connect
Ca0 f5 mainOut f2 tail #connect
Ca0 f2 head f3 mainIn #connect
