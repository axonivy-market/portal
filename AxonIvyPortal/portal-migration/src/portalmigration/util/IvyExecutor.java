package portalmigration.util;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;

public class IvyExecutor {

  private IvyExecutor() {}

  public static <T> T executeAsSystem(Callable<T> callable){
    try {
      return Sudo.call(callable);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      Ivy.log().error(e);
      return null;
    }
  }

  public static void executeOnceInAllProcessModelVersion(IProcessModelVersion pmv, Runnable runnable) {
    List<IProcessModelVersion> releasedPmvs = IApplicationConfigurationManager.instance().getProcessModelVersions().stream()
            .filter(p -> isReleasedPmvOf(p, pmv.getLibrary())).collect(toList());
    if (CollectionUtils.isNotEmpty(releasedPmvs) && releasedPmvs.get(0).getId() == pmv.getId()) {
      runnable.run();
    }
  }

  private static boolean isReleasedPmvOf(IProcessModelVersion pmv, ILibrary library) {
    return pmv.getReleaseState() == ReleaseState.RELEASED
        && pmv.getLibrary().getId().equals(library.getId())
        && (pmv.getActivityOperationState() == ActivityOperationState.ACTIVE || pmv.getActivityOperationState() == ActivityOperationState.ACTIVATING);
  }
}