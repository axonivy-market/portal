[Ivy]
[>Created: Mon May 09 10:53:11 ICT 2016]
14C4FF3FCD291EF5 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalCasesProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 PortalCasesProcess #zField
Ps0 f0 guid 15436B1E00BBDE99 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalCases.PortalCasesData #txt
Ps0 f0 method useView(ch.ivy.addon.portal.generic.view.CaseView) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portal.generic.view.CaseView view> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.view=param.view;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(CaseView)</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 85 53 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalCases.PortalCasesData #txt
Ps0 f1 373 53 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 107 64 373 64 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCases.PortalCasesData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f1 mainIn #connect
