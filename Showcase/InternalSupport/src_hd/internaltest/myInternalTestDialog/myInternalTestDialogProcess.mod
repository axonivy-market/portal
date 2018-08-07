[Ivy]
[>Created: Wed Jun 18 13:11:10 CEST 2014]
146AEAC5C7816D00 3.17 #module
>Proto >Proto Collection #zClass
ms0 myInternalTestDialogProcess Big #zClass
ms0 RD #cInfo
ms0 #process
ms0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
ms0 @TextInP .rdData2UIAction .rdData2UIAction #zField
ms0 @TextInP .resExport .resExport #zField
ms0 @TextInP .type .type #zField
ms0 @TextInP .processKind .processKind #zField
ms0 @AnnotationInP-0n ai ai #zField
ms0 @MessageFlowInP-0n messageIn messageIn #zField
ms0 @MessageFlowOutP-0n messageOut messageOut #zField
ms0 @TextInP .xml .xml #zField
ms0 @TextInP .responsibility .responsibility #zField
ms0 @RichDialogInitStart f0 '' #zField
ms0 @RichDialogProcessEnd f1 '' #zField
ms0 @PushWFArc f2 '' #zField
ms0 @RichDialogProcessStart f3 '' #zField
ms0 @RichDialogEnd f4 '' #zField
ms0 @PushWFArc f5 '' #zField
>Proto ms0 ms0 myInternalTestDialogProcess #zField
ms0 f0 guid 146AEAC5CA7F4CD2 #txt
ms0 f0 type internaltest.myInternalTestDialog.myInternalTestDialogData #txt
ms0 f0 method start(List<ch.ivy.ws.addon.IvyCase>) #txt
ms0 f0 disableUIEvents true #txt
ms0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<List<ch.ivy.ws.addon.IvyCase> cases> param = methodEvent.getInputArguments();
' #txt
ms0 f0 inParameterMapAction 'out.cases=param.cases;
' #txt
ms0 f0 outParameterDecl '<List<ch.ivy.ws.addon.IvyCase> cases> result;
' #txt
ms0 f0 outParameterMapAction 'result.cases=in.cases;
' #txt
ms0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;IvyCase&gt;)</name>
    </language>
</elementInfo>
' #txt
ms0 f0 51 51 26 26 -54 15 #rect
ms0 f0 @|RichDialogInitStartIcon #fIcon
ms0 f0 -1|-1|-9671572 #nodeStyle
ms0 f1 type internaltest.myInternalTestDialog.myInternalTestDialogData #txt
ms0 f1 243 51 26 26 0 12 #rect
ms0 f1 @|RichDialogProcessEndIcon #fIcon
ms0 f1 -1|-1|-9671572 #nodeStyle
ms0 f2 expr out #txt
ms0 f2 77 64 243 64 #arcP
ms0 f3 guid 146AEAC5CB5BDD25 #txt
ms0 f3 type internaltest.myInternalTestDialog.myInternalTestDialogData #txt
ms0 f3 actionDecl 'internaltest.myInternalTestDialog.myInternalTestDialogData out;
' #txt
ms0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
ms0 f3 51 147 26 26 -15 12 #rect
ms0 f3 @|RichDialogProcessStartIcon #fIcon
ms0 f3 -1|-1|-9671572 #nodeStyle
ms0 f4 type internaltest.myInternalTestDialog.myInternalTestDialogData #txt
ms0 f4 guid 146AEAC5CB6B3F9E #txt
ms0 f4 243 147 26 26 0 12 #rect
ms0 f4 @|RichDialogEndIcon #fIcon
ms0 f4 -1|-1|-9671572 #nodeStyle
ms0 f5 expr out #txt
ms0 f5 77 160 243 160 #arcP
>Proto ms0 .type internaltest.myInternalTestDialog.myInternalTestDialogData #txt
>Proto ms0 .processKind HTML_DIALOG #txt
>Proto ms0 -8 -8 16 16 16 26 #rect
>Proto ms0 '' #fIcon
ms0 f0 mainOut f2 tail #connect
ms0 f2 head f1 mainIn #connect
ms0 f3 mainOut f5 tail #connect
ms0 f5 head f4 mainIn #connect
