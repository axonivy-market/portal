[Ivy]
[>Created: Thu Apr 27 10:10:48 ICT 2017]
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
Ps0 @RichDialogMethodStart f2 '' #zField
Ps0 @RichDialogProcessEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f0 method start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portal.generic.view.TaskView taskView> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.isNotLogin=ivy.session.isSessionUserUnknown();
out.taskView=param.taskView;
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
Ps0 f2 guid 15BAD5F2E0E9E835 #txt
Ps0 f2 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f2 method getDataModel() #txt
Ps0 f2 disableUIEvents false #txt
Ps0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f2 inActionCode 'import ch.ivy.ws.addon.TaskSearchCriteria;

TaskSearchCriteria criteria = out.taskView.dataModel.searchCriteria;
if (out.isNotLogin) {
	criteria.setInvolvedUsername(ivy.session.getSessionUserName());
	out.isNotLogin = false;
}' #txt
Ps0 f2 outParameterDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> result;
' #txt
Ps0 f2 outParameterMapAction 'result.dataModel=in.taskView.dataModel;
' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getDataModel()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 88 150 22 22 14 0 #rect
Ps0 f2 @|RichDialogMethodStartIcon #fIcon
Ps0 f3 type internalPortal.PortalHome.PortalHomeData #txt
Ps0 f3 216 150 22 22 14 0 #rect
Ps0 f3 @|RichDialogProcessEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 110 161 216 161 #arcP
>Proto Ps0 .type internalPortal.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f1 tail #connect
Ps0 f1 head f10 mainIn #connect
Ps0 f2 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
