[Ivy]
15FA4CC33E5D866A 7.5.0 #module
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
Ls0 f0 @|StartSubIcon #fIcon
Ls0 f1 81 361 30 30 0 15 #rect
Ls0 f1 @|EndSubIcon #fIcon
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 actionCode 'import org.apache.commons.lang3.StringUtils;
import Boolean;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.addon.portal.generic.menu.DashboardSubMenuItem;
import ch.addon.portal.generic.menu.ProcessSubMenuItem;
import ch.addon.portal.generic.menu.CaseSubMenuItem;
import ch.addon.portal.generic.menu.TaskSubMenuItem;

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
  	in.subMenuItems.add(new DashboardSubMenuItem());
	}
}' #txt
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
Ls0 f3 @|StepIcon #fIcon
Ls0 f4 expr out #txt
Ls0 f4 96 167 96 226 #arcP
Ls0 f2 expr out #txt
Ls0 f2 96 270 96 361 #arcP
Ls0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TO REMOVE A DEFAULT SUB MENU ITEM:
Remove  one of these lines:
in.subMenuItems.add(new ProcessSubMenuItem());
in.subMenuItems.add(new TaskSubMenuItem());
in.subMenuItems.add(new CaseSubMenuItem());
in.subMenuItems.add(new DashboardSubMenuItem());

PERMISSION CHECK TO SEE SUB MENU ITEM
In PermissionUtils we provide some methods to check permission to see sub menu item
- checkAccessFullProcessListPermission() for Process list sub menu item
- checkAccessFullTaskListPermission() for Task list sub menu item
- checkAccessFullCaseListPermission() for Case list sub menu item
- checkAccessFullStatisticsListPermission() for Statistics sub menu item


TO CREATE A SUB MENU ITEM:

SubMenuItem subMenuItem = new SubMenuItem();
subMenuItem.setMenuKind(MenuKind.CUSTOM);
subMenuItem.setIcon(&lt;SUB_MENU_ICON&gt;);
subMenuItem.setLabel(&lt;SUB_MENU_LABEL&gt;);
subMenuItem.setLink(&lt;SUB_MENU_LINK&gt;);

//add file names of pages where the menu item will be highlighted e.g selfService.getViews().add("PortalHome.xhtml")
selfService.getViews().add(&lt;PAGE_TO_BE_HIGHLIGHT&gt;);

in.subMenuItems.add(subMenuItem);

OUT: subMenuItems: List&lt;SubMenuItem&gt;

HINT: how to build a menu link
Axon.Ivy link
- Absolute path: ivy.html.startref(...)
- Relative path: RequestUriFactory.createProcessStartUri(...)
External link: 
- www.yourexternallink.com
- http://www.yourexternallink.com&#xD;
&#xD;
NOTE:&#xD;
If you want to hide Statistic widget, please copy these line of code into your overrided process&#xD;
&#xD;
GlobalSettingService globalSettingService = new GlobalSettingService();&#xD;
String isHideStatisticStr = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET.toString());&#xD;
boolean isHideStatistic = StringUtils.isNotBlank(isHideStatisticStr) ? Boolean.parseBoolean(isHideStatisticStr) : false;&#xD;
if (!isHideStatistic) {&#xD;
  in.subMenuItems.add(new DashboardSubMenuItem());&#xD;
}</name>
        <nameStyle>1854,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f5 280 10 688 764 -339 -376 #rect
Ls0 f5 @|IBIcon #fIcon
Ls0 f6 280 392 152 248 #arcP
Ls0 f6 1 280 248 #addKink
Ls0 f6 1 0.22725934349270996 0 0 #arcLabel
>Proto Ls0 .type ch.ivy.addon.portal.generic.LoadSubMenuItemsData #txt
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
