[Ivy]
[>Created: Mon Sep 21 10:17:46 ICT 2015]
14FEEC123381CEF1 3.17 #module
>Proto >Proto Collection #zClass
Is0 IconSelectionProcess Big #zClass
Is0 RD #cInfo
Is0 #process
Is0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Is0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Is0 @TextInP .resExport .resExport #zField
Is0 @TextInP .type .type #zField
Is0 @TextInP .processKind .processKind #zField
Is0 @AnnotationInP-0n ai ai #zField
Is0 @MessageFlowInP-0n messageIn messageIn #zField
Is0 @MessageFlowOutP-0n messageOut messageOut #zField
Is0 @TextInP .xml .xml #zField
Is0 @TextInP .responsibility .responsibility #zField
Is0 @RichDialogInitStart f0 '' #zField
Is0 @RichDialogProcessEnd f1 '' #zField
Is0 @PushWFArc f2 '' #zField
Is0 @RichDialogProcessStart f3 '' #zField
Is0 @RichDialogEnd f4 '' #zField
Is0 @PushWFArc f5 '' #zField
>Proto Is0 Is0 IconSelectionProcess #zField
Is0 f0 guid 14FEDE843831B16D #txt
Is0 f0 type ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData #txt
Is0 f0 method start() #txt
Is0 f0 disableUIEvents true #txt
Is0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Is0 f0 outParameterDecl '<> result;
' #txt
Is0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Is0 f0 53 85 22 22 14 0 #rect
Is0 f0 @|RichDialogInitStartIcon #fIcon
Is0 f1 type ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData #txt
Is0 f1 53 213 22 22 14 0 #rect
Is0 f1 @|RichDialogProcessEndIcon #fIcon
Is0 f2 expr out #txt
Is0 f2 64 107 64 213 #arcP
Is0 f3 guid 14FEDE84398C93DB #txt
Is0 f3 type ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData #txt
Is0 f3 actionDecl 'ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData out;
' #txt
Is0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Is0 f3 149 85 22 22 14 0 #rect
Is0 f3 @|RichDialogProcessStartIcon #fIcon
Is0 f4 type ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData #txt
Is0 f4 guid 14FEDE843999EE45 #txt
Is0 f4 149 213 22 22 14 0 #rect
Is0 f4 @|RichDialogEndIcon #fIcon
Is0 f5 expr out #txt
Is0 f5 160 107 160 213 #arcP
>Proto Is0 .type ch.ivy.addon.portalkit.component.IconSelection.IconSelectionData #txt
>Proto Is0 .processKind HTML_DIALOG #txt
>Proto Is0 -8 -8 16 16 16 26 #rect
>Proto Is0 '' #fIcon
Is0 f0 mainOut f2 tail #connect
Is0 f2 head f1 mainIn #connect
Is0 f3 mainOut f5 tail #connect
Is0 f5 head f4 mainIn #connect
