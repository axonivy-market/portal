package ch.ivyteam.ivy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.security.IUser;

public class Helper {

	public static List<IUser> sortUsers(List<IUser> userList) {
		userList.sort((first, second) -> first.getDisplayName().toLowerCase().compareTo(
			second.getDisplayName().toLowerCase()));
		return userList;
	}

	public static List<IUser> filterUsers(List<IUser> userList, String query) {
		if (StringUtils.isEmpty(query)) {
			return userList;
		}

		java.util.List<IUser> result = new ArrayList<>();
		for (IUser user : userList) {
			if (user.getDisplayName().toLowerCase().contains(query.toLowerCase())
				|| user.getMemberName().toLowerCase().contains(query.toLowerCase())) {
				result.add(user);
			}
		}
		return sortUsers(result);
	}
}