[Ivy]
1709E0D204F88640 7.5.0 #module
>Proto >Proto Collection #zClass
Le0 LeaveRequestEndPage Big #zClass
Le0 B #cInfo
Le0 #process
Le0 @TextInP .type .type #zField
Le0 @TextInP .processKind .processKind #zField
Le0 @TextInP .xml .xml #zField
Le0 @TextInP .responsibility .responsibility #zField
Le0 @StartRequest f0 '' #zField
Le0 @EndTask f1 '' #zField
Le0 @UserDialog f3 '' #zField
Le0 @PushWFArc f4 '' #zField
Le0 @PushWFArc f2 '' #zField
>Proto Le0 Le0 LeaveRequestEndPage #zField
Le0 f0 outLink start.ivp #txt
Le0 f0 inParamDecl '<Long caseId> param;' #txt
Le0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Le0 f0 requestEnabled true #txt
Le0 f0 triggerEnabled false #txt
Le0 f0 callSignature start(Long) #txt
Le0 f0 caseData businessCase.attach=true #txt
Le0 f0 showInStartList 0 #txt
Le0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Le0 f0 @C|.responsibility Everybody #txt
Le0 f0 81 49 30 30 -21 17 #rect
Le0 f0 @|StartRequestIcon #fIcon
Le0 f1 417 49 30 30 0 15 #rect
Le0 f1 @|EndIcon #fIcon
Le0 f3 dialogId com.axonivy.portal.userexamples.leaverequest.LeaveRequestCustomEndPage #txt
Le0 f3 startMethod start(Long) #txt
Le0 f3 requestActionDecl '<Long caseId> param;' #txt
Le0 f3 requestMappingAction 'param.caseId=in.caseId;
' #txt
Le0 f3 responseMappingAction 'out=in;
' #txt
Le0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestCustomEndPage</name>
    </language>
</elementInfo>
' #txt
Le0 f3 168 42 192 44 -87 -8 #rect
Le0 f3 @|UserDialogIcon #fIcon
Le0 f4 111 64 168 64 #arcP
Le0 f2 360 64 417 64 #arcP
>Proto Le0 .type com.axonivy.portal.userexamples.credit.HandleEndPageData #txt
>Proto Le0 .processKind NORMAL #txt
>Proto Le0 0 0 32 24 18 0 #rect
>Proto Le0 @|BIcon #fIcon
Le0 f0 mainOut f4 tail #connect
Le0 f4 head f3 mainIn #connect
Le0 f3 mainOut f2 tail #connect
Le0 f2 head f1 mainIn #connect
