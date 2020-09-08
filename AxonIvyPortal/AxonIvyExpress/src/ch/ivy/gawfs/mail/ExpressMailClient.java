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
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.mail.Attachment;
import ch.ivyteam.ivy.mail.MailClient;
import ch.ivyteam.ivy.mail.MailClientConfig;
import ch.ivyteam.ivy.mail.MailClientConfigProvider;
import ch.ivyteam.ivy.mail.MailMessage;
import ch.ivyteam.ivy.mail.MailMessage.Builder;
import ch.ivyteam.ivy.project.IIvyProject;
import ch.ivyteam.ivy.project.IvyProjectUtil;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

@SuppressWarnings("restriction")
public class ExpressMailClient {

  public static void send(ExpressUserEmail mail) {
    IvyExecutor.executeAsSystem(() -> {
      MailMessage message = mapMailMessage(mail);
      MailClientConfig mailSetupConfig = getMailClientConfig();

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
        var attachedFile = new File(expressAttachment.getPath(), false);
        Attachment mailAttachment = Attachment.create()
                .file(attachedFile.getJavaFile())
                .toAttachment();

        emailAttachments.add(mailAttachment);
      } catch (IOException e) {
        Ivy.log().error("Cannot read express attachment", e);
      }
    }

    return emailAttachments;
  }

  private static MailMessage mapMailMessage(ExpressUserEmail mail) {
    Builder mailMessageBuilder = MailMessage.create()
            .subject(mail.getSubject())
            .htmlContent(mail.getContent())
            .attachments(getAttachments(mail.getAttachments()))
            .to(buildToMailAddress(mail.getRecipients()));

    if (StringUtils.isNotEmpty(mail.getResponseTo())) {
      mailMessageBuilder.from(mail.getResponseTo());
    }
    return mailMessageBuilder.toMailMessage();
  }

  private static List<String> buildToMailAddress(String recipients) {
    List<String> recipientEmails = new ArrayList<>();
    if (recipients.contains(";")) {
      recipientEmails.addAll(Arrays.asList(recipients.split(";"))
              .stream()
              .map(String::trim)
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
    return WorkflowNavigationUtil.getWorkflowProcessModelVersion(Ivy.request().getProcessModelVersion()).getProject().getName();
  }

}
