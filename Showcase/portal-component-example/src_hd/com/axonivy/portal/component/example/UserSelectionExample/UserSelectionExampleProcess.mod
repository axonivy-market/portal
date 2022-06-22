[Ivy]
1814656506BC04C7 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 UserSelectionExampleProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @UdProcessEnd f9 '' #zField
Ds0 @GridStep f6 '' #zField
Ds0 @UdEvent f3 '' #zField
Ds0 @UdExitEnd f4 '' #zField
Ds0 @UdEvent f8 '' #zField
Ds0 @PushWFArc f7 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @PushWFArc f5 '' #zField
>Proto Ds0 Ds0 UserSelectionExampleProcess #zField
Ds0 f0 guid 181607413235837A #txt
Ds0 f0 method start() #txt
Ds0 f0 inParameterDecl '<> param;' #txt
Ds0 f0 outParameterDecl '<com.axonivy.portal.component.dto.UserDTO selectedUser> result;' #txt
Ds0 f0 outParameterMapAction 'result.selectedUser=in.selectedUser;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 51 51 26 26 -16 15 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 307 51 26 26 0 12 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f9 179 115 26 26 0 12 #rect
Ds0 f9 @|UdProcessEndIcon #fIcon
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'import java.util.Locale;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import com.axonivy.portal.component.dto.UserDTO;
import java.util.Arrays;

in.definedRoleNames = Arrays.asList("BackendDeveloper", "Tester");
String backendDev1UserName = "backendDev1";
IUser backendDev1 = ivy.wf.getSecurityContext().findUser(backendDev1UserName);
if (backendDev1 == null) {
  backendDev1 = ivy.wf.getSecurityContext().createUser(backendDev1UserName, "Backend Developer 1", "", Locale.GERMAN, "", "");
  IRole backendDev = ivy.wf.getSecurityContext().findRole("BackendDeveloper");
  if (backendDev != null) {
  	backendDev1.addRole(backendDev);
  }
}
in.selectedUserForReadOnlyField = new UserDTO(backendDev1);
in.excludedUsernames = Arrays.asList("supporter", "tester");' #txt
Ds0 f6 security system #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ds0 f6 136 42 112 44 -21 -8 #rect
Ds0 f6 @|StepIcon #fIcon
Ds0 f3 guid 181607413244BCC0 #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 51 179 26 26 -15 15 #rect
Ds0 f3 @|UdEventIcon #fIcon
Ds0 f4 179 179 26 26 0 12 #rect
Ds0 f4 @|UdExitEndIcon #fIcon
Ds0 f8 guid 181607413244BEE7 #txt
Ds0 f8 actionTable 'out=in;
' #txt
Ds0 f8 actionCode 'import org.primefaces.PrimeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("form:item-select-event-component:item-select-event-for-user-selection", new FacesMessage(FacesMessage.SEVERITY_INFO, "You selected " + in.selectedUserForInsertChildren.getDisplayName(), null));' #txt
Ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showSelectedUser</name>
    </language>
</elementInfo>
' #txt
Ds0 f8 51 115 26 26 -14 15 #rect
Ds0 f8 @|UdEventIcon #fIcon
Ds0 f7 248 64 307 64 #arcP
Ds0 f10 77 128 179 128 #arcP
Ds0 f2 77 64 136 64 #arcP
Ds0 f5 77 192 179 192 #arcP
>Proto Ds0 .type com.axonivy.portal.component.example.UserSelectionExample.UserSelectionExampleData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
Ds0 f2 head f6 mainIn #connect
Ds0 f7 head f1 mainIn #connect
Ds0 f0 mainOut f2 tail #connect
Ds0 f6 mainOut f7 tail #connect
Ds0 f8 mainOut f10 tail #connect
Ds0 f10 head f9 mainIn #connect
