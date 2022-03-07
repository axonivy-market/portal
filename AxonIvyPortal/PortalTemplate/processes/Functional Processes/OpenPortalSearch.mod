[Ivy]
163AFDEDB4167156 9.4.0 #module
>Proto >Proto Collection #zClass
Oh0 OpenPortalSearch Big #zClass
Oh0 B #cInfo
Oh0 #process
Oh0 @TextInP .type .type #zField
Oh0 @TextInP .processKind .processKind #zField
Oh0 @AnnotationInP-0n ai ai #zField
Oh0 @MessageFlowInP-0n messageIn messageIn #zField
Oh0 @MessageFlowOutP-0n messageOut messageOut #zField
Oh0 @TextInP .xml .xml #zField
Oh0 @TextInP .responsibility .responsibility #zField
Oh0 @StartSub f0 '' #zField
Oh0 @EndSub f1 '' #zField
Oh0 @UserDialog f3 '' #zField
Oh0 @PushWFArc f4 '' #zField
Oh0 @PushWFArc f2 '' #zField
Oh0 @StartSub f5 '' #zField
Oh0 @EndSub f6 '' #zField
Oh0 @UserDialog f7 '' #zField
Oh0 @PushWFArc f8 '' #zField
Oh0 @PushWFArc f9 '' #zField
Oh0 @InfoButton f10 '' #zField
Oh0 @InfoButton f11 '' #zField
Oh0 @AnnotationArc f12 '' #zField
Oh0 @AnnotationArc f13 '' #zField
>Proto Oh0 Oh0 OpenPortalSearch #zField
Oh0 f0 inParamDecl '<String keyword> param;' #txt
Oh0 f0 inParamTable 'out.keyword=param.keyword;
' #txt
Oh0 f0 outParamDecl '<> result;' #txt
Oh0 f0 callSignature call(String) #txt
Oh0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
    </language>
</elementInfo>
' #txt
Oh0 f0 81 113 30 30 -24 -35 #rect
Oh0 f1 753 113 30 30 0 15 #rect
Oh0 f3 dialogId ch.ivy.addon.portal.generic.SearchResults #txt
Oh0 f3 startMethod start(String) #txt
Oh0 f3 requestActionDecl '<String keyword> param;' #txt
Oh0 f3 requestMappingAction 'param.keyword=in.keyword;
' #txt
Oh0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalSearchData out;
' #txt
Oh0 f3 responseMappingAction 'out=in;
' #txt
Oh0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SearchResults</name>
    </language>
</elementInfo>
' #txt
Oh0 f3 392 106 112 44 -41 -8 #rect
Oh0 f4 expr out #txt
Oh0 f4 111 128 392 128 #arcP
Oh0 f2 expr out #txt
Oh0 f2 504 128 753 128 #arcP
Oh0 f5 inParamDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel searchResultsDataModel,Number activeTabIndex> param;' #txt
Oh0 f5 inParamTable 'out.activeTabIndex=param.activeTabIndex;
out.searchResultsDataModel=param.searchResultsDataModel;
' #txt
Oh0 f5 outParamDecl '<> result;' #txt
Oh0 f5 callSignature call(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number) #txt
Oh0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(SearchResultsDataModel,Number)</name>
    </language>
</elementInfo>
' #txt
Oh0 f5 81 497 30 30 -62 -36 #rect
Oh0 f6 753 497 30 30 0 15 #rect
Oh0 f7 dialogId ch.ivy.addon.portal.generic.SearchResults #txt
Oh0 f7 startMethod start(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number) #txt
Oh0 f7 requestActionDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel dataModel,Number activeTabIndex> param;' #txt
Oh0 f7 requestMappingAction 'param.dataModel=in.searchResultsDataModel;
param.activeTabIndex=in.activeTabIndex;
' #txt
Oh0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalSearchData out;
' #txt
Oh0 f7 responseMappingAction 'out=in;
' #txt
Oh0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SearchResults</name>
    </language>
</elementInfo>
' #txt
Oh0 f7 386 490 112 44 -41 -8 #rect
Oh0 f8 expr out #txt
Oh0 f8 111 512 386 512 #arcP
Oh0 f9 expr out #txt
Oh0 f9 498 512 753 512 #arcP
Oh0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal search results UI: you should use Axon Ivy HTMLOverride Dialog to override the SearchResults Html dialog.</name>
    </language>
</elementInfo>
' #txt
Oh0 f10 168 194 688 76 -341 -34 #rect
Oh0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal search results UI: you should use Axon Ivy HTMLOverride Dialog to override the SearchResults Html dialog.</name>
    </language>
</elementInfo>
' #txt
Oh0 f11 168 586 688 76 -341 -34 #rect
Oh0 f12 168 232 104 140 #arcP
Oh0 f13 168 624 104 524 #arcP
>Proto Oh0 .type ch.ivy.addon.portal.generic.OpenPortalSearchData #txt
>Proto Oh0 .processKind CALLABLE_SUB #txt
>Proto Oh0 0 0 32 24 18 0 #rect
>Proto Oh0 @|BIcon #fIcon
Oh0 f0 mainOut f4 tail #connect
Oh0 f4 head f3 mainIn #connect
Oh0 f3 mainOut f2 tail #connect
Oh0 f2 head f1 mainIn #connect
Oh0 f5 mainOut f8 tail #connect
Oh0 f8 head f7 mainIn #connect
Oh0 f7 mainOut f9 tail #connect
Oh0 f9 head f6 mainIn #connect
Oh0 f10 ao f12 tail #connect
Oh0 f12 head f0 @CG|ai #connect
Oh0 f11 ao f13 tail #connect
Oh0 f13 head f5 @CG|ai #connect
