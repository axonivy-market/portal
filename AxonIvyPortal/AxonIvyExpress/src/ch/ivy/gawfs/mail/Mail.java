package ch.ivy.gawfs.mail;

import java.util.List;

import ch.ivyteam.ivy.components.config.EmailConfiguration;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.scripting.objects.File;

/**
 * Class to send emails from java code.
 */
@SuppressWarnings("restriction")
public class Mail {

  private EmailConfiguration emailConfig;
  private List<File> attachmentFiles;
  private static final String SEND_EMAIL_CALLABLE = "Functional Processes/sendEmail";

  private Mail(MailBuilder builder) {
    this.emailConfig = builder.emailConfig;
    this.attachmentFiles = builder.attachmentFiles;
  }

  public void send() {
    SubProcessCall.withPath(SEND_EMAIL_CALLABLE).withParam("emailConfig", emailConfig).withParam("attachmentFiles", attachmentFiles).call();
  }

  public static class MailBuilder {
    private EmailConfiguration emailConfig;
    private List<File> attachmentFiles;

    public MailBuilder() {
      emailConfig = new EmailConfiguration();
    }
    public MailBuilder subject(String subject) {
      emailConfig.setSubject(subject);
      return this;
    }

    public MailBuilder from(String from) {
      emailConfig.setFrom(from);
      return this;
    }

    public MailBuilder replyTo(String replyTo) {
      emailConfig.setReplyTo(replyTo);
      return this;
    }

    public MailBuilder to(String to) {
      emailConfig.setTo(to);
      return this;
    }

    public MailBuilder cc(String cc) {
      emailConfig.setCC(cc);
      return this;
    }

    public MailBuilder bcc(String bcc) {
      emailConfig.setBCC(bcc);
      return this;
    }

    public MailBuilder message(String message) {
      emailConfig.setMessageContent(message);
      return this;
    }

    public MailBuilder attachments(List<File> attachmentFiles) {
      this.attachmentFiles = attachmentFiles;
      return this;
    }

    public Mail build() {
      return new Mail(this);
    }
  }
}
