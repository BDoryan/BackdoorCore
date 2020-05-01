package isotopestudio.backdoor.core.matchmaking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;

import doryanbessiere.isotopestudio.commons.messaging.Message;

public class MatchmakingClient {

	/**
	 *
	 * @param email
	 * @param token
	 * @return returned the connection status, if return null the socket cannot be
	 *         connected
	 */
	public static String connect(String email, String token) {
		boolean running = true;
		try {
			Socket socket = new Socket("localhost", 2424);
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			DataInputStream reader = new DataInputStream(socket.getInputStream());

			String authentication = Message.write("email", email, "token", token) + "\n";
			writer.writeBytes(authentication+"\n");
			writer.flush();
			
			while (running) {
				String line = reader.readLine();
				HashMap<String, String> message = Message.read(line);
				if (message.containsKey("authenticate")) {
					Boolean authenticate = Boolean.valueOf(message.get("authenticate"));
					if (!authenticate) {
						return "authentication_failed";
					}
				} else if (message.containsKey("kick")) {
					running = false;
					return message.get("kick");
				} else if (message.containsKey("connection")) {
					running = false;
					return "connection=" + socket.getInetAddress().getHostAddress() + ":" + message.get("connection");
				}
			}
			socket.close();
			reader.close();
			writer.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			running = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
