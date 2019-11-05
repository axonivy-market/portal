[Ivy]
1657EA461CE412A1 7.5.0 #module
>Proto >Proto Collection #zClass
Le0 LogoutPage Big #zClass
Le0 B #cInfo
Le0 #process
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
Le0 f0 outParamDecl '<String logoutPage> result;' #txt
Le0 f0 outParamTable 'result.logoutPage=in.logoutPage;
' #txt
Le0 f0 callSignature getLogoutPage() #txt
Le0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLogoutPage()</name>
    </language>
</elementInfo>
' #txt
Le0 f0 81 49 30 30 -46 17 #rect
Le0 f0 @|StartSubIcon #fIcon
Le0 f1 337 49 30 30 0 15 #rect
Le0 f1 @|EndSubIcon #fIcon
Le0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides the LogoutPage process of Portal Template. It set the in.logoutPage to the url of the overview page of Axon.Ivy.
So whenever user logout of Portal, he will be redirected to the overview page.</name>
        <nameStyle>214,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Le0 f3 456 34 736 44 -361 -16 #rect
Le0 f3 @|IBIcon #fIcon
Le0 f5 actionTable 'out=in;
' #txt
Le0 f5 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.support.UrlDetector;

in.logoutPage = new UrlDetector().getBaseURL(FacesContext.getCurrentInstance());' #txt
Le0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set logout page</name>
    </language>
</elementInfo>
' #txt
Le0 f5 168 42 112 44 -43 -8 #rect
Le0 f5 @|StepIcon #fIcon
Le0 f6 expr out #txt
Le0 f6 111 64 168 64 #arcP
Le0 f2 expr out #txt
Le0 f2 280 64 337 64 #arcP
>Proto Le0 .type _ch.ivyteam.ivy.project.portal.examples.LogoutPageOverrideData #txt
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
