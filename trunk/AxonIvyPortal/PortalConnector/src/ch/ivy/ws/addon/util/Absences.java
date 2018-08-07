package ch.ivy.ws.addon.util;

import java.util.List;

import ch.ivy.ws.addon.transformer.IvyAbsenceTransformer;
import ch.ivy.ws.addon.types.Absence;
import ch.ivy.ws.addon.types.IvyAbsence;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IUser;

public class Absences {

	public Absence toAbsence(IUser user, IApplication application) {
		
		List<IvyAbsence> ivyAbsences = new IvyAbsenceTransformer().transform(user.getAbsences(), application);
		
		Absence absence = new Absence();
		absence.setUserName(user.getName());
		absence.setUserFullName(user.getFullName());
		absence.setAbsences(ivyAbsences);
		
		return absence;
	}
}
