[Ivy]
163716C0F2A365F7 3.23 #module
>Proto >Proto Collection #zClass
Fs0 FinishedUserTasksFormProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Fs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Fs0 @TextInP .resExport .resExport #zField
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @AnnotationInP-0n ai ai #zField
Fs0 @MessageFlowInP-0n messageIn messageIn #zField
Fs0 @MessageFlowOutP-0n messageOut messageOut #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @RichDialogInitStart f0 '' #zField
Fs0 @RichDialogProcessEnd f1 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @RichDialogProcessStart f3 '' #zField
Fs0 @RichDialogEnd f4 '' #zField
Fs0 @PushWFArc f5 '' #zField
>Proto Fs0 Fs0 FinishedUserTasksFormProcess #zField
Fs0 f0 guid 163716C0F4AF1650 #txt
Fs0 f0 type ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData #txt
Fs0 f0 method start() #txt
Fs0 f0 disableUIEvents true #txt
Fs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f0 outParameterDecl '<> result;
' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -16 15 #rect
Fs0 f0 @|RichDialogInitStartIcon #fIcon
Fs0 f1 type ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData #txt
Fs0 f1 211 51 26 26 0 12 #rect
Fs0 f1 @|RichDialogProcessEndIcon #fIcon
Fs0 f2 expr out #txt
Fs0 f2 109 64 211 64 #arcP
Fs0 f3 guid 163716C0F598630B #txt
Fs0 f3 type ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData #txt
Fs0 f3 actionDecl 'ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData out;
' #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 147 26 26 -15 12 #rect
Fs0 f3 @|RichDialogProcessStartIcon #fIcon
Fs0 f4 type ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData #txt
Fs0 f4 guid 163716C0F59879BF #txt
Fs0 f4 211 147 26 26 0 12 #rect
Fs0 f4 @|RichDialogEndIcon #fIcon
Fs0 f5 expr out #txt
Fs0 f5 109 160 211 160 #arcP
>Proto Fs0 .type ch.ivy.gawfs.component.FinishedUserTasksForm.FinishedUserTasksFormData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
