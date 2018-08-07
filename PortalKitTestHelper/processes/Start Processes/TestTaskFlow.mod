[Ivy]
[>Created: Fri Apr 24 17:00:46 ICT 2015]
14B77A0F6AC9C288 3.17 #module
>Proto >Proto Collection #zClass
Tw0 TestTaskFlow Big #zClass
Tw0 B #cInfo
Tw0 #process
Tw0 @TextInP .resExport .resExport #zField
Tw0 @TextInP .type .type #zField
Tw0 @TextInP .processKind .processKind #zField
Tw0 @AnnotationInP-0n ai ai #zField
Tw0 @TextInP .xml .xml #zField
Tw0 @TextInP .responsibility .responsibility #zField
Tw0 @StartRequest f0 '' #zField
Tw0 @TaskSwitch f6 '' #zField
Tw0 @RichDialog f8 '' #zField
Tw0 @RichDialog f9 '' #zField
Tw0 @RichDialog f10 '' #zField
Tw0 @PushWFArc f11 '' #zField
Tw0 @PushWFArc f12 '' #zField
Tw0 @PushWFArc f13 '' #zField
Tw0 @EndTask f14 '' #zField
Tw0 @PushWFArc f15 '' #zField
Tw0 @PushWFArc f16 '' #zField
Tw0 @PushWFArc f17 '' #zField
Tw0 @GridStep f1 '' #zField
Tw0 @PushWFArc f4 '' #zField
Tw0 @PushWFArc f2 '' #zField
Tw0 @GridStep f5 '' #zField
Tw0 @PushWFArc f7 '' #zField
Tw0 @TkArc f3 '' #zField
>Proto Tw0 Tw0 TestTaskFlow #zField
Tw0 f0 outLink createSubmitableTask.ivp #txt
Tw0 f0 type internaltest.Data #txt
Tw0 f0 inParamDecl '<> param;' #txt
Tw0 f0 actionDecl 'internaltest.Data out;
' #txt
Tw0 f0 guid 14B77A1238FF3781 #txt
Tw0 f0 requestEnabled true #txt
Tw0 f0 triggerEnabled false #txt
Tw0 f0 callSignature createSubmitableTask() #txt
Tw0 f0 persist false #txt
Tw0 f0 startName 'Internal create submitable task' #txt
Tw0 f0 taskData '#
#Fri Apr 24 17:00:46 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=My Task
' #txt
Tw0 f0 caseData '#
#Fri Apr 24 17:00:46 ICT 2015
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
case.name=my case
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
Tw0 f0 showInStartList 1 #txt
Tw0 f0 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("my case"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("My Task"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tw0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createSubmitableTask.ivp</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f0 @C|.responsibility Everybody #txt
Tw0 f0 139 51 26 26 14 0 #rect
Tw0 f0 @|StartRequestIcon #fIcon
Tw0 f6 actionDecl 'internaltest.Data out;
' #txt
Tw0 f6 actionTable 'out=in1;
' #txt
Tw0 f6 outTypes "internaltest.Data","internaltest.Data","internaltest.Data","internaltest.Data" #txt
Tw0 f6 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp","TaskD.ivp" #txt
Tw0 f6 caseData '#
#Thu Apr 02 10:35:07 ICT 2015
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
case.name=ttttt
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
Tw0 f6 taskData '#
#Thu Apr 02 10:35:07 ICT 2015
TaskA.DEL=''P2h3m''
TaskA.DESC=Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
TaskA.EXPRI=2
TaskA.EXTYPE=3
TaskA.NAM=My task
TaskA.PRI=1
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.varchar.5="mytask"
TaskB.DEL=''P2h3m''
TaskB.DESC=Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Your task
TaskB.PRI=2
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.5="yourtask"
TaskC.DEL=''P2h3m''
TaskC.DESC=Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Our task
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.5="ourtask"
TaskD.DEL=''P2h3m''
TaskD.EXPRI=2
TaskD.EXROL=Everybody
TaskD.EXTYPE=0
TaskD.PRI=1
TaskD.ROL=SYSTEM
TaskD.SKIP_TASK_LIST=false
TaskD.TYPE=0
' #txt
Tw0 f6 taskAction 'ivy.case.setName(engine.expandMacros("ttttt"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("My task"));
taskDef.setDescription(engine.expandMacros("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setDelayPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("mytask");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("Your task"));
taskDef.setDescription(engine.expandMacros("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setDelayPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("yourtask");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("Our task"));
taskDef.setDescription(engine.expandMacros("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setDelayPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5("ourtask");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskD.ivp");
taskDef.setAutoStartTask(false);
taskDef.setActivator("SYSTEM");
taskDef.setDelayPeriod(1000 * (''P2h3m'').toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Tw0 f6 type internaltest.Data #txt
Tw0 f6 template "" #txt
Tw0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f6 138 146 28 28 14 0 #rect
Tw0 f6 @|TaskSwitchIcon #fIcon
Tw0 f8 targetWindow NEW:card: #txt
Tw0 f8 targetDisplay TOP #txt
Tw0 f8 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskForm #txt
Tw0 f8 startMethod start() #txt
Tw0 f8 type internaltest.Data #txt
Tw0 f8 requestActionDecl '<> param;' #txt
Tw0 f8 responseActionDecl 'internaltest.Data out;
' #txt
Tw0 f8 responseMappingAction 'out=in;
' #txt
Tw0 f8 windowConfiguration '* ' #txt
Tw0 f8 isAsynch false #txt
Tw0 f8 isInnerRd false #txt
Tw0 f8 userContext '* ' #txt
Tw0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f8 46 220 36 24 20 -2 #rect
Tw0 f8 @|RichDialogIcon #fIcon
Tw0 f9 targetWindow NEW:card: #txt
Tw0 f9 targetDisplay TOP #txt
Tw0 f9 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskForm #txt
Tw0 f9 startMethod start() #txt
Tw0 f9 type internaltest.Data #txt
Tw0 f9 requestActionDecl '<> param;' #txt
Tw0 f9 responseActionDecl 'internaltest.Data out;
' #txt
Tw0 f9 responseMappingAction 'out=in;
' #txt
Tw0 f9 windowConfiguration '* ' #txt
Tw0 f9 isAsynch false #txt
Tw0 f9 isInnerRd false #txt
Tw0 f9 userContext '* ' #txt
Tw0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f9 134 220 36 24 20 -2 #rect
Tw0 f9 @|RichDialogIcon #fIcon
Tw0 f10 targetWindow NEW:card: #txt
Tw0 f10 targetDisplay TOP #txt
Tw0 f10 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.tasks.TaskForm #txt
Tw0 f10 startMethod start() #txt
Tw0 f10 type internaltest.Data #txt
Tw0 f10 requestActionDecl '<> param;' #txt
Tw0 f10 responseActionDecl 'internaltest.Data out;
' #txt
Tw0 f10 responseMappingAction 'out=in;
' #txt
Tw0 f10 windowConfiguration '* ' #txt
Tw0 f10 isAsynch false #txt
Tw0 f10 isInnerRd false #txt
Tw0 f10 userContext '* ' #txt
Tw0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f10 230 220 36 24 20 -2 #rect
Tw0 f10 @|RichDialogIcon #fIcon
Tw0 f11 expr data #txt
Tw0 f11 outCond ivp=="TaskA.ivp" #txt
Tw0 f11 138 160 64 220 #arcP
Tw0 f11 1 64 160 #addKink
Tw0 f11 0 0.8520049076545005 0 0 #arcLabel
Tw0 f12 expr data #txt
Tw0 f12 outCond ivp=="TaskB.ivp" #txt
Tw0 f12 152 174 152 220 #arcP
Tw0 f13 expr data #txt
Tw0 f13 outCond ivp=="TaskC.ivp" #txt
Tw0 f13 166 160 248 220 #arcP
Tw0 f13 1 248 160 #addKink
Tw0 f13 0 0.8520049076545005 0 0 #arcLabel
Tw0 f14 type internaltest.Data #txt
Tw0 f14 139 307 26 26 14 0 #rect
Tw0 f14 @|EndIcon #fIcon
Tw0 f15 expr out #txt
Tw0 f15 152 244 152 307 #arcP
Tw0 f16 expr out #txt
Tw0 f16 64 244 139 320 #arcP
Tw0 f16 1 64 320 #addKink
Tw0 f16 1 0.05415670111803739 0 0 #arcLabel
Tw0 f17 expr out #txt
Tw0 f17 248 244 165 320 #arcP
Tw0 f17 1 248 320 #addKink
Tw0 f17 1 0.05922676379839703 0 0 #arcLabel
Tw0 f1 actionDecl 'internaltest.Data out;
' #txt
Tw0 f1 actionTable 'out=in;
' #txt
Tw0 f1 actionCode 'import ch.ivy.addon.portalkit.test.util.CaseUtils;
CaseUtils.createSystemNote(ivy.case,"a system note");
' #txt
Tw0 f1 type internaltest.Data #txt
Tw0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create
system note</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f1 326 212 36 24 20 -2 #rect
Tw0 f1 @|StepIcon #fIcon
Tw0 f4 expr out #txt
Tw0 f4 344 236 165 320 #arcP
Tw0 f4 1 344 312 #addKink
Tw0 f4 2 336 320 #addKink
Tw0 f4 2 0.24705142786823092 0 0 #arcLabel
Tw0 f2 expr data #txt
Tw0 f2 outCond ivp=="TaskD.ivp" #txt
Tw0 f2 166 160 344 212 #arcP
Tw0 f2 1 336 160 #addKink
Tw0 f2 2 344 168 #addKink
Tw0 f2 0 0.6758103840563138 0 0 #arcLabel
Tw0 f5 actionDecl 'internaltest.Data out;
' #txt
Tw0 f5 actionTable 'out=in;
' #txt
Tw0 f5 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tw0 f5 type internaltest.Data #txt
Tw0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tw0 f5 134 92 36 24 20 -2 #rect
Tw0 f5 @|StepIcon #fIcon
Tw0 f7 expr out #txt
Tw0 f7 152 77 152 92 #arcP
Tw0 f3 expr out #txt
Tw0 f3 type internaltest.Data #txt
Tw0 f3 var in1 #txt
Tw0 f3 152 116 152 146 #arcP
>Proto Tw0 .type internaltest.Data #txt
>Proto Tw0 .processKind NORMAL #txt
>Proto Tw0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>504</swimlaneSize>
    <swimlaneColor>-6710785</swimlaneColor>
</elementInfo>
' #txt
>Proto Tw0 0 0 32 24 18 0 #rect
>Proto Tw0 @|BIcon #fIcon
Tw0 f6 out f11 tail #connect
Tw0 f11 head f8 mainIn #connect
Tw0 f6 out f12 tail #connect
Tw0 f12 head f9 mainIn #connect
Tw0 f6 out f13 tail #connect
Tw0 f13 head f10 mainIn #connect
Tw0 f9 mainOut f15 tail #connect
Tw0 f15 head f14 mainIn #connect
Tw0 f8 mainOut f16 tail #connect
Tw0 f16 head f14 mainIn #connect
Tw0 f10 mainOut f17 tail #connect
Tw0 f17 head f14 mainIn #connect
Tw0 f1 mainOut f4 tail #connect
Tw0 f4 head f14 mainIn #connect
Tw0 f6 out f2 tail #connect
Tw0 f2 head f1 mainIn #connect
Tw0 f0 mainOut f7 tail #connect
Tw0 f7 head f5 mainIn #connect
Tw0 f5 mainOut f3 tail #connect
Tw0 f3 head f6 in #connect
