[Ivy]
166D409FCC5FB4BD 3.28 #module
>Proto >Proto Collection #zClass
Mu0 MobileDefaultPage Big #zClass
Mu0 B #cInfo
Mu0 #process
Mu0 @TextInP .type .type #zField
Mu0 @TextInP .processKind .processKind #zField
Mu0 @AnnotationInP-0n ai ai #zField
Mu0 @MessageFlowInP-0n messageIn messageIn #zField
Mu0 @MessageFlowOutP-0n messageOut messageOut #zField
Mu0 @TextInP .xml .xml #zField
Mu0 @TextInP .responsibility .responsibility #zField
Mu0 @StartSub f0 '' #zField
Mu0 @EndSub f1 '' #zField
Mu0 @GridStep f3 '' #zField
Mu0 @PushWFArc f4 '' #zField
Mu0 @PushWFArc f2 '' #zField
Mu0 @InfoButton f5 '' #zField
Mu0 @AnnotationArc f6 '' #zField
>Proto Mu0 Mu0 MobileDefaultPage #zField
Mu0 f0 inParamDecl '<> param;' #txt
Mu0 f0 outParamDecl '<ch.ivy.addon.portalkit.enums.MenuKind page> result;
' #txt
Mu0 f0 outParamTable 'result.page=in.page;
' #txt
Mu0 f0 callSignature call() #txt
Mu0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Mu0 f0 81 49 30 30 -13 17 #rect
Mu0 f0 @|StartSubIcon #fIcon
Mu0 f1 337 49 30 30 0 15 #rect
Mu0 f1 @|EndSubIcon #fIcon
Mu0 f3 actionTable 'out=in;
' #txt
Mu0 f3 actionCode 'out.page = ch.ivy.addon.portalkit.enums.MenuKind.TASK;' #txt
Mu0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default page</name>
    </language>
</elementInfo>
' #txt
Mu0 f3 168 42 112 44 -44 -8 #rect
Mu0 f3 @|StepIcon #fIcon
Mu0 f4 expr out #txt
Mu0 f4 111 64 168 64 #arcP
Mu0 f2 expr out #txt
Mu0 f2 280 64 337 64 #arcP
Mu0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change to PROCESS if you want to set the default page to the processes page</name>
        <nameStyle>75
</nameStyle>
    </language>
</elementInfo>
' #txt
Mu0 f5 72 129 448 30 -214 -8 #rect
Mu0 f5 @|IBIcon #fIcon
Mu0 f6 296 129 224 86 #arcP
>Proto Mu0 .type ch.ivy.add.portalkit.MobileDefaultPageData #txt
>Proto Mu0 .processKind CALLABLE_SUB #txt
>Proto Mu0 0 0 32 24 18 0 #rect
>Proto Mu0 @|BIcon #fIcon
Mu0 f0 mainOut f4 tail #connect
Mu0 f4 head f3 mainIn #connect
Mu0 f3 mainOut f2 tail #connect
Mu0 f2 head f1 mainIn #connect
Mu0 f5 ao f6 tail #connect
Mu0 f6 head f3 @CG|ai #connect
