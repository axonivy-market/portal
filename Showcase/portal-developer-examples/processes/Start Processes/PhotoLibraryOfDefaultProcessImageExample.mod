[Ivy]
179D499523153784 9.3.0 #module
>Proto >Proto Collection #zClass
Pe0 PhotoLibraryOfDefaultProcessImageExample Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartRequest f0 '' #zField
Pe0 @EndTask f1 '' #zField
Pe0 @UserDialog f3 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @TaskSwitchSimple f5 '' #zField
Pe0 @TkArc f6 '' #zField
Pe0 @PushWFArc f2 '' #zField
>Proto Pe0 Pe0 PhotoLibraryOfDefaultProcessImageExample #zField
Pe0 f0 outLink start.ivp #txt
Pe0 f0 inParamDecl '<> param;' #txt
Pe0 f0 requestEnabled true #txt
Pe0 f0 triggerEnabled false #txt
Pe0 f0 callSignature start() #txt
Pe0 f0 startName 'Default Process Image selection example' #txt
Pe0 f0 startDescription 'Loading photo for default process image by selection' #txt
Pe0 f0 startCategory Example #txt
Pe0 f0 startCustomFields processImage=<%\=ivy.cms.co("/images/process/INNOVATION")%> #txt
Pe0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Pe0 f0 wfuser 1 #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pe0 f0 @C|.responsibility Everybody #txt
Pe0 f0 81 49 30 30 -21 17 #rect
Pe0 f1 625 49 30 30 0 15 #rect
Pe0 f3 dialogId com.axonivy.portal.developerexamples.showcase.PhotoLibraryOfDefaultProcessImageExample #txt
Pe0 f3 startMethod start() #txt
Pe0 f3 requestActionDecl '<> param;' #txt
Pe0 f3 responseMappingAction 'out=in;
' #txt
Pe0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PhotoLibraryOfDefaultProcessImageExample</name>
    </language>
</elementInfo>
' #txt
Pe0 f3 168 42 256 44 -124 -8 #rect
Pe0 f4 111 64 168 64 #arcP
Pe0 f5 actionTable 'out=in1;
' #txt
Pe0 f5 taskData 'TaskA.DESC=Task created by Photo Library Of Default Process Image Example
TaskA.NAM=Task created by Photo Library Of Default Process Image Example' #txt
Pe0 f5 513 49 30 30 0 16 #rect
Pe0 f6 424 64 513 64 #arcP
Pe0 f2 543 64 625 64 #arcP
>Proto Pe0 .type com.axonivy.portal.developerexamples.Data #txt
>Proto Pe0 .processKind NORMAL #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f0 mainOut f4 tail #connect
Pe0 f4 head f3 mainIn #connect
Pe0 f3 mainOut f6 tail #connect
Pe0 f6 head f5 in #connect
Pe0 f5 out f2 tail #connect
Pe0 f2 head f1 mainIn #connect
