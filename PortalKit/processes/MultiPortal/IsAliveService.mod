[Ivy]
[>Created: Thu Feb 04 11:33:38 ICT 2016]
14CBC98C67F229E7 3.18 #module
>Proto >Proto Collection #zClass
Ie0 IsAliveService Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .resExport .resExport #zField
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @AnnotationInP-0n ai ai #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @StartSub f0 '' #zField
Ie0 @GridStep f15 '' #zField
Ie0 @CallSub f5 '' #zField
Ie0 @PushWFArc f7 '' #zField
Ie0 @StartSub f3 '' #zField
Ie0 @GridStep f8 '' #zField
Ie0 @PushWFArc f9 '' #zField
Ie0 @PushWFArc f4 '' #zField
Ie0 @PushWFArc f12 '' #zField
Ie0 @EndSub f6 '' #zField
Ie0 @CallSub f144 '' #zField
Ie0 @PushWFArc f40 '' #zField
Ie0 @PushWFArc f1 '' #zField
>Proto Ie0 Ie0 IsAliveService #zField
Ie0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ie0 f0 inParamTable 'out.server=param.server;
' #txt
Ie0 f0 outParamDecl '<java.lang.Boolean isServerAlive,List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyApplication> apps> result;
' #txt
Ie0 f0 outParamTable 'result.isServerAlive=in.isServerAlive;
result.errors=in.errors;
result.apps=in.applicationList;
' #txt
Ie0 f0 actionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f0 callSignature getApplicationList(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ie0 f0 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getApplicationList
(Server)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f0 51 35 26 26 14 0 #rect
Ie0 f0 @|StartSubIcon #fIcon
Ie0 f15 actionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f15 actionTable 'out=in;
' #txt
Ie0 f15 actionCode '
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.IS_ALIVE.toString();
' #txt
Ie0 f15 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual
endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f15 46 164 36 24 26 -17 #rect
Ie0 f15 @|StepIcon #fIcon
Ie0 f5 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f5 processCall ServiceIntegrators/IsAliveServiceIntegrator:isAlive(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ie0 f5 doCall true #txt
Ie0 f5 requestActionDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ie0 f5 requestMappingAction 'param.endpoint=in.endpoint;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Ie0 f5 responseActionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f5 responseMappingAction 'out=in;
out.applicationList=result.applications;
out.errors=result.errors;
out.isServerAlive=result.isServerAlive;
' #txt
Ie0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isAlive(String,List&lt;String&gt;,Server)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f5 46 236 36 24 20 -2 #rect
Ie0 f5 @|CallSubIcon #fIcon
Ie0 f7 expr out #txt
Ie0 f7 64 188 64 236 #arcP
Ie0 f3 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ie0 f3 inParamTable 'out.apps=[];
out.server=param.server;
' #txt
Ie0 f3 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyApplication> apps,java.lang.Boolean isServerAlive> result;
' #txt
Ie0 f3 outParamTable 'result.errors=in.errors;
result.apps=in.applicationList;
result.isServerAlive=in.isServerAlive;
' #txt
Ie0 f3 actionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f3 callSignature getAllApplications(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ie0 f3 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAllApplications
(Server)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f3 243 35 26 26 14 0 #rect
Ie0 f3 @|StartSubIcon #fIcon
Ie0 f8 actionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f8 actionTable 'out=in;
' #txt
Ie0 f8 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
in.apps = SecurityServiceUtils.getAppNames(in.server);' #txt
Ie0 f8 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get applications user
can work on</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f8 46 100 36 24 20 -2 #rect
Ie0 f8 @|StepIcon #fIcon
Ie0 f9 expr out #txt
Ie0 f9 64 61 64 100 #arcP
Ie0 f4 expr out #txt
Ie0 f4 64 124 64 164 #arcP
Ie0 f12 expr out #txt
Ie0 f12 256 61 82 176 #arcP
Ie0 f12 1 256 176 #addKink
Ie0 f12 1 0.2219818033885933 0 0 #arcLabel
Ie0 f6 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f6 51 339 26 26 14 0 #rect
Ie0 f6 @|EndSubIcon #fIcon
Ie0 f144 type ch.ivyteam.wf.processes.IsAliveServiceData #txt
Ie0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ie0 f144 doCall true #txt
Ie0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ie0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ie0 f144 responseActionDecl 'ch.ivyteam.wf.processes.IsAliveServiceData out;
' #txt
Ie0 f144 responseMappingAction 'out=in;
' #txt
Ie0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f144 46 292 36 24 20 -2 #rect
Ie0 f144 @|CallSubIcon #fIcon
Ie0 f40 expr out #txt
Ie0 f40 64 316 64 339 #arcP
Ie0 f1 expr out #txt
Ie0 f1 64 260 64 292 #arcP
>Proto Ie0 .type ch.ivyteam.wf.processes.IsAliveServiceData #txt
>Proto Ie0 .processKind CALLABLE_SUB #txt
>Proto Ie0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Get Applications</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneColor>-3355393</swimlaneColor>
</elementInfo>
' #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f15 mainOut f7 tail #connect
Ie0 f7 head f5 mainIn #connect
Ie0 f0 mainOut f9 tail #connect
Ie0 f9 head f8 mainIn #connect
Ie0 f8 mainOut f4 tail #connect
Ie0 f4 head f15 mainIn #connect
Ie0 f3 mainOut f12 tail #connect
Ie0 f12 head f15 mainIn #connect
Ie0 f144 mainOut f40 tail #connect
Ie0 f40 head f6 mainIn #connect
Ie0 f5 mainOut f1 tail #connect
Ie0 f1 head f144 mainIn #connect
