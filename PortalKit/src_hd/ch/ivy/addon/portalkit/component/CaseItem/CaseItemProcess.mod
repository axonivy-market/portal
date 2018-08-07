[Ivy]
[>Created: Tue Jul 12 16:37:02 ICT 2016]
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
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @CallSub f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @PushWFArc f8 '' #zField
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
Cs0 f6 guid 155DD626CD99604B #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f6 method updateCaseDescription(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateCaseDescription(RemoteCase)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 469 85 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f7 469 277 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.CaseItem.CaseItemData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import ch.ivy.ws.addon.IvyCase;

in.ivyCase = new IvyCase();

in.ivyCase.id = in.remoteCase.id;

if(in.remoteCase.#description is initialized) {
  in.ivyCase.description = in.remoteCase.description;
}

in.ivyCase.serverId = in.remoteCase.server.id;' #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f9 462 148 36 24 20 -2 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 480 107 480 148 #arcP
Cs0 f14 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f14 processCall MultiPortal/CaseService:saveCase(Long,ch.ivy.ws.addon.IvyCase) #txt
Cs0 f14 doCall true #txt
Cs0 f14 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.IvyCase ivyCase> param;
' #txt
Cs0 f14 requestMappingAction 'param.serverId=in.ivyCase.serverId;
param.ivyCase=in.ivyCase;
' #txt
Cs0 f14 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItem.CaseItemData out;
' #txt
Cs0 f14 responseMappingAction 'out=in;
' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 462 212 36 24 20 -2 #rect
Cs0 f14 @|CallSubIcon #fIcon
Cs0 f15 expr out #txt
Cs0 f15 480 172 480 212 #arcP
Cs0 f8 expr out #txt
Cs0 f8 480 236 480 277 #arcP
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
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f14 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
