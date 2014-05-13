package sillyclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleCall {

	@Test
	public void test() throws UnknownHostException, IOException {
		Socket s = null;
		try {
			s = new Socket("localhost", 6666);
			s.getOutputStream().write(("TEST\nEND\n").getBytes());
			String line = new BufferedReader(new InputStreamReader(
					s.getInputStream())).readLine();
			assertEquals("3", line);
		} finally {
			if (s != null && s.isClosed() == false)
				s.close();
		}
	}

}
