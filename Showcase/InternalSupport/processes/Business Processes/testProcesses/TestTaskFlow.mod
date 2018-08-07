[Ivy]
[>Created: Thu Dec 15 11:12:03 ICT 2016]
14B2FC03D2E87141 3.18 #module
>Proto >Proto Collection #zClass
Tt0 TestTaskFlow Big #zClass
Tt0 B #cInfo
Tt0 #process
Tt0 @TextInP .resExport .resExport #zField
Tt0 @TextInP .type .type #zField
Tt0 @TextInP .processKind .processKind #zField
Tt0 @AnnotationInP-0n ai ai #zField
Tt0 @TextInP .xml .xml #zField
Tt0 @TextInP .responsibility .responsibility #zField
Tt0 @StartRequest f5 '' #zField
Tt0 @TaskSwitch f7 '' #zField
Tt0 @RichDialog f9 '' #zField
Tt0 @RichDialog f11 '' #zField
Tt0 @RichDialog f12 '' #zField
Tt0 @PushWFArc f13 '' #zField
Tt0 @PushWFArc f14 '' #zField
Tt0 @PushWFArc f15 '' #zField
Tt0 @StartRequest f0 '' #zField
Tt0 @EndTask f22 '' #zField
Tt0 @TaskSwitch f23 '' #zField
Tt0 @PushWFArc f25 '' #zField
Tt0 @GridStep f1 '' #zField
Tt0 @PushWFArc f2 '' #zField
Tt0 @TkArc f3 '' #zField
Tt0 @EndRequest f20 '' #zField
Tt0 @PushWFArc f18 '' #zField
Tt0 @PushWFArc f17 '' #zField
Tt0 @PushWFArc f19 '' #zField
Tt0 @TkArc f4 '' #zField
Tt0 @TaskSwitchSimple f8 '' #zField
Tt0 @EndTask f10 '' #zField
Tt0 @PushWFArc f21 '' #zField
Tt0 @StartRequest f6 '' #zField
Tt0 @TkArc f16 '' #zField
Tt0 @RichDialog f24 '' #zField
Tt0 @RichDialog f26 '' #zField
Tt0 @StartRequest f27 '' #zField
Tt0 @TaskSwitch f28 '' #zField
Tt0 @RichDialog f29 '' #zField
Tt0 @EndRequest f30 '' #zField
Tt0 @PushWFArc f31 '' #zField
Tt0 @PushWFArc f32 '' #zField
Tt0 @PushWFArc f33 '' #zField
Tt0 @PushWFArc f34 '' #zField
Tt0 @PushWFArc f35 '' #zField
Tt0 @PushWFArc f36 '' #zField
Tt0 @TkArc f37 '' #zField
>Proto Tt0 Tt0 TestTaskFlow #zField
Tt0 f5 outLink CategoriedLeaveRequest.ivp #txt
Tt0 f5 type internaltest.Data #txt
Tt0 f5 inParamDecl '<> param;' #txt
Tt0 f5 actionDecl 'internaltest.Data out;
' #txt
Tt0 f5 guid 14B3A6DCB767D79A #txt
Tt0 f5 requestEnabled true #txt
Tt0 f5 triggerEnabled false #txt
Tt0 f5 callSignature CategoriedLeaveRequest() #txt
Tt0 f5 persist false #txt
Tt0 f5 startName 'Categoried Leave Request' #txt
Tt0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f5 caseData 'case.description=Leave Request Description
case.name=Leave Request
customFields.varchar.1="Leave Request CustomVarCharField1"
customFields.varchar.2="Leave Request CustomVarCharField2"
customFields.varchar.3="Leave Request CustomVarCharField3"
customFields.varchar.4="Leave Request CustomVarCharField4"
customFields.varchar.5="Leave Request CustomVarCharField5"
process.code=pubRequested
process.name=Publication Requested
processCategory.code=pubRequested
processCategory.name=Publication Requested' #txt
Tt0 f5 showInStartList 1 #txt
Tt0 f5 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("Leave Request"));
ivy.case.setDescription(engine.expandMacros("Leave Request Description"));
ivy.case.setProcessCategory(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
ivy.case.setProcess(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
ivy.case.setCustomVarCharField1("Leave Request CustomVarCharField1");
ivy.case.setCustomVarCharField2("Leave Request CustomVarCharField2");
ivy.case.setCustomVarCharField3("Leave Request CustomVarCharField3");
ivy.case.setCustomVarCharField4("Leave Request CustomVarCharField4");
ivy.case.setCustomVarCharField5("Leave Request CustomVarCharField5");
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CategoriedLeaveRequest.ivp</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f5 @C|.responsibility Everybody #txt
Tt0 f5 187 51 26 26 14 0 #rect
Tt0 f5 @|StartRequestIcon #fIcon
Tt0 f7 actionDecl 'internaltest.Data out;
' #txt
Tt0 f7 actionTable 'out=in1;
' #txt
Tt0 f7 outTypes "internaltest.Data","internaltest.Data","internaltest.Data" #txt
Tt0 f7 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Tt0 f7 taskData 'TaskA.DESC=Annual Leave Request Description
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Annual Leave Request
TaskA.PRI=2
TaskA.ROL="demo"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.1="Annual CustomVarCharField1"
TaskA.customFields.varchar.2="Annual CustomVarCharField2"
TaskA.customFields.varchar.3="Annual CustomVarCharField3"
TaskA.customFields.varchar.4="Annual CustomVarCharField4"
TaskA.customFields.varchar.5="Annual Leave"
TaskB.DESC=Sick Leave Request Description
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Sick Leave Request
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.1="Sick CustomVarCharField1"
TaskB.customFields.varchar.2="Sick CustomVarCharField2"
TaskB.customFields.varchar.3="Sick CustomVarCharField3"
TaskB.customFields.varchar.4="Sick CustomVarCharField4"
TaskB.customFields.varchar.5="Other Leave/Sick/Long"
TaskC.DESC=Maternity Leave Request Description
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Maternity Leave Request
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.1="Maternity CustomVarCharField1"
TaskC.customFields.varchar.2="Maternity CustomVarCharField2"
TaskC.customFields.varchar.3="Maternity CustomVarCharField3"
TaskC.customFields.varchar.4="Maternity CustomVarCharField4"
TaskC.customFields.varchar.5="Other Leave/Maternity"' #txt
Tt0 f7 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Annual Leave Request"));
taskDef.setDescription(engine.expandMacros("Annual Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + "demo");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration("3H")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Annual CustomVarCharField1");
taskDef.setCustomVarCharField2("Annual CustomVarCharField2");
taskDef.setCustomVarCharField3("Annual CustomVarCharField3");
taskDef.setCustomVarCharField4("Annual CustomVarCharField4");
taskDef.setCustomVarCharField5("Annual Leave");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("Sick Leave Request"));
taskDef.setDescription(engine.expandMacros("Sick Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryPeriod(1000 * (new Duration("1D")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Sick CustomVarCharField1");
taskDef.setCustomVarCharField2("Sick CustomVarCharField2");
taskDef.setCustomVarCharField3("Sick CustomVarCharField3");
taskDef.setCustomVarCharField4("Sick CustomVarCharField4");
taskDef.setCustomVarCharField5("Other Leave/Sick/Long");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("Maternity Leave Request"));
taskDef.setDescription(engine.expandMacros("Maternity Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryPeriod(1000 * (new Duration("2D")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Maternity CustomVarCharField1");
taskDef.setCustomVarCharField2("Maternity CustomVarCharField2");
taskDef.setCustomVarCharField3("Maternity CustomVarCharField3");
taskDef.setCustomVarCharField4("Maternity CustomVarCharField4");
taskDef.setCustomVarCharField5("Other Leave/Maternity");
taskDefinitions.add(taskDef);
' #txt
Tt0 f7 type internaltest.Data #txt
Tt0 f7 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f7 186 178 28 28 9 -21 #rect
Tt0 f7 @|TaskSwitchIcon #fIcon
Tt0 f9 targetWindow NEW:card: #txt
Tt0 f9 targetDisplay TOP #txt
Tt0 f9 richDialogId internaltest.TaskForm #txt
Tt0 f9 startMethod start() #txt
Tt0 f9 type internaltest.Data #txt
Tt0 f9 requestActionDecl '<> param;' #txt
Tt0 f9 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f9 responseMappingAction 'out=in;
' #txt
Tt0 f9 windowConfiguration '* ' #txt
Tt0 f9 isAsynch false #txt
Tt0 f9 isInnerRd false #txt
Tt0 f9 userContext '* ' #txt
Tt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>6,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f9 54 284 36 24 20 -2 #rect
Tt0 f9 @|RichDialogIcon #fIcon
Tt0 f11 targetWindow NEW:card: #txt
Tt0 f11 targetDisplay TOP #txt
Tt0 f11 richDialogId internaltest.TaskForm #txt
Tt0 f11 startMethod start() #txt
Tt0 f11 type internaltest.Data #txt
Tt0 f11 requestActionDecl '<> param;' #txt
Tt0 f11 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f11 responseMappingAction 'out=in;
' #txt
Tt0 f11 windowConfiguration '* ' #txt
Tt0 f11 isAsynch false #txt
Tt0 f11 isInnerRd false #txt
Tt0 f11 userContext '* ' #txt
Tt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f11 182 284 36 24 20 -2 #rect
Tt0 f11 @|RichDialogIcon #fIcon
Tt0 f12 targetWindow NEW:card: #txt
Tt0 f12 targetDisplay TOP #txt
Tt0 f12 richDialogId internaltest.TaskForm #txt
Tt0 f12 startMethod start() #txt
Tt0 f12 type internaltest.Data #txt
Tt0 f12 requestActionDecl '<> param;' #txt
Tt0 f12 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f12 responseMappingAction 'out=in;
' #txt
Tt0 f12 windowConfiguration '* ' #txt
Tt0 f12 isAsynch false #txt
Tt0 f12 isInnerRd false #txt
Tt0 f12 userContext '* ' #txt
Tt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f12 318 284 36 24 20 -2 #rect
Tt0 f12 @|RichDialogIcon #fIcon
Tt0 f13 expr data #txt
Tt0 f13 outCond ivp=="TaskA.ivp" #txt
Tt0 f13 186 192 72 284 #arcP
Tt0 f13 1 72 192 #addKink
Tt0 f13 0 0.8879104383411323 0 0 #arcLabel
Tt0 f14 expr data #txt
Tt0 f14 outCond ivp=="TaskB.ivp" #txt
Tt0 f14 200 206 200 284 #arcP
Tt0 f15 expr data #txt
Tt0 f15 outCond ivp=="TaskC.ivp" #txt
Tt0 f15 214 192 336 284 #arcP
Tt0 f15 1 336 192 #addKink
Tt0 f15 0 0.8643692094953389 0 0 #arcLabel
Tt0 f0 outLink CreateSupportTicket.ivp #txt
Tt0 f0 type internaltest.Data #txt
Tt0 f0 inParamDecl '<> param;' #txt
Tt0 f0 actionDecl 'internaltest.Data out;
' #txt
Tt0 f0 guid 14D41201484D00B4 #txt
Tt0 f0 requestEnabled true #txt
Tt0 f0 triggerEnabled false #txt
Tt0 f0 callSignature CreateSupportTicket() #txt
Tt0 f0 persist false #txt
Tt0 f0 startName 'Create Support Ticket' #txt
Tt0 f0 taskData '#
#Mon May 11 11:02:05 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tt0 f0 caseData '#
#Mon May 11 11:02:05 ICT 2015
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
Tt0 f0 showInStartList 1 #txt
Tt0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Support Ticket</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f0 @C|.responsibility Everybody #txt
Tt0 f0 531 51 26 26 25 -2 #rect
Tt0 f0 @|StartRequestIcon #fIcon
Tt0 f22 type internaltest.Data #txt
Tt0 f22 531 403 26 26 14 0 #rect
Tt0 f22 @|EndIcon #fIcon
Tt0 f23 actionDecl 'internaltest.Data out;
' #txt
Tt0 f23 actionTable 'out=in1;
' #txt
Tt0 f23 outTypes "internaltest.Data" #txt
Tt0 f23 outLinks "TaskA.ivp" #txt
Tt0 f23 caseData 'case.name=SupportTicket
process.code=Ticket Process Code
process.name=Ticket Process Name
processCategory.code=Ticket Category Code
processCategory.name=Ticket Category Name' #txt
Tt0 f23 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SupportTicket
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUserName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3' #txt
Tt0 f23 taskAction 'ivy.case.setName(engine.expandMacros("SupportTicket"));
ivy.case.setProcessCategory(engine.expandMacros("Ticket Category Code"), engine.expandMacros("Ticket Category Name"));
ivy.case.setProcess(engine.expandMacros("Ticket Process Code"), engine.expandMacros("Ticket Process Name"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("SupportTicket"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUserName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Tt0 f23 type internaltest.Data #txt
Tt0 f23 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SupportTicket</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f23 530 274 28 28 21 -3 #rect
Tt0 f23 @|TaskSwitchIcon #fIcon
Tt0 f25 expr data #txt
Tt0 f25 outCond ivp=="TaskA.ivp" #txt
Tt0 f25 544 302 544 403 #arcP
Tt0 f1 actionDecl 'internaltest.Data out;
' #txt
Tt0 f1 actionTable 'out=in;
' #txt
Tt0 f1 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalSupportPortalHome/caseDetails.ivp"));
CaseUtils.setCaseMainContactFolderId(ivy.case,"http://www.axonactive.vn/");

in.expiredDate = new Duration(1,0,0,0,0,0);' #txt
Tt0 f1 type internaltest.Data #txt
Tt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add case details link
add contactfolder id link</name>
        <nameStyle>47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f1 526 180 36 24 20 -2 #rect
Tt0 f1 @|StepIcon #fIcon
Tt0 f2 expr out #txt
Tt0 f2 544 77 544 180 #arcP
Tt0 f3 expr out #txt
Tt0 f3 type internaltest.Data #txt
Tt0 f3 var in1 #txt
Tt0 f3 template /ProcessPages/portalHome.ivc #txt
Tt0 f3 544 204 544 274 #arcP
Tt0 f20 type internaltest.Data #txt
Tt0 f20 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f20 187 451 26 26 14 0 #rect
Tt0 f20 @|EndRequestIcon #fIcon
Tt0 f18 expr out #txt
Tt0 f18 72 308 187 464 #arcP
Tt0 f18 1 72 464 #addKink
Tt0 f18 1 0.032798207405834785 0 0 #arcLabel
Tt0 f17 expr out #txt
Tt0 f17 336 308 213 464 #arcP
Tt0 f17 1 336 464 #addKink
Tt0 f17 1 0.03785109119051796 0 0 #arcLabel
Tt0 f19 expr out #txt
Tt0 f19 200 308 200 451 #arcP
Tt0 f4 expr out #txt
Tt0 f4 type internaltest.Data #txt
Tt0 f4 var in1 #txt
Tt0 f4 template /ProcessPages/portalHome.ivc #txt
Tt0 f4 200 77 200 178 #arcP
Tt0 f8 actionDecl 'internaltest.Data out;
' #txt
Tt0 f8 actionTable 'out=in1;
' #txt
Tt0 f8 outTypes "internaltest.Data" #txt
Tt0 f8 outLinks "TaskA.ivp" #txt
Tt0 f8 caseData 'case.name=\u00D6sterreich Resource with ID 1212' #txt
Tt0 f8 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=\u00D6sterreich Resource with ID 1212
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Tt0 f8 taskAction 'ivy.case.setName(engine.expandMacros("Österreich Resource with ID 1212"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Österreich Resource with ID 1212"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Tt0 f8 type internaltest.Data #txt
Tt0 f8 template "" #txt
Tt0 f8 851 155 26 26 13 0 #rect
Tt0 f8 @|TaskSwitchSimpleIcon #fIcon
Tt0 f10 type internaltest.Data #txt
Tt0 f10 851 243 26 26 14 0 #rect
Tt0 f10 @|EndIcon #fIcon
Tt0 f21 expr data #txt
Tt0 f21 outCond ivp=="TaskA.ivp" #txt
Tt0 f21 864 181 864 243 #arcP
Tt0 f6 outLink CreateTaskWithSpecialCharacter.ivp #txt
Tt0 f6 type internaltest.Data #txt
Tt0 f6 inParamDecl '<> param;' #txt
Tt0 f6 actionDecl 'internaltest.Data out;
' #txt
Tt0 f6 guid 154EC137231EA5FE #txt
Tt0 f6 requestEnabled true #txt
Tt0 f6 triggerEnabled false #txt
Tt0 f6 callSignature CreateTaskWithSpecialCharacter() #txt
Tt0 f6 persist false #txt
Tt0 f6 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f6 showInStartList 1 #txt
Tt0 f6 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateTaskWithSpecialCharacter.ivp</name>
    </language>
</elementInfo>
' #txt
Tt0 f6 @C|.responsibility Everybody #txt
Tt0 f6 851 51 26 26 14 0 #rect
Tt0 f6 @|StartRequestIcon #fIcon
Tt0 f16 expr out #txt
Tt0 f16 type internaltest.Data #txt
Tt0 f16 var in1 #txt
Tt0 f16 864 77 864 155 #arcP
Tt0 f24 targetWindow NEW:card: #txt
Tt0 f24 targetDisplay TOP #txt
Tt0 f24 richDialogId internaltest.TaskForm #txt
Tt0 f24 startMethod start() #txt
Tt0 f24 type internaltest.Data #txt
Tt0 f24 requestActionDecl '<> param;' #txt
Tt0 f24 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f24 responseMappingAction 'out=in;
' #txt
Tt0 f24 windowConfiguration '* ' #txt
Tt0 f24 isAsynch false #txt
Tt0 f24 isInnerRd false #txt
Tt0 f24 userContext '* ' #txt
Tt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f24 1286 308 36 24 20 -2 #rect
Tt0 f24 @|RichDialogIcon #fIcon
Tt0 f26 targetWindow NEW:card: #txt
Tt0 f26 targetDisplay TOP #txt
Tt0 f26 richDialogId internaltest.TaskForm #txt
Tt0 f26 startMethod start() #txt
Tt0 f26 type internaltest.Data #txt
Tt0 f26 requestActionDecl '<> param;' #txt
Tt0 f26 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f26 responseMappingAction 'out=in;
' #txt
Tt0 f26 windowConfiguration '* ' #txt
Tt0 f26 isAsynch false #txt
Tt0 f26 isInnerRd false #txt
Tt0 f26 userContext '* ' #txt
Tt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f26 1422 308 36 24 20 -2 #rect
Tt0 f26 @|RichDialogIcon #fIcon
Tt0 f27 outLink TestRelatedTaskWithNoResponsible.ivp #txt
Tt0 f27 type internaltest.Data #txt
Tt0 f27 inParamDecl '<> param;' #txt
Tt0 f27 actionDecl 'internaltest.Data out;
' #txt
Tt0 f27 guid 15900A8080AC1636 #txt
Tt0 f27 requestEnabled true #txt
Tt0 f27 triggerEnabled false #txt
Tt0 f27 callSignature TestRelatedTaskWithNoResponsible() #txt
Tt0 f27 persist false #txt
Tt0 f27 startName 'Categoried Leave Request for TestRelatedTaskWithNoResponsible' #txt
Tt0 f27 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f27 caseData 'case.description=Leave Request Description
case.name=Leave Request For TestRelatedTaskWithNoResponsible
customFields.varchar.1="Leave Request CustomVarCharField1"
customFields.varchar.2="Leave Request CustomVarCharField2"
customFields.varchar.3="Leave Request CustomVarCharField3"
customFields.varchar.4="Leave Request CustomVarCharField4"
customFields.varchar.5="Leave Request CustomVarCharField5"
process.code=pubRequested
process.name=Publication Requested
processCategory.code=pubRequested
processCategory.name=Publication Requested' #txt
Tt0 f27 showInStartList 1 #txt
Tt0 f27 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("Leave Request For TestRelatedTaskWithNoResponsible"));
ivy.case.setDescription(engine.expandMacros("Leave Request Description"));
ivy.case.setProcessCategory(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
ivy.case.setProcess(engine.expandMacros("pubRequested"), engine.expandMacros("Publication Requested"));
ivy.case.setCustomVarCharField1("Leave Request CustomVarCharField1");
ivy.case.setCustomVarCharField2("Leave Request CustomVarCharField2");
ivy.case.setCustomVarCharField3("Leave Request CustomVarCharField3");
ivy.case.setCustomVarCharField4("Leave Request CustomVarCharField4");
ivy.case.setCustomVarCharField5("Leave Request CustomVarCharField5");
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TestRelatedTaskWithNoResponsible.ivp</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f27 @C|.responsibility Everybody #txt
Tt0 f27 1291 75 26 26 14 0 #rect
Tt0 f27 @|StartRequestIcon #fIcon
Tt0 f28 actionDecl 'internaltest.Data out;
' #txt
Tt0 f28 actionTable 'out=in1;
' #txt
Tt0 f28 outTypes "internaltest.Data","internaltest.Data","internaltest.Data" #txt
Tt0 f28 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Tt0 f28 taskData 'TaskA.DESC=Annual Leave Request Description
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Annual Leave Request
TaskA.PRI=2
TaskA.ROL="demoNotExisted"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.1="Annual CustomVarCharField1"
TaskA.customFields.varchar.2="Annual CustomVarCharField2"
TaskA.customFields.varchar.3="Annual CustomVarCharField3"
TaskA.customFields.varchar.4="Annual CustomVarCharField4"
TaskA.customFields.varchar.5="Annual Leave"
TaskB.DESC=Sick Leave Request Description
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Sick Leave Request
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.1="Sick CustomVarCharField1"
TaskB.customFields.varchar.2="Sick CustomVarCharField2"
TaskB.customFields.varchar.3="Sick CustomVarCharField3"
TaskB.customFields.varchar.4="Sick CustomVarCharField4"
TaskB.customFields.varchar.5="Other Leave/Sick/Long"
TaskC.DESC=Maternity Leave Request Description
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Maternity Leave Request
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.1="Maternity CustomVarCharField1"
TaskC.customFields.varchar.2="Maternity CustomVarCharField2"
TaskC.customFields.varchar.3="Maternity CustomVarCharField3"
TaskC.customFields.varchar.4="Maternity CustomVarCharField4"
TaskC.customFields.varchar.5="Other Leave/Maternity"' #txt
Tt0 f28 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Annual Leave Request"));
taskDef.setDescription(engine.expandMacros("Annual Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + "demoNotExisted");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration("3H")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Annual CustomVarCharField1");
taskDef.setCustomVarCharField2("Annual CustomVarCharField2");
taskDef.setCustomVarCharField3("Annual CustomVarCharField3");
taskDef.setCustomVarCharField4("Annual CustomVarCharField4");
taskDef.setCustomVarCharField5("Annual Leave");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("Sick Leave Request"));
taskDef.setDescription(engine.expandMacros("Sick Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryPeriod(1000 * (new Duration("1D")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Sick CustomVarCharField1");
taskDef.setCustomVarCharField2("Sick CustomVarCharField2");
taskDef.setCustomVarCharField3("Sick CustomVarCharField3");
taskDef.setCustomVarCharField4("Sick CustomVarCharField4");
taskDef.setCustomVarCharField5("Other Leave/Sick/Long");
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("Maternity Leave Request"));
taskDef.setDescription(engine.expandMacros("Maternity Leave Request Description"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryPeriod(1000 * (new Duration("2D")).toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("Maternity CustomVarCharField1");
taskDef.setCustomVarCharField2("Maternity CustomVarCharField2");
taskDef.setCustomVarCharField3("Maternity CustomVarCharField3");
taskDef.setCustomVarCharField4("Maternity CustomVarCharField4");
taskDef.setCustomVarCharField5("Other Leave/Maternity");
taskDefinitions.add(taskDef);
' #txt
Tt0 f28 type internaltest.Data #txt
Tt0 f28 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f28 1290 202 28 28 9 -21 #rect
Tt0 f28 @|TaskSwitchIcon #fIcon
Tt0 f29 targetWindow NEW:card: #txt
Tt0 f29 targetDisplay TOP #txt
Tt0 f29 richDialogId internaltest.TaskForm #txt
Tt0 f29 startMethod start() #txt
Tt0 f29 type internaltest.Data #txt
Tt0 f29 requestActionDecl '<> param;' #txt
Tt0 f29 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f29 responseMappingAction 'out=in;
' #txt
Tt0 f29 windowConfiguration '* ' #txt
Tt0 f29 isAsynch false #txt
Tt0 f29 isInnerRd false #txt
Tt0 f29 userContext '* ' #txt
Tt0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>6,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f29 1158 308 36 24 20 -2 #rect
Tt0 f29 @|RichDialogIcon #fIcon
Tt0 f30 type internaltest.Data #txt
Tt0 f30 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f30 1291 475 26 26 14 0 #rect
Tt0 f30 @|EndRequestIcon #fIcon
Tt0 f31 expr data #txt
Tt0 f31 outCond ivp=="TaskA.ivp" #txt
Tt0 f31 1290 216 1176 308 #arcP
Tt0 f31 1 1176 216 #addKink
Tt0 f31 0 0.8879104383411323 0 0 #arcLabel
Tt0 f32 expr data #txt
Tt0 f32 outCond ivp=="TaskB.ivp" #txt
Tt0 f32 1304 230 1304 308 #arcP
Tt0 f33 expr data #txt
Tt0 f33 outCond ivp=="TaskC.ivp" #txt
Tt0 f33 1318 216 1440 308 #arcP
Tt0 f33 1 1440 216 #addKink
Tt0 f33 0 0.8643692094953389 0 0 #arcLabel
Tt0 f34 expr out #txt
Tt0 f34 1176 332 1291 488 #arcP
Tt0 f34 1 1176 488 #addKink
Tt0 f34 1 0.032798207405834785 0 0 #arcLabel
Tt0 f35 expr out #txt
Tt0 f35 1440 332 1317 488 #arcP
Tt0 f35 1 1440 488 #addKink
Tt0 f35 1 0.03785109119051796 0 0 #arcLabel
Tt0 f36 expr out #txt
Tt0 f36 1304 332 1304 475 #arcP
Tt0 f37 expr out #txt
Tt0 f37 type internaltest.Data #txt
Tt0 f37 var in1 #txt
Tt0 f37 template /ProcessPages/portalHome.ivc #txt
Tt0 f37 1304 101 1304 202 #arcP
>Proto Tt0 .type internaltest.Data #txt
>Proto Tt0 .processKind NORMAL #txt
>Proto Tt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Create Tasks</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>424</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Tt0 0 0 32 24 18 0 #rect
>Proto Tt0 @|BIcon #fIcon
Tt0 f7 out f13 tail #connect
Tt0 f13 head f9 mainIn #connect
Tt0 f7 out f14 tail #connect
Tt0 f14 head f11 mainIn #connect
Tt0 f7 out f15 tail #connect
Tt0 f15 head f12 mainIn #connect
Tt0 f12 mainOut f17 tail #connect
Tt0 f17 head f20 mainIn #connect
Tt0 f11 mainOut f19 tail #connect
Tt0 f19 head f20 mainIn #connect
Tt0 f9 mainOut f18 tail #connect
Tt0 f18 head f20 mainIn #connect
Tt0 f23 out f25 tail #connect
Tt0 f25 head f22 mainIn #connect
Tt0 f0 mainOut f2 tail #connect
Tt0 f2 head f1 mainIn #connect
Tt0 f1 mainOut f3 tail #connect
Tt0 f3 head f23 in #connect
Tt0 f5 mainOut f4 tail #connect
Tt0 f4 head f7 in #connect
Tt0 f8 out f21 tail #connect
Tt0 f21 head f10 mainIn #connect
Tt0 f6 mainOut f16 tail #connect
Tt0 f16 head f8 in #connect
Tt0 f28 out f31 tail #connect
Tt0 f31 head f29 mainIn #connect
Tt0 f28 out f32 tail #connect
Tt0 f32 head f24 mainIn #connect
Tt0 f28 out f33 tail #connect
Tt0 f33 head f26 mainIn #connect
Tt0 f26 mainOut f35 tail #connect
Tt0 f35 head f30 mainIn #connect
Tt0 f24 mainOut f36 tail #connect
Tt0 f36 head f30 mainIn #connect
Tt0 f29 mainOut f34 tail #connect
Tt0 f34 head f30 mainIn #connect
Tt0 f27 mainOut f37 tail #connect
Tt0 f37 head f28 in #connect
