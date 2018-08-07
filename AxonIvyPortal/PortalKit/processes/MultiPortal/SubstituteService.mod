[Ivy]
[>Created: Mon May 30 13:33:56 ICT 2016]
14BECA923C1F4A4B 3.18 #module
>Proto >Proto Collection #zClass
Se0 SubstituteService Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @GridStep f65 '' #zField
Se0 @GridStep f89 '' #zField
Se0 @StartSub f54 '' #zField
Se0 @Alternative f91 '' #zField
Se0 @GridStep f57 '' #zField
Se0 @GridStep f59 '' #zField
Se0 @Alternative f34 '' #zField
Se0 @StartSub f82 '' #zField
Se0 @PushWFArc f74 '' #zField
Se0 @PushWFArc f76 '' #zField
Se0 @PushWFArc f102 '' #zField
Se0 @GridStep f1 '' #zField
Se0 @CallSub f2 '' #zField
Se0 @PushWFArc f11 '' #zField
Se0 @CallSub f12 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @GridStep f0 '' #zField
Se0 @PushWFArc f14 '' #zField
Se0 @PushWFArc f15 '' #zField
Se0 @GridStep f16 '' #zField
Se0 @PushWFArc f17 '' #zField
Se0 @PushWFArc f18 '' #zField
Se0 @CallSub f77 '' #zField
Se0 @GridStep f79 '' #zField
Se0 @PushWFArc f19 '' #zField
Se0 @PushWFArc f20 '' #zField
Se0 @PushWFArc f21 '' #zField
Se0 @GridStep f7 '' #zField
Se0 @PushWFArc f23 '' #zField
Se0 @Alternative f148 '' #zField
Se0 @EndSub f5 '' #zField
Se0 @CallSub f144 '' #zField
Se0 @PushWFArc f40 '' #zField
Se0 @PushWFArc f107 '' #zField
Se0 @PushWFArc f8 '' #zField
Se0 @PushWFArc f9 '' #zField
Se0 @PushWFArc f25 '' #zField
Se0 @PushWFArc f3 '' #zField
Se0 @PushWFArc f6 '' #zField
Se0 @PushWFArc f10 '' #zField
Se0 @PushWFArc f75 '' #zField
Se0 @Alternative f60 '' #zField
>Proto Se0 Se0 SubstituteService #zField
Se0 f65 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f65 actionTable 'out=in;
' #txt
Se0 f65 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.ws.addon.IvyApplication;
import ch.ivy.addon.portalkit.service.UserService;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;

in.apps.clear();


UserService userService = new UserService();
java.util.List<String> apps = userService.findApplicationNamesUserCanWorkOn(in.username, in.server.id);
in.apps = AbsenceAndSubstituteUtils.getAbsencesSettingSupportedApps(apps, in.server.getApplications());
if (in.apps.isEmpty()) {
	ApplicationService applicationService = new ApplicationService();
	in.apps = applicationService.getApplicationNames(in.applications);
}
PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();

' #txt
Se0 f65 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Alive Apps
ws endpoint</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f65 47 340 34 24 21 -18 #rect
Se0 f65 @|StepIcon #fIcon
Se0 f89 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f89 actionTable 'out=in;
' #txt
Se0 f89 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);

in.apps.clear();' #txt
Se0 f89 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f89 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Se0 f89 470 205 36 23 19 14 #rect
Se0 f89 @|StepIcon #fIcon
Se0 f54 inParamDecl '<java.lang.String user> param;' #txt
Se0 f54 inParamTable 'out.username=param.user;
' #txt
Se0 f54 outParamDecl '<List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> remoteApplicationUsers,java.util.List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> remoteSubstitutes> result;
' #txt
Se0 f54 outParamTable 'result.remoteApplicationUsers=in.remoteApplicationUsers;
result.errors=in.errors;
result.remoteSubstitutes=in.remoteSusbtitutes;
' #txt
Se0 f54 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f54 callSignature getSubstitutes(String) #txt
Se0 f54 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSubstitutes</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f54 51 35 26 26 14 0 #rect
Se0 f54 @|StartSubIcon #fIcon
Se0 f91 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f91 346 170 28 28 22 -21 #rect
Se0 f91 @|AlternativeIcon #fIcon
Se0 f57 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f57 actionTable 'out=in;
' #txt
Se0 f57 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);

in.apps.clear();
' #txt
Se0 f57 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f57 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Se0 f57 166 181 36 23 19 14 #rect
Se0 f57 @|StepIcon #fIcon
Se0 f59 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f59 actionTable 'out=in;
' #txt
Se0 f59 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteApplicationUserMapper;
import ch.ivy.addon.portalkit.bo.RemoteApplicationUser;
import ch.ivy.addon.portalkit.mapper.RemoteSubstituteMapper;
import ch.ivy.addon.portalkit.bo.RemoteSubstitute;

List<RemoteSubstitute> remoteSubstitutes = RemoteSubstituteMapper.mapToRemoteSubstitutes(in.substitutes, in.server.id);
in.remoteSusbtitutes.addAll(remoteSubstitutes);
	
List<RemoteApplicationUser> remoteUsers = RemoteApplicationUserMapper.mapToRemoteApplicationUsers(in.applicationUsers, in.server);
in.remoteApplicationUsers.addAll(remoteUsers);
' #txt
Se0 f59 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add substitutes &amp; users</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f59 46 556 36 24 -10 19 #rect
Se0 f59 @|StepIcon #fIcon
Se0 f34 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f34 50 146 28 28 22 -21 #rect
Se0 f34 @|AlternativeIcon #fIcon
Se0 f82 inParamDecl '<List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> substitutes,java.lang.String user> param;' #txt
Se0 f82 inParamTable 'out.remoteSusbtitutes=param.substitutes;
out.username=param.user;
' #txt
Se0 f82 outParamDecl '<java.util.List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> remoteSubstitutes> result;
' #txt
Se0 f82 outParamTable 'result.errors=in.errors;
result.remoteSubstitutes=in.remoteSusbtitutes;
' #txt
Se0 f82 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f82 callSignature setSubstitutes(List<ch.ivy.addon.portalkit.bo.RemoteSubstitute>,String) #txt
Se0 f82 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setSubstitutes</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f82 347 35 26 26 14 0 #rect
Se0 f82 @|StartSubIcon #fIcon
Se0 f74 expr out #txt
Se0 f74 82 568 184 203 #arcP
Se0 f74 1 184 568 #addKink
Se0 f74 1 0.4321413036724042 0 0 #arcLabel
Se0 f76 expr out #txt
Se0 f76 184 180 78 160 #arcP
Se0 f76 1 184 160 #addKink
Se0 f76 1 0.31135433052386374 0 0 #arcLabel
Se0 f102 expr out #txt
Se0 f102 488 204 374 184 #arcP
Se0 f102 1 488 184 #addKink
Se0 f102 1 0.31135433052386374 0 0 #arcLabel
Se0 f1 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f1 actionTable 'out=in;
' #txt
Se0 f1 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.addon.portalkit.mapper.RemoteSubstituteMapper;


PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.USER_SETTING.toString();

in.substitutes = RemoteSubstituteMapper.mapToIvySubstitutesForServer(in.remoteSusbtitutes, in.server.id);' #txt
Se0 f1 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Alive Apps
ws endpoint</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f1 342 308 36 24 23 -14 #rect
Se0 f1 @|StepIcon #fIcon
Se0 f2 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f2 processCall ServiceIntegrators/UserServiceIntegrator:getSubstitutes(String,String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f2 doCall true #txt
Se0 f2 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Se0 f2 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Se0 f2 responseActionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f2 responseMappingAction 'out=in;
out.applicationUsers=result.users;
out.substitutes=result.substitutes;
out.tempErrors=result.errors;
' #txt
Se0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSubstitutes
(String,String,List&lt;String&gt;,Server)</name>
        <nameStyle>50,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f2 46 476 36 24 20 -2 #rect
Se0 f2 @|CallSubIcon #fIcon
Se0 f11 expr out #txt
Se0 f11 64 500 64 556 #arcP
Se0 f12 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f12 processCall ServiceIntegrators/UserServiceIntegrator:setSubstitutes(String,String,ch.ivy.addon.portalkit.persistence.domain.Server,List<ch.ivy.ws.addon.IvySubstitute>) #txt
Se0 f12 doCall true #txt
Se0 f12 requestActionDecl '<java.lang.String endpoint,java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,List<ch.ivy.ws.addon.IvySubstitute> substitutes> param;
' #txt
Se0 f12 requestMappingAction 'param.endpoint=in.endpoint;
param.userName=in.username;
param.server=in.server;
param.substitutes=in.substitutes;
' #txt
Se0 f12 responseActionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f12 responseMappingAction 'out=in;
out.tempErrors=result.errors;
' #txt
Se0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setSubstitutes
(String,String,Server,List&lt;IvySubstitute&gt;)</name>
        <nameStyle>57,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f12 342 396 36 24 20 -2 #rect
Se0 f12 @|CallSubIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 378 408 488 227 #arcP
Se0 f4 1 488 408 #addKink
Se0 f4 1 0.463958635866633 0 0 #arcLabel
Se0 f0 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f0 actionTable 'out=in;
' #txt
Se0 f0 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Se0 f0 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 46 92 36 24 20 -2 #rect
Se0 f0 @|StepIcon #fIcon
Se0 f14 expr out #txt
Se0 f14 64 61 64 92 #arcP
Se0 f15 expr out #txt
Se0 f15 64 116 64 146 #arcP
Se0 f16 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f16 actionTable 'out=in;
' #txt
Se0 f16 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Se0 f16 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f16 342 100 36 24 20 -2 #rect
Se0 f16 @|StepIcon #fIcon
Se0 f17 expr out #txt
Se0 f17 360 61 360 100 #arcP
Se0 f18 expr out #txt
Se0 f18 360 124 360 170 #arcP
Se0 f77 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f77 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f77 doCall true #txt
Se0 f77 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Se0 f77 requestMappingAction 'param.server=in.server;
' #txt
Se0 f77 responseActionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f77 responseMappingAction 'out=in;
out.applications=result.applications;
out.tempErrors=result.errors;
' #txt
Se0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get applications 
of server</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f77 46 276 36 24 20 -2 #rect
Se0 f77 @|CallSubIcon #fIcon
Se0 f79 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f79 actionTable 'out=in;
' #txt
Se0 f79 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;

in.server = in.listIterator.next() as Server;' #txt
Se0 f79 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f79 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f79 46 212 36 24 20 -2 #rect
Se0 f79 @|StepIcon #fIcon
Se0 f19 expr out #txt
Se0 f19 64 236 64 276 #arcP
Se0 f20 expr in #txt
Se0 f20 outCond in.listIterator.hasNext() #txt
Se0 f20 64 174 64 212 #arcP
Se0 f21 expr out #txt
Se0 f21 64 300 64 340 #arcP
Se0 f7 actionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f7 actionTable 'out=in;
' #txt
Se0 f7 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Se0 f7 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f7 342 236 36 24 20 -2 #rect
Se0 f7 @|StepIcon #fIcon
Se0 f23 expr in #txt
Se0 f23 outCond in.listIterator.hasNext() #txt
Se0 f23 360 198 360 236 #arcP
Se0 f148 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f148 314 602 28 28 14 0 #rect
Se0 f148 @|AlternativeIcon #fIcon
Se0 f5 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f5 315 691 26 26 14 0 #rect
Se0 f5 @|EndSubIcon #fIcon
Se0 f144 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Se0 f144 doCall true #txt
Se0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Se0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f144 responseActionDecl 'ch.ivyteam.wf.processes.SubstitueServiceData out;
' #txt
Se0 f144 responseMappingAction 'out=in;
' #txt
Se0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f144 310 644 36 24 20 -2 #rect
Se0 f144 @|CallSubIcon #fIcon
Se0 f40 expr out #txt
Se0 f40 328 668 328 691 #arcP
Se0 f107 expr in #txt
Se0 f107 328 630 328 644 #arcP
Se0 f8 expr in #txt
Se0 f8 346 184 328 602 #arcP
Se0 f8 1 328 184 #addKink
Se0 f8 1 0.46041906670520466 0 0 #arcLabel
Se0 f9 expr in #txt
Se0 f9 50 160 314 616 #arcP
Se0 f9 1 17 160 #addKink
Se0 f9 2 17 616 #addKink
Se0 f9 1 0.4697017091790999 0 0 #arcLabel
Se0 f25 expr out #txt
Se0 f25 360 332 360 396 #arcP
Se0 f3 expr out #txt
Se0 f3 360 260 360 308 #arcP
Se0 f6 expr out #txt
Se0 f6 64 364 64 476 #arcP
Se0 f10 expr in #txt
Se0 f10 outCond in.apps.size()>0 #txt
Se0 f10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f10 64 430 64 476 #arcP
Se0 f10 0 0.48717948717948717 13 0 #arcLabel
Se0 f75 expr in #txt
Se0 f75 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f75 78 416 184 203 #arcP
Se0 f75 1 184 416 #addKink
Se0 f75 0 0.5283018867924528 0 11 #arcLabel
Se0 f60 type ch.ivyteam.wf.processes.SubstitueServiceData #txt
Se0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>any apps active?</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f60 50 402 28 28 11 -21 #rect
Se0 f60 @|AlternativeIcon #fIcon
>Proto Se0 .type ch.ivyteam.wf.processes.SubstitueServiceData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Add substitutes</swimlaneLabel>
        <swimlaneLabel>Set substitutes</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>272</swimlaneSize>
    <swimlaneSize>496</swimlaneSize>
    <swimlaneSize>840</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f59 mainOut f74 tail #connect
Se0 f74 head f57 mainIn #connect
Se0 f75 head f57 mainIn #connect
Se0 f57 mainOut f76 tail #connect
Se0 f76 head f34 in #connect
Se0 f89 mainOut f102 tail #connect
Se0 f102 head f91 in #connect
Se0 f60 out f10 tail #connect
Se0 f10 head f2 mainIn #connect
Se0 f60 out f75 tail #connect
Se0 f2 mainOut f11 tail #connect
Se0 f11 head f59 mainIn #connect
Se0 f12 mainOut f4 tail #connect
Se0 f4 head f89 mainIn #connect
Se0 f54 mainOut f14 tail #connect
Se0 f14 head f0 mainIn #connect
Se0 f0 mainOut f15 tail #connect
Se0 f15 head f34 in #connect
Se0 f82 mainOut f17 tail #connect
Se0 f17 head f16 mainIn #connect
Se0 f16 mainOut f18 tail #connect
Se0 f18 head f91 in #connect
Se0 f79 mainOut f19 tail #connect
Se0 f19 head f77 mainIn #connect
Se0 f34 out f20 tail #connect
Se0 f20 head f79 mainIn #connect
Se0 f77 mainOut f21 tail #connect
Se0 f21 head f65 mainIn #connect
Se0 f91 out f23 tail #connect
Se0 f23 head f7 mainIn #connect
Se0 f144 mainOut f40 tail #connect
Se0 f40 head f5 mainIn #connect
Se0 f148 out f107 tail #connect
Se0 f107 head f144 mainIn #connect
Se0 f91 out f8 tail #connect
Se0 f8 head f148 in #connect
Se0 f34 out f9 tail #connect
Se0 f9 head f148 in #connect
Se0 f1 mainOut f25 tail #connect
Se0 f25 head f12 mainIn #connect
Se0 f7 mainOut f3 tail #connect
Se0 f3 head f1 mainIn #connect
Se0 f65 mainOut f6 tail #connect
Se0 f6 head f2 mainIn #connect
