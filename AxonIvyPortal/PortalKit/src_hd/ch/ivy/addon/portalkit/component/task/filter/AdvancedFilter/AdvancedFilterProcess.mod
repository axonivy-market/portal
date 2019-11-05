[Ivy]
15DC16CB8B32F2B4 7.5.0 #module
>Proto >Proto Collection #zClass
Ss0 AdvancedFilterProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @UdInit f0 '' #zField
Ss0 @UdProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @UdMethod f3 '' #zField
Ss0 @UdProcessEnd f4 '' #zField
Ss0 @GridStep f5 '' #zField
Ss0 @PushWFArc f6 '' #zField
Ss0 @PushWFArc f7 '' #zField
>Proto Ss0 Ss0 AdvancedFilterProcess #zField
Ss0 f0 guid 152EE20AC7136182 #txt
Ss0 f0 method start() #txt
Ss0 f0 inParameterDecl '<> param;' #txt
Ss0 f0 outParameterDecl '<> result;' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 53 85 22 22 14 0 #rect
Ss0 f0 @|UdInitIcon #fIcon
Ss0 f1 213 85 22 22 14 0 #rect
Ss0 f1 @|UdProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 75 96 213 96 #arcP
Ss0 f3 guid 15DA7A98A57644D7 #txt
Ss0 f3 method updateTasksByFilter(String) #txt
Ss0 f3 inParameterDecl '<String taskContainerId> param;' #txt
Ss0 f3 inParameterMapAction 'out.taskContainerId=param.taskContainerId;
' #txt
Ss0 f3 outParameterDecl '<> result;' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTasksByFilter()</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f3 51 179 26 26 -60 15 #rect
Ss0 f3 @|UdMethodIcon #fIcon
Ss0 f4 307 179 26 26 0 12 #rect
Ss0 f4 @|UdProcessEndIcon #fIcon
Ss0 f5 actionTable 'out=in;
' #txt
Ss0 f5 actionCode 'import org.primefaces.PrimeFaces;
import javax.faces.context.FacesContext;

if (!FacesContext.getCurrentInstance().isValidationFailed()) {
	PrimeFaces.current().ajax().update(in.taskContainerId);
}' #txt
Ss0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update if
validation passed</name>
        <nameStyle>27
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f5 144 170 128 44 -44 -16 #rect
Ss0 f5 @|StepIcon #fIcon
Ss0 f6 expr out #txt
Ss0 f6 77 192 144 192 #arcP
Ss0 f7 expr out #txt
Ss0 f7 272 192 307 192 #arcP
>Proto Ss0 .type ch.ivy.addon.portalkit.component.task.filter.AdvancedFilter.AdvancedFilterData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f3 mainOut f6 tail #connect
Ss0 f6 head f5 mainIn #connect
Ss0 f5 mainOut f7 tail #connect
Ss0 f7 head f4 mainIn #connect
