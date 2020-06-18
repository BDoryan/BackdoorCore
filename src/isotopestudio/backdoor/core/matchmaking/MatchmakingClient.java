package isotopestudio.backdoor.core.matchmaking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import doryanbessiere.isotopestudio.api.IsotopeStudioAPI;
import doryanbessiere.isotopestudio.commons.messaging.Message;

public abstract class MatchmakingClient {

	private String email;
	private String token;
	private String version;

	public MatchmakingClient(String email, String token, String version) {
		this.email = email;
		this.token = token;
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public abstract void error(HashMap<String, String> message);
	public abstract void redirect(String adress, int port);
	
	public void connect(){
		boolean running = true;
		try {
			Socket socket = new Socket(IsotopeStudioAPI.BACKDOOR_MATCHMAKING_SERVER, 2424);
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			DataInputStream reader = new DataInputStream(socket.getInputStream());

			String authentication = Message.write("email", email, "token", token, "version", version) + "\n";
			writer.writeBytes(authentication+"\n");
			writer.flush();
			
			while (running) {
				String line = reader.readLine();
				HashMap<String, String> message = Message.read(line);
				if (message.containsKey("authenticate")) {
					Boolean authenticate = Boolean.valueOf(message.get("authenticate"));
					if (!authenticate) {
						error(message);
					}
				} else if (message.containsKey("kick")) {
					running = false;
					error(message);
				} else if (message.containsKey("connection")) {
					running = false;
					redirect(socket.getInetAddress().getHostAddress(), Integer.valueOf(message.get("connection")));
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
	}
}
