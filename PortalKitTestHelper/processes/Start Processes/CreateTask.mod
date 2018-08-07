[Ivy]
[>Created: Wed Nov 18 15:05:18 ICT 2015]
14B2E44128370BDD 3.18 #module
>Proto >Proto Collection #zClass
Ck0 CreateTask Big #zClass
Ck0 B #cInfo
Ck0 #process
Ck0 @TextInP .resExport .resExport #zField
Ck0 @TextInP .type .type #zField
Ck0 @TextInP .processKind .processKind #zField
Ck0 @AnnotationInP-0n ai ai #zField
Ck0 @TextInP .xml .xml #zField
Ck0 @TextInP .responsibility .responsibility #zField
Ck0 @StartRequest f0 '' #zField
Ck0 @EndTask f1 '' #zField
Ck0 @TaskSwitch f3 '' #zField
Ck0 @PushWFArc f4 '' #zField
Ck0 @EndTask f6 '' #zField
Ck0 @EndTask f16 '' #zField
Ck0 @EndTask f20 '' #zField
Ck0 @EndTask f27 '' #zField
Ck0 @EndTask f30 '' #zField
Ck0 @PushWFArc f31 '' #zField
Ck0 @PushWFArc f5 '' #zField
Ck0 @PushWFArc f7 '' #zField
Ck0 @PushWFArc f8 '' #zField
Ck0 @PushWFArc f9 '' #zField
Ck0 @EndTask f10 '' #zField
Ck0 @PushWFArc f11 '' #zField
Ck0 @GridStep f32 '' #zField
Ck0 @PushWFArc f12 '' #zField
Ck0 @TkArc f2 '' #zField
Ck0 @StartRequest f13 '' #zField
Ck0 @PushWFArc f18 '' #zField
Ck0 @GridStep f17 '' #zField
Ck0 @Trigger f22 '' #zField
Ck0 @Alternative f21 '' #zField
Ck0 @PushWFArc f23 '' #zField
Ck0 @PushWFArc f19 '' #zField
Ck0 @EndTask f14 '' #zField
Ck0 @PushWFArc f15 '' #zField
Ck0 @GridStep f25 '' #zField
Ck0 @PushWFArc f26 '' #zField
Ck0 @PushWFArc f24 '' #zField
>Proto Ck0 Ck0 CreateTask #zField
Ck0 f0 outLink start.ivp #txt
Ck0 f0 type portalKit_test.Data #txt
Ck0 f0 inParamDecl '<> param;' #txt
Ck0 f0 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f0 guid 14AC3678ADAFD0B4 #txt
Ck0 f0 requestEnabled true #txt
Ck0 f0 triggerEnabled false #txt
Ck0 f0 callSignature start() #txt
Ck0 f0 persist false #txt
Ck0 f0 startName 'Internal Create tasks' #txt
Ck0 f0 taskData '#
#Fri Apr 24 16:39:43 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ck0 f0 caseData '#
#Fri Apr 24 16:39:43 ICT 2015
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
Ck0 f0 showInStartList 1 #txt
Ck0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ck0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f0 @C|.responsibility Everybody #txt
Ck0 f0 435 51 26 26 14 0 #rect
Ck0 f0 @|StartRequestIcon #fIcon
Ck0 f1 type portalKit_test.Data #txt
Ck0 f1 219 275 26 26 14 0 #rect
Ck0 f1 @|EndIcon #fIcon
Ck0 f3 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f3 actionTable 'out=in1;
' #txt
Ck0 f3 outTypes "portalKit_test.Data","portalKit_test.Data","portalKit_test.Data","portalKit_test.Data","portalKit_test.Data","portalKit_test.Data","portalKit_test.Data" #txt
Ck0 f3 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp","TaskD.ivp","TaskE.ivp","TaskF.ivp","TaskG.ivp" #txt
Ck0 f3 caseData 'case.name=Case 1' #txt
Ck0 f3 taskData 'TaskA.DEL=''P2h3m''
TaskA.DESC=made in viet nam by axon ivy
TaskA.EXP=''P2h3m''
TaskA.EXPRI=1
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=My task 1
TaskA.PRI=1
TaskA.ROL=ivy.session.getSessionUserName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5="OM/My Category/Rejected"
TaskB.DESC=Request \#12345 \: Please check the request data
TaskB.EXP=''P2h3m''
TaskB.EXPRI=1
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Your task
TaskB.PRI=2
TaskB.ROL=ivy.session.getSessionUserName()
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=3
TaskB.customFields.varchar.5="OM/My Category/Approval"
TaskC.DESC=Request \#4567 \: Please check the request data
TaskC.EXP=''P2h3m''
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Our Task
TaskC.PRI=3
TaskC.ROL=ivy.session.getSessionUserName()
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=3
TaskC.customFields.varchar.5="OM/My Category/Approval"
TaskD.DESC=Request \#89123 \: Please check the request data
TaskD.EXP=''P2h3m''
TaskD.EXPRI=2
TaskD.EXROL=Everybody
TaskD.EXTYPE=0
TaskD.NAM=My Task 2
TaskD.PRI=1
TaskD.ROL=ivy.session.getSessionUserName()
TaskD.SKIP_TASK_LIST=false
TaskD.TYPE=3
TaskE.DESC=Request \#00023 \: Please check the request data
TaskE.EXP=''P2h3m''
TaskE.EXPRI=2
TaskE.EXROL=Everybody
TaskE.EXTYPE=0
TaskE.NAM=Your Task
TaskE.PRI=2
TaskE.ROL=ivy.session.getSessionUserName()
TaskE.SKIP_TASK_LIST=false
TaskE.TYPE=3
TaskE.customFields.varchar.5="HR/My Category/Rejected"
TaskF.DESC=Request \#34343 \: Please check the request data
TaskF.EXP=''P2h3m''
TaskF.EXPRI=2
TaskF.EXROL=Everybody
TaskF.EXTYPE=0
TaskF.NAM=Our Task
TaskF.PRI=3
TaskF.ROL=ivy.session.getSessionUserName()
TaskF.SKIP_TASK_LIST=false
TaskF.TYPE=3
TaskF.customFields.varchar.5="HR/My Category/Sub Category/Rejected"
TaskG.DESC=Request \#32323 \: Please check the request data
TaskG.EXPRI=2
TaskG.EXROL=Everybody
TaskG.EXTYPE=0
TaskG.NAM=My Task 3
TaskG.PRI=2
TaskG.ROL=ivy.session.getSessionUserName()
TaskG.SKIP_TASK_LIST=false
TaskG.TYPE=3
TaskG.customFields.varchar.5="HR/My Category/Sub Category/Rejected"' #txt
Ck0 f3 taskAction 'ivy.case.setName(engine.expandMacros("Case 1"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("My task 1"));
taskDef.setDescription(engine.expandMacros("made in viet nam by axon ivy"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setDelayPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setCustomVarCharField5("OM/My Category/Rejected");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("Your task"));
taskDef.setDescription(engine.expandMacros("Request #12345 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setCustomVarCharField5("OM/My Category/Approval");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("Our Task"));
taskDef.setDescription(engine.expandMacros("Request #4567 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("OM/My Category/Approval");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskD.ivp");
taskDef.setName(engine.expandMacros("My Task 2"));
taskDef.setDescription(engine.expandMacros("Request #89123 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskE.ivp");
taskDef.setName(engine.expandMacros("Your Task"));
taskDef.setDescription(engine.expandMacros("Request #00023 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("HR/My Category/Rejected");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskF.ivp");
taskDef.setName(engine.expandMacros("Our Task"));
taskDef.setDescription(engine.expandMacros("Request #34343 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("HR/My Category/Sub Category/Rejected");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskG.ivp");
taskDef.setName(engine.expandMacros("My Task 3"));
taskDef.setDescription(engine.expandMacros("Request #32323 : Please check the request data"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("HR/My Category/Sub Category/Rejected");
taskDefinitions.add(taskDef);
' #txt
Ck0 f3 type portalKit_test.Data #txt
Ck0 f3 template "" #txt
Ck0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create task</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f3 434 154 28 28 -81 -22 #rect
Ck0 f3 @|TaskSwitchIcon #fIcon
Ck0 f4 expr data #txt
Ck0 f4 outCond ivp=="TaskA.ivp" #txt
Ck0 f4 439 173 243 281 #arcP
Ck0 f6 type portalKit_test.Data #txt
Ck0 f6 435 275 26 26 14 0 #rect
Ck0 f6 @|EndIcon #fIcon
Ck0 f16 type portalKit_test.Data #txt
Ck0 f16 531 275 26 26 14 0 #rect
Ck0 f16 @|EndIcon #fIcon
Ck0 f20 type portalKit_test.Data #txt
Ck0 f20 627 275 26 26 14 0 #rect
Ck0 f20 @|EndIcon #fIcon
Ck0 f27 type portalKit_test.Data #txt
Ck0 f27 723 275 26 26 14 0 #rect
Ck0 f27 @|EndIcon #fIcon
Ck0 f30 type portalKit_test.Data #txt
Ck0 f30 339 275 26 26 14 0 #rect
Ck0 f30 @|EndIcon #fIcon
Ck0 f31 expr data #txt
Ck0 f31 outCond ivp=="TaskB.ivp" #txt
Ck0 f31 442 176 360 277 #arcP
Ck0 f5 expr data #txt
Ck0 f5 outCond ivp=="TaskC.ivp" #txt
Ck0 f5 448 182 448 275 #arcP
Ck0 f7 expr data #txt
Ck0 f7 outCond ivp=="TaskD.ivp" #txt
Ck0 f7 454 176 535 277 #arcP
Ck0 f8 expr data #txt
Ck0 f8 outCond ivp=="TaskE.ivp" #txt
Ck0 f8 457 173 628 281 #arcP
Ck0 f9 expr data #txt
Ck0 f9 outCond ivp=="TaskF.ivp" #txt
Ck0 f9 458 172 723 283 #arcP
Ck0 f10 type portalKit_test.Data #txt
Ck0 f10 803 275 26 26 14 0 #rect
Ck0 f10 @|EndIcon #fIcon
Ck0 f11 expr data #txt
Ck0 f11 outCond ivp=="TaskG.ivp" #txt
Ck0 f11 459 171 803 283 #arcP
Ck0 f32 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f32 actionTable 'out=in;
' #txt
Ck0 f32 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Ck0 f32 type portalKit_test.Data #txt
Ck0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f32 430 100 36 24 20 -2 #rect
Ck0 f32 @|StepIcon #fIcon
Ck0 f12 expr out #txt
Ck0 f12 448 77 448 100 #arcP
Ck0 f2 expr out #txt
Ck0 f2 type portalKit_test.Data #txt
Ck0 f2 var in1 #txt
Ck0 f2 448 124 448 154 #arcP
Ck0 f13 outLink Create1000tasks.ivp #txt
Ck0 f13 type portalKit_test.Data #txt
Ck0 f13 inParamDecl '<> param;' #txt
Ck0 f13 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f13 guid 151188A493CF9667 #txt
Ck0 f13 requestEnabled true #txt
Ck0 f13 triggerEnabled false #txt
Ck0 f13 callSignature Create1000tasks() #txt
Ck0 f13 persist false #txt
Ck0 f13 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ck0 f13 showInStartList 1 #txt
Ck0 f13 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ck0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create 1000 tasks</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f13 @C|.responsibility Everybody #txt
Ck0 f13 1139 51 26 26 14 0 #rect
Ck0 f13 @|StartRequestIcon #fIcon
Ck0 f18 expr out #txt
Ck0 f18 1152 77 1152 116 #arcP
Ck0 f17 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f17 actionTable 'out=in;
' #txt
Ck0 f17 actionCode 'import ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils;
import ch.ivy.addon.portalkit.test.util.TaskUtils;


TaskConcurrencyUtils.setInProcess(true);
TaskUtils.destroyAllCase();
in.taskCounter = 1000;
' #txt
Ck0 f17 type portalKit_test.Data #txt
Ck0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clean all task and case
in.taskCounter = 1000</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f17 1134 116 36 24 20 -2 #rect
Ck0 f17 @|StepIcon #fIcon
Ck0 f22 type portalKit_test.Data #txt
Ck0 f22 processCall 'Business Processes/createARangeOfTasks:generateTasks(Number)' #txt
Ck0 f22 doCall true #txt
Ck0 f22 requestActionDecl '<java.lang.Number counter> param;
' #txt
Ck0 f22 requestMappingAction 'param.counter=in.taskCounter;
' #txt
Ck0 f22 responseActionDecl 'portalKit_test.Data out;
' #txt
Ck0 f22 responseMappingAction 'out=in;
' #txt
Ck0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createARangeOfTasks</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f22 1134 244 36 24 20 -2 #rect
Ck0 f22 @|TriggerIcon #fIcon
Ck0 f21 type portalKit_test.Data #txt
Ck0 f21 1138 178 28 28 14 0 #rect
Ck0 f21 @|AlternativeIcon #fIcon
Ck0 f23 expr out #txt
Ck0 f23 1152 140 1152 178 #arcP
Ck0 f19 expr in #txt
Ck0 f19 outCond 'in.taskCounter > 0' #txt
Ck0 f19 1152 206 1152 244 #arcP
Ck0 f14 type portalKit_test.Data #txt
Ck0 f14 1299 179 26 26 14 0 #rect
Ck0 f14 @|EndIcon #fIcon
Ck0 f15 expr in #txt
Ck0 f15 outCond 'in.taskCounter <= 0' #txt
Ck0 f15 1166 192 1299 192 #arcP
Ck0 f25 actionDecl 'portalKit_test.Data out;
' #txt
Ck0 f25 actionTable 'out=in;
' #txt
Ck0 f25 actionCode in.taskCounter--; #txt
Ck0 f25 type portalKit_test.Data #txt
Ck0 f25 1038 212 36 24 20 -2 #rect
Ck0 f25 @|StepIcon #fIcon
Ck0 f26 expr out #txt
Ck0 f26 1134 256 1056 236 #arcP
Ck0 f26 1 1056 256 #addKink
Ck0 f26 1 0.53125 0 0 #arcLabel
Ck0 f24 expr out #txt
Ck0 f24 1056 212 1138 192 #arcP
Ck0 f24 1 1056 192 #addKink
Ck0 f24 1 0.53125 0 0 #arcLabel
>Proto Ck0 .type portalKit_test.Data #txt
>Proto Ck0 .processKind NORMAL #txt
>Proto Ck0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ck0 0 0 32 24 18 0 #rect
>Proto Ck0 @|BIcon #fIcon
Ck0 f3 out f4 tail #connect
Ck0 f4 head f1 mainIn #connect
Ck0 f3 out f31 tail #connect
Ck0 f31 head f30 mainIn #connect
Ck0 f3 out f5 tail #connect
Ck0 f5 head f6 mainIn #connect
Ck0 f3 out f7 tail #connect
Ck0 f7 head f16 mainIn #connect
Ck0 f3 out f8 tail #connect
Ck0 f8 head f20 mainIn #connect
Ck0 f3 out f9 tail #connect
Ck0 f9 head f27 mainIn #connect
Ck0 f3 out f11 tail #connect
Ck0 f11 head f10 mainIn #connect
Ck0 f0 mainOut f12 tail #connect
Ck0 f12 head f32 mainIn #connect
Ck0 f32 mainOut f2 tail #connect
Ck0 f2 head f3 in #connect
Ck0 f13 mainOut f18 tail #connect
Ck0 f18 head f17 mainIn #connect
Ck0 f17 mainOut f23 tail #connect
Ck0 f23 head f21 in #connect
Ck0 f21 out f19 tail #connect
Ck0 f19 head f22 mainIn #connect
Ck0 f21 out f15 tail #connect
Ck0 f15 head f14 mainIn #connect
Ck0 f22 mainOut f26 tail #connect
Ck0 f26 head f25 mainIn #connect
Ck0 f25 mainOut f24 tail #connect
Ck0 f24 head f21 in #connect
