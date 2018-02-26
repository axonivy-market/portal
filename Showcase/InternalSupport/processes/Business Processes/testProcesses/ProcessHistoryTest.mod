[Ivy]
16193718E2B3D6C4 3.20 #module
>Proto >Proto Collection #zClass
Pt0 ProcessHistoryTest Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @StartRequest f3 '' #zField
Pt0 @CallSub f4 '' #zField
Pt0 @EndTask f8 '' #zField
Pt0 @TaskSwitchSimple f6 '' #zField
Pt0 @TkArc f7 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @RichDialog f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @PushWFArc f20 '' #zField
Pt0 @PushWFArc f5 '' #zField
>Proto Pt0 Pt0 ProcessHistoryTest #zField
Pt0 f0 outLink viewProcessHistoryOfResource.ivp #txt
Pt0 f0 type internaltest.Data #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 actionDecl 'internaltest.Data out;
' #txt
Pt0 f0 guid 16193718E30E34C0 #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature viewProcessHistoryOfResource() #txt
Pt0 f0 persist false #txt
Pt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 caseData businessCase.attach=true #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryOfResource.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 129 49 30 30 -96 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 type internaltest.Data #txt
Pt0 f1 433 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 outLink InspectResource.ivp #txt
Pt0 f3 type internaltest.Data #txt
Pt0 f3 inParamDecl '<> param;' #txt
Pt0 f3 actionDecl 'internaltest.Data out;
' #txt
Pt0 f3 guid 1619371B302A51B3 #txt
Pt0 f3 requestEnabled true #txt
Pt0 f3 triggerEnabled false #txt
Pt0 f3 callSignature InspectResource() #txt
Pt0 f3 persist false #txt
Pt0 f3 startName 'Inspect resource' #txt
Pt0 f3 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f3 caseData 'case.name=Resource
businessCase.attach=true' #txt
Pt0 f3 showInStartList 1 #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InspectResource.ivp</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 @C|.responsibility Everybody #txt
Pt0 f3 81 177 30 30 -56 17 #rect
Pt0 f3 @|StartRequestIcon #fIcon
Pt0 f4 type internaltest.Data #txt
Pt0 f4 processCall 'Functional Processes/SetBusinessEntityId:call(String)' #txt
Pt0 f4 doCall true #txt
Pt0 f4 requestActionDecl '<java.lang.String businessEntityId> param;
' #txt
Pt0 f4 requestMappingAction 'param.businessEntityId="resource1";
' #txt
Pt0 f4 responseActionDecl 'internaltest.Data out;
' #txt
Pt0 f4 responseMappingAction 'out=in;
' #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SetBusinessEntityId</name>
    </language>
</elementInfo>
' #txt
Pt0 f4 320 170 128 44 -55 -8 #rect
Pt0 f4 @|CallSubIcon #fIcon
Pt0 f8 type internaltest.Data #txt
Pt0 f8 689 177 30 30 0 15 #rect
Pt0 f8 @|EndIcon #fIcon
Pt0 f6 actionDecl 'internaltest.Data out;
' #txt
Pt0 f6 actionTable 'out=in1;
' #txt
Pt0 f6 outTypes "internaltest.Data" #txt
Pt0 f6 outLinks "TaskA.ivp" #txt
Pt0 f6 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Inspect Resource
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f6 type internaltest.Data #txt
Pt0 f6 template "" #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Inspect Resource</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 561 177 30 30 -48 17 #rect
Pt0 f6 @|TaskSwitchSimpleIcon #fIcon
Pt0 f7 expr out #txt
Pt0 f7 type internaltest.Data #txt
Pt0 f7 var in1 #txt
Pt0 f7 448 192 561 192 #arcP
Pt0 f9 expr data #txt
Pt0 f9 outCond ivp=="TaskA.ivp" #txt
Pt0 f9 591 192 689 192 #arcP
Pt0 f17 targetWindow NEW #txt
Pt0 f17 targetDisplay TOP #txt
Pt0 f17 richDialogId internaltest.ProcessHistoryOfResource #txt
Pt0 f17 startMethod start() #txt
Pt0 f17 type internaltest.Data #txt
Pt0 f17 requestActionDecl '<> param;' #txt
Pt0 f17 responseActionDecl 'internaltest.Data out;
' #txt
Pt0 f17 responseMappingAction 'out=in;
' #txt
Pt0 f17 isAsynch false #txt
Pt0 f17 isInnerRd false #txt
Pt0 f17 userContext '* ' #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Process History</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 264 42 112 44 -43 -8 #rect
Pt0 f17 @|RichDialogIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 159 64 264 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 376 64 433 64 #arcP
Pt0 f19 actionDecl 'internaltest.Data out;
' #txt
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'ivy.case.setProcess("Resource Inspection", "Resource Inspection");' #txt
Pt0 f19 security system #txt
Pt0 f19 type internaltest.Data #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set process&#xD;
code name</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 168 170 112 44 -33 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f20 expr out #txt
Pt0 f20 111 192 168 192 #arcP
Pt0 f5 expr out #txt
Pt0 f5 280 192 320 192 #arcP
>Proto Pt0 .type internaltest.Data #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f4 mainOut f7 tail #connect
Pt0 f7 head f6 in #connect
Pt0 f6 out f9 tail #connect
Pt0 f9 head f8 mainIn #connect
Pt0 f0 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f17 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
Pt0 f3 mainOut f20 tail #connect
Pt0 f20 head f19 mainIn #connect
Pt0 f19 mainOut f5 tail #connect
Pt0 f5 head f4 mainIn #connect
