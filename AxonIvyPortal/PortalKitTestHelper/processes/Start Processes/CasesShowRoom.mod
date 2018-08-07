[Ivy]
[>Created: Fri Feb 19 16:54:46 ICT 2016]
14BDECA22347DEF2 3.18 #module
>Proto >Proto Collection #zClass
Ce0 CasesShowRoom Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .resExport .resExport #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartRequest f30 '' #zField
Ce0 @EndTask f31 '' #zField
Ce0 @StartRequest f32 '' #zField
Ce0 @EndTask f33 '' #zField
Ce0 @RichDialog f34 '' #zField
Ce0 @RichDialog f35 '' #zField
Ce0 @PushWFArc f36 '' #zField
Ce0 @PushWFArc f37 '' #zField
Ce0 @PushWFArc f38 '' #zField
Ce0 @PushWFArc f39 '' #zField
Ce0 @RichDialog f41 '' #zField
Ce0 @EndTask f42 '' #zField
Ce0 @StartRequest f40 '' #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @RichDialog f2 '' #zField
Ce0 @PushWFArc f3 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @PushWFArc f7 '' #zField
Ce0 @RichDialog f6 '' #zField
Ce0 @StartRequest f5 '' #zField
Ce0 @TaskSwitchSimple f27 '' #zField
Ce0 @PushWFArc f23 '' #zField
Ce0 @TkArc f8 '' #zField
Ce0 @TaskSwitchSimple f9 '' #zField
Ce0 @TkArc f20 '' #zField
Ce0 @PushWFArc f21 '' #zField
Ce0 @StartRequest f22 '' #zField
Ce0 @EndTask f24 '' #zField
Ce0 @RichDialog f25 '' #zField
Ce0 @PushWFArc f26 '' #zField
Ce0 @PushWFArc f28 '' #zField
>Proto Ce0 Ce0 CasesShowRoom #zField
Ce0 f30 outLink RunningCaseTable.ivp #txt
Ce0 f30 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f30 inParamDecl '<> param;' #txt
Ce0 f30 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f30 guid 14C68DFC0678EE70 #txt
Ce0 f30 requestEnabled true #txt
Ce0 f30 triggerEnabled false #txt
Ce0 f30 callSignature RunningCaseTable() #txt
Ce0 f30 persist false #txt
Ce0 f30 startName 'Internal RunningCaseTable' #txt
Ce0 f30 taskData '#
#Fri Apr 24 16:37:03 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ce0 f30 caseData '#
#Fri Apr 24 16:37:03 ICT 2015
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
Ce0 f30 showInStartList 1 #txt
Ce0 f30 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>RunningCaseTable.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f30 @C|.responsibility Everybody #txt
Ce0 f30 43 35 26 26 19 -6 #rect
Ce0 f30 @|StartRequestIcon #fIcon
Ce0 f31 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f31 43 147 26 26 14 0 #rect
Ce0 f31 @|EndIcon #fIcon
Ce0 f32 outLink FinishedCaseTable.ivp #txt
Ce0 f32 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f32 inParamDecl '<> param;' #txt
Ce0 f32 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f32 guid 14C68DFC057429E8 #txt
Ce0 f32 requestEnabled true #txt
Ce0 f32 triggerEnabled false #txt
Ce0 f32 callSignature FinishedCaseTable() #txt
Ce0 f32 persist false #txt
Ce0 f32 startName 'Internal FinishedCaseTable' #txt
Ce0 f32 taskData '#
#Fri Apr 24 16:37:35 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ce0 f32 caseData '#
#Fri Apr 24 16:37:35 ICT 2015
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
Ce0 f32 showInStartList 1 #txt
Ce0 f32 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FinishedCaseTable.ivp</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f32 @C|.responsibility Everybody #txt
Ce0 f32 275 35 26 26 14 0 #rect
Ce0 f32 @|StartRequestIcon #fIcon
Ce0 f33 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f33 275 139 26 26 14 0 #rect
Ce0 f33 @|EndIcon #fIcon
Ce0 f34 targetWindow NEW:card: #txt
Ce0 f34 targetDisplay TOP #txt
Ce0 f34 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest #txt
Ce0 f34 startMethod startForRunningCase() #txt
Ce0 f34 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f34 requestActionDecl '<> param;' #txt
Ce0 f34 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f34 responseMappingAction 'out=in;
' #txt
Ce0 f34 windowConfiguration '* ' #txt
Ce0 f34 isAsynch false #txt
Ce0 f34 isInnerRd false #txt
Ce0 f34 userContext '* ' #txt
Ce0 f34 38 92 36 24 20 -2 #rect
Ce0 f34 @|RichDialogIcon #fIcon
Ce0 f35 targetWindow NEW:card: #txt
Ce0 f35 targetDisplay TOP #txt
Ce0 f35 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest #txt
Ce0 f35 startMethod startForFinishedCase() #txt
Ce0 f35 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f35 requestActionDecl '<> param;' #txt
Ce0 f35 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f35 responseMappingAction 'out=in;
' #txt
Ce0 f35 windowConfiguration '* ' #txt
Ce0 f35 isAsynch false #txt
Ce0 f35 isInnerRd false #txt
Ce0 f35 userContext '* ' #txt
Ce0 f35 270 84 36 24 20 -2 #rect
Ce0 f35 @|RichDialogIcon #fIcon
Ce0 f36 expr out #txt
Ce0 f36 56 61 56 92 #arcP
Ce0 f37 expr out #txt
Ce0 f37 56 116 56 147 #arcP
Ce0 f38 expr out #txt
Ce0 f38 288 61 288 84 #arcP
Ce0 f39 expr out #txt
Ce0 f39 288 108 288 139 #arcP
Ce0 f41 targetWindow NEW:card: #txt
Ce0 f41 targetDisplay TOP #txt
Ce0 f41 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.cases.PortalCaseDetailsHome #txt
Ce0 f41 startMethod start(Number) #txt
Ce0 f41 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f41 requestActionDecl '<Number caseId> param;' #txt
Ce0 f41 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ce0 f41 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f41 responseMappingAction 'out=in;
' #txt
Ce0 f41 windowConfiguration '* ' #txt
Ce0 f41 isAsynch false #txt
Ce0 f41 isInnerRd false #txt
Ce0 f41 userContext '* ' #txt
Ce0 f41 38 404 36 24 20 -2 #rect
Ce0 f41 @|RichDialogIcon #fIcon
Ce0 f42 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f42 43 459 26 26 14 0 #rect
Ce0 f42 @|EndIcon #fIcon
Ce0 f40 outLink OpenCaseDetails.ivp #txt
Ce0 f40 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f40 inParamDecl '<java.lang.Number caseId> param;' #txt
Ce0 f40 inParamTable 'out.caseId=param.caseId;
' #txt
Ce0 f40 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f40 guid 14C91867EA64FB67 #txt
Ce0 f40 requestEnabled true #txt
Ce0 f40 triggerEnabled false #txt
Ce0 f40 callSignature OpenCaseDetails(Number) #txt
Ce0 f40 persist false #txt
Ce0 f40 startName 'Internal openCaseDetails' #txt
Ce0 f40 taskData '#
#Fri Apr 24 16:38:00 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=OpenCaseDetails
' #txt
Ce0 f40 caseData '#
#Fri Apr 24 16:38:00 ICT 2015
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
Ce0 f40 showInStartList 1 #txt
Ce0 f40 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("OpenCaseDetails"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenCaseDetails.ivp</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f40 @C|.responsibility Everybody #txt
Ce0 f40 43 219 26 26 14 0 #rect
Ce0 f40 @|StartRequestIcon #fIcon
Ce0 f0 outLink ProvidedCasesCaseTableTest.ivp #txt
Ce0 f0 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f0 inParamDecl '<> param;' #txt
Ce0 f0 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f0 guid 14D705CB2457BDC3 #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature ProvidedCasesCaseTableTest() #txt
Ce0 f0 persist false #txt
Ce0 f0 taskData '#
#Wed May 20 15:09:40 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ce0 f0 caseData '#
#Wed May 20 15:09:40 ICT 2015
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
Ce0 f0 showInStartList 1 #txt
Ce0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProvidedCasesCaseTableTest.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 275 227 26 26 14 -8 #rect
Ce0 f0 @|StartRequestIcon #fIcon
Ce0 f1 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f1 275 379 26 26 14 0 #rect
Ce0 f1 @|EndIcon #fIcon
Ce0 f2 targetWindow NEW:card: #txt
Ce0 f2 targetDisplay TOP #txt
Ce0 f2 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.cases.ProvidedCasesCaseTableTest #txt
Ce0 f2 startMethod start() #txt
Ce0 f2 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f2 requestActionDecl '<> param;' #txt
Ce0 f2 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f2 responseMappingAction 'out=in;
' #txt
Ce0 f2 windowConfiguration '* ' #txt
Ce0 f2 isAsynch false #txt
Ce0 f2 isInnerRd false #txt
Ce0 f2 userContext '* ' #txt
Ce0 f2 270 300 36 24 20 -2 #rect
Ce0 f2 @|RichDialogIcon #fIcon
Ce0 f3 expr out #txt
Ce0 f3 288 253 288 300 #arcP
Ce0 f4 expr out #txt
Ce0 f4 288 324 288 379 #arcP
Ce0 f7 expr out #txt
Ce0 f7 896 69 896 188 #arcP
Ce0 f6 targetWindow NEW:card: #txt
Ce0 f6 targetDisplay TOP #txt
Ce0 f6 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.cases.DocumentFilePage #txt
Ce0 f6 startMethod start(Number) #txt
Ce0 f6 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f6 requestActionDecl '<Number caseId> param;' #txt
Ce0 f6 requestMappingAction 'param.caseId=1989;
' #txt
Ce0 f6 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f6 responseMappingAction 'out=in;
' #txt
Ce0 f6 windowConfiguration '* ' #txt
Ce0 f6 isAsynch false #txt
Ce0 f6 isInnerRd false #txt
Ce0 f6 userContext '* ' #txt
Ce0 f6 878 188 36 24 20 -2 #rect
Ce0 f6 @|RichDialogIcon #fIcon
Ce0 f5 outLink documentPage.ivp #txt
Ce0 f5 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f5 inParamDecl '<> param;' #txt
Ce0 f5 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f5 guid 14DE7214BF11E438 #txt
Ce0 f5 requestEnabled true #txt
Ce0 f5 triggerEnabled false #txt
Ce0 f5 callSignature documentPage() #txt
Ce0 f5 persist false #txt
Ce0 f5 taskData '#
#Fri Jun 12 16:49:04 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ce0 f5 caseData '#
#Fri Jun 12 16:49:04 ICT 2015
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
Ce0 f5 showInStartList 1 #txt
Ce0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>documentPage.ivp</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f5 @C|.responsibility Everybody #txt
Ce0 f5 883 43 26 26 14 0 #rect
Ce0 f5 @|StartRequestIcon #fIcon
Ce0 f27 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f27 actionTable 'out=in1;
' #txt
Ce0 f27 outTypes "ch.ivy.addon.portalkit.showroom.ExtendedTask" #txt
Ce0 f27 outLinks "TaskA.ivp" #txt
Ce0 f27 caseData '#
#Fri Jul 03 16:39:24 ICT 2015
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
Ce0 f27 taskData '#
#Fri Jul 03 16:39:24 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0
' #txt
Ce0 f27 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setAutoStartTask(true);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ce0 f27 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f27 template "" #txt
Ce0 f27 43 275 26 26 13 0 #rect
Ce0 f27 @|TaskSwitchSimpleIcon #fIcon
Ce0 f23 expr out #txt
Ce0 f23 56 428 56 459 #arcP
Ce0 f8 expr out #txt
Ce0 f8 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f8 var in1 #txt
Ce0 f8 56 245 56 275 #arcP
Ce0 f9 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f9 actionTable 'out=in1;
' #txt
Ce0 f9 outTypes "ch.ivy.addon.portalkit.showroom.ExtendedTask" #txt
Ce0 f9 outLinks "TaskA.ivp" #txt
Ce0 f9 caseData '#
#Fri Jul 03 16:40:57 ICT 2015
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
Ce0 f9 taskData '#
#Fri Jul 03 16:40:57 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0
' #txt
Ce0 f9 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setAutoStartTask(true);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ce0 f9 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f9 template "" #txt
Ce0 f9 43 339 26 26 13 0 #rect
Ce0 f9 @|TaskSwitchSimpleIcon #fIcon
Ce0 f20 expr data #txt
Ce0 f20 outCond ivp=="TaskA.ivp" #txt
Ce0 f20 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f20 var in1 #txt
Ce0 f20 56 301 56 339 #arcP
Ce0 f20 0 0.49021280491382413 0 0 #arcLabel
Ce0 f21 expr data #txt
Ce0 f21 outCond ivp=="TaskA.ivp" #txt
Ce0 f21 56 365 56 404 #arcP
Ce0 f21 0 0.49021280491382413 0 0 #arcLabel
Ce0 f22 outLink startWithPropertyFilter.ivp #txt
Ce0 f22 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f22 inParamDecl '<> param;' #txt
Ce0 f22 actionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f22 guid 14E61520695CC7FD #txt
Ce0 f22 requestEnabled true #txt
Ce0 f22 triggerEnabled false #txt
Ce0 f22 callSignature startWithPropertyFilter() #txt
Ce0 f22 persist false #txt
Ce0 f22 startName 'Internal FinishedCaseTable' #txt
Ce0 f22 taskData '#
#Mon Jul 06 10:14:15 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ce0 f22 caseData '#
#Mon Jul 06 10:14:15 ICT 2015
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
Ce0 f22 showInStartList 1 #txt
Ce0 f22 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithPropertyFilter.ivp</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f22 @C|.responsibility Everybody #txt
Ce0 f22 443 35 26 26 14 0 #rect
Ce0 f22 @|StartRequestIcon #fIcon
Ce0 f24 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f24 443 139 26 26 14 0 #rect
Ce0 f24 @|EndIcon #fIcon
Ce0 f25 targetWindow NEW:card: #txt
Ce0 f25 targetDisplay TOP #txt
Ce0 f25 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest #txt
Ce0 f25 startMethod startWithIPropertyFilter() #txt
Ce0 f25 type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
Ce0 f25 requestActionDecl '<> param;' #txt
Ce0 f25 responseActionDecl 'ch.ivy.addon.portalkit.showroom.ExtendedTask out;
' #txt
Ce0 f25 responseMappingAction 'out=in;
' #txt
Ce0 f25 windowConfiguration '* ' #txt
Ce0 f25 isAsynch false #txt
Ce0 f25 isInnerRd false #txt
Ce0 f25 userContext '* ' #txt
Ce0 f25 438 84 36 24 20 -2 #rect
Ce0 f25 @|RichDialogIcon #fIcon
Ce0 f26 expr out #txt
Ce0 f26 456 61 456 84 #arcP
Ce0 f28 expr out #txt
Ce0 f28 456 108 456 139 #arcP
>Proto Ce0 .type ch.ivy.addon.portalkit.showroom.ExtendedTask #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>CaseTable</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>632</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f30 mainOut f36 tail #connect
Ce0 f36 head f34 mainIn #connect
Ce0 f34 mainOut f37 tail #connect
Ce0 f37 head f31 mainIn #connect
Ce0 f32 mainOut f38 tail #connect
Ce0 f38 head f35 mainIn #connect
Ce0 f35 mainOut f39 tail #connect
Ce0 f39 head f33 mainIn #connect
Ce0 f0 mainOut f3 tail #connect
Ce0 f3 head f2 mainIn #connect
Ce0 f2 mainOut f4 tail #connect
Ce0 f4 head f1 mainIn #connect
Ce0 f5 mainOut f7 tail #connect
Ce0 f7 head f6 mainIn #connect
Ce0 f41 mainOut f23 tail #connect
Ce0 f23 head f42 mainIn #connect
Ce0 f40 mainOut f8 tail #connect
Ce0 f8 head f27 in #connect
Ce0 f27 out f20 tail #connect
Ce0 f20 head f9 in #connect
Ce0 f9 out f21 tail #connect
Ce0 f21 head f41 mainIn #connect
Ce0 f22 mainOut f26 tail #connect
Ce0 f26 head f25 mainIn #connect
Ce0 f25 mainOut f28 tail #connect
Ce0 f28 head f24 mainIn #connect
