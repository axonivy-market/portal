[Ivy]
156A1AA176DE2A21 9.2.0 #module
>Proto >Proto Collection #zClass
As0 ApplicationSelectionMenuProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @GridStep f67 '' #zField
As0 @Alternative f70 '' #zField
As0 @UdMethod f72 '' #zField
As0 @UdInit f74 '' #zField
As0 @Alternative f76 '' #zField
As0 @UdProcessEnd f81 '' #zField
As0 @GridStep f83 '' #zField
As0 @UdInit f87 '' #zField
As0 @GridStep f91 '' #zField
As0 @GridStep f94 '' #zField
As0 @GridStep f95 '' #zField
As0 @CallSub f97 '' #zField
As0 @Alternative f98 '' #zField
As0 @PushWFArc f109 '' #zField
As0 @PushWFArc f110 '' #zField
As0 @PushWFArc f111 '' #zField
As0 @PushWFArc f112 '' #zField
As0 @PushWFArc f114 '' #zField
As0 @PushWFArc f125 '' #zField
As0 @PushWFArc f129 '' #zField
As0 @GridStep f14 '' #zField
As0 @PushWFArc f16 '' #zField
As0 @GridStep f10 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @GridStep f15 '' #zField
As0 @PushWFArc f22 '' #zField
As0 @UdProcessEnd f34 '' #zField
As0 @GridStep f17 '' #zField
As0 @PushWFArc f28 '' #zField
As0 @GridStep f31 '' #zField
As0 @PushWFArc f37 '' #zField
As0 @PushWFArc f0 '' #zField
As0 @PushWFArc f21 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @PushWFArc f24 '' #zField
As0 @PushWFArc f33 '' #zField
As0 @Split f5 '' #zField
As0 @PushWFArc f6 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @UdMethod f4 '' #zField
As0 @GridStep f25 '' #zField
As0 @PushWFArc f26 '' #zField
As0 @UdProcessEnd f9 '' #zField
As0 @PushWFArc f38 '' #zField
As0 @PushWFArc f29 '' #zField
As0 @UdMethod f3 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @UdMethod f12 '' #zField
As0 @PushWFArc f18 '' #zField
As0 @GridStep f19 '' #zField
As0 @PushWFArc f13 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @PushWFArc f30 '' #zField
As0 @GridStep f32 '' #zField
As0 @PushWFArc f36 '' #zField
As0 @PushWFArc f39 '' #zField
As0 @PushWFArc f27 '' #zField
As0 @PushWFArc f35 '' #zField
As0 @UdMethod f40 '' #zField
As0 @PushWFArc f1 '' #zField
As0 @UdMethod f2 '' #zField
As0 @UdProcessEnd f41 '' #zField
As0 @GridStep f43 '' #zField
As0 @PushWFArc f44 '' #zField
As0 @PushWFArc f42 '' #zField
As0 @UdMethod f45 '' #zField
As0 @UdProcessEnd f46 '' #zField
As0 @PushWFArc f47 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
As0 f67 actionTable 'out=in;
' #txt
As0 f67 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.selectedSubMenuItem.buildLink());' #txt
As0 f67 security system #txt
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
As0 f70 848 368 32 32 0 16 #rect
As0 f70 @|AlternativeIcon #fIcon
As0 f72 guid 15FB36E87007F717 #txt
As0 f72 method openApp(String,ch.ivyteam.ivy.workflow.ITask) #txt
As0 f72 inParameterDecl '<String appUrl,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
As0 f72 inParameterMapAction 'out.applicationUrl=param.appUrl;
out.selectedSubMenuItem=null;
out.workingTask=param.task;
' #txt
As0 f72 outParameterDecl '<> result;' #txt
As0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openApp(String,ITask)</name>
    </language>
</elementInfo>
' #txt
As0 f72 1013 21 22 22 14 0 #rect
As0 f72 @|UdMethodIcon #fIcon
As0 f74 guid 15FB36E8701C0FCF #txt
As0 f74 method StartNotRequiredLogin() #txt
As0 f74 inParameterDecl '<> param;' #txt
As0 f74 inParameterMapAction 'out.isNotRequiredLogin=true;
' #txt
As0 f74 outParameterDecl '<> result;' #txt
As0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>StartNotRequiredLogin()</name>
    </language>
</elementInfo>
' #txt
As0 f74 68 20 24 24 14 0 #rect
As0 f74 @|UdInitIcon #fIcon
As0 f76 1008 176 32 32 0 16 #rect
As0 f76 @|AlternativeIcon #fIcon
As0 f81 1171 275 26 26 0 12 #rect
As0 f81 @|UdProcessEndIcon #fIcon
As0 f83 actionTable 'out=in;
' #txt
As0 f83 actionCode 'import ch.ivy.addon.portalkit.util.PrimeFacesUtils;

PrimeFacesUtils.executeScript("PF(''task-losing-confirmation-dialog'').show()");' #txt
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
As0 f87 guid 15FB36E87031CAD2 #txt
As0 f87 method start() #txt
As0 f87 inParameterDecl '<> param;' #txt
As0 f87 outParameterDecl '<> result;' #txt
As0 f87 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f87 260 20 24 24 13 0 #rect
As0 f87 @|UdInitIcon #fIcon
As0 f91 actionTable 'out=in;
' #txt
As0 f91 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(in.#workingTask != null ? in.workingTask : ivy.task);' #txt
As0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserve task</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f91 816 68 96 40 -29 -6 #rect
As0 f91 @|StepIcon #fIcon
As0 f94 actionTable 'out=in;
' #txt
As0 f94 actionCode 'import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import java.util.Collections;

Collections.sort(in.applications, new ApplicationIndexAscendingComparator());' #txt
As0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort applications</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f94 248 176 48 32 33 -8 #rect
As0 f94 @|StepIcon #fIcon
As0 f95 actionTable 'out=in;
' #txt
As0 f95 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.applicationUrl);' #txt
As0 f95 security system #txt
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
As0 f97 processCall 'Business Processes/FindApplicationsByUser:findApplicationsByUser(String)' #txt
As0 f97 requestActionDecl '<String username> param;' #txt
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
As0 f97 248 96 48 32 38 -3 #rect
As0 f97 @|CallSubIcon #fIcon
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
As0 f109 outCond 'in.isWorkingOnATask && in.workingTask.getState() != ch.ivyteam.ivy.workflow.TaskState.DONE' #txt
As0 f109 1024 208 1024 266 #arcP
As0 f110 expr in #txt
As0 f110 outCond 'in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.CUSTOM || in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.EXTERNAL_LINK' #txt
As0 f110 848 384 544 512 #arcP
As0 f110 1 544 384 #addKink
As0 f110 0 0.8608001985669408 0 0 #arcLabel
As0 f111 expr out #txt
As0 f111 1024 43 1024 176 #arcP
As0 f111 0 0.8554291471631579 0 0 #arcLabel
As0 f112 expr in #txt
As0 f112 outCond '(!in.#selectedSubMenuItem is initialized) && (!in.#destinationBreadcrumbPage is initialized)' #txt
As0 f112 853 389 704 512 #arcP
As0 f112 1 704 448 #addKink
As0 f112 0 0.8119251469460217 0 0 #arcLabel
As0 f114 expr out #txt
As0 f114 1088 288 1171 288 #arcP
As0 f114 0 0.2964491651298413 0 0 #arcLabel
As0 f125 expr in #txt
As0 f125 1008 192 878 192 #arcP
As0 f129 expr out #txt
As0 f129 272 128 272 176 #arcP
As0 f14 actionTable 'out=in;
' #txt
As0 f14 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.#workingTask != null ? in.workingTask : ivy.task);
' #txt
As0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave and &#xD;
reset task</name>
    </language>
</elementInfo>
' #txt
As0 f14 648 68 112 40 -30 -16 #rect
As0 f14 @|StepIcon #fIcon
As0 f16 expr in #txt
As0 f16 864 206 864 368 #arcP
As0 f10 actionTable 'out=in;
' #txt
As0 f10 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator.navigateToPortalProcess();' #txt
As0 f10 security system #txt
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
As0 f15 actionTable 'out=in;
' #txt
As0 f15 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator.navigateToPortalStatistic();' #txt
As0 f15 security system #txt
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
As0 f22 outCond 'in.#selectedSubMenuItem.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.STATISTICS' #txt
As0 f22 875 389 1024 512 #arcP
As0 f22 1 1024 448 #addKink
As0 f22 0 0.6303319079715384 0 0 #arcLabel
As0 f34 851 691 26 26 0 12 #rect
As0 f34 @|UdProcessEndIcon #fIcon
As0 f17 actionTable 'out=in;
' #txt
As0 f17 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

in.destinationBreadcrumbPage = null;
PortalNavigator.navigateToPortalTask();' #txt
As0 f17 security system #txt
As0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to task list</name>
    </language>
</elementInfo>
' #txt
As0 f17 1276 512 136 48 -52 -8 #rect
As0 f17 @|StepIcon #fIcon
As0 f28 expr out #txt
As0 f28 1344 560 877 704 #arcP
As0 f28 1 1344 704 #addKink
As0 f28 1 0.35157960906624564 0 0 #arcLabel
As0 f31 actionTable 'out=in;
' #txt
As0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator.navigateToPortalCase();' #txt
As0 f31 security system #txt
As0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to case list</name>
    </language>
</elementInfo>
' #txt
As0 f31 1124 512 120 48 -53 -8 #rect
As0 f31 @|StepIcon #fIcon
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
As0 f33 expr out #txt
As0 f33 272 44 272 96 #arcP
As0 f5 actionTable 'out1=in;
' #txt
As0 f5 152 240 32 32 0 16 #rect
As0 f5 @|ThreadIcon #fIcon
As0 f6 expr out #txt
As0 f6 272 208 184 256 #arcP
As0 f6 1 272 256 #addKink
As0 f6 1 0.18663324733876824 0 0 #arcLabel
As0 f7 expr out #txt
As0 f7 80 44 152 256 #arcP
As0 f7 1 80 256 #addKink
As0 f7 1 0.4001152932228009 0 0 #arcLabel
As0 f4 guid 16D420B337DFE68F #txt
As0 f4 method fetchMenuItem() #txt
As0 f4 inParameterDecl '<> param;' #txt
As0 f4 outParameterDecl '<> result;' #txt
As0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>fetchMenuItem</name>
    </language>
</elementInfo>
' #txt
As0 f4 491 19 26 26 14 6 #rect
As0 f4 @|UdMethodIcon #fIcon
As0 f25 actionTable 'out=in;
' #txt
As0 f25 actionCode 'import ch.addon.portal.generic.menu.MenuView;

MenuView menu = new MenuView();
menu.buildMenuView(in.applications);' #txt
As0 f25 security system #txt
As0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reload menu when&#xD;
language changed</name>
    </language>
</elementInfo>
' #txt
As0 f25 104 384 128 48 -55 -16 #rect
As0 f25 @|StepIcon #fIcon
As0 f26 expr out #txt
As0 f26 504 45 232 408 #arcP
As0 f26 1 504 304 #addKink
As0 f26 2 384 408 #addKink
As0 f26 1 0.5405595343708136 0 0 #arcLabel
As0 f9 155 507 26 26 0 12 #rect
As0 f9 @|UdProcessEndIcon #fIcon
As0 f38 expr out #txt
As0 f38 168 432 168 507 #arcP
As0 f29 expr out1 #txt
As0 f29 168 272 168 384 #arcP
As0 f29 0 0.18808549022839285 0 0 #arcLabel
As0 f3 guid 16E3A96DE4048374 #txt
As0 f3 method leave(ITask) #txt
As0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask workingTask> param;' #txt
As0 f3 inParameterMapAction 'out.workingTask=param.#workingTask;
' #txt
As0 f3 outParameterDecl '<> result;' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave(ITask)</name>
    </language>
</elementInfo>
' #txt
As0 f3 691 19 26 26 5 11 #rect
As0 f3 @|UdMethodIcon #fIcon
As0 f8 704 45 704 68 #arcP
As0 f12 guid 16E3A985548930F5 #txt
As0 f12 method reserveTask(ITask) #txt
As0 f12 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask workingTask> param;' #txt
As0 f12 inParameterMapAction 'out.workingTask=param.workingTask;
' #txt
As0 f12 outParameterDecl '<> result;' #txt
As0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask(ITask)</name>
    </language>
</elementInfo>
' #txt
As0 f12 851 19 26 26 5 14 #rect
As0 f12 @|UdMethodIcon #fIcon
As0 f18 864 45 864 68 #arcP
As0 f19 actionTable 'out=in;
' #txt
As0 f19 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

String displayMessageAfterFinishOrLeaveTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable) : true;
if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	if (!flash.containsKey("overridePortalGrowl")) {
		FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
		FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
	}
	flash.setRedirect(true);
	flash.setKeepMessages(true);
}' #txt
As0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
As0 f19 632 130 144 44 -54 -16 #rect
As0 f19 @|StepIcon #fIcon
As0 f13 704 108 704 130 #arcP
As0 f20 864 108 776 152 #arcP
As0 f20 1 864 152 #addKink
As0 f20 1 0.25 0 0 #arcLabel
As0 f30 704 174 850 192 #arcP
As0 f30 1 704 192 #addKink
As0 f30 1 0.24741320293901495 0 0 #arcLabel
As0 f32 actionTable 'out=in;
' #txt
As0 f32 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

in.destinationBreadcrumbPage = null;
PortalNavigator.navigateToPortalHome();' #txt
As0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to Home&#13;
from breadcrumb</name>
    </language>
</elementInfo>
' #txt
As0 f32 1448 514 128 44 -44 -16 #rect
As0 f32 @|StepIcon #fIcon
As0 f36 expr in #txt
As0 f36 outCond 'ch.ivy.addon.portalkit.enums.BreadCrumbKind.HOME.name().equals(in.#destinationBreadcrumbPage)' #txt
As0 f36 880 384 1512 514 #arcP
As0 f36 1 1512 384 #addKink
As0 f36 0 0.6017546259697808 0 0 #arcLabel
As0 f39 1512 558 877 704 #arcP
As0 f39 1 1512 704 #addKink
As0 f39 1 0.48910616495972775 0 0 #arcLabel
As0 f27 expr in #txt
As0 f27 outCond 'in.#selectedSubMenuItem.#menuKind  == ch.ivy.addon.portalkit.enums.MenuKind.TASK || ch.ivy.addon.portalkit.enums.BreadCrumbKind.TASK.name().equals(in.#destinationBreadcrumbPage)' #txt
As0 f27 880 384 1344 512 #arcP
As0 f27 1 1344 384 #addKink
As0 f27 0 0.6567363686648227 0 0 #arcLabel
As0 f35 expr in #txt
As0 f35 877 387 1184 512 #arcP
As0 f35 1 1184 448 #addKink
As0 f35 0 0.6300696351252247 0 0 #arcLabel
As0 f40 guid 1720D07F42B8F9E8 #txt
As0 f40 method onClickSubMenuItem(ch.addon.portal.generic.menu.SubMenuItem,ch.ivyteam.ivy.workflow.ITask) #txt
As0 f40 inParameterDecl '<ch.addon.portal.generic.menu.SubMenuItem selectedSubMenuItem,ch.ivyteam.ivy.workflow.ITask workingTask> param;' #txt
As0 f40 inParameterMapAction 'out.selectedSubMenuItem=param.selectedSubMenuItem;
out.workingTask=param.workingTask;
' #txt
As0 f40 outParameterDecl '<> result;' #txt
As0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickSubMenuItem(SubMenuItem,ITask)</name>
    </language>
</elementInfo>
' #txt
As0 f40 1187 19 26 26 -25 15 #rect
As0 f40 @|UdMethodIcon #fIcon
As0 f1 1200 45 1040 192 #arcP
As0 f1 1 1200 192 #addKink
As0 f1 1 0.0471542345010647 0 0 #arcLabel
As0 f2 guid 1720D1735A9827DD #txt
As0 f2 method continueWorkingOnTask() #txt
As0 f2 inParameterDecl '<> param;' #txt
As0 f2 outParameterDecl '<> result;' #txt
As0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>continueWorkingOnTask()</name>
    </language>
</elementInfo>
' #txt
As0 f2 1523 19 26 26 -25 15 #rect
As0 f2 @|UdMethodIcon #fIcon
As0 f41 1523 211 26 26 0 12 #rect
As0 f41 @|UdProcessEndIcon #fIcon
As0 f43 actionTable 'out=in;
' #txt
As0 f43 actionCode 'in.destinationBreadcrumbPage = null;' #txt
As0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset selection</name>
    </language>
</elementInfo>
' #txt
As0 f43 1480 106 112 44 -43 -8 #rect
As0 f43 @|StepIcon #fIcon
As0 f44 1536 45 1536 106 #arcP
As0 f42 1536 150 1536 211 #arcP
As0 f45 guid 1734B654FF31BF39 #txt
As0 f45 method init(Boolean) #txt
As0 f45 inParameterDecl '<Boolean isWorkingOnATask> param;' #txt
As0 f45 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask;
' #txt
As0 f45 outParameterDecl '<> result;' #txt
As0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init(Boolean)</name>
    </language>
</elementInfo>
' #txt
As0 f45 83 659 26 26 -23 15 #rect
As0 f45 @|UdMethodIcon #fIcon
As0 f46 307 659 26 26 0 12 #rect
As0 f46 @|UdProcessEndIcon #fIcon
As0 f47 109 672 307 672 #arcP
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
As0 f98 out f16 tail #connect
As0 f16 head f70 in #connect
As0 f70 out f11 tail #connect
As0 f11 head f10 mainIn #connect
As0 f70 out f22 tail #connect
As0 f22 head f15 mainIn #connect
As0 f70 out f110 tail #connect
As0 f17 mainOut f28 tail #connect
As0 f28 head f34 mainIn #connect
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
As0 f87 mainOut f33 tail #connect
As0 f33 head f97 mainIn #connect
As0 f94 mainOut f6 tail #connect
As0 f6 head f5 in #connect
As0 f74 mainOut f7 tail #connect
As0 f7 head f5 in #connect
As0 f4 mainOut f26 tail #connect
As0 f26 head f25 mainIn #connect
As0 f25 mainOut f38 tail #connect
As0 f38 head f9 mainIn #connect
As0 f5 out f29 tail #connect
As0 f29 head f25 mainIn #connect
As0 f3 mainOut f8 tail #connect
As0 f8 head f14 mainIn #connect
As0 f12 mainOut f18 tail #connect
As0 f18 head f91 mainIn #connect
As0 f14 mainOut f13 tail #connect
As0 f13 head f19 mainIn #connect
As0 f91 mainOut f20 tail #connect
As0 f20 head f19 mainIn #connect
As0 f19 mainOut f30 tail #connect
As0 f30 head f98 in #connect
As0 f70 out f36 tail #connect
As0 f36 head f32 mainIn #connect
As0 f32 mainOut f39 tail #connect
As0 f39 head f34 mainIn #connect
As0 f70 out f27 tail #connect
As0 f27 head f17 mainIn #connect
As0 f70 out f35 tail #connect
As0 f35 head f31 mainIn #connect
As0 f40 mainOut f1 tail #connect
As0 f1 head f76 in #connect
As0 f2 mainOut f44 tail #connect
As0 f44 head f43 mainIn #connect
As0 f43 mainOut f42 tail #connect
As0 f42 head f41 mainIn #connect
As0 f45 mainOut f47 tail #connect
As0 f47 head f46 mainIn #connect
