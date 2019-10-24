[Ivy]
1475D6ED2115CF9C 7.5.0 #module
>Proto >Proto Collection #zClass
bs0 bookRequestProcess Big #zClass
bs0 RD #cInfo
bs0 #process
bs0 @TextInP .type .type #zField
bs0 @TextInP .processKind .processKind #zField
bs0 @AnnotationInP-0n ai ai #zField
bs0 @MessageFlowInP-0n messageIn messageIn #zField
bs0 @MessageFlowOutP-0n messageOut messageOut #zField
bs0 @TextInP .xml .xml #zField
bs0 @TextInP .responsibility .responsibility #zField
bs0 @UdInit f0 '' #zField
bs0 @UdProcessEnd f1 '' #zField
bs0 @PushWFArc f2 '' #zField
bs0 @UdEvent f3 '' #zField
bs0 @UdExitEnd f4 '' #zField
bs0 @PushWFArc f5 '' #zField
>Proto bs0 bs0 bookRequestProcess #zField
bs0 f0 guid 1475D6ED239FD8D7 #txt
bs0 f0 method start(String,Date,Date,String,Boolean,Boolean,String,String,internalPortal.ProcessStatus) #txt
bs0 f0 inParameterDecl '<String Mitarbeiter,Date Von,Date Bis,String Art,Boolean beantragt,Boolean genehmigt,String Ablehnungsgrund,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
bs0 f0 inParameterMapAction 'out.Ablehnungsgrund=param.Ablehnungsgrund;
out.actualStepIndex=param.processStatus.actualStepIndex;
out.Art=param.Art;
out.beantragt=param.beantragt;
out.Bis=param.Bis;
out.genehmigt=param.genehmigt;
out.Mitarbeiter=param.Mitarbeiter;
out.steps=param.processStatus.steps;
out.Vertretung=param.Vertretung;
out.Von=param.Von;
' #txt
bs0 f0 outParameterDecl '<Number AnzahlTage,Number Resturlaubstage,Boolean storniert,internalPortal.ProcessStatus processStatus> result;' #txt
bs0 f0 outParameterMapAction 'result.AnzahlTage=in.AnzahlTage;
result.Resturlaubstage=in.Resturlaubstage;
result.storniert=in.storniert;
result.processStatus.actualStepIndex=in.actualStepIndex;
result.processStatus.steps=in.steps;
' #txt
bs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Date,Date,String,Boolean,Boolean,String,String)</name>
        <nameStyle>60,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
bs0 f0 51 51 26 26 -162 15 #rect
bs0 f0 @|UdInitIcon #fIcon
bs0 f0 -1|-1|-9671572 #nodeStyle
bs0 f1 243 51 26 26 0 12 #rect
bs0 f1 @|UdProcessEndIcon #fIcon
bs0 f1 -1|-1|-9671572 #nodeStyle
bs0 f2 expr out #txt
bs0 f2 77 64 243 64 #arcP
bs0 f3 guid 1475D6ED2416CE2A #txt
bs0 f3 actionTable 'out=in;
' #txt
bs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
bs0 f3 51 147 26 26 -15 12 #rect
bs0 f3 @|UdEventIcon #fIcon
bs0 f3 -1|-1|-9671572 #nodeStyle
bs0 f4 243 147 26 26 0 12 #rect
bs0 f4 @|UdExitEndIcon #fIcon
bs0 f4 -1|-1|-9671572 #nodeStyle
bs0 f5 expr out #txt
bs0 f5 77 160 243 160 #arcP
>Proto bs0 .type internaltest.ui.bookRequest.bookRequestData #txt
>Proto bs0 .processKind HTML_DIALOG #txt
>Proto bs0 -8 -8 16 16 16 26 #rect
>Proto bs0 '' #fIcon
bs0 f0 mainOut f2 tail #connect
bs0 f2 head f1 mainIn #connect
bs0 f3 mainOut f5 tail #connect
bs0 f5 head f4 mainIn #connect
