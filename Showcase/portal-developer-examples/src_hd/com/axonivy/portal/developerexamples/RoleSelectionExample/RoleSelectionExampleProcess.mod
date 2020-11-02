[Ivy]
175494178585DA0E 9.2.0 #module
>Proto >Proto Collection #zClass
Us0 RoleSelectionExampleProcess Big #zClass
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
Us0 @UdProcessEnd f9 '' #zField
Us0 @UdEvent f8 '' #zField
Us0 @PushWFArc f10 '' #zField
>Proto Us0 Us0 RoleSelectionExampleProcess #zField
Us0 f0 guid 1705146891B787A6 #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<ch.ivy.addon.portalkit.dto.RoleDTO selectedRoleForDefault> result;' #txt
Us0 f0 outParameterMapAction 'result.selectedRoleForDefault=in.selectedRoleForDefault;
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
Us0 f3 guid 17051468929E66C4 #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 243 26 26 -15 15 #rect
Us0 f3 @|UdEventIcon #fIcon
Us0 f4 211 243 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f5 109 256 211 256 #arcP
Us0 f6 actionTable 'out=in;
' #txt
Us0 f6 actionCode 'import ch.ivyteam.ivy.environment.Ivy;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivyteam.ivy.security.IRole;
import java.util.Arrays;

String everybodyRoleName = "Everybody";
IRole everybodyRole = ivy.wf.getSecurityContext().findRole(everybodyRoleName);
in.selectedRoleForReadOnlyField = new RoleDTO(everybodyRole);
Ivy.log().info("selected Role for read only field: " + in.selectedRoleForReadOnlyField.getDisplayName());
in.excludedRoleNames = Arrays.asList("CaseOwner", "GeneralManager");
in.definedRoleNames = Arrays.asList("Everybody");' #txt
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
Us0 f9 211 147 26 26 0 12 #rect
Us0 f9 @|UdProcessEndIcon #fIcon
Us0 f8 guid 01754A09107EDB89 #txt
Us0 f8 actionTable 'out=in;
' #txt
Us0 f8 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("form:item-select-event-component:item-select-event-for-role-selection", new FacesMessage(FacesMessage.SEVERITY_INFO, "You selected " + in.selectedRoleForInsertChildren.getDisplayName(), null));' #txt
Us0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showSelectedRole</name>
    </language>
</elementInfo>
' #txt
Us0 f8 83 147 26 26 -14 15 #rect
Us0 f8 @|UdEventIcon #fIcon
Us0 f10 109 160 211 160 #arcP
>Proto Us0 .type com.axonivy.portal.developerexamples.RoleSelectionExample.RoleSelectionExampleData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f3 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
Us0 f2 head f6 mainIn #connect
Us0 f7 head f1 mainIn #connect
Us0 f0 mainOut f2 tail #connect
Us0 f6 mainOut f7 tail #connect
Us0 f8 mainOut f10 tail #connect
Us0 f10 head f9 mainIn #connect
