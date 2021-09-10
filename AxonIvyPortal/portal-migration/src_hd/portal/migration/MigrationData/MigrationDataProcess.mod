[Ivy]
17AF165647CC8A09 9.3.0 #module
>Proto >Proto Collection #zClass
Ms0 MigrationDataProcess Big #zClass
Ms0 RD #cInfo
Ms0 #process
Ms0 @TextInP .type .type #zField
Ms0 @TextInP .processKind .processKind #zField
Ms0 @TextInP .xml .xml #zField
Ms0 @TextInP .responsibility .responsibility #zField
Ms0 @UdInit f0 '' #zField
Ms0 @UdProcessEnd f1 '' #zField
Ms0 @UdEvent f3 '' #zField
Ms0 @UdExitEnd f4 '' #zField
Ms0 @PushWFArc f5 '' #zField
Ms0 @UdMethod f15 '' #zField
Ms0 @GridStep f17 '' #zField
Ms0 @GridStep f18 '' #zField
Ms0 @UdProcessEnd f19 '' #zField
Ms0 @PushWFArc f20 '' #zField
Ms0 @PushWFArc f22 '' #zField
Ms0 @Alternative f6 '' #zField
Ms0 @PushWFArc f7 '' #zField
Ms0 @PushWFArc f8 '' #zField
Ms0 @UdProcessEnd f9 '' #zField
Ms0 @GridStep f11 '' #zField
Ms0 @PushWFArc f12 '' #zField
Ms0 @PushWFArc f10 '' #zField
Ms0 @PushWFArc f2 '' #zField
>Proto Ms0 Ms0 MigrationDataProcess #zField
Ms0 f0 guid 175F959BFAA127EB #txt
Ms0 f0 method start() #txt
Ms0 f0 inParameterDecl '<> param;' #txt
Ms0 f0 inParameterMapAction 'out.mustMigrateData=true;
out.showLegacyUI=false;
' #txt
Ms0 f0 outParameterDecl '<> result;' #txt
Ms0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ms0 f0 75 51 26 26 -16 15 #rect
Ms0 f1 211 51 26 26 0 12 #rect
Ms0 f3 guid 175F959BFB013917 #txt
Ms0 f3 actionTable 'out=in;
' #txt
Ms0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ms0 f3 83 467 26 26 -15 15 #rect
Ms0 f4 211 467 26 26 0 12 #rect
Ms0 f5 109 480 211 480 #arcP
Ms0 f15 guid 17AF169359777882 #txt
Ms0 f15 method proceed() #txt
Ms0 f15 inParameterDecl '<> param;' #txt
Ms0 f15 outParameterDecl '<> result;' #txt
Ms0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>proceed()</name>
    </language>
</elementInfo>
' #txt
Ms0 f15 75 163 26 26 -25 15 #rect
Ms0 f17 actionTable 'out=in;
' #txt
Ms0 f17 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.component.message.Message;

FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Migration finished", "Completed");
FacesContext.getCurrentInstance().addMessage(null, message);

in.mustMigrateData = false;' #txt
Ms0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update message</name>
    </language>
</elementInfo>
' #txt
Ms0 f17 416 154 112 44 -48 -8 #rect
Ms0 f18 actionTable 'out=in;
' #txt
Ms0 f18 actionCode 'import portalmigration.enums.PortalVariable;
import java.util.HashMap;
import java.util.Map;
import portalmigration.service.PortalMigrationService;

Map options = new HashMap();
options.put(PortalVariable.SHOW_LEGACY_UI.key, in.showLegacyUI);

List<String> result = PortalMigrationService.migrate(options);
in.hasError = result.isEmpty() ? false : true;
in.errors = result;' #txt
Ms0 f18 security system #txt
Ms0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Migrate data</name>
    </language>
</elementInfo>
' #txt
Ms0 f18 160 154 112 44 -33 -8 #rect
Ms0 f19 587 163 26 26 0 12 #rect
Ms0 f20 101 176 160 176 #arcP
Ms0 f22 528 176 587 176 #arcP
Ms0 f6 328 160 32 32 0 16 #rect
Ms0 f7 272 176 328 176 #arcP
Ms0 f8 expr in #txt
Ms0 f8 outCond !in.hasError #txt
Ms0 f8 360 176 416 176 #arcP
Ms0 f9 331 355 26 26 0 12 #rect
Ms0 f11 actionTable 'out=in;
' #txt
Ms0 f11 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.component.message.Message;

StringBuilder string = new StringBuilder();
string.append("/n");
for (String error : in.errors) {
	string.append(error);
	string.append("/n");
}

FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Migration failed", string.toString());
FacesContext.getCurrentInstance().addMessage(null, message);' #txt
Ms0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>print log</name>
    </language>
</elementInfo>
' #txt
Ms0 f11 288 250 112 44 -22 -8 #rect
Ms0 f12 expr in #txt
Ms0 f12 344 192 344 250 #arcP
Ms0 f10 344 294 344 355 #arcP
Ms0 f2 101 64 211 64 #arcP
>Proto Ms0 .type portal.migration.MigrationData.MigrationDataData #txt
>Proto Ms0 .processKind HTML_DIALOG #txt
>Proto Ms0 -8 -8 16 16 16 26 #rect
Ms0 f3 mainOut f5 tail #connect
Ms0 f5 head f4 mainIn #connect
Ms0 f15 mainOut f20 tail #connect
Ms0 f20 head f18 mainIn #connect
Ms0 f17 mainOut f22 tail #connect
Ms0 f22 head f19 mainIn #connect
Ms0 f18 mainOut f7 tail #connect
Ms0 f7 head f6 in #connect
Ms0 f6 out f8 tail #connect
Ms0 f8 head f17 mainIn #connect
Ms0 f6 out f12 tail #connect
Ms0 f12 head f11 mainIn #connect
Ms0 f11 mainOut f10 tail #connect
Ms0 f10 head f9 mainIn #connect
Ms0 f0 mainOut f2 tail #connect
Ms0 f2 head f1 mainIn #connect
