[Ivy]
[>Created: Thu Mar 24 10:53:59 ICT 2016]
15306A682D565719 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemProcess Big #zClass
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
Cs0 @RichDialogMethodStart f11 '' #zField
Cs0 @RichDialogProcessEnd f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
>Proto Cs0 Cs0 CaseItemProcess #zField
Cs0 f0 guid 15306A6832C49147 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f1 53 213 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 64 107 64 213 #arcP
Cs0 f3 guid 15306A6833FC6534 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.CaseItem.CaseItemData out;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 149 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f4 guid 15306A6833F307F8 #txt
Cs0 f4 149 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 160 107 160 213 #arcP
Cs0 f11 guid 1534FF9AB46F1781 #txt
Cs0 f11 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f11 method setExpandedCaseId(java.lang.Long) #txt
Cs0 f11 disableUIEvents false #txt
Cs0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f11 inActionCode 'if (out.expandedCaseId != param.caseId) {
	out.expandedCaseId = param.caseId;
} else {
	out.expandedCaseId = 0;	
}
' #txt
Cs0 f11 outParameterDecl '<> result;
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setExpandedCaseId(Long)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 253 85 22 22 14 0 #rect
Cs0 f11 @|RichDialogMethodStartIcon #fIcon
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f12 253 213 22 22 14 0 #rect
Cs0 f12 @|RichDialogProcessEndIcon #fIcon
Cs0 f13 expr out #txt
Cs0 f13 264 107 264 213 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f11 mainOut f13 tail #connect
Cs0 f13 head f12 mainIn #connect
