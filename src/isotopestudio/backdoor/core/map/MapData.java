	package isotopestudio.backdoor.core.map;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import isotopestudio.backdoor.core.elements.GameElement;
import isotopestudio.backdoor.core.elements.GameElementType;
import isotopestudio.backdoor.core.team.Team;

public class MapData {

	private String name;
	
	public MapData(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Map<String, GameElement> elements = Collections.synchronizedMap(new LinkedHashMap<>());
	public Map<Team, GameElement> team_servers = Collections.synchronizedMap(new LinkedHashMap<>());
	
	public Map<String, GameElement> getElements() {
		return elements;
	}
	
	public GameElement getTeamServer(Team team) {
		return team_servers.get(team);
	}
	
	public Map<Team, GameElement> getTeamServers() {
		return team_servers;
	}

	public void updateElement(String name, GameElement gameElement) {
		getElements().remove(name);
		getElements().put(name, gameElement);
	}
}
