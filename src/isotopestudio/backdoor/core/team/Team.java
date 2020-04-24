package isotopestudio.backdoor.core.team;

import java.util.ArrayList;

public enum Team {

	RED("team_red"), 
	BLUE("team_blue");

	private String path;

	private Team(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public static Team get(String teamString) {
		for (Team team : Team.values()) {
			if (team.toString().equalsIgnoreCase(teamString)) {
				return team;
			}
		}
		return null;
	}
}
