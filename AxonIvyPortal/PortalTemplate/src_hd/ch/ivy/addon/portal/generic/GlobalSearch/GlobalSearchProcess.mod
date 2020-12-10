[Ivy]
163AFDE4C30836BD 9.2.0 #module
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
Cs0 @GridStep f8 '' #zField
Cs0 @UdMethod f3 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @InfoButton f7 '' #zField
Cs0 @AnnotationArc f10 '' #zField
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
Cs0 f4 571 211 26 26 0 12 #rect
Cs0 f4 @|UdProcessEndIcon #fIcon
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.util.GrowlMessageUtils;

if(in.isWorkingOnTask) {
	GrowlMessageUtils.addFeedbackMessage(in.isTaskFinished, ivy.case);
}' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 144 202 144 44 -54 -16 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f3 guid 16FA88E321FFCEB1 #txt
Cs0 f3 method search(Boolean) #txt
Cs0 f3 inParameterDecl '<Boolean isWorkingOnTask> param;' #txt
Cs0 f3 inParameterMapAction 'out.isWorkingOnTask=param.isWorkingOnTask;
' #txt
Cs0 f3 outParameterDecl '<> result;' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>search(Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 51 211 26 26 -25 15 #rect
Cs0 f3 @|UdMethodIcon #fIcon
Cs0 f9 77 224 144 224 #arcP
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator.navigateToPortalGlobalSearch(in.keyword);' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task details</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 352 202 128 44 -59 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f5 288 224 352 224 #arcP
Cs0 f6 480 224 571 224 #arcP
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sample scenario to test&#13;
start a task &gt; perform global search</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 152 266 208 44 -97 -16 #rect
Cs0 f7 @|IBIcon #fIcon
Cs0 f10 256 266 216 246 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.GlobalSearch.GlobalSearchData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f8 mainOut f5 tail #connect
Cs0 f5 head f31 mainIn #connect
Cs0 f31 mainOut f6 tail #connect
Cs0 f6 head f4 mainIn #connect
Cs0 f7 ao f10 tail #connect
Cs0 f10 head f8 @CG|ai #connect
