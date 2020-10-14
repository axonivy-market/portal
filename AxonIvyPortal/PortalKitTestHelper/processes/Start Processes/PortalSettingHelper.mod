[Ivy]
17208192E0AF4185 9.2.0 #module
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
Pr0 @GridStep f5 '' #zField
Pr0 @PushWFArc f6 '' #zField
Pr0 @PushWFArc f2 '' #zField
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
Pr0 f0 @|StartRequestIcon #fIcon
Pr0 f1 521 49 30 30 0 15 #rect
Pr0 f1 @|EndIcon #fIcon
Pr0 f3 actionTable 'out=in;
' #txt
Pr0 f3 actionCode 'import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
GlobalSetting globalSetting = globalSettingService.findGlobalSettingByKey(in.settingName);
globalSetting.setValue(in.settingValue);
globalSettingService.save(globalSetting);
IvyCacheService.newInstance().cacheGlobalSetting(in.settingName, in.settingValue);' #txt
Pr0 f3 security system #txt
Pr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update Portal Setting</name>
    </language>
</elementInfo>
' #txt
Pr0 f3 160 42 128 44 -58 -8 #rect
Pr0 f3 @|StepIcon #fIcon
Pr0 f4 111 64 160 64 #arcP
Pr0 f5 actionTable 'out=in;
' #txt
Pr0 f5 actionCode 'import ch.ivy.addon.portalkit.service.IvyCacheService;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
GlobalSetting savedSetting = null;

// Check settting is updated, try 10 times
for (int i = 0; i<=10; i++) {
	// Find Setting without via CacheServer
	for (GlobalSetting globalSetting : globalSettingService.findAll()) {
		if (StringUtils.equals(globalSetting.getKey(), in.settingName)) {
	 		savedSetting = globalSetting;
			break;
		}
	}

	if (StringUtils.equalsIgnoreCase(savedSetting.value, in.settingValue)) {
		break;
	} else {
		savedSetting.setValue(in.settingValue);
		globalSettingService.save(savedSetting);
		IvyCacheService.newInstance().cacheGlobalSetting(in.settingName, in.settingValue);
	}

	Thread.sleep(200); // make Portal Setting updated more stable
}' #txt
Pr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check Setting is effected</name>
    </language>
</elementInfo>
' #txt
Pr0 f5 336 42 144 44 -67 -8 #rect
Pr0 f5 @|StepIcon #fIcon
Pr0 f6 288 64 336 64 #arcP
Pr0 f2 480 64 521 64 #arcP
>Proto Pr0 .type portalKit_test.PortalSettingHelperData #txt
>Proto Pr0 .processKind NORMAL #txt
>Proto Pr0 0 0 32 24 18 0 #rect
>Proto Pr0 @|BIcon #fIcon
Pr0 f0 mainOut f4 tail #connect
Pr0 f4 head f3 mainIn #connect
Pr0 f3 mainOut f6 tail #connect
Pr0 f6 head f5 mainIn #connect
Pr0 f5 mainOut f2 tail #connect
Pr0 f2 head f1 mainIn #connect
