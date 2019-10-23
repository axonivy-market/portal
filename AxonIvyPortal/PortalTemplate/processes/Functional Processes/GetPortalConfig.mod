[Ivy]
14BFF07A8307A43D 7.5.0 #module
>Proto >Proto Collection #zClass
Gg0 GetPortalConfig Big #zClass
Gg0 B #cInfo
Gg0 #process
Gg0 @TextInP .type .type #zField
Gg0 @TextInP .processKind .processKind #zField
Gg0 @AnnotationInP-0n ai ai #zField
Gg0 @MessageFlowInP-0n messageIn messageIn #zField
Gg0 @MessageFlowOutP-0n messageOut messageOut #zField
Gg0 @TextInP .xml .xml #zField
Gg0 @TextInP .responsibility .responsibility #zField
Gg0 @StartSub f0 '' #zField
Gg0 @EndSub f1 '' #zField
Gg0 @CallSub f5 '' #zField
Gg0 @PushWFArc f2 '' #zField
Gg0 @InfoButton f11 '' #zField
Gg0 @PushWFArc f3 '' #zField
>Proto Gg0 Gg0 GetPortalConfig #zField
Gg0 f0 inParamDecl '<> param;' #txt
Gg0 f0 outParamDecl '<ch.ivy.addon.portal.generic.PortalConfig portalConfig> result;' #txt
Gg0 f0 outParamTable 'result.portalConfig=in.portalConfig;
' #txt
Gg0 f0 callSignature call() #txt
Gg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gg0 f0 81 49 30 30 -13 17 #rect
Gg0 f0 @|StartSubIcon #fIcon
Gg0 f1 553 49 30 30 0 15 #rect
Gg0 f1 @|EndSubIcon #fIcon
Gg0 f5 processCall 'Functional Processes/CustomPortalConfig:call(ch.ivy.addon.portal.generic.PortalConfig)' #txt
Gg0 f5 requestActionDecl '<ch.ivy.addon.portal.generic.PortalConfig portalConfig> param;' #txt
Gg0 f5 requestMappingAction 'param.portalConfig=in.portalConfig;
' #txt
Gg0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.GetPortalConfigData out;
' #txt
Gg0 f5 responseMappingAction 'out=in;
out.portalConfig=result.portalConfig;
' #txt
Gg0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>apply custom settings</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gg0 f5 240 42 128 44 -60 -8 #rect
Gg0 f5 @|CallSubIcon #fIcon
Gg0 f2 expr out #txt
Gg0 f2 368 64 553 64 #arcP
Gg0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Important note:
Do not change this. Override &quot;CustomPortalConfig&quot; to change settings!</name>
        <nameStyle>16,7
69,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gg0 f11 32 154 400 44 -192 -16 #rect
Gg0 f11 @|IBIcon #fIcon
Gg0 f11 -1|-1|-65536 #nodeStyle
Gg0 f3 expr out #txt
Gg0 f3 111 64 240 64 #arcP
>Proto Gg0 .type ch.ivy.addon.portal.generic.GetPortalConfigData #txt
>Proto Gg0 .processKind CALLABLE_SUB #txt
>Proto Gg0 0 0 32 24 18 0 #rect
>Proto Gg0 @|BIcon #fIcon
Gg0 f5 mainOut f2 tail #connect
Gg0 f2 head f1 mainIn #connect
Gg0 f0 mainOut f3 tail #connect
Gg0 f3 head f5 mainIn #connect
