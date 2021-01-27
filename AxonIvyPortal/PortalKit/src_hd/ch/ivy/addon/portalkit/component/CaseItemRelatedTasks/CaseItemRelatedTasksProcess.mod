[Ivy]
17727FBAA8BC2EDF 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedTasksProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f14 '' #zField
Cs0 @GridStep f33 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @UdMethod f45 '' #zField
Cs0 @PushWFArc f47 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @GridStep f4 '' #zField
Cs0 @PushWFArc f51 '' #zField
Cs0 @UdMethod f43 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedTasksProcess #zField
Cs0 f0 guid 167E9A75EF3D0909 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 275 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 96 275 96 #arcP
Cs0 f6 guid 167E9A777AB171EA #txt
Cs0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f6 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 179 26 26 -41 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 339 179 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f14 actionTable 'out=in;
' #txt
Cs0 f14 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.security.ISession;
import ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;

in.relatedTasks.clear();

int count = 1;
in.totalRelatedTasks = 0;
boolean excludeHiddenTasks = Boolean.parseBoolean(ivy.var.get(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED));
ISession session = ivy.session;
boolean isOwner = in.iCase.#owner != null ? in.iCase.getOwner().isMember(ivy.session, true) : false;
boolean ableToSeeAllRelatedTaskOfCase = PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission() || isOwner;
for (ITask task : in.iCase.tasks().all()) {
	if ((task.getState() == TaskState.SUSPENDED || task.getState() == TaskState.RESUMED || task.getState() == TaskState.PARKED || task.getState() == TaskState.CREATED)
				&& (excludeHiddenTasks ? StringUtils.isEmpty(task.customFields().stringField(AdditionalProperty.HIDE.toString()).getOrNull()) : true) && task.isPersistent()) {
		if (ableToSeeAllRelatedTaskOfCase) {
			in.totalRelatedTasks++;
			if (count <= 21) {//get only 21 tasks
					in.relatedTasks.add(task);
			}
			count++;
		} else {
			if(task.canUserResumeTask(session).wasSuccessful()) {
				in.totalRelatedTasks++;
				if (count <= 21) {//get only 21 tasks
						in.relatedTasks.add(task);
				}
				count++;
			}
		}
	}
}
' #txt
Cs0 f14 security system #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find related tasks</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 168 170 112 44 -49 -8 #rect
Cs0 f14 @|StepIcon #fIcon
Cs0 f33 actionTable 'out=in;
' #txt
Cs0 f33 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update TaskStartedAttribute</name>
    </language>
</elementInfo>
' #txt
Cs0 f33 168 362 160 44 -76 -8 #rect
Cs0 f33 @|StepIcon #fIcon
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

if (in.inFrame) {
	PortalNavigator.navigateToPortalTaskDetailsInFrame(in.taskId);
} else {
	PortalNavigator.navigateToPortalTaskDetails(in.taskId);
}' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task details</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 384 360 176 48 -59 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f36 328 384 384 384 #arcP
Cs0 f45 guid 1720C62D9BF92EB1 #txt
Cs0 f45 method navigateToSelectedTaskDetails(Long,Boolean,Boolean) #txt
Cs0 f45 inParameterDecl '<Long taskId,Boolean isWorkingOnTask,Boolean inFrame> param;' #txt
Cs0 f45 inParameterMapAction 'out.inFrame=param.inFrame;
out.isWorkingOnTask=param.isWorkingOnTask;
out.taskId=param.taskId;
' #txt
Cs0 f45 outParameterDecl '<> result;' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToSelectedTaskDetails(Long,Boolean,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f45 83 371 26 26 -77 20 #rect
Cs0 f45 @|UdMethodIcon #fIcon
Cs0 f47 109 384 168 384 #arcP
Cs0 f47 0 0.5541990710792534 0 0 #arcLabel
Cs0 f8 109 192 168 192 #arcP
Cs0 f9 280 192 339 192 #arcP
Cs0 f4 actionTable 'out=in;
' #txt
Cs0 f4 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

if (in.inFrame) {
	PortalNavigator.navigateToPortalRelatedTasksOfCaseInFrame(in.iCase.getId(), true, PermissionUtils.getCaseName(in.iCase));
} else {
	PortalNavigator.navigateToPortalRelatedTasksOfCase(in.iCase.getId(), true, PermissionUtils.getCaseName(in.iCase));
}' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task list</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 392 266 112 44 -50 -8 #rect
Cs0 f4 @|StepIcon #fIcon
Cs0 f51 109 288 392 288 #arcP
Cs0 f51 0 0.5658938645158399 0 0 #arcLabel
Cs0 f43 guid 1720C5CCA7DA3932 #txt
Cs0 f43 method navigateToRelatedTasksOfCase(Boolean,Boolean) #txt
Cs0 f43 inParameterDecl '<Boolean isWorkingOnTask,Boolean inFrame> param;' #txt
Cs0 f43 inParameterMapAction 'out.inFrame=param.inFrame;
out.isWorkingOnTask=param.isWorkingOnTask;
' #txt
Cs0 f43 outParameterDecl '<> result;' #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedTasksOfCase(Boolean,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f43 83 275 26 26 -78 23 #rect
Cs0 f43 @|UdMethodIcon #fIcon
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f33 mainOut f36 tail #connect
Cs0 f36 head f31 mainIn #connect
Cs0 f45 mainOut f47 tail #connect
Cs0 f47 head f33 mainIn #connect
Cs0 f43 mainOut f51 tail #connect
Cs0 f51 head f4 mainIn #connect
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f14 mainIn #connect
Cs0 f14 mainOut f9 tail #connect
Cs0 f9 head f7 mainIn #connect
