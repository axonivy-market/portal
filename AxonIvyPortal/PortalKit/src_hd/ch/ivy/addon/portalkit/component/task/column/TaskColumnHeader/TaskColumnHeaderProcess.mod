[Ivy]
15AF019B09976068 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskColumnHeaderProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdMethod f4 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdProcessEnd f3 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskColumnHeaderProcess #zField
Ts0 f0 guid 15AE9A0BAA4D09B7 #txt
Ts0 f0 method start(ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f0 inParameterDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f0 inParameterMapAction 'out.dataModel=param.#dataModel;
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f4 guid 15AF4FD86A995BBD #txt
Ts0 f4 method sort(String,Boolean) #txt
Ts0 f4 inParameterDecl '<String field,Boolean isDescending> param;' #txt
Ts0 f4 inParameterMapAction 'out.field=param.field;
out.isDescending=param.isDescending;
' #txt
Ts0 f4 outParameterDecl '<> result;' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort(String,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 276 86 22 22 14 0 #rect
Ts0 f4 @|UdMethodIcon #fIcon
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'in.dataModel.setSortField(in.field, in.isDescending);' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort tasks
by type</name>
        <nameStyle>18
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 269 149 36 24 20 -2 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 287 108 287 149 #arcP
Ts0 f8 0 0.40933572710951527 0 0 #arcLabel
Ts0 f3 277 213 22 22 14 0 #rect
Ts0 f3 @|UdProcessEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 287 173 287 213 #arcP
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import javax.faces.context.FacesContext;

if (!out.#dataModel is initialized) {
	FacesContext context = FacesContext.getCurrentInstance();
	TaskWidgetBean taskWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskWidgetBean}", TaskWidgetBean.class) as TaskWidgetBean;
	out.dataModel = taskWidgetBean.dataModel;
}' #txt
Ts0 f10 security system #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init dataModel
if empty</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f10 46 146 36 24 20 -2 #rect
Ts0 f10 @|StepIcon #fIcon
Ts0 f11 expr out #txt
Ts0 f11 64 107 64 146 #arcP
Ts0 f2 expr out #txt
Ts0 f2 64 170 64 213 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.column.TaskColumnHeader.TaskColumnHeaderData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f4 mainOut f8 tail #connect
Ts0 f8 head f6 mainIn #connect
Ts0 f6 mainOut f5 tail #connect
Ts0 f5 head f3 mainIn #connect
Ts0 f0 mainOut f11 tail #connect
Ts0 f11 head f10 mainIn #connect
Ts0 f10 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
