[Ivy]
17326ECC60698A9A 9.2.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressBusinessViewProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdMethod f10 '' #zField
Es0 @UdProcessEnd f11 '' #zField
Es0 @GridStep f13 '' #zField
Es0 @PushWFArc f12 '' #zField
Es0 @PushWFArc f3 '' #zField
Es0 @PushWFArc f2 '' #zField
>Proto Es0 Es0 ExpressBusinessViewProcess #zField
Es0 f0 guid 17326ECC68E24F98 #txt
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
Es0 f0 83 51 26 26 -33 25 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 403 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f10 guid 1732C155871EAA16 #txt
Es0 f10 method cancel() #txt
Es0 f10 inParameterDecl '<> param;' #txt
Es0 f10 outParameterDecl '<> result;' #txt
Es0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel()</name>
    </language>
</elementInfo>
' #txt
Es0 f10 83 179 26 26 -25 15 #rect
Es0 f10 @|UdMethodIcon #fIcon
Es0 f11 403 179 26 26 0 12 #rect
Es0 f11 @|UdProcessEndIcon #fIcon
Es0 f13 actionTable 'out=in;
' #txt
Es0 f13 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
PortalNavigator.navigateToPortalEndPage();' #txt
Es0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Back to Endpage</name>
    </language>
</elementInfo>
' #txt
Es0 f13 208 170 112 44 -46 -8 #rect
Es0 f13 @|StepIcon #fIcon
Es0 f12 320 192 403 192 #arcP
Es0 f3 109 64 403 64 #arcP
Es0 f2 109 192 208 192 #arcP
>Proto Es0 .type ch.ivy.addon.express.generic.ExpressBusinessView.ExpressBusinessViewData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f13 mainOut f12 tail #connect
Es0 f12 head f11 mainIn #connect
Es0 f0 mainOut f3 tail #connect
Es0 f3 head f1 mainIn #connect
Es0 f10 mainOut f2 tail #connect
Es0 f2 head f13 mainIn #connect
