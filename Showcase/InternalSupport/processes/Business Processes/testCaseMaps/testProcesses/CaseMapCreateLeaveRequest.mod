[Ivy]
16E3F9428CFA3D20 7.5.0 #module
>Proto >Proto Collection #zClass
Ct0 CaseMapCreateLeaveRequest Big #zClass
Ct0 B #cInfo
Ct0 #process
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @StartRequest f0 '' #zField
Ct0 @EndTask f1 '' #zField
Ct0 @UserTask f3 '' #zField
Ct0 @PushWFArc f2 '' #zField
Ct0 @GridStep f5 '' #zField
Ct0 @PushWFArc f6 '' #zField
Ct0 @TkArc f9 '' #zField
>Proto Ct0 Ct0 CaseMapCreateLeaveRequest #zField
Ct0 f0 outLink newLeaveRequest.ivp #txt
Ct0 f0 inParamDecl '<> param;' #txt
Ct0 f0 requestEnabled true #txt
Ct0 f0 triggerEnabled false #txt
Ct0 f0 callSignature newLeaveRequest() #txt
Ct0 f0 taskData 'TaskTriggered.NAM=Start Case Map\: Create Leave Request 1' #txt
Ct0 f0 caseData 'businessCase.attach=true
case.description=Case Map\: Leave Request
case.name=Case Map\: Leave Request
customFields.STRING.embedInFrame="false"' #txt
Ct0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>newLeaveRequest.ivp</name>
    </language>
</elementInfo>
' #txt
Ct0 f0 @C|.responsibility Everybody #txt
Ct0 f0 81 49 30 30 -21 17 #rect
Ct0 f0 @|StartRequestIcon #fIcon
Ct0 f1 497 49 30 30 0 15 #rect
Ct0 f1 @|EndIcon #fIcon
Ct0 f3 dialogId internaltest.ui.NewLeaveRequest #txt
Ct0 f3 startMethod start() #txt
Ct0 f3 requestActionDecl '<> param;' #txt
Ct0 f3 responseMappingAction 'out=in;
' #txt
Ct0 f3 caseData 'case.description=Case Map\: Leave Request
case.name=Case Map\: Leave Request' #txt
Ct0 f3 taskData 'TaskA.NAM=Case Map Leave Request' #txt
Ct0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NewLeaveRequest</name>
    </language>
</elementInfo>
' #txt
Ct0 f3 328 42 112 44 -52 -8 #rect
Ct0 f3 @|UserTaskIcon #fIcon
Ct0 f2 440 64 497 64 #arcP
Ct0 f5 actionTable 'out=in;
' #txt
Ct0 f5 actionCode 'ivy.case.getBusinessCase().setName("Business Case Map: Leave Request");' #txt
Ct0 f5 security system #txt
Ct0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case name</name>
    </language>
</elementInfo>
' #txt
Ct0 f5 168 42 112 44 -41 -8 #rect
Ct0 f5 @|StepIcon #fIcon
Ct0 f6 111 64 168 64 #arcP
Ct0 f9 280 64 328 64 #arcP
>Proto Ct0 .type internaltest.CaseMapCreateLeaveRequestData #txt
>Proto Ct0 .processKind NORMAL #txt
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct0 f3 out f2 tail #connect
Ct0 f2 head f1 mainIn #connect
Ct0 f0 mainOut f6 tail #connect
Ct0 f6 head f5 mainIn #connect
Ct0 f5 mainOut f9 tail #connect
Ct0 f9 head f3 in #connect
