[Ivy]
[>Created: Wed Apr 15 11:53:27 ICT 2015]
14BCA779D60A8E1B 3.17 #module
>Proto >Proto Collection #zClass
Ts0 TaskInformationTestProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogProcessEnd f4 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @RichDialogEnd f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @RichDialogInitStart f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
>Proto Ts0 Ts0 TaskInformationTestProcess #zField
Ts0 f4 type ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskInformationTest.TaskInformationTestData #txt
Ts0 f4 54 182 20 20 13 0 #rect
Ts0 f4 @|RichDialogProcessEndIcon #fIcon
Ts0 f6 guid 14B6D7BACC71CA79 #txt
Ts0 f6 type ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskInformationTest.TaskInformationTestData #txt
Ts0 f6 method navigateOut() #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateOut()</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 270 54 20 20 13 0 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskInformationTest.TaskInformationTestData #txt
Ts0 f7 guid 14B6D7BB70F51459 #txt
Ts0 f7 270 182 20 20 13 0 #rect
Ts0 f7 @|RichDialogEndIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 280 74 280 182 #arcP
Ts0 f9 guid 14B71BA74CBDCC70 #txt
Ts0 f9 type ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskInformationTest.TaskInformationTestData #txt
Ts0 f9 method startWithTaskId(Number) #txt
Ts0 f9 disableUIEvents true #txt
Ts0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number taskId> param = methodEvent.getInputArguments();
' #txt
Ts0 f9 inActionCode '//out.selectedTask = ivy.wf.findTask(param.taskId);
import ch.ivyteam.logicalexpression.RelationalOperator;
import java.util.EnumSet;
import ch.ivyteam.ivy.workflow.PropertyOrder;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.TaskProperty;
import ch.ivyteam.ivy.persistence.OrderDirection;
import ch.ivyteam.ivy.persistence.IQueryResult;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IPropertyFilter;

out.selectedTask = null;
IPropertyFilter taskFilter = ivy.wf.createTaskPropertyFilter(TaskProperty.ID, RelationalOperator.EQUAL, param.taskId);

	IQueryResult queryResult = ivy.session.findWorkedOnTasks(taskFilter,
		PropertyOrder.create(TaskProperty.ID, OrderDirection.DESCENDING),0, 1 ,true);
  if(queryResult.getAllCount() != 0)
	{
		out.selectedTask = queryResult.get(0) as ITask;
	}


if(out.selectedTask == null)
{

	IQueryResult queryResult  = ivy.session.findWorkTasks(taskFilter, PropertyOrder.create(TaskProperty.ID, OrderDirection.DESCENDING), 
  	0, 1, true, EnumSet.of(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED));
	if(queryResult.getResultCount() > 0){
		out.selectedTask = queryResult.get(0) as ITask;
	}
}
out.visible = true;' #txt
Ts0 f9 outParameterDecl '<> result;
' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithTaskId(Number)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f9 54 54 20 20 13 0 #rect
Ts0 f9 @|RichDialogInitStartIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 64 74 64 182 #arcP
Ts0 f10 0 0.6454224957957531 0 0 #arcLabel
>Proto Ts0 .type ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskInformationTest.TaskInformationTestData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>starts</swimlaneLabel>
        <swimlaneLabel>methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>232</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f9 mainOut f10 tail #connect
Ts0 f10 head f4 mainIn #connect
