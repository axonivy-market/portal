[Ivy]
16B3F9C285F4279D 7.5.0 #module
>Proto >Proto Collection #zClass
Ss0 TestDataProviderForPortalExpress Big #zClass
Ss0 B #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @StartSub f0 '' #zField
Ss0 @EndSub f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
>Proto Ss0 Ss0 TestDataProviderForPortalExpress #zField
Ss0 f0 inParamDecl '<> param;' #txt
Ss0 f0 outParamDecl '<List<String> data> result;' #txt
Ss0 f0 outParamTable 'result.data=java.util.Arrays.asList("Data Provider Item 1", "Data Provider Item 2", "Data Provider Item 3");
' #txt
Ss0 f0 callSignature portalExpressDataProvider() #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>portalExpressDataProvider()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 81 49 30 30 -73 27 #rect
Ss0 f0 @|StartSubIcon #fIcon
Ss0 f1 337 49 30 30 0 15 #rect
Ss0 f1 @|EndSubIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 111 64 337 64 #arcP
>Proto Ss0 .type portalKit_test.TestDataProviderForPortalExpressData #txt
>Proto Ss0 .processKind CALLABLE_SUB #txt
>Proto Ss0 0 0 32 24 18 0 #rect
>Proto Ss0 @|BIcon #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
