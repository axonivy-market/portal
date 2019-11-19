[Ivy]
14C6E04377E17A3D 3.20 #module
>Proto >Proto Collection #zClass
tt0 SynchronizeApplicationUser Big #zClass
tt0 B #cInfo
tt0 #process
tt0 @TextInP .resExport .resExport #zField
tt0 @TextInP .type .type #zField
tt0 @TextInP .processKind .processKind #zField
tt0 @AnnotationInP-0n ai ai #zField
tt0 @TextInP .xml .xml #zField
tt0 @TextInP .responsibility .responsibility #zField
tt0 @StartSub f0 '' #zField
tt0 @EndSub f1 '' #zField
tt0 @CallSub f12 '' #zField
tt0 @GridStep f16 '' #zField
tt0 @GridStep f5 '' #zField
tt0 @PushWFArc f7 '' #zField
tt0 @Trigger f11 '' #zField
tt0 @PushWFArc f8 '' #zField
tt0 @PushWFArc f2 '' #zField
tt0 @GridStep f4 '' #zField
tt0 @PushWFArc f6 '' #zField
tt0 @PushWFArc f9 '' #zField
tt0 @GridStep f10 '' #zField
tt0 @PushWFArc f13 '' #zField
tt0 @Alternative f14 '' #zField
tt0 @PushWFArc f15 '' #zField
tt0 @PushWFArc f3 '' #zField
tt0 @PushWFArc f17 '' #zField
>Proto tt0 tt0 SynchronizeApplicationUser #zField
tt0 f0 outParamDecl '<List<java.lang.String> errorMsgs,List<ch.ivy.addon.portalkit.persistence.domain.User> users> result;
' #txt
tt0 f0 outParamTable 'result.errorMsgs=in.errorMsgs;
result.users=in.users;
' #txt
tt0 f0 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f0 callSignature synchronizeApplicationUsers() #txt
tt0 f0 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronizeApplicationUsers</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f0 163 59 26 26 14 0 #rect
tt0 f0 @|StartSubIcon #fIcon
tt0 f1 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f1 163 707 26 26 14 0 #rect
tt0 f1 @|EndSubIcon #fIcon
tt0 f12 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f12 processCall MultiPortal/SecurityService:findAllUsers() #txt
tt0 f12 doCall true #txt
tt0 f12 requestActionDecl '<> param;
' #txt
tt0 f12 responseActionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f12 responseMappingAction 'out=in;
out.remoteUsers=result.users;
' #txt
tt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsers()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f12 158 252 36 24 20 -2 #rect
tt0 f12 @|CallSubIcon #fIcon
tt0 f16 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f16 actionTable 'out=in;
' #txt
tt0 f16 actionCode 'import ch.ivy.addon.portalkit.util.ApplicationUserCacheUtils;
import ch.ivy.addon.portalkit.service.UserService;

UserService userService = new UserService();
in.users = ApplicationUserCacheUtils.convertToEntity(in.remoteUsers);
in.users = userService.saveAll(in.users);
' #txt
tt0 f16 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create and save entities</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f16 158 364 36 24 20 -2 #rect
tt0 f16 @|StepIcon #fIcon
tt0 f5 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f5 actionTable 'out=in;
' #txt
tt0 f5 actionCode 'import ch.ivy.addon.portalkit.service.UserService;

UserService userService = new UserService();
userService.deleteAll(userService.findAll());' #txt
tt0 f5 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete all</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f5 158 316 36 24 20 -2 #rect
tt0 f5 @|StepIcon #fIcon
tt0 f7 expr out #txt
tt0 f7 176 276 176 316 #arcP
tt0 f11 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f11 processCall 'Business Processes/AbstractSynchronizingConfiguration:deleteByPrefixThenUpdateMany(String,List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
tt0 f11 doCall true #txt
tt0 f11 requestActionDecl '<java.lang.String propertyPrefix,List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
tt0 f11 requestMappingAction 'param.propertyPrefix=ch.ivy.addon.portalkit.persistence.variable.PropertyKey.PORTAL_PROPERTY_START + "." + ch.ivy.addon.portalkit.persistence.domain.User.class.getSimpleName() + ".";
param.businessEntities=in.users;
' #txt
tt0 f11 responseActionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f11 responseMappingAction 'out=in;
' #txt
tt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronize user to all portal server</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f11 158 652 36 24 20 -2 #rect
tt0 f11 @|TriggerIcon #fIcon
tt0 f8 expr out #txt
tt0 f8 176 676 176 707 #arcP
tt0 f2 expr out #txt
tt0 f2 176 340 176 364 #arcP
tt0 f4 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f4 actionTable 'out=in;
' #txt
tt0 f4 actionCode 'import ch.ivy.addon.portalkit.webservice.WebserviceCache;
WebserviceCache.getInstance().invalidateGetApplicationsWSCache();' #txt
tt0 f4 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>invalidate get apps cache</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f4 104 146 144 44 -69 -8 #rect
tt0 f4 @|StepIcon #fIcon
tt0 f6 expr out #txt
tt0 f6 176 85 176 146 #arcP
tt0 f9 expr out #txt
tt0 f9 176 190 176 252 #arcP
tt0 f10 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f10 actionTable 'out=in;
' #txt
tt0 f10 actionCode 'import ch.ivyteam.ivy.application.IApplication;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.support.DataCache;
import org.boon.datarepo.Repo;
import ch.ivy.addon.portalkit.persistence.dao.UserDao;

UserDao userDao = new UserDao();
Repo repo = userDao.buildRepoIndexedByUserName(in.users);
ApplicationService appService = new ApplicationService();
List<String> applicationNames = new ArrayList();
List<Application> applications = appService.findAllIvyApplications();
for (Application app : applications) {
	applicationNames.add(app.getName());
}
applicationNames.add(IApplication.PORTAL_APPLICATION_NAME);

for (String applicationName : applicationNames) {
	//invalidate cache
	DataCache.invalidateUsersCache(applicationName);
}

//insert entries to current app cache
String applicationName = ivy.wf.getApplication().getName();
DataCache.cacheAllUsers(applicationName, in.users);
DataCache.cacheUsersRepo(applicationName, repo);
' #txt
tt0 f10 security system #txt
tt0 f10 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>refresh users cache</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f10 112 450 128 44 -55 -8 #rect
tt0 f10 @|StepIcon #fIcon
tt0 f13 expr out #txt
tt0 f13 176 388 176 450 #arcP
tt0 f14 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>multi server?</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f14 160 552 32 32 -35 18 #rect
tt0 f14 @|AlternativeIcon #fIcon
tt0 f15 expr out #txt
tt0 f15 176 494 176 552 #arcP
tt0 f3 expr in #txt
tt0 f3 outCond '(new ch.ivy.addon.portalkit.service.ServerService()).findAll().size() > 1' #txt
tt0 f3 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f3 176 584 176 652 #arcP
tt0 f3 0 0.5441176470588235 15 0 #arcLabel
tt0 f17 expr in #txt
tt0 f17 160 568 163 720 #arcP
tt0 f17 1 128 568 #addKink
tt0 f17 2 128 720 #addKink
tt0 f17 1 0.5098684210526315 0 0 #arcLabel
>Proto tt0 .type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
>Proto tt0 .processKind CALLABLE_SUB #txt
>Proto tt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>SYNCHRONIZE</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>424</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto tt0 0 0 32 24 18 0 #rect
>Proto tt0 @|BIcon #fIcon
tt0 f12 mainOut f7 tail #connect
tt0 f7 head f5 mainIn #connect
tt0 f11 mainOut f8 tail #connect
tt0 f8 head f1 mainIn #connect
tt0 f5 mainOut f2 tail #connect
tt0 f2 head f16 mainIn #connect
tt0 f0 mainOut f6 tail #connect
tt0 f6 head f4 mainIn #connect
tt0 f4 mainOut f9 tail #connect
tt0 f9 head f12 mainIn #connect
tt0 f16 mainOut f13 tail #connect
tt0 f13 head f10 mainIn #connect
tt0 f10 mainOut f15 tail #connect
tt0 f15 head f14 in #connect
tt0 f14 out f3 tail #connect
tt0 f3 head f11 mainIn #connect
tt0 f14 out f17 tail #connect
tt0 f17 head f1 mainIn #connect
