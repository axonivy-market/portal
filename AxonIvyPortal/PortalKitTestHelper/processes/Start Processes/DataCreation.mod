[Ivy]
[>Created: Mon Apr 11 18:15:27 ICT 2016]
153CACC26D0D4C3D 3.18 #module
>Proto >Proto Collection #zClass
Dt0 DataCreation Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .resExport .resExport #zField
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartRequest f0 '' #zField
Dt0 @EndTask f1 '' #zField
Dt0 @TaskSwitch f3 '' #zField
Dt0 @EndTask f5 '' #zField
Dt0 @EndTask f6 '' #zField
Dt0 @EndTask f7 '' #zField
Dt0 @EndTask f8 '' #zField
Dt0 @EndTask f9 '' #zField
Dt0 @EndTask f10 '' #zField
Dt0 @EndTask f11 '' #zField
Dt0 @EndTask f12 '' #zField
Dt0 @EndTask f13 '' #zField
Dt0 @PushWFArc f14 '' #zField
Dt0 @PushWFArc f15 '' #zField
Dt0 @PushWFArc f16 '' #zField
Dt0 @PushWFArc f17 '' #zField
Dt0 @PushWFArc f18 '' #zField
Dt0 @PushWFArc f19 '' #zField
Dt0 @PushWFArc f20 '' #zField
Dt0 @PushWFArc f21 '' #zField
Dt0 @PushWFArc f22 '' #zField
Dt0 @GridStep f23 '' #zField
Dt0 @TkArc f4 '' #zField
Dt0 @StartRequest f25 '' #zField
Dt0 @Trigger f28 '' #zField
Dt0 @GridStep f30 '' #zField
Dt0 @Alternative f32 '' #zField
Dt0 @PushWFArc f33 '' #zField
Dt0 @PushWFArc f29 '' #zField
Dt0 @EndTask f34 '' #zField
Dt0 @PushWFArc f35 '' #zField
Dt0 @RichDialog f27 '' #zField
Dt0 @PushWFArc f36 '' #zField
Dt0 @PushWFArc f2 '' #zField
Dt0 @GridStep f41 '' #zField
Dt0 @PushWFArc f42 '' #zField
Dt0 @PushWFArc f26 '' #zField
Dt0 @PushWFArc f43 '' #zField
Dt0 @RichDialog f24 '' #zField
Dt0 @PushWFArc f37 '' #zField
Dt0 @PushWFArc f31 '' #zField
>Proto Dt0 Dt0 DataCreation #zField
Dt0 f0 outLink createTasks.ivp #txt
Dt0 f0 type portalKit_test.DataCreationData #txt
Dt0 f0 inParamDecl '<java.lang.String taskStructureInfo> param;' #txt
Dt0 f0 inParamTable 'out.taskStructureInfo=param.taskStructureInfo;
' #txt
Dt0 f0 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f0 guid 153CACC26E436D14 #txt
Dt0 f0 requestEnabled false #txt
Dt0 f0 triggerEnabled true #txt
Dt0 f0 callSignature createTasks(String) #txt
Dt0 f0 persist false #txt
Dt0 f0 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f0 showInStartList 1 #txt
Dt0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTasks(String)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f0 @C|.responsibility Everybody #txt
Dt0 f0 35 323 26 26 14 0 #rect
Dt0 f0 @|StartRequestIcon #fIcon
Dt0 f1 type portalKit_test.DataCreationData #txt
Dt0 f1 467 203 26 26 14 0 #rect
Dt0 f1 @|EndIcon #fIcon
Dt0 f3 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f3 actionTable 'out=in1;
' #txt
Dt0 f3 outTypes "portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData" #txt
Dt0 f3 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp","TaskD.ivp","TaskE.ivp","TaskF.ivp","TaskG.ivp","TaskH.ivp","TaskI.ivp","TaskJ.ivp" #txt
Dt0 f3 caseData case.name=<%\=in1.caseName%> #txt
Dt0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.caseName%>_A
TaskA.PRI=0
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.varchar.5=in1.taskStructureInfo
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=<%\=in1.caseName%>_B
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.5=in1.taskStructureInfo
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=<%\=in1.caseName%>_C
TaskC.PRI=2
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.5=in1.taskStructureInfo
TaskD.EXPRI=2
TaskD.EXROL=Everybody
TaskD.EXTYPE=0
TaskD.NAM=<%\=in1.caseName%>_D
TaskD.PRI=3
TaskD.ROL=Everybody
TaskD.SKIP_TASK_LIST=false
TaskD.TYPE=0
TaskD.customFields.varchar.5=in1.taskStructureInfo
TaskE.EXP=''1D''
TaskE.EXPRI=2
TaskE.EXROL=Everybody
TaskE.EXTYPE=0
TaskE.NAM=<%\=in1.caseName%>_E
TaskE.PRI=2
TaskE.ROL=Everybody
TaskE.SKIP_TASK_LIST=false
TaskE.TYPE=0
TaskE.customFields.varchar.5=in1.taskStructureInfo
TaskF.EXP=''2D''
TaskF.EXPRI=2
TaskF.EXROL=Everybody
TaskF.EXTYPE=0
TaskF.NAM=<%\=in1.caseName%>_F
TaskF.PRI=2
TaskF.ROL=Everybody
TaskF.SKIP_TASK_LIST=false
TaskF.TYPE=0
TaskF.customFields.varchar.5=in1.taskStructureInfo
TaskG.EXP=''3D''
TaskG.EXPRI=2
TaskG.EXROL=Everybody
TaskG.EXTYPE=0
TaskG.NAM=<%\=in1.caseName%>_G
TaskG.PRI=2
TaskG.ROL=Everybody
TaskG.SKIP_TASK_LIST=false
TaskG.TYPE=0
TaskG.customFields.varchar.5=in1.taskStructureInfo
TaskH.EXPRI=2
TaskH.EXROL=Everybody
TaskH.EXTYPE=0
TaskH.NAM=<%\=in1.caseName%>_H
TaskH.PRI=2
TaskH.ROL=Everybody
TaskH.SKIP_TASK_LIST=false
TaskH.TYPE=0
TaskH.customFields.varchar.5=in1.taskStructureInfo
TaskI.EXPRI=2
TaskI.EXROL=Everybody
TaskI.EXTYPE=0
TaskI.NAM=<%\=in1.caseName%>_I
TaskI.PRI=2
TaskI.ROL=Everybody
TaskI.SKIP_TASK_LIST=false
TaskI.TYPE=0
TaskI.customFields.varchar.5=in1.taskStructureInfo
TaskJ.EXPRI=2
TaskJ.EXROL=Everybody
TaskJ.EXTYPE=0
TaskJ.NAM=<%\=in1.caseName%>_J
TaskJ.PRI=2
TaskJ.ROL=Everybody
TaskJ.SKIP_TASK_LIST=false
TaskJ.TYPE=0
TaskJ.customFields.varchar.5=in1.taskStructureInfo' #txt
Dt0 f3 taskAction 'ivy.case.setName(engine.expandMacros("<%=in1.caseName%>"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_A"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(0));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_B"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(1));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskC.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_C"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskD.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_D"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(3));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskE.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_E"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''1D'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskF.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_F"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''2D'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskG.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_G"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (''3D'').toNumber());
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskH.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_H"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskI.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_I"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskJ.ivp");
taskDef.setName(engine.expandMacros("<%=in1.caseName%>_J"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Everybody");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField5(in1.taskStructureInfo);
taskDefinitions.add(taskDef);
' #txt
Dt0 f3 type portalKit_test.DataCreationData #txt
Dt0 f3 template "" #txt
Dt0 f3 274 322 28 28 14 0 #rect
Dt0 f3 @|TaskSwitchIcon #fIcon
Dt0 f5 type portalKit_test.DataCreationData #txt
Dt0 f5 467 243 26 26 14 0 #rect
Dt0 f5 @|EndIcon #fIcon
Dt0 f6 type portalKit_test.DataCreationData #txt
Dt0 f6 467 275 26 26 14 0 #rect
Dt0 f6 @|EndIcon #fIcon
Dt0 f7 type portalKit_test.DataCreationData #txt
Dt0 f7 467 307 26 26 14 0 #rect
Dt0 f7 @|EndIcon #fIcon
Dt0 f8 type portalKit_test.DataCreationData #txt
Dt0 f8 467 371 26 26 14 0 #rect
Dt0 f8 @|EndIcon #fIcon
Dt0 f9 type portalKit_test.DataCreationData #txt
Dt0 f9 467 339 26 26 14 0 #rect
Dt0 f9 @|EndIcon #fIcon
Dt0 f10 type portalKit_test.DataCreationData #txt
Dt0 f10 467 403 26 26 14 0 #rect
Dt0 f10 @|EndIcon #fIcon
Dt0 f11 type portalKit_test.DataCreationData #txt
Dt0 f11 467 467 26 26 14 0 #rect
Dt0 f11 @|EndIcon #fIcon
Dt0 f12 type portalKit_test.DataCreationData #txt
Dt0 f12 467 435 26 26 14 0 #rect
Dt0 f12 @|EndIcon #fIcon
Dt0 f13 type portalKit_test.DataCreationData #txt
Dt0 f13 467 499 26 26 14 0 #rect
Dt0 f13 @|EndIcon #fIcon
Dt0 f14 expr data #txt
Dt0 f14 outCond ivp=="TaskB.ivp" #txt
Dt0 f14 298 332 467 261 #arcP
Dt0 f15 expr data #txt
Dt0 f15 outCond ivp=="TaskC.ivp" #txt
Dt0 f15 299 333 467 291 #arcP
Dt0 f16 expr data #txt
Dt0 f16 outCond ivp=="TaskD.ivp" #txt
Dt0 f16 301 335 467 321 #arcP
Dt0 f17 expr data #txt
Dt0 f17 outCond ivp=="TaskE.ivp" #txt
Dt0 f17 301 337 467 350 #arcP
Dt0 f18 expr data #txt
Dt0 f18 outCond ivp=="TaskF.ivp" #txt
Dt0 f18 299 339 467 380 #arcP
Dt0 f19 expr data #txt
Dt0 f19 outCond ivp=="TaskG.ivp" #txt
Dt0 f19 298 340 467 411 #arcP
Dt0 f20 expr data #txt
Dt0 f20 outCond ivp=="TaskH.ivp" #txt
Dt0 f20 297 341 468 441 #arcP
Dt0 f21 expr data #txt
Dt0 f21 outCond ivp=="TaskI.ivp" #txt
Dt0 f21 296 342 469 472 #arcP
Dt0 f22 expr data #txt
Dt0 f22 outCond ivp=="TaskJ.ivp" #txt
Dt0 f22 295 343 470 503 #arcP
Dt0 f23 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f23 actionTable 'out=in;
' #txt
Dt0 f23 actionCode 'in.caseName = in.taskStructureInfo + "_" + ivy.case.getId();' #txt
Dt0 f23 type portalKit_test.DataCreationData #txt
Dt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case name</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f23 182 324 36 24 -33 -36 #rect
Dt0 f23 @|StepIcon #fIcon
Dt0 f4 expr out #txt
Dt0 f4 type portalKit_test.DataCreationData #txt
Dt0 f4 var in1 #txt
Dt0 f4 218 336 274 336 #arcP
Dt0 f25 outLink start.ivp #txt
Dt0 f25 type portalKit_test.DataCreationData #txt
Dt0 f25 inParamDecl '<> param;' #txt
Dt0 f25 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f25 guid 153CAEDE745C4D37 #txt
Dt0 f25 requestEnabled true #txt
Dt0 f25 triggerEnabled false #txt
Dt0 f25 callSignature start() #txt
Dt0 f25 persist false #txt
Dt0 f25 startName 'Create test data' #txt
Dt0 f25 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f25 showInStartList 1 #txt
Dt0 f25 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Dt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f25 @C|.responsibility Everybody #txt
Dt0 f25 35 83 26 26 14 0 #rect
Dt0 f25 @|StartRequestIcon #fIcon
Dt0 f28 type portalKit_test.DataCreationData #txt
Dt0 f28 processCall 'Start Processes/DataCreation:createTasks(String)' #txt
Dt0 f28 doCall true #txt
Dt0 f28 requestActionDecl '<java.lang.String taskStructureInfo> param;
' #txt
Dt0 f28 requestMappingAction 'param.taskStructureInfo=in.handler.getNextTaskStructureInfo();
' #txt
Dt0 f28 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f28 responseMappingAction 'out=in;
' #txt
Dt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DataCreation</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f28 502 84 36 24 20 -2 #rect
Dt0 f28 @|TriggerIcon #fIcon
Dt0 f30 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f30 actionTable 'out=in;
' #txt
Dt0 f30 actionCode 'import ch.ivy.addon.portalkit.test.util.DataCreationHandler;
in.handler = new DataCreationHandler(in.numOfCats, in.numOfSubCats, in.numOfCasesPerSubCat);
' #txt
Dt0 f30 type portalKit_test.DataCreationData #txt
Dt0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init handler</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f30 214 84 36 24 -29 -36 #rect
Dt0 f30 @|StepIcon #fIcon
Dt0 f32 type portalKit_test.DataCreationData #txt
Dt0 f32 282 82 28 28 14 0 #rect
Dt0 f32 @|AlternativeIcon #fIcon
Dt0 f33 expr out #txt
Dt0 f33 250 96 282 96 #arcP
Dt0 f29 expr in #txt
Dt0 f29 outCond in.handler.createMoreTasks() #txt
Dt0 f29 310 96 502 96 #arcP
Dt0 f34 type portalKit_test.DataCreationData #txt
Dt0 f34 571 139 26 26 14 0 #rect
Dt0 f34 @|EndIcon #fIcon
Dt0 f35 expr in #txt
Dt0 f35 296 110 571 152 #arcP
Dt0 f35 1 296 152 #addKink
Dt0 f35 1 0.4965635738831616 0 0 #arcLabel
Dt0 f27 targetWindow NEW #txt
Dt0 f27 targetDisplay TOP #txt
Dt0 f27 startMethod start() #txt
Dt0 f27 type portalKit_test.DataCreationData #txt
Dt0 f27 requestActionDecl '<> param;' #txt
Dt0 f27 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f27 responseMappingAction 'out=in;
' #txt
Dt0 f27 isAsynch false #txt
Dt0 f27 isInnerRd false #txt
Dt0 f27 382 252 36 24 20 -2 #rect
Dt0 f27 @|RichDialogIcon #fIcon
Dt0 f36 expr data #txt
Dt0 f36 outCond ivp=="TaskA.ivp" #txt
Dt0 f36 297 331 382 276 #arcP
Dt0 f2 expr out #txt
Dt0 f2 418 253 468 222 #arcP
Dt0 f41 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f41 actionTable 'out=in;
' #txt
Dt0 f41 actionCode Thread.sleep(10); #txt
Dt0 f41 type portalKit_test.DataCreationData #txt
Dt0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sleep a bit before
triggering new process</name>
        <nameStyle>41,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f41 326 36 36 24 28 -17 #rect
Dt0 f41 @|StepIcon #fIcon
Dt0 f42 expr out #txt
Dt0 f42 520 84 362 48 #arcP
Dt0 f42 1 520 48 #addKink
Dt0 f42 1 0.48487282617628363 0 0 #arcLabel
Dt0 f26 expr out #txt
Dt0 f26 326 48 296 82 #arcP
Dt0 f26 1 296 48 #addKink
Dt0 f26 1 0.48487282617628363 0 0 #arcLabel
Dt0 f43 expr out #txt
Dt0 f43 61 336 182 336 #arcP
Dt0 f24 targetWindow NEW:card: #txt
Dt0 f24 targetDisplay TOP #txt
Dt0 f24 richDialogId ch.ivy.addon.portalkit.test.DataCreation #txt
Dt0 f24 startMethod start() #txt
Dt0 f24 type portalKit_test.DataCreationData #txt
Dt0 f24 requestActionDecl '<> param;' #txt
Dt0 f24 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f24 responseMappingAction 'out=in;
out.numOfCasesPerSubCat=result.numOfCasesPerSubCat;
out.numOfCats=result.numOfCats;
out.numOfSubCats=result.numOfSubCats;
' #txt
Dt0 f24 windowConfiguration '* ' #txt
Dt0 f24 isAsynch false #txt
Dt0 f24 isInnerRd false #txt
Dt0 f24 userContext '* ' #txt
Dt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>input number of tasks</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f24 126 84 36 24 -70 -34 #rect
Dt0 f24 @|RichDialogIcon #fIcon
Dt0 f37 expr out #txt
Dt0 f37 61 96 126 96 #arcP
Dt0 f31 expr out #txt
Dt0 f31 162 96 214 96 #arcP
>Proto Dt0 .type portalKit_test.DataCreationData #txt
>Proto Dt0 .processKind NORMAL #txt
>Proto Dt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f14 head f5 mainIn #connect
Dt0 f15 head f6 mainIn #connect
Dt0 f16 head f7 mainIn #connect
Dt0 f17 head f9 mainIn #connect
Dt0 f18 head f8 mainIn #connect
Dt0 f19 head f10 mainIn #connect
Dt0 f20 head f12 mainIn #connect
Dt0 f21 head f11 mainIn #connect
Dt0 f22 head f13 mainIn #connect
Dt0 f23 mainOut f4 tail #connect
Dt0 f4 head f3 in #connect
Dt0 f30 mainOut f33 tail #connect
Dt0 f33 head f32 in #connect
Dt0 f32 out f29 tail #connect
Dt0 f29 head f28 mainIn #connect
Dt0 f32 out f35 tail #connect
Dt0 f35 head f34 mainIn #connect
Dt0 f3 out f36 tail #connect
Dt0 f36 head f27 mainIn #connect
Dt0 f3 out f14 tail #connect
Dt0 f3 out f15 tail #connect
Dt0 f3 out f16 tail #connect
Dt0 f3 out f17 tail #connect
Dt0 f3 out f18 tail #connect
Dt0 f3 out f19 tail #connect
Dt0 f3 out f20 tail #connect
Dt0 f3 out f21 tail #connect
Dt0 f3 out f22 tail #connect
Dt0 f27 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f28 mainOut f42 tail #connect
Dt0 f42 head f41 mainIn #connect
Dt0 f41 mainOut f26 tail #connect
Dt0 f26 head f32 in #connect
Dt0 f0 mainOut f43 tail #connect
Dt0 f43 head f23 mainIn #connect
Dt0 f25 mainOut f37 tail #connect
Dt0 f37 head f24 mainIn #connect
Dt0 f24 mainOut f31 tail #connect
Dt0 f31 head f30 mainIn #connect
