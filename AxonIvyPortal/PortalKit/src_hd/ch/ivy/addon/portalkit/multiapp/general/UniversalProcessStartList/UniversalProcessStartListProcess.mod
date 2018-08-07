[Ivy]
[>Created: Fri Feb 26 15:03:37 ICT 2016]
14BCA53FE2DDD318 3.18 #module
>Proto >Proto Collection #zClass
Ps0 UniversalProcessStartListProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @RichDialogProcessStart f7 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 UniversalProcessStartListProcess #zField
Ps0 f0 guid 14790C433AE95B46 #txt
Ps0 f0 type ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.user=ivy.session.getSessionUserName();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 86 54 20 20 13 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData #txt
Ps0 f1 86 246 20 20 13 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f7 guid 147CF5C1C86E080A #txt
Ps0 f7 type ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData #txt
Ps0 f7 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData out;
' #txt
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onRefresh</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 214 54 20 20 13 0 #rect
Ps0 f7 @|RichDialogProcessStartIcon #fIcon
Ps0 f8 type ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData #txt
Ps0 f8 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Ps0 f8 doCall true #txt
Ps0 f8 requestActionDecl '<ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f8 requestMappingAction 'param.processSearchCriteria.involvedUsername=ivy.session.getSessionUserName();
' #txt
Ps0 f8 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData out;
' #txt
Ps0 f8 responseMappingAction 'out=in;
out.errors=result.errors;
out.processStarts=result.processStarts;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllProcessStarts(String)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 78 148 36 24 20 -2 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 96 74 96 148 #arcP
Ps0 f6 expr out #txt
Ps0 f6 224 74 114 153 #arcP
Ps0 f6 1 224 112 #addKink
Ps0 f6 1 0.5701305848312255 0 0 #arcLabel
Ps0 f2 expr out #txt
Ps0 f2 96 172 96 246 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.general.UniversalProcessStartList.UniversalProcessStartListData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f3 tail #connect
Ps0 f3 head f8 mainIn #connect
Ps0 f7 mainOut f6 tail #connect
Ps0 f6 head f8 mainIn #connect
Ps0 f8 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
