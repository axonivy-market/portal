[Ivy]
16266C89744978E3 3.23 #module
>Proto >Proto Collection #zClass
Ae0 LibraryService Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartSub f0 '' #zField
Ae0 @Alternative f6 '' #zField
Ae0 @Alternative f148 '' #zField
Ae0 @GridStep f74 '' #zField
Ae0 @Alternative f44 '' #zField
Ae0 @CallSub f77 '' #zField
Ae0 @GridStep f13 '' #zField
Ae0 @CallSub f2 '' #zField
Ae0 @EndSub f14 '' #zField
Ae0 @CallSub f144 '' #zField
Ae0 @GridStep f79 '' #zField
Ae0 @GridStep f11 '' #zField
Ae0 @GridStep f12 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f107 '' #zField
Ae0 @PushWFArc f9 '' #zField
Ae0 @PushWFArc f40 '' #zField
Ae0 @PushWFArc f19 '' #zField
Ae0 @PushWFArc f1 '' #zField
Ae0 @PushWFArc f82 '' #zField
Ae0 @PushWFArc f23 '' #zField
Ae0 @PushWFArc f7 '' #zField
Ae0 @PushWFArc f46 '' #zField
Ae0 @PushWFArc f18 '' #zField
Ae0 @PushWFArc f22 '' #zField
Ae0 @PushWFArc f5 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @CallSub f16 '' #zField
Ae0 @StartSub f17 '' #zField
Ae0 @GridStep f21 '' #zField
Ae0 @PushWFArc f24 '' #zField
Ae0 @PushWFArc f8 '' #zField
Ae0 @GridStep f15 '' #zField
Ae0 @PushWFArc f3 '' #zField
Ae0 @PushWFArc f20 '' #zField
>Proto Ae0 Ae0 LibraryService #zField
Ae0 f0 inParamDecl '<java.lang.String username> param;' #txt
Ae0 f0 inParamTable 'out.username=param.username;
' #txt
Ae0 f0 outParamDecl '<List<ch.ivy.addon.portalkit.bo.RemoteLibrary> libraries,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ae0 f0 outParamTable 'result.libraries=in.libraries;
result.errors=in.errors;
' #txt
Ae0 f0 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f0 callSignature getLibraries(String) #txt
Ae0 f0 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibraries(String)</name>
    </language>
</elementInfo>
' #txt
Ae0 f0 116 83 26 26 14 0 #rect
Ae0 f0 @|StartSubIcon #fIcon
Ae0 f6 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 115 211 28 28 15 -19 #rect
Ae0 f6 @|AlternativeIcon #fIcon
Ae0 f148 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f148 483 659 28 28 14 0 #rect
Ae0 f148 @|AlternativeIcon #fIcon
Ae0 f74 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f74 actionTable 'out=in;
' #txt
Ae0 f74 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Ae0 f74 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f74 111 157 36 24 20 -2 #rect
Ae0 f74 @|StepIcon #fIcon
Ae0 f44 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>any apps active?</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f44 115 475 28 28 14 0 #rect
Ae0 f44 @|AlternativeIcon #fIcon
Ae0 f77 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f77 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f77 doCall true #txt
Ae0 f77 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f77 requestMappingAction 'param.server=in.server;
' #txt
Ae0 f77 responseActionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f77 responseMappingAction 'out=in;
out.applications=result.applications;
out.errors=result.errors;
' #txt
Ae0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApplicationService</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f77 111 325 36 24 20 -2 #rect
Ae0 f77 @|CallSubIcon #fIcon
Ae0 f13 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f13 actionTable 'out=in;
' #txt
Ae0 f13 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);
' #txt
Ae0 f13 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Ae0 f13 287 213 36 24 20 -2 #rect
Ae0 f13 @|StepIcon #fIcon
Ae0 f2 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f2 processCall ServiceIntegrators/LibraryServiceIntergrator:getLibraries(List<String>,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f2 doCall true #txt
Ae0 f2 requestActionDecl '<List<java.lang.String> apps,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f2 requestMappingAction 'param.apps=in.apps;
param.endpoint=in.endpoint;
param.server=in.server;
' #txt
Ae0 f2 responseActionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f2 responseMappingAction 'out=in;
out.errors=result.errors;
out.ivyLibraries=result.libraries;
' #txt
Ae0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LibraryServiceIntergrator</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f2 111 549 36 24 20 -2 #rect
Ae0 f2 @|CallSubIcon #fIcon
Ae0 f14 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f14 484 780 26 26 14 0 #rect
Ae0 f14 @|EndSubIcon #fIcon
Ae0 f144 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ae0 f144 doCall true #txt
Ae0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ae0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ae0 f144 responseActionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f144 responseMappingAction 'out=in;
' #txt
Ae0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f144 479 717 36 24 20 -2 #rect
Ae0 f144 @|CallSubIcon #fIcon
Ae0 f79 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f79 actionTable 'out=in;
' #txt
Ae0 f79 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Ae0 f79 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f79 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f79 111 261 36 24 20 -2 #rect
Ae0 f79 @|StepIcon #fIcon
Ae0 f11 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f11 actionTable 'out=in;
' #txt
Ae0 f11 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Application;
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
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.LIBRARY.toString();' #txt
Ae0 f11 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get request data
and alive apps</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f11 111 405 36 24 23 -8 #rect
Ae0 f11 @|StepIcon #fIcon
Ae0 f12 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f12 actionTable 'out=in;
' #txt
Ae0 f12 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;

if (in.errors.isEmpty()) {
	in.libraries.addAll(RemoteLibraryMapper.mapLibraries(in.ivyLibraries));
}' #txt
Ae0 f12 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add library</name>
        <nameStyle>11,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f12 111 613 36 24 20 -2 #rect
Ae0 f12 @|StepIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 129 181 129 211 #arcP
Ae0 f107 expr in #txt
Ae0 f107 497 687 497 717 #arcP
Ae0 f9 expr out #txt
Ae0 f9 129 573 129 613 #arcP
Ae0 f40 expr out #txt
Ae0 f40 497 741 497 780 #arcP
Ae0 f19 expr out #txt
Ae0 f19 287 225 143 225 #arcP
Ae0 f19 0 0.42304813725372814 0 0 #arcLabel
Ae0 f1 expr in #txt
Ae0 f1 outCond in.listIterator.hasNext() #txt
Ae0 f1 129 239 129 261 #arcP
Ae0 f82 expr out #txt
Ae0 f82 129 285 129 325 #arcP
Ae0 f23 expr out #txt
Ae0 f23 129 349 129 405 #arcP
Ae0 f7 expr in #txt
Ae0 f7 outCond 'in.apps.size() > 0' #txt
Ae0 f7 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>true</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f7 129 503 129 549 #arcP
Ae0 f46 expr in #txt
Ae0 f46 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f46 143 489 305 237 #arcP
Ae0 f46 1 256 489 #addKink
Ae0 f46 1 0.007569718733780407 0 0 #arcLabel
Ae0 f18 expr out #txt
Ae0 f18 147 625 305 237 #arcP
Ae0 f18 1 305 625 #addKink
Ae0 f18 0 0.7194531941737214 0 0 #arcLabel
Ae0 f22 expr in #txt
Ae0 f22 115 225 483 673 #arcP
Ae0 f22 1 32 225 #addKink
Ae0 f22 2 32 673 #addKink
Ae0 f22 1 0.5113782761300745 0 0 #arcLabel
Ae0 f5 expr out #txt
Ae0 f5 129 429 129 475 #arcP
Ae0 f10 expr out #txt
Ae0 f10 129 109 129 157 #arcP
Ae0 f10 0 0.9001092177717163 0 0 #arcLabel
Ae0 f16 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f16 processCall ServiceIntegrators/LibraryServiceIntergrator:getLibrary(String,ch.ivy.addon.portalkit.persistence.domain.Server,String,String) #txt
Ae0 f16 doCall true #txt
Ae0 f16 requestActionDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String appName,java.lang.String libraryId> param;
' #txt
Ae0 f16 requestMappingAction 'param.endpoint=in.endpoint;
param.server=in.server;
param.appName=in.appName;
param.libraryId=in.libraryId;
' #txt
Ae0 f16 responseActionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f16 responseMappingAction 'out=in;
out.errors=result.errors;
out.ivyLibrary=result.library;
' #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LibraryServiceIntergrator</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f16 477 217 36 24 20 -2 #rect
Ae0 f16 @|CallSubIcon #fIcon
Ae0 f17 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String appName,java.lang.String libraryId> param;' #txt
Ae0 f17 inParamTable 'out.appName=param.appName;
out.libraryId=param.libraryId;
out.server=param.server;
' #txt
Ae0 f17 outParamDecl '<ch.ivy.addon.portalkit.bo.RemoteLibrary library,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ae0 f17 outParamTable 'result.library=in.library;
result.errors=in.errors;
' #txt
Ae0 f17 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f17 callSignature getLibrary(ch.ivy.addon.portalkit.persistence.domain.Server,String,String) #txt
Ae0 f17 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibrary(Server,String,String)</name>
        <nameStyle>32,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f17 482 87 26 26 14 0 #rect
Ae0 f17 @|StartSubIcon #fIcon
Ae0 f21 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f21 actionTable 'out=in;
' #txt
Ae0 f21 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteCaseMapper;
import ch.ivy.ws.addon.WsException;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;

for(WsException error : in.errors){
	error.server = in.server.name;
}

if (in.errors.size() == 0) {
	in.library = RemoteLibraryMapper.mapLibrary(in.ivyLibrary);
}
' #txt
Ae0 f21 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Ae0 f21 478 267 36 24 20 -2 #rect
Ae0 f21 @|StepIcon #fIcon
Ae0 f24 expr out #txt
Ae0 f24 496 291 497 659 #arcP
Ae0 f8 expr out #txt
Ae0 f8 495 241 496 267 #arcP
Ae0 f15 actionDecl 'ch.ivyteam.wf.processes.LibraryServiceData out;
' #txt
Ae0 f15 actionTable 'out=in;
' #txt
Ae0 f15 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

if(in.server.id != null) {
	PortalConnectorDetector detector = new PortalConnectorDetector();
	in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.LIBRARY.toString();
} else {
	in.endpoint = in.server.getPath() + WebServiceEndPoint.SERVER.toString();
}' #txt
Ae0 f15 type ch.ivyteam.wf.processes.LibraryServiceData #txt
Ae0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get endpoint</name>
    </language>
</elementInfo>
' #txt
Ae0 f15 480 164 32 24 20 -2 #rect
Ae0 f15 @|StepIcon #fIcon
Ae0 f3 expr out #txt
Ae0 f3 495 112 496 164 #arcP
Ae0 f20 expr out #txt
Ae0 f20 496 188 495 217 #arcP
>Proto Ae0 .type ch.ivyteam.wf.processes.LibraryServiceData #txt
>Proto Ae0 .processKind CALLABLE_SUB #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f12 mainOut f18 tail #connect
Ae0 f18 head f13 mainIn #connect
Ae0 f13 mainOut f19 tail #connect
Ae0 f19 head f6 in #connect
Ae0 f46 head f13 mainIn #connect
Ae0 f44 out f7 tail #connect
Ae0 f7 head f2 mainIn #connect
Ae0 f44 out f46 tail #connect
Ae0 f2 mainOut f9 tail #connect
Ae0 f9 head f12 mainIn #connect
Ae0 f74 mainOut f4 tail #connect
Ae0 f4 head f6 in #connect
Ae0 f79 mainOut f82 tail #connect
Ae0 f82 head f77 mainIn #connect
Ae0 f6 out f1 tail #connect
Ae0 f1 head f79 mainIn #connect
Ae0 f11 mainOut f5 tail #connect
Ae0 f5 head f44 in #connect
Ae0 f144 mainOut f40 tail #connect
Ae0 f40 head f14 mainIn #connect
Ae0 f148 out f107 tail #connect
Ae0 f107 head f144 mainIn #connect
Ae0 f6 out f22 tail #connect
Ae0 f22 head f148 in #connect
Ae0 f77 mainOut f23 tail #connect
Ae0 f23 head f11 mainIn #connect
Ae0 f0 mainOut f10 tail #connect
Ae0 f10 head f74 mainIn #connect
Ae0 f21 mainOut f24 tail #connect
Ae0 f24 head f148 in #connect
Ae0 f16 mainOut f8 tail #connect
Ae0 f8 head f21 mainIn #connect
Ae0 f17 mainOut f3 tail #connect
Ae0 f3 head f15 mainIn #connect
Ae0 f15 mainOut f20 tail #connect
Ae0 f20 head f16 mainIn #connect
