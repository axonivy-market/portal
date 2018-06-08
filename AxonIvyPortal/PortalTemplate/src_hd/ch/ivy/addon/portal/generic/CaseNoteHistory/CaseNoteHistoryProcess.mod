[Ivy]
160347990F681701 3.23 #module
>Proto >Proto Collection #zClass
Cs0 CaseNoteHistoryProcess Big #zClass
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
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 CaseNoteHistoryProcess #zField
Cs0 f0 guid 16034799114FDF76 #txt
Cs0 f0 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f0 method start(List<ch.ivy.addon.portalkit.bo.History>,String,ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<List<ch.ivy.addon.portalkit.bo.History> histories,java.lang.String exportedFileName,ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredHistories=param.histories;
out.histories=param.histories;
out.remoteCase=param.remoteCase;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;History&gt;,String,RemoteCase)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -108 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 160347991273BD4F #txt
Cs0 f3 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 75 251 26 26 -15 12 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f4 guid 16034799128E0AF2 #txt
Cs0 f4 203 251 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 101 264 203 264 #arcP
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f6 guid 163D96CAE1553778 #txt
Cs0 f6 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f6 method start(java.util.List<ch.ivy.addon.portalkit.bo.History>,String,ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 disableUIEvents true #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.bo.History> histories,java.lang.String exportedFileName,ch.ivyteam.ivy.workflow.ICase ivyCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredHistories=param.histories;
out.histories=param.histories;
' #txt
Cs0 f6 inActionCode 'out.remoteCase.setName(param.ivyCase.getName());
out.remoteCase.setId(param.ivyCase.getId());
out.remoteCase.setState(param.ivyCase.getState());

if (param.ivyCase.getCreatorUser() != null) {
	out.remoteCase.setCreatorFullName(param.ivyCase.getCreatorUser().getFullName());	
}' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;History&gt;,String,ICase)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 147 26 26 -87 15 #rect
Cs0 f6 @|RichDialogInitStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
Cs0 f7 211 147 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 109 160 211 160 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
