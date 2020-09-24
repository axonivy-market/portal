[Ivy]
1749B87B8C1B77BE 9.2.0 #module
>Proto >Proto Collection #zClass
Pr0 PortalGlobalVariableHelper Big #zClass
Pr0 B #cInfo
Pr0 #process
Pr0 @TextInP .type .type #zField
Pr0 @TextInP .processKind .processKind #zField
Pr0 @TextInP .xml .xml #zField
Pr0 @TextInP .responsibility .responsibility #zField
Pr0 @EndTask f6 '' #zField
Pr0 @GridStep f7 '' #zField
Pr0 @StartRequest f5 '' #zField
Pr0 @PushWFArc f9 '' #zField
Pr0 @PushWFArc f8 '' #zField
>Proto Pr0 Pr0 PortalGlobalVariableHelper #zField
Pr0 f6 321 65 30 30 0 15 #rect
Pr0 f6 @|EndIcon #fIcon
Pr0 f7 actionTable 'out=in;
' #txt
Pr0 f7 actionCode 'ivy.session.logoutSessionUser();
ivy.var.set(in.variableName, in.variableValue);' #txt
Pr0 f7 security system #txt
Pr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update Global Variable</name>
    </language>
</elementInfo>
' #txt
Pr0 f7 128 58 144 44 -63 -8 #rect
Pr0 f7 @|StepIcon #fIcon
Pr0 f5 outLink updateGlobalVariable.ivp #txt
Pr0 f5 inParamDecl '<String variableName,String variableValue> param;' #txt
Pr0 f5 inParamTable 'out.variableName=param.variableName;
out.variableValue=param.variableValue;
' #txt
Pr0 f5 requestEnabled true #txt
Pr0 f5 triggerEnabled false #txt
Pr0 f5 callSignature updateGlobalVariable(String,String) #txt
Pr0 f5 caseData businessCase.attach=true #txt
Pr0 f5 showInStartList 1 #txt
Pr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateGlobalVariable.ivp</name>
    </language>
</elementInfo>
' #txt
Pr0 f5 @C|.responsibility Everybody #txt
Pr0 f5 65 65 30 30 -67 21 #rect
Pr0 f5 @|StartRequestIcon #fIcon
Pr0 f9 272 80 321 80 #arcP
Pr0 f8 95 80 128 80 #arcP
>Proto Pr0 .type portalKit_test.PortalGlobalVariableHelperData #txt
>Proto Pr0 .processKind NORMAL #txt
>Proto Pr0 0 0 32 24 18 0 #rect
>Proto Pr0 @|BIcon #fIcon
Pr0 f5 mainOut f8 tail #connect
Pr0 f8 head f7 mainIn #connect
Pr0 f7 mainOut f9 tail #connect
Pr0 f9 head f6 mainIn #connect
