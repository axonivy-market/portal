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

      givenString = StringUtils.lowerCase(givenString);

      if (givenString.contains("synch")) {
        return AwesomeIcon.FA_RETWEET;
      }

      if (givenString.contains("detail")) {
        return AwesomeIcon.FA_FOLDER_OPEN;
      }

      if (givenString.contains("run")) {
        return AwesomeIcon.FA_ROCKET;
      }

      if (givenString.contains("global")) {
        return AwesomeIcon.FA_GLOBE;
      }

      if (givenString.contains("language")) {
        return AwesomeIcon.FA_LANGUAGE;
      }

      if (givenString.contains("activate") || givenString.contains("activating")) {
        return AwesomeIcon.FA_BOLT;
      }

      if (givenString.contains("clean")) {
        return AwesomeIcon.FA_MAGIC;
      }

      if (givenString.contains("info")) {
        return AwesomeIcon.FA_INBOX;
      }

      if (givenString.contains("document")) {
        return AwesomeIcon.FA_BOOK;
      }

      if (givenString.contains("upload")) {
        return AwesomeIcon.FA_CLOUD_UPLOAD;
      }

      if (givenString.contains("absence")) {
        return AwesomeIcon.FA_HOME;
      }

      if (givenString.contains("mail")) {
        return AwesomeIcon.FA_ENVELOPE_O;
      }

      if (givenString.contains("create") || givenString.contains("creating")) {
        return AwesomeIcon.FA_PLUS_SQUARE;
      }

      if (givenString.contains("generate") || givenString.contains("generating")) {
        return AwesomeIcon.FA_CROSSHAIRS;
      }

      if (givenString.contains("approve") || givenString.contains("approving")) {
        return AwesomeIcon.FA_CHECK_SQUARE;
      }

      if (givenString.contains("human")) {
        return AwesomeIcon.FA_USER;
      }

      if (givenString.contains("money") || givenString.contains("price") || givenString.contains("cash")
          || givenString.contains("salary")) {
        return AwesomeIcon.FA_DOLLAR;
      }

      if (givenString.contains("holiday")) {
        return AwesomeIcon.FA_SUITCASE;
      }

      if (givenString.contains("schedule")) {
        return AwesomeIcon.FA_CALENDAR;
      }

      if (givenString.contains("capture") || givenString.contains("photo")) {
        return AwesomeIcon.FA_CAMERA;
      }

      if (givenString.contains("copy") || givenString.contains("clone")) {
        return AwesomeIcon.FA_COPY;
      }

      if (givenString.contains("admin")) {
        return AwesomeIcon.FA_COGS;
      }

      if (givenString.contains("setting")) {
        return AwesomeIcon.FA_COG;
      }

      int index = givenString.hashCode() % AwesomeIcon.values().length;

      return AwesomeIcon.values()[Math.abs(index)];
    }
  }
}
