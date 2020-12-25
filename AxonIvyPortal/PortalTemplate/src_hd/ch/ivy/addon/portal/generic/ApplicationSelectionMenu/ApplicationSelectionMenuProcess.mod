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
As0 f74 @|UdInitIcon #fIcon
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
As0 f87 @|UdInitIcon #fIcon
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
As0 f91 192 458 112 44 -36 -8 #rect
As0 f91 @|StepIcon #fIcon
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
As0 f14 192 556 112 40 -30 -16 #rect
As0 f14 @|StepIcon #fIcon
As0 f9 307 83 26 26 0 12 #rect
As0 f9 @|UdProcessEndIcon #fIcon
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
As0 f3 83 563 26 26 -24 14 #rect
As0 f3 @|UdMethodIcon #fIcon
As0 f8 109 576 192 576 #arcP
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
As0 f12 83 467 26 26 -47 16 #rect
As0 f12 @|UdMethodIcon #fIcon
As0 f18 109 480 192 480 #arcP
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
As0 f19 408 458 144 44 -54 -16 #rect
As0 f19 @|StepIcon #fIcon
As0 f13 304 576 480 502 #arcP
As0 f13 1 480 576 #addKink
As0 f13 0 0.8907686408955474 0 0 #arcLabel
As0 f20 304 480 408 480 #arcP
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
As0 f2 @|UdMethodIcon #fIcon
As0 f41 307 275 26 26 0 12 #rect
As0 f41 @|UdProcessEndIcon #fIcon
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
As0 f45 @|UdMethodIcon #fIcon
As0 f46 915 83 26 26 0 12 #rect
As0 f46 @|UdProcessEndIcon #fIcon
As0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sample scenario to test&#13;
start a task &gt; click on menu &gt; leave the task</name>
    </language>
</elementInfo>
' #txt
As0 f4 568 554 256 44 -118 -16 #rect
As0 f4 @|IBIcon #fIcon
As0 f26 568 576 480 502 #arcP
As0 f25 expr out #txt
As0 f25 116 184 320 109 #arcP
As0 f25 1 320 184 #addKink
As0 f25 1 0.4001152932228009 0 0 #arcLabel
As0 f7 expr out #txt
As0 f7 116 96 307 96 #arcP
As0 f7 0 0.11179083649811018 0 0 #arcLabel
As0 f5 actionTable 'out=in;
' #txt
As0 f5 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.ApplicationSelectionMenuBean;

ApplicationSelectionMenuBean applicationSelectionMenuBean = ManagedBeans.get("applicationSelectionMenuBean") as ApplicationSelectionMenuBean;
applicationSelectionMenuBean.navigateToTargetPage(in.isClickOnBreadcrumb, in.destinationBreadcrumbPage);' #txt
As0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Proceed menu</name>
    </language>
</elementInfo>
' #txt
As0 f5 664 458 112 44 -40 -8 #rect
As0 f5 @|StepIcon #fIcon
As0 f29 907 467 26 26 0 12 #rect
As0 f29 @|UdProcessEndIcon #fIcon
As0 f33 776 480 907 480 #arcP
As0 f1 552 480 664 480 #arcP
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
As0 f6 @|StepIcon #fIcon
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
As0 f0 @|UdMethodIcon #fIcon
As0 f15 307 371 26 26 0 12 #rect
As0 f15 @|UdProcessEndIcon #fIcon
As0 f17 117 384 307 384 #arcP
As0 f10 117 288 307 288 #arcP
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
    <swimlaneSpaceBefore>8</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
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
