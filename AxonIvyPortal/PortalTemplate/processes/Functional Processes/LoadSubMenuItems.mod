[Ivy]
15FA4CC33E5D866A 9.2.0 #module
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
import ch.addon.portal.generic.menu.StatisticSubMenuItem;
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
  	in.subMenuItems.add(new StatisticSubMenuItem());
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
        <name>TO REMOVE A DEFAULT SUB MENU ITEM:&#13;
Remove  one of these lines:&#13;
in.subMenuItems.add(new ProcessSubMenuItem());&#13;
in.subMenuItems.add(new TaskSubMenuItem());&#13;
in.subMenuItems.add(new CaseSubMenuItem());&#13;
in.subMenuItems.add(new StatisticSubMenuItem());&#13;
&#13;
PERMISSION CHECK TO SEE SUB MENU ITEM&#13;
In PermissionUtils we provide some methods to check permission to see sub menu item&#13;
- checkAccessFullProcessListPermission() for Process list sub menu item&#13;
- checkAccessFullTaskListPermission() for Task list sub menu item&#13;
- checkAccessFullCaseListPermission() for Case list sub menu item&#13;
- checkAccessFullStatisticsListPermission() for Statistics sub menu item&#13;
&#13;
&#13;
TO CREATE A SUB MENU ITEM:&#13;
&#13;
SubMenuItem subMenuItem = new SubMenuItem();&#13;
subMenuItem.setMenuKind(&lt;MenuKind.CUSTOM&gt; or &lt;MenuKind.EXTERNAL_LINK&gt;);&#13;
subMenuItem.setIcon(&lt;SUB_MENU_ICON&gt;);&#13;
subMenuItem.setLabel(&lt;SUB_MENU_LABEL&gt;);&#13;
subMenuItem.setLink(&lt;SUB_MENU_LINK&gt;);&#13;
&#13;
in.subMenuItems.add(subMenuItem);&#13;
&#13;
OUT: subMenuItems: List&lt;SubMenuItem&gt;&#13;
&#13;
HINT: how to build a menu link&#13;
Axon.Ivy link: IProcessStart#getLink() to get absolute/relative path (Refer to Axon.ivy Public API document)&#13;
External link: &#13;
- www.yourexternallink.com&#13;
- http://www.yourexternallink.com&#13;
&#13;
NOTE:&#13;
If you want to hide Statistic widget, please copy these line of code into your overrided process&#13;
&#13;
GlobalSettingService globalSettingService = new GlobalSettingService();&#13;
String isHideStatisticStr = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET.toString());&#13;
boolean isHideStatistic = StringUtils.isNotBlank(isHideStatisticStr) ? Boolean.parseBoolean(isHideStatisticStr) : false;&#13;
if (!isHideStatistic) {&#13;
  in.subMenuItems.add(new StatisticSubMenuItem());&#13;
}</name>
    </language>
</elementInfo>
' #txt
Ls0 f5 296 50 656 684 -323 -336 #rect
Ls0 f5 @|IBIcon #fIcon
Ls0 f6 296 392 96 270 #arcP
Ls0 f6 0 0.27018937996533715 0 0 #arcLabel
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
