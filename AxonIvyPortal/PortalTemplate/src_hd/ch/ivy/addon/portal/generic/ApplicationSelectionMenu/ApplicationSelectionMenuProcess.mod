[Ivy]
156A1AA176DE2A21 9.3.1 #module
>Proto >Proto Collection #zClass
As0 ApplicationSelectionMenuProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f74 '' #zField
As0 @UdInit f87 '' #zField
As0 @GridStep f91 '' #zField
As0 @GridStep f14 '' #zField
As0 @UdProcessEnd f9 '' #zField
As0 @UdMethod f3 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @UdMethod f12 '' #zField
As0 @PushWFArc f18 '' #zField
As0 @GridStep f19 '' #zField
As0 @PushWFArc f13 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @UdMethod f2 '' #zField
As0 @UdProcessEnd f41 '' #zField
As0 @UdMethod f45 '' #zField
As0 @UdProcessEnd f46 '' #zField
As0 @InfoButton f4 '' #zField
As0 @AnnotationArc f26 '' #zField
As0 @PushWFArc f25 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @GridStep f5 '' #zField
As0 @UdProcessEnd f29 '' #zField
As0 @PushWFArc f33 '' #zField
As0 @PushWFArc f1 '' #zField
As0 @GridStep f6 '' #zField
As0 @PushWFArc f30 '' #zField
As0 @PushWFArc f40 '' #zField
As0 @UdMethod f0 '' #zField
As0 @UdProcessEnd f15 '' #zField
As0 @PushWFArc f17 '' #zField
As0 @PushWFArc f10 '' #zField
As0 @UdMethod f11 '' #zField
As0 @UdProcessEnd f16 '' #zField
As0 @GridStep f22 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @PushWFArc f21 '' #zField
>Proto As0 As0 ApplicationSelectionMenuProcess #zField
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
As0 f74 92 172 24 24 14 0 #rect
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
As0 f87 92 84 24 24 13 0 #rect
As0 f91 actionTable 'out=in;
' #txt
As0 f91 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(in.#workingTask != null ? in.workingTask : ivy.task);' #txt
As0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reserve task</name>
        <nameStyle>1,5
11,5
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f91 192 474 112 44 -36 -8 #rect
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
As0 f14 192 572 112 40 -30 -16 #rect
As0 f9 307 83 26 26 0 12 #rect
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
As0 f3 83 579 26 26 -24 14 #rect
As0 f8 109 592 192 592 #arcP
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
As0 f12 83 483 26 26 -47 16 #rect
As0 f18 109 496 192 496 #arcP
As0 f19 actionTable 'out=in;
' #txt
As0 f19 actionCode 'import ch.ivy.addon.portalkit.util.GrowlMessageUtils;

GrowlMessageUtils.addFeedbackMessage(false, (in.#workingTask != null ? in.workingTask : ivy.task).getCase());' #txt
As0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
As0 f19 408 474 144 44 -54 -16 #rect
As0 f13 304 592 480 518 #arcP
As0 f13 1 480 592 #addKink
As0 f13 0 0.8907686408955474 0 0 #arcLabel
As0 f20 304 496 408 496 #arcP
As0 f20 0 0.25 0 0 #arcLabel
As0 f2 guid 1720D1735A9827DD #txt
As0 f2 method continueWorkingOnTask() #txt
As0 f2 inParameterDecl '<> param;' #txt
As0 f2 inParameterMapAction 'out.destinationBreadcrumbPage=null;
out.isClickOnBreadcrumb=false;
' #txt
As0 f2 outParameterDecl '<> result;' #txt
As0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>continueWorkingOnTask()</name>
    </language>
</elementInfo>
' #txt
As0 f2 91 275 26 26 -70 17 #rect
As0 f41 307 275 26 26 0 12 #rect
As0 f45 guid 1734B654FF31BF39 #txt
As0 f45 method init(Boolean,ch.ivyteam.ivy.workflow.ITask) #txt
As0 f45 inParameterDecl '<Boolean isWorkingOnATask,ch.ivyteam.ivy.workflow.ITask workingTask> param;' #txt
As0 f45 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask;
out.workingTask=param.workingTask;
' #txt
As0 f45 outParameterDecl '<> result;' #txt
As0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init(Boolean,ITask)</name>
    </language>
</elementInfo>
' #txt
As0 f45 595 83 26 26 -53 16 #rect
As0 f46 915 83 26 26 0 12 #rect
As0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sample scenario to test&#13;
start a task &gt; click on menu &gt; leave the task</name>
    </language>
</elementInfo>
' #txt
As0 f4 568 570 256 44 -118 -16 #rect
As0 f26 696 570 530 518 #arcP
As0 f25 expr out #txt
As0 f25 116 184 320 109 #arcP
As0 f25 1 320 184 #addKink
As0 f25 1 0.4001152932228009 0 0 #arcLabel
As0 f7 expr out #txt
As0 f7 116 96 307 96 #arcP
As0 f7 0 0.11179083649811018 0 0 #arcLabel
As0 f5 actionTable 'out=in;
' #txt
As0 f5 actionCode 'import ch.addon.portal.generic.menu.MenuView;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

MenuView menuView = ManagedBeans.get("menuView") as MenuView;
menuView.navigateToTargetPage(in.isClickOnBreadcrumb, in.destinationBreadcrumbPage);' #txt
As0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Proceed menu</name>
    </language>
</elementInfo>
' #txt
As0 f5 664 474 112 44 -40 -8 #rect
As0 f29 907 483 26 26 0 12 #rect
As0 f33 776 496 907 496 #arcP
As0 f1 552 496 664 496 #arcP
As0 f1 0 0.9609045819708579 0 0 #arcLabel
As0 f6 actionTable 'out=in;
' #txt
As0 f6 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.addon.portal.generic.menu.MenuView;

MenuView menuView = ManagedBeans.get("menuView") as MenuView;
menuView.buildPortalLeftMenu(in.workingTask, in.isWorkingOnATask);' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call MenuBean</name>
    </language>
</elementInfo>
' #txt
As0 f6 720 74 112 44 -42 -8 #rect
As0 f30 621 96 720 96 #arcP
As0 f40 832 96 915 96 #arcP
As0 f0 guid 17698241579FF362 #txt
As0 f0 method clickBreadcrumb(String) #txt
As0 f0 inParameterDecl '<String destination> param;' #txt
As0 f0 inParameterMapAction 'out.destinationBreadcrumbPage=param.destination;
out.isClickOnBreadcrumb=true;
' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clickBreadcrumb(String)</name>
    </language>
</elementInfo>
' #txt
As0 f0 91 371 26 26 -64 15 #rect
As0 f15 307 371 26 26 0 12 #rect
As0 f17 117 384 307 384 #arcP
As0 f10 117 288 307 288 #arcP
As0 f11 guid 17EF66B4C689BAE3 #txt
As0 f11 method fireEventClickOnMenuItem() #txt
As0 f11 inParameterDecl '<> param;' #txt
As0 f11 outParameterDecl '<> result;' #txt
As0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>fireEventClickOnMenuItem()</name>
    </language>
</elementInfo>
' #txt
As0 f11 83 675 26 26 -57 16 #rect
As0 f16 403 675 26 26 0 12 #rect
As0 f22 actionTable 'out=in;
' #txt
As0 f22 actionCode 'import java.util.Optional;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.addon.portal.generic.menu.MenuView;
import org.primefaces.PrimeFaces;

Object menuItemId = Optional.ofNullable(ivy.session.getAttribute(MenuView.SELECTED_MENU_ID)).orElse(null);
Object preMenuItemId = Optional.ofNullable(ivy.session.getAttribute(MenuView.PREV_SELECTED_MENU_ID)).orElse(null);

PrimeFaces.current().executeScript(String.format(MenuView.CLICK_ON_MENU_ITEM_PATTERN, menuItemId, preMenuItemId));
ivy.session.setAttribute(MenuView.PREV_SELECTED_MENU_ID, menuItemId);' #txt
As0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Fire event click&#13;
on menuItem</name>
    </language>
</elementInfo>
' #txt
As0 f22 192 666 128 44 -40 -16 #rect
As0 f23 109 688 192 688 #arcP
As0 f21 320 688 403 688 #arcP
>Proto As0 .type ch.ivy.addon.portal.generic.ApplicationSelectionMenu.ApplicationSelectionMenuData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>startMethods</swimlaneLabel>
        <swimlaneLabel>LeavingTaskMethods
</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>440</swimlaneSize>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-3355393</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneSpaceBefore>8</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto As0 -8 -8 16 16 16 26 #rect
As0 f3 mainOut f8 tail #connect
As0 f8 head f14 mainIn #connect
As0 f12 mainOut f18 tail #connect
As0 f18 head f91 mainIn #connect
As0 f14 mainOut f13 tail #connect
As0 f13 head f19 mainIn #connect
As0 f91 mainOut f20 tail #connect
As0 f20 head f19 mainIn #connect
As0 f4 ao f26 tail #connect
As0 f26 head f19 @CG|ai #connect
As0 f74 mainOut f25 tail #connect
As0 f25 head f9 mainIn #connect
As0 f87 mainOut f7 tail #connect
As0 f7 head f9 mainIn #connect
As0 f5 mainOut f33 tail #connect
As0 f33 head f29 mainIn #connect
As0 f19 mainOut f1 tail #connect
As0 f1 head f5 mainIn #connect
As0 f45 mainOut f30 tail #connect
As0 f30 head f6 mainIn #connect
As0 f6 mainOut f40 tail #connect
As0 f40 head f46 mainIn #connect
As0 f0 mainOut f17 tail #connect
As0 f17 head f15 mainIn #connect
As0 f2 mainOut f10 tail #connect
As0 f10 head f41 mainIn #connect
As0 f11 mainOut f23 tail #connect
As0 f23 head f22 mainIn #connect
As0 f22 mainOut f21 tail #connect
As0 f21 head f16 mainIn #connect
