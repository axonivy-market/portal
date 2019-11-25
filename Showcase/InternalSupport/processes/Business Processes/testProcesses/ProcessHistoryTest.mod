[Ivy]
16193718E2B3D6C4 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcessHistoryTest Big #zClass
Pt0 B #cInfo
Pt0 #process
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
Pt0 @UserDialog f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @PushWFArc f20 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @EndTask f10 '' #zField
Pt0 @UserDialog f11 '' #zField
Pt0 @StartRequest f12 '' #zField
Pt0 @PushWFArc f13 '' #zField
Pt0 @PushWFArc f14 '' #zField
>Proto Pt0 Pt0 ProcessHistoryTest #zField
Pt0 f0 outLink viewProcessHistoryOfResource.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature viewProcessHistoryOfResource() #txt
Pt0 f0 persist false #txt
Pt0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryOfResource.ivp</name>
        <nameStyle>32,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 129 49 30 30 -96 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 433 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 outLink InspectResource.ivp #txt
Pt0 f3 inParamDecl '<> param;' #txt
Pt0 f3 requestEnabled true #txt
Pt0 f3 triggerEnabled false #txt
Pt0 f3 callSignature InspectResource() #txt
Pt0 f3 persist false #txt
Pt0 f3 startName 'Inspect resource' #txt
Pt0 f3 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f3 caseData 'businessCase.attach=true
case.name=Resource A247' #txt
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
Pt0 f3 105 265 30 30 -56 17 #rect
Pt0 f3 @|StartRequestIcon #fIcon
Pt0 f4 processCall 'Functional Processes/SetBusinessEntityId:call(String)' #txt
Pt0 f4 requestActionDecl '<String businessEntityId> param;' #txt
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
Pt0 f4 344 258 128 44 -55 -8 #rect
Pt0 f4 @|CallSubIcon #fIcon
Pt0 f8 713 265 30 30 0 15 #rect
Pt0 f8 @|EndIcon #fIcon
Pt0 f6 actionTable 'out=in1;
' #txt
Pt0 f6 outLinks "TaskA.ivp" #txt
Pt0 f6 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Inspect Resource
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
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
Pt0 f6 585 265 30 30 -48 17 #rect
Pt0 f6 @|TaskSwitchSimpleIcon #fIcon
Pt0 f7 expr out #txt
Pt0 f7 type internaltest.Data #txt
Pt0 f7 var in1 #txt
Pt0 f7 472 280 585 280 #arcP
Pt0 f9 expr data #txt
Pt0 f9 outCond ivp=="TaskA.ivp" #txt
Pt0 f9 615 280 713 280 #arcP
Pt0 f17 dialogId internaltest.ProcessHistoryOfResource #txt
Pt0 f17 startMethod start() #txt
Pt0 f17 requestActionDecl '<> param;' #txt
Pt0 f17 responseActionDecl 'internaltest.Data out;
' #txt
Pt0 f17 responseMappingAction 'out=in;
' #txt
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
Pt0 f17 @|UserDialogIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 159 64 264 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 376 64 433 64 #arcP
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'ivy.case.setProcess("Resource Inspection", "Resource Inspection");' #txt
Pt0 f19 security system #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set process&#xD;
code name</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 192 258 112 44 -33 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f20 expr out #txt
Pt0 f20 135 280 192 280 #arcP
Pt0 f5 expr out #txt
Pt0 f5 304 280 344 280 #arcP
Pt0 f10 425 134 30 30 0 15 #rect
Pt0 f10 @|EndIcon #fIcon
Pt0 f11 dialogId internaltest.ProcessHistoryAsDialogOfResource #txt
Pt0 f11 startMethod start() #txt
Pt0 f11 requestActionDecl '<> param;' #txt
Pt0 f11 responseActionDecl 'internaltest.Data out;
' #txt
Pt0 f11 responseMappingAction 'out=in;
' #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Process History</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 256 127 112 44 -43 -8 #rect
Pt0 f11 @|UserDialogIcon #fIcon
Pt0 f12 outLink viewProcessHistoryAsDialogOfResource.ivp #txt
Pt0 f12 inParamDecl '<> param;' #txt
Pt0 f12 requestEnabled true #txt
Pt0 f12 triggerEnabled false #txt
Pt0 f12 callSignature viewProcessHistoryAsDialogOfResource() #txt
Pt0 f12 persist false #txt
Pt0 f12 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f12 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Pt0 f12 showInStartList 1 #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryAsDialogOfResource.ivp</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f12 @C|.responsibility Everybody #txt
Pt0 f12 121 134 30 30 -121 17 #rect
Pt0 f12 @|StartRequestIcon #fIcon
Pt0 f13 expr out #txt
Pt0 f13 368 149 425 149 #arcP
Pt0 f14 expr out #txt
Pt0 f14 151 149 256 149 #arcP
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
Pt0 f12 mainOut f14 tail #connect
Pt0 f14 head f11 mainIn #connect
Pt0 f11 mainOut f13 tail #connect
Pt0 f13 head f10 mainIn #connect
