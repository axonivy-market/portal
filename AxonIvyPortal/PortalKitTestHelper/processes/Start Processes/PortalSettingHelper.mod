[Ivy]
17208192E0AF4185 9.4.0 #module
>Proto >Proto Collection #zClass
Pr0 PortalSettingHelper Big #zClass
Pr0 B #cInfo
Pr0 #process
Pr0 @TextInP .type .type #zField
Pr0 @TextInP .processKind .processKind #zField
Pr0 @TextInP .xml .xml #zField
Pr0 @TextInP .responsibility .responsibility #zField
Pr0 @StartRequest f0 '' #zField
Pr0 @EndTask f1 '' #zField
Pr0 @GridStep f3 '' #zField
Pr0 @PushWFArc f4 '' #zField
Pr0 @StartRequest f7 '' #zField
Pr0 @EndTask f8 '' #zField
Pr0 @GridStep f10 '' #zField
Pr0 @PushWFArc f11 '' #zField
Pr0 @PushWFArc f9 '' #zField
Pr0 @PushWFArc f12 '' #zField
>Proto Pr0 Pr0 PortalSettingHelper #zField
Pr0 f0 outLink updatePortalSetting.ivp #txt
Pr0 f0 inParamDecl '<String settingName,String settingValue> param;' #txt
Pr0 f0 inParamTable 'out.settingName=param.settingName;
out.settingValue=param.settingValue;
' #txt
Pr0 f0 requestEnabled true #txt
Pr0 f0 triggerEnabled false #txt
Pr0 f0 callSignature updatePortalSetting(String,String) #txt
Pr0 f0 caseData businessCase.attach=true #txt
Pr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePortalSetting.ivp</name>
    </language>
</elementInfo>
' #txt
Pr0 f0 @C|.responsibility Everybody #txt
Pr0 f0 81 49 30 30 -66 23 #rect
Pr0 f1 521 49 30 30 0 15 #rect
Pr0 f3 actionTable 'out=in;
' #txt
Pr0 f3 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
GlobalSetting globalSetting = globalSettingService.findGlobalSettingByGlobalVariable(GlobalVariable.valueOfKey(in.settingName));
globalSetting.setValue(in.settingValue);
globalSettingService.save(globalSetting);
' #txt
Pr0 f3 security system #txt
Pr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update Portal Setting</name>
    </language>
</elementInfo>
' #txt
Pr0 f3 160 42 128 44 -58 -8 #rect
Pr0 f4 111 64 160 64 #arcP
Pr0 f7 outLink cleanSessionCache.ivp #txt
Pr0 f7 inParamDecl '<> param;' #txt
Pr0 f7 requestEnabled true #txt
Pr0 f7 triggerEnabled false #txt
Pr0 f7 callSignature cleanSessionCache() #txt
Pr0 f7 caseData businessCase.attach=true #txt
Pr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanSessionCache.ivp</name>
    </language>
</elementInfo>
' #txt
Pr0 f7 @C|.responsibility Everybody #txt
Pr0 f7 81 177 30 30 -56 19 #rect
Pr0 f8 465 177 30 30 0 15 #rect
Pr0 f10 actionTable 'out=in;
' #txt
Pr0 f10 actionCode ivy.datacache.getSessionCache().invalidate(); #txt
Pr0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clean session cache</name>
    </language>
</elementInfo>
' #txt
Pr0 f10 160 170 128 44 -58 -8 #rect
Pr0 f11 111 192 160 192 #arcP
Pr0 f9 288 192 465 192 #arcP
Pr0 f12 288 64 521 64 #arcP
>Proto Pr0 .type portalKit_test.PortalSettingHelperData #txt
>Proto Pr0 .processKind NORMAL #txt
>Proto Pr0 0 0 32 24 18 0 #rect
>Proto Pr0 @|BIcon #fIcon
Pr0 f0 mainOut f4 tail #connect
Pr0 f4 head f3 mainIn #connect
Pr0 f7 mainOut f11 tail #connect
Pr0 f11 head f10 mainIn #connect
Pr0 f10 mainOut f9 tail #connect
Pr0 f9 head f8 mainIn #connect
Pr0 f3 mainOut f12 tail #connect
Pr0 f12 head f1 mainIn #connect
