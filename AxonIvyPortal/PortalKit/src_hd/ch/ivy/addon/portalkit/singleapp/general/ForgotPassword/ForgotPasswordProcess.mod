[Ivy]
175F90CDEB5D001F 9.2.0 #module
>Proto >Proto Collection #zClass
Fs0 ForgotPasswordProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @UdInit f0 '' #zField
Fs0 @UdProcessEnd f1 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @UdEvent f3 '' #zField
Fs0 @UdExitEnd f4 '' #zField
Fs0 @PushWFArc f5 '' #zField
Fs0 @UdEvent f18 '' #zField
Fs0 @UdProcessEnd f6 '' #zField
Fs0 @CallSub f7 '' #zField
Fs0 @PushWFArc f9 '' #zField
Fs0 @Alternative f10 '' #zField
Fs0 @PushWFArc f11 '' #zField
Fs0 @PushWFArc f8 '' #zField
Fs0 @GridStep f118 '' #zField
Fs0 @PushWFArc f12 '' #zField
>Proto Fs0 Fs0 ForgotPasswordProcess #zField
Fs0 f0 guid 175F90CDEF7A1E5C #txt
Fs0 f0 method start() #txt
Fs0 f0 inParameterDecl '<> param;' #txt
Fs0 f0 outParameterDecl '<> result;' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -16 15 #rect
Fs0 f0 @|UdInitIcon #fIcon
Fs0 f1 211 51 26 26 0 12 #rect
Fs0 f1 @|UdProcessEndIcon #fIcon
Fs0 f2 109 64 211 64 #arcP
Fs0 f3 guid 0175F90CDFB96F15 #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 147 26 26 -15 15 #rect
Fs0 f3 @|UdEventIcon #fIcon
Fs0 f4 211 147 26 26 0 12 #rect
Fs0 f4 @|UdExitEndIcon #fIcon
Fs0 f5 109 160 211 160 #arcP
Fs0 f18 guid 175F92152EC548D2 #txt
Fs0 f18 actionTable 'out=in;
' #txt
Fs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send</name>
    </language>
</elementInfo>
' #txt
Fs0 f18 83 243 26 26 -14 15 #rect
Fs0 f18 @|UdEventIcon #fIcon
Fs0 f6 547 243 26 26 0 12 #rect
Fs0 f6 @|UdProcessEndIcon #fIcon
Fs0 f7 processCall 'Functional Processes/SendPasswordResetEmail:call(String)' #txt
Fs0 f7 requestActionDecl '<String email> param;' #txt
Fs0 f7 requestMappingAction 'param.email=in.email;
' #txt
Fs0 f7 responseMappingAction 'out=in;
' #txt
Fs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send email</name>
    </language>
</elementInfo>
' #txt
Fs0 f7 344 234 112 44 -31 -8 #rect
Fs0 f7 @|CallSubIcon #fIcon
Fs0 f9 456 256 547 256 #arcP
Fs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logged in?</name>
    </language>
</elementInfo>
' #txt
Fs0 f10 208 240 32 32 8 16 #rect
Fs0 f10 @|AlternativeIcon #fIcon
Fs0 f11 109 256 208 256 #arcP
Fs0 f8 expr in #txt
Fs0 f8 outCond ivy.session.isSessionUserUnknown() #txt
Fs0 f8 240 256 344 256 #arcP
Fs0 f118 actionTable 'out=in;
' #txt
Fs0 f118 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator.navigateToPortalHome();' #txt
Fs0 f118 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect to home page</name>
    </language>
</elementInfo>
' #txt
Fs0 f118 336 298 128 44 -60 -8 #rect
Fs0 f118 @|StepIcon #fIcon
Fs0 f12 expr in #txt
Fs0 f12 224 272 336 320 #arcP
Fs0 f12 1 224 320 #addKink
Fs0 f12 1 0.24087665727845922 0 0 #arcLabel
>Proto Fs0 .type ch.ivy.addon.portalkit.singleapp.general.ForgotPassword.ForgotPasswordData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f7 mainOut f9 tail #connect
Fs0 f9 head f6 mainIn #connect
Fs0 f18 mainOut f11 tail #connect
Fs0 f11 head f10 in #connect
Fs0 f10 out f8 tail #connect
Fs0 f8 head f7 mainIn #connect
Fs0 f10 out f12 tail #connect
Fs0 f12 head f118 mainIn #connect
