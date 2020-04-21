package isotopestudio.backdoor.core.team;

import java.util.ArrayList;

import isotopestudio.backdoor.network.player.NetworkedPlayer;
import isotopestudio.backdoor.network.server.GameServer;

public enum Team {

	RED("team_red", 1), 
	BLUE("team_blue", 1);

	private String path;
	private int max_players;

	private Team(String path, int max_players) {
		this.path = path;
		this.max_players = max_players;
	}

	public String getPath() {
		return path;
	}

	private ArrayList<NetworkedPlayer> players = new ArrayList<>();

	public void join(NetworkedPlayer player) {
		players.add(player);
	}

	public void leave(NetworkedPlayer player) {
		players.remove(player);
		if (GameServer.gameServer.getParty() != null && GameServer.gameServer.getParty().isStarted()) {
			GameServer.gameServer.getParty().lose(this);
			GameServer.gameServer.stopParty();
		}
	}

	public int getMaxPlayers() {
		return max_players;
	}

	public ArrayList<NetworkedPlayer> getPlayers() {
		return players;
	}

	public static Team get(String teamString) {
		for (Team team : Team.values()) {
			if (team.toString().equalsIgnoreCase(teamString)) {
				return team;
			}
		}
		return null;
	}

	public static Team joinAuto(NetworkedPlayer player) {
		for (Team team : Team.values()) {
			if (team.getPlayers().size() < team.getMaxPlayers()) {
				team.join(player);
				System.out.println(player.getUsername()+" has join -> "+player.getTeam());
				return team;
			}
		}
		return null;
	}
}
