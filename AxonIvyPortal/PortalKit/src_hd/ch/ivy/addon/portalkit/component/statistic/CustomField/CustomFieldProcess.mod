[Ivy]
16A76A01ED2FA2D0 9.3.1 #module
>Proto >Proto Collection #zClass
Cs0 CustomFieldProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f12 '' #zField
Cs0 @UdProcessEnd f10 '' #zField
Cs0 @UdMethod f9 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @UdMethod f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
>Proto Cs0 Cs0 CustomFieldProcess #zField
Cs0 f0 guid 16A76A01F08609A3 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f3 guid 16A76A01F317AB59 #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 12 #rect
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f5 expr out #txt
Cs0 f5 109 160 211 160 #arcP
Cs0 f12 actionTable 'out=in;
' #txt
Cs0 f12 actionCode 'if(!in.tempSelectedCustomValues.contains(in.selectedCustomValue)) {
	in.tempSelectedCustomValues.add(in.selectedCustomValue);
	in.selectedCustomValue = null;
}
' #txt
Cs0 f12 169 236 112 44 0 -8 #rect
Cs0 f10 340 245 26 26 0 12 #rect
Cs0 f9 guid 16A76A3527AC714D #txt
Cs0 f9 method addCustomVal() #txt
Cs0 f9 inParameterDecl '<> param;' #txt
Cs0 f9 outParameterDecl '<> result;' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addCustomVal()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 84 245 26 26 -45 15 #rect
Cs0 f11 expr out #txt
Cs0 f11 281 258 340 258 #arcP
Cs0 f13 expr out #txt
Cs0 f13 110 258 169 258 #arcP
Cs0 f7 211 339 26 26 0 12 #rect
Cs0 f14 guid 16ADE4E91DC3CB1F #txt
Cs0 f14 method cleanUp() #txt
Cs0 f14 inParameterDecl '<> param;' #txt
Cs0 f14 inParameterMapAction 'out.selectedCustomValues=new java.util.ArrayList();
out.tempSelectedCustomValues=new java.util.ArrayList();
' #txt
Cs0 f14 outParameterDecl '<> result;' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanUp()</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 83 339 26 26 -27 15 #rect
Cs0 f15 expr out #txt
Cs0 f15 109 352 211 352 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.statistic.CustomField.CustomFieldData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f9 mainOut f13 tail #connect
Cs0 f13 head f12 mainIn #connect
Cs0 f12 mainOut f11 tail #connect
Cs0 f11 head f10 mainIn #connect
Cs0 f14 mainOut f15 tail #connect
Cs0 f15 head f7 mainIn #connect
