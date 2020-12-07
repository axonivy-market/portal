[Ivy]
175F92F085BD11E8 9.2.0 #module
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
>Proto Fs0 Fs0 ForgotPasswordProcess #zField
Fs0 f0 guid 175F92F086549B43 #txt
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
Fs0 f3 guid 175F92F086F35319 #txt
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
>Proto Fs0 .type ch.ivy.addon.portal.generic.ForgotPassword.ForgotPasswordData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
