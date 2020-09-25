[Ivy]
16F7F951DB019946 9.2.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressManagementProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdInit f0 '' #zField
Es0 @PushWFArc f12 '' #zField
>Proto Es0 Es0 ExpressManagementProcess #zField
Es0 f1 267 83 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f0 guid 16F82C1E20E8A359 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 83 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f12 109 96 267 96 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.component.ExpressManagement.ExpressManagementData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f12 tail #connect
Es0 f12 head f1 mainIn #connect
