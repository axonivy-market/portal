[Ivy]
176463FD4BBF6C93 9.2.0 #module
>Proto >Proto Collection #zClass
Pt0 PasswordReset Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @PushWFArc f2 '' #zField
>Proto Pt0 Pt0 PasswordReset #zField
Pt0 f0 outLink PasswordReset.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature PasswordReset() #txt
Pt0 f0 caseData businessCase.attach=true #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PasswordReset.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 81 49 30 30 -21 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 433 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 actionTable 'out=in;
' #txt
Pt0 f3 actionCode 'import java.util.Calendar;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.util.UserUtils;
import java.util.Locale;

String username = "test_forgot_password_user";
String userFullName = "Forgot Password User";
String userPassword = "123";
String userEmail = "wawa@axonivy.io";

if (ivy.wf.getSecurityContext().findUser(username) != null) {
	ivy.wf.getSecurityContext().deleteUser(username);
}
ivy.wf.getSecurityContext().createUser(username, userFullName, userPassword, Locale.ENGLISH, userEmail, "");

IUser user = UserUtils.findUserByUsername(username);
long expiryTime = Calendar.getInstance().getTimeInMillis() + 60*60000;
String token = "123";
user.setProperty(UserProperty.RESET_PASSWORD_TOKEN, token);
user.setProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY, String.valueOf(expiryTime));' #txt
Pt0 f3 security system #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create test user</name>
    </language>
</elementInfo>
' #txt
Pt0 f3 216 42 112 44 -42 -8 #rect
Pt0 f3 @|StepIcon #fIcon
Pt0 f4 111 64 216 64 #arcP
Pt0 f2 328 64 433 64 #arcP
>Proto Pt0 .type portalKit_test.Data #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
