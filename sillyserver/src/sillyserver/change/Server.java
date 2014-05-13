package sillyserver.change;

import java.io.IOException;
import java.lang.Thread.State;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private static final int PORT = 6666;
	private static final int MAX_WORKERS = 2;
	
	public static void main(String args[]) throws IOException {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			List<ConnectionHandler> handlers = new ArrayList<>();
			while (true) {
				Socket clientConnection = server.accept();
				System.out.println("New connection accepted");
				int workingThreads = 0;
				do {
					workingThreads = 0;
					for (ConnectionHandler ch : handlers) {
						if (ch.getState() == State.RUNNABLE)
							workingThreads++;
					}
				} while (workingThreads > MAX_WORKERS);
				System.out.println("Working threads: "+workingThreads);
				ConnectionHandler h = new ConnectionHandler(clientConnection);
				handlers.add(h);
				h.start();
				System.out.println("Handler starder");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if (server != null)
				server.close();
		}
	}

}
