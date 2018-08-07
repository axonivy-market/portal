[Ivy]
15F0A2F67491EB6F 3.20 #module
>Proto >Proto Collection #zClass
Le0 LogoutPage Big #zClass
Le0 B #cInfo
Le0 #process
Le0 @TextInP .resExport .resExport #zField
Le0 @TextInP .type .type #zField
Le0 @TextInP .processKind .processKind #zField
Le0 @AnnotationInP-0n ai ai #zField
Le0 @MessageFlowInP-0n messageIn messageIn #zField
Le0 @MessageFlowOutP-0n messageOut messageOut #zField
Le0 @TextInP .xml .xml #zField
Le0 @TextInP .responsibility .responsibility #zField
Le0 @StartSub f0 '' #zField
Le0 @EndSub f1 '' #zField
Le0 @InfoButton f3 '' #zField
Le0 @GridStep f5 '' #zField
Le0 @PushWFArc f6 '' #zField
Le0 @PushWFArc f2 '' #zField
>Proto Le0 Le0 LogoutPage #zField
Le0 f0 inParamDecl '<> param;' #txt
Le0 f0 outParamDecl '<java.lang.String logoutPage> result;
' #txt
Le0 f0 outParamTable 'result.logoutPage=in.logoutPage;
' #txt
Le0 f0 actionDecl 'internaltest.LogoutPageOverrideData out;
' #txt
Le0 f0 callSignature call() #txt
Le0 f0 type internaltest.LogoutPageOverrideData #txt
Le0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Le0 f0 81 49 30 30 -13 17 #rect
Le0 f0 @|StartSubIcon #fIcon
Le0 f1 type internaltest.LogoutPageOverrideData #txt
Le0 f1 337 49 30 30 0 15 #rect
Le0 f1 @|EndSubIcon #fIcon
Le0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change the value of in.logoutPage 
to customize the logout page</name>
        <nameStyle>63
</nameStyle>
    </language>
</elementInfo>
' #txt
Le0 f3 120 130 208 44 -95 -16 #rect
Le0 f3 @|IBIcon #fIcon
Le0 f5 actionDecl 'internaltest.LogoutPageOverrideData out;
' #txt
Le0 f5 actionTable 'out=in;
' #txt
Le0 f5 actionCode 'in.logoutPage = "https://www.google.com";' #txt
Le0 f5 type internaltest.LogoutPageOverrideData #txt
Le0 f5 168 42 112 44 0 -8 #rect
Le0 f5 @|StepIcon #fIcon
Le0 f6 expr out #txt
Le0 f6 111 64 168 64 #arcP
Le0 f2 expr out #txt
Le0 f2 280 64 337 64 #arcP
>Proto Le0 .type internaltest.LogoutPageOverrideData #txt
>Proto Le0 .processKind CALLABLE_SUB #txt
>Proto Le0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Le0 0 0 32 24 18 0 #rect
>Proto Le0 @|BIcon #fIcon
Le0 f0 mainOut f6 tail #connect
Le0 f6 head f5 mainIn #connect
Le0 f5 mainOut f2 tail #connect
Le0 f2 head f1 mainIn #connect
