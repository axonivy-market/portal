[Ivy]
15493BD80A4C7D12 9.3.0 #module
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
Ts0 @GridStep f20 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @UdProcessEnd f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdMethod f7 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @PushWFArc f9 '' #zField
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
Ts0 f1 307 83 26 26 0 12 #rect
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
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import ch.ivyteam.ivy.environment.Ivy;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;

ICase iCase = in.task.getCase();
boolean showSystemNotes = out.showSystemNotes && out.showSystemNotesChkbox;
out.notes = CaseUtils.findNotes(iCase, !showSystemNotes);
' #txt
Ts0 f4 security system #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load notes</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 352 170 112 44 -31 -8 #rect
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
Ts0 f20 552 170 112 44 -28 -8 #rect
Ts0 f21 expr out #txt
Ts0 f21 464 192 552 192 #arcP
Ts0 f22 723 179 26 26 0 12 #rect
Ts0 f23 expr out #txt
Ts0 f23 664 192 723 192 #arcP
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
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'in.task.getCase().createNote(ivy.session, in.noteContent);
in.noteContent = "";' #txt
Ts0 f10 security system #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 160 264 144 48 -32 -8 #rect
Ts0 f14 expr out #txt
Ts0 f14 109 288 160 288 #arcP
Ts0 f8 expr out #txt
Ts0 f8 232 264 368 214 #arcP
Ts0 f7 guid 177282755F4CB896 #txt
Ts0 f7 method showHideSystemNotes() #txt
Ts0 f7 inParameterDecl '<> param;' #txt
Ts0 f7 outParameterDecl '<> result;' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showHideSystemNotes()</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 83 371 26 26 -25 15 #rect
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;

GlobalSettingService globalSettingService = new GlobalSettingService();
out.showSystemNotesChkbox = !globalSettingService.findHideSystemNotesFromHistorySettingValue();
out.showSystemNotes = PermissionUtils.isSessionUserHasAdminRole();' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init note''s configuration</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 160 170 144 44 -63 -8 #rect
Ts0 f15 expr out #txt
Ts0 f15 109 192 160 192 #arcP
Ts0 f5 304 192 352 192 #arcP
Ts0 f9 109 384 408 214 #arcP
Ts0 f9 1 408 384 #addKink
Ts0 f9 0 0.7924194694090007 0 0 #arcLabel
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemNotes.TaskItemNotesData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f4 mainOut f21 tail #connect
Ts0 f21 head f20 mainIn #connect
Ts0 f20 mainOut f23 tail #connect
Ts0 f23 head f22 mainIn #connect
Ts0 f6 mainOut f14 tail #connect
Ts0 f14 head f10 mainIn #connect
Ts0 f10 mainOut f8 tail #connect
Ts0 f8 head f4 mainIn #connect
Ts0 f3 mainOut f15 tail #connect
Ts0 f15 head f13 mainIn #connect
Ts0 f13 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f7 mainOut f9 tail #connect
Ts0 f9 head f4 mainIn #connect
