[Ivy]
[>Created: Thu Jul 27 17:13:48 ICT 2017]
15493A537A91F8FC 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemGeneralInfoProcess Big #zClass
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
Ts0 @PushWFArc f2 '' #zField
Ts0 @GridStep f69 '' #zField
Ts0 @RichDialogProcessStart f68 '' #zField
Ts0 @RichDialogProcessEnd f73 '' #zField
Ts0 @CallSub f71 '' #zField
Ts0 @PushWFArc f70 '' #zField
Ts0 @PushWFArc f72 '' #zField
Ts0 @PushWFArc f74 '' #zField
Ts0 @RichDialogProcessEnd f78 '' #zField
Ts0 @CallSub f76 '' #zField
Ts0 @GridStep f80 '' #zField
Ts0 @RichDialogMethodStart f75 '' #zField
Ts0 @PushWFArc f81 '' #zField
Ts0 @PushWFArc f77 '' #zField
Ts0 @CallSub f4 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @RichDialogMethodStart f5 '' #zField
Ts0 @RichDialogProcessEnd f7 '' #zField
Ts0 @CallSub f8 '' #zField
Ts0 @GridStep f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @GridStep f18 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f20 '' #zField
>Proto Ts0 Ts0 TaskItemGeneralInfoProcess #zField
Ts0 f0 guid 15493A53801D93FC #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
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
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f69 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f69 actionTable 'out=in;
' #txt
Ts0 f69 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.jsf.Attrs;

RemoteTask remoteTask = Attrs.currentContext().get("task") as RemoteTask;
in.globalCaseId = GlobalCaseId.inServer(remoteTask.applicationRegister.serverId).caseId(remoteTask.case.getId()).build();
in.caseName = remoteTask.case.name;' #txt
Ts0 f69 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare global case id</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f69 190 148 36 24 20 -2 #rect
Ts0 f69 @|StepIcon #fIcon
Ts0 f68 guid 15494E15A857FE42 #txt
Ts0 f68 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f68 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f68 actionTable 'out=in;
' #txt
Ts0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCase</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f68 197 85 22 22 14 0 #rect
Ts0 f68 @|RichDialogProcessStartIcon #fIcon
Ts0 f73 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f73 197 309 22 22 14 0 #rect
Ts0 f73 @|RichDialogProcessEndIcon #fIcon
Ts0 f71 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f71 processCall 'Functional Processes/Navigator:viewCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Ts0 f71 doCall true #txt
Ts0 f71 requestActionDecl '<java.lang.String caseName,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;
' #txt
Ts0 f71 requestMappingAction 'param.caseName=in.caseName;
param.caseId=in.globalCaseId;
' #txt
Ts0 f71 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f71 responseMappingAction 'out=in;
' #txt
Ts0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f71 190 212 36 24 20 -2 #rect
Ts0 f71 @|CallSubIcon #fIcon
Ts0 f70 expr out #txt
Ts0 f70 208 107 208 148 #arcP
Ts0 f72 expr out #txt
Ts0 f72 208 172 208 212 #arcP
Ts0 f74 expr out #txt
Ts0 f74 208 236 208 309 #arcP
Ts0 f78 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f78 429 421 22 22 14 0 #rect
Ts0 f78 @|RichDialogProcessEndIcon #fIcon
Ts0 f76 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f76 processCall MultiPortal/TaskService:save(ch.ivy.ws.addon.IvyTask,Long) #txt
Ts0 f76 doCall true #txt
Ts0 f76 requestActionDecl '<ch.ivy.ws.addon.IvyTask task,java.lang.Long serverId> param;
' #txt
Ts0 f76 requestMappingAction 'param.task=in.ivyTask;
param.serverId=in.task.applicationRegister.getServerId();
' #txt
Ts0 f76 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f76 responseMappingAction 'out=in;
' #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f76 422 212 36 24 20 -2 #rect
Ts0 f76 @|CallSubIcon #fIcon
Ts0 f80 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f80 actionTable 'out=in;
' #txt
Ts0 f80 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
in.ivyTask = service.prepareDataToSaveTask(in.task);' #txt
Ts0 f80 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f80 422 148 36 24 20 -2 #rect
Ts0 f80 @|StepIcon #fIcon
Ts0 f75 guid 15494E41FCAFE092 #txt
Ts0 f75 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f75 method updateDeadline(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f75 disableUIEvents false #txt
Ts0 f75 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f75 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f75 outParameterDecl '<> result;
' #txt
Ts0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateDeadline(RemoteTask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f75 429 85 22 22 14 0 #rect
Ts0 f75 @|RichDialogMethodStartIcon #fIcon
Ts0 f81 expr out #txt
Ts0 f81 440 107 440 148 #arcP
Ts0 f77 expr out #txt
Ts0 f77 440 172 440 212 #arcP
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f4 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Ts0 f4 doCall true #txt
Ts0 f4 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Ts0 f4 requestMappingAction 'param.server=in.task.#applicationRegister.#server;
param.caseId=in.task.case.getId();
param.username=ivy.session.getSessionUserName();
param.content=in.changeDeadlineNoteContent;
' #txt
Ts0 f4 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f4 responseMappingAction 'out=in;
' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f4 422 356 36 24 20 -2 #rect
Ts0 f4 @|CallSubIcon #fIcon
Ts0 f3 expr out #txt
Ts0 f3 440 380 440 421 #arcP
Ts0 f5 guid 1557B3C895921579 #txt
Ts0 f5 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f5 method updatePriority(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f5 disableUIEvents false #txt
Ts0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask remoteTask> param = methodEvent.getInputArguments();
' #txt
Ts0 f5 inParameterMapAction 'out.task=param.remoteTask;
' #txt
Ts0 f5 outParameterDecl '<> result;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePriority(RemoteTask)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f5 645 85 22 22 14 0 #rect
Ts0 f5 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f7 645 309 22 22 14 0 #rect
Ts0 f7 @|RichDialogProcessEndIcon #fIcon
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f8 processCall MultiPortal/TaskService:save(ch.ivy.ws.addon.IvyTask,Long) #txt
Ts0 f8 doCall true #txt
Ts0 f8 requestActionDecl '<ch.ivy.ws.addon.IvyTask task,java.lang.Long serverId> param;
' #txt
Ts0 f8 requestMappingAction 'param.task=in.ivyTask;
param.serverId=in.task.applicationRegister.getServerId();
' #txt
Ts0 f8 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f8 responseMappingAction 'out=in;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f8 638 244 36 24 20 -2 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f9 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f9 actionTable 'out=in;
' #txt
Ts0 f9 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
in.ivyTask = service.prepareDataToSaveTask(in.task);' #txt
Ts0 f9 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f9 638 180 36 24 20 -2 #rect
Ts0 f9 @|StepIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 656 204 656 244 #arcP
Ts0 f11 expr out #txt
Ts0 f11 656 107 656 180 #arcP
Ts0 f12 expr out #txt
Ts0 f12 656 268 656 309 #arcP
Ts0 f18 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData out;
' #txt
Ts0 f18 actionTable 'out=in;
' #txt
Ts0 f18 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.service.TaskInforActionService;


TaskInforActionService service = new TaskInforActionService();
IUser user = ivy.session.getSessionUser();
String fullName = user.getFullName();
String userName = StringUtils.substring(user.getMemberName(), 1);
in.changeDeadlineNoteContent = service.prepareChangeDeadlineNoteContent(fullName, userName ,in.task.expiryTimestamp, in.task.id);' #txt
Ts0 f18 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare note content</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f18 422 292 36 24 20 -2 #rect
Ts0 f18 @|StepIcon #fIcon
Ts0 f16 expr out #txt
Ts0 f16 440 316 440 356 #arcP
Ts0 f20 expr out #txt
Ts0 f20 440 236 440 292 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f68 mainOut f70 tail #connect
Ts0 f70 head f69 mainIn #connect
Ts0 f69 mainOut f72 tail #connect
Ts0 f72 head f71 mainIn #connect
Ts0 f71 mainOut f74 tail #connect
Ts0 f74 head f73 mainIn #connect
Ts0 f75 mainOut f81 tail #connect
Ts0 f81 head f80 mainIn #connect
Ts0 f80 mainOut f77 tail #connect
Ts0 f77 head f76 mainIn #connect
Ts0 f4 mainOut f3 tail #connect
Ts0 f3 head f78 mainIn #connect
Ts0 f9 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f5 mainOut f11 tail #connect
Ts0 f11 head f9 mainIn #connect
Ts0 f8 mainOut f12 tail #connect
Ts0 f12 head f7 mainIn #connect
Ts0 f18 mainOut f16 tail #connect
Ts0 f16 head f4 mainIn #connect
Ts0 f76 mainOut f20 tail #connect
Ts0 f20 head f18 mainIn #connect
