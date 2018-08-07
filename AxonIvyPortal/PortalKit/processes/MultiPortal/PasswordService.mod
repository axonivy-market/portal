[Ivy]
[>Created: Wed Aug 23 10:35:52 ICT 2017]
15E0CEDE89CC1D0E 3.20 #module
>Proto >Proto Collection #zClass
Pe0 PasswordService Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartSub f0 '' #zField
Pe0 @EndSub f1 '' #zField
Pe0 @CallSub f144 '' #zField
Pe0 @PushWFArc f2 '' #zField
Pe0 @GridStep f74 '' #zField
Pe0 @GridStep f13 '' #zField
Pe0 @GridStep f21 '' #zField
Pe0 @Alternative f4 '' #zField
Pe0 @GridStep f5 '' #zField
Pe0 @CallSub f44 '' #zField
Pe0 @Alternative f7 '' #zField
Pe0 @PushWFArc f12 '' #zField
Pe0 @PushWFArc f31 '' #zField
Pe0 @PushWFArc f51 '' #zField
Pe0 @PushWFArc f17 '' #zField
Pe0 @PushWFArc f8 '' #zField
Pe0 @PushWFArc f46 '' #zField
Pe0 @PushWFArc f6 '' #zField
Pe0 @PushWFArc f10 '' #zField
Pe0 @PushWFArc f9 '' #zField
Pe0 @Alternative f3 '' #zField
Pe0 @PushWFArc f11 '' #zField
Pe0 @PushWFArc f14 '' #zField
>Proto Pe0 Pe0 PasswordService #zField
Pe0 f0 inParamDecl '<java.lang.String username,java.lang.String newPassword> param;' #txt
Pe0 f0 inParamTable 'out.newPassword=param.newPassword;
out.username=param.username;
' #txt
Pe0 f0 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Pe0 f0 outParamTable 'result.errors=in.errors;
' #txt
Pe0 f0 actionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f0 callSignature changePassword(String,String) #txt
Pe0 f0 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changePassword(String,String)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f0 129 57 30 30 -86 17 #rect
Pe0 f0 @|StartSubIcon #fIcon
Pe0 f1 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f1 465 745 30 30 0 15 #rect
Pe0 f1 @|EndSubIcon #fIcon
Pe0 f144 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Pe0 f144 doCall true #txt
Pe0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Pe0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pe0 f144 responseActionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f144 responseMappingAction 'out=in;
' #txt
Pe0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f144 462 684 36 24 20 -2 #rect
Pe0 f144 @|CallSubIcon #fIcon
Pe0 f2 expr out #txt
Pe0 f2 480 708 480 745 #arcP
Pe0 f74 actionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f74 actionTable 'out=in;
' #txt
Pe0 f74 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Pe0 f74 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f74 126 154 36 24 20 -2 #rect
Pe0 f74 @|StepIcon #fIcon
Pe0 f13 actionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f13 actionTable 'out=in;
' #txt
Pe0 f13 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.apps.clear();



in.apps = SecurityServiceUtils.getAppNames(in.server);

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();
' #txt
Pe0 f13 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Alive Apps
ws endpoint</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f13 124 352 40 32 30 -18 #rect
Pe0 f13 @|StepIcon #fIcon
Pe0 f21 actionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f21 actionTable 'out=in;
' #txt
Pe0 f21 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);

in.apps.clear();
' #txt
Pe0 f21 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>utils</name>
        <nameStyle>5,7
</nameStyle>
        <desc>add server name for exceptions

clear apps list</desc>
    </language>
</elementInfo>
' #txt
Pe0 f21 264 243 36 23 19 14 #rect
Pe0 f21 @|StepIcon #fIcon
Pe0 f4 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f4 130 208 28 28 22 -21 #rect
Pe0 f4 @|AlternativeIcon #fIcon
Pe0 f5 actionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f5 actionTable 'out=in;
' #txt
Pe0 f5 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;

in.server = in.listIterator.next() as Server;' #txt
Pe0 f5 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f5 126 274 36 24 20 -2 #rect
Pe0 f5 @|StepIcon #fIcon
Pe0 f44 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f44 processCall ServiceIntegrators/UserServiceIntegrator:changePassword(String,String,String,ch.ivy.addon.portalkit.persistence.domain.Server,List<String>) #txt
Pe0 f44 doCall true #txt
Pe0 f44 requestActionDecl '<java.lang.String newPassword,java.lang.String endpoint,java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,List<java.lang.String> applicationNames> param;
' #txt
Pe0 f44 requestMappingAction 'param.newPassword=in.newPassword;
param.endpoint=in.endpoint;
param.userName=in.username;
param.server=in.server;
param.applicationNames=in.apps;
' #txt
Pe0 f44 responseActionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f44 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Pe0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changePassword</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f44 126 530 36 24 33 7 #rect
Pe0 f44 @|CallSubIcon #fIcon
Pe0 f7 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>any apps active?</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f7 130 464 28 28 9 -35 #rect
Pe0 f7 @|AlternativeIcon #fIcon
Pe0 f12 expr in #txt
Pe0 f12 158 478 282 265 #arcP
Pe0 f12 1 282 478 #addKink
Pe0 f12 1 0.5115178601655154 0 0 #arcLabel
Pe0 f31 expr in #txt
Pe0 f31 outCond in.listIterator.hasNext() #txt
Pe0 f31 144 236 144 274 #arcP
Pe0 f51 expr out #txt
Pe0 f51 144 178 144 208 #arcP
Pe0 f17 expr out #txt
Pe0 f17 282 242 158 222 #arcP
Pe0 f17 1 282 222 #addKink
Pe0 f17 1 0.31135433052386374 0 0 #arcLabel
Pe0 f8 expr out #txt
Pe0 f8 144 384 144 464 #arcP
Pe0 f46 expr in #txt
Pe0 f46 outCond in.apps.size()>0 #txt
Pe0 f46 144 492 144 530 #arcP
Pe0 f6 expr out #txt
Pe0 f6 144 298 144 352 #arcP
Pe0 f10 expr out #txt
Pe0 f10 144 87 144 154 #arcP
Pe0 f9 expr out #txt
Pe0 f9 162 542 282 265 #arcP
Pe0 f9 1 282 542 #addKink
Pe0 f9 1 0.2477583649213471 0 0 #arcLabel
Pe0 f3 type ch.ivyteam.wf.processes.PasswordServiceData #txt
Pe0 f3 464 624 32 32 0 16 #rect
Pe0 f3 @|AlternativeIcon #fIcon
Pe0 f11 expr in #txt
Pe0 f11 480 656 480 684 #arcP
Pe0 f14 expr in #txt
Pe0 f14 130 222 464 640 #arcP
Pe0 f14 1 40 222 #addKink
Pe0 f14 2 40 640 #addKink
Pe0 f14 1 0.8179642692553053 0 0 #arcLabel
>Proto Pe0 .type ch.ivyteam.wf.processes.PasswordServiceData #txt
>Proto Pe0 .processKind CALLABLE_SUB #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f144 mainOut f2 tail #connect
Pe0 f2 head f1 mainIn #connect
Pe0 f12 head f21 mainIn #connect
Pe0 f21 mainOut f17 tail #connect
Pe0 f17 head f4 in #connect
Pe0 f13 mainOut f8 tail #connect
Pe0 f8 head f7 in #connect
Pe0 f7 out f46 tail #connect
Pe0 f46 head f44 mainIn #connect
Pe0 f7 out f12 tail #connect
Pe0 f74 mainOut f51 tail #connect
Pe0 f51 head f4 in #connect
Pe0 f31 head f5 mainIn #connect
Pe0 f4 out f31 tail #connect
Pe0 f5 mainOut f6 tail #connect
Pe0 f6 head f13 mainIn #connect
Pe0 f0 mainOut f10 tail #connect
Pe0 f10 head f74 mainIn #connect
Pe0 f44 mainOut f9 tail #connect
Pe0 f9 head f21 mainIn #connect
Pe0 f3 out f11 tail #connect
Pe0 f11 head f144 mainIn #connect
Pe0 f4 out f14 tail #connect
Pe0 f14 head f3 in #connect
