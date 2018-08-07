[Ivy]
[>Created: Tue Jun 14 16:11:11 ICT 2016]
150CB8D0B02F26AA 3.18 #module
>Proto >Proto Collection #zClass
Ag0 AddOrUpdateWSCalling Big #zClass
Ag0 B #cInfo
Ag0 #process
Ag0 @TextInP .resExport .resExport #zField
Ag0 @TextInP .type .type #zField
Ag0 @TextInP .processKind .processKind #zField
Ag0 @AnnotationInP-0n ai ai #zField
Ag0 @MessageFlowInP-0n messageIn messageIn #zField
Ag0 @MessageFlowOutP-0n messageOut messageOut #zField
Ag0 @TextInP .xml .xml #zField
Ag0 @TextInP .responsibility .responsibility #zField
Ag0 @StartSub f0 '' #zField
Ag0 @EndSub f1 '' #zField
Ag0 @Alternative f20 '' #zField
Ag0 @Alternative f13 '' #zField
Ag0 @Alternative f4 '' #zField
Ag0 @Alternative f6 '' #zField
Ag0 @GridStep f22 '' #zField
Ag0 @WSElement f17 '' #zField
Ag0 @CallSub f8 '' #zField
Ag0 @WSElement f15 '' #zField
Ag0 @CallSub f11 '' #zField
Ag0 @GridStep f24 '' #zField
Ag0 @PushWFArc f12 '' #zField
Ag0 @PushWFArc f3 '' #zField
Ag0 @PushWFArc f18 '' #zField
Ag0 @PushWFArc f9 '' #zField
Ag0 @PushWFArc f14 '' #zField
Ag0 @PushWFArc f10 '' #zField
Ag0 @PushWFArc f5 '' #zField
Ag0 @PushWFArc f26 '' #zField
Ag0 @PushWFArc f27 '' #zField
Ag0 @PushWFArc f19 '' #zField
Ag0 @PushWFArc f7 '' #zField
Ag0 @ErrorBoundaryEvent Et1 ErrorBoundaryEvent #zField
Ag0 @ErrorBoundaryEvent Et0 ErrorBoundaryEvent #zField
Ag0 @PushWFArc f16 '' #zField
Ag0 @PushWFArc f2 '' #zField
Ag0 @PushWFArc f23 '' #zField
Ag0 @WSElement f25 '' #zField
Ag0 @PushWFArc f28 '' #zField
Ag0 @PushWFArc f21 '' #zField
Ag0 @ErrorBoundaryEvent Et2 ErrorBoundaryEvent #zField
Ag0 @PushWFArc f29 '' #zField
Ag0 @PushWFArc f30 '' #zField
>Proto Ag0 Ag0 AddOrUpdateWSCalling #zField
Ag0 f0 inParamDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customerPropertyPairs,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ag0 f0 inParamTable 'out.authenticationType=param.server.wsAuthenticationType;
out.customPropertyPairs=param.customerPropertyPairs;
out.domain=param.server.domain;
out.host=param.server.host;
out.password=param.server.password;
out.serverURL=param.server.path;
out.username=param.server.username;
' #txt
Ag0 f0 outParamDecl '<> result;
' #txt
Ag0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f0 callSignature addOrUpdate(List<ch.ivy.ws.addon.CustomPropertyPair>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ag0 f0 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdate(List&lt;CustomPropertyPair&gt;,Server)</name>
    </language>
</elementInfo>
' #txt
Ag0 f0 403 83 26 26 14 0 #rect
Ag0 f0 @|StartSubIcon #fIcon
Ag0 f1 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f1 403 747 26 26 14 0 #rect
Ag0 f1 @|EndSubIcon #fIcon
Ag0 f20 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f20 146 402 28 28 14 0 #rect
Ag0 f20 @|AlternativeIcon #fIcon
Ag0 f13 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f13 402 210 28 28 14 0 #rect
Ag0 f13 @|AlternativeIcon #fIcon
Ag0 f4 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sychronize successful?</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f4 402 658 28 28 14 0 #rect
Ag0 f4 @|AlternativeIcon #fIcon
Ag0 f6 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f6 402 370 28 28 14 -22 #rect
Ag0 f6 @|AlternativeIcon #fIcon
Ag0 f22 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f22 actionTable 'out=in;
' #txt
Ag0 f22 actionCode in.retryTime++; #txt
Ag0 f22 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>increase retry times</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f22 142 340 36 24 -125 17 #rect
Ag0 f22 @|StepIcon #fIcon
Ag0 f17 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f17 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f17 actionTable 'out=in;
out.message=wsResponse.addOrUpdateResponse.result.message;
out.returnedStatus=wsResponse.addOrUpdateResponse.result.status;
' #txt
Ag0 f17 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ag0 f17 timeout 0 #txt
Ag0 f17 beanConfig '"KEY_PASSWORD=<%\\=in.password%>
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=<%\\=in.domain%>
KEY_USERNAME=<%\\=in.username%>
KEY_OPERATION=addOrUpdate
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.addOrUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"""' #txt
Ag0 f17 returningObjectList '[wsResponse]' #txt
Ag0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f17 398 436 36 24 21 -3 #rect
Ag0 f17 @|WebServiceIcon #fIcon
Ag0 f8 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f8 processCall SynchronizeDataProcesses/ErrorHandling:handleWebServiceException(String,Integer,String) #txt
Ag0 f8 doCall true #txt
Ag0 f8 requestActionDecl '<java.lang.String action,java.lang.Integer retryTime,java.lang.String message> param;
' #txt
Ag0 f8 requestMappingAction 'param.action="Add or Update";
param.retryTime=in.retryTime;
param.message=in.message;
' #txt
Ag0 f8 responseActionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f8 responseMappingAction 'out=in;
' #txt
Ag0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle 
webservice error</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f8 142 276 36 24 -109 1 #rect
Ag0 f8 @|CallSubIcon #fIcon
Ag0 f15 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f15 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f15 actionTable 'out=in;
out.message=wsResponse.addOrUpdateResponse.result.message;
out.returnedStatus=wsResponse.addOrUpdateResponse.result.status;
' #txt
Ag0 f15 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ag0 f15 timeout 0 #txt
Ag0 f15 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=addOrUpdate
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.addOrUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"""' #txt
Ag0 f15 returningObjectList '[wsResponse]' #txt
Ag0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default setting</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f15 718 564 36 24 20 -2 #rect
Ag0 f15 @|WebServiceIcon #fIcon
Ag0 f11 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f11 processCall SynchronizeDataProcesses/WebServiceInformation:generateWSEndPoint(String) #txt
Ag0 f11 doCall true #txt
Ag0 f11 requestActionDecl '<java.lang.String serverURL> param;
' #txt
Ag0 f11 requestMappingAction 'param.serverURL=in.serverURL;
' #txt
Ag0 f11 responseActionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f11 responseMappingAction 'out=in;
out.endpoint=result.endpoint;
' #txt
Ag0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate ws end point</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f11 398 276 36 24 20 -2 #rect
Ag0 f11 @|CallSubIcon #fIcon
Ag0 f24 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f24 actionTable 'out=in;
' #txt
Ag0 f24 actionCode 'in.retryTime = 0;' #txt
Ag0 f24 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize retry times</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f24 398 148 36 24 20 -2 #rect
Ag0 f24 @|StepIcon #fIcon
Ag0 f12 expr in #txt
Ag0 f12 416 238 416 276 #arcP
Ag0 f3 expr out #txt
Ag0 f3 416 172 416 210 #arcP
Ag0 f18 expr in #txt
Ag0 f18 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.authenticationType' #txt
Ag0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Ag0 f18 416 398 416 436 #arcP
Ag0 f18 0 0.5211870143653559 0 0 #arcLabel
Ag0 f9 expr out #txt
Ag0 f9 736 588 430 672 #arcP
Ag0 f9 1 736 672 #addKink
Ag0 f9 1 0.2346970365851248 0 0 #arcLabel
Ag0 f14 expr out #txt
Ag0 f14 416 460 416 658 #arcP
Ag0 f10 expr out #txt
Ag0 f10 160 340 160 300 #arcP
Ag0 f10 0 0.5038480345818864 0 0 #arcLabel
Ag0 f5 expr out #txt
Ag0 f5 160 276 402 224 #arcP
Ag0 f5 1 160 224 #addKink
Ag0 f5 0 0.18716831996155311 0 0 #arcLabel
Ag0 f26 expr in #txt
Ag0 f26 160 402 160 364 #arcP
Ag0 f26 0 0.7790944249033374 0 0 #arcLabel
Ag0 f27 expr out #txt
Ag0 f27 403 464 160 430 #arcP
Ag0 f27 1 160 464 #addKink
Ag0 f27 0 0.9534729894980475 0 0 #arcLabel
Ag0 f19 expr out #txt
Ag0 f19 723 592 160 430 #arcP
Ag0 f19 1 160 592 #addKink
Ag0 f19 0 0.7251543281365668 0 0 #arcLabel
Ag0 f7 expr out #txt
Ag0 f7 416 300 416 370 #arcP
Ag0 Et1 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 Et1 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Ag0 Et1 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 Et1 attachedToRef 150CB8D0B02F26AA-f17 #txt
Ag0 Et1 403 451 26 26 14 0 #rect
Ag0 Et1 @|ErrorBoundaryEventIcon #fIcon
Ag0 Et0 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 Et0 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Ag0 Et0 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 Et0 attachedToRef 150CB8D0B02F26AA-f15 #txt
Ag0 Et0 723 579 26 26 14 0 #rect
Ag0 Et0 @|ErrorBoundaryEventIcon #fIcon
Ag0 f16 expr out #txt
Ag0 f16 416 109 416 148 #arcP
Ag0 f2 expr in #txt
Ag0 f2 outCond 'in.returnedStatus == ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL' #txt
Ag0 f2 416 686 416 747 #arcP
Ag0 f2 0 0.7379182156133829 0 0 #arcLabel
Ag0 f23 expr in #txt
Ag0 f23 402 672 160 430 #arcP
Ag0 f23 1 160 672 #addKink
Ag0 f23 0 0.8171070593218652 0 0 #arcLabel
Ag0 f25 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f25 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f25 actionTable 'out=in;
out.message=wsResponse.addOrUpdateResponse.result.message;
out.returnedStatus=wsResponse.addOrUpdateResponse.result.status;
' #txt
Ag0 f25 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ag0 f25 timeout 0 #txt
Ag0 f25 beanConfig '"KEY_PASSWORD=<%\\=in.password%>
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=
KEY_USERNAME=<%\\=in.username%>
KEY_OPERATION=addOrUpdate
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.addOrUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"""' #txt
Ag0 f25 returningObjectList '[wsResponse]' #txt
Ag0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f25 558 500 36 24 21 -3 #rect
Ag0 f25 @|WebServiceIcon #fIcon
Ag0 f28 expr in #txt
Ag0 f28 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.authenticationType' #txt
Ag0 f28 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Ag0 f28 430 384 576 500 #arcP
Ag0 f28 1 576 384 #addKink
Ag0 f28 1 0.41 10 0 #arcLabel
Ag0 f21 expr in #txt
Ag0 f21 430 384 736 564 #arcP
Ag0 f21 1 736 384 #addKink
Ag0 f21 0 0.7542796058284387 0 0 #arcLabel
Ag0 Et2 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 Et2 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Ag0 Et2 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 Et2 attachedToRef 150CB8D0B02F26AA-f25 #txt
Ag0 Et2 557 521 26 26 14 0 #rect
Ag0 Et2 @|ErrorBoundaryEventIcon #fIcon
Ag0 f29 557 534 160 430 #arcP
Ag0 f29 1 160 534 #addKink
Ag0 f29 0 0.770569448336104 0 0 #arcLabel
Ag0 f30 expr out #txt
Ag0 f30 576 524 430 672 #arcP
Ag0 f30 1 576 672 #addKink
Ag0 f30 0 0.6388578878165849 0 0 #arcLabel
>Proto Ag0 .type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
>Proto Ag0 .processKind CALLABLE_SUB #txt
>Proto Ag0 0 0 32 24 18 0 #rect
>Proto Ag0 @|BIcon #fIcon
Ag0 f13 out f12 tail #connect
Ag0 f12 head f11 mainIn #connect
Ag0 f24 mainOut f3 tail #connect
Ag0 f3 head f13 in #connect
Ag0 f6 out f18 tail #connect
Ag0 f18 head f17 mainIn #connect
Ag0 f9 head f4 in #connect
Ag0 f14 head f4 in #connect
Ag0 f17 mainOut f14 tail #connect
Ag0 f15 mainOut f9 tail #connect
Ag0 f22 mainOut f10 tail #connect
Ag0 f10 head f8 mainIn #connect
Ag0 f8 mainOut f5 tail #connect
Ag0 f5 head f13 in #connect
Ag0 f20 out f26 tail #connect
Ag0 f26 head f22 mainIn #connect
Ag0 Et1 mainOut f27 tail #connect
Ag0 f27 head f20 in #connect
Ag0 Et0 mainOut f19 tail #connect
Ag0 f19 head f20 in #connect
Ag0 f11 mainOut f7 tail #connect
Ag0 f7 head f6 in #connect
Ag0 f0 mainOut f16 tail #connect
Ag0 f16 head f24 mainIn #connect
Ag0 f4 out f2 tail #connect
Ag0 f2 head f1 mainIn #connect
Ag0 f4 out f23 tail #connect
Ag0 f23 head f20 in #connect
Ag0 f6 out f28 tail #connect
Ag0 f28 head f25 mainIn #connect
Ag0 f6 out f21 tail #connect
Ag0 f21 head f15 mainIn #connect
Ag0 Et2 mainOut f29 tail #connect
Ag0 f29 head f20 in #connect
Ag0 f25 mainOut f30 tail #connect
Ag0 f30 head f4 in #connect
