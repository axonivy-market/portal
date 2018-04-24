[Ivy]
162F58D930D1BB6D 3.23 #module
>Proto >Proto Collection #zClass
Hk0 HookLogout Big #zClass
Hk0 B #cInfo
Hk0 #process
Hk0 @TextInP .resExport .resExport #zField
Hk0 @TextInP .type .type #zField
Hk0 @TextInP .processKind .processKind #zField
Hk0 @AnnotationInP-0n ai ai #zField
Hk0 @MessageFlowInP-0n messageIn messageIn #zField
Hk0 @MessageFlowOutP-0n messageOut messageOut #zField
Hk0 @TextInP .xml .xml #zField
Hk0 @TextInP .responsibility .responsibility #zField
Hk0 @StartSub f0 '' #zField
Hk0 @EndSub f1 '' #zField
Hk0 @InfoButton f3 '' #zField
Hk0 @GridStep f4 '' #zField
Hk0 @PushWFArc f5 '' #zField
Hk0 @PushWFArc f2 '' #zField
>Proto Hk0 Hk0 HookLogout #zField
Hk0 f0 inParamDecl '<> param;' #txt
Hk0 f0 outParamDecl '<> result;
' #txt
Hk0 f0 actionDecl '_ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData out;
' #txt
Hk0 f0 callSignature call() #txt
Hk0 f0 type _ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData #txt
Hk0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Hk0 f0 81 49 30 30 -13 17 #rect
Hk0 f0 @|StartSubIcon #fIcon
Hk0 f1 type _ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData #txt
Hk0 f1 537 49 30 30 0 15 #rect
Hk0 f1 @|EndSubIcon #fIcon
Hk0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add your additional steps here
It will be processed right before user log out
This example will add 1 record to db before user log out, please go to Persistence to generate DB before you run this sample</name>
        <nameStyle>202
</nameStyle>
    </language>
</elementInfo>
' #txt
Hk0 f3 88 202 688 60 -340 -24 #rect
Hk0 f3 @|IBIcon #fIcon
Hk0 f4 actionDecl '_ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData out;
' #txt
Hk0 f4 actionTable 'out=in;
' #txt
Hk0 f4 actionCode 'import ch.ivyteam.ivy.project.portal.examples.HistoryTrace;
String loginUser = ivy.session.getSessionUserName();

HistoryTrace trace = new HistoryTrace();
trace.setUserName(loginUser);
trace.setLastVisit(new java.util.Date());
ivy.persistence.PersistenceSample.persist(trace);
' #txt
Hk0 f4 type _ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData #txt
Hk0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add 1 record to db before user log out</name>
    </language>
</elementInfo>
' #txt
Hk0 f4 184 42 224 44 -102 -8 #rect
Hk0 f4 @|StepIcon #fIcon
Hk0 f5 expr out #txt
Hk0 f5 111 64 184 64 #arcP
Hk0 f2 expr out #txt
Hk0 f2 408 64 537 64 #arcP
>Proto Hk0 .type _ch.ivyteam.ivy.project.portal.examples.HookLogoutOverrideData #txt
>Proto Hk0 .processKind CALLABLE_SUB #txt
>Proto Hk0 0 0 32 24 18 0 #rect
>Proto Hk0 @|BIcon #fIcon
Hk0 f0 mainOut f5 tail #connect
Hk0 f5 head f4 mainIn #connect
Hk0 f4 mainOut f2 tail #connect
Hk0 f2 head f1 mainIn #connect
