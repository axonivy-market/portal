[Ivy]
[>Created: Tue Aug 04 09:53:07 ICT 2015]
14C4F002DC54C5D1 3.17 #module
>Proto >Proto Collection #zClass
Ts0 TaskDelegateProcess Big #zClass
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
Ts0 @RichDialogProcessStart f11 '' #zField
Ts0 @RichDialogProcessEnd f12 '' #zField
Ts0 @PushWFArc f13 '' #zField
Ts0 @RichDialogMethodStart f14 '' #zField
Ts0 @RichDialogProcessEnd f15 '' #zField
Ts0 @GridStep f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @RichDialogProcessStart f16 '' #zField
Ts0 @RichDialogProcessEnd f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @GridStep f19 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogProcessStart f21 '' #zField
Ts0 @RichDialogProcessEnd f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
>Proto Ts0 Ts0 TaskDelegateProcess #zField
Ts0 f0 guid 14B6C8A45D15025F #txt
Ts0 f0 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inParameterMapAction 'out.disabledDelegateButton=true;
out.isUser=true;
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
Ts0 f1 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f1 86 182 20 20 13 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f11 guid 14C55F7DD62DAAA2 #txt
Ts0 f11 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData out;
' #txt
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 actionCode '// reset data
in.selectedRole = null;
in.selectedUser = null;
in.isUser = true;
in.disabledDelegateButton = true;' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f11 558 54 20 20 13 0 #rect
Ts0 f11 @|RichDialogProcessStartIcon #fIcon
Ts0 f12 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f12 558 182 20 20 13 0 #rect
Ts0 f12 @|RichDialogProcessEndIcon #fIcon
Ts0 f13 expr out #txt
Ts0 f13 568 74 568 182 #arcP
Ts0 f14 guid 14C596D61B3E475A #txt
Ts0 f14 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f14 method delegateTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f14 disableUIEvents false #txt
Ts0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f14 inParameterMapAction 'out.itask=param.task;
' #txt
Ts0 f14 outParameterDecl '<> result;
' #txt
Ts0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegateTask(ITask)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f14 838 54 20 20 13 0 #rect
Ts0 f14 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f15 838 174 20 20 13 0 #rect
Ts0 f15 @|RichDialogProcessEndIcon #fIcon
Ts0 f7 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData out;
' #txt
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

if (in.selectedUser != null && in.selectedRole == null) {
	TaskUtils.delegateTask(in.itask,in.selectedUser);
} else {
	TaskUtils.delegateTask(in.itask,in.selectedRole);
}
// update message

// reset data
in.selectedRole = null;
in.selectedUser = null;
in.isUser = true;
in.disabledDelegateButton = true;' #txt
Ts0 f7 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegate task</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f7 830 116 36 24 25 -4 #rect
Ts0 f7 @|StepIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 848 74 848 116 #arcP
Ts0 f10 expr out #txt
Ts0 f10 848 140 848 174 #arcP
Ts0 f16 guid 14C5AB8753C19431 #txt
Ts0 f16 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f16 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData out;
' #txt
Ts0 f16 actionTable 'out=in;
' #txt
Ts0 f16 actionCode '
if (in.selectedRole != null || in.selectedUser != null) {
	in.disabledDelegateButton = false;
} else {
	in.disabledDelegateButton = true;
}' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>checkValue</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f16 286 278 20 20 13 0 #rect
Ts0 f16 @|RichDialogProcessStartIcon #fIcon
Ts0 f17 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f17 286 398 20 20 13 0 #rect
Ts0 f17 @|RichDialogProcessEndIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 296 298 296 398 #arcP
Ts0 f19 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData out;
' #txt
Ts0 f19 actionTable 'out=in;
' #txt
Ts0 f19 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.addon.portalkit.util.RoleUtils;

out.roles = RoleUtils.getRolesForDelegate();
out.users = UserUtils.getAllUsersForDelegate();' #txt
Ts0 f19 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f19 78 116 36 24 20 -2 #rect
Ts0 f19 @|StepIcon #fIcon
Ts0 f20 expr out #txt
Ts0 f20 96 74 96 116 #arcP
Ts0 f2 expr out #txt
Ts0 f2 96 140 96 182 #arcP
Ts0 f21 guid 14EB9407F722A0D3 #txt
Ts0 f21 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f21 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData out;
' #txt
Ts0 f21 actionTable 'out=in;
' #txt
Ts0 f21 actionCode 'if (in.isUser){
	out.selectedRole = null;
	if (out.selectedUser == null) {
		out.disabledDelegateButton = true;
	}
}else{
	out.selectedUser = null;
	if (out.selectedRole == null) {
		out.disabledDelegateButton = true;
	}
}' #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeAssignType</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f21 277 53 22 22 14 0 #rect
Ts0 f21 @|RichDialogProcessStartIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
Ts0 f22 277 181 22 22 14 0 #rect
Ts0 f22 @|RichDialogProcessEndIcon #fIcon
Ts0 f23 expr out #txt
Ts0 f23 288 75 288 181 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate.TaskDelegateData #txt
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
    <swimlaneSize>544</swimlaneSize>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-13108</swimlaneColor>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f11 mainOut f13 tail #connect
Ts0 f13 head f12 mainIn #connect
Ts0 f14 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f7 mainOut f10 tail #connect
Ts0 f10 head f15 mainIn #connect
Ts0 f16 mainOut f18 tail #connect
Ts0 f18 head f17 mainIn #connect
Ts0 f0 mainOut f20 tail #connect
Ts0 f20 head f19 mainIn #connect
Ts0 f19 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f21 mainOut f23 tail #connect
Ts0 f23 head f22 mainIn #connect
