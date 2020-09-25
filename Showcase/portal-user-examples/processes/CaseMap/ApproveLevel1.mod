[Ivy]
1703222B39B57AE4 9.2.0 #module
>Proto >Proto Collection #zClass
A10 ApproveLevel1 Big #zClass
A10 B #cInfo
A10 #process
A10 @TextInP .type .type #zField
A10 @TextInP .processKind .processKind #zField
A10 @AnnotationInP-0n ai ai #zField
A10 @MessageFlowInP-0n messageIn messageIn #zField
A10 @MessageFlowOutP-0n messageOut messageOut #zField
A10 @TextInP .xml .xml #zField
A10 @TextInP .responsibility .responsibility #zField
A10 @StartRequest f0 '' #zField
A10 @EndTask f1 '' #zField
A10 @GridStep f3 '' #zField
A10 @PushWFArc f2 '' #zField
A10 @UserDialog f5 '' #zField
A10 @PushWFArc f6 '' #zField
A10 @PushWFArc f4 '' #zField
>Proto A10 A10 ApproveLevel1 #zField
A10 f0 outLink start.ivp #txt
A10 f0 inParamDecl '<> param;' #txt
A10 f0 requestEnabled true #txt
A10 f0 triggerEnabled false #txt
A10 f0 callSignature start() #txt
A10 f0 persist false #txt
A10 f0 startName 'Approve Level 1' #txt
A10 f0 taskData 'TaskTriggered.EXP=new Duration("24h")
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/approveLevel1")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
A10 f0 caseData businessCase.attach=true #txt
A10 f0 showInStartList 0 #txt
A10 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
A10 f0 @C|.responsibility Everybody #txt
A10 f0 81 49 30 30 -21 17 #rect
A10 f0 @|StartRequestIcon #fIcon
A10 f1 505 49 30 30 0 15 #rect
A10 f1 @|EndIcon #fIcon
A10 f3 actionTable 'out=in;
' #txt
A10 f3 actionCode 'ivy.log.info("Business Process is in Stage " + ivy.case.getBusinessCase().getStage().getName());' #txt
A10 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log stage</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
A10 f3 328 42 112 44 -25 -8 #rect
A10 f3 @|StepIcon #fIcon
A10 f2 expr out #txt
A10 f2 440 64 505 64 #arcP
A10 f5 dialogId com.axonivy.portal.userexamples.credit.ApproveLevel1 #txt
A10 f5 startMethod start() #txt
A10 f5 requestActionDecl '<> param;' #txt
A10 f5 responseActionDecl 'workflow.humantask.Data out;
' #txt
A10 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve
Level 1</name>
        <nameStyle>8,7
7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
A10 f5 168 42 112 44 -22 -16 #rect
A10 f5 @|UserDialogIcon #fIcon
A10 f6 expr out #txt
A10 f6 111 64 168 64 #arcP
A10 f6 0 0.7758833401611239 0 0 #arcLabel
A10 f4 expr out #txt
A10 f4 280 64 328 64 #arcP
A10 f4 0 0.7758833401611239 0 0 #arcLabel
>Proto A10 .type com.axonivy.portal.userexamples.Data #txt
>Proto A10 .processKind NORMAL #txt
>Proto A10 0 0 32 24 18 0 #rect
>Proto A10 @|BIcon #fIcon
A10 f3 mainOut f2 tail #connect
A10 f2 head f1 mainIn #connect
A10 f0 mainOut f6 tail #connect
A10 f6 head f5 mainIn #connect
A10 f5 mainOut f4 tail #connect
A10 f4 head f3 mainIn #connect
