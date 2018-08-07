[Ivy]
[>Created: Wed Nov 18 19:02:56 ICT 2015]
14B2E443F3CCFF94 3.18 #module
>Proto >Proto Collection #zClass
gs0 generateManyCasesTaks Big #zClass
gs0 B #cInfo
gs0 #process
gs0 @TextInP .resExport .resExport #zField
gs0 @TextInP .type .type #zField
gs0 @TextInP .processKind .processKind #zField
gs0 @AnnotationInP-0n ai ai #zField
gs0 @TextInP .xml .xml #zField
gs0 @TextInP .responsibility .responsibility #zField
gs0 @StartRequest f0 '' #zField
gs0 @EndTask f1 '' #zField
gs0 @TaskSwitch f4 '' #zField
gs0 @GridStep f10 '' #zField
gs0 @GridStep f8 '' #zField
gs0 @EndTask f2 '' #zField
gs0 @Alternative f12 '' #zField
gs0 @PushWFArc f9 '' #zField
gs0 @PushWFArc f5 '' #zField
gs0 @TkArc f6 '' #zField
gs0 @Trigger f14 '' #zField
gs0 @PushWFArc f7 '' #zField
gs0 @PushWFArc f15 '' #zField
gs0 @PushWFArc f21 '' #zField
gs0 @GridStep f17 '' #zField
gs0 @StartRequest f24 '' #zField
gs0 @GridStep f25 '' #zField
gs0 @PushWFArc f26 '' #zField
gs0 @GridStep f28 '' #zField
gs0 @GridStep f29 '' #zField
gs0 @StartRequest f30 '' #zField
gs0 @StartRequest f31 '' #zField
gs0 @StartRequest f32 '' #zField
gs0 @PushWFArc f34 '' #zField
gs0 @PushWFArc f35 '' #zField
gs0 @GridStep f36 '' #zField
gs0 @PushWFArc f37 '' #zField
gs0 @Alternative f16 '' #zField
gs0 @PushWFArc f18 '' #zField
gs0 @EndRequest f19 '' #zField
gs0 @PushWFArc f20 '' #zField
gs0 @GridStep f23 '' #zField
gs0 @PushWFArc f27 '' #zField
gs0 @PushWFArc f3 '' #zField
gs0 @Alternative f33 '' #zField
gs0 @PushWFArc f38 '' #zField
gs0 @PushWFArc f11 '' #zField
gs0 @Alternative f43 '' #zField
gs0 @PushWFArc f44 '' #zField
gs0 @Alternative f40 '' #zField
gs0 @PushWFArc f45 '' #zField
gs0 @Alternative f46 '' #zField
gs0 @PushWFArc f47 '' #zField
gs0 @EndRequest f42 '' #zField
gs0 @PushWFArc f48 '' #zField
gs0 @PushWFArc f49 '' #zField
gs0 @EndRequest f51 '' #zField
gs0 @PushWFArc f52 '' #zField
gs0 @PushWFArc f58 '' #zField
gs0 @RichDialog f13 '' #zField
gs0 @Alternative f54 '' #zField
gs0 @PushWFArc f53 '' #zField
gs0 @PushWFArc f55 '' #zField
gs0 @PushWFArc f50 '' #zField
gs0 @PushWFArc f41 '' #zField
gs0 @PushWFArc f39 '' #zField
gs0 @PushWFArc f62 '' #zField
gs0 @PushWFArc f22 '' #zField
gs0 @PushWFArc f63 '' #zField
>Proto gs0 gs0 generateManyCasesTaks #zField
gs0 f0 outLink test1_10catx5subcatx50tasks.ivp #txt
gs0 f0 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f0 inParamDecl '<> param;' #txt
gs0 f0 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f0 guid 14AC3E3D0DB9790E #txt
gs0 f0 requestEnabled true #txt
gs0 f0 triggerEnabled false #txt
gs0 f0 callSignature test1_10catx5subcatx50tasks() #txt
gs0 f0 persist false #txt
gs0 f0 startName 'Internal Generate 10catx5subcatx50tasks' #txt
gs0 f0 taskData '#
#Fri Apr 24 16:40:14 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
gs0 f0 caseData '#
#Fri Apr 24 16:40:14 ICT 2015
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
gs0 f0 showInStartList 1 #txt
gs0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>test1_10catx5subcatx50tasks.ivp</name>
        <nameStyle>31,5,7
</nameStyle>
        <desc>10 Main Categories
5 Subcategories each Category
50 Ivy Tasks each Subcategory</desc>
    </language>
</elementInfo>
' #txt
gs0 f0 @C|.responsibility Everybody #txt
gs0 f0 43 75 26 26 14 0 #rect
gs0 f0 @|StartRequestIcon #fIcon
gs0 f1 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f1 243 723 26 26 14 0 #rect
gs0 f1 @|EndIcon #fIcon
gs0 f4 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f4 actionTable 'out=in1;
' #txt
gs0 f4 outTypes "ch.ivy.add.portalkit.task.TaskGenerationData","ch.ivy.add.portalkit.task.TaskGenerationData" #txt
gs0 f4 outLinks "TaskB.ivp","TaskA.ivp" #txt
gs0 f4 caseData '#
#Wed Jan 14 09:28:19 ICT 2015
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
gs0 f4 taskData '#
#Wed Jan 14 09:28:19 ICT 2015
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
' #txt
gs0 f4 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskB.ivp");
taskDef.setAutoStartTask(false);
taskDef.setActivator("SYSTEM");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setAutoStartTask(true);
taskDef.setActivator("SYSTEM");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
gs0 f4 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f4 template "" #txt
gs0 f4 242 586 28 28 14 0 #rect
gs0 f4 @|TaskSwitchIcon #fIcon
gs0 f10 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f10 actionTable 'out=in;
' #txt
gs0 f10 actionCode 'import java.util.ArrayList;

//init
if(ivy.session.getSessionUser()!=null && ivy.session.getSessionUser().getName()!=null){
	in.activator = ivy.session.getSessionUser().getName();
}else{
	in.activator = "";
}
in.categoryCounter = 1;
in.subCatCounter = 1;

in.categoryNumber = 10;
in.subCatNumber = 5;

in.taskCounter = 50;
in.pathCounter = 1;
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();' #txt
gs0 f10 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f10 38 132 36 24 20 -2 #rect
gs0 f10 @|StepIcon #fIcon
gs0 f8 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f8 actionTable 'out=in;
' #txt
gs0 f8 actionCode 'in.pathCounter+=1;
if(in.subCatCounter==in.subCatNumber){
	in.subCatCounter=1;
	in.categoryCounter++;
}else{
	in.subCatCounter++;	
}
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();

ivy.log.debug(in.structuredData);' #txt
gs0 f8 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter + 1</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f8 382 588 36 24 -4 16 #rect
gs0 f8 @|StepIcon #fIcon
gs0 f2 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f2 51 491 26 26 14 0 #rect
gs0 f2 @|EndIcon #fIcon
gs0 f12 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter &lt;  count of subcategories
</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f12 242 490 28 28 14 0 #rect
gs0 f12 @|AlternativeIcon #fIcon
gs0 f9 expr data #txt
gs0 f9 outCond ivp=="TaskB.ivp" #txt
gs0 f9 270 600 382 600 #arcP
gs0 f5 expr out #txt
gs0 f5 400 588 270 504 #arcP
gs0 f5 1 400 504 #addKink
gs0 f5 1 0.21717601638785222 0 0 #arcLabel
gs0 f6 expr in #txt
gs0 f6 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f6 var in1 #txt
gs0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f6 256 518 256 586 #arcP
gs0 f6 0 0.5 15 0 #arcLabel
gs0 f14 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f14 processCall 'Business Processes/createARangeOfTasks:splitTaskGeneration(ch.ivy.add.portalkit.task.TaskGenerationDataRange)' #txt
gs0 f14 doCall true #txt
gs0 f14 requestActionDecl '<ch.ivy.add.portalkit.task.TaskGenerationDataRange rangeData> param;
' #txt
gs0 f14 requestMappingAction 'param.rangeData.from=0;
param.rangeData.structuredData=in.structuredData;
param.rangeData.to=in.taskCounter;
' #txt
gs0 f14 responseActionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f14 responseMappingAction 'out=in;
' #txt
gs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate tasks</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f14 238 660 36 24 20 7 #rect
gs0 f14 @|TriggerIcon #fIcon
gs0 f7 expr out #txt
gs0 f7 256 684 256 723 #arcP
gs0 f15 expr data #txt
gs0 f15 outCond ivp=="TaskA.ivp" #txt
gs0 f15 256 614 256 660 #arcP
gs0 f21 expr out #txt
gs0 f21 56 101 56 132 #arcP
gs0 f17 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f17 actionTable 'out=in;
' #txt
gs0 f17 actionCode 'import ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils;
import ch.ivy.addon.portalkit.test.util.TaskUtils;


TaskConcurrencyUtils.setInProcess(true);
TaskUtils.destroyAllCase();' #txt
gs0 f17 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear all tasks &amp; cases</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f17 238 428 36 24 20 -2 #rect
gs0 f17 @|StepIcon #fIcon
gs0 f24 outLink test2_100catx2subcatx100tasks.ivp #txt
gs0 f24 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f24 inParamDecl '<> param;' #txt
gs0 f24 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f24 guid 14AE6003A97926A1 #txt
gs0 f24 requestEnabled true #txt
gs0 f24 triggerEnabled false #txt
gs0 f24 callSignature test2_100catx2subcatx100tasks() #txt
gs0 f24 persist false #txt
gs0 f24 startName 'Internal Generate 100catx2subcatx100tasks' #txt
gs0 f24 taskData '#
#Fri Apr 24 16:40:22 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
gs0 f24 caseData '#
#Fri Apr 24 16:40:22 ICT 2015
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
gs0 f24 showInStartList 1 #txt
gs0 f24 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
gs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>test2_100catx2subcatx100tasks.ivp</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f24 @C|.responsibility Everybody #txt
gs0 f24 283 83 26 26 14 0 #rect
gs0 f24 @|StartRequestIcon #fIcon
gs0 f25 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f25 actionTable 'out=in;
out.activator=ivy.session.getSessionUserName();
out.taskCounter=0;
' #txt
gs0 f25 actionCode 'import java.util.ArrayList;

//init
in.activator = ivy.session.getSessionUser().getName();
in.categoryCounter = 1;
in.subCatCounter = 1;

in.categoryNumber = 100;
in.subCatNumber = 2;

in.taskCounter = 100;
in.pathCounter = 1;
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();' #txt
gs0 f25 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f25 278 132 36 24 20 -2 #rect
gs0 f25 @|StepIcon #fIcon
gs0 f26 expr out #txt
gs0 f26 296 109 296 132 #arcP
gs0 f28 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f28 actionTable 'out=in;
out.activator=ivy.session.getSessionUserName();
out.taskCounter=0;
' #txt
gs0 f28 actionCode 'import java.util.ArrayList;

//init
in.activator = ivy.session.getSessionUser().getName();
in.categoryCounter = 1;
in.subCatCounter = 1;

in.categoryNumber = 100;
in.subCatNumber = 2;

in.taskCounter = 500;
in.pathCounter = 1;
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();' #txt
gs0 f28 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f28 542 140 36 24 20 -2 #rect
gs0 f28 @|StepIcon #fIcon
gs0 f29 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f29 actionTable 'out=in;
out.activator=ivy.session.getSessionUserName();
out.taskCounter=0;
' #txt
gs0 f29 actionCode 'import java.util.ArrayList;

//init
in.activator = ivy.session.getSessionUser().getName();
in.categoryCounter = 1;
in.subCatCounter = 1;

in.categoryNumber = 200;
in.subCatNumber = 2;

in.taskCounter = 1000;
in.pathCounter = 1;
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();' #txt
gs0 f29 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f29 766 140 36 24 20 -2 #rect
gs0 f29 @|StepIcon #fIcon
gs0 f30 outLink test3_100catx2subcatx500tasks.ivp #txt
gs0 f30 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f30 inParamDecl '<> param;' #txt
gs0 f30 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f30 guid 14AE6076FD314928 #txt
gs0 f30 requestEnabled true #txt
gs0 f30 triggerEnabled false #txt
gs0 f30 callSignature test3_100catx2subcatx500tasks() #txt
gs0 f30 persist false #txt
gs0 f30 startName 'Internal Generate 100catx2subcatx500tasks' #txt
gs0 f30 taskData '#
#Fri Apr 24 16:40:28 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
gs0 f30 caseData '#
#Fri Apr 24 16:40:28 ICT 2015
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
gs0 f30 showInStartList 1 #txt
gs0 f30 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
gs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>test3_100catx2subcatx500tasks.ivp</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f30 @C|.responsibility Everybody #txt
gs0 f30 547 91 26 26 14 0 #rect
gs0 f30 @|StartRequestIcon #fIcon
gs0 f31 outLink test4_200catx2subcatx1000tasks.ivp #txt
gs0 f31 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f31 inParamDecl '<> param;' #txt
gs0 f31 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f31 guid 14AE6077B0CE1AC8 #txt
gs0 f31 requestEnabled true #txt
gs0 f31 triggerEnabled false #txt
gs0 f31 callSignature test4_200catx2subcatx1000tasks() #txt
gs0 f31 persist false #txt
gs0 f31 startName 'Internal Generate 200catx2subcatx1000tasks' #txt
gs0 f31 taskData '#
#Fri Apr 24 16:40:36 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
gs0 f31 caseData '#
#Fri Apr 24 16:40:36 ICT 2015
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
gs0 f31 showInStartList 1 #txt
gs0 f31 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
gs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>test4_200catx2subcatx1000tasks.ivp</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f31 @C|.responsibility Everybody #txt
gs0 f31 771 91 26 26 14 0 #rect
gs0 f31 @|StartRequestIcon #fIcon
gs0 f32 outLink test5_100catx10subcatx100tasks.ivp #txt
gs0 f32 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f32 inParamDecl '<> param;' #txt
gs0 f32 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f32 guid 14AE6079C1BE8607 #txt
gs0 f32 requestEnabled true #txt
gs0 f32 triggerEnabled false #txt
gs0 f32 callSignature test5_100catx10subcatx100tasks() #txt
gs0 f32 persist false #txt
gs0 f32 startName 'Internal Generate 100catx10subcatx100tasks' #txt
gs0 f32 taskData '#
#Fri Apr 24 16:40:47 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
gs0 f32 caseData '#
#Fri Apr 24 16:40:47 ICT 2015
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
gs0 f32 showInStartList 1 #txt
gs0 f32 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
gs0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>test5_100catx10subcatx100tasks.ivp</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f32 @C|.responsibility Everybody #txt
gs0 f32 1027 91 26 26 14 0 #rect
gs0 f32 @|StartRequestIcon #fIcon
gs0 f34 expr out #txt
gs0 f34 560 117 560 140 #arcP
gs0 f35 expr out #txt
gs0 f35 784 117 784 140 #arcP
gs0 f36 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f36 actionTable 'out=in;
out.activator=ivy.session.getSessionUserName();
out.taskCounter=0;
' #txt
gs0 f36 actionCode 'import java.util.ArrayList;

//init
in.activator = ivy.session.getSessionUser().getName();
in.categoryCounter = 1;
in.subCatCounter = 1;

in.categoryNumber = 100;
in.subCatNumber = 10;

in.taskCounter = 100;
in.pathCounter = 1;
in.structuredData = "CAT" + in.categoryCounter.toString() + "/SUBCAT" + in.subCatCounter.toString();' #txt
gs0 f36 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f36 1022 148 36 24 20 -2 #rect
gs0 f36 @|StepIcon #fIcon
gs0 f37 expr out #txt
gs0 f37 1040 117 1040 148 #arcP
gs0 f16 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>another process is running ?</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f16 282 194 28 28 14 0 #rect
gs0 f16 @|AlternativeIcon #fIcon
gs0 f18 expr out #txt
gs0 f18 296 156 296 194 #arcP
gs0 f18 0 0.525748133922916 0 0 #arcLabel
gs0 f19 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f19 template "Message.html" #txt
gs0 f19 195 195 26 26 14 0 #rect
gs0 f19 @|EndRequestIcon #fIcon
gs0 f20 expr in #txt
gs0 f20 outCond ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils.isInProcess() #txt
gs0 f20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f20 282 208 221 208 #arcP
gs0 f20 0 0.4426229508196721 0 -11 #arcLabel
gs0 f23 actionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f23 actionTable 'out=in;
' #txt
gs0 f23 actionCode 'import ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils;

TaskConcurrencyUtils.setInProcess(false);' #txt
gs0 f23 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>open 
process
 lock</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f23 110 492 36 24 20 -2 #rect
gs0 f23 @|StepIcon #fIcon
gs0 f27 expr in #txt
gs0 f27 outCond 'in.pathCounter > in.categoryNumber * in.subCatNumber' #txt
gs0 f27 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f27 242 504 146 504 #arcP
gs0 f27 0 0.3645833333333333 0 11 #arcLabel
gs0 f3 expr out #txt
gs0 f3 110 504 77 504 #arcP
gs0 f33 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>another process is running ?</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f33 42 194 28 28 6 19 #rect
gs0 f33 @|AlternativeIcon #fIcon
gs0 f38 expr out #txt
gs0 f38 56 156 56 194 #arcP
gs0 f38 0 0.7044236841629367 0 0 #arcLabel
gs0 f11 expr in #txt
gs0 f11 outCond ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils.isInProcess() #txt
gs0 f11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f11 70 208 195 208 #arcP
gs0 f11 0 0.464 0 -14 #arcLabel
gs0 f43 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>another process
 is running ?</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f43 546 194 28 28 14 0 #rect
gs0 f43 @|AlternativeIcon #fIcon
gs0 f44 expr out #txt
gs0 f44 560 164 560 194 #arcP
gs0 f44 0 0.7839083535168448 0 0 #arcLabel
gs0 f40 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>another process is running ?</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f40 770 194 28 28 14 0 #rect
gs0 f40 @|AlternativeIcon #fIcon
gs0 f45 expr out #txt
gs0 f45 784 164 784 194 #arcP
gs0 f45 0 0.7959467929693852 0 0 #arcLabel
gs0 f46 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>another process
 is running ?</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f46 1026 194 28 28 14 0 #rect
gs0 f46 @|AlternativeIcon #fIcon
gs0 f47 expr out #txt
gs0 f47 1040 172 1040 194 #arcP
gs0 f47 0 0.8126208169379362 0 0 #arcLabel
gs0 f42 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f42 template "Message.html" #txt
gs0 f42 675 195 26 26 14 0 #rect
gs0 f42 @|EndRequestIcon #fIcon
gs0 f48 expr in #txt
gs0 f48 outCond ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils.isInProcess() #txt
gs0 f48 574 208 675 208 #arcP
gs0 f48 0 0.5016330166078694 0 0 #arcLabel
gs0 f49 expr in #txt
gs0 f49 outCond ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils.isInProcess() #txt
gs0 f49 770 208 701 208 #arcP
gs0 f51 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f51 template "Message.html" #txt
gs0 f51 1171 195 26 26 14 0 #rect
gs0 f51 @|EndRequestIcon #fIcon
gs0 f52 expr in #txt
gs0 f52 outCond ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils.isInProcess() #txt
gs0 f52 1054 208 1171 208 #arcP
gs0 f58 expr out #txt
gs0 f58 256 452 256 490 #arcP
gs0 f13 targetWindow NEW:card: #txt
gs0 f13 targetDisplay TOP #txt
gs0 f13 richDialogId ch.ivy.addon.portalkit.test.Confirmation #txt
gs0 f13 startMethod start(String,String) #txt
gs0 f13 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f13 requestActionDecl '<String message, String title> param;' #txt
gs0 f13 requestActionCode 'param.message = String.format("Are you sure to start generating %d categories x %d subcategories x %d tasks ?", in.categoryNumber, in.subCatNumber, in.taskCounter);' #txt
gs0 f13 responseActionDecl 'ch.ivy.add.portalkit.task.TaskGenerationData out;
' #txt
gs0 f13 responseMappingAction 'out=in;
out.isStart=result.approved;
' #txt
gs0 f13 windowConfiguration '* ' #txt
gs0 f13 isAsynch false #txt
gs0 f13 isInnerRd false #txt
gs0 f13 userContext '* ' #txt
gs0 f13 238 316 36 24 20 -2 #rect
gs0 f13 @|RichDialogIcon #fIcon
gs0 f54 type ch.ivy.add.portalkit.task.TaskGenerationData #txt
gs0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>continue ?</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f54 242 370 28 28 14 0 #rect
gs0 f54 @|AlternativeIcon #fIcon
gs0 f53 expr in #txt
gs0 f53 1040 222 256 316 #arcP
gs0 f53 1 1040 288 #addKink
gs0 f53 2 256 288 #addKink
gs0 f53 1 0.40546486158727557 0 0 #arcLabel
gs0 f55 expr in #txt
gs0 f55 784 222 256 316 #arcP
gs0 f55 1 784 288 #addKink
gs0 f55 2 256 288 #addKink
gs0 f55 1 0.48248765536375504 0 0 #arcLabel
gs0 f50 expr in #txt
gs0 f50 560 222 256 316 #arcP
gs0 f50 1 560 288 #addKink
gs0 f50 2 256 288 #addKink
gs0 f50 1 0.456884847970273 0 0 #arcLabel
gs0 f41 expr in #txt
gs0 f41 56 222 256 316 #arcP
gs0 f41 1 56 288 #addKink
gs0 f41 2 256 288 #addKink
gs0 f41 1 0.48509919656260025 0 0 #arcLabel
gs0 f39 expr in #txt
gs0 f39 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
gs0 f39 296 222 256 316 #arcP
gs0 f39 1 296 256 #addKink
gs0 f39 2 256 288 #addKink
gs0 f39 0 0.7142857142857143 11 0 #arcLabel
gs0 f62 expr out #txt
gs0 f62 256 340 256 370 #arcP
gs0 f62 0 0.8197879517423788 0 0 #arcLabel
gs0 f22 expr in #txt
gs0 f22 outCond in.isStart #txt
gs0 f22 256 398 256 428 #arcP
gs0 f63 expr in #txt
gs0 f63 242 384 64 491 #arcP
gs0 f63 1 64 384 #addKink
gs0 f63 0 0.8100868364730212 0 0 #arcLabel
>Proto gs0 .type ch.ivy.add.portalkit.task.TaskGenerationData #txt
>Proto gs0 .processKind NORMAL #txt
>Proto gs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>1280</swimlaneSize>
    <swimlaneSize>376</swimlaneSize>
    <swimlaneColor>-3355393</swimlaneColor>
    <swimlaneColor>-13057</swimlaneColor>
</elementInfo>
' #txt
>Proto gs0 0 0 32 24 18 0 #rect
>Proto gs0 @|BIcon #fIcon
gs0 f9 head f8 mainIn #connect
gs0 f8 mainOut f5 tail #connect
gs0 f5 head f12 in #connect
gs0 f6 head f4 in #connect
gs0 f4 out f9 tail #connect
gs0 f14 mainOut f7 tail #connect
gs0 f7 head f1 mainIn #connect
gs0 f4 out f15 tail #connect
gs0 f15 head f14 mainIn #connect
gs0 f0 mainOut f21 tail #connect
gs0 f21 head f10 mainIn #connect
gs0 f24 mainOut f26 tail #connect
gs0 f26 head f25 mainIn #connect
gs0 f30 mainOut f34 tail #connect
gs0 f34 head f28 mainIn #connect
gs0 f31 mainOut f35 tail #connect
gs0 f35 head f29 mainIn #connect
gs0 f32 mainOut f37 tail #connect
gs0 f37 head f36 mainIn #connect
gs0 f25 mainOut f18 tail #connect
gs0 f18 head f16 in #connect
gs0 f16 out f20 tail #connect
gs0 f20 head f19 mainIn #connect
gs0 f12 out f27 tail #connect
gs0 f27 head f23 mainIn #connect
gs0 f12 out f6 tail #connect
gs0 f23 mainOut f3 tail #connect
gs0 f3 head f2 mainIn #connect
gs0 f10 mainOut f38 tail #connect
gs0 f38 head f33 in #connect
gs0 f33 out f11 tail #connect
gs0 f11 head f19 mainIn #connect
gs0 f28 mainOut f44 tail #connect
gs0 f44 head f43 in #connect
gs0 f29 mainOut f45 tail #connect
gs0 f45 head f40 in #connect
gs0 f36 mainOut f47 tail #connect
gs0 f47 head f46 in #connect
gs0 f43 out f48 tail #connect
gs0 f48 head f42 mainIn #connect
gs0 f40 out f49 tail #connect
gs0 f49 head f42 mainIn #connect
gs0 f46 out f52 tail #connect
gs0 f52 head f51 mainIn #connect
gs0 f17 mainOut f58 tail #connect
gs0 f58 head f12 in #connect
gs0 f46 out f53 tail #connect
gs0 f53 head f13 mainIn #connect
gs0 f40 out f55 tail #connect
gs0 f55 head f13 mainIn #connect
gs0 f43 out f50 tail #connect
gs0 f50 head f13 mainIn #connect
gs0 f33 out f41 tail #connect
gs0 f41 head f13 mainIn #connect
gs0 f16 out f39 tail #connect
gs0 f39 head f13 mainIn #connect
gs0 f13 mainOut f62 tail #connect
gs0 f62 head f54 in #connect
gs0 f54 out f22 tail #connect
gs0 f22 head f17 mainIn #connect
gs0 f54 out f63 tail #connect
gs0 f63 head f2 mainIn #connect
