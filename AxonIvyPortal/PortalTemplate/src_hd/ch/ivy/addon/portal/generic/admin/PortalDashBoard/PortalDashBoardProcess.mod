[Ivy]
[>Created: Thu Apr 14 14:40:36 ICT 2016]
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
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
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
Ps0 f1 589 53 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f9 processCall MultiPortal/TaskService:analyzePriorityStatistic(String) #txt
Ps0 f9 doCall true #txt
Ps0 f9 requestActionDecl '<java.lang.String userName> param;
' #txt
Ps0 f9 requestMappingAction 'param.userName=ivy.session.getSessionUserName();
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
Ps0 f9 286 52 36 24 -41 14 #rect
Ps0 f9 @|CallSubIcon #fIcon
Ps0 f12 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f12 processCall MultiPortal/TaskService:analyzeExpiryStatistic(String) #txt
Ps0 f12 doCall true #txt
Ps0 f12 requestActionDecl '<java.lang.String userName> param;
' #txt
Ps0 f12 requestMappingAction 'param.userName=ivy.session.getSessionUserName();
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
Ps0 f12 286 132 36 24 20 -2 #rect
Ps0 f12 @|CallSubIcon #fIcon
Ps0 f20 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out;
' #txt
Ps0 f20 actionTable 'out=in1;
out.statistic.expiryStatistic=in2.statistic.expiryStatistic;
out.statistic.priorityStatistic=in1.statistic.priorityStatistic;
' #txt
Ps0 f20 386 50 28 28 14 0 #rect
Ps0 f20 @|JoinIcon #fIcon
Ps0 f18 actionDecl 'ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out1;
ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData out2;
' #txt
Ps0 f18 actionTable 'out1=in;
out2=in;
' #txt
Ps0 f18 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f18 194 50 28 28 14 0 #rect
Ps0 f18 @|ThreadIcon #fIcon
Ps0 f11 expr out1 #txt
Ps0 f11 222 64 286 64 #arcP
Ps0 f13 expr out #txt
Ps0 f13 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f13 var in1 #txt
Ps0 f13 322 64 386 64 #arcP
Ps0 f21 expr out2 #txt
Ps0 f21 216 70 290 132 #arcP
Ps0 f21 0 0.48592282486828997 0 0 #arcLabel
Ps0 f22 expr out #txt
Ps0 f22 type ch.ivy.addon.portal.generic.admin.PortalDashBoard.PortalDashBoardData #txt
Ps0 f22 var in2 #txt
Ps0 f22 318 132 392 70 #arcP
Ps0 f22 0 0.5140771751317101 0 0 #arcLabel
Ps0 f2 expr out #txt
Ps0 f2 123 64 194 64 #arcP
Ps0 f3 expr out #txt
Ps0 f3 414 64 589 64 #arcP
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
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f18 in #connect
Ps0 f20 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
