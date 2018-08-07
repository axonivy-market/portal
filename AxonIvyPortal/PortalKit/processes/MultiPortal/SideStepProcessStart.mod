[Ivy]
[>Created: Fri Jun 23 11:28:05 ICT 2017]
15C6291386FB461E 3.20 #module
>Proto >Proto Collection #zClass
Pt0 SideStepProcessStart Big #zClass
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
Pt0 @GridStep f28 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @StartSub f11 '' #zField
Pt0 @GridStep f94 '' #zField
Pt0 @EndSub f34 '' #zField
Pt0 @CallSub f144 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @GridStep f13 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f10 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f9 '' #zField
>Proto Pt0 Pt0 SideStepProcessStart #zField
Pt0 f4 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
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
' #txt
Pt0 f4 type ch.ivyteam.wf.processes.SideStepProcessData #txt
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
Pt0 f4 174 396 36 24 20 -2 #rect
Pt0 f4 @|StepIcon #fIcon
Pt0 f5 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f5 processCall ServiceIntegrators/SideStepProcessServiceIntegrator:findSideStepsByCriteria(Boolean,String,ch.ivy.ws.addon.SideStepSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f5 doCall true #txt
Pt0 f5 requestActionDecl '<java.lang.Boolean isUrlBuiltFromSystemProperties,java.lang.String endpoint,ch.ivy.ws.addon.SideStepSearchCriteria sideStepSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f5 requestMappingAction 'param.isUrlBuiltFromSystemProperties=in.isMultiServer;
param.endpoint=in.endpoint;
param.sideStepSearchCriteria=in.sideStepSearchCriteria;
param.server=in.server;
' #txt
Pt0 f5 responseActionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
out.ivySideSteps=result.sideSteps;
out.tempErrors=result.errors;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SideStepServiceIntegrator</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 174 244 36 24 20 -2 #rect
Pt0 f5 @|CallSubIcon #fIcon
Pt0 f28 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f28 actionTable 'out=in;
' #txt
Pt0 f28 actionCode 'import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.mapper.RemoteSideStepMapper;

if (in.tempErrors.isEmpty()) {
	String host = RequestUriFactory.createServerUri(ivy.request as IHttpRequest).toString();
	if (in.isMultiServer) {
		UrlDetector detector = new UrlDetector();
		host = detector.getHost(in.server.path);
	}

	in.sideSteps.addAll(RemoteSideStepMapper.mapSideSteps(in.ivySideSteps, host));
}' #txt
Pt0 f28 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add side steps</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f28 174 308 36 24 20 -2 #rect
Pt0 f28 @|StepIcon #fIcon
Pt0 f30 expr out #txt
Pt0 f30 192 332 192 396 #arcP
Pt0 f30 0 0.41603713082097676 0 0 #arcLabel
Pt0 f36 expr out #txt
Pt0 f36 192 268 192 308 #arcP
Pt0 f11 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,ch.ivy.ws.addon.SideStepSearchCriteria sideStepSearchCriteria> param;' #txt
Pt0 f11 inParamTable 'out.server=param.server;
out.sideStepSearchCriteria=param.sideStepSearchCriteria;
' #txt
Pt0 f11 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteSideStep> sideSteps> result;
' #txt
Pt0 f11 outParamTable 'result.errors=in.errors;
result.sideSteps=in.sideSteps;
' #txt
Pt0 f11 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f11 callSignature findSideStepsByCriteria(ch.ivy.addon.portalkit.persistence.domain.Server,ch.ivy.ws.addon.SideStepSearchCriteria) #txt
Pt0 f11 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSideStepsByCriteria(Server,SideStepSearchCriteria)</name>
        <nameStyle>54,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 179 51 26 26 14 0 #rect
Pt0 f11 @|StartSubIcon #fIcon
Pt0 f94 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f94 actionTable 'out=in;
' #txt
Pt0 f94 actionCode 'import ch.ivy.addon.portalkit.comparator.SideStepNameComparator;

if (in.sideSteps.size() > 1){
	in.sideSteps.sort(new SideStepNameComparator());
}
' #txt
Pt0 f94 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort side steps by name</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f94 174 484 36 24 -58 -35 #rect
Pt0 f94 @|StepIcon #fIcon
Pt0 f34 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f34 179 587 26 26 14 0 #rect
Pt0 f34 @|EndSubIcon #fIcon
Pt0 f144 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Pt0 f144 doCall true #txt
Pt0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Pt0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f144 responseActionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
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
Pt0 f144 174 540 36 24 20 -2 #rect
Pt0 f144 @|CallSubIcon #fIcon
Pt0 f44 expr out #txt
Pt0 f44 192 564 192 587 #arcP
Pt0 f2 expr out #txt
Pt0 f2 192 508 192 540 #arcP
Pt0 f2 0 0.9947812634514803 0 0 #arcLabel
Pt0 f13 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f13 actionTable 'out=in;
' #txt
Pt0 f13 actionCode 'import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.SIDE_STEP.toString();' #txt
Pt0 f13 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get ws endpoint</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 174 116 36 24 20 -2 #rect
Pt0 f13 @|StepIcon #fIcon
Pt0 f17 actionDecl 'ch.ivyteam.wf.processes.SideStepProcessData out;
' #txt
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService service = new ServerService();
in.isMultiServer = service.isMultiServers();' #txt
Pt0 f17 type ch.ivyteam.wf.processes.SideStepProcessData #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is multi server</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 174 180 36 24 20 -2 #rect
Pt0 f17 @|StepIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 192 140 192 180 #arcP
Pt0 f10 expr out #txt
Pt0 f10 192 204 192 244 #arcP
Pt0 f8 expr out #txt
Pt0 f8 192 77 192 116 #arcP
Pt0 f9 expr out #txt
Pt0 f9 192 420 192 484 #arcP
>Proto Pt0 .type ch.ivyteam.wf.processes.SideStepProcessData #txt
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
Pt0 f144 mainOut f44 tail #connect
Pt0 f44 head f34 mainIn #connect
Pt0 f94 mainOut f2 tail #connect
Pt0 f2 head f144 mainIn #connect
Pt0 f13 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f17 mainOut f10 tail #connect
Pt0 f10 head f5 mainIn #connect
Pt0 f11 mainOut f8 tail #connect
Pt0 f8 head f13 mainIn #connect
Pt0 f4 mainOut f9 tail #connect
Pt0 f9 head f94 mainIn #connect
