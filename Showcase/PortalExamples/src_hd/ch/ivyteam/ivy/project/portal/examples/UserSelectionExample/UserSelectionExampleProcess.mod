[Ivy]
1702D46BD34B4FE4 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserSelectionExampleProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @UdEvent f3 '' #zField
Us0 @UdExitEnd f4 '' #zField
Us0 @PushWFArc f5 '' #zField
Us0 @GridStep f6 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @PushWFArc f7 '' #zField
>Proto Us0 Us0 UserSelectionExampleProcess #zField
Us0 f0 guid 1702D46BD3BF10DA #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<ch.ivy.addon.portalkit.dto.UserDTO selectedUserForDefinedRoles> result;' #txt
Us0 f0 outParameterMapAction 'result.selectedUserForDefinedRoles=in.selectedUserForDefinedRoles;
' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f1 339 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f3 guid 1702D46BD412C7F5 #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 131 26 26 -15 15 #rect
Us0 f3 @|UdEventIcon #fIcon
Us0 f4 211 131 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f5 109 144 211 144 #arcP
Us0 f6 actionTable 'out=in;
' #txt
Us0 f6 actionCode 'import ch.ivy.addon.portalkit.dto.UserDTO;
import java.util.Arrays;

in.definedRoleNames = Arrays.asList("CostObject", "GeneralManager");
in.selectedUserForReadOnlyField = new UserDTO(ivy.wf.getSecurityContext().findUser("costObject1"));
' #txt
Us0 f6 security system #txt
Us0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Us0 f6 168 42 112 44 -21 -8 #rect
Us0 f6 @|StepIcon #fIcon
Us0 f2 109 64 168 64 #arcP
Us0 f7 280 64 339 64 #arcP
>Proto Us0 .type ch.ivyteam.ivy.project.portal.examples.UserSelectionExample.UserSelectionExampleData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f3 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
Us0 f2 head f6 mainIn #connect
Us0 f7 head f1 mainIn #connect
Us0 f0 mainOut f2 tail #connect
Us0 f6 mainOut f7 tail #connect
