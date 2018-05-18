[Ivy]
163716D8E0C79F71 3.23 #module
>Proto >Proto Collection #zClass
Pn0 PostLogin Big #zClass
Pn0 B #cInfo
Pn0 #process
Pn0 @TextInP .resExport .resExport #zField
Pn0 @TextInP .type .type #zField
Pn0 @TextInP .processKind .processKind #zField
Pn0 @AnnotationInP-0n ai ai #zField
Pn0 @MessageFlowInP-0n messageIn messageIn #zField
Pn0 @MessageFlowOutP-0n messageOut messageOut #zField
Pn0 @TextInP .xml .xml #zField
Pn0 @TextInP .responsibility .responsibility #zField
Pn0 @StartSub f0 '' #zField
Pn0 @EndSub f1 '' #zField
Pn0 @InfoButton f3 '' #zField
Pn0 @GridStep f5 '' #zField
Pn0 @PushWFArc f6 '' #zField
Pn0 @PushWFArc f2 '' #zField
>Proto Pn0 Pn0 PostLogin #zField
Pn0 f0 inParamDecl '<> param;' #txt
Pn0 f0 outParamDecl '<> result;
' #txt
Pn0 f0 actionDecl '_ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData out;
' #txt
Pn0 f0 callSignature call() #txt
Pn0 f0 type _ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData #txt
Pn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Pn0 f0 81 49 30 30 -13 17 #rect
Pn0 f0 @|StartSubIcon #fIcon
Pn0 f1 type _ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData #txt
Pn0 f1 337 49 30 30 0 15 #rect
Pn0 f1 @|EndSubIcon #fIcon
Pn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add your customized post-login step</name>
        <nameStyle>35
</nameStyle>
    </language>
</elementInfo>
' #txt
Pn0 f3 112 137 208 30 -99 -8 #rect
Pn0 f3 @|IBIcon #fIcon
Pn0 f5 actionDecl '_ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData out;
' #txt
Pn0 f5 actionTable 'out=in;
' #txt
Pn0 f5 actionCode 'Date now = new Date();
File file = new File("login-events.log", false);
if (!file.exists()) {
	file.createNewFile();
}
DateTime time = new DateTime();
String content = file.read();
file.write(content + ivy.session.getSessionUserName() + " logged in Axon.ivy Portal at " + time.format("medium") + "\r\n");
' #txt
Pn0 f5 type _ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData #txt
Pn0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Log event</name>
    </language>
</elementInfo>
' #txt
Pn0 f5 168 42 112 44 -26 -8 #rect
Pn0 f5 @|StepIcon #fIcon
Pn0 f6 expr out #txt
Pn0 f6 111 64 168 64 #arcP
Pn0 f2 expr out #txt
Pn0 f2 280 64 337 64 #arcP
>Proto Pn0 .type _ch.ivyteam.ivy.project.portal.examples.PostLoginOverrideData #txt
>Proto Pn0 .processKind CALLABLE_SUB #txt
>Proto Pn0 0 0 32 24 18 0 #rect
>Proto Pn0 @|BIcon #fIcon
Pn0 f0 mainOut f6 tail #connect
Pn0 f6 head f5 mainIn #connect
Pn0 f5 mainOut f2 tail #connect
Pn0 f2 head f1 mainIn #connect
