[Ivy]
14C7834636E63BEA 7.5.0 #module
>Proto >Proto Collection #zClass
Fr0 FindApplicationsByUser Big #zClass
Fr0 B #cInfo
Fr0 #process
Fr0 @TextInP .type .type #zField
Fr0 @TextInP .processKind .processKind #zField
Fr0 @AnnotationInP-0n ai ai #zField
Fr0 @TextInP .xml .xml #zField
Fr0 @TextInP .responsibility .responsibility #zField
Fr0 @StartSub f0 '' #zField
Fr0 @EndSub f1 '' #zField
Fr0 @GridStep f3 '' #zField
Fr0 @PushWFArc f4 '' #zField
Fr0 @PushWFArc f2 '' #zField
>Proto Fr0 Fr0 FindApplicationsByUser #zField
Fr0 f0 inParamDecl '<String username> param;' #txt
Fr0 f0 inParamTable 'out.username=param.username;
' #txt
Fr0 f0 outParamDecl '<List<ch.ivy.addon.portalkit.persistence.domain.Application> applications> result;' #txt
Fr0 f0 outParamTable 'result.applications=in.applications;
' #txt
Fr0 f0 callSignature findApplicationsByUser(String) #txt
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
Fr0 f1 171 243 26 26 14 0 #rect
Fr0 f1 @|EndSubIcon #fIcon
Fr0 f3 actionTable 'out=in;
' #txt
Fr0 f3 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
in.applications = service.findApplicationByUser(in.username);' #txt
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
Fr0 f2 expr out #txt
Fr0 f2 184 140 184 243 #arcP
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
Fr0 f3 mainOut f2 tail #connect
Fr0 f2 head f1 mainIn #connect
