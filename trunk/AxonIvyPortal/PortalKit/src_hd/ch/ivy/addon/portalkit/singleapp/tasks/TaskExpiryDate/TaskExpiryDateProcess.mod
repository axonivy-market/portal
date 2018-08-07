[Ivy]
[>Created: Mon May 04 17:46:04 ICT 2015]
14D1E188D366B4D6 3.17 #module
>Proto >Proto Collection #zClass
Ts0 TaskExpiryDateProcess Big #zClass
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
Ts0 @RichDialogProcessStart f8 '' #zField
Ts0 @RichDialogProcessEnd f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @RichDialogMethodStart f13 '' #zField
Ts0 @RichDialogProcessEnd f15 '' #zField
Ts0 @GridStep f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskExpiryDateProcess #zField
Ts0 f0 guid 14B6C8A45D15025F #txt
Ts0 f0 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
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
Ts0 f1 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f1 86 182 20 20 13 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f8 guid 14CFA5CE8C6D9723 #txt
Ts0 f8 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData out;
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
Ts0 f8 270 54 20 20 13 0 #rect
Ts0 f8 @|RichDialogProcessStartIcon #fIcon
Ts0 f9 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f9 270 182 20 20 13 0 #rect
Ts0 f9 @|RichDialogProcessEndIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 280 74 280 182 #arcP
Ts0 f13 guid 14D1DCBA138E2485 #txt
Ts0 f13 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f13 method saveExpiryDate(ch.ivyteam.ivy.workflow.ITask) #txt
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
        <name>saveExpiryDate(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 462 54 20 20 13 0 #rect
Ts0 f13 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f15 462 182 20 20 13 0 #rect
Ts0 f15 @|RichDialogProcessEndIcon #fIcon
Ts0 f17 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData out;
' #txt
Ts0 f17 actionTable 'out=in;
' #txt
Ts0 f17 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskExpiryDateDialog/message/setDeadlineSuccess"), null ));
' #txt
Ts0 f17 type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
Ts0 f17 454 108 36 24 20 -2 #rect
Ts0 f17 @|StepIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 472 74 472 108 #arcP
Ts0 f16 expr out #txt
Ts0 f16 472 132 472 182 #arcP
Ts0 f2 expr out #txt
Ts0 f2 96 74 96 182 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate.TaskExpiryDateData #txt
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
Ts0 f8 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f13 mainOut f18 tail #connect
Ts0 f18 head f17 mainIn #connect
Ts0 f17 mainOut f16 tail #connect
Ts0 f16 head f15 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
