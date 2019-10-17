[Ivy]
168DF9B20193A93C 7.5.0 #module
>Proto >Proto Collection #zClass
Gs0 GrantOrDenySpecificPermissionProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @UdInit f0 '' #zField
Gs0 @UdProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @UdEvent f3 '' #zField
Gs0 @UdExitEnd f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
>Proto Gs0 Gs0 GrantOrDenySpecificPermissionProcess #zField
Gs0 f0 guid 168DF9B203F80ECE #txt
Gs0 f0 method start(Boolean) #txt
Gs0 f0 inParameterDecl '<Boolean isGrantPermission> param;' #txt
Gs0 f0 inParameterMapAction 'out.isGrantPermission=param.isGrantPermission;
' #txt
Gs0 f0 outParameterDecl '<String permission> result;' #txt
Gs0 f0 outParameterMapAction 'result.permission=in.permission;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Boolean)</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f0 @|UdInitIcon #fIcon
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|UdProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 109 64 211 64 #arcP
Gs0 f3 guid 168DF9B205E9A038 #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -15 12 #rect
Gs0 f3 @|UdEventIcon #fIcon
Gs0 f4 211 147 26 26 0 12 #rect
Gs0 f4 @|UdExitEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 109 160 211 160 #arcP
>Proto Gs0 .type ch.ivy.addon.portalkit.test.GrantOrDenySpecificPermission.GrantOrDenySpecificPermissionData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
