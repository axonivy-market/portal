package ch.ivy.addon.portalkit.document;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyDocument;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.document.IDocument;

/**
 * @deprecated use IvyDocumentTransformer in package com.axonivy.portal.component.document
 *
 */
@Deprecated(since="8.0.27")
public class IvyDocumentTransformer {
  public IvyDocument transform(IDocument document) {
    IvyDocument result = new IvyDocument();

    result.setId(String.valueOf(document.getId()));
    result.setName(document.getName());
    result.setPath(document.getPath().asString());
    result.setSize(document.getSize());
    result.setCreation(document.getCreation());
    result.setLastModification(document.getLastModification());

    try {
      File file = new File(document.getPath().asString());
      result.setContentType(Files.probeContentType(file.getJavaFile().toPath()));
    } catch (IOException e) {
      Ivy.log().error(e);
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
