[Ivy]
163FC6A2452CF497 3.23 #module
>Proto >Proto Collection #zClass
Cs0 CaseCategoryFilterProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @CallSub f21 '' #zField
Cs0 @CallSub f25 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f7 '' #zField
>Proto Cs0 Cs0 CaseCategoryFilterProcess #zField
Cs0 f0 guid 163FC6A2487A904F #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f1 803 51 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 163FC6A24A2973B5 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 12 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f4 guid 163FC6A24A2E609B #txt
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 109 160 211 160 #arcP
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import org.primefaces.model.CheckboxTreeNode;

in.root = CaseTreeUtils.buildCaseCategoryCheckboxTree(in.allCaseCategories);' #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f6 632 42 112 44 0 -8 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 744 64 803 64 #arcP
Cs0 f21 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f21 processCall MultiPortal/CaseService:findCaseCategoriesByCriteria(String,List<String>,Long,String) #txt
Cs0 f21 doCall true #txt
Cs0 f21 requestActionDecl '<java.lang.String jsonQuery,java.util.List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Cs0 f21 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Cs0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData out;
' #txt
Cs0 f21 responseMappingAction 'out=in;
out.allCaseCategories=result.caseCategories;
out.errors=result.errors;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all cases of all users</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 520 52 36 24 -64 -51 #rect
Cs0 f21 @|CallSubIcon #fIcon
Cs0 f25 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f25 processCall 'Functional Processes/BuildCaseJsonQuery:buildCaseJsonQuery()' #txt
Cs0 f25 doCall true #txt
Cs0 f25 requestActionDecl '<> param;
' #txt
Cs0 f25 responseActionDecl 'ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData out;
' #txt
Cs0 f25 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildCaseJsonQuery</name>
    </language>
</elementInfo>
' #txt
Cs0 f25 398 52 36 24 -51 18 #rect
Cs0 f25 @|CallSubIcon #fIcon
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
String appName = SecurityServiceUtils.getApplicationNameFromSession();
if (#appName is initialized) {
	in.involvedApplications = [appName];
}' #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set involved applications</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 162 40 160 48 -66 -9 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 322 64 398 64 #arcP
Cs0 f10 expr out #txt
Cs0 f10 434 64 520 64 #arcP
Cs0 f12 expr out #txt
Cs0 f12 109 64 162 64 #arcP
Cs0 f7 expr out #txt
Cs0 f7 556 64 632 64 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.cases.filter.CaseCategoryFilter.CaseCategoryFilterData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f8 mainOut f9 tail #connect
Cs0 f9 head f25 mainIn #connect
Cs0 f25 mainOut f10 tail #connect
Cs0 f10 head f21 mainIn #connect
Cs0 f0 mainOut f12 tail #connect
Cs0 f12 head f8 mainIn #connect
Cs0 f21 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
