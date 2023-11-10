package portalTest.utils;

import ch.ivyteam.ivy.environment.Ivy;

public class CaseTestUtils {
  public static String findUUID(String caseId) {
    return Ivy.wf().findCase(Long.valueOf(caseId)).uuid();
  }
}
