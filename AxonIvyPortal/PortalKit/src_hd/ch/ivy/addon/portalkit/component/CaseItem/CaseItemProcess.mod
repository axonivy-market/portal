[Ivy]
15306A682D565719 3.23 #module
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
Cs0 @CallSub f31 '' #zField
Cs0 @RichDialogMethodStart f36 '' #zField
Cs0 @RichDialogProcessEnd f32 '' #zField
Cs0 @PushWFArc f61 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f3 '' #zField
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
Cs0 f31 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f31 processCall MultiPortal/SideStepProcessStart:findSideStepsByCriteria(ch.ivy.addon.portalkit.persistence.domain.Server,ch.ivy.ws.addon.SideStepSearchCriteria) #txt
Cs0 f31 doCall true #txt
Cs0 f31 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,ch.ivy.ws.addon.SideStepSearchCriteria sideStepSearchCriteria> param;
' #txt
Cs0 f31 requestMappingAction 'param.server=in.remoteCase.server;
param.sideStepSearchCriteria=in.sideStepSearchCriteria;
' #txt
Cs0 f31 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItem.CaseItemData out;
' #txt
Cs0 f31 responseMappingAction 'out=in;
out.sideSteps=result.sideSteps;
' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sideStepProcessService</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f31 176 213 36 24 20 -2 #rect
Cs0 f31 @|CallSubIcon #fIcon
Cs0 f36 guid 166C3BA9E79196D0 #txt
Cs0 f36 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f36 method collectSideSteps(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f36 disableUIEvents false #txt
Cs0 f36 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f36 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Cs0 f36 outParameterDecl '<> result;
' #txt
Cs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Cs0 f36 181 84 26 26 7 10 #rect
Cs0 f36 @|RichDialogMethodStartIcon #fIcon
Cs0 f32 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f32 181 276 26 26 0 12 #rect
Cs0 f32 @|RichDialogProcessEndIcon #fIcon
Cs0 f61 expr out #txt
Cs0 f61 194 237 194 276 #arcP
Cs0 f39 actionDecl 'ch.ivy.addon.portalkit.component.CaseItem.CaseItemData out;
' #txt
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.ws.addon.SideStepSearchCriteria;
in.sideStepSearchCriteria.setCaseId(in.remoteCase.getId());
in.sideStepSearchCriteria.setInvolvedUsername(ivy.session.getSessionUserName());
' #txt
Cs0 f39 type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init criteria</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f39 138 144 112 44 -28 -8 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 194 110 194 144 #arcP
Cs0 f4 0 0.4903579494503649 0 0 #arcLabel
Cs0 f3 expr out #txt
Cs0 f3 194 188 194 213 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f31 mainOut f61 tail #connect
Cs0 f61 head f32 mainIn #connect
Cs0 f36 mainOut f4 tail #connect
Cs0 f4 head f39 mainIn #connect
Cs0 f39 mainOut f3 tail #connect
Cs0 f3 head f31 mainIn #connect
