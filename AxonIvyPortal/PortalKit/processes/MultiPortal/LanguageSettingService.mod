[Ivy]
[>Created: Mon May 30 10:55:46 ICT 2016]
14BE80F25BC9033C 3.18 #module
>Proto >Proto Collection #zClass
Ee0 LanguageSettingService Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .resExport .resExport #zField
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @PushWFArc f36 '' #zField
Ee0 @PushWFArc f19 '' #zField
Ee0 @PushWFArc f18 '' #zField
Ee0 @GridStep f35 '' #zField
Ee0 @StartSub f0 '' #zField
Ee0 @GridStep f12 '' #zField
Ee0 @Alternative f6 '' #zField
Ee0 @StartSub f21 '' #zField
Ee0 @GridStep f11 '' #zField
Ee0 @Alternative f28 '' #zField
Ee0 @GridStep f13 '' #zField
Ee0 @GridStep f46 '' #zField
Ee0 @Alternative f51 '' #zField
Ee0 @PushWFArc f52 '' #zField
Ee0 @PushWFArc f53 '' #zField
Ee0 @GridStep f54 '' #zField
Ee0 @PushWFArc f55 '' #zField
Ee0 @CallSub f32 '' #zField
Ee0 @PushWFArc f33 '' #zField
Ee0 @PushWFArc f16 '' #zField
Ee0 @CallSub f9 '' #zField
Ee0 @PushWFArc f17 '' #zField
Ee0 @PushWFArc f34 '' #zField
Ee0 @GridStep f74 '' #zField
Ee0 @PushWFArc f37 '' #zField
Ee0 @PushWFArc f1 '' #zField
Ee0 @GridStep f3 '' #zField
Ee0 @PushWFArc f4 '' #zField
Ee0 @PushWFArc f29 '' #zField
Ee0 @CallSub f77 '' #zField
Ee0 @GridStep f79 '' #zField
Ee0 @PushWFArc f82 '' #zField
Ee0 @PushWFArc f30 '' #zField
Ee0 @GridStep f5 '' #zField
Ee0 @PushWFArc f31 '' #zField
Ee0 @Alternative f148 '' #zField
Ee0 @EndSub f14 '' #zField
Ee0 @CallSub f144 '' #zField
Ee0 @PushWFArc f40 '' #zField
Ee0 @PushWFArc f107 '' #zField
Ee0 @PushWFArc f15 '' #zField
Ee0 @PushWFArc f20 '' #zField
Ee0 @PushWFArc f8 '' #zField
Ee0 @PushWFArc f2 '' #zField
>Proto Ee0 Ee0 LanguageSettingService #zField
Ee0 f36 expr out #txt
Ee0 f36 526 168 398 168 #arcP
Ee0 f19 expr out #txt
Ee0 f19 200 196 94 176 #arcP
Ee0 f19 1 200 176 #addKink
Ee0 f19 1 0.3141892952261297 0 0 #arcLabel
Ee0 f18 expr out #txt
Ee0 f18 98 632 218 208 #arcP
Ee0 f18 1 248 632 #addKink
Ee0 f18 2 248 208 #addKink
Ee0 f18 0 0.9749172189158009 0 0 #arcLabel
Ee0 f35 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f35 actionTable 'out=in;
' #txt
Ee0 f35 actionCode 'import ch.ivy.ws.addon.WsException;

for (int i = 0; i < in.tempErrors.size(); i++) {
	WsException error = in.tempErrors.get(i) as WsException;
	error.server = in.server.name;
	in.tempErrors.set(i, error);
}

in.errors.addAll(in.tempErrors);
' #txt
Ee0 f35 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Utils</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f35 526 156 36 24 7 14 #rect
Ee0 f35 @|StepIcon #fIcon
Ee0 f0 inParamDecl '<java.util.List<java.lang.String> appNames,java.lang.String user> param;' #txt
Ee0 f0 inParamTable 'out.username=param.user;
' #txt
Ee0 f0 outParamDecl '<List<java.lang.String> apps,List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> languageSettings> result;
' #txt
Ee0 f0 outParamTable 'result.apps=in.apps;
result.errors=in.errors;
result.languageSettings=in.remoteLanguageSettings;
' #txt
Ee0 f0 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f0 callSignature findAllLanguageSettings(List<String>,String) #txt
Ee0 f0 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllLanguageSettings
(apps,user)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f0 67 35 26 26 16 -6 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f12 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f12 actionTable 'out=in;
' #txt
Ee0 f12 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteLanguageSettingMapper;

in.remoteLanguageSettings.addAll(RemoteLanguageSettingMapper.mapToRemoteLanguageSettings(in.ivyLanguagesEachSever, in.server));' #txt
Ee0 f12 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add languages</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f12 62 620 36 24 20 -2 #rect
Ee0 f12 @|StepIcon #fIcon
Ee0 f6 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f6 66 162 28 28 14 0 #rect
Ee0 f6 @|AlternativeIcon #fIcon
Ee0 f21 inParamDecl '<List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> remoteLanguageSettings,java.lang.String user> param;' #txt
Ee0 f21 inParamTable 'out.remoteLanguageSettings=param.remoteLanguageSettings;
out.username=param.user;
' #txt
Ee0 f21 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ee0 f21 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f21 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f21 callSignature saveAllEmailSettings(List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting>,String) #txt
Ee0 f21 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveLanguageSettings
(apps,user)</name>
        <nameStyle>32,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f21 371 35 26 26 16 -6 #rect
Ee0 f21 @|StartSubIcon #fIcon
Ee0 f11 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
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
	for (Application application : in.server.getApplications()) {
			if(appName.equals(application.name) && application.isVisible && !in.apps.contains(appName)){
					in.apps.add(appName);
			}
	}
}
if (in.apps.isEmpty()) {
	ApplicationService applicationService = new ApplicationService();
	in.apps = applicationService.getApplicationNames(in.applications);
}	

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();' #txt
Ee0 f11 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Alive Apps
ws endpoint</name>
        <nameStyle>15,7
11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f11 62 388 36 24 22 -15 #rect
Ee0 f11 @|StepIcon #fIcon
Ee0 f28 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next endpoint</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f28 370 154 28 28 14 0 #rect
Ee0 f28 @|AlternativeIcon #fIcon
Ee0 f13 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
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

in.apps.clear();' #txt
Ee0 f13 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
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
Ee0 f46 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f46 actionTable 'out=in;
' #txt
Ee0 f46 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();

' #txt
Ee0 f46 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get ws endpoint</name>
        <nameStyle>4,7
11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f46 366 292 36 24 26 -10 #rect
Ee0 f46 @|StepIcon #fIcon
Ee0 f51 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has app?</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f51 66 482 28 28 14 0 #rect
Ee0 f51 @|AlternativeIcon #fIcon
Ee0 f52 expr out #txt
Ee0 f52 80 412 80 482 #arcP
Ee0 f53 expr in #txt
Ee0 f53 94 496 200 220 #arcP
Ee0 f53 1 200 496 #addKink
Ee0 f53 1 0.2043554384245989 0 0 #arcLabel
Ee0 f54 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f54 actionTable 'out=in;
' #txt
Ee0 f54 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteLanguageSettingMapper;

in.ivyLanguagesEachSever.addAll(RemoteLanguageSettingMapper.mapToIvyLanguageSettings(in.remoteLanguageSettings));' #txt
Ee0 f54 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get WS settings</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f54 366 348 36 24 20 -2 #rect
Ee0 f54 @|StepIcon #fIcon
Ee0 f55 expr out #txt
Ee0 f55 384 316 384 348 #arcP
Ee0 f32 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f32 processCall ServiceIntegrators/UserServiceIntegrator:getLanguageSettings(String,String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ee0 f32 doCall true #txt
Ee0 f32 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ee0 f32 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Ee0 f32 responseActionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f32 responseMappingAction 'out=in;
out.ivyLanguagesEachSever=result.languageSettings;
out.tempErrors=result.errors;
' #txt
Ee0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get language settings</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f32 62 548 36 24 20 -2 #rect
Ee0 f32 @|CallSubIcon #fIcon
Ee0 f33 expr in #txt
Ee0 f33 outCond 'in.apps.size() > 0' #txt
Ee0 f33 80 510 80 548 #arcP
Ee0 f16 expr out #txt
Ee0 f16 80 572 80 620 #arcP
Ee0 f9 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f9 processCall ServiceIntegrators/UserServiceIntegrator:setLanguageSettings(String,String,ch.ivy.addon.portalkit.persistence.domain.Server,List<ch.ivy.ws.addon.IvyLanguageSetting>) #txt
Ee0 f9 doCall true #txt
Ee0 f9 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,List<ch.ivy.ws.addon.IvyLanguageSetting> languageSettings> param;
' #txt
Ee0 f9 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.server=in.server;
param.languageSettings=in.ivyLanguagesEachSever;
' #txt
Ee0 f9 responseActionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f9 responseMappingAction 'out=in;
out.tempErrors=result.errors;
' #txt
Ee0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set language settings</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f9 366 404 36 24 20 -2 #rect
Ee0 f9 @|CallSubIcon #fIcon
Ee0 f17 expr out #txt
Ee0 f17 384 372 384 404 #arcP
Ee0 f34 expr out #txt
Ee0 f34 402 416 562 168 #arcP
Ee0 f34 1 607 416 #addKink
Ee0 f34 2 607 168 #addKink
Ee0 f34 1 0.21337599658305714 0 0 #arcLabel
Ee0 f74 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f74 actionTable 'out=in;
' #txt
Ee0 f74 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Ee0 f74 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f74 62 92 36 24 20 -2 #rect
Ee0 f74 @|StepIcon #fIcon
Ee0 f37 expr out #txt
Ee0 f37 80 61 80 92 #arcP
Ee0 f1 expr out #txt
Ee0 f1 80 116 80 162 #arcP
Ee0 f3 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f3 actionTable 'out=in;
' #txt
Ee0 f3 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Ee0 f3 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f3 366 100 36 24 20 -2 #rect
Ee0 f3 @|StepIcon #fIcon
Ee0 f4 expr out #txt
Ee0 f4 384 61 384 100 #arcP
Ee0 f29 expr out #txt
Ee0 f29 384 124 384 154 #arcP
Ee0 f77 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f77 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ee0 f77 doCall true #txt
Ee0 f77 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ee0 f77 requestMappingAction 'param.server=in.server;
' #txt
Ee0 f77 responseActionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f77 responseMappingAction 'out=in;
out.applications=result.applications;
out.tempErrors=result.errors;
' #txt
Ee0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get applications 
of server</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f77 62 292 36 24 20 -2 #rect
Ee0 f77 @|CallSubIcon #fIcon
Ee0 f79 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f79 actionTable 'out=in;
' #txt
Ee0 f79 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Ee0 f79 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f79 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f79 62 228 36 24 20 -2 #rect
Ee0 f79 @|StepIcon #fIcon
Ee0 f82 expr out #txt
Ee0 f82 80 252 80 292 #arcP
Ee0 f30 expr in #txt
Ee0 f30 outCond in.listIterator.hasNext() #txt
Ee0 f30 80 190 80 228 #arcP
Ee0 f5 actionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
' #txt
Ee0 f5 actionTable 'out=in;
' #txt
Ee0 f5 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Ee0 f5 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f5 366 220 36 24 20 -2 #rect
Ee0 f5 @|StepIcon #fIcon
Ee0 f31 expr in #txt
Ee0 f31 outCond in.listIterator.hasNext() #txt
Ee0 f31 384 182 384 220 #arcP
Ee0 f148 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f148 322 666 28 28 14 0 #rect
Ee0 f148 @|AlternativeIcon #fIcon
Ee0 f14 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f14 323 755 26 26 14 0 #rect
Ee0 f14 @|EndSubIcon #fIcon
Ee0 f144 type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
Ee0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ee0 f144 doCall true #txt
Ee0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ee0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f144 responseActionDecl 'ch.ivyteam.wf.processes.LanguageSettingServiceData out;
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
Ee0 f144 318 708 36 24 20 -2 #rect
Ee0 f144 @|CallSubIcon #fIcon
Ee0 f40 expr out #txt
Ee0 f40 336 732 336 755 #arcP
Ee0 f107 expr in #txt
Ee0 f107 336 694 336 708 #arcP
Ee0 f15 expr in #txt
Ee0 f15 66 176 322 680 #arcP
Ee0 f15 1 42 176 #addKink
Ee0 f15 2 42 680 #addKink
Ee0 f15 1 0.5113782761300745 0 0 #arcLabel
Ee0 f20 expr in #txt
Ee0 f20 370 168 336 666 #arcP
Ee0 f20 1 336 168 #addKink
Ee0 f20 1 0.5155836814853244 0 0 #arcLabel
Ee0 f8 expr out #txt
Ee0 f8 384 244 384 292 #arcP
Ee0 f2 expr out #txt
Ee0 f2 80 316 80 388 #arcP
>Proto Ee0 .type ch.ivyteam.wf.processes.LanguageSettingServiceData #txt
>Proto Ee0 .processKind CALLABLE_SUB #txt
>Proto Ee0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findAllLanguageSettings</swimlaneLabel>
        <swimlaneLabel>saveLanguageSettings</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>288</swimlaneSize>
    <swimlaneSize>400</swimlaneSize>
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
Ee0 f35 mainOut f36 tail #connect
Ee0 f36 head f28 in #connect
Ee0 f11 mainOut f52 tail #connect
Ee0 f52 head f51 in #connect
Ee0 f53 head f13 mainIn #connect
Ee0 f46 mainOut f55 tail #connect
Ee0 f55 head f54 mainIn #connect
Ee0 f51 out f33 tail #connect
Ee0 f33 head f32 mainIn #connect
Ee0 f51 out f53 tail #connect
Ee0 f32 mainOut f16 tail #connect
Ee0 f16 head f12 mainIn #connect
Ee0 f54 mainOut f17 tail #connect
Ee0 f17 head f9 mainIn #connect
Ee0 f9 mainOut f34 tail #connect
Ee0 f34 head f35 mainIn #connect
Ee0 f0 mainOut f37 tail #connect
Ee0 f37 head f74 mainIn #connect
Ee0 f74 mainOut f1 tail #connect
Ee0 f1 head f6 in #connect
Ee0 f21 mainOut f4 tail #connect
Ee0 f4 head f3 mainIn #connect
Ee0 f3 mainOut f29 tail #connect
Ee0 f29 head f28 in #connect
Ee0 f79 mainOut f82 tail #connect
Ee0 f82 head f77 mainIn #connect
Ee0 f6 out f30 tail #connect
Ee0 f30 head f79 mainIn #connect
Ee0 f28 out f31 tail #connect
Ee0 f31 head f5 mainIn #connect
Ee0 f144 mainOut f40 tail #connect
Ee0 f40 head f14 mainIn #connect
Ee0 f148 out f107 tail #connect
Ee0 f107 head f144 mainIn #connect
Ee0 f6 out f15 tail #connect
Ee0 f15 head f148 in #connect
Ee0 f28 out f20 tail #connect
Ee0 f20 head f148 in #connect
Ee0 f5 mainOut f8 tail #connect
Ee0 f8 head f46 mainIn #connect
Ee0 f77 mainOut f2 tail #connect
Ee0 f2 head f11 mainIn #connect
