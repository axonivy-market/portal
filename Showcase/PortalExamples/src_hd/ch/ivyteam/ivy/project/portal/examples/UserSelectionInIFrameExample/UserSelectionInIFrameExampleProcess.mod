[Ivy]
180AD454F307E783 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserSelectionInIFrameExampleProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @AnnotationInP-0n ai ai #zField
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
Us0 @PushWFArc f7 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @UdEvent f8 '' #zField
Us0 @UdProcessEnd f9 '' #zField
Us0 @PushWFArc f10 '' #zField
>Proto Us0 Us0 UserSelectionInIFrameExampleProcess #zField
Us0 f0 guid 180AD454F4A7D6C8 #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<com.axonivy.portal.component.dto.UserDTO selectedUserForDefinedRoles> result;' #txt
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
Us0 f3 guid 180AD454F6055512 #txt
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
Us0 f4 339 243 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f5 109 256 339 256 #arcP
Us0 f6 actionTable 'out=in;
' #txt
Us0 f6 actionCode 'import java.util.Locale;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import com.axonivy.portal.component.dto.UserDTO;
import java.util.Arrays;

in.definedRoleNames = Arrays.asList("CostObject", "GeneralManager");
String costObject1UserName = "costObject1";
IUser costObject1 = ivy.wf.getSecurityContext().findUser(costObject1UserName);
if (costObject1 == null) {
  costObject1 = ivy.wf.getSecurityContext().createUser(costObject1UserName, "Cost Object 1", "", Locale.GERMAN, "", "");
  IRole costObject = ivy.wf.getSecurityContext().findRole("CostObject");
  if (costObject != null) {
  	costObject1.addRole(costObject);
  }
}
in.selectedUserForReadOnlyField = new UserDTO(costObject1);
in.excludedUsernames = Arrays.asList("gm1", "gm2", "admin");' #txt
Us0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Us0 f6 168 42 112 44 -21 -8 #rect
Us0 f6 @|StepIcon #fIcon
Us0 f7 109 64 168 64 #arcP
Us0 f2 280 64 339 64 #arcP
Us0 f8 guid 180AD4EB4B5B1604 #txt
Us0 f8 actionTable 'out=in;
' #txt
Us0 f8 actionCode 'import org.primefaces.PrimeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("form:item-select-event-component:item-select-event-for-user-selection", new FacesMessage(FacesMessage.SEVERITY_INFO, "You selected " + in.selectedUserForInsertChildren.getDisplayName(), null));' #txt
Us0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showSelectedUser</name>
    </language>
</elementInfo>
' #txt
Us0 f8 83 147 26 26 -14 15 #rect
Us0 f8 @|UdEventIcon #fIcon
Us0 f9 339 147 26 26 0 12 #rect
Us0 f9 @|UdProcessEndIcon #fIcon
Us0 f10 109 160 339 160 #arcP
>Proto Us0 .type ch.ivyteam.ivy.project.portal.examples.UserSelectionInIFrameExample.UserSelectionInIFrameExampleData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f3 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
Us0 f0 mainOut f7 tail #connect
Us0 f7 head f6 mainIn #connect
Us0 f6 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f8 mainOut f10 tail #connect
Us0 f10 head f9 mainIn #connect
