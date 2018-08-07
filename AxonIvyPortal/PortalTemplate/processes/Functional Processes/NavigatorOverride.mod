[Ivy]
[>Created: Wed Mar 22 13:50:03 ICT 2017]
1543D9E65076619B 3.20 #module
>Proto >Proto Collection #zClass
Nr0 Navigator Big #zClass
Nr0 B #cInfo
Nr0 #process
Nr0 @TextInP .resExport .resExport #zField
Nr0 @TextInP .type .type #zField
Nr0 @TextInP .processKind .processKind #zField
Nr0 @AnnotationInP-0n ai ai #zField
Nr0 @MessageFlowInP-0n messageIn messageIn #zField
Nr0 @MessageFlowOutP-0n messageOut messageOut #zField
Nr0 @TextInP .xml .xml #zField
Nr0 @TextInP .responsibility .responsibility #zField
Nr0 @StartSub f0 '' #zField
Nr0 @EndSub f1 '' #zField
Nr0 @CallSub f5 '' #zField
Nr0 @PushWFArc f2 '' #zField
Nr0 @GridStep f7 '' #zField
Nr0 @PushWFArc f8 '' #zField
Nr0 @PushWFArc f6 '' #zField
Nr0 @EndSub f10 '' #zField
Nr0 @CallSub f12 '' #zField
Nr0 @PushWFArc f11 '' #zField
Nr0 @StartSub f9 '' #zField
Nr0 @PushWFArc f15 '' #zField
Nr0 @PushWFArc f13 '' #zField
Nr0 @GridStep f14 '' #zField
Nr0 @InfoButton f21 '' #zField
Nr0 @AnnotationArc f3 '' #zField
Nr0 @InfoButton f4 '' #zField
Nr0 @AnnotationArc f22 '' #zField
>Proto Nr0 Nr0 Navigator #zField
Nr0 f0 inParamDecl '<java.lang.String caseName,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Nr0 f0 inParamTable 'out.caseId=param.caseId;
out.caseName=param.caseName;
' #txt
Nr0 f0 outParamDecl '<> result;
' #txt
Nr0 f0 actionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f0 callSignature viewCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f0 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCase(String,GlobalCaseId)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f0 51 83 26 26 14 0 #rect
Nr0 f0 @|StartSubIcon #fIcon
Nr0 f1 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f1 51 387 26 26 14 0 #rect
Nr0 f1 @|EndSubIcon #fIcon
Nr0 f5 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f5 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Nr0 f5 doCall true #txt
Nr0 f5 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Nr0 f5 requestMappingAction 'param.view=in.caseView;
' #txt
Nr0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f5 responseMappingAction 'out=in;
' #txt
Nr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f5 46 260 36 24 20 -2 #rect
Nr0 f5 @|CallSubIcon #fIcon
Nr0 f2 expr out #txt
Nr0 f2 64 284 64 387 #arcP
Nr0 f7 actionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f7 actionTable 'out=in;
' #txt
Nr0 f7 actionCode 'import java.util.Arrays;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/relatedCaseHeader", Arrays.asList(in.caseId.id().toString(), in.caseName));
CaseLazyDataModel dataModel = new CaseLazyDataModel();
dataModel.setCaseId(in.caseId.id());
in.caseView = CaseView.create().dataModel(dataModel).hideCaseFilter(true).withTitle(title).autoSelectIfExists(in.caseId).buildNewView();' #txt
Nr0 f7 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f7 46 196 36 24 20 -2 #rect
Nr0 f7 @|StepIcon #fIcon
Nr0 f8 expr out #txt
Nr0 f8 64 109 64 196 #arcP
Nr0 f6 expr out #txt
Nr0 f6 64 220 64 260 #arcP
Nr0 f10 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f10 51 683 26 26 14 0 #rect
Nr0 f10 @|EndSubIcon #fIcon
Nr0 f12 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f12 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Nr0 f12 doCall true #txt
Nr0 f12 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
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
Nr0 f12 46 604 36 24 20 -2 #rect
Nr0 f12 @|CallSubIcon #fIcon
Nr0 f11 expr out #txt
Nr0 f11 64 628 64 683 #arcP
Nr0 f9 inParamDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;' #txt
Nr0 f9 inParamTable 'out.caseId=param.caseId;
out.caseName=param.caseName;
out.taskId=param.taskId;
' #txt
Nr0 f9 outParamDecl '<> result;
' #txt
Nr0 f9 actionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f9 callSignature viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String) #txt
Nr0 f9 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTask(Long,GlobalCaseId,String)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f9 51 451 26 26 14 0 #rect
Nr0 f9 @|StartSubIcon #fIcon
Nr0 f15 expr out #txt
Nr0 f15 64 477 64 532 #arcP
Nr0 f13 expr out #txt
Nr0 f13 64 556 64 604 #arcP
Nr0 f14 actionDecl 'ch.ivy.addon.portal.generic.NavigatorOverrideData out;
' #txt
Nr0 f14 actionTable 'out=in;
' #txt
Nr0 f14 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Arrays;


MainMenuNode category = new MainMenuNode();
String pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList("#" + in.caseId.toString(), in.caseName));

TaskLazyDataModel dataModel = new TaskLazyDataModel();
dataModel.setCaseId(in.caseId.id());
dataModel.setIgnoreInvolvedUser(true);
dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
dataModel.addIncludedStates(Arrays.asList(TaskState.DONE));

in.taskView = TaskView.create()
											.category(category)
											.canLinkBackCaseDetail(true)
											.serverId(in.caseId.serverId())
											.caseId(in.caseId.id())
											.caseName(in.caseName)
											.remoteTaskId(in.taskId)
											.pageTitle(pageTitle)
											.dataModel(dataModel).createNewTaskView();' #txt
Nr0 f14 security system #txt
Nr0 f14 type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
Nr0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepareTaskView</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f14 46 532 36 24 20 -2 #rect
Nr0 f14 @|StepIcon #fIcon
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
Nr0 f3 264 216 82 272 #arcP
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
Nr0 f4 160 658 496 172 -238 -84 #rect
Nr0 f4 @|IBIcon #fIcon
Nr0 f22 160 744 64 628 #arcP
>Proto Nr0 .type ch.ivy.addon.portal.generic.NavigatorOverrideData #txt
>Proto Nr0 .processKind CALLABLE_SUB #txt
>Proto Nr0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Nr0 0 0 32 24 18 0 #rect
>Proto Nr0 @|BIcon #fIcon
Nr0 f5 mainOut f2 tail #connect
Nr0 f2 head f1 mainIn #connect
Nr0 f0 mainOut f8 tail #connect
Nr0 f8 head f7 mainIn #connect
Nr0 f7 mainOut f6 tail #connect
Nr0 f6 head f5 mainIn #connect
Nr0 f12 mainOut f11 tail #connect
Nr0 f11 head f10 mainIn #connect
Nr0 f9 mainOut f15 tail #connect
Nr0 f15 head f14 mainIn #connect
Nr0 f14 mainOut f13 tail #connect
Nr0 f13 head f12 mainIn #connect
Nr0 f21 ao f3 tail #connect
Nr0 f3 head f5 @CG|ai #connect
Nr0 f4 ao f22 tail #connect
Nr0 f22 head f12 @CG|ai #connect
