[Ivy]
175495F02A2BCEB2 9.2.0 #module
>Proto >Proto Collection #zClass
Re0 RoleSelectionExample Big #zClass
Re0 B #cInfo
Re0 #process
Re0 @TextInP .type .type #zField
Re0 @TextInP .processKind .processKind #zField
Re0 @TextInP .xml .xml #zField
Re0 @TextInP .responsibility .responsibility #zField
Re0 @StartRequest f0 '' #zField
Re0 @EndTask f1 '' #zField
Re0 @UserDialog f3 '' #zField
Re0 @PushWFArc f4 '' #zField
Re0 @TaskSwitchSimple f5 '' #zField
Re0 @TkArc f6 '' #zField
Re0 @PushWFArc f2 '' #zField
>Proto Re0 Re0 RoleSelectionExample #zField
Re0 f0 outLink start.ivp #txt
Re0 f0 inParamDecl '<> param;' #txt
Re0 f0 requestEnabled true #txt
Re0 f0 triggerEnabled false #txt
Re0 f0 callSignature start() #txt
Re0 f0 startName 'Role selection component example' #txt
Re0 f0 startDescription 'Loading all roles, loading roles by predefined role names, floating label,...' #txt
Re0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Re0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Re0 f0 @C|.responsibility Everybody #txt
Re0 f0 81 49 30 30 -21 17 #rect
Re0 f0 @|StartRequestIcon #fIcon
Re0 f1 433 49 30 30 0 15 #rect
Re0 f1 @|EndIcon #fIcon
Re0 f3 dialogId com.axonivy.portal.developerexamples.RoleSelectionExample #txt
Re0 f3 startMethod start() #txt
Re0 f3 requestActionDecl '<> param;' #txt
Re0 f3 responseMappingAction 'out=in;
out.selectedRole=result.selectedRoleForDefault;
' #txt
Re0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>RoleSelectionExample</name>
    </language>
</elementInfo>
' #txt
Re0 f3 152 42 144 44 -62 -8 #rect
Re0 f3 @|UserDialogIcon #fIcon
Re0 f4 111 64 152 64 #arcP
Re0 f5 actionTable 'out=in1;
' #txt
Re0 f5 taskData 'TaskA.DESC=This task is created by Role Selection Component Example
TaskA.NAM=Task created by Role Selection Component Example
TaskA.ROL=in1.selectedRole.getMemberName()
TaskA.TYPE=2' #txt
Re0 f5 353 49 30 30 0 16 #rect
Re0 f5 @|TaskSwitchSimpleIcon #fIcon
Re0 f6 296 64 353 64 #arcP
Re0 f2 383 64 433 64 #arcP
>Proto Re0 .type com.axonivy.portal.developerexamples.RoleSelectionExampleData #txt
>Proto Re0 .processKind NORMAL #txt
>Proto Re0 0 0 32 24 18 0 #rect
>Proto Re0 @|BIcon #fIcon
Re0 f0 mainOut f4 tail #connect
Re0 f4 head f3 mainIn #connect
Re0 f3 mainOut f6 tail #connect
Re0 f6 head f5 in #connect
Re0 f5 out f2 tail #connect
Re0 f2 head f1 mainIn #connect
