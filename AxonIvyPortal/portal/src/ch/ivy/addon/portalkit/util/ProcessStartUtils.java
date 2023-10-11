package ch.ivy.addon.portalkit.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;

public class ProcessStartUtils {

  private ProcessStartUtils() {}

  public static boolean isExpressProcessAdded(ExpressProcess expressProcess, List<UserProcess> userProcesses) {
    return userProcesses.stream()
    .filter(process -> (process.getProcessType() == ProcessType.EXPRESS_PROCESS) && StringUtils.isNoneBlank(process.getProcessId()) && process.getProcessId().equals(expressProcess.getId()))
    .findFirst()
    .isPresent();
  }

//  public static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
//    return IvyExecutor.executeAsSystem(() -> {
//      IProcessStart processStart = null;
//      processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.request().getProcessModelVersion());
//      if (processStart != null) {
//        return processStart;
//      }
//
//      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
//      
//      List<IProcessModel> processModels = applicationsInSecurityContext.stream()
//                                          .map(IApplication::getProcessModelsSortedByName)
//                                          .flatMap(List::stream)
//                                          .collect(Collectors.toList());
//
//      for (IProcessModel processModel : processModels) {
//        Optional<IProcessStart> processStartOptional =
//            Optional.of(processModel).filter(pm -> isActive(pm)).map(IProcessModel::getReleasedProcessModelVersion)
//                .filter(pmv -> isActive(pmv)).map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p))
//                .filter(Objects::nonNull);
//        if (processStartOptional.isPresent()) {
//          return processStartOptional.get();
//        }
//      }
//      return processStart;
//    });
//  }
  
//  private static IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
//      IProcessModelVersion processModelVersion) {
//    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath); 
//  }
  
//  private static boolean isActive(IProcessModelVersion processModelVersion) {
//    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
//  }

//  private static boolean isActive(IProcessModel processModel) {
//    return processModel.getActivityState() == ActivityState.ACTIVE;
//  }

//  public static boolean isExternalLink(ProcessType processType) {
//    return ProcessType.EXTERNAL_LINK == processType;
//  }
//
//  public static boolean isIvyProcess(ProcessType processType) {
//    return ProcessType.IVY_PROCESS == processType;
//  }
//
//  public static boolean isExpressProcess(ProcessType processType) {
//    return ProcessType.EXPRESS_PROCESS == processType;
//  }

  public static String getDefaultProcessImageSetting() {
    return GlobalSettingService.getInstance()
        .findGlobalSettingByGlobalVariable(GlobalVariable.DEFAULT_PROCESS_IMAGE)
        .getDisplayValue().toUpperCase();
  }

  public static String findFriendlyRequestPathContainsKeyword(String keyword){
    return IvyExecutor.executeAsSystem(() -> {
      Object portalStartPmvId = Ivy.session().getAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return ProcessStartCollector.getInstance().findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }

//  public static String findFriendlyRequestPathContainsKeywordInPMV(String keyword, IProcessModelVersion processModelVersion){
//    return IvyExecutor.executeAsSystem(() -> {
//      return ProcessStartCollector.getInstance().findFriendlyRequestPathContainsKeyword(keyword, processModelVersion);
//    });
//  }
}
