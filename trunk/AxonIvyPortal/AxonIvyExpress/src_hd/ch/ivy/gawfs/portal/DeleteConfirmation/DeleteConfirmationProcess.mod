[Ivy]
[>Created: Wed May 10 11:44:14 ICT 2017]
15811D6A5CA25F9C 3.20 #module
>Proto >Proto Collection #zClass
Gs0 DeleteConfirmationProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @GridStep f8 '' #zField
Gs0 @PushWFArc f6 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @RichDialogProcessStart f7 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @PushWFArc f9 '' #zField
>Proto Gs0 Gs0 DeleteConfirmationProcess #zField
Gs0 f0 guid 158034522559ACD1 #txt
Gs0 f0 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f0 method start(gawfs.DevLoadWorkflowsData) #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 inParameterMapAction 'out.devLoadWorkflowsData=param.devLoadWorkflowsData;
' #txt
Gs0 f0 outParameterDecl '<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> result;
' #txt
Gs0 f0 outParameterMapAction 'result.devLoadWorkflowsData=in.devLoadWorkflowsData;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(DevLoadWorkflowsData)</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -82 15 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f1 435 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f3 guid 1580345226E6BE12 #txt
Gs0 f3 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f3 actionDecl 'ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData out;
' #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeDelete</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -33 15 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f4 guid 158034522701D8F0 #txt
Gs0 f4 436 148 24 24 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f8 actionDecl 'ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData out;
' #txt
Gs0 f8 actionTable 'out=in;
' #txt
Gs0 f8 actionCode 'import gawfs.Workflow;
import gawfs.Workflow;

gawfs.Workflow workflow = ivy.persistence.GAWFS.find(gawfs.Workflow.class,in.devLoadWorkflowsData.workflowID) as gawfs.Workflow;

in.devLoadWorkflowsData.workflowDescription = workflow.processDescription;
in.devLoadWorkflowsData.workflowName = workflow.processName;



' #txt
Gs0 f8 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Workflow Credentials</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f8 184 42 144 44 -68 -8 #rect
Gs0 f8 @|StepIcon #fIcon
Gs0 f6 expr out #txt
Gs0 f6 109 64 184 64 #arcP
Gs0 f2 expr out #txt
Gs0 f2 328 64 435 64 #arcP
Gs0 f7 guid 15811E2391A3BDB3 #txt
Gs0 f7 type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
Gs0 f7 actionDecl 'ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData out;
' #txt
Gs0 f7 actionTable 'out=in;
out.devLoadWorkflowsData.deleteFlag=false;
out.devLoadWorkflowsData.workflowID=null;
' #txt
Gs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeCancel</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f7 83 219 26 26 -34 15 #rect
Gs0 f7 @|RichDialogProcessStartIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 109 160 436 160 #arcP
Gs0 f9 expr out #txt
Gs0 f9 108 229 436 162 #arcP
>Proto Gs0 .type ch.ivy.gawfs.portal.DeleteConfirmation.DeleteConfirmationData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f6 tail #connect
Gs0 f6 head f8 mainIn #connect
Gs0 f8 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
Gs0 f7 mainOut f9 tail #connect
Gs0 f9 head f4 mainIn #connect
