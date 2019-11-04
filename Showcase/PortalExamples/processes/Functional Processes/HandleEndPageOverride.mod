[Ivy]
165801CAE71C2982 7.5.0 #module
>Proto >Proto Collection #zClass
He0 HandleEndPageOverride Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @AnnotationInP-0n ai ai #zField
He0 @MessageFlowInP-0n messageIn messageIn #zField
He0 @MessageFlowOutP-0n messageOut messageOut #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @StartSub f0 '' #zField
He0 @EndSub f1 '' #zField
He0 @GridStep f3 '' #zField
He0 @PushWFArc f4 '' #zField
He0 @PushWFArc f2 '' #zField
He0 @InfoButton f5 '' #zField
>Proto He0 He0 HandleEndPageOverride #zField
He0 f0 inParamDecl '<> param;' #txt
He0 f0 outParamDecl '<String callbackUrl> result;' #txt
He0 f0 outParamTable 'result.callbackUrl=in.callbackUrl;
' #txt
He0 f0 callSignature handleEndPage() #txt
He0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handleEndPage()</name>
    </language>
</elementInfo>
' #txt
He0 f0 81 49 30 30 -48 17 #rect
He0 f0 @|StartSubIcon #fIcon
He0 f1 337 49 30 30 0 15 #rect
He0 f1 @|EndSubIcon #fIcon
He0 f3 actionTable 'out=in;
' #txt
He0 f3 actionCode 'import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector collector = new ProcessStartCollector(ivy.wf.getApplication());
String ourNewEndPageFriendlyRequestPath = "Start Processes/ToCustomizedExpressEndPage/start.ivp";
in.callbackUrl = collector.findLinkByFriendlyRequestPath(ourNewEndPageFriendlyRequestPath);' #txt
He0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set End Page</name>
    </language>
</elementInfo>
' #txt
He0 f3 168 42 112 44 -37 -8 #rect
He0 f3 @|StepIcon #fIcon
He0 f4 expr out #txt
He0 f4 111 64 168 64 #arcP
He0 f2 expr out #txt
He0 f2 280 64 337 64 #arcP
He0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides HandleEndPage process in AxonIvyExpress. Now when user finish the last task of an 
Express workflow, he will be redirected to the customized express end page of this example project.
</name>
        <nameStyle>206,7
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f5 420 28 608 60 -297 -24 #rect
He0 f5 @|IBIcon #fIcon
>Proto He0 .type ch.ivyteam.ivy.project.portal.examples.HandleEndPageData #txt
>Proto He0 .processKind CALLABLE_SUB #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f0 mainOut f4 tail #connect
He0 f4 head f3 mainIn #connect
He0 f3 mainOut f2 tail #connect
He0 f2 head f1 mainIn #connect
