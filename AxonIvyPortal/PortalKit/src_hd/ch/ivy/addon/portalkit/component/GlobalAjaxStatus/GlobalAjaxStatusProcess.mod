[Ivy]
[>Created: Thu Nov 19 14:56:54 ICT 2015]
1511EBF05C5FCFDA 3.18 #module
>Proto >Proto Collection #zClass
Gs0 GlobalAjaxStatusProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
>Proto Gs0 Gs0 GlobalAjaxStatusProcess #zField
Gs0 f0 guid 1511EBF0605F14EC #txt
Gs0 f0 type ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData #txt
Gs0 f0 method start() #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 outParameterDecl '<> result;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 53 85 22 22 14 0 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData #txt
Gs0 f1 53 213 22 22 14 0 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 64 107 64 213 #arcP
Gs0 f3 guid 1511EBF0614F046F #txt
Gs0 f3 type ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData #txt
Gs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData out;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 149 85 22 22 14 0 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData #txt
Gs0 f4 guid 1511EBF06149BBDC #txt
Gs0 f4 149 213 22 22 14 0 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 160 107 160 213 #arcP
>Proto Gs0 .type ch.ivy.addon.portalkit.component.GlobalAjaxStatus.GlobalAjaxStatusData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
