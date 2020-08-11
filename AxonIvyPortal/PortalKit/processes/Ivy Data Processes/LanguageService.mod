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
Ee0 @EndSub f4 '' #zField
Ee0 @GridStep f17 '' #zField
Ee0 @EndSub f18 '' #zField
Ee0 @StartSub f23 '' #zField
Ee0 @PushWFArc f16 '' #zField
Ee0 @EndSub f32 '' #zField
Ee0 @GridStep f33 '' #zField
Ee0 @StartSub f34 '' #zField
Ee0 @PushWFArc f38 '' #zField
Ee0 @GridStep f2 '' #zField
Ee0 @PushWFArc f5 '' #zField
Ee0 @PushWFArc f7 '' #zField
Ee0 @PushWFArc f1 '' #zField
Ee0 @PushWFArc f3 '' #zField
>Proto Ee0 Ee0 LanguageService #zField
Ee0 f0 inParamDecl '<> param;' #txt
Ee0 f0 outParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language> result;' #txt
Ee0 f0 outParamTable 'result.language=in.language;
' #txt
Ee0 f0 callSignature findUserLanguages() #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUserLanguages()</name>
    </language>
</elementInfo>
' #txt
Ee0 f0 81 49 30 30 -31 19 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f4 521 49 30 30 0 15 #rect
Ee0 f4 @|EndSubIcon #fIcon
Ee0 f17 actionTable 'out=in;
' #txt
Ee0 f17 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;


IvyLanguageResultDTO dto = LanguageService.newInstance().findUserLanguages();//getSupportedLanguages();
out.language = dto.ivyLanguage;' #txt
Ee0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load supported languages for current app</name>
    </language>
</elementInfo>
' #txt
Ee0 f17 208 234 240 44 -113 -8 #rect
Ee0 f17 @|StepIcon #fIcon
Ee0 f18 521 241 30 30 0 15 #rect
Ee0 f18 @|EndSubIcon #fIcon
Ee0 f23 inParamDecl '<> param;' #txt
Ee0 f23 outParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage supportedLanguge> result;' #txt
Ee0 f23 outParamTable 'result.supportedLanguge=in.language;
' #txt
Ee0 f23 callSignature loadSupportedLanguages() #txt
Ee0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadSupportedLanguages()</name>
    </language>
</elementInfo>
' #txt
Ee0 f23 81 241 30 30 -66 21 #rect
Ee0 f23 @|StartSubIcon #fIcon
Ee0 f16 expr out #txt
Ee0 f16 111 256 208 256 #arcP
Ee0 f32 521 145 30 30 0 15 #rect
Ee0 f32 @|EndSubIcon #fIcon
Ee0 f33 actionTable 'out=in;
' #txt
Ee0 f33 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

LanguageService.newInstance().saveUserLanguage(in.language);' #txt
Ee0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f33 208 138 128 44 -58 -8 #rect
Ee0 f33 @|StepIcon #fIcon
Ee0 f34 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language> param;' #txt
Ee0 f34 inParamTable 'out.language=param.language;
' #txt
Ee0 f34 outParamDecl '<> result;' #txt
Ee0 f34 callSignature saveUserLanguage(ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage) #txt
Ee0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserLanguage(IvyLanguage)</name>
    </language>
</elementInfo>
' #txt
Ee0 f34 81 145 30 30 -67 24 #rect
Ee0 f34 @|StartSubIcon #fIcon
Ee0 f38 expr out #txt
Ee0 f38 111 160 208 160 #arcP
Ee0 f2 actionTable 'out=in;
' #txt
Ee0 f2 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

out.language = LanguageService.newInstance().findUserLanguages().getIvyLanguage();
' #txt
Ee0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find user languages</name>
    </language>
</elementInfo>
' #txt
Ee0 f2 208 42 128 44 -57 -8 #rect
Ee0 f2 @|StepIcon #fIcon
Ee0 f5 111 64 208 64 #arcP
Ee0 f7 expr out #txt
Ee0 f7 336 64 521 64 #arcP
Ee0 f1 expr out #txt
Ee0 f1 336 160 521 160 #arcP
Ee0 f3 expr out #txt
Ee0 f3 448 256 521 256 #arcP
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
Ee0 f23 mainOut f16 tail #connect
Ee0 f16 head f17 mainIn #connect
Ee0 f34 mainOut f38 tail #connect
Ee0 f38 head f33 mainIn #connect
Ee0 f0 mainOut f5 tail #connect
Ee0 f5 head f2 mainIn #connect
Ee0 f2 mainOut f7 tail #connect
Ee0 f7 head f4 mainIn #connect
Ee0 f33 mainOut f1 tail #connect
Ee0 f1 head f32 mainIn #connect
Ee0 f17 mainOut f3 tail #connect
Ee0 f3 head f18 mainIn #connect
