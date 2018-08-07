[Ivy]
[>Created: Fri Jul 25 08:42:20 CEST 2014]
1476C4189AABAD75 3.17 #module
>Proto >Proto Collection #zClass
As0 AuditDetailsFormProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
As0 @TextInP .rdData2UIAction .rdData2UIAction #zField
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @RichDialogInitStart f0 '' #zField
As0 @RichDialogProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @RichDialogProcessStart f3 '' #zField
As0 @RichDialogEnd f4 '' #zField
As0 @PushWFArc f5 '' #zField
>Proto As0 As0 AuditDetailsFormProcess #zField
As0 f0 guid 1476C4189E65A9C8 #txt
As0 f0 type test.ui.AuditDetailsForm.AuditDetailsFormData #txt
As0 f0 method start() #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f0 outParameterDecl '<java.lang.String Mitarbeiter,java.lang.String Auditor,ch.ivyteam.ivy.scripting.objects.Date Termin,java.lang.Boolean compliant> result;
' #txt
As0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.Auditor=in.Auditor;
result.Termin=in.Termin;
result.compliant=in.compliant;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 51 51 26 26 -16 15 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f0 -1|-1|-9671572 #nodeStyle
As0 f1 type test.ui.AuditDetailsForm.AuditDetailsFormData #txt
As0 f1 243 51 26 26 0 12 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f1 -1|-1|-9671572 #nodeStyle
As0 f2 expr out #txt
As0 f2 77 64 243 64 #arcP
As0 f3 guid 1476C4189F0F38C4 #txt
As0 f3 type test.ui.AuditDetailsForm.AuditDetailsFormData #txt
As0 f3 actionDecl 'test.ui.AuditDetailsForm.AuditDetailsFormData out;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 51 147 26 26 -15 12 #rect
As0 f3 @|RichDialogProcessStartIcon #fIcon
As0 f3 -1|-1|-9671572 #nodeStyle
As0 f4 type test.ui.AuditDetailsForm.AuditDetailsFormData #txt
As0 f4 guid 1476C4189F0127F2 #txt
As0 f4 243 147 26 26 0 12 #rect
As0 f4 @|RichDialogEndIcon #fIcon
As0 f4 -1|-1|-9671572 #nodeStyle
As0 f5 expr out #txt
As0 f5 77 160 243 160 #arcP
>Proto As0 .type test.ui.AuditDetailsForm.AuditDetailsFormData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect
