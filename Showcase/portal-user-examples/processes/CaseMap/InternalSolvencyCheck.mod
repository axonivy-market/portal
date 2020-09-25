[Ivy]
1703223B24A42DAC 9.2.0 #module
>Proto >Proto Collection #zClass
Ik0 InternalSolvencyCheck Big #zClass
Ik0 B #cInfo
Ik0 #process
Ik0 @TextInP .type .type #zField
Ik0 @TextInP .processKind .processKind #zField
Ik0 @AnnotationInP-0n ai ai #zField
Ik0 @MessageFlowInP-0n messageIn messageIn #zField
Ik0 @MessageFlowOutP-0n messageOut messageOut #zField
Ik0 @TextInP .xml .xml #zField
Ik0 @TextInP .responsibility .responsibility #zField
Ik0 @StartRequest f0 '' #zField
Ik0 @EndTask f1 '' #zField
Ik0 @GridStep f3 '' #zField
Ik0 @PushWFArc f2 '' #zField
Ik0 @UserDialog f5 '' #zField
Ik0 @PushWFArc f6 '' #zField
Ik0 @PushWFArc f4 '' #zField
>Proto Ik0 Ik0 InternalSolvencyCheck #zField
Ik0 f0 outLink start.ivp #txt
Ik0 f0 inParamDecl '<> param;' #txt
Ik0 f0 requestEnabled true #txt
Ik0 f0 triggerEnabled false #txt
Ik0 f0 callSignature start() #txt
Ik0 f0 persist false #txt
Ik0 f0 startName 'Internal Solvency Check' #txt
Ik0 f0 taskData 'TaskTriggered.EXP=new Duration("20h")
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/internalSolvencyCheck")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
Ik0 f0 caseData businessCase.attach=true #txt
Ik0 f0 showInStartList 0 #txt
Ik0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ik0 f0 @C|.responsibility Everybody #txt
Ik0 f0 81 49 30 30 -21 17 #rect
Ik0 f0 @|StartRequestIcon #fIcon
Ik0 f1 497 49 30 30 0 15 #rect
Ik0 f1 @|EndIcon #fIcon
Ik0 f3 actionTable 'out=in;
' #txt
Ik0 f3 actionCode 'import org.hibernate.validator.constraints.CreditCardNumber;
import com.axonivy.portal.userexamples.credit.CreditDossier;

// Load dossier for current business case
CreditDossier dossier  = ivy.repo.get(CreditDossier.class) as CreditDossier;

dossier.decision.granted = false;
// Execute rules to evaluate if it needs approval Level 1 and / or Level 2
ivy.rules.engine.createRuleBase().loadRulesFromNamespace("com.axonivy.portal.userexamples.credit").createSession().execute(dossier);
// Save dossier
ivy.repo.save(dossier);' #txt
Ik0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approvals
evaluation</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ik0 f3 328 42 112 44 -28 -16 #rect
Ik0 f3 @|StepIcon #fIcon
Ik0 f2 expr out #txt
Ik0 f2 440 64 497 64 #arcP
Ik0 f5 dialogId com.axonivy.portal.userexamples.credit.CheckInternalSolvency #txt
Ik0 f5 startMethod start() #txt
Ik0 f5 requestActionDecl '<> param;' #txt
Ik0 f5 responseMappingAction 'out=in;
' #txt
Ik0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InternalSolvencyInternal</name>
    </language>
</elementInfo>
' #txt
Ik0 f5 152 42 144 44 -65 -8 #rect
Ik0 f5 @|UserDialogIcon #fIcon
Ik0 f6 expr out #txt
Ik0 f6 111 64 152 64 #arcP
Ik0 f4 296 64 328 64 #arcP
>Proto Ik0 .type com.axonivy.portal.userexamples.Data #txt
>Proto Ik0 .processKind NORMAL #txt
>Proto Ik0 0 0 32 24 18 0 #rect
>Proto Ik0 @|BIcon #fIcon
Ik0 f3 mainOut f2 tail #connect
Ik0 f2 head f1 mainIn #connect
Ik0 f0 mainOut f6 tail #connect
Ik0 f6 head f5 mainIn #connect
Ik0 f5 mainOut f4 tail #connect
Ik0 f4 head f3 mainIn #connect
