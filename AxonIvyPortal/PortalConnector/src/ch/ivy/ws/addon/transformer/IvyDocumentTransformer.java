package ch.ivy.ws.addon.transformer;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyDocument;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.document.IDocument;

/**
 * Transform a ICase object to an IvyCase object
 */
public class IvyDocumentTransformer {

  public IvyDocument transform(IDocument document) {
    IvyDocument result = new IvyDocument();

    result.setId(document.getId());
    result.setName(document.getName());
    result.setPath(document.getPath());
    result.setSize(document.getSize());
    result.setCreation(document.getCreation());
    result.setLastModification(document.getLastModification());

    try {
      File file = new File(document.getPath().asString());
      result.setContentType(Files.probeContentType(file.getJavaFile().toPath()));
    } catch (Exception e) {
    }

    return result;
  }

  public List<IvyDocument> transform(List<IDocument> documents) {
    List<IvyDocument> result = new ArrayList<>();

    for (IDocument document : documents) {
      result.add(transform(document));
    }

    return result;
  }
}
