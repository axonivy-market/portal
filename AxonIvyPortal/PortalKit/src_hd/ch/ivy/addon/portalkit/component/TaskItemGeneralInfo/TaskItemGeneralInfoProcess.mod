[Ivy]
15493A537A91F8FC 7.5.0 #module
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
Ts0 @PushWFArc f10 '' #zField
Ts0 @CallSub f9 '' #zField
Ts0 @UdEvent f6 '' #zField
Ts0 @PushWFArc f3 '' #zField
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
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 307 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 109 96 307 96 #arcP
Ts0 f5 guid 16826946D7BC78E5 #txt
Ts0 f5 method updateDeadline(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f5 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f5 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f5 outParameterDecl '<> result;' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateDeadline(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 83 275 26 26 -63 15 #rect
Ts0 f5 @|UdMethodIcon #fIcon
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
IUser user = ivy.session.getSessionUser();
String fullName = user.getFullName();
String userName = StringUtils.substring(user.getMemberName(), 1);
in.changeDeadlineNoteContent = service.prepareChangeDeadlineNoteContent(fullName, userName, in.task.expiryTimestamp, in.task.getId());' #txt
Ts0 f13 security system #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare note content</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 160 266 128 44 -57 -8 #rect
Ts0 f13 @|StepIcon #fIcon
Ts0 f14 expr out #txt
Ts0 f14 109 288 160 288 #arcP
Ts0 f15 actionTable 'out=in;
' #txt
Ts0 f15 actionCode 'in.task.getCase().getBusinessCase().createNote(ivy.session, in.changeDeadlineNoteContent);' #txt
Ts0 f15 security system #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 328 266 112 44 -24 -8 #rect
Ts0 f15 @|StepIcon #fIcon
Ts0 f17 expr out #txt
Ts0 f17 288 288 328 288 #arcP
Ts0 f19 499 275 26 26 0 12 #rect
Ts0 f19 @|UdProcessEndIcon #fIcon
Ts0 f20 expr out #txt
Ts0 f20 440 288 499 288 #arcP
Ts0 f11 499 179 26 26 0 12 #rect
Ts0 f11 @|UdProcessEndIcon #fIcon
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import org.primefaces.component.commandlink.CommandLink;

out.selectedCase = out.commandLink.getAttributes().get("caseView") as ICase;
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
Ts0 f7 @|StepIcon #fIcon
Ts0 f12 expr out #txt
Ts0 f12 440 192 499 192 #arcP
Ts0 f10 expr out #txt
Ts0 f10 288 192 328 192 #arcP
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
Ts0 f9 @|CallSubIcon #fIcon
Ts0 f6 guid 16D8B98E15129219 #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

out.commandLink = event.getSource() as CommandLink;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCase</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -73 20 #rect
Ts0 f6 @|UdEventIcon #fIcon
Ts0 f3 109 192 160 192 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemGeneralInfo.TaskItemGeneralInfoData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f7 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f9 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f5 mainOut f14 tail #connect
Ts0 f14 head f13 mainIn #connect
Ts0 f13 mainOut f17 tail #connect
Ts0 f17 head f15 mainIn #connect
Ts0 f15 mainOut f20 tail #connect
Ts0 f20 head f19 mainIn #connect
Ts0 f6 mainOut f3 tail #connect
Ts0 f3 head f7 mainIn #connect
