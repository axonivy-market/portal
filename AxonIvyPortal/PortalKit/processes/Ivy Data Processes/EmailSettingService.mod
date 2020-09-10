[Ivy]
14BDEA64F884ED76 9.2.0 #module
>Proto >Proto Collection #zClass
Ee0 EmailSettingService Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @GridStep f9 '' #zField
Ee0 @CallSub f11 '' #zField
Ee0 @EndSub f12 '' #zField
Ee0 @StartSub f13 '' #zField
Ee0 @PushWFArc f16 '' #zField
Ee0 @PushWFArc f17 '' #zField
Ee0 @PushWFArc f8 '' #zField
Ee0 @CallSub f14 '' #zField
Ee0 @StartSub f15 '' #zField
Ee0 @EndSub f18 '' #zField
Ee0 @GridStep f19 '' #zField
Ee0 @PushWFArc f21 '' #zField
Ee0 @PushWFArc f22 '' #zField
Ee0 @PushWFArc f23 '' #zField
>Proto Ee0 Ee0 EmailSettingService #zField
Ee0 f9 actionTable 'out=in;
' #txt
Ee0 f9 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().findEmailSetting(in.username);
out.ivyEmailSetting = dto.ivyEmailSetting;
EmailSettingService.newInstance().displayDailySummary(out.ivyEmailSetting);
out.errors = dto.errors;
' #txt
Ee0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find email setting</name>
    </language>
</elementInfo>
' #txt
Ee0 f9 168 26 112 44 -49 -8 #rect
Ee0 f9 @|StepIcon #fIcon
Ee0 f11 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f11 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f11 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f11 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
' #txt
Ee0 f11 responseMappingAction 'out=in;
' #txt
Ee0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ee0 f11 352 26 112 44 -35 -8 #rect
Ee0 f11 @|CallSubIcon #fIcon
Ee0 f12 529 33 30 30 0 15 #rect
Ee0 f12 @|EndSubIcon #fIcon
Ee0 f13 inParamDecl '<String username> param;' #txt
Ee0 f13 inParamTable 'out.username=param.username;
' #txt
Ee0 f13 outParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting ivyEmailSetting,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f13 outParamTable 'result.ivyEmailSetting=in.ivyEmailSetting;
result.errors=in.errors;
' #txt
Ee0 f13 callSignature findEmailSetting(String) #txt
Ee0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findEmailSetting(String)</name>
    </language>
</elementInfo>
' #txt
Ee0 f13 65 33 30 30 -55 19 #rect
Ee0 f13 @|StartSubIcon #fIcon
Ee0 f16 expr out #txt
Ee0 f16 280 48 352 48 #arcP
Ee0 f17 expr out #txt
Ee0 f17 464 48 529 48 #arcP
Ee0 f8 95 48 168 48 #arcP
Ee0 f14 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ee0 f14 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ee0 f14 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ee0 f14 responseActionDecl 'ch.ivyteam.wf.processes.EmailSettingServiceData out;
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
Ee0 f14 352 106 112 44 -35 -8 #rect
Ee0 f14 @|CallSubIcon #fIcon
Ee0 f15 inParamDecl '<String username,ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting emailSetting> param;' #txt
Ee0 f15 inParamTable 'out.ivyEmailSetting=param.emailSetting;
out.username=param.username;
' #txt
Ee0 f15 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Ee0 f15 outParamTable 'result.errors=in.errors;
' #txt
Ee0 f15 callSignature saveEmailSetting(String,ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting) #txt
Ee0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveEmailSetting(String,IvyEmailSetting)</name>
    </language>
</elementInfo>
' #txt
Ee0 f15 65 113 30 30 -63 20 #rect
Ee0 f15 @|StartSubIcon #fIcon
Ee0 f18 529 113 30 30 0 15 #rect
Ee0 f18 @|EndSubIcon #fIcon
Ee0 f19 actionTable 'out=in;
' #txt
Ee0 f19 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().saveEmailSetting(in.username, in.ivyEmailSetting);
out.errors = dto.errors;
' #txt
Ee0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save email setting</name>
    </language>
</elementInfo>
' #txt
Ee0 f19 168 106 112 44 -50 -8 #rect
Ee0 f19 @|StepIcon #fIcon
Ee0 f21 expr out #txt
Ee0 f21 280 128 352 128 #arcP
Ee0 f22 expr out #txt
Ee0 f22 464 128 529 128 #arcP
Ee0 f23 expr out #txt
Ee0 f23 95 128 168 128 #arcP
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
Ee0 f9 mainOut f16 tail #connect
Ee0 f16 head f11 mainIn #connect
Ee0 f11 mainOut f17 tail #connect
Ee0 f17 head f12 mainIn #connect
Ee0 f13 mainOut f8 tail #connect
Ee0 f8 head f9 mainIn #connect
Ee0 f19 mainOut f21 tail #connect
Ee0 f21 head f14 mainIn #connect
Ee0 f14 mainOut f22 tail #connect
Ee0 f22 head f18 mainIn #connect
Ee0 f15 mainOut f23 tail #connect
Ee0 f23 head f19 mainIn #connect
