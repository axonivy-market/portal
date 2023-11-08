package ch.ivy.addon.portalkit.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.process.extension.ui.ExtensionUiBuilder;
import ch.ivyteam.ivy.process.extension.ui.UiEditorExtension;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.vars.Variable;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.util.IvyRuntimeException;

/**
 * A bean that starts processes periodically with a defined delay or at certain times defined by a daily time or CRON
 * expression. The configuration can either be configured directly or indirectly via a variable. If the variable changes
 * the bean is reconfigured automatically.
 **/

public class CronByGlobalVariableTriggerStartEventBean extends AbstractProcessStartEventBean {
  private static final String VARIABLE = "variable";
  private static String name;
  private static String description;

  public CronByGlobalVariableTriggerStartEventBean() {
    super(name, description);
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    name = eventRuntime.getProcessStart().getName();
    description = eventRuntime.getProcessStart().getDescription();
    try {
      Variable var =
          Variables.of(eventRuntime.getProcessModelVersion().getApplication()).variable(configuration.get(VARIABLE));
      if (var != null) {
        String pattern = var.value();
        CronExpression cronExpression = new CronExpression(pattern);
        String unixPattern = cronExpression.convertQuartzCronToUnixPattern();
        Ivy.log().error(unixPattern);
        if (unixPattern != null && unixPattern.length() > 0) {
          eventRuntime.poll().asDefinedByExpression(unixPattern);
        }
      }
    } catch (Exception ex) {
      throw new IvyRuntimeException("Cannot evaluate the ivyScript configuration ", ex);
    }
  }


  @Override
  public void poll() {
    try {
      getEventBeanRuntime().processStarter()
          .withReason("Time elapsed or reached cron pattern " + getConfig().get(VARIABLE)).start();
    } catch (RequestException ex) {
      throw new IvyRuntimeException("Cannot start process", ex);
    }
  }

  // Class CronExpression to parse Quartz CRON pattern to Unix
  public class CronExpression {

    private final List<String> cronFields;

    private static final String QUARTZ_CRON_FORMAT =
        "^(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)(?:\\s+(\\S+))?$";
    private static final Pattern QUARTZ_CRON_PATTERN_REGEX = Pattern.compile(QUARTZ_CRON_FORMAT);

    private static final int EXPECTED_QUARTZ_FIELDS = 6;

    public CronExpression(String cronPattern) throws ParseException {
      validateCronPattern(cronPattern);
      this.cronFields = parseCronPattern(cronPattern);
    }

    // Remove the second value from Quartz CRON
    public String convertQuartzCronToUnixPattern() {
      int minute = parseField(cronFields.get(1), 0, 59);
      int hour = parseField(cronFields.get(2), 0, 23);
      int dayOfMonth = parseField(cronFields.get(3), 1, 31);
      int month = parseField(cronFields.get(4), 1, 12);
      int dayOfWeek = parseField(cronFields.get(5), 0, 7);

      return String.format("%s %s %s %s %s", convertToUnixPattern(minute), convertToUnixPattern(hour),
          convertToUnixPattern(dayOfMonth), convertToUnixPattern(month), convertToUnixPattern(dayOfWeek));
    }

    private void validateCronPattern(String cronPattern) throws ParseException {
      Matcher matcher = QUARTZ_CRON_PATTERN_REGEX.matcher(cronPattern.trim());
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Invalid Quartz cron pattern: " + cronPattern);
      }

      String[] fields = cronPattern.trim().split("\\s+");
      if (fields.length != EXPECTED_QUARTZ_FIELDS && fields.length != EXPECTED_QUARTZ_FIELDS + 1) {
        throw new IllegalArgumentException("Invalid number of fields in Quartz cron pattern: " + cronPattern);
      }

      for (String field : fields) {
        if (!"*".equals(field) && !"?".equals(field) && !field.matches("\\d+")) {
          throw new IllegalArgumentException("Invalid value in Quartz cron pattern: " + field);
        }
      }
    }

    private List<String> parseCronPattern(String cronPattern) throws ParseException {
      Matcher matcher = QUARTZ_CRON_PATTERN_REGEX.matcher(cronPattern.trim());
      if (matcher.matches()) {
        return Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5),
            matcher.group(6));
      } else {
        throw new ParseException("Invalid cron pattern: " + cronPattern, 0);
      }
    }

    private int parseField(String field, int min, int max) {
      return "*".equals(field) || "?".equals(field) ? -1 : validateAndParse(field, min, max);
    }

    private int validateAndParse(String field, int min, int max) {
      int value = Integer.parseInt(field);
      if (value < min || value > max) {
        throw new IllegalArgumentException("Invalid value in cron pattern: " + field);
      }
      return value;
    }

    private String convertToUnixPattern(int value) {
      return (value == -1) ? "*" : String.valueOf(value);
    }

  }

  /**
   * Editor class to work with the configuration.
   *
   * @author maonguyen
   */
  public static class Editor extends UiEditorExtension {

    @Override
    public void initUiFields(ExtensionUiBuilder ui) {
      ui.textField(VARIABLE).create();
    }
  }
}
