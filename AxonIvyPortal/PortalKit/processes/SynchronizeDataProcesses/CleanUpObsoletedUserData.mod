[Ivy]
1626C02D46BF5153 7.5.0 #module
>Proto >Proto Collection #zClass
Ca0 CleanUpObsoletedUserData Big #zClass
Ca0 B #cInfo
Ca0 #process
Ca0 @TextInP .type .type #zField
Ca0 @TextInP .processKind .processKind #zField
Ca0 @AnnotationInP-0n ai ai #zField
Ca0 @MessageFlowInP-0n messageIn messageIn #zField
Ca0 @MessageFlowOutP-0n messageOut messageOut #zField
Ca0 @TextInP .xml .xml #zField
Ca0 @TextInP .responsibility .responsibility #zField
Ca0 @GridStep f7 '' #zField
Ca0 @EndTask f6 '' #zField
Ca0 @StartEvent f5 '' #zField
Ca0 @PushWFArc f0 '' #zField
Ca0 @GridStep f2 '' #zField
Ca0 @PushWFArc f3 '' #zField
Ca0 @PushWFArc f1 '' #zField
>Proto Ca0 Ca0 CleanUpObsoletedUserData #zField
Ca0 f7 actionTable 'out=in;
' #txt
Ca0 f7 actionCode 'import ch.ivy.addon.portalkit.service.CleanUpObsoletedUserDataService;

ivy.log.info("Start to clean up data of obsoleted users");
CleanUpObsoletedUserDataService cleanUpObsoletedUserDataService = new CleanUpObsoletedUserDataService();
cleanUpObsoletedUserDataService.cleanUpData();' #txt
Ca0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clean up data</name>
    </language>
</elementInfo>
' #txt
Ca0 f7 102 117 36 24 20 -2 #rect
Ca0 f7 @|StepIcon #fIcon
Ca0 f6 107 307 26 26 14 0 #rect
Ca0 f6 @|EndIcon #fIcon
Ca0 f5 outerBean "ch.ivy.addon.portalkit.util.CronByGlobalVariableTriggerStartEventBean" #txt
Ca0 f5 beanConfig "PortalStartTimeCleanObsoletedDataExpression" #txt
Ca0 f5 outLink eventLink.ivp #txt
Ca0 f5 type ch.ivy.add.portalkit.Data #txt
Ca0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>eventStarterbyQuatzScheduler</name>
        <nameStyle>28,7
</nameStyle>
        <desc>This is a scheduled process of Portal</desc>
    </language>
</elementInfo>
' #txt
Ca0 f5 @C|.responsibility Everybody #txt
Ca0 f5 107 36 26 26 14 0 #rect
Ca0 f5 @|StartEventIcon #fIcon
Ca0 f0 expr out #txt
Ca0 f0 120 62 120 117 #arcP
Ca0 f2 actionTable 'out=in;
' #txt
Ca0 f2 actionCode 'import ch.ivy.addon.portalkit.service.DeleteFinishedHiddenCasesService;
DeleteFinishedHiddenCasesService deletFinishedHiddenCasesService = new DeleteFinishedHiddenCasesService();
deletFinishedHiddenCasesService.deleteFinishedHiddenCases();' #txt
Ca0 f2 security system #txt
Ca0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete finished hidden&#xD;
system cases</name>
    </language>
</elementInfo>
' #txt
Ca0 f2 48 202 144 44 -54 -16 #rect
Ca0 f2 @|StepIcon #fIcon
Ca0 f3 expr out #txt
Ca0 f3 120 141 120 202 #arcP
Ca0 f1 expr out #txt
Ca0 f1 120 246 120 307 #arcP
>Proto Ca0 .type ch.ivy.add.portalkit.Data #txt
>Proto Ca0 .processKind NORMAL #txt
>Proto Ca0 0 0 32 24 18 0 #rect
>Proto Ca0 @|BIcon #fIcon
Ca0 f5 mainOut f0 tail #connect
Ca0 f0 head f7 mainIn #connect
Ca0 f7 mainOut f3 tail #connect
Ca0 f3 head f2 mainIn #connect
Ca0 f2 mainOut f1 tail #connect
Ca0 f1 head f6 mainIn #connect
