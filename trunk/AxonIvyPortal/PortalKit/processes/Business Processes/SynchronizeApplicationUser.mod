[Ivy]
[>Created: Mon Dec 28 11:41:13 ICT 2015]
14C6E04377E17A3D 3.18 #module
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
tt0 @PushWFArc f13 '' #zField
tt0 @GridStep f16 '' #zField
tt0 @Alternative f3 '' #zField
tt0 @PushWFArc f2 '' #zField
tt0 @GridStep f6 '' #zField
tt0 @PushWFArc f9 '' #zField
tt0 @GridStep f5 '' #zField
tt0 @PushWFArc f7 '' #zField
tt0 @PushWFArc f10 '' #zField
tt0 @Trigger f11 '' #zField
tt0 @PushWFArc f14 '' #zField
tt0 @PushWFArc f8 '' #zField
tt0 @CallSub f31 '' #zField
tt0 @PushWFArc f18 '' #zField
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
tt0 f1 163 571 26 26 14 0 #rect
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
tt0 f12 158 124 36 24 20 -2 #rect
tt0 f12 @|CallSubIcon #fIcon
tt0 f13 expr out #txt
tt0 f13 176 85 176 124 #arcP
tt0 f16 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f16 actionTable 'out=in;
' #txt
tt0 f16 actionCode 'import ch.ivy.addon.portalkit.util.ApplicationUserCacheUtils;

in.users = ApplicationUserCacheUtils.convertToEntity(in.remoteUsers);' #txt
tt0 f16 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create entities</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f16 158 380 36 24 20 -2 #rect
tt0 f16 @|StepIcon #fIcon
tt0 f3 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has Users ?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f3 162 314 28 28 14 0 #rect
tt0 f3 @|AlternativeIcon #fIcon
tt0 f2 expr in #txt
tt0 f2 176 342 176 380 #arcP
tt0 f6 actionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f6 actionTable 'out=in;
' #txt
tt0 f6 actionCode 'import ch.ivy.addon.portalkit.service.UserService;

UserService userService = new UserService();
in.users = userService.saveAll(in.users);' #txt
tt0 f6 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save all</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f6 158 444 36 24 20 -2 #rect
tt0 f6 @|StepIcon #fIcon
tt0 f9 expr out #txt
tt0 f9 176 404 176 444 #arcP
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
tt0 f5 158 188 36 24 20 -2 #rect
tt0 f5 @|StepIcon #fIcon
tt0 f7 expr out #txt
tt0 f7 176 148 176 188 #arcP
tt0 f10 expr in #txt
tt0 f10 outCond in.remoteUsers.isEmpty() #txt
tt0 f10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f10 162 328 163 584 #arcP
tt0 f10 1 64 328 #addKink
tt0 f10 2 64 584 #addKink
tt0 f10 0 0.9038491192769225 1 -7 #arcLabel
tt0 f11 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f11 processCall 'Business Processes/AbstractSynchronizingConfiguration:addOrUpdateMany(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
tt0 f11 doCall true #txt
tt0 f11 requestActionDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
tt0 f11 requestMappingAction 'param.businessEntities=in.users;
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
tt0 f11 158 508 36 24 20 -2 #rect
tt0 f11 @|TriggerIcon #fIcon
tt0 f14 expr out #txt
tt0 f14 176 468 176 508 #arcP
tt0 f8 expr out #txt
tt0 f8 176 532 176 571 #arcP
tt0 f31 type ch.ivyteam.wf.processes.SynchronizeApplicationUserData #txt
tt0 f31 processCall SynchronizeDataProcesses/Synchronizing:deleteByPrefix(String) #txt
tt0 f31 doCall true #txt
tt0 f31 requestActionDecl '<java.lang.String keyPrefixToBeDeleted> param;
' #txt
tt0 f31 requestMappingAction 'param.keyPrefixToBeDeleted=ch.ivy.addon.portalkit.persistence.variable.PropertyKey.PORTAL_PROPERTY_START + "." + ch.ivy.addon.portalkit.persistence.domain.User.class.getSimpleName() + ".";
' #txt
tt0 f31 responseActionDecl 'ch.ivyteam.wf.processes.SynchronizeApplicationUserData out;
' #txt
tt0 f31 responseMappingAction 'out=in;
' #txt
tt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete UserCache data 
on all Portal Servers</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
tt0 f31 158 252 36 24 19 -2 #rect
tt0 f31 @|CallSubIcon #fIcon
tt0 f18 expr out #txt
tt0 f18 176 212 176 252 #arcP
tt0 f17 expr out #txt
tt0 f17 176 276 176 314 #arcP
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
tt0 f0 mainOut f13 tail #connect
tt0 f13 head f12 mainIn #connect
tt0 f2 head f16 mainIn #connect
tt0 f16 mainOut f9 tail #connect
tt0 f9 head f6 mainIn #connect
tt0 f12 mainOut f7 tail #connect
tt0 f7 head f5 mainIn #connect
tt0 f3 out f10 tail #connect
tt0 f10 head f1 mainIn #connect
tt0 f3 out f2 tail #connect
tt0 f6 mainOut f14 tail #connect
tt0 f14 head f11 mainIn #connect
tt0 f11 mainOut f8 tail #connect
tt0 f8 head f1 mainIn #connect
tt0 f5 mainOut f18 tail #connect
tt0 f18 head f31 mainIn #connect
tt0 f31 mainOut f17 tail #connect
tt0 f17 head f3 in #connect
