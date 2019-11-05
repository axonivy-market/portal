[Ivy]
15F9451DFD921A3D 7.5.0 #module
>Proto >Proto Collection #zClass
As0 AdvancedFilterProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f0 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @UdMethod f3 '' #zField
As0 @GridStep f4 '' #zField
As0 @UdProcessEnd f5 '' #zField
As0 @PushWFArc f6 '' #zField
As0 @PushWFArc f7 '' #zField
>Proto As0 As0 AdvancedFilterProcess #zField
As0 f0 guid 15F9451E014B2097 #txt
As0 f0 method start() #txt
As0 f0 inParameterDecl '<> param;' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f3 guid 15F946685C4600B5 #txt
As0 f3 method updateCasesByFilter(String) #txt
As0 f3 inParameterDecl '<String caseFilterContainerId> param;' #txt
As0 f3 inParameterMapAction 'out.caseContainerId=param.caseFilterContainerId;
' #txt
As0 f3 outParameterDecl '<> result;' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateCasesByFilter(String)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f3 83 147 26 26 -77 15 #rect
As0 f3 @|UdMethodIcon #fIcon
As0 f4 actionTable 'out=in;
' #txt
As0 f4 actionCode 'import org.primefaces.PrimeFaces;
import javax.faces.context.FacesContext;

if (!FacesContext.getCurrentInstance().isValidationFailed()) {
	PrimeFaces.current().ajax().update(in.caseContainerId);
}' #txt
As0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update case list
if pass validation</name>
        <nameStyle>17,7
18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f4 200 138 128 44 -43 -16 #rect
As0 f4 @|StepIcon #fIcon
As0 f5 403 147 26 26 0 12 #rect
As0 f5 @|UdProcessEndIcon #fIcon
As0 f6 expr out #txt
As0 f6 109 160 200 160 #arcP
As0 f7 expr out #txt
As0 f7 328 160 403 160 #arcP
>Proto As0 .type ch.ivy.addon.portalkit.component.cases.filter.AdvancedFilter.AdvancedFilterData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f6 tail #connect
As0 f6 head f4 mainIn #connect
As0 f4 mainOut f7 tail #connect
As0 f7 head f5 mainIn #connect
