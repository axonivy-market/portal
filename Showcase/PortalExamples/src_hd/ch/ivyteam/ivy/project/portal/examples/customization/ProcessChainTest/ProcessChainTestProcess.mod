[Ivy]
164DB519052DCBE0 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessChainTestProcess Big #zClass
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
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdEvent f6 '' #zField
Ps0 @UdProcessEnd f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 ProcessChainTestProcess #zField
Ps0 f0 guid 164DB51906823131 #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 339 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f3 guid 164DB51906E4FABC #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 12 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 109 160 211 160 #arcP
Ps0 f6 guid 164DB553C94F9CE9 #txt
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'if (in.actualCurrentIndex == 8){
	in.actualCurrentIndex = 0;
} else{
	++in.actualCurrentIndex;
}' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 83 243 26 26 -11 15 #rect
Ps0 f6 @|UdEventIcon #fIcon
Ps0 f7 211 243 26 26 0 12 #rect
Ps0 f7 @|UdProcessEndIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 109 256 211 256 #arcP
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'in.actualCurrentIndex = 0;' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ps0 f9 168 42 112 44 -21 -8 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 109 64 168 64 #arcP
Ps0 f2 expr out #txt
Ps0 f2 280 64 339 64 #arcP
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.customization.ProcessChainTest.ProcessChainTestData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f0 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f9 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
