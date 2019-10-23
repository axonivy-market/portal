[Ivy]
1624D1F5CBEA5332 7.5.0 #module
>Proto >Proto Collection #zClass
Ae0 AdditionalCaseDetailPage Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @TaskSwitchSimple f3 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @CallSub f5 '' #zField
Ae0 @PushWFArc f6 '' #zField
Ae0 @TkArc f4 '' #zField
Ae0 @StartRequest f7 '' #zField
Ae0 @EndTask f8 '' #zField
Ae0 @UserDialog f9 '' #zField
Ae0 @PushWFArc f11 '' #zField
Ae0 @InfoButton f13 '' #zField
Ae0 @GridStep f10 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @PushWFArc f14 '' #zField
>Proto Ae0 Ae0 AdditionalCaseDetailPage #zField
Ae0 f0 outLink createInvestmentRequest.ivp #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature createInvestmentRequest() #txt
Ae0 f0 persist false #txt
Ae0 f0 startName 'Investment Request for Customized Case Detail Page example' #txt
Ae0 f0 startDescription 'Investment Request for Customized Case Detail Page example' #txt
Ae0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ae0 f0 caseData 'businessCase.attach=true
case.description=Investment Request for Customized Case Detail Page example
case.name=Investment Request
customFields.NUMBER.CustomDecimalField1=100
customFields.NUMBER.CustomDecimalField2=2000
customFields.STRING.CustomVarCharField1="Apartment A"
customFields.STRING.CustomVarCharField2="Request for new computer"
customFields.TIMESTAMP.CustomTimestampField1=new DateTime(2018, 1, 1, 1, 1, 1)
customFields.TIMESTAMP.CustomTimestampField2=new DateTime(2018, 2, 2, 2, 2, 2)' #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createInvestmentRequest.ivp</name>
        <nameStyle>27,5,7
</nameStyle>
        <desc>Investment Request for Customized Case Detail Page example</desc>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 121 169 30 30 -79 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 537 169 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 actionTable 'out=in1;
' #txt
Ae0 f3 outLinks "TaskA.ivp" #txt
Ae0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=InvestmentRequest
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f3 template "" #txt
Ae0 f3 441 169 30 30 0 16 #rect
Ae0 f3 @|TaskSwitchSimpleIcon #fIcon
Ae0 f2 expr data #txt
Ae0 f2 outCond ivp=="TaskA.ivp" #txt
Ae0 f2 471 184 537 184 #arcP
Ae0 f5 processCall 'Functional Processes/SetAdditonalCaseDetailPage:call(String)' #txt
Ae0 f5 requestActionDecl '<String linkToAddtionalCaseDetailPage> param;' #txt
Ae0 f5 requestMappingAction 'param.linkToAddtionalCaseDetailPage="Start Processes/AdditionalCaseDetailPage/showInvestmentRequestCustomFields.ivp";
' #txt
Ae0 f5 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPage out;
' #txt
Ae0 f5 responseMappingAction 'out=in;
' #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SetAdditonalCaseDetailPage</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 232 162 176 44 -80 -8 #rect
Ae0 f5 @|CallSubIcon #fIcon
Ae0 f6 expr out #txt
Ae0 f6 151 184 232 184 #arcP
Ae0 f4 expr out #txt
Ae0 f4 type ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPage #txt
Ae0 f4 var in1 #txt
Ae0 f4 408 184 441 184 #arcP
Ae0 f7 outLink showInvestmentRequestCustomFields.ivp #txt
Ae0 f7 inParamDecl '<Long caseId> param;' #txt
Ae0 f7 inParamTable 'out.caseId=param.caseId;
' #txt
Ae0 f7 requestEnabled true #txt
Ae0 f7 triggerEnabled false #txt
Ae0 f7 callSignature showInvestmentRequestCustomFields(Long) #txt
Ae0 f7 persist false #txt
Ae0 f7 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ae0 f7 caseData businessCase.attach=true #txt
Ae0 f7 showInStartList 0 #txt
Ae0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showInvestmentRequestCustomFields.ivp</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f7 @C|.responsibility Everybody #txt
Ae0 f7 129 49 30 30 -116 17 #rect
Ae0 f7 @|StartRequestIcon #fIcon
Ae0 f8 701 49 30 30 0 15 #rect
Ae0 f8 @|EndIcon #fIcon
Ae0 f9 dialogId ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage #txt
Ae0 f9 startMethod start(ch.ivyteam.ivy.workflow.ICase) #txt
Ae0 f9 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Ae0 f9 requestMappingAction 'param.iCase=in.iCase;
' #txt
Ae0 f9 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPage out;
' #txt
Ae0 f9 responseMappingAction 'out=in;
' #txt
Ae0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Customization
Additional Case Details Page</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f9 476 42 192 44 -76 -16 #rect
Ae0 f9 @|UserDialogIcon #fIcon
Ae0 f11 expr out #txt
Ae0 f11 668 64 701 64 #arcP
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO CUSTOMIZE CASE DETAIL PAGE:
We need to do 2 steps:

1. Create the process for the customize case detail page. In this example, it is showInvestmentRequestCustomFields.ivp

2. If you want to have customized case detail page, store the URL of custom page to "CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE" additonal property.
We also provide  the callable process "SetAdditionalCaseDetailPage", you could use this if you need information from case only.
Input for this process is the friendly URL of your customized case detail page. Example: Start Processes/CustomizedCaseDetail/example.ivp

HOW TO RUN THIS EXAMPLE:
1. Run createInvestmentRequest.ivp process

2. Enter Portal case list, expand Investment Request case, then click to "Show details" button. New tab will open, showing customized case detail page.


</name>
        <nameStyle>35,7
783,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 784 2 896 268 -438 -128 #rect
Ae0 f13 @|IBIcon #fIcon
Ae0 f10 actionTable 'out=in;
' #txt
Ae0 f10 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.iCase = CaseUtils.findCase(in.caseId);' #txt
Ae0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find case by id</name>
    </language>
</elementInfo>
' #txt
Ae0 f10 296 42 112 44 -41 -8 #rect
Ae0 f10 @|StepIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 159 64 296 64 #arcP
Ae0 f14 expr out #txt
Ae0 f14 408 64 476 64 #arcP
>Proto Ae0 .type ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPage #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f3 out f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f0 mainOut f6 tail #connect
Ae0 f6 head f5 mainIn #connect
Ae0 f5 mainOut f4 tail #connect
Ae0 f4 head f3 in #connect
Ae0 f9 mainOut f11 tail #connect
Ae0 f11 head f8 mainIn #connect
Ae0 f7 mainOut f12 tail #connect
Ae0 f12 head f10 mainIn #connect
Ae0 f10 mainOut f14 tail #connect
Ae0 f14 head f9 mainIn #connect
