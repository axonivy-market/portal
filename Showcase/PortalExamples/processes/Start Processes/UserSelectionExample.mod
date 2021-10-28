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
Ut0 @EndTask f5 '' #zField
Ut0 @UserDialog f6 '' #zField
Ut0 @TaskSwitchSimple f7 '' #zField
Ut0 @StartRequest f8 '' #zField
Ut0 @PushWFArc f11 '' #zField
Ut0 @PushWFArc f12 '' #zField
Ut0 @TkArc f13 '' #zField
>Proto Ut0 Ut0 UserSelectionExample #zField
Ut0 f0 outLink userSelectionExample.ivp #txt
Ut0 f0 inParamDecl '<> param;' #txt
Ut0 f0 requestEnabled true #txt
Ut0 f0 triggerEnabled false #txt
Ut0 f0 callSignature userSelectionExample() #txt
Ut0 f0 startName 'User selection component example' #txt
Ut0 f0 startDescription 'Loading all users, loading users by predefined role names, floating label,...' #txt
Ut0 f0 caseData businessCase.attach=true #txt
Ut0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>userSelectionExample.ivp</name>
    </language>
</elementInfo>
' #txt
Ut0 f0 @C|.responsibility Everybody #txt
Ut0 f0 81 49 30 30 -45 23 #rect
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
Ut0 f5 473 145 30 30 0 15 #rect
Ut0 f5 @|EndIcon #fIcon
Ut0 f6 dialogId ch.ivyteam.ivy.project.portal.examples.UserSelectionExample #txt
Ut0 f6 startMethod start() #txt
Ut0 f6 requestActionDecl '<> param;' #txt
Ut0 f6 responseMappingAction 'out=in;
out.selectedUser=result.selectedUserForDefinedRoles;
' #txt
Ut0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserSelectionExample</name>
    </language>
</elementInfo>
' #txt
Ut0 f6 168 138 144 44 -63 -8 #rect
Ut0 f6 @|UserDialogIcon #fIcon
Ut0 f7 actionTable 'out=in1;
' #txt
Ut0 f7 taskData 'TaskA.DESC=This task is created by User Selection Component Example
TaskA.NAM=Task created by User Selection Component Example
TaskA.ROL=in1.selectedUser.getName()
TaskA.TYPE=3' #txt
Ut0 f7 369 145 30 30 0 16 #rect
Ut0 f7 @|TaskSwitchSimpleIcon #fIcon
Ut0 f8 outLink userSelectionExampleInIframe.ivp #txt
Ut0 f8 inParamDecl '<> param;' #txt
Ut0 f8 requestEnabled true #txt
Ut0 f8 triggerEnabled false #txt
Ut0 f8 callSignature userSelectionExampleInIframe() #txt
Ut0 f8 startName 'User selection component example in Iframe' #txt
Ut0 f8 startDescription 'Loading all users, loading users by predefined role names, floating label,...' #txt
Ut0 f8 caseData businessCase.attach=true #txt
Ut0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>userSelectionExampleInIframe.ivp</name>
    </language>
</elementInfo>
' #txt
Ut0 f8 @C|.responsibility Everybody #txt
Ut0 f8 81 145 30 30 -24 17 #rect
Ut0 f8 @|StartRequestIcon #fIcon
Ut0 f11 399 160 473 160 #arcP
Ut0 f12 111 160 168 160 #arcP
Ut0 f13 312 160 369 160 #arcP
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
Ut0 f8 mainOut f12 tail #connect
Ut0 f12 head f6 mainIn #connect
Ut0 f6 mainOut f13 tail #connect
Ut0 f13 head f7 in #connect
Ut0 f7 out f11 tail #connect
Ut0 f11 head f5 mainIn #connect
