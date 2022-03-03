[Ivy]
176CBB3B60DD7FF2 9.4.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessHistoryDialogComponentProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @CallSub f10 '' #zField
Ps0 @UdInit f8 '' #zField
Ps0 @UdExitEnd f9 '' #zField
Ps0 @UdEvent f11 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
>Proto Ps0 Ps0 ProcessHistoryDialogComponentProcess #zField
Ps0 f6 659 91 26 26 0 12 #rect
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 actionCode 'import ch.ivy.addon.portalkit.enums.AdditionalProperty;
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
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init criteria count</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 200 82 112 44 -44 -8 #rect
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
Ps0 f10 400 82 112 44 -52 -8 #rect
Ps0 f8 guid 176F4A23AB3CA924 #txt
Ps0 f8 method start() #txt
Ps0 f8 inParameterDecl '<> param;' #txt
Ps0 f8 outParameterDecl '<> result;' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 115 91 26 26 -16 15 #rect
Ps0 f9 243 187 26 26 0 12 #rect
Ps0 f11 guid 176F4A23AB335D4F #txt
Ps0 f11 actionTable 'out=in;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f11 115 187 26 26 -15 12 #rect
Ps0 f12 expr out #txt
Ps0 f12 141 104 200 104 #arcP
Ps0 f13 expr out #txt
Ps0 f13 141 200 243 200 #arcP
Ps0 f14 512 104 659 104 #arcP
Ps0 f15 312 104 400 104 #arcP
>Proto Ps0 .type com.axonivy.portal.developerexamples.customization.ProcessHistoryDialogComponent.ProcessHistoryDialogComponentData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f11 mainOut f13 tail #connect
Ps0 f13 head f9 mainIn #connect
Ps0 f8 mainOut f12 tail #connect
Ps0 f12 head f7 mainIn #connect
Ps0 f7 mainOut f15 tail #connect
Ps0 f15 head f10 mainIn #connect
Ps0 f10 mainOut f14 tail #connect
Ps0 f14 head f6 mainIn #connect
