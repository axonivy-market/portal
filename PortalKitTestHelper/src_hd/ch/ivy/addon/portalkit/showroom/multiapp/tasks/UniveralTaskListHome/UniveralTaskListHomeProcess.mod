[Ivy]
[>Created: Wed Jul 30 12:04:57 CEST 2014]
14BCA8BA265F3A28 3.17 #module
>Proto >Proto Collection #zClass
Hs0 UniveralTaskListHomeProcess Big #zClass
Hs0 RD #cInfo
Hs0 #process
Hs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Hs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Hs0 @TextInP .resExport .resExport #zField
Hs0 @TextInP .type .type #zField
Hs0 @TextInP .processKind .processKind #zField
Hs0 @AnnotationInP-0n ai ai #zField
Hs0 @TextInP .xml .xml #zField
Hs0 @TextInP .responsibility .responsibility #zField
Hs0 @RichDialogInitStart f0 '' #zField
Hs0 @RichDialogProcessEnd f1 '' #zField
Hs0 @PushWFArc f2 '' #zField
Hs0 @RichDialogProcessStart f3 '' #zField
Hs0 @RichDialogProcessEnd f4 '' #zField
Hs0 @PushWFArc f5 '' #zField
>Proto Hs0 Hs0 UniveralTaskListHomeProcess #zField
Hs0 f0 guid 146F62F680D3DE3E #txt
Hs0 f0 type ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData #txt
Hs0 f0 method start() #txt
Hs0 f0 disableUIEvents true #txt
Hs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Hs0 f0 outParameterDecl '<> result;
' #txt
Hs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Hs0 f0 86 54 20 20 13 0 #rect
Hs0 f0 @|RichDialogInitStartIcon #fIcon
Hs0 f1 type ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData #txt
Hs0 f1 86 150 20 20 13 0 #rect
Hs0 f1 @|RichDialogProcessEndIcon #fIcon
Hs0 f2 expr out #txt
Hs0 f2 96 74 96 150 #arcP
Hs0 f3 guid 14786BAD741FB5CA #txt
Hs0 f3 type ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData #txt
Hs0 f3 actionDecl 'ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData out;
' #txt
Hs0 f3 actionTable 'out=in;
' #txt
Hs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onload</name>
    </language>
</elementInfo>
' #txt
Hs0 f3 195 54 20 20 13 0 #rect
Hs0 f3 @|RichDialogProcessStartIcon #fIcon
Hs0 f4 type ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData #txt
Hs0 f4 195 150 20 20 13 0 #rect
Hs0 f4 @|RichDialogProcessEndIcon #fIcon
Hs0 f5 205 74 205 150 #arcP
>Proto Hs0 .type ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome.UniveralTaskListHomeData #txt
>Proto Hs0 .processKind HTML_DIALOG #txt
>Proto Hs0 -8 -8 16 16 16 26 #rect
>Proto Hs0 '' #fIcon
Hs0 f0 mainOut f2 tail #connect
Hs0 f2 head f1 mainIn #connect
Hs0 f3 mainOut f5 tail #connect
Hs0 f5 head f4 mainIn #connect
