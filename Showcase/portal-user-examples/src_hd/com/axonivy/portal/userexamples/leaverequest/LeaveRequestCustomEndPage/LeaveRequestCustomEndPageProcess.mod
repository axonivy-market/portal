[Ivy]
1709A49717C793EB 7.5.0 #module
>Proto >Proto Collection #zClass
Ls0 LeaveRequestCustomEndPageProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Bk0 BpmnUserTask Big #zClass
Bk0 B #cInfo
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 Bk0 S40 'Sub 4' #zField
Ls0 @PushWFArc f6 '' #zField
Ls0 @PushWFArc f5 '' #zField
>Proto Ls0 Ls0 LeaveRequestCustomEndPageProcess #zField
Bk0 @TextInP .type .type #zField
Bk0 @TextInP .processKind .processKind #zField
Bk0 @TextInP .xml .xml #zField
Bk0 @TextInP .responsibility .responsibility #zField
Bk0 @GridStep f106 '' #zField
Bk0 @CallSub f104 '' #zField
Bk0 @PushWFArc f107 '' #zField
Bk0 @PushTrueWFInG-01 g0 '' #zField
Bk0 @PushWFArc f0 '' #zField
Bk0 @PushTrueWFOutG-01 g1 '' #zField
Bk0 @PushWFArc f1 '' #zField
>Proto Bk0 Bk0 BpmnUserTask #zField
Ls0 f0 guid 1709A497185D32FE #txt
Ls0 f0 method start(Long) #txt
Ls0 f0 inParameterDecl '<Long caseId> param;' #txt
Ls0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 211 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f2 109 64 211 64 #arcP
Ls0 f3 guid 1709A4971932B975 #txt
Ls0 f3 actionTable 'out=in;
out.caseId=in.caseId;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 83 147 26 26 -15 15 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 403 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 S40 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Case Details Page</name>
    </language>
</elementInfo>
' #txt
Ls0 S40 200 138 112 44 -49 -8 #rect
Ls0 S40 @|BpmnUserTaskIcon #fIcon
Ls0 f6 312 160 403 160 #arcP
Ls0 f5 109 160 200 160 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.leaverequest.LeaveRequestCustomEndPage.LeaveRequestCustomEndPageData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Bk0 f106 actionTable 'out=in;
' #txt
Bk0 f106 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

out.caseSelected = ivy.wf.getGlobalContext().getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(in.caseId)) as ICase;' #txt
Bk0 f106 security system #txt
Bk0 f106 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
    </language>
</elementInfo>
' #txt
Bk0 f106 136 138 112 44 -28 -8 #rect
Bk0 f106 @|StepIcon #fIcon
Bk0 f104 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Bk0 f104 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Bk0 f104 requestMappingAction 'param.caseData=in.caseSelected;
param.isShowBackButton=true;
' #txt
Bk0 f104 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Bk0 f104 responseMappingAction 'out=in;
' #txt
Bk0 f104 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Bk0 f104 288 138 144 44 -65 -8 #rect
Bk0 f104 @|CallSubIcon #fIcon
Bk0 f107 expr out #txt
Bk0 f107 248 160 288 160 #arcP
Bk0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g0 51 147 26 26 0 5 #rect
Bk0 g0 @|MIGIcon #fIcon
Bk0 f0 77 160 136 160 #arcP
Bk0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g1 499 147 26 26 0 5 #rect
Bk0 g1 @|MOGIcon #fIcon
Bk0 f1 expr out #txt
Bk0 f1 432 160 499 160 #arcP
>Proto Bk0 0 0 32 24 18 0 #rect
>Proto Bk0 @|BIcon #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 S40 g1 f6 tail #connect
Ls0 f6 head f4 mainIn #connect
Ls0 f3 mainOut f5 tail #connect
Ls0 f5 head S40 g0 #connect
Bk0 f106 mainOut f107 tail #connect
Bk0 f107 head f104 mainIn #connect
Bk0 g0 m f0 tail #connect
Bk0 f0 head f106 mainIn #connect
Bk0 f1 head g1 m #connect
Bk0 f104 mainOut f1 tail #connect
Bk0 0 0 576 320 0 #ivRect
