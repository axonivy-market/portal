[Ivy]
17313A70C291401F 9.3.1 #module
>Proto >Proto Collection #zClass
Cs0 CaseOwnerFilterProcess Big #zClass
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
>Proto Cs0 Cs0 CaseOwnerFilterProcess #zField
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
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f6 guid 17313AEE1F4996A7 #txt
Cs0 f6 method query(String) #txt
Cs0 f6 inParameterDecl '<String query> param;' #txt
Cs0 f6 inParameterMapAction 'out.query=param.query;
' #txt
Cs0 f6 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> owners> result;' #txt
Cs0 f6 outActionCode 'import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

result.owners = SecurityMemberUtils.findSecurityMembers(in.query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>query(String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 147 26 26 -29 14 #rect
Cs0 f7 275 147 26 26 0 12 #rect
Cs0 f8 expr out #txt
Cs0 f8 109 160 275 160 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.cases.filter.CaseOwnerFilter.CaseOwnerFilterData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
