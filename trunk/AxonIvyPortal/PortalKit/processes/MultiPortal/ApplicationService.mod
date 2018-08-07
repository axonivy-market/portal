[Ivy]
[>Created: Tue Jul 05 09:56:28 ICT 2016]
154F0EDEE841D65B 3.18 #module
>Proto >Proto Collection #zClass
Ae0 ApplicationService Big #zClass
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
Ae0 @EndSub f1 '' #zField
Ae0 @GridStep f15 '' #zField
Ae0 @PushWFArc f3 '' #zField
Ae0 @CallSub f4 '' #zField
Ae0 @PushWFArc f5 '' #zField
Ae0 @StartSub f6 '' #zField
Ae0 @EndSub f7 '' #zField
Ae0 @CallSub f9 '' #zField
Ae0 @GridStep f13 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @PushWFArc f11 '' #zField
Ae0 @CallSub f144 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @Alternative f14 '' #zField
Ae0 @PushWFArc f16 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @PushWFArc f17 '' #zField
Ae0 @Alternative f18 '' #zField
Ae0 @CallSub f19 '' #zField
Ae0 @PushWFArc f20 '' #zField
Ae0 @PushWFArc f21 '' #zField
Ae0 @PushWFArc f8 '' #zField
Ae0 @PushWFArc f22 '' #zField
>Proto Ae0 Ae0 ApplicationService #zField
Ae0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ae0 f0 inParamTable 'out.server=param.server;
' #txt
Ae0 f0 outParamDecl '<List<ch.ivy.ws.addon.IvyApplication> applications,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ae0 f0 outParamTable 'result.applications=in.applications;
result.errors=in.errors;
' #txt
Ae0 f0 actionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f0 callSignature getAllApplication(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f0 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAllApplication(Server)</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 99 83 26 26 14 0 #rect
Ae0 f0 @|StartSubIcon #fIcon
Ae0 f1 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f1 99 459 26 26 14 0 #rect
Ae0 f1 @|EndSubIcon #fIcon
Ae0 f15 actionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f15 actionTable 'out=in;
' #txt
Ae0 f15 actionCode '
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.APPLICATION.toString();
' #txt
Ae0 f15 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual
endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f15 94 164 36 24 26 -17 #rect
Ae0 f15 @|StepIcon #fIcon
Ae0 f3 expr out #txt
Ae0 f3 112 109 112 164 #arcP
Ae0 f3 0 0.9001092177717163 0 0 #arcLabel
Ae0 f4 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f4 processCall ServiceIntegrators/ApplicationServiceIntergrator:getAllApplications(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f4 doCall true #txt
Ae0 f4 requestActionDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f4 requestMappingAction 'param.endpoint=in.endpoint;
param.server=in.server;
' #txt
Ae0 f4 responseActionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f4 responseMappingAction 'out=in;
out.applications=result.applications;
out.errors=result.errors;
' #txt
Ae0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all applications of server</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f4 94 244 36 24 20 -2 #rect
Ae0 f4 @|CallSubIcon #fIcon
Ae0 f5 expr out #txt
Ae0 f5 112 188 112 244 #arcP
Ae0 f6 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ae0 f6 inParamTable 'out.server=param.server;
' #txt
Ae0 f6 outParamDecl '<List<ch.ivy.ws.addon.IvyApplication> applications,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ae0 f6 outParamTable 'result.applications=in.applications;
result.errors=in.errors;
' #txt
Ae0 f6 actionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f6 callSignature getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f6 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getApplicationsConfiguredOn(Server)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 523 83 26 26 14 0 #rect
Ae0 f6 @|StartSubIcon #fIcon
Ae0 f7 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f7 523 459 26 26 14 0 #rect
Ae0 f7 @|EndSubIcon #fIcon
Ae0 f9 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f9 processCall ServiceIntegrators/ApplicationServiceIntergrator:getApplicationsConfiguredOnServer(ch.ivy.addon.portalkit.persistence.domain.Server,String) #txt
Ae0 f9 doCall true #txt
Ae0 f9 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String endpoint> param;
' #txt
Ae0 f9 requestMappingAction 'param.server=in.server;
param.endpoint=in.endpoint;
' #txt
Ae0 f9 responseActionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f9 responseMappingAction 'out=in;
out.applications=result.applications;
out.errors=result.errors;
' #txt
Ae0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get application configured on server</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f9 518 244 36 24 20 -2 #rect
Ae0 f9 @|CallSubIcon #fIcon
Ae0 f13 actionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f13 actionTable 'out=in;
' #txt
Ae0 f13 actionCode '
import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.APPLICATION.toString();
' #txt
Ae0 f13 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual
endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 518 164 36 24 26 -17 #rect
Ae0 f13 @|StepIcon #fIcon
Ae0 f10 expr out #txt
Ae0 f10 536 109 536 164 #arcP
Ae0 f11 expr out #txt
Ae0 f11 536 188 536 244 #arcP
Ae0 f144 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ae0 f144 doCall true #txt
Ae0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ae0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ae0 f144 responseActionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
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
Ae0 f144 94 388 36 24 20 -2 #rect
Ae0 f144 @|CallSubIcon #fIcon
Ae0 f2 expr out #txt
Ae0 f2 112 412 112 459 #arcP
Ae0 f14 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has errors?</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f14 98 306 28 28 14 0 #rect
Ae0 f14 @|AlternativeIcon #fIcon
Ae0 f16 expr out #txt
Ae0 f16 112 268 112 306 #arcP
Ae0 f12 expr in #txt
Ae0 f12 outCond !in.errors.isEmpty() #txt
Ae0 f12 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f12 112 334 112 388 #arcP
Ae0 f12 0 0.3888888888888889 13 0 #arcLabel
Ae0 f17 expr in #txt
Ae0 f17 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f17 98 320 99 472 #arcP
Ae0 f17 1 56 320 #addKink
Ae0 f17 2 56 472 #addKink
Ae0 f17 0 0.5 0 -8 #arcLabel
Ae0 f18 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has errors?</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f18 522 306 28 28 14 0 #rect
Ae0 f18 @|AlternativeIcon #fIcon
Ae0 f19 type ch.ivyteam.wf.processes.ApplicationServiceData #txt
Ae0 f19 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ae0 f19 doCall true #txt
Ae0 f19 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ae0 f19 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ae0 f19 responseActionDecl 'ch.ivyteam.wf.processes.ApplicationServiceData out;
' #txt
Ae0 f19 responseMappingAction 'out=in;
' #txt
Ae0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f19 518 388 36 24 20 -2 #rect
Ae0 f19 @|CallSubIcon #fIcon
Ae0 f20 expr in #txt
Ae0 f20 outCond !in.errors.isEmpty() #txt
Ae0 f20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f20 536 334 536 388 #arcP
Ae0 f20 0 0.3333333333333333 11 0 #arcLabel
Ae0 f21 expr out #txt
Ae0 f21 536 268 536 306 #arcP
Ae0 f8 expr out #txt
Ae0 f8 536 412 536 459 #arcP
Ae0 f22 expr in #txt
Ae0 f22 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f22 522 320 523 472 #arcP
Ae0 f22 1 488 320 #addKink
Ae0 f22 2 488 472 #addKink
Ae0 f22 0 0.35294117647058826 0 -9 #arcLabel
>Proto Ae0 .type ch.ivyteam.wf.processes.ApplicationServiceData #txt
>Proto Ae0 .processKind CALLABLE_SUB #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f3 tail #connect
Ae0 f3 head f15 mainIn #connect
Ae0 f15 mainOut f5 tail #connect
Ae0 f5 head f4 mainIn #connect
Ae0 f6 mainOut f10 tail #connect
Ae0 f10 head f13 mainIn #connect
Ae0 f13 mainOut f11 tail #connect
Ae0 f11 head f9 mainIn #connect
Ae0 f144 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f4 mainOut f16 tail #connect
Ae0 f16 head f14 in #connect
Ae0 f14 out f12 tail #connect
Ae0 f12 head f144 mainIn #connect
Ae0 f14 out f17 tail #connect
Ae0 f17 head f1 mainIn #connect
Ae0 f18 out f20 tail #connect
Ae0 f20 head f19 mainIn #connect
Ae0 f9 mainOut f21 tail #connect
Ae0 f21 head f18 in #connect
Ae0 f19 mainOut f8 tail #connect
Ae0 f8 head f7 mainIn #connect
Ae0 f18 out f22 tail #connect
Ae0 f22 head f7 mainIn #connect
