[Ivy]
14EB4D799BBF04C8 3.20 #module
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
Ps0 @GridStep f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogInitStart f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @PushWFArc f9 '' #zField
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
Ps0 f1 565 53 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
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
Ps0 f8 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f8 processCall 'Functional Processes/BuildTaskJsonQuery:buildTaskJsonQuery()' #txt
Ps0 f8 doCall true #txt
Ps0 f8 requestActionDecl '<> param;
' #txt
Ps0 f8 responseActionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f8 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskJsonQuery</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 310 52 36 24 -51 18 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 242 64 310 64 #arcP
Ps0 f2 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
in.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());' #txt
Ps0 f2 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get statistic charts</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 449 52 36 24 -49 14 #rect
Ps0 f2 @|StepIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 346 64 449 64 #arcP
Ps0 f9 expr out #txt
Ps0 f9 485 64 565 64 #arcP
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
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f4 mainIn #connect
Ps0 f4 mainOut f10 tail #connect
Ps0 f10 head f8 mainIn #connect
Ps0 f8 mainOut f3 tail #connect
Ps0 f3 head f2 mainIn #connect
Ps0 f2 mainOut f9 tail #connect
Ps0 f9 head f1 mainIn #connect
