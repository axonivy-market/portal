[Ivy]
160347990F681701 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseNoteHistoryProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 CaseNoteHistoryProcess #zField
Cs0 f6 guid 163D96CAE1553778 #txt
Cs0 f6 method start(java.util.List<ch.ivy.addon.portalkit.bo.History>,String,ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 inParameterDecl '<java.util.List<ch.ivy.addon.portalkit.bo.History> histories,String exportedFileName,ch.ivyteam.ivy.workflow.ICase ivyCase> param;' #txt
Cs0 f6 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredHistories=param.histories;
out.histories=param.histories;
out.ivyCase=param.ivyCase;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;History&gt;,String,ICase)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 83 26 26 -87 15 #rect
Cs0 f6 @|UdInitIcon #fIcon
Cs0 f7 211 83 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 109 96 211 96 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
