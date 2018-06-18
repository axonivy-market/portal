[Ivy]
164109DD23C353D3 3.23 #module
>Proto >Proto Collection #zClass
Ts0 TaskCategoryFilterProcess Big #zClass
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
Ts0 @RichDialogProcessStart f3 '' #zField
Ts0 @RichDialogEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @CallSub f21 '' #zField
Ts0 @GridStep f7 '' #zField
Ts0 @CallSub f25 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @GridStep f2 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
>Proto Ts0 Ts0 TaskCategoryFilterProcess #zField
Ts0 f0 guid 164109DD26F2EC14 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
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
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f1 867 51 26 26 0 12 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f3 guid 164109DD28B26D1F #txt
Ts0 f3 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f3 actionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData out;
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
Ts0 f3 83 147 26 26 -15 12 #rect
Ts0 f3 @|RichDialogProcessStartIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f4 guid 164109DD28C5D30F #txt
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|RichDialogEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 160 211 160 #arcP
Ts0 f21 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f21 processCall MultiPortal/TaskService:findCategories(String,String,List<String>,Long) #txt
Ts0 f21 doCall true #txt
Ts0 f21 requestActionDecl '<java.lang.String jsonQuery,java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ts0 f21 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ts0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData out;
' #txt
Ts0 f21 responseMappingAction 'out=in;
out.allTaskCategories=result.categories;
out.errors=result.errors;
' #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all tasks of all users</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f21 533 52 36 24 -39 19 #rect
Ts0 f21 @|CallSubIcon #fIcon
Ts0 f7 actionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData out;
' #txt
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
String appName = SecurityServiceUtils.getApplicationNameFromSession();
if (#appName is initialized) {
	in.involvedApplications = [appName];
}' #txt
Ts0 f7 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set involved applications</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f7 159 40 160 48 -66 -9 #rect
Ts0 f7 @|StepIcon #fIcon
Ts0 f25 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f25 processCall 'Functional Processes/BuildTaskJsonQuery:buildTaskJsonQuery()' #txt
Ts0 f25 doCall true #txt
Ts0 f25 requestActionDecl '<> param;
' #txt
Ts0 f25 responseActionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData out;
' #txt
Ts0 f25 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Ts0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskJsonQuery</name>
    </language>
</elementInfo>
' #txt
Ts0 f25 387 52 36 24 -51 18 #rect
Ts0 f25 @|CallSubIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 109 64 159 64 #arcP
Ts0 f2 actionDecl 'ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData out;
' #txt
Ts0 f2 actionTable 'out=in;
' #txt
Ts0 f2 actionCode 'import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import org.primefaces.model.CheckboxTreeNode;

in.root = TaskTreeUtils.buildTaskCategoryCheckboxTree(in.allTaskCategories);' #txt
Ts0 f2 type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
Ts0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build Category Tree</name>
    </language>
</elementInfo>
' #txt
Ts0 f2 664 42 112 44 -53 -8 #rect
Ts0 f2 @|StepIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 319 64 387 64 #arcP
Ts0 f9 expr out #txt
Ts0 f9 423 64 533 64 #arcP
Ts0 f10 expr out #txt
Ts0 f10 569 64 664 64 #arcP
Ts0 f11 expr out #txt
Ts0 f11 776 64 867 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.filter.TaskCategoryFilter.TaskCategoryFilterData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f0 mainOut f6 tail #connect
Ts0 f6 head f7 mainIn #connect
Ts0 f7 mainOut f8 tail #connect
Ts0 f8 head f25 mainIn #connect
Ts0 f25 mainOut f9 tail #connect
Ts0 f9 head f21 mainIn #connect
Ts0 f21 mainOut f10 tail #connect
Ts0 f10 head f2 mainIn #connect
Ts0 f2 mainOut f11 tail #connect
Ts0 f11 head f1 mainIn #connect
