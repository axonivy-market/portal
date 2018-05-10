package ch.ivy.gawfs.mail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.gawfs.mail.Mail.MailBuilder;
import ch.ivyteam.ivy.environment.Ivy;

public class InformationMailSender {
  public void send(ExpressUserEmail mail) {
    MailBuilder mailBuilder = new MailBuilder();
    //TODO z1 send email
    mailBuilder.to(mail.getRecipients()).replyTo(mail.getResponseTo()).subject(mail.getSubject())
    .message(mail.getContent()).attachments(getAttachments(mail.getAttachments())).build().send();
  }

  private Map<String, File> getAttachments(List<ExpressAttachment> attachments) {
    Map<String, File> attachmentMap = new HashMap<>();
    for (ExpressAttachment attachment : attachments) {
      try {
        attachmentMap.put(attachment.getName(),
            new ch.ivyteam.ivy.scripting.objects.File(attachment.getPath(), false).getJavaFile());
      } catch (IOException ex) {
        Ivy.log().warn("Attachment is not existed", ex);
      }
    }
    return attachmentMap;

  }

}
