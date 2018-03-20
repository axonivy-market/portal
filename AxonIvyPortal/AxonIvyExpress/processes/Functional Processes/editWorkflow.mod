[Ivy]
15791C23B125821B 3.20 #module
>Proto >Proto Collection #zClass
ew0 editWorkflow Big #zClass
ew0 B #cInfo
ew0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
ew0 Ct0 S10 'Sub 1' #zField
ew0 @TextInP .resExport .resExport #zField
ew0 @TextInP .type .type #zField
ew0 @TextInP .processKind .processKind #zField
ew0 @AnnotationInP-0n ai ai #zField
ew0 @MessageFlowInP-0n messageIn messageIn #zField
ew0 @MessageFlowOutP-0n messageOut messageOut #zField
ew0 @TextInP .xml .xml #zField
ew0 @TextInP .responsibility .responsibility #zField
ew0 @StartSub f0 '' #zField
ew0 @EndSub f1 '' #zField
ew0 @GridStep f5 '' #zField
ew0 @RichDialog f7 '' #zField
ew0 @GridStep f9 '' #zField
ew0 @RichDialog f3 '' #zField
ew0 @Alternative f11 '' #zField
ew0 @Alternative f13 '' #zField
ew0 @PushWFArc f14 '' #zField
ew0 @PushWFArc f8 '' #zField
ew0 @GridStep f16 '' #zField
ew0 @PushWFArc f17 '' #zField
ew0 @PushWFArc f15 '' #zField
ew0 @Alternative f18 '' #zField
ew0 @CallSub f20 '' #zField
ew0 @PushWFArc f2 '' #zField
ew0 @PushWFArc f23 '' #zField
ew0 @StartSub f21 '' #zField
ew0 @PushWFArc f33 '' #zField
ew0 @PushWFArc f34 '' #zField
ew0 @GridStep f24 '' #zField
ew0 @PushWFArc f25 '' #zField
ew0 @PushWFArc f6 '' #zField
ew0 @EndSub f32 '' #zField
ew0 @Alternative f41 '' #zField
ew0 @PushWFArc f42 '' #zField
ew0 @PushWFArc f4 '' #zField
ew0 @EndTask f43 '' #zField
ew0 @PushWFArc f44 '' #zField
ew0 @Alternative f45 '' #zField
ew0 @EndTask f46 '' #zField
ew0 @PushWFArc f47 '' #zField
ew0 @PushWFArc f48 '' #zField
ew0 @PushWFArc f12 '' #zField
ew0 @PushWFArc f49 '' #zField
ew0 @PushWFArc f10 '' #zField
ew0 @PushWFArc f19 '' #zField
>Proto ew0 ew0 editWorkflow #zField
Ct0 @TextInP .resExport .resExport #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @MessageFlowInP-0n messageIn messageIn #zField
Ct0 @MessageFlowOutP-0n messageOut messageOut #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @GridStep f27 '' #zField
Ct0 @GridStep f28 '' #zField
Ct0 @PushWFArc f29 '' #zField
Ct0 @PushWFArc f30 '' #zField
Ct0 @GridStep f25 '' #zField
Ct0 @PushWFArc f31 '' #zField
Ct0 @GridStep f26 '' #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushWFArc f0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @PushWFArc f1 '' #zField
>Proto Ct0 Ct0 Component #zField
ew0 S10 .resExport export #txt
ew0 S10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>loadWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 S10 312 266 112 44 -37 -8 #rect
ew0 S10 @|BIcon #fIcon
ew0 f0 inParamDecl '<> param;' #txt
ew0 f0 outParamDecl '<> result;
' #txt
ew0 f0 actionDecl 'gawfs.Data out;
' #txt
ew0 f0 callSignature newWorkflow() #txt
ew0 f0 type gawfs.Data #txt
ew0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>new Workflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f0 65 145 30 30 -38 17 #rect
ew0 f0 @|StartSubIcon #fIcon
ew0 f1 type gawfs.Data #txt
ew0 f1 1553 145 30 30 0 15 #rect
ew0 f1 @|EndSubIcon #fIcon
ew0 f5 actionDecl 'gawfs.Data out;
' #txt
ew0 f5 actionTable 'out=in;
' #txt
ew0 f5 actionCode 'in.steps.add(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/start"));
in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/WorkflowPropertiesStep"));
in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/FormDefinitionStep"));
in.steps.add(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/EndStep"));

in.directExecutionFlag = false;' #txt
ew0 f5 security system #txt
ew0 f5 type gawfs.Data #txt
ew0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init steps</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f5 312 138 112 44 -25 -8 #rect
ew0 f5 @|StepIcon #fIcon
ew0 f7 targetWindow NEW:card: #txt
ew0 f7 targetDisplay TOP #txt
ew0 f7 richDialogId ch.ivy.gawfs.workflowCreation.WorkflowDefinition #txt
ew0 f7 startMethod start(gawfs.Data) #txt
ew0 f7 type gawfs.Data #txt
ew0 f7 requestActionDecl '<gawfs.Data data> param;' #txt
ew0 f7 requestMappingAction 'param.data=in;
' #txt
ew0 f7 responseActionDecl 'gawfs.Data out;
' #txt
ew0 f7 responseMappingAction 'out=in;
' #txt
ew0 f7 windowConfiguration '* ' #txt
ew0 f7 isAsynch false #txt
ew0 f7 isInnerRd false #txt
ew0 f7 userContext '* ' #txt
ew0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Workflow Settings Dialog</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f7 536 138 144 44 -68 -8 #rect
ew0 f7 @|RichDialogIcon #fIcon
ew0 f9 actionDecl 'gawfs.Data out;
' #txt
ew0 f9 actionTable 'out=in;
' #txt
ew0 f9 actionCode 'import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import gawfs.TaskDef;
import ch.ivy.gawfs.Formelement;

//save workflowdescription
	in.processRepository = ExpressServiceRegistry.getProcessService().findById(in.processID) as ExpressProcess;
	in.processRepository.processName = in.processName;
	in.processRepository.processDescription = in.processDescription;
	in.processRepository.processType = in.processType;
	in.processRepository.processOwner = ivy.session.getSessionUser().getMemberName();
	in.processRepository.processPermission = in.definedTasks.get(0).actor;
	BusinessDataInfo info = ExpressServiceRegistry.getProcessService().save(in.processRepository);
	in.processRepository.id = info.getId();



//get process ID to save with other values
String processID = in.processRepository.id;
in.processID = processID;

//delete all other fields
ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(in.processID);
ExpressServiceRegistry.getFormElementService().deleteByProcessId(in.processID);

//save all formelements with ID, location and order

for (Formelement e: in.dragAndDropController.selectedFormelementsHeader){
		ExpressFormElement x = new ExpressFormElement();
		x.elementID = e.id;
		x.elementPosition = "HEADER";
		x.elementType = e.type;
		x.intSetting = e.intSetting;
		x.label = e.label;
		x.name = e.name;
		x.required = e.required;
		x.processID =  processID;
		x.optionsStr = e.getOptionsAsString();

		ExpressServiceRegistry.getFormElementService().save(x);
}

for (Formelement e: in.dragAndDropController.selectedFormelementsLeftPanel){
		ExpressFormElement x = new ExpressFormElement();
		x.elementID = e.id;
		x.elementPosition = "LEFTPANEL";
		x.elementType = e.type;
		x.intSetting = e.intSetting;
		x.label = e.label;
		x.name = e.name;
		x.required = e.required;
		x.processID = processID;
		x.optionsStr = e.getOptionsAsString();
		ExpressServiceRegistry.getFormElementService().save(x);}

for (Formelement e: in.dragAndDropController.selectedFormelementsRightPanel){
		ExpressFormElement x = new ExpressFormElement();
		x.elementID = e.id;
		x.elementPosition = "RIGHTPANEL";
		x.elementType = e.type;
		x.intSetting = e.intSetting;
		x.label = e.label;
		x.name = e.name;
		x.required = e.required;
		x.processID = processID;
		x.optionsStr = e.getOptionsAsString();
		ExpressServiceRegistry.getFormElementService().save(x);}

for (Formelement e: in.dragAndDropController.selectedFormelementsFooter){
		ExpressFormElement x = new ExpressFormElement();
		x.elementID = e.id;
		x.elementPosition = "FOOTER";
		x.elementType = e.type;
		x.intSetting = e.intSetting;
		x.label = e.label;
		x.name = e.name;
		x.required = e.required;
		x.processID = processID;
		x.optionsStr = e.getOptionsAsString();
		ExpressServiceRegistry.getFormElementService().save(x);}

//save the taskdefinition with the order of the tasks

for (TaskDef t: in.definedTasks){
		ExpressTaskDefinition tp = new ExpressTaskDefinition();
		tp.subject = t.subject;
		tp.description = t.description;		
		tp.taskActor = t.actor;
		tp.untilDays = t.untilDays;
		tp.processID = processID;
		tp.taskCount = t.count;
		ExpressServiceRegistry.getTaskDefinitionService().save(tp);
}
' #txt
ew0 f9 security system #txt
ew0 f9 type gawfs.Data #txt
ew0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update Workflow to Persistence DB</name>
        <nameStyle>33,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f9 1272 138 208 44 -96 -8 #rect
ew0 f9 @|StepIcon #fIcon
ew0 f3 targetWindow NEW:card: #txt
ew0 f3 targetDisplay TOP #txt
ew0 f3 richDialogId ch.ivy.gawfs.workflowCreation.FormDefinition #txt
ew0 f3 startMethod start(gawfs.Data) #txt
ew0 f3 type gawfs.Data #txt
ew0 f3 requestActionDecl '<gawfs.Data data> param;' #txt
ew0 f3 requestMappingAction 'param.data=in;
' #txt
ew0 f3 responseActionDecl 'gawfs.Data out;
' #txt
ew0 f3 responseMappingAction 'out=in;
' #txt
ew0 f3 windowConfiguration '* ' #txt
ew0 f3 isAsynch false #txt
ew0 f3 isInnerRd false #txt
ew0 f3 userContext '* ' #txt
ew0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Workflow Formdefinition Dialog</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f3 768 138 176 44 -85 -8 #rect
ew0 f3 @|RichDialogIcon #fIcon
ew0 f11 type gawfs.Data #txt
ew0 f11 1032 144 32 32 0 16 #rect
ew0 f11 @|AlternativeIcon #fIcon
ew0 f13 type gawfs.Data #txt
ew0 f13 480 144 32 32 0 16 #rect
ew0 f13 @|AlternativeIcon #fIcon
ew0 f14 expr out #txt
ew0 f14 424 160 480 160 #arcP
ew0 f8 expr in #txt
ew0 f8 512 160 536 160 #arcP
ew0 f16 actionDecl 'gawfs.Data out;
' #txt
ew0 f16 actionTable 'out=in;
out.backFlag=false;
' #txt
ew0 f16 type gawfs.Data #txt
ew0 f16 672 84 32 24 0 -8 #rect
ew0 f16 @|StepIcon #fIcon
ew0 f17 expr in #txt
ew0 f17 outCond in.backFlag==true #txt
ew0 f17 1048 144 704 96 #arcP
ew0 f17 1 1048 96 #addKink
ew0 f17 1 0.5 0 0 #arcLabel
ew0 f15 expr out #txt
ew0 f15 672 96 496 144 #arcP
ew0 f15 1 496 96 #addKink
ew0 f15 1 0.5 0 0 #arcLabel
ew0 f18 type gawfs.Data #txt
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
ew0 f18 1096 144 32 32 -33 -49 #rect
ew0 f18 @|AlternativeIcon #fIcon
ew0 f20 type gawfs.Data #txt
ew0 f20 processCall 'Functional Processes/executePredefinedWorkflow:call(List<gawfs.TaskDef>,String,String,String,ch.ivy.gawfs.DragAndDropController,ch.ivy.gawfs.DynaFormController,String)' #txt
ew0 f20 doCall true #txt
ew0 f20 requestActionDecl '<List<gawfs.TaskDef> definedTasks,java.lang.String processName,java.lang.String processDescription,java.lang.String processType,ch.ivy.gawfs.DragAndDropController dragAndDropController,ch.ivy.gawfs.DynaFormController dynaFormController,java.lang.String processID> param;
' #txt
ew0 f20 requestMappingAction 'param.definedTasks=in.definedTasks;
param.processName=in.processName;
param.processDescription=in.processDescription;
param.processType=in.processType;
param.dragAndDropController=in.dragAndDropController;
param.dynaFormController=in.dynaFormController;
param.processID=in.processID;
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
ew0 f20 1288 234 176 44 -84 -8 #rect
ew0 f20 @|CallSubIcon #fIcon
ew0 f2 expr in #txt
ew0 f2 outCond in.directExecutionFlag #txt
ew0 f2 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f2 1112 176 1288 256 #arcP
ew0 f2 1 1112 256 #addKink
ew0 f2 0 0.3125 13 0 #arcLabel
ew0 f23 expr in #txt
ew0 f23 1064 160 1096 160 #arcP
ew0 f21 inParamDecl '<java.lang.String workflowID> param;' #txt
ew0 f21 inParamTable 'out.processID=param.workflowID;
' #txt
ew0 f21 outParamDecl '<> result;
' #txt
ew0 f21 actionDecl 'gawfs.Data out;
' #txt
ew0 f21 callSignature editWorkflow(String) #txt
ew0 f21 type gawfs.Data #txt
ew0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f21 65 273 30 30 -35 17 #rect
ew0 f21 @|StartSubIcon #fIcon
ew0 f33 expr out #txt
ew0 f33 95 288 312 288 #arcP
ew0 f34 368 266 368 182 #arcP
ew0 f34 0 0.37027027027027026 0 0 #arcLabel
ew0 f24 actionDecl 'gawfs.Data out;
' #txt
ew0 f24 actionTable 'out=in;
' #txt
ew0 f24 actionCode 'import ch.ivy.gawfs.FormelementOption;
import ch.ivy.gawfs.DynaFormController;
import ch.ivy.gawfs.DragAndDropController;

in.dragAndDropController = new DragAndDropController();

in.dynaFormController = new DynaFormController(in.dragAndDropController);
in.dragAndDropController.setDynaFormController(in.dynaFormController);

in.discard = false;

in.dynaFormController.createForm();' #txt
ew0 f24 security system #txt
ew0 f24 type gawfs.Data #txt
ew0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init Controllers</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f24 152 138 112 44 -40 -8 #rect
ew0 f24 @|StepIcon #fIcon
ew0 f25 expr out #txt
ew0 f25 95 160 152 160 #arcP
ew0 f6 expr out #txt
ew0 f6 264 160 312 160 #arcP
ew0 f32 type gawfs.Data #txt
ew0 f32 1553 241 30 30 0 15 #rect
ew0 f32 @|EndSubIcon #fIcon
ew0 f41 type gawfs.Data #txt
ew0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>discard?</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f41 720 144 32 32 -24 18 #rect
ew0 f41 @|AlternativeIcon #fIcon
ew0 f42 expr out #txt
ew0 f42 680 160 720 160 #arcP
ew0 f4 expr in #txt
ew0 f4 outCond !in.discard #txt
ew0 f4 752 160 768 160 #arcP
ew0 f43 type gawfs.Data #txt
ew0 f43 721 209 30 30 0 15 #rect
ew0 f43 @|EndIcon #fIcon
ew0 f44 expr in #txt
ew0 f44 736 176 736 209 #arcP
ew0 f45 type gawfs.Data #txt
ew0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>exit?</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ew0 f45 968 144 32 32 -12 18 #rect
ew0 f45 @|AlternativeIcon #fIcon
ew0 f46 type gawfs.Data #txt
ew0 f46 969 209 30 30 0 15 #rect
ew0 f46 @|EndIcon #fIcon
ew0 f47 expr in #txt
ew0 f47 outCond in.discard #txt
ew0 f47 984 176 984 209 #arcP
ew0 f48 expr out #txt
ew0 f48 944 160 968 160 #arcP
ew0 f12 expr in #txt
ew0 f12 1000 160 1032 160 #arcP
ew0 f49 expr in #txt
ew0 f49 1128 160 1272 160 #arcP
ew0 f10 expr out #txt
ew0 f10 1480 160 1553 160 #arcP
ew0 f19 expr out #txt
ew0 f19 1464 256 1553 256 #arcP
>Proto ew0 .type gawfs.Data #txt
>Proto ew0 .processKind CALLABLE_SUB #txt
>Proto ew0 0 0 32 24 18 0 #rect
>Proto ew0 @|BIcon #fIcon
Ct0 f27 actionDecl 'gawfs.Data out;
' #txt
Ct0 f27 actionTable 'out=in;
' #txt
Ct0 f27 actionCode 'import ch.ivy.gawfs.FormelementOption;
import ch.ivy.gawfs.DynaFormController;
import ch.ivy.gawfs.DragAndDropController;

in.dragAndDropController = new DragAndDropController();

in.dynaFormController = new DynaFormController(in.dragAndDropController);
in.dragAndDropController.setDynaFormController(in.dynaFormController);

in.dynaFormController.createForm();' #txt
Ct0 f27 security system #txt
Ct0 f27 type gawfs.Data #txt
Ct0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init Controllers</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f27 152 138 112 44 -40 -8 #rect
Ct0 f27 @|StepIcon #fIcon
Ct0 f28 actionDecl 'gawfs.Data out;
' #txt
Ct0 f28 actionTable 'out=in;
' #txt
Ct0 f28 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressFormElement;

List<ExpressFormElement> formelements = ExpressServiceRegistry.getFormElementService().findByProcessId(in.processID);

for (ExpressFormElement element: formelements){
	ch.ivy.gawfs.Formelement formelement;
	
	formelement.id = element.elementID;
	formelement.intSetting = element.intSetting;
	formelement.label = element.label;
	formelement.name = element.name;
	formelement.required = element.required;
	formelement.type = element.elementType;

	List<String> optionsStrx = element.optionsStr.split(":",-1);
	
	//direct assignement makes an array list, which makes problems in the seriealization, workaround:
	for(String optionx: optionsStrx){
		formelement.addOption(optionx);
	}
	
	if(element.elementPosition=="HEADER"){
		in.dragAndDropController.selectedFormelementsHeader.add(formelement);
	}else if(element.elementPosition=="LEFTPANEL"){
		in.dragAndDropController.selectedFormelementsLeftPanel.add(formelement);
	}else if(element.elementPosition=="RIGHTPANEL"){
		in.dragAndDropController.selectedFormelementsRightPanel.add(formelement);
	}else{
		in.dragAndDropController.selectedFormelementsFooter.add(formelement);
	}
	
	
}' #txt
Ct0 f28 security system #txt
Ct0 f28 type gawfs.Data #txt
Ct0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get formelements</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f28 152 330 112 44 -48 -8 #rect
Ct0 f28 @|StepIcon #fIcon
Ct0 f29 expr out #txt
Ct0 f29 208 278 208 330 #arcP
Ct0 f30 expr out #txt
Ct0 f30 208 182 208 234 #arcP
Ct0 f25 actionDecl 'gawfs.Data out;
' #txt
Ct0 f25 actionTable 'out=in;
' #txt
Ct0 f25 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.gawfs.Helper;
import ch.ivyteam.ivy.security.IUser;
import gawfs.TaskDef;

List<ExpressTaskDefinition> taskSteps = ExpressServiceRegistry.getTaskDefinitionService().findByProcessId(in.processID);


for(ExpressTaskDefinition task: taskSteps){
	TaskDef xtask = new TaskDef();
	
	xtask.actor = task.taskActor;
	xtask.count = task.taskCount;
	xtask.description = task.description;
	xtask.subject = task.subject;
	xtask.untilDays = task.untilDays;
	xtask.actorDisplayName = "";
	
	if(task.taskActor.startsWith("#")){
		IUser user = ivy.wf.getSecurityContext().findUser(task.taskActor.substring(1));
		if(#user is initialized) { 
		  xtask.actorDisplayName = ivy.wf.getSecurityContext().findUser(task.taskActor.substring(1)).getDisplayName();
		}
	}else{
		IRole role =  ivy.wf.getSecurityContext().findRole(task.taskActor);
		if(#role is initialized) {
		  xtask.actorDisplayName = ivy.wf.getSecurityContext().findRole(task.taskActor).getDisplayName();
		}
	}
	
	in.definedTasks.add(xtask);
}

in.definedTasks = Helper.sortTasks(in.definedTasks);' #txt
Ct0 f25 security system #txt
Ct0 f25 type gawfs.Data #txt
Ct0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Tasksteps</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f25 152 402 112 44 -39 -8 #rect
Ct0 f25 @|StepIcon #fIcon
Ct0 f31 expr out #txt
Ct0 f31 208 374 208 402 #arcP
Ct0 f26 actionDecl 'gawfs.Data out;
' #txt
Ct0 f26 actionTable 'out=in;
' #txt
Ct0 f26 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;

ExpressProcess workflow =ExpressServiceRegistry.getProcessService().findById(in.processID) as ExpressProcess;
in.processDescription = workflow.processDescription;
in.processName = workflow.processName;
in.processType = workflow.processType;


' #txt
Ct0 f26 security system #txt
Ct0 f26 type gawfs.Data #txt
Ct0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Workflow Credentials</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f26 136 234 144 44 -68 -8 #rect
Ct0 f26 @|StepIcon #fIcon
Ct0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g0 67 91 26 26 0 5 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 f0 93 104 208 138 #arcP
Ct0 f0 1 208 104 #addKink
Ct0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g1 195 507 26 26 0 5 #rect
Ct0 g1 @|MOGIcon #fIcon
Ct0 f1 expr out #txt
Ct0 f1 208 446 208 507 #arcP
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
ew0 f18 out f2 tail #connect
ew0 f2 head f20 mainIn #connect
ew0 f11 out f23 tail #connect
ew0 f23 head f18 in #connect
ew0 f33 head S10 g0 #connect
ew0 S10 g1 f34 tail #connect
ew0 f34 head f5 mainIn #connect
ew0 f21 mainOut f33 tail #connect
ew0 f0 mainOut f25 tail #connect
ew0 f25 head f24 mainIn #connect
ew0 f24 mainOut f6 tail #connect
ew0 f6 head f5 mainIn #connect
ew0 f7 mainOut f42 tail #connect
ew0 f42 head f41 in #connect
ew0 f41 out f4 tail #connect
ew0 f4 head f3 mainIn #connect
ew0 f41 out f44 tail #connect
ew0 f44 head f43 mainIn #connect
ew0 f45 out f47 tail #connect
ew0 f47 head f46 mainIn #connect
ew0 f3 mainOut f48 tail #connect
ew0 f48 head f45 in #connect
ew0 f45 out f12 tail #connect
ew0 f12 head f11 in #connect
ew0 f18 out f49 tail #connect
ew0 f49 head f9 mainIn #connect
ew0 f9 mainOut f10 tail #connect
ew0 f10 head f1 mainIn #connect
ew0 f20 mainOut f19 tail #connect
ew0 f19 head f32 mainIn #connect
Ct0 f28 mainOut f31 tail #connect
Ct0 f31 head f25 mainIn #connect
Ct0 f27 mainOut f30 tail #connect
Ct0 f30 head f26 mainIn #connect
Ct0 f26 mainOut f29 tail #connect
Ct0 f29 head f28 mainIn #connect
Ct0 g0 m f0 tail #connect
Ct0 f0 head f27 mainIn #connect
Ct0 f1 head g1 m #connect
Ct0 f25 mainOut f1 tail #connect
Ct0 0 0 416 608 0 #ivRect
