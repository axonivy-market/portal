[Ivy]
15493BD80A4C7D12 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemNotesProcess Big #zClass
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
Ts0 @CallSub f64 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @RichDialogProcessEnd f18 '' #zField
Ts0 @GridStep f66 '' #zField
Ts0 @PushWFArc f67 '' #zField
Ts0 @PushWFArc f19 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @Alternative f8 '' #zField
Ts0 @GridStep f12 '' #zField
Ts0 @RichDialogProcessEnd f9 '' #zField
Ts0 @RichDialogMethodStart f10 '' #zField
Ts0 @GridStep f11 '' #zField
Ts0 @CallSub f13 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @PushWFArc f33 '' #zField
Ts0 @PushWFArc f34 '' #zField
>Proto Ts0 Ts0 TaskItemNotesProcess #zField
Ts0 f0 guid 15493BD80C2DD1C3 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
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
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f64 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f64 processCall MultiPortal/CaseService:findNotes(Boolean,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ts0 f64 doCall true #txt
Ts0 f64 requestActionDecl '<java.lang.Boolean excludeSystemNotes,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ts0 f64 requestMappingAction 'param.excludeSystemNotes=in.excludeSystemNotes;
param.server=in.task.applicationRegister.server;
param.caseId=in.task.case.getId();
' #txt
Ts0 f64 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData out;
' #txt
Ts0 f64 responseMappingAction 'out=in;
out.notes=result.notes;
' #txt
Ts0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find notes</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f64 270 140 36 24 20 -2 #rect
Ts0 f64 @|CallSubIcon #fIcon
Ts0 f6 guid 154943DF2362E27D #txt
Ts0 f6 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f6 method initData(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 inActionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;GlobalSettingService globalSettingSerive = new GlobalSettingService();
out.excludeSystemNotes = globalSettingSerive.findHideSystemTasksFromHistorySettingValue();' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(RemoteTask)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 277 85 22 22 14 0 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f18 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f18 277 293 22 22 14 0 #rect
Ts0 f18 @|RichDialogProcessEndIcon #fIcon
Ts0 f66 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData out;
' #txt
Ts0 f66 actionTable 'out=in;
' #txt
Ts0 f66 actionCode 'import ch.ivy.addon.portalkit.comparator.IvyNoteComparator;

in.notes.sort(new IvyNoteComparator());' #txt
Ts0 f66 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort notes</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f66 270 204 36 24 19 -9 #rect
Ts0 f66 @|StepIcon #fIcon
Ts0 f67 expr out #txt
Ts0 f67 288 164 288 204 #arcP
Ts0 f19 expr out #txt
Ts0 f19 288 228 288 293 #arcP
Ts0 f7 expr out #txt
Ts0 f7 288 107 288 140 #arcP
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successful?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f8 506 218 28 28 18 -24 #rect
Ts0 f8 @|AlternativeIcon #fIcon
Ts0 f12 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData out;
' #txt
Ts0 f12 actionTable 'out=in;
' #txt
Ts0 f12 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage("note-content", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}
FacesContext.getCurrentInstance().validationFailed();' #txt
Ts0 f12 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show errors</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f12 646 308 36 24 20 -2 #rect
Ts0 f12 @|StepIcon #fIcon
Ts0 f9 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f9 509 405 22 22 14 0 #rect
Ts0 f9 @|RichDialogProcessEndIcon #fIcon
Ts0 f10 guid 154944516998AD63 #txt
Ts0 f10 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f10 method createNote() #txt
Ts0 f10 disableUIEvents false #txt
Ts0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f10 outParameterDecl '<> result;
' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f10 509 85 22 22 14 0 #rect
Ts0 f10 @|RichDialogMethodStartIcon #fIcon
Ts0 f11 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData out;
' #txt
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 actionCode 'import ch.ivy.addon.portalkit.comparator.IvyNoteComparator;
import ch.ivy.addon.portalkit.bo.RemoteNote;

in.notes.add(in.note);
in.notes.sort(new IvyNoteComparator());
in.noteContent = "";' #txt
Ts0 f11 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear note field
update notes</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f11 502 308 36 24 19 -9 #rect
Ts0 f11 @|StepIcon #fIcon
Ts0 f13 type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
Ts0 f13 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Ts0 f13 doCall true #txt
Ts0 f13 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Ts0 f13 requestMappingAction 'param.server=in.task.#applicationRegister.#server;
param.caseId=in.task.case.getId();
param.username=ivy.session.getSessionUserName();
param.content=in.noteContent;
' #txt
Ts0 f13 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData out;
' #txt
Ts0 f13 responseMappingAction 'out=in;
out.errors=result.errors;
out.note=result.note;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create note service</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f13 502 148 36 24 20 -2 #rect
Ts0 f13 @|CallSubIcon #fIcon
Ts0 f14 expr out #txt
Ts0 f14 520 107 520 148 #arcP
Ts0 f15 expr out #txt
Ts0 f15 520 332 520 405 #arcP
Ts0 f16 expr in #txt
Ts0 f16 outCond in.errors.isEmpty() #txt
Ts0 f16 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f16 520 246 520 308 #arcP
Ts0 f16 0 0.5 16 0 #arcLabel
Ts0 f17 expr out #txt
Ts0 f17 520 172 520 218 #arcP
Ts0 f33 expr in #txt
Ts0 f33 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f33 534 232 664 308 #arcP
Ts0 f33 1 664 232 #addKink
Ts0 f33 1 0.5789473684210527 -14 0 #arcLabel
Ts0 f34 expr out #txt
Ts0 f34 664 332 531 416 #arcP
Ts0 f34 1 664 416 #addKink
Ts0 f34 1 0.2371051590392842 0 0 #arcLabel
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f64 mainOut f67 tail #connect
Ts0 f67 head f66 mainIn #connect
Ts0 f66 mainOut f19 tail #connect
Ts0 f19 head f18 mainIn #connect
Ts0 f6 mainOut f7 tail #connect
Ts0 f7 head f64 mainIn #connect
Ts0 f10 mainOut f14 tail #connect
Ts0 f14 head f13 mainIn #connect
Ts0 f11 mainOut f15 tail #connect
Ts0 f15 head f9 mainIn #connect
Ts0 f8 out f16 tail #connect
Ts0 f16 head f11 mainIn #connect
Ts0 f13 mainOut f17 tail #connect
Ts0 f17 head f8 in #connect
Ts0 f8 out f33 tail #connect
Ts0 f33 head f12 mainIn #connect
Ts0 f12 mainOut f34 tail #connect
Ts0 f34 head f9 mainIn #connect
