[Ivy]
[>Created: Wed May 10 15:28:43 ICT 2017]
15AD21F4FF17E906 3.20 #module
>Proto >Proto Collection #zClass
as0 GroupMgmtProcess Big #zClass
as0 RD #cInfo
as0 #process
as0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
as0 @TextInP .rdData2UIAction .rdData2UIAction #zField
as0 @TextInP .resExport .resExport #zField
as0 @TextInP .type .type #zField
as0 @TextInP .processKind .processKind #zField
as0 @AnnotationInP-0n ai ai #zField
as0 @MessageFlowInP-0n messageIn messageIn #zField
as0 @MessageFlowOutP-0n messageOut messageOut #zField
as0 @TextInP .xml .xml #zField
as0 @TextInP .responsibility .responsibility #zField
as0 @RichDialogProcessEnd f1 '' #zField
as0 @RichDialogProcessStart f3 '' #zField
as0 @RichDialogEnd f4 '' #zField
as0 @PushWFArc f5 '' #zField
as0 @RichDialogMethodStart f6 '' #zField
as0 @RichDialogEnd f7 '' #zField
as0 @GridStep f9 '' #zField
as0 @RichDialogMethodStart f11 '' #zField
as0 @RichDialogProcessEnd f12 '' #zField
as0 @PushWFArc f13 '' #zField
as0 @RichDialogInitStart f14 '' #zField
as0 @RichDialogMethodStart f16 '' #zField
as0 @RichDialogEnd f17 '' #zField
as0 @PushWFArc f18 '' #zField
as0 @GridStep f19 '' #zField
as0 @PushWFArc f20 '' #zField
as0 @PushWFArc f8 '' #zField
as0 @RichDialogMethodStart f21 '' #zField
as0 @RichDialogProcessEnd f22 '' #zField
as0 @GridStep f24 '' #zField
as0 @PushWFArc f25 '' #zField
as0 @PushWFArc f23 '' #zField
as0 @RichDialogMethodStart f26 '' #zField
as0 @RichDialogProcessEnd f27 '' #zField
as0 @GridStep f29 '' #zField
as0 @PushWFArc f30 '' #zField
as0 @PushWFArc f28 '' #zField
as0 @GridStep f31 '' #zField
as0 @RichDialogMethodStart f32 '' #zField
as0 @RichDialogProcessEnd f33 '' #zField
as0 @PushWFArc f34 '' #zField
as0 @PushWFArc f35 '' #zField
as0 @RichDialogMethodStart f36 '' #zField
as0 @RichDialogProcessEnd f37 '' #zField
as0 @GridStep f39 '' #zField
as0 @PushWFArc f40 '' #zField
as0 @PushWFArc f38 '' #zField
as0 @RichDialogMethodStart f41 '' #zField
as0 @RichDialogProcessEnd f42 '' #zField
as0 @GridStep f44 '' #zField
as0 @PushWFArc f45 '' #zField
as0 @PushWFArc f43 '' #zField
as0 @PushWFArc f0 '' #zField
as0 @PushWFArc f2 '' #zField
>Proto as0 as0 GroupMgmtProcess #zField
as0 f1 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f1 547 67 26 26 0 12 #rect
as0 f1 @|RichDialogProcessEndIcon #fIcon
as0 f3 guid 15114C06FFCF728E #txt
as0 f3 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f3 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f3 99 259 26 26 -15 15 #rect
as0 f3 @|RichDialogProcessStartIcon #fIcon
as0 f4 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f4 guid 15114C06FFC668B0 #txt
as0 f4 211 259 26 26 0 12 #rect
as0 f4 @|RichDialogEndIcon #fIcon
as0 f5 expr out #txt
as0 f5 125 272 211 272 #arcP
as0 f6 guid 15114C2690530BF1 #txt
as0 f6 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f6 method cancel() #txt
as0 f6 disableUIEvents false #txt
as0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f6 outParameterDecl '<> result;
' #txt
as0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel()</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f6 99 419 26 26 16 4 #rect
as0 f6 @|RichDialogMethodStartIcon #fIcon
as0 f7 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f7 guid 15114C27B74C6682 #txt
as0 f7 387 419 26 26 0 12 #rect
as0 f7 @|RichDialogEndIcon #fIcon
as0 f9 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f9 actionTable 'out=in;
' #txt
as0 f9 actionCode 'import ch.ivyteam.ivy.security.IRole;
import ch.ivy.statUtils.UserGroupService;
import java.util.Collection;
import ch.ivyteam.ivy.security.IUser;


//get all AIFA Groups for APPTIO
List userrole = UserGroupService.getBewertungsGruppenAdmin();

in.groups.clear();
in.groups.addAll(userrole);


in.link = "www.google.de";

//get available users
try {
	in.users =  UserGroupService.getUsersSorted();
}
catch (Exception e) {
	ivy.log.error("Cannot get users:"+e);
}

// set case
out.actCase = ivy.case;

List values;
out.header = ivy.cms.co("/de.eon.aifa.ui/Group/Title/header", values);

if (out.isPrecreation) {
	out.taskDescription = ivy.cms.co("/de.eon.aifa.ui/Request/taskdescription/creation");
} else {
	out.taskDescription = ivy.cms.co("/de.eon.aifa.ui/Request/taskdescription/check_request");
}

for(IRole r : in.groups) {
	ivy.log.debug("Role display name : "+ r.getName());
}' #txt
as0 f9 security system #txt
as0 f9 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f9 302 68 36 24 20 2 #rect
as0 f9 @|StepIcon #fIcon
as0 f11 guid 1512E411FE07CFB2 #txt
as0 f11 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f11 method show() #txt
as0 f11 disableUIEvents false #txt
as0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f11 outParameterDecl '<> result;
' #txt
as0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f11 99 467 26 26 18 5 #rect
as0 f11 @|RichDialogMethodStartIcon #fIcon
as0 f12 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f12 211 467 26 26 0 12 #rect
as0 f12 @|RichDialogProcessEndIcon #fIcon
as0 f13 expr out #txt
as0 f13 125 480 211 480 #arcP
as0 f14 guid 15133BC654CE15D7 #txt
as0 f14 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f14 method start() #txt
as0 f14 disableUIEvents true #txt
as0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f14 outParameterDecl '<> result;
' #txt
as0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f14 99 67 26 26 -19 15 #rect
as0 f14 @|RichDialogInitStartIcon #fIcon
as0 f16 guid 1514EEF61CC730AD #txt
as0 f16 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f16 method submit() #txt
as0 f16 disableUIEvents false #txt
as0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f16 outParameterDecl '<> result;
' #txt
as0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>submit()</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f16 102 358 20 20 13 0 #rect
as0 f16 @|RichDialogMethodStartIcon #fIcon
as0 f17 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f17 guid 1514EEF7D864C757 #txt
as0 f17 214 358 20 20 13 0 #rect
as0 f17 @|RichDialogEndIcon #fIcon
as0 f18 expr out #txt
as0 f18 122 368 214 368 #arcP
as0 f19 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f19 actionTable 'out=in;
' #txt
as0 f19 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.CaseState;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.richdialog.exec.ProcessStartConfiguration;

//ivy.task.destroy(); //delete task
if (ivy.task.getState().equals(TaskState.RESUMED)) {
	ivy.task.reset();
}
 
HttpServletRequest  req = FacesContext.getCurrentInstance().getExternalContext().getRequest() as HttpServletRequest;
 
                IProcessStart processStart;
                for (IProcessStart start : ivy.session.getStartableProcessStarts())
                {
                               ivy.log.debug("Process-start Id:"+start.getName()+"/"+start.getProcessElementId());
                               if (start.getProcessElementId().equals("1576E76B009E23DD-f0")) { //Portal
                                               processStart = start;
                                               break;
                               }
                }

String context = ivy.html.applicationHomeRef().substring(0,ivy.html.applicationHomeRef().indexOf("/",1));
//ivy.log.info("HomeRef:"+context);
                
String link = "http://"+req.getServerName() + ":"+ req.getServerPort() + context + "/pro/";
                if(processStart != null) {
                               if (!ivy.case.getState().equals(CaseState.ZOMBIE) && !ivy.case.getState().equals(CaseState.CREATED)) {
                                               link += processStart.getFullRequestPath()+"?taskIdentifier="+ivy.task.getId();
                               }
                               else {
                                               link += processStart.getFullRequestPath();
                               }
                }

if (ivy.case.getState().equals(CaseState.ZOMBIE)) {
                ivy.wf.deleteCompletedCase(ivy.case);
}

//redirect to portal
//ivy.log.debug("Link to Portal found:"+link);
FacesContext.getCurrentInstance().getExternalContext().redirect(link);
' #txt
as0 f19 security system #txt
as0 f19 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f19 222 420 36 24 20 -2 #rect
as0 f19 @|StepIcon #fIcon
as0 f20 expr out #txt
as0 f20 125 432 222 432 #arcP
as0 f8 expr out #txt
as0 f8 258 432 387 432 #arcP
as0 f21 guid 1518E5D39FD8858E #txt
as0 f21 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f21 method setUsersForRole(List<ch.ivyteam.ivy.security.IUser>,String) #txt
as0 f21 disableUIEvents false #txt
as0 f21 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<List<ch.ivyteam.ivy.security.IUser> users,java.lang.String rolename> param = methodEvent.getInputArguments();
' #txt
as0 f21 inParameterMapAction 'out.actualGroup=param.rolename;
out.actualUsers=param.users;
' #txt
as0 f21 outParameterDecl '<> result;
' #txt
as0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setUsersForRole(List&lt;IUser&gt;)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f21 102 518 20 20 13 0 #rect
as0 f21 @|RichDialogMethodStartIcon #fIcon
as0 f22 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f22 390 518 20 20 13 0 #rect
as0 f22 @|RichDialogProcessEndIcon #fIcon
as0 f24 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f24 actionTable 'out=in;
' #txt
as0 f24 actionCode 'import ch.ivy.statUtils.UserGroupService;
import ch.ivyteam.ivy.security.IRole;


if (in.actualGroup.length()>0 && !in.actualGroup.equals(in.actualGroupOriginal)) {
	in.changed = true;
}
if ((in.isNew || in.changed) && in.actualGroup.length()>0) {
	if (in.isNew) {
		in.actualGroupOriginal = in.actualGroup;
		try{
			//IRole rol = ivy.wf.getSecurityContext().findRole(in.actualGroup);
			List<IRole> rols = UserGroupService.findGruppeFilter(in.actualGroupOriginal,true);
			if (rols.size()==0) {
				IRole rol = UserGroupService.createBewertungsGruppe(in.actualGroup);
				if (rol != null) {
					ivy.log.debug("NOT SHURE WHAT SHOULD HAPPEN HERE!");
					in.groups.add(rol);
				}
			}
		}
		catch (Exception e) {
			ivy.log.error("Cannot create group(role) ''"+in.actualGroup+"''. Error:"+e);
		}
	}
	
	try{
		//IRole rol = ivy.wf.getSecurityContext().findRole(in.actualGroup);
		List<IRole> rols = UserGroupService.findGruppeFilter(in.actualGroupOriginal,true);
		//ivy.log.info("ROLES found:"+rols.size());
		if (rols.size()>0) {
			IRole rol = rols.get(0);
			//ivy.log.info("ROLE found:"+rol.getName());
			if (rol != null) { //exists role?
				if (!(in.isNew && rol.getUsers().size()>0)) { //if new there must be no users yet!
					UserGroupService.setUsersForRole(in.actualUsers, rol.getName());
					if (!in.actualGroup.equals(in.actualGroupOriginal)) {
						List<IRole> rolschk = UserGroupService.findGruppeFilter(in.actualGroup,true); //check group with new name exists?
						if (rolschk.size()==0) {
							UserGroupService.updateBewertungsGruppe(rol,in.actualGroup);
						}
					}
				}
			}
			else {
				ivy.log.info("Cannot save users because Role does not exists:"+in.actualGroup);
			}
		}
		else {
			ivy.log.info("Cannot save users because Role does not exists:"+in.actualGroup);
		}
	}
	catch (Exception e) {
		ivy.log.error("Cannot write users to group(role) ''"+in.actualGroup+"''. Error:"+e);
	}
}

in.changed = false;
in.isNew = false;
in.actualGroupOriginal = "";
' #txt
as0 f24 security system #txt
as0 f24 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f24 302 516 36 24 20 -2 #rect
as0 f24 @|StepIcon #fIcon
as0 f25 expr out #txt
as0 f25 122 528 302 528 #arcP
as0 f23 expr out #txt
as0 f23 338 528 390 528 #arcP
as0 f26 guid 1519035442BF35CB #txt
as0 f26 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f26 method addToList() #txt
as0 f26 disableUIEvents false #txt
as0 f26 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f26 outParameterDecl '<> result;
' #txt
as0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addToList()</name>
    </language>
</elementInfo>
' #txt
as0 f26 102 566 20 20 13 0 #rect
as0 f26 @|RichDialogMethodStartIcon #fIcon
as0 f27 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f27 390 566 20 20 13 0 #rect
as0 f27 @|RichDialogProcessEndIcon #fIcon
as0 f29 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f29 actionTable 'out=in;
' #txt
as0 f29 actionCode 'in.actualUser = ivy.wf.getSecurityContext().findUser(in.actualUserId);
if (in.#actualUser is initialized && in.actualUser.getName().length()>0) {
	if (!in.actualUsers.contains(in.actualUser)) {
		in.actualUsers.add(in.actualUser);
		in.removeUserId = in.actualUserId;
		in.changed = true;
	}
}
' #txt
as0 f29 security system #txt
as0 f29 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add User
to List</name>
        <nameStyle>9,7
7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f29 254 564 36 24 20 -2 #rect
as0 f29 @|StepIcon #fIcon
as0 f30 expr out #txt
as0 f30 122 576 254 576 #arcP
as0 f28 expr out #txt
as0 f28 290 576 390 576 #arcP
as0 f31 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f31 actionTable 'out=in;
' #txt
as0 f31 actionCode 'in.actualUser = ivy.wf.getSecurityContext().findUser(in.removeUserId);
if (in.#actualUser is initialized && in.actualUser.getName().length()>0) {
	if (in.actualUsers.contains(in.actualUser)) {
		in.actualUsers.remove(in.actualUser);
		in.changed = true;
	}
}
' #txt
as0 f31 security system #txt
as0 f31 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove User
from List</name>
        <nameStyle>12,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f31 638 564 36 24 20 -2 #rect
as0 f31 @|StepIcon #fIcon
as0 f32 guid 15191D724806DBB6 #txt
as0 f32 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f32 method removeFromList() #txt
as0 f32 disableUIEvents false #txt
as0 f32 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
as0 f32 outParameterDecl '<> result;
' #txt
as0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>removeFromList()</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f32 486 566 20 20 13 0 #rect
as0 f32 @|RichDialogMethodStartIcon #fIcon
as0 f33 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f33 774 566 20 20 13 0 #rect
as0 f33 @|RichDialogProcessEndIcon #fIcon
as0 f34 expr out #txt
as0 f34 506 576 638 576 #arcP
as0 f35 expr out #txt
as0 f35 674 576 774 576 #arcP
as0 f36 guid 15192F6A21819F2F #txt
as0 f36 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f36 method getUsersFromRole(String) #txt
as0 f36 disableUIEvents false #txt
as0 f36 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String rolename> param = methodEvent.getInputArguments();
' #txt
as0 f36 inParameterMapAction 'out.actualGroup=param.rolename;
' #txt
as0 f36 outParameterDecl '<List<ch.ivyteam.ivy.security.IUser> users> result;
' #txt
as0 f36 outParameterMapAction 'result.users=in.actualUsers;
' #txt
as0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getUsersFromRole(String)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f36 486 518 20 20 13 0 #rect
as0 f36 @|RichDialogMethodStartIcon #fIcon
as0 f37 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f37 774 518 20 20 13 0 #rect
as0 f37 @|RichDialogProcessEndIcon #fIcon
as0 f39 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f39 actionTable 'out=in;
' #txt
as0 f39 actionCode '
out.actualUsers = ch.ivy.statUtils.UserGroupService.getUsersFromGroupSorted(in.actualGroup);
out.actualGroupOriginal = out.actualGroup; //remember name of group (if it will be changed)
' #txt
as0 f39 security system #txt
as0 f39 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Users
from Role</name>
        <nameStyle>10,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f39 670 516 36 24 20 -2 #rect
as0 f39 @|StepIcon #fIcon
as0 f40 expr out #txt
as0 f40 506 528 670 528 #arcP
as0 f38 expr out #txt
as0 f38 706 528 774 528 #arcP
as0 f41 guid 1519C8CFCDE749CB #txt
as0 f41 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f41 method deleteRole(String) #txt
as0 f41 disableUIEvents false #txt
as0 f41 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String rolename> param = methodEvent.getInputArguments();
' #txt
as0 f41 inParameterMapAction 'out.actualGroup=param.rolename;
' #txt
as0 f41 outParameterDecl '<> result;
' #txt
as0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteRole(String)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f41 486 470 20 20 13 0 #rect
as0 f41 @|RichDialogMethodStartIcon #fIcon
as0 f42 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f42 774 470 20 20 13 0 #rect
as0 f42 @|RichDialogProcessEndIcon #fIcon
as0 f44 actionDecl 'ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData out;
' #txt
as0 f44 actionTable 'out=in;
' #txt
as0 f44 actionCode 'import ch.ivyteam.ivy.security.IRole;
import ch.ivy.statUtils.UserGroupService;

try{
	//IRole rol = ivy.wf.getSecurityContext().findRole(in.actualGroup);
		List<IRole> rols = UserGroupService.findGruppeFilter(in.actualGroup,true);
		if (rols.size()>0) {
			IRole rol = rols.get(0);
			if (rol != null) {
				if (UserGroupService.deleteBewertungsGruppe(rol.getName())) {
					in.groups.remove(rol); //remove from list if deleted ok
				}
			}
	  }
}
catch (Exception e) {
	ivy.log.error("Cannot delete Role:"+in.actualGroup);
}
' #txt
as0 f44 security system #txt
as0 f44 type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
as0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete Role</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
as0 f44 638 468 36 24 20 -2 #rect
as0 f44 @|StepIcon #fIcon
as0 f45 expr out #txt
as0 f45 506 480 638 480 #arcP
as0 f43 expr out #txt
as0 f43 674 480 774 480 #arcP
as0 f0 expr out #txt
as0 f0 125 80 302 80 #arcP
as0 f0 0 0.5812336622692803 0 0 #arcLabel
as0 f2 expr out #txt
as0 f2 338 80 547 80 #arcP
>Proto as0 .type ch.ivy.gawfs.usergroupMgmt.GroupMgmt.GroupMgmtData #txt
>Proto as0 .processKind HTML_DIALOG #txt
>Proto as0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start events</swimlaneLabel>
        <swimlaneLabel>Events/Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>224</swimlaneSize>
    <swimlaneSize>416</swimlaneSize>
    <swimlaneColor>-3355393</swimlaneColor>
    <swimlaneColor>-3342388</swimlaneColor>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto as0 -8 -8 16 16 16 26 #rect
>Proto as0 '' #fIcon
as0 f3 mainOut f5 tail #connect
as0 f5 head f4 mainIn #connect
as0 f11 mainOut f13 tail #connect
as0 f13 head f12 mainIn #connect
as0 f16 mainOut f18 tail #connect
as0 f18 head f17 mainIn #connect
as0 f6 mainOut f20 tail #connect
as0 f20 head f19 mainIn #connect
as0 f19 mainOut f8 tail #connect
as0 f8 head f7 mainIn #connect
as0 f21 mainOut f25 tail #connect
as0 f25 head f24 mainIn #connect
as0 f24 mainOut f23 tail #connect
as0 f23 head f22 mainIn #connect
as0 f26 mainOut f30 tail #connect
as0 f30 head f29 mainIn #connect
as0 f29 mainOut f28 tail #connect
as0 f28 head f27 mainIn #connect
as0 f32 mainOut f34 tail #connect
as0 f34 head f31 mainIn #connect
as0 f31 mainOut f35 tail #connect
as0 f35 head f33 mainIn #connect
as0 f36 mainOut f40 tail #connect
as0 f40 head f39 mainIn #connect
as0 f39 mainOut f38 tail #connect
as0 f38 head f37 mainIn #connect
as0 f41 mainOut f45 tail #connect
as0 f45 head f44 mainIn #connect
as0 f44 mainOut f43 tail #connect
as0 f43 head f42 mainIn #connect
as0 f14 mainOut f0 tail #connect
as0 f0 head f9 mainIn #connect
as0 f9 mainOut f2 tail #connect
as0 f2 head f1 mainIn #connect
