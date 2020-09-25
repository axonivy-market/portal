[Ivy]
171061A88CF2D492 9.2.0 #module
>Proto >Proto Collection #zClass
Bs0 PortalBreadcrumbProcess Big #zClass
Bs0 RD #cInfo
Bs0 #process
Bs0 @TextInP .type .type #zField
Bs0 @TextInP .processKind .processKind #zField
Bs0 @TextInP .xml .xml #zField
Bs0 @TextInP .responsibility .responsibility #zField
Bs0 @UdInit f0 '' #zField
Bs0 @UdProcessEnd f1 '' #zField
Bs0 @PushWFArc f2 '' #zField
Bs0 @UdEvent f3 '' #zField
Bs0 @UdExitEnd f4 '' #zField
Bs0 @PushWFArc f5 '' #zField
Bs0 @UdMethod f28 '' #zField
Bs0 @GridStep f29 '' #zField
Bs0 @PushWFArc f30 '' #zField
Bs0 @UdProcessEnd f31 '' #zField
Bs0 @PushWFArc f32 '' #zField
Bs0 @UdMethod f33 '' #zField
Bs0 @Alternative f34 '' #zField
Bs0 @PushWFArc f35 '' #zField
Bs0 @GridStep f36 '' #zField
Bs0 @GridStep f37 '' #zField
Bs0 @PushWFArc f38 '' #zField
Bs0 @UdProcessEnd f39 '' #zField
Bs0 @PushWFArc f40 '' #zField
Bs0 @PushWFArc f41 '' #zField
Bs0 @PushWFArc f42 '' #zField
Bs0 @Alternative f6 '' #zField
Bs0 @GridStep f7 '' #zField
Bs0 @UdProcessEnd f8 '' #zField
Bs0 @GridStep f9 '' #zField
Bs0 @UdMethod f10 '' #zField
Bs0 @PushWFArc f11 '' #zField
Bs0 @PushWFArc f17 '' #zField
Bs0 @PushWFArc f21 '' #zField
Bs0 @PushWFArc f22 '' #zField
Bs0 @PushWFArc f23 '' #zField
>Proto Bs0 Bs0 PortalBreadcrumbProcess #zField
Bs0 f0 guid 171061A8BDE2A71D #txt
Bs0 f0 method start() #txt
Bs0 f0 inParameterDecl '<> param;' #txt
Bs0 f0 outParameterDecl '<> result;' #txt
Bs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Bs0 f0 83 51 26 26 -16 15 #rect
Bs0 f0 @|UdInitIcon #fIcon
Bs0 f1 211 51 26 26 0 12 #rect
Bs0 f1 @|UdProcessEndIcon #fIcon
Bs0 f2 109 64 211 64 #arcP
Bs0 f3 guid 171061A8C498ABA1 #txt
Bs0 f3 actionTable 'out=in;
' #txt
Bs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Bs0 f3 307 51 26 26 -15 15 #rect
Bs0 f3 @|UdEventIcon #fIcon
Bs0 f4 435 51 26 26 0 12 #rect
Bs0 f4 @|UdExitEndIcon #fIcon
Bs0 f5 333 64 435 64 #arcP
Bs0 f28 guid 1715770AAA949799 #txt
Bs0 f28 method navigateToCaseList() #txt
Bs0 f28 inParameterDecl '<> param;' #txt
Bs0 f28 outParameterDecl '<> result;' #txt
Bs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToCaseList()</name>
    </language>
</elementInfo>
' #txt
Bs0 f28 83 147 26 26 -46 14 #rect
Bs0 f28 @|UdMethodIcon #fIcon
Bs0 f29 actionTable 'out=in;
' #txt
Bs0 f29 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalCase();
' #txt
Bs0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate</name>
    </language>
</elementInfo>
' #txt
Bs0 f29 224 138 112 44 -24 -8 #rect
Bs0 f29 @|StepIcon #fIcon
Bs0 f30 109 160 224 160 #arcP
Bs0 f31 499 147 26 26 0 12 #rect
Bs0 f31 @|UdProcessEndIcon #fIcon
Bs0 f32 336 160 499 160 #arcP
Bs0 f33 guid 1715774BBEBA4FD9 #txt
Bs0 f33 method navigateToTaskList(Boolean) #txt
Bs0 f33 inParameterDecl '<Boolean isWorkingOnATask> param;' #txt
Bs0 f33 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask;
' #txt
Bs0 f33 outParameterDecl '<> result;' #txt
Bs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToTaskList(Boolean)</name>
    </language>
</elementInfo>
' #txt
Bs0 f33 83 243 26 26 -50 15 #rect
Bs0 f33 @|UdMethodIcon #fIcon
Bs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is working on a task?</name>
    </language>
</elementInfo>
' #txt
Bs0 f34 208 240 32 32 -53 -32 #rect
Bs0 f34 @|AlternativeIcon #fIcon
Bs0 f35 109 256 208 256 #arcP
Bs0 f36 actionTable 'out=in;
' #txt
Bs0 f36 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalTask();' #txt
Bs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate</name>
    </language>
</elementInfo>
' #txt
Bs0 f36 320 314 112 44 -24 -8 #rect
Bs0 f36 @|StepIcon #fIcon
Bs0 f37 actionTable 'out=in;
' #txt
Bs0 f37 actionCode 'import ch.ivy.addon.portalkit.util.PrimeFacesUtils;

PrimeFacesUtils.executeScript("navigateToTaskListFromBreadcrumb();");' #txt
Bs0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show confirm dialog</name>
    </language>
</elementInfo>
' #txt
Bs0 f37 312 234 128 44 -56 -8 #rect
Bs0 f37 @|StepIcon #fIcon
Bs0 f38 expr in #txt
Bs0 f38 outCond in.isWorkingOnATask #txt
Bs0 f38 240 256 312 256 #arcP
Bs0 f39 499 243 26 26 0 12 #rect
Bs0 f39 @|UdProcessEndIcon #fIcon
Bs0 f40 440 256 499 256 #arcP
Bs0 f41 expr in #txt
Bs0 f41 224 272 320 336 #arcP
Bs0 f41 1 224 336 #addKink
Bs0 f41 1 0.1 0 0 #arcLabel
Bs0 f42 432 336 512 269 #arcP
Bs0 f42 1 512 336 #addKink
Bs0 f42 0 0.7636194805484646 0 0 #arcLabel
Bs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is working on a task?</name>
    </language>
</elementInfo>
' #txt
Bs0 f6 208 400 32 32 -53 -32 #rect
Bs0 f6 @|AlternativeIcon #fIcon
Bs0 f7 actionTable 'out=in;
' #txt
Bs0 f7 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalHome();' #txt
Bs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate</name>
    </language>
</elementInfo>
' #txt
Bs0 f7 320 474 112 44 -24 -8 #rect
Bs0 f7 @|StepIcon #fIcon
Bs0 f8 499 403 26 26 0 12 #rect
Bs0 f8 @|UdProcessEndIcon #fIcon
Bs0 f9 actionTable 'out=in;
' #txt
Bs0 f9 actionCode 'import ch.ivy.addon.portalkit.util.PrimeFacesUtils;

PrimeFacesUtils.executeScript("navigateToHomeFromBreadcrumb();");' #txt
Bs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show confirm dialog</name>
    </language>
</elementInfo>
' #txt
Bs0 f9 312 394 128 44 -56 -8 #rect
Bs0 f9 @|StepIcon #fIcon
Bs0 f10 guid 171577D0D294355D #txt
Bs0 f10 method navigateToPortalHome(Boolean) #txt
Bs0 f10 inParameterDecl '<Boolean isWorkingOnATask> param;' #txt
Bs0 f10 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask;
' #txt
Bs0 f10 outParameterDecl '<> result;' #txt
Bs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToPortalHome(Boolean)</name>
    </language>
</elementInfo>
' #txt
Bs0 f10 83 403 26 26 -50 15 #rect
Bs0 f10 @|UdMethodIcon #fIcon
Bs0 f11 440 416 499 416 #arcP
Bs0 f17 432 496 512 429 #arcP
Bs0 f17 1 512 496 #addKink
Bs0 f17 0 0.7636194805484646 0 0 #arcLabel
Bs0 f21 expr in #txt
Bs0 f21 outCond in.isWorkingOnATask #txt
Bs0 f21 240 416 312 416 #arcP
Bs0 f22 109 416 208 416 #arcP
Bs0 f23 expr in #txt
Bs0 f23 224 432 320 496 #arcP
Bs0 f23 1 224 496 #addKink
Bs0 f23 1 0.1 0 0 #arcLabel
>Proto Bs0 .type ch.ivy.addon.portal.generic.PortalBreadcrumb.PortalBreadcrumbData #txt
>Proto Bs0 .processKind HTML_DIALOG #txt
>Proto Bs0 -8 -8 16 16 16 26 #rect
>Proto Bs0 '' #fIcon
Bs0 f0 mainOut f2 tail #connect
Bs0 f2 head f1 mainIn #connect
Bs0 f3 mainOut f5 tail #connect
Bs0 f5 head f4 mainIn #connect
Bs0 f28 mainOut f30 tail #connect
Bs0 f30 head f29 mainIn #connect
Bs0 f29 mainOut f32 tail #connect
Bs0 f32 head f31 mainIn #connect
Bs0 f33 mainOut f35 tail #connect
Bs0 f35 head f34 in #connect
Bs0 f34 out f38 tail #connect
Bs0 f38 head f37 mainIn #connect
Bs0 f37 mainOut f40 tail #connect
Bs0 f40 head f39 mainIn #connect
Bs0 f34 out f41 tail #connect
Bs0 f41 head f36 mainIn #connect
Bs0 f36 mainOut f42 tail #connect
Bs0 f42 head f39 mainIn #connect
Bs0 f10 mainOut f22 tail #connect
Bs0 f22 head f6 in #connect
Bs0 f6 out f21 tail #connect
Bs0 f21 head f9 mainIn #connect
Bs0 f9 mainOut f11 tail #connect
Bs0 f11 head f8 mainIn #connect
Bs0 f6 out f23 tail #connect
Bs0 f23 head f7 mainIn #connect
Bs0 f7 mainOut f17 tail #connect
Bs0 f17 head f8 mainIn #connect
