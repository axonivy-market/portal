[Ivy]
14E53233041A883D 7.5.0 #module
>Proto >Proto Collection #zClass
Cr0 CaseHistoryCustomTaskFilter Big #zClass
Cr0 B #cInfo
Cr0 #process
Cr0 @TextInP .type .type #zField
Cr0 @TextInP .processKind .processKind #zField
Cr0 @AnnotationInP-0n ai ai #zField
Cr0 @MessageFlowInP-0n messageIn messageIn #zField
Cr0 @MessageFlowOutP-0n messageOut messageOut #zField
Cr0 @TextInP .xml .xml #zField
Cr0 @TextInP .responsibility .responsibility #zField
Cr0 @StartSub f0 '' #zField
Cr0 @EndSub f1 '' #zField
Cr0 @InfoButton f7 '' #zField
Cr0 @GridStep f3 '' #zField
Cr0 @PushWFArc f4 '' #zField
Cr0 @PushWFArc f2 '' #zField
>Proto Cr0 Cr0 CaseHistoryCustomTaskFilter #zField
Cr0 f0 inParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> param;' #txt
Cr0 f0 inParamTable 'out.tasks=param.tasks;
' #txt
Cr0 f0 outParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;' #txt
Cr0 f0 outParamTable 'result.tasks=in.filterdTasks;
' #txt
Cr0 f0 callSignature call(List<ch.ivyteam.ivy.workflow.ITask>) #txt
Cr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filterTasks(List&lt;ITask&gt;)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cr0 f0 51 83 26 26 17 -6 #rect
Cr0 f0 @|StartSubIcon #fIcon
Cr0 f1 51 323 26 26 14 0 #rect
Cr0 f1 @|EndSubIcon #fIcon
Cr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>IN: List&lt;ITask&gt;
OUT: List&lt;ITask&gt;

Use this subprocess to add filter logic 
in between. (e.g. remove system tasks)</name>
        <nameStyle>113,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cr0 f7 168 154 224 92 -107 -40 #rect
Cr0 f7 @|IBIcon #fIcon
Cr0 f3 actionTable 'out=in;
' #txt
Cr0 f3 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import java.util.ArrayList;
import ch.ivyteam.ivy.workflow.ITask;

in.filterdTasks.addAll(in.tasks);

for(ITask itask:in.tasks){
	if (itask.getActivator()==null || itask.getActivatorName()==null){
		in.filterdTasks.remove(itask);		
	}
}' #txt
Cr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter 
list of tasks</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cr0 f3 46 197 36 22 23 -18 #rect
Cr0 f3 @|StepIcon #fIcon
Cr0 f4 expr out #txt
Cr0 f4 64 109 64 197 #arcP
Cr0 f2 expr out #txt
Cr0 f2 64 219 64 323 #arcP
>Proto Cr0 .type portalKit_test.CaseHistoryCustomTaskFilterOverrideData #txt
>Proto Cr0 .processKind CALLABLE_SUB #txt
>Proto Cr0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <desc>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</desc>
    </language>
</elementInfo>
' #txt
>Proto Cr0 0 0 32 24 18 0 #rect
>Proto Cr0 @|BIcon #fIcon
Cr0 f0 mainOut f4 tail #connect
Cr0 f4 head f3 mainIn #connect
Cr0 f3 mainOut f2 tail #connect
Cr0 f2 head f1 mainIn #connect
