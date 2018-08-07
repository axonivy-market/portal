[Ivy]
[>Created: Mon Dec 28 11:39:17 ICT 2015]
14C2C60920CC3DD1 3.18 #module
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
As0 @RichDialogProcessStart f5 '' #zField
As0 @GridStep f9 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f13 '' #zField
As0 @CallSub f4 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @RichDialogInitStart f20 '' #zField
As0 @PushWFArc f21 '' #zField
As0 @GridStep f22 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @PushWFArc f3 '' #zField
As0 @PushWFArc f6 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @RichDialogMethodStart f10 '' #zField
As0 @RichDialogProcessEnd f11 '' #zField
As0 @GridStep f14 '' #zField
As0 @PushWFArc f15 '' #zField
As0 @PushWFArc f12 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f0 guid 14AF0B1C8DE6C030 #txt
As0 f0 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f0 method start(String) #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String appName> param = methodEvent.getInputArguments();
' #txt
As0 f0 inParameterMapAction 'out.appName=param.appName;
' #txt
As0 f0 outParameterDecl '<> result;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
As0 f0 198 62 20 20 13 0 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f1 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f1 198 366 20 20 13 0 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f5 guid 14B062E57487FE15 #txt
As0 f5 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f5 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f5 actionTable 'out=in;
' #txt
As0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onRefresh</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f5 478 55 20 19 13 0 #rect
As0 f5 @|RichDialogProcessStartIcon #fIcon
As0 f9 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f9 actionTable 'out=in;
' #txt
As0 f9 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import org.primefaces.component.commandbutton.CommandButton;
import ch.ivy.addon.portalkit.util.CookieHelper;
import org.primefaces.component.button.Button;

Button menuItemMatchesPortalLink;
Button menuItemMatchesProcessModel;
String portalLinkMatchesProcessModel;
Button menuItemMatchesApplication;
String portalLinkMatchesApplication;

String hostNameAndContexPathWithoutPortNumber;
String hostNameAndContextPath = in.hostName+in.contextPath;
int portNumberIndex = in.hostName.indexOf(":", 5);
if(portNumberIndex > -1) {
	hostNameAndContexPathWithoutPortNumber = in.hostName.substring(0, portNumberIndex) + in.contextPath;
} else {
	hostNameAndContexPathWithoutPortNumber = hostNameAndContextPath;
}

//get home button
Boolean checkHomeExist = false;
for (Application application : in.applications){
	 Button menuItem = new Button();
	 menuItem.value = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
	 if(!(application.#serverId is initialized)){
				String requestUri = SecurityServiceUtils.getProcessUri(SecurityServiceUtils.INTERNAL_CUSTOM_APPLICATION);
				menuItem.href = in.contextPath + requestUri + "?url=" + application.link + "&appName=" + application.name;
		}else{
				menuItem.href = application.link;
		}
	 menuItem.icon = "fa "+application.menuIcon;
	
	 if (!application.isOnline) {
			menuItem.disabled = true;
	 }
	 if (application.isVisible) {
	 		in.menuItems.add(menuItem);
			if (!(application.#serverId is initialized)) {
				if (in.appName.equals(application.name)) {
					menuItem.styleClass = "ivy-active";
					CookieHelper cookieHelper = new CookieHelper();
					cookieHelper.setCookie("lastWorkingApp", application.link);
				}
			} else if(!#menuItemMatchesPortalLink is initialized){
				if (application.link.contains(hostNameAndContexPathWithoutPortNumber) || application.link.contains(hostNameAndContextPath)) {
				  if (application.link.contains(in.processStartLink)) {
						menuItemMatchesPortalLink = menuItem;
						menuItem.styleClass = "ivy-active";
						CookieHelper cookieHelper = new CookieHelper();
						cookieHelper.setCookie("lastWorkingApp", application.link);
				  } else if (application.link.contains(in.processModelName) && in.appName.equals(application.name) && (!#menuItemMatchesProcessModel is initialized)) {
						portalLinkMatchesProcessModel = application.link;
						menuItemMatchesProcessModel = menuItem;
					} else if (in.appName.equals(application.name) && (!#menuItemMatchesApplication is initialized)) {	
						portalLinkMatchesApplication = application.link;
						menuItemMatchesApplication = menuItem;
				  }
				}
			}
	 }
}

if (!#menuItemMatchesPortalLink is initialized) {
		if(#menuItemMatchesProcessModel is initialized){
			menuItemMatchesProcessModel.styleClass = "ivy-active";
			CookieHelper cookieHelper = new CookieHelper();
			cookieHelper.setCookie("lastWorkingApp", portalLinkMatchesProcessModel);
	  } else if(#menuItemMatchesApplication is initialized){
			menuItemMatchesApplication.styleClass = "ivy-active";
			CookieHelper cookieHelper = new CookieHelper();
			cookieHelper.setCookie("lastWorkingApp", portalLinkMatchesApplication);
		}
}' #txt
As0 f9 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
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
As0 f9 188 300 40 24 27 -14 #rect
As0 f9 @|StepIcon #fIcon
As0 f2 expr out #txt
As0 f2 208 324 208 366 #arcP
As0 f13 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f13 actionTable 'out=in;
' #txt
As0 f13 actionCode 'import ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ServerInformation;
import ch.ivy.addon.portalkit.util.IvyCaseUtility;
import org.apache.commons.lang.StringUtils;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

if(StringUtils.isEmpty(in.appName)){
	in.appName = ivy.request.getApplication().getName();
}

in.processStartLink = IvyCaseUtility.getCurrentProcessStartLink();
in.processModelName = IvyCaseUtility.getCurrentProcessModelName();

in.displayName = ivy.session.getSessionUser().getFullName();
in.applications.clear();
 
FacesContext facesContext = FacesContext.getCurrentInstance(); 
ExternalContext externalContext = facesContext.getExternalContext();
HttpServletRequest req = externalContext.getRequest() as HttpServletRequest;
in.contextPath = externalContext.getRequestContextPath();

String requestUrl = req.getRequestURL().toString();
in.hostName = requestUrl.substring(0, requestUrl.length() - req.getRequestURI().length());

in.serverInformation = new ServerInformation();' #txt
As0 f13 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set hostName, contextPath,
processStartLink, appName, displayName</name>
        <nameStyle>65,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f13 190 124 36 24 24 -14 #rect
As0 f13 @|StepIcon #fIcon
As0 f4 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f4 processCall 'Business Processes/FindApplicationsByUser:findApplicationsByUser(String)' #txt
As0 f4 doCall true #txt
As0 f4 requestActionDecl '<java.lang.String username> param;
' #txt
As0 f4 requestMappingAction 'param.username=ivy.session.getSessionUserName();
' #txt
As0 f4 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
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
As0 f4 190 188 36 24 20 -2 #rect
As0 f4 @|CallSubIcon #fIcon
As0 f7 expr out #txt
As0 f7 208 148 208 188 #arcP
As0 f20 guid 14EA97867FAAF9AA #txt
As0 f20 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
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
As0 f21 expr out #txt
As0 f21 32 75 198 376 #arcP
As0 f21 1 32 376 #addKink
As0 f21 0 0.7080705449857414 0 0 #arcLabel
As0 f22 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f22 actionTable 'out=in;
' #txt
As0 f22 actionCode 'import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import java.util.Collections;

Collections.sort(in.applications, new ApplicationIndexAscendingComparator());' #txt
As0 f22 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort applications</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f22 190 244 36 24 20 -2 #rect
As0 f22 @|StepIcon #fIcon
As0 f23 expr out #txt
As0 f23 208 212 208 244 #arcP
As0 f3 expr out #txt
As0 f3 208 268 208 300 #arcP
As0 f6 expr out #txt
As0 f6 488 73 226 136 #arcP
As0 f6 1 488 136 #addKink
As0 f6 1 0.017109181470223493 0 0 #arcLabel
As0 f8 expr out #txt
As0 f8 208 82 208 124 #arcP
As0 f10 guid 150830E3579CC17E #txt
As0 f10 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f10 method resetCurrentTask() #txt
As0 f10 disableUIEvents false #txt
As0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f10 outParameterDecl '<> result;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetCurrentTask()</name>
    </language>
</elementInfo>
' #txt
As0 f10 661 53 22 22 14 0 #rect
As0 f10 @|RichDialogMethodStartIcon #fIcon
As0 f11 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f11 661 181 22 22 14 0 #rect
As0 f11 @|RichDialogProcessEndIcon #fIcon
As0 f14 actionDecl 'ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData out;
' #txt
As0 f14 actionTable 'out=in;
' #txt
As0 f14 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
TaskUtils.resetTask(ivy.task);' #txt
As0 f14 type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
As0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset current task</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f14 654 116 36 24 20 -2 #rect
As0 f14 @|StepIcon #fIcon
As0 f15 expr out #txt
As0 f15 672 75 672 116 #arcP
As0 f12 expr out #txt
As0 f12 672 140 672 181 #arcP
>Proto As0 .type ch.ivy.addon.portalkit.multiapp.general.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>startMethods</swimlaneLabel>
        <swimlaneLabel>events</swimlaneLabel>
        <swimlaneLabel>Method
</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>440</swimlaneSize>
    <swimlaneSize>168</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-13057</swimlaneColor>
    <swimlaneColor gradient="true">-3355393</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f9 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f13 mainOut f7 tail #connect
As0 f7 head f4 mainIn #connect
As0 f20 mainOut f21 tail #connect
As0 f21 head f1 mainIn #connect
As0 f4 mainOut f23 tail #connect
As0 f23 head f22 mainIn #connect
As0 f22 mainOut f3 tail #connect
As0 f3 head f9 mainIn #connect
As0 f5 mainOut f6 tail #connect
As0 f6 head f13 mainIn #connect
As0 f0 mainOut f8 tail #connect
As0 f8 head f13 mainIn #connect
As0 f10 mainOut f15 tail #connect
As0 f15 head f14 mainIn #connect
As0 f14 mainOut f12 tail #connect
As0 f12 head f11 mainIn #connect
