[Ivy]
16C8374336423894 3.26 #module
>Proto >Proto Collection #zClass
Ts0 TestTaskTemplateProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @RichDialogProcessStart f3 '' #zField
Ts0 @RichDialogEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TestTaskTemplateProcess #zField
Ts0 f0 guid 16C8374337A6693D #txt
Ts0 f0 type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
Ts0 f0 method start(ch.ivyteam.ivy.project.portal.examples.showcase.Data) #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData out;
' #txt
Ts0 f0 inParameterMapAction 'out.data=param.data;
' #txt
Ts0 f0 outParameterDecl '<ch.ivyteam.ivy.project.portal.examples.showcase.Data data> result;
' #txt
Ts0 f0 outParameterMapAction 'result.data=in.data;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Data)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -29 15 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
Ts0 f1 339 51 26 26 0 12 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f3 guid 16C837433A29385A #txt
Ts0 f3 type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
Ts0 f3 actionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -15 12 #rect
Ts0 f3 @|RichDialogProcessStartIcon #fIcon
Ts0 f4 type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
Ts0 f4 guid 16C837433A2E4026 #txt
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|RichDialogEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 160 211 160 #arcP
Ts0 f6 actionDecl 'ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData out;
' #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'in.actualStepIndex = 0;
in.steps.add("Start");
in.steps.add("Create request process testing");
in.steps.add("Clarify process testing");
in.steps.add("Approve process testing");
in.steps.add("Manager Approve process testing");
in.steps.add("Fisnished");
' #txt
Ts0 f6 type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 168 42 112 44 -21 -8 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 109 64 168 64 #arcP
Ts0 f2 expr out #txt
Ts0 f2 280 64 339 64 #arcP
>Proto Ts0 .type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f0 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
