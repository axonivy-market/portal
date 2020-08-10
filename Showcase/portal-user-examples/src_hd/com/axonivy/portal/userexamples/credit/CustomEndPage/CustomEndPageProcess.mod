[Ivy]
17099FEAF8325AA8 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CustomEndPageProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Bk1 BpmnUserTask Big #zClass
Bk1 B #cInfo
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 Bk1 S40 'Sub 4' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f5 '' #zField
>Proto Cs0 Cs0 CustomEndPageProcess #zField
Bk1 @TextInP .type .type #zField
Bk1 @TextInP .processKind .processKind #zField
Bk1 @TextInP .xml .xml #zField
Bk1 @TextInP .responsibility .responsibility #zField
Bk1 @GridStep f106 '' #zField
Bk1 @CallSub f104 '' #zField
Bk1 @PushWFArc f107 '' #zField
Bk1 @PushTrueWFInG-01 g0 '' #zField
Bk1 @PushWFArc f0 '' #zField
Bk1 @PushTrueWFOutG-01 g1 '' #zField
Bk1 @PushWFArc f1 '' #zField
>Proto Bk1 Bk1 BpmnUserTask #zField
Cs0 f0 guid 17099FEAF8AFF2B1 #txt
Cs0 f0 method start(Long) #txt
Cs0 f0 inParameterDecl '<Long caseId> param;' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 339 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 guid 17099FEAF9411114 #txt
Cs0 f3 actionTable 'out=in;
out.caseId=in.caseId;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 15 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f4 363 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 S40 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Case Details Page</name>
    </language>
</elementInfo>
' #txt
Cs0 S40 168 138 112 44 -49 -8 #rect
Cs0 S40 @|BpmnUserTaskIcon #fIcon
Cs0 f6 280 160 363 160 #arcP
Cs0 f2 109 64 339 64 #arcP
Cs0 f5 109 160 168 160 #arcP
>Proto Cs0 .type com.axonivy.portal.userexamples.credit.CustomEndPage.CustomEndPageData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Bk1 f106 actionTable 'out=in;
' #txt
Bk1 f106 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

out.caseSelected = ivy.wf.getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(in.caseId)) as ICase;' #txt
Bk1 f106 security system #txt
Bk1 f106 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
    </language>
</elementInfo>
' #txt
Bk1 f106 136 138 112 44 -28 -8 #rect
Bk1 f106 @|StepIcon #fIcon
Bk1 f104 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Bk1 f104 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Bk1 f104 requestMappingAction 'param.caseData=in.caseSelected;
param.isShowBackButton=true;
' #txt
Bk1 f104 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Bk1 f104 responseMappingAction 'out=in;
' #txt
Bk1 f104 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Bk1 f104 288 138 144 44 -65 -8 #rect
Bk1 f104 @|CallSubIcon #fIcon
Bk1 f107 expr out #txt
Bk1 f107 248 160 288 160 #arcP
Bk1 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Bk1 g0 51 147 26 26 0 5 #rect
Bk1 g0 @|MIGIcon #fIcon
Bk1 f0 77 160 136 160 #arcP
Bk1 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Bk1 g1 499 147 26 26 0 5 #rect
Bk1 g1 @|MOGIcon #fIcon
Bk1 f1 expr out #txt
Bk1 f1 432 160 499 160 #arcP
>Proto Bk1 0 0 32 24 18 0 #rect
>Proto Bk1 @|BIcon #fIcon
Cs0 S40 g1 f6 tail #connect
Cs0 f6 head f4 mainIn #connect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head S40 g0 #connect
Bk1 f106 mainOut f107 tail #connect
Bk1 f107 head f104 mainIn #connect
Bk1 g0 m f0 tail #connect
Bk1 f0 head f106 mainIn #connect
Bk1 f1 head g1 m #connect
Bk1 f104 mainOut f1 tail #connect
Bk1 0 0 576 320 0 #ivRect
