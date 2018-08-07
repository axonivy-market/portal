[Ivy]
[>Created: Tue Sep 15 11:39:36 ICT 2015]
14768063E604D23A 3.17 #module
>Proto >Proto Collection #zClass
Ss0 SubmitITOrderFormProcess Big #zClass
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
>Proto Ss0 Ss0 SubmitITOrderFormProcess #zField
Ss0 f0 guid 14768063E81F3028 #txt
Ss0 f0 type test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData #txt
Ss0 f0 method start() #txt
Ss0 f0 disableUIEvents true #txt
Ss0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ss0 f0 outParameterDecl '<java.lang.String Mitarbeiter,java.lang.String Manager,java.lang.String Equipment,java.lang.Number Durchwahl,java.lang.Boolean Approved> result;
' #txt
Ss0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.Manager=in.Manager;
result.Equipment=in.Equipment;
result.Durchwahl=in.Durchwahl;
result.Approved=in.Approved;
' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 51 51 26 26 -16 15 #rect
Ss0 f0 @|RichDialogInitStartIcon #fIcon
Ss0 f0 -1|-1|-9671572 #nodeStyle
Ss0 f1 type test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData #txt
Ss0 f1 243 51 26 26 0 12 #rect
Ss0 f1 @|RichDialogProcessEndIcon #fIcon
Ss0 f1 -1|-1|-9671572 #nodeStyle
Ss0 f2 expr out #txt
Ss0 f2 77 64 243 64 #arcP
Ss0 f3 guid 14768063E8BF0D22 #txt
Ss0 f3 type test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData #txt
Ss0 f3 actionDecl 'test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData out;
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
Ss0 f3 51 147 26 26 -15 12 #rect
Ss0 f3 @|RichDialogProcessStartIcon #fIcon
Ss0 f3 -1|-1|-9671572 #nodeStyle
Ss0 f4 type test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData #txt
Ss0 f4 guid 14768063E8BA4531 #txt
Ss0 f4 243 147 26 26 0 12 #rect
Ss0 f4 @|RichDialogEndIcon #fIcon
Ss0 f4 -1|-1|-9671572 #nodeStyle
Ss0 f5 expr out #txt
Ss0 f5 77 160 243 160 #arcP
>Proto Ss0 .type test002ITInfrastructure.SubmitITOrderForm.SubmitITOrderFormData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f3 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
