[Ivy]
[>Created: Fri Feb 06 08:50:34 ICT 2015]
14BCA85F77BEC9C2 3.17 #module
>Proto >Proto Collection #zClass
Ts0 ProcessStartsHomeProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 ProcessStartsHomeProcess #zField
Ts0 f0 guid 14AF113712219DA1 #txt
Ts0 f0 type ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome.ProcessStartsHomeData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 86 54 20 20 13 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome.ProcessStartsHomeData #txt
Ts0 f1 86 150 20 20 13 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f6 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome.ProcessStartsHomeData out;
' #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'in.rendered=true;
in.watermark="Enter keyword for searching";
' #txt
Ts0 f6 type ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome.ProcessStartsHomeData #txt
Ts0 f6 78 92 36 24 20 -2 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 96 74 96 92 #arcP
Ts0 f2 expr out #txt
Ts0 f2 96 116 96 150 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome.ProcessStartsHomeData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
