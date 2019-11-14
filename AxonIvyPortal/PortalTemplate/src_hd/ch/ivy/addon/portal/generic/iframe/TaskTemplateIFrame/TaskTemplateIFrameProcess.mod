[Ivy]
16E1584824AEC16C 7.5.0 #module
>Proto >Proto Collection #zClass
Bs0 TaskTemplateIFrameProcess Big #zClass
Bs0 RD #cInfo
Bs0 #process
Bs0 @TextInP .type .type #zField
Bs0 @TextInP .processKind .processKind #zField
Bs0 @TextInP .xml .xml #zField
Bs0 @TextInP .responsibility .responsibility #zField
Bs0 @UdInit f0 '' #zField
Bs0 @UdProcessEnd f1 '' #zField
Bs0 @GridStep f3 '' #zField
Bs0 @PushWFArc f4 '' #zField
Bs0 @PushWFArc f2 '' #zField
>Proto Bs0 Bs0 TaskTemplateIFrameProcess #zField
Bs0 f0 guid 16E11EC8F88D54A6 #txt
Bs0 f0 method start(String,Number) #txt
Bs0 f0 inParameterDecl '<String url,Number taskId> param;' #txt
Bs0 f0 inParameterMapAction 'out.taskId=param.taskId;
out.url=param.url;
' #txt
Bs0 f0 outParameterDecl '<> result;' #txt
Bs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Number)</name>
    </language>
</elementInfo>
' #txt
Bs0 f0 83 51 26 26 -15 15 #rect
Bs0 f0 @|UdInitIcon #fIcon
Bs0 f1 339 51 26 26 0 12 #rect
Bs0 f1 @|UdProcessEndIcon #fIcon
Bs0 f3 actionTable 'out=in;
' #txt
Bs0 f3 actionCode 'out.task = ivy.wf.findTask(in.taskId);' #txt
Bs0 f3 security system #txt
Bs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find task by id</name>
    </language>
</elementInfo>
' #txt
Bs0 f3 200 42 112 44 -37 -8 #rect
Bs0 f3 @|StepIcon #fIcon
Bs0 f4 109 64 200 64 #arcP
Bs0 f2 312 64 339 64 #arcP
>Proto Bs0 .type ch.ivy.addon.portal.generic.iframe.TaskTemplateIFrame.TaskTemplateIFrameData #txt
>Proto Bs0 .processKind HTML_DIALOG #txt
>Proto Bs0 -8 -8 16 16 16 26 #rect
>Proto Bs0 '' #fIcon
Bs0 f0 mainOut f4 tail #connect
Bs0 f4 head f3 mainIn #connect
Bs0 f3 mainOut f2 tail #connect
Bs0 f2 head f1 mainIn #connect
