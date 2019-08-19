[Ivy]
1610C9DBEDBBF46E 3.28 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseMenuProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @CallSub f38 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @Alternative f10 '' #zField
Ps0 @Alternative f22 '' #zField
Ps0 @CallSub f33 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @CallSub f14 '' #zField
Ps0 @PushWFArc f23 '' #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f24 '' #zField
Ps0 @CallSub f25 '' #zField
Ps0 @PushWFArc f26 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @UdEvent f12 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @PushWFArc f20 '' #zField
>Proto Ps0 Ps0 PortalCaseMenuProcess #zField
Ps0 f4 1075 211 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.hasReadAllCasesPermisson = PermissionUtils.checkReadAllCasesPermission();' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 360 200 128 48 -45 -8 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f38 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
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
Ps0 f38 490 330 112 44 -49 -8 #rect
Ps0 f38 @|CallSubIcon #fIcon
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivyteam.ivy.workflow.CaseState;

RegisteredApplicationService service = new RegisteredApplicationService();
java.util.List apps = service.findActiveIvyAppsBasedOnConfiguration(ivy.session.getSessionUserName());
out.criteria.apps = apps;

out.criteria.includedStates = [CaseState.CREATED, CaseState.RUNNING, CaseState.DONE];' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build criteria</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 184 202 112 44 -34 -8 #rect
Ps0 f7 @|StepIcon #fIcon
Ps0 f17 expr out #txt
Ps0 f17 296 224 360 224 #arcP
Ps0 f10 690 210 28 28 14 0 #rect
Ps0 f10 @|AlternativeIcon #fIcon
Ps0 f22 882 210 28 28 14 0 #rect
Ps0 f22 @|AlternativeIcon #fIcon
Ps0 f33 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
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
Ps0 f33 152 330 144 44 -66 -8 #rect
Ps0 f33 @|CallSubIcon #fIcon
Ps0 f8 expr in #txt
Ps0 f8 718 224 882 224 #arcP
Ps0 f14 processCall 'Functional Processes/BuildCaseQuery:buildCaseQuery()' #txt
Ps0 f14 requestActionDecl '<> param;
' #txt
Ps0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f14 responseMappingAction 'out=in;
out.criteria.customCaseQuery=result.#caseQuery;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildCaseQuery</name>
    </language>
</elementInfo>
' #txt
Ps0 f14 544 202 112 44 -45 -8 #rect
Ps0 f14 @|CallSubIcon #fIcon
Ps0 f23 expr out #txt
Ps0 f23 488 224 544 224 #arcP
Ps0 f0 guid 1680CAF963FE7BB5 #txt
Ps0 f0 method start() #txt
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
Ps0 f0 83 83 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 307 83 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 96 307 96 #arcP
Ps0 f3 guid 1680CAFE56F5E311 #txt
Ps0 f3 method findCaseCategory() #txt
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
Ps0 f3 83 211 26 26 -53 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 109 224 184 224 #arcP
Ps0 f24 expr out #txt
Ps0 f24 656 224 690 224 #arcP
Ps0 f25 processCall 'Ivy Data Processes/CaseService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria)' #txt
Ps0 f25 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria caseCategorySearchCriteria> param;
' #txt
Ps0 f25 requestMappingAction 'param.caseCategorySearchCriteria=in.criteria;
' #txt
Ps0 f25 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f25 responseMappingAction 'out=in;
out.allCaseCategoryTree=result.#categoryTree;
' #txt
Ps0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories of&#xD;
all cases</name>
    </language>
</elementInfo>
' #txt
Ps0 f25 744 138 112 44 -49 -20 #rect
Ps0 f25 @|CallSubIcon #fIcon
Ps0 f26 expr in #txt
Ps0 f26 outCond in.hasReadAllCasesPermisson #txt
Ps0 f26 704 210 744 160 #arcP
Ps0 f26 1 704 160 #addKink
Ps0 f26 1 0.032978762810833735 0 0 #arcLabel
Ps0 f5 expr out #txt
Ps0 f5 856 160 896 210 #arcP
Ps0 f5 1 896 160 #addKink
Ps0 f5 0 0.9741638052568026 0 0 #arcLabel
Ps0 f6 processCall 'Ivy Data Processes/CaseService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria)' #txt
Ps0 f6 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria caseCategorySearchCriteria> param;
' #txt
Ps0 f6 requestMappingAction 'param.caseCategorySearchCriteria=in.criteria;
param.caseCategorySearchCriteria.involvedUsername=ivy.session.getSessionUserName();
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.myCaseCategoryTree=result.#categoryTree;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories of&#xD;
involved cases</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 936 202 112 44 -49 -20 #rect
Ps0 f6 @|CallSubIcon #fIcon
Ps0 f11 expr in #txt
Ps0 f11 910 224 936 224 #arcP
Ps0 f16 expr out #txt
Ps0 f16 1048 224 1075 224 #arcP
Ps0 f12 guid 1680D56C142FD50A #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadCase</name>
    </language>
</elementInfo>
' #txt
Ps0 f12 83 339 26 26 -29 15 #rect
Ps0 f12 @|UdEventIcon #fIcon
Ps0 f19 expr out #txt
Ps0 f19 109 352 152 352 #arcP
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portal.generic.view.CaseView;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.MainMenuNode;

in.selectedMenuItem = in.selectedNode.getData() as MainMenuNode;
CaseNode categoryMenu = in.selectedMenuItem as CaseNode;

in.dataModel.setCategory(categoryMenu.category);

if(in.selectedNode.type.startsWith(TreeNodeType.CASES_ALL_CASES)){
	in.hasReadAllCasesPermisson = PermissionUtils.checkReadAllCasesPermission();
	in.dataModel.setAdminQuery(in.hasReadAllCasesPermisson);
} else if(in.selectedNode.type.startsWith(TreeNodeType.CASES_MY_CASES)) {
	in.dataModel.setAdminQuery(false);
}

in.caseView = CaseView.create().category(categoryMenu).dataModel(in.dataModel).withTitle(categoryMenu.value).buildNewView();' #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build datamodel,&#xD;
caseview</name>
    </language>
</elementInfo>
' #txt
Ps0 f13 320 330 128 44 -43 -16 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f18 expr out #txt
Ps0 f18 296 352 320 352 #arcP
Ps0 f20 expr out #txt
Ps0 f20 448 352 490 352 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f9 mainIn #connect
Ps0 f8 head f22 in #connect
Ps0 f9 mainOut f23 tail #connect
Ps0 f23 head f14 mainIn #connect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f15 tail #connect
Ps0 f15 head f7 mainIn #connect
Ps0 f14 mainOut f24 tail #connect
Ps0 f24 head f10 in #connect
Ps0 f10 out f26 tail #connect
Ps0 f26 head f25 mainIn #connect
Ps0 f10 out f8 tail #connect
Ps0 f25 mainOut f5 tail #connect
Ps0 f5 head f22 in #connect
Ps0 f22 out f11 tail #connect
Ps0 f11 head f6 mainIn #connect
Ps0 f6 mainOut f16 tail #connect
Ps0 f16 head f4 mainIn #connect
Ps0 f12 mainOut f19 tail #connect
Ps0 f19 head f33 mainIn #connect
Ps0 f33 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f13 mainOut f20 tail #connect
Ps0 f20 head f38 mainIn #connect
