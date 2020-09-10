[Ivy]
14BE80F25BC9033C 9.2.0 #module
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
Ee0 @EndSub f4 '' #zField
Ee0 @GridStep f17 '' #zField
Ee0 @EndSub f18 '' #zField
Ee0 @CallSub f19 '' #zField
Ee0 @PushWFArc f20 '' #zField
Ee0 @PushWFArc f21 '' #zField
Ee0 @StartSub f23 '' #zField
Ee0 @PushWFArc f16 '' #zField
Ee0 @EndSub f32 '' #zField
Ee0 @GridStep f33 '' #zField
Ee0 @StartSub f34 '' #zField
Ee0 @CallSub f35 '' #zField
Ee0 @PushWFArc f36 '' #zField
Ee0 @PushWFArc f37 '' #zField
Ee0 @PushWFArc f38 '' #zField
Ee0 @CallSub f1 '' #zField
Ee0 @GridStep f2 '' #zField
Ee0 @PushWFArc f3 '' #zField
Ee0 @PushWFArc f5 '' #zField
Ee0 @PushWFArc f6 '' #zField
>Proto Ee0 Ee0 LanguageService #zField
Ee0 f0 inParamDecl '<String username> param;' #txt
Ee0 f0 inParamTable 'out.username=param.username;
' #txt
Ee0 f0 outParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f0 outParamTable 'result.language=in.language;
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
Ee0 f0 81 49 30 30 -31 19 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f4 609 49 30 30 0 15 #rect
Ee0 f4 @|EndSubIcon #fIcon
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
Ee0 f17 176 234 192 44 -93 -8 #rect
Ee0 f17 @|StepIcon #fIcon
Ee0 f18 609 241 30 30 0 15 #rect
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
Ee0 f19 424 234 112 44 -35 -8 #rect
Ee0 f19 @|CallSubIcon #fIcon
Ee0 f20 expr out #txt
Ee0 f20 536 256 609 256 #arcP
Ee0 f21 expr out #txt
Ee0 f21 368 256 424 256 #arcP
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
Ee0 f23 81 241 30 30 -30 19 #rect
Ee0 f23 @|StartSubIcon #fIcon
Ee0 f16 expr out #txt
Ee0 f16 111 256 176 256 #arcP
Ee0 f32 609 145 30 30 0 15 #rect
Ee0 f32 @|EndSubIcon #fIcon
Ee0 f33 actionTable 'out=in;
' #txt
Ee0 f33 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

IvyLanguageResultDTO dto = LanguageService.newInstance().saveUserLanguage(in.username, in.language);
out.errors = dto.errors;' #txt
Ee0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f33 224 138 128 44 -58 -8 #rect
Ee0 f33 @|StepIcon #fIcon
Ee0 f34 inParamDecl '<String username,ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language> param;' #txt
Ee0 f34 inParamTable 'out.language=param.language;
out.username=param.username;
' #txt
Ee0 f34 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f34 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f34 callSignature saveUserLanguage(String,ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage) #txt
Ee0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserLanguage(String,IvyLanguage)</name>
    </language>
</elementInfo>
' #txt
Ee0 f34 81 145 30 30 -33 21 #rect
Ee0 f34 @|StartSubIcon #fIcon
Ee0 f35 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f35 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f35 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f35 responseActionDecl 'ch.ivyteam.wf.processes.LanguageServiceData out;
' #txt
Ee0 f35 responseMappingAction 'out=in;
' #txt
Ee0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f35 424 138 112 44 -35 -8 #rect
Ee0 f35 @|CallSubIcon #fIcon
Ee0 f36 expr out #txt
Ee0 f36 536 160 609 160 #arcP
Ee0 f37 expr out #txt
Ee0 f37 352 160 424 160 #arcP
Ee0 f38 expr out #txt
Ee0 f38 111 160 224 160 #arcP
Ee0 f1 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f1 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f1 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f1 responseActionDecl 'ch.ivyteam.wf.processes.LanguageServiceData out;
' #txt
Ee0 f1 responseMappingAction 'out=in;
' #txt
Ee0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f1 424 42 112 44 -35 -8 #rect
Ee0 f1 @|CallSubIcon #fIcon
Ee0 f2 actionTable 'out=in;
' #txt
Ee0 f2 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

out.language = LanguageService.newInstance().findUserLanguages(in.username).getIvyLanguage();
' #txt
Ee0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f2 216 42 128 44 -57 -8 #rect
Ee0 f2 @|StepIcon #fIcon
Ee0 f3 expr out #txt
Ee0 f3 344 64 424 64 #arcP
Ee0 f5 111 64 216 64 #arcP
Ee0 f6 536 64 609 64 #arcP
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
Ee0 f17 mainOut f21 tail #connect
Ee0 f21 head f19 mainIn #connect
Ee0 f19 mainOut f20 tail #connect
Ee0 f20 head f18 mainIn #connect
Ee0 f23 mainOut f16 tail #connect
Ee0 f16 head f17 mainIn #connect
Ee0 f34 mainOut f38 tail #connect
Ee0 f38 head f33 mainIn #connect
Ee0 f33 mainOut f37 tail #connect
Ee0 f37 head f35 mainIn #connect
Ee0 f35 mainOut f36 tail #connect
Ee0 f36 head f32 mainIn #connect
Ee0 f2 mainOut f3 tail #connect
Ee0 f3 head f1 mainIn #connect
Ee0 f0 mainOut f5 tail #connect
Ee0 f5 head f2 mainIn #connect
Ee0 f1 mainOut f6 tail #connect
Ee0 f6 head f4 mainIn #connect
