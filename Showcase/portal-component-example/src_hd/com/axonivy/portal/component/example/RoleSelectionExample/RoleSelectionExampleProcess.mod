[Ivy]
1816045BF8513F9C 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 RoleSelectionExampleProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdProcessEnd f6 '' #zField
Ds0 @UdInit f10 '' #zField
Ds0 @GridStep f12 '' #zField
Ds0 @PushWFArc f14 '' #zField
Ds0 @PushWFArc f15 '' #zField
Ds0 @UdProcessEnd f0 '' #zField
Ds0 @UdEvent f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdExitEnd f4 '' #zField
Ds0 @UdEvent f3 '' #zField
Ds0 @PushWFArc f5 '' #zField
>Proto Ds0 Ds0 RoleSelectionExampleProcess #zField
Ds0 f6 339 19 26 26 0 12 #rect
Ds0 f6 @|UdProcessEndIcon #fIcon
Ds0 f10 guid 181465783666DA74 #txt
Ds0 f10 method start() #txt
Ds0 f10 inParameterDecl '<> param;' #txt
Ds0 f10 outParameterDecl '<com.axonivy.portal.component.dto.RoleDTO selectedRole> result;' #txt
Ds0 f10 outParameterMapAction 'result.selectedRole=in.selectedRole;
' #txt
Ds0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ds0 f10 83 19 26 26 -16 15 #rect
Ds0 f10 @|UdInitIcon #fIcon
Ds0 f12 actionTable 'out=in;
' #txt
Ds0 f12 actionCode 'import ch.ivyteam.ivy.environment.Ivy;
import com.axonivy.portal.component.dto.RoleDTO;
import ch.ivyteam.ivy.security.IRole;
import java.util.Arrays;

String everybodyRoleName = "Everybody";
IRole everybodyRole = ivy.wf.getSecurityContext().findRole(everybodyRoleName);
in.selectedRoleForReadOnlyField = new RoleDTO(everybodyRole);
Ivy.log().info("selected Role for read only field: " + in.selectedRoleForReadOnlyField.getDisplayName());
in.excludedRoleNames = Arrays.asList("CaseOwner", "GeneralManager");
in.definedRoleNames = Arrays.asList("Everybody");' #txt
Ds0 f12 security system #txt
Ds0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ds0 f12 168 10 112 44 -21 -8 #rect
Ds0 f12 @|StepIcon #fIcon
Ds0 f14 280 32 339 32 #arcP
Ds0 f15 109 32 168 32 #arcP
Ds0 f0 211 115 26 26 0 12 #rect
Ds0 f0 @|UdProcessEndIcon #fIcon
Ds0 f1 guid 181467B2A51C1AB8 #txt
Ds0 f1 actionTable 'out=in;
' #txt
Ds0 f1 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("form:role-item-select-event-component:item-select-event-for-role-selection", new FacesMessage(FacesMessage.SEVERITY_INFO, "You selected " + in.selectedRoleForInsertChildren.getDisplayName(), null));' #txt
Ds0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showSelectedRole</name>
    </language>
</elementInfo>
' #txt
Ds0 f1 83 115 26 26 -14 15 #rect
Ds0 f1 @|UdEventIcon #fIcon
Ds0 f2 109 128 211 128 #arcP
Ds0 f4 211 211 26 26 0 12 #rect
Ds0 f4 @|UdExitEndIcon #fIcon
Ds0 f3 guid 181604C8A33D3F7E #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 83 211 26 26 -15 15 #rect
Ds0 f3 @|UdEventIcon #fIcon
Ds0 f5 109 224 211 224 #arcP
>Proto Ds0 .type com.axonivy.portal.component.example.RoleSelectionExample.RoleSelectionExampleData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f15 head f12 mainIn #connect
Ds0 f14 head f6 mainIn #connect
Ds0 f10 mainOut f15 tail #connect
Ds0 f12 mainOut f14 tail #connect
Ds0 f1 mainOut f2 tail #connect
Ds0 f2 head f0 mainIn #connect
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
