[Ivy]
16CF626AB4B6E5B2 7.5.0 #module
>Proto >Proto Collection #zClass
Gs0 GlobalSearchLeftMenuProcess Big #zClass
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
Gs0 @CallSub f6 '' #zField
Gs0 @UdEvent f7 '' #zField
Gs0 @UdProcessEnd f8 '' #zField
Gs0 @PushWFArc f9 '' #zField
Gs0 @PushWFArc f10 '' #zField
>Proto Gs0 Gs0 GlobalSearchLeftMenuProcess #zField
Gs0 f0 guid 16CF626AB9D7CE8D #txt
Gs0 f0 method start() #txt
Gs0 f0 inParameterDecl '<> param;' #txt
Gs0 f0 outParameterDecl '<> result;' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f0 @|UdInitIcon #fIcon
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|UdProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 109 64 211 64 #arcP
Gs0 f6 processCall 'Functional Processes/OpenPortalSearch:call(String)' #txt
Gs0 f6 requestActionDecl '<String keyword> param;' #txt
Gs0 f6 requestMappingAction 'param.keyword=in.keyword;
' #txt
Gs0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchLeftMenu.GlobalSearchLeftMenuData out;
' #txt
Gs0 f6 responseMappingAction 'out=in;
' #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalSearch</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f6 168 138 112 44 -50 -8 #rect
Gs0 f6 @|CallSubIcon #fIcon
Gs0 f7 guid 16CF6284713656E6 #txt
Gs0 f7 actionTable 'out=in;
' #txt
Gs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>search</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f7 83 147 26 26 -19 15 #rect
Gs0 f7 @|UdEventIcon #fIcon
Gs0 f8 339 147 26 26 0 12 #rect
Gs0 f8 @|UdProcessEndIcon #fIcon
Gs0 f9 expr out #txt
Gs0 f9 109 160 168 160 #arcP
Gs0 f10 expr out #txt
Gs0 f10 280 160 339 160 #arcP
>Proto Gs0 .type ch.ivy.addon.portal.generic.GlobalSearchLeftMenu.GlobalSearchLeftMenuData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f7 mainOut f9 tail #connect
Gs0 f9 head f6 mainIn #connect
Gs0 f6 mainOut f10 tail #connect
Gs0 f10 head f8 mainIn #connect
