[Ivy]
16C8374336423894 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TestTaskTemplateProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdMethod f8 '' #zField
Ts0 @UdProcessEnd f9 '' #zField
Ts0 @GridStep f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @GridStep f21 '' #zField
Ts0 @UdMethod f18 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @UdProcessEnd f19 '' #zField
Ts0 @PushWFArc f20 '' #zField
>Proto Ts0 Ts0 TestTaskTemplateProcess #zField
Ts0 f0 guid 16C8374337A6693D #txt
Ts0 f0 method start(ch.ivyteam.ivy.project.portal.examples.showcase.Data) #txt
Ts0 f0 inParameterDecl '<ch.ivyteam.ivy.project.portal.examples.showcase.Data data> param;' #txt
Ts0 f0 inParameterMapAction 'out.actualStepIndex=param.data.actuaStepIndex + 1;
out.data=param.data;
out.processChainDirection=param.data.processChainDirection;
out.processChainShape=param.data.processChainShape;
' #txt
Ts0 f0 outParameterDecl '<ch.ivyteam.ivy.project.portal.examples.showcase.Data data> result;' #txt
Ts0 f0 outParameterMapAction 'result.data=in.data;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Data)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -29 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 339 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 guid 16C837433A29385A #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -15 12 #rect
Ts0 f3 @|UdEventIcon #fIcon
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|UdExitEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 160 211 160 #arcP
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData;

in.steps.add("Start");
in.steps.add("Create request process testing");
in.steps.add("Clarify process testing");
in.steps.add("Approve process testing");
in.steps.add("Manager Approve process testing");
in.steps.add("Fisnished");
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 168 42 112 44 -21 -8 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 109 64 168 64 #arcP
Ts0 f2 expr out #txt
Ts0 f2 280 64 339 64 #arcP
Ts0 f8 guid 16C9819838F1E711 #txt
Ts0 f8 method saveToSession() #txt
Ts0 f8 inParameterDecl '<> param;' #txt
Ts0 f8 outParameterDecl '<> result;' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveToSession()</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 83 259 26 26 -47 15 #rect
Ts0 f8 @|UdMethodIcon #fIcon
Ts0 f9 339 259 26 26 0 12 #rect
Ts0 f9 @|UdProcessEndIcon #fIcon
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 actionCode 'ivy.session.setAttribute("taskConfigExampleActualStepIndex", in.actualStepIndex - 1);
ivy.session.setAttribute("taskConfigExampleProcessChainDirection", in.processChainDirection);
ivy.session.setAttribute("taskConfigExampleProcessChainShape", in.processChainShape);' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save config</name>
    </language>
</elementInfo>
' #txt
Ts0 f11 168 250 112 44 -31 -8 #rect
Ts0 f11 @|StepIcon #fIcon
Ts0 f12 expr out #txt
Ts0 f12 109 272 168 272 #arcP
Ts0 f10 expr out #txt
Ts0 f10 280 272 339 272 #arcP
Ts0 f21 actionTable 'out=in;
' #txt
Ts0 f21 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import org.apache.commons.lang3.StringUtils;

String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CustomizedTaskTemplate.ivp");
if (StringUtils.isEmpty(friendlyRequestPath)) {
    friendlyRequestPath = "Start Processes/Showcases/CustomizedTaskTemplate.ivp";
}

String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
if (StringUtils.isNotEmpty(requestPath)) {
    UrlDetector urlDetector = new UrlDetector();
    String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());

    FacesContext.getCurrentInstance().getExternalContext().redirect(serverUrl + requestPath);
}
  ' #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reload process</name>
    </language>
</elementInfo>
' #txt
Ts0 f21 176 362 112 44 -41 -8 #rect
Ts0 f21 @|StepIcon #fIcon
Ts0 f18 guid 16C98491C1920D90 #txt
Ts0 f18 method reload() #txt
Ts0 f18 inParameterDecl '<> param;' #txt
Ts0 f18 outParameterDecl '<> result;' #txt
Ts0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reload()</name>
    </language>
</elementInfo>
' #txt
Ts0 f18 75 371 26 26 -21 15 #rect
Ts0 f18 @|UdMethodIcon #fIcon
Ts0 f22 expr out #txt
Ts0 f22 101 384 176 384 #arcP
Ts0 f19 379 371 26 26 0 12 #rect
Ts0 f19 @|UdProcessEndIcon #fIcon
Ts0 f20 expr out #txt
Ts0 f20 288 384 379 384 #arcP
>Proto Ts0 .type ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f0 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f8 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f11 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f18 mainOut f22 tail #connect
Ts0 f22 head f21 mainIn #connect
Ts0 f21 mainOut f20 tail #connect
Ts0 f20 head f19 mainIn #connect
