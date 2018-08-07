[Ivy]
[>Created: Fri Feb 26 14:48:43 ICT 2016]
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
Pt0 @Alternative f26 '' #zField
Pt0 @CallSub f27 '' #zField
Pt0 @GridStep f28 '' #zField
Pt0 @GridStep f29 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f33 '' #zField
Pt0 @PushWFArc f35 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @PushWFArc f38 '' #zField
Pt0 @PushWFArc f39 '' #zField
Pt0 @PushWFArc f40 '' #zField
Pt0 @PushWFArc f41 '' #zField
Pt0 @PushWFArc f37 '' #zField
Pt0 @StartSub f11 '' #zField
Pt0 @GridStep f94 '' #zField
Pt0 @PushWFArc f43 '' #zField
Pt0 @Alternative f148 '' #zField
Pt0 @EndSub f34 '' #zField
Pt0 @CallSub f144 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f107 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f0 '' #zField
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

in.apps.clear();' #txt
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
Pt0 f5 processCall ServiceIntegrators/ProcessStartServiceIntegrator:findProcessStartsByCriteria(String,ch.ivy.ws.addon.ProcessSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f5 doCall true #txt
Pt0 f5 requestActionDecl '<java.lang.String endpoint,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f5 requestMappingAction 'param.endpoint=in.endpoint;
param.processSearchCriteria=in.processSearchCriteria;
param.processSearchCriteria.involvedApplications=in.apps;
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
Pt0 f5 174 500 36 24 20 -2 #rect
Pt0 f5 @|CallSubIcon #fIcon
Pt0 f12 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f12 actionTable 'out=in;
' #txt
Pt0 f12 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.ws.addon.IvyApplication;

for (IvyApplication app : in.applications) {
	if (app.isActive) {
		in.apps.add(app.name);
	}
}

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.PROCESS_START.toString();' #txt
Pt0 f12 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f12 174 436 36 24 20 -2 #rect
Pt0 f12 @|StepIcon #fIcon
Pt0 f20 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Pt0 f20 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
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
Pt0 f26 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>server alive</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f26 178 370 28 28 12 -23 #rect
Pt0 f26 @|AlternativeIcon #fIcon
Pt0 f27 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f27 processCall MultiPortal/IsAliveService:getApplicationList(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f27 doCall true #txt
Pt0 f27 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f27 requestMappingAction 'param.server=in.server;
' #txt
Pt0 f27 responseActionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f27 responseMappingAction 'out=in;
out.applications=result.apps;
out.isServerAlive=result.isServerAlive;
out.tempErrors=result.errors;
' #txt
Pt0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>IsAliveService</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f27 174 308 36 24 20 -2 #rect
Pt0 f27 @|CallSubIcon #fIcon
Pt0 f28 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f28 actionTable 'out=in;
' #txt
Pt0 f28 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteProcessStartMapper;

if (in.tempErrors.isEmpty()) {
	in.processStarts.addAll(RemoteProcessStartMapper.mapProcessStarts(in.ivyProcessStarts, in.server));
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
Pt0 f28 174 564 36 24 20 -2 #rect
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
Pt0 f30 210 576 384 204 #arcP
Pt0 f30 1 384 576 #addKink
Pt0 f30 1 0.21281805234667672 0 0 #arcLabel
Pt0 f32 expr in #txt
Pt0 f32 outCond in.isServerAlive #txt
Pt0 f32 192 398 192 436 #arcP
Pt0 f33 expr in #txt
Pt0 f33 206 384 384 204 #arcP
Pt0 f33 1 384 384 #addKink
Pt0 f33 0 0.9460848240335697 0 0 #arcLabel
Pt0 f35 expr out #txt
Pt0 f35 192 460 192 500 #arcP
Pt0 f36 expr out #txt
Pt0 f36 192 524 192 564 #arcP
Pt0 f38 expr out #txt
Pt0 f38 192 148 192 178 #arcP
Pt0 f39 expr out #txt
Pt0 f39 192 268 192 308 #arcP
Pt0 f40 expr in #txt
Pt0 f40 outCond in.listIterator.hasNext() #txt
Pt0 f40 192 206 192 244 #arcP
Pt0 f41 expr out #txt
Pt0 f41 192 332 192 370 #arcP
Pt0 f37 expr out #txt
Pt0 f37 192 77 192 124 #arcP
Pt0 f11 inParamDecl '<ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;' #txt
Pt0 f11 inParamTable 'out.processSearchCriteria=param.processSearchCriteria;
' #txt
Pt0 f11 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivyteam.ivy.workflow.IProcessStart> processStarts> result;
' #txt
Pt0 f11 outParamTable 'result.errors=in.errors;
result.processStarts=in.processStarts;
' #txt
Pt0 f11 actionDecl 'ch.ivyteam.wf.processes.ProcessStartData out;
' #txt
Pt0 f11 callSignature findProcessStartsByCriteria(ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Pt0 f11 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findProcessStartsByCriteria(ProcessSearchCriteria)</name>
        <nameStyle>50,5,7
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
Pt0 f94 46 180 36 24 -58 -35 #rect
Pt0 f94 @|StepIcon #fIcon
Pt0 f43 expr in #txt
Pt0 f43 178 192 82 192 #arcP
Pt0 f43 0 0.6122047356561809 0 0 #arcLabel
Pt0 f148 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f148 306 642 28 28 14 0 #rect
Pt0 f148 @|AlternativeIcon #fIcon
Pt0 f34 type ch.ivyteam.wf.processes.ProcessStartData #txt
Pt0 f34 307 731 26 26 14 0 #rect
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
Pt0 f144 302 684 36 24 20 -2 #rect
Pt0 f144 @|CallSubIcon #fIcon
Pt0 f44 expr out #txt
Pt0 f44 320 708 320 731 #arcP
Pt0 f107 expr in #txt
Pt0 f107 320 670 320 684 #arcP
Pt0 f45 expr out #txt
Pt0 f45 64 204 306 656 #arcP
Pt0 f45 1 64 656 #addKink
Pt0 f45 1 0.7686360130156248 0 0 #arcLabel
Pt0 f0 expr out #txt
Pt0 f0 366 192 206 192 #arcP
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
Pt0 f26 out f32 tail #connect
Pt0 f32 head f12 mainIn #connect
Pt0 f26 out f33 tail #connect
Pt0 f33 head f4 mainIn #connect
Pt0 f12 mainOut f35 tail #connect
Pt0 f35 head f5 mainIn #connect
Pt0 f5 mainOut f36 tail #connect
Pt0 f36 head f28 mainIn #connect
Pt0 f11 mainOut f37 tail #connect
Pt0 f37 head f20 mainIn #connect
Pt0 f20 mainOut f38 tail #connect
Pt0 f38 head f21 in #connect
Pt0 f29 mainOut f39 tail #connect
Pt0 f39 head f27 mainIn #connect
Pt0 f21 out f40 tail #connect
Pt0 f40 head f29 mainIn #connect
Pt0 f27 mainOut f41 tail #connect
Pt0 f41 head f26 in #connect
Pt0 f21 out f43 tail #connect
Pt0 f43 head f94 mainIn #connect
Pt0 f144 mainOut f44 tail #connect
Pt0 f44 head f34 mainIn #connect
Pt0 f148 out f107 tail #connect
Pt0 f107 head f144 mainIn #connect
Pt0 f94 mainOut f45 tail #connect
Pt0 f45 head f148 in #connect
Pt0 f4 mainOut f0 tail #connect
Pt0 f0 head f21 in #connect
