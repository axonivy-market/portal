[Ivy]
[>Created: Tue Jul 18 11:09:53 ICT 2017]
1576E76B009E23DD 3.20 #module
>Proto >Proto Collection #zClass
Pt0 PortalStartGAFWS Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @RichDialog f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @PushWFArc f2 '' #zField
>Proto Pt0 Pt0 PortalStartGAFWS #zField
Pt0 f0 outLink AxonIvyExpress.ivp #txt
Pt0 f0 type gawfs.PortalStartData #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 actionDecl 'gawfs.PortalStartData out;
' #txt
Pt0 f0 guid 1576E76B02BFE617 #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature AxonIvyExpress() #txt
Pt0 f0 persist false #txt
Pt0 f0 startName 'Axon.ivy Express' #txt
Pt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 caseData businessCase.attach=false #txt
Pt0 f0 wfuser 1 #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AxonIvyExpress.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 81 49 30 30 -51 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 type gawfs.PortalStartData #txt
Pt0 f1 497 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 targetWindow NEW:card: #txt
Pt0 f3 targetDisplay TOP #txt
Pt0 f3 richDialogId ch.ivy.gawfs.portal.GAWFSPortalHome #txt
Pt0 f3 startMethod start(gawfs.DevLoadWorkflowsData) #txt
Pt0 f3 type gawfs.PortalStartData #txt
Pt0 f3 requestActionDecl '<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> param;' #txt
Pt0 f3 responseActionDecl 'gawfs.PortalStartData out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
' #txt
Pt0 f3 windowConfiguration '* ' #txt
Pt0 f3 isAsynch false #txt
Pt0 f3 isInnerRd false #txt
Pt0 f3 userContext '* ' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 272 42 112 44 -50 -8 #rect
Pt0 f3 @|RichDialogIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 111 64 272 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 384 64 497 64 #arcP
>Proto Pt0 .type gawfs.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
