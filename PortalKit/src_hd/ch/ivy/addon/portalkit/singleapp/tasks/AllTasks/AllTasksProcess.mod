[Ivy]
[>Created: Fri Aug 21 10:06:06 ICT 2015]
14F4E2FA899444D9 3.17 #module
>Proto >Proto Collection #zClass
Cs0 AllTasksProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @RichDialogMethodStart f5 '' #zField
Cs0 @RichDialogProcessEnd f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @RichDialogProcessStart f8 '' #zField
Cs0 @RichDialogProcessEnd f9 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f10 '' #zField
>Proto Cs0 Cs0 AllTasksProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f1 86 182 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;


in.tasks =    TaskUtils.findAllRunningTasks();



' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTask</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 78 116 36 24 20 -2 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 140 96 182 #arcP
Cs0 f0 guid 14C6F34C8BCE860B #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Number)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 86 46 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 96 66 96 116 #arcP
Cs0 f5 guid 14CC007D031BC3B4 #txt
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f5 method taskDetail(ch.ivyteam.ivy.workflow.ITask) #txt
Cs0 f5 disableUIEvents false #txt
Cs0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask iTask> param = methodEvent.getInputArguments();
' #txt
Cs0 f5 inParameterMapAction 'out.selectedTask=param.iTask;
' #txt
Cs0 f5 outParameterDecl '<> result;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskDetail</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 550 46 20 20 13 0 #rect
Cs0 f5 @|RichDialogMethodStartIcon #fIcon
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f6 550 182 20 20 13 0 #rect
Cs0 f6 @|RichDialogProcessEndIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 560 66 560 182 #arcP
Cs0 f8 guid 14D230A03E3B7AB6 #txt
Cs0 f8 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 798 46 20 20 13 0 #rect
Cs0 f8 @|RichDialogProcessStartIcon #fIcon
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f9 797 174 22 20 13 0 #rect
Cs0 f9 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.tasks = TaskUtils.findAllRunningTasks();





' #txt
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reload data when 
dialog close</name>
        <nameStyle>17,7
1,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 790 108 36 24 20 -17 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 808 66 808 108 #arcP
Cs0 f10 expr out #txt
Cs0 f10 808 132 808 174 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.tasks.AllTasks.AllTasksData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>starts</swimlaneLabel>
        <swimlaneLabel>methods</swimlaneLabel>
        <swimlaneLabel>events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>448</swimlaneSize>
    <swimlaneSize>288</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-3342388</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f5 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f8 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f11 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
