[Ivy]
163AFDE4C30836BD 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 GlobalSearchProcess Big #zClass
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
Cs0 @UdProcessEnd f4 '' #zField
Cs0 @CallSub f6 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @UdEvent f3 '' #zField
>Proto Cs0 Cs0 GlobalSearchProcess #zField
Cs0 f0 guid 163AFD8B43132CEB #txt
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
Cs0 f0 51 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 307 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 77 96 307 96 #arcP
Cs0 f4 307 211 26 26 0 12 #rect
Cs0 f4 @|UdProcessEndIcon #fIcon
Cs0 f6 processCall 'Functional Processes/OpenPortalSearch:call(String)' #txt
Cs0 f6 requestActionDecl '<String keyword> param;' #txt
Cs0 f6 requestMappingAction 'param.keyword=in.keyword;
' #txt
Cs0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearch.GlobalSearchData out;
' #txt
Cs0 f6 responseMappingAction 'out=in;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalSearch</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 136 202 112 44 -50 -8 #rect
Cs0 f6 @|CallSubIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 248 224 307 224 #arcP
Cs0 f7 expr out #txt
Cs0 f7 77 224 136 224 #arcP
Cs0 f3 guid 163AFD8D89298F7B #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>search</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 51 211 26 26 -19 15 #rect
Cs0 f3 @|UdEventIcon #fIcon
>Proto Cs0 .type ch.ivy.addon.portal.generic.GlobalSearch.GlobalSearchData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
