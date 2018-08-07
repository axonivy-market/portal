[Ivy]
[>Created: Mon May 30 11:17:42 ICT 2016]
14E9138C39D61F94 3.18 #module
>Proto >Proto Collection #zClass
Gs0 GetSupportedLanguagesService Big #zClass
Gs0 B #cInfo
Gs0 #process
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @StartSub f0 '' #zField
Gs0 @EndSub f1 '' #zField
Gs0 @GridStep f2 '' #zField
Gs0 @CallSub f3 '' #zField
Gs0 @PushWFArc f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @PushWFArc f6 '' #zField
>Proto Gs0 Gs0 GetSupportedLanguagesService #zField
Gs0 f0 inParamDecl '<java.lang.String applicationName,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Gs0 f0 inParamTable 'out.applicationName=param.applicationName;
out.server=param.server;
' #txt
Gs0 f0 outParamDecl '<List<java.lang.String> supportedLanguages> result;
' #txt
Gs0 f0 outParamTable 'result.supportedLanguages=in.supportedLanguages;
' #txt
Gs0 f0 actionDecl 'ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData out;
' #txt
Gs0 f0 callSignature getSupportedLanguagesService(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Gs0 f0 type ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSupportedLanguagesService(String,Server)</name>
        <nameStyle>43,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 14 0 #rect
Gs0 f0 @|StartSubIcon #fIcon
Gs0 f1 type ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData #txt
Gs0 f1 83 307 26 26 14 0 #rect
Gs0 f1 @|EndSubIcon #fIcon
Gs0 f2 actionDecl 'ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData out;
' #txt
Gs0 f2 actionTable 'out=in;
' #txt
Gs0 f2 actionCode 'import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

PortalConnectorDetector detector = new PortalConnectorDetector();
in.endpoint = detector.getPortalConnectorURLOf(in.server) + WebServiceEndPoint.SUPPORTED_LANGUAGE.toString();
' #txt
Gs0 f2 type ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData #txt
Gs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual
endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f2 78 140 36 24 26 -15 #rect
Gs0 f2 @|StepIcon #fIcon
Gs0 f3 type ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData #txt
Gs0 f3 processCall ServiceIntegrators/GetSupportedLanguageServiceIntegrator:getSupportedLanguages(ch.ivy.addon.portalkit.persistence.domain.Server,String,String) #txt
Gs0 f3 doCall true #txt
Gs0 f3 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String endpoint,java.lang.String applicationName> param;
' #txt
Gs0 f3 requestMappingAction 'param.server=in.server;
param.endpoint=in.endpoint;
param.applicationName=in.applicationName;
' #txt
Gs0 f3 responseActionDecl 'ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData out;
' #txt
Gs0 f3 responseMappingAction 'out=in;
out.supportedLanguages=result.supportedLanguages;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSupportedLanguages(Server,String,String)</name>
        <nameStyle>43,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f3 77 212 38 24 20 -2 #rect
Gs0 f3 @|CallSubIcon #fIcon
Gs0 f4 expr out #txt
Gs0 f4 96 77 96 140 #arcP
Gs0 f5 expr out #txt
Gs0 f5 96 164 96 212 #arcP
Gs0 f6 expr out #txt
Gs0 f6 96 236 96 307 #arcP
>Proto Gs0 .type ch.ivyteam.wf.processes.GetSupportedLanguagesServiceData #txt
>Proto Gs0 .processKind CALLABLE_SUB #txt
>Proto Gs0 0 0 32 24 18 0 #rect
>Proto Gs0 @|BIcon #fIcon
Gs0 f0 mainOut f4 tail #connect
Gs0 f4 head f2 mainIn #connect
Gs0 f2 mainOut f5 tail #connect
Gs0 f5 head f3 mainIn #connect
Gs0 f3 mainOut f6 tail #connect
Gs0 f6 head f1 mainIn #connect
