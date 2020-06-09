package ch.ivy.addon.portalkit.bo;

import java.util.HashMap;
import java.util.Map;

public class GuidePool {

  private static GuidePool instance;
  
  private Map<String, Guide> guideByUser;

  private GuidePool() {
    guideByUser = new HashMap<>();
  }

  public static GuidePool instance() {
    if (instance == null) {
      instance = new GuidePool();
    }
    return instance;
  }
  
  public Guide guide(String username) {
    if (!guideByUser.containsKey(username)) {
      guideByUser.put(username, new Guide());
    }
    return guideByUser.get(username);
  }
}
