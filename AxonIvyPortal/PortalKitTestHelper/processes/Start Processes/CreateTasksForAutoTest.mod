[Ivy]
[>Created: Wed Oct 28 14:01:33 ICT 2015]
150A810BC71F629F 3.17 #module
>Proto >Proto Collection #zClass
Ck0 CreateTasksForAutoTest Big #zClass
Ck0 B #cInfo
Ck0 #process
Ck0 @TextInP .resExport .resExport #zField
Ck0 @TextInP .type .type #zField
Ck0 @TextInP .processKind .processKind #zField
Ck0 @AnnotationInP-0n ai ai #zField
Ck0 @MessageFlowInP-0n messageIn messageIn #zField
Ck0 @MessageFlowOutP-0n messageOut messageOut #zField
Ck0 @TextInP .xml .xml #zField
Ck0 @TextInP .responsibility .responsibility #zField
Ck0 @GridStep f16 '' #zField
Ck0 @StartRequest f5 '' #zField
Ck0 @PushWFArc f21 '' #zField
Ck0 @StartRequest f9 '' #zField
Ck0 @EndTask f10 '' #zField
Ck0 @GridStep f12 '' #zField
Ck0 @PushWFArc f13 '' #zField
Ck0 @PushWFArc f11 '' #zField
Ck0 @TaskSwitch f0 '' #zField
Ck0 @EndTask f1 '' #zField
Ck0 @EndTask f2 '' #zField
Ck0 @EndTask f3 '' #zField
Ck0 @TkArc f4 '' #zField
Ck0 @PushWFArc f6 '' #zField
Ck0 @PushWFArc f7 '' #zField
Ck0 @PushWFArc f8 '' #zField
>Proto Ck0 Ck0 CreateTasksForAutoTest #zField
Ck0 f16 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f16 actionTable 'out=in;
' #txt
Ck0 f16 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalSupportPortalHome/caseDetails.ivp"));
CaseUtils.setCaseMainContactFolderId(ivy.case,"http://www.axonactive.vn/");

' #txt
Ck0 f16 type portalKit_test.Data #txt
Ck0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add case details link
add contactfolder id link</name>
        <nameStyle>22,7
25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f16 174 84 36 24 20 -2 #rect
Ck0 f16 @|StepIcon #fIcon
Ck0 f5 outLink CreateTestTasks.ivp #txt
Ck0 f5 type internaltest.Data #txt
Ck0 f5 inParamDecl '<> param;' #txt
Ck0 f5 actionDecl 'internaltest.Data out;
' #txt
Ck0 f5 guid 150A810E2282F411 #txt
Ck0 f5 requestEnabled true #txt
Ck0 f5 triggerEnabled false #txt
Ck0 f5 callSignature CreateTestTasks() #txt
Ck0 f5 persist false #txt
Ck0 f5 startName 'Create tasks for testing' #txt
Ck0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ck0 f5 caseData '#
#Fri Sep 04 18:03:03 ICT 2015
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
process.code=pubRequested
process.name=Publication Requested
processCategory.code=pubRequested
processCategory.name=Publication Requested
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ck0 f5 showInStartList 1 #txt
Ck0 f5 taskAndCaseSetupAction 'ivy.case.setProcessCategory(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
ivy.case.setProcess(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ck0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateTestTasks.ivp</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f5 @C|.responsibility Everybody #txt
Ck0 f5 179 19 26 26 14 0 #rect
Ck0 f5 @|StartRequestIcon #fIcon
Ck0 f21 expr out #txt
Ck0 f21 192 45 192 84 #arcP
Ck0 f9 outLink start.ivp #txt
Ck0 f9 type portalKit_test.Data #txt
Ck0 f9 inParamDecl '<> param;' #txt
Ck0 f9 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f9 guid 150ACA6FE3368472 #txt
Ck0 f9 requestEnabled true #txt
Ck0 f9 triggerEnabled false #txt
Ck0 f9 callSignature start() #txt
Ck0 f9 persist false #txt
Ck0 f9 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ck0 f9 showInStartList 1 #txt
Ck0 f9 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ck0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateResumeTask</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f9 @C|.responsibility Everybody #txt
Ck0 f9 467 19 26 26 14 0 #rect
Ck0 f9 @|StartRequestIcon #fIcon
Ck0 f10 type portalKit_test.Data #txt
Ck0 f10 467 307 26 26 14 0 #rect
Ck0 f10 @|EndIcon #fIcon
Ck0 f12 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f12 actionTable 'out=in;
' #txt
Ck0 f12 actionCode 'import ch.ivy.addon.portalkit.test.util.TaskUtils;

ivy.log.error("AAAAAAAAAAA");
TaskUtils.resumeFirstTask();
ivy.log.error("BBBBBBBBBB");



' #txt
Ck0 f12 type portalKit_test.Data #txt
Ck0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateResumeTask</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f12 462 148 36 24 20 -2 #rect
Ck0 f12 @|StepIcon #fIcon
Ck0 f13 expr out #txt
Ck0 f13 480 45 480 148 #arcP
Ck0 f11 expr out #txt
Ck0 f11 480 172 480 307 #arcP
Ck0 f0 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f0 actionTable 'out=in1;
' #txt
Ck0 f0 outTypes "portalKit_test.Data","portalKit_test.Data","portalKit_test.Data" #txt
Ck0 f0 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Ck0 f0 taskData 'TaskA.EXP=''P2h3m''
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Suport Ticket For Test
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.EXP=''P2h3m''
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Leave Request For Test
TaskB.PRI=2
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskC.EXP=''P2h3m''
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Approve Request For Test
TaskC.PRI=2
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0' #txt
Ck0 f0 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Suport Ticket For Test"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("Leave Request For Test"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("Approve Request For Test"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ck0 f0 type portalKit_test.Data #txt
Ck0 f0 template "" #txt
Ck0 f0 178 146 28 28 14 0 #rect
Ck0 f0 @|TaskSwitchIcon #fIcon
Ck0 f1 type portalKit_test.Data #txt
Ck0 f1 115 243 26 26 14 0 #rect
Ck0 f1 @|EndIcon #fIcon
Ck0 f2 type portalKit_test.Data #txt
Ck0 f2 179 243 26 26 14 0 #rect
Ck0 f2 @|EndIcon #fIcon
Ck0 f3 type portalKit_test.Data #txt
Ck0 f3 243 243 26 26 14 0 #rect
Ck0 f3 @|EndIcon #fIcon
Ck0 f4 expr out #txt
Ck0 f4 type portalKit_test.Data #txt
Ck0 f4 var in1 #txt
Ck0 f4 192 108 192 146 #arcP
Ck0 f6 expr data #txt
Ck0 f6 outCond ivp=="TaskA.ivp" #txt
Ck0 f6 186 168 135 245 #arcP
Ck0 f7 expr data #txt
Ck0 f7 outCond ivp=="TaskB.ivp" #txt
Ck0 f7 192 174 192 243 #arcP
Ck0 f8 expr data #txt
Ck0 f8 outCond ivp=="TaskC.ivp" #txt
Ck0 f8 198 168 248 245 #arcP
>Proto Ck0 .type portalKit_test.Data #txt
>Proto Ck0 .processKind NORMAL #txt
>Proto Ck0 0 0 32 24 18 0 #rect
>Proto Ck0 @|BIcon #fIcon
Ck0 f5 mainOut f21 tail #connect
Ck0 f21 head f16 mainIn #connect
Ck0 f9 mainOut f13 tail #connect
Ck0 f13 head f12 mainIn #connect
Ck0 f12 mainOut f11 tail #connect
Ck0 f11 head f10 mainIn #connect
Ck0 f16 mainOut f4 tail #connect
Ck0 f4 head f0 in #connect
Ck0 f0 out f6 tail #connect
Ck0 f6 head f1 mainIn #connect
Ck0 f0 out f7 tail #connect
Ck0 f7 head f2 mainIn #connect
Ck0 f0 out f8 tail #connect
Ck0 f8 head f3 mainIn #connect
