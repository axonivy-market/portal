[Ivy]
[>Created: Wed Oct 12 11:51:54 ICT 2016]
15498A84F89ACDE7 3.18 #module
>Proto >Proto Collection #zClass
Ts0 TaskSearchResultProcess Big #zClass
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
Ts0 @CallSub f4 '' #zField
Ts0 @RichDialogProcessStart f6 '' #zField
Ts0 @GridStep f23 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @PushWFArc f5 '' #zField
>Proto Ts0 Ts0 TaskSearchResultProcess #zField
Ts0 f0 guid 15498A84FB37527F #txt
Ts0 f0 type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
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
        <name>start(String,RemoteTask)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f4 type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
Ts0 f4 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Ts0 f4 doCall true #txt
Ts0 f4 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Ts0 f4 requestMappingAction 'param.taskView=in.view;
' #txt
Ts0 f4 responseActionDecl 'ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData out;
' #txt
Ts0 f4 responseMappingAction 'out=in;
' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f4 278 220 36 24 20 -2 #rect
Ts0 f4 @|CallSubIcon #fIcon
Ts0 f6 guid 15498BCDDAD82718 #txt
Ts0 f6 type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
Ts0 f6 actionDecl 'ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData out;
' #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import org.primefaces.component.commandlink.CommandLink;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import javax.faces.event.ActionEvent;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import java.util.Arrays;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;

CommandLink commandLink = event.getSource() as CommandLink;

String keyword = commandLink.getAttributes().get("keyword") as String;
RemoteTask foundTask = commandLink.getAttributes().get("foundTask") as RemoteTask;

MainMenuNode category = new MainMenuNode();
category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/task"), keyword));
String pageTitle = category.value;
TaskLazyDataModel dataModel = new TaskLazyDataModel();

dataModel.setKeyword(keyword);

out.view = TaskView.create().category(category).pageTitle(pageTitle).keyword(keyword).remoteTaskId(foundTask.getId()).serverId(foundTask.applicationRegister.serverId).dataModel(dataModel).createNewTaskView();' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 284 92 24 24 14 0 #rect
Ts0 f6 @|RichDialogProcessStartIcon #fIcon
Ts0 f23 actionDecl 'ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData out;
' #txt
Ts0 f23 actionTable 'out=in;
' #txt
Ts0 f23 actionCode 'import ch.ivy.addon.portalkit.util.MenuUtils;
MenuUtils.clearMenuState();' #txt
Ts0 f23 type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
Ts0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear menu state</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f23 278 156 36 24 20 -2 #rect
Ts0 f23 @|StepIcon #fIcon
Ts0 f3 expr out #txt
Ts0 f3 296 116 296 156 #arcP
Ts0 f5 expr out #txt
Ts0 f5 296 180 296 220 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskSearchResult.TaskSearchResultData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f6 mainOut f3 tail #connect
Ts0 f3 head f23 mainIn #connect
Ts0 f23 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
