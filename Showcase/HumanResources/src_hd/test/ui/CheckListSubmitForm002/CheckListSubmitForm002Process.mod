[Ivy]
[>Created: Thu Jul 24 14:43:37 CEST 2014]
1476865F10D64A1A 3.17 #module
>Proto >Proto Collection #zClass
Cs0 CheckListSubmitForm002Process Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
>Proto Cs0 Cs0 CheckListSubmitForm002Process #zField
Cs0 f0 guid 1476865F12BE670B #txt
Cs0 f0 type test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<java.lang.String Mitarbeiter,java.lang.String Manager,java.lang.Number Personalnummer,java.lang.Number Checklist_id,ch.ivyteam.ivy.scripting.objects.Date EntryDate> result;
' #txt
Cs0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.Manager=in.Manager;
result.Personalnummer=in.Personalnummer;
result.Checklist_id=in.Checklist_id;
result.EntryDate=in.EntryDate;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 51 51 26 26 -16 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f0 -1|-1|-9671572 #nodeStyle
Cs0 f1 type test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data #txt
Cs0 f1 243 51 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f1 -1|-1|-9671572 #nodeStyle
Cs0 f2 expr out #txt
Cs0 f2 77 64 243 64 #arcP
Cs0 f3 guid 1476865F1312EAD0 #txt
Cs0 f3 type test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data #txt
Cs0 f3 actionDecl 'test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data out;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 51 147 26 26 -15 12 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f3 -1|-1|-9671572 #nodeStyle
Cs0 f4 type test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data #txt
Cs0 f4 guid 1476865F132193D0 #txt
Cs0 f4 243 147 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f4 -1|-1|-9671572 #nodeStyle
Cs0 f5 expr out #txt
Cs0 f5 77 160 243 160 #arcP
>Proto Cs0 .type test.ui.CheckListSubmitForm002.CheckListSubmitForm002Data #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
