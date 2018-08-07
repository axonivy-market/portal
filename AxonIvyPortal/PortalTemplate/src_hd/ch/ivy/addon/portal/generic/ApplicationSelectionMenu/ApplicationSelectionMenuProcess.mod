[Ivy]
[>Created: Wed Nov 02 19:11:49 ICT 2016]
156A1AA176DE2A21 3.18 #module
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
As0 @RichDialogInitStart f0 '' #zField
As0 @RichDialogProcessEnd f1 '' #zField
As0 @GridStep f9 '' #zField
As0 @CallSub f4 '' #zField
As0 @RichDialogInitStart f20 '' #zField
As0 @GridStep f22 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @PushWFArc f3 '' #zField
As0 @GridStep f14 '' #zField
As0 @RichDialogProcessEnd f26 '' #zField
As0 @GridStep f28 '' #zField
As0 @PushWFArc f27 '' #zField
As0 @Alternative f5 '' #zField
As0 @RichDialogProcessStart f29 '' #zField
As0 @RichDialogProcessStart f12 '' #zField
As0 @PushWFArc f19 '' #zField
As0 @RichDialogMethodStart f10 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @PushWFArc f6 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @RichDialogProcessStart f13 '' #zField
As0 @RichDialogProcessStart f25 '' #zField
As0 @RichDialog f33 '' #zField
As0 @RichDialog f32 '' #zField
As0 @PushWFArc f34 '' #zField
As0 @PushWFArc f30 '' #zField
As0 @PushWFArc f15 '' #zField
As0 @PushWFArc f17 '' #zField
As0 @ErrorBoundaryEvent Et0 ErrorBoundaryEvent #zField
As0 @RichDialogProcessStart f18 '' #zField
As0 @GridStep f24 '' #zField
As0 @PushWFArc f31 '' #zField
As0 @CallSub f56 '' #zField
As0 @PushWFArc f35 '' #zField
As0 @GridStep f36 '' #zField
As0 @PushWFArc f37 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @PushWFArc f38 '' #zField
As0 @GridStep f21 '' #zField
As0 @RichDialogProcessStart f39 '' #zField
As0 @CallSub f40 '' #zField
As0 @PushWFArc f41 '' #zField
As0 @PushWFArc f42 '' #zField
As0 @PushWFArc f45 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f0 guid 14AF0B1C8DE6C030 #txt
As0 f0 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f0 method start() #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f0 outParameterDecl '<> result;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 198 62 20 20 13 0 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f1 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f1 198 358 20 20 13 0 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f9 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f9 actionTable 'out=in;
' #txt
As0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import org.primefaces.component.button.Button;
import ch.ivy.addon.portalkit.service.ServerWorkingOnDetector;

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
	menuItem.icon = "fa fa-dashcube";
	if (numberOfIvyApplications == 0) {
		menuItem.href = new PortalNavigator().getPortalStartUrlOfCurrentApplication();
		menuItem.styleClass="ivy-active";
	} else {
		menuItem.href = SecurityServiceUtils.getDefaultPortalStartUrl();
		boolean isAllAppSelected = IApplication.PORTAL_APPLICATION_NAME.equals(ivy.request.getApplication().getName());
		if (isAllAppSelected) {
			menuItem.styleClass = "ivy-active";
		}
	}
	in.menuItems.add(0, menuItem);
}' #txt
As0 f9 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Convert 
to menu items</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f9 188 228 40 24 27 -14 #rect
As0 f9 @|StepIcon #fIcon
As0 f4 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f4 processCall 'Business Processes/FindApplicationsByUser:findApplicationsByUser(String)' #txt
As0 f4 doCall true #txt
As0 f4 requestActionDecl '<java.lang.String username> param;
' #txt
As0 f4 requestMappingAction 'param.username=ivy.session.getSessionUserName();
' #txt
As0 f4 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f4 responseMappingAction 'out=in;
out.applications=result.applications;
' #txt
As0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findApplicationsByUser(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f4 190 116 36 24 20 -2 #rect
As0 f4 @|CallSubIcon #fIcon
As0 f20 guid 14EA97867FAAF9AA #txt
As0 f20 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f20 method StartNotRequiredLogin() #txt
As0 f20 disableUIEvents true #txt
As0 f20 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f20 inParameterMapAction 'out.isNotRequiredLogin=true;
' #txt
As0 f20 outParameterDecl '<> result;
' #txt
As0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>StartNotRequiredLogin()</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f20 21 53 22 22 14 0 #rect
As0 f20 @|RichDialogInitStartIcon #fIcon
As0 f22 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f22 actionTable 'out=in;
' #txt
As0 f22 actionCode 'import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import java.util.Collections;

Collections.sort(in.applications, new ApplicationIndexAscendingComparator());' #txt
As0 f22 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort applications</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f22 190 172 36 24 20 -2 #rect
As0 f22 @|StepIcon #fIcon
As0 f23 expr out #txt
As0 f23 208 140 208 172 #arcP
As0 f3 expr out #txt
As0 f3 208 196 208 228 #arcP
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
        <name>reset current task</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f14 494 116 36 24 20 -2 #rect
As0 f14 @|StepIcon #fIcon
As0 f26 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f26 725 293 22 22 14 0 #rect
As0 f26 @|RichDialogProcessEndIcon #fIcon
As0 f28 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f28 actionTable 'out=in;
' #txt
As0 f28 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.applicationUrl);' #txt
As0 f28 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to
selected app</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f28 718 204 36 24 20 -2 #rect
As0 f28 @|StepIcon #fIcon
As0 f27 expr out #txt
As0 f27 736 228 736 293 #arcP
As0 f5 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
As0 f5 722 114 28 28 14 0 #rect
As0 f5 @|AlternativeIcon #fIcon
As0 f29 guid 1559F48EB5F439AE #txt
As0 f29 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f29 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f29 actionTable 'out=in;
' #txt
As0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openApp</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f29 725 53 22 22 14 0 #rect
As0 f29 @|RichDialogProcessStartIcon #fIcon
As0 f12 guid 1559F4D94ACC30B2 #txt
As0 f12 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f12 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f12 actionTable 'out=in;
' #txt
As0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetCurrentTaskAndOpenApp</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f12 501 53 22 22 14 0 #rect
As0 f12 @|RichDialogProcessStartIcon #fIcon
As0 f19 expr out #txt
As0 f19 512 75 512 116 #arcP
As0 f10 guid 1559F666C6B122FC #txt
As0 f10 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f10 method openApp(String) #txt
As0 f10 disableUIEvents false #txt
As0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String appUrl> param = methodEvent.getInputArguments();
' #txt
As0 f10 inParameterMapAction 'out.applicationUrl=param.appUrl;
' #txt
As0 f10 outParameterDecl '<> result;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openApp(String)</name>
    </language>
</elementInfo>
' #txt
As0 f10 853 53 22 22 14 0 #rect
As0 f10 @|RichDialogMethodStartIcon #fIcon
As0 f7 expr out #txt
As0 f7 208 82 208 116 #arcP
As0 f6 expr out #txt
As0 f6 530 128 722 128 #arcP
As0 f6 0 0.7501913490111207 0 0 #arcLabel
As0 f8 expr out #txt
As0 f8 864 75 750 128 #arcP
As0 f8 1 864 128 #addKink
As0 f8 1 0.7070537981990302 0 0 #arcLabel
As0 f11 expr out #txt
As0 f11 736 75 736 114 #arcP
As0 f13 guid 156A1B020CC1F41B #txt
As0 f13 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f13 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f13 actionTable 'out=in;
' #txt
As0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openProcesses</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f13 1045 53 22 22 14 0 #rect
As0 f13 @|RichDialogProcessStartIcon #fIcon
As0 f25 guid 156A1EF46887CD36 #txt
As0 f25 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f25 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f25 actionTable 'out=in;
' #txt
As0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openStatistic</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f25 1181 53 22 22 14 0 #rect
As0 f25 @|RichDialogProcessStartIcon #fIcon
As0 f33 targetWindow NEW:card: #txt
As0 f33 targetDisplay TOP #txt
As0 f33 richDialogId ch.ivy.addon.portal.generic.Processes #txt
As0 f33 startMethod startWithMenuState(String) #txt
As0 f33 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f33 requestActionDecl '<String menuState> param;' #txt
As0 f33 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
As0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f33 responseMappingAction 'out=in;
' #txt
As0 f33 windowConfiguration '* ' #txt
As0 f33 isAsynch false #txt
As0 f33 isInnerRd true #txt
As0 f33 userContext '* ' #txt
As0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f33 1020 108 72 40 -22 -8 #rect
As0 f33 @|RichDialogIcon #fIcon
As0 f32 targetWindow NEW:card: #txt
As0 f32 targetDisplay TOP #txt
As0 f32 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
As0 f32 startMethod startWithMenuState(String) #txt
As0 f32 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f32 requestActionDecl '<String menuState> param;' #txt
As0 f32 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
As0 f32 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f32 responseMappingAction 'out=in;
' #txt
As0 f32 windowConfiguration '* ' #txt
As0 f32 isAsynch false #txt
As0 f32 isInnerRd true #txt
As0 f32 userContext '* ' #txt
As0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>dash board</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f32 1152 108 80 40 -27 -8 #rect
As0 f32 @|RichDialogIcon #fIcon
As0 f34 expr out #txt
As0 f34 1192 75 1192 108 #arcP
As0 f30 expr out #txt
As0 f30 1192 148 746 300 #arcP
As0 f30 1 1192 160 #addKink
As0 f30 1 0.44378206904513473 0 0 #arcLabel
As0 f15 expr out #txt
As0 f15 1056 148 746 299 #arcP
As0 f15 1 1056 176 #addKink
As0 f15 1 0.40824443746900757 0 0 #arcLabel
As0 f17 expr out #txt
As0 f17 1056 75 1056 108 #arcP
As0 Et0 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 Et0 actionTable 'out=in;
' #txt
As0 Et0 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 Et0 attachedToRef 156A1AA176DE2A21-f32 #txt
As0 Et0 1195 145 26 26 14 0 #rect
As0 Et0 @|ErrorBoundaryEventIcon #fIcon
As0 f18 guid 156DAB14114D1261 #txt
As0 f18 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f18 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f18 actionTable 'out=in;
' #txt
As0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openCases</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f18 1333 53 22 22 14 0 #rect
As0 f18 @|RichDialogProcessStartIcon #fIcon
As0 f24 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f24 actionTable 'out=in;
' #txt
As0 f24 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;

CaseLazyDataModel dataModel = new CaseLazyDataModel();

dataModel.setIgnoreInvolvedUser(in.hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	dataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	dataModel.setInvolvedApplications(applicationName);
}

in.caseView = CaseView.create().dataModel(dataModel).buildNewView();' #txt
As0 f24 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, case view</name>
        <nameStyle>27
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f24 1326 116 36 24 20 -2 #rect
As0 f24 @|StepIcon #fIcon
As0 f31 expr out #txt
As0 f31 1344 75 1344 116 #arcP
As0 f56 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f56 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
As0 f56 doCall true #txt
As0 f56 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
As0 f56 requestMappingAction 'param.view=in.caseView;
' #txt
As0 f56 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f56 responseMappingAction 'out=in;
' #txt
As0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f56 1288 184 112 48 -49 -12 #rect
As0 f56 @|CallSubIcon #fIcon
As0 f35 expr out #txt
As0 f35 1344 140 1344 184 #arcP
As0 f36 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f36 actionTable 'out=in;
' #txt
As0 f36 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();
in.hasReadAllCasesPermission = TaskUtils.checkReadAllCasesPermission();' #txt
As0 f36 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check permission</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f36 190 292 36 24 20 -2 #rect
As0 f36 @|StepIcon #fIcon
As0 f37 expr out #txt
As0 f37 208 252 208 292 #arcP
As0 f2 expr out #txt
As0 f2 208 316 208 358 #arcP
As0 f38 expr out #txt
As0 f38 32 75 190 304 #arcP
As0 f38 1 32 304 #addKink
As0 f38 0 0.7080705449857414 0 0 #arcLabel
As0 f21 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f21 actionTable 'out=in;
' #txt
As0 f21 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

TaskLazyDataModel dataModel = new TaskLazyDataModel();
dataModel.setIgnoreInvolvedUser(in.hasReadAllTasksPermisson);


Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	dataModel.setServerId(serverId);
}

dataModel.setTaskAssigneeType(ch.ivy.ws.addon.TaskAssigneeType.ALL);

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	dataModel.setInvolvedApplications(applicationName);
}

in.taskView = TaskView.create().dataModel(dataModel).noTaskFoundMessage("").createNewTaskView();
' #txt
As0 f21 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f21 1606 124 36 24 20 -2 #rect
As0 f21 @|StepIcon #fIcon
As0 f39 guid 15731420EB6AD172 #txt
As0 f39 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f39 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f39 actionTable 'out=in;
' #txt
As0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openTasks</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f39 1613 61 22 22 14 0 #rect
As0 f39 @|RichDialogProcessStartIcon #fIcon
As0 f40 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f40 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
As0 f40 doCall true #txt
As0 f40 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
As0 f40 requestMappingAction 'param.taskView=in.taskView;
' #txt
As0 f40 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f40 responseMappingAction 'out=in;
' #txt
As0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f40 1572 188 104 56 -48 -12 #rect
As0 f40 @|CallSubIcon #fIcon
As0 f41 expr out #txt
As0 f41 1624 83 1624 124 #arcP
As0 f42 expr out #txt
As0 f42 1624 148 1624 188 #arcP
As0 f45 expr in #txt
As0 f45 736 142 736 204 #arcP
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
As0 f4 mainOut f23 tail #connect
As0 f23 head f22 mainIn #connect
As0 f22 mainOut f3 tail #connect
As0 f3 head f9 mainIn #connect
As0 f28 mainOut f27 tail #connect
As0 f27 head f26 mainIn #connect
As0 f12 mainOut f19 tail #connect
As0 f19 head f14 mainIn #connect
As0 f0 mainOut f7 tail #connect
As0 f7 head f4 mainIn #connect
As0 f14 mainOut f6 tail #connect
As0 f6 head f5 in #connect
As0 f10 mainOut f8 tail #connect
As0 f8 head f5 in #connect
As0 f29 mainOut f11 tail #connect
As0 f11 head f5 in #connect
As0 f25 mainOut f34 tail #connect
As0 f34 head f32 mainIn #connect
As0 f32 mainOut f30 tail #connect
As0 f30 head f26 mainIn #connect
As0 f33 mainOut f15 tail #connect
As0 f15 head f26 mainIn #connect
As0 f13 mainOut f17 tail #connect
As0 f17 head f33 mainIn #connect
As0 f18 mainOut f31 tail #connect
As0 f31 head f24 mainIn #connect
As0 f24 mainOut f35 tail #connect
As0 f35 head f56 mainIn #connect
As0 f9 mainOut f37 tail #connect
As0 f37 head f36 mainIn #connect
As0 f36 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f20 mainOut f38 tail #connect
As0 f38 head f36 mainIn #connect
As0 f39 mainOut f41 tail #connect
As0 f41 head f21 mainIn #connect
As0 f21 mainOut f42 tail #connect
As0 f42 head f40 mainIn #connect
As0 f5 out f45 tail #connect
As0 f45 head f28 mainIn #connect
