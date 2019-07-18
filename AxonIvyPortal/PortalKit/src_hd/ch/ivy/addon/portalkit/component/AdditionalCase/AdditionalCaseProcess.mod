[Ivy]
16BFA27E496A71E9 3.26 #module
>Proto >Proto Collection #zClass
As0 AdditionalCaseProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
As0 @TextInP .rdData2UIAction .rdData2UIAction #zField
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @RichDialogInitStart f0 '' #zField
As0 @RichDialogProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @RichDialogMethodStart f6 '' #zField
As0 @RichDialogProcessEnd f3 '' #zField
As0 @GridStep f5 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @PushWFArc f4 '' #zField
>Proto As0 As0 AdditionalCaseProcess #zField
As0 f0 guid 16BCF9DE57B756F8 #txt
As0 f0 type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
As0 f0 method start() #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
As0 f0 outParameterDecl '<> result;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f1 type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f6 guid 16BCFA0EAD385374 #txt
As0 f6 type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
As0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
As0 f6 disableUIEvents false #txt
As0 f6 inParameterDecl 'ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData out;
' #txt
As0 f6 inParameterMapAction 'out.selectedCase=param.caseData;
' #txt
As0 f6 outParameterDecl '<> result;
' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData</name>
    </language>
</elementInfo>
' #txt
As0 f6 83 147 26 26 -41 15 #rect
As0 f6 @|RichDialogMethodStartIcon #fIcon
As0 f3 type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
As0 f3 339 147 26 26 0 12 #rect
As0 f3 @|RichDialogProcessEndIcon #fIcon
As0 f5 actionDecl 'ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData out;
' #txt
As0 f5 actionTable 'out=in;
' #txt
As0 f5 actionCode 'in.customFields = in.selectedCase.customFields().all();

if (in.customFields.size() > 0) {
	ivy.log.info("Custom Fields {0}", in.customFields.get(in.customFields.size() - 1).toString());
}
' #txt
As0 f5 type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
As0 f5 168 138 112 44 0 -8 #rect
As0 f5 @|StepIcon #fIcon
As0 f7 expr out #txt
As0 f7 109 160 168 160 #arcP
As0 f4 expr out #txt
As0 f4 280 160 339 160 #arcP
>Proto As0 .type ch.ivy.addon.portalkit.component.AdditionalCase.AdditionalCaseData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f6 mainOut f7 tail #connect
As0 f7 head f5 mainIn #connect
As0 f5 mainOut f4 tail #connect
As0 f4 head f3 mainIn #connect
