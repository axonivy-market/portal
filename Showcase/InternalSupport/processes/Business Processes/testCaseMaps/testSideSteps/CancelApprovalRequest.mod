[Ivy]
[>Created: Mon Jul 17 11:48:31 ICT 2017]
15D4E4EBDE11BDC2 3.20 #module
>Proto >Proto Collection #zClass
ct0 cancelApprovalRequest Big #zClass
ct0 B #cInfo
ct0 #process
ct0 @TextInP .resExport .resExport #zField
ct0 @TextInP .type .type #zField
ct0 @TextInP .processKind .processKind #zField
ct0 @AnnotationInP-0n ai ai #zField
ct0 @MessageFlowInP-0n messageIn messageIn #zField
ct0 @MessageFlowOutP-0n messageOut messageOut #zField
ct0 @TextInP .xml .xml #zField
ct0 @TextInP .responsibility .responsibility #zField
ct0 @StartRequest f0 '' #zField
ct0 @EndTask f1 '' #zField
ct0 @RichDialog f3 '' #zField
ct0 @PushWFArc f2 '' #zField
ct0 @PushWFArc f4 '' #zField
>Proto ct0 ct0 cancelApprovalRequest #zField
ct0 f0 outLink start.ivp #txt
ct0 f0 type internaltest.Data #txt
ct0 f0 inParamDecl '<> param;' #txt
ct0 f0 actionDecl 'internaltest.Data out;
' #txt
ct0 f0 guid 15D4E4EBE0DA9EED #txt
ct0 f0 requestEnabled true #txt
ct0 f0 triggerEnabled false #txt
ct0 f0 callSignature start() #txt
ct0 f0 persist false #txt
ct0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Cancel Approval Request' #txt
ct0 f0 caseData 'case.name=Cancel Leave Request
businessCase.attach=true' #txt
ct0 f0 showInStartList 1 #txt
ct0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ct0 f0 @C|.responsibility Everybody #txt
ct0 f0 65 57 30 30 -21 17 #rect
ct0 f0 @|StartRequestIcon #fIcon
ct0 f1 type internaltest.Data #txt
ct0 f1 321 57 30 30 0 15 #rect
ct0 f1 @|EndIcon #fIcon
ct0 f3 targetWindow NEW #txt
ct0 f3 targetDisplay TOP #txt
ct0 f3 richDialogId internaltest.ui.CancelApprovalRequest #txt
ct0 f3 startMethod start() #txt
ct0 f3 type internaltest.Data #txt
ct0 f3 requestActionDecl '<> param;' #txt
ct0 f3 responseActionDecl 'internaltest.Data out;
' #txt
ct0 f3 responseMappingAction 'out=in;
' #txt
ct0 f3 isAsynch false #txt
ct0 f3 isInnerRd false #txt
ct0 f3 userContext '* ' #txt
ct0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Cancel Approval Request</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ct0 f3 136 50 144 44 -69 -8 #rect
ct0 f3 @|RichDialogIcon #fIcon
ct0 f2 expr out #txt
ct0 f2 95 72 136 72 #arcP
ct0 f4 expr out #txt
ct0 f4 280 72 321 72 #arcP
>Proto ct0 .type internaltest.Data #txt
>Proto ct0 .processKind NORMAL #txt
>Proto ct0 0 0 32 24 18 0 #rect
>Proto ct0 @|BIcon #fIcon
ct0 f0 mainOut f2 tail #connect
ct0 f2 head f3 mainIn #connect
ct0 f3 mainOut f4 tail #connect
ct0 f4 head f1 mainIn #connect
