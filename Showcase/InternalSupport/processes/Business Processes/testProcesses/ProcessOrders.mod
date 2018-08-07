[Ivy]
[>Created: Wed Feb 04 16:06:19 ICT 2015]
146AE7A4B2C20C13 3.17 #module
>Proto >Proto Collection #zClass
Ps0 ProcessOrders Big #zClass
Ps0 B #cInfo
Ps0 #process
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @StartRequest f0 '' #zField
Ps0 @TaskSwitchSimple f1 '' #zField
Ps0 @TkArc f2 '' #zField
Ps0 @TaskSwitchSimple f3 '' #zField
Ps0 @TkArc f4 '' #zField
Ps0 @TaskSwitchSimple f5 '' #zField
Ps0 @TkArc f6 '' #zField
Ps0 @EndTask f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 ProcessOrders #zField
Ps0 f0 outLink StartOrder.ivp #txt
Ps0 f0 type internaltest.Data #txt
Ps0 f0 inParamDecl '<> param;' #txt
Ps0 f0 actionDecl 'internaltest.Data out;
' #txt
Ps0 f0 guid 146AE7CB3DE44965 #txt
Ps0 f0 requestEnabled true #txt
Ps0 f0 triggerEnabled false #txt
Ps0 f0 callSignature StartOrder() #txt
Ps0 f0 persist false #txt
Ps0 f0 startName 'Process Orders' #txt
Ps0 f0 taskData '#
#Wed Feb 04 16:06:17 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Manager
TaskTriggered.NAM=START Process Orders
' #txt
Ps0 f0 caseData '#
#Wed Feb 04 16:06:17 ICT 2015
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
case.name=Start Order - START
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
Ps0 f0 showInStartList 1 #txt
Ps0 f0 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("Start Order - START"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("START Process Orders"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Manager");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>StartOrder.ivp</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 @C|.responsibility Manager #txt
Ps0 f0 97 81 30 30 16 0 #rect
Ps0 f0 @|StartRequestIcon #fIcon
Ps0 f0 -1|-1|-9671572 #nodeStyle
Ps0 f1 actionDecl 'internaltest.Data out;
' #txt
Ps0 f1 actionTable 'out=in1;
' #txt
Ps0 f1 outTypes "internaltest.Data" #txt
Ps0 f1 outLinks "TaskA.ivp" #txt
Ps0 f1 caseData '#
#Mon Jan 19 10:04:21 ICT 2015
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
customFields.decimal.1=230
customFields.decimal.2=21.4
customFields.decimal.3=3
customFields.varchar.1="Catalog"
customFields.varchar.2="Rabattnachlass"
customFields.varchar.3="Vorzugslieferant"
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
Ps0 f1 taskData '#
#Mon Jan 19 10:04:21 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=PrepareOrder TEST001
TaskA.PRI=2
TaskA.ROL=Purchasing
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.varchar.1="Field 1"
' #txt
Ps0 f1 taskAction 'ivy.case.setCustomVarCharField1("Catalog");
ivy.case.setCustomDecimalField1(230);
ivy.case.setCustomVarCharField2("Rabattnachlass");
ivy.case.setCustomDecimalField2(21.4);
ivy.case.setCustomVarCharField3("Vorzugslieferant");
ivy.case.setCustomDecimalField3(3);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("PrepareOrder TEST001"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Purchasing");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Field 1");
taskDefinitions.add(taskDef);
' #txt
Ps0 f1 type internaltest.Data #txt
Ps0 f1 template "" #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PrepareOrder</name>
        <nameStyle>12,7
</nameStyle>
        <desc>Bestellung wird vorbereitet
</desc>
    </language>
</elementInfo>
' #txt
Ps0 f1 97 137 30 30 15 0 #rect
Ps0 f1 @|TaskSwitchSimpleIcon #fIcon
Ps0 f1 -1|-1|-9671572 #nodeStyle
Ps0 f2 expr out #txt
Ps0 f2 type internaltest.Data #txt
Ps0 f2 var in1 #txt
Ps0 f2 112 111 112 137 #arcP
Ps0 f3 actionDecl 'internaltest.Data out;
' #txt
Ps0 f3 actionTable 'out=in1;
' #txt
Ps0 f3 outTypes "internaltest.Data" #txt
Ps0 f3 outLinks "TaskA.ivp" #txt
Ps0 f3 caseData '#
#Mon Jan 19 10:04:30 ICT 2015
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
Ps0 f3 taskData '#
#Mon Jan 19 10:04:30 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Approve Order TEST 002
TaskA.PRI=2
TaskA.ROL=Storage
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.3=0.00002
TaskA.customFields.varchar.1="Genehmigung"
' #txt
Ps0 f3 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Approve Order TEST 002"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Storage");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Genehmigung");
taskDef.setCustomDecimalField3(0.00002);
taskDefinitions.add(taskDef);
' #txt
Ps0 f3 type internaltest.Data #txt
Ps0 f3 template "" #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveOrder</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 281 225 30 30 15 0 #rect
Ps0 f3 @|TaskSwitchSimpleIcon #fIcon
Ps0 f3 -1|-1|-9671572 #nodeStyle
Ps0 f4 expr data #txt
Ps0 f4 outCond ivp=="TaskA.ivp" #txt
Ps0 f4 type internaltest.Data #txt
Ps0 f4 var in1 #txt
Ps0 f4 125 158 282 233 #arcP
Ps0 f5 actionDecl 'internaltest.Data out;
' #txt
Ps0 f5 actionTable 'out=in1;
' #txt
Ps0 f5 outTypes "internaltest.Data" #txt
Ps0 f5 outLinks "TaskA.ivp" #txt
Ps0 f5 caseData '#
#Mon Jan 19 10:04:38 ICT 2015
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
customFields.decimal.1=3
customFields.decimal.2=0.99999
customFields.varchar.1="CASE\: Submit Order"
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
Ps0 f5 taskData '#
#Mon Jan 19 10:04:38 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Submit Order TEST 003
TaskA.PRI=2
TaskA.ROL=Purchasing
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
' #txt
Ps0 f5 taskAction 'ivy.case.setCustomVarCharField1("CASE: Submit Order");
ivy.case.setCustomDecimalField1(3);
ivy.case.setCustomDecimalField2(0.99999);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Submit Order TEST 003"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Purchasing");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ps0 f5 type internaltest.Data #txt
Ps0 f5 template "" #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SubmitOrder</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f5 105 329 30 30 15 0 #rect
Ps0 f5 @|TaskSwitchSimpleIcon #fIcon
Ps0 f5 -1|-1|-9671572 #nodeStyle
Ps0 f6 expr data #txt
Ps0 f6 outCond ivp=="TaskA.ivp" #txt
Ps0 f6 type internaltest.Data #txt
Ps0 f6 var in1 #txt
Ps0 f6 283 247 132 336 #arcP
Ps0 f7 type internaltest.Data #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OrderSubmitted</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f7 105 409 30 30 16 0 #rect
Ps0 f7 @|EndIcon #fIcon
Ps0 f7 -1|-1|-9671572 #nodeStyle
Ps0 f8 expr data #txt
Ps0 f8 outCond ivp=="TaskA.ivp" #txt
Ps0 f8 120 359 120 409 #arcP
>Proto Ps0 .type internaltest.Data #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Requisitioner</swimlaneLabel>
        <swimlaneLabel>Cost Center Owner</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-10040065</swimlaneColor>
    <swimlaneColor>-26164</swimlaneColor>
</elementInfo>
' #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 in #connect
Ps0 f1 out f4 tail #connect
Ps0 f4 head f3 in #connect
Ps0 f3 out f6 tail #connect
Ps0 f6 head f5 in #connect
Ps0 f5 out f8 tail #connect
Ps0 f8 head f7 mainIn #connect
