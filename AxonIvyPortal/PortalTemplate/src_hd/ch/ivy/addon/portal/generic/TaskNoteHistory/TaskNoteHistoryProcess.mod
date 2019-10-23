[Ivy]
160451A075522446 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskNoteHistoryProcess Big #zClass
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
>Proto Ts0 Ts0 TaskNoteHistoryProcess #zField
Ts0 f0 guid 160451A076641B8C #txt
Ts0 f0 method start(java.util.List<ch.ivyteam.ivy.workflow.INote>,String) #txt
Ts0 f0 inParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.INote> notes,String exportedFileName> param;' #txt
Ts0 f0 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredNotes=param.notes;
out.notes=param.notes;
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;INote&gt;,String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -52 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 109 64 211 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskNoteHistory.TaskNoteHistoryData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
