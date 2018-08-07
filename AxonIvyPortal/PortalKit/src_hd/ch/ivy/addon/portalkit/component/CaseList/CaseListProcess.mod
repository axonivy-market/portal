[Ivy]
[>Created: Tue Apr 12 10:56:22 ICT 2016]
1540466DCE137181 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseListProcess Big #zClass
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
Cs0 @CallSub f80 '' #zField
Cs0 @RichDialogProcessEnd f23 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @RichDialogMethodStart f7 '' #zField
Cs0 @CallSub f25 '' #zField
Cs0 @RichDialogProcessEnd f14 '' #zField
Cs0 @RichDialogMethodStart f10 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @GridStep f18 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogInitStart f2 '' #zField
Cs0 @RichDialogInitStart f12 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @Alternative f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f13 '' #zField
>Proto Cs0 Cs0 CaseListProcess #zField
Cs0 f80 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f80 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f80 doCall true #txt
Cs0 f80 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f80 requestMappingAction 'param.server=in.selectedCase.server;
param.caseId=in.selectedCase.id;
' #txt
Cs0 f80 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseList.CaseListData out;
' #txt
Cs0 f80 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find documents</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f80 326 428 36 24 -28 14 #rect
Cs0 f80 @|CallSubIcon #fIcon
Cs0 f23 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f23 445 341 22 22 14 0 #rect
Cs0 f23 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.component.CaseList.CaseListData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import org.primefaces.model.TreeNode;
import ch.ivy.addon.portalkit.util.BeanUtils;

in.selectedNode = BeanUtils.invokeBeanMethodViaMethodExpression("#{mainMenuBean.getSelectedNode()}") as TreeNode;
CaseNode selectedCategory = in.selectedNode.getData() as CaseNode;
selectedCategory.cases.remove(in.selectedCase);' #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f6 326 340 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f7 guid 15404EAB0E4F4839 #txt
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f7 method destroyCase() #txt
Cs0 f7 disableUIEvents false #txt
Cs0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f7 outParameterDecl '<> result;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyCase()</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 53 341 22 22 14 0 #rect
Cs0 f7 @|RichDialogMethodStartIcon #fIcon
Cs0 f25 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f25 processCall MultiPortal/CaseService:destroyCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f25 doCall true #txt
Cs0 f25 requestActionDecl '<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param;
' #txt
Cs0 f25 requestMappingAction 'param.remoteCase=in.selectedCase;
' #txt
Cs0 f25 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseList.CaseListData out;
' #txt
Cs0 f25 responseMappingAction 'out=in;
' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f25 182 340 36 24 -34 -30 #rect
Cs0 f25 @|CallSubIcon #fIcon
Cs0 f14 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f14 445 429 22 22 14 0 #rect
Cs0 f14 @|RichDialogProcessEndIcon #fIcon
Cs0 f10 guid 15404EAB0DEEAEC3 #txt
Cs0 f10 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f10 method documentsOfCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f10 disableUIEvents false #txt
Cs0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f10 inParameterMapAction 'out.selectedCase=param.remoteCase;
' #txt
Cs0 f10 outParameterDecl '<java.util.ArrayList<java.lang.Object> documents> result;
' #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>documentsOfCase(RemoteCase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f10 53 429 22 22 14 0 #rect
Cs0 f10 @|RichDialogMethodStartIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 75 352 182 352 #arcP
Cs0 f8 expr out #txt
Cs0 f8 218 352 326 352 #arcP
Cs0 f9 expr out #txt
Cs0 f9 362 352 445 352 #arcP
Cs0 f11 expr out #txt
Cs0 f11 75 440 326 440 #arcP
Cs0 f15 expr out #txt
Cs0 f15 362 440 445 440 #arcP
Cs0 f18 actionDecl 'ch.ivy.addon.portalkit.component.CaseList.CaseListData out;
' #txt
Cs0 f18 actionTable 'out=in;
' #txt
Cs0 f18 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.TreeNodeType;

if(in.selectedNode.type.equals(TreeNodeType.CASES)) {
	out.category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
}
if(in.selectedNode.type.equals(TreeNodeType.CASES_MY_CASES)){
		out.category.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myCases");
}
if(in.selectedNode.type.equals(TreeNodeType.CASES_ALL_CASES)){
		out.category.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/allCases");
}' #txt
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update selected category
if language changed</name>
        <nameStyle>44,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 326 44 36 24 -53 17 #rect
Cs0 f18 @|StepIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f1 507 139 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 guid 15408845ABA2F230 #txt
Cs0 f2 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f2 method startFromCaseSearching(String,java.lang.Long,java.lang.Long) #txt
Cs0 f2 disableUIEvents true #txt
Cs0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Long selectedCaseId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Cs0 f2 inParameterMapAction 'out.caseSearchCriteria.keyword=param.keyword;
out.category.value=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/case"), param.keyword));
out.selectedCase.id=param.selectedCaseId;
out.serverId=param.serverId;
' #txt
Cs0 f2 outParameterDecl '<> result;
' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromCaseSearching(String,Long,Long)</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 53 141 22 22 14 12 #rect
Cs0 f2 @|RichDialogInitStartIcon #fIcon
Cs0 f12 guid 15408845AB89CB22 #txt
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f12 method startFromLinkedTask(java.lang.Long,java.lang.Long) #txt
Cs0 f12 disableUIEvents true #txt
Cs0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Cs0 f12 inParameterMapAction 'out.selectedCase.id=param.caseId;
out.serverId=param.serverId;
' #txt
Cs0 f12 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
out.category.value = TaskUtils.checkReadAllCasesPermission()? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/allCases"): ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");' #txt
Cs0 f12 outParameterDecl '<> result;
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromLinkedTask(Long,Long)</name>
    </language>
</elementInfo>
' #txt
Cs0 f12 53 237 22 22 14 0 #rect
Cs0 f12 @|RichDialogInitStartIcon #fIcon
Cs0 f0 guid 15408845AB9FF8BA #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f0 method startFromMainMenu(org.primefaces.model.TreeNode) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.model.TreeNode category> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.category=param.category.getData() as ch.ivy.addon.portalkit.bo.MainMenuNode;
out.selectedNode=param.category;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromMainMenu(TreeNode)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 52 44 24 24 17 14 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
Cs0 f3 442 138 28 28 14 0 #rect
Cs0 f3 @|AlternativeIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 75 152 442 152 #arcP
Cs0 f4 0 0.8500142733560037 0 0 #arcLabel
Cs0 f5 expr in #txt
Cs0 f5 470 152 507 152 #arcP
Cs0 f16 expr out #txt
Cs0 f16 75 248 456 166 #arcP
Cs0 f16 1 456 248 #addKink
Cs0 f16 1 0.24724765070410185 0 0 #arcLabel
Cs0 f17 expr out #txt
Cs0 f17 76 56 326 56 #arcP
Cs0 f13 expr out #txt
Cs0 f13 362 56 456 138 #arcP
Cs0 f13 1 456 56 #addKink
Cs0 f13 1 0.0025787957332305046 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseList.CaseListData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f7 mainOut f24 tail #connect
Cs0 f24 head f25 mainIn #connect
Cs0 f25 mainOut f8 tail #connect
Cs0 f8 head f6 mainIn #connect
Cs0 f6 mainOut f9 tail #connect
Cs0 f9 head f23 mainIn #connect
Cs0 f10 mainOut f11 tail #connect
Cs0 f11 head f80 mainIn #connect
Cs0 f80 mainOut f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f2 mainOut f4 tail #connect
Cs0 f4 head f3 in #connect
Cs0 f3 out f5 tail #connect
Cs0 f5 head f1 mainIn #connect
Cs0 f12 mainOut f16 tail #connect
Cs0 f16 head f3 in #connect
Cs0 f0 mainOut f17 tail #connect
Cs0 f17 head f18 mainIn #connect
Cs0 f18 mainOut f13 tail #connect
Cs0 f13 head f3 in #connect
