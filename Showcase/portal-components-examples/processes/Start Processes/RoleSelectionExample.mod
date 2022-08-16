[Ivy]
1816040F0135A544 7.5.0 #module
>Proto >Proto Collection #zClass
Re0 RoleSelectionExample Big #zClass
Re0 B #cInfo
Re0 #process
Re0 @AnnotationInP-0n ai ai #zField
Re0 @TextInP .type .type #zField
Re0 @TextInP .processKind .processKind #zField
Re0 @TextInP .xml .xml #zField
Re0 @TextInP .responsibility .responsibility #zField
Re0 @StartRequest f0 '' #zField
Re0 @EndTask f1 '' #zField
Re0 @UserDialog f10 '' #zField
Re0 @PushWFArc f11 '' #zField
Re0 @TaskSwitchSimple f12 '' #zField
Re0 @PushWFArc f13 '' #zField
Re0 @TkArc f14 '' #zField
>Proto Re0 Re0 RoleSelectionExample #zField
Re0 f0 outLink showRoleSelectionExamples.ivp #txt
Re0 f0 inParamDecl '<> param;' #txt
Re0 f0 requestEnabled true #txt
Re0 f0 triggerEnabled false #txt
Re0 f0 callSignature showRoleSelectionExamples() #txt
Re0 f0 startName 'Role Selection Example' #txt
Re0 f0 caseData businessCase.attach=true #txt
Re0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showRoleSelectionExamples.ivp</name>
    </language>
</elementInfo>
' #txt
Re0 f0 @C|.responsibility Everybody #txt
Re0 f0 81 49 30 30 -80 15 #rect
Re0 f0 @|StartRequestIcon #fIcon
Re0 f1 561 49 30 30 0 15 #rect
Re0 f1 @|EndIcon #fIcon
Re0 f10 dialogId com.axonivy.portal.components.examples.RoleSelectionExample #txt
Re0 f10 startMethod start() #txt
Re0 f10 requestActionDecl '<> param;' #txt
Re0 f10 responseMappingAction 'out=in;
out.selectedRole=result.selectedRole;
' #txt
Re0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>RoleSelectionExample</name>
    </language>
</elementInfo>
' #txt
Re0 f10 200 42 144 44 -62 -8 #rect
Re0 f10 @|UserDialogIcon #fIcon
Re0 f11 111 64 200 64 #arcP
Re0 f12 actionTable 'out=in1;
' #txt
Re0 f12 taskData 'TaskA.DESC=This task is created by Role Selection Example
TaskA.NAM=Task created by Role Selection Example
TaskA.ROL=in1.selectedRole.getMemberName()
TaskA.TYPE=2' #txt
Re0 f12 433 49 30 30 0 16 #rect
Re0 f12 @|TaskSwitchSimpleIcon #fIcon
Re0 f13 463 64 561 64 #arcP
Re0 f14 344 64 433 64 #arcP
>Proto Re0 .type com.axonivy.portal.components.examples.RoleSelectionExampleData #txt
>Proto Re0 .processKind NORMAL #txt
>Proto Re0 0 0 32 24 18 0 #rect
>Proto Re0 @|BIcon #fIcon
Re0 f0 mainOut f11 tail #connect
Re0 f11 head f10 mainIn #connect
Re0 f12 out f13 tail #connect
Re0 f13 head f1 mainIn #connect
Re0 f10 mainOut f14 tail #connect
Re0 f14 head f12 in #connect
