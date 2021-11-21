[Ivy]
17B85C8E8CCA8F78 9.3.1 #module
>Proto >Proto Collection #zClass
Ls0 LoginProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @AnnotationInP-0n ai ai #zField
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdEvent f6 '' #zField
Ls0 @GridStep f8 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @Alternative f3 '' #zField
Ls0 @PushWFArc f4 '' #zField
Ls0 @UdProcessEnd f12 '' #zField
Ls0 @PushWFArc f13 '' #zField
Ls0 @UdExitEnd f5 '' #zField
Ls0 @PushWFArc f11 '' #zField
>Proto Ls0 Ls0 LoginProcess #zField
Ls0 f0 guid 17B85C8E8D4A9603 #txt
Ls0 f0 method start() #txt
Ls0 f0 inParameterDecl '<> param;' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f1 179 51 26 26 0 12 #rect
Ls0 f2 109 64 179 64 #arcP
Ls0 f6 guid 17B85CC491521A2C #txt
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f6 85 173 20 20 -9 13 #rect
Ls0 f8 actionTable 'out=in;
' #txt
Ls0 f8 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IRole;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

in.loginOk = ivy.session.loginSessionUser(in.username, in.password);
out.password = null;
boolean hasPermission = false;
if (in.loginOk)
{
	List<IRole> userRoles = ivy.session.getSessionUser().getRoles();
	for (IRole role : userRoles) {
		if (StringUtils.equalsIgnoreCase("AXONIVY_PORTAL_ADMIN", role.getName()))
		{
			hasPermission = true;
			break;
		}
	}
}

if (!in.loginOk || !hasPermission) 
{
	String message = in.loginOk ? "You do not have permission to start this link" : "Login failed";
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message));
	FacesContext.getCurrentInstance().validationFailed();
	in.loginOk = false;
}' #txt
Ls0 f8 security system #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login get user settings</name>
        <nameStyle>6,5
17,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f8 176 161 128 44 -61 -8 #rect
Ls0 f9 expr out #txt
Ls0 f9 105 183 176 183 #arcP
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is successful?</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 368 167 32 32 -35 -39 #rect
Ls0 f4 expr out #txt
Ls0 f4 304 183 368 183 #arcP
Ls0 f12 507 170 26 26 0 12 #rect
Ls0 f13 expr in #txt
Ls0 f13 outCond !in.loginOk #txt
Ls0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>failed</name>
    </language>
</elementInfo>
' #txt
Ls0 f13 400 183 507 183 #arcP
Ls0 f13 0 0.5214723926380368 0 -13 #arcLabel
Ls0 f5 371 266 26 26 0 12 #rect
Ls0 f11 expr in #txt
Ls0 f11 384 199 384 266 #arcP
>Proto Ls0 .type portal.migration.Login.LoginData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f6 mainOut f9 tail #connect
Ls0 f9 head f8 mainIn #connect
Ls0 f8 mainOut f4 tail #connect
Ls0 f4 head f3 in #connect
Ls0 f3 out f13 tail #connect
Ls0 f13 head f12 mainIn #connect
Ls0 f3 out f11 tail #connect
Ls0 f11 head f5 mainIn #connect
