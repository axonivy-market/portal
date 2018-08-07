[Ivy]
[>Created: Tue Mar 28 10:57:57 ICT 2017]
15B0ED8770CD5F13 3.20 #module
>Proto >Proto Collection #zClass
Ps0 PortalHomeProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f10 '' #zField
Ps0 @PushWFArc f1 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f10 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f10 461 53 22 22 14 0 #rect
Ps0 f10 @|RichDialogProcessEndIcon #fIcon
Ps0 f1 expr out #txt
Ps0 f1 109 64 461 64 #arcP
Ps0 f2 actionDecl 'internalPortal.PortalHome.PortalHomeData out;
' #txt
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode 'import ch.internalsupport.CustomizedTaskLazyDataModel;
if(!in.#dataModel is initialized) {
	in.dataModel = new CustomizedTaskLazyDataModel();
}' #txt
Ps0 f2 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f2 254 148 36 24 20 -2 #rect
Ps0 f2 @|StepIcon #fIcon
Ps0 f3 guid 15B130D6320CEBC9 #txt
Ps0 f3 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f3 method getDataModel() #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 outParameterDecl '<ch.internalsupport.CustomizedTaskLazyDataModel dataModel> result;
' #txt
Ps0 f3 outParameterMapAction 'result.dataModel=in.dataModel;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getModel()</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 85 149 22 22 14 0 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f4 437 149 22 22 14 0 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 107 160 254 160 #arcP
Ps0 f6 expr out #txt
Ps0 f6 290 160 437 160 #arcP
>Proto Ps0 .type internalPortal.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f1 tail #connect
Ps0 f1 head f10 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f2 mainIn #connect
Ps0 f2 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
