[Ivy]
16BCF9DE54BE4C4C 3.26 #module
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
As0 @PushWFArc f4 '' #zField
>Proto As0 As0 AdditionalCaseProcess #zField
As0 f0 guid 16BCF9DE57B756F8 #txt
As0 f0 type ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData #txt
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
As0 f1 type ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData #txt
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f6 guid 16BCFA0EAD385374 #txt
As0 f6 type ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData #txt
As0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
As0 f6 disableUIEvents false #txt
As0 f6 inParameterDecl 'ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData out;
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
As0 f6 83 243 26 26 -41 15 #rect
As0 f6 @|RichDialogMethodStartIcon #fIcon
As0 f3 type ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData #txt
As0 f3 211 243 26 26 0 12 #rect
As0 f3 @|RichDialogProcessEndIcon #fIcon
As0 f4 expr out #txt
As0 f4 109 256 211 256 #arcP
>Proto As0 .type ch.ivy.addon.portal.component.AdditionalCase.AdditionalCaseData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f6 mainOut f4 tail #connect
As0 f4 head f3 mainIn #connect
