[Ivy]
17F49E555B7A8306 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressBusinessViewProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @UdMethod f3 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @UdProcessEnd f4 '' #zField
Es0 @PushWFArc f5 '' #zField
>Proto Es0 Es0 ExpressBusinessViewProcess #zField
Es0 f0 guid 17F49E555E61E3F8 #txt
Es0 f0 method start(Long) #txt
Es0 f0 inParameterDecl '<Long caseId> param;' #txt
Es0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 339 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f2 109 64 339 64 #arcP
Es0 f3 guid 17F49E8185271F03 #txt
Es0 f3 method cancel() #txt
Es0 f3 inParameterDecl '<> param;' #txt
Es0 f3 outParameterDecl '<> result;' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel()</name>
    </language>
</elementInfo>
' #txt
Es0 f3 83 147 26 26 -25 15 #rect
Es0 f3 @|UdMethodIcon #fIcon
Es0 f6 actionTable 'out=in;
' #txt
Es0 f6 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
PortalNavigatorAPI.navigateToPortalEndPage();' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Back to Endpage</name>
    </language>
</elementInfo>
' #txt
Es0 f6 168 138 112 44 -46 -8 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f7 109 160 168 160 #arcP
Es0 f4 339 147 26 26 0 12 #rect
Es0 f4 @|UdProcessEndIcon #fIcon
Es0 f5 280 160 339 160 #arcP
>Proto Es0 .type ch.ivy.addon.express.generic.ExpressBusinessView.ExpressBusinessViewData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f3 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
