[Ivy]
176CBB37D34B8292 9.4.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessHistoryComponentProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @CallSub f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 ProcessHistoryComponentProcess #zField
Ps0 f0 guid 1624C202ECA658B6 #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 115 115 26 26 -16 15 #rect
Ps0 f1 659 115 26 26 0 12 #rect
Ps0 f3 guid 1624C202EE3F8AAE #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 115 211 26 26 -15 12 #rect
Ps0 f4 243 211 26 26 0 12 #rect
Ps0 f5 expr out #txt
Ps0 f5 141 224 243 224 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;

in.criteria = new CaseSearchCriteria();
in.criteria.setBusinessCase(true);

CaseQuery caseQuery = CaseUtils.createBusinessCaseQuery();
caseQuery.where().customField().textField(AdditionalProperty.CASE_BUSINESS_ENTITY_PROPERTY.toString())
          .isEqual("alpha");
in.criteria.setCustomCaseQuery(caseQuery);
in.criteria.setFinalCaseQuery(in.criteria.createQuery());
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init criteria count</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 200 106 112 44 -44 -8 #rect
Ps0 f7 expr out #txt
Ps0 f7 141 128 200 128 #arcP
Ps0 f10 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Ps0 f10 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ps0 f10 requestMappingAction 'param.caseSearchCriteria=in.criteria;
' #txt
Ps0 f10 responseMappingAction 'out=in;
out.emptyData=result.totalCases == 0;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count Alpha cases</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 400 106 112 44 -52 -8 #rect
Ps0 f11 312 128 400 128 #arcP
Ps0 f2 512 128 659 128 #arcP
>Proto Ps0 .type com.axonivy.portal.developerexamples.customization.ProcessHistoryComponent.ProcessHistoryComponentData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f10 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
