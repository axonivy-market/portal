[Ivy]
160347990F681701 9.4.6 #module
>Proto >Proto Collection #zClass
Cs0 CaseNoteHistoryProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .colors .colors #zField
Cs0 @TextInP color color #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f0 '' #zField
Cs0 @PushWFArc f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
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
    </language>
</elementInfo>
' #txt
Cs0 f6 83 83 26 26 -87 15 #rect
Cs0 f7 339 83 26 26 0 12 #rect
Cs0 f0 actionTable 'out=in;
' #txt
Cs0 f0 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
GlobalSettingService service = new GlobalSettingService();
in.showRelatedCaseInfo = in.ivyCase.isBusinessCase() && !service.findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_RELATED_CASE_INFO_FROM_HISTORY);' #txt
Cs0 f0 security system #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Read Portal settings</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 160 74 128 44 -56 -8 #rect
Cs0 f1 expr out #txt
Cs0 f1 109 96 160 96 #arcP
Cs0 f2 color default #txt
Cs0 f2 288 96 339 96 #arcP
>Proto Cs0 .colors 'default=;
' #txt
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseNoteHistory.CaseNoteHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f6 mainOut f1 tail #connect
Cs0 f1 head f0 mainIn #connect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f7 mainIn #connect
