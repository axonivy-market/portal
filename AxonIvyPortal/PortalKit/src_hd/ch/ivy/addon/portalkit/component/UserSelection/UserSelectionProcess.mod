[Ivy]
1701DB2EAC42F069 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserSelectionProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @UdMethod f8 '' #zField
Us0 @UdProcessEnd f3 '' #zField
Us0 @PushWFArc f4 '' #zField
Us0 @UdMethod f7 '' #zField
Us0 @UdProcessEnd f9 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @PushWFArc f5 '' #zField
>Proto Us0 Us0 UserSelectionProcess #zField
Us0 f0 guid 1701DB2EB182D39E #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<> result;' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f1 227 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f8 guid 1703D8DDA5DF1EF9 #txt
Us0 f8 method completeUser(String) #txt
Us0 f8 inParameterDecl '<String query> param;' #txt
Us0 f8 inParameterMapAction 'out.query=param.query;
' #txt
Us0 f8 outParameterDecl '<List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Us0 f8 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

result.users = UserUtils.filterUsersDTO(in.users, in.query);



' #txt
Us0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>completeUser(String)</name>
    </language>
</elementInfo>
' #txt
Us0 f8 83 131 26 26 -25 15 #rect
Us0 f8 @|UdMethodIcon #fIcon
Us0 f3 227 131 26 26 0 12 #rect
Us0 f3 @|UdProcessEndIcon #fIcon
Us0 f4 109 144 227 144 #arcP
Us0 f7 guid 1703DD5AE97B88C4 #txt
Us0 f7 method initUsers() #txt
Us0 f7 inParameterDecl '<> param;' #txt
Us0 f7 outParameterDecl '<> result;' #txt
Us0 f7 outActionCode 'import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivyteam.ivy.security.IUser;
import org.apache.commons.collections4.CollectionUtils;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;

in.roleNames = Attrs.currentContext().getAttribute("#{cc.attrs.roleNames}", java.util.List.class) as List;

if (CollectionUtils.isEmpty(in.roleNames)) { //Load: waiting for API
  	in.users = UserUtils.findAllUserDTOsByApplication();
	} else { //Load: waiting for API
  	for (String roleName : in.roleNames) {
    	List<IUser> userList = ivy.wf.getSecurityContext().findRole(roleName).getAllUsers();
    	for(IUser user: userList) {
    		in.users.add(new UserDTO(user));
    	} 
  	}
}' #txt
Us0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initUsers</name>
    </language>
</elementInfo>
' #txt
Us0 f7 83 195 26 26 -25 15 #rect
Us0 f7 @|UdMethodIcon #fIcon
Us0 f9 227 195 26 26 0 12 #rect
Us0 f9 @|UdProcessEndIcon #fIcon
Us0 f2 109 64 227 64 #arcP
Us0 f5 109 208 227 208 #arcP
>Proto Us0 .type ch.ivy.addon.portalkit.component.UserSelection.UserSelectionData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f8 mainOut f4 tail #connect
Us0 f4 head f3 mainIn #connect
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f7 mainOut f5 tail #connect
Us0 f5 head f9 mainIn #connect
