[Ivy]
14EB4D799BBF04C8 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalDashBoardProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 PortalDashBoardProcess #zField
Ps0 f0 guid 14EB4D799DA15883 #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
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
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 309 53 22 22 14 0 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
in.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get statistic charts</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 209 52 36 24 -49 14 #rect
Ps0 f2 @|StepIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 245 64 309 64 #arcP
Ps0 f7 expr out #txt
Ps0 f7 123 64 209 64 #arcP
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
Ps0 f2 mainOut f9 tail #connect
Ps0 f9 head f1 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f2 mainIn #connect
