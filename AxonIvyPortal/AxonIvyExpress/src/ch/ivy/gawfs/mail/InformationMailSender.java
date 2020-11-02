package ch.ivy.gawfs.mail;

import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivyteam.ivy.environment.Ivy;

public class InformationMailSender {

  public void send(ExpressUserEmail mail) {
    try {
      ExpressMailClient.send(mail);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

}
