package sillyserver.change;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

import sillyserver.dontchange.LogWriter;
import sillyserver.dontchange.MessageProcessor;

public class ConnectionHandler extends Thread {

	private Socket connection;

	public ConnectionHandler(Socket connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void run() {
		int msg_count = 0;
		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line;
			while ((line = input.readLine()) != null) {
				msg_count++;
				MessageProcessor.process(line);
				logMessage(line);
				if (line.equals("END"))
					break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection.getOutputStream().write(
					Integer.toString(msg_count).getBytes());
			connection.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void logMessage(final String msg) {

		final int id = IdGenerator.getInstance().getNewId();
		final String date = DateFormatter.getInstance().format(
				new Date(System.currentTimeMillis()));

		// write to log file asynchronously
		new Thread(new Runnable() {

			@Override
			public void run() {
				LogWriter.writeToLog(date + " " + id + " " + msg);
			}
		}).start();

	}

}
