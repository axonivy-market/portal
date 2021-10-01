[Ivy]
1702D31A0F4C89BB 7.5.0 #module
>Proto >Proto Collection #zClass
Ut0 UserSelectionExample Big #zClass
Ut0 B #cInfo
Ut0 #process
Ut0 @TextInP .type .type #zField
Ut0 @TextInP .processKind .processKind #zField
Ut0 @TextInP .xml .xml #zField
Ut0 @TextInP .responsibility .responsibility #zField
Ut0 @StartRequest f0 '' #zField
Ut0 @EndTask f1 '' #zField
Ut0 @UserDialog f9 '' #zField
Ut0 @PushWFArc f10 '' #zField
Ut0 @TaskSwitchSimple f3 '' #zField
Ut0 @TkArc f4 '' #zField
Ut0 @PushWFArc f2 '' #zField
>Proto Ut0 Ut0 UserSelectionExample #zField
Ut0 f0 outLink start.ivp #txt
Ut0 f0 inParamDecl '<> param;' #txt
Ut0 f0 requestEnabled true #txt
Ut0 f0 triggerEnabled false #txt
Ut0 f0 callSignature start() #txt
Ut0 f0 startName 'User selection component example' #txt
Ut0 f0 startDescription 'Loading all users, loading users by predefined role names, floating label,...' #txt
Ut0 f0 caseData businessCase.attach=true #txt
Ut0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ut0 f0 @C|.responsibility Everybody #txt
Ut0 f0 81 49 30 30 -19 20 #rect
Ut0 f0 @|StartRequestIcon #fIcon
Ut0 f1 473 49 30 30 0 15 #rect
Ut0 f1 @|EndIcon #fIcon
Ut0 f9 dialogId ch.ivyteam.ivy.project.portal.examples.UserSelectionExample #txt
Ut0 f9 startMethod start() #txt
Ut0 f9 requestActionDecl '<> param;' #txt
Ut0 f9 responseMappingAction 'out=in;
out.selectedUser=result.selectedUserForDefinedRoles;
' #txt
Ut0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserSelectionExample</name>
    </language>
</elementInfo>
' #txt
Ut0 f9 168 42 144 44 -63 -8 #rect
Ut0 f9 @|UserDialogIcon #fIcon
Ut0 f10 111 64 168 64 #arcP
Ut0 f3 actionTable 'out=in1;
' #txt
Ut0 f3 taskData 'TaskA.DESC=This task is created by User Selection Component Example
TaskA.NAM=Task created by User Selection Component Example
TaskA.ROL=in1.selectedUser.getName()
TaskA.TYPE=3' #txt
Ut0 f3 369 49 30 30 0 16 #rect
Ut0 f3 @|TaskSwitchSimpleIcon #fIcon
Ut0 f4 312 64 369 64 #arcP
Ut0 f2 399 64 473 64 #arcP
>Proto Ut0 .type ch.ivyteam.ivy.project.portal.examples.UserSelectionExampleData #txt
>Proto Ut0 .processKind NORMAL #txt
>Proto Ut0 0 0 32 24 18 0 #rect
>Proto Ut0 @|BIcon #fIcon
Ut0 f0 mainOut f10 tail #connect
Ut0 f10 head f9 mainIn #connect
Ut0 f9 mainOut f4 tail #connect
Ut0 f4 head f3 in #connect
Ut0 f3 out f2 tail #connect
Ut0 f2 head f1 mainIn #connect
