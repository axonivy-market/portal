[Ivy]
[>Created: Fri Jul 31 14:33:40 ICT 2015]
14CE96E80B89A88F 3.17 #module
>Proto >Proto Collection #zClass
Ls0 SetLanguageProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ls0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ls0 @TextInP .resExport .resExport #zField
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @AnnotationInP-0n ai ai #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @RichDialogInitStart f0 '' #zField
Ls0 @RichDialogProcessEnd f1 '' #zField
Ls0 @GridStep f8 '' #zField
Ls0 @PushWFArc f3 '' #zField
Ls0 @PushWFArc f2 '' #zField
>Proto Ls0 Ls0 SetLanguageProcess #zField
Ls0 f0 guid 14739D9F00572FE2 #txt
Ls0 f0 type ch.ivy.addon.portalkit.singleapp.general.SetLanguage.SetLanguageData #txt
Ls0 f0 method start() #txt
Ls0 f0 disableUIEvents true #txt
Ls0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ls0 f0 outParameterDecl '<> result;
' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f0 86 54 20 20 13 0 #rect
Ls0 f0 @|RichDialogInitStartIcon #fIcon
Ls0 f1 type ch.ivy.addon.portalkit.singleapp.general.SetLanguage.SetLanguageData #txt
Ls0 f1 86 182 20 20 13 0 #rect
Ls0 f1 @|RichDialogProcessEndIcon #fIcon
Ls0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.SetLanguage.SetLanguageData out;
' #txt
Ls0 f8 actionTable 'out=in;
' #txt
Ls0 f8 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
UserUtils.setLanguague();' #txt
Ls0 f8 type ch.ivy.addon.portalkit.singleapp.general.SetLanguage.SetLanguageData #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f8 78 124 36 24 20 -2 #rect
Ls0 f8 @|StepIcon #fIcon
Ls0 f3 expr out #txt
Ls0 f3 96 74 96 124 #arcP
Ls0 f2 expr out #txt
Ls0 f2 96 148 96 182 #arcP
>Proto Ls0 .type ch.ivy.addon.portalkit.singleapp.general.SetLanguage.SetLanguageData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f3 tail #connect
Ls0 f3 head f8 mainIn #connect
Ls0 f8 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
