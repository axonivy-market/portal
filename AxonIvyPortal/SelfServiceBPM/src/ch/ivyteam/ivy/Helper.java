package ch.ivyteam.ivy;

import java.util.Collections;
import java.util.Comparator;

import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.security.IUser;

public class Helper {

  public static List<IUser> sortUsers(List<IUser> userList) {
    Collections.sort(userList, new Comparator<IUser>() {
      public int compare(IUser u1, IUser u2) {
        try {
          return (u1.getFullName().compareTo(u2.getFullName()));
        } catch (Exception ex) {
          return 0;
        }
      }
    });
    return userList;
  }

}
