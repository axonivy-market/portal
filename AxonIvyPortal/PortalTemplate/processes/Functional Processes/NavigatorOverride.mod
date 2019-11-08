[Ivy]
1543D9E65076619B 7.5.0 #module
>Proto >Proto Collection #zClass
Nr0 Navigator Big #zClass
Nr0 B #cInfo
Nr0 #process
Nr0 @TextInP .type .type #zField
Nr0 @TextInP .processKind .processKind #zField
Nr0 @AnnotationInP-0n ai ai #zField
Nr0 @MessageFlowInP-0n messageIn messageIn #zField
Nr0 @MessageFlowOutP-0n messageOut messageOut #zField
Nr0 @TextInP .xml .xml #zField
Nr0 @TextInP .responsibility .responsibility #zField
Nr0 @EndSub f10 '' #zField
Nr0 @CallSub f12 '' #zField
Nr0 @PushWFArc f11 '' #zField
Nr0 @StartSub f9 '' #zField
Nr0 @PushWFArc f13 '' #zField
Nr0 @GridStep f14 '' #zField
Nr0 @InfoButton f4 '' #zField
Nr0 @AnnotationArc f22 '' #zField
Nr0 @CallSub f16 '' #zField
Nr0 @PushWFArc f17 '' #zField
Nr0 @PushWFArc f15 '' #zField
Nr0 @GridStep f20 '' #zField
Nr0 @CallSub f23 '' #zField
Nr0 @EndSub f24 '' #zField
Nr0 @StartSub f25 '' #zField
Nr0 @CallSub f26 '' #zField
Nr0 @PushWFArc f27 '' #zField
Nr0 @PushWFArc f28 '' #zField
Nr0 @PushWFArc f29 '' #zField
Nr0 @PushWFArc f30 '' #zField
Nr0 @GridStep f31 '' #zField
Nr0 @StartSub f32 '' #zField
Nr0 @EndSub f33 '' #zField
Nr0 @CallSub f34 '' #zField
Nr0 @CallSub f35 '' #zField
Nr0 @PushWFArc f36 '' #zField
Nr0 @PushWFArc f37 '' #zField
Nr0 @PushWFArc f38 '' #zField
Nr0 @PushWFArc f39 '' #zField
Nr0 @InfoButton f40 '' #zField
Nr0 @AnnotationArc f41 '' #zField
Nr0 @InfoButton f42 '' #zField
Nr0 @AnnotationArc f43 '' #zField
Nr0 @StartSub f44 '' #zField
Nr0 @EndSub f45 '' #zField
Nr0 @CallSub f46 '' #zField
Nr0 @PushWFArc f47 '' #zField
Nr0 @GridStep f48 '' #zField
Nr0 @CallSub f49 '' #zField
Nr0 @PushWFArc f50 '' #zField
Nr0 @PushWFArc f51 '' #zField
Nr0 @PushWFArc f52 '' #zField
Nr0 @InfoButton f53 '' #zField
Nr0 @CallSub f5 '' #zField
Nr0 @EndSub f1 '' #zField
Nr0 @InfoButton f21 '' #zField
Nr0 @StartSub f0 '' #zField
Nr0 @GridStep f7 '' #zField
Nr0 @PushWFArc f2 '' #zField
Nr0 @PushWFArc f6 '' #zField
Nr0 @PushWFArc f8 '' #zField
Nr0 @StartSub f54 '' #zField
Nr0 @InfoButton f19 '' #zField
Nr0 @EndSub f18 '' #zField
Nr0 @GridStep f56 '' #zField
Nr0 @CallSub f58 '' #zField
Nr0 @PushWFArc f57 '' #zField
Nr0 @PushWFArc f59 '' #zField
Nr0 @PushWFArc f55 '' #zField
Nr0 @AnnotationArc f3 '' #zField
>Proto Nr0 Nr0 Navigator #zField
Nr0 f10 51 779 26 26 14 0 #rect
Nr0 f10 @|EndSubIcon #fIcon
Nr0 f12 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Nr0 f12 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Nr0 f12 requestMappingAction 'param.taskView=in.taskView;
' #txt
Nr0 f12 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f12 responseMappingAction 'out=in;
' #txt
Nr0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f12 46 700 36 24 20 -2 #rect
Nr0 f12 @|CallSubIcon #fIcon
Nr0 f11 expr out #txt
Nr0 f11 64 724 64 779 #arcP
Nr0 f9 inParamDecl '<Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,String caseName> param;' #txt
Nr0 f9 inParamTable 'out.caseId=param.caseId;
out.caseName=param.caseName;
out.taskId=param.taskId;
' #txt
Nr0 f9 outParamDecl '<> result;' #txt
Nr0 f9 callSignature viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String) #txt
Nr0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTask(Long,GlobalCaseId,String)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f9 51 459 26 26 14 0 #rect
Nr0 f9 @|StartSubIcon #fIcon
Nr0 f13 expr out #txt
Nr0 f13 64 652 64 700 #arcP
Nr0 f14 actionTable 'out=in;
' #txt
Nr0 f14 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

String pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList("#" + in.caseId.toString(), in.caseName));

in.taskDataModel.setCaseId(in.caseId.id());
in.taskDataModel.setSortField(TaskSortField.ID.toString(), true);
in.taskDataModel.getCriteria().setKeyword(StringUtils.EMPTY);
in.taskDataModel.setQueryByBusinessCaseId(in.caseId.isBusinessCase());
in.taskDataModel.setCaseName(in.caseName);
in.taskDataModel.setAdminQuery(PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission());
in.taskDataModel.setInvolvedUsername(ivy.session.getSessionUserName());
in.taskDataModel.setRelatedTaskDisplayed(true);

in.taskView = TaskView.create()
											.canLinkBackCaseDetail(true)
											.taskId(in.taskId)
											.pageTitle(pageTitle)
											.showHeaderToolbar(false)
											.displayTaskCategory(PermissionUtils.checkAccessFullTaskListPermission())
											.dataModel(in.taskDataModel).createNewTaskView();								
											' #txt
Nr0 f14 security system #txt
Nr0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepareTaskView</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f14 46 628 36 24 20 -2 #rect
Nr0 f14 @|StepIcon #fIcon
Nr0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing task.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f4 224 586 496 172 -238 -84 #rect
Nr0 f4 @|IBIcon #fIcon
Nr0 f22 224 672 82 712 #arcP
Nr0 f16 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Nr0 f16 requestActionDecl '<> param;' #txt
Nr0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f16 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
Nr0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f16 8 538 112 44 -40 -8 #rect
Nr0 f16 @|CallSubIcon #fIcon
Nr0 f17 expr out #txt
Nr0 f17 64 485 64 538 #arcP
Nr0 f15 expr out #txt
Nr0 f15 64 582 64 628 #arcP
Nr0 f20 actionTable 'out=in;
' #txt
Nr0 f20 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.PortalPage;
import org.apache.commons.lang3.StringUtils;

String pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedStatisticHeader") + in.chartName;

in.taskDataModel.getCriteria().setCustomTaskQuery(in.taskQuery);
in.taskDataModel.getCriteria().setKeyword(StringUtils.EMPTY);
in.taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
in.taskDataModel.setNotKeepFilter(true);

in.taskView = TaskView
								.create()
								.pageTitle(pageTitle)
								.showHeaderToolbar(false)
								.displayTaskCategory(PermissionUtils.checkAccessFullTaskListPermission())
								.dataModel(in.taskDataModel)
								.createNewTaskView();' #txt
Nr0 f20 security system #txt
Nr0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepareTaskView</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f20 847 631 36 24 20 -2 #rect
Nr0 f20 @|StepIcon #fIcon
Nr0 f23 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Nr0 f23 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Nr0 f23 requestMappingAction 'param.taskView=in.taskView;
' #txt
Nr0 f23 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f23 responseMappingAction 'out=in;
' #txt
Nr0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f23 847 703 36 24 20 -2 #rect
Nr0 f23 @|CallSubIcon #fIcon
Nr0 f24 852 782 26 26 14 0 #rect
Nr0 f24 @|EndSubIcon #fIcon
Nr0 f25 inParamDecl '<String chartName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Nr0 f25 inParamTable 'out.chartName=param.chartName;
out.taskQuery=param.taskQuery;
' #txt
Nr0 f25 outParamDecl '<> result;' #txt
Nr0 f25 callSignature viewTaskForAnalytic(String,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
Nr0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTaskForAnalytic(String, TaskQuery)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f25 852 462 26 26 14 0 #rect
Nr0 f25 @|StartSubIcon #fIcon
Nr0 f26 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Nr0 f26 requestActionDecl '<> param;' #txt
Nr0 f26 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f26 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
Nr0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f26 809 541 112 44 -40 -8 #rect
Nr0 f26 @|CallSubIcon #fIcon
Nr0 f27 expr out #txt
Nr0 f27 865 655 865 703 #arcP
Nr0 f28 expr out #txt
Nr0 f28 865 727 865 782 #arcP
Nr0 f29 expr out #txt
Nr0 f29 865 488 865 541 #arcP
Nr0 f30 expr out #txt
Nr0 f30 865 585 865 631 #arcP
Nr0 f31 actionTable 'out=in;
' #txt
Nr0 f31 actionCode 'import ch.ivy.addon.portal.generic.view.CaseView;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import org.apache.commons.lang3.StringUtils;

String pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/headerTitle/relatedStatisticHeader") + in.chartName;

in.caseDataModel.getCriteria().setBusinessCase(true);
in.caseDataModel.setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
in.caseDataModel.getCriteria().setCustomCaseQuery(in.caseQuery);
in.caseDataModel.getCriteria().setKeyword(StringUtils.EMPTY);
in.caseDataModel.setNotKeepFilter(true);

in.caseView = CaseView.create()
								.dataModel(in.caseDataModel)
								.displayCaseCategory(PermissionUtils.checkAccessFullCaseListPermission())
								.withTitle(pageTitle)
								.buildNewView();' #txt
Nr0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f31 848 275 36 24 20 -2 #rect
Nr0 f31 @|StepIcon #fIcon
Nr0 f32 inParamDecl '<String chartName,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
Nr0 f32 inParamTable 'out.caseQuery=param.caseQuery;
out.chartName=param.chartName;
' #txt
Nr0 f32 outParamDecl '<> result;' #txt
Nr0 f32 callSignature viewCaseForAnalytic(String,ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
Nr0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCaseForAnalytic(String, CaseQuery)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f32 853 82 26 26 14 0 #rect
Nr0 f32 @|StartSubIcon #fIcon
Nr0 f33 853 402 26 26 14 0 #rect
Nr0 f33 @|EndSubIcon #fIcon
Nr0 f34 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Nr0 f34 requestActionDecl '<> param;' #txt
Nr0 f34 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f34 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
Nr0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init case
data model</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f34 810 169 112 44 -31 -20 #rect
Nr0 f34 @|CallSubIcon #fIcon
Nr0 f35 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Nr0 f35 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Nr0 f35 requestMappingAction 'param.view=in.caseView;
' #txt
Nr0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f35 responseMappingAction 'out=in;
' #txt
Nr0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f35 848 339 36 24 20 -2 #rect
Nr0 f35 @|CallSubIcon #fIcon
Nr0 f36 expr out #txt
Nr0 f36 866 213 866 275 #arcP
Nr0 f37 expr out #txt
Nr0 f37 866 299 866 339 #arcP
Nr0 f37 0 0.4999999999999999 0 0 #arcLabel
Nr0 f38 expr out #txt
Nr0 f38 866 108 866 169 #arcP
Nr0 f39 expr out #txt
Nr0 f39 866 363 866 402 #arcP
Nr0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing case.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f40 1096 131 496 172 -238 -84 #rect
Nr0 f40 @|IBIcon #fIcon
Nr0 f41 1096 217 884 351 #arcP
Nr0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing task.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f42 1059 556 496 172 -238 -84 #rect
Nr0 f42 @|IBIcon #fIcon
Nr0 f43 1059 642 883 715 #arcP
Nr0 f44 inParamDecl '<String businessCaseName,ch.ivy.addon.portalkit.dto.GlobalCaseId businessCaseId> param;' #txt
Nr0 f44 inParamTable 'out.caseId=param.businessCaseId;
out.caseName=param.businessCaseName;
' #txt
Nr0 f44 outParamDecl '<> result;' #txt
Nr0 f44 callSignature viewTechnicalCasesOfBusniessCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTechnicalCasesOfBusniessCase(String,GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f44 1777 81 30 30 -164 17 #rect
Nr0 f44 @|StartSubIcon #fIcon
Nr0 f45 1777 465 30 30 0 15 #rect
Nr0 f45 @|EndSubIcon #fIcon
Nr0 f46 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Nr0 f46 requestActionDecl '<> param;' #txt
Nr0 f46 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f46 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
Nr0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init case&#xD;
data model</name>
    </language>
</elementInfo>
' #txt
Nr0 f46 1736 154 112 44 -31 -20 #rect
Nr0 f46 @|CallSubIcon #fIcon
Nr0 f47 expr out #txt
Nr0 f47 1792 111 1792 154 #arcP
Nr0 f48 actionTable 'out=in;
' #txt
Nr0 f48 actionCode 'import ch.ivy.addon.portalkit.enums.CaseSortField;
import java.util.Arrays;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;
import org.apache.commons.lang3.StringUtils;

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/headerTitle/technicalCasesOfBusinessCaseTitle", Arrays.asList(in.caseId.id().toString(), in.caseName));

in.caseDataModel.getCriteria().setKeyword(StringUtils.EMPTY);
in.caseDataModel.getCriteria().setBusinessCase(false);
in.caseDataModel.getCriteria().setBusinessCaseId(in.caseId.id());
in.caseDataModel.getCriteria().setTechnicalCase(true);
in.caseDataModel.getCriteria().sortField = CaseSortField.NAME.toString();
in.caseDataModel.setNotKeepFilter(true);
in.caseView = CaseView.create().dataModel(in.caseDataModel).hideCaseFilter(true).withTitle(title).buildNewView();' #txt
Nr0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
    </language>
</elementInfo>
' #txt
Nr0 f48 1736 266 112 44 -50 -8 #rect
Nr0 f48 @|StepIcon #fIcon
Nr0 f49 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Nr0 f49 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Nr0 f49 requestMappingAction 'param.view=in.caseView;
' #txt
Nr0 f49 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f49 responseMappingAction 'out=in;
' #txt
Nr0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
    </language>
</elementInfo>
' #txt
Nr0 f49 1736 378 112 44 -49 -8 #rect
Nr0 f49 @|CallSubIcon #fIcon
Nr0 f50 expr out #txt
Nr0 f50 1792 198 1792 266 #arcP
Nr0 f51 expr out #txt
Nr0 f51 1792 310 1792 378 #arcP
Nr0 f52 expr out #txt
Nr0 f52 1792 422 1792 465 #arcP
Nr0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit&#xD;
to override and implement the functionality&#xD;
of redirecting into a HtmlDialog for viewing case.&#xD;
&#xD;
By default, this is doing nothing. The default implementation, however,&#xD;
is put in PortalTemplate.</name>
        <nameStyle>15,5,8,0
252,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f53 1920 187 496 172 -238 -84 #rect
Nr0 f53 @|IBIcon #fIcon
Nr0 f5 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ICase,Boolean)' #txt
Nr0 f5 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Nr0 f5 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
Nr0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f5 responseMappingAction 'out=in;
' #txt
Nr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
        <nameStyle>21,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f5 46 292 36 24 20 -2 #rect
Nr0 f5 @|CallSubIcon #fIcon
Nr0 f1 51 403 26 26 14 0 #rect
Nr0 f1 @|EndSubIcon #fIcon
Nr0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing case.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f21 264 130 496 172 -238 -84 #rect
Nr0 f21 @|IBIcon #fIcon
Nr0 f0 inParamDecl '<ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Nr0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Nr0 f0 outParamDecl '<> result;' #txt
Nr0 f0 callSignature viewCase(ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCase(GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f0 51 83 26 26 14 0 #rect
Nr0 f0 @|StartSubIcon #fIcon
Nr0 f7 actionTable 'out=in;
' #txt
Nr0 f7 actionCode 'import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.ICase;

out.iCase = ivy.wf.getGlobalContext().getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(in.caseId.id())) as ICase;' #txt
Nr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f7 46 172 36 24 20 -2 #rect
Nr0 f7 @|StepIcon #fIcon
Nr0 f2 expr out #txt
Nr0 f2 64 316 64 403 #arcP
Nr0 f6 expr out #txt
Nr0 f6 64 196 64 292 #arcP
Nr0 f8 expr out #txt
Nr0 f8 64 109 64 172 #arcP
Nr0 f54 inParamDecl '<Long taskId> param;' #txt
Nr0 f54 inParamTable 'out.taskId=param.taskId;
' #txt
Nr0 f54 outParamDecl '<> result;' #txt
Nr0 f54 callSignature viewRelatedTask(Long) #txt
Nr0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewRelatedTask(String,GlobalCaseId,String)</name>
        <nameStyle>43,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f54 1779 539 26 26 14 0 #rect
Nr0 f54 @|StartSubIcon #fIcon
Nr0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing task.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f19 1856 626 496 172 -238 -84 #rect
Nr0 f19 @|IBIcon #fIcon
Nr0 f18 1779 827 26 26 14 0 #rect
Nr0 f18 @|EndSubIcon #fIcon
Nr0 f56 actionTable 'out=in;
' #txt
Nr0 f56 actionCode 'import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

out.iTask = ivy.wf.getGlobalContext().getTaskQueryExecutor().getFirstResult(TaskQuery.create().where().taskId().isEqual(in.taskId)) as ITask;' #txt
Nr0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare task view</name>
    </language>
</elementInfo>
' #txt
Nr0 f56 1736 626 112 44 -48 -8 #rect
Nr0 f56 @|StepIcon #fIcon
Nr0 f58 processCall 'Functional Processes/OpenPortalTaskDetailsHook:call(ITask,TaskLazyDataModel,PortalPage,Boolean)' #txt
Nr0 f58 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Nr0 f58 requestMappingAction 'param.task=in.iTask;
param.portalPage=ch.ivy.addon.portalkit.enums.PortalPage.CASE_DETAIL_FROM_TASK;
' #txt
Nr0 f58 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f58 responseMappingAction 'out=in;
' #txt
Nr0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTaskDetails</name>
    </language>
</elementInfo>
' #txt
Nr0 f58 1768 732 48 24 -126 28 #rect
Nr0 f58 @|CallSubIcon #fIcon
Nr0 f57 expr out #txt
Nr0 f57 1792 565 1792 626 #arcP
Nr0 f59 expr out #txt
Nr0 f59 1792 670 1792 732 #arcP
Nr0 f55 expr out #txt
Nr0 f55 1792 756 1792 827 #arcP
Nr0 f3 264 216 82 304 #arcP
>Proto Nr0 .type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
>Proto Nr0 .processKind CALLABLE_SUB #txt
>Proto Nr0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Nr0 0 0 32 24 18 0 #rect
>Proto Nr0 @|BIcon #fIcon
Nr0 f12 mainOut f11 tail #connect
Nr0 f11 head f10 mainIn #connect
Nr0 f14 mainOut f13 tail #connect
Nr0 f13 head f12 mainIn #connect
Nr0 f4 ao f22 tail #connect
Nr0 f22 head f12 @CG|ai #connect
Nr0 f9 mainOut f17 tail #connect
Nr0 f17 head f16 mainIn #connect
Nr0 f16 mainOut f15 tail #connect
Nr0 f15 head f14 mainIn #connect
Nr0 f23 mainOut f28 tail #connect
Nr0 f28 head f24 mainIn #connect
Nr0 f20 mainOut f27 tail #connect
Nr0 f27 head f23 mainIn #connect
Nr0 f25 mainOut f29 tail #connect
Nr0 f29 head f26 mainIn #connect
Nr0 f26 mainOut f30 tail #connect
Nr0 f30 head f20 mainIn #connect
Nr0 f35 mainOut f39 tail #connect
Nr0 f39 head f33 mainIn #connect
Nr0 f31 mainOut f37 tail #connect
Nr0 f37 head f35 mainIn #connect
Nr0 f32 mainOut f38 tail #connect
Nr0 f38 head f34 mainIn #connect
Nr0 f34 mainOut f36 tail #connect
Nr0 f36 head f31 mainIn #connect
Nr0 f40 ao f41 tail #connect
Nr0 f41 head f35 @CG|ai #connect
Nr0 f42 ao f43 tail #connect
Nr0 f43 head f23 @CG|ai #connect
Nr0 f44 mainOut f47 tail #connect
Nr0 f47 head f46 mainIn #connect
Nr0 f46 mainOut f50 tail #connect
Nr0 f50 head f48 mainIn #connect
Nr0 f48 mainOut f51 tail #connect
Nr0 f51 head f49 mainIn #connect
Nr0 f49 mainOut f52 tail #connect
Nr0 f52 head f45 mainIn #connect
Nr0 f5 mainOut f2 tail #connect
Nr0 f2 head f1 mainIn #connect
Nr0 f7 mainOut f6 tail #connect
Nr0 f6 head f5 mainIn #connect
Nr0 f0 mainOut f8 tail #connect
Nr0 f8 head f7 mainIn #connect
Nr0 f54 mainOut f57 tail #connect
Nr0 f57 head f56 mainIn #connect
Nr0 f56 mainOut f59 tail #connect
Nr0 f59 head f58 mainIn #connect
Nr0 f58 mainOut f55 tail #connect
Nr0 f55 head f18 mainIn #connect
Nr0 f21 ao f3 tail #connect
Nr0 f3 head f5 @CG|ai #connect
