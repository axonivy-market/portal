[Ivy]
16A7BB5465796F04 7.5.0 #module
>Proto >Proto Collection #zClass
Gs0 GlobalGrowlProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @UdInit f0 '' #zField
Gs0 @UdProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @UdEvent f3 '' #zField
Gs0 @UdExitEnd f4 '' #zField
Gs0 @GridStep f6 '' #zField
Gs0 @PushWFArc f7 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @GridStep f8 '' #zField
Gs0 @UdEvent f10 '' #zField
Gs0 @PushWFArc f12 '' #zField
Gs0 @GridStep f13 '' #zField
Gs0 @UdProcessEnd f14 '' #zField
Gs0 @PushWFArc f15 '' #zField
Gs0 @PushWFArc f9 '' #zField
>Proto Gs0 Gs0 GlobalGrowlProcess #zField
Gs0 f0 guid 16A7BB54782C073F #txt
Gs0 f0 method start() #txt
Gs0 f0 inParameterDecl '<> param;' #txt
Gs0 f0 outParameterDecl '<> result;' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f0 @|UdInitIcon #fIcon
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|UdProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 109 64 211 64 #arcP
Gs0 f3 guid 16A7BB547A66A223 #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -15 12 #rect
Gs0 f3 @|UdEventIcon #fIcon
Gs0 f4 403 147 26 26 0 12 #rect
Gs0 f4 @|UdExitEndIcon #fIcon
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

FacesMessage message = new FacesMessage("Task is done successfully");
FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
flash.setKeepMessages(true);' #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display custom growl message&#xD;
after finish task</name>
    </language>
</elementInfo>
' #txt
Gs0 f6 152 138 208 44 -83 -16 #rect
Gs0 f6 @|StepIcon #fIcon
Gs0 f7 expr out #txt
Gs0 f7 109 160 152 160 #arcP
Gs0 f5 expr out #txt
Gs0 f5 360 160 403 160 #arcP
Gs0 f8 actionTable 'out=in;
' #txt
Gs0 f8 actionCode 'import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

FacesMessage message = new FacesMessage("You have cancelled and left the task successfully");
FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
flash.setKeepMessages(true);' #txt
Gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display custom growl message&#13;
after cancel task</name>
    </language>
</elementInfo>
' #txt
Gs0 f8 152 250 208 44 -83 -16 #rect
Gs0 f8 @|StepIcon #fIcon
Gs0 f10 guid 16F18187F468C5F8 #txt
Gs0 f10 actionTable 'out=in;
' #txt
Gs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Gs0 f10 83 259 26 26 -15 12 #rect
Gs0 f10 @|UdEventIcon #fIcon
Gs0 f12 expr out #txt
Gs0 f12 109 272 152 272 #arcP
Gs0 f13 actionTable 'out=in;
' #txt
Gs0 f13 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();
' #txt
Gs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back to task list</name>
    </language>
</elementInfo>
' #txt
Gs0 f13 448 250 112 44 -42 -8 #rect
Gs0 f13 @|StepIcon #fIcon
Gs0 f14 643 259 26 26 0 12 #rect
Gs0 f14 @|UdProcessEndIcon #fIcon
Gs0 f15 expr out #txt
Gs0 f15 560 272 643 272 #arcP
Gs0 f9 360 272 448 272 #arcP
>Proto Gs0 .type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f7 tail #connect
Gs0 f7 head f6 mainIn #connect
Gs0 f6 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
Gs0 f10 mainOut f12 tail #connect
Gs0 f12 head f8 mainIn #connect
Gs0 f13 mainOut f15 tail #connect
Gs0 f15 head f14 mainIn #connect
Gs0 f8 mainOut f9 tail #connect
Gs0 f9 head f13 mainIn #connect
