[Ivy]
153CACC26D0D4C3D 3.20 #module
>Proto >Proto Collection #zClass
Dt0 DataCreation Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .resExport .resExport #zField
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartRequest f0 '' #zField
Dt0 @EndTask f1 '' #zField
Dt0 @EndTask f5 '' #zField
Dt0 @EndTask f6 '' #zField
Dt0 @GridStep f23 '' #zField
Dt0 @StartRequest f25 '' #zField
Dt0 @Trigger f28 '' #zField
Dt0 @GridStep f30 '' #zField
Dt0 @Alternative f32 '' #zField
Dt0 @PushWFArc f33 '' #zField
Dt0 @PushWFArc f29 '' #zField
Dt0 @EndTask f34 '' #zField
Dt0 @PushWFArc f35 '' #zField
Dt0 @RichDialog f27 '' #zField
Dt0 @PushWFArc f2 '' #zField
Dt0 @GridStep f41 '' #zField
Dt0 @PushWFArc f42 '' #zField
Dt0 @PushWFArc f26 '' #zField
Dt0 @PushWFArc f43 '' #zField
Dt0 @RichDialog f24 '' #zField
Dt0 @PushWFArc f37 '' #zField
Dt0 @PushWFArc f31 '' #zField
Dt0 @TaskSwitch f3 '' #zField
Dt0 @TkArc f4 '' #zField
Dt0 @PushWFArc f7 '' #zField
Dt0 @PushWFArc f8 '' #zField
Dt0 @PushWFArc f9 '' #zField
Dt0 @StartRequest f10 '' #zField
Dt0 @EndTask f11 '' #zField
Dt0 @RichDialog f13 '' #zField
Dt0 @PushWFArc f14 '' #zField
Dt0 @PushWFArc f12 '' #zField
Dt0 @StartRequest f15 '' #zField
Dt0 @GridStep f16 '' #zField
Dt0 @EndTask f17 '' #zField
Dt0 @PushWFArc f18 '' #zField
Dt0 @PushWFArc f19 '' #zField
Dt0 @StartRequest f20 '' #zField
Dt0 @EndTask f21 '' #zField
Dt0 @GridStep f22 '' #zField
Dt0 @PushWFArc f36 '' #zField
Dt0 @PushWFArc f38 '' #zField
>Proto Dt0 Dt0 DataCreation #zField
Dt0 f0 outLink createTasks.ivp #txt
Dt0 f0 type portalKit_test.DataCreationData #txt
Dt0 f0 inParamDecl '<java.lang.String taskStructureInfo> param;' #txt
Dt0 f0 inParamTable 'out.taskStructureInfo=param.taskStructureInfo;
' #txt
Dt0 f0 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f0 guid 153CACC26E436D14 #txt
Dt0 f0 requestEnabled false #txt
Dt0 f0 triggerEnabled true #txt
Dt0 f0 callSignature createTasks(String) #txt
Dt0 f0 persist false #txt
Dt0 f0 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f0 showInStartList 1 #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTasks(String)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f0 @C|.responsibility Everybody #txt
Dt0 f0 35 323 26 26 14 0 #rect
Dt0 f0 @|StartRequestIcon #fIcon
Dt0 f1 type portalKit_test.DataCreationData #txt
Dt0 f1 467 203 26 26 14 0 #rect
Dt0 f1 @|EndIcon #fIcon
Dt0 f5 type portalKit_test.DataCreationData #txt
Dt0 f5 467 243 26 26 14 0 #rect
Dt0 f5 @|EndIcon #fIcon
Dt0 f6 type portalKit_test.DataCreationData #txt
Dt0 f6 467 275 26 26 14 0 #rect
Dt0 f6 @|EndIcon #fIcon
Dt0 f23 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f23 actionTable 'out=in;
' #txt
Dt0 f23 actionCode 'in.caseName = in.taskStructureInfo + "_" + ivy.case.getId();' #txt
Dt0 f23 type portalKit_test.DataCreationData #txt
Dt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case name</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f23 182 324 36 24 -33 -36 #rect
Dt0 f23 @|StepIcon #fIcon
Dt0 f25 outLink start.ivp #txt
Dt0 f25 type portalKit_test.DataCreationData #txt
Dt0 f25 inParamDecl '<> param;' #txt
Dt0 f25 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f25 guid 153CAEDE745C4D37 #txt
Dt0 f25 requestEnabled true #txt
Dt0 f25 triggerEnabled false #txt
Dt0 f25 callSignature start() #txt
Dt0 f25 persist false #txt
Dt0 f25 startName 'Create test data' #txt
Dt0 f25 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f25 showInStartList 1 #txt
Dt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f25 @C|.responsibility Everybody #txt
Dt0 f25 35 83 26 26 14 0 #rect
Dt0 f25 @|StartRequestIcon #fIcon
Dt0 f28 type portalKit_test.DataCreationData #txt
Dt0 f28 processCall 'Start Processes/DataCreation:createTasks(String)' #txt
Dt0 f28 doCall true #txt
Dt0 f28 requestActionDecl '<java.lang.String taskStructureInfo> param;
' #txt
Dt0 f28 requestMappingAction 'param.taskStructureInfo=in.handler.getNextTaskStructureInfo();
' #txt
Dt0 f28 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f28 responseMappingAction 'out=in;
' #txt
Dt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DataCreation</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f28 502 84 36 24 20 -2 #rect
Dt0 f28 @|TriggerIcon #fIcon
Dt0 f30 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f30 actionTable 'out=in;
' #txt
Dt0 f30 actionCode 'import ch.ivy.addon.portalkit.test.util.DataCreationHandler;
in.handler = new DataCreationHandler(in.numOfCats, in.numOfSubCats, in.numOfCasesPerSubCat);
' #txt
Dt0 f30 type portalKit_test.DataCreationData #txt
Dt0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init handler</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f30 214 84 36 24 -29 -36 #rect
Dt0 f30 @|StepIcon #fIcon
Dt0 f32 type portalKit_test.DataCreationData #txt
Dt0 f32 282 82 28 28 14 0 #rect
Dt0 f32 @|AlternativeIcon #fIcon
Dt0 f33 expr out #txt
Dt0 f33 250 96 282 96 #arcP
Dt0 f29 expr in #txt
Dt0 f29 outCond in.handler.createMoreTasks() #txt
Dt0 f29 310 96 502 96 #arcP
Dt0 f34 type portalKit_test.DataCreationData #txt
Dt0 f34 571 139 26 26 14 0 #rect
Dt0 f34 @|EndIcon #fIcon
Dt0 f35 expr in #txt
Dt0 f35 296 110 571 152 #arcP
Dt0 f35 1 296 152 #addKink
Dt0 f35 1 0.4965635738831616 0 0 #arcLabel
Dt0 f27 targetWindow NEW #txt
Dt0 f27 targetDisplay TOP #txt
Dt0 f27 richDialogId ch.ivy.addon.portalkit.test.DefaultDialog #txt
Dt0 f27 startMethod start() #txt
Dt0 f27 type portalKit_test.DataCreationData #txt
Dt0 f27 requestActionDecl '<> param;' #txt
Dt0 f27 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f27 responseMappingAction 'out=in;
' #txt
Dt0 f27 isAsynch false #txt
Dt0 f27 isInnerRd false #txt
Dt0 f27 userContext '* ' #txt
Dt0 f27 382 252 36 24 20 -2 #rect
Dt0 f27 @|RichDialogIcon #fIcon
Dt0 f2 expr out #txt
Dt0 f2 418 253 468 222 #arcP
Dt0 f41 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f41 actionTable 'out=in;
' #txt
Dt0 f41 actionCode Thread.sleep(10); #txt
Dt0 f41 type portalKit_test.DataCreationData #txt
Dt0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sleep a bit before
triggering new process</name>
        <nameStyle>41,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f41 326 36 36 24 28 -17 #rect
Dt0 f41 @|StepIcon #fIcon
Dt0 f42 expr out #txt
Dt0 f42 520 84 362 48 #arcP
Dt0 f42 1 520 48 #addKink
Dt0 f42 1 0.48487282617628363 0 0 #arcLabel
Dt0 f26 expr out #txt
Dt0 f26 326 48 296 82 #arcP
Dt0 f26 1 296 48 #addKink
Dt0 f26 1 0.48487282617628363 0 0 #arcLabel
Dt0 f43 expr out #txt
Dt0 f43 61 336 182 336 #arcP
Dt0 f24 targetWindow NEW:card: #txt
Dt0 f24 targetDisplay TOP #txt
Dt0 f24 richDialogId ch.ivy.addon.portalkit.test.DataCreation #txt
Dt0 f24 startMethod start() #txt
Dt0 f24 type portalKit_test.DataCreationData #txt
Dt0 f24 requestActionDecl '<> param;' #txt
Dt0 f24 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f24 responseMappingAction 'out=in;
out.numOfCasesPerSubCat=result.numOfCasesPerSubCat;
out.numOfCats=result.numOfCats;
out.numOfSubCats=result.numOfSubCats;
' #txt
Dt0 f24 windowConfiguration '* ' #txt
Dt0 f24 isAsynch false #txt
Dt0 f24 isInnerRd false #txt
Dt0 f24 userContext '* ' #txt
Dt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>input number of tasks</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f24 126 84 36 24 -70 -34 #rect
Dt0 f24 @|RichDialogIcon #fIcon
Dt0 f37 expr out #txt
Dt0 f37 61 96 126 96 #arcP
Dt0 f31 expr out #txt
Dt0 f31 162 96 214 96 #arcP
Dt0 f3 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f3 actionTable 'out=in1;
' #txt
Dt0 f3 outTypes "portalKit_test.DataCreationData","portalKit_test.DataCreationData","portalKit_test.DataCreationData" #txt
Dt0 f3 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Dt0 f3 caseData case.name=<%\=in1.caseName%> #txt
Dt0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.caseName%>_A
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=<%\=in1.caseName%>_B
TaskB.PRI=2
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=<%\=in1.caseName%>_C
TaskC.PRI=2
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0' #txt
Dt0 f3 type portalKit_test.DataCreationData #txt
Dt0 f3 template "" #txt
Dt0 f3 274 322 28 28 14 0 #rect
Dt0 f3 @|TaskSwitchIcon #fIcon
Dt0 f4 expr out #txt
Dt0 f4 type portalKit_test.DataCreationData #txt
Dt0 f4 var in1 #txt
Dt0 f4 218 336 274 336 #arcP
Dt0 f7 expr data #txt
Dt0 f7 outCond ivp=="TaskA.ivp" #txt
Dt0 f7 297 331 382 276 #arcP
Dt0 f8 expr data #txt
Dt0 f8 outCond ivp=="TaskB.ivp" #txt
Dt0 f8 298 332 467 261 #arcP
Dt0 f9 expr data #txt
Dt0 f9 outCond ivp=="TaskC.ivp" #txt
Dt0 f9 299 333 467 291 #arcP
Dt0 f10 outLink destroyTask.ivp #txt
Dt0 f10 type portalKit_test.DataCreationData #txt
Dt0 f10 inParamDecl '<> param;' #txt
Dt0 f10 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f10 guid 1559AE1CBC310259 #txt
Dt0 f10 requestEnabled true #txt
Dt0 f10 triggerEnabled false #txt
Dt0 f10 callSignature destroyTask() #txt
Dt0 f10 persist false #txt
Dt0 f10 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f10 showInStartList 1 #txt
Dt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DestroyTask</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f10 @C|.responsibility Everybody #txt
Dt0 f10 35 467 26 26 14 0 #rect
Dt0 f10 @|StartRequestIcon #fIcon
Dt0 f11 type portalKit_test.DataCreationData #txt
Dt0 f11 307 467 26 26 14 0 #rect
Dt0 f11 @|EndIcon #fIcon
Dt0 f13 targetWindow NEW:card: #txt
Dt0 f13 targetDisplay TOP #txt
Dt0 f13 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.tasks.DestroyTaskPage #txt
Dt0 f13 startMethod start() #txt
Dt0 f13 type portalKit_test.DataCreationData #txt
Dt0 f13 requestActionDecl '<> param;' #txt
Dt0 f13 responseActionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f13 responseMappingAction 'out=in;
' #txt
Dt0 f13 windowConfiguration '* ' #txt
Dt0 f13 isAsynch false #txt
Dt0 f13 isInnerRd false #txt
Dt0 f13 userContext '* ' #txt
Dt0 f13 174 468 36 24 20 -2 #rect
Dt0 f13 @|RichDialogIcon #fIcon
Dt0 f14 expr out #txt
Dt0 f14 61 480 174 480 #arcP
Dt0 f12 expr out #txt
Dt0 f12 210 480 307 480 #arcP
Dt0 f15 outLink createTestUser.ivp #txt
Dt0 f15 type portalKit_test.DataCreationData #txt
Dt0 f15 inParamDecl '<> param;' #txt
Dt0 f15 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f15 guid 1608D2E8AE8B452C #txt
Dt0 f15 requestEnabled true #txt
Dt0 f15 triggerEnabled false #txt
Dt0 f15 callSignature createTestUser() #txt
Dt0 f15 persist false #txt
Dt0 f15 startName 'Create test user' #txt
Dt0 f15 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f15 caseData businessCase.attach=true #txt
Dt0 f15 showInStartList 1 #txt
Dt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTestUser.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f15 @C|.responsibility Everybody #txt
Dt0 f15 81 561 30 30 -51 17 #rect
Dt0 f15 @|StartRequestIcon #fIcon
Dt0 f16 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f16 actionTable 'out=in;
' #txt
Dt0 f16 actionCode 'import java.util.Locale;
if (ivy.wf.getSecurityContext().findUser("test_change_password_user") != null) {
	ivy.wf.getSecurityContext().deleteUser("test_change_password_user");
}
ivy.wf.getSecurityContext().createUser("test_change_password_user", "Elton", "123", Locale.ENGLISH, "", "");' #txt
Dt0 f16 security system #txt
Dt0 f16 type portalKit_test.DataCreationData #txt
Dt0 f16 152 554 112 44 0 -8 #rect
Dt0 f16 @|StepIcon #fIcon
Dt0 f17 type portalKit_test.DataCreationData #txt
Dt0 f17 321 561 30 30 0 15 #rect
Dt0 f17 @|EndIcon #fIcon
Dt0 f18 expr out #txt
Dt0 f18 264 576 321 576 #arcP
Dt0 f19 expr out #txt
Dt0 f19 111 576 152 576 #arcP
Dt0 f20 outLink createRelatedTasksTestUser.ivp #txt
Dt0 f20 type portalKit_test.DataCreationData #txt
Dt0 f20 inParamDecl '<> param;' #txt
Dt0 f20 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f20 guid 16178D3DE23E3373 #txt
Dt0 f20 requestEnabled true #txt
Dt0 f20 triggerEnabled false #txt
Dt0 f20 callSignature createRelatedTasksTestUser() #txt
Dt0 f20 persist false #txt
Dt0 f20 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f20 caseData businessCase.attach=true #txt
Dt0 f20 showInStartList 1 #txt
Dt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createRelatedTasksTestUser.ivp</name>
    </language>
</elementInfo>
' #txt
Dt0 f20 @C|.responsibility Everybody #txt
Dt0 f20 65 721 30 30 -75 23 #rect
Dt0 f20 @|StartRequestIcon #fIcon
Dt0 f21 type portalKit_test.DataCreationData #txt
Dt0 f21 337 721 30 30 0 15 #rect
Dt0 f21 @|EndIcon #fIcon
Dt0 f22 actionDecl 'portalKit_test.DataCreationData out;
' #txt
Dt0 f22 actionTable 'out=in;
' #txt
Dt0 f22 actionCode 'if (ivy.wf.getSecurityContext().findUser("test_related_tasks_user") != null) {
	ivy.wf.getSecurityContext().deleteUser("test_related_tasks_user");
}
ivy.wf.getSecurityContext().createUser("test_related_tasks_user", "Related Tasks User", "+d3m0++", java.util.Locale.ENGLISH, "", "");' #txt
Dt0 f22 security system #txt
Dt0 f22 type portalKit_test.DataCreationData #txt
Dt0 f22 168 714 112 44 0 -8 #rect
Dt0 f22 @|StepIcon #fIcon
Dt0 f36 expr out #txt
Dt0 f36 95 736 168 736 #arcP
Dt0 f38 expr out #txt
Dt0 f38 280 736 337 736 #arcP
>Proto Dt0 .type portalKit_test.DataCreationData #txt
>Proto Dt0 .processKind NORMAL #txt
>Proto Dt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f30 mainOut f33 tail #connect
Dt0 f33 head f32 in #connect
Dt0 f32 out f29 tail #connect
Dt0 f29 head f28 mainIn #connect
Dt0 f32 out f35 tail #connect
Dt0 f35 head f34 mainIn #connect
Dt0 f27 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f28 mainOut f42 tail #connect
Dt0 f42 head f41 mainIn #connect
Dt0 f41 mainOut f26 tail #connect
Dt0 f26 head f32 in #connect
Dt0 f0 mainOut f43 tail #connect
Dt0 f43 head f23 mainIn #connect
Dt0 f25 mainOut f37 tail #connect
Dt0 f37 head f24 mainIn #connect
Dt0 f24 mainOut f31 tail #connect
Dt0 f31 head f30 mainIn #connect
Dt0 f23 mainOut f4 tail #connect
Dt0 f4 head f3 in #connect
Dt0 f3 out f7 tail #connect
Dt0 f7 head f27 mainIn #connect
Dt0 f3 out f8 tail #connect
Dt0 f8 head f5 mainIn #connect
Dt0 f3 out f9 tail #connect
Dt0 f9 head f6 mainIn #connect
Dt0 f10 mainOut f14 tail #connect
Dt0 f14 head f13 mainIn #connect
Dt0 f13 mainOut f12 tail #connect
Dt0 f12 head f11 mainIn #connect
Dt0 f16 mainOut f18 tail #connect
Dt0 f18 head f17 mainIn #connect
Dt0 f15 mainOut f19 tail #connect
Dt0 f19 head f16 mainIn #connect
Dt0 f20 mainOut f36 tail #connect
Dt0 f36 head f22 mainIn #connect
Dt0 f22 mainOut f38 tail #connect
Dt0 f38 head f21 mainIn #connect
