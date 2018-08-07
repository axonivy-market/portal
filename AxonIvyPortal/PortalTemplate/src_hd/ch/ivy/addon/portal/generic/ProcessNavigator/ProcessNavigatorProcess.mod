[Ivy]
15F6AF4140FEAD47 3.20 #module
>Proto >Proto Collection #zClass
Ps0 ProcessNavigatorProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialogEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogProcessStart f6 '' #zField
Ps0 @RichDialog f33 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 ProcessNavigatorProcess #zField
Ps0 f0 guid 15F6AF414726293F #txt
Ps0 f0 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 15F6AF4149524409 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 219 26 26 -15 12 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f4 guid 15F6AF414968DB4B #txt
Ps0 f4 211 219 26 26 0 12 #rect
Ps0 f4 @|RichDialogEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 109 232 211 232 #arcP
Ps0 f6 guid 15F6AF4C4B321D1E #txt
Ps0 f6 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f6 actionDecl 'ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData out;
' #txt
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openProcess</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 83 131 26 26 -37 15 #rect
Ps0 f6 @|RichDialogProcessStartIcon #fIcon
Ps0 f33 targetWindow NEW:card: #txt
Ps0 f33 targetDisplay TOP #txt
Ps0 f33 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f33 startMethod startWithMenuState(String) #txt
Ps0 f33 type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
Ps0 f33 requestActionDecl '<String menuState> param;' #txt
Ps0 f33 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ps0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
Ps0 f33 responseMappingAction 'out=in;
' #txt
Ps0 f33 windowConfiguration '* ' #txt
Ps0 f33 isAsynch false #txt
Ps0 f33 isInnerRd true #txt
Ps0 f33 userContext '* ' #txt
Ps0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f33 196 120 88 48 -22 -8 #rect
Ps0 f33 @|RichDialogIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 109 144 196 144 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.ProcessNavigator.ProcessNavigatorData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f33 mainIn #connect
