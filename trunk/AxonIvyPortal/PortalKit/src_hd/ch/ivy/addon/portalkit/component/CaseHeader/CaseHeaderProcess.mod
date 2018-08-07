[Ivy]
[>Created: Tue Jan 03 13:36:13 ICT 2017]
156455501228CE44 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseHeaderProcess Big #zClass
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
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @CallSub f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @RichDialogMethodStart f16 '' #zField
Cs0 @RichDialogProcessEnd f17 '' #zField
Cs0 @CallSub f18 '' #zField
Cs0 @GridStep f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
>Proto Cs0 Cs0 CaseHeaderProcess #zField
Cs0 f0 guid 15306A6832C49147 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
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
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f1 53 213 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 64 107 64 213 #arcP
Cs0 f3 guid 15306A6833FC6534 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData out;
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
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f4 guid 15306A6833F307F8 #txt
Cs0 f4 149 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 160 107 160 213 #arcP
Cs0 f6 guid 155DD626CD99604B #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
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
Cs0 f6 277 77 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f7 277 269 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.CaseInforActionService;

CaseInforActionService service = new CaseInforActionService();
in.remoteCase.setDescription(in.remoteCase.description.trim());
in.ivyCase = service.prepareDataToSaveCase(in.remoteCase);' #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 270 140 36 24 20 -2 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 288 99 288 140 #arcP
Cs0 f14 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f14 processCall MultiPortal/CaseService:saveCase(Long,ch.ivy.ws.addon.IvyCase) #txt
Cs0 f14 doCall true #txt
Cs0 f14 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.IvyCase ivyCase> param;
' #txt
Cs0 f14 requestMappingAction 'param.serverId=in.remoteCase.server.getId();
param.ivyCase=in.ivyCase;
' #txt
Cs0 f14 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData out;
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
Cs0 f14 270 204 36 24 20 -2 #rect
Cs0 f14 @|CallSubIcon #fIcon
Cs0 f15 expr out #txt
Cs0 f15 288 164 288 204 #arcP
Cs0 f8 expr out #txt
Cs0 f8 288 228 288 269 #arcP
Cs0 f16 guid 156258B195EAA636 #txt
Cs0 f16 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f16 method updateName(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f16 disableUIEvents false #txt
Cs0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase case> param = methodEvent.getInputArguments();
' #txt
Cs0 f16 inParameterMapAction 'out.remoteCase=param.case;
' #txt
Cs0 f16 outParameterDecl '<> result;
' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateName(RemoteCase)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 573 77 22 22 14 0 #rect
Cs0 f16 @|RichDialogMethodStartIcon #fIcon
Cs0 f17 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f17 573 269 22 22 14 0 #rect
Cs0 f17 @|RichDialogProcessEndIcon #fIcon
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f18 processCall MultiPortal/CaseService:saveCase(Long,ch.ivy.ws.addon.IvyCase) #txt
Cs0 f18 doCall true #txt
Cs0 f18 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.IvyCase ivyCase> param;
' #txt
Cs0 f18 requestMappingAction 'param.serverId=in.remoteCase.server.getId();
param.ivyCase=in.ivyCase;
' #txt
Cs0 f18 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData out;
' #txt
Cs0 f18 responseMappingAction 'out=in;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 566 204 36 24 20 -2 #rect
Cs0 f18 @|CallSubIcon #fIcon
Cs0 f19 actionDecl 'ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData out;
' #txt
Cs0 f19 actionTable 'out=in;
' #txt
Cs0 f19 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.CaseInforActionService;

CaseInforActionService service = new CaseInforActionService();
in.remoteCase.setName(in.remoteCase.name.trim());
in.ivyCase = service.prepareDataToSaveCase(in.remoteCase);' #txt
Cs0 f19 type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 568 140 32 24 20 -2 #rect
Cs0 f19 @|StepIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 584 99 584 140 #arcP
Cs0 f21 expr out #txt
Cs0 f21 584 164 584 204 #arcP
Cs0 f22 expr out #txt
Cs0 f22 584 228 584 269 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseHeader.CaseHeaderData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f14 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f16 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f19 mainOut f21 tail #connect
Cs0 f21 head f18 mainIn #connect
Cs0 f18 mainOut f22 tail #connect
Cs0 f22 head f17 mainIn #connect
