[Ivy]
164B1ACD267C825F 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemDetailsProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @UdInit f6 '' #zField
Cs0 @PushWFArc f0 '' #zField
Cs0 @UdEvent f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
>Proto Cs0 Cs0 CaseItemDetailsProcess #zField
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f6 guid 16D8A8AD1A19B3C2 #txt
Cs0 f6 method start() #txt
Cs0 f6 inParameterDecl '<> param;' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 51 26 26 -19 15 #rect
Cs0 f6 @|UdInitIcon #fIcon
Cs0 f0 109 64 211 64 #arcP
Cs0 f2 guid 16D8A8B4B1D695F8 #txt
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 83 147 26 26 -14 15 #rect
Cs0 f2 @|UdEventIcon #fIcon
Cs0 f3 109 160 211 160 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemDetails.CaseItemDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f6 mainOut f0 tail #connect
Cs0 f0 head f1 mainIn #connect
Cs0 f2 mainOut f3 tail #connect
Cs0 f3 head f4 mainIn #connect
