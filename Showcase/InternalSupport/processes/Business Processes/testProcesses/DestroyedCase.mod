[Ivy]
[>Created: Wed Sep 09 10:35:07 ICT 2015]
14FB01F59664D93D 3.17 #module
>Proto >Proto Collection #zClass
Ds0 DestroyedCase Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartRequest f0 '' #zField
Ds0 @GridStep f2 '' #zField
Ds0 @TaskSwitchSimple f3 '' #zField
Ds0 @TkArc f4 '' #zField
Ds0 @PushWFArc f1 '' #zField
>Proto Ds0 Ds0 DestroyedCase #zField
Ds0 f0 outLink DestroyCase.ivp #txt
Ds0 f0 type internaltest.Data #txt
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 actionDecl 'internaltest.Data out;
' #txt
Ds0 f0 guid 14FB01F6E77D7715 #txt
Ds0 f0 requestEnabled true #txt
Ds0 f0 triggerEnabled false #txt
Ds0 f0 callSignature DestroyCase() #txt
Ds0 f0 persist false #txt
Ds0 f0 taskData '#
#Wed Sep 09 10:34:29 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ds0 f0 caseData '#
#Wed Sep 09 10:34:29 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=Case to be destroyed
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ds0 f0 showInStartList 1 #txt
Ds0 f0 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("Case to be destroyed"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DestroyCase.ivp</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 @C|.responsibility Everybody #txt
Ds0 f0 83 51 26 26 14 0 #rect
Ds0 f0 @|StartRequestIcon #fIcon
Ds0 f2 actionDecl 'internaltest.Data out;
' #txt
Ds0 f2 actionTable 'out=in;
' #txt
Ds0 f2 actionCode 'import ch.internalsupport.CaseDemoUtils;
CaseDemoUtils.destroyCase(ivy.case);' #txt
Ds0 f2 type internaltest.Data #txt
Ds0 f2 78 180 36 24 20 -2 #rect
Ds0 f2 @|StepIcon #fIcon
Ds0 f3 actionDecl 'internaltest.Data out;
' #txt
Ds0 f3 actionTable 'out=in1;
' #txt
Ds0 f3 outTypes "internaltest.Data" #txt
Ds0 f3 outLinks "TaskA.ivp" #txt
Ds0 f3 caseData '#
#Wed Sep 09 10:34:34 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ds0 f3 taskData '#
#Wed Sep 09 10:34:34 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Task to be destroyed
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
' #txt
Ds0 f3 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Task to be destroyed"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ds0 f3 type internaltest.Data #txt
Ds0 f3 template "" #txt
Ds0 f3 82 114 28 28 14 0 #rect
Ds0 f3 @|TaskSwitchSimpleIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 type internaltest.Data #txt
Ds0 f4 var in1 #txt
Ds0 f4 96 77 96 114 #arcP
Ds0 f1 expr data #txt
Ds0 f1 outCond ivp=="TaskA.ivp" #txt
Ds0 f1 96 142 96 180 #arcP
>Proto Ds0 .type internaltest.Data #txt
>Proto Ds0 .processKind NORMAL #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 in #connect
Ds0 f3 out f1 tail #connect
Ds0 f1 head f2 mainIn #connect
