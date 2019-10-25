[Ivy]
16150F8167BC0EF5 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TestStatisticWidgetPageProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @GridStep f4 '' #zField
Ts0 @GridStep f2 '' #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @CallSub f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f3 '' #zField
>Proto Ts0 Ts0 TestStatisticWidgetPageProcess #zField
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
java.util.List apps = service.findActiveIvyAppsBasedOnConfiguration(ivy.session.getSessionUserName());
in.involvedApplications = apps;' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get selected apps</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f4 162 54 36 24 -47 19 #rect
Ts0 f4 @|StepIcon #fIcon
Ts0 f2 actionTable 'out=in;
' #txt
Ts0 f2 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;


StatisticService service = new StatisticService();
in.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());
' #txt
Ts0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get statistic charts</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f2 453 54 36 24 -49 14 #rect
Ts0 f2 @|StepIcon #fIcon
Ts0 f0 guid 16150F9B6D41C56F #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 57 55 22 22 -16 13 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 569 55 22 22 14 0 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f8 processCall 'Functional Processes/BuildTaskQuery:buildTaskQuery()' #txt
Ts0 f8 requestActionDecl '<> param;' #txt
Ts0 f8 responseActionDecl 'internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData out;
' #txt
Ts0 f8 responseMappingAction 'out=in;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskQuery</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 314 54 36 24 -51 18 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 198 66 314 66 #arcP
Ts0 f5 expr out #txt
Ts0 f5 79 66 162 66 #arcP
Ts0 f9 expr out #txt
Ts0 f9 489 66 569 66 #arcP
Ts0 f3 expr out #txt
Ts0 f3 350 66 453 66 #arcP
>Proto Ts0 .type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f4 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f8 mainOut f3 tail #connect
Ts0 f3 head f2 mainIn #connect
Ts0 f2 mainOut f9 tail #connect
Ts0 f9 head f1 mainIn #connect
