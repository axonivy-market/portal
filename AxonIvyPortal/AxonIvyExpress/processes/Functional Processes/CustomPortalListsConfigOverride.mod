[Ivy]
[>Created: Thu Mar 16 12:40:32 CET 2017]
15AD6B506857EAF8 3.18 #module
>Proto >Proto Collection #zClass
Cg0 CustomPortalListsConfig Big #zClass
Cg0 B #cInfo
Cg0 #process
Cg0 @TextInP .resExport .resExport #zField
Cg0 @TextInP .type .type #zField
Cg0 @TextInP .processKind .processKind #zField
Cg0 @AnnotationInP-0n ai ai #zField
Cg0 @MessageFlowInP-0n messageIn messageIn #zField
Cg0 @MessageFlowOutP-0n messageOut messageOut #zField
Cg0 @TextInP .xml .xml #zField
Cg0 @TextInP .responsibility .responsibility #zField
Cg0 @StartSub f0 '' #zField
Cg0 @EndSub f1 '' #zField
Cg0 @InfoButton f3 '' #zField
Cg0 @GridStep f4 '' #zField
Cg0 @PushWFArc f5 '' #zField
Cg0 @PushWFArc f2 '' #zField
>Proto Cg0 Cg0 CustomPortalListsConfig #zField
Cg0 f0 inParamDecl '<> param;' #txt
Cg0 f0 outParamDecl '<ch.ivy.addon.portal.generic.PortalListsConfig portalListsConfig> result;
' #txt
Cg0 f0 outParamTable 'result.portalListsConfig=in.portalListsConfig;
' #txt
Cg0 f0 actionDecl 'gawfs.CustomPortalListsConfigOverrideData out;
' #txt
Cg0 f0 callSignature call() #txt
Cg0 f0 type gawfs.CustomPortalListsConfigOverrideData #txt
Cg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f0 51 115 26 26 14 0 #rect
Cg0 f0 @|StartSubIcon #fIcon
Cg0 f1 type gawfs.CustomPortalListsConfigOverrideData #txt
Cg0 f1 371 115 26 26 14 0 #rect
Cg0 f1 @|EndSubIcon #fIcon
Cg0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Subprocess for configuration of Task and Case List 
customVarcharFields columns. Do not set it here,
use override of this subprocess in your project instead.</name>
        <nameStyle>101,7
56,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f3 80 178 304 60 -149 -24 #rect
Cg0 f3 @|IBIcon #fIcon
Cg0 f3 -1|-1|-65536 #nodeStyle
Cg0 f4 actionDecl 'gawfs.CustomPortalListsConfigOverrideData out;
' #txt
Cg0 f4 actionTable 'out=in;
' #txt
Cg0 f4 actionCode 'in.portalListsConfig.caseDetailProcessStartID = "158AD79B1D9233EC-f0";' #txt
Cg0 f4 type gawfs.CustomPortalListsConfigOverrideData #txt
Cg0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>case detail process start id settings</name>
        <nameStyle>37,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f4 206 116 36 24 -87 -41 #rect
Cg0 f4 @|StepIcon #fIcon
Cg0 f5 expr out #txt
Cg0 f5 77 128 206 128 #arcP
Cg0 f2 expr out #txt
Cg0 f2 242 128 371 128 #arcP
>Proto Cg0 .type gawfs.CustomPortalListsConfigOverrideData #txt
>Proto Cg0 .processKind CALLABLE_SUB #txt
>Proto Cg0 0 0 32 24 18 0 #rect
>Proto Cg0 @|BIcon #fIcon
Cg0 f0 mainOut f5 tail #connect
Cg0 f5 head f4 mainIn #connect
Cg0 f4 mainOut f2 tail #connect
Cg0 f2 head f1 mainIn #connect
