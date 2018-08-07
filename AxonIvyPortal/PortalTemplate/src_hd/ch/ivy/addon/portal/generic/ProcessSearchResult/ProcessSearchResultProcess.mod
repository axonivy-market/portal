[Ivy]
[>Created: Fri Jan 06 10:43:07 ICT 2017]
154B95001B0C4DDC 3.18 #module
>Proto >Proto Collection #zClass
Ps0 ProcessSearchResultProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @GridStep f4 '' #zField
Ps0 @RichDialogProcessEnd f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 ProcessSearchResultProcess #zField
Ps0 f0 guid 154B95001C43D8E5 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 53 85 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f1 53 213 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 64 107 64 213 #arcP
Ps0 f3 guid 15971DE77D79DF39 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f3 method startProcess(String) #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String link> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 inParameterMapAction 'out.processStartLink=param.link;
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startProcess(String)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 213 85 22 22 14 0 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 actionDecl 'ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData out;
' #txt
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.processStartLink);' #txt
Ps0 f4 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f4 206 148 36 24 20 -2 #rect
Ps0 f4 @|StepIcon #fIcon
Ps0 f5 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f5 213 213 22 22 14 0 #rect
Ps0 f5 @|RichDialogProcessEndIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 224 107 224 148 #arcP
Ps0 f7 expr out #txt
Ps0 f7 224 172 224 213 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
Ps0 f4 mainOut f7 tail #connect
Ps0 f7 head f5 mainIn #connect
