[Ivy]
163AFDEDB4167156 3.23 #module
>Proto >Proto Collection #zClass
Oh0 OpenPortalSearch Big #zClass
Oh0 B #cInfo
Oh0 #process
Oh0 @TextInP .resExport .resExport #zField
Oh0 @TextInP .type .type #zField
Oh0 @TextInP .processKind .processKind #zField
Oh0 @AnnotationInP-0n ai ai #zField
Oh0 @MessageFlowInP-0n messageIn messageIn #zField
Oh0 @MessageFlowOutP-0n messageOut messageOut #zField
Oh0 @TextInP .xml .xml #zField
Oh0 @TextInP .responsibility .responsibility #zField
Oh0 @StartSub f0 '' #zField
Oh0 @EndSub f1 '' #zField
Oh0 @RichDialog f3 '' #zField
Oh0 @PushWFArc f4 '' #zField
Oh0 @PushWFArc f2 '' #zField
>Proto Oh0 Oh0 OpenPortalSearch #zField
Oh0 f0 inParamDecl '<java.lang.String keyword> param;' #txt
Oh0 f0 inParamTable 'out.keyword=param.keyword;
' #txt
Oh0 f0 outParamDecl '<> result;
' #txt
Oh0 f0 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalSearchData out;
' #txt
Oh0 f0 callSignature call(String) #txt
Oh0 f0 type ch.ivy.addon.portal.generic.OpenPortalSearchData #txt
Oh0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
    </language>
</elementInfo>
' #txt
Oh0 f0 81 49 30 30 -29 17 #rect
Oh0 f0 @|StartSubIcon #fIcon
Oh0 f1 type ch.ivy.addon.portal.generic.OpenPortalSearchData #txt
Oh0 f1 337 49 30 30 0 15 #rect
Oh0 f1 @|EndSubIcon #fIcon
Oh0 f3 targetWindow NEW #txt
Oh0 f3 targetDisplay TOP #txt
Oh0 f3 richDialogId ch.ivy.addon.portal.generic.SearchResults #txt
Oh0 f3 startMethod start(String) #txt
Oh0 f3 type ch.ivy.addon.portal.generic.OpenPortalSearchData #txt
Oh0 f3 requestActionDecl '<String keyword> param;' #txt
Oh0 f3 requestMappingAction 'param.keyword=in.keyword;
' #txt
Oh0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalSearchData out;
' #txt
Oh0 f3 responseMappingAction 'out=in;
' #txt
Oh0 f3 isAsynch false #txt
Oh0 f3 isInnerRd false #txt
Oh0 f3 userContext '* ' #txt
Oh0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SearchResults</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Oh0 f3 168 42 112 44 -41 -8 #rect
Oh0 f3 @|RichDialogIcon #fIcon
Oh0 f4 expr out #txt
Oh0 f4 111 64 168 64 #arcP
Oh0 f2 expr out #txt
Oh0 f2 280 64 337 64 #arcP
>Proto Oh0 .type ch.ivy.addon.portal.generic.OpenPortalSearchData #txt
>Proto Oh0 .processKind CALLABLE_SUB #txt
>Proto Oh0 0 0 32 24 18 0 #rect
>Proto Oh0 @|BIcon #fIcon
Oh0 f0 mainOut f4 tail #connect
Oh0 f4 head f3 mainIn #connect
Oh0 f3 mainOut f2 tail #connect
Oh0 f2 head f1 mainIn #connect
