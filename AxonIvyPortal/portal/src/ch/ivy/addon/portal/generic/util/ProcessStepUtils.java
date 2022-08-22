package ch.ivy.addon.portal.generic.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ProcessStep;
import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.security.ISecurityContext;

public class ProcessStepUtils {

  private static final String PROCESSES_CMS_URI = "Processes";
  private static final String PROCESS_STEP = "processStep";
  private static final String SLASH = "/";
  private static final String HASHTAG = "#";

  /**
   * Find process steps of an user process
   * 
   * @param process
   * @return process steps saved in CMS of given user process
   */
  public static List<ProcessStep> findProcessStepsOfProcess(UserProcess process) {
    if (process == null || StringUtils.isBlank(process.getLink())) {
      return null;
    }

    String[] processParts = process.getLink().split(SLASH);
    String processName = processParts[processParts.length - 1].replace(".ivp", "");
    String processModelName = processParts[processParts.length - 3];

    return IvyExecutor.executeAsSystem(() -> {
      IProcessModelVersion releasedProcessModelVersion = null;
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      for (IApplication app : applicationsInSecurityContext) {
        IProcessModel findProcessModel = app.findProcessModel(processModelName);
        if (findProcessModel != null) {
          releasedProcessModelVersion = findProcessModel.getReleasedProcessModelVersion();
        }
      }
      
      if (releasedProcessModelVersion != null) {
        ContentManagement contentManagement = ContentManagement.of(ContentManagement.cms(releasedProcessModelVersion));
        String processSteps = contentManagement.co(SLASH.concat(PROCESSES_CMS_URI).concat(SLASH).concat(processName).concat(SLASH).concat(PROCESS_STEP));
        if (StringUtils.isNotBlank(processSteps)) {
          return convertToProcessStep(processSteps);
        }
      }
      
      return null;
      });
  }

  /**
   * Convert raw string of process step read from CMS to list of process steps
   * 
   * @param rawProcessStep
   * @return List of converted process steps
   */
  private static List<ProcessStep> convertToProcessStep(String rawProcessStep) {
    List<ProcessStep> result = new ArrayList<>();
    List<String> rawStepLines = Arrays.asList(rawProcessStep.split(StringUtils.LF));

    ProcessStep processStep = new ProcessStep();
    for (String line : rawStepLines) {
      // If line start with "#", then it's the name of process step. Initialize process step.
      // else, add this line as description of process step.
      if (line.startsWith(HASHTAG)) {
        if (processStep.getName() != null) {
          result.add(processStep);
        }

        processStep = new ProcessStep();
        processStep.setName(line.substring(1));
        processStep.setDescriptions(new ArrayList<>());
      } else {
        processStep.getDescriptions().add(line);
      }

      // If final line is reached, add the building process step.
      if (rawStepLines.indexOf(line) == rawStepLines.size() - 1) {
        result.add(processStep);
      }
    }

    return result;
  }
}