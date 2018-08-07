[Ivy]
[>Created: Wed Jul 08 15:23:03 ICT 2015]
14C68DF8344E92B4 3.17 #module
>Proto >Proto Collection #zClass
Cs0 CaseTableTestProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f5 '' #zField
Cs0 @RichDialogInitStart f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f4 '' #zField
>Proto Cs0 Cs0 CaseTableTestProcess #zField
Cs0 f0 guid 14AEBD26D026CC71 #txt
Cs0 f0 type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
Cs0 f0 method startForRunningCase() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inActionCode 'out.isFisnishedCase = false;
out.isIPropertyFilterCase=false;
out.isRunningCase=true;' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startForRunningCase()</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 70 54 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
Cs0 f1 70 270 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivyteam.logicalexpression.RelationalOperator;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.IPropertyFilter;

IPropertyFilter<CaseProperty> filter = ivy.wf.createCasePropertyFilter(CaseProperty.STATE, 
				RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
		filter = filter.or(CaseProperty.STATE,
				RelationalOperator.EQUAL, CaseState.CREATED.intValue());

in.propertyFilter=filter;
' #txt
Cs0 f3 type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Filter Running Cases</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 446 116 36 24 25 -6 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 464 140 80 270 #arcP
Cs0 f2 1 464 184 #addKink
Cs0 f2 2 80 184 #addKink
Cs0 f2 1 0.6615943806342758 0 0 #arcLabel
Cs0 f5 guid 14AEBE5FE78781CF #txt
Cs0 f5 type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
Cs0 f5 method startForFinishedCase() #txt
Cs0 f5 disableUIEvents true #txt
Cs0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f5 inActionCode 'out.isFisnishedCase = true;
out.isIPropertyFilterCase=false;
out.isRunningCase=false;' #txt
Cs0 f5 outParameterDecl '<> result;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startForFinishedCase()</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 230 54 20 20 13 0 #rect
Cs0 f5 @|RichDialogInitStartIcon #fIcon
Cs0 f7 guid 14E61480A2D94A2C #txt
Cs0 f7 type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
Cs0 f7 method startWithIPropertyFilter() #txt
Cs0 f7 disableUIEvents true #txt
Cs0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f7 inActionCode 'out.isFisnishedCase = false;
out.isIPropertyFilterCase=true;
out.isRunningCase=false;' #txt
Cs0 f7 outParameterDecl '<> result;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithIPropertyFilter()</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 453 53 22 22 14 0 #rect
Cs0 f7 @|RichDialogInitStartIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 464 75 464 116 #arcP
Cs0 f8 0 0.330698180032529 0 0 #arcLabel
Cs0 f9 expr out #txt
Cs0 f9 80 74 80 270 #arcP
Cs0 f4 expr out #txt
Cs0 f4 240 74 80 270 #arcP
Cs0 f4 1 240 136 #addKink
Cs0 f4 2 80 136 #addKink
Cs0 f4 1 0.20368491267363784 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.showroom.singleapp.cases.CaseTableTest.CaseTableTestData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>632</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f7 mainOut f8 tail #connect
Cs0 f8 head f3 mainIn #connect
Cs0 f0 mainOut f9 tail #connect
Cs0 f9 head f1 mainIn #connect
Cs0 f5 mainOut f4 tail #connect
Cs0 f4 head f1 mainIn #connect
