[Ivy]
16B40A95DA8F858E 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 MotorbikeProviderForPortalExpress Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartSub f0 '' #zField
Ds0 @EndSub f1 '' #zField
Ds0 @GridStep f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @InfoButton f5 '' #zField
>Proto Ds0 Ds0 MotorbikeProviderForPortalExpress #zField
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 outParamDecl '<List<String> data> result;' #txt
Ds0 f0 outParamTable 'result.data=in.items;
' #txt
Ds0 f0 callSignature portalExpressDataProvider() #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>portalExpressDataProvider()</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 81 49 30 30 24 -1 #rect
Ds0 f0 @|StartSubIcon #fIcon
Ds0 f1 81 273 30 30 0 15 #rect
Ds0 f1 @|EndSubIcon #fIcon
Ds0 f3 actionTable 'out=in;
out.items=java.util.Arrays.asList("BMW", "Harley Davidson", "Honda", "Agusta", "Triumph");
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load data</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 40 162 112 44 -27 -8 #rect
Ds0 f3 @|StepIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 96 79 96 162 #arcP
Ds0 f2 expr out #txt
Ds0 f2 96 206 96 273 #arcP
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process provide the data for portal express.&#xD;
The callable subprocess has signature "portalExpressDataProvider" and return the list of String.</name>
    </language>
</elementInfo>
' #txt
Ds0 f5 208 170 528 44 -261 -16 #rect
Ds0 f5 @|IBIcon #fIcon
>Proto Ds0 .type ch.ivyteam.ivy.project.portal.examples.DataProviderForPortalExpressData #txt
>Proto Ds0 .processKind CALLABLE_SUB #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 mainIn #connect
Ds0 f3 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
