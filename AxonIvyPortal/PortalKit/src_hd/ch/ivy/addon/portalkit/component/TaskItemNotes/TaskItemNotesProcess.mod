[Ivy]
15493BD80A4C7D12 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemNotesProcess Big #zClass
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
Ts0 @UdMethod f3 '' #zField
Ts0 @GridStep f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f20 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @UdProcessEnd f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @PushWFArc f8 '' #zField
>Proto Ts0 Ts0 TaskItemNotesProcess #zField
Ts0 f0 guid 1682705E65C6C417 #txt
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
Ts0 f3 guid 168270608CC35DAF #txt
Ts0 f3 method initData(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f3 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 179 26 26 -40 15 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import java.util.ArrayList;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

ICase iCase = in.task.getCase().getBusinessCase();
GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingService.findHideSystemTasksFromHistorySettingValue();
out.notes = CaseUtils.findNotes(iCase, excludeTechnicalHistory);
' #txt
Ts0 f4 security system #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find notes</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 168 170 112 44 -29 -8 #rect
Ts0 f4 @|StepIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 192 168 192 #arcP
Ts0 f20 actionTable 'out=in;
' #txt
Ts0 f20 actionCode 'import ch.ivy.addon.portalkit.comparator.NoteComparator;

in.notes.sort(new NoteComparator());' #txt
Ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sort notes</name>
    </language>
</elementInfo>
' #txt
Ts0 f20 312 170 112 44 -28 -8 #rect
Ts0 f20 @|StepIcon #fIcon
Ts0 f21 expr out #txt
Ts0 f21 280 192 312 192 #arcP
Ts0 f22 467 179 26 26 0 12 #rect
Ts0 f22 @|UdProcessEndIcon #fIcon
Ts0 f23 expr out #txt
Ts0 f23 424 192 467 192 #arcP
Ts0 f6 guid 16827078DBAC9718 #txt
Ts0 f6 method createNote() #txt
Ts0 f6 inParameterDecl '<> param;' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote()</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 275 26 26 -34 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'in.task.getCase().getBusinessCase().createNote(ivy.session, in.noteContent);
in.noteContent = "";' #txt
Ts0 f10 security system #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 168 266 112 44 -32 -8 #rect
Ts0 f10 @|StepIcon #fIcon
Ts0 f14 expr out #txt
Ts0 f14 109 288 168 288 #arcP
Ts0 f8 expr out #txt
Ts0 f8 224 266 224 214 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f4 mainOut f21 tail #connect
Ts0 f21 head f20 mainIn #connect
Ts0 f20 mainOut f23 tail #connect
Ts0 f23 head f22 mainIn #connect
Ts0 f6 mainOut f14 tail #connect
Ts0 f14 head f10 mainIn #connect
Ts0 f10 mainOut f8 tail #connect
Ts0 f8 head f4 mainIn #connect
