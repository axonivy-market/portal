[Ivy]
[>Created: Thu Mar 03 14:27:02 ICT 2016]
14C4FF3FCD291EF5 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseListProcess Big #zClass
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
Ps0 @Alternative f8 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @RichDialogMethodStart f5 '' #zField
Ps0 @RichDialogProcessEnd f23 '' #zField
Ps0 @CallSub f25 '' #zField
Ps0 @PushWFArc f24 '' #zField
Ps0 @RichDialogInitStart f2 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @GridStep f4 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @RichDialogMethodStart f10 '' #zField
Ps0 @CallSub f80 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @RichDialogProcessEnd f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
>Proto Ps0 Ps0 PortalCaseListProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f0 method start(org.primefaces.model.TreeNode) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.model.TreeNode category> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.category=param.category.getData() as ch.ivy.addon.portalkit.bo.MainMenuNode;
out.selectedNode=param.category;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TreeNode)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 84 52 24 24 -43 14 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f1 307 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f8 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f8 178 50 28 28 14 0 #rect
Ps0 f8 @|AlternativeIcon #fIcon
Ps0 f13 expr out #txt
Ps0 f13 108 64 178 64 #arcP
Ps0 f5 guid 15312B28C8443766 #txt
Ps0 f5 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f5 method destroyCase() #txt
Ps0 f5 disableUIEvents false #txt
Ps0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f5 outParameterDecl '<> result;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyCase()</name>
    </language>
</elementInfo>
' #txt
Ps0 f5 61 253 22 22 14 0 #rect
Ps0 f5 @|RichDialogMethodStartIcon #fIcon
Ps0 f23 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f23 357 253 22 22 14 0 #rect
Ps0 f23 @|RichDialogProcessEndIcon #fIcon
Ps0 f25 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f25 processCall MultiPortal/CaseService:destroyCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Ps0 f25 doCall true #txt
Ps0 f25 requestActionDecl '<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param;
' #txt
Ps0 f25 requestMappingAction 'param.remoteCase=in.selectedCase;
' #txt
Ps0 f25 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f25 responseMappingAction 'out=in;
' #txt
Ps0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f25 190 252 36 24 -34 -30 #rect
Ps0 f25 @|CallSubIcon #fIcon
Ps0 f24 expr out #txt
Ps0 f24 83 264 190 264 #arcP
Ps0 f2 guid 1531667580A567C2 #txt
Ps0 f2 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f2 method start(String,java.lang.Long,java.lang.Long) #txt
Ps0 f2 disableUIEvents true #txt
Ps0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Long selectedCaseId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ps0 f2 inParameterMapAction 'out.caseSearchCriteria.keyword=param.keyword;
out.category.value=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/case"), param.keyword));
out.selectedCase.id=param.selectedCaseId;
out.serverId=param.serverId;
' #txt
Ps0 f2 outParameterDecl '<> result;
' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Long,Long)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 85 149 22 22 -63 13 #rect
Ps0 f2 @|RichDialogInitStartIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 107 160 192 78 #arcP
Ps0 f9 1 192 160 #addKink
Ps0 f9 1 0.19702763418641026 0 0 #arcLabel
Ps0 f3 expr in #txt
Ps0 f3 206 64 307 64 #arcP
Ps0 f4 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import org.primefaces.model.TreeNode;
import ch.ivy.addon.portalkit.util.BeanUtils;

in.selectedNode = BeanUtils.invokeBeanMethodViaMethodExpression("#{mainMenuBean.getSelectedNode()}") as TreeNode;
CaseNode selectedCategory = in.selectedNode.getData() as CaseNode;
selectedCategory.cases.remove(in.selectedCase);' #txt
Ps0 f4 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f4 270 252 36 24 20 -2 #rect
Ps0 f4 @|StepIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 226 264 270 264 #arcP
Ps0 f7 expr out #txt
Ps0 f7 306 264 357 264 #arcP
Ps0 f10 guid 1533B44BA95EB2F8 #txt
Ps0 f10 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f10 method documentsOfCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Ps0 f10 disableUIEvents false #txt
Ps0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Ps0 f10 inParameterMapAction 'out.selectedCase=param.remoteCase;
' #txt
Ps0 f10 outParameterDecl '<java.util.ArrayList<java.lang.Object> documents> result;
' #txt
Ps0 f10 outParameterMapAction 'result.documents=in.documents;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>documentsOfCase(RemoteCase)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f10 61 341 22 22 14 0 #rect
Ps0 f10 @|RichDialogMethodStartIcon #fIcon
Ps0 f80 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f80 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ps0 f80 doCall true #txt
Ps0 f80 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ps0 f80 requestMappingAction 'param.server=in.selectedCase.server;
param.caseId=in.selectedCase.id;
' #txt
Ps0 f80 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f80 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ps0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find documents</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f80 302 340 36 24 -28 14 #rect
Ps0 f80 @|CallSubIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 83 352 302 352 #arcP
Ps0 f14 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f14 389 341 22 22 14 0 #rect
Ps0 f14 @|RichDialogProcessEndIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 338 352 389 352 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f13 tail #connect
Ps0 f13 head f8 in #connect
Ps0 f5 mainOut f24 tail #connect
Ps0 f24 head f25 mainIn #connect
Ps0 f2 mainOut f9 tail #connect
Ps0 f9 head f8 in #connect
Ps0 f8 out f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f25 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
Ps0 f4 mainOut f7 tail #connect
Ps0 f7 head f23 mainIn #connect
Ps0 f10 mainOut f11 tail #connect
Ps0 f11 head f80 mainIn #connect
Ps0 f80 mainOut f15 tail #connect
Ps0 f15 head f14 mainIn #connect
