[Ivy]
156A1AA176DE2A21 3.26 #module
>Proto >Proto Collection #zClass
As0 ApplicationSelectionMenuProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
As0 @TextInP .rdData2UIAction .rdData2UIAction #zField
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @GridStep f67 '' #zField
As0 @Alternative f70 '' #zField
As0 @RichDialogMethodStart f72 '' #zField
As0 @RichDialogInitStart f74 '' #zField
As0 @Alternative f76 '' #zField
As0 @RichDialogProcessEnd f81 '' #zField
As0 @GridStep f83 '' #zField
As0 @RichDialogProcessEnd f85 '' #zField
As0 @RichDialogInitStart f87 '' #zField
As0 @GridStep f91 '' #zField
As0 @GridStep f92 '' #zField
As0 @GridStep f94 '' #zField
As0 @GridStep f95 '' #zField
As0 @CallSub f97 '' #zField
As0 @Alternative f98 '' #zField
As0 @PushWFArc f109 '' #zField
As0 @PushWFArc f110 '' #zField
As0 @PushWFArc f111 '' #zField
As0 @PushWFArc f112 '' #zField
As0 @PushWFArc f114 '' #zField
As0 @PushWFArc f122 '' #zField
As0 @PushWFArc f125 '' #zField
As0 @PushWFArc f129 '' #zField
As0 @RichDialogProcessStart f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f3 '' #zField
As0 @RichDialogMethodStart f7 '' #zField
As0 @GridStep f14 '' #zField
As0 @PushWFArc f13 '' #zField
As0 @PushWFArc f16 '' #zField
As0 @RichDialogProcessStart f18 '' #zField
As0 @RichDialogProcessStart f19 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @GridStep f10 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @GridStep f15 '' #zField
As0 @PushWFArc f22 '' #zField
As0 @PushWFArc f12 '' #zField
As0 @RichDialogProcessEnd f34 '' #zField
As0 @GridStep f17 '' #zField
As0 @PushWFArc f27 '' #zField
As0 @PushWFArc f28 '' #zField
As0 @GridStep f31 '' #zField
As0 @PushWFArc f35 '' #zField
As0 @PushWFArc f37 '' #zField
As0 @PushWFArc f0 '' #zField
As0 @PushWFArc f21 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @PushWFArc f24 '' #zField
As0 @PushWFArc f25 '' #zField
As0 @PushWFArc f115 '' #zField
As0 @PushWFArc f6 '' #zField
As0 @RichDialogProcessEnd f5 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @PushWFArc f8 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f67 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f67 actionTable 'out=in;
' #txt
As0 f67 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.selectedSubMenuItem.getExternalLink());' #txt
As0 f67 security system #txt
As0 f67 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to custom
menu link</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f67 484 512 120 48 -48 -16 #rect
As0 f67 @|StepIcon #fIcon
As0 f70 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f70 848 368 32 32 0 16 #rect
As0 f70 @|AlternativeIcon #fIcon
As0 f72 guid 15FB36E87007F717 #txt
As0 f72 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f72 method openApp(String) #txt
As0 f72 disableUIEvents false #txt
As0 f72 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String appUrl> param = methodEvent.getInputArguments();
' #txt
As0 f72 inParameterMapAction 'out.applicationUrl=param.appUrl;
out.selectedSubMenuItem=null;
' #txt
As0 f72 outParameterDecl '<> result;
' #txt
As0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openApp(String)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f72 1013 21 22 22 14 0 #rect
As0 f72 @|RichDialogMethodStartIcon #fIcon
As0 f74 guid 15FB36E8701C0FCF #txt
As0 f74 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f74 method StartNotRequiredLogin() #txt
As0 f74 disableUIEvents true #txt
As0 f74 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f74 inParameterMapAction 'out.isNotRequiredLogin=true;
' #txt
As0 f74 outParameterDecl '<> result;
' #txt
As0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>StartNotRequiredLogin()</name>
    </language>
</elementInfo>
' #txt
As0 f74 68 20 24 24 14 0 #rect
As0 f74 @|RichDialogInitStartIcon #fIcon
As0 f76 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f76 1008 176 32 32 0 16 #rect
As0 f76 @|AlternativeIcon #fIcon
As0 f81 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f81 1171 275 26 26 0 12 #rect
As0 f81 @|RichDialogProcessEndIcon #fIcon
As0 f83 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f83 actionTable 'out=in;
' #txt
As0 f83 actionCode 'import org.primefaces.context.RequestContext;

RequestContext.getCurrentInstance().execute("PF(''task-losing-confirmation-dialog'').show()");' #txt
As0 f83 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f83 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display warning 
dialog</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f83 960 266 128 44 -42 -16 #rect
As0 f83 @|StepIcon #fIcon
As0 f85 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f85 228 244 24 24 13 0 #rect
As0 f85 @|RichDialogProcessEndIcon #fIcon
As0 f87 guid 15FB36E87031CAD2 #txt
As0 f87 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f87 method start(String) #txt
As0 f87 disableUIEvents true #txt
As0 f87 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String isWorkingOnATask> param = methodEvent.getInputArguments();
' #txt
As0 f87 inParameterMapAction 'out.isWorkingOnATask=Boolean.parseBoolean(param.isWorkingOnATask);
' #txt
As0 f87 outParameterDecl '<> result;
' #txt
As0 f87 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f87 228 20 24 24 13 0 #rect
As0 f87 @|RichDialogInitStartIcon #fIcon
As0 f91 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f91 actionTable 'out=in;
' #txt
As0 f91 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(ivy.task);' #txt
As0 f91 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserve task</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f91 816 92 96 40 -29 -6 #rect
As0 f91 @|StepIcon #fIcon
As0 f92 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f92 actionTable 'out=in;
' #txt
As0 f92 actionCode 'import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import org.primefaces.component.button.Button;
import ch.ivyteam.ivy.server.ServerFactory;

in.menuItems.clear();
RegisteredApplicationService applicationService = new RegisteredApplicationService();
int numberOfIvyApplications = applicationService.countIvyApplications(in.applications);
SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP.toString());
SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());

for (Application application : in.applications){
	Button menuItem = new Button();
	menuItem.value = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
	boolean isThirdPartyApp = !application.#serverId is initialized;
	if (isThirdPartyApp){
		menuItem.getAttributes().put("isThirdPartyApp", true);
	}
	menuItem.href = application.link;
	menuItem.getAttributes().put("appName", application.name);
	menuItem.icon = "fa " + application.menuIcon;
	
	in.menuItems.add(menuItem);
	if (application.name.equals(ivy.request.getApplication().getName()) || (!isThirdPartyApp && numberOfIvyApplications == 1)) {
		menuItem.styleClass = "ivy-active";
		SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP.toString(), application.name);
		SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString(), application.displayName);
	}
}

if (numberOfIvyApplications > 1 || numberOfIvyApplications == 0) {
	Button menuItem = new Button();
	menuItem.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/dashboard");
	menuItem.icon = "fa fa-home";
	if (numberOfIvyApplications == 0) {
		menuItem.href = new PortalNavigator().getPortalStartUrlOfCurrentApplication();
		menuItem.styleClass="ivy-active";
		in.menuItems.add(0, menuItem);
	} else {
		IApplication portal = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
		if (portal != null && portal.getActivityState() != ch.ivyteam.ivy.application.ActivityState.INACTIVE && portal.getSecurityContext().findUser(ivy.session.getSessionUserName()) != null) {
			menuItem.href = SecurityServiceUtils.getDefaultPortalStartUrl();
			if (PortalConstants.PORTAL_APPLICATION_NAME.equals(ivy.request.getApplication().getName())) {
				menuItem.styleClass = "ivy-active";
			}
			in.menuItems.add(0, menuItem);
		}
	}
}' #txt
As0 f92 security system #txt
As0 f92 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f92 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Convert menu items</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f92 452 112 56 32 39 -14 #rect
As0 f92 @|StepIcon #fIcon
As0 f94 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f94 actionTable 'out=in;
' #txt
As0 f94 actionCode 'import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import java.util.Collections;

Collections.sort(in.applications, new ApplicationIndexAscendingComparator());' #txt
As0 f94 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort applications</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f94 216 176 48 32 33 -8 #rect
As0 f94 @|StepIcon #fIcon
As0 f95 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f95 actionTable 'out=in;
' #txt
As0 f95 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.applicationUrl);' #txt
As0 f95 security system #txt
As0 f95 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f95 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to
selected app</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f95 644 512 120 48 -33 -16 #rect
As0 f95 @|StepIcon #fIcon
As0 f97 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f97 processCall 'Business Processes/FindApplicationsByUser:findApplicationsByUser(String)' #txt
As0 f97 doCall true #txt
As0 f97 requestActionDecl '<java.lang.String username> param;
' #txt
As0 f97 requestMappingAction 'param.username=ivy.session.getSessionUserName();
' #txt
As0 f97 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f97 responseMappingAction 'out=in;
out.applications=result.applications;
' #txt
As0 f97 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findApplicationsByUser(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f97 216 96 48 32 38 -3 #rect
As0 f97 @|CallSubIcon #fIcon
As0 f98 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f98 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
As0 f98 850 178 28 28 14 0 #rect
As0 f98 @|AlternativeIcon #fIcon
As0 f109 expr in #txt
As0 f109 outCond in.isWorkingOnATask #txt
As0 f109 1024 208 1024 266 #arcP
As0 f110 expr in #txt
As0 f110 outCond 'in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.CUSTOM' #txt
As0 f110 848 384 544 512 #arcP
As0 f110 1 544 384 #addKink
As0 f110 0 0.8608001985669408 0 0 #arcLabel
As0 f111 expr out #txt
As0 f111 1024 43 1024 176 #arcP
As0 f111 0 0.8554291471631579 0 0 #arcLabel
As0 f112 expr in #txt
As0 f112 outCond '!in.#selectedSubMenuItem is initialized' #txt
As0 f112 853 389 704 512 #arcP
As0 f112 1 704 448 #addKink
As0 f112 0 0.8119251469460217 0 0 #arcLabel
As0 f114 expr out #txt
As0 f114 1088 288 1171 288 #arcP
As0 f114 0 0.2964491651298413 0 0 #arcLabel
As0 f122 expr out #txt
As0 f122 864 132 864 178 #arcP
As0 f125 expr in #txt
As0 f125 1008 192 878 192 #arcP
As0 f129 expr out #txt
As0 f129 240 128 240 176 #arcP
As0 f1 guid 15FB83C392F10C9D #txt
As0 f1 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f1 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f1 actionTable 'out=in;
' #txt
As0 f1 actionCode 'import ch.addon.portal.generic.menu.SubMenuItem;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
in.selectedSubMenuItem = commandLink.getAttributes().get("selectedSubMenuItem") as SubMenuItem;' #txt
As0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickSubMenuItem</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f1 1171 19 26 26 16 -3 #rect
As0 f1 @|RichDialogProcessStartIcon #fIcon
As0 f2 expr out #txt
As0 f2 1184 45 1040 192 #arcP
As0 f2 1 1184 192 #addKink
As0 f2 1 0.16104556874251277 0 0 #arcLabel
As0 f3 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import java.util.Arrays;
import ch.addon.portal.generic.menu.SubMenuItem;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import java.util.Map;
Map response = IvyAdapterService.startSubProcess("loadSubMenuItems()", null, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
in.subMenuItems = response.get("subMenuItems") as List<SubMenuItem>;' #txt
As0 f3 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load sub menu items</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f3 456 176 48 32 31 -9 #rect
As0 f3 @|StepIcon #fIcon
As0 f7 guid 15FDC9D01571BA80 #txt
As0 f7 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f7 method init() #txt
As0 f7 disableUIEvents false #txt
As0 f7 inParameterDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f7 outParameterDecl '<> result;
' #txt
As0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init()</name>
    </language>
</elementInfo>
' #txt
As0 f7 467 19 26 26 -13 17 #rect
As0 f7 @|RichDialogMethodStartIcon #fIcon
As0 f14 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f14 actionTable 'out=in;
' #txt
As0 f14 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(ivy.task);
' #txt
As0 f14 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave and &#xD;
reset task</name>
    </language>
</elementInfo>
' #txt
As0 f14 648 92 112 40 -30 -16 #rect
As0 f14 @|StepIcon #fIcon
As0 f13 expr out #txt
As0 f13 704 132 850 192 #arcP
As0 f13 1 704 192 #addKink
As0 f13 1 0.302020000020303 0 0 #arcLabel
As0 f16 expr in #txt
As0 f16 864 206 864 368 #arcP
As0 f18 guid 163FD88EDB522F75 #txt
As0 f18 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f18 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f18 actionTable 'out=in;
' #txt
As0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f18 691 19 26 26 21 -1 #rect
As0 f18 @|RichDialogProcessStartIcon #fIcon
As0 f19 guid 163FD891A0AB1B03 #txt
As0 f19 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f19 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f19 actionTable 'out=in;
' #txt
As0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f19 851 19 26 26 21 -4 #rect
As0 f19 @|RichDialogProcessStartIcon #fIcon
As0 f20 expr out #txt
As0 f20 864 45 864 92 #arcP
As0 f10 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f10 actionTable 'out=in;
' #txt
As0 f10 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalProcess();' #txt
As0 f10 security system #txt
As0 f10 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to process</name>
    </language>
</elementInfo>
' #txt
As0 f10 804 512 120 48 -51 -8 #rect
As0 f10 @|StepIcon #fIcon
As0 f11 expr in #txt
As0 f11 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.PROCESS' #txt
As0 f11 864 400 864 512 #arcP
As0 f11 0 0.32034598197015046 0 0 #arcLabel
As0 f15 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f15 actionTable 'out=in;
' #txt
As0 f15 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalStatistic();' #txt
As0 f15 security system #txt
As0 f15 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to statistic</name>
    </language>
</elementInfo>
' #txt
As0 f15 968 512 112 48 -49 -8 #rect
As0 f15 @|StepIcon #fIcon
As0 f22 expr in #txt
As0 f22 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.DASHBOARD' #txt
As0 f22 875 389 1024 512 #arcP
As0 f22 1 1024 448 #addKink
As0 f22 0 0.6303319079715384 0 0 #arcLabel
As0 f12 expr out #txt
As0 f12 704 45 704 92 #arcP
As0 f34 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f34 851 691 26 26 0 12 #rect
As0 f34 @|RichDialogProcessEndIcon #fIcon
As0 f17 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f17 actionTable 'out=in;
' #txt
As0 f17 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalTask();' #txt
As0 f17 security system #txt
As0 f17 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to task list</name>
    </language>
</elementInfo>
' #txt
As0 f17 1276 512 136 48 -52 -8 #rect
As0 f17 @|StepIcon #fIcon
As0 f27 expr in #txt
As0 f27 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.TASK' #txt
As0 f27 880 384 1344 512 #arcP
As0 f27 1 1344 384 #addKink
As0 f27 0 0.5801593067057396 0 0 #arcLabel
As0 f28 expr out #txt
As0 f28 1344 560 877 704 #arcP
As0 f28 1 1344 704 #addKink
As0 f28 1 0.35157960906624564 0 0 #arcLabel
As0 f31 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f31 actionTable 'out=in;
' #txt
As0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalCase();' #txt
As0 f31 security system #txt
As0 f31 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to case list</name>
    </language>
</elementInfo>
' #txt
As0 f31 1124 512 120 48 -53 -8 #rect
As0 f31 @|StepIcon #fIcon
As0 f35 expr in #txt
As0 f35 877 387 1184 512 #arcP
As0 f35 1 1184 448 #addKink
As0 f35 0 0.751679304478716 0 0 #arcLabel
As0 f37 expr out #txt
As0 f37 1184 560 876 701 #arcP
As0 f37 1 1184 640 #addKink
As0 f37 1 0.9030104071006827 0 0 #arcLabel
As0 f0 expr out #txt
As0 f0 544 560 851 704 #arcP
As0 f0 1 544 704 #addKink
As0 f0 0 0.9979701176976735 0 0 #arcLabel
As0 f21 expr out #txt
As0 f21 704 560 851 699 #arcP
As0 f21 1 704 640 #addKink
As0 f21 1 0.027724139484003176 0 0 #arcLabel
As0 f23 expr out #txt
As0 f23 864 560 864 691 #arcP
As0 f23 0 0.6931429895536669 0 0 #arcLabel
As0 f24 expr out #txt
As0 f24 1024 560 876 699 #arcP
As0 f24 1 1024 640 #addKink
As0 f24 1 0.2637121995195685 0 0 #arcLabel
As0 f25 expr out #txt
As0 f25 80 44 228 256 #arcP
As0 f25 1 80 256 #addKink
As0 f25 0 0.8605300296400707 0 0 #arcLabel
As0 f115 expr out #txt
As0 f115 240 44 240 96 #arcP
As0 f6 expr out #txt
As0 f6 240 208 240 244 #arcP
As0 f5 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f5 467 243 26 26 0 12 #rect
As0 f5 @|RichDialogProcessEndIcon #fIcon
As0 f9 expr out #txt
As0 f9 480 208 480 243 #arcP
As0 f4 expr out #txt
As0 f4 480 45 480 112 #arcP
As0 f8 expr out #txt
As0 f8 480 144 480 176 #arcP
>Proto As0 .type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>startMethods</swimlaneLabel>
        <swimlaneLabel>
</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>440</swimlaneSize>
    <swimlaneSize>1504</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-3355393</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f97 mainOut f129 tail #connect
As0 f129 head f94 mainIn #connect
As0 f87 mainOut f115 tail #connect
As0 f115 head f97 mainIn #connect
As0 f91 mainOut f122 tail #connect
As0 f122 head f98 in #connect
As0 f72 mainOut f111 tail #connect
As0 f111 head f76 in #connect
As0 f76 out f109 tail #connect
As0 f109 head f83 mainIn #connect
As0 f70 out f112 tail #connect
As0 f112 head f95 mainIn #connect
As0 f76 out f125 tail #connect
As0 f125 head f98 in #connect
As0 f83 mainOut f114 tail #connect
As0 f114 head f81 mainIn #connect
As0 f110 head f67 mainIn #connect
As0 f1 mainOut f2 tail #connect
As0 f2 head f76 in #connect
As0 f14 mainOut f13 tail #connect
As0 f13 head f98 in #connect
As0 f98 out f16 tail #connect
As0 f16 head f70 in #connect
As0 f19 mainOut f20 tail #connect
As0 f20 head f91 mainIn #connect
As0 f70 out f11 tail #connect
As0 f11 head f10 mainIn #connect
As0 f70 out f22 tail #connect
As0 f22 head f15 mainIn #connect
As0 f18 mainOut f12 tail #connect
As0 f12 head f14 mainIn #connect
As0 f70 out f27 tail #connect
As0 f27 head f17 mainIn #connect
As0 f70 out f110 tail #connect
As0 f17 mainOut f28 tail #connect
As0 f28 head f34 mainIn #connect
As0 f70 out f35 tail #connect
As0 f35 head f31 mainIn #connect
As0 f31 mainOut f37 tail #connect
As0 f37 head f34 mainIn #connect
As0 f67 mainOut f0 tail #connect
As0 f0 head f34 mainIn #connect
As0 f95 mainOut f21 tail #connect
As0 f21 head f34 mainIn #connect
As0 f10 mainOut f23 tail #connect
As0 f23 head f34 mainIn #connect
As0 f15 mainOut f24 tail #connect
As0 f24 head f34 mainIn #connect
As0 f74 mainOut f25 tail #connect
As0 f25 head f85 mainIn #connect
As0 f94 mainOut f6 tail #connect
As0 f6 head f85 mainIn #connect
As0 f3 mainOut f9 tail #connect
As0 f9 head f5 mainIn #connect
As0 f7 mainOut f4 tail #connect
As0 f4 head f92 mainIn #connect
As0 f92 mainOut f8 tail #connect
As0 f8 head f3 mainIn #connect
