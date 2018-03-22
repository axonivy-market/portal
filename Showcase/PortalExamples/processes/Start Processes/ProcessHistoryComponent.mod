[Ivy]
1624C1C79661758C 3.20 #module
>Proto >Proto Collection #zClass
Pt0 ProcessHistoryComponent Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @RichDialog f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @CallSub f5 '' #zField
Pt0 @StartRequest f6 '' #zField
Pt0 @EndTask f8 '' #zField
Pt0 @TaskSwitchSimple f7 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @TkArc f10 '' #zField
Pt0 @PushWFArc f20 '' #zField
Pt0 @PushWFArc f11 '' #zField
>Proto Pt0 Pt0 ProcessHistoryComponent #zField
Pt0 f0 outLink viewProcessHistoryOfAlphaCompany.ivp #txt
Pt0 f0 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 actionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f0 guid 1624C1C79758E6C7 #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature viewProcessHistoryOfAlphaCompany() #txt
Pt0 f0 persist false #txt
Pt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 caseData businessCase.attach=true #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryOfAlphaCompany.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 201 49 30 30 -111 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f1 585 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 targetWindow NEW #txt
Pt0 f3 targetDisplay TOP #txt
Pt0 f3 richDialogId ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponent #txt
Pt0 f3 startMethod start() #txt
Pt0 f3 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f3 requestActionDecl '<> param;' #txt
Pt0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
' #txt
Pt0 f3 isAsynch false #txt
Pt0 f3 isInnerRd false #txt
Pt0 f3 userContext '* ' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Alpha Company Process History</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 336 42 192 44 -88 -8 #rect
Pt0 f3 @|RichDialogIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 231 64 336 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 528 64 585 64 #arcP
Pt0 f5 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f5 processCall 'Functional Processes/SetBusinessEntityId:call(String)' #txt
Pt0 f5 doCall true #txt
Pt0 f5 requestActionDecl '<java.lang.String businessEntityId> param;
' #txt
Pt0 f5 requestMappingAction 'param.businessEntityId="alpha";
' #txt
Pt0 f5 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SetBusinessEntityId</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 407 147 128 44 -55 -8 #rect
Pt0 f5 @|CallSubIcon #fIcon
Pt0 f6 outLink createAlphaCompany.ivp #txt
Pt0 f6 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f6 inParamDecl '<> param;' #txt
Pt0 f6 actionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f6 guid 1624C747CBB8E704 #txt
Pt0 f6 requestEnabled true #txt
Pt0 f6 triggerEnabled false #txt
Pt0 f6 callSignature createAlphaCompany() #txt
Pt0 f6 persist false #txt
Pt0 f6 startName 'Inspect resource' #txt
Pt0 f6 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f6 caseData 'case.name=Alpha Company
businessCase.attach=true' #txt
Pt0 f6 showInStartList 1 #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createAlphaCompany.ivp</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 @C|.responsibility Everybody #txt
Pt0 f6 168 154 30 30 -68 17 #rect
Pt0 f6 @|StartRequestIcon #fIcon
Pt0 f8 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f8 776 154 30 30 0 15 #rect
Pt0 f8 @|EndIcon #fIcon
Pt0 f7 actionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f7 actionTable 'out=in1;
' #txt
Pt0 f7 outTypes "ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData" #txt
Pt0 f7 outLinks "TaskA.ivp" #txt
Pt0 f7 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Alpha Company
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f7 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f7 template "" #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Alpha Company</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f7 648 154 30 30 -43 17 #rect
Pt0 f7 @|TaskSwitchSimpleIcon #fIcon
Pt0 f19 actionDecl 'ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData out;
' #txt
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'ivy.case.setProcess("Alpha Company", "Alpha Company");' #txt
Pt0 f19 security system #txt
Pt0 f19 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set process&#xD;
code name</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 255 147 112 44 -33 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f9 expr out #txt
Pt0 f9 367 169 407 169 #arcP
Pt0 f10 expr out #txt
Pt0 f10 type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
Pt0 f10 var in1 #txt
Pt0 f10 535 169 648 169 #arcP
Pt0 f20 expr out #txt
Pt0 f20 198 169 255 169 #arcP
Pt0 f11 expr data #txt
Pt0 f11 outCond ivp=="TaskA.ivp" #txt
Pt0 f11 678 169 776 169 #arcP
>Proto Pt0 .type ch.ivyteam.ivy.project.portal.examples.component.ProcessHistoryComponentData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
Pt0 f5 mainOut f10 tail #connect
Pt0 f10 head f7 in #connect
Pt0 f7 out f11 tail #connect
Pt0 f11 head f8 mainIn #connect
Pt0 f6 mainOut f20 tail #connect
Pt0 f20 head f19 mainIn #connect
Pt0 f19 mainOut f9 tail #connect
Pt0 f9 head f5 mainIn #connect
