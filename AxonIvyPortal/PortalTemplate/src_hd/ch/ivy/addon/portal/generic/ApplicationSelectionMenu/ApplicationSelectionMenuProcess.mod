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
As0 @RichDialogProcessEnd f66 '' #zField
As0 @GridStep f67 '' #zField
As0 @GridStep f69 '' #zField
As0 @Alternative f70 '' #zField
As0 @RichDialog f71 '' #zField
As0 @RichDialogMethodStart f72 '' #zField
As0 @CallSub f73 '' #zField
As0 @RichDialogInitStart f74 '' #zField
As0 @GridStep f75 '' #zField
As0 @Alternative f76 '' #zField
As0 @RichDialogProcessEnd f77 '' #zField
As0 @RichDialogProcessEnd f78 '' #zField
As0 @GridStep f79 '' #zField
As0 @RichDialogProcessEnd f81 '' #zField
As0 @RichDialog f82 '' #zField
As0 @GridStep f83 '' #zField
As0 @CallSub f84 '' #zField
As0 @RichDialogProcessEnd f85 '' #zField
As0 @RichDialogInitStart f87 '' #zField
As0 @RichDialogProcessEnd f90 '' #zField
As0 @GridStep f91 '' #zField
As0 @GridStep f92 '' #zField
As0 @GridStep f93 '' #zField
As0 @GridStep f94 '' #zField
As0 @GridStep f95 '' #zField
As0 @CallSub f97 '' #zField
As0 @Alternative f98 '' #zField
As0 @PushWFArc f100 '' #zField
As0 @PushWFArc f101 '' #zField
As0 @PushWFArc f102 '' #zField
As0 @PushWFArc f103 '' #zField
As0 @PushWFArc f105 '' #zField
As0 @PushWFArc f106 '' #zField
As0 @PushWFArc f107 '' #zField
As0 @PushWFArc f109 '' #zField
As0 @PushWFArc f110 '' #zField
As0 @PushWFArc f111 '' #zField
As0 @PushWFArc f112 '' #zField
As0 @PushWFArc f113 '' #zField
As0 @PushWFArc f114 '' #zField
As0 @PushWFArc f115 '' #zField
As0 @PushWFArc f116 '' #zField
As0 @PushWFArc f117 '' #zField
As0 @PushWFArc f118 '' #zField
As0 @PushWFArc f121 '' #zField
As0 @PushWFArc f122 '' #zField
As0 @PushWFArc f124 '' #zField
As0 @PushWFArc f125 '' #zField
As0 @PushWFArc f126 '' #zField
As0 @PushWFArc f129 '' #zField
As0 @GridStep f24 '' #zField
As0 @CallSub f56 '' #zField
As0 @CallSub f62 '' #zField
As0 @PushWFArc f52 '' #zField
As0 @PushWFArc f34 '' #zField
As0 @PushWFArc f0 '' #zField
As0 @RichDialogProcessStart f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f3 '' #zField
As0 @RichDialogMethodStart f7 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @PushWFArc f5 '' #zField
As0 @RichDialogProcessEnd f6 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @RichDialogMethodStart f10 '' #zField
As0 @RichDialogMethodStart f11 '' #zField
As0 @PushWFArc f12 '' #zField
As0 @PushWFArc f13 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f66 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f66 934 512 26 26 0 12 #rect
As0 f66 @|RichDialogProcessEndIcon #fIcon
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
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f67 611 417 32 24 20 -2 #rect
As0 f67 @|StepIcon #fIcon
As0 f69 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f69 actionTable 'out=in;
' #txt
As0 f69 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;

in.taskDataModel.setIgnoreInvolvedUser(in.hasReadAllTasksPermisson);
in.taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.taskDataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();
' #txt
As0 f69 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f69 1441 385 36 24 20 -2 #rect
As0 f69 @|StepIcon #fIcon
As0 f70 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f70 771 317 32 32 0 16 #rect
As0 f70 @|AlternativeIcon #fIcon
As0 f71 targetWindow NEW:card: #txt
As0 f71 targetDisplay TOP #txt
As0 f71 richDialogId ch.ivy.addon.portal.generic.Processes #txt
As0 f71 startMethod startWithMenuState(String) #txt
As0 f71 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f71 requestActionDecl '<String menuState> param;' #txt
As0 f71 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
As0 f71 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f71 responseMappingAction 'out=in;
' #txt
As0 f71 windowConfiguration '* ' #txt
As0 f71 isAsynch false #txt
As0 f71 isInnerRd true #txt
As0 f71 userContext '* ' #txt
As0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f71 911 437 72 40 -22 -8 #rect
As0 f71 @|RichDialogIcon #fIcon
As0 f72 guid 15FB36E87007F717 #txt
As0 f72 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f72 method openApp(String) #txt
As0 f72 disableUIEvents false #txt
As0 f72 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String appUrl> param = methodEvent.getInputArguments();
' #txt
As0 f72 inParameterMapAction 'out.applicationUrl=param.appUrl;
' #txt
As0 f72 outParameterDecl '<> result;
' #txt
As0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openApp(String)</name>
    </language>
</elementInfo>
' #txt
As0 f72 968 34 22 22 14 0 #rect
As0 f72 @|RichDialogMethodStartIcon #fIcon
As0 f73 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f73 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
As0 f73 doCall true #txt
As0 f73 requestActionDecl '<> param;
' #txt
As0 f73 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f73 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
As0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f73 1403 311 112 44 -40 -8 #rect
As0 f73 @|CallSubIcon #fIcon
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
As0 f74 76 44 24 24 14 0 #rect
As0 f74 @|RichDialogInitStartIcon #fIcon
As0 f75 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f75 actionTable 'out=in;
' #txt
As0 f75 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), PortalPage.LINK_TO_TASK);

SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString());' #txt
As0 f75 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store the last page 
as Portal Task to session</name>
        <nameStyle>46
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f75 1395 437 128 48 -62 -16 #rect
As0 f75 @|StepIcon #fIcon
As0 f76 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f76 963 157 32 32 0 16 #rect
As0 f76 @|AlternativeIcon #fIcon
As0 f77 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f77 776 514 22 22 14 0 #rect
As0 f77 @|RichDialogProcessEndIcon #fIcon
As0 f78 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f78 616 512 22 22 14 0 #rect
As0 f78 @|RichDialogProcessEndIcon #fIcon
As0 f79 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f79 actionTable 'out=in;
' #txt
As0 f79 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.removeSessionAttribute(SessionAttribute.LAST_PAGE.toString());' #txt
As0 f79 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f79 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Remove the session
attribute of last page</name>
        <nameStyle>41
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f79 769 225 36 24 20 -2 #rect
As0 f79 @|StepIcon #fIcon
As0 f81 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f81 1094 224 26 26 0 12 #rect
As0 f81 @|RichDialogProcessEndIcon #fIcon
As0 f82 targetWindow NEW:card: #txt
As0 f82 targetDisplay TOP #txt
As0 f82 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
As0 f82 startMethod startWithMenuState(String) #txt
As0 f82 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f82 requestActionDecl '<String menuState> param;' #txt
As0 f82 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
As0 f82 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f82 responseMappingAction 'out=in;
' #txt
As0 f82 windowConfiguration '* ' #txt
As0 f82 isAsynch false #txt
As0 f82 isInnerRd true #txt
As0 f82 userContext '* ' #txt
As0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>dash board</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f82 1051 441 80 40 -27 -8 #rect
As0 f82 @|RichDialogIcon #fIcon
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
As0 f83 915 215 128 44 -42 -16 #rect
As0 f83 @|StepIcon #fIcon
As0 f84 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f84 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
As0 f84 doCall true #txt
As0 f84 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
As0 f84 requestMappingAction 'param.taskView=in.taskView;
' #txt
As0 f84 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f84 responseMappingAction 'out=in;
' #txt
As0 f84 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f84 1407 505 104 40 -48 -12 #rect
As0 f84 @|CallSubIcon #fIcon
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
As0 f87 244 44 24 24 13 0 #rect
As0 f87 @|RichDialogInitStartIcon #fIcon
As0 f90 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f90 1078 512 26 26 0 12 #rect
As0 f90 @|RichDialogProcessEndIcon #fIcon
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
As0 f91 769 97 36 24 20 -2 #rect
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
As0 f92 228 208 56 32 39 -14 #rect
As0 f92 @|StepIcon #fIcon
As0 f93 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f93 actionTable 'out=in;
' #txt
As0 f93 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();
in.hasReadAllCasesPermission = TaskUtils.checkReadAllCasesPermission();' #txt
As0 f93 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f93 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check permission</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f93 232 272 48 32 37 -5 #rect
As0 f93 @|StepIcon #fIcon
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
As0 f94 232 152 48 32 33 -8 #rect
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
As0 f95 769 417 36 24 20 -2 #rect
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
As0 f98 773 159 28 28 14 0 #rect
As0 f98 @|AlternativeIcon #fIcon
As0 f100 expr out #txt
As0 f100 1091 481 1091 512 #arcP
As0 f100 0 0.3933539761963605 0 0 #arcLabel
As0 f101 expr out #txt
As0 f101 947 477 947 512 #arcP
As0 f101 0 0.3639429230002049 0 0 #arcLabel
As0 f102 expr out #txt
As0 f102 256 304 256 348 #arcP
As0 f103 expr in #txt
As0 f103 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.PROCESS' #txt
As0 f103 796 340 921 437 #arcP
As0 f105 expr out #txt
As0 f105 1459 485 1459 505 #arcP
As0 f106 expr in #txt
As0 f106 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.DASHBOARD' #txt
As0 f106 798 338 1051 444 #arcP
As0 f107 expr in #txt
As0 f107 787 187 787 225 #arcP
As0 f109 expr in #txt
As0 f109 outCond in.isWorkingOnATask #txt
As0 f109 979 189 979 215 #arcP
As0 f110 expr in #txt
As0 f110 outCond 'in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.CUSTOM' #txt
As0 f110 771 333 627 417 #arcP
As0 f110 1 627 333 #addKink
As0 f110 0 0.8608001985669408 0 0 #arcLabel
As0 f111 expr out #txt
As0 f111 979 56 979 157 #arcP
As0 f111 0 0.8554291471631579 0 0 #arcLabel
As0 f112 expr in #txt
As0 f112 outCond '!in.#selectedSubMenuItem is initialized' #txt
As0 f112 787 349 787 417 #arcP
As0 f113 expr out #txt
As0 f113 787 441 787 514 #arcP
As0 f114 expr out #txt
As0 f114 1043 237 1094 237 #arcP
As0 f114 0 0.2964491651298413 0 0 #arcLabel
As0 f115 expr out #txt
As0 f115 256 68 256 96 #arcP
As0 f116 expr out #txt
As0 f116 627 441 627 512 #arcP
As0 f117 expr in #txt
As0 f117 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.TASK' #txt
As0 f117 803 333 1403 333 #arcP
As0 f118 expr out #txt
As0 f118 1459 355 1459 385 #arcP
As0 f121 expr out #txt
As0 f121 1459 409 1459 437 #arcP
As0 f122 expr out #txt
As0 f122 787 121 787 159 #arcP
As0 f124 expr out #txt
As0 f124 787 249 787 317 #arcP
As0 f125 expr in #txt
As0 f125 963 173 801 173 #arcP
As0 f126 expr out #txt
As0 f126 256 184 256 208 #arcP
As0 f129 expr out #txt
As0 f129 256 128 256 152 #arcP
As0 f24 actionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f24 actionTable 'out=in;
' #txt
As0 f24 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;


in.caseDataModel.setIgnoreInvolvedUser(in.hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.caseDataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.caseDataModel.setInvolvedApplications(applicationName);
}

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
As0 f24 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, case view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f24 1249 529 36 24 20 -2 #rect
As0 f24 @|StepIcon #fIcon
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
As0 f56 1211 581 112 48 -49 -12 #rect
As0 f56 @|CallSubIcon #fIcon
As0 f62 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f62 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
As0 f62 doCall true #txt
As0 f62 requestActionDecl '<> param;
' #txt
As0 f62 responseActionDecl 'ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f62 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
As0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize case 
data model</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f62 1211 447 112 44 -38 -20 #rect
As0 f62 @|CallSubIcon #fIcon
As0 f52 expr out #txt
As0 f52 1267 491 1267 529 #arcP
As0 f34 expr out #txt
As0 f34 1267 553 1267 581 #arcP
As0 f0 expr in #txt
As0 f0 799 337 1211 469 #arcP
As0 f0 0 0.6190089580954733 0 0 #arcLabel
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
As0 f1 1155 35 26 26 16 -3 #rect
As0 f1 @|RichDialogProcessStartIcon #fIcon
As0 f2 expr out #txt
As0 f2 1168 61 995 173 #arcP
As0 f2 1 1168 173 #addKink
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
As0 f3 208 432 48 32 -25 18 #rect
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
As0 f7 91 435 26 26 -57 15 #rect
As0 f7 @|RichDialogMethodStartIcon #fIcon
As0 f8 expr out #txt
As0 f8 117 448 208 448 #arcP
As0 f4 expr out #txt
As0 f4 256 240 256 272 #arcP
As0 f5 expr out #txt
As0 f5 88 68 232 288 #arcP
As0 f5 1 88 288 #addKink
As0 f5 0 0.8605300296400707 0 0 #arcLabel
As0 f6 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f6 339 435 26 26 0 12 #rect
As0 f6 @|RichDialogProcessEndIcon #fIcon
As0 f9 expr out #txt
As0 f9 256 448 339 448 #arcP
As0 f10 guid 1612B6262174A058 #txt
As0 f10 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f10 method reserveTask() #txt
As0 f10 disableUIEvents false #txt
As0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f10 outParameterDecl '<> result;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask()</name>
    </language>
</elementInfo>
' #txt
As0 f10 773 35 26 26 -38 15 #rect
As0 f10 @|RichDialogMethodStartIcon #fIcon
As0 f11 guid 1612B626D52C92AB #txt
As0 f11 type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f11 method leave() #txt
As0 f11 disableUIEvents false #txt
As0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f11 outParameterDecl '<> result;
' #txt
As0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave()</name>
    </language>
</elementInfo>
' #txt
As0 f11 563 35 26 26 -18 15 #rect
As0 f11 @|RichDialogMethodStartIcon #fIcon
As0 f12 expr out #txt
As0 f12 786 60 787 97 #arcP
As0 f13 expr out #txt
As0 f13 576 61 773 173 #arcP
As0 f13 1 576 173 #addKink
As0 f13 1 0.197032312622628 0 0 #arcLabel
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
As0 f93 mainOut f102 tail #connect
As0 f102 head f85 mainIn #connect
As0 f69 mainOut f121 tail #connect
As0 f121 head f75 mainIn #connect
As0 f75 mainOut f105 tail #connect
As0 f105 head f84 mainIn #connect
As0 f98 out f107 tail #connect
As0 f107 head f79 mainIn #connect
As0 f71 mainOut f101 tail #connect
As0 f101 head f66 mainIn #connect
As0 f82 mainOut f100 tail #connect
As0 f100 head f90 mainIn #connect
As0 f91 mainOut f122 tail #connect
As0 f122 head f98 in #connect
As0 f73 mainOut f118 tail #connect
As0 f118 head f69 mainIn #connect
As0 f72 mainOut f111 tail #connect
As0 f111 head f76 in #connect
As0 f76 out f109 tail #connect
As0 f109 head f83 mainIn #connect
As0 f79 mainOut f124 tail #connect
As0 f124 head f70 in #connect
As0 f70 out f112 tail #connect
As0 f112 head f95 mainIn #connect
As0 f76 out f125 tail #connect
As0 f125 head f98 in #connect
As0 f83 mainOut f114 tail #connect
As0 f114 head f81 mainIn #connect
As0 f70 out f103 tail #connect
As0 f103 head f71 mainIn #connect
As0 f70 out f106 tail #connect
As0 f106 head f82 mainIn #connect
As0 f70 out f117 tail #connect
As0 f117 head f73 mainIn #connect
As0 f67 mainOut f116 tail #connect
As0 f116 head f78 mainIn #connect
As0 f70 out f110 tail #connect
As0 f110 head f67 mainIn #connect
As0 f24 mainOut f34 tail #connect
As0 f34 head f56 mainIn #connect
As0 f62 mainOut f52 tail #connect
As0 f52 head f24 mainIn #connect
As0 f70 out f0 tail #connect
As0 f0 head f62 mainIn #connect
As0 f1 mainOut f2 tail #connect
As0 f2 head f76 in #connect
As0 f7 mainOut f8 tail #connect
As0 f8 head f3 mainIn #connect
As0 f92 mainOut f4 tail #connect
As0 f4 head f93 mainIn #connect
As0 f74 mainOut f5 tail #connect
As0 f5 head f93 mainIn #connect
As0 f3 mainOut f9 tail #connect
As0 f9 head f6 mainIn #connect
As0 f10 mainOut f12 tail #connect
As0 f12 head f91 mainIn #connect
As0 f11 mainOut f13 tail #connect
As0 f13 head f98 in #connect
