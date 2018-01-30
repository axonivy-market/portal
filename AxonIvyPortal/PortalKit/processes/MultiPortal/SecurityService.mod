[Ivy]
1485F329FE84F01E 3.20 #module
>Proto >Proto Collection #zClass
Pt0 SecurityService Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartSub f0 '' #zField
Pt0 @Alternative f3 '' #zField
Pt0 @GridStep f7 '' #zField
Pt0 @GridStep f9 '' #zField
Pt0 @GridStep f10 '' #zField
Pt0 @PushWFArc f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @GridStep f15 '' #zField
Pt0 @GridStep f31 '' #zField
Pt0 @StartSub f32 '' #zField
Pt0 @Alternative f37 '' #zField
Pt0 @GridStep f39 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f46 '' #zField
Pt0 @StartSub f55 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @GridStep f64 '' #zField
Pt0 @PushWFArc f65 '' #zField
Pt0 @CallSub f28 '' #zField
Pt0 @CallSub f19 '' #zField
Pt0 @PushWFArc f21 '' #zField
Pt0 @PushWFArc f33 '' #zField
Pt0 @CallSub f8 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f43 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f51 '' #zField
Pt0 @CallSub f4 '' #zField
Pt0 @StartSub f13 '' #zField
Pt0 @GridStep f52 '' #zField
Pt0 @CallSub f54 '' #zField
Pt0 @GridStep f56 '' #zField
Pt0 @PushWFArc f59 '' #zField
Pt0 @PushWFArc f61 '' #zField
Pt0 @PushWFArc f27 '' #zField
Pt0 @PushWFArc f41 '' #zField
Pt0 @Alternative f148 '' #zField
Pt0 @EndSub f12 '' #zField
Pt0 @CallSub f144 '' #zField
Pt0 @PushWFArc f47 '' #zField
Pt0 @PushWFArc f107 '' #zField
Pt0 @PushWFArc f57 '' #zField
Pt0 @PushWFArc f25 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f16 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @StartSub f6 '' #zField
Pt0 @PushWFArc f22 '' #zField
>Proto Pt0 Pt0 SecurityService #zField
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteRole> roles> result;
' #txt
Pt0 f0 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Pt0 f0 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f0 callSignature findAllRoles() #txt
Pt0 f0 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRoles</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 59 43 26 26 14 0 #rect
Pt0 f0 @|StartSubIcon #fIcon
Pt0 f3 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 58 170 28 28 14 0 #rect
Pt0 f3 @|AlternativeIcon #fIcon
Pt0 f7 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f7 actionTable 'out=in;
' #txt
Pt0 f7 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.ws.addon.IvyApplication;

if (in.hasAppsParam) {
	java.util.Iterator iterator = in.apps.iterator();
	while (iterator.hasNext()) {
		String app = iterator.next() as String;
		for (IvyApplication application : in.applications) {
			if(application.name.equals(app) && !application.isActive){
				iterator.remove();
			}
		}
	}
} else {
	in.apps.clear();
	for (IvyApplication app : in.applications) {
		if(app.isActive){
			in.apps.add(app.name);
		}
	}
}

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.SECURITY_SETTING.toString();' #txt
Pt0 f7 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f7 54 372 36 24 20 -2 #rect
Pt0 f7 @|StepIcon #fIcon
Pt0 f9 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f9 actionTable 'out=in;
' #txt
Pt0 f9 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteRoleMapper;

if(in.tempErrors.size()==0){
	in.roles.addAll(RemoteRoleMapper.mapIvyRoles(in.ivyRoles));
}' #txt
Pt0 f9 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add security
members</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f9 54 500 36 24 20 -2 #rect
Pt0 f9 @|StepIcon #fIcon
Pt0 f10 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f10 actionTable 'out=in;
' #txt
Pt0 f10 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);
' #txt
Pt0 f10 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f10 190 172 36 24 20 -2 #rect
Pt0 f10 @|StepIcon #fIcon
Pt0 f17 expr out #txt
Pt0 f17 90 512 226 196 #arcP
Pt0 f17 1 256 512 #addKink
Pt0 f17 2 256 216 #addKink
Pt0 f17 1 0.48303474097746707 0 0 #arcLabel
Pt0 f18 expr out #txt
Pt0 f18 190 184 86 184 #arcP
Pt0 f18 0 0.3573220439540461 0 0 #arcLabel
Pt0 f15 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f15 actionTable 'out=in;
' #txt
Pt0 f15 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;
import ch.ivy.ws.addon.IvyApplication;

if (in.hasAppsParam) {
	java.util.Iterator iterator = in.apps.iterator();
	while (iterator.hasNext()) {
		String app = iterator.next() as String;
		for (IvyApplication application : in.applications) {
			if(application.name.equals(app) && !application.isActive){
				iterator.remove();
			}
		}
	}
} else {
	in.apps.clear();
	for (IvyApplication app : in.applications) {
		if(app.isActive){
			in.apps.add(app.name);
		}
	}
}

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.SECURITY_SETTING.toString();' #txt
Pt0 f15 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f15 430 372 36 24 20 -2 #rect
Pt0 f15 @|StepIcon #fIcon
Pt0 f31 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f31 actionTable 'out=in;
' #txt
Pt0 f31 actionCode 'import ch.ivy.ws.addon.WsException;
for(int i = 0 ; i < in.tempErrors.size() ; i++){
	WsException w = in.tempErrors.get(i) as WsException;
	w.server = in.server.name;
	in.tempErrors.set(i,w);
	}
in.errors.addAll(in.tempErrors);' #txt
Pt0 f31 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pt0 f31 558 172 36 24 20 -2 #rect
Pt0 f31 @|StepIcon #fIcon
Pt0 f32 inParamDecl '<> param;' #txt
Pt0 f32 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteUser> users> result;
' #txt
Pt0 f32 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f32 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f32 callSignature findAllUsers() #txt
Pt0 f32 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsers</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f32 435 43 26 26 14 0 #rect
Pt0 f32 @|StartSubIcon #fIcon
Pt0 f37 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next server</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f37 434 170 28 28 14 0 #rect
Pt0 f37 @|AlternativeIcon #fIcon
Pt0 f39 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f39 actionTable 'out=in;
' #txt
Pt0 f39 actionCode 'import ch.ivy.addon.portalkit.mapper.RemoteUserMapper;

if(in.tempErrors.size()==0){
	in.users.addAll(RemoteUserMapper.mapIvyUsers(in.ivyUsers, in.server));
}' #txt
Pt0 f39 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add security
members</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f39 430 500 36 24 20 -2 #rect
Pt0 f39 @|StepIcon #fIcon
Pt0 f45 expr out #txt
Pt0 f45 466 512 590 196 #arcP
Pt0 f45 1 624 512 #addKink
Pt0 f45 2 624 224 #addKink
Pt0 f45 1 0.48303474097746707 0 0 #arcLabel
Pt0 f46 expr out #txt
Pt0 f46 558 184 462 184 #arcP
Pt0 f46 0 0.3573220439540461 0 0 #arcLabel
Pt0 f55 inParamDecl '<java.lang.String app> param;' #txt
Pt0 f55 inParamTable 'out.apps=[param.app];
out.hasAppsParam=true;
' #txt
Pt0 f55 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteRole> roles> result;
' #txt
Pt0 f55 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Pt0 f55 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f55 callSignature findAllRolesByApplication(String) #txt
Pt0 f55 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRolesByApplication</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f55 179 43 26 26 14 0 #rect
Pt0 f55 @|StartSubIcon #fIcon
Pt0 f11 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f11 actionTable 'out=in;
' #txt
Pt0 f11 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;

in.server = in.listIterator.next() as Server;' #txt
Pt0 f11 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server and apps</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 430 228 36 24 20 -2 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f36 expr in #txt
Pt0 f36 outCond in.listIterator.hasNext() #txt
Pt0 f36 448 198 448 228 #arcP
Pt0 f64 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f64 actionTable 'out=in;
' #txt
Pt0 f64 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;


in.server = in.listIterator.next() as Server;' #txt
Pt0 f64 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f64 54 220 36 24 20 -2 #rect
Pt0 f64 @|StepIcon #fIcon
Pt0 f65 expr in #txt
Pt0 f65 outCond in.listIterator.hasNext() #txt
Pt0 f65 72 198 72 220 #arcP
Pt0 f28 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f28 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f28 doCall true #txt
Pt0 f28 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f28 requestMappingAction 'param.server=in.server;
' #txt
Pt0 f28 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f28 responseMappingAction 'out=in;
out.applications=result.applications;
out.tempErrors=result.errors;
' #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get applications 
of server</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f28 54 292 36 24 20 -2 #rect
Pt0 f28 @|CallSubIcon #fIcon
Pt0 f19 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f19 processCall ServiceIntegrators/SecurityServiceIntegrator:findAllRoles(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f19 doCall true #txt
Pt0 f19 requestActionDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f19 requestMappingAction 'param.endpoint=in.endpoint;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Pt0 f19 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f19 responseMappingAction 'out=in;
out.errors=result.errors;
out.ivyRoles=result.roles;
' #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRoles(String,List&lt;String&gt;,Server)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f19 54 436 36 24 20 -2 #rect
Pt0 f19 @|CallSubIcon #fIcon
Pt0 f21 expr out #txt
Pt0 f21 72 396 72 436 #arcP
Pt0 f33 expr out #txt
Pt0 f33 72 460 72 500 #arcP
Pt0 f8 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f8 processCall ServiceIntegrators/SecurityServiceIntegrator:findAllUsers(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f8 doCall true #txt
Pt0 f8 requestActionDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f8 requestMappingAction 'param.endpoint=in.endpoint;
param.applicationNames=in.apps;
param.server=in.server;
' #txt
Pt0 f8 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f8 responseMappingAction 'out=in;
out.errors=result.errors;
out.ivyUsers=result.users;
' #txt
Pt0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsers
(String,List&lt;String&gt;,Server)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f8 430 436 36 24 21 -2 #rect
Pt0 f8 @|CallSubIcon #fIcon
Pt0 f30 expr out #txt
Pt0 f30 448 396 448 436 #arcP
Pt0 f34 expr out #txt
Pt0 f34 448 460 448 500 #arcP
Pt0 f20 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Pt0 f20 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 430 108 36 24 20 -2 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 448 69 448 108 #arcP
Pt0 f43 expr out #txt
Pt0 f43 448 132 448 170 #arcP
Pt0 f2 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f2 actionTable 'out=in;
' #txt
Pt0 f2 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.listIterator = serverService.findActiveServers().listIterator();' #txt
Pt0 f2 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all servers</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f2 54 108 36 24 20 -2 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f42 expr out #txt
Pt0 f42 72 69 72 108 #arcP
Pt0 f44 expr out #txt
Pt0 f44 180 62 90 120 #arcP
Pt0 f44 0 0.47604790419161674 0 0 #arcLabel
Pt0 f51 expr out #txt
Pt0 f51 72 132 72 170 #arcP
Pt0 f4 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f4 processCall MultiPortal/ApplicationService:getApplicationConfiguredOn(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f4 doCall true #txt
Pt0 f4 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f4 requestMappingAction 'param.server=in.server;
' #txt
Pt0 f4 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f4 responseMappingAction 'out=in;
out.applications=result.applications;
out.tempErrors=result.errors;
' #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get applications 
of server</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f4 430 292 36 24 20 -2 #rect
Pt0 f4 @|CallSubIcon #fIcon
Pt0 f13 inParamDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Pt0 f13 inParamTable 'out.server=param.server;
out.taskId=param.taskId;
' #txt
Pt0 f13 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,ch.ivyteam.wf.processes.SecurityMemberData securityMemberData> result;
' #txt
Pt0 f13 outParamTable 'result.errors=in.errors;
result.securityMemberData=in.securityMemberData;
' #txt
Pt0 f13 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f13 callSignature findSecurityMembersToDelegateBy(Long,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f13 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembersToDelegateBy
(Long,Server)</name>
        <nameStyle>45,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 691 43 26 26 14 0 #rect
Pt0 f13 @|StartSubIcon #fIcon
Pt0 f52 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f52 actionTable 'out=in;
' #txt
Pt0 f52 actionCode 'import ch.ivy.ws.addon.WsException;
for(WsException error : in.errors){
	error.server = in.server.name;
}' #txt
Pt0 f52 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>utils</name>
        <nameStyle>5,7
</nameStyle>
        <desc>add server name for exceptions</desc>
    </language>
</elementInfo>
' #txt
Pt0 f52 686 260 36 24 20 -2 #rect
Pt0 f52 @|StepIcon #fIcon
Pt0 f54 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f54 processCall ServiceIntegrators/SecurityServiceIntegrator:findSecurityMembersToDelegate(Long,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pt0 f54 doCall true #txt
Pt0 f54 requestActionDecl '<java.lang.Long taskId,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Pt0 f54 requestMappingAction 'param.taskId=in.taskId;
param.endpoint=in.endpoint;
param.server=in.server;
' #txt
Pt0 f54 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f54 responseMappingAction 'out=in;
out.errors=result.errors;
out.securityMemberData.ivyRoles=result.roles;
out.securityMemberData.ivyUsers=result.users;
' #txt
Pt0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityServiceIntegrator</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f54 686 196 36 24 20 -2 #rect
Pt0 f54 @|CallSubIcon #fIcon
Pt0 f56 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f56 actionTable 'out=in;
' #txt
Pt0 f56 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.SECURITY_SETTING.toString();' #txt
Pt0 f56 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f56 686 132 36 24 20 -2 #rect
Pt0 f56 @|StepIcon #fIcon
Pt0 f59 expr out #txt
Pt0 f59 704 220 704 260 #arcP
Pt0 f61 expr out #txt
Pt0 f61 704 156 704 196 #arcP
Pt0 f27 expr out #txt
Pt0 f27 72 244 72 292 #arcP
Pt0 f41 expr out #txt
Pt0 f41 448 252 448 292 #arcP
Pt0 f148 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f148 394 618 28 28 14 0 #rect
Pt0 f148 @|AlternativeIcon #fIcon
Pt0 f12 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f12 395 707 26 26 14 0 #rect
Pt0 f12 @|EndSubIcon #fIcon
Pt0 f144 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Pt0 f144 doCall true #txt
Pt0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Pt0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f144 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
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
Pt0 f144 390 660 36 24 20 -2 #rect
Pt0 f144 @|CallSubIcon #fIcon
Pt0 f47 expr out #txt
Pt0 f47 408 684 408 707 #arcP
Pt0 f107 expr in #txt
Pt0 f107 408 646 408 660 #arcP
Pt0 f57 expr in #txt
Pt0 f57 58 184 394 632 #arcP
Pt0 f57 1 32 184 #addKink
Pt0 f57 2 32 632 #addKink
Pt0 f57 1 0.5722278574370061 0 0 #arcLabel
Pt0 f25 expr in #txt
Pt0 f25 434 184 408 618 #arcP
Pt0 f25 1 408 184 #addKink
Pt0 f25 1 0.4663034063366414 0 0 #arcLabel
Pt0 f1 expr out #txt
Pt0 f1 704 284 422 632 #arcP
Pt0 f1 1 704 632 #addKink
Pt0 f1 0 0.9181321800877275 0 0 #arcLabel
Pt0 f14 expr out #txt
Pt0 f14 704 69 704 132 #arcP
Pt0 f16 expr out #txt
Pt0 f16 72 316 72 372 #arcP
Pt0 f5 expr out #txt
Pt0 f5 448 316 448 372 #arcP
Pt0 f6 inParamDecl '<java.lang.String app> param;' #txt
Pt0 f6 inParamTable 'out.apps=[param.app];
out.hasAppsParam=true;
' #txt
Pt0 f6 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.addon.portalkit.bo.RemoteUser> users> result;
' #txt
Pt0 f6 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f6 actionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f6 callSignature findAllUsersByApplication(String) #txt
Pt0 f6 type ch.ivyteam.wf.processes.SecurityServiceData #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsersByApplication</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 552 46 26 26 14 0 #rect
Pt0 f6 @|StartSubIcon #fIcon
Pt0 f22 expr out #txt
Pt0 f22 553 65 466 120 #arcP
>Proto Pt0 .type ch.ivyteam.wf.processes.SecurityServiceData #txt
>Proto Pt0 .processKind CALLABLE_SUB #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Find all roles</swimlaneLabel>
        <swimlaneLabel>Find all users</swimlaneLabel>
        <swimlaneLabel>Find security members to delegate</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>352</swimlaneSize>
    <swimlaneSize>304</swimlaneSize>
    <swimlaneSize>264</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-10027162</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f9 mainOut f17 tail #connect
Pt0 f17 head f10 mainIn #connect
Pt0 f10 mainOut f18 tail #connect
Pt0 f18 head f3 in #connect
Pt0 f39 mainOut f45 tail #connect
Pt0 f45 head f31 mainIn #connect
Pt0 f31 mainOut f46 tail #connect
Pt0 f46 head f37 in #connect
Pt0 f37 out f36 tail #connect
Pt0 f36 head f11 mainIn #connect
Pt0 f3 out f65 tail #connect
Pt0 f65 head f64 mainIn #connect
Pt0 f7 mainOut f21 tail #connect
Pt0 f21 head f19 mainIn #connect
Pt0 f19 mainOut f33 tail #connect
Pt0 f33 head f9 mainIn #connect
Pt0 f15 mainOut f30 tail #connect
Pt0 f30 head f8 mainIn #connect
Pt0 f8 mainOut f34 tail #connect
Pt0 f34 head f39 mainIn #connect
Pt0 f32 mainOut f26 tail #connect
Pt0 f26 head f20 mainIn #connect
Pt0 f20 mainOut f43 tail #connect
Pt0 f43 head f37 in #connect
Pt0 f0 mainOut f42 tail #connect
Pt0 f42 head f2 mainIn #connect
Pt0 f55 mainOut f44 tail #connect
Pt0 f44 head f2 mainIn #connect
Pt0 f2 mainOut f51 tail #connect
Pt0 f51 head f3 in #connect
Pt0 f54 mainOut f59 tail #connect
Pt0 f59 head f52 mainIn #connect
Pt0 f56 mainOut f61 tail #connect
Pt0 f61 head f54 mainIn #connect
Pt0 f64 mainOut f27 tail #connect
Pt0 f27 head f28 mainIn #connect
Pt0 f11 mainOut f41 tail #connect
Pt0 f41 head f4 mainIn #connect
Pt0 f144 mainOut f47 tail #connect
Pt0 f47 head f12 mainIn #connect
Pt0 f148 out f107 tail #connect
Pt0 f107 head f144 mainIn #connect
Pt0 f3 out f57 tail #connect
Pt0 f57 head f148 in #connect
Pt0 f37 out f25 tail #connect
Pt0 f25 head f148 in #connect
Pt0 f52 mainOut f1 tail #connect
Pt0 f1 head f148 in #connect
Pt0 f13 mainOut f14 tail #connect
Pt0 f14 head f56 mainIn #connect
Pt0 f28 mainOut f16 tail #connect
Pt0 f16 head f7 mainIn #connect
Pt0 f4 mainOut f5 tail #connect
Pt0 f5 head f15 mainIn #connect
Pt0 f6 mainOut f22 tail #connect
Pt0 f22 head f20 mainIn #connect
