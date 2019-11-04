[Ivy]
1475D5F3DD0B29B3 7.5.0 #module
>Proto >Proto Collection #zClass
as0 approveLeaveProcess Big #zClass
as0 RD #cInfo
as0 #process
as0 @TextInP .type .type #zField
as0 @TextInP .processKind .processKind #zField
as0 @AnnotationInP-0n ai ai #zField
as0 @MessageFlowInP-0n messageIn messageIn #zField
as0 @MessageFlowOutP-0n messageOut messageOut #zField
as0 @TextInP .xml .xml #zField
as0 @TextInP .responsibility .responsibility #zField
as0 @UdInit f0 '' #zField
as0 @UdProcessEnd f1 '' #zField
as0 @PushWFArc f2 '' #zField
as0 @UdEvent f3 '' #zField
as0 @UdExitEnd f4 '' #zField
as0 @GridStep f6 '' #zField
as0 @PushWFArc f7 '' #zField
as0 @PushWFArc f5 '' #zField
>Proto as0 as0 approveLeaveProcess #zField
as0 f0 guid 1475D5F3DF47BD15 #txt
as0 f0 method start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
as0 f0 inParameterDecl '<String Mitarbeiter,Date Von,Date Bis,Boolean beantragt,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
as0 f0 inParameterMapAction 'out.actualStepIndex=param.processStatus.actualStepIndex;
out.beantragt=param.beantragt;
out.Bis=param.Bis;
out.Mitarbeiter=param.Mitarbeiter;
out.steps=param.processStatus.steps;
out.Vertretung=param.Vertretung;
out.Von=param.Von;
' #txt
as0 f0 outParameterDecl '<String Mitarbeiter,Boolean genehmigt,String Ablehnungsgrund,internalPortal.ProcessStatus processStatus> result;' #txt
as0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.genehmigt=in.genehmigt;
result.Ablehnungsgrund=in.Ablehnungsgrund;
result.processStatus.actualStepIndex=in.actualStepIndex;
result.processStatus.steps=in.steps;
' #txt
as0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Date,Date,Boolean,String)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f0 51 51 26 26 -103 15 #rect
as0 f0 @|UdInitIcon #fIcon
as0 f0 -1|-1|-9671572 #nodeStyle
as0 f1 243 51 26 26 0 12 #rect
as0 f1 @|UdProcessEndIcon #fIcon
as0 f1 -1|-1|-9671572 #nodeStyle
as0 f2 expr out #txt
as0 f2 77 64 243 64 #arcP
as0 f3 guid 1475D5F3DFB2CA23 #txt
as0 f3 actionTable 'out=in;
' #txt
as0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f3 51 147 26 26 -15 12 #rect
as0 f3 @|UdEventIcon #fIcon
as0 f3 -1|-1|-9671572 #nodeStyle
as0 f4 307 147 26 26 0 12 #rect
as0 f4 @|UdExitEndIcon #fIcon
as0 f4 -1|-1|-9671572 #nodeStyle
as0 f6 actionTable 'out=in;
' #txt
as0 f6 actionCode ' ivy.case.getBusinessCase().setStageId("postponed");' #txt
as0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Jump to Cancel Request</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f6 120 138 144 44 -68 -8 #rect
as0 f6 @|StepIcon #fIcon
as0 f7 expr out #txt
as0 f7 77 160 120 160 #arcP
as0 f5 expr out #txt
as0 f5 264 160 307 160 #arcP
>Proto as0 .type internaltest.ui.approveLeave.approveLeaveData #txt
>Proto as0 .processKind HTML_DIALOG #txt
>Proto as0 -8 -8 16 16 16 26 #rect
>Proto as0 '' #fIcon
as0 f0 mainOut f2 tail #connect
as0 f2 head f1 mainIn #connect
as0 f3 mainOut f7 tail #connect
as0 f7 head f6 mainIn #connect
as0 f6 mainOut f5 tail #connect
as0 f5 head f4 mainIn #connect
