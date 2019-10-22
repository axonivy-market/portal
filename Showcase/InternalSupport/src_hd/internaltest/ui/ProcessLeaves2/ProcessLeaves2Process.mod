[Ivy]
1475D5B079CBD2F2 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessLeaves2Process Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 ProcessLeaves2Process #zField
Ps0 f0 guid 1475D5B07CAF2F91 #txt
Ps0 f0 method start(internalPortal.ProcessStatus) #txt
Ps0 f0 inParameterDecl '<internalPortal.ProcessStatus processStatus> param;' #txt
Ps0 f0 inParameterMapAction 'out.actualStepIndex=param.processStatus.actualStepIndex;
out.steps=param.processStatus.steps;
' #txt
Ps0 f0 outParameterDecl '<String Mitarbeiter,Date Von,Date Bis,String Art,Boolean beantragt,String Vertretung,internalPortal.ProcessStatus processStatus> result;' #txt
Ps0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.Von=in.Von;
result.Bis=in.Bis;
result.Art=in.Art;
result.beantragt=in.beantragt;
result.Vertretung=in.Vertretung;
result.processStatus.actualStepIndex=in.actualStepIndex;
result.processStatus.steps=in.steps;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 51 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f0 -1|-1|-9671572 #nodeStyle
Ps0 f1 243 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f1 -1|-1|-9671572 #nodeStyle
Ps0 f2 expr out #txt
Ps0 f2 77 64 243 64 #arcP
Ps0 f3 guid 1475D5B07D2F80B2 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 147 26 26 -15 12 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f3 -1|-1|-9671572 #nodeStyle
Ps0 f4 243 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f4 -1|-1|-9671572 #nodeStyle
Ps0 f5 expr out #txt
Ps0 f5 77 160 243 160 #arcP
>Proto Ps0 .type internaltest.ui.ProcessLeaves2.ProcessLeaves2Data #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
