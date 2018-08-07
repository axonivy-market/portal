[Ivy]
[>Created: Tue Jun 06 09:24:24 ICT 2017]
15C7B30FB93C827E 3.20 #module
>Proto >Proto Collection #zClass
Ae0 ApproveLeave Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @RichDialog f3 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f2 '' #zField
>Proto Ae0 Ae0 ApproveLeave #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type internaltest.Data #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 actionDecl 'internaltest.Data out;
' #txt
Ae0 f0 guid 15C7B30FB98ED618 #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start() #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=Approve Leave Request
TaskTriggered.EXROL=Everybody' #txt
Ae0 f0 caseData 'case.name=Approve Leave Request
businessCase.attach=true' #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 81 49 30 30 -21 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 type internaltest.Data #txt
Ae0 f1 337 49 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 targetWindow NEW #txt
Ae0 f3 targetDisplay TOP #txt
Ae0 f3 richDialogId internaltest.ui.approveLeave #txt
Ae0 f3 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ae0 f3 type internaltest.Data #txt
Ae0 f3 requestActionDecl '<String Mitarbeiter, Date Von, Date Bis, Boolean beantragt, String Vertretung, internalPortal.ProcessStatus processStatus> param;' #txt
Ae0 f3 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 isAsynch false #txt
Ae0 f3 isInnerRd false #txt
Ae0 f3 userContext '* ' #txt
Ae0 f3 168 42 112 44 0 -8 #rect
Ae0 f3 @|RichDialogIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 111 64 168 64 #arcP
Ae0 f2 expr out #txt
Ae0 f2 280 64 337 64 #arcP
>Proto Ae0 .type internaltest.Data #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f4 tail #connect
Ae0 f4 head f3 mainIn #connect
Ae0 f3 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
