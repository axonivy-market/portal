[Ivy]
[>Created: Mon Dec 28 10:50:12 ICT 2015]
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
Ag0 @PushWFArc f21 '' #zField
Ag0 @PushWFArc f26 '' #zField
Ag0 @PushWFArc f27 '' #zField
Ag0 @PushWFArc f19 '' #zField
Ag0 @PushWFArc f7 '' #zField
Ag0 @ErrorBoundaryEvent Et1 ErrorBoundaryEvent #zField
Ag0 @ErrorBoundaryEvent Et0 ErrorBoundaryEvent #zField
Ag0 @PushWFArc f16 '' #zField
Ag0 @PushWFArc f2 '' #zField
Ag0 @PushWFArc f23 '' #zField
>Proto Ag0 Ag0 AddOrUpdateWSCalling #zField
Ag0 f0 inParamDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customerPropertyPairs,java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL> param;' #txt
Ag0 f0 inParamTable 'out.customPropertyPairs=param.customerPropertyPairs;
out.isNTLMAuthentication=param.isNTLMAuthentication;
out.serverURL=param.serverURL;
' #txt
Ag0 f0 outParamDecl '<> result;
' #txt
Ag0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 f0 callSignature addOrUpdate(List<ch.ivy.ws.addon.CustomPropertyPair>,Boolean,String) #txt
Ag0 f0 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdate(List&lt;CustomPropertyPair&gt;,Boolean,String)</name>
        <nameStyle>52,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f0 259 83 26 26 14 0 #rect
Ag0 f0 @|StartSubIcon #fIcon
Ag0 f1 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f1 259 563 26 26 14 0 #rect
Ag0 f1 @|EndSubIcon #fIcon
Ag0 f20 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f20 130 394 28 28 14 0 #rect
Ag0 f20 @|AlternativeIcon #fIcon
Ag0 f13 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f13 258 234 28 28 14 0 #rect
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
Ag0 f4 258 474 28 28 14 0 #rect
Ag0 f4 @|AlternativeIcon #fIcon
Ag0 f6 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f6 258 346 28 28 14 -22 #rect
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
Ag0 f22 126 340 36 24 -125 17 #rect
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
Ag0 f17 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=PortalDataServicePort
KEY_WEBSERVICECONFIG_ID=1503AE0FAA915FAA
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=addOrUpdate
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.addOrUpdate.customPropertyPairs__@@__Array<ch.ivy.ws.addon.CustomPropertyPair>__@@__in.customPropertyPairs"""' #txt
Ag0 f17 returningObjectList '[wsResponse]' #txt
Ag0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call web service 
with NTLM authentication</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f17 254 404 36 24 21 -3 #rect
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
Ag0 f8 126 276 36 24 -109 1 #rect
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
        <name>call web service 
with default setting</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f15 486 420 36 24 20 -2 #rect
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
Ag0 f11 254 300 36 24 20 -2 #rect
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
Ag0 f24 254 172 36 24 20 -2 #rect
Ag0 f24 @|StepIcon #fIcon
Ag0 f12 expr in #txt
Ag0 f12 272 262 272 300 #arcP
Ag0 f3 expr out #txt
Ag0 f3 272 196 272 234 #arcP
Ag0 f18 expr in #txt
Ag0 f18 outCond in.isNTLMAuthentication #txt
Ag0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f18 272 374 272 404 #arcP
Ag0 f18 0 0.5141154410867703 0 0 #arcLabel
Ag0 f9 expr out #txt
Ag0 f9 504 444 286 488 #arcP
Ag0 f9 1 504 488 #addKink
Ag0 f9 1 0.3669313442454635 0 0 #arcLabel
Ag0 f14 expr out #txt
Ag0 f14 272 428 272 474 #arcP
Ag0 f10 expr out #txt
Ag0 f10 144 340 144 300 #arcP
Ag0 f10 0 0.5038480345818864 0 0 #arcLabel
Ag0 f5 expr out #txt
Ag0 f5 144 276 258 248 #arcP
Ag0 f5 1 144 248 #addKink
Ag0 f5 0 0.18716831996155311 0 0 #arcLabel
Ag0 f21 expr in #txt
Ag0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ag0 f21 286 360 504 420 #arcP
Ag0 f21 1 504 360 #addKink
Ag0 f21 1 0.4166666666666667 10 0 #arcLabel
Ag0 f26 expr in #txt
Ag0 f26 144 394 144 364 #arcP
Ag0 f26 0 0.7790944249033374 0 0 #arcLabel
Ag0 f27 expr out #txt
Ag0 f27 259 432 144 422 #arcP
Ag0 f27 1 144 432 #addKink
Ag0 f27 0 0.6087272914022362 0 0 #arcLabel
Ag0 f19 expr out #txt
Ag0 f19 491 448 144 422 #arcP
Ag0 f19 1 144 448 #addKink
Ag0 f19 0 0.582714800043308 0 0 #arcLabel
Ag0 f7 expr out #txt
Ag0 f7 272 324 272 346 #arcP
Ag0 Et1 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 Et1 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Ag0 Et1 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 Et1 attachedToRef 150CB8D0B02F26AA-f17 #txt
Ag0 Et1 259 419 26 26 14 0 #rect
Ag0 Et1 @|ErrorBoundaryEventIcon #fIcon
Ag0 Et0 actionDecl 'ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData out;
' #txt
Ag0 Et0 actionTable 'out=in;
out.message=error.stackTrace.toString();
' #txt
Ag0 Et0 type ch.ivy.add.portalkit.synchronization.AddOrUpdateWSCallingData #txt
Ag0 Et0 attachedToRef 150CB8D0B02F26AA-f15 #txt
Ag0 Et0 491 435 26 26 14 0 #rect
Ag0 Et0 @|ErrorBoundaryEventIcon #fIcon
Ag0 f16 expr out #txt
Ag0 f16 272 109 272 172 #arcP
Ag0 f2 expr in #txt
Ag0 f2 outCond 'in.returnedStatus == ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL' #txt
Ag0 f2 272 502 272 563 #arcP
Ag0 f2 0 0.7379182156133829 0 0 #arcLabel
Ag0 f23 expr in #txt
Ag0 f23 258 488 144 422 #arcP
Ag0 f23 1 144 488 #addKink
Ag0 f23 0 0.8171070593218652 0 0 #arcLabel
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
Ag0 f6 out f21 tail #connect
Ag0 f21 head f15 mainIn #connect
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
