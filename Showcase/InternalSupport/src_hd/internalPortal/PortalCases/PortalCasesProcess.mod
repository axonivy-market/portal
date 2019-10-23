[Ivy]
15FA4033B2D9F3EE 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalCasesProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdInit f2 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 PortalCasesProcess #zField
Ps0 f1 477 53 22 22 14 0 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 guid 1573372FDA37BBD6 #txt
Ps0 f2 method useViewWithStateMenu(ch.ivy.addon.portal.generic.view.CaseView,String) #txt
Ps0 f2 inParameterDecl '<ch.ivy.addon.portal.generic.view.CaseView caseView,String menuState> param;' #txt
Ps0 f2 inParameterMapAction 'out.caseView=param.caseView;
out.menuState=param.menuState;
' #txt
Ps0 f2 outParameterDecl '<> result;' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useViewWithStateMenu(CaseView,String)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 85 53 22 22 14 0 #rect
Ps0 f2 @|UdInitIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 107 64 477 64 #arcP
>Proto Ps0 .type internalPortal.PortalCases.PortalCasesData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f2 mainOut f5 tail #connect
Ps0 f5 head f1 mainIn #connect
