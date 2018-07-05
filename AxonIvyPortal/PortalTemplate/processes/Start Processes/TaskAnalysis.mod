[Ivy]
163D97C604D3D4C9 3.23 #module
>Proto >Proto Collection #zClass
Ts0 TaskAnalysis Big #zClass
Ts0 B #cInfo
Ts0 #process
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @StartRequest f0 '' #zField
Ts0 @EndTask f1 '' #zField
Ts0 @RichDialog f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskAnalysis #zField
Ts0 f0 outLink start.ivp #txt
Ts0 f0 type ch.ivy.addon.portal.generic.TaskAnalysisData #txt
Ts0 f0 inParamDecl '<> param;' #txt
Ts0 f0 actionDecl 'ch.ivy.addon.portal.generic.TaskAnalysisData out;
' #txt
Ts0 f0 guid 163D97C608CA7B65 #txt
Ts0 f0 requestEnabled true #txt
Ts0 f0 triggerEnabled false #txt
Ts0 f0 callSignature start() #txt
Ts0 f0 persist false #txt
Ts0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ts0 f0 caseData businessCase.attach=true #txt
Ts0 f0 showInStartList 0 #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 @C|.responsibility Everybody #txt
Ts0 f0 81 49 30 30 -21 17 #rect
Ts0 f0 @|StartRequestIcon #fIcon
Ts0 f1 type ch.ivy.addon.portal.generic.TaskAnalysisData #txt
Ts0 f1 337 49 30 30 0 15 #rect
Ts0 f1 @|EndIcon #fIcon
Ts0 f5 targetWindow NEW #txt
Ts0 f5 targetDisplay TOP #txt
Ts0 f5 richDialogId ch.ivy.addon.portal.generic.TaskAnalysis #txt
Ts0 f5 startMethod start(String) #txt
Ts0 f5 type ch.ivy.addon.portal.generic.TaskAnalysisData #txt
Ts0 f5 requestActionDecl '<String menuState> param;' #txt
Ts0 f5 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ts0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.TaskAnalysisData out;
' #txt
Ts0 f5 responseMappingAction 'out=in;
' #txt
Ts0 f5 isAsynch false #txt
Ts0 f5 isInnerRd false #txt
Ts0 f5 userContext '* ' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task Analysis dialog</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f5 160 42 128 44 -56 -8 #rect
Ts0 f5 @|RichDialogIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 111 64 160 64 #arcP
Ts0 f2 expr out #txt
Ts0 f2 288 64 337 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskAnalysisData #txt
>Proto Ts0 .processKind NORMAL #txt
>Proto Ts0 0 0 32 24 18 0 #rect
>Proto Ts0 @|BIcon #fIcon
Ts0 f0 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
