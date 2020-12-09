[Ivy]
1612B5181308632F 7.5.0 #module
>Proto >Proto Collection #zClass
Ws0 WarningBeforeLeavingTaskProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @MessageFlowInP-0n messageIn messageIn #zField
Ws0 @MessageFlowOutP-0n messageOut messageOut #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @UdInit f0 '' #zField
Ws0 @UdProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @UdMethod f3 '' #zField
Ws0 @UdMethod f4 '' #zField
Ws0 @GridStep f8 '' #zField
Ws0 @UdProcessEnd f5 '' #zField
Ws0 @PushWFArc f6 '' #zField
Ws0 @PushWFArc f7 '' #zField
Ws0 @PushWFArc f9 '' #zField
Ws0 @InfoButton f10 '' #zField
Ws0 @AnnotationArc f11 '' #zField
>Proto Ws0 Ws0 WarningBeforeLeavingTaskProcess #zField
Ws0 f0 guid 15F80B73AFE43AE4 #txt
Ws0 f0 method start() #txt
Ws0 f0 inParameterDecl '<> param;' #txt
Ws0 f0 outParameterDecl '<> result;' #txt
Ws0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ws0 f0 83 51 26 26 -16 15 #rect
Ws0 f0 @|UdInitIcon #fIcon
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|UdProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 16FA866C14936E0D #txt
Ws0 f3 method leave() #txt
Ws0 f3 inParameterDecl '<> param;' #txt
Ws0 f3 outParameterDecl '<> result;' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave()</name>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 179 26 26 -25 15 #rect
Ws0 f3 @|UdMethodIcon #fIcon
Ws0 f4 guid 16FA866CC3BA9742 #txt
Ws0 f4 method reserve() #txt
Ws0 f4 inParameterDecl '<> param;' #txt
Ws0 f4 outParameterDecl '<> result;' #txt
Ws0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserve()</name>
    </language>
</elementInfo>
' #txt
Ws0 f4 83 243 26 26 -28 15 #rect
Ws0 f4 @|UdMethodIcon #fIcon
Ws0 f8 actionTable 'out=in;
' #txt
Ws0 f8 actionCode 'import ch.ivy.addon.portalkit.util.GrowlMessageUtils;

GrowlMessageUtils.addFeedbackMessage(in.isTaskFinished, ivy.case);' #txt
Ws0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Ws0 f8 232 170 144 44 -54 -16 #rect
Ws0 f8 @|StepIcon #fIcon
Ws0 f5 499 179 26 26 0 12 #rect
Ws0 f5 @|UdProcessEndIcon #fIcon
Ws0 f6 109 192 232 192 #arcP
Ws0 f7 376 192 499 192 #arcP
Ws0 f9 109 256 304 214 #arcP
Ws0 f9 1 304 256 #addKink
Ws0 f9 0 0.7103960471811249 0 0 #arcLabel
Ws0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sample scenario to test&#13;
start a task &gt; click on logo &gt; leave the task</name>
    </language>
</elementInfo>
' #txt
Ws0 f10 328 122 240 44 -114 -16 #rect
Ws0 f10 @|IBIcon #fIcon
Ws0 f11 328 144 304 170 #arcP
>Proto Ws0 .type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f6 tail #connect
Ws0 f6 head f8 mainIn #connect
Ws0 f8 mainOut f7 tail #connect
Ws0 f7 head f5 mainIn #connect
Ws0 f4 mainOut f9 tail #connect
Ws0 f9 head f8 mainIn #connect
Ws0 f10 ao f11 tail #connect
Ws0 f11 head f8 @CG|ai #connect
