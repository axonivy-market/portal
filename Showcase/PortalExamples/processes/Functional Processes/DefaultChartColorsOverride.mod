[Ivy]
16DB01132AFEAB36 3.28 #module
>Proto >Proto Collection #zClass
Ds0 DefaultChartColors Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartSub f0 '' #zField
Ds0 @EndSub f1 '' #zField
Ds0 @GridStep f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @InfoButton f5 '' #zField
>Proto Ds0 Ds0 DefaultChartColors #zField
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 outParamDecl '<ch.ivy.addon.portalkit.statistics.StatisticColors chartColors> result;' #txt
Ds0 f0 outParamTable 'result.chartColors=in.chartColors;
' #txt
Ds0 f0 callSignature defaultChartColors() #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>defaultChartColors()</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 81 49 30 30 -13 17 #rect
Ds0 f0 @|StartSubIcon #fIcon
Ds0 f1 81 313 30 30 0 15 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticColors;

StatisticColors chartColor = new StatisticColors();
chartColor.taskExceptionPriority = "rgb(255, 99, 132)";
chartColor.taskHighPriority = "rgb(54, 162, 235)";
chartColor.taskNormalPriority = "rgb(255, 205, 86)";
chartColor.taskLowPriority = StatisticColors.DEFAULT_LOW_PRIORITY_COLOR;

chartColor.createdCase = StatisticColors.DEFAULT_CREATED_CASE_COLOR;
chartColor.runningCase = StatisticColors.DEFAULT_RUNNING_CASE_COLOR;
chartColor.doneCase = StatisticColors.DEFAULT_DONE_CASE_COLOR;
chartColor.failedCase = StatisticColors.DEFAULT_FAILED_CASE_COLOR;

in.chartColors = chartColor;' #txt
Ds0 f3 security system #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Default chart colors</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 40 170 112 44 -53 -8 #rect
Ds0 f3 @|StepIcon #fIcon
Ds0 f4 96 79 96 170 #arcP
Ds0 f2 96 214 96 313 #arcP
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>// FOLLOW THIS INSTRUCTION TO OVERRIDE DEFAULT CHART COLORS&#13;
// ALL THE COLORS FOR CHART - PLEASE USE THE RGB COLORS FORMAT&#13;
&#13;
import ch.ivy.addon.portalkit.statistics.StatisticColors;&#13;
&#13;
StatisticColors chartColor = new StatisticColors();&#13;
chartColor.taskExceptionPriority = "rgb(212, 212, 212)";&#13;
chartColor.taskHighPriority = "rgb(255, 90, 90)";&#13;
chartColor.taskNormalPriority = "rgb(255, 192, 192)";&#13;
chartColor.taskLowPriority = "rgb(150, 220, 130)";&#13;
&#13;
chartColor.createdCase = "rgb(255, 99, 132)";&#13;
chartColor.runningCase = "rgb(54, 162, 235)";&#13;
chartColor.doneCase = "rgb(255, 99, 132)";&#13;
chartColor.failedCase = "rgb(212, 212, 212)";&#13;
&#13;
in.chartColors = chartColor;</name>
        <desc>// FOLLOW THIS INSTRUCTION TO OVERRIDE DEFAULT CHART COLORS&#13;
// All colors please use the RGB Colors format&#13;
&#13;
import ch.ivy.addon.portalkit.statistics.StatisticColors;&#13;
&#13;
StatisticColors chartColor = new StatisticColors();&#13;
chartColor.taskExceptionPriority = "rgb(212, 212, 212)";&#13;
chartColor.taskHighPriority = "rgb(255, 90, 90)";&#13;
chartColor.taskNormalPriority = "rgb(255, 192, 192)";&#13;
chartColor.taskLowPriority = "rgb(150, 220, 130)";&#13;
&#13;
chartColor.createdCase = "rgb(255, 99, 132)";&#13;
chartColor.runningCase = "rgb(54, 162, 235)";&#13;
chartColor.doneCase = "rgb(255, 99, 132)";&#13;
chartColor.failedCase = "rgb(212, 212, 212)";&#13;
&#13;
in.chartColors = chartColor;</desc>
    </language>
</elementInfo>
' #txt
Ds0 f5 200 20 496 328 -213 -136 #rect
Ds0 f5 @|IBIcon #fIcon
>Proto Ds0 .type _ch.ivyteam.ivy.project.portal.examples.DefaultChartColorsOverrideData #txt
>Proto Ds0 .processKind CALLABLE_SUB #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 mainIn #connect
Ds0 f3 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
