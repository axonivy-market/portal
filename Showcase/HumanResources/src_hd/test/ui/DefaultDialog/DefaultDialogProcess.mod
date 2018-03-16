[Ivy]
1622D95D859EEDE1 3.20 #module
>Proto >Proto Collection #zClass
Ds0 DefaultDialogProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @RichDialogProcessStart f3 '' #zField
Ds0 @RichDialogEnd f4 '' #zField
Ds0 @PushWFArc f5 '' #zField
>Proto Ds0 Ds0 DefaultDialogProcess #zField
Ds0 f0 guid 1622D95D86F9FCD2 #txt
Ds0 f0 type test.ui.DefaultDialog.DefaultDialogData #txt
Ds0 f0 method start() #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 outParameterDecl '<> result;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 53 85 22 22 14 0 #rect
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f1 type test.ui.DefaultDialog.DefaultDialogData #txt
Ds0 f1 53 213 22 22 14 0 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 64 107 64 213 #arcP
Ds0 f3 guid 1622D95D87D76A92 #txt
Ds0 f3 type test.ui.DefaultDialog.DefaultDialogData #txt
Ds0 f3 actionDecl 'test.ui.DefaultDialog.DefaultDialogData out;
' #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 149 85 22 22 14 0 #rect
Ds0 f3 @|RichDialogProcessStartIcon #fIcon
Ds0 f4 type test.ui.DefaultDialog.DefaultDialogData #txt
Ds0 f4 guid 1622D95D87DE9341 #txt
Ds0 f4 149 213 22 22 14 0 #rect
Ds0 f4 @|RichDialogEndIcon #fIcon
Ds0 f5 expr out #txt
Ds0 f5 160 107 160 213 #arcP
>Proto Ds0 .type test.ui.DefaultDialog.DefaultDialogData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
