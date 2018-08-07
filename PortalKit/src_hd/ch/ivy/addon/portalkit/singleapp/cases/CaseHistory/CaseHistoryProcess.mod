[Ivy]
[>Created: Wed Mar 09 14:42:32 ICT 2016]
14C72CAD3ED740E8 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseHistoryProcess Big #zClass
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
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogMethodStart f7 '' #zField
Cs0 @RichDialogProcessEnd f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @RichDialogProcessStart f18 '' #zField
Cs0 @RichDialogProcessEnd f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @GridStep f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @CallSub f23 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @RichDialogProcessStart f5 '' #zField
Cs0 @PushWFArc f10 '' #zField
>Proto Cs0 Cs0 CaseHistoryProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f1 86 326 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.comparator.TaskVOComparator;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivy.addon.portalkit.util.ConverterUtils;

in.tasks.clear();

in.tasks = ConverterUtils.convertITasksToTaskVOs(in.iTasks);

if(in.#wfCase is initialized && in.wfCase.hasNotes()){
	List<TaskVO> notes = ConverterUtils.convertINotesToTaskVOs(in.wfCase.getNotes());
	in.tasks.addAll(notes);
}
in.tasks.sort(new TaskVOComparator());' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>convert to TaskVO
&amp; case notes</name>
        <nameStyle>18,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 78 244 36 24 21 6 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f0 guid 14C7342B64190064 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f0 method start(Number) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
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
Cs0 f7 guid 14CBAF9CFBBA4300 #txt
Cs0 f7 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f7 method taskDetail(ch.ivyteam.ivy.workflow.ICase,java.lang.Long) #txt
Cs0 f7 disableUIEvents false #txt
Cs0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ICase iCase,java.lang.Long taskId> param = methodEvent.getInputArguments();
' #txt
Cs0 f7 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
out.selectedTask = TaskUtils.convertToITask(param.iCase,param.taskId);' #txt
Cs0 f7 outParameterDecl '<> result;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskDetail(ICase,Long)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 638 54 20 20 13 0 #rect
Cs0 f7 @|RichDialogMethodStartIcon #fIcon
Cs0 f8 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f8 638 182 20 20 13 0 #rect
Cs0 f8 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 648 74 648 182 #arcP
Cs0 f18 guid 14CBB67C379CBE9A #txt
Cs0 f18 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f18 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData out;
' #txt
Cs0 f18 actionTable 'out=in;
' #txt
Cs0 f18 actionCode '// clear the selected Task
out.selectedTask = null;' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buttonCancelTaskDetailClicked</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 854 54 20 20 13 0 #rect
Cs0 f18 @|RichDialogProcessStartIcon #fIcon
Cs0 f19 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f19 854 182 20 20 13 0 #rect
Cs0 f19 @|RichDialogProcessEndIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 864 74 864 182 #arcP
Cs0 f6 guid 14CBCBF00BD86CC5 #txt
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f6 method commentDetail(ch.ivy.addon.portalkit.vo.TaskVO) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.vo.TaskVO selectedNote> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.comment=param.selectedNote.description;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>commentDetail(TaskVO)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 470 54 20 20 13 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f14 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f14 470 182 20 20 13 0 #rect
Cs0 f14 @|RichDialogProcessEndIcon #fIcon
Cs0 f15 expr out #txt
Cs0 f15 480 74 480 182 #arcP
Cs0 f2 expr out #txt
Cs0 f2 96 268 96 326 #arcP
Cs0 f21 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData out;
' #txt
Cs0 f21 actionTable 'out=in;
' #txt
Cs0 f21 actionCode 'import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivy.addon.portalkit.util.ConverterUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;

in.wfCase = CaseUtils.getCaseById(in.caseId);
in.iTasks=TaskUtils.getFinishedTasksByCase(in.wfCase);' #txt
Cs0 f21 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find All finished ITasks</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 78 108 36 24 20 -2 #rect
Cs0 f21 @|StepIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 96 66 96 108 #arcP
Cs0 f23 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f23 processCall 'Functional Processes/CaseHistoryCustomTaskFilter:call(List<ch.ivyteam.ivy.workflow.ITask>)' #txt
Cs0 f23 doCall true #txt
Cs0 f23 requestActionDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> param;
' #txt
Cs0 f23 requestMappingAction 'param.tasks=in.iTasks;
' #txt
Cs0 f23 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData out;
' #txt
Cs0 f23 responseMappingAction 'out=in;
out.iTasks=result.tasks;
' #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call custom task filter</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 78 172 36 24 20 -5 #rect
Cs0 f23 @|CallSubIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 96 132 96 172 #arcP
Cs0 f24 expr out #txt
Cs0 f24 96 196 96 244 #arcP
Cs0 f5 guid 1535A3E791A4F1A2 #txt
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
Cs0 f5 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateNotes</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 317 53 22 22 14 0 #rect
Cs0 f5 @|RichDialogProcessStartIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 328 75 114 256 #arcP
Cs0 f10 1 328 256 #addKink
Cs0 f10 1 0.0738796445080869 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseHistory.CaseHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>startMethods</swimlaneLabel>
        <swimlaneLabel>methods &amp; events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>712</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-3342388</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f7 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f18 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f6 mainOut f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f22 tail #connect
Cs0 f22 head f21 mainIn #connect
Cs0 f21 mainOut f4 tail #connect
Cs0 f4 head f23 mainIn #connect
Cs0 f23 mainOut f24 tail #connect
Cs0 f24 head f3 mainIn #connect
Cs0 f5 mainOut f10 tail #connect
Cs0 f10 head f3 mainIn #connect
