package sillyserver.change;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private SimpleDateFormat df = new SimpleDateFormat(
			"yyyyy.MMMMM.dd GGG hh:mm aaa");

	private DateFormatter() {
	}

	private static final DateFormatter instance = new DateFormatter();

	public static DateFormatter getInstance() {
		return instance;
	}

	public String format(Date d) {
		String result;
		synchronized (df) {
			result = df.format(d);
		}
		return result;

	}

}
