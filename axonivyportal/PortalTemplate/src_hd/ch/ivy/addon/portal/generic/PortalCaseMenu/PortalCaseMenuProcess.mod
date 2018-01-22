[Ivy]
1610C9DBEDBBF46E 3.20 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseMenuProcess Big #zClass
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
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @CallSub f16 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @CallSub f21 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @CallSub f38 '' #zField
Ps0 @RichDialogProcessStart f12 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @Alternative f10 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @Alternative f22 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @CallSub f25 '' #zField
Ps0 @PushWFArc f26 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @CallSub f33 '' #zField
Ps0 @PushWFArc f34 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @PushWFArc f20 '' #zField
>Proto Ps0 Ps0 PortalCaseMenuProcess #zField
Ps0 f0 guid 156F869FC6B3D9ED #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
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
Ps0 f0 45 85 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f1 261 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 67 96 261 96 #arcP
Ps0 f3 guid 156F86D9BA2F2903 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f3 method findCaseCategory() #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCaseCategory()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 45 189 22 22 -53 13 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f4 915 187 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f16 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f16 processCall MultiPortal/CaseService:findCaseCategoriesByCriteria(String,List<String>,Long,String) #txt
Ps0 f16 doCall true #txt
Ps0 f16 requestActionDecl '<java.lang.String jsonQuery,java.util.List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Ps0 f16 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
param.userName=in.loginUser;
' #txt
Ps0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f16 responseMappingAction 'out=in;
out.errors=result.errors;
out.myCaseCategories=result.caseCategories;
' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
my cases</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 838 188 36 24 -49 14 #rect
Ps0 f16 @|CallSubIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
in.loginUser = ivy.session.getSessionUserName();
in.hasReadAllCasesPermisson = CaseUtils.checkReadAllCasesPermission();' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 304 176 128 48 -45 -8 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f21 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f21 processCall MultiPortal/CaseService:findCaseCategoriesByCriteria(String,List<String>,Long,String) #txt
Ps0 f21 doCall true #txt
Ps0 f21 requestActionDecl '<java.lang.String jsonQuery,java.util.List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Ps0 f21 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f21 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f21 responseMappingAction 'out=in;
out.allCaseCategories=result.caseCategories;
out.errors=result.errors;
' #txt
Ps0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all cases of all users</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f21 678 140 36 24 -64 -51 #rect
Ps0 f21 @|CallSubIcon #fIcon
Ps0 f13 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import java.util.Arrays;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.util.MenuUtils;
import ch.ivy.addon.portalkit.bean.MenuStateBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.enums.CaseAssigneeType;

in.selectedMenuItem = in.selectedNode.getData() as MainMenuNode;
CaseNode categoryMenu = in.selectedMenuItem as CaseNode;

in.dataModel.setCategory(categoryMenu.categoryRawPath);

if(in.selectedNode.type.startsWith(TreeNodeType.CASES_ALL_CASES)){
	in.dataModel.setCaseAssigneeType(CaseAssigneeType.ALL);
	in.hasReadAllCasesPermisson = CaseUtils.checkReadAllCasesPermission();
	in.dataModel.setIgnoreInvolvedUser(in.hasReadAllCasesPermisson);
} else if(in.selectedNode.type.startsWith(TreeNodeType.CASES_MY_CASES)) {
	in.dataModel.setCaseAssigneeType(CaseAssigneeType.USER);
	in.dataModel.setIgnoreInvolvedUser(false);
}

in.caseView = CaseView.create().category(categoryMenu).dataModel(in.dataModel).withTitle(categoryMenu.value).buildNewView();' #txt
Ps0 f13 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, 
case view</name>
        <nameStyle>28,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f13 294 340 36 24 -47 14 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f38 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f38 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Ps0 f38 doCall true #txt
Ps0 f38 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Ps0 f38 requestMappingAction 'param.view=in.caseView;
' #txt
Ps0 f38 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f38 responseMappingAction 'out=in;
' #txt
Ps0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
    </language>
</elementInfo>
' #txt
Ps0 f38 394 330 112 44 -49 -8 #rect
Ps0 f38 @|CallSubIcon #fIcon
Ps0 f12 guid 156FDAF210A2DE69 #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadCase</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 45 341 22 22 -27 13 #rect
Ps0 f12 @|RichDialogProcessStartIcon #fIcon
Ps0 f7 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
String appName = SecurityServiceUtils.getApplicationNameFromSession();
if (#appName is initialized) {
	in.involvedApplications = [appName];
}' #txt
Ps0 f7 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set involved applications</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f7 120 176 160 48 -66 -9 #rect
Ps0 f7 @|StepIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 67 200 120 200 #arcP
Ps0 f17 expr out #txt
Ps0 f17 280 200 304 200 #arcP
Ps0 f10 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f10 594 186 28 28 14 0 #rect
Ps0 f10 @|AlternativeIcon #fIcon
Ps0 f5 expr in #txt
Ps0 f5 outCond in.hasReadAllCasesPermisson #txt
Ps0 f5 608 186 678 152 #arcP
Ps0 f5 1 608 152 #addKink
Ps0 f5 1 0.0989409665044952 0 0 #arcLabel
Ps0 f22 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f22 786 186 28 28 14 0 #rect
Ps0 f22 @|AlternativeIcon #fIcon
Ps0 f11 expr in #txt
Ps0 f11 814 200 838 200 #arcP
Ps0 f25 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f25 processCall 'Functional Processes/BuildCaseJsonQuery:buildCaseJsonQuery()' #txt
Ps0 f25 doCall true #txt
Ps0 f25 requestActionDecl '<> param;
' #txt
Ps0 f25 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f25 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Ps0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildCaseJsonQuery</name>
    </language>
</elementInfo>
' #txt
Ps0 f25 492 188 36 24 -51 18 #rect
Ps0 f25 @|CallSubIcon #fIcon
Ps0 f26 expr out #txt
Ps0 f26 432 200 492 200 #arcP
Ps0 f14 expr out #txt
Ps0 f14 528 200 594 200 #arcP
Ps0 f33 type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
Ps0 f33 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Ps0 f33 doCall true #txt
Ps0 f33 requestActionDecl '<> param;
' #txt
Ps0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f33 responseMappingAction 'out=in;
out.dataModel=result.caseDataModel;
' #txt
Ps0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeCaseDataModel</name>
    </language>
</elementInfo>
' #txt
Ps0 f33 112 330 144 44 -66 -8 #rect
Ps0 f33 @|CallSubIcon #fIcon
Ps0 f34 expr out #txt
Ps0 f34 67 352 112 352 #arcP
Ps0 f18 expr out #txt
Ps0 f18 256 352 294 352 #arcP
Ps0 f8 expr in #txt
Ps0 f8 622 200 786 200 #arcP
Ps0 f6 expr out #txt
Ps0 f6 874 200 915 200 #arcP
Ps0 f19 expr out #txt
Ps0 f19 714 152 800 186 #arcP
Ps0 f19 1 800 152 #addKink
Ps0 f19 0 0.627266316724181 0 0 #arcLabel
Ps0 f20 expr out #txt
Ps0 f20 330 352 394 352 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f15 tail #connect
Ps0 f15 head f7 mainIn #connect
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f9 mainIn #connect
Ps0 f10 out f5 tail #connect
Ps0 f5 head f21 mainIn #connect
Ps0 f22 out f11 tail #connect
Ps0 f11 head f16 mainIn #connect
Ps0 f9 mainOut f26 tail #connect
Ps0 f26 head f25 mainIn #connect
Ps0 f25 mainOut f14 tail #connect
Ps0 f14 head f10 in #connect
Ps0 f12 mainOut f34 tail #connect
Ps0 f34 head f33 mainIn #connect
Ps0 f33 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f10 out f8 tail #connect
Ps0 f8 head f22 in #connect
Ps0 f16 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
Ps0 f21 mainOut f19 tail #connect
Ps0 f19 head f22 in #connect
Ps0 f13 mainOut f20 tail #connect
Ps0 f20 head f38 mainIn #connect
