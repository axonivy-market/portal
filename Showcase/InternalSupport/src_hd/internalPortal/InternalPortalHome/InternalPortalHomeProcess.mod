[Ivy]
14C798D58C630E2D 3.28 #module
>Proto >Proto Collection #zClass
As0 InternalPortalHomeProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f0 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @UdEvent f3 '' #zField
As0 @UdExitEnd f4 '' #zField
As0 @PushWFArc f5 '' #zField
>Proto As0 As0 InternalPortalHomeProcess #zField
As0 f0 guid 14BEE52D5FCBA4F5 #txt
As0 f0 method start() #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
As0 f0 outParameterDecl '<> result;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f3 guid 14BEE52D606DD020 #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 83 147 26 26 -15 12 #rect
As0 f3 @|UdEventIcon #fIcon
As0 f4 211 147 26 26 0 12 #rect
As0 f4 @|UdExitEndIcon #fIcon
As0 f5 expr out #txt
As0 f5 109 160 211 160 #arcP
>Proto As0 .type internalPortal.InternalPortalHome.InternalPortalHomeData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect
