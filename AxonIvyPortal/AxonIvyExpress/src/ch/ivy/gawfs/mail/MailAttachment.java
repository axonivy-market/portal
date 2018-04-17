package ch.ivy.gawfs.mail;

import java.io.IOException;
import java.util.List;

import ch.ivy.addon.portalkit.dto.AttachmentDTO;
import ch.ivy.gawfs.enums.EmailAttachmentStatus;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Binary;
import ch.ivyteam.ivy.scripting.objects.File;

public class MailAttachment {

  private List<AttachmentDTO> attachments;
  
  public MailAttachment(List<AttachmentDTO> attachments) {
    this.attachments = attachments;
  }
  
  public void updatePhysicalPaths() {
    attachments.stream().forEach(this::updatePhysicalAttachment);
  }

  private void updatePhysicalAttachment(AttachmentDTO attachment) {
    if (attachment.getStatus() == EmailAttachmentStatus.ADDED) {
      try {
        Binary binary = new Binary(attachment.getContent());
        File file = new File(attachment.getPath(), false);
        file.writeBinary(binary);
      } catch (IOException ex) {
        Ivy.log().error("Cannot write file with path " + attachment.getPath(), ex);
      }
    } else if (attachment.getStatus() == EmailAttachmentStatus.DELETED) {
      try {
        File file = new File(attachment.getPath(), false);
        file.forceDelete();
      } catch (IOException ex) {
        Ivy.log().error("Cannot delete file with path " + attachment.getPath(), ex);
      }
    }
    attachment.setStatus(null);
  }
}
