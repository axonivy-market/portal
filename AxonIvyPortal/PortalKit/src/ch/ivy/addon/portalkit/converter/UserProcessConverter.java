package ch.ivy.addon.portalkit.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

/**
 * 
 * <p>
 * {@link UserProcess}'s helper class provides methods which help to convert object to
 * {@link UserProcess} object.
 * </p>
 *
 */
public class UserProcessConverter {

  /**
   * Converts list of {@link RemoteWebStartable} to list of {@link UserProcess}.
   * @param webStartables 
   * 
   * @return list of {@link UserProcess}
   */
  public List<UserProcess> convert(List<RemoteWebStartable> webStartables) {
    List<UserProcess> userProcesses = new ArrayList<>();

    for (RemoteWebStartable webStartable : webStartables) {
      userProcesses.add(convert(webStartable));
    }

    return userProcesses;
  }

  private UserProcess convert(RemoteWebStartable webStartable) {
    UserProcess p = new UserProcess();
    p.setProcessName(webStartable.getDisplayName());
    String icon = IconPseudoRandomChooser.basedOn(webStartable.getName()).getIconCode();
    p.setIcon(icon);
    p.setLink(webStartable.getStartLink());
    return p;
  }

  /**
   * This class provides methods which help to choose icon in a pseudo random style.
   */
  private static class IconPseudoRandomChooser {
    private IconPseudoRandomChooser() {}

    /**
     * <p>
     * Selects an icon from the {@link AwesomeIcon} based on the given string.
     * <p/>
     * 
     * <p>
     * If none of pre-defined categories is matched then select icon base on the given string's
     * hashcode. <br />
     * For example: Lets assume that the {@link AwesomeIcon} has 500 icons. The given string is
     * "Accept leave request". Then the selected icon should be: the 327th icon. The icon's index is
     * calculated by this: index = |820949262 % 500| <br />
     * <b>index</b>: is result as 326 <br />
     * <b>820949262</b>: is the hashcode number of "Accept leave request")
     * <p/>
     * 
     * @param givenString
     * @return an icon from {@link AwesomeIcon}
     */
    public static AwesomeIcon basedOn(String givenString) {

      String lowerCaseString = StringUtils.lowerCase(givenString);

      if (lowerCaseString.contains("synch")) {
        return AwesomeIcon.FA_RETWEET;
      }

      if (lowerCaseString.contains("detail")) {
        return AwesomeIcon.FA_FOLDER_OPEN;
      }

      if (lowerCaseString.contains("run")) {
        return AwesomeIcon.FA_ROCKET;
      }

      if (lowerCaseString.contains("global")) {
        return AwesomeIcon.FA_GLOBE;
      }

      if (lowerCaseString.contains("language")) {
        return AwesomeIcon.FA_LANGUAGE;
      }

      if (lowerCaseString.contains("activate") || lowerCaseString.contains("activating")) {
        return AwesomeIcon.FA_BOLT;
      }

      if (lowerCaseString.contains("clean")) {
        return AwesomeIcon.FA_MAGIC;
      }

      if (lowerCaseString.contains("info")) {
        return AwesomeIcon.FA_INBOX;
      }

      if (lowerCaseString.contains("document")) {
        return AwesomeIcon.FA_BOOK;
      }

      if (lowerCaseString.contains("upload")) {
        return AwesomeIcon.FA_CLOUD_UPLOAD;
      }

      if (lowerCaseString.contains("absence")) {
        return AwesomeIcon.FA_HOME;
      }

      if (lowerCaseString.contains("mail")) {
        return AwesomeIcon.FA_ENVELOPE_O;
      }

      if (lowerCaseString.contains("create") || lowerCaseString.contains("creating")) {
        return AwesomeIcon.FA_PLUS_SQUARE;
      }

      if (lowerCaseString.contains("generate") || lowerCaseString.contains("generating")) {
        return AwesomeIcon.FA_CROSSHAIRS;
      }

      if (lowerCaseString.contains("approve") || lowerCaseString.contains("approving")) {
        return AwesomeIcon.FA_CHECK_SQUARE;
      }

      if (lowerCaseString.contains("human")) {
        return AwesomeIcon.FA_USER;
      }

      if (lowerCaseString.contains("money") || lowerCaseString.contains("price") || lowerCaseString.contains("cash")
          || lowerCaseString.contains("salary")) {
        return AwesomeIcon.FA_DOLLAR;
      }

      if (lowerCaseString.contains("holiday")) {
        return AwesomeIcon.FA_SUITCASE;
      }

      if (lowerCaseString.contains("schedule")) {
        return AwesomeIcon.FA_CALENDAR;
      }

      if (lowerCaseString.contains("capture") || lowerCaseString.contains("photo")) {
        return AwesomeIcon.FA_CAMERA;
      }

      if (lowerCaseString.contains("copy") || lowerCaseString.contains("clone")) {
        return AwesomeIcon.FA_COPY;
      }

      if (lowerCaseString.contains("admin")) {
        return AwesomeIcon.FA_COGS;
      }

      if (lowerCaseString.contains("setting")) {
        return AwesomeIcon.FA_COG;
      }

      int index = lowerCaseString.hashCode() % AwesomeIcon.values().length;

      return AwesomeIcon.values()[Math.abs(index)];
    }
  }
}
