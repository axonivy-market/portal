[Ivy]
[>Created: Thu Jan 05 16:21:45 ICT 2017]
1473A12DE00609FB 3.18 #module
>Proto >Proto Collection #zClass
Pt0 ProcessStart Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @GridStep f4 '' #zField
Pt0 @CallSub f5 '' #zField
Pt0 @GridStep f12 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @Alternative f21 '' #zField
Pt0 @GridStep f28 '' #zField
Pt0 @GridStep f29 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @PushWFArc f38 '' #zField
Pt0 @PushWFArc f37 '' #zField
Pt0 @StartSub f11 '' #zField
Pt0 @GridStep f94 '' #zField
Pt0 @PushWFArc f43 '' #zField
Pt0 @EndSub f34 '' #zField
Pt0 @CallSub f144 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f0 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @CallSub f3 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f6 '' #zField
Pt0 @Alternative f1 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @PushWFArc f7 '' #zField
Pt0 @GridStep f13 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f16 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f10 '' #zField
>Proto Pt0 Pt0 ProcessStart #zField
Pt0 f4 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f4 actionTable 'out=in;
' #txt
Pt0 f4 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);

if (!in.apps.isEmpty()) {
	in.processSearchCriteria.involvedApplications = in.apps.clear();
}' #txt
Pt0 f4 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f4 366 180 36 24 20 -2 #rect
Pt0 f4 @|StepIcon #fIcon
Pt0 f5 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f5 processCall ServiceIntegrators/ProcessStartServiceIntegrator:findProcessStartsByCriteria(Boolean,String,String,ch.ivy.ws.addon.ProcessSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f5 doCall true #txt
Pt0 f5 requestActionDecl '<java.lang.Boolean isUrlBuiltFromSystemProperties,java.lang.String endpoint,java.lang.String language,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f5 requestMappingAction 'param.isUrlBuiltFromSystemProperties=in.isMultiServer;
param.endpoint=in.endpoint;
param.language=in.language;
param.processSearchCriteria=in.processSearchCriteria;
param.server=in.server;
' #txt
Pt0 f5 responseActionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
out.ivyProcessStarts=result.processStarts;
out.tempErrors=result.errors;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessStartServiceIntegrator</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 174 628 36 24 20 -2 #rect
Pt0 f5 @|CallSubIcon #fIcon
Pt0 f12 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f12 actionTable 'out=in;
' #txt
Pt0 f12 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.ws.addon.IvyApplication;
import ch.ivy.addon.portalkit.service.UserService;

in.apps.clear();

UserService userService = new UserService();
java.util.List<String> apps = userService.findApplicationNamesUserCanWorkOn(in.processSearchCriteria.involvedUsername, in.server.id);
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

in.processSearchCriteria.involvedApplications = in.apps;' #txt
Pt0 f12 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Alive Apps</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f12 174 444 36 24 20 -2 #rect
Pt0 f12 @|StepIcon #fIcon
Pt0 f20 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;
import java.util.Arrays;
import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();

List<Server> servers;
Long serverId = in.processSearchCriteria.#serverId;
if (#serverId is initialized && serverId != 0) {
	servers = Arrays.asList(serverService.findById(serverId));
} else {
	servers = serverService.findActiveServers();
}

in.listIterator = servers.listIterator();' #txt
Pt0 f20 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get servers</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 174 124 36 24 20 -2 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f21 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f21 178 178 28 28 14 0 #rect
Pt0 f21 @|AlternativeIcon #fIcon
Pt0 f28 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f28 actionTable 'out=in;
' #txt
Pt0 f28 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.mapper.RemoteProcessStartMapper;

if (in.tempErrors.isEmpty()) {
	String host = "";
	if (in.isMultiServer) {
		UrlDetector detector = new UrlDetector();
		host = detector.getHost(in.server.path);
	}

	in.processStarts.addAll(RemoteProcessStartMapper.mapProcessStarts(in.ivyProcessStarts, in.server.getId(), host));
}' #txt
Pt0 f28 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add process
starts</name>
        <nameStyle>12,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f28 174 692 36 24 20 -2 #rect
Pt0 f28 @|StepIcon #fIcon
Pt0 f29 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f29 actionTable 'out=in;
' #txt
Pt0 f29 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;

in.server = in.listIterator.next() as Server;' #txt
Pt0 f29 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f29 174 244 36 24 20 -2 #rect
Pt0 f29 @|StepIcon #fIcon
Pt0 f30 expr out #txt
Pt0 f30 210 704 384 204 #arcP
Pt0 f30 1 384 704 #addKink
Pt0 f30 1 0.21281805234667672 0 0 #arcLabel
Pt0 f36 expr out #txt
Pt0 f36 192 652 192 692 #arcP
Pt0 f38 expr out #txt
Pt0 f38 192 148 192 178 #arcP
Pt0 f37 expr out #txt
Pt0 f37 192 77 192 124 #arcP
Pt0 f11 inParamDecl '<java.lang.String language,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;' #txt
Pt0 f11 inParamTable 'out.language=param.language;
out.processSearchCriteria=param.processSearchCriteria;
' #txt
Pt0 f11 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivyteam.ivy.workflow.IProcessStart> processStarts> result;
' #txt
Pt0 f11 outParamTable 'result.errors=in.errors;
result.processStarts=in.processStarts;
' #txt
Pt0 f11 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f11 callSignature findProcessStartsByCriteria(String,ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Pt0 f11 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findProcessStartsByCriteria(String,ProcessSearchCriteria)</name>
        <nameStyle>57,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 179 51 26 26 14 0 #rect
Pt0 f11 @|StartSubIcon #fIcon
Pt0 f94 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f94 actionTable 'out=in;
' #txt
Pt0 f94 actionCode 'import ch.ivy.addon.portalkit.comparator.ProcessNameComparator;

if (in.processStarts.size() > 1){
	in.processStarts.sort(new ProcessNameComparator());
}
' #txt
Pt0 f94 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort processes by name</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f94 46 340 36 24 -58 -35 #rect
Pt0 f94 @|StepIcon #fIcon
Pt0 f43 expr in #txt
Pt0 f43 178 192 64 340 #arcP
Pt0 f43 1 64 192 #addKink
Pt0 f43 1 0.06731597908022724 0 0 #arcLabel
Pt0 f34 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f34 179 803 26 26 14 0 #rect
Pt0 f34 @|EndSubIcon #fIcon
Pt0 f144 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Pt0 f144 doCall true #txt
Pt0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Pt0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f144 responseActionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f144 responseMappingAction 'out=in;
' #txt
Pt0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f144 174 756 36 24 20 -2 #rect
Pt0 f144 @|CallSubIcon #fIcon
Pt0 f44 expr out #txt
Pt0 f44 192 780 192 803 #arcP
Pt0 f0 expr out #txt
Pt0 f0 366 192 206 192 #arcP
Pt0 f2 expr out #txt
Pt0 f2 64 364 174 768 #arcP
Pt0 f2 1 64 768 #addKink
Pt0 f2 1 0.7686360130156248 0 0 #arcLabel
Pt0 f3 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f3 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f3 doCall true #txt
Pt0 f3 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f3 requestMappingAction 'param.server=in.server;
' #txt
Pt0 f3 responseActionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
out.applications=result.applications;
out.tempErrors=result.errors;
' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get applications of server</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 174 372 36 24 20 -2 #rect
Pt0 f3 @|CallSubIcon #fIcon
Pt0 f8 expr in #txt
Pt0 f8 outCond in.listIterator.hasNext() #txt
Pt0 f8 192 206 192 244 #arcP
Pt0 f6 expr out #txt
Pt0 f6 192 396 192 444 #arcP
Pt0 f1 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no selected apps?</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f1 178 306 28 28 14 0 #rect
Pt0 f1 @|AlternativeIcon #fIcon
Pt0 f9 expr out #txt
Pt0 f9 192 268 192 306 #arcP
Pt0 f7 expr in #txt
Pt0 f7 outCond in.processSearchCriteria.involvedApplications.isEmpty() #txt
Pt0 f7 192 334 192 372 #arcP
Pt0 f13 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f13 actionTable 'out=in;
' #txt
Pt0 f13 actionCode 'import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.PROCESS_START.toString();' #txt
Pt0 f13 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get ws endpoint</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 174 500 36 24 20 -2 #rect
Pt0 f13 @|StepIcon #fIcon
Pt0 f14 expr out #txt
Pt0 f14 192 468 192 500 #arcP
Pt0 f16 expr in #txt
Pt0 f16 178 320 174 512 #arcP
Pt0 f16 1 144 320 #addKink
Pt0 f16 2 144 512 #addKink
Pt0 f16 1 0.49 0 0 #arcLabel
Pt0 f17 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService service = new ServerService();
in.isMultiServer = service.isMultiServers();' #txt
Pt0 f17 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is multi server</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 174 564 36 24 20 -2 #rect
Pt0 f17 @|StepIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 192 524 192 564 #arcP
Pt0 f10 expr out #txt
Pt0 f10 192 588 192 628 #arcP
>Proto Pt0 .type ch.ivyteam.wf.processes.ProcessStartData #txt
>Proto Pt0 .processKind CALLABLE_SUB #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>1024</swimlaneSize>
    <swimlaneColor>-6697729</swimlaneColor>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f28 mainOut f30 tail #connect
Pt0 f30 head f4 mainIn #connect
Pt0 f5 mainOut f36 tail #connect
Pt0 f36 head f28 mainIn #connect
Pt0 f11 mainOut f37 tail #connect
Pt0 f37 head f20 mainIn #connect
Pt0 f20 mainOut f38 tail #connect
Pt0 f38 head f21 in #connect
Pt0 f43 head f94 mainIn #connect
Pt0 f144 mainOut f44 tail #connect
Pt0 f44 head f34 mainIn #connect
Pt0 f4 mainOut f0 tail #connect
Pt0 f0 head f21 in #connect
Pt0 f94 mainOut f2 tail #connect
Pt0 f2 head f144 mainIn #connect
Pt0 f21 out f8 tail #connect
Pt0 f8 head f29 mainIn #connect
Pt0 f21 out f43 tail #connect
Pt0 f3 mainOut f6 tail #connect
Pt0 f6 head f12 mainIn #connect
Pt0 f29 mainOut f9 tail #connect
Pt0 f9 head f1 in #connect
Pt0 f1 out f7 tail #connect
Pt0 f7 head f3 mainIn #connect
Pt0 f12 mainOut f14 tail #connect
Pt0 f14 head f13 mainIn #connect
Pt0 f1 out f16 tail #connect
Pt0 f16 head f13 mainIn #connect
Pt0 f13 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f17 mainOut f10 tail #connect
Pt0 f10 head f5 mainIn #connect
