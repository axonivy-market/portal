[Ivy]
15F6AF64009AFF42 3.20 #module
>Proto >Proto Collection #zClass
Ss0 StatisticNavigatorProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ss0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ss0 @TextInP .resExport .resExport #zField
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @RichDialogInitStart f0 '' #zField
Ss0 @RichDialogProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @RichDialogProcessStart f3 '' #zField
Ss0 @RichDialogEnd f4 '' #zField
Ss0 @PushWFArc f5 '' #zField
Ss0 @RichDialogProcessStart f6 '' #zField
Ss0 @RichDialog f32 '' #zField
Ss0 @PushWFArc f7 '' #zField
>Proto Ss0 Ss0 StatisticNavigatorProcess #zField
Ss0 f0 guid 15F6AF6401914AC3 #txt
Ss0 f0 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f0 method start() #txt
Ss0 f0 disableUIEvents true #txt
Ss0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ss0 f0 outParameterDecl '<> result;
' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 83 51 26 26 -16 15 #rect
Ss0 f0 @|RichDialogInitStartIcon #fIcon
Ss0 f1 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f1 211 51 26 26 0 12 #rect
Ss0 f1 @|RichDialogProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 109 64 211 64 #arcP
Ss0 f3 guid 15F6AF640293ADA2 #txt
Ss0 f3 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f3 actionDecl 'ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData out;
' #txt
Ss0 f3 actionTable 'out=in;
' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ss0 f3 83 195 26 26 -15 12 #rect
Ss0 f3 @|RichDialogProcessStartIcon #fIcon
Ss0 f4 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f4 guid 15F6AF6402921A0F #txt
Ss0 f4 211 195 26 26 0 12 #rect
Ss0 f4 @|RichDialogEndIcon #fIcon
Ss0 f5 expr out #txt
Ss0 f5 109 208 211 208 #arcP
Ss0 f6 guid 15F6AF6A8AA1F6F5 #txt
Ss0 f6 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f6 actionDecl 'ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData out;
' #txt
Ss0 f6 actionTable 'out=in;
' #txt
Ss0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openStatistic</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f6 80 132 26 26 -35 15 #rect
Ss0 f6 @|RichDialogProcessStartIcon #fIcon
Ss0 f32 targetWindow NEW:card: #txt
Ss0 f32 targetDisplay TOP #txt
Ss0 f32 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Ss0 f32 startMethod startWithMenuState(String) #txt
Ss0 f32 type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
Ss0 f32 requestActionDecl '<String menuState> param;' #txt
Ss0 f32 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ss0 f32 responseActionDecl 'ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData out;
' #txt
Ss0 f32 responseMappingAction 'out=in;
' #txt
Ss0 f32 windowConfiguration '* ' #txt
Ss0 f32 isAsynch false #txt
Ss0 f32 isInnerRd true #txt
Ss0 f32 userContext '* ' #txt
Ss0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Statistic</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f32 182 122 112 44 -21 -8 #rect
Ss0 f32 @|RichDialogIcon #fIcon
Ss0 f7 expr out #txt
Ss0 f7 105 144 182 144 #arcP
>Proto Ss0 .type ch.ivy.addon.portal.generic.StatisticNavigator.StatisticNavigatorData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f3 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
Ss0 f6 mainOut f7 tail #connect
Ss0 f7 head f32 mainIn #connect
