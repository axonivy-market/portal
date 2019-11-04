[Ivy]
15C1FA307D3E1AB1 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 DrillDownChartsProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdMethod f3 '' #zField
Ds0 @GridStep f4 '' #zField
Ds0 @PushWFArc f6 '' #zField
Ds0 @UdExitEnd f8 '' #zField
Ds0 @PushWFArc f9 '' #zField
>Proto Ds0 Ds0 DrillDownChartsProcess #zField
Ds0 f0 guid 15C1FA307E9AFFBE #txt
Ds0 f0 method start() #txt
Ds0 f0 inParameterDecl '<> param;' #txt
Ds0 f0 outParameterDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> result;' #txt
Ds0 f0 outParameterMapAction 'result.taskView=in.taskView;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 83 51 26 26 -16 15 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 211 51 26 26 0 12 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 109 64 211 64 #arcP
Ds0 f3 guid 15C1FC4805AB2CA0 #txt
Ds0 f3 method itemSelect(org.primefaces.event.ItemSelectEvent) #txt
Ds0 f3 inParameterDecl '<org.primefaces.event.ItemSelectEvent event> param;' #txt
Ds0 f3 inParameterMapAction 'out.event=param.event;
' #txt
Ds0 f3 outParameterDecl '<> result;' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>itemSelect(ItemSelectEvent)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f3 83 147 26 26 -77 15 #rect
Ds0 f3 @|UdMethodIcon #fIcon
Ds0 f4 actionTable 'out=in;
' #txt
Ds0 f4 actionCode 'import ch.internalsupport.CustomizedTaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.internalsupport.ChartView;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
ChartView chartView = context.getApplication().evaluateExpressionGet(context, "#{chartView}", ChartView.class) as ChartView;

TaskLazyDataModel dataModel = new CustomizedTaskLazyDataModel();

if (in.event.getItemIndex() == 0) {
	dataModel.getCriteria().setCustomTaskQuery(chartView.getToday());
} else if (in.event.getItemIndex() == 1) {
	dataModel.getCriteria().setCustomTaskQuery(chartView.getTomorrow());
} else if (in.event.getItemIndex() == 2) {
	dataModel.getCriteria().setCustomTaskQuery(chartView.getIn2Days());
} else if (in.event.getItemIndex() == 3) {
	dataModel.getCriteria().setCustomTaskQuery(chartView.getIn3Days());
}

out.taskView = TaskView.create().dataModel(dataModel).showHeaderToolbar(false).createNewTaskView();' #txt
Ds0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task view</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f4 168 138 112 44 -40 -8 #rect
Ds0 f4 @|StepIcon #fIcon
Ds0 f6 expr out #txt
Ds0 f6 109 160 168 160 #arcP
Ds0 f8 339 147 26 26 0 12 #rect
Ds0 f8 @|UdExitEndIcon #fIcon
Ds0 f9 expr out #txt
Ds0 f9 280 160 339 160 #arcP
>Proto Ds0 .type internaltest.DrillDownCharts.DrillDownChartsData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f3 mainOut f6 tail #connect
Ds0 f6 head f4 mainIn #connect
Ds0 f4 mainOut f9 tail #connect
Ds0 f9 head f8 mainIn #connect
