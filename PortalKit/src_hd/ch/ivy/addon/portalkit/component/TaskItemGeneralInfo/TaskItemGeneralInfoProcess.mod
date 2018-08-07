[Ivy]
[>Created: Mon May 09 17:50:39 ICT 2016]
15493A537A91F8FC 3.18 #module
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
Ts0 @PushWFArc f79 '' #zField
Ts0 @PushWFArc f81 '' #zField
Ts0 @PushWFArc f77 '' #zField
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
Ts0 f78 429 309 22 22 14 0 #rect
Ts0 f78 @|RichDialogProcessEndIcon #fIcon
Ts0 f76 type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
Ts0 f76 processCall MultiPortal/TaskService:save(ch.ivy.ws.addon.IvyTask,Long) #txt
Ts0 f76 doCall true #txt
Ts0 f76 requestActionDecl '<ch.ivy.ws.addon.IvyTask task,java.lang.Long serverId> param;
' #txt
Ts0 f76 requestMappingAction 'param.task=in.ivyTask;
param.serverId=in.ivyTask.getServerId();
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
Ts0 f80 actionCode 'import java.util.Calendar;
import ch.ivy.ws.addon.IvyTask;

in.ivyTask = new IvyTask();

in.ivyTask.id = in.task.getId();

if (in.task.#expiryTimestamp is initialized) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(in.task.getExpiryTimestamp());
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	in.ivyTask.expireTimestamp = cal;
} else {
	in.ivyTask.expireTimestamp = null;
}

in.ivyTask.serverId = in.task.applicationRegister.serverId;' #txt
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
Ts0 f75 method save(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
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
        <name>save(RemoteTask)</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f75 429 85 22 22 14 0 #rect
Ts0 f75 @|RichDialogMethodStartIcon #fIcon
Ts0 f79 expr out #txt
Ts0 f79 440 236 440 309 #arcP
Ts0 f81 expr out #txt
Ts0 f81 440 107 440 148 #arcP
Ts0 f77 expr out #txt
Ts0 f77 440 172 440 212 #arcP
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
Ts0 f76 mainOut f79 tail #connect
Ts0 f79 head f78 mainIn #connect
Ts0 f75 mainOut f81 tail #connect
Ts0 f81 head f80 mainIn #connect
Ts0 f80 mainOut f77 tail #connect
Ts0 f77 head f76 mainIn #connect
