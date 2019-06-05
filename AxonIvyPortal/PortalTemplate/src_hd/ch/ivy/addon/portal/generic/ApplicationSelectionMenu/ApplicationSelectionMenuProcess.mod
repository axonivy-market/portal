[Ivy]
156A1AA176DE2A21 3.20 #module
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
As0 @RichDialogProcessEnd f77 '' #zField
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
As0 @PushWFArc f113 '' #zField
As0 @PushWFArc f114 '' #zField
As0 @PushWFArc f115 '' #zField
As0 @PushWFArc f122 '' #zField
As0 @PushWFArc f125 '' #zField
As0 @PushWFArc f126 '' #zField
As0 @PushWFArc f129 '' #zField
As0 @RichDialogProcessStart f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f3 '' #zField
As0 @RichDialogMethodStart f7 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @RichDialogProcessEnd f6 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @GridStep f14 '' #zField
As0 @PushWFArc f13 '' #zField
As0 @PushWFArc f16 '' #zField
As0 @RichDialogProcessStart f20 '' #zField
As0 @PushWFArc f21 '' #zField
As0 @RichDialogProcessStart f10 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @GridStep f12 '' #zField
As0 @PushWFArc f15 '' #zField
As0 @PushWFArc f18 '' #zField
As0 @GridStep f19 '' #zField
As0 @PushWFArc f22 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @GridStep f25 '' #zField
As0 @PushWFArc f26 '' #zField
As0 @GridStep f0 '' #zField
As0 @PushWFArc f24 '' #zField
As0 @PushWFArc f17 '' #zField
As0 @PushWFArc f27 '' #zField
As0 @PushWFArc f28 '' #zField
As0 @PushWFArc f29 '' #zField
As0 @PushWFArc f4 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f67 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f67 actionTable 'out=in;
' #txt
As0 f67 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.selectedSubMenuItem.getExternalLink());' #txt
As0 f67 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to custom
menu link</name>
        <nameStyle>28
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f67 512 426 128 44 -44 -16 #rect
As0 f67 @|StepIcon #fIcon
As0 f70 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f70 753 305 32 32 0 16 #rect
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
As0 f72 949 21 22 22 14 0 #rect
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
As0 f74 84 20 24 24 14 0 #rect
As0 f74 @|RichDialogInitStartIcon #fIcon
As0 f76 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f76 944 161 32 32 0 16 #rect
As0 f76 @|AlternativeIcon #fIcon
As0 f77 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f77 758 630 22 22 14 0 #rect
As0 f77 @|RichDialogProcessEndIcon #fIcon
As0 f81 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f81 1107 224 26 26 0 12 #rect
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
As0 f83 896 215 128 44 -42 -16 #rect
As0 f83 @|StepIcon #fIcon
As0 f85 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f85 244 348 24 24 13 0 #rect
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
As0 f87 244 20 24 24 13 0 #rect
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
As0 f91 751 97 36 24 20 -2 #rect
As0 f91 @|StepIcon #fIcon
As0 f92 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f92 actionTable 'out=in;
' #txt
As0 f92 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import org.primefaces.component.button.Button;
import ch.ivy.addon.portalkit.service.ServerWorkingOnDetector;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.application.IApplication;

ServerWorkingOnDetector serverWorkingOnDetector = new ServerWorkingOnDetector();
ApplicationService applicationService = new ApplicationService();
int numberOfIvyApplications = applicationService.countIvyApplications(in.applications);
SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP.toString());
SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SERVER_ID.toString());

for (Application application : in.applications){
	if (application.isVisible) {
		Button menuItem = new Button();
		menuItem.value = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
		boolean isThirdPartyApp = !application.#serverId is initialized;
		if (!isThirdPartyApp){
			menuItem.getAttributes().put("serverId", application.serverId.toString());
		} else {
			menuItem.getAttributes().put("isThirdPartyApp", true);
		}
		menuItem.href = application.link;
		menuItem.getAttributes().put("appName", application.name);
		menuItem.icon = "fa " + application.menuIcon;
		
		if (!application.isOnline) {
			menuItem.disabled = true;
		}
	
		in.menuItems.add(menuItem);
		if ((application.name.equals(ivy.request.getApplication().getName()) && application.getServerId() == serverWorkingOnDetector.getServerWorkingOn().getId()) || (!isThirdPartyApp && numberOfIvyApplications == 1)) {
			menuItem.styleClass = "ivy-active";
			SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP.toString(), application.name);
			SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString(), application.displayName);
			SecurityServiceUtils.setSessionAttribute(SessionAttribute.SERVER_ID.toString(), application.serverId);
		}
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
		IApplication portal = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(IApplication.PORTAL_APPLICATION_NAME);
		if (portal != null && portal.getActivityState() != ch.ivyteam.ivy.application.ActivityState.INACTIVE && portal.getSecurityContext().findUser(ivy.session.getSessionUserName()) != null) {
			menuItem.href = SecurityServiceUtils.getDefaultPortalStartUrl();
			boolean isAllAppSelected = IApplication.PORTAL_APPLICATION_NAME.equals(ivy.request.getApplication().getName());
			if (isAllAppSelected) {
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
As0 f92 228 264 56 32 39 -14 #rect
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
As0 f94 232 184 48 32 33 -8 #rect
As0 f94 @|StepIcon #fIcon
As0 f95 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f95 actionTable 'out=in;
' #txt
As0 f95 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.applicationUrl);' #txt
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
As0 f95 712 426 112 44 -35 -16 #rect
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
As0 f97 232 96 48 32 38 -3 #rect
As0 f97 @|CallSubIcon #fIcon
As0 f98 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f98 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
As0 f98 755 163 28 28 14 0 #rect
As0 f98 @|AlternativeIcon #fIcon
As0 f109 expr in #txt
As0 f109 outCond in.isWorkingOnATask #txt
As0 f109 960 193 960 215 #arcP
As0 f110 expr in #txt
As0 f110 outCond 'in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.CUSTOM' #txt
As0 f110 753 321 576 426 #arcP
As0 f110 1 576 321 #addKink
As0 f110 0 0.8608001985669408 0 0 #arcLabel
As0 f111 expr out #txt
As0 f111 960 43 960 161 #arcP
As0 f111 0 0.8554291471631579 0 0 #arcLabel
As0 f112 expr in #txt
As0 f112 outCond '!in.#selectedSubMenuItem is initialized' #txt
As0 f112 769 337 768 426 #arcP
As0 f113 expr out #txt
As0 f113 768 470 768 630 #arcP
As0 f114 expr out #txt
As0 f114 1024 237 1107 237 #arcP
As0 f114 0 0.2964491651298413 0 0 #arcLabel
As0 f115 expr out #txt
As0 f115 256 44 256 96 #arcP
As0 f122 expr out #txt
As0 f122 769 121 769 163 #arcP
As0 f125 expr in #txt
As0 f125 944 177 783 177 #arcP
As0 f126 expr out #txt
As0 f126 256 216 256 264 #arcP
As0 f129 expr out #txt
As0 f129 256 128 256 184 #arcP
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
As0 f1 1107 19 26 26 16 -3 #rect
As0 f1 @|RichDialogProcessStartIcon #fIcon
As0 f2 expr out #txt
As0 f2 1120 45 976 177 #arcP
As0 f2 1 1120 177 #addKink
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
As0 f3 232 432 48 32 -25 18 #rect
As0 f3 @|StepIcon #fIcon
As0 f7 guid 15FDC9D01571BA80 #txt
As0 f7 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f7 method loadSubMenuItems() #txt
As0 f7 disableUIEvents false #txt
As0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f7 outParameterDecl '<> result;
' #txt
As0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadSubMenuItems()</name>
    </language>
</elementInfo>
' #txt
As0 f7 83 435 26 26 -57 15 #rect
As0 f7 @|RichDialogMethodStartIcon #fIcon
As0 f8 expr out #txt
As0 f8 109 448 232 448 #arcP
As0 f6 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f6 371 435 26 26 0 12 #rect
As0 f6 @|RichDialogProcessEndIcon #fIcon
As0 f9 expr out #txt
As0 f9 280 448 371 448 #arcP
As0 f14 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f14 actionTable 'out=in;
' #txt
As0 f14 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(ivy.task);' #txt
As0 f14 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave and &#xD;
reset task</name>
    </language>
</elementInfo>
' #txt
As0 f14 520 90 112 44 -30 -16 #rect
As0 f14 @|StepIcon #fIcon
As0 f13 expr out #txt
As0 f13 576 134 755 177 #arcP
As0 f13 1 576 177 #addKink
As0 f13 1 0.302020000020303 0 0 #arcLabel
As0 f16 expr in #txt
As0 f16 769 191 769 305 #arcP
As0 f20 guid 163FDDC6D322CE5C #txt
As0 f20 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f20 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f20 actionTable 'out=in;
' #txt
As0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f20 755 19 26 26 -34 15 #rect
As0 f20 @|RichDialogProcessStartIcon #fIcon
As0 f21 expr out #txt
As0 f21 768 44 769 97 #arcP
As0 f10 guid 163FDDDA274AC61E #txt
As0 f10 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f10 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f10 actionTable 'out=in;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f10 563 19 26 26 -14 15 #rect
As0 f10 @|RichDialogProcessStartIcon #fIcon
As0 f11 expr out #txt
As0 f11 576 45 576 90 #arcP
As0 f12 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f12 actionTable 'out=in;
' #txt
As0 f12 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalProcess();' #txt
As0 f12 security system #txt
As0 f12 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to process</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f12 904 426 112 44 -51 -8 #rect
As0 f12 @|StepIcon #fIcon
As0 f15 expr in #txt
As0 f15 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.PROCESS' #txt
As0 f15 781 325 960 426 #arcP
As0 f15 1 960 384 #addKink
As0 f15 0 0.5688499334886715 0 0 #arcLabel
As0 f18 expr out #txt
As0 f18 960 470 779 637 #arcP
As0 f18 1 960 576 #addKink
As0 f18 1 0.2248562502841817 0 0 #arcLabel
As0 f19 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f19 actionTable 'out=in;
' #txt
As0 f19 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalStatistic();' #txt
As0 f19 security system #txt
As0 f19 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to statistic</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f19 1064 426 112 44 -49 -8 #rect
As0 f19 @|StepIcon #fIcon
As0 f22 expr in #txt
As0 f22 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.DASHBOARD' #txt
As0 f22 783 323 1120 426 #arcP
As0 f22 1 1120 384 #addKink
As0 f22 0 0.6322196097138731 0 0 #arcLabel
As0 f23 expr out #txt
As0 f23 1120 470 779 638 #arcP
As0 f23 1 1120 576 #addKink
As0 f23 0 0.8527125712511222 0 0 #arcLabel
As0 f25 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f25 actionTable 'out=in;
' #txt
As0 f25 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalCase();' #txt
As0 f25 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to case</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f25 1256 426 112 44 -42 -8 #rect
As0 f25 @|StepIcon #fIcon
As0 f26 expr in #txt
As0 f26 783 323 1312 426 #arcP
As0 f26 1 1312 384 #addKink
As0 f26 0 0.8050277714172202 0 0 #arcLabel
As0 f0 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f0 actionTable 'out=in;
' #txt
As0 f0 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalTask();' #txt
As0 f0 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to task</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f0 1416 426 112 44 -40 -8 #rect
As0 f0 @|StepIcon #fIcon
As0 f24 expr in #txt
As0 f24 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.TASK' #txt
As0 f24 785 321 1472 426 #arcP
As0 f24 1 1472 321 #addKink
As0 f24 0 0.5818434192618736 0 0 #arcLabel
As0 f17 expr out #txt
As0 f17 1312 470 779 639 #arcP
As0 f17 1 1312 576 #addKink
As0 f17 1 0.42514678446149795 0 0 #arcLabel
As0 f27 expr out #txt
As0 f27 1472 470 780 641 #arcP
As0 f27 1 1472 641 #addKink
As0 f27 1 0.392153013993921 0 0 #arcLabel
As0 f28 expr out #txt
As0 f28 576 470 758 641 #arcP
As0 f28 1 576 641 #addKink
As0 f28 1 0.8156608506236205 0 0 #arcLabel
As0 f29 expr out #txt
As0 f29 96 44 244 360 #arcP
As0 f29 1 96 360 #addKink
As0 f29 0 0.8605300296400707 0 0 #arcLabel
As0 f4 expr out #txt
As0 f4 256 296 256 348 #arcP
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
As0 f94 mainOut f126 tail #connect
As0 f126 head f92 mainIn #connect
As0 f95 mainOut f113 tail #connect
As0 f113 head f77 mainIn #connect
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
As0 f7 mainOut f8 tail #connect
As0 f8 head f3 mainIn #connect
As0 f3 mainOut f9 tail #connect
As0 f9 head f6 mainIn #connect
As0 f14 mainOut f13 tail #connect
As0 f13 head f98 in #connect
As0 f98 out f16 tail #connect
As0 f16 head f70 in #connect
As0 f20 mainOut f21 tail #connect
As0 f21 head f91 mainIn #connect
As0 f10 mainOut f11 tail #connect
As0 f11 head f14 mainIn #connect
As0 f70 out f15 tail #connect
As0 f15 head f12 mainIn #connect
As0 f12 mainOut f18 tail #connect
As0 f18 head f77 mainIn #connect
As0 f70 out f22 tail #connect
As0 f22 head f19 mainIn #connect
As0 f19 mainOut f23 tail #connect
As0 f23 head f77 mainIn #connect
As0 f26 head f25 mainIn #connect
As0 f70 out f24 tail #connect
As0 f24 head f0 mainIn #connect
As0 f70 out f110 tail #connect
As0 f70 out f26 tail #connect
As0 f25 mainOut f17 tail #connect
As0 f17 head f77 mainIn #connect
As0 f0 mainOut f27 tail #connect
As0 f27 head f77 mainIn #connect
As0 f67 mainOut f28 tail #connect
As0 f28 head f77 mainIn #connect
As0 f74 mainOut f29 tail #connect
As0 f29 head f85 mainIn #connect
As0 f92 mainOut f4 tail #connect
As0 f4 head f85 mainIn #connect
