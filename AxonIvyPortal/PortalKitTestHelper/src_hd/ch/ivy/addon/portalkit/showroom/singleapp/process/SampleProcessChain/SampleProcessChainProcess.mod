[Ivy]
[>Created: Wed Feb 11 15:13:16 ICT 2015]
150417A809298DE8 3.17 #module
>Proto >Proto Collection #zClass
Ts0 SampleProcessChainProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @GridStep f3 '' #zField
Ts0 @PushWFArc f4 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogMethodStart f5 '' #zField
Ts0 @RichDialogProcessEnd f6 '' #zField
Ts0 @Alternative f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @RichDialogProcessEnd f9 '' #zField
Ts0 @RichDialogProcessEnd f10 '' #zField
Ts0 @RichDialogProcessEnd f11 '' #zField
Ts0 @RichDialogProcessEnd f12 '' #zField
Ts0 @RichDialogProcessEnd f13 '' #zField
Ts0 @GridStep f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @GridStep f19 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @GridStep f21 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @GridStep f27 '' #zField
Ts0 @PushWFArc f28 '' #zField
Ts0 @PushWFArc f24 '' #zField
Ts0 @GridStep f29 '' #zField
Ts0 @PushWFArc f30 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @GridStep f23 '' #zField
Ts0 @PushWFArc f31 '' #zField
Ts0 @PushWFArc f26 '' #zField
>Proto Ts0 Ts0 SampleProcessChainProcess #zField
Ts0 f0 guid 14B71A6C9792BB74 #txt
Ts0 f0 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 118 54 20 20 13 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f1 118 278 20 20 13 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f3 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 actionCode 'in.steps.add("Create Quotation");
in.steps.add("Approve by PM");
in.steps.add("Send Quotation");
in.steps.add("Create Order");
in.steps.add("WBS element BL");
in.steps.add("WBS element COL");
in.steps.add("Finished");
in.actualStepIndex = 0;' #txt
Ts0 f3 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f3 110 164 36 24 20 -2 #rect
Ts0 f3 @|StepIcon #fIcon
Ts0 f4 expr out #txt
Ts0 f4 128 74 128 164 #arcP
Ts0 f2 expr out #txt
Ts0 f2 128 188 128 278 #arcP
Ts0 f5 guid 14B779D0B9D9C0CB #txt
Ts0 f5 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f5 method nextView(String) #txt
Ts0 f5 disableUIEvents false #txt
Ts0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String nextView> param = methodEvent.getInputArguments();
' #txt
Ts0 f5 inParameterMapAction 'out.currentView=param.nextView;
' #txt
Ts0 f5 outParameterDecl '<java.lang.String returnView> result;
' #txt
Ts0 f5 outParameterMapAction 'result.returnView=in.nextView;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>nextView(String)</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f5 566 54 20 20 13 0 #rect
Ts0 f5 @|RichDialogMethodStartIcon #fIcon
Ts0 f6 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f6 502 310 20 20 13 0 #rect
Ts0 f6 @|RichDialogProcessEndIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f7 562 146 28 28 14 0 #rect
Ts0 f7 @|AlternativeIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 576 74 576 146 #arcP
Ts0 f9 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f9 374 310 20 20 13 0 #rect
Ts0 f9 @|RichDialogProcessEndIcon #fIcon
Ts0 f10 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f10 438 310 20 20 13 0 #rect
Ts0 f10 @|RichDialogProcessEndIcon #fIcon
Ts0 f11 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f11 630 310 20 20 13 0 #rect
Ts0 f11 @|RichDialogProcessEndIcon #fIcon
Ts0 f12 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f12 694 310 20 20 13 0 #rect
Ts0 f12 @|RichDialogProcessEndIcon #fIcon
Ts0 f13 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f13 758 310 20 20 13 0 #rect
Ts0 f13 @|RichDialogProcessEndIcon #fIcon
Ts0 f17 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f17 actionTable 'out=in;
' #txt
Ts0 f17 actionCode 'in.actualStepIndex = 1;
in.nextView = "ApproveByPM";' #txt
Ts0 f17 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f17 366 244 36 24 20 -2 #rect
Ts0 f17 @|StepIcon #fIcon
Ts0 f18 expr in #txt
Ts0 f18 outCond "createQuotation".equalsIgnoreCase(in.currentView) #txt
Ts0 f18 562 160 384 244 #arcP
Ts0 f18 1 384 160 #addKink
Ts0 f18 0 0.91597468011625 0 0 #arcLabel
Ts0 f14 expr out #txt
Ts0 f14 384 268 384 310 #arcP
Ts0 f19 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f19 actionTable 'out=in;
' #txt
Ts0 f19 actionCode 'in.actualStepIndex = 2;
in.nextView = "SendQuotation";' #txt
Ts0 f19 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f19 430 244 36 24 20 -2 #rect
Ts0 f19 @|StepIcon #fIcon
Ts0 f20 expr in #txt
Ts0 f20 outCond "approvePm".equalsIgnoreCase(in.currentView) #txt
Ts0 f20 568 166 448 244 #arcP
Ts0 f20 1 536 192 #addKink
Ts0 f20 2 448 192 #addKink
Ts0 f20 1 0.9753080448189344 0 0 #arcLabel
Ts0 f15 expr out #txt
Ts0 f15 448 268 448 310 #arcP
Ts0 f21 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f21 actionTable 'out=in;
' #txt
Ts0 f21 actionCode 'in.actualStepIndex = 3;
in.nextView = "CreateOrder";' #txt
Ts0 f21 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f21 494 244 36 24 20 -2 #rect
Ts0 f21 @|StepIcon #fIcon
Ts0 f22 expr in #txt
Ts0 f22 outCond "sendQuotation".equalsIgnoreCase(in.currentView) #txt
Ts0 f22 570 168 512 244 #arcP
Ts0 f22 1 544 208 #addKink
Ts0 f22 2 512 224 #addKink
Ts0 f22 2 0.030972819965544452 0 0 #arcLabel
Ts0 f16 expr out #txt
Ts0 f16 512 268 512 310 #arcP
Ts0 f27 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f27 actionTable 'out=in;
' #txt
Ts0 f27 actionCode 'in.actualStepIndex = 4;
in.nextView = "WBSElementBL";' #txt
Ts0 f27 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f27 622 244 36 24 20 -2 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f28 expr in #txt
Ts0 f28 outCond "createOrder".equalsIgnoreCase(in.currentView) #txt
Ts0 f28 582 168 640 244 #arcP
Ts0 f28 1 608 208 #addKink
Ts0 f28 2 640 224 #addKink
Ts0 f28 1 0.8327519631983183 0 0 #arcLabel
Ts0 f24 expr out #txt
Ts0 f24 640 268 640 310 #arcP
Ts0 f29 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f29 actionTable 'out=in;
' #txt
Ts0 f29 actionCode 'in.actualStepIndex = 5;
in.nextView = "WBSElemenCOL";' #txt
Ts0 f29 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f29 686 244 36 24 20 -2 #rect
Ts0 f29 @|StepIcon #fIcon
Ts0 f30 expr in #txt
Ts0 f30 outCond "BOL".equalsIgnoreCase(in.currentView) #txt
Ts0 f30 583 167 704 244 #arcP
Ts0 f30 1 608 192 #addKink
Ts0 f30 2 704 192 #addKink
Ts0 f30 1 0.856301969098787 0 0 #arcLabel
Ts0 f25 expr out #txt
Ts0 f25 704 268 704 310 #arcP
Ts0 f23 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData out;
' #txt
Ts0 f23 actionTable 'out=in;
' #txt
Ts0 f23 actionCode 'in.actualStepIndex = 6;
in.nextView = "Finished";' #txt
Ts0 f23 type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
Ts0 f23 750 244 36 24 20 -2 #rect
Ts0 f23 @|StepIcon #fIcon
Ts0 f31 expr in #txt
Ts0 f31 590 160 768 244 #arcP
Ts0 f31 1 768 160 #addKink
Ts0 f31 0 0.8569520423790205 0 0 #arcLabel
Ts0 f26 expr out #txt
Ts0 f26 768 268 768 310 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain.SampleProcessChainData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start</swimlaneLabel>
        <swimlaneLabel>Method
</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneSize>576</swimlaneSize>
    <swimlaneColor>-3342388</swimlaneColor>
    <swimlaneColor>-26215</swimlaneColor>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f4 tail #connect
Ts0 f4 head f3 mainIn #connect
Ts0 f3 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f5 mainOut f8 tail #connect
Ts0 f8 head f7 in #connect
Ts0 f7 out f18 tail #connect
Ts0 f18 head f17 mainIn #connect
Ts0 f17 mainOut f14 tail #connect
Ts0 f14 head f9 mainIn #connect
Ts0 f7 out f20 tail #connect
Ts0 f20 head f19 mainIn #connect
Ts0 f19 mainOut f15 tail #connect
Ts0 f15 head f10 mainIn #connect
Ts0 f7 out f22 tail #connect
Ts0 f22 head f21 mainIn #connect
Ts0 f21 mainOut f16 tail #connect
Ts0 f16 head f6 mainIn #connect
Ts0 f7 out f28 tail #connect
Ts0 f28 head f27 mainIn #connect
Ts0 f27 mainOut f24 tail #connect
Ts0 f24 head f11 mainIn #connect
Ts0 f7 out f30 tail #connect
Ts0 f30 head f29 mainIn #connect
Ts0 f29 mainOut f25 tail #connect
Ts0 f25 head f12 mainIn #connect
Ts0 f7 out f31 tail #connect
Ts0 f31 head f23 mainIn #connect
Ts0 f23 mainOut f26 tail #connect
Ts0 f26 head f13 mainIn #connect
