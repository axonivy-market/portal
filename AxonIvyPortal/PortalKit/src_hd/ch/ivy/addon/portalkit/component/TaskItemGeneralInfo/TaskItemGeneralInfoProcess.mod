[Ivy]
15493A537A91F8FC 9.3.1 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemGeneralInfoProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdMethod f5 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @GridStep f15 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @UdProcessEnd f19 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @GridStep f7 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @CallSub f9 '' #zField
Ts0 @UdMethod f16 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f18 '' #zField
Ts0 @GridStep f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f24 '' #zField
>Proto Ts0 Ts0 TaskItemGeneralInfoProcess #zField
Ts0 f0 guid 1682691BC1A26D76 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f1 307 83 26 26 0 12 #rect
Ts0 f2 expr out #txt
Ts0 f2 109 96 307 96 #arcP
Ts0 f5 guid 16826946D7BC78E5 #txt
Ts0 f5 method updateExpiryTime(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f5 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f5 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f5 outParameterDecl '<> result;' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateExpiryTime(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 83 275 26 26 -63 15 #rect
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

// Behind the scene, Portal sets expiry activator to the current responsible then set expiry time.
boolean isUpdateExpiryActivator = false;
if (in.task.getExpiryActivator() == null) {
	in.task.setExpiryActivator(in.task.getActivator());
	isUpdateExpiryActivator = true;
}

TaskInforActionService service = new TaskInforActionService();
IUser user = ivy.session.getSessionUser();
String fullName = user.getFullName();
String userName = StringUtils.substring(user.getMemberName(), 1);

if (!in.#expiryTimestamp is initialized) {
	TaskUtils.removeTaskDeadline(in.task);
	in.changeDeadlineNoteContent = service.prepareRemoveExpiryTimeNoteContent(fullName, userName, in.task.getId());
} else {
	in.task.setExpiryTimestamp(in.expiryTimestamp);
	in.changeDeadlineNoteContent = service.prepareChangeExpiryNoteContent(fullName, userName, in.task.expiryTimestamp, in.task.getId());
}

if (isUpdateExpiryActivator) {
	in.changeDeadlineNoteContent = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/setExpiryActivatorAndTimeNotes", Arrays.asList(in.changeDeadlineNoteContent, in.task.getExpiryActivator().getDisplayName()));
}' #txt
Ts0 f13 security system #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change task expiry</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 168 266 112 44 -52 -8 #rect
Ts0 f14 expr out #txt
Ts0 f14 109 288 168 288 #arcP
Ts0 f15 actionTable 'out=in;
' #txt
Ts0 f15 actionCode 'in.task.getCase().createNote(ivy.session, in.changeDeadlineNoteContent);' #txt
Ts0 f15 security system #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 328 266 112 44 -24 -8 #rect
Ts0 f17 expr out #txt
Ts0 f17 280 288 328 288 #arcP
Ts0 f19 499 275 26 26 0 12 #rect
Ts0 f20 expr out #txt
Ts0 f20 440 288 499 288 #arcP
Ts0 f11 499 179 26 26 0 12 #rect
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;

in.globalCaseId = GlobalCaseId.caseId(in.selectedCase.getId()).build();' #txt
Ts0 f7 security system #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare global case id</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 160 170 128 44 -61 -8 #rect
Ts0 f12 expr out #txt
Ts0 f12 440 192 499 192 #arcP
Ts0 f9 processCall 'Functional Processes/Navigator:viewCase(ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Ts0 f9 requestActionDecl '<ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Ts0 f9 requestMappingAction 'param.caseId=in.globalCaseId;
' #txt
Ts0 f9 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f9 responseMappingAction 'out=in;
' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 328 170 112 44 -26 -8 #rect
Ts0 f16 guid 1720D0EE85E5021E #txt
Ts0 f16 method navigateToRelatedCase(ch.ivyteam.ivy.workflow.ICase) #txt
Ts0 f16 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase selectedCase> param;' #txt
Ts0 f16 inParameterMapAction 'out.selectedCase=param.selectedCase;
' #txt
Ts0 f16 outParameterDecl '<> result;' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCase(ICase)</name>
    </language>
</elementInfo>
' #txt
Ts0 f16 83 179 26 26 -63 20 #rect
Ts0 f3 109 192 160 192 #arcP
Ts0 f6 guid 172FDEB375661428 #txt
Ts0 f6 method updateDelayTimestamp(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateDelayTimestamp(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 403 26 26 -76 27 #rect
Ts0 f18 427 403 26 26 0 12 #rect
Ts0 f22 actionTable 'out=in;
' #txt
Ts0 f22 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
IUser user = ivy.session.getSessionUser();
String fullName = user.getFullName();
String userName = StringUtils.substring(user.getMemberName(), 1);
String delayNote = service.prepareChangeDelayNoteContent(fullName, userName, in.task.delayTimestamp, in.task.getId());
in.task.getCase().createNote(ivy.session, delayNote);
' #txt
Ts0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Note</name>
    </language>
</elementInfo>
' #txt
Ts0 f22 200 394 112 44 -25 -8 #rect
Ts0 f23 109 416 200 416 #arcP
Ts0 f21 312 416 427 416 #arcP
Ts0 f24 expr out #txt
Ts0 f24 288 192 328 192 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f9 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f5 mainOut f14 tail #connect
Ts0 f14 head f13 mainIn #connect
Ts0 f13 mainOut f17 tail #connect
Ts0 f17 head f15 mainIn #connect
Ts0 f15 mainOut f20 tail #connect
Ts0 f20 head f19 mainIn #connect
Ts0 f16 mainOut f3 tail #connect
Ts0 f3 head f7 mainIn #connect
Ts0 f6 mainOut f23 tail #connect
Ts0 f23 head f22 mainIn #connect
Ts0 f22 mainOut f21 tail #connect
Ts0 f21 head f18 mainIn #connect
Ts0 f7 mainOut f24 tail #connect
Ts0 f24 head f9 mainIn #connect
