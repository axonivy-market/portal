[Ivy]
15F954914B8785D1 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseCreatorFilterProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 CaseCreatorFilterProcess #zField
Cs0 f0 guid 15F954914E422B7F #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f6 guid 15FB4DDD4631366E #txt
Cs0 f6 method autoCompleteForCreator(String) #txt
Cs0 f6 inParameterDecl '<String query> param;' #txt
Cs0 f6 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Cs0 f6 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IUser> creators> result;' #txt
Cs0 f6 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
if(in.creators.isEmpty()) {
	in.creators = UserUtils.findAllUserByApplication();
}
result.creators = UserUtils.filterUsers(in.creators, in.queryAutoComplete);' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForCreator(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 147 26 26 -88 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 211 147 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 109 160 211 160 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.cases.filter.CaseCreatorFilter.CaseCreatorFilterData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
