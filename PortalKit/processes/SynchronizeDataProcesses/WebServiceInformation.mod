[Ivy]
[>Created: Wed Oct 07 18:37:18 ICT 2015]
150420F4BCAFA55F 3.17 #module
>Proto >Proto Collection #zClass
Wn0 WebServiceInformation Big #zClass
Wn0 B #cInfo
Wn0 #process
Wn0 @TextInP .resExport .resExport #zField
Wn0 @TextInP .type .type #zField
Wn0 @TextInP .processKind .processKind #zField
Wn0 @AnnotationInP-0n ai ai #zField
Wn0 @MessageFlowInP-0n messageIn messageIn #zField
Wn0 @MessageFlowOutP-0n messageOut messageOut #zField
Wn0 @TextInP .xml .xml #zField
Wn0 @TextInP .responsibility .responsibility #zField
Wn0 @StartSub f0 '' #zField
Wn0 @EndSub f1 '' #zField
Wn0 @GridStep f30 '' #zField
Wn0 @PushWFArc f5 '' #zField
Wn0 @PushWFArc f4 '' #zField
>Proto Wn0 Wn0 WebServiceInformation #zField
Wn0 f0 inParamDecl '<java.lang.String serverURL> param;' #txt
Wn0 f0 inParamTable 'out.serverURL=param.serverURL;
' #txt
Wn0 f0 outParamDecl '<java.lang.String endpoint> result;
' #txt
Wn0 f0 outParamTable 'result.endpoint=in.endpoint;
' #txt
Wn0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.WebServiceInformationData out;
' #txt
Wn0 f0 callSignature generateWSEndPoint(String) #txt
Wn0 f0 type ch.ivy.add.portalkit.synchronization.WebServiceInformationData #txt
Wn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generateWSEndPoint(String)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Wn0 f0 51 83 26 26 14 0 #rect
Wn0 f0 @|StartSubIcon #fIcon
Wn0 f1 type ch.ivy.add.portalkit.synchronization.WebServiceInformationData #txt
Wn0 f1 51 339 26 26 14 0 #rect
Wn0 f1 @|EndSubIcon #fIcon
Wn0 f30 actionDecl 'ch.ivy.add.portalkit.synchronization.WebServiceInformationData out;
' #txt
Wn0 f30 actionTable 'out=in;
' #txt
Wn0 f30 actionCode 'import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

in.endpoint = in.serverURL + "/" + WebServiceEndPoint.PORTAL_DATA.wsProcessId();' #txt
Wn0 f30 type ch.ivy.add.portalkit.synchronization.WebServiceInformationData #txt
Wn0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate 
webservice endpoint</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Wn0 f30 46 196 36 24 20 -2 #rect
Wn0 f30 @|StepIcon #fIcon
Wn0 f5 expr out #txt
Wn0 f5 64 109 64 196 #arcP
Wn0 f4 expr out #txt
Wn0 f4 64 220 64 339 #arcP
>Proto Wn0 .type ch.ivy.add.portalkit.synchronization.WebServiceInformationData #txt
>Proto Wn0 .processKind CALLABLE_SUB #txt
>Proto Wn0 0 0 32 24 18 0 #rect
>Proto Wn0 @|BIcon #fIcon
Wn0 f0 mainOut f5 tail #connect
Wn0 f5 head f30 mainIn #connect
Wn0 f30 mainOut f4 tail #connect
Wn0 f4 head f1 mainIn #connect
