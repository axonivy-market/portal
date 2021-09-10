[Ivy]
1657E6622F0C0122 9.3.0 #module
>Proto >Proto Collection #zClass
Ls0 LoadSubMenuItems Big #zClass
Ls0 B #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @AnnotationInP-0n ai ai #zField
Ls0 @MessageFlowInP-0n messageIn messageIn #zField
Ls0 @MessageFlowOutP-0n messageOut messageOut #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @StartSub f0 '' #zField
Ls0 @EndSub f1 '' #zField
Ls0 @GridStep f3 '' #zField
Ls0 @PushWFArc f4 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @InfoButton f5 '' #zField
Ls0 @AnnotationArc f6 '' #zField
>Proto Ls0 Ls0 LoadSubMenuItems #zField
Ls0 f0 inParamDecl '<> param;' #txt
Ls0 f0 outParamDecl '<List<ch.addon.portal.generic.menu.SubMenuItem> subMenuItems> result;' #txt
Ls0 f0 outParamTable 'result.subMenuItems=in.subMenuItems;
' #txt
Ls0 f0 callSignature loadSubMenuItems() #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadSubMenuItems()</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 81 137 30 30 16 11 #rect
Ls0 f1 81 361 30 30 0 15 #rect
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 actionCode 'import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;

import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.addon.portal.generic.menu.StatisticSubMenuItem;
import ch.addon.portal.generic.menu.ProcessSubMenuItem;
import ch.addon.portal.generic.menu.CaseSubMenuItem;
import ch.addon.portal.generic.menu.TaskSubMenuItem;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.addon.portal.generic.menu.SubMenuItem;

if(PermissionUtils.checkAccessFullProcessListPermission()) {
	in.subMenuItems.add(new ProcessSubMenuItem());
}
if(PermissionUtils.checkAccessFullTaskListPermission()) {
	in.subMenuItems.add(new TaskSubMenuItem());
}
if(PermissionUtils.checkAccessFullCaseListPermission()) {
	in.subMenuItems.add(new CaseSubMenuItem());
}
if(PermissionUtils.checkAccessFullStatisticsListPermission()) {
	GlobalSettingService globalSettingService = new GlobalSettingService();
	String isHideStatisticStr = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET.toString());
	boolean isHideStatistic = StringUtils.isNotBlank(isHideStatisticStr) ? Boolean.parseBoolean(isHideStatisticStr) : false;
	if (!isHideStatistic) {
  	in.subMenuItems.add(new StatisticSubMenuItem());
	}
}


String userExampleGuideLink = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("Start Processes/UserExampleGuide/userExampleGuide.ivp");
if (!StringUtils.isEmpty(userExampleGuideLink)){	
	SubMenuItem userExampleGuide = new SubMenuItem();
	userExampleGuide.setIcon("si si-bulb");
	userExampleGuide.setName("ExampleGuide");
	userExampleGuide.setLabel("User example guide");
	userExampleGuide.setMenuKind(MenuKind.CUSTOM);
	userExampleGuide.setLink(userExampleGuideLink);
	in.subMenuItems.add(userExampleGuide);
}

SubMenuItem google = new SubMenuItem();
google.setIcon("si si-information-circle");
google.setLabel("Google");
google.setMenuKind(MenuKind.EXTERNAL_LINK);
google.setLink("www.google.com");
in.subMenuItems.add(google);' #txt
Ls0 f3 security system #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load menu items</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f3 40 226 112 44 -48 -8 #rect
Ls0 f4 expr out #txt
Ls0 f4 96 167 96 226 #arcP
Ls0 f2 expr out #txt
Ls0 f2 96 270 96 361 #arcP
Ls0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides LoadSubMenuItems process in Portal Template and add 1 menu item:
Google: open external link www.google.com.</name>
        <nameStyle>130,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f5 248 226 528 44 -257 -16 #rect
Ls0 f6 248 248 152 248 #arcP
>Proto Ls0 .type _com.axonivy.portal.developerexamples.LoadSubMenuItemsOverrideData #txt
>Proto Ls0 .processKind CALLABLE_SUB #txt
>Proto Ls0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ls0 0 0 32 24 18 0 #rect
>Proto Ls0 @|BIcon #fIcon
Ls0 f0 mainOut f4 tail #connect
Ls0 f4 head f3 mainIn #connect
Ls0 f3 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f5 ao f6 tail #connect
Ls0 f6 head f3 @CG|ai #connect
