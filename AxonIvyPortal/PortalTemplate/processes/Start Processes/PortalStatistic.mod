[Ivy]
164121DD89FFAEA8 3.23 #module
>Proto >Proto Collection #zClass
Pc0 PortalStatistic Big #zClass
Pc0 B #cInfo
Pc0 #process
Pc0 @TextInP .resExport .resExport #zField
Pc0 @TextInP .type .type #zField
Pc0 @TextInP .processKind .processKind #zField
Pc0 @AnnotationInP-0n ai ai #zField
Pc0 @MessageFlowInP-0n messageIn messageIn #zField
Pc0 @MessageFlowOutP-0n messageOut messageOut #zField
Pc0 @TextInP .xml .xml #zField
Pc0 @TextInP .responsibility .responsibility #zField
Pc0 @StartRequest f0 '' #zField
Pc0 @EndTask f1 '' #zField
Pc0 @RichDialog f82 '' #zField
Pc0 @PushWFArc f3 '' #zField
Pc0 @PushWFArc f2 '' #zField
>Proto Pc0 Pc0 PortalStatistic #zField
Pc0 f0 outLink start.ivp #txt
Pc0 f0 type ch.ivy.addon.portal.generic.PortalStatisticData #txt
Pc0 f0 inParamDecl '<> param;' #txt
Pc0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStatisticData out;
' #txt
Pc0 f0 guid 164121DD8A2D8F03 #txt
Pc0 f0 requestEnabled true #txt
Pc0 f0 triggerEnabled false #txt
Pc0 f0 callSignature start() #txt
Pc0 f0 persist false #txt
Pc0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pc0 f0 caseData businessCase.attach=true #txt
Pc0 f0 showInStartList 0 #txt
Pc0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pc0 f0 @C|.responsibility Everybody #txt
Pc0 f0 81 49 30 30 -21 17 #rect
Pc0 f0 @|StartRequestIcon #fIcon
Pc0 f1 type ch.ivy.addon.portal.generic.PortalStatisticData #txt
Pc0 f1 337 49 30 30 0 15 #rect
Pc0 f1 @|EndIcon #fIcon
Pc0 f82 targetWindow NEW:card: #txt
Pc0 f82 targetDisplay TOP #txt
Pc0 f82 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Pc0 f82 startMethod startWithMenuState(String) #txt
Pc0 f82 type ch.ivy.addon.portal.generic.PortalStatisticData #txt
Pc0 f82 requestActionDecl '<String menuState> param;' #txt
Pc0 f82 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Pc0 f82 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStatisticData out;
' #txt
Pc0 f82 responseMappingAction 'out=in;
' #txt
Pc0 f82 windowConfiguration '* ' #txt
Pc0 f82 isAsynch false #txt
Pc0 f82 isInnerRd false #txt
Pc0 f82 userContext '* ' #txt
Pc0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>dash board</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pc0 f82 179 44 80 40 -27 -8 #rect
Pc0 f82 @|RichDialogIcon #fIcon
Pc0 f3 expr out #txt
Pc0 f3 111 64 179 64 #arcP
Pc0 f2 expr out #txt
Pc0 f2 259 64 337 64 #arcP
>Proto Pc0 .type ch.ivy.addon.portal.generic.PortalStatisticData #txt
>Proto Pc0 .processKind NORMAL #txt
>Proto Pc0 0 0 32 24 18 0 #rect
>Proto Pc0 @|BIcon #fIcon
Pc0 f0 mainOut f3 tail #connect
Pc0 f3 head f82 mainIn #connect
Pc0 f82 mainOut f2 tail #connect
Pc0 f2 head f1 mainIn #connect
