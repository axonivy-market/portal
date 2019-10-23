[Ivy]
1636734E13CEC872 7.5.0 #module
>Proto >Proto Collection #zClass
Ln0 Login Big #zClass
Ln0 B #cInfo
Ln0 #process
Ln0 @TextInP .type .type #zField
Ln0 @TextInP .processKind .processKind #zField
Ln0 @AnnotationInP-0n ai ai #zField
Ln0 @MessageFlowInP-0n messageIn messageIn #zField
Ln0 @MessageFlowOutP-0n messageOut messageOut #zField
Ln0 @TextInP .xml .xml #zField
Ln0 @TextInP .responsibility .responsibility #zField
Ln0 @EndTask f1 '' #zField
Ln0 @GridStep f3 '' #zField
Ln0 @PushWFArc f2 '' #zField
Ln0 @PushWFArc f4 '' #zField
Ln0 @StartRequest f0 '' #zField
>Proto Ln0 Ln0 Login #zField
Ln0 f1 337 49 30 30 0 15 #rect
Ln0 f1 @|EndIcon #fIcon
Ln0 f3 actionTable 'out=in;
' #txt
Ln0 f3 actionCode 'ivy.session.loginSessionUser(in.username, in.password);
' #txt
Ln0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
    </language>
</elementInfo>
' #txt
Ln0 f3 168 42 112 44 -13 -8 #rect
Ln0 f3 @|StepIcon #fIcon
Ln0 f2 expr out #txt
Ln0 f2 280 64 337 64 #arcP
Ln0 f4 expr out #txt
Ln0 f4 111 64 168 64 #arcP
Ln0 f0 outLink login.ivp #txt
Ln0 f0 inParamDecl '<String username,String password> param;' #txt
Ln0 f0 inParamTable 'out.password=param.password;
out.username=param.username;
' #txt
Ln0 f0 requestEnabled true #txt
Ln0 f0 triggerEnabled false #txt
Ln0 f0 callSignature login(String,String) #txt
Ln0 f0 persist false #txt
Ln0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ln0 f0 caseData businessCase.attach=true #txt
Ln0 f0 showInStartList 1 #txt
Ln0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login.ivp</name>
    </language>
</elementInfo>
' #txt
Ln0 f0 @C|.responsibility Everybody #txt
Ln0 f0 81 49 30 30 -22 17 #rect
Ln0 f0 @|StartRequestIcon #fIcon
>Proto Ln0 .type portalKit_test.LoginData #txt
>Proto Ln0 .processKind NORMAL #txt
>Proto Ln0 0 0 32 24 18 0 #rect
>Proto Ln0 @|BIcon #fIcon
Ln0 f0 mainOut f4 tail #connect
Ln0 f4 head f3 mainIn #connect
Ln0 f3 mainOut f2 tail #connect
Ln0 f2 head f1 mainIn #connect
