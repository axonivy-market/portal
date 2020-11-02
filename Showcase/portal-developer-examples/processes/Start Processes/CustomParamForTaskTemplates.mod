[Ivy]
1718293B3F6E5478 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CustomParamForTaskTemplates Big #zClass
Cs0 B #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @StartRequest f0 '' #zField
Cs0 @EndTask f1 '' #zField
Cs0 @TaskSwitch f3 '' #zField
Cs0 @TkArc f4 '' #zField
Cs0 @UserDialog f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UserDialog f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
>Proto Cs0 Cs0 CustomParamForTaskTemplates #zField
Cs0 f0 outLink start.ivp #txt
Cs0 f0 inParamDecl '<> param;' #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature start() #txt
Cs0 f0 startName 'Templates with custom params' #txt
Cs0 f0 caseData businessCase.attach=true #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 @C|.responsibility Everybody #txt
Cs0 f0 81 49 30 30 -21 17 #rect
Cs0 f0 @|StartRequestIcon #fIcon
Cs0 f1 489 49 30 30 0 15 #rect
Cs0 f1 @|EndIcon #fIcon
Cs0 f3 actionTable 'out=in1;
' #txt
Cs0 f3 outLinks "TaskB.ivp","TaskA.ivp" #txt
Cs0 f3 taskData 'TaskB.NAM=Task template 8 with custom params
TaskB.customFields.STRING.embedInFrame="false"
TaskA.NAM=IFrame task with custom params' #txt
Cs0 f3 176 48 32 32 0 16 #rect
Cs0 f3 @|TaskSwitchIcon #fIcon
Cs0 f4 var in1 #txt
Cs0 f4 111 64 176 64 #arcP
Cs0 f5 dialogId com.axonivy.portal.developerexamples.testdata.CustomParamsForIFrame #txt
Cs0 f5 startMethod start() #txt
Cs0 f5 requestActionDecl '<> param;' #txt
Cs0 f5 responseMappingAction 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomParamsForIFrame</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 264 42 160 44 -72 -8 #rect
Cs0 f5 @|UserDialogIcon #fIcon
Cs0 f6 expr data #txt
Cs0 f6 outCond ivp=="TaskA.ivp" #txt
Cs0 f6 208 64 264 64 #arcP
Cs0 f2 424 64 489 64 #arcP
Cs0 f7 dialogId com.axonivy.portal.developerexamples.testdata.CustomParamsForTaskTemplate8 #txt
Cs0 f7 startMethod start() #txt
Cs0 f7 requestActionDecl '<> param;' #txt
Cs0 f7 responseMappingAction 'out=in;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomParamsForTaskTemplate8</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 248 138 208 44 -96 -8 #rect
Cs0 f7 @|UserDialogIcon #fIcon
Cs0 f8 expr data #txt
Cs0 f8 outCond ivp=="TaskB.ivp" #txt
Cs0 f8 192 80 248 160 #arcP
Cs0 f8 1 192 160 #addKink
Cs0 f8 1 0.1556203489120305 0 0 #arcLabel
Cs0 f9 456 160 504 79 #arcP
Cs0 f9 1 504 160 #addKink
Cs0 f9 0 0.7457560571053344 0 0 #arcLabel
>Proto Cs0 .type com.axonivy.portal.developerexamples.CustomParamForTaskTemplatesData #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 in #connect
Cs0 f3 out f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f5 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 out f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f9 tail #connect
Cs0 f9 head f1 mainIn #connect
