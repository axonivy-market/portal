[Ivy]
164113C37538D0E6 3.23 #module
>Proto >Proto Collection #zClass
Ps0 PortalProcess Big #zClass
Ps0 B #cInfo
Ps0 #process
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @EndTask f1 '' #zField
Ps0 @StartRequest f51 '' #zField
Ps0 @RichDialog f71 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f0 '' #zField
>Proto Ps0 Ps0 PortalProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalProcessData #txt
Ps0 f1 337 50 30 30 0 15 #rect
Ps0 f1 @|EndIcon #fIcon
Ps0 f51 outLink start.ivp #txt
Ps0 f51 type ch.ivy.addon.portal.generic.PortalProcessData #txt
Ps0 f51 inParamDecl '<> param;' #txt
Ps0 f51 actionDecl 'ch.ivy.addon.portal.generic.PortalProcessData out;
' #txt
Ps0 f51 guid 164114384E1D02AC #txt
Ps0 f51 requestEnabled true #txt
Ps0 f51 triggerEnabled false #txt
Ps0 f51 callSignature start() #txt
Ps0 f51 persist false #txt
Ps0 f51 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ps0 f51 caseData businessCase.attach=true #txt
Ps0 f51 showInStartList 0 #txt
Ps0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f51 @C|.responsibility Everybody #txt
Ps0 f51 60 50 30 30 -21 17 #rect
Ps0 f51 @|StartRequestIcon #fIcon
Ps0 f71 targetWindow NEW:card: #txt
Ps0 f71 targetDisplay TOP #txt
Ps0 f71 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f71 startMethod startWithMenuState(String) #txt
Ps0 f71 type ch.ivy.addon.portal.generic.PortalProcessData #txt
Ps0 f71 requestActionDecl '<String menuState> param;' #txt
Ps0 f71 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ps0 f71 responseActionDecl 'ch.ivy.addon.portal.generic.PortalProcessData out;
' #txt
Ps0 f71 responseMappingAction 'out=in;
' #txt
Ps0 f71 windowConfiguration '* ' #txt
Ps0 f71 isAsynch false #txt
Ps0 f71 isInnerRd false #txt
Ps0 f71 userContext '* ' #txt
Ps0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f71 167 45 72 40 -22 -8 #rect
Ps0 f71 @|RichDialogIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 90 65 167 65 #arcP
Ps0 f0 expr out #txt
Ps0 f0 239 65 337 65 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalProcessData #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ps0 f51 mainOut f2 tail #connect
Ps0 f2 head f71 mainIn #connect
Ps0 f71 mainOut f0 tail #connect
Ps0 f0 head f1 mainIn #connect
