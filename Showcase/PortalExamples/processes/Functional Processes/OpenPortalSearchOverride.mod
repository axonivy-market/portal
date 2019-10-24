[Ivy]
163AF40A52CD810B 7.5.0 #module
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
Oh0 f0 81 49 30 30 -29 17 #rect
Oh0 f0 @|StartSubIcon #fIcon
Oh0 f1 337 49 30 30 0 15 #rect
Oh0 f1 @|EndSubIcon #fIcon
Oh0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults #txt
Oh0 f3 startMethod start(String) #txt
Oh0 f3 requestActionDecl '<String keyword> param;' #txt
Oh0 f3 requestMappingAction 'param.keyword=in.keyword;
' #txt
Oh0 f3 responseActionDecl '_ch.ivyteam.ivy.project.portal.examples.OpenPortalSearchOverrideData out;
' #txt
Oh0 f3 responseMappingAction 'out=in;
' #txt
Oh0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Search Results</name>
        <nameStyle>14
</nameStyle>
    </language>
</elementInfo>
' #txt
Oh0 f3 161 42 112 44 -42 -8 #rect
Oh0 f3 @|UserDialogIcon #fIcon
Oh0 f4 expr out #txt
Oh0 f4 111 64 161 64 #arcP
Oh0 f2 expr out #txt
Oh0 f2 273 64 337 64 #arcP
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
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Oh0 f5 81 178 30 30 -69 19 #rect
Oh0 f5 @|StartSubIcon #fIcon
Oh0 f6 337 178 30 30 0 15 #rect
Oh0 f6 @|EndSubIcon #fIcon
Oh0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults #txt
Oh0 f7 startMethod start(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number) #txt
Oh0 f7 requestActionDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel searchResultsDataModel,Number activeTabIndex> param;' #txt
Oh0 f7 requestMappingAction 'param.searchResultsDataModel=in.searchResultsDataModel;
param.activeTabIndex=in.activeTabIndex;
' #txt
Oh0 f7 responseActionDecl '_ch.ivyteam.ivy.project.portal.examples.OpenPortalSearchOverrideData out;
' #txt
Oh0 f7 responseMappingAction 'out=in;
' #txt
Oh0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SearchResults</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Oh0 f7 162 171 112 44 -41 -8 #rect
Oh0 f7 @|UserDialogIcon #fIcon
Oh0 f8 expr out #txt
Oh0 f8 111 193 162 193 #arcP
Oh0 f9 expr out #txt
Oh0 f9 274 193 337 193 #arcP
>Proto Oh0 .type _ch.ivyteam.ivy.project.portal.examples.OpenPortalSearchOverrideData #txt
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
