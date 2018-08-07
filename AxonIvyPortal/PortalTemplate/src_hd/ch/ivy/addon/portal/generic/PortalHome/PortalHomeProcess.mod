[Ivy]
[>Created: Tue Feb 23 09:19:39 ICT 2016]
14BEF201D2E3FF7D 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalHomeProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @RichDialogProcessEnd f10 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @RichDialogMethodStart f2 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @RichDialog f16 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f1 451 131 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f6 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f6 processCall MultiPortal/TaskService:findActiveTasks(String) #txt
Ps0 f6 doCall true #txt
Ps0 f6 requestActionDecl '<java.lang.String user> param;
' #txt
Ps0 f6 requestMappingAction 'param.user=ivy.session.getSessionUserName();
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.PortalHome.PortalHomeData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 222 132 36 24 -36 16 #rect
Ps0 f6 @|CallSubIcon #fIcon
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalHome.PortalHomeData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'import ch.ivy.addon.portalkit.comparator.TaskPriorityComparator;

in.tasks.sort(new TaskPriorityComparator());' #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort by priority as default</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 324 132 40 24 -50 14 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 258 144 324 144 #arcP
Ps0 f7 expr out #txt
Ps0 f7 364 144 451 144 #arcP
Ps0 f10 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f10 197 53 22 22 14 0 #rect
Ps0 f10 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 107 144 222 144 #arcP
Ps0 f2 guid 150EB4F9988FC5CA #txt
Ps0 f2 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f2 method getTasksOfSessionUser() #txt
Ps0 f2 disableUIEvents false #txt
Ps0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f2 outParameterDecl '<List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks> result;
' #txt
Ps0 f2 outParameterMapAction 'result.tasks=in.tasks;
' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getTasksOfSessionUser()</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 85 133 22 22 -51 15 #rect
Ps0 f2 @|RichDialogMethodStartIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 109 64 197 64 #arcP
Ps0 f11 0 0.49999999999999994 0 0 #arcLabel
Ps0 f16 targetWindow NEW:card: #txt
Ps0 f16 targetDisplay TOP #txt
Ps0 f16 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f16 startMethod start() #txt
Ps0 f16 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f16 requestActionDecl '<> param;' #txt
Ps0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.PortalHome.PortalHomeData out;
' #txt
Ps0 f16 responseMappingAction 'out=in;
' #txt
Ps0 f16 windowConfiguration '* ' #txt
Ps0 f16 isAsynch false #txt
Ps0 f16 isInnerRd true #txt
Ps0 f16 userContext '* ' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>process dialog</name>
        <nameStyle>14
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 264 300 112 40 -42 -11 #rect
Ps0 f16 @|RichDialogIcon #fIcon
Ps0 f3 guid 15239B1BE747328A #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portal.generic.PortalHome.PortalHomeData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToProcessDialog</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 69 309 22 22 -47 15 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 91 320 264 320 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f6 mainOut f8 tail #connect
Ps0 f8 head f12 mainIn #connect
Ps0 f12 mainOut f7 tail #connect
Ps0 f7 head f1 mainIn #connect
Ps0 f2 mainOut f9 tail #connect
Ps0 f9 head f6 mainIn #connect
Ps0 f0 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f3 mainOut f4 tail #connect
Ps0 f4 head f16 mainIn #connect
