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
Ee0 @GridStep f9 '' #zField
Ee0 @EndSub f12 '' #zField
Ee0 @StartSub f13 '' #zField
Ee0 @PushWFArc f8 '' #zField
Ee0 @StartSub f15 '' #zField
Ee0 @EndSub f18 '' #zField
Ee0 @GridStep f19 '' #zField
Ee0 @PushWFArc f23 '' #zField
Ee0 @PushWFArc f0 '' #zField
Ee0 @PushWFArc f1 '' #zField
>Proto Ee0 Ee0 EmailSettingService #zField
Ee0 f9 actionTable 'out=in;
' #txt
Ee0 f9 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().findEmailSetting();
out.ivyEmailSetting = dto.ivyEmailSetting;
EmailSettingService.newInstance().displayDailySummary(out.ivyEmailSetting);
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
Ee0 f12 369 33 30 30 0 15 #rect
Ee0 f12 @|EndSubIcon #fIcon
Ee0 f13 inParamDecl '<> param;' #txt
Ee0 f13 outParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting ivyEmailSetting> result;' #txt
Ee0 f13 outParamTable 'result.ivyEmailSetting=in.ivyEmailSetting;
' #txt
Ee0 f13 callSignature findEmailSetting() #txt
Ee0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findEmailSetting()</name>
    </language>
</elementInfo>
' #txt
Ee0 f13 65 33 30 30 -55 19 #rect
Ee0 f13 @|StartSubIcon #fIcon
Ee0 f8 95 48 168 48 #arcP
Ee0 f15 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting emailSetting> param;' #txt
Ee0 f15 inParamTable 'out.ivyEmailSetting=param.emailSetting;
' #txt
Ee0 f15 outParamDecl '<> result;' #txt
Ee0 f15 callSignature saveEmailSetting(ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting) #txt
Ee0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveEmailSetting(IvyEmailSetting)</name>
    </language>
</elementInfo>
' #txt
Ee0 f15 65 113 30 30 -63 20 #rect
Ee0 f15 @|StartSubIcon #fIcon
Ee0 f18 369 113 30 30 0 15 #rect
Ee0 f18 @|EndSubIcon #fIcon
Ee0 f19 actionTable 'out=in;
' #txt
Ee0 f19 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

IvyEmailSettingResultDTO dto = EmailSettingService.newInstance().saveEmailSetting(in.ivyEmailSetting);
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
Ee0 f23 expr out #txt
Ee0 f23 95 128 168 128 #arcP
Ee0 f0 expr out #txt
Ee0 f0 280 48 369 48 #arcP
Ee0 f1 expr out #txt
Ee0 f1 280 128 369 128 #arcP
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
Ee0 f13 mainOut f8 tail #connect
Ee0 f8 head f9 mainIn #connect
Ee0 f15 mainOut f23 tail #connect
Ee0 f23 head f19 mainIn #connect
Ee0 f9 mainOut f0 tail #connect
Ee0 f0 head f12 mainIn #connect
Ee0 f19 mainOut f1 tail #connect
Ee0 f1 head f18 mainIn #connect
