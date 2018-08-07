[Ivy]
[>Created: Fri Sep 16 21:37:41 ICT 2016]
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
Ps0 @CallSub f9 '' #zField
Ps0 @CallSub f12 '' #zField
Ps0 @Join f20 '' #zField
Ps0 @Split f18 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @SJArc f13 '' #zField
Ps0 @PushWFArc f21 '' #zField
Ps0 @SJArc f22 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @GridStep f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogInitStart f6 '' #zField
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
Ps0 f0 101 53 22 22 -16 12 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f1 621 53 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f9 processCall MultiPortal/TaskService:analyzePriorityStatistic(List<String>,Long,String) #txt
Ps0 f9 doCall true #txt
Ps0 f9 requestActionDecl '<List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Ps0 f9 requestMappingAction 'param.apps=in.involvedApplications;
param.serverId=in.serverId;
param.userName=ivy.session.getSessionUserName();
' #txt
Ps0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f9 responseMappingAction 'out=in;
out.statistic.priorityStatistic=result.priorityStatistic;
' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Priority Statistic</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 422 52 36 24 -41 14 #rect
Ps0 f9 @|CallSubIcon #fIcon
Ps0 f12 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f12 processCall MultiPortal/TaskService:analyzeExpiryStatistic(List<String>,Long,String) #txt
Ps0 f12 doCall true #txt
Ps0 f12 requestActionDecl '<List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Ps0 f12 requestMappingAction 'param.apps=in.involvedApplications;
param.serverId=in.serverId;
param.userName=ivy.session.getSessionUserName();
' #txt
Ps0 f12 responseActionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f12 responseMappingAction 'out=in;
out.statistic.expiryStatistic=result.expiryStatistic;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Expiry Statistic</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 422 132 36 24 20 -2 #rect
Ps0 f12 @|CallSubIcon #fIcon
Ps0 f20 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f20 actionTable 'out=in1;
out.statistic.expiryStatistic=in2.statistic.expiryStatistic;
out.statistic.priorityStatistic=in1.statistic.priorityStatistic;
' #txt
Ps0 f20 522 50 28 28 14 0 #rect
Ps0 f20 @|JoinIcon #fIcon
Ps0 f18 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out1;
ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out2;
' #txt
Ps0 f18 actionTable 'out1=in;
out2=in;
' #txt
Ps0 f18 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f18 330 50 28 28 14 0 #rect
Ps0 f18 @|ThreadIcon #fIcon
Ps0 f11 expr out1 #txt
Ps0 f11 358 64 422 64 #arcP
Ps0 f13 expr out #txt
Ps0 f13 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f13 var in1 #txt
Ps0 f13 458 64 522 64 #arcP
Ps0 f21 expr out2 #txt
Ps0 f21 344 78 422 144 #arcP
Ps0 f21 1 344 144 #addKink
Ps0 f21 0 0.996371915539765 0 0 #arcLabel
Ps0 f22 expr out #txt
Ps0 f22 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f22 var in2 #txt
Ps0 f22 458 144 536 78 #arcP
Ps0 f22 1 536 144 #addKink
Ps0 f22 0 0.9757858781220886 0 0 #arcLabel
Ps0 f3 expr out #txt
Ps0 f3 550 64 621 64 #arcP
Ps0 f4 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.serverId = SecurityServiceUtils.getServerIdFromSession();
String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.involvedApplications = [applicationName];
}' #txt
Ps0 f4 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get selected apps</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f4 206 52 36 24 -47 19 #rect
Ps0 f4 @|StepIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 123 64 206 64 #arcP
Ps0 f2 expr out #txt
Ps0 f2 242 64 330 64 #arcP
Ps0 f6 guid 157336D81FC73158 #txt
Ps0 f6 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f6 method startWithMenuState(String) #txt
Ps0 f6 disableUIEvents true #txt
Ps0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String menuState> param = methodEvent.getInputArguments();
' #txt
Ps0 f6 inParameterMapAction 'out.menuState=param.menuState;
' #txt
Ps0 f6 outParameterDecl '<> result;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithMenuState(String)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 101 133 22 22 -73 13 #rect
Ps0 f6 @|RichDialogInitStartIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 120 137 207 76 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start method</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f18 out f11 tail #connect
Ps0 f11 head f9 mainIn #connect
Ps0 f9 mainOut f13 tail #connect
Ps0 f13 head f20 in #connect
Ps0 f18 out f21 tail #connect
Ps0 f21 head f12 mainIn #connect
Ps0 f12 mainOut f22 tail #connect
Ps0 f22 head f20 in #connect
Ps0 f20 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f4 mainOut f2 tail #connect
Ps0 f2 head f18 in #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f4 mainIn #connect
