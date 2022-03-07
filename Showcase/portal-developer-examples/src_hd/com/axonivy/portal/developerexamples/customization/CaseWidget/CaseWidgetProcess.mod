[Ivy]
176CBB45115F7981 9.4.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseWidgetProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdProcessEnd f6 '' #zField
Cs0 @UdInit f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 CaseWidgetProcess #zField
Cs0 f6 373 85 22 22 14 0 #rect
Cs0 f7 guid 176CC20160848611 #txt
Cs0 f7 method useView(CaseView) #txt
Cs0 f7 inParameterDecl '<ch.ivy.addon.portal.generic.view.CaseView caseView> param;' #txt
Cs0 f7 inParameterMapAction 'out.caseView=param.caseView;
' #txt
Cs0 f7 outParameterDecl '<> result;' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(CaseView)</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 85 85 22 22 14 0 #rect
Cs0 f8 expr out #txt
Cs0 f8 107 96 373 96 #arcP
>Proto Cs0 .type com.axonivy.portal.developerexamples.customization.CaseWidget.CaseWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f7 mainOut f8 tail #connect
Cs0 f8 head f6 mainIn #connect
