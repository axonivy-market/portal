[Ivy]
163A4BFC48261B85 9.3.1 #module
>Proto >Proto Collection #zClass
Ps0 SearchResultsProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdInit f3 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 SearchResultsProcess #zField
Ps0 f0 guid 150D5B63FE18100D #txt
Ps0 f0 method start(String) #txt
Ps0 f0 inParameterDecl '<String keyword> param;' #txt
Ps0 f0 inParameterMapAction 'out.activeTabIndex=0;
out.dataModel.caseDataModel.notKeepFilter=true;
out.dataModel.keyword=param.keyword;
out.dataModel.taskDataModel.compactMode=false;
out.dataModel.taskDataModel.notKeepFilter=true;
' #txt
Ps0 f0 inActionCode out.dataModel.search(); #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 53 85 22 22 -16 13 #rect
Ps0 f1 333 85 22 22 14 0 #rect
Ps0 f2 expr out #txt
Ps0 f2 75 96 333 96 #arcP
Ps0 f3 guid 163AFE8CC44DFD15 #txt
Ps0 f3 method start(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number) #txt
Ps0 f3 inParameterDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel dataModel,Number activeTabIndex> param;' #txt
Ps0 f3 inParameterMapAction 'out.activeTabIndex=param.activeTabIndex;
out.dataModel=param.dataModel;
' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(SearchResultsDataModel)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 179 26 26 -52 19 #rect
Ps0 f4 339 179 26 26 0 12 #rect
Ps0 f5 expr out #txt
Ps0 f5 77 192 339 192 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.SearchResults.SearchResultsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
