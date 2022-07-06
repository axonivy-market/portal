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
import java.util.Collections;

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

import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.IProcessExtensionConfigurationEditorEnvironment;
import ch.ivyteam.ivy.process.extension.impl.AbstractProcessExtensionConfigurationEditor;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.service.ServiceException;
import ch.ivyteam.ivy.vars.Variable;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.log.Logger;

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
  private static final Object SYN_OBJECT = new Object();
  private static Map<String, Long> startedJobs = Collections.synchronizedMap(new HashMap<String, Long>());


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
      Variable var = Variables.of(eventRuntime.getProcessModelVersion().getApplication()).variable(configuration);
      if (var != null) {
        String pattern = var.value();
        SchedulerFactory sf = new StdSchedulerFactory();
        if (pattern != null && pattern.length() > 0) {
          // sf.getScheduler() method has to be called inside synchronized block to
          // prevent racing condition.
          // E.g: two thread initialize Scheduler would cause 
          // SchedulerException: Scheduler with name 'DefaultQuartzScheduler' already exists.
          synchronized (SYN_OBJECT) {
            sched = sf.getScheduler();
          }
          triggerIdentifier = String.format("CronJobIdentifier:%s", var.name());

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
    } catch (SchedulerException | PersistencyException e) {
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
        final Logger log = eventRuntime.getRuntimeLogLogger();
        final String triggerIdentifier = context.getTrigger().getJobKey().getName();
        Throwable throwable = null;

        Long jobStartTs = startedJobs.get(triggerIdentifier);
        String nextRun = String.format("Next fire at %1$tF %1$tT.", context.getTrigger().getNextFireTime());
        if (jobStartTs != null) {
          log.warn ("Not starting job {0}, since an instance is currently running (the instance is running since {1} ms). {2}",
              triggerIdentifier, System.currentTimeMillis() - jobStartTs, nextRun);
        } else {
          log.info ("Starting job {0}", triggerIdentifier);
          long startTs = System.currentTimeMillis();
          startedJobs.put(triggerIdentifier, startTs);
          try {
            eventRuntime.executeAsSystem(new Callable<Void>() {
              @Override
              public Void call() throws Exception {
                String firingReason = "Cron Trigger started " + triggerIdentifier;
                Map<String, Object> parameters = new HashMap<>();
                eventRuntime.fireProcessStartEventRequest(null, firingReason, parameters);
                return null;
              }
            });
          } catch (Throwable t) {
              log.error("Exception while trying to execute cron job {0}. Note, the exception might have been shown already.", t, triggerIdentifier);
              throwable = t;
          } finally {
              startedJobs.remove(triggerIdentifier);
          }
          
          long endTs = System.currentTimeMillis();
          String stats = String.format("execution time %.3f", (endTs - startTs) / 1000.0);
          if (throwable != null) {
            log.error ("Job {0} ended with error {1} ({2})", triggerIdentifier, throwable, stats);
          }
          else {
            log.info ("Job {0} ended normally ({1})", triggerIdentifier, stats);
          }
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
