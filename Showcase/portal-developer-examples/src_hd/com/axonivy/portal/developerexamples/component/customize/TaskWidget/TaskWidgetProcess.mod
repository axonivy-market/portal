[Ivy]
1624D16247B4E149 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 TaskWidgetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdInit f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @UdInit f0 '' #zField
Ps0 @GridStep f8 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 TaskWidgetProcess #zField
Ps0 f1 229 133 22 22 14 0 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 guid 1573377403EC1C55 #txt
Ps0 f2 method useTaskView(TaskView) #txt
Ps0 f2 inParameterDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Ps0 f2 inParameterMapAction 'out.taskView=param.taskView;
' #txt
Ps0 f2 outParameterDecl '<> result;' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useTaskView(TaskView)</name>
    </language>
</elementInfo>
' #txt
Ps0 f2 77 133 22 22 -36 17 #rect
Ps0 f2 @|UdInitIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 99 144 229 144 #arcP
Ps0 f0 guid 17553C64DE25586A #txt
Ps0 f0 method start(String,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,Long) #txt
Ps0 f0 inParameterDecl '<String keyword,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,Long filterGroupId> param;' #txt
Ps0 f0 inParameterMapAction 'out.keyword=param.#keyword;
out.taskFilterGroupId=param.filterGroupId;
' #txt
Ps0 f0 inActionCode 'if (param.#dataModel is initialized){
	out.dataModel = param.dataModel;
}' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 77 53 22 22 -14 13 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import com.axonivy.portal.developerexamples.component.customize.CustomizedTaskLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
TaskWidgetBean taskWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskWidgetBean}", TaskWidgetBean.class) as TaskWidgetBean;
if (!in.#dataModel is initialized) {
	TaskLazyDataModel dataModel = new CustomizedTaskLazyDataModel();
	dataModel.setCompactMode(true);
	taskWidgetBean.setDataModel(dataModel);
	in.dataModel = taskWidgetBean.dataModel;
} else {
	taskWidgetBean.setDataModel(in.dataModel);
}' #txt
Ps0 f8 security system #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data model</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 160 42 112 44 -40 -8 #rect
Ps0 f8 @|StepIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 99 64 160 64 #arcP
Ps0 f4 339 51 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f5 272 64 339 64 #arcP
>Proto Ps0 .type com.axonivy.portal.developerexamples.component.customize.TaskWidget.TaskWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f2 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f0 mainOut f10 tail #connect
Ps0 f10 head f8 mainIn #connect
Ps0 f8 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
