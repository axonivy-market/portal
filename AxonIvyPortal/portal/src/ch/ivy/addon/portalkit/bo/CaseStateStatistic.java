package ch.ivy.addon.portalkit.bo;

public class CaseStateStatistic {

  private long created;
  private long running;
  private long done;
  private long failed;

  public long getCreated() {
    return created;
  }

  public void setCreated(long created) {
    this.created = created;
  }

  public long getRunning() {
    return running;
  }

  public void setRunning(long running) {
    this.running = running;
  }

  public long getDone() {
    return done;
  }

  public void setDone(long done) {
    this.done = done;
  }

  public long getFailed() {
    return failed;
  }

  public void setFailed(long failed) {
    this.failed = failed;
  }


}
