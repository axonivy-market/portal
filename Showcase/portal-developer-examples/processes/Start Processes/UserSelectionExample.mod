[Ivy]
170514494945ADB9 9.2.0 #module
>Proto >Proto Collection #zClass
Ue0 UserSelectionExample Big #zClass
Ue0 B #cInfo
Ue0 #process
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
Ue0 f0 outLink start.ivp #txt
Ue0 f0 inParamDecl '<> param;' #txt
Ue0 f0 requestEnabled true #txt
Ue0 f0 triggerEnabled false #txt
Ue0 f0 callSignature start() #txt
Ue0 f0 startName 'User selection component example' #txt
Ue0 f0 startDescription 'Loading all users, loading users by predefined role names, floating label,...' #txt
Ue0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Ue0 f0 showInStartList 1 #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 81 49 30 30 -21 17 #rect
Ue0 f0 @|StartRequestIcon #fIcon
Ue0 f1 433 49 30 30 0 15 #rect
Ue0 f1 @|EndIcon #fIcon
Ue0 f3 dialogId com.axonivy.portal.developerexamples.UserSelectionExample #txt
Ue0 f3 startMethod start() #txt
Ue0 f3 requestActionDecl '<> param;' #txt
Ue0 f3 responseMappingAction 'out=in;
out.selectedUser=result.selectedUserForDefinedRoles;
' #txt
Ue0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserSelectionExample</name>
    </language>
</elementInfo>
' #txt
Ue0 f3 152 42 144 44 -63 -8 #rect
Ue0 f3 @|UserDialogIcon #fIcon
Ue0 f4 111 64 152 64 #arcP
Ue0 f5 actionTable 'out=in1;
' #txt
Ue0 f5 taskData 'TaskA.DESC=This task is created by User Selection Component Example
TaskA.NAM=Task created by User Selection Component Example
TaskA.ROL=in1.selectedUser.getName()
TaskA.TYPE=3' #txt
Ue0 f5 353 49 30 30 0 16 #rect
Ue0 f5 @|TaskSwitchSimpleIcon #fIcon
Ue0 f6 296 64 353 64 #arcP
Ue0 f2 383 64 433 64 #arcP
>Proto Ue0 .type com.axonivy.portal.developerexamples.UserSelectionExampleData #txt
>Proto Ue0 .processKind NORMAL #txt
>Proto Ue0 0 0 32 24 18 0 #rect
>Proto Ue0 @|BIcon #fIcon
Ue0 f0 mainOut f4 tail #connect
Ue0 f4 head f3 mainIn #connect
Ue0 f3 mainOut f6 tail #connect
Ue0 f6 head f5 in #connect
Ue0 f5 out f2 tail #connect
Ue0 f2 head f1 mainIn #connect
