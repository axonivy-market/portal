[Ivy]
17645474439A53D8 9.2.0 #module
>Proto >Proto Collection #zClass
Cm0 CheckIvySecuritySystem Big #zClass
Cm0 B #cInfo
Cm0 #process
Cm0 @TextInP .type .type #zField
Cm0 @TextInP .processKind .processKind #zField
Cm0 @TextInP .xml .xml #zField
Cm0 @TextInP .responsibility .responsibility #zField
Cm0 @StartSub f0 '' #zField
Cm0 @EndSub f1 '' #zField
Cm0 @GridStep f112 '' #zField
Cm0 @PushWFArc f3 '' #zField
Cm0 @PushWFArc f2 '' #zField
>Proto Cm0 Cm0 CheckIvySecuritySystem #zField
Cm0 f0 inParamDecl '<> param;' #txt
Cm0 f0 outParamDecl '<Boolean isIvySecuritySystem> result;' #txt
Cm0 f0 outParamTable 'result.isIvySecuritySystem=in.isIvySecuritySystem;
' #txt
Cm0 f0 callSignature call() #txt
Cm0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Cm0 f0 81 49 30 30 -13 17 #rect
Cm0 f0 @|StartSubIcon #fIcon
Cm0 f1 465 49 30 30 0 15 #rect
Cm0 f1 @|EndSubIcon #fIcon
Cm0 f112 actionTable 'out=in;
' #txt
Cm0 f112 actionCode 'import ch.ivy.addon.portalkit.util.SecuritySystemUtils;
in.isIvySecuritySystem = SecuritySystemUtils.isIvySecuritySystem();' #txt
Cm0 f112 security system #txt
Cm0 f112 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check Ivy Security System</name>
    </language>
</elementInfo>
' #txt
Cm0 f112 216 42 144 44 -69 -8 #rect
Cm0 f112 @|StepIcon #fIcon
Cm0 f3 111 64 216 64 #arcP
Cm0 f2 360 64 465 64 #arcP
>Proto Cm0 .type portalTemplate.CheckIvySecuritySystemData #txt
>Proto Cm0 .processKind CALLABLE_SUB #txt
>Proto Cm0 0 0 32 24 18 0 #rect
>Proto Cm0 @|BIcon #fIcon
Cm0 f0 mainOut f3 tail #connect
Cm0 f3 head f112 mainIn #connect
Cm0 f112 mainOut f2 tail #connect
Cm0 f2 head f1 mainIn #connect
