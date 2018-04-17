package ch.ivy.gawfs.mail;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.components.config.EmailConfiguration;
import ch.ivyteam.ivy.email.SimpleMailSender;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Class to send emails from java code.
 */
@SuppressWarnings("restriction")
public class Mail {

  private EmailConfiguration emailConfig;
  private Map<String, File> attachmentFiles;

  private Mail(MailBuilder builder) {
    this.emailConfig = builder.emailConfig;
    this.attachmentFiles = builder.attachmentFiles;
  }

  public void send() throws Exception {
    ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {

      @Override
      public Void call() throws Exception {
        SimpleMailSender mailSender = new SimpleMailSender(attachmentFiles, emailConfig, Ivy.log());
        mailSender.sendMessage();
        return null;
      }
    });
  }

  public static class MailBuilder {
    private EmailConfiguration emailConfig;
    private Map<String, File> attachmentFiles;

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

    public MailBuilder attachments(Map<String, File> attachments) {
      this.attachmentFiles = attachments;
      return this;
    }

    public Mail build() {
      return new Mail(this);
    }
  }
}
