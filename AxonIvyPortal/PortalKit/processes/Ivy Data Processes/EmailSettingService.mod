[Ivy]
14BDEA64F884ED76 7.5.0 #module
>Proto >Proto Collection #zClass
Ee0 EmailSettingService Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @StartSub f3 '' #zField
Ee0 @GridStep f10 '' #zField
Ee0 @GridStep f20 '' #zField
Ee0 @CallSub f24 '' #zField
Ee0 @EndSub f25 '' #zField
Ee0 @PushWFArc f26 '' #zField
Ee0 @PushWFArc f27 '' #zField
Ee0 @PushWFArc f37 '' #zField
Ee0 @PushWFArc f38 '' #zField
Ee0 @StartSub f0 '' #zField
Ee0 @GridStep f6 '' #zField
Ee0 @CallSub f2 '' #zField
Ee0 @EndSub f4 '' #zField
Ee0 @PushWFArc f1 '' #zField
Ee0 @PushWFArc f5 '' #zField
Ee0 @PushWFArc f7 '' #zField
>Proto Ee0 Ee0 EmailSettingService #zField
Ee0 f3 inParamDecl '<String username> param;' #txt
Ee0 f3 inParamTable 'out.username=param.username;
' #txt
Ee0 f3 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting> ivyEmailSettings,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f3 outParamTable 'result.ivyEmailSettings=in.ivyEmailSettings;
result.errors=in.errors;
' #txt
Ee0 f3 callSignature findEmailSettings(String) #txt
Ee0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findEmailSettings(String)</name>
    </language>
</elementInfo>
' #txt
Ee0 f3 81 49 30 30 -55 19 #rect
Ee0 f3 @|StartSubIcon #fIcon
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
Ee0 f10 192 42 176 44 -81 -8 #rect
Ee0 f10 @|StepIcon #fIcon
Ee0 f20 actionTable 'out=in;
' #txt
Ee0 f20 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().findEmailSetting(in.username, in.apps);
out.ivyEmailSettings = dto.ivyEmailSettings;
EmailSettingService.newInstance().displayDailySummary(out.ivyEmailSettings);
out.errors = dto.errors;
' #txt
Ee0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find email settings</name>
    </language>
</elementInfo>
' #txt
Ee0 f20 424 42 112 44 -52 -8 #rect
Ee0 f20 @|StepIcon #fIcon
Ee0 f24 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f24 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f24 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f24 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f24 responseMappingAction 'out=in;
' #txt
Ee0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f24 608 42 112 44 -35 -8 #rect
Ee0 f24 @|CallSubIcon #fIcon
Ee0 f25 785 49 30 30 0 15 #rect
Ee0 f25 @|EndSubIcon #fIcon
Ee0 f26 expr out #txt
Ee0 f26 368 64 424 64 #arcP
Ee0 f27 expr out #txt
Ee0 f27 536 64 608 64 #arcP
Ee0 f37 expr out #txt
Ee0 f37 720 64 785 64 #arcP
Ee0 f38 expr out #txt
Ee0 f38 111 64 192 64 #arcP
Ee0 f0 inParamDecl '<String username,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting> emailSettings> param;' #txt
Ee0 f0 inParamTable 'out.ivyEmailSettings=param.emailSettings;
out.username=param.username;
' #txt
Ee0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f0 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f0 callSignature saveEmailSettings(String,List<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting>) #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveEmailSettings(String,List&lt;IvyEmailSetting&gt;)</name>
    </language>
</elementInfo>
' #txt
Ee0 f0 81 177 30 30 -79 14 #rect
Ee0 f0 @|StartSubIcon #fIcon
Ee0 f6 actionTable 'out=in;
' #txt
Ee0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().saveEmailSetting(in.username, in.ivyEmailSettings);
out.errors = dto.errors;
' #txt
Ee0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save email settings</name>
    </language>
</elementInfo>
' #txt
Ee0 f6 296 170 112 44 -53 -8 #rect
Ee0 f6 @|StepIcon #fIcon
Ee0 f2 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f2 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f2 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f2 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
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
Ee0 f2 520 170 112 44 -35 -8 #rect
Ee0 f2 @|CallSubIcon #fIcon
Ee0 f4 785 177 30 30 0 15 #rect
Ee0 f4 @|EndSubIcon #fIcon
Ee0 f1 expr out #txt
Ee0 f1 408 192 520 192 #arcP
Ee0 f5 expr out #txt
Ee0 f5 632 192 785 192 #arcP
Ee0 f7 expr out #txt
Ee0 f7 111 192 296 192 #arcP
>Proto Ee0 .type ch.ivyteam.wf.processes.EmailSettingServiceData #txt
>Proto Ee0 .processKind CALLABLE_SUB #txt
>Proto Ee0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findAllEmailSettings</swimlaneLabel>
        <swimlaneLabel>saveAllEmailSettings</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>280</swimlaneSize>
    <swimlaneSize>344</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Ee0 0 0 32 24 18 0 #rect
>Proto Ee0 @|BIcon #fIcon
Ee0 f10 mainOut f26 tail #connect
Ee0 f26 head f20 mainIn #connect
Ee0 f20 mainOut f27 tail #connect
Ee0 f27 head f24 mainIn #connect
Ee0 f24 mainOut f37 tail #connect
Ee0 f37 head f25 mainIn #connect
Ee0 f3 mainOut f38 tail #connect
Ee0 f38 head f10 mainIn #connect
Ee0 f6 mainOut f1 tail #connect
Ee0 f1 head f2 mainIn #connect
Ee0 f2 mainOut f5 tail #connect
Ee0 f5 head f4 mainIn #connect
Ee0 f0 mainOut f7 tail #connect
Ee0 f7 head f6 mainIn #connect
