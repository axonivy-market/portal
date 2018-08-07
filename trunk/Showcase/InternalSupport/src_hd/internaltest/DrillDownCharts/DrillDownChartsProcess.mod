[Ivy]
[>Created: Fri May 19 15:31:44 ICT 2017]
15C1FA307D3E1AB1 3.20 #module
>Proto >Proto Collection #zClass
Ds0 DrillDownChartsProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @RichDialogMethodStart f3 '' #zField
Ds0 @GridStep f4 '' #zField
Ds0 @PushWFArc f6 '' #zField
Ds0 @RichDialogEnd f8 '' #zField
Ds0 @PushWFArc f9 '' #zField
>Proto Ds0 Ds0 DrillDownChartsProcess #zField
Ds0 f0 guid 15C1FA307E9AFFBE #txt
Ds0 f0 type internaltest.DrillDownCharts.DrillDownChartsData #txt
Ds0 f0 method start() #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 outParameterDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> result;
' #txt
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
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f1 type internaltest.DrillDownCharts.DrillDownChartsData #txt
Ds0 f1 211 51 26 26 0 12 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 109 64 211 64 #arcP
Ds0 f3 guid 15C1FC4805AB2CA0 #txt
Ds0 f3 type internaltest.DrillDownCharts.DrillDownChartsData #txt
Ds0 f3 method itemSelect(org.primefaces.event.ItemSelectEvent) #txt
Ds0 f3 disableUIEvents false #txt
Ds0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.ItemSelectEvent event> param = methodEvent.getInputArguments();
' #txt
Ds0 f3 inParameterMapAction 'out.event=param.event;
' #txt
Ds0 f3 outParameterDecl '<> result;
' #txt
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
Ds0 f3 @|RichDialogMethodStartIcon #fIcon
Ds0 f4 actionDecl 'internaltest.DrillDownCharts.DrillDownChartsData out;
' #txt
Ds0 f4 actionTable 'out=in;
' #txt
Ds0 f4 actionCode 'import ch.internalsupport.CustomizedTaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.internalsupport.ChartView;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
ChartView chartView = context.getApplication().evaluateExpressionGet(context, "#{chartView}", ChartView.class) as ChartView;

TaskLazyDataModel dataModel = new CustomizedTaskLazyDataModel();

if (in.event.getItemIndex() == 0) {
	dataModel.getQueryCriteria().setTaskQuery(chartView.getToday());
} else if (in.event.getItemIndex() == 1) {
	dataModel.getQueryCriteria().setTaskQuery(chartView.getTomorrow());
} else if (in.event.getItemIndex() == 2) {
	dataModel.getQueryCriteria().setTaskQuery(chartView.getIn2Days());
} else if (in.event.getItemIndex() == 3) {
	dataModel.getQueryCriteria().setTaskQuery(chartView.getIn3Days());
}

out.taskView = TaskView.create().dataModel(dataModel).showHeaderToolbar(false).createNewTaskView();' #txt
Ds0 f4 type internaltest.DrillDownCharts.DrillDownChartsData #txt
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
Ds0 f8 type internaltest.DrillDownCharts.DrillDownChartsData #txt
Ds0 f8 guid 15C1FD5734CD43A3 #txt
Ds0 f8 339 147 26 26 0 12 #rect
Ds0 f8 @|RichDialogEndIcon #fIcon
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
