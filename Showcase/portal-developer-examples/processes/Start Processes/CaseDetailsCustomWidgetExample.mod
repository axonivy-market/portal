[Ivy]
17A2C6D73AB4186E 9.3.1 #module
>Proto >Proto Collection #zClass
Ce0 CaseDetailsCustomWidgetExample Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @TaskSwitch f3 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @EndTask f5 '' #zField
Ce0 @EndTask f6 '' #zField
Ce0 @PushWFArc f7 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @SignalStartEvent f9 '' #zField
Ce0 @EndTask f10 '' #zField
Ce0 @TaskSwitchSimple f12 '' #zField
Ce0 @TkArc f13 '' #zField
Ce0 @PushWFArc f11 '' #zField
Ce0 @GridStep f14 '' #zField
Ce0 @PushWFArc f15 '' #zField
Ce0 @TkArc f4 '' #zField
Ce0 @StartRequest f16 '' #zField
Ce0 @EndTask f17 '' #zField
Ce0 @UserDialog f19 '' #zField
Ce0 @PushWFArc f18 '' #zField
Ce0 @GridStep f21 '' #zField
Ce0 @PushWFArc f22 '' #zField
Ce0 @PushWFArc f20 '' #zField
Ce0 @StartRequest f30 '' #zField
Ce0 @GridStep f29 '' #zField
Ce0 @TaskSwitchSimple f23 '' #zField
Ce0 @TkArc f24 '' #zField
Ce0 @PushWFArc f25 '' #zField
>Proto Ce0 Ce0 CaseDetailsCustomWidgetExample #zField
Ce0 f0 outLink CreateEventTest.ivp #txt
Ce0 f0 inParamDecl '<> param;' #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature CreateEventTest() #txt
Ce0 f0 startName 'Create Event for test "Custom Case Details"' #txt
Ce0 f0 startDescription 'Create a case with category is "customWidget" to test filters by categories for case details' #txt
Ce0 f0 caseData 'businessCase.attach=true
case.category=customWidget
case.description=Test custom widgets for case details
case.name=Create Event\: Test custom case details
customFields.STRING.investmentDescription="This text get from Case customFields"' #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateEventTest.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 81 69 30 30 -21 17 #rect
Ce0 f1 577 5 30 30 0 15 #rect
Ce0 f3 actionTable 'out=in1;
' #txt
Ce0 f3 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Ce0 f3 taskData 'TaskB.DESC=Test custom widget case details
TaskB.NAM=Send invitation
TaskA.DESC=Test custom widget case details
TaskA.NAM=Send budget request
TaskC.DESC=Test custom widget case details
TaskC.NAM=Wait for approval' #txt
Ce0 f3 432 68 32 32 0 16 #rect
Ce0 f2 expr data #txt
Ce0 f2 outCond ivp=="TaskA.ivp" #txt
Ce0 f2 448 68 577 20 #arcP
Ce0 f2 1 448 20 #addKink
Ce0 f2 1 0.26737726995157113 0 0 #arcLabel
Ce0 f5 577 69 30 30 0 15 #rect
Ce0 f6 577 133 30 30 0 15 #rect
Ce0 f7 expr data #txt
Ce0 f7 outCond ivp=="TaskB.ivp" #txt
Ce0 f7 464 84 577 84 #arcP
Ce0 f8 expr data #txt
Ce0 f8 outCond ivp=="TaskC.ivp" #txt
Ce0 f8 448 100 577 148 #arcP
Ce0 f8 1 448 148 #addKink
Ce0 f8 1 0.26737726995157113 0 0 #arcLabel
Ce0 f9 signalCode customcasedetails:tech #txt
Ce0 f9 attachToBusinessCase true #txt
Ce0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>customcasedetails:tech</name>
    </language>
</elementInfo>
' #txt
Ce0 f9 257 197 30 30 0 15 #rect
Ce0 f10 577 197 30 30 0 15 #rect
Ce0 f12 actionTable 'out=in1;
' #txt
Ce0 f12 caseData 'case.name=Signal case of Custom case details' #txt
Ce0 f12 taskData 'TaskA.DESC=Test custom widget case details
TaskA.NAM=Send email' #txt
Ce0 f12 433 197 30 30 0 16 #rect
Ce0 f13 287 212 433 212 #arcP
Ce0 f11 463 212 577 212 #arcP
Ce0 f14 actionTable 'out=in;
' #txt
Ce0 f14 actionCode '// send simple signal
ivy.wf.signals().send("customcasedetails:tech");' #txt
Ce0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Send signal</name>
    </language>
</elementInfo>
' #txt
Ce0 f14 224 62 112 44 -33 -8 #rect
Ce0 f15 111 84 224 84 #arcP
Ce0 f4 var in1 #txt
Ce0 f4 336 84 432 84 #arcP
Ce0 f16 outLink startReview.ivp #txt
Ce0 f16 inParamDecl '<Long startedCaseId,String startedCaseCategory,Number investmentId,String investmentDescription> param;' #txt
Ce0 f16 inParamInfo 'investmentDescription.description=it will get case.customFields.investmentDescription
startedCaseCategory.description=it will get case.category
startedCaseId.description=it will get case.id' #txt
Ce0 f16 actionCode 'out.exampleIFrameData.decision.note = "Case ID is: " + param.startedCaseId + ", with Category: " + param.startedCaseCategory;
out.exampleIFrameData.investment.company.description = param.investmentDescription;
out.exampleIFrameData.investment.company.id = String.valueOf(param.startedCaseId);
out.exampleIFrameData.investment.id = param.investmentId.toString();

' #txt
Ce0 f16 requestEnabled true #txt
Ce0 f16 triggerEnabled false #txt
Ce0 f16 callSignature startReview(Long,String,Number,String) #txt
Ce0 f16 caseData businessCase.attach=true #txt
Ce0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startReview.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f16 @C|.responsibility Everybody #txt
Ce0 f16 81 325 30 30 -21 17 #rect
Ce0 f17 577 325 30 30 0 15 #rect
Ce0 f19 dialogId com.axonivy.portal.developerexamples.testdata.Review #txt
Ce0 f19 startMethod start(com.axonivy.portal.developerexamples.ExampleIFrameData) #txt
Ce0 f19 requestActionDecl '<com.axonivy.portal.developerexamples.ExampleIFrameData investmentRequest> param;' #txt
Ce0 f19 requestMappingAction 'param.investmentRequest=in.exampleIFrameData;
' #txt
Ce0 f19 responseMappingAction 'out=in;
' #txt
Ce0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Review</name>
    </language>
</elementInfo>
' #txt
Ce0 f19 400 318 112 44 -20 -8 #rect
Ce0 f18 512 340 577 340 #arcP
Ce0 f21 actionTable 'out=in;
' #txt
Ce0 f21 actionCode 'ivy.log.debug("Data from case json: " + in.exampleIFrameData);

out.exampleIFrameData.decision.isApproved = true;
out.exampleIFrameData.investment.company.name = "Test Case details";
out.exampleIFrameData.investment.investedMoney = "2500";
out.exampleIFrameData.investment.profit = "100";
out.exampleIFrameData.investment.note = "Test case details with custom params";
out.exampleIFrameData.isReadOnly = true;
out.exampleIFrameData.investment.profitPercentage = "99.9";
out.exampleIFrameData.investment.setStartDate(new Date());' #txt
Ce0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>additional data</name>
    </language>
</elementInfo>
' #txt
Ce0 f21 192 318 112 44 -40 -8 #rect
Ce0 f22 111 340 192 340 #arcP
Ce0 f20 304 340 400 340 #arcP
Ce0 f30 outLink CreatePersonalRequest.ivp #txt
Ce0 f30 inParamDecl '<> param;' #txt
Ce0 f30 requestEnabled true #txt
Ce0 f30 triggerEnabled false #txt
Ce0 f30 callSignature CreatePersonalRequest() #txt
Ce0 f30 persist false #txt
Ce0 f30 startName 'Create Personal Request for test "Custom Case Details"' #txt
Ce0 f30 startDescription 'Create a Destroyed case to test filters by states in case details page' #txt
Ce0 f30 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ce0 f30 caseData businessCase.attach=true #txt
Ce0 f30 showInStartList 1 #txt
Ce0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreatePersonalRequest.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f30 @C|.responsibility Everybody #txt
Ce0 f30 91 467 26 26 -31 24 #rect
Ce0 f29 actionTable 'out=in;
' #txt
Ce0 f29 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;

ivy.case.destroy();

PortalNavigatorAPI.navigateToPortalHome();' #txt
Ce0 f29 security system #txt
Ce0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroy case&#13;
redirect to homepage</name>
    </language>
</elementInfo>
' #txt
Ce0 f29 288 458 144 44 -53 -16 #rect
Ce0 f23 actionTable 'out=in1;
' #txt
Ce0 f23 taskData TaskA.SKIP_TASK_LIST=true #txt
Ce0 f23 185 465 30 30 0 16 #rect
Ce0 f24 expr out #txt
Ce0 f24 117 480 185 480 #arcP
Ce0 f24 0 0.5000000000000001 0 0 #arcLabel
Ce0 f25 215 480 288 480 #arcP
Ce0 f25 0 0.5000000000000001 0 0 #arcLabel
>Proto Ce0 .type com.axonivy.portal.developerexamples.Data #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f3 out f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f3 out f7 tail #connect
Ce0 f7 head f5 mainIn #connect
Ce0 f3 out f8 tail #connect
Ce0 f8 head f6 mainIn #connect
Ce0 f9 mainOut f13 tail #connect
Ce0 f13 head f12 in #connect
Ce0 f12 out f11 tail #connect
Ce0 f11 head f10 mainIn #connect
Ce0 f0 mainOut f15 tail #connect
Ce0 f15 head f14 mainIn #connect
Ce0 f14 mainOut f4 tail #connect
Ce0 f4 head f3 in #connect
Ce0 f19 mainOut f18 tail #connect
Ce0 f18 head f17 mainIn #connect
Ce0 f16 mainOut f22 tail #connect
Ce0 f22 head f21 mainIn #connect
Ce0 f21 mainOut f20 tail #connect
Ce0 f20 head f19 mainIn #connect
Ce0 f30 mainOut f24 tail #connect
Ce0 f24 head f23 in #connect
Ce0 f23 out f25 tail #connect
Ce0 f25 head f29 mainIn #connect
