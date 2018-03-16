[Ivy]
150D5B63F4513514 3.20 #module
>Proto >Proto Collection #zClass
Ps0 ProcessesProcess Big #zClass
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
Ps0 @RichDialogInitStart f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @RichDialogInitStart f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 ProcessesProcess #zField
Ps0 f0 guid 150D5B63FE18100D #txt
Ps0 f0 type ch.ivy.addon.portal.generic.Processes.ProcessesData #txt
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
Ps0 f0 53 85 22 22 -18 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.Processes.ProcessesData #txt
Ps0 f1 333 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 75 96 333 96 #arcP
Ps0 f3 guid 1531CA90D878577E #txt
Ps0 f3 type ch.ivy.addon.portal.generic.Processes.ProcessesData #txt
Ps0 f3 method start(String,String) #txt
Ps0 f3 disableUIEvents true #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.String processStartLink> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 inParameterMapAction 'out.keyword=param.keyword;
out.processStartLink=param.processStartLink;
out.title=param.#keyword is initialized ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/process"), param.keyword)) : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String, String)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 53 149 22 22 -38 15 #rect
Ps0 f3 @|RichDialogInitStartIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 75 160 344 107 #arcP
Ps0 f4 1 344 160 #addKink
Ps0 f4 0 0.07050411304930455 0 0 #arcLabel
Ps0 f5 guid 15733160DA48A6B3 #txt
Ps0 f5 type ch.ivy.addon.portal.generic.Processes.ProcessesData #txt
Ps0 f5 method startWithMenuState(String) #txt
Ps0 f5 disableUIEvents true #txt
Ps0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String menuState> param = methodEvent.getInputArguments();
' #txt
Ps0 f5 inParameterMapAction 'out.menuState=param.menuState;
' #txt
Ps0 f5 outParameterDecl '<> result;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithMenuState(String)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f5 53 213 22 22 -53 18 #rect
Ps0 f5 @|RichDialogInitStartIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 75 224 344 107 #arcP
Ps0 f6 1 344 224 #addKink
Ps0 f6 0 0.7304918649943876 0 0 #arcLabel
>Proto Ps0 .type ch.ivy.addon.portal.generic.Processes.ProcessesData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f4 tail #connect
Ps0 f4 head f1 mainIn #connect
Ps0 f5 mainOut f6 tail #connect
Ps0 f6 head f1 mainIn #connect
