[Ivy]
1705118A05E4FB9D 7.5.0 #module
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
Us0 @PushWFArc f2 '' #zField
Us0 @UdProcessEnd f3 '' #zField
Us0 @UdMethod f4 '' #zField
Us0 @UdMethod f5 '' #zField
Us0 @UdProcessEnd f6 '' #zField
Us0 @PushWFArc f7 '' #zField
Us0 @PushWFArc f8 '' #zField
>Proto Us0 Us0 UserSelectionProcess #zField
Us0 f0 guid 1705118A1557E73F #txt
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
Us0 f1 211 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f2 109 64 211 64 #arcP
Us0 f3 211 115 26 26 0 12 #rect
Us0 f3 @|UdProcessEndIcon #fIcon
Us0 f4 guid 170511AAD5664FE0 #txt
Us0 f4 method completeUser(String) #txt
Us0 f4 inParameterDecl '<String query> param;' #txt
Us0 f4 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Us0 f4 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.addon.portalkit.jsf.Attrs;

result.users = UserUtils.findUsers(in.query, 0, in.maxResults);' #txt
Us0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>completeUser(String)</name>
    </language>
</elementInfo>
' #txt
Us0 f4 83 115 26 26 -25 15 #rect
Us0 f4 @|UdMethodIcon #fIcon
Us0 f5 guid 170511ABF35C456B #txt
Us0 f5 method initUsers(Integer) #txt
Us0 f5 inParameterDecl '<Integer maxResults> param;' #txt
Us0 f5 inParameterMapAction 'out.maxResults=param.maxResults;
' #txt
Us0 f5 outParameterDecl '<> result;' #txt
Us0 f5 outActionCode 'import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivyteam.ivy.security.IUser;
import org.apache.commons.collections4.CollectionUtils;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;

in.roleNames = Attrs.currentContext().getAttribute("#{cc.attrs.roleNames}", java.util.List.class) as List;

if (CollectionUtils.isEmpty(in.roleNames)) {
  	in.users = UserUtils.findUsers(in.query, 0, in.maxResults);
	} else { //Load: waiting for API
  	
}' #txt
Us0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initUsers(Integer)</name>
    </language>
</elementInfo>
' #txt
Us0 f5 83 179 26 26 -28 15 #rect
Us0 f5 @|UdMethodIcon #fIcon
Us0 f6 211 179 26 26 0 12 #rect
Us0 f6 @|UdProcessEndIcon #fIcon
Us0 f7 109 128 211 128 #arcP
Us0 f8 109 192 211 192 #arcP
>Proto Us0 .type ch.ivy.addon.portalkit.component.UserSelection.UserSelectionData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f4 mainOut f7 tail #connect
Us0 f7 head f3 mainIn #connect
Us0 f5 mainOut f8 tail #connect
Us0 f8 head f6 mainIn #connect
