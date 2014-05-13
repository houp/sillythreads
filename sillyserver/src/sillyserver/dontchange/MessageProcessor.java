package sillyserver.dontchange;

public class MessageProcessor {
	public static void process(String msg) {
		try {
			Thread.sleep(700);
		} catch (Exception e) {
			// ignore
		}
	}
}
