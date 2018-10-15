[Ivy]
16656C2A7F8A4D5C 3.23 #module
>Proto >Proto Collection #zClass
Cs0 CaseNameProcess Big #zClass
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
Cs0 @GridStep f19 '' #zField
Cs0 @CallSub f18 '' #zField
Cs0 @RichDialogMethodStart f16 '' #zField
Cs0 @RichDialogProcessEnd f17 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @PushWFArc f21 '' #zField
>Proto Cs0 Cs0 CaseNameProcess #zField
Cs0 f0 guid 16656C2A8283CF3C #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
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
Cs0 f0 83 51 26 26 6 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f1 83 147 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 77 96 147 #arcP
Cs0 f3 guid 16656C2A845B160B #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData out;
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
Cs0 f3 211 51 26 26 5 16 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f4 guid 16656C2A845B2082 #txt
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 224 77 224 147 #arcP
Cs0 f19 actionDecl 'ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData out;
' #txt
Cs0 f19 actionTable 'out=in;
' #txt
Cs0 f19 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.CaseInforActionService;

CaseInforActionService service = new CaseInforActionService();
in.remoteCase.setName(in.remoteCase.name.trim());
in.ivyCase = service.prepareDataToSaveCase(in.remoteCase);' #txt
Cs0 f19 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 366 112 32 24 20 -2 #rect
Cs0 f19 @|StepIcon #fIcon
Cs0 f18 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f18 processCall MultiPortal/CaseService:saveCase(Long,ch.ivy.ws.addon.IvyCase) #txt
Cs0 f18 doCall true #txt
Cs0 f18 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.IvyCase ivyCase> param;
' #txt
Cs0 f18 requestMappingAction 'param.serverId=in.remoteCase.server.getId();
param.ivyCase=in.ivyCase;
' #txt
Cs0 f18 responseActionDecl 'ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData out;
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
Cs0 f18 364 176 36 24 20 -2 #rect
Cs0 f18 @|CallSubIcon #fIcon
Cs0 f16 guid 16656F54D2CD91ED #txt
Cs0 f16 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
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
Cs0 f16 370 48 24 24 14 0 #rect
Cs0 f16 @|RichDialogMethodStartIcon #fIcon
Cs0 f17 type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
Cs0 f17 371 241 22 22 14 0 #rect
Cs0 f17 @|RichDialogProcessEndIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 382 72 382 112 #arcP
Cs0 f22 expr out #txt
Cs0 f22 382 200 382 241 #arcP
Cs0 f21 expr out #txt
Cs0 f21 382 136 382 176 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.cases.column.CaseName.CaseNameData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f16 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f19 mainOut f21 tail #connect
Cs0 f21 head f18 mainIn #connect
Cs0 f18 mainOut f22 tail #connect
Cs0 f22 head f17 mainIn #connect
