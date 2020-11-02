[Ivy]
17501D6F0762D9A7 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LoginProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
>Proto Ls0 Ls0 LoginProcess #zField
Ls0 f0 guid 17501D6F0923F1F3 #txt
Ls0 f0 method start(String) #txt
Ls0 f0 inParameterDecl '<String callbackUrl> param;' #txt
Ls0 f0 inParameterMapAction 'out.callbackUrl=param.callbackUrl;
' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -15 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 211 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f2 109 64 211 64 #arcP
>Proto Ls0 .type ch.ivy.addon.portal.generic.Login.LoginData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
