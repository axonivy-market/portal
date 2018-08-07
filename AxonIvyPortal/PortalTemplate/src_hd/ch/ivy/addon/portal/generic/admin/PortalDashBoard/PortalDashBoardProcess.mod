[Ivy]
[>Created: Mon Jan 18 11:56:33 ICT 2016]
14EB4D799BBF04C8 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalDashBoardProcess Big #zClass
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
Ps0 @PushWFArc f3 '' #zField
Ps0 @RichDialogMethodStart f2 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 PortalDashBoardProcess #zField
Ps0 f0 guid 14EB4D799DA15883 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
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
Ps0 f0 53 85 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f1 53 213 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 64 107 64 213 #arcP
Ps0 f2 guid 15253162DC81C4E5 #txt
Ps0 f2 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
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
Ps0 f2 437 85 22 22 14 0 #rect
Ps0 f2 @|RichDialogMethodStartIcon #fIcon
Ps0 f6 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f6 processCall MultiPortal/TaskService:findActiveTasks(String) #txt
Ps0 f6 doCall true #txt
Ps0 f6 requestActionDecl '<java.lang.String user> param;
' #txt
Ps0 f6 requestMappingAction 'param.user=ivy.session.getSessionUserName();
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 430 148 36 24 -33 14 #rect
Ps0 f6 @|CallSubIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f4 437 213 22 22 14 0 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 448 107 448 148 #arcP
Ps0 f7 expr out #txt
Ps0 f7 448 172 448 213 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start method</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>304</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f2 mainOut f5 tail #connect
Ps0 f5 head f6 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f4 mainIn #connect
