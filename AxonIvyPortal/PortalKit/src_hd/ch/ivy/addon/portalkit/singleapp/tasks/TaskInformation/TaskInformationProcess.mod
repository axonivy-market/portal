[Ivy]
[>Created: Fri Sep 04 16:53:04 ICT 2015]
14BCA43966AC409B 3.17 #module
>Proto >Proto Collection #zClass
Ts0 TaskInformationProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogProcessEnd f13 '' #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessStart f8 '' #zField
Ts0 @RichDialogProcessEnd f9 '' #zField
Ts0 @RichDialogMethodStart f11 '' #zField
Ts0 @RichDialogProcessEnd f12 '' #zField
Ts0 @GridStep f14 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogMethodStart f3 '' #zField
Ts0 @RichDialogProcessEnd f4 '' #zField
Ts0 @GridStep f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @RichDialogProcessStart f19 '' #zField
Ts0 @RichDialogProcessEnd f20 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @RichDialogMethodStart f24 '' #zField
Ts0 @RichDialogProcessEnd f25 '' #zField
Ts0 @GridStep f27 '' #zField
Ts0 @PushWFArc f28 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @RichDialogProcessStart f10 '' #zField
Ts0 @RichDialogProcessEnd f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @RichDialogMethodStart f22 '' #zField
Ts0 @RichDialogProcessEnd f23 '' #zField
Ts0 @PushWFArc f29 '' #zField
Ts0 @RichDialogProcessStart f30 '' #zField
Ts0 @RichDialogProcessEnd f31 '' #zField
Ts0 @PushWFArc f32 '' #zField
Ts0 @RichDialogMethodStart f33 '' #zField
Ts0 @RichDialogProcessEnd f34 '' #zField
Ts0 @GridStep f35 '' #zField
Ts0 @PushWFArc f36 '' #zField
Ts0 @PushWFArc f37 '' #zField
>Proto Ts0 Ts0 TaskInformationProcess #zField
Ts0 f13 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f13 86 182 20 20 13 0 #rect
Ts0 f13 @|RichDialogProcessEndIcon #fIcon
Ts0 f0 guid 14C4F410510F9B73 #txt
Ts0 f0 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 86 38 20 20 13 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f8 guid 14C55CBD2D968147 #txt
Ts0 f8 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buttonDelegateClicked</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f8 342 38 20 20 13 0 #rect
Ts0 f8 @|RichDialogProcessStartIcon #fIcon
Ts0 f9 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f9 342 198 20 20 13 0 #rect
Ts0 f9 @|RichDialogProcessEndIcon #fIcon
Ts0 f11 guid 14C68CF1747693C3 #txt
Ts0 f11 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f11 method parkTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f11 disableUIEvents false #txt
Ts0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask iTask> param = methodEvent.getInputArguments();
' #txt
Ts0 f11 inParameterMapAction 'out.iTask=param.iTask;
' #txt
Ts0 f11 outParameterDecl '<> result;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>parkTask(ITask)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f11 710 38 20 20 13 0 #rect
Ts0 f11 @|RichDialogMethodStartIcon #fIcon
Ts0 f12 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f12 710 198 20 20 13 0 #rect
Ts0 f12 @|RichDialogProcessEndIcon #fIcon
Ts0 f14 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f14 actionTable 'out=in;
' #txt
Ts0 f14 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(out.iTask);

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskInformation/parkTaskSuccessfully"), null ));



' #txt
Ts0 f14 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>parkTask</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f14 702 116 36 24 20 -2 #rect
Ts0 f14 @|StepIcon #fIcon
Ts0 f15 expr out #txt
Ts0 f15 720 58 720 116 #arcP
Ts0 f16 expr out #txt
Ts0 f16 720 140 720 198 #arcP
Ts0 f1 expr out #txt
Ts0 f1 96 58 96 182 #arcP
Ts0 f2 expr out #txt
Ts0 f2 352 58 352 198 #arcP
Ts0 f3 guid 14C6F62390284766 #txt
Ts0 f3 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f3 method resetTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f3 disableUIEvents false #txt
Ts0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask iTask> param = methodEvent.getInputArguments();
' #txt
Ts0 f3 inParameterMapAction 'out.iTask=param.iTask;
' #txt
Ts0 f3 outParameterDecl '<> result;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(ITask)</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f3 870 46 20 20 13 0 #rect
Ts0 f3 @|RichDialogMethodStartIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f4 870 198 20 20 13 0 #rect
Ts0 f4 @|RichDialogProcessEndIcon #fIcon
Ts0 f5 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f5 actionTable 'out=in;
' #txt
Ts0 f5 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(out.iTask);

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskInformation/resetTaskSuccessfully"), null ));
' #txt
Ts0 f5 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f5 862 116 36 24 20 -2 #rect
Ts0 f5 @|StepIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 880 66 880 116 #arcP
Ts0 f7 expr out #txt
Ts0 f7 880 140 880 198 #arcP
Ts0 f19 guid 14CA2563936E01D4 #txt
Ts0 f19 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f19 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f19 actionTable 'out=in;
' #txt
Ts0 f19 actionCode 'out.newNoteMessage = "";' #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buttonCancelNoteClick</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f19 502 38 20 20 13 0 #rect
Ts0 f19 @|RichDialogProcessStartIcon #fIcon
Ts0 f20 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f20 502 190 20 20 13 0 #rect
Ts0 f20 @|RichDialogProcessEndIcon #fIcon
Ts0 f21 expr out #txt
Ts0 f21 512 58 512 190 #arcP
Ts0 f24 guid 14CB0D3CD44DAD8D #txt
Ts0 f24 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f24 method createTaskNote(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f24 disableUIEvents false #txt
Ts0 f24 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f24 inParameterMapAction 'out.iTask=param.task;
' #txt
Ts0 f24 outParameterDecl '<> result;
' #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskNote(ITask)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f24 710 246 20 20 13 0 #rect
Ts0 f24 @|RichDialogMethodStartIcon #fIcon
Ts0 f25 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f25 710 374 20 20 13 0 #rect
Ts0 f25 @|RichDialogProcessEndIcon #fIcon
Ts0 f27 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f27 actionTable 'out=in;
' #txt
Ts0 f27 actionCode 'if(in.newNoteMessage.trim() != ""){
	in.iTask.createNote(ivy.session, in.newNoteMessage);
}
//reset
in.newNoteMessage = "";' #txt
Ts0 f27 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f27 702 300 36 24 20 -2 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f28 expr out #txt
Ts0 f28 720 266 720 300 #arcP
Ts0 f26 expr out #txt
Ts0 f26 720 324 720 374 #arcP
Ts0 f10 guid 14CBB5CE6156FB02 #txt
Ts0 f10 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f10 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f10 342 246 20 20 13 0 #rect
Ts0 f10 @|RichDialogProcessStartIcon #fIcon
Ts0 f17 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f17 342 374 20 20 13 0 #rect
Ts0 f17 @|RichDialogProcessEndIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 352 266 352 374 #arcP
Ts0 f22 guid 14CC6D5A4E91A77F #txt
Ts0 f22 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f22 method viewNote(ch.ivyteam.ivy.workflow.INote) #txt
Ts0 f22 disableUIEvents false #txt
Ts0 f22 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.INote note> param = methodEvent.getInputArguments();
' #txt
Ts0 f22 inParameterMapAction 'out.selectedNote=param.note;
' #txt
Ts0 f22 outParameterDecl '<> result;
' #txt
Ts0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewNote(INote)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f22 870 246 20 20 13 0 #rect
Ts0 f22 @|RichDialogMethodStartIcon #fIcon
Ts0 f23 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f23 870 374 20 20 13 0 #rect
Ts0 f23 @|RichDialogProcessEndIcon #fIcon
Ts0 f29 expr out #txt
Ts0 f29 880 266 880 374 #arcP
Ts0 f30 guid 14D1E516B044C6E4 #txt
Ts0 f30 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f30 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f30 actionTable 'out=in;
' #txt
Ts0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buttonGenericClicked</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f30 502 246 20 20 13 0 #rect
Ts0 f30 @|RichDialogProcessStartIcon #fIcon
Ts0 f31 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f31 502 374 20 20 13 0 #rect
Ts0 f31 @|RichDialogProcessEndIcon #fIcon
Ts0 f32 expr out #txt
Ts0 f32 512 266 512 374 #arcP
Ts0 f33 guid 14F96874075BF0EC #txt
Ts0 f33 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f33 method startTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f33 disableUIEvents false #txt
Ts0 f33 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask iTask> param = methodEvent.getInputArguments();
' #txt
Ts0 f33 inParameterMapAction 'out.iTask=param.iTask;
' #txt
Ts0 f33 outParameterDecl '<> result;
' #txt
Ts0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f33 710 438 20 20 13 0 #rect
Ts0 f33 @|RichDialogMethodStartIcon #fIcon
Ts0 f34 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f34 710 566 20 20 13 0 #rect
Ts0 f34 @|RichDialogProcessEndIcon #fIcon
Ts0 f35 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData out;
' #txt
Ts0 f35 actionTable 'out=in;
' #txt
Ts0 f35 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
String startLink = externalContext.getRequestContextPath() + "/pro/" + in.iTask.getFullRequestPath() + "?taskId=" + in.iTask.getId();
TaskUtils.resetTask(in.iTask);
externalContext.redirect(startLink);' #txt
Ts0 f35 type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
Ts0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f35 702 500 36 24 20 -2 #rect
Ts0 f35 @|StepIcon #fIcon
Ts0 f36 expr out #txt
Ts0 f36 720 458 720 500 #arcP
Ts0 f37 expr out #txt
Ts0 f37 720 524 720 566 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation.TaskInformationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>start methods</swimlaneLabel>
        <swimlaneLabel>events</swimlaneLabel>
        <swimlaneLabel>Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>296</swimlaneSize>
    <swimlaneSize>352</swimlaneSize>
    <swimlaneSize>376</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-52</swimlaneColor>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f11 mainOut f15 tail #connect
Ts0 f15 head f14 mainIn #connect
Ts0 f14 mainOut f16 tail #connect
Ts0 f16 head f12 mainIn #connect
Ts0 f0 mainOut f1 tail #connect
Ts0 f1 head f13 mainIn #connect
Ts0 f8 mainOut f2 tail #connect
Ts0 f2 head f9 mainIn #connect
Ts0 f3 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f7 tail #connect
Ts0 f7 head f4 mainIn #connect
Ts0 f19 mainOut f21 tail #connect
Ts0 f21 head f20 mainIn #connect
Ts0 f24 mainOut f28 tail #connect
Ts0 f28 head f27 mainIn #connect
Ts0 f27 mainOut f26 tail #connect
Ts0 f26 head f25 mainIn #connect
Ts0 f10 mainOut f18 tail #connect
Ts0 f18 head f17 mainIn #connect
Ts0 f22 mainOut f29 tail #connect
Ts0 f29 head f23 mainIn #connect
Ts0 f30 mainOut f32 tail #connect
Ts0 f32 head f31 mainIn #connect
Ts0 f33 mainOut f36 tail #connect
Ts0 f36 head f35 mainIn #connect
Ts0 f35 mainOut f37 tail #connect
Ts0 f37 head f34 mainIn #connect
