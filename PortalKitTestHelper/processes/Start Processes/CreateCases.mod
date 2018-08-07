[Ivy]
[>Created: Fri Apr 24 16:39:21 ICT 2015]
14B2E43D8E23A3C9 3.17 #module
>Proto >Proto Collection #zClass
Tn0 CreateCases Big #zClass
Tn0 B #cInfo
Tn0 #process
Tn0 @TextInP .resExport .resExport #zField
Tn0 @TextInP .type .type #zField
Tn0 @TextInP .processKind .processKind #zField
Tn0 @AnnotationInP-0n ai ai #zField
Tn0 @TextInP .xml .xml #zField
Tn0 @TextInP .responsibility .responsibility #zField
Tn0 @StartRequest f0 '' #zField
Tn0 @EndTask f1 '' #zField
Tn0 @TaskSwitchSimple f2 '' #zField
Tn0 @TaskSwitch f5 '' #zField
Tn0 @TkArc f6 '' #zField
Tn0 @PushWFArc f4 '' #zField
Tn0 @TaskSwitchSimple f7 '' #zField
Tn0 @StartRequest f8 '' #zField
Tn0 @EndTask f9 '' #zField
Tn0 @PushWFArc f11 '' #zField
Tn0 @TaskSwitchSimple f12 '' #zField
Tn0 @EndTask f13 '' #zField
Tn0 @StartRequest f14 '' #zField
Tn0 @PushWFArc f16 '' #zField
Tn0 @StartRequest f17 '' #zField
Tn0 @TaskSwitchSimple f18 '' #zField
Tn0 @EndTask f19 '' #zField
Tn0 @PushWFArc f21 '' #zField
Tn0 @StartRequest f22 '' #zField
Tn0 @TaskSwitchSimple f23 '' #zField
Tn0 @EndTask f24 '' #zField
Tn0 @PushWFArc f26 '' #zField
Tn0 @StartRequest f27 '' #zField
Tn0 @TaskSwitchSimple f28 '' #zField
Tn0 @EndTask f29 '' #zField
Tn0 @PushWFArc f31 '' #zField
Tn0 @GridStep f32 '' #zField
Tn0 @PushWFArc f33 '' #zField
Tn0 @TkArc f3 '' #zField
Tn0 @GridStep f34 '' #zField
Tn0 @PushWFArc f35 '' #zField
Tn0 @TkArc f10 '' #zField
Tn0 @GridStep f36 '' #zField
Tn0 @PushWFArc f37 '' #zField
Tn0 @TkArc f15 '' #zField
Tn0 @GridStep f38 '' #zField
Tn0 @PushWFArc f39 '' #zField
Tn0 @TkArc f20 '' #zField
Tn0 @GridStep f40 '' #zField
Tn0 @PushWFArc f41 '' #zField
Tn0 @TkArc f25 '' #zField
Tn0 @GridStep f42 '' #zField
Tn0 @PushWFArc f43 '' #zField
Tn0 @TkArc f30 '' #zField
>Proto Tn0 Tn0 CreateCases #zField
Tn0 f0 outLink start1.ivp #txt
Tn0 f0 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f0 inParamDecl '<> param;' #txt
Tn0 f0 inParamTable 'out.structuredData="CategoryA/SubCategoryA/000";
' #txt
Tn0 f0 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f0 guid 14AC257B1E15C8AD #txt
Tn0 f0 requestEnabled true #txt
Tn0 f0 triggerEnabled false #txt
Tn0 f0 callSignature start1() #txt
Tn0 f0 persist false #txt
Tn0 f0 startName 'Internal Create cases 1' #txt
Tn0 f0 taskData '#
#Fri Apr 24 16:38:24 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f0 caseData '#
#Fri Apr 24 16:38:24 ICT 2015
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
Tn0 f0 showInStartList 1 #txt
Tn0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start1.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f0 @C|.responsibility Everybody #txt
Tn0 f0 75 51 26 26 14 0 #rect
Tn0 f0 @|StartRequestIcon #fIcon
Tn0 f1 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f1 75 315 26 26 14 0 #rect
Tn0 f1 @|EndIcon #fIcon
Tn0 f2 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f2 actionTable 'out=in1;
' #txt
Tn0 f2 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f2 outLinks "TaskA.ivp" #txt
Tn0 f2 caseData '#
#Thu Apr 02 10:32:21 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description="OverlayPanel is a generic container component that can overlay other components on page. Notable features are custom positioning, configurable events and effects. Lazy content loading to reduce page load time is also supported via dynamic option, when enabled overlayPanel will load the contents just before being shown."
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f2 taskData '#
#Thu Apr 02 10:32:21 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f2 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setDescription(engine.expandMacros("\"OverlayPanel is a generic container component that can overlay other components on page. Notable features are custom positioning, configurable events and effects. Lazy content loading to reduce page load time is also supported via dynamic option, when enabled overlayPanel will load the contents just before being shown.\""));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f2 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f2 template "" #txt
Tn0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryA/SubCategoryA/000&quot;</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f2 74 162 28 28 14 0 #rect
Tn0 f2 @|TaskSwitchSimpleIcon #fIcon
Tn0 f5 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f5 actionTable 'out=in1;
out.structuredData="CatA/SubCatA/MicrosubCatE";
' #txt
Tn0 f5 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f5 outLinks "TaskA.ivp" #txt
Tn0 f5 caseData '#
#Wed Apr 08 13:57:14 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f5 taskData '#
#Wed Apr 08 13:57:14 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL="H23246"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f5 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + "H23246");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f5 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f5 template "" #txt
Tn0 f5 74 242 28 28 14 0 #rect
Tn0 f5 @|TaskSwitchIcon #fIcon
Tn0 f6 expr data #txt
Tn0 f6 outCond ivp=="TaskA.ivp" #txt
Tn0 f6 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f6 var in1 #txt
Tn0 f6 88 190 88 242 #arcP
Tn0 f4 expr data #txt
Tn0 f4 outCond ivp=="TaskA.ivp" #txt
Tn0 f4 88 270 88 315 #arcP
Tn0 f7 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f7 actionTable 'out=in1;
' #txt
Tn0 f7 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f7 outLinks "TaskA.ivp" #txt
Tn0 f7 caseData '#
#Wed Apr 08 13:57:27 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f7 taskData '#
#Wed Apr 08 13:57:27 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f7 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f7 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f7 template "" #txt
Tn0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryA/SubCategoryB/1111&quot;</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f7 330 162 28 28 14 0 #rect
Tn0 f7 @|TaskSwitchSimpleIcon #fIcon
Tn0 f8 outLink start2.ivp #txt
Tn0 f8 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f8 inParamDecl '<> param;' #txt
Tn0 f8 inParamTable 'out.structuredData="CategoryA/SubCategoryB/1111";
' #txt
Tn0 f8 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f8 guid 14ACCE7AE84A7B78 #txt
Tn0 f8 requestEnabled true #txt
Tn0 f8 triggerEnabled false #txt
Tn0 f8 callSignature start2() #txt
Tn0 f8 persist false #txt
Tn0 f8 startName 'Internal  Create cases 2' #txt
Tn0 f8 taskData '#
#Fri Apr 24 16:38:31 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f8 caseData '#
#Fri Apr 24 16:38:31 ICT 2015
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
Tn0 f8 showInStartList 1 #txt
Tn0 f8 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start2.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f8 @C|.responsibility Everybody #txt
Tn0 f8 331 51 26 26 14 0 #rect
Tn0 f8 @|StartRequestIcon #fIcon
Tn0 f9 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f9 331 315 26 26 14 0 #rect
Tn0 f9 @|EndIcon #fIcon
Tn0 f11 expr data #txt
Tn0 f11 outCond ivp=="TaskA.ivp" #txt
Tn0 f11 344 190 344 315 #arcP
Tn0 f12 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f12 actionTable 'out=in1;
' #txt
Tn0 f12 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f12 outLinks "TaskA.ivp" #txt
Tn0 f12 caseData '#
#Thu Apr 02 10:31:59 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f12 taskData '#
#Thu Apr 02 10:31:59 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f12 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f12 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f12 template "" #txt
Tn0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryA/SubCategoryD/3333&quot;</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f12 618 162 28 28 14 0 #rect
Tn0 f12 @|TaskSwitchSimpleIcon #fIcon
Tn0 f13 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f13 619 315 26 26 14 0 #rect
Tn0 f13 @|EndIcon #fIcon
Tn0 f14 outLink start3.ivp #txt
Tn0 f14 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f14 inParamDecl '<> param;' #txt
Tn0 f14 inParamTable 'out.structuredData="CategoryA/SubCategoryD/3333";
' #txt
Tn0 f14 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f14 guid 14ACCE9311F3BC68 #txt
Tn0 f14 requestEnabled true #txt
Tn0 f14 triggerEnabled false #txt
Tn0 f14 callSignature start3() #txt
Tn0 f14 persist false #txt
Tn0 f14 startName 'Internal create cases' #txt
Tn0 f14 taskData '#
#Fri Apr 24 16:38:44 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f14 caseData '#
#Fri Apr 24 16:38:44 ICT 2015
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
Tn0 f14 showInStartList 1 #txt
Tn0 f14 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start3.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f14 @C|.responsibility Everybody #txt
Tn0 f14 619 51 26 26 14 0 #rect
Tn0 f14 @|StartRequestIcon #fIcon
Tn0 f16 expr data #txt
Tn0 f16 outCond ivp=="TaskA.ivp" #txt
Tn0 f16 632 190 632 315 #arcP
Tn0 f17 outLink start4.ivp #txt
Tn0 f17 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f17 inParamDecl '<> param;' #txt
Tn0 f17 inParamTable 'out.structuredData="CategoryB/SubCategoryD/3333";
' #txt
Tn0 f17 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f17 guid 14ACCE9DAEC8E8F8 #txt
Tn0 f17 requestEnabled true #txt
Tn0 f17 triggerEnabled false #txt
Tn0 f17 callSignature start4() #txt
Tn0 f17 persist false #txt
Tn0 f17 startName 'Internal create cases' #txt
Tn0 f17 taskData '#
#Fri Apr 24 16:39:10 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f17 caseData '#
#Fri Apr 24 16:39:10 ICT 2015
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
Tn0 f17 showInStartList 1 #txt
Tn0 f17 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start4.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f17 @C|.responsibility Everybody #txt
Tn0 f17 75 443 26 26 14 0 #rect
Tn0 f17 @|StartRequestIcon #fIcon
Tn0 f18 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f18 actionTable 'out=in1;
' #txt
Tn0 f18 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f18 outLinks "TaskA.ivp" #txt
Tn0 f18 caseData '#
#Wed Apr 08 13:59:56 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.1=in1.structuredData
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
Tn0 f18 taskData '#
#Wed Apr 08 13:59:56 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f18 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField1(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f18 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f18 template "" #txt
Tn0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryB/SubCategoryD/3333&quot;</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f18 74 554 28 28 14 0 #rect
Tn0 f18 @|TaskSwitchSimpleIcon #fIcon
Tn0 f19 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f19 75 707 26 26 14 0 #rect
Tn0 f19 @|EndIcon #fIcon
Tn0 f21 expr data #txt
Tn0 f21 outCond ivp=="TaskA.ivp" #txt
Tn0 f21 88 582 88 707 #arcP
Tn0 f22 outLink start5.ivp #txt
Tn0 f22 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f22 inParamDecl '<> param;' #txt
Tn0 f22 inParamTable 'out.structuredData="CategoryC/SubCategoryD/3333/ZZZZ";
' #txt
Tn0 f22 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f22 guid 14ACCE9DE2EE91D9 #txt
Tn0 f22 requestEnabled true #txt
Tn0 f22 triggerEnabled false #txt
Tn0 f22 callSignature start5() #txt
Tn0 f22 persist false #txt
Tn0 f22 startName 'Internal create cases' #txt
Tn0 f22 taskData '#
#Fri Apr 24 16:39:15 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f22 caseData '#
#Fri Apr 24 16:39:15 ICT 2015
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
Tn0 f22 showInStartList 1 #txt
Tn0 f22 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start5.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f22 @C|.responsibility Everybody #txt
Tn0 f22 331 443 26 26 14 0 #rect
Tn0 f22 @|StartRequestIcon #fIcon
Tn0 f23 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f23 actionTable 'out=in1;
' #txt
Tn0 f23 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f23 outLinks "TaskA.ivp" #txt
Tn0 f23 caseData '#
#Wed Apr 08 14:01:59 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f23 taskData '#
#Wed Apr 08 14:01:59 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.5=in1.structuredData
' #txt
Tn0 f23 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f23 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f23 template "" #txt
Tn0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryC/SubCategoryD/3333/ZZZZ&quot;</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f23 330 554 28 28 14 0 #rect
Tn0 f23 @|TaskSwitchSimpleIcon #fIcon
Tn0 f24 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f24 331 707 26 26 14 0 #rect
Tn0 f24 @|EndIcon #fIcon
Tn0 f26 expr data #txt
Tn0 f26 outCond ivp=="TaskA.ivp" #txt
Tn0 f26 344 582 344 707 #arcP
Tn0 f27 outLink start6.ivp #txt
Tn0 f27 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f27 inParamDecl '<> param;' #txt
Tn0 f27 inParamTable 'out.structuredData="CategoryE/3333";
' #txt
Tn0 f27 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f27 guid 14ACCE9E0BC3931D #txt
Tn0 f27 requestEnabled true #txt
Tn0 f27 triggerEnabled false #txt
Tn0 f27 callSignature start6() #txt
Tn0 f27 persist false #txt
Tn0 f27 startName 'Internal create cases' #txt
Tn0 f27 taskData '#
#Fri Apr 24 16:39:21 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tn0 f27 caseData '#
#Fri Apr 24 16:39:21 ICT 2015
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
Tn0 f27 showInStartList 1 #txt
Tn0 f27 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tn0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start6.ivp</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f27 @C|.responsibility Everybody #txt
Tn0 f27 619 443 26 26 14 0 #rect
Tn0 f27 @|StartRequestIcon #fIcon
Tn0 f28 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f28 actionTable 'out=in1;
' #txt
Tn0 f28 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData" #txt
Tn0 f28 outLinks "TaskA.ivp" #txt
Tn0 f28 caseData '#
#Wed Apr 08 14:03:26 ICT 2015
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
case.name=<%\=in1.structuredData%>
correspondent.id=
customFields.varchar.5=in1.structuredData
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
Tn0 f28 taskData '#
#Wed Apr 08 14:03:26 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=ivy.session.getSessionUser().getName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.1=in1.structuredData
' #txt
Tn0 f28 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.structuredData%>"));
ivy.case.setCustomVarCharField5(in1.structuredData);
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.structuredData%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("#" + ivy.session.getSessionUser().getName());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1(in1.structuredData);
taskDefinitions.add(taskDef);
' #txt
Tn0 f28 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f28 template "" #txt
Tn0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>&quot;CategoryA/SubCategoryD/3333&quot;</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f28 618 554 28 28 14 0 #rect
Tn0 f28 @|TaskSwitchSimpleIcon #fIcon
Tn0 f29 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f29 619 707 26 26 14 0 #rect
Tn0 f29 @|EndIcon #fIcon
Tn0 f31 expr data #txt
Tn0 f31 outCond ivp=="TaskA.ivp" #txt
Tn0 f31 632 582 632 707 #arcP
Tn0 f32 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f32 actionTable 'out=in;
' #txt
Tn0 f32 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f32 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f32 70 100 36 24 20 -2 #rect
Tn0 f32 @|StepIcon #fIcon
Tn0 f33 expr out #txt
Tn0 f33 88 77 88 100 #arcP
Tn0 f3 expr out #txt
Tn0 f3 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f3 var in1 #txt
Tn0 f3 88 124 88 162 #arcP
Tn0 f34 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f34 actionTable 'out=in;
' #txt
Tn0 f34 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f34 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f34 326 108 36 24 20 -2 #rect
Tn0 f34 @|StepIcon #fIcon
Tn0 f35 expr out #txt
Tn0 f35 344 77 344 108 #arcP
Tn0 f10 expr out #txt
Tn0 f10 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f10 var in1 #txt
Tn0 f10 344 132 344 162 #arcP
Tn0 f36 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f36 actionTable 'out=in;
' #txt
Tn0 f36 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f36 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f36 614 108 36 24 20 -2 #rect
Tn0 f36 @|StepIcon #fIcon
Tn0 f37 expr out #txt
Tn0 f37 632 77 632 108 #arcP
Tn0 f15 expr out #txt
Tn0 f15 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f15 var in1 #txt
Tn0 f15 632 132 632 162 #arcP
Tn0 f38 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f38 actionTable 'out=in;
' #txt
Tn0 f38 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f38 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f38 70 500 36 24 20 -2 #rect
Tn0 f38 @|StepIcon #fIcon
Tn0 f39 expr out #txt
Tn0 f39 88 469 88 500 #arcP
Tn0 f20 expr out #txt
Tn0 f20 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f20 var in1 #txt
Tn0 f20 88 524 88 554 #arcP
Tn0 f40 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f40 actionTable 'out=in;
' #txt
Tn0 f40 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f40 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f40 326 500 36 24 20 -2 #rect
Tn0 f40 @|StepIcon #fIcon
Tn0 f41 expr out #txt
Tn0 f41 344 469 344 500 #arcP
Tn0 f25 expr out #txt
Tn0 f25 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f25 var in1 #txt
Tn0 f25 344 524 344 554 #arcP
Tn0 f42 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
Tn0 f42 actionTable 'out=in;
' #txt
Tn0 f42 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case, ivy.html.startref("14BDECA22347DEF2/OpenCaseDetails.ivp"));' #txt
Tn0 f42 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create case details link</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tn0 f42 614 500 36 24 20 -2 #rect
Tn0 f42 @|StepIcon #fIcon
Tn0 f43 expr out #txt
Tn0 f43 632 469 632 500 #arcP
Tn0 f30 expr out #txt
Tn0 f30 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
Tn0 f30 var in1 #txt
Tn0 f30 632 524 632 554 #arcP
>Proto Tn0 .type ch.ivy.add.portalkit.task.TaskGenerationData #txt
>Proto Tn0 .processKind NORMAL #txt
>Proto Tn0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Tn0 0 0 32 24 18 0 #rect
>Proto Tn0 @|BIcon #fIcon
Tn0 f2 out f6 tail #connect
Tn0 f6 head f5 in #connect
Tn0 f5 out f4 tail #connect
Tn0 f4 head f1 mainIn #connect
Tn0 f7 out f11 tail #connect
Tn0 f11 head f9 mainIn #connect
Tn0 f12 out f16 tail #connect
Tn0 f16 head f13 mainIn #connect
Tn0 f18 out f21 tail #connect
Tn0 f21 head f19 mainIn #connect
Tn0 f23 out f26 tail #connect
Tn0 f26 head f24 mainIn #connect
Tn0 f28 out f31 tail #connect
Tn0 f31 head f29 mainIn #connect
Tn0 f0 mainOut f33 tail #connect
Tn0 f33 head f32 mainIn #connect
Tn0 f32 mainOut f3 tail #connect
Tn0 f3 head f2 in #connect
Tn0 f8 mainOut f35 tail #connect
Tn0 f35 head f34 mainIn #connect
Tn0 f34 mainOut f10 tail #connect
Tn0 f10 head f7 in #connect
Tn0 f14 mainOut f37 tail #connect
Tn0 f37 head f36 mainIn #connect
Tn0 f36 mainOut f15 tail #connect
Tn0 f15 head f12 in #connect
Tn0 f17 mainOut f39 tail #connect
Tn0 f39 head f38 mainIn #connect
Tn0 f38 mainOut f20 tail #connect
Tn0 f20 head f18 in #connect
Tn0 f22 mainOut f41 tail #connect
Tn0 f41 head f40 mainIn #connect
Tn0 f40 mainOut f25 tail #connect
Tn0 f25 head f23 in #connect
Tn0 f27 mainOut f43 tail #connect
Tn0 f43 head f42 mainIn #connect
Tn0 f42 mainOut f30 tail #connect
Tn0 f30 head f28 in #connect
