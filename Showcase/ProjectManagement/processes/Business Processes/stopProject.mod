[Ivy]
[>Created: Tue May 05 16:23:24 ICT 2015]
147A019F7BE42947 3.17 #module
>Proto >Proto Collection #zClass
st0 stopProject Big #zClass
st0 B #cInfo
st0 #process
st0 @TextInP .resExport .resExport #zField
st0 @TextInP .type .type #zField
st0 @TextInP .processKind .processKind #zField
st0 @AnnotationInP-0n ai ai #zField
st0 @MessageFlowInP-0n messageIn messageIn #zField
st0 @MessageFlowOutP-0n messageOut messageOut #zField
st0 @TextInP .xml .xml #zField
st0 @TextInP .responsibility .responsibility #zField
st0 @StartRequest f0 '' #zField
st0 @TaskSwitchSimple f1 '' #zField
st0 @EndTask f3 '' #zField
st0 @PushWFArc f4 '' #zField
st0 @GridStep f5 '' #zField
st0 @PushWFArc f6 '' #zField
st0 @TkArc f2 '' #zField
>Proto st0 st0 stopProject #zField
st0 f0 outLink stopProject.ivp #txt
st0 f0 type test005ProjectManagement.Data #txt
st0 f0 inParamDecl '<> param;' #txt
st0 f0 actionDecl 'test005ProjectManagement.Data out;
' #txt
st0 f0 guid 147A01A7C367BA3C #txt
st0 f0 requestEnabled true #txt
st0 f0 triggerEnabled false #txt
st0 f0 callSignature stopProject() #txt
st0 f0 persist false #txt
st0 f0 taskData '#
#Mon Jan 19 17:46:56 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
st0 f0 caseData '#
#Mon Jan 19 17:46:56 ICT 2015
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
st0 f0 showInStartList 1 #txt
st0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
st0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>stopProject</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
st0 f0 @C|.responsibility Everybody #txt
st0 f0 89 81 30 30 16 0 #rect
st0 f0 @|StartRequestIcon #fIcon
st0 f0 -1|-1|-9671572 #nodeStyle
st0 f1 actionDecl 'test005ProjectManagement.Data out;
' #txt
st0 f1 actionTable 'out=in1;
' #txt
st0 f1 outTypes "test005ProjectManagement.Data" #txt
st0 f1 outLinks "TaskA.ivp" #txt
st0 f1 caseData '#
#Mon Jan 19 10:47:22 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=TEST Description
case.name=Stop Project state 1
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
st0 f1 taskData '#
#Mon Jan 19 10:47:22 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Projectmanager
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=Projectmanager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
' #txt
st0 f1 taskAction 'ivy.case.setName(engine.expandMacros("Stop Project state 1"));
ivy.case.setDescription(engine.expandMacros("TEST Description"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setAutoStartTask(false);
taskDef.setActivator("Projectmanager");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Projectmanager");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
st0 f1 type test005ProjectManagement.Data #txt
st0 f1 template "" #txt
st0 f1 89 201 30 30 15 0 #rect
st0 f1 @|TaskSwitchSimpleIcon #fIcon
st0 f1 -1|-1|-9671572 #nodeStyle
st0 f3 type test005ProjectManagement.Data #txt
st0 f3 89 337 30 30 16 0 #rect
st0 f3 @|EndIcon #fIcon
st0 f3 -1|-1|-9671572 #nodeStyle
st0 f4 expr data #txt
st0 f4 outCond ivp=="TaskA.ivp" #txt
st0 f4 104 231 104 337 #arcP
st0 f5 actionDecl 'test005ProjectManagement.Data out;
' #txt
st0 f5 actionTable 'out=in;
' #txt
st0 f5 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalPortalHome/caseDetails.ivp"));' #txt
st0 f5 type test005ProjectManagement.Data #txt
st0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case details link</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
st0 f5 86 132 36 24 20 -2 #rect
st0 f5 @|StepIcon #fIcon
st0 f6 expr out #txt
st0 f6 104 111 104 132 #arcP
st0 f2 expr out #txt
st0 f2 type test005ProjectManagement.Data #txt
st0 f2 var in1 #txt
st0 f2 104 156 104 201 #arcP
>Proto st0 .type test005ProjectManagement.Data #txt
>Proto st0 .processKind NORMAL #txt
>Proto st0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Project Manager
</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-52</swimlaneColor>
</elementInfo>
' #txt
>Proto st0 0 0 32 24 18 0 #rect
>Proto st0 @|BIcon #fIcon
st0 f1 out f4 tail #connect
st0 f4 head f3 mainIn #connect
st0 f0 mainOut f6 tail #connect
st0 f6 head f5 mainIn #connect
st0 f5 mainOut f2 tail #connect
st0 f2 head f1 in #connect
