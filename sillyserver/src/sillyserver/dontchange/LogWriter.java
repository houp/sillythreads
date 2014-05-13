package sillyserver.dontchange;

public class LogWriter {
	public static synchronized void writeToLog(String s) {
		System.out.println("LOG: "+s);
	}
}
