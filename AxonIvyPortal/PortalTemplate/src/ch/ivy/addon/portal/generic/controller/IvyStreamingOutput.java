package ch.ivy.addon.portal.generic.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;
import ch.ivyteam.ivy.request.RequestFactory;
import ch.ivyteam.ivy.request.metadata.MetaData;
import ch.ivyteam.ivy.security.ISession;

/**
 * @author rew
 *
 *         Very primitive adaption of a stream that runs in the correct IVY PMV/SEssion context. Required in order to
 *         use any IVY.xyz API in streaming code...
 * 
 * 
 *         LIMITATION: currently the chunked output stream is only working because the general RestResourceConfig
 *         (server wide servlet of ivy) has the property initParams.put(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER,
 *         "0"); for a final solution this parameter should be configurable per service method. And not only globally...
 * 
 * @see https
 *      ://answers.axonivy.com/questions/2072/jax-rs-method-returns-streamingoutput-fails-with-access-to-ivy-environment
 *      -outside-a-process-request-thread-is-not-possible
 */
public abstract class IvyStreamingOutput implements StreamingOutput {

  private final IProcessModelVersion pmv;
  private final ISession session;

  public IvyStreamingOutput() {
    this(MetaData.getProcessModelVersion(), MetaData.getSession());
  }

  public IvyStreamingOutput(IProcessModelVersion pmv, ISession session) {
    this.pmv = pmv;
    this.session = session;
  }

  @Override
  public final void write(OutputStream stream) throws IOException, WebApplicationException {
    IProcessModelVersionRequest streamRequest = RequestFactory.createRestRequest(pmv, session);
    MetaData.pushRequest(streamRequest);
    try {
      writeInIvyContext(stream);
    } finally {
      MetaData.popRequest();
    }
  }

  public abstract void writeInIvyContext(OutputStream stream) throws IOException, WebApplicationException;

}
