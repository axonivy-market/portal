[Ivy]
[>Created: Fri Jan 22 18:00:01 ICT 2016]
15268544244B73B8 3.18 #module
>Proto >Proto Collection #zClass
Es0 ErrorPageProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Es0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Es0 @TextInP .resExport .resExport #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @RichDialogInitStart f0 '' #zField
Es0 @RichDialogProcessEnd f1 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @RichDialogProcessStart f3 '' #zField
Es0 @RichDialogEnd f4 '' #zField
Es0 @PushWFArc f5 '' #zField
>Proto Es0 Es0 ErrorPageProcess #zField
Es0 f0 guid 1526854426E48AD9 #txt
Es0 f0 type ch.ivy.addon.portal.error.ErrorPage.ErrorPageData #txt
Es0 f0 method start(String) #txt
Es0 f0 disableUIEvents true #txt
Es0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String errorCode> param = methodEvent.getInputArguments();
' #txt
Es0 f0 inParameterMapAction 'out.errorCode=param.errorCode;
' #txt
Es0 f0 outParameterDecl '<> result;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Es0 f0 53 85 22 22 14 0 #rect
Es0 f0 @|RichDialogInitStartIcon #fIcon
Es0 f1 type ch.ivy.addon.portal.error.ErrorPage.ErrorPageData #txt
Es0 f1 53 213 22 22 14 0 #rect
Es0 f1 @|RichDialogProcessEndIcon #fIcon
Es0 f2 expr out #txt
Es0 f2 64 107 64 213 #arcP
Es0 f3 guid 1526854427B44586 #txt
Es0 f3 type ch.ivy.addon.portal.error.ErrorPage.ErrorPageData #txt
Es0 f3 actionDecl 'ch.ivy.addon.portal.error.ErrorPage.ErrorPageData out;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Es0 f3 149 85 22 22 14 0 #rect
Es0 f3 @|RichDialogProcessStartIcon #fIcon
Es0 f4 type ch.ivy.addon.portal.error.ErrorPage.ErrorPageData #txt
Es0 f4 guid 1526854427B6B5F3 #txt
Es0 f4 149 213 22 22 14 0 #rect
Es0 f4 @|RichDialogEndIcon #fIcon
Es0 f5 expr out #txt
Es0 f5 160 107 160 213 #arcP
>Proto Es0 .type ch.ivy.addon.portal.error.ErrorPage.ErrorPageData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f3 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
