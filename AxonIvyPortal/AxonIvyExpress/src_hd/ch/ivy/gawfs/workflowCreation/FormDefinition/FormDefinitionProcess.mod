[Ivy]
[>Created: Wed Jul 19 10:31:51 ICT 2017]
1574EBDBE9576CED 3.20 #module
>Proto >Proto Collection #zClass
Ds0 FormDefinitionProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @RichDialogEnd f4 '' #zField
Ds0 @GridStep f6 '' #zField
Ds0 @RichDialogMethodStart f8 '' #zField
Ds0 @GridStep f9 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @RichDialogMethodStart f12 '' #zField
Ds0 @RichDialogMethodStart f14 '' #zField
Ds0 @RichDialogMethodStart f15 '' #zField
Ds0 @RichDialogMethodStart f16 '' #zField
Ds0 @GridStep f17 '' #zField
Ds0 @PushWFArc f18 '' #zField
Ds0 @Alternative f19 '' #zField
Ds0 @PushWFArc f7 '' #zField
Ds0 @PushWFArc f21 '' #zField
Ds0 @GridStep f11 '' #zField
Ds0 @PushWFArc f13 '' #zField
Ds0 @PushWFArc f20 '' #zField
Ds0 @PushWFArc f22 '' #zField
Ds0 @RichDialogProcessEnd f24 '' #zField
Ds0 @GridStep f26 '' #zField
Ds0 @PushWFArc f25 '' #zField
Ds0 @RichDialogMethodStart f28 '' #zField
Ds0 @RichDialogProcessEnd f29 '' #zField
Ds0 @GridStep f31 '' #zField
Ds0 @PushWFArc f32 '' #zField
Ds0 @PushWFArc f30 '' #zField
Ds0 @RichDialogMethodStart f23 '' #zField
Ds0 @PushWFArc f27 '' #zField
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
Ds0 @RichDialogProcessStart f44 '' #zField
Ds0 @PushWFArc f45 '' #zField
Ds0 @RichDialogProcessStart f42 '' #zField
Ds0 @PushWFArc f43 '' #zField
Ds0 @RichDialogProcessStart f51 '' #zField
Ds0 @RichDialogProcessStart f3 '' #zField
Ds0 @PushWFArc f5 '' #zField
Ds0 @GridStep f48 '' #zField
Ds0 @RichDialogEnd f50 '' #zField
Ds0 @PushWFArc f53 '' #zField
Ds0 @PushWFArc f54 '' #zField
>Proto Ds0 Ds0 FormDefinitionProcess #zField
Ds0 f0 guid 156E35E680453115 #txt
Ds0 f0 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f0 method start(gawfs.Data) #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.Data data> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 inParameterMapAction 'out.data=param.data;
' #txt
Ds0 f0 outParameterDecl '<gawfs.Data data> result;
' #txt
Ds0 f0 outParameterMapAction 'result.data=in.data;
result.data.dragAndDropController=in.dragAndDropController;
result.data.dynaFormController=in.dynaFormController;
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
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f1 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f1 843 51 26 26 0 12 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f4 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f4 guid 156E35E6813D29F6 #txt
Ds0 f4 563 723 26 26 0 12 #rect
Ds0 f4 @|RichDialogEndIcon #fIcon
Ds0 f6 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'import ch.ivy.gawfs.FormelementOption;
import ch.ivy.gawfs.Formelement;

in.createInputFieldTab = new Formelement();

in.createFileUploadTab = new Formelement();
	in.createFileUploadTab.type="FileUpload";
	in.createFileUploadTab.intSetting = 0; //initial value for number of files allowed
	FormelementOption erlaubtesDateiformat1 = new FormelementOption(ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/PDF"));
	FormelementOption erlaubtesDateiformat2 = new FormelementOption(ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/Word"));
	FormelementOption erlaubtesDateiformat3 = new FormelementOption(ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/Excel"));
	FormelementOption erlaubtesDateiformat4 = new FormelementOption(ivy.cms.co("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/OtherFormats"));
	in.createFileUploadTab.addOption(erlaubtesDateiformat1);
	in.createFileUploadTab.addOption(erlaubtesDateiformat2);
	in.createFileUploadTab.addOption(erlaubtesDateiformat3);
	in.createFileUploadTab.addOption(erlaubtesDateiformat4);
	
	in.createInputFieldTab.required=false;

in.createInputAreaTab = new Formelement();
	in.createInputAreaTab.intSetting = 2; //initial value for number of rows
	in.createInputAreaTab.type = "InputTextArea";
	in.createInputAreaTab.required = false;

in.createManyCheckboxTab = new Formelement();
in.createManyCheckboxTab.type="ManyCheckbox";

in.createOneRadioTab = new Formelement();
in.createOneRadioTab.type="OneRadio";


' #txt
Ds0 f6 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init createTabs</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f6 672 42 112 44 -40 -8 #rect
Ds0 f6 @|StepIcon #fIcon
Ds0 f8 guid 15749D9D5247FA7C #txt
Ds0 f8 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f8 method createInputField() #txt
Ds0 f8 disableUIEvents false #txt
Ds0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f8 outParameterDecl '<> result;
' #txt
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
Ds0 f8 @|RichDialogMethodStartIcon #fIcon
Ds0 f9 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f9 actionTable 'out=in;
' #txt
Ds0 f9 actionCode '

in.createInputFieldTab.id = in.createInputFieldTab.name + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createInputFieldTab);



' #txt
Ds0 f9 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Values from InputField Definition, create and add Formelement</name>
        <nameStyle>65,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f9 207 138 368 44 -181 -8 #rect
Ds0 f9 @|StepIcon #fIcon
Ds0 f10 expr out #txt
Ds0 f10 109 160 207 160 #arcP
Ds0 f12 guid 1574A296AECB0DD4 #txt
Ds0 f12 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f12 method createInputTextArea() #txt
Ds0 f12 disableUIEvents false #txt
Ds0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f12 outParameterDecl '<> result;
' #txt
Ds0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createInputTextArea()</name>
    </language>
</elementInfo>
' #txt
Ds0 f12 83 211 26 26 -58 15 #rect
Ds0 f12 @|RichDialogMethodStartIcon #fIcon
Ds0 f14 guid 1574A2979225007A #txt
Ds0 f14 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f14 method createManyCheckbox() #txt
Ds0 f14 disableUIEvents false #txt
Ds0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f14 outParameterDecl '<> result;
' #txt
Ds0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createManyCheckbox()</name>
    </language>
</elementInfo>
' #txt
Ds0 f14 83 275 26 26 -62 15 #rect
Ds0 f14 @|RichDialogMethodStartIcon #fIcon
Ds0 f15 guid 1574A2984D932DE0 #txt
Ds0 f15 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f15 method createOneRadio() #txt
Ds0 f15 disableUIEvents false #txt
Ds0 f15 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f15 outParameterDecl '<> result;
' #txt
Ds0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createOneRadio()</name>
    </language>
</elementInfo>
' #txt
Ds0 f15 83 339 26 26 -49 15 #rect
Ds0 f15 @|RichDialogMethodStartIcon #fIcon
Ds0 f16 guid 1574A2991F562461 #txt
Ds0 f16 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f16 method createFileUpload() #txt
Ds0 f16 disableUIEvents false #txt
Ds0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f16 outParameterDecl '<> result;
' #txt
Ds0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createFileUpload()</name>
    </language>
</elementInfo>
' #txt
Ds0 f16 83 403 26 26 -51 15 #rect
Ds0 f16 @|RichDialogMethodStartIcon #fIcon
Ds0 f17 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f17 actionTable 'out=in;
' #txt
Ds0 f17 actionCode '
in.createInputAreaTab.id = in.createInputAreaTab.name + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createInputAreaTab);

' #txt
Ds0 f17 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Values from createInputAreaTab, create and add Formelement</name>
        <nameStyle>62,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f17 207 202 368 44 -180 -8 #rect
Ds0 f17 @|StepIcon #fIcon
Ds0 f18 expr out #txt
Ds0 f18 109 224 207 224 #arcP
Ds0 f19 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f19 592 48 32 32 0 16 #rect
Ds0 f19 @|AlternativeIcon #fIcon
Ds0 f7 expr in #txt
Ds0 f7 624 64 672 64 #arcP
Ds0 f21 expr out #txt
Ds0 f21 575 160 608 80 #arcP
Ds0 f21 1 608 160 #addKink
Ds0 f21 0 0.8169815478141365 0 0 #arcLabel
Ds0 f11 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f11 actionTable 'out=in;
out.activeTab=0;
' #txt
Ds0 f11 actionCode 'in.dragAndDropController = in.data.dragAndDropController;
in.dynaFormController = in.data.dynaFormController;

in.dynaFormController.createForm();


ivy.task.setName(ivy.cms.co("/Dialogs/Tasks/FormDefinition/TaskName"));
ivy.task.setDescription(ivy.cms.co("/Dialogs/Tasks/FormDefinition/TaskDescription"));' #txt
Ds0 f11 security system #txt
Ds0 f11 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init of DnDFormelements Helper</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f11 240 42 192 44 -89 -8 #rect
Ds0 f11 @|StepIcon #fIcon
Ds0 f13 expr out #txt
Ds0 f13 109 64 240 64 #arcP
Ds0 f20 expr out #txt
Ds0 f20 432 64 592 64 #arcP
Ds0 f22 expr out #txt
Ds0 f22 575 224 608 80 #arcP
Ds0 f22 1 616 224 #addKink
Ds0 f22 2 608 224 #addKink
Ds0 f22 2 0.3515094270353499 0 0 #arcLabel
Ds0 f24 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f24 595 499 26 26 0 12 #rect
Ds0 f24 @|RichDialogProcessEndIcon #fIcon
Ds0 f26 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f26 actionTable 'out=in;
' #txt
Ds0 f26 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>making sure the active tab get handed back to the engine</name>
        <nameStyle>56,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f26 224 490 320 44 -155 -8 #rect
Ds0 f26 @|StepIcon #fIcon
Ds0 f25 expr out #txt
Ds0 f25 544 512 595 512 #arcP
Ds0 f28 guid 15753E6FD948E4ED #txt
Ds0 f28 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f28 method updateForm() #txt
Ds0 f28 disableUIEvents false #txt
Ds0 f28 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f28 outParameterDecl '<> result;
' #txt
Ds0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateForm</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f28 83 595 26 26 -33 15 #rect
Ds0 f28 @|RichDialogMethodStartIcon #fIcon
Ds0 f29 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f29 595 595 26 26 0 12 #rect
Ds0 f29 @|RichDialogProcessEndIcon #fIcon
Ds0 f31 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f31 actionTable 'out=in;
' #txt
Ds0 f31 actionCode in.dynaFormController.createForm(); #txt
Ds0 f31 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update form</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f31 296 586 112 44 -33 -8 #rect
Ds0 f31 @|StepIcon #fIcon
Ds0 f32 expr out #txt
Ds0 f32 109 608 296 608 #arcP
Ds0 f30 expr out #txt
Ds0 f30 408 608 595 608 #arcP
Ds0 f23 guid 15754EC407DC81EC #txt
Ds0 f23 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f23 method tabChange() #txt
Ds0 f23 disableUIEvents false #txt
Ds0 f23 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f23 outParameterDecl '<> result;
' #txt
Ds0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>tabChange()</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f23 83 499 26 26 -34 15 #rect
Ds0 f23 @|RichDialogMethodStartIcon #fIcon
Ds0 f27 expr out #txt
Ds0 f27 109 512 224 512 #arcP
Ds0 f33 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f33 actionTable 'out=in;
' #txt
Ds0 f33 actionCode '
in.createManyCheckboxTab.id = in.createManyCheckboxTab.name + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createManyCheckboxTab);

' #txt
Ds0 f33 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Values from createManyCheckbox, create and add Formelement</name>
        <nameStyle>62,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f33 199 266 384 44 -185 -8 #rect
Ds0 f33 @|StepIcon #fIcon
Ds0 f34 expr out #txt
Ds0 f34 109 288 199 288 #arcP
Ds0 f35 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f35 actionTable 'out=in;
' #txt
Ds0 f35 actionCode '
in.createOneRadioTab.id = in.createOneRadioTab.name + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createOneRadioTab);


' #txt
Ds0 f35 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Values from createOneRadio, create and add Formelement</name>
        <nameStyle>58,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f35 215 329 352 44 -172 -8 #rect
Ds0 f35 @|StepIcon #fIcon
Ds0 f36 expr out #txt
Ds0 f36 108 351 215 351 #arcP
Ds0 f37 expr out #txt
Ds0 f37 583 288 608 80 #arcP
Ds0 f37 1 608 288 #addKink
Ds0 f37 1 0.6109477554941023 0 0 #arcLabel
Ds0 f38 expr out #txt
Ds0 f38 567 351 608 80 #arcP
Ds0 f38 1 608 351 #addKink
Ds0 f38 1 0.4796960275419352 0 0 #arcLabel
Ds0 f39 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f39 actionTable 'out=in;
' #txt
Ds0 f39 actionCode '
in.createFileUploadTab.id = in.createFileUploadTab.name + new DateTime();
in.dragAndDropController.availableFormelements.add(in.createFileUploadTab);


' #txt
Ds0 f39 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Values from createFileUpload, create and add Formelement</name>
        <nameStyle>60,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f39 215 394 352 44 -173 -8 #rect
Ds0 f39 @|StepIcon #fIcon
Ds0 f40 expr out #txt
Ds0 f40 109 416 215 416 #arcP
Ds0 f41 expr out #txt
Ds0 f41 567 416 608 80 #arcP
Ds0 f41 1 608 416 #addKink
Ds0 f41 1 0.35720571036856263 0 0 #arcLabel
Ds0 f2 expr out #txt
Ds0 f2 784 64 843 64 #arcP
Ds0 f44 guid 15791C3E4B5AA64C #txt
Ds0 f44 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f44 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
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
Ds0 f44 83 787 26 26 -13 15 #rect
Ds0 f44 @|RichDialogProcessStartIcon #fIcon
Ds0 f45 expr out #txt
Ds0 f45 109 800 576 749 #arcP
Ds0 f45 1 576 800 #addKink
Ds0 f45 0 0.5699203045049207 0 0 #arcLabel
Ds0 f42 guid 15798472F333F271 #txt
Ds0 f42 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f42 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f42 actionTable 'out=in;
out.data.directExecutionFlag=true;
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
Ds0 f42 83 723 26 26 -41 15 #rect
Ds0 f42 @|RichDialogProcessStartIcon #fIcon
Ds0 f43 expr out #txt
Ds0 f43 109 736 563 736 #arcP
Ds0 f43 0 0.5649178034353388 0 0 #arcLabel
Ds0 f51 guid 158942FA3188C6C0 #txt
Ds0 f51 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f51 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
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
Ds0 f51 83 915 26 26 -18 15 #rect
Ds0 f51 @|RichDialogProcessStartIcon #fIcon
Ds0 f3 guid 158A981757CB402D #txt
Ds0 f3 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f3 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f3 83 851 26 26 -15 15 #rect
Ds0 f3 @|RichDialogProcessStartIcon #fIcon
Ds0 f5 expr out #txt
Ds0 f5 109 864 576 749 #arcP
Ds0 f5 1 576 864 #addKink
Ds0 f5 0 0.6342623844357043 0 0 #arcLabel
Ds0 f48 actionDecl 'ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData out;
' #txt
Ds0 f48 actionTable 'out=in;
' #txt
Ds0 f48 actionCode 'in.data.discard = true;' #txt
Ds0 f48 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>discard = true</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f48 216 906 112 44 -37 -8 #rect
Ds0 f48 @|StepIcon #fIcon
Ds0 f50 type ch.ivy.gawfs.workflowCreation.FormDefinition.FormDefinitionData #txt
Ds0 f50 guid 15AF77AA5D3C3910 #txt
Ds0 f50 387 915 26 26 0 12 #rect
Ds0 f50 @|RichDialogEndIcon #fIcon
Ds0 f53 expr out #txt
Ds0 f53 328 928 387 928 #arcP
Ds0 f54 expr out #txt
Ds0 f54 109 928 216 928 #arcP
Ds0 f54 0 0.20328842452716808 0 0 #arcLabel
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
Ds0 f0 mainOut f13 tail #connect
Ds0 f13 head f11 mainIn #connect
Ds0 f11 mainOut f20 tail #connect
Ds0 f20 head f19 in #connect
Ds0 f17 mainOut f22 tail #connect
Ds0 f22 head f19 in #connect
Ds0 f26 mainOut f25 tail #connect
Ds0 f25 head f24 mainIn #connect
Ds0 f28 mainOut f32 tail #connect
Ds0 f32 head f31 mainIn #connect
Ds0 f31 mainOut f30 tail #connect
Ds0 f30 head f29 mainIn #connect
Ds0 f23 mainOut f27 tail #connect
Ds0 f27 head f26 mainIn #connect
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
Ds0 f42 mainOut f43 tail #connect
Ds0 f43 head f4 mainIn #connect
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
Ds0 f48 mainOut f53 tail #connect
Ds0 f53 head f50 mainIn #connect
Ds0 f51 mainOut f54 tail #connect
Ds0 f54 head f48 mainIn #connect
