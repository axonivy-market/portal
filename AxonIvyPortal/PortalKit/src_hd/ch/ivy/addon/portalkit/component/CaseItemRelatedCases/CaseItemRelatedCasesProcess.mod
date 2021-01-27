[Ivy]
1773DD760B7F094F 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedCasesProcess Big #zClass
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
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @CallSub f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f4 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedCasesProcess #zField
Cs0 f0 guid 167E9A75EF3D0909 #txt
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
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 275 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 96 275 96 #arcP
Cs0 f6 guid 167E9A777AB171EA #txt
Cs0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f6 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 179 26 26 -41 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 499 179 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f17 actionTable 'out=in;
' #txt
Cs0 f17 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import java.util.Arrays;

out.caseSearchCriteria.businessCaseId = in.iCase.getId();
out.caseSearchCriteria.setIncludedStates(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE));
out.caseSearchCriteria.technicalCase = true;
out.caseSearchCriteria.adminQuery = PermissionUtils.checkReadAllCasesPermission();' #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare case&#xD;
search criteria</name>
    </language>
</elementInfo>
' #txt
Cs0 f17 168 170 112 44 -39 -16 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f19 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f19 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f19 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=0;
param.count=21;
' #txt
Cs0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f19 responseMappingAction 'out=in;
out.relatedCases=new java.util.ArrayList(result.cases);
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f19 336 170 112 44 -35 -8 #rect
Cs0 f19 @|CallSubIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 280 192 336 192 #arcP
Cs0 f2 109 192 168 192 #arcP
Cs0 f4 448 192 499 192 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedCases.CaseItemRelatedCasesData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f17 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f17 mainIn #connect
Cs0 f19 mainOut f4 tail #connect
Cs0 f4 head f7 mainIn #connect
