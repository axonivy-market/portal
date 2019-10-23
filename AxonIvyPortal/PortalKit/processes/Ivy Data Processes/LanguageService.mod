[Ivy]
14BE80F25BC9033C 7.5.0 #module
>Proto >Proto Collection #zClass
Ee0 LanguageService Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @StartSub f0 '' #zField
Ee0 @GridStep f10 '' #zField
Ee0 @PushWFArc f1 '' #zField
Ee0 @GridStep f6 '' #zField
Ee0 @PushWFArc f7 '' #zField
Ee0 @CallSub f2 '' #zField
Ee0 @PushWFArc f3 '' #zField
Ee0 @EndSub f4 '' #zField
Ee0 @PushWFArc f5 '' #zField
Ee0 @StartSub f8 '' #zField
Ee0 @EndSub f9 '' #zField
Ee0 @GridStep f12 '' #zField
Ee0 @PushWFArc f13 '' #zField
Ee0 @CallSub f14 '' #zField
Ee0 @PushWFArc f15 '' #zField
Ee0 @PushWFArc f11 '' #zField
Ee0 @GridStep f17 '' #zField
Ee0 @EndSub f18 '' #zField
Ee0 @CallSub f19 '' #zField
Ee0 @PushWFArc f20 '' #zField
Ee0 @PushWFArc f21 '' #zField
Ee0 @StartSub f23 '' #zField
Ee0 @PushWFArc f16 '' #zField
>Proto Ee0 Ee0 LanguageService #zField
Ee0 f0 inParamDecl '<String username> param;' #txt
Ee0 f0 inParamTable 'out.username=param.username;
' #txt
Ee0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage> languages,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f0 outParamTable 'result.languages=in.languages;
result.errors=in.errors;
' #txt
Ee0 f0 callSignature findUserLanguages(String) #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUserLanguages(String)</name>
    </language>
</elementInfo>
' #txt
Ee0 f0 81 49 30 30 -75 17 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f10 actionTable 'out=in;
' #txt
Ee0 f10 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Ee0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ee0 f10 168 42 176 44 -81 -8 #rect
Ee0 f10 @|StepIcon #fIcon
Ee0 f1 expr out #txt
Ee0 f1 111 64 168 64 #arcP
Ee0 f6 actionTable 'out=in;
' #txt
Ee0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

IvyLanguageResultDTO dto = LanguageService.newInstance().findUserLanguages(in.username, in.apps);
out.languages = dto.ivyLanguages;
out.errors = dto.errors;' #txt
Ee0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f6 392 42 128 44 -57 -8 #rect
Ee0 f6 @|StepIcon #fIcon
Ee0 f7 expr out #txt
Ee0 f7 344 64 392 64 #arcP
Ee0 f2 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f2 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f2 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f2 responseActionDecl 'ch.ivyteam.wf.processes.LanguageServiceData out;
' #txt
Ee0 f2 responseMappingAction 'out=in;
' #txt
Ee0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f2 584 42 112 44 -35 -8 #rect
Ee0 f2 @|CallSubIcon #fIcon
Ee0 f3 expr out #txt
Ee0 f3 520 64 584 64 #arcP
Ee0 f4 753 49 30 30 0 15 #rect
Ee0 f4 @|EndSubIcon #fIcon
Ee0 f5 expr out #txt
Ee0 f5 696 64 753 64 #arcP
Ee0 f8 inParamDecl '<String username,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage> languages> param;' #txt
Ee0 f8 inParamTable 'out.languages=param.languages;
out.username=param.username;
' #txt
Ee0 f8 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f8 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f8 callSignature saveUserLanguages(String,List<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage>) #txt
Ee0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserLanguages(String,List&lt;IvyLanguage&gt;)</name>
    </language>
</elementInfo>
' #txt
Ee0 f8 81 177 30 30 -94 23 #rect
Ee0 f8 @|StartSubIcon #fIcon
Ee0 f9 753 177 30 30 0 15 #rect
Ee0 f9 @|EndSubIcon #fIcon
Ee0 f12 actionTable 'out=in;
' #txt
Ee0 f12 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

IvyLanguageResultDTO dto = LanguageService.newInstance().saveUserLanguages(in.username, in.languages);
out.errors = dto.errors;' #txt
Ee0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f12 224 170 128 44 -58 -8 #rect
Ee0 f12 @|StepIcon #fIcon
Ee0 f13 expr out #txt
Ee0 f13 111 192 224 192 #arcP
Ee0 f14 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f14 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f14 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f14 responseActionDecl 'ch.ivyteam.wf.processes.LanguageServiceData out;
' #txt
Ee0 f14 responseMappingAction 'out=in;
' #txt
Ee0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f14 496 170 112 44 -35 -8 #rect
Ee0 f14 @|CallSubIcon #fIcon
Ee0 f15 expr out #txt
Ee0 f15 352 192 496 192 #arcP
Ee0 f11 expr out #txt
Ee0 f11 608 192 753 192 #arcP
Ee0 f17 actionTable 'out=in;
' #txt
Ee0 f17 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

IvyLanguageResultDTO dto = LanguageService.newInstance().getSupportedLanguages(in.apps.get(0));
out.languages = dto.ivyLanguages;
out.errors = dto.errors;' #txt
Ee0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load supported languages for app</name>
    </language>
</elementInfo>
' #txt
Ee0 f17 200 298 192 44 -93 -8 #rect
Ee0 f17 @|StepIcon #fIcon
Ee0 f18 761 305 30 30 0 15 #rect
Ee0 f18 @|EndSubIcon #fIcon
Ee0 f19 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f19 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f19 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f19 responseActionDecl 'ch.ivyteam.wf.processes.LanguageServiceData out;
' #txt
Ee0 f19 responseMappingAction 'out=in;
' #txt
Ee0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f19 504 298 112 44 -35 -8 #rect
Ee0 f19 @|CallSubIcon #fIcon
Ee0 f20 expr out #txt
Ee0 f20 616 320 761 320 #arcP
Ee0 f21 expr out #txt
Ee0 f21 392 320 504 320 #arcP
Ee0 f23 inParamDecl '<String appName> param;' #txt
Ee0 f23 inParamTable 'out.apps=java.util.Arrays.asList(param.appName);
' #txt
Ee0 f23 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage supportedLanguge> result;' #txt
Ee0 f23 outParamTable 'result.errors=in.errors;
result.supportedLanguge=org.apache.commons.collections4.CollectionUtils.isNotEmpty(in.languages) ? in.languages.get(0) : null;
' #txt
Ee0 f23 callSignature loadSupportedLanguages(String) #txt
Ee0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadSupportedLanguages(String)</name>
    </language>
</elementInfo>
' #txt
Ee0 f23 81 305 30 30 -92 17 #rect
Ee0 f23 @|StartSubIcon #fIcon
Ee0 f16 expr out #txt
Ee0 f16 111 320 200 320 #arcP
>Proto Ee0 .type ch.ivyteam.wf.processes.LanguageServiceData #txt
>Proto Ee0 .processKind CALLABLE_SUB #txt
>Proto Ee0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findAllLanguageSettings</swimlaneLabel>
        <swimlaneLabel>saveLanguageSettings</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>288</swimlaneSize>
    <swimlaneSize>544</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Ee0 0 0 32 24 18 0 #rect
>Proto Ee0 @|BIcon #fIcon
Ee0 f0 mainOut f1 tail #connect
Ee0 f1 head f10 mainIn #connect
Ee0 f10 mainOut f7 tail #connect
Ee0 f7 head f6 mainIn #connect
Ee0 f6 mainOut f3 tail #connect
Ee0 f3 head f2 mainIn #connect
Ee0 f2 mainOut f5 tail #connect
Ee0 f5 head f4 mainIn #connect
Ee0 f8 mainOut f13 tail #connect
Ee0 f13 head f12 mainIn #connect
Ee0 f12 mainOut f15 tail #connect
Ee0 f15 head f14 mainIn #connect
Ee0 f14 mainOut f11 tail #connect
Ee0 f11 head f9 mainIn #connect
Ee0 f17 mainOut f21 tail #connect
Ee0 f21 head f19 mainIn #connect
Ee0 f19 mainOut f20 tail #connect
Ee0 f20 head f18 mainIn #connect
Ee0 f23 mainOut f16 tail #connect
Ee0 f16 head f17 mainIn #connect
