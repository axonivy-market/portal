package ch.ivy.addon.portalkit.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.JTextField;

import org.eclipse.core.runtime.IProgressMonitor;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import ch.ivyteam.ivy.application.restricted.IGlobalVariable;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.IProcessExtensionConfigurationEditorEnvironment;
import ch.ivyteam.ivy.process.extension.impl.AbstractProcessExtensionConfigurationEditor;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.service.ServiceException;

/**
 * Cron Expsression Start Event Bean. This bean gets a cron expression via the
 * configuartion string and will schedule by using the expression
 * 
 * The Quarz framework is used as underlying scheduler framework.
 * 
 * @author mde
 * 
 */
public class CronByGlobalVariableTriggerStartEventBean extends AbstractProcessStartEventBean implements Job {
  private Scheduler sched = null;
  private JobDetail job = null;
  private CronTrigger trigger = null;
  private String triggerIdentifier;
  private static final String RUNTIME_KEY = "eventRuntime";

  /**
   * Default constructor
   */
  public CronByGlobalVariableTriggerStartEventBean() {
    super("CronTrigger", "Description of CronTrigger");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, String configuration) {
    super.initialize(eventRuntime, configuration);
    // Disable Ivy polling
    eventRuntime.setPollTimeInterval(0);

    try {
      IGlobalVariable var = eventRuntime.getProcessModelVersion().getApplication().findGlobalVariable(configuration);
      if (var != null) {
        String pattern = var.getValue();
        SchedulerFactory sf = new StdSchedulerFactory();
        if (pattern != null && pattern.length() > 0) {
          // sf.getScheduler() method has to be called inside synchronized block to
          // prevent racing condition.
          // E.g: two thread initialize Scheduler would cause 
          // SchedulerException: Scheduler with name 'DefaultQuartzScheduler' already exists.
          synchronized (RUNTIME_KEY) {
            sched = sf.getScheduler();
          }
          triggerIdentifier = String.format("CronJobIdentifier:%s", var.getName());

          job = newJob(CronByGlobalVariableTriggerStartEventBean.class).withIdentity(triggerIdentifier).build();

          // Pass runtime instance to job, that the job thread has access to it
          job.getJobDataMap().put(RUNTIME_KEY, eventRuntime);

          trigger = newTrigger().withIdentity(triggerIdentifier, "Group").withSchedule(cronSchedule(pattern)).build();

          sched.scheduleJob(job, trigger);
          getEventBeanRuntime().getRuntimeLogLogger().info(
              "Init trigger " + triggerIdentifier + " " + trigger.getCronExpression() + " First start: "
                  + trigger.getNextFireTime());
        }
      }
    } catch (SchedulerException e) {
      sched = null;
      getEventBeanRuntime().getRuntimeLogLogger().error(e);
    } catch (PersistencyException e) {
      sched = null;
      getEventBeanRuntime().getRuntimeLogLogger().error(e);
    }
  }

  @Override
  public void start(IProgressMonitor monitor) throws ServiceException {
    super.start(monitor);
    if (sched != null && trigger != null) {
      try {
        sched.start();
      } catch (SchedulerException e) {
        throw new ServiceException(e);
      }
    }
  }

  @Override
  public void stop(IProgressMonitor monitor) throws ServiceException {
    super.stop(monitor);
    if (sched != null) {
      try {
        sched.shutdown();
      } catch (SchedulerException e) {
        throw new ServiceException(e);
      }
    }
  }

  @Override
  public void execute(final JobExecutionContext context) throws JobExecutionException {

    if (context.getJobDetail().getJobDataMap().containsKey(RUNTIME_KEY)) {
      final IProcessStartEventBeanRuntime eventRuntime = (IProcessStartEventBeanRuntime) context.getJobDetail()
          .getJobDataMap().get(RUNTIME_KEY);

      if (eventRuntime != null) {
        try {
          eventRuntime.executeAsSystem(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
              String triggerIdentifier = context.getTrigger().getJobKey().getName();
              String firingReason = "Cron Trigger started " + triggerIdentifier;
              Map<String, Object> parameters = new HashMap<String, Object>();
              eventRuntime.fireProcessStartEventRequest(null, firingReason, parameters);
              return null;
            }
          });
          eventRuntime.getRuntimeLogLogger().info(
              "Next fire of " + context.getTrigger().getJobKey().getName() + ": "
                  + context.getTrigger().getNextFireTime());
        } catch (RequestException e) {
          eventRuntime.getRuntimeLogLogger().error(e);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Editor class to work with the configuration.
   *
   * @author maonguyen
   */
  public static class Editor extends AbstractProcessExtensionConfigurationEditor {

    private final JTextField globalVariable = new JTextField(60);

    @Override
    protected void createEditorPanelContent(Container editorPanel,
        IProcessExtensionConfigurationEditorEnvironment editorEnvironment) {

      editorPanel.add(globalVariable, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST,
          GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    public String getConfiguration() {
      return globalVariable.getText();
    }

    @Override
    public void setConfiguration(String configString) {
      globalVariable.setText(configString);
    }
  }
}
