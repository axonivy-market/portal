[Ivy]
[>Created: Wed Mar 09 16:36:56 ICT 2016]
14BCA4067235E461 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseNoteProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessStart f5 '' #zField
Cs0 @RichDialogProcessStart f10 '' #zField
Cs0 @GridStep f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @GridStep f4 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
>Proto Cs0 Cs0 CaseNoteProcess #zField
Cs0 f0 guid 14B6C2CB6F2F23A7 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f0 method start(java.lang.Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 86 54 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f1 86 334 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.comparator.INoteComparator;

in.notes.clear();
in.notes.addAll(in.currentCase.getNotes());
in.notes = in.notes.sort(new INoteComparator());' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get notes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 78 260 36 24 15 7 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 284 96 334 #arcP
Cs0 f5 guid 14B6C43122BED623 #txt
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f5 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateNotes</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 262 54 20 20 13 0 #rect
Cs0 f5 @|RichDialogProcessStartIcon #fIcon
Cs0 f10 guid 14B6C924C57DE78B #txt
Cs0 f10 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f10 422 54 20 20 13 0 #rect
Cs0 f10 @|RichDialogProcessStartIcon #fIcon
Cs0 f13 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 actionCode 'import ch.ivyteam.ivy.workflow.INote;

INote note = in.currentCase.createNote(ivy.session, in.noteContent.trim());
in.noteContent = "";' #txt
Cs0 f13 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create note
reset note field</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 414 156 36 24 23 -10 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 432 74 432 156 #arcP
Cs0 f4 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f4 actionTable 'out=in;
' #txt
Cs0 f4 actionCode 'in.currentCase = ivy.wf.findCase(in.caseId);' #txt
Cs0 f4 security system #txt
Cs0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCaseById</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 78 148 36 24 20 -2 #rect
Cs0 f4 @|StepIcon #fIcon
Cs0 f15 expr out #txt
Cs0 f15 96 74 96 148 #arcP
Cs0 f16 expr out #txt
Cs0 f16 96 172 96 260 #arcP
Cs0 f6 expr out #txt
Cs0 f6 272 74 114 272 #arcP
Cs0 f6 1 272 272 #addKink
Cs0 f6 1 0.08601638861575965 0 0 #arcLabel
Cs0 f7 expr out #txt
Cs0 f7 432 180 114 272 #arcP
Cs0 f7 1 432 272 #addKink
Cs0 f7 1 0.32169602531567654 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>start</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>224</swimlaneSize>
    <swimlaneSize>328</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f10 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f0 mainOut f15 tail #connect
Cs0 f15 head f4 mainIn #connect
Cs0 f4 mainOut f16 tail #connect
Cs0 f16 head f3 mainIn #connect
Cs0 f5 mainOut f6 tail #connect
Cs0 f6 head f3 mainIn #connect
Cs0 f13 mainOut f7 tail #connect
Cs0 f7 head f3 mainIn #connect
