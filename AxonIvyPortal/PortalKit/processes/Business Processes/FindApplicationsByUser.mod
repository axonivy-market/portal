[Ivy]
14C7834636E63BEA 3.20 #module
>Proto >Proto Collection #zClass
Fr0 FindApplicationsByUser Big #zClass
Fr0 B #cInfo
Fr0 #process
Fr0 @TextInP .resExport .resExport #zField
Fr0 @TextInP .type .type #zField
Fr0 @TextInP .processKind .processKind #zField
Fr0 @AnnotationInP-0n ai ai #zField
Fr0 @TextInP .xml .xml #zField
Fr0 @TextInP .responsibility .responsibility #zField
Fr0 @StartSub f0 '' #zField
Fr0 @EndSub f1 '' #zField
Fr0 @GridStep f3 '' #zField
Fr0 @PushWFArc f4 '' #zField
Fr0 @Alternative f5 '' #zField
Fr0 @PushWFArc f6 '' #zField
Fr0 @Alternative f11 '' #zField
Fr0 @PushWFArc f15 '' #zField
Fr0 @GridStep f8 '' #zField
Fr0 @PushWFArc f9 '' #zField
Fr0 @PushWFArc f7 '' #zField
Fr0 @PushWFArc f2 '' #zField
Fr0 @GridStep f17 '' #zField
Fr0 @PushWFArc f12 '' #zField
Fr0 @GridStep f19 '' #zField
Fr0 @PushWFArc f18 '' #zField
Fr0 @GridStep f16 '' #zField
Fr0 @PushWFArc f13 '' #zField
Fr0 @PushWFArc f21 '' #zField
>Proto Fr0 Fr0 FindApplicationsByUser #zField
Fr0 f0 inParamDecl '<java.lang.String username> param;' #txt
Fr0 f0 inParamTable 'out.username=param.username;
' #txt
Fr0 f0 outParamDecl '<List<ch.ivy.addon.portalkit.persistence.domain.Application> applications> result;
' #txt
Fr0 f0 outParamTable 'result.applications=in.applications;
' #txt
Fr0 f0 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f0 callSignature findApplicationsByUser(String) #txt
Fr0 f0 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findApplicationsByUser(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f0 171 51 26 26 14 0 #rect
Fr0 f0 @|StartSubIcon #fIcon
Fr0 f1 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f1 171 531 26 26 14 0 #rect
Fr0 f1 @|EndSubIcon #fIcon
Fr0 f3 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f3 actionTable 'out=in;
' #txt
Fr0 f3 actionCode 'import ch.ivy.addon.portalkit.service.UserSynchronizationService;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;

IDataCacheEntry cacheData = ivy.datacache.getSessionCache().getEntry(in.username, "Applications");

if (cacheData != null) {
	List applications= cacheData.getValue() as List;
	in.applications.addAll(applications);
}

UserSynchronizationService.addUserToCacheAndUserService(in.username);
' #txt
Fr0 f3 security system #txt
Fr0 f3 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Apps in session</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f3 166 116 36 24 20 -2 #rect
Fr0 f3 @|StepIcon #fIcon
Fr0 f4 expr out #txt
Fr0 f4 184 77 184 116 #arcP
Fr0 f5 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has apps ?</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f5 170 178 28 28 14 0 #rect
Fr0 f5 @|AlternativeIcon #fIcon
Fr0 f6 expr out #txt
Fr0 f6 184 140 184 178 #arcP
Fr0 f11 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has app ?</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f11 170 434 28 28 14 0 #rect
Fr0 f11 @|AlternativeIcon #fIcon
Fr0 f15 expr in #txt
Fr0 f15 170 192 171 544 #arcP
Fr0 f15 1 104 192 #addKink
Fr0 f15 2 104 544 #addKink
Fr0 f15 1 0.49099939050933755 0 0 #arcLabel
Fr0 f8 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f8 actionTable 'out=in;
' #txt
Fr0 f8 actionCode 'ivy.datacache.getSessionCache().setEntry(in.username, "Applications", in.applications);' #txt
Fr0 f8 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save to session</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f8 254 476 36 24 20 -2 #rect
Fr0 f8 @|StepIcon #fIcon
Fr0 f9 expr in #txt
Fr0 f9 outCond !in.applications.isEmpty() #txt
Fr0 f9 194 452 254 488 #arcP
Fr0 f9 0 0.8582176589511026 0 0 #arcLabel
Fr0 f7 expr out #txt
Fr0 f7 254 499 194 537 #arcP
Fr0 f7 0 0.04737217639050585 0 0 #arcLabel
Fr0 f2 expr in #txt
Fr0 f2 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f2 184 462 184 531 #arcP
Fr0 f2 0 0.456 11 0 #arcLabel
Fr0 f17 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f17 actionTable 'out=in;
' #txt
Fr0 f17 actionCode '// add all custom application
in.applications.addAll(in.thirdPartyApplications);' #txt
Fr0 f17 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add applications</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f17 166 372 36 24 20 -2 #rect
Fr0 f17 @|StepIcon #fIcon
Fr0 f12 expr out #txt
Fr0 f12 184 396 184 434 #arcP
Fr0 f19 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f19 actionTable 'out=in;
' #txt
Fr0 f19 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;
ApplicationService applicationService = new ApplicationService();
in.thirdPartyApplications = applicationService.findAllThirdPartyApplications();' #txt
Fr0 f19 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get third party applications</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f19 166 316 36 24 20 -2 #rect
Fr0 f19 @|StepIcon #fIcon
Fr0 f18 expr out #txt
Fr0 f18 184 340 184 372 #arcP
Fr0 f16 actionDecl 'ch.ivyteam.wf.processes.FindApplicationsByUserData out;
' #txt
Fr0 f16 actionTable 'out=in;
' #txt
Fr0 f16 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.User;

import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.service.UserService;

UserService userService = new UserService();
ApplicationService applicationService = new ApplicationService();

List users = userService.findByUserName(in.username);

if(!users.isEmpty()){
	for(User user: users){
		in.applications.addAll(applicationService.findByNameAndServerId(user.applicationName,user.serverId));
	}
}' #txt
Fr0 f16 type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
Fr0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find apps in DB</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f16 166 236 36 24 20 -2 #rect
Fr0 f16 @|StepIcon #fIcon
Fr0 f13 expr out #txt
Fr0 f13 184 260 184 316 #arcP
Fr0 f21 expr in #txt
Fr0 f21 outCond in.applications.isEmpty() #txt
Fr0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fr0 f21 184 206 184 236 #arcP
Fr0 f21 0 0.0 -16 0 #arcLabel
>Proto Fr0 .type ch.ivyteam.wf.processes.FindApplicationsByUserData #txt
>Proto Fr0 .processKind CALLABLE_SUB #txt
>Proto Fr0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>FIND APPLICATIONS BY USER</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>480</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Fr0 0 0 32 24 18 0 #rect
>Proto Fr0 @|BIcon #fIcon
Fr0 f0 mainOut f4 tail #connect
Fr0 f4 head f3 mainIn #connect
Fr0 f3 mainOut f6 tail #connect
Fr0 f6 head f5 in #connect
Fr0 f15 head f1 mainIn #connect
Fr0 f11 out f9 tail #connect
Fr0 f9 head f8 mainIn #connect
Fr0 f8 mainOut f7 tail #connect
Fr0 f7 head f1 mainIn #connect
Fr0 f11 out f2 tail #connect
Fr0 f2 head f1 mainIn #connect
Fr0 f17 mainOut f12 tail #connect
Fr0 f12 head f11 in #connect
Fr0 f19 mainOut f18 tail #connect
Fr0 f18 head f17 mainIn #connect
Fr0 f16 mainOut f13 tail #connect
Fr0 f13 head f19 mainIn #connect
Fr0 f5 out f21 tail #connect
Fr0 f21 head f16 mainIn #connect
Fr0 f5 out f15 tail #connect
