[Ivy]
[>Created: Mon Nov 16 10:57:38 ICT 2015]
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
Cs0 @RichDialogProcessEnd f6 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @RichDialogProcessStart f10 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @GridStep f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @RichDialogEnd f15 '' #zField
Cs0 @Alternative f16 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f18 '' #zField
>Proto Cs0 Cs0 CaseNoteProcess #zField
Cs0 f0 guid 14B6C2CB6F2F23A7 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f0 method start(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ICase wfCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.wfCase=param.wfCase;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
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
Cs0 f3 actionCode 'import ch.ivyteam.ivy.workflow.INote;

//CleanUp
out.notes.clear();
out.notesSystem.clear();
out.notesUser.clear();

if(in.wfCase.hasNotes()){	
	//
	java.util.List notesList = in.wfCase.getNotes();
	java.util.List notesListSystem;
	java.util.List notesListUser;
	out.notes.addAll(notesList);
	
	// Split notes into user and system notes
	for (INote note : notesList) {
		if (note.getWritter() != null && note.getWritter().getName() != null && note.getWritter().getName().equals(ivy.wf.getSecurityContext().getSystemUser().getName())) {
			// System note
			notesListSystem.add(note);
		} else {
			notesListUser.add(note);
		}
	}
	
	out.notesSystem.addAll(notesListSystem);
	out.notesUser.addAll(notesListUser);
}

out.currentNotes = out.notes;
out.notesSize = out.notes.size();' #txt
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
Cs0 f3 78 260 36 24 20 -2 #rect
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
        <name>addNote</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 438 54 20 20 13 0 #rect
Cs0 f5 @|RichDialogProcessStartIcon #fIcon
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f6 438 182 20 20 13 0 #rect
Cs0 f6 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivyteam.ivy.workflow.INote;

if(in.newNoteStr.trim().length()>0){
	INote note = in.wfCase.createNote(ivy.session, in.newNoteStr.trim());
	
	//update notes list
	out.notes.add(note);
	out.notesSize = out.notes.size();
	if (note.getWritter() != null && note.getWritter().getName() != null && note.getWritter().getName().equals(ivy.wf.getSecurityContext().getSystemUser().getName())) {
			out.notesSystem.add(note);
	} else {
			out.notesUser.add(note);
	}
	
	//clear note text
	out.newNoteStr = "";
	//reset filter
	out.currentNotes = out.notes;
	out.selectedFilter = "0";
}

' #txt
Cs0 f8 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add
note</name>
        <nameStyle>4,7
4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 430 108 36 24 20 -2 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 448 74 448 108 #arcP
Cs0 f7 expr out #txt
Cs0 f7 448 132 448 182 #arcP
Cs0 f10 guid 14B6C924C57DE78B #txt
Cs0 f10 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>applyFilter</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f10 278 54 20 20 13 0 #rect
Cs0 f10 @|RichDialogProcessStartIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f11 278 182 20 20 13 0 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f13 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData out;
' #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 actionCode 'if(in.selectedFilter=="0"){
	out.currentNotes = in.notes;
}else if(in.selectedFilter=="1"){
	out.currentNotes = in.notesSystem;
}else if(in.selectedFilter=="2"){
	out.currentNotes = in.notesUser;
}' #txt
Cs0 f13 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reassign 
currentNotesList</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 270 108 36 24 20 -2 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 288 74 288 108 #arcP
Cs0 f12 expr out #txt
Cs0 f12 288 132 288 182 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f15 guid 14B76853E9FF242C #txt
Cs0 f15 182 214 20 20 13 0 #rect
Cs0 f15 @|RichDialogEndIcon #fIcon
Cs0 f16 type ch.ivy.addon.portalkit.singleapp.cases.CaseNote.CaseNoteData #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>case 
is NOT null ?</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 82 146 28 28 14 0 #rect
Cs0 f16 @|AlternativeIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 96 74 96 146 #arcP
Cs0 f4 expr in #txt
Cs0 f4 outCond 'in.wfCase is initialized' #txt
Cs0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 96 174 96 260 #arcP
Cs0 f4 0 0.5813953488372093 12 0 #arcLabel
Cs0 f18 expr in #txt
Cs0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 110 160 192 214 #arcP
Cs0 f18 1 192 160 #addKink
Cs0 f18 0 0.8279274174269612 0 -19 #arcLabel
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
Cs0 f5 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f8 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f10 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f13 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f0 mainOut f17 tail #connect
Cs0 f17 head f16 in #connect
Cs0 f16 out f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f16 out f18 tail #connect
Cs0 f18 head f15 mainIn #connect
