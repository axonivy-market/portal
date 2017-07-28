[Ivy]
[>Created: Wed Jul 26 11:05:35 ICT 2017]
15D7D120CF82BF05 3.20 #module
>Proto >Proto Collection #zClass
Ss0 AdvancedFilterProcess Big #zClass
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
>Proto Ss0 Ss0 AdvancedFilterProcess #zField
Ss0 f0 guid 152EE20AC7136182 #txt
Ss0 f0 type ch.ivy.addon.portalkit.component.AdvancedFilter.AdvancedFilterData #txt
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
Ss0 f0 53 85 22 22 14 0 #rect
Ss0 f0 @|RichDialogInitStartIcon #fIcon
Ss0 f1 type ch.ivy.addon.portalkit.component.AdvancedFilter.AdvancedFilterData #txt
Ss0 f1 213 85 22 22 14 0 #rect
Ss0 f1 @|RichDialogProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 75 96 213 96 #arcP
>Proto Ss0 .type ch.ivy.addon.portalkit.component.AdvancedFilter.AdvancedFilterData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
