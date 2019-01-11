import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("serial")
public class LifeCycleListener implements PhaseListener{
	
	private long time = 0;
	
	@Override
	public void afterPhase(PhaseEvent event) {
	  Ivy.log().warn("==========Time take at phase: {0} - - - {1}",event.getPhaseId(),(System.nanoTime()-time));		
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	  time = System.nanoTime();
	  Ivy.log().warn("=====Start phase: {0}", event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}