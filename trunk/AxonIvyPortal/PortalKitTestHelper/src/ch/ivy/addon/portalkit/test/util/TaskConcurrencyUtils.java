package ch.ivy.addon.portalkit.test.util;

public class TaskConcurrencyUtils {
	private static boolean isInProcess = false;

	public static boolean isInProcess() {
		return isInProcess;
	}

	public static void setInProcess(boolean isInProcess) {
		TaskConcurrencyUtils.isInProcess = isInProcess;
	}
}
