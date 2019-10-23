[Ivy]
15FA059633297DF9 7.5.0 #module
>Proto >Proto Collection #zClass
Il0 InitializeCaseDataModel Big #zClass
Il0 B #cInfo
Il0 #process
Il0 @TextInP .type .type #zField
Il0 @TextInP .processKind .processKind #zField
Il0 @AnnotationInP-0n ai ai #zField
Il0 @MessageFlowInP-0n messageIn messageIn #zField
Il0 @MessageFlowOutP-0n messageOut messageOut #zField
Il0 @TextInP .xml .xml #zField
Il0 @TextInP .responsibility .responsibility #zField
Il0 @StartSub f0 '' #zField
Il0 @EndSub f1 '' #zField
Il0 @GridStep f3 '' #zField
Il0 @PushWFArc f4 '' #zField
Il0 @PushWFArc f2 '' #zField
>Proto Il0 Il0 InitializeCaseDataModel #zField
Il0 f0 inParamDecl '<> param;' #txt
Il0 f0 outParamDecl '<ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel caseDataModel> result;' #txt
Il0 f0 outParamTable 'result.caseDataModel=in.dataModel;
' #txt
Il0 f0 callSignature call() #txt
Il0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f0 81 49 30 30 -13 17 #rect
Il0 f0 @|StartSubIcon #fIcon
Il0 f1 337 49 30 30 0 15 #rect
Il0 f1 @|EndSubIcon #fIcon
Il0 f3 actionTable 'out=in;
' #txt
Il0 f3 actionCode 'import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
in.dataModel = new CaseLazyDataModel();' #txt
Il0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize case data model</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f3 152 42 144 44 -69 -8 #rect
Il0 f3 @|StepIcon #fIcon
Il0 f4 expr out #txt
Il0 f4 111 64 152 64 #arcP
Il0 f2 expr out #txt
Il0 f2 296 64 337 64 #arcP
>Proto Il0 .type ch.ivy.addon.portal.generic.InitializeCaseDataModel #txt
>Proto Il0 .processKind CALLABLE_SUB #txt
>Proto Il0 0 0 32 24 18 0 #rect
>Proto Il0 @|BIcon #fIcon
Il0 f0 mainOut f4 tail #connect
Il0 f4 head f3 mainIn #connect
Il0 f3 mainOut f2 tail #connect
Il0 f2 head f1 mainIn #connect
