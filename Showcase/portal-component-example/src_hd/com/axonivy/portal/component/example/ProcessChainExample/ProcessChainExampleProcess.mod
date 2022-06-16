[Ivy]
18146A90DC0C058F 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 ProcessChainExampleProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdProcessEnd f7 '' #zField
Ds0 @UdEvent f6 '' #zField
Ds0 @UdInit f8 '' #zField
Ds0 @UdEvent f9 '' #zField
Ds0 @UdProcessEnd f10 '' #zField
Ds0 @UdExitEnd f11 '' #zField
Ds0 @GridStep f12 '' #zField
Ds0 @PushWFArc f13 '' #zField
Ds0 @PushWFArc f14 '' #zField
Ds0 @PushWFArc f15 '' #zField
Ds0 @PushWFArc f16 '' #zField
>Proto Ds0 Ds0 ProcessChainExampleProcess #zField
Ds0 f7 195 227 26 26 0 12 #rect
Ds0 f7 @|UdProcessEndIcon #fIcon
Ds0 f6 guid 18146ABF6BFEDF0B #txt
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'if (in.actualCurrentIndex == 8){
	in.actualCurrentIndex = 0;
} else{
	++in.actualCurrentIndex;
}' #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f6 67 227 26 26 -11 15 #rect
Ds0 f6 @|UdEventIcon #fIcon
Ds0 f8 guid 18146ABF6BFCC800 #txt
Ds0 f8 method start() #txt
Ds0 f8 inParameterDecl '<> param;' #txt
Ds0 f8 outParameterDecl '<> result;' #txt
Ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ds0 f8 67 35 26 26 -16 15 #rect
Ds0 f8 @|UdInitIcon #fIcon
Ds0 f9 guid 18146ABF6BFBF812 #txt
Ds0 f9 actionTable 'out=in;
' #txt
Ds0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ds0 f9 67 131 26 26 -15 12 #rect
Ds0 f9 @|UdEventIcon #fIcon
Ds0 f10 323 35 26 26 0 12 #rect
Ds0 f10 @|UdProcessEndIcon #fIcon
Ds0 f11 195 131 26 26 0 12 #rect
Ds0 f11 @|UdExitEndIcon #fIcon
Ds0 f12 actionTable 'out=in;
' #txt
Ds0 f12 actionCode 'import java.util.Arrays;

in.actualCurrentIndex = 2;
in.steps = Arrays.asList("Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8","Step 9");' #txt
Ds0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ds0 f12 152 26 112 44 -21 -8 #rect
Ds0 f12 @|StepIcon #fIcon
Ds0 f13 expr out #txt
Ds0 f13 264 48 323 48 #arcP
Ds0 f14 expr out #txt
Ds0 f14 93 240 195 240 #arcP
Ds0 f15 expr out #txt
Ds0 f15 93 48 152 48 #arcP
Ds0 f16 expr out #txt
Ds0 f16 93 144 195 144 #arcP
>Proto Ds0 .type com.axonivy.portal.component.example.ProcessChainExample.ProcessChainExampleData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f9 mainOut f16 tail #connect
Ds0 f16 head f11 mainIn #connect
Ds0 f6 mainOut f14 tail #connect
Ds0 f14 head f7 mainIn #connect
Ds0 f8 mainOut f15 tail #connect
Ds0 f15 head f12 mainIn #connect
Ds0 f12 mainOut f13 tail #connect
Ds0 f13 head f10 mainIn #connect
