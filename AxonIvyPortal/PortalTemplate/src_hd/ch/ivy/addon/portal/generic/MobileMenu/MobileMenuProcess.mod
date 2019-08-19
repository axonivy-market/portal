[Ivy]
166CEA9D8FCAD3B5 3.28 #module
>Proto >Proto Collection #zClass
As0 MobileMenuProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @Alternative f70 '' #zField
As0 @Alternative f76 '' #zField
As0 @UdProcessEnd f81 '' #zField
As0 @GridStep f83 '' #zField
As0 @UdProcessEnd f85 '' #zField
As0 @UdInit f87 '' #zField
As0 @GridStep f91 '' #zField
As0 @Alternative f98 '' #zField
As0 @PushWFArc f109 '' #zField
As0 @PushWFArc f114 '' #zField
As0 @PushWFArc f122 '' #zField
As0 @PushWFArc f125 '' #zField
As0 @UdEvent f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f14 '' #zField
As0 @PushWFArc f13 '' #zField
As0 @PushWFArc f16 '' #zField
As0 @UdEvent f18 '' #zField
As0 @UdEvent f19 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @PushWFArc f12 '' #zField
As0 @UdProcessEnd f34 '' #zField
As0 @GridStep f17 '' #zField
As0 @PushWFArc f27 '' #zField
As0 @PushWFArc f28 '' #zField
As0 @GridStep f31 '' #zField
As0 @PushWFArc f35 '' #zField
As0 @PushWFArc f37 '' #zField
As0 @PushWFArc f3 '' #zField
As0 @UdEvent f0 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @GridStep f8 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @PushWFArc f6 '' #zField
>Proto As0 As0 MobileMenuProcess #zField
As0 f70 432 400 32 32 0 16 #rect
As0 f70 @|AlternativeIcon #fIcon
As0 f76 592 208 32 32 0 16 #rect
As0 f76 @|AlternativeIcon #fIcon
As0 f81 755 307 26 26 0 12 #rect
As0 f81 @|UdProcessEndIcon #fIcon
As0 f83 actionTable 'out=in;
' #txt
As0 f83 actionCode 'import org.primefaces.context.RequestContext;

RequestContext.getCurrentInstance().execute("PF(''task-losing-confirmation-dialog'').show()");' #txt
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
As0 f83 544 298 128 44 -42 -16 #rect
As0 f83 @|StepIcon #fIcon
As0 f85 84 212 24 24 13 0 #rect
As0 f85 @|UdProcessEndIcon #fIcon
As0 f87 guid 15FB36E87031CAD2 #txt
As0 f87 method start(String) #txt
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
As0 f87 84 52 24 24 13 0 #rect
As0 f87 @|UdInitIcon #fIcon
As0 f91 actionTable 'out=in;
' #txt
As0 f91 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(ivy.task);' #txt
As0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserve task</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f91 400 124 96 40 -29 -6 #rect
As0 f91 @|StepIcon #fIcon
As0 f98 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
As0 f98 434 210 28 28 14 0 #rect
As0 f98 @|AlternativeIcon #fIcon
As0 f109 expr in #txt
As0 f109 outCond in.isWorkingOnATask #txt
As0 f109 608 240 608 298 #arcP
As0 f114 expr out #txt
As0 f114 672 320 755 320 #arcP
As0 f114 0 0.2964491651298413 0 0 #arcLabel
As0 f122 expr out #txt
As0 f122 448 164 448 210 #arcP
As0 f125 expr in #txt
As0 f125 592 224 462 224 #arcP
As0 f1 guid 15FB83C392F10C9D #txt
As0 f1 actionTable 'out=in;
out.menuKind=ch.ivy.addon.portalkit.enums.MenuKind.TASK;
' #txt
As0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickTasks</name>
    </language>
</elementInfo>
' #txt
As0 f1 595 51 26 26 16 -3 #rect
As0 f1 @|UdEventIcon #fIcon
As0 f2 expr out #txt
As0 f2 608 77 608 208 #arcP
As0 f2 0 0.5848472917488723 0 0 #arcLabel
As0 f14 actionTable 'out=in;
' #txt
As0 f14 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(ivy.task);
' #txt
As0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave and &#xD;
reset task</name>
    </language>
</elementInfo>
' #txt
As0 f14 232 124 112 40 -30 -16 #rect
As0 f14 @|StepIcon #fIcon
As0 f13 expr out #txt
As0 f13 288 164 434 224 #arcP
As0 f13 1 288 224 #addKink
As0 f13 1 0.302020000020303 0 0 #arcLabel
As0 f16 expr in #txt
As0 f16 448 238 448 400 #arcP
As0 f18 guid 163FD88EDB522F75 #txt
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
As0 f18 275 51 26 26 21 -1 #rect
As0 f18 @|UdEventIcon #fIcon
As0 f19 guid 163FD891A0AB1B03 #txt
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
As0 f19 435 51 26 26 21 -4 #rect
As0 f19 @|UdEventIcon #fIcon
As0 f20 expr out #txt
As0 f20 448 77 448 124 #arcP
As0 f12 expr out #txt
As0 f12 288 77 288 124 #arcP
As0 f34 435 595 26 26 0 12 #rect
As0 f34 @|UdProcessEndIcon #fIcon
As0 f17 actionTable 'out=in;
' #txt
As0 f17 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToMobilePortalTask();' #txt
As0 f17 security system #txt
As0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to task list</name>
    </language>
</elementInfo>
' #txt
As0 f17 188 488 136 48 -52 -8 #rect
As0 f17 @|StepIcon #fIcon
As0 f27 expr in #txt
As0 f27 outCond 'in.#menuKind == ch.ivy.addon.portalkit.enums.MenuKind.TASK' #txt
As0 f27 432 416 256 488 #arcP
As0 f27 1 256 416 #addKink
As0 f27 0 0.5801593067057396 0 0 #arcLabel
As0 f28 expr out #txt
As0 f28 256 536 435 608 #arcP
As0 f28 1 256 608 #addKink
As0 f28 1 0.35157960906624564 0 0 #arcLabel
As0 f31 actionTable 'out=in;
' #txt
As0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToMobilePortalCase();' #txt
As0 f31 security system #txt
As0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to case list</name>
    </language>
</elementInfo>
' #txt
As0 f31 548 488 120 48 -53 -8 #rect
As0 f31 @|StepIcon #fIcon
As0 f35 expr in #txt
As0 f35 464 416 608 488 #arcP
As0 f35 1 608 416 #addKink
As0 f35 0 0.9110242418509811 0 0 #arcLabel
As0 f37 expr out #txt
As0 f37 608 536 461 608 #arcP
As0 f37 1 608 608 #addKink
As0 f37 1 0.8827343468967564 0 0 #arcLabel
As0 f3 expr out #txt
As0 f3 96 76 96 212 #arcP
As0 f0 guid 166D246F7171F299 #txt
As0 f0 actionTable 'out=in;
out.menuKind=ch.ivy.addon.portalkit.enums.MenuKind.PROCESS;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickProcesses</name>
    </language>
</elementInfo>
' #txt
As0 f0 722 52 26 26 16 -3 #rect
As0 f0 @|UdEventIcon #fIcon
As0 f4 expr out #txt
As0 f4 735 77 624 224 #arcP
As0 f4 1 736 224 #addKink
As0 f4 0 0.9062480058623832 0 0 #arcLabel
As0 f8 actionTable 'out=in;
' #txt
As0 f8 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToMobilePortalProcess();' #txt
As0 f8 security system #txt
As0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate to process list</name>
    </language>
</elementInfo>
' #txt
As0 f8 376 490 144 44 -63 -8 #rect
As0 f8 @|StepIcon #fIcon
As0 f9 expr in #txt
As0 f9 outCond 'in.menuKind == ch.ivy.addon.portalkit.enums.MenuKind.PROCESS' #txt
As0 f9 448 432 448 490 #arcP
As0 f9 0 0.32034598197015046 0 0 #arcLabel
As0 f6 expr out #txt
As0 f6 448 534 448 595 #arcP
>Proto As0 .type ch.ivy.addon.portal.generic.MobileMenu.MobileMenuData #txt
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
As0 f91 mainOut f122 tail #connect
As0 f122 head f98 in #connect
As0 f76 out f109 tail #connect
As0 f109 head f83 mainIn #connect
As0 f76 out f125 tail #connect
As0 f125 head f98 in #connect
As0 f83 mainOut f114 tail #connect
As0 f114 head f81 mainIn #connect
As0 f1 mainOut f2 tail #connect
As0 f2 head f76 in #connect
As0 f14 mainOut f13 tail #connect
As0 f13 head f98 in #connect
As0 f98 out f16 tail #connect
As0 f16 head f70 in #connect
As0 f19 mainOut f20 tail #connect
As0 f20 head f91 mainIn #connect
As0 f18 mainOut f12 tail #connect
As0 f12 head f14 mainIn #connect
As0 f27 head f17 mainIn #connect
As0 f17 mainOut f28 tail #connect
As0 f28 head f34 mainIn #connect
As0 f35 head f31 mainIn #connect
As0 f31 mainOut f37 tail #connect
As0 f37 head f34 mainIn #connect
As0 f87 mainOut f3 tail #connect
As0 f3 head f85 mainIn #connect
As0 f0 mainOut f4 tail #connect
As0 f4 head f76 in #connect
As0 f70 out f9 tail #connect
As0 f9 head f8 mainIn #connect
As0 f70 out f27 tail #connect
As0 f70 out f35 tail #connect
As0 f8 mainOut f6 tail #connect
As0 f6 head f34 mainIn #connect
