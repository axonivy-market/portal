[Ivy]
15DC1795219AF4F0 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TaskResponsibleFilterProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogProcessStart f3 '' #zField
Ts0 @RichDialogEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @RichDialogProcessEnd f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
>Proto Ts0 Ts0 TaskResponsibleFilterProcess #zField
Ts0 f0 guid 15AE9A0BAA4D09B7 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
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
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
Ts0 f1 245 85 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 75 96 245 96 #arcP
Ts0 f3 guid 15AE9A0BAC670820 #txt
Ts0 f3 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
Ts0 f3 actionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData out;
' #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 53 149 22 22 14 0 #rect
Ts0 f3 @|RichDialogProcessStartIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
Ts0 f4 guid 15AE9A0BAC654FD0 #txt
Ts0 f4 245 149 22 22 14 0 #rect
Ts0 f4 @|RichDialogEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 75 160 245 160 #arcP
Ts0 f6 guid 15FB4A9997759979 #txt
Ts0 f6 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
Ts0 f6 method autoCompleteForResponsible(String) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String query> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f6 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.bo.RemoteSecurityMember> responsibleList> result;
' #txt
Ts0 f6 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.addon.portalkit.bo.RemoteUser;

result.responsibleList = UserUtils.filterSecurityMembers(in.responsibles, in.queryAutoComplete);' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForResponsible(String)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 51 211 26 26 -103 15 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
Ts0 f7 243 211 26 26 0 12 #rect
Ts0 f7 @|RichDialogProcessEndIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 77 224 243 224 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.filter.TaskResponsibleFilter.TaskResponsibleFilterData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
