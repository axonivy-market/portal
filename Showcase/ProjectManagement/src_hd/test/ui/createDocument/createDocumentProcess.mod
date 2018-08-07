[Ivy]
[>Created: Fri Jul 25 10:27:43 CEST 2014]
1476CA205106A66D 3.17 #module
>Proto >Proto Collection #zClass
cs0 createDocumentProcess Big #zClass
cs0 RD #cInfo
cs0 #process
cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
cs0 @TextInP .resExport .resExport #zField
cs0 @TextInP .type .type #zField
cs0 @TextInP .processKind .processKind #zField
cs0 @AnnotationInP-0n ai ai #zField
cs0 @MessageFlowInP-0n messageIn messageIn #zField
cs0 @MessageFlowOutP-0n messageOut messageOut #zField
cs0 @TextInP .xml .xml #zField
cs0 @TextInP .responsibility .responsibility #zField
cs0 @RichDialogInitStart f0 '' #zField
cs0 @RichDialogProcessEnd f1 '' #zField
cs0 @PushWFArc f2 '' #zField
cs0 @RichDialogProcessStart f3 '' #zField
cs0 @RichDialogEnd f4 '' #zField
cs0 @PushWFArc f5 '' #zField
>Proto cs0 cs0 createDocumentProcess #zField
cs0 f0 guid 1476CA2052C9A37F #txt
cs0 f0 type test.ui.createDocument.createDocumentData #txt
cs0 f0 method start() #txt
cs0 f0 disableUIEvents true #txt
cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
cs0 f0 outParameterDecl '<java.lang.String ProjectManager,ch.ivyteam.ivy.scripting.objects.Date ProjectStart,ch.ivyteam.ivy.scripting.objects.Date ProjectEnd,java.lang.Number ProjectID,java.lang.String ProjectName> result;
' #txt
cs0 f0 outParameterMapAction 'result.ProjectManager=in.ProjectManager;
result.ProjectStart=in.ProjectStart;
result.ProjectEnd=in.ProjectEnd;
result.ProjectID=in.ProjectID;
result.ProjectName=in.ProjectName;
' #txt
cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
cs0 f0 51 51 26 26 -16 15 #rect
cs0 f0 @|RichDialogInitStartIcon #fIcon
cs0 f0 -1|-1|-9671572 #nodeStyle
cs0 f1 type test.ui.createDocument.createDocumentData #txt
cs0 f1 243 51 26 26 0 12 #rect
cs0 f1 @|RichDialogProcessEndIcon #fIcon
cs0 f1 -1|-1|-9671572 #nodeStyle
cs0 f2 expr out #txt
cs0 f2 77 64 243 64 #arcP
cs0 f3 guid 1476CA20533444A1 #txt
cs0 f3 type test.ui.createDocument.createDocumentData #txt
cs0 f3 actionDecl 'test.ui.createDocument.createDocumentData out;
' #txt
cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
cs0 f3 51 147 26 26 -15 12 #rect
cs0 f3 @|RichDialogProcessStartIcon #fIcon
cs0 f3 -1|-1|-9671572 #nodeStyle
cs0 f4 type test.ui.createDocument.createDocumentData #txt
cs0 f4 guid 1476CA2053381AA1 #txt
cs0 f4 243 147 26 26 0 12 #rect
cs0 f4 @|RichDialogEndIcon #fIcon
cs0 f4 -1|-1|-9671572 #nodeStyle
cs0 f5 expr out #txt
cs0 f5 77 160 243 160 #arcP
>Proto cs0 .type test.ui.createDocument.createDocumentData #txt
>Proto cs0 .processKind HTML_DIALOG #txt
>Proto cs0 -8 -8 16 16 16 26 #rect
>Proto cs0 '' #fIcon
cs0 f0 mainOut f2 tail #connect
cs0 f2 head f1 mainIn #connect
cs0 f3 mainOut f5 tail #connect
cs0 f5 head f4 mainIn #connect
