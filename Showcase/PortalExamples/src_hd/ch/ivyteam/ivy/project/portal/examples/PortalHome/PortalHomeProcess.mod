[Ivy]
1624C8263F9A746E 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalHomeProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f3 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @UdMethod f1 '' #zField
Ps0 @UdMethod f5 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 1624C82641E60027 #txt
Ps0 f0 method start(TaskView) #txt
Ps0 f0 inParameterDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Ps0 f0 inParameterMapAction 'out.taskView=param.taskView;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TaskView)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f3 275 51 26 26 0 12 #rect
Ps0 f3 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 275 64 #arcP
Ps0 f2 0 0.5128987759876967 0 0 #arcLabel
Ps0 f4 210 210 22 22 14 0 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f1 guid 16DFD35F29D35A9A #txt
Ps0 f1 method getDataModel() #txt
Ps0 f1 inParameterDecl '<> param;' #txt
Ps0 f1 inActionCode 'import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;

TaskSearchCriteria criteria = out.taskView.dataModel.criteria;
if (out.isNotLogin) {
	criteria.setInvolvedUsername(ivy.session.getSessionUserName());
	out.isNotLogin = false;
}' #txt
Ps0 f1 outParameterDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> result;' #txt
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
Ps0 f1 @|UdMethodIcon #fIcon
Ps0 f5 guid 16DFD35F29D362F8 #txt
Ps0 f5 method getStatisticCharts() #txt
Ps0 f5 inParameterDecl '<> param;' #txt
Ps0 f5 inActionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
out.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());' #txt
Ps0 f5 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> result;' #txt
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
Ps0 f5 @|UdMethodIcon #fIcon
Ps0 f6 213 149 22 22 14 0 #rect
Ps0 f6 @|UdProcessEndIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 107 160 213 160 #arcP
Ps0 f8 expr out #txt
Ps0 f8 104 221 210 221 #arcP
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f3 mainIn #connect
Ps0 f1 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f5 mainOut f8 tail #connect
Ps0 f8 head f4 mainIn #connect
