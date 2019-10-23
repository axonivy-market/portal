[Ivy]
160070C3BA6D3702 7.5.0 #module
>Proto >Proto Collection #zClass
Ce0 HandleEndPage Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @MessageFlowInP-0n messageIn messageIn #zField
Ce0 @MessageFlowOutP-0n messageOut messageOut #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @EndSub f3 '' #zField
Ce0 @GridStep f4 '' #zField
Ce0 @StartSub f5 '' #zField
Ce0 @PushWFArc f6 '' #zField
Ce0 @PushWFArc f7 '' #zField
Ce0 @InfoButton f0 '' #zField
>Proto Ce0 Ce0 HandleEndPage #zField
Ce0 f3 378 233 30 30 0 15 #rect
Ce0 f3 @|EndSubIcon #fIcon
Ce0 f4 actionTable 'out=in;
' #txt
Ce0 f4 actionCode 'in.callbackUrl = ivy.html.startref("1600BDF7CC0AE52F/start.ivp");

' #txt
Ce0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get end page url</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f4 209 226 112 44 -44 -8 #rect
Ce0 f4 @|StepIcon #fIcon
Ce0 f5 inParamDecl '<> param;' #txt
Ce0 f5 outParamDecl '<String callbackUrl> result;' #txt
Ce0 f5 outParamTable 'result.callbackUrl=in.callbackUrl;
' #txt
Ce0 f5 callSignature handleEndPage() #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handleEndPage()</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f5 74 233 30 30 -48 17 #rect
Ce0 f5 @|StartSubIcon #fIcon
Ce0 f6 expr out #txt
Ce0 f6 104 248 209 248 #arcP
Ce0 f7 expr out #txt
Ce0 f7 321 248 378 248 #arcP
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO CREATE CUSTOM END PAGE FOR EXPRESS PROCESS

import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector collector = new ProcessStartCollector(ivy.wf.getApplication());
String ourNewEndPageFriendlyRequestPath = "Start Processes/NewEndPageOfExpress/startCustomEndPage.ivp";
in.callbackUrl = collector.findLinkByFriendlyRequestPath(ourNewEndPageFriendlyRequestPath);

OUT: callbackUrl : String</name>
        <nameStyle>421
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f0 24 18 640 156 -316 -72 #rect
Ce0 f0 @|IBIcon #fIcon
>Proto Ce0 .type ch.ivy.addon.express.generic.HandleEndPageData #txt
>Proto Ce0 .processKind CALLABLE_SUB #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f5 mainOut f6 tail #connect
Ce0 f6 head f4 mainIn #connect
Ce0 f4 mainOut f7 tail #connect
Ce0 f7 head f3 mainIn #connect
