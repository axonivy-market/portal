[Ivy]
15DC1795219AF4F0 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskResponsibleFilterProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskResponsibleFilterProcess #zField
Ts0 f6 guid 15FB4A9997759979 #txt
Ts0 f6 method autoCompleteForResponsible(String) #txt
Ts0 f6 inParameterDecl '<String query> param;' #txt
Ts0 f6 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f6 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.ISecurityMember> responsibleList> result;' #txt
Ts0 f6 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

result.responsibleList = UserUtils.filterSecurityMembers(in.responsibles, in.queryAutoComplete);' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForResponsible(String)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -57 13 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 275 179 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 109 192 275 192 #arcP
Ts0 f0 guid 16827CB81F8394D6 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 275 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 109 96 275 96 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
