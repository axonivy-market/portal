[Ivy]
15FA4CC33E5D866A 3.20 #module
>Proto >Proto Collection #zClass
Ls0 LoadSubMenuItems Big #zClass
Ls0 B #cInfo
Ls0 #process
Ls0 @TextInP .resExport .resExport #zField
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
Ls0 f0 outParamDecl '<List<ch.addon.portal.generic.menu.SubMenuItem> subMenuItems> result;
' #txt
Ls0 f0 outParamTable 'result.subMenuItems=in.subMenuItems;
' #txt
Ls0 f0 actionDecl 'ch.ivy.addon.portal.generic.LoadSubMenuItemsData out;
' #txt
Ls0 f0 callSignature loadSubMenuItems() #txt
Ls0 f0 type ch.ivy.addon.portal.generic.LoadSubMenuItemsData #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadSubMenuItems()</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 81 137 30 30 16 11 #rect
Ls0 f0 @|StartSubIcon #fIcon
Ls0 f1 type ch.ivy.addon.portal.generic.LoadSubMenuItemsData #txt
Ls0 f1 81 361 30 30 0 15 #rect
Ls0 f1 @|EndSubIcon #fIcon
Ls0 f3 actionDecl 'ch.ivy.addon.portal.generic.LoadSubMenuItemsData out;
' #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.addon.portal.generic.menu.DashboardSubMenuItem;
import ch.addon.portal.generic.menu.ProcessSubMenuItem;
import ch.addon.portal.generic.menu.CaseSubMenuItem;
import ch.addon.portal.generic.menu.TaskSubMenuItem;

in.subMenuItems.add(new ProcessSubMenuItem());
in.subMenuItems.add(new TaskSubMenuItem());
in.subMenuItems.add(new CaseSubMenuItem());

GlobalSettingService globalSettingService = new GlobalSettingService();
boolean isHideStatistic = globalSettingService.findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_STATISTIC_WIDGET);
if (!isHideStatistic) {
  in.subMenuItems.add(new DashboardSubMenuItem());
}' #txt
Ls0 f3 type ch.ivy.addon.portal.generic.LoadSubMenuItemsData #txt
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
- http://www.yourexternallink.com</name>
        <nameStyle>946,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f5 296 10 656 476 -321 -232 #rect
Ls0 f5 @|IBIcon #fIcon
Ls0 f6 296 248 152 248 #arcP
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
