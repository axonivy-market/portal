[Ivy]
162B3F441F244BD2 3.23 #module
>Proto >Proto Collection #zClass
Ta0 TestCleanUpObsoletedUserData Big #zClass
Ta0 B #cInfo
Ta0 #process
Ta0 @TextInP .resExport .resExport #zField
Ta0 @TextInP .type .type #zField
Ta0 @TextInP .processKind .processKind #zField
Ta0 @AnnotationInP-0n ai ai #zField
Ta0 @MessageFlowInP-0n messageIn messageIn #zField
Ta0 @MessageFlowOutP-0n messageOut messageOut #zField
Ta0 @TextInP .xml .xml #zField
Ta0 @TextInP .responsibility .responsibility #zField
Ta0 @GridStep f3 '' #zField
Ta0 @EndTask f1 '' #zField
Ta0 @StartRequest f0 '' #zField
Ta0 @PushWFArc f4 '' #zField
Ta0 @PushWFArc f2 '' #zField
>Proto Ta0 Ta0 TestCleanUpObsoletedUserData #zField
Ta0 f3 actionDecl 'internaltest.Data out;
' #txt
Ta0 f3 actionTable 'out=in;
' #txt
Ta0 f3 actionCode 'import ch.ivy.addon.portalkit.service.CleanUpObsoletedUserDataService;

ivy.log.info("Start to clean up data of obsoleted users");
CleanUpObsoletedUserDataService cleanUpObsoletedUserDataService = new CleanUpObsoletedUserDataService();
cleanUpObsoletedUserDataService.cleanUpData();' #txt
Ta0 f3 type internaltest.Data #txt
Ta0 f3 152 42 112 44 0 -8 #rect
Ta0 f3 @|StepIcon #fIcon
Ta0 f1 type internaltest.Data #txt
Ta0 f1 321 49 30 30 0 15 #rect
Ta0 f1 @|EndIcon #fIcon
Ta0 f0 outLink start.ivp #txt
Ta0 f0 type ch.ivy.add.portalkit.Data #txt
Ta0 f0 inParamDecl '<> param;' #txt
Ta0 f0 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Ta0 f0 guid 162B3F470CEFC4A5 #txt
Ta0 f0 requestEnabled true #txt
Ta0 f0 triggerEnabled false #txt
Ta0 f0 callSignature start() #txt
Ta0 f0 caseData businessCase.attach=true #txt
Ta0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ta0 f0 @C|.responsibility Everybody #txt
Ta0 f0 65 49 30 30 -21 17 #rect
Ta0 f0 @|StartRequestIcon #fIcon
Ta0 f4 expr out #txt
Ta0 f4 95 64 152 64 #arcP
Ta0 f2 expr out #txt
Ta0 f2 264 64 321 64 #arcP
>Proto Ta0 .type internaltest.Data #txt
>Proto Ta0 .processKind NORMAL #txt
>Proto Ta0 0 0 32 24 18 0 #rect
>Proto Ta0 @|BIcon #fIcon
Ta0 f0 mainOut f4 tail #connect
Ta0 f4 head f3 mainIn #connect
Ta0 f3 mainOut f2 tail #connect
Ta0 f2 head f1 mainIn #connect
