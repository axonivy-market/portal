[Ivy]
[>Created: Mon Jun 05 13:32:06 ICT 2017]
15C761D21739D263 3.20 #module
>Proto >Proto Collection #zClass
Dg0 DeleteThenUpdateWSCalling Big #zClass
Dg0 B #cInfo
Dg0 #process
Dg0 @TextInP .resExport .resExport #zField
Dg0 @TextInP .type .type #zField
Dg0 @TextInP .processKind .processKind #zField
Dg0 @AnnotationInP-0n ai ai #zField
Dg0 @MessageFlowInP-0n messageIn messageIn #zField
Dg0 @MessageFlowOutP-0n messageOut messageOut #zField
Dg0 @TextInP .xml .xml #zField
Dg0 @TextInP .responsibility .responsibility #zField
Dg0 @StartSub f0 '' #zField
Dg0 @EndSub f1 '' #zField
Dg0 @GridStep f35 '' #zField
Dg0 @PushWFArc f2 '' #zField
Dg0 @CallSub f10 '' #zField
Dg0 @WSElement f4 '' #zField
Dg0 @WSElement f6 '' #zField
Dg0 @WSElement f7 '' #zField
Dg0 @Alternative f8 '' #zField
Dg0 @PushWFArc f9 '' #zField
Dg0 @PushWFArc f5 '' #zField
Dg0 @PushWFArc f11 '' #zField
Dg0 @PushWFArc f12 '' #zField
Dg0 @Alternative f13 '' #zField
Dg0 @PushWFArc f14 '' #zField
Dg0 @PushWFArc f15 '' #zField
Dg0 @PushWFArc f16 '' #zField
Dg0 @PushWFArc f17 '' #zField
Dg0 @Alternative f18 '' #zField
Dg0 @PushWFArc f19 '' #zField
Dg0 @GridStep f43 '' #zField
Dg0 @PushWFArc f20 '' #zField
Dg0 @CallSub f38 '' #zField
Dg0 @PushWFArc f21 '' #zField
Dg0 @Alternative f22 '' #zField
Dg0 @PushWFArc f23 '' #zField
Dg0 @PushWFArc f3 '' #zField
Dg0 @PushWFArc f24 '' #zField
Dg0 @ErrorBoundaryEvent f25 '' #zField
Dg0 @PushWFArc f26 '' #zField
Dg0 @ErrorBoundaryEvent f27 '' #zField
Dg0 @PushWFArc f28 '' #zField
Dg0 @ErrorBoundaryEvent f29 '' #zField
Dg0 @PushWFArc f30 '' #zField
>Proto Dg0 Dg0 DeleteThenUpdateWSCalling #zField
Dg0 f0 inParamDecl '<java.lang.String keyPrefix,ch.ivy.addon.portalkit.persistence.domain.Server server,List<ch.ivy.ws.addon.CustomPropertyPair> customPropertyPairs> param;' #txt
Dg0 f0 inParamTable 'out.authenticationType=param.server.wsAuthenticationType;
out.customPropertyPairs=param.customPropertyPairs;
out.domain=param.server.domain;
out.host=param.server.host;
out.keyPrefix=param.keyPrefix;
out.password=param.server.password;
out.serverURL=param.server.path;
out.username=param.server.username;
' #txt
Dg0 f0 outParamDecl '<> result;
' #txt
Dg0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f0 callSignature deleteByPrefixThenUpdate(String,ch.ivy.addon.portalkit.persistence.domain.Server,List<ch.ivy.ws.addon.CustomPropertyPair>) #txt
Dg0 f0 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteByPrefixThenUpdate(String,String,List&lt;CustomPropertyPair&gt;)</name>
        <nameStyle>64,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f0 273 25 30 30 -143 -36 #rect
Dg0 f0 @|StartSubIcon #fIcon
Dg0 f1 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f1 273 689 30 30 0 15 #rect
Dg0 f1 @|EndSubIcon #fIcon
Dg0 f35 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f35 actionTable 'out=in;
' #txt
Dg0 f35 actionCode 'in.retryTime = 0;' #txt
Dg0 f35 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize retry times</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f35 272 92 32 24 21 -10 #rect
Dg0 f35 @|StepIcon #fIcon
Dg0 f2 expr out #txt
Dg0 f2 288 55 288 92 #arcP
Dg0 f10 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f10 processCall SynchronizeDataProcesses/WebServiceInformation:generateWSEndPoint(String) #txt
Dg0 f10 doCall true #txt
Dg0 f10 requestActionDecl '<java.lang.String serverURL> param;
' #txt
Dg0 f10 requestMappingAction 'param.serverURL=in.serverURL;
' #txt
Dg0 f10 responseActionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f10 responseMappingAction 'out=in;
out.endpoint=result.endpoint;
' #txt
Dg0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate ws end point</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f10 272 212 32 24 20 -2 #rect
Dg0 f10 @|CallSubIcon #fIcon
Dg0 f4 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f4 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f4 actionTable 'out=in;
out.message=wsResponse.deleteByPrefixThenUpdateResponse.result.message;
out.returnedStatus=wsResponse.deleteByPrefixThenUpdateResponse.result.status;
' #txt
Dg0 f4 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Dg0 f4 timeout 0 #txt
Dg0 f4 beanConfig '"KEY_PASSWORD=<%\\=in.password%>
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=<%\\=in.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=true
KEY_USERNAME=<%\\=in.username%>
KEY_OPERATION=deleteByPrefixThenUpdate
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.deleteByPrefixThenUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"",""arg0.deleteByPrefixThenUpdate.keyPrefixToBeDeleted__@@__String__@@__in.keyPrefix"""' #txt
Dg0 f4 returningObjectList '[wsResponse]' #txt
Dg0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f4 268 412 40 24 24 -13 #rect
Dg0 f4 @|WebServiceIcon #fIcon
Dg0 f6 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f6 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f6 actionTable 'out=in;
out.message=wsResponse.deleteByPrefixThenUpdateResponse.result.message;
out.returnedStatus=wsResponse.deleteByPrefixThenUpdateResponse.result.status;
' #txt
Dg0 f6 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Dg0 f6 timeout 0 #txt
Dg0 f6 beanConfig '"KEY_PASSWORD=<%\\=in.password%>
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=true
KEY_USERNAME=<%\\=in.username%>
KEY_OPERATION=deleteByPrefixThenUpdate
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.deleteByPrefixThenUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"",""arg0.deleteByPrefixThenUpdate.keyPrefixToBeDeleted__@@__String__@@__in.keyPrefix"""' #txt
Dg0 f6 returningObjectList '[wsResponse]' #txt
Dg0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f6 420 452 40 24 26 -5 #rect
Dg0 f6 @|WebServiceIcon #fIcon
Dg0 f7 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f7 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f7 actionTable 'out=in;
out.message=wsResponse.deleteByPrefixThenUpdateResponse.result.message;
out.returnedStatus=wsResponse.deleteByPrefixThenUpdateResponse.result.status;
' #txt
Dg0 f7 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Dg0 f7 timeout 0 #txt
Dg0 f7 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=true
KEY_USERNAME=
KEY_OPERATION=deleteByPrefixThenUpdate
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.deleteByPrefixThenUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"",""arg0.deleteByPrefixThenUpdate.keyPrefixToBeDeleted__@@__String__@@__in.keyPrefix"""' #txt
Dg0 f7 returningObjectList '[wsResponse]' #txt
Dg0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default setting</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f7 572 492 40 24 32 -9 #rect
Dg0 f7 @|WebServiceIcon #fIcon
Dg0 f8 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f8 272 328 32 32 0 16 #rect
Dg0 f8 @|AlternativeIcon #fIcon
Dg0 f9 expr out #txt
Dg0 f9 288 236 288 328 #arcP
Dg0 f5 expr in #txt
Dg0 f5 outCond 'in.authenticationType == ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM' #txt
Dg0 f5 288 360 288 412 #arcP
Dg0 f11 expr in #txt
Dg0 f11 outCond 'in.authenticationType == ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC' #txt
Dg0 f11 304 344 440 452 #arcP
Dg0 f11 1 440 344 #addKink
Dg0 f11 0 0.9190540604480659 0 0 #arcLabel
Dg0 f12 expr in #txt
Dg0 f12 304 344 592 492 #arcP
Dg0 f12 1 592 344 #addKink
Dg0 f12 0 0.7663242804436811 0 0 #arcLabel
Dg0 f13 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronize successful?</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f13 272 584 32 32 16 9 #rect
Dg0 f13 @|AlternativeIcon #fIcon
Dg0 f14 expr out #txt
Dg0 f14 288 436 288 584 #arcP
Dg0 f15 expr out #txt
Dg0 f15 440 476 304 600 #arcP
Dg0 f15 1 440 600 #addKink
Dg0 f15 0 0.8728876331827182 0 0 #arcLabel
Dg0 f16 expr out #txt
Dg0 f16 592 516 304 600 #arcP
Dg0 f16 1 592 600 #addKink
Dg0 f16 1 0.30089286160148043 0 0 #arcLabel
Dg0 f17 expr in #txt
Dg0 f17 outCond 'in.returnedStatus == ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL' #txt
Dg0 f17 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f17 288 616 288 689 #arcP
Dg0 f17 0 0.5205479452054794 -16 0 #arcLabel
Dg0 f18 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f18 136 328 32 32 0 16 #rect
Dg0 f18 @|AlternativeIcon #fIcon
Dg0 f19 expr in #txt
Dg0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f19 272 600 152 360 #arcP
Dg0 f19 1 152 600 #addKink
Dg0 f19 0 0.6 0 -14 #arcLabel
Dg0 f43 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f43 actionTable 'out=in;
' #txt
Dg0 f43 actionCode in.retryTime++; #txt
Dg0 f43 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>increase retry times</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f43 136 244 32 24 -135 -2 #rect
Dg0 f43 @|StepIcon #fIcon
Dg0 f20 expr in #txt
Dg0 f20 152 328 152 268 #arcP
Dg0 f38 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f38 processCall SynchronizeDataProcesses/ErrorHandling:handleWebServiceException(String,Integer,String) #txt
Dg0 f38 doCall true #txt
Dg0 f38 requestActionDecl '<java.lang.String action,java.lang.Integer retryTime,java.lang.String message> param;
' #txt
Dg0 f38 requestMappingAction 'param.action="Delete By Prefix";
param.retryTime=in.retryTime;
param.message=in.message;
' #txt
Dg0 f38 responseActionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f38 responseMappingAction 'out=in;
' #txt
Dg0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle 
webservice error</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dg0 f38 134 180 36 24 -118 -18 #rect
Dg0 f38 @|CallSubIcon #fIcon
Dg0 f21 expr out #txt
Dg0 f21 152 244 152 204 #arcP
Dg0 f22 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f22 272 136 32 32 0 16 #rect
Dg0 f22 @|AlternativeIcon #fIcon
Dg0 f23 expr out #txt
Dg0 f23 288 116 288 136 #arcP
Dg0 f3 expr in #txt
Dg0 f3 288 168 288 212 #arcP
Dg0 f24 expr out #txt
Dg0 f24 152 180 272 152 #arcP
Dg0 f24 1 152 152 #addKink
Dg0 f24 1 0.22880392010988027 0 0 #arcLabel
Dg0 f25 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f25 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Dg0 f25 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f25 attachedToRef 15C761D21739D263-f4 #txt
Dg0 f25 256 432 24 24 0 15 #rect
Dg0 f25 @|ErrorBoundaryEventIcon #fIcon
Dg0 f26 256 444 152 360 #arcP
Dg0 f26 1 152 444 #addKink
Dg0 f26 0 0.6147918863734592 0 0 #arcLabel
Dg0 f27 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f27 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Dg0 f27 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f27 attachedToRef 15C761D21739D263-f6 #txt
Dg0 f27 408 472 24 24 0 15 #rect
Dg0 f27 @|ErrorBoundaryEventIcon #fIcon
Dg0 f28 408 484 152 360 #arcP
Dg0 f28 1 152 484 #addKink
Dg0 f28 0 0.547659782428802 0 0 #arcLabel
Dg0 f29 actionDecl 'ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData out;
' #txt
Dg0 f29 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Dg0 f29 type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
Dg0 f29 attachedToRef 15C761D21739D263-f7 #txt
Dg0 f29 560 512 24 24 0 15 #rect
Dg0 f29 @|ErrorBoundaryEventIcon #fIcon
Dg0 f30 560 524 152 360 #arcP
Dg0 f30 1 152 524 #addKink
Dg0 f30 1 0.3203125 0 0 #arcLabel
>Proto Dg0 .type ch.ivy.add.portalkit.synchronization.DeleteThenUpdateWSCallingData #txt
>Proto Dg0 .processKind CALLABLE_SUB #txt
>Proto Dg0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Dg0 0 0 32 24 18 0 #rect
>Proto Dg0 @|BIcon #fIcon
Dg0 f0 mainOut f2 tail #connect
Dg0 f2 head f35 mainIn #connect
Dg0 f10 mainOut f9 tail #connect
Dg0 f9 head f8 in #connect
Dg0 f8 out f5 tail #connect
Dg0 f5 head f4 mainIn #connect
Dg0 f8 out f11 tail #connect
Dg0 f11 head f6 mainIn #connect
Dg0 f8 out f12 tail #connect
Dg0 f12 head f7 mainIn #connect
Dg0 f4 mainOut f14 tail #connect
Dg0 f14 head f13 in #connect
Dg0 f6 mainOut f15 tail #connect
Dg0 f15 head f13 in #connect
Dg0 f7 mainOut f16 tail #connect
Dg0 f16 head f13 in #connect
Dg0 f13 out f17 tail #connect
Dg0 f17 head f1 mainIn #connect
Dg0 f13 out f19 tail #connect
Dg0 f19 head f18 in #connect
Dg0 f18 out f20 tail #connect
Dg0 f20 head f43 mainIn #connect
Dg0 f43 mainOut f21 tail #connect
Dg0 f21 head f38 mainIn #connect
Dg0 f35 mainOut f23 tail #connect
Dg0 f23 head f22 in #connect
Dg0 f22 out f3 tail #connect
Dg0 f3 head f10 mainIn #connect
Dg0 f38 mainOut f24 tail #connect
Dg0 f24 head f22 in #connect
Dg0 f25 mainOut f26 tail #connect
Dg0 f26 head f18 in #connect
Dg0 f27 mainOut f28 tail #connect
Dg0 f28 head f18 in #connect
Dg0 f29 mainOut f30 tail #connect
Dg0 f30 head f18 in #connect
