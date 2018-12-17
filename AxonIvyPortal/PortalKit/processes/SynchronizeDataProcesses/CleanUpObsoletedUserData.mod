[Ivy]
1626C02D46BF5153 3.20 #module
>Proto >Proto Collection #zClass
Ca0 CleanUpObsoletedUserData Big #zClass
Ca0 B #cInfo
Ca0 #process
Ca0 @TextInP .resExport .resExport #zField
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
Ca0 @StartRequest f4 '' #zField
Ca0 @PushWFArc f8 '' #zField
>Proto Ca0 Ca0 CleanUpObsoletedUserData #zField
Ca0 f7 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Ca0 f7 actionTable 'out=in;
' #txt
Ca0 f7 actionCode 'import ch.ivy.addon.portalkit.service.CleanUpObsoletedUserDataService;

ivy.log.info("Start to clean up data of obsoleted users");
CleanUpObsoletedUserDataService cleanUpObsoletedUserDataService = new CleanUpObsoletedUserDataService();
cleanUpObsoletedUserDataService.cleanUpData();' #txt
Ca0 f7 type ch.ivy.add.portalkit.Data #txt
Ca0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clean up data</name>
    </language>
</elementInfo>
' #txt
Ca0 f7 102 117 36 24 20 -2 #rect
Ca0 f7 @|StepIcon #fIcon
Ca0 f6 type ch.ivy.add.portalkit.Data #txt
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
Ca0 f2 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Ca0 f2 actionTable 'out=in;
' #txt
Ca0 f2 actionCode 'import ch.ivy.addon.portalkit.service.DeleteZombieAndFinishedHiddenCasesService;
DeleteZombieAndFinishedHiddenCasesService deleteZombieAndFinishedHiddenCasesService = new DeleteZombieAndFinishedHiddenCasesService();
deleteZombieAndFinishedHiddenCasesService.deleteZombieAndFinishedHiddenCases();' #txt
Ca0 f2 security system #txt
Ca0 f2 type ch.ivy.add.portalkit.Data #txt
Ca0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete zombie &amp; finished hidden
system cases</name>
        <nameStyle>44,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f2 16 202 208 44 -84 -16 #rect
Ca0 f2 @|StepIcon #fIcon
Ca0 f3 expr out #txt
Ca0 f3 120 141 120 202 #arcP
Ca0 f1 expr out #txt
Ca0 f1 120 246 120 307 #arcP
Ca0 f4 outLink hidecase.ivp #txt
Ca0 f4 type ch.ivy.add.portalkit.Data #txt
Ca0 f4 inParamDecl '<> param;' #txt
Ca0 f4 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Ca0 f4 guid 167AC316E559FB42 #txt
Ca0 f4 requestEnabled true #txt
Ca0 f4 triggerEnabled false #txt
Ca0 f4 callSignature hidecase() #txt
Ca0 f4 persist false #txt
Ca0 f4 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f4 caseData businessCase.attach=true #txt
Ca0 f4 showInStartList 1 #txt
Ca0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>hidecase.ivp</name>
    </language>
</elementInfo>
' #txt
Ca0 f4 @C|.responsibility Everybody #txt
Ca0 f4 433 225 30 30 -34 17 #rect
Ca0 f4 @|StartRequestIcon #fIcon
Ca0 f8 expr out #txt
Ca0 f8 433 239 224 224 #arcP
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
Ca0 f4 mainOut f8 tail #connect
Ca0 f8 head f2 mainIn #connect
