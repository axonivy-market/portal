[Ivy]
[>Created: Fri Aug 01 10:51:56 CEST 2014]
14BCA90F6E5A9AF9 3.17 #module
>Proto >Proto Collection #zClass
Ps0 UniversalProcessStartListHomeProcess Big #zClass
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
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 UniversalProcessStartListHomeProcess #zField
Ps0 f0 guid 14790C36EF2EA68E #txt
Ps0 f0 type ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData #txt
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
Ps0 f0 86 54 20 20 13 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData #txt
Ps0 f1 86 150 20 20 13 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 96 74 96 150 #arcP
Ps0 f3 guid 14790C4B765C5485 #txt
Ps0 f3 type ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onload</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 195 54 20 20 13 0 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData #txt
Ps0 f4 195 150 20 20 13 0 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 205 74 205 150 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome.UniversalProcessStartListHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
