[Ivy]
15791C23B125821B 7.5.0 #module
>Proto >Proto Collection #zClass
ew0 editWorkflow Big #zClass
ew0 B #cInfo
ew0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
ew0 Ct0 S10 'Sub 1' #zField
ew0 @TextInP .type .type #zField
ew0 @TextInP .processKind .processKind #zField
ew0 @AnnotationInP-0n ai ai #zField
ew0 @MessageFlowInP-0n messageIn messageIn #zField
ew0 @MessageFlowOutP-0n messageOut messageOut #zField
ew0 @TextInP .xml .xml #zField
ew0 @TextInP .responsibility .responsibility #zField
ew0 @StartSub f0 '' #zField
ew0 @GridStep f5 '' #zField
ew0 @UserDialog f7 '' #zField
ew0 @GridStep f9 '' #zField
ew0 @UserDialog f3 '' #zField
ew0 @Alternative f11 '' #zField
ew0 @Alternative f13 '' #zField
ew0 @PushWFArc f14 '' #zField
ew0 @PushWFArc f8 '' #zField
ew0 @GridStep f16 '' #zField
ew0 @PushWFArc f17 '' #zField
ew0 @PushWFArc f15 '' #zField
ew0 @Alternative f18 '' #zField
ew0 @CallSub f20 '' #zField
ew0 @StartSub f21 '' #zField
ew0 @PushWFArc f34 '' #zField
ew0 @EndSub f32 '' #zField
ew0 @Alternative f41 '' #zField
ew0 @EndTask f43 '' #zField
ew0 @PushWFArc f44 '' #zField
ew0 @Alternative f45 '' #zField
ew0 @EndTask f46 '' #zField
ew0 @PushWFArc f47 '' #zField
ew0 @PushWFArc f48 '' #zField
ew0 @PushWFArc f12 '' #zField
ew0 @PushWFArc f19 '' #zField
ew0 @GridStep f22 '' #zField
ew0 @PushWFArc f26 '' #zField
ew0 @Alternative f27 '' #zField
ew0 @PushWFArc f29 '' #zField
ew0 @PushWFArc f4 '' #zField
ew0 @PushWFArc f30 '' #zField
ew0 @PushWFArc f24 '' #zField
ew0 @PushWFArc f25 '' #zField
ew0 @PushWFArc f23 '' #zField
ew0 @GridStep f28 '' #zField
ew0 @PushWFArc f31 '' #zField
ew0 @PushWFArc f6 '' #zField
ew0 @Alternative f35 '' #zField
ew0 @PushWFArc f36 '' #zField
ew0 @TaskSwitchSimple f37 '' #zField
ew0 @TkArc f38 '' #zField
ew0 @PushWFArc f40 '' #zField
ew0 @GridStep f1 '' #zField
ew0 @PushWFArc f39 '' #zField
ew0 @PushWFArc f33 '' #zField
ew0 @GridStep f42 '' #zField
ew0 @PushWFArc f49 '' #zField
ew0 @PushWFArc f10 '' #zField
ew0 @StartSub f50 '' #zField
ew0 @GridStep f52 '' #zField
ew0 @PushWFArc f53 '' #zField
ew0 @PushWFArc f51 '' #zField
ew0 @GridStep f54 '' #zField
ew0 @PushWFArc f55 '' #zField
ew0 @PushWFArc f58 '' #zField
>Proto ew0 ew0 editWorkflow #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @MessageFlowInP-0n messageIn messageIn #zField
Ct0 @MessageFlowOutP-0n messageOut messageOut #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @GridStep f25 '' #zField
Ct0 @GridStep f26 '' #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @PushWFArc f1 '' #zField
Ct0 @PushWFArc f0 '' #zField
Ct0 @PushWFArc f2 '' #zField
>Proto Ct0 Ct0 Component #zField
ew0 S10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>loadWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 S10 312 330 112 44 -37 -8 #rect
ew0 S10 @|BIcon #fIcon
ew0 f0 inParamDecl '<> param;' #txt
ew0 f0 outParamDecl '<> result;' #txt
ew0 f0 callSignature newWorkflow() #txt
ew0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>new Workflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f0 65 249 30 30 -38 17 #rect
ew0 f0 @|StartSubIcon #fIcon
ew0 f5 actionTable 'out=in;
' #txt
ew0 f5 actionCode 'if(!in.isAdhocProcess) {
	in.steps.add(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/start"));
	in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/WorkflowPropertiesStep"));
	in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/FormDefinitionStep"));
	in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/EndStep"));
}
in.discard = false;' #txt
ew0 f5 security system #txt
ew0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init steps</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f5 312 242 112 44 -25 -8 #rect
ew0 f5 @|StepIcon #fIcon
ew0 f7 dialogId ch.ivy.gawfs.workflowCreation.WorkflowDefinition #txt
ew0 f7 startMethod start(gawfs.Data) #txt
ew0 f7 requestActionDecl '<gawfs.Data data> param;' #txt
ew0 f7 requestMappingAction 'param.data=in;
' #txt
ew0 f7 responseActionDecl 'gawfs.Data out;
' #txt
ew0 f7 responseMappingAction 'out=in;
' #txt
ew0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Workflow Settings Dialog</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f7 568 242 144 44 -68 -8 #rect
ew0 f7 @|UserDialogIcon #fIcon
ew0 f9 actionTable 'out=in;
' #txt
ew0 f9 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.gawfs.Helper;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import gawfs.TaskDef;
import ch.ivy.gawfs.Formelement;

//save workflowdescription
ExpressProcessUtils utils = new ExpressProcessUtils();
in.processRepository = utils.saveProcess(in);' #txt
ew0 f9 security system #txt
ew0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save workflow to Database</name>
    </language>
</elementInfo>
' #txt
ew0 f9 1640 242 160 44 -74 -8 #rect
ew0 f9 @|StepIcon #fIcon
ew0 f3 dialogId ch.ivy.gawfs.workflowCreation.FormDefinition #txt
ew0 f3 startMethod start(gawfs.Data) #txt
ew0 f3 requestActionDecl '<gawfs.Data data> param;' #txt
ew0 f3 requestMappingAction 'param.data=in;
' #txt
ew0 f3 responseActionDecl 'gawfs.Data out;
' #txt
ew0 f3 responseMappingAction 'out=result.data;
' #txt
ew0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Workflow Formdefinition Dialog</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f3 960 242 176 44 -85 -8 #rect
ew0 f3 @|UserDialogIcon #fIcon
ew0 f11 1256 248 32 32 0 16 #rect
ew0 f11 @|AlternativeIcon #fIcon
ew0 f13 488 248 32 32 0 16 #rect
ew0 f13 @|AlternativeIcon #fIcon
ew0 f14 expr out #txt
ew0 f14 424 264 488 264 #arcP
ew0 f8 expr in #txt
ew0 f8 520 264 568 264 #arcP
ew0 f16 actionTable 'out=in;
out.backFlag=false;
out.savedFlag=true;
' #txt
ew0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set go back flag</name>
    </language>
</elementInfo>
' #txt
ew0 f16 840 146 112 44 -43 -8 #rect
ew0 f16 @|StepIcon #fIcon
ew0 f17 expr in #txt
ew0 f17 outCond in.backFlag==true #txt
ew0 f17 1272 248 952 168 #arcP
ew0 f17 1 1272 168 #addKink
ew0 f17 1 0.5 0 0 #arcLabel
ew0 f15 expr out #txt
ew0 f15 840 168 504 248 #arcP
ew0 f15 1 504 168 #addKink
ew0 f15 1 0.5 0 0 #arcLabel
ew0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>direct Execution
for AHWF</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f18 1552 248 32 32 -33 -49 #rect
ew0 f18 @|AlternativeIcon #fIcon
ew0 f20 processCall 'Functional Processes/executePredefinedWorkflow:call(List<gawfs.TaskDef>,String,String,ch.ivy.gawfs.enums.ProcessType,String,Boolean,Long)' #txt
ew0 f20 requestActionDecl '<List<gawfs.TaskDef> definedTasks,String processName,String processDescription,ch.ivy.gawfs.enums.ProcessType processType,String processID,Boolean isAdhocProcess,Long originalTaskId> param;' #txt
ew0 f20 requestMappingAction 'param.definedTasks=in.definedTasks;
param.processName=in.processName;
param.processDescription=in.processDescription;
param.processType=in.processType;
param.processID=in.processID;
param.isAdhocProcess=in.isAdhocProcess;
param.originalTaskId=in.originalTaskId;
' #txt
ew0 f20 responseActionDecl 'gawfs.Data out;
' #txt
ew0 f20 responseMappingAction 'out=in;
' #txt
ew0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>direct execution of WF if  AHWF</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f20 1968 338 176 44 -84 -8 #rect
ew0 f20 @|CallSubIcon #fIcon
ew0 f21 inParamDecl '<String workflowID> param;' #txt
ew0 f21 inParamTable 'out.processID=param.workflowID;
' #txt
ew0 f21 outParamDecl '<> result;' #txt
ew0 f21 callSignature editWorkflow(String) #txt
ew0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f21 65 337 30 30 -35 17 #rect
ew0 f21 @|StartSubIcon #fIcon
ew0 f34 368 330 368 286 #arcP
ew0 f34 0 0.37027027027027026 0 0 #arcLabel
ew0 f32 2193 249 30 30 0 15 #rect
ew0 f32 @|EndSubIcon #fIcon
ew0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Discarded?</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f41 776 248 32 32 -31 -33 #rect
ew0 f41 @|AlternativeIcon #fIcon
ew0 f43 777 345 30 30 0 15 #rect
ew0 f43 @|EndIcon #fIcon
ew0 f44 expr in #txt
ew0 f44 792 280 792 345 #arcP
ew0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>exit?</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f45 1192 248 32 32 -12 18 #rect
ew0 f45 @|AlternativeIcon #fIcon
ew0 f46 1193 313 30 30 0 15 #rect
ew0 f46 @|EndIcon #fIcon
ew0 f47 expr in #txt
ew0 f47 outCond in.discard #txt
ew0 f47 1208 280 1208 313 #arcP
ew0 f48 expr out #txt
ew0 f48 1136 264 1192 264 #arcP
ew0 f12 expr in #txt
ew0 f12 1224 264 1256 264 #arcP
ew0 f19 expr out #txt
ew0 f19 2144 360 2208 279 #arcP
ew0 f19 1 2208 360 #addKink
ew0 f19 0 0.7027137765500376 0 0 #arcLabel
ew0 f22 actionTable 'out=in;
' #txt
ew0 f22 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.gawfs.enums.TaskType;
import gawfs.TaskDef;
ExpressProcessUtils expressProcesUtils = new ch.ivy.gawfs.ExpressProcessUtils();
if(!in.#processFolder is initialized) {
	in.processFolder = expressProcesUtils.generateProcessFolder();
}
expressProcesUtils.saveAttachments(in.processFolder, in.definedTasks);' #txt
ew0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle Email attachments</name>
    </language>
</elementInfo>
' #txt
ew0 f22 1328 242 160 44 -73 -8 #rect
ew0 f22 @|StepIcon #fIcon
ew0 f26 expr out #txt
ew0 f26 712 264 776 264 #arcP
ew0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Use default template?</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f27 872 248 32 32 -58 -32 #rect
ew0 f27 @|AlternativeIcon #fIcon
ew0 f29 expr in #txt
ew0 f29 outCond !in.discard #txt
ew0 f29 808 264 872 264 #arcP
ew0 f4 expr in #txt
ew0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f4 904 264 960 264 #arcP
ew0 f4 0 0.48214285714285715 0 -8 #arcLabel
ew0 f30 expr in #txt
ew0 f30 outCond in.isUseDefaultUI #txt
ew0 f30 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f30 888 280 1561 273 #arcP
ew0 f30 1 888 360 #addKink
ew0 f30 2 1496 360 #addKink
ew0 f30 0 0.35 13 0 #arcLabel
ew0 f24 expr in #txt
ew0 f24 1584 264 1640 264 #arcP
ew0 f25 expr in #txt
ew0 f25 1288 264 1328 264 #arcP
ew0 f23 expr out #txt
ew0 f23 1488 264 1552 264 #arcP
ew0 f28 actionTable 'out=in;
' #txt
ew0 f28 actionCode 'in.readyToExecute = false;
in.editFlag = false;' #txt
ew0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set flags</name>
    </language>
</elementInfo>
' #txt
ew0 f28 152 242 112 44 -24 -8 #rect
ew0 f28 @|StepIcon #fIcon
ew0 f31 expr out #txt
ew0 f31 95 264 152 264 #arcP
ew0 f6 expr out #txt
ew0 f6 264 264 312 264 #arcP
ew0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is ready to execute?</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f35 1872 248 32 32 -49 18 #rect
ew0 f35 @|AlternativeIcon #fIcon
ew0 f36 expr out #txt
ew0 f36 1800 264 1872 264 #arcP
ew0 f37 actionTable 'out=in1;
' #txt
ew0 f37 outLinks "TaskA.ivp" #txt
ew0 f37 caseData case.category=CreateExpressWorkflow #txt
ew0 f37 taskData 'TaskA.CATEGORY=CreateExpressWorkflow
TaskA.DESC=<%\=ivy.cms.co("/Dialogs/Tasks/SaveWorkflow/TaskName")%>\: <%\=in1.processName%>
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=ivy.cms.co("/Dialogs/Tasks/SaveWorkflow/TaskName")%>\: <%\=in1.processName%>
TaskA.PRI=2
TaskA.ROL=CREATOR
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
ew0 f37 template "" #txt
ew0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create task to store
progress of workflow</name>
        <nameStyle>41
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f37 1873 153 30 30 24 -18 #rect
ew0 f37 @|TaskSwitchSimpleIcon #fIcon
ew0 f38 expr in #txt
ew0 f38 outCond !in.readyToExecute #txt
ew0 f38 type gawfs.Data #txt
ew0 f38 var in1 #txt
ew0 f38 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f38 1888 248 1888 183 #arcP
ew0 f38 0 0.4461538461538462 -10 0 #arcLabel
ew0 f40 expr in #txt
ew0 f40 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f40 1904 264 2193 264 #arcP
ew0 f40 0 0.410958904109589 0 -10 #arcLabel
ew0 f1 actionTable 'out=in;
' #txt
ew0 f1 actionCode 'in.editFlag = true;' #txt
ew0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set edit flag</name>
    </language>
</elementInfo>
' #txt
ew0 f1 152 330 112 44 -32 -8 #rect
ew0 f1 @|StepIcon #fIcon
ew0 f39 expr out #txt
ew0 f39 95 352 152 352 #arcP
ew0 f33 expr out #txt
ew0 f33 264 352 312 352 #arcP
ew0 f42 actionTable 'out=in;
' #txt
ew0 f42 actionCode 'in.savedFlag = true;' #txt
ew0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set saved flag</name>
    </language>
</elementInfo>
' #txt
ew0 f42 1480 50 112 44 -38 -8 #rect
ew0 f42 @|StepIcon #fIcon
ew0 f49 expr data #txt
ew0 f49 outCond ivp=="TaskA.ivp" #txt
ew0 f49 1888 153 1592 72 #arcP
ew0 f49 1 1888 72 #addKink
ew0 f49 1 0.4647529069767442 0 0 #arcLabel
ew0 f10 expr out #txt
ew0 f10 1480 72 504 248 #arcP
ew0 f10 1 504 72 #addKink
ew0 f10 1 0.4647529069767442 0 0 #arcLabel
ew0 f50 inParamDecl '<Long originalTaskId> param;' #txt
ew0 f50 inParamTable 'out.isAdhocProcess=true;
out.isUseDefaultUI=true;
out.originalTaskId=param.originalTaskId;
out.processType=ch.ivy.gawfs.enums.ProcessType.AD_HOC;
' #txt
ew0 f50 outParamDecl '<> result;' #txt
ew0 f50 callSignature newAdhocWorkflow(Long) #txt
ew0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>newAdhocWorkflow(Long)</name>
    </language>
</elementInfo>
' #txt
ew0 f50 65 57 30 30 -74 26 #rect
ew0 f50 @|StartSubIcon #fIcon
ew0 f52 actionTable 'out=in;
' #txt
ew0 f52 actionCode 'import java.util.Arrays;
import ch.ivyteam.ivy.workflow.ITask;
ITask originalTask = ivy.wf.findTask(in.originalTaskId);
if(originalTask != null) {
	in.processName = ivy.cms.co("/Dialogs/workflowCreation/AdhocProcessName", Arrays.asList(String.valueOf(originalTask.getId()), originalTask.getName()));
}' #txt
ew0 f52 security system #txt
ew0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init Process Name</name>
    </language>
</elementInfo>
' #txt
ew0 f52 152 50 112 44 -51 -8 #rect
ew0 f52 @|StepIcon #fIcon
ew0 f53 expr out #txt
ew0 f53 95 72 152 72 #arcP
ew0 f53 0 0.9833105470266609 0 0 #arcLabel
ew0 f51 expr out #txt
ew0 f51 208 94 208 242 #arcP
ew0 f51 0 0.04035645403300575 0 0 #arcLabel
ew0 f54 actionTable 'out=in;
' #txt
ew0 f54 actionCode 'import ch.ivy.addon.portalkit.util.AdhocUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;

if(in.isAdhocProcess){
	ITask originalTask = ivy.wf.findTask(in.originalTaskId);
	if(originalTask != null){
		TaskUtils.setHidePropertyToHideInPortal(originalTask);
		//Mark task as adhoc express
		originalTask.customFields().stringField(AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString()).set(AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString());
		originalTask.customFields().stringField(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()).set(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString());
	}
	ICase businessCase = originalTask.getCase().ensureBusinessCase();
	AdhocUtils.attachToBusinessCase(ivy.case, businessCase.getId());
}' #txt
ew0 f54 security system #txt
ew0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide original task and&#xD;
attach to business case</name>
    </language>
</elementInfo>
' #txt
ew0 f54 1688 338 160 44 -60 -16 #rect
ew0 f54 @|StepIcon #fIcon
ew0 f55 expr in #txt
ew0 f55 outCond 'in.processType == ch.ivy.gawfs.enums.ProcessType.AD_HOC' #txt
ew0 f55 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f55 1568 280 1688 360 #arcP
ew0 f55 1 1568 360 #addKink
ew0 f55 0 0.3125 13 0 #arcLabel
ew0 f58 expr out #txt
ew0 f58 1848 360 1968 360 #arcP
ew0 f58 0 0.3125 13 0 #arcLabel
>Proto ew0 .type gawfs.Data #txt
>Proto ew0 .processKind CALLABLE_SUB #txt
>Proto ew0 0 0 32 24 18 0 #rect
>Proto ew0 @|BIcon #fIcon
Ct0 f25 actionTable 'out=in;
' #txt
Ct0 f25 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;

ExpressProcessUtils utils = new ExpressProcessUtils();
in.definedTasks = utils.getDefinedTasks(in.processID);' #txt
Ct0 f25 security system #txt
Ct0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get defined tasks</name>
    </language>
</elementInfo>
' #txt
Ct0 f25 152 290 112 44 -48 -8 #rect
Ct0 f25 @|StepIcon #fIcon
Ct0 f26 actionTable 'out=in;
' #txt
Ct0 f26 actionCode 'import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import ch.ivy.gawfs.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.gawfs.ExpressProcessUtils;

ExpressProcess workflow = ExpressServiceRegistry.getProcessService().findById(in.processID) as ExpressProcess;

in.processDescription = workflow.processDescription;
in.processName = workflow.processName;
in.processFolder = workflow.processFolder;
in.processCoOwners = workflow.processCoOwners;
//Old processes won''t have process co owners, so we add process owner as default
if(CollectionUtils.isEmpty(in.processCoOwners)) {
	in.processCoOwners = new ArrayList();
	in.processCoOwners.add(workflow.processOwner);
}

ExpressProcessUtils util = new ExpressProcessUtils();
in.processCoOwnersDisplayName = util.generateResponsibleDisplayName(in.processCoOwners);
in.isUseDefaultUI = workflow.useDefaultUI;

for(ProcessType type : ProcessType.values()) {
	if (type.getValue() == workflow.processType) {
	 	in.processType = type;
	}
}' #txt
Ct0 f26 security system #txt
Ct0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Workflow Credentials</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f26 136 194 144 44 -68 -8 #rect
Ct0 f26 @|StepIcon #fIcon
Ct0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g0 195 91 26 26 0 5 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g1 195 403 26 26 0 5 #rect
Ct0 g1 @|MOGIcon #fIcon
Ct0 f1 expr out #txt
Ct0 f1 208 334 208 403 #arcP
Ct0 f0 208 117 208 194 #arcP
Ct0 f2 expr out #txt
Ct0 f2 208 238 208 290 #arcP
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
ew0 f5 mainOut f14 tail #connect
ew0 f14 head f13 in #connect
ew0 f13 out f8 tail #connect
ew0 f8 head f7 mainIn #connect
ew0 f11 out f17 tail #connect
ew0 f17 head f16 mainIn #connect
ew0 f16 mainOut f15 tail #connect
ew0 f15 head f13 in #connect
ew0 S10 g1 f34 tail #connect
ew0 f34 head f5 mainIn #connect
ew0 f44 head f43 mainIn #connect
ew0 f45 out f47 tail #connect
ew0 f47 head f46 mainIn #connect
ew0 f3 mainOut f48 tail #connect
ew0 f48 head f45 in #connect
ew0 f45 out f12 tail #connect
ew0 f12 head f11 in #connect
ew0 f20 mainOut f19 tail #connect
ew0 f19 head f32 mainIn #connect
ew0 f7 mainOut f26 tail #connect
ew0 f26 head f41 in #connect
ew0 f41 out f29 tail #connect
ew0 f29 head f27 in #connect
ew0 f41 out f44 tail #connect
ew0 f4 head f3 mainIn #connect
ew0 f27 out f30 tail #connect
ew0 f30 head f18 in #connect
ew0 f27 out f4 tail #connect
ew0 f24 head f9 mainIn #connect
ew0 f11 out f25 tail #connect
ew0 f25 head f22 mainIn #connect
ew0 f22 mainOut f23 tail #connect
ew0 f23 head f18 in #connect
ew0 f0 mainOut f31 tail #connect
ew0 f31 head f28 mainIn #connect
ew0 f28 mainOut f6 tail #connect
ew0 f6 head f5 mainIn #connect
ew0 f9 mainOut f36 tail #connect
ew0 f36 head f35 in #connect
ew0 f35 out f38 tail #connect
ew0 f38 head f37 in #connect
ew0 f35 out f40 tail #connect
ew0 f40 head f32 mainIn #connect
ew0 f21 mainOut f39 tail #connect
ew0 f39 head f1 mainIn #connect
ew0 f1 mainOut f33 tail #connect
ew0 f33 head S10 g0 #connect
ew0 f37 out f49 tail #connect
ew0 f49 head f42 mainIn #connect
ew0 f42 mainOut f10 tail #connect
ew0 f10 head f13 in #connect
ew0 f50 mainOut f53 tail #connect
ew0 f53 head f52 mainIn #connect
ew0 f52 mainOut f51 tail #connect
ew0 f51 head f28 mainIn #connect
ew0 f18 out f55 tail #connect
ew0 f55 head f54 mainIn #connect
ew0 f18 out f24 tail #connect
ew0 f54 mainOut f58 tail #connect
ew0 f58 head f20 mainIn #connect
Ct0 f1 head g1 m #connect
Ct0 f25 mainOut f1 tail #connect
Ct0 g0 m f0 tail #connect
Ct0 f0 head f26 mainIn #connect
Ct0 f26 mainOut f2 tail #connect
Ct0 f2 head f25 mainIn #connect
Ct0 0 0 424 512 0 #ivRect
