[Ivy]
[>Created: Wed May 20 14:57:07 ICT 2015]
14D88FAA3AB277D5 3.17 #module
>Proto >Proto Collection #zClass
Cs0 ProvidedCasesCaseTableTestProcess Big #zClass
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
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f4 '' #zField
>Proto Cs0 Cs0 ProvidedCasesCaseTableTestProcess #zField
Cs0 f1 type internalPortal.caselist.ProvidedCasesCaseTableTest.ProvidedCasesCaseTableTestData #txt
Cs0 f1 70 270 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'internalPortal.caselist.ProvidedCasesCaseTableTest.ProvidedCasesCaseTableTestData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivyteam.ivy.workflow.ICase;

import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.logicalexpression.RelationalOperator;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
IPropertyFilter<CaseProperty> filter = ivy.wf.createCasePropertyFilter(CaseProperty.STATE,
		RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
		filter = filter.or(CaseProperty.STATE,
		RelationalOperator.EQUAL, CaseState.CREATED.intValue());
		in.cases = ivy.session.findInvolvedCases(filter,null,0,-1,true).getResultList();' #txt
Cs0 f3 type internalPortal.caselist.ProvidedCasesCaseTableTest.ProvidedCasesCaseTableTestData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>query invoived cases
&amp; build tree</name>
        <nameStyle>33,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 62 164 36 24 28 -11 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 80 188 80 270 #arcP
Cs0 f0 guid 14D704FA13BBFFD6 #txt
Cs0 f0 type internalPortal.caselist.ProvidedCasesCaseTableTest.ProvidedCasesCaseTableTestData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 70 70 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 80 90 80 164 #arcP
>Proto Cs0 .type internalPortal.caselist.ProvidedCasesCaseTableTest.ProvidedCasesCaseTableTestData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>400</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
