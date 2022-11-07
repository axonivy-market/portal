[Ivy]
181604391D11B731 7.5.0 #module
>Proto >Proto Collection #zClass
Ue0 UserSelectionExample Big #zClass
Ue0 B #cInfo
Ue0 #process
Ue0 @AnnotationInP-0n ai ai #zField
Ue0 @TextInP .type .type #zField
Ue0 @TextInP .processKind .processKind #zField
Ue0 @TextInP .xml .xml #zField
Ue0 @TextInP .responsibility .responsibility #zField
Ue0 @StartRequest f0 '' #zField
Ue0 @EndTask f1 '' #zField
Ue0 @UserDialog f3 '' #zField
Ue0 @PushWFArc f4 '' #zField
Ue0 @TaskSwitchSimple f5 '' #zField
Ue0 @TkArc f6 '' #zField
Ue0 @PushWFArc f2 '' #zField
>Proto Ue0 Ue0 UserSelectionExample #zField
Ue0 f0 outLink showUserSelectionExamples.ivp #txt
Ue0 f0 inParamDecl '<> param;' #txt
Ue0 f0 requestEnabled true #txt
Ue0 f0 triggerEnabled false #txt
Ue0 f0 callSignature showUserSelectionExamples() #txt
Ue0 f0 startName 'User Selection Example' #txt
Ue0 f0 caseData businessCase.attach=true #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showUserSelectionExamples.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 81 49 30 30 -80 16 #rect
Ue0 f0 @|StartRequestIcon #fIcon
Ue0 f1 561 49 30 30 0 15 #rect
Ue0 f1 @|EndIcon #fIcon
Ue0 f3 dialogId com.axonivy.portal.components.examples.UserSelectionExample #txt
Ue0 f3 startMethod start() #txt
Ue0 f3 requestActionDecl '<> param;' #txt
Ue0 f3 responseMappingAction 'out=in;
out.selectedUser=result.selectedUser;
' #txt
Ue0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserSelectionExample</name>
    </language>
</elementInfo>
' #txt
Ue0 f3 200 42 144 44 -63 -8 #rect
Ue0 f3 @|UserDialogIcon #fIcon
Ue0 f4 111 64 200 64 #arcP
Ue0 f5 actionTable 'out=in1;
' #txt
Ue0 f5 taskData 'TaskA.DESC=This task is created by User Selection Example
TaskA.NAM=Task created by User Selection Example
TaskA.ROL=in1.selectedUser.getName()
TaskA.TYPE=3' #txt
Ue0 f5 433 49 30 30 0 16 #rect
Ue0 f5 @|TaskSwitchSimpleIcon #fIcon
Ue0 f6 344 64 433 64 #arcP
Ue0 f2 463 64 561 64 #arcP
>Proto Ue0 .type com.axonivy.portal.components.examples.UserSelectionExampleData #txt
>Proto Ue0 .processKind NORMAL #txt
>Proto Ue0 0 0 32 24 18 0 #rect
>Proto Ue0 @|BIcon #fIcon
Ue0 f0 mainOut f4 tail #connect
Ue0 f4 head f3 mainIn #connect
Ue0 f3 mainOut f6 tail #connect
Ue0 f6 head f5 in #connect
Ue0 f5 out f2 tail #connect
Ue0 f2 head f1 mainIn #connect
