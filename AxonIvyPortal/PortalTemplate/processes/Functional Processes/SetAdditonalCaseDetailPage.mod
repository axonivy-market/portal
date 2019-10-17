[Ivy]
1624CFE2532EF6BE 7.5.0 #module
>Proto >Proto Collection #zClass
Se0 SetAdditonalCaseDetailPage Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @MessageFlowInP-0n messageIn messageIn #zField
Se0 @MessageFlowOutP-0n messageOut messageOut #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartSub f0 '' #zField
Se0 @EndSub f1 '' #zField
Se0 @GridStep f3 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @PushWFArc f2 '' #zField
>Proto Se0 Se0 SetAdditonalCaseDetailPage #zField
Se0 f0 inParamDecl '<String linkToAddtionalCaseDetailPage> param;' #txt
Se0 f0 inParamTable 'out.linkToAdditonalCaseDetailPage=param.linkToAddtionalCaseDetailPage;
' #txt
Se0 f0 outParamDecl '<> result;' #txt
Se0 f0 callSignature call(String) #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 81 49 30 30 -29 17 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f1 337 49 30 30 0 15 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f3 actionTable 'out=in;
' #txt
Se0 f3 actionCode 'import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.support.UrlDetector;

ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());
String casePageUrl = collector.findLinkByFriendlyRequestPath(in.linkToAdditonalCaseDetailPage)  
											+ "?caseId=" + ivy.case.getId();
											
ivy.case.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).set(casePageUrl);' #txt
Se0 f3 security system #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set additional case detail page</name>
    </language>
</elementInfo>
' #txt
Se0 f3 136 42 176 44 -84 -8 #rect
Se0 f3 @|StepIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 111 64 136 64 #arcP
Se0 f2 expr out #txt
Se0 f2 312 64 337 64 #arcP
>Proto Se0 .type ch.ivy.addon.portal.generic.SetAdditonalCaseDetailPageData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f0 mainOut f4 tail #connect
Se0 f4 head f3 mainIn #connect
Se0 f3 mainOut f2 tail #connect
Se0 f2 head f1 mainIn #connect
