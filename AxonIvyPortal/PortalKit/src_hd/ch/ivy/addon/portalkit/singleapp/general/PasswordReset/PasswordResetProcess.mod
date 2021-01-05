[Ivy]
175F97002EA7435A 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 PasswordResetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @UdEvent f10 '' #zField
Ps0 @CallSub f7 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @Alternative f11 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f118 '' #zField
Ps0 @PushWFArc f13 '' #zField
>Proto Ps0 Ps0 PasswordResetProcess #zField
Ps0 f0 guid 175F970030C8B97C #txt
Ps0 f0 method start(String,String) #txt
Ps0 f0 inParameterDecl '<String token,String username> param;' #txt
Ps0 f0 inParameterMapAction 'out.token=param.token;
out.username=param.username;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 175F9700322D7666 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 15 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 109 160 211 160 #arcP
Ps0 f6 547 243 26 26 0 12 #rect
Ps0 f6 @|UdProcessEndIcon #fIcon
Ps0 f10 guid 176217D65744540F #txt
Ps0 f10 actionTable 'out=in;
out.resetSuccess=false;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 83 243 26 26 -14 15 #rect
Ps0 f10 @|UdEventIcon #fIcon
Ps0 f7 processCall 'Functional Processes/ResetPassword:call(String,String,String,String)' #txt
Ps0 f7 requestActionDecl '<String newPassword,String passwordConfirmation,String token,String username> param;' #txt
Ps0 f7 requestMappingAction 'param.newPassword=in.newPassword;
param.passwordConfirmation=in.passwordConfirmation;
param.token=in.token;
param.username=in.username;
' #txt
Ps0 f7 responseMappingAction 'out=in;
out.message=result.message;
out.resetSuccess=result.resetSuccess;
' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset password</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 344 234 112 44 -43 -8 #rect
Ps0 f7 @|CallSubIcon #fIcon
Ps0 f9 456 256 547 256 #arcP
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logged in?</name>
    </language>
</elementInfo>
' #txt
Ps0 f11 208 240 32 32 8 16 #rect
Ps0 f11 @|AlternativeIcon #fIcon
Ps0 f12 109 256 208 256 #arcP
Ps0 f8 expr in #txt
Ps0 f8 outCond ivy.session.isSessionUserUnknown() #txt
Ps0 f8 240 256 344 256 #arcP
Ps0 f118 actionTable 'out=in;
' #txt
Ps0 f118 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator.navigateToPortalHome();' #txt
Ps0 f118 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to home page</name>
    </language>
</elementInfo>
' #txt
Ps0 f118 336 298 128 44 -60 -8 #rect
Ps0 f118 @|StepIcon #fIcon
Ps0 f13 expr in #txt
Ps0 f13 224 272 336 320 #arcP
Ps0 f13 1 224 320 #addKink
Ps0 f13 1 0.24087665727845922 0 0 #arcLabel
>Proto Ps0 .type ch.ivy.addon.portalkit.singleapp.general.PasswordReset.PasswordResetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f7 mainOut f9 tail #connect
Ps0 f9 head f6 mainIn #connect
Ps0 f10 mainOut f12 tail #connect
Ps0 f12 head f11 in #connect
Ps0 f11 out f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f11 out f13 tail #connect
Ps0 f13 head f118 mainIn #connect
