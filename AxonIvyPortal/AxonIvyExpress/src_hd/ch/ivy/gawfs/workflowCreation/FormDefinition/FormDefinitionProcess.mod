[Ivy]
1574EBDBE9576CED 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 FormDefinitionProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @UdExitEnd f4 '' #zField
Ds0 @GridStep f6 '' #zField
Ds0 @UdMethod f8 '' #zField
Ds0 @GridStep f9 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @UdMethod f12 '' #zField
Ds0 @UdMethod f14 '' #zField
Ds0 @UdMethod f15 '' #zField
Ds0 @UdMethod f16 '' #zField
Ds0 @GridStep f17 '' #zField
Ds0 @PushWFArc f18 '' #zField
Ds0 @Alternative f19 '' #zField
Ds0 @PushWFArc f7 '' #zField
Ds0 @PushWFArc f21 '' #zField
Ds0 @GridStep f11 '' #zField
Ds0 @PushWFArc f20 '' #zField
Ds0 @PushWFArc f22 '' #zField
Ds0 @GridStep f33 '' #zField
Ds0 @PushWFArc f34 '' #zField
Ds0 @GridStep f35 '' #zField
Ds0 @PushWFArc f36 '' #zField
Ds0 @PushWFArc f37 '' #zField
Ds0 @PushWFArc f38 '' #zField
Ds0 @GridStep f39 '' #zField
Ds0 @PushWFArc f40 '' #zField
Ds0 @PushWFArc f41 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdEvent f44 '' #zField
Ds0 @PushWFArc f45 '' #zField
Ds0 @UdEvent f42 '' #zField
Ds0 @UdEvent f51 '' #zField
Ds0 @GridStep f48 '' #zField
Ds0 @GridStep f46 '' #zField
Ds0 @PushWFArc f47 '' #zField
Ds0 @PushWFArc f13 '' #zField
Ds0 @UdMethod f49 '' #zField
Ds0 @UdProcessEnd f52 '' #zField
Ds0 @GridStep f56 '' #zField
Ds0 @PushWFArc f55 '' #zField
Ds0 @UdMethod f58 '' #zField
Ds0 @PushWFArc f61 '' #zField
Ds0 @PushWFArc f57 '' #zField
Ds0 @PushWFArc f30 '' #zField
Ds0 @Alternative f26 '' #zField
Ds0 @PushWFArc f25 '' #zField
Ds0 @PushWFArc f31 '' #zField
Ds0 @GridStep f23 '' #zField
Ds0 @PushWFArc f24 '' #zField
Ds0 @PushWFArc f27 '' #zField
Ds0 @GridStep f53 '' #zField
Ds0 @UdEvent f60 '' #zField
Ds0 @UdEvent f62 '' #zField
Ds0 @GridStep f63 '' #zField
Ds0 @PushWFArc f64 '' #zField
Ds0 @PushWFArc f66 '' #zField
Ds0 @UdExitEnd f3 '' #zField
Ds0 @PushWFArc f5 '' #zField
Ds0 @Alternative f28 '' #zField
Ds0 @PushWFArc f32 '' #zField
Ds0 @GridStep f43 '' #zField
Ds0 @PushWFArc f50 '' #zField
Ds0 @PushWFArc f29 '' #zField
Ds0 @UdProcessEnd f59 '' #zField
Ds0 @PushWFArc f65 '' #zField
Ds0 @Alternative f67 '' #zField
Ds0 @PushWFArc f68 '' #zField
Ds0 @GridStep f69 '' #zField
Ds0 @PushWFArc f70 '' #zField
Ds0 @PushWFArc f54 '' #zField
>Proto Ds0 Ds0 FormDefinitionProcess #zField
Ds0 f0 guid 156E35E680453115 #txt
Ds0 f0 method start(gawfs.Data) #txt
Ds0 f0 inParameterDecl '<gawfs.Data data> param;' #txt
Ds0 f0 inParameterMapAction 'out.data=param.data;
' #txt
Ds0 f0 outParameterDecl '<gawfs.Data data> result;' #txt
Ds0 f0 outParameterMapAction 'result.data=in.data;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Data)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 83 51 26 26 -28 33 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 819 51 26 26 0 12 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f4 643 595 26 26 0 12 #rect
Ds0 f4 @|UdExitEndIcon #fIcon
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'import ch.ivy.gawfs.DynaFormController;

in.createFileUploadTab = DynaFormController.initializeFileUploadElement();
in.createInputFieldTab = DynaFormController.initializeInputFieldElement();
in.createInputAreaTab = DynaFormController.initializeInputAreaElement();
in.createManyCheckboxTab = DynaFormController.initializeCheckboxElement();
in.createOneRadioTab = DynaFormController.initializeRadioButtonElement();

' #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init createTabs</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f6 640 42 112 44 -40 -8 #rect
Ds0 f6 @|StepIcon #fIcon
Ds0 f8 guid 15749D9D5247FA7C #txt
Ds0 f8 method createInputField() #txt
Ds0 f8 inParameterDecl '<> param;' #txt
Ds0 f8 outParameterDecl '<> result;' #txt
Ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createInputField</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f8 83 147 26 26 -44 15 #rect
Ds0 f8 @|UdMethodIcon #fIcon
Ds0 f9 actionTable 'out=in;
' #txt
Ds0 f9 actionCode '
in.createInputFieldTab.id = in.createInputFieldTab.label + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createInputFieldTab);' #txt
Ds0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Text Input element</name>
    </language>
</elementInfo>
' #txt
Ds0 f9 193 138 128 44 -61 -8 #rect
Ds0 f9 @|StepIcon #fIcon
Ds0 f10 expr out #txt
Ds0 f10 109 160 193 160 #arcP
Ds0 f12 guid 1574A296AECB0DD4 #txt
Ds0 f12 method createInputTextArea() #txt
Ds0 f12 inParameterDecl '<> param;' #txt
Ds0 f12 outParameterDecl '<> result;' #txt
Ds0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createInputTextArea()</name>
    </language>
</elementInfo>
' #txt
Ds0 f12 83 211 26 26 -58 15 #rect
Ds0 f12 @|UdMethodIcon #fIcon
Ds0 f14 guid 1574A2979225007A #txt
Ds0 f14 method createManyCheckbox() #txt
Ds0 f14 inParameterDecl '<> param;' #txt
Ds0 f14 outParameterDecl '<> result;' #txt
Ds0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createManyCheckbox()</name>
    </language>
</elementInfo>
' #txt
Ds0 f14 83 275 26 26 -62 15 #rect
Ds0 f14 @|UdMethodIcon #fIcon
Ds0 f15 guid 1574A2984D932DE0 #txt
Ds0 f15 method createOneRadio() #txt
Ds0 f15 inParameterDecl '<> param;' #txt
Ds0 f15 outParameterDecl '<> result;' #txt
Ds0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createOneRadio()</name>
    </language>
</elementInfo>
' #txt
Ds0 f15 83 339 26 26 -49 15 #rect
Ds0 f15 @|UdMethodIcon #fIcon
Ds0 f16 guid 1574A2991F562461 #txt
Ds0 f16 method createFileUpload() #txt
Ds0 f16 inParameterDecl '<> param;' #txt
Ds0 f16 outParameterDecl '<> result;' #txt
Ds0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createFileUpload()</name>
    </language>
</elementInfo>
' #txt
Ds0 f16 83 403 26 26 -51 15 #rect
Ds0 f16 @|UdMethodIcon #fIcon
Ds0 f17 actionTable 'out=in;
' #txt
Ds0 f17 actionCode '
in.createInputAreaTab.id = in.createInputAreaTab.label + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createInputAreaTab);

' #txt
Ds0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Textarea element</name>
    </language>
</elementInfo>
' #txt
Ds0 f17 193 202 128 44 -59 -8 #rect
Ds0 f17 @|StepIcon #fIcon
Ds0 f18 expr out #txt
Ds0 f18 109 224 193 224 #arcP
Ds0 f19 560 48 32 32 0 16 #rect
Ds0 f19 @|AlternativeIcon #fIcon
Ds0 f7 expr in #txt
Ds0 f7 592 64 640 64 #arcP
Ds0 f21 expr out #txt
Ds0 f21 321 160 576 80 #arcP
Ds0 f21 1 576 160 #addKink
Ds0 f21 0 0.8169815478141365 0 0 #arcLabel
Ds0 f11 actionTable 'out=in;
out.activeTab=0;
' #txt
Ds0 f11 actionCode 'import gawfs.ExternalDataProvider;
import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivy.gawfs.DynaFormController;
import ch.ivy.gawfs.DragAndDropController;
import gawfs.TaskDef;

for (TaskDef taskDef : in.data.definedTasks) {
	if(taskDef.getTaskType() == TaskType.EMAIL) {
		if(!taskDef.#email is initialized) {
			taskDef.setEmail(new ExpressUserEmail());	
		}	
	}
	else if(taskDef.getTaskType() != TaskType.EMAIL) {
		taskDef.setEmail(null);
		if (taskDef.#dynaFormController is initialized) {
			continue;
		}
			taskDef.dragAndDropController = new DragAndDropController();
		  taskDef.dynaFormController = new DynaFormController(taskDef.dragAndDropController);
		  taskDef.dragAndDropController.setDynaFormController(taskDef.dynaFormController);
		  taskDef.dynaFormController.createForm();
		}
}
if(in.data.definedTasks.get(0).taskType != TaskType.EMAIL) {
	in.dragAndDropController = in.data.definedTasks.get(0).dragAndDropController;
	in.dynaFormController = in.data.definedTasks.get(0).dynaFormController;
}
in.activeTaskIndex = 0;
ExpressProcessUtils expressUtils = new ExpressProcessUtils();
in.displayNextBtn = expressUtils.displayNextButton(in.data.definedTasks, in.activeTaskIndex);

in.selectedDataProvider = new ExternalDataProvider();
in.selectedDataProvider.name = ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/NoDataProvider");
in.dataProviders.add(in.selectedDataProvider);
in.dataProviders.addAll(expressUtils.findDataProviders());' #txt
Ds0 f11 security system #txt
Ds0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize controllers</name>
    </language>
</elementInfo>
' #txt
Ds0 f11 384 40 112 48 -52 -8 #rect
Ds0 f11 @|StepIcon #fIcon
Ds0 f20 expr out #txt
Ds0 f20 496 64 560 64 #arcP
Ds0 f22 expr out #txt
Ds0 f22 321 224 576 80 #arcP
Ds0 f22 1 576 224 #addKink
Ds0 f22 1 0.3515094270353499 0 0 #arcLabel
Ds0 f33 actionTable 'out=in;
' #txt
Ds0 f33 actionCode '
in.createManyCheckboxTab.id = in.createManyCheckboxTab.label + new DateTime();
if (!in.selectedDataProvider.libraryId.isEmpty()) {
	in.createManyCheckboxTab.addOption(in.selectedDataProvider.libraryId);
	in.createManyCheckboxTab.addOption(in.selectedDataProvider.signature);
}
in.dragAndDropController.availableFormelements.add(in.createManyCheckboxTab);

' #txt
Ds0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Checkbox element</name>
    </language>
</elementInfo>
' #txt
Ds0 f33 193 266 144 44 -63 -8 #rect
Ds0 f33 @|StepIcon #fIcon
Ds0 f34 expr out #txt
Ds0 f34 109 288 193 288 #arcP
Ds0 f35 actionTable 'out=in;
' #txt
Ds0 f35 actionCode '
in.createOneRadioTab.id = in.createOneRadioTab.label + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createOneRadioTab);


' #txt
Ds0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Radion Button element</name>
    </language>
</elementInfo>
' #txt
Ds0 f35 193 329 160 44 -75 -8 #rect
Ds0 f35 @|StepIcon #fIcon
Ds0 f36 expr out #txt
Ds0 f36 108 351 193 351 #arcP
Ds0 f37 expr out #txt
Ds0 f37 337 288 576 80 #arcP
Ds0 f37 1 576 288 #addKink
Ds0 f37 1 0.6109477554941023 0 0 #arcLabel
Ds0 f38 expr out #txt
Ds0 f38 353 351 576 80 #arcP
Ds0 f38 1 576 351 #addKink
Ds0 f38 1 0.4796960275419352 0 0 #arcLabel
Ds0 f39 actionTable 'out=in;
' #txt
Ds0 f39 actionCode '
in.createFileUploadTab.id = in.createFileUploadTab.label + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createFileUploadTab);


' #txt
Ds0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add File Upload element</name>
    </language>
</elementInfo>
' #txt
Ds0 f39 193 394 144 44 -67 -8 #rect
Ds0 f39 @|StepIcon #fIcon
Ds0 f40 expr out #txt
Ds0 f40 109 416 193 416 #arcP
Ds0 f41 expr out #txt
Ds0 f41 337 416 576 80 #arcP
Ds0 f41 1 576 416 #addKink
Ds0 f41 1 0.35720571036856263 0 0 #arcLabel
Ds0 f2 expr out #txt
Ds0 f2 752 64 819 64 #arcP
Ds0 f44 guid 15791C3E4B5AA64C #txt
Ds0 f44 actionTable 'out=in;
out.data.backFlag=true;
' #txt
Ds0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f44 83 659 26 26 -13 15 #rect
Ds0 f44 @|UdEventIcon #fIcon
Ds0 f45 expr out #txt
Ds0 f45 109 672 656 621 #arcP
Ds0 f45 1 656 672 #addKink
Ds0 f45 0 0.5699203045049207 0 0 #arcLabel
Ds0 f42 guid 15798472F333F271 #txt
Ds0 f42 actionTable 'out=in;
' #txt
Ds0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>directExecution</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f42 83 595 26 26 -41 15 #rect
Ds0 f42 @|UdEventIcon #fIcon
Ds0 f51 guid 158942FA3188C6C0 #txt
Ds0 f51 actionTable 'out=in;
' #txt
Ds0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f51 83 723 26 26 -18 15 #rect
Ds0 f51 @|UdEventIcon #fIcon
Ds0 f48 actionTable 'out=in;
' #txt
Ds0 f48 actionCode 'in.data.discard = true;' #txt
Ds0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>discard = true</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f48 280 794 112 44 -37 -8 #rect
Ds0 f48 @|StepIcon #fIcon
Ds0 f46 actionTable 'out=in;
' #txt
Ds0 f46 actionCode 'import ch.ivy.gawfs.enums.FormElementType;

if(!in.data.savedFlag) {
	ivy.task.setName(ivy.cms.co("/Dialogs/Tasks/FormDefinition/TaskName"));
	ivy.task.setDescription(ivy.cms.co("/Dialogs/Tasks/FormDefinition/TaskDescription"));
}
' #txt
Ds0 f46 security system #txt
Ds0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize task</name>
    </language>
</elementInfo>
' #txt
Ds0 f46 192 42 112 44 -35 -8 #rect
Ds0 f46 @|StepIcon #fIcon
Ds0 f47 expr out #txt
Ds0 f47 109 64 192 64 #arcP
Ds0 f13 expr out #txt
Ds0 f13 304 64 384 64 #arcP
Ds0 f49 guid 162BD04BAE6408C4 #txt
Ds0 f49 method moveNextTask() #txt
Ds0 f49 inParameterDecl '<> param;' #txt
Ds0 f49 inActionCode 'import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.gawfs.enums.TaskType;
if(out.data.definedTasks.get(out.activeTaskIndex).getTaskType() != TaskType.EMAIL) {
	out.data.definedTasks.get(out.activeTaskIndex).setDynaFormController(out.dynaFormController);
	out.data.definedTasks.get(out.activeTaskIndex).setDragAndDropController(out.dragAndDropController);

}
ExpressProcessUtils expressUtils = new ExpressProcessUtils();
out.activeTaskIndex = expressUtils.nextAvailableTaskIndex(out.data.definedTasks, out.activeTaskIndex);' #txt
Ds0 f49 outParameterDecl '<> result;' #txt
Ds0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveNextTask()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f49 83 467 26 26 -44 15 #rect
Ds0 f49 @|UdMethodIcon #fIcon
Ds0 f52 563 467 26 26 0 12 #rect
Ds0 f52 @|UdProcessEndIcon #fIcon
Ds0 f56 actionTable 'out=in;
' #txt
Ds0 f56 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.gawfs.enums.TaskType;
import gawfs.TaskDef;

TaskDef nextTaskDef = in.data.definedTasks.get(in.activeTaskIndex);
if(nextTaskDef.getTaskType() != TaskType.EMAIL) {
	in.dynaFormController = nextTaskDef.getDynaFormController();
	in.dragAndDropController = nextTaskDef.getDragAndDropController();
}
ExpressProcessUtils expressUtils = new ExpressProcessUtils();
in.displayNextBtn = expressUtils.displayNextButton(in.data.definedTasks, in.activeTaskIndex);
' #txt
Ds0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize next&#xD;
controllers</name>
    </language>
</elementInfo>
' #txt
Ds0 f56 192 458 112 44 -34 -16 #rect
Ds0 f56 @|StepIcon #fIcon
Ds0 f55 expr out #txt
Ds0 f55 304 480 563 480 #arcP
Ds0 f58 guid 162BD1499F4DC89A #txt
Ds0 f58 method moveToTask(java.lang.Integer) #txt
Ds0 f58 inParameterDecl '<Integer index> param;' #txt
Ds0 f58 inActionCode 'import ch.ivy.gawfs.enums.TaskType;
if(out.data.definedTasks.get(out.activeTaskIndex).getTaskType() != TaskType.EMAIL) {
	out.data.definedTasks.get(out.activeTaskIndex).setDynaFormController(out.dynaFormController);
	out.data.definedTasks.get(out.activeTaskIndex).setDragAndDropController(out.dragAndDropController);

}
out.activeTaskIndex =param.index;' #txt
Ds0 f58 outParameterDecl '<> result;' #txt
Ds0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveToTask()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f58 83 531 26 26 -39 15 #rect
Ds0 f58 @|UdMethodIcon #fIcon
Ds0 f61 expr out #txt
Ds0 f61 109 544 248 502 #arcP
Ds0 f61 1 248 544 #addKink
Ds0 f61 0 0.6714985851425087 0 0 #arcLabel
Ds0 f57 expr out #txt
Ds0 f57 109 480 192 480 #arcP
Ds0 f30 expr out #txt
Ds0 f30 392 816 656 621 #arcP
Ds0 f30 1 656 816 #addKink
Ds0 f30 0 0.8290441176470589 0 0 #arcLabel
Ds0 f26 400 592 32 32 0 16 #rect
Ds0 f26 @|AlternativeIcon #fIcon
Ds0 f25 expr in #txt
Ds0 f25 outCond in.isAbleToExecute #txt
Ds0 f25 432 608 643 608 #arcP
Ds0 f25 0 0.5649178034353388 0 0 #arcLabel
Ds0 f31 expr in #txt
Ds0 f31 416 592 563 480 #arcP
Ds0 f31 1 416 480 #addKink
Ds0 f31 1 0.09342369812886672 0 0 #arcLabel
Ds0 f23 actionTable 'out=in;
' #txt
Ds0 f23 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.gawfs.ExpressProcessUtils;
import javax.faces.application.FacesMessage;

ExpressProcessUtils utils = new ExpressProcessUtils();
in.isAbleToExecute = utils.canFinishFormDefinition(in.data.definedTasks);
if(!in.isAbleToExecute){
	String growlTitle = ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/ExecuteWorkflowWarning");
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_WARN, growlTitle, null);
	FacesContext.getCurrentInstance().addMessage(null, message);
}' #txt
Ds0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if able to execute wf</name>
    </language>
</elementInfo>
' #txt
Ds0 f23 192 586 160 44 -72 -8 #rect
Ds0 f23 @|StepIcon #fIcon
Ds0 f24 expr out #txt
Ds0 f24 109 608 192 608 #arcP
Ds0 f27 expr out #txt
Ds0 f27 352 608 400 608 #arcP
Ds0 f53 actionTable 'out=in;
' #txt
Ds0 f53 actionCode 'import ch.ivy.gawfs.enums.TaskType;
if(out.data.definedTasks.get(out.activeTaskIndex).taskType != TaskType.EMAIL) {
	out.data.definedTasks.get(out.activeTaskIndex).setDynaFormController(out.dynaFormController);
	out.data.definedTasks.get(out.activeTaskIndex).setDragAndDropController(out.dragAndDropController);
}
' #txt
Ds0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update current controllers</name>
    </language>
</elementInfo>
' #txt
Ds0 f53 640 882 160 44 -71 -8 #rect
Ds0 f53 @|StepIcon #fIcon
Ds0 f60 guid 1638704721DA1E76 #txt
Ds0 f60 actionTable 'out=in;
' #txt
Ds0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f60 82 891 26 26 -13 15 #rect
Ds0 f60 @|UdEventIcon #fIcon
Ds0 f62 guid 1638704721E99BED #txt
Ds0 f62 actionTable 'out=in;
' #txt
Ds0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f62 82 955 26 26 -15 15 #rect
Ds0 f62 @|UdEventIcon #fIcon
Ds0 f63 actionTable 'out=in;
' #txt
Ds0 f63 actionCode 'in.data.readyToExecute = true;' #txt
Ds0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Marked as finished</name>
    </language>
</elementInfo>
' #txt
Ds0 f63 480 946 112 44 -52 -8 #rect
Ds0 f63 @|StepIcon #fIcon
Ds0 f64 expr out #txt
Ds0 f64 592 968 720 926 #arcP
Ds0 f64 1 720 968 #addKink
Ds0 f64 0 0.7915839930188887 0 0 #arcLabel
Ds0 f66 expr out #txt
Ds0 f66 108 904 640 904 #arcP
Ds0 f3 851 891 26 26 0 12 #rect
Ds0 f3 @|UdExitEndIcon #fIcon
Ds0 f5 expr out #txt
Ds0 f5 800 904 851 904 #arcP
Ds0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validated?</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f28 368 952 32 32 -26 -34 #rect
Ds0 f28 @|AlternativeIcon #fIcon
Ds0 f32 expr in #txt
Ds0 f32 outCond in.isAbleToExecute #txt
Ds0 f32 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f32 400 968 480 968 #arcP
Ds0 f32 0 0.4375 0 -9 #arcLabel
Ds0 f43 actionTable 'out=in;
' #txt
Ds0 f43 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.gawfs.ExpressProcessUtils;
import javax.faces.application.FacesMessage;

ExpressProcessUtils utils = new ExpressProcessUtils();
in.isAbleToExecute = utils.canFinishFormDefinition(in.data.definedTasks);
if(!in.isAbleToExecute){
	String growlTitle = ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/ExecuteWorkflowWarning");
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_WARN, growlTitle, null);
	FacesContext.getCurrentInstance().addMessage(null, message);
}' #txt
Ds0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validate</name>
    </language>
</elementInfo>
' #txt
Ds0 f43 192 946 112 44 -22 -8 #rect
Ds0 f43 @|StepIcon #fIcon
Ds0 f50 expr out #txt
Ds0 f50 108 968 192 968 #arcP
Ds0 f50 0 0.5863480725728565 0 0 #arcLabel
Ds0 f29 expr out #txt
Ds0 f29 304 968 368 968 #arcP
Ds0 f29 0 0.5863480725728565 0 0 #arcLabel
Ds0 f59 851 1019 26 26 0 12 #rect
Ds0 f59 @|UdProcessEndIcon #fIcon
Ds0 f65 expr in #txt
Ds0 f65 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f65 384 984 851 1032 #arcP
Ds0 f65 1 384 1032 #addKink
Ds0 f65 0 0.3541666666666667 13 0 #arcLabel
Ds0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is saved task?</name>
    </language>
</elementInfo>
' #txt
Ds0 f67 176 720 32 32 -40 -39 #rect
Ds0 f67 @|AlternativeIcon #fIcon
Ds0 f68 expr out #txt
Ds0 f68 109 736 176 736 #arcP
Ds0 f68 0 0.20328842452716808 0 0 #arcLabel
Ds0 f69 actionTable 'out=in;
' #txt
Ds0 f69 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
TaskUtils.resetTask(ivy.task);

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
Ds0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task and&#xD;
navigate back</name>
    </language>
</elementInfo>
' #txt
Ds0 f69 280 714 128 44 -40 -16 #rect
Ds0 f69 @|StepIcon #fIcon
Ds0 f70 expr in #txt
Ds0 f70 outCond in.data.savedFlag #txt
Ds0 f70 208 736 280 736 #arcP
Ds0 f70 0 0.20328842452716808 0 0 #arcLabel
Ds0 f54 expr in #txt
Ds0 f54 192 752 280 816 #arcP
Ds0 f54 1 192 816 #addKink
Ds0 f54 1 0.1487695459411198 0 0 #arcLabel
>Proto Ds0 .type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f6 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f8 mainOut f10 tail #connect
Ds0 f10 head f9 mainIn #connect
Ds0 f12 mainOut f18 tail #connect
Ds0 f18 head f17 mainIn #connect
Ds0 f19 out f7 tail #connect
Ds0 f7 head f6 mainIn #connect
Ds0 f9 mainOut f21 tail #connect
Ds0 f21 head f19 in #connect
Ds0 f11 mainOut f20 tail #connect
Ds0 f20 head f19 in #connect
Ds0 f17 mainOut f22 tail #connect
Ds0 f22 head f19 in #connect
Ds0 f14 mainOut f34 tail #connect
Ds0 f34 head f33 mainIn #connect
Ds0 f15 mainOut f36 tail #connect
Ds0 f36 head f35 mainIn #connect
Ds0 f33 mainOut f37 tail #connect
Ds0 f37 head f19 in #connect
Ds0 f35 mainOut f38 tail #connect
Ds0 f38 head f19 in #connect
Ds0 f16 mainOut f40 tail #connect
Ds0 f40 head f39 mainIn #connect
Ds0 f39 mainOut f41 tail #connect
Ds0 f41 head f19 in #connect
Ds0 f44 mainOut f45 tail #connect
Ds0 f45 head f4 mainIn #connect
Ds0 f0 mainOut f47 tail #connect
Ds0 f47 head f46 mainIn #connect
Ds0 f46 mainOut f13 tail #connect
Ds0 f13 head f11 mainIn #connect
Ds0 f56 mainOut f55 tail #connect
Ds0 f55 head f52 mainIn #connect
Ds0 f58 mainOut f61 tail #connect
Ds0 f61 head f56 mainIn #connect
Ds0 f49 mainOut f57 tail #connect
Ds0 f57 head f56 mainIn #connect
Ds0 f48 mainOut f30 tail #connect
Ds0 f30 head f4 mainIn #connect
Ds0 f26 out f25 tail #connect
Ds0 f25 head f4 mainIn #connect
Ds0 f26 out f31 tail #connect
Ds0 f31 head f52 mainIn #connect
Ds0 f42 mainOut f24 tail #connect
Ds0 f24 head f23 mainIn #connect
Ds0 f23 mainOut f27 tail #connect
Ds0 f27 head f26 in #connect
Ds0 f60 mainOut f66 tail #connect
Ds0 f66 head f53 mainIn #connect
Ds0 f63 mainOut f64 tail #connect
Ds0 f64 head f53 mainIn #connect
Ds0 f53 mainOut f5 tail #connect
Ds0 f5 head f3 mainIn #connect
Ds0 f28 out f32 tail #connect
Ds0 f32 head f63 mainIn #connect
Ds0 f62 mainOut f50 tail #connect
Ds0 f50 head f43 mainIn #connect
Ds0 f43 mainOut f29 tail #connect
Ds0 f29 head f28 in #connect
Ds0 f28 out f65 tail #connect
Ds0 f65 head f59 mainIn #connect
Ds0 f51 mainOut f68 tail #connect
Ds0 f68 head f67 in #connect
Ds0 f67 out f70 tail #connect
Ds0 f70 head f69 mainIn #connect
Ds0 f67 out f54 tail #connect
Ds0 f54 head f48 mainIn #connect
