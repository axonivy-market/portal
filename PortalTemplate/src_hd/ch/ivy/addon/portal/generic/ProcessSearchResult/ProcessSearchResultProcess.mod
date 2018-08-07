[Ivy]
[>Created: Mon May 16 18:32:14 ICT 2016]
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
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialog f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
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
Ps0 f3 guid 154B9533BB1661C3 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 actionCode 'import org.primefaces.component.commandlink.CommandLink;
CommandLink commandLink = event.getSource()as CommandLink;

out.keyword = commandLink.getAttributes().get("keyword") as String;
out.processStartLink = commandLink.getAttributes().get("processStartLink") as String;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToProcessList</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 229 85 22 22 14 0 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 targetWindow NEW:card: #txt
Ps0 f4 targetDisplay TOP #txt
Ps0 f4 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f4 startMethod start(String,String) #txt
Ps0 f4 type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
Ps0 f4 requestActionDecl '<String keyword, String processStartLink> param;' #txt
Ps0 f4 requestMappingAction 'param.keyword=in.keyword;
param.processStartLink=in.processStartLink;
' #txt
Ps0 f4 responseActionDecl 'ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData out;
' #txt
Ps0 f4 responseMappingAction 'out=in;
' #txt
Ps0 f4 windowConfiguration '* ' #txt
Ps0 f4 isAsynch false #txt
Ps0 f4 isInnerRd true #txt
Ps0 f4 userContext '* ' #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>list process dialog</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f4 176 164 128 56 -45 -8 #rect
Ps0 f4 @|RichDialogIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 240 107 240 164 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.ProcessSearchResult.ProcessSearchResultData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
