[Ivy]
14BEF201D2E3FF7D 3.20 #module
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
Ps0 @PushWFArc f11 '' #zField
Ps0 @RichDialogMethodStart f1 '' #zField
Ps0 @RichDialogProcessEnd f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @RichDialogMethodStart f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
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
Ps0 f10 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f10 213 53 22 22 14 0 #rect
Ps0 f10 @|RichDialogProcessEndIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 109 64 213 64 #arcP
Ps0 f11 0 0.49999999999999994 0 0 #arcLabel
Ps0 f1 guid 15BA979D15878BE6 #txt
Ps0 f1 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f1 method getDataModel() #txt
Ps0 f1 disableUIEvents false #txt
Ps0 f1 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f1 inActionCode 'import ch.ivy.ws.addon.TaskSearchCriteria;

TaskSearchCriteria criteria = out.taskView.dataModel.searchCriteria;
if (out.isNotLogin) {
	criteria.setInvolvedUsername(ivy.session.getSessionUserName());
	out.isNotLogin = false;
}' #txt
Ps0 f1 outParameterDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> result;
' #txt
Ps0 f1 outParameterMapAction 'result.dataModel=in.taskView.dataModel;
' #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getDataModel()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f1 85 149 22 22 14 0 #rect
Ps0 f1 @|RichDialogMethodStartIcon #fIcon
Ps0 f2 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f2 213 149 22 22 14 0 #rect
Ps0 f2 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 107 160 213 160 #arcP
Ps0 f4 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f4 210 210 22 22 14 0 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 guid 1604F4CCC363BD5A #txt
Ps0 f5 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f5 method getStatisticCharts() #txt
Ps0 f5 disableUIEvents false #txt
Ps0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f5 inActionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
out.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());' #txt
Ps0 f5 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> result;
' #txt
Ps0 f5 outParameterMapAction 'result.statisticChartList=in.statisticChartList;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getStatisticCharts()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f5 82 210 22 22 14 0 #rect
Ps0 f5 @|RichDialogMethodStartIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 104 221 210 221 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f1 mainOut f3 tail #connect
Ps0 f3 head f2 mainIn #connect
Ps0 f5 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
