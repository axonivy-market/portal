[Ivy]
[>Created: Fri Aug 22 11:27:04 CEST 2014]
14BCA8C3007612FE 3.17 #module
>Proto >Proto Collection #zClass
Cs0 UniversalCaseListHomeProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 UniversalCaseListHomeProcess #zField
Cs0 f0 guid 14749E02503A508C #txt
Cs0 f0 type ch.ivy.addon.portalkit.showroom.multiapp.cases.UniversalCaseListHome.UniversalCaseListHomeData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 86 54 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.showroom.multiapp.cases.UniversalCaseListHome.UniversalCaseListHomeData #txt
Cs0 f1 86 150 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 74 96 150 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.showroom.multiapp.cases.UniversalCaseListHome.UniversalCaseListHomeData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
