package ch.ivy.gawfs.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.gawfs.mail.Mail.MailBuilder;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public class InformationMailSender {
  public void send(ExpressUserEmail mail) {
    MailBuilder mailBuilder = new MailBuilder();
    //send email
    mailBuilder.to(mail.getRecipients()).replyTo(mail.getResponseTo()).subject(mail.getSubject())
    .message(mail.getContent()).attachments(getAttachments(mail.getAttachments())).build().send();
  }

  private List<File> getAttachments(List<ExpressAttachment> attachments) {
    List<File> attachmentFiles = new ArrayList<>();
    for (ExpressAttachment attachment : attachments) {
      try {
        attachmentFiles.add(new ch.ivyteam.ivy.scripting.objects.File(attachment.getPath(), false));
      } catch (IOException ex) {
        Ivy.log().warn("Attachment is not existed", ex);
      }
    }
    return attachmentFiles;

  }

}
