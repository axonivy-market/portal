package ch.ivy.gawfs.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.email.Attachment;
import ch.ivyteam.ivy.email.MailClient;
import ch.ivyteam.ivy.email.MailClientConfig;
import ch.ivyteam.ivy.email.MailClientConfigProvider;
import ch.ivyteam.ivy.email.MailMessage;
import ch.ivyteam.ivy.email.MailMessage.Builder;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.project.IIvyProject;
import ch.ivyteam.ivy.project.IvyProjectUtil;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

@SuppressWarnings("restriction")
public class ExpressMailClient {

  public static void send(ExpressUserEmail mail) {
    IvyExecutor.executeAsSystem(() -> {
      var message = mappingMailMessage(mail);
      var mailSetupConfig = getMailClientConfig();

      // Send Email
      var mailClient = MailClient.newMailClient(mailSetupConfig);
      mailClient.send(message);
      return null;
    });
  }

  private static List<Attachment> getAttachments(List<ExpressAttachment> expressAttachments) {
    List<Attachment> emailAttachments = new ArrayList<>();
    for (ExpressAttachment expressAttachment : expressAttachments) {

      try {
        File attachedFile = new File(expressAttachment.getPath(), false);
        var mailAttachment = Attachment.create()
                      .file(attachedFile.getJavaFile())
                      .toAttachment();
        emailAttachments.add(mailAttachment);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return emailAttachments;
  }

  private static MailMessage mappingMailMessage(ExpressUserEmail mail) {
    Builder mailMessageBuilder = MailMessage.create();
    mailMessageBuilder.subject(mail.getSubject())
                      .htmlContent(mail.getContent())
                      .attachments(getAttachments(mail.getAttachments()));
    mailMessageBuilder.to(buildToMailAddress(mail.getRecipients()));

    if (StringUtils.isNotEmpty(mail.getResponseTo())) {
      // Waiting for API update, current we lost field replyTo
      mailMessageBuilder.from(mail.getResponseTo());
    }
    return mailMessageBuilder.toMailMessage();
  }

  private static List<String> buildToMailAddress(String recipients) {
    List<String> recipientEmails = new ArrayList<>();
    if (recipients.contains(";")) {
      recipientEmails.addAll(Arrays.asList(recipients.split(";"))
              .stream().map(String::trim)
              .collect(Collectors.toList()));
    } else {
      recipientEmails.add(recipients);
    }
    return recipientEmails;
  }



  private static MailClientConfig getMailClientConfig() {
    IIvyProject ivyProject = IvyProjectUtil.getIvyProjectByName(getProjectName());
    return MailClientConfigProvider.get(ivyProject);
  }

  private static String getProjectName() {
    return WorkflowNavigationUtil.getWorkflowProcessModelVersion(Ivy.request().getProcessModelVersion()).getProject()
        .getName();
  }

}
