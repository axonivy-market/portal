[Ivy]
16150F8167BC0EF5 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TestStatisticWidgetPageProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @GridStep f4 '' #zField
Ts0 @GridStep f2 '' #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @CallSub f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f3 '' #zField
>Proto Ts0 Ts0 TestStatisticWidgetPageProcess #zField
Ts0 f4 actionDecl 'internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData out;
' #txt
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.serverId = SecurityServiceUtils.getServerIdFromSession();
String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.involvedApplications = [applicationName];
}' #txt
Ts0 f4 type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
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
Ts0 f2 actionDecl 'internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData out;
' #txt
Ts0 f2 actionTable 'out=in;
' #txt
Ts0 f2 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;


StatisticService service = new StatisticService();
in.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());
' #txt
Ts0 f2 type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
Ts0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get statistic charts</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f2 405 54 36 24 -49 14 #rect
Ts0 f2 @|StepIcon #fIcon
Ts0 f0 guid 16150F9B6D41C56F #txt
Ts0 f0 type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 57 55 22 22 -16 13 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
Ts0 f1 521 55 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f8 type internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData #txt
Ts0 f8 processCall 'Functional Processes/BuildTaskJsonQuery:buildTaskJsonQuery()' #txt
Ts0 f8 doCall true #txt
Ts0 f8 requestActionDecl '<> param;
' #txt
Ts0 f8 responseActionDecl 'internalPortal.TestStatisticWidgetPage.TestStatisticWidgetPageData out;
' #txt
Ts0 f8 responseMappingAction 'out=in;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskJsonQuery</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 266 54 36 24 -51 18 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 198 66 266 66 #arcP
Ts0 f5 expr out #txt
Ts0 f5 79 66 162 66 #arcP
Ts0 f9 expr out #txt
Ts0 f9 441 66 521 66 #arcP
Ts0 f3 expr out #txt
Ts0 f3 302 66 405 66 #arcP
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
