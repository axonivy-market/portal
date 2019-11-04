[Ivy]
16C0334AA0031ACF 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 Tasks Big #zClass
Ts0 B #cInfo
Ts0 #process
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @StartRequest f0 '' #zField
Ts0 @EndTask f1 '' #zField
Ts0 @TaskSwitch f3 '' #zField
Ts0 @TkArc f4 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @EndTask f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @EndTask f7 '' #zField
Ts0 @EndTask f8 '' #zField
Ts0 @EndTask f9 '' #zField
Ts0 @EndTask f10 '' #zField
Ts0 @EndTask f11 '' #zField
Ts0 @EndTask f12 '' #zField
Ts0 @EndTask f13 '' #zField
Ts0 @EndTask f14 '' #zField
Ts0 @EndTask f15 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f19 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f24 '' #zField
>Proto Ts0 Ts0 Tasks #zField
Ts0 f0 outLink start.ivp #txt
Ts0 f0 inParamDecl '<> param;' #txt
Ts0 f0 requestEnabled true #txt
Ts0 f0 triggerEnabled false #txt
Ts0 f0 callSignature start() #txt
Ts0 f0 persist false #txt
Ts0 f0 caseData 'businessCase.attach=true
case.name=Multi tasks' #txt
Ts0 f0 showInStartList 1 #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 @C|.responsibility Everybody #txt
Ts0 f0 81 49 30 30 -21 17 #rect
Ts0 f0 @|StartRequestIcon #fIcon
Ts0 f1 337 49 30 30 0 15 #rect
Ts0 f1 @|EndIcon #fIcon
Ts0 f3 actionTable 'out=in1;
' #txt
Ts0 f3 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp","TaskD.ivp","TaskE.ivp","TaskF.ivp","TaskG.ivp","TaskH.ivp","TaskI.ivp","TaskJ.ivp","TaskK.ivp" #txt
Ts0 f3 template "" #txt
Ts0 f3 208 48 32 32 0 16 #rect
Ts0 f3 @|TaskSwitchIcon #fIcon
Ts0 f4 expr out #txt
Ts0 f4 var in1 #txt
Ts0 f4 111 64 208 64 #arcP
Ts0 f2 expr data #txt
Ts0 f2 outCond ivp=="TaskA.ivp" #txt
Ts0 f2 240 64 337 64 #arcP
Ts0 f5 337 145 30 30 0 15 #rect
Ts0 f5 @|EndIcon #fIcon
Ts0 f6 expr data #txt
Ts0 f6 outCond ivp=="TaskB.ivp" #txt
Ts0 f6 233 71 340 151 #arcP
Ts0 f7 337 241 30 30 0 15 #rect
Ts0 f7 @|EndIcon #fIcon
Ts0 f8 337 337 30 30 0 15 #rect
Ts0 f8 @|EndIcon #fIcon
Ts0 f9 337 433 30 30 0 15 #rect
Ts0 f9 @|EndIcon #fIcon
Ts0 f10 337 529 30 30 0 15 #rect
Ts0 f10 @|EndIcon #fIcon
Ts0 f11 337 593 30 30 0 15 #rect
Ts0 f11 @|EndIcon #fIcon
Ts0 f12 145 465 30 30 0 15 #rect
Ts0 f12 @|EndIcon #fIcon
Ts0 f13 49 369 30 30 0 15 #rect
Ts0 f13 @|EndIcon #fIcon
Ts0 f14 113 417 30 30 0 15 #rect
Ts0 f14 @|EndIcon #fIcon
Ts0 f15 65 273 30 30 0 15 #rect
Ts0 f15 @|EndIcon #fIcon
Ts0 f16 expr data #txt
Ts0 f16 outCond ivp=="TaskC.ivp" #txt
Ts0 f16 230 74 343 243 #arcP
Ts0 f17 expr data #txt
Ts0 f17 outCond ivp=="TaskD.ivp" #txt
Ts0 f17 229 75 345 338 #arcP
Ts0 f18 expr data #txt
Ts0 f18 outCond ivp=="TaskE.ivp" #txt
Ts0 f18 228 76 347 433 #arcP
Ts0 f19 expr data #txt
Ts0 f19 outCond ivp=="TaskF.ivp" #txt
Ts0 f19 227 77 348 529 #arcP
Ts0 f20 expr data #txt
Ts0 f20 outCond ivp=="TaskG.ivp" #txt
Ts0 f20 240 64 337 608 #arcP
Ts0 f20 1 288 64 #addKink
Ts0 f20 2 288 608 #addKink
Ts0 f20 1 0.5009191176470589 0 0 #arcLabel
Ts0 f21 expr data #txt
Ts0 f21 outCond ivp=="TaskH.ivp" #txt
Ts0 f21 222 78 162 465 #arcP
Ts0 f22 expr data #txt
Ts0 f22 outCond ivp=="TaskI.ivp" #txt
Ts0 f22 221 77 131 417 #arcP
Ts0 f23 expr data #txt
Ts0 f23 outCond ivp=="TaskJ.ivp" #txt
Ts0 f23 219 75 70 370 #arcP
Ts0 f24 expr data #txt
Ts0 f24 outCond ivp=="TaskK.ivp" #txt
Ts0 f24 218 74 88 275 #arcP
>Proto Ts0 .type internaltest.Data #txt
>Proto Ts0 .processKind NORMAL #txt
>Proto Ts0 0 0 32 24 18 0 #rect
>Proto Ts0 @|BIcon #fIcon
Ts0 f0 mainOut f4 tail #connect
Ts0 f4 head f3 in #connect
Ts0 f3 out f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 out f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f3 out f16 tail #connect
Ts0 f16 head f7 mainIn #connect
Ts0 f3 out f17 tail #connect
Ts0 f17 head f8 mainIn #connect
Ts0 f3 out f18 tail #connect
Ts0 f18 head f9 mainIn #connect
Ts0 f3 out f19 tail #connect
Ts0 f19 head f10 mainIn #connect
Ts0 f3 out f20 tail #connect
Ts0 f20 head f11 mainIn #connect
Ts0 f3 out f21 tail #connect
Ts0 f21 head f12 mainIn #connect
Ts0 f3 out f22 tail #connect
Ts0 f22 head f14 mainIn #connect
Ts0 f3 out f23 tail #connect
Ts0 f23 head f13 mainIn #connect
Ts0 f3 out f24 tail #connect
Ts0 f24 head f15 mainIn #connect
