[Ivy]
[>Created: Tue May 05 17:50:47 ICT 2015]
14CFA1989B22854E 3.17 #module
>Proto >Proto Collection #zClass
Ts0 TaskPriorityProcess Big #zClass
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
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @GridStep f3 '' #zField
Ts0 @PushWFArc f4 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogProcessStart f8 '' #zField
Ts0 @RichDialogProcessEnd f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @RichDialogProcessEnd f12 '' #zField
Ts0 @RichDialogMethodStart f14 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @RichDialogMethodStart f13 '' #zField
Ts0 @RichDialogProcessEnd f15 '' #zField
Ts0 @GridStep f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f16 '' #zField
>Proto Ts0 Ts0 TaskPriorityProcess #zField
Ts0 f0 guid 14B6C8A45D15025F #txt
Ts0 f0 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
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
Ts0 f0 86 54 20 20 13 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f1 86 182 20 20 13 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData out;
' #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 actionCode 'import ch.ivyteam.ivy.workflow.WorkflowPriority;

in.priorities = [WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW];' #txt
Ts0 f3 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init
priority values</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f3 78 108 36 24 20 -2 #rect
Ts0 f3 @|StepIcon #fIcon
Ts0 f4 expr out #txt
Ts0 f4 96 74 96 108 #arcP
Ts0 f2 expr out #txt
Ts0 f2 96 132 96 182 #arcP
Ts0 f8 guid 14CFA5CE8C6D9723 #txt
Ts0 f8 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData out;
' #txt
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f8 214 54 20 20 13 0 #rect
Ts0 f8 @|RichDialogProcessStartIcon #fIcon
Ts0 f9 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f9 214 182 20 20 13 0 #rect
Ts0 f9 @|RichDialogProcessEndIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 224 74 224 182 #arcP
Ts0 f12 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f12 590 182 20 20 13 0 #rect
Ts0 f12 @|RichDialogProcessEndIcon #fIcon
Ts0 f14 guid 14CFA72B4E142236 #txt
Ts0 f14 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f14 method preTaskPriority(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f14 disableUIEvents false #txt
Ts0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f14 inActionCode '// get current priority
if(param.task != null){
	out.currentPriority = param.task.getPriority();
}
' #txt
Ts0 f14 outParameterDecl '<> result;
' #txt
Ts0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>preTaskPriority(ITask)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f14 590 54 20 20 13 0 #rect
Ts0 f14 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 600 74 600 182 #arcP
Ts0 f13 guid 14D1DCBA138E2485 #txt
Ts0 f13 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f13 method savePriority(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f13 disableUIEvents false #txt
Ts0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f13 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f13 outParameterDecl '<> result;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>savePriority(ITask)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f13 462 54 20 20 13 0 #rect
Ts0 f13 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f15 462 182 20 20 13 0 #rect
Ts0 f15 @|RichDialogProcessEndIcon #fIcon
Ts0 f17 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData out;
' #txt
Ts0 f17 actionTable 'out=in;
' #txt
Ts0 f17 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//Original Priority
in.task.setOriginalPriority(in.currentPriority);
//Expiry Priority
in.task.setExpiryPriority(in.currentPriority);

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskPriorityDialog/message/setPrioritySuccess"), null ));' #txt
Ts0 f17 type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
Ts0 f17 454 108 36 24 20 -2 #rect
Ts0 f17 @|StepIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 472 74 472 108 #arcP
Ts0 f16 expr out #txt
Ts0 f16 472 132 472 182 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority.TaskPriorityData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Starts</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel>Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>232</swimlaneSize>
    <swimlaneSize>344</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-13108</swimlaneColor>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f4 tail #connect
Ts0 f4 head f3 mainIn #connect
Ts0 f3 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f8 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f14 mainOut f7 tail #connect
Ts0 f7 head f12 mainIn #connect
Ts0 f13 mainOut f18 tail #connect
Ts0 f18 head f17 mainIn #connect
Ts0 f17 mainOut f16 tail #connect
Ts0 f16 head f15 mainIn #connect
