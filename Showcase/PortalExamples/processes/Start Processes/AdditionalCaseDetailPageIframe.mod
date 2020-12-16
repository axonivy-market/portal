[Ivy]
1765103842C486B3 7.5.0 #module
>Proto >Proto Collection #zClass
Ae0 AdditionalCaseDetailPageIframe Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @CallSub f5 '' #zField
Ae0 @StartRequest f7 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @GridStep f10 '' #zField
Ae0 @EndTask f8 '' #zField
Ae0 @TaskSwitchSimple f3 '' #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @UserDialog f9 '' #zField
Ae0 @PushWFArc f14 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @PushWFArc f6 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @TkArc f4 '' #zField
Ae0 @PushWFArc f11 '' #zField
Ae0 @InfoButton f13 '' #zField
>Proto Ae0 Ae0 AdditionalCaseDetailPageIframe #zField
Ae0 f5 processCall 'Functional Processes/SetAdditonalCaseDetailPage:call(String)' #txt
Ae0 f5 requestActionDecl '<String linkToAddtionalCaseDetailPage> param;' #txt
Ae0 f5 requestMappingAction 'param.linkToAddtionalCaseDetailPage="Start Processes/AdditionalCaseDetailPageIframe/showInvestmentRequestCustomFieldIframe.ivp";
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
Ae0 f5 272 162 176 44 -80 -8 #rect
Ae0 f5 @|CallSubIcon #fIcon
Ae0 f7 outLink showInvestmentRequestCustomFieldIframe.ivp #txt
Ae0 f7 inParamDecl '<Long caseId> param;' #txt
Ae0 f7 inParamTable 'out.caseId=param.caseId;
' #txt
Ae0 f7 requestEnabled true #txt
Ae0 f7 triggerEnabled false #txt
Ae0 f7 callSignature showInvestmentRequestCustomFieldIframe(Long) #txt
Ae0 f7 persist false #txt
Ae0 f7 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ae0 f7 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="true"' #txt
Ae0 f7 showInStartList 0 #txt
Ae0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showInvestmentRequestCustomFieldIframe.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f7 @C|.responsibility Everybody #txt
Ae0 f7 121 49 30 30 -116 17 #rect
Ae0 f7 @|StartRequestIcon #fIcon
Ae0 f1 577 169 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
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
Ae0 f10 288 42 112 44 -41 -8 #rect
Ae0 f10 @|StepIcon #fIcon
Ae0 f8 693 49 30 30 0 15 #rect
Ae0 f8 @|EndIcon #fIcon
Ae0 f3 actionTable 'out=in1;
' #txt
Ae0 f3 outLinks "TaskA.ivp" #txt
Ae0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Investment request with custom case detail using iFrame
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f3 template "" #txt
Ae0 f3 481 169 30 30 0 16 #rect
Ae0 f3 @|TaskSwitchSimpleIcon #fIcon
Ae0 f0 outLink createInvestmentRequestIframe.ivp #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature createInvestmentRequestIframe() #txt
Ae0 f0 persist false #txt
Ae0 f0 startName 'Custom case detail using iframe' #txt
Ae0 f0 startDescription 'Investment request for custom case detail using iframe R' #txt
Ae0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ae0 f0 caseData 'businessCase.attach=true
case.description=Custom case detail using iFrame example
case.name=Investment request with custom case detail using iFrame
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
        <name>createInvestmentRequestIframe.ivp</name>
        <desc>Investment Request for Customized Case Detail Page example N</desc>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 113 169 30 30 -79 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f9 dialogId ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailIFrame #txt
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
Ae0 f9 468 42 192 44 -76 -16 #rect
Ae0 f9 @|UserDialogIcon #fIcon
Ae0 f14 expr out #txt
Ae0 f14 400 64 468 64 #arcP
Ae0 f12 expr out #txt
Ae0 f12 151 64 288 64 #arcP
Ae0 f6 expr out #txt
Ae0 f6 143 184 272 184 #arcP
Ae0 f2 expr data #txt
Ae0 f2 outCond ivp=="TaskA.ivp" #txt
Ae0 f2 511 184 577 184 #arcP
Ae0 f4 expr out #txt
Ae0 f4 type ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPage #txt
Ae0 f4 var in1 #txt
Ae0 f4 448 184 481 184 #arcP
Ae0 f11 expr out #txt
Ae0 f11 660 64 693 64 #arcP
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO CUSTOMIZE CASE DETAIL PAGE USING IFRAME:&#13;
We need to do 3 steps:&#13;
&#13;
1. Create the process for the customize case detail page. In this example, it is showInvestmentRequestCustomFieldIframe.ivp&#13;
&#13;
2. Create custom string field name for case with name : "embedInFrame" and value is "true"&#13;
&#13;
3. Store the URL of custom page to "CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE" additonal property.&#13;
We provide  the callable process "SetAdditionalCaseDetailPage" to do this&#13;
Input for this process is the friendly URL of your customized case detail page. &#13;
For this example, it''s : Start Processes/AdditionalCaseDetailPageIframe/showInvestmentRequestCustomFieldIframe.ivp&#13;
&#13;
HOW TO RUN THIS EXAMPLE:&#13;
&#13;
1. Run createInvestmentRequestIframe.ivp&#13;
&#13;
2. In Portal case list, expand "Investment request with custom case detail using iFrame" case, then click to "Show business details" button&#13;
&#13;
</name>
    </language>
</elementInfo>
' #txt
Ae0 f13 720 18 768 316 -375 -152 #rect
Ae0 f13 @|IBIcon #fIcon
>Proto Ae0 .type ch.ivyteam.ivy.project.portal.examples.customization.AdditionalCaseDetailPageIframeData #txt
>Proto Ae0 .processKind NORMAL #txt
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
