[Ivy]
[>Created: Thu Feb 04 11:31:45 ICT 2016]
14BDEA64F884ED76 3.18 #module
>Proto >Proto Collection #zClass
Ee0 EmailSettingService Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .resExport .resExport #zField
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @StartSub f0 '' #zField
Ee0 @Alternative f6 '' #zField
Ee0 @Alternative f10 '' #zField
Ee0 @GridStep f12 '' #zField
Ee0 @GridStep f13 '' #zField
Ee0 @PushWFArc f18 '' #zField
Ee0 @PushWFArc f19 '' #zField
Ee0 @PushWFArc f24 '' #zField
Ee0 @StartSub f21 '' #zField
Ee0 @GridStep f30 '' #zField
Ee0 @PushWFArc f31 '' #zField
Ee0 @PushWFArc f29 '' #zField
Ee0 @Alternative f28 '' #zField
Ee0 @PushWFArc f33 '' #zField
Ee0 @GridStep f32 '' #zField
Ee0 @GridStep f35 '' #zField
Ee0 @PushWFArc f36 '' #zField
Ee0 @Alternative f44 '' #zField
Ee0 @PushWFArc f46 '' #zField
Ee0 @CallSub f2 '' #zField
Ee0 @PushWFArc f7 '' #zField
Ee0 @PushWFArc f9 '' #zField
Ee0 @CallSub f15 '' #zField
Ee0 @PushWFArc f16 '' #zField
Ee0 @PushWFArc f17 '' #zField
Ee0 @GridStep f74 '' #zField
Ee0 @PushWFArc f34 '' #zField
Ee0 @PushWFArc f4 '' #zField
Ee0 @CallSub f77 '' #zField
Ee0 @GridStep f79 '' #zField
Ee0 @PushWFArc f82 '' #zField
Ee0 @PushWFArc f1 '' #zField
Ee0 @PushWFArc f3 '' #zField
Ee0 @GridStep f11 '' #zField
Ee0 @PushWFArc f20 '' #zField
Ee0 @PushWFArc f5 '' #zField
Ee0 @Alternative f148 '' #zField
Ee0 @EndSub f14 '' #zField
Ee0 @CallSub f144 '' #zField
Ee0 @PushWFArc f40 '' #zField
Ee0 @PushWFArc f107 '' #zField
Ee0 @PushWFArc f22 '' #zField
Ee0 @PushWFArc f8 '' #zField
>Proto Ee0 Ee0 EmailSettingService #zField
Ee0 f0 inParamDecl '<java.lang.String user> param;' #txt
Ee0 f0 inParamTable 'out.username=param.user;
' #txt
Ee0 f0 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> emailSettings> result;
' #txt
Ee0 f0 outParamTable 'result.errors=in.errors;
result.emailSettings=in.emailSettings;
' #txt
Ee0 f0 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f0 callSignature findAllEmailSettings(String) #txt
Ee0 f0 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllEmailSettings
(String)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f0 67 35 26 26 14 0 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f6 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f6 66 162 28 28 15 -19 #rect
Ee0 f6 @|AlternativeIcon #fIcon
Ee0 f10 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>server alive</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f10 66 322 28 28 12 -23 #rect
Ee0 f10 @|AlternativeIcon #fIcon
Ee0 f12 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f12 actionTable 'out=in;
' #txt
Ee0 f12 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteEmailSettingMapper;

in.emailSettings.addAll(RemoteEmailSettingMapper.mapEmailSettings(in.ivyEmailSettings, in.endpoint, in.server));' #txt
Ee0 f12 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add email setting</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f12 62 564 36 24 20 -2 #rect
Ee0 f12 @|StepIcon #fIcon
Ee0 f13 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f13 actionTable 'out=in;
' #txt
Ee0 f13 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);
' #txt
Ee0 f13 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Ee0 f13 182 196 36 24 20 -2 #rect
Ee0 f13 @|StepIcon #fIcon
Ee0 f18 expr out #txt
Ee0 f18 98 576 214 220 #arcP
Ee0 f18 1 256 576 #addKink
Ee0 f18 2 256 256 #addKink
Ee0 f18 1 0.4079416081603623 0 0 #arcLabel
Ee0 f19 expr out #txt
Ee0 f19 200 196 94 176 #arcP
Ee0 f19 1 200 176 #addKink
Ee0 f19 1 0.3141892952261297 0 0 #arcLabel
Ee0 f24 expr in #txt
Ee0 f24 94 336 200 220 #arcP
Ee0 f24 1 200 336 #addKink
Ee0 f24 1 0.20086255747871642 0 0 #arcLabel
Ee0 f21 inParamDecl '<java.lang.String user,List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> emailSettingMaps> param;' #txt
Ee0 f21 inParamTable 'out.emailSettings=param.emailSettingMaps;
out.username=param.user;
' #txt
Ee0 f21 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ee0 f21 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f21 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f21 callSignature saveAllEmailSettings(String,List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting>) #txt
Ee0 f21 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveAllEmailSettings
(List&lt;RemoteEmailSetting&gt;)</name>
        <nameStyle>47,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f21 347 35 26 26 14 0 #rect
Ee0 f21 @|StartSubIcon #fIcon
Ee0 f30 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f30 actionTable 'out=in;
' #txt
Ee0 f30 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteEmailSettingMapper;

in.listIterator = RemoteEmailSettingMapper.mapEmailSetting(in.emailSettings).listIterator();' #txt
Ee0 f30 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Mapper to WS object</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f30 342 108 36 24 20 -2 #rect
Ee0 f30 @|StepIcon #fIcon
Ee0 f31 expr out #txt
Ee0 f31 360 61 360 108 #arcP
Ee0 f29 expr out #txt
Ee0 f29 360 132 360 162 #arcP
Ee0 f28 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next endpoint</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f28 346 162 28 28 14 0 #rect
Ee0 f28 @|AlternativeIcon #fIcon
Ee0 f33 expr in #txt
Ee0 f33 outCond in.listIterator.hasNext() #txt
Ee0 f33 360 190 360 228 #arcP
Ee0 f32 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f32 actionTable 'out=in;
' #txt
Ee0 f32 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;
import ch.ivy.addon.portalkit.mapper.RemoteEmailSettingMapper;
import ch.ivy.ws.addon.IvyEmailSetting;
import java.util.Map;

Map settingMap = in.listIterator.next() as Map;
RemoteEmailSettingMapper.parseData(in, settingMap);
PortalConnectorDetector detector = new PortalConnectorDetector();

for (RemoteEmailSetting item : in.emailSettings) {
	String endpointSS = detector.getPortalConnectorURLOf(item.server) + WebServiceEndPoint.USER_SETTING.toString();
	if(in.endpoint.equalsIgnoreCase(endpointSS)){
		in.server = item.server;
		break;
	}
}' #txt
Ee0 f32 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Setting data</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f32 342 228 36 24 20 -2 #rect
Ee0 f32 @|StepIcon #fIcon
Ee0 f35 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f35 actionTable 'out=in;
' #txt
Ee0 f35 actionCode 'import ch.ivy.ws.addon.WsException;

for (int i = 0; i < in.tempErrors.size(); i++) {
	WsException error = in.tempErrors.get(i) as WsException;
	error.server = in.server.name;
	in.tempErrors.set(i, error);
}

in.errors.addAll(in.tempErrors);' #txt
Ee0 f35 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Utils</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f35 478 228 36 24 20 -2 #rect
Ee0 f35 @|StepIcon #fIcon
Ee0 f36 expr out #txt
Ee0 f36 496 228 374 176 #arcP
Ee0 f36 1 496 176 #addKink
Ee0 f36 1 0.3875554221274583 0 0 #arcLabel
Ee0 f44 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has app ?</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f44 66 434 28 28 14 0 #rect
Ee0 f44 @|AlternativeIcon #fIcon
Ee0 f46 expr in #txt
Ee0 f46 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f46 94 448 200 220 #arcP
Ee0 f46 1 232 448 #addKink
Ee0 f46 2 232 256 #addKink
Ee0 f46 1 0.3019628078261944 0 0 #arcLabel
Ee0 f2 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f2 processCall ServiceIntegrators/UserServiceIntegrator:getEmailSettings(String,String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ee0 f2 doCall true #txt
Ee0 f2 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ee0 f2 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Ee0 f2 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f2 responseMappingAction 'out=in;
out.ivyEmailSettings=result.emailSettings;
out.tempErrors=result.errors;
' #txt
Ee0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserServiceIntegrator</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f2 62 500 36 24 20 -2 #rect
Ee0 f2 @|CallSubIcon #fIcon
Ee0 f7 expr in #txt
Ee0 f7 outCond 'in.apps.size() > 0' #txt
Ee0 f7 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>true</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f7 80 462 80 500 #arcP
Ee0 f9 expr out #txt
Ee0 f9 80 524 80 564 #arcP
Ee0 f15 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f15 processCall ServiceIntegrators/UserServiceIntegrator:setEmailSettings(String,String,ch.ivy.addon.portalkit.persistence.domain.Server,List<ch.ivy.ws.addon.IvyEmailSetting>) #txt
Ee0 f15 doCall true #txt
Ee0 f15 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,List<ch.ivy.ws.addon.IvyEmailSetting> emailSettings> param;
' #txt
Ee0 f15 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.server=in.server;
param.emailSettings=in.ivyEmailSettings;
' #txt
Ee0 f15 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f15 responseMappingAction 'out=in;
out.tempErrors=result.errors;
' #txt
Ee0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setEmailSettings
(String,String,Server,List&lt;IvyEmailSetting&gt;)</name>
        <nameStyle>61,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f15 342 292 36 24 20 -2 #rect
Ee0 f15 @|CallSubIcon #fIcon
Ee0 f16 expr out #txt
Ee0 f16 360 252 360 292 #arcP
Ee0 f17 expr out #txt
Ee0 f17 378 304 496 252 #arcP
Ee0 f17 1 496 304 #addKink
Ee0 f17 0 0.8050192234028938 0 0 #arcLabel
Ee0 f74 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f74 actionTable 'out=in;
' #txt
Ee0 f74 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Ee0 f74 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f74 62 108 36 24 20 -2 #rect
Ee0 f74 @|StepIcon #fIcon
Ee0 f34 expr out #txt
Ee0 f34 80 61 80 108 #arcP
Ee0 f4 expr out #txt
Ee0 f4 80 132 80 162 #arcP
Ee0 f77 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f77 processCall MultiPortal/IsAliveService:getApplicationList(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ee0 f77 doCall true #txt
Ee0 f77 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ee0 f77 requestMappingAction 'param.server=in.server;
' #txt
Ee0 f77 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f77 responseMappingAction 'out=in;
out.applications=result.apps;
out.isServerAlive=result.isServerAlive;
out.tempErrors=result.errors;
' #txt
Ee0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>IsAliveService</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f77 62 276 36 24 20 -2 #rect
Ee0 f77 @|CallSubIcon #fIcon
Ee0 f79 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f79 actionTable 'out=in;
' #txt
Ee0 f79 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Ee0 f79 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f79 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f79 62 212 36 24 20 -2 #rect
Ee0 f79 @|StepIcon #fIcon
Ee0 f82 expr out #txt
Ee0 f82 80 236 80 276 #arcP
Ee0 f1 expr in #txt
Ee0 f1 outCond in.listIterator.hasNext() #txt
Ee0 f1 80 190 80 212 #arcP
Ee0 f3 expr out #txt
Ee0 f3 80 300 80 322 #arcP
Ee0 f11 actionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f11 actionTable 'out=in;
' #txt
Ee0 f11 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.ws.addon.IvyApplication;
import ch.ivy.addon.portalkit.service.UserService;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;


in.apps.clear();

UserService userService = new UserService();
java.util.List<String> apps = userService.findApplicationNamesUserCanWorkOn(in.username, in.server.id);
for(String appName: apps) {
	for (Application app : in.server.getApplications()) {
		if(app.name.equals(appName) && app.isVisible && app.isSupportEmailSettings && !in.apps.contains(appName)){
			in.apps.add(app.name);
		}
	}
}
if (in.apps.isEmpty()) {
	ApplicationService applicationService = new ApplicationService();
	in.apps = applicationService.getApplicationNames(in.applications);
}

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();' #txt
Ee0 f11 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get request data
and alive apps</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f11 62 388 36 24 23 -8 #rect
Ee0 f11 @|StepIcon #fIcon
Ee0 f20 expr in #txt
Ee0 f20 outCond in.isServerAlive #txt
Ee0 f20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f20 80 350 80 388 #arcP
Ee0 f5 expr out #txt
Ee0 f5 80 412 80 434 #arcP
Ee0 f148 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f148 306 594 28 28 14 0 #rect
Ee0 f148 @|AlternativeIcon #fIcon
Ee0 f14 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f14 307 683 26 26 14 0 #rect
Ee0 f14 @|EndSubIcon #fIcon
Ee0 f144 type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
Ee0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ee0 f144 doCall true #txt
Ee0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ee0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f144 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f144 responseMappingAction 'out=in;
' #txt
Ee0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f144 302 636 36 24 20 -2 #rect
Ee0 f144 @|CallSubIcon #fIcon
Ee0 f40 expr out #txt
Ee0 f40 320 660 320 683 #arcP
Ee0 f107 expr in #txt
Ee0 f107 320 622 320 636 #arcP
Ee0 f22 expr in #txt
Ee0 f22 66 176 306 608 #arcP
Ee0 f22 1 24 176 #addKink
Ee0 f22 2 24 608 #addKink
Ee0 f22 1 0.5113782761300745 0 0 #arcLabel
Ee0 f8 expr in #txt
Ee0 f8 346 176 320 594 #arcP
Ee0 f8 1 320 176 #addKink
Ee0 f8 1 0.4562366736062787 0 0 #arcLabel
>Proto Ee0 .type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
>Proto Ee0 .processKind CALLABLE_SUB #txt
>Proto Ee0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findAllEmailSettings</swimlaneLabel>
        <swimlaneLabel>saveAllEmailSettings</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>280</swimlaneSize>
    <swimlaneSize>344</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Ee0 0 0 32 24 18 0 #rect
>Proto Ee0 @|BIcon #fIcon
Ee0 f12 mainOut f18 tail #connect
Ee0 f18 head f13 mainIn #connect
Ee0 f13 mainOut f19 tail #connect
Ee0 f19 head f6 in #connect
Ee0 f24 head f13 mainIn #connect
Ee0 f21 mainOut f31 tail #connect
Ee0 f31 head f30 mainIn #connect
Ee0 f30 mainOut f29 tail #connect
Ee0 f29 head f28 in #connect
Ee0 f28 out f33 tail #connect
Ee0 f33 head f32 mainIn #connect
Ee0 f35 mainOut f36 tail #connect
Ee0 f36 head f28 in #connect
Ee0 f46 head f13 mainIn #connect
Ee0 f44 out f7 tail #connect
Ee0 f7 head f2 mainIn #connect
Ee0 f44 out f46 tail #connect
Ee0 f2 mainOut f9 tail #connect
Ee0 f9 head f12 mainIn #connect
Ee0 f32 mainOut f16 tail #connect
Ee0 f16 head f15 mainIn #connect
Ee0 f15 mainOut f17 tail #connect
Ee0 f17 head f35 mainIn #connect
Ee0 f0 mainOut f34 tail #connect
Ee0 f34 head f74 mainIn #connect
Ee0 f74 mainOut f4 tail #connect
Ee0 f4 head f6 in #connect
Ee0 f79 mainOut f82 tail #connect
Ee0 f82 head f77 mainIn #connect
Ee0 f6 out f1 tail #connect
Ee0 f1 head f79 mainIn #connect
Ee0 f77 mainOut f3 tail #connect
Ee0 f3 head f10 in #connect
Ee0 f10 out f20 tail #connect
Ee0 f20 head f11 mainIn #connect
Ee0 f10 out f24 tail #connect
Ee0 f11 mainOut f5 tail #connect
Ee0 f5 head f44 in #connect
Ee0 f144 mainOut f40 tail #connect
Ee0 f40 head f14 mainIn #connect
Ee0 f148 out f107 tail #connect
Ee0 f107 head f144 mainIn #connect
Ee0 f6 out f22 tail #connect
Ee0 f22 head f148 in #connect
Ee0 f28 out f8 tail #connect
Ee0 f8 head f148 in #connect
