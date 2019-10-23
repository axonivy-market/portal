[Ivy]
16583F0F73864543 7.5.0 #module
>Proto >Proto Collection #zClass
Hs0 HideTechnicalStuffs Big #zClass
Hs0 B #cInfo
Hs0 #process
Hs0 @TextInP .type .type #zField
Hs0 @TextInP .processKind .processKind #zField
Hs0 @AnnotationInP-0n ai ai #zField
Hs0 @MessageFlowInP-0n messageIn messageIn #zField
Hs0 @MessageFlowOutP-0n messageOut messageOut #zField
Hs0 @TextInP .xml .xml #zField
Hs0 @TextInP .responsibility .responsibility #zField
Hs0 @StartRequest f0 '' #zField
Hs0 @EndTask f1 '' #zField
Hs0 @StartRequest f3 '' #zField
Hs0 @EndTask f4 '' #zField
Hs0 @GridStep f9 '' #zField
Hs0 @PushWFArc f10 '' #zField
Hs0 @PushWFArc f2 '' #zField
Hs0 @GridStep f11 '' #zField
Hs0 @StartRequest f12 '' #zField
Hs0 @EndTask f13 '' #zField
Hs0 @PushWFArc f14 '' #zField
Hs0 @PushWFArc f15 '' #zField
Hs0 @GridStep f16 '' #zField
Hs0 @TaskSwitchSimple f18 '' #zField
Hs0 @PushWFArc f20 '' #zField
Hs0 @PushWFArc f21 '' #zField
Hs0 @GridStep f17 '' #zField
Hs0 @PushWFArc f5 '' #zField
Hs0 @TkArc f19 '' #zField
Hs0 @InfoButton f22 '' #zField
Hs0 @InfoButton f23 '' #zField
Hs0 @InfoButton f26 '' #zField
Hs0 @StartRequest f27 '' #zField
Hs0 @EndTask f29 '' #zField
Hs0 @CallSub f30 '' #zField
Hs0 @TaskSwitch f31 '' #zField
Hs0 @PushWFArc f33 '' #zField
Hs0 @PushWFArc f34 '' #zField
Hs0 @PushWFArc f36 '' #zField
Hs0 @TkArc f6 '' #zField
>Proto Hs0 Hs0 HideTechnicalStuffs #zField
Hs0 f0 outLink HideTechnicalRole.ivp #txt
Hs0 f0 inParamDecl '<> param;' #txt
Hs0 f0 requestEnabled true #txt
Hs0 f0 triggerEnabled false #txt
Hs0 f0 callSignature HideTechnicalRole() #txt
Hs0 f0 persist false #txt
Hs0 f0 startName 'Hide Technical Role' #txt
Hs0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Hs0 f0 caseData businessCase.attach=true #txt
Hs0 f0 showInStartList 1 #txt
Hs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideTechnicalRole.ivp</name>
    </language>
</elementInfo>
' #txt
Hs0 f0 @C|.responsibility Everybody #txt
Hs0 f0 153 57 30 30 -61 17 #rect
Hs0 f0 @|StartRequestIcon #fIcon
Hs0 f1 545 57 30 30 0 15 #rect
Hs0 f1 @|EndIcon #fIcon
Hs0 f3 outLink createHiddenTechnicalTask.ivp #txt
Hs0 f3 inParamDecl '<> param;' #txt
Hs0 f3 requestEnabled true #txt
Hs0 f3 triggerEnabled false #txt
Hs0 f3 callSignature createHiddenTechnicalTask() #txt
Hs0 f3 persist false #txt
Hs0 f3 startName 'Create hidden technical task' #txt
Hs0 f3 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Hs0 f3 caseData businessCase.attach=true #txt
Hs0 f3 showInStartList 1 #txt
Hs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createHiddenTechnicalTask.ivp</name>
    </language>
</elementInfo>
' #txt
Hs0 f3 @C|.responsibility Everybody #txt
Hs0 f3 144 320 32 32 -86 17 #rect
Hs0 f3 @|StartRequestIcon #fIcon
Hs0 f4 696 320 32 32 0 15 #rect
Hs0 f4 @|EndIcon #fIcon
Hs0 f9 actionTable 'out=in;
' #txt
Hs0 f9 actionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.security.IRole;
IRole role = ivy.session.getSecurityContext().findRole("PortalSystem");
if(#role is initialized) {
	RoleUtils.setProperty(role, RoleUtils.HIDE, "hide");
}' #txt
Hs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide role "PortalSystem"</name>
    </language>
</elementInfo>
' #txt
Hs0 f9 296 50 144 44 -67 -8 #rect
Hs0 f9 @|StepIcon #fIcon
Hs0 f10 expr out #txt
Hs0 f10 183 72 296 72 #arcP
Hs0 f2 expr out #txt
Hs0 f2 440 72 545 72 #arcP
Hs0 f11 actionTable 'out=in;
' #txt
Hs0 f11 actionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.security.IRole;
IRole role = ivy.session.getSecurityContext().findRole("PortalSystem");
if(#role is initialized) {
	RoleUtils.removeProperty(role, RoleUtils.HIDE);
}' #txt
Hs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Unhide role "PortalSystem"</name>
    </language>
</elementInfo>
' #txt
Hs0 f11 287 151 160 44 -74 -8 #rect
Hs0 f11 @|StepIcon #fIcon
Hs0 f12 outLink UnhideTechnicalRole.ivp #txt
Hs0 f12 inParamDecl '<> param;' #txt
Hs0 f12 requestEnabled true #txt
Hs0 f12 triggerEnabled false #txt
Hs0 f12 callSignature UnhideTechnicalRole() #txt
Hs0 f12 persist false #txt
Hs0 f12 startName 'Unhide Technical Role' #txt
Hs0 f12 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Hs0 f12 caseData businessCase.attach=true #txt
Hs0 f12 showInStartList 1 #txt
Hs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UnhideTechnicalRole.ivp</name>
    </language>
</elementInfo>
' #txt
Hs0 f12 @C|.responsibility Everybody #txt
Hs0 f12 152 158 30 30 -68 17 #rect
Hs0 f12 @|StartRequestIcon #fIcon
Hs0 f13 544 158 30 30 0 15 #rect
Hs0 f13 @|EndIcon #fIcon
Hs0 f14 expr out #txt
Hs0 f14 182 173 287 173 #arcP
Hs0 f15 expr out #txt
Hs0 f15 447 173 544 173 #arcP
Hs0 f16 actionTable 'out=in;
' #txt
Hs0 f16 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
TaskUtils.setHidePropertyToHideInPortal(ivy.task);' #txt
Hs0 f16 security system #txt
Hs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide task</name>
    </language>
</elementInfo>
' #txt
Hs0 f16 508 312 120 48 -26 -8 #rect
Hs0 f16 @|StepIcon #fIcon
Hs0 f18 actionTable 'out=in1;
' #txt
Hs0 f18 outLinks "TaskA.ivp" #txt
Hs0 f18 caseData 'case.name=Case contains hidden task' #txt
Hs0 f18 taskData 'TaskA.DESC=Task, which has the HIDE additional property, won''t be displayed in Portal
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Second Hidden Task
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
Hs0 f18 template "" #txt
Hs0 f18 424 320 32 32 0 16 #rect
Hs0 f18 @|TaskSwitchSimpleIcon #fIcon
Hs0 f20 expr data #txt
Hs0 f20 outCond ivp=="TaskA.ivp" #txt
Hs0 f20 456 336 508 336 #arcP
Hs0 f21 expr out #txt
Hs0 f21 628 336 696 336 #arcP
Hs0 f21 0 0.4999999999999999 0 0 #arcLabel
Hs0 f17 actionTable 'out=in;
' #txt
Hs0 f17 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
TaskUtils.setHidePropertyToHideInPortal(ivy.task);' #txt
Hs0 f17 security system #txt
Hs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide task</name>
    </language>
</elementInfo>
' #txt
Hs0 f17 268 312 104 48 -26 -8 #rect
Hs0 f17 @|StepIcon #fIcon
Hs0 f5 expr out #txt
Hs0 f5 176 336 268 336 #arcP
Hs0 f19 expr out #txt
Hs0 f19 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Hs0 f19 var in1 #txt
Hs0 f19 372 336 424 336 #arcP
Hs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example demonstrates the hide technical roles feature of Portal

HOW TO RUN THIS EXAMPLE
Run HideTechnicalRole.ivp to hide the technical role "Portal System". 
After running the process, you should not see the role in role lists of Portal e.g Delegate role list

Run UnhideTechnicalRole.ivp to unhide the role. You should see the role again in role lists.</name>
        <nameStyle>69,7
291,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Hs0 f22 792 58 528 124 -259 -56 #rect
Hs0 f22 @|IBIcon #fIcon
Hs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example demonstates the hide technical tasks feature of Portal

HOW TO RUN THIS EXAMPLE
Run createHiddenTechnicalTask.ivp to create a task named "Second Hidden Task" which will be auto-completed. 
Afterwards, login with a user with TASK_READ_ALL permission to see DONE tasks, the task should not be shown in any Portal task lists.</name>
        <nameStyle>68,7
267,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Hs0 f23 792 266 752 92 -373 -40 #rect
Hs0 f23 @|IBIcon #fIcon
Hs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example demonstates the hide technical cases feature of Portal

HOW TO RUN THIS EXAMPLE
Run createHiddenTechnicalCase.ivp to create a case named "Create hidden technical case" and 2 tasks: "Report" &amp; "Report and hide case".
Run "Report and hide case" task.  Afterwards, login with a user with CASE_READ_ALL permission to see DONE cases, the case should not be shown in every Portal case lists.
The tasks of the case should be hidden also in every Portal task lists. </name>
        <nameStyle>68,7
403,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Hs0 f26 798 452 960 108 -477 -48 #rect
Hs0 f26 @|IBIcon #fIcon
Hs0 f27 outLink createHiddenTechnicalCase.ivp #txt
Hs0 f27 inParamDecl '<> param;' #txt
Hs0 f27 requestEnabled true #txt
Hs0 f27 triggerEnabled false #txt
Hs0 f27 callSignature createHiddenTechnicalCase() #txt
Hs0 f27 persist false #txt
Hs0 f27 startName 'Create hidden technical case' #txt
Hs0 f27 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Hs0 f27 caseData 'businessCase.attach=true
case.name=Repair Computer' #txt
Hs0 f27 showInStartList 1 #txt
Hs0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createHiddenTechnicalCase.ivp</name>
    </language>
</elementInfo>
' #txt
Hs0 f27 @C|.responsibility Everybody #txt
Hs0 f27 121 471 30 30 -87 17 #rect
Hs0 f27 @|StartRequestIcon #fIcon
Hs0 f29 713 471 30 30 0 15 #rect
Hs0 f29 @|EndIcon #fIcon
Hs0 f30 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Hs0 f30 requestActionDecl '<Boolean hideBusinessCase> param;' #txt
Hs0 f30 requestMappingAction 'param.hideBusinessCase=true;
' #txt
Hs0 f30 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Hs0 f30 responseMappingAction 'out=in;
' #txt
Hs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Hs0 f30 536 464 112 44 -48 -8 #rect
Hs0 f30 @|CallSubIcon #fIcon
Hs0 f31 actionTable 'out=in1;
' #txt
Hs0 f31 outLinks "TaskA.ivp","TaskB.ivp" #txt
Hs0 f31 taskData 'TaskA.DESC=Report and hide case
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Report and hide case
TaskA.PRI=2
TaskA.ROL=AXONIVY_PORTAL_ADMIN
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.DESC=Report but don''t hide case. This task should be hidden if you run "Report and hide case" task.
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Report
TaskB.PRI=2
TaskB.ROL=CREATOR
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Hs0 f31 template "" #txt
Hs0 f31 456 470 32 32 0 16 #rect
Hs0 f31 @|TaskSwitchIcon #fIcon
Hs0 f33 expr data #txt
Hs0 f33 outCond ivp=="TaskB.ivp" #txt
Hs0 f33 472 502 728 501 #arcP
Hs0 f33 1 472 560 #addKink
Hs0 f33 2 728 560 #addKink
Hs0 f33 1 0.5013020833333334 0 0 #arcLabel
Hs0 f34 expr out #txt
Hs0 f34 648 486 713 486 #arcP
Hs0 f36 expr data #txt
Hs0 f36 outCond ivp=="TaskA.ivp" #txt
Hs0 f36 488 486 536 486 #arcP
Hs0 f6 expr out #txt
Hs0 f6 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Hs0 f6 var in1 #txt
Hs0 f6 151 486 456 486 #arcP
>Proto Hs0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Hs0 .processKind NORMAL #txt
>Proto Hs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Hide Technical Stuffs</swimlaneLabel>
        <swimlaneLabel>Hide technical roles</swimlaneLabel>
        <swimlaneLabel>Hide technical tasks</swimlaneLabel>
        <swimlaneLabel>Hide technical cases</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>640</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneSpaceBefore>32</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Hs0 0 0 32 24 18 0 #rect
>Proto Hs0 @|BIcon #fIcon
Hs0 f0 mainOut f10 tail #connect
Hs0 f10 head f9 mainIn #connect
Hs0 f9 mainOut f2 tail #connect
Hs0 f2 head f1 mainIn #connect
Hs0 f12 mainOut f14 tail #connect
Hs0 f14 head f11 mainIn #connect
Hs0 f11 mainOut f15 tail #connect
Hs0 f15 head f13 mainIn #connect
Hs0 f18 out f20 tail #connect
Hs0 f20 head f16 mainIn #connect
Hs0 f16 mainOut f21 tail #connect
Hs0 f21 head f4 mainIn #connect
Hs0 f3 mainOut f5 tail #connect
Hs0 f5 head f17 mainIn #connect
Hs0 f17 mainOut f19 tail #connect
Hs0 f19 head f18 in #connect
Hs0 f30 mainOut f34 tail #connect
Hs0 f34 head f29 mainIn #connect
Hs0 f31 out f36 tail #connect
Hs0 f36 head f30 mainIn #connect
Hs0 f31 out f33 tail #connect
Hs0 f33 head f29 mainIn #connect
Hs0 f27 mainOut f6 tail #connect
Hs0 f6 head f31 in #connect
