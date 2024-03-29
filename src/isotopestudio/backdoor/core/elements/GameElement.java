package isotopestudio.backdoor.core.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import isotopestudio.backdoor.core.team.Team;
import isotopestudio.backdoor.core.utils.Country;

public class GameElement {

	public static final Map<String, GameElement> CACHE = Collections.synchronizedMap(new LinkedHashMap<>());
	
	private String name;
	private UUID uuid;
	private static ArrayList<String> adress_used = new ArrayList<String>();
	private String address;
	private Country country;
	private ArrayList<GameElementLink> links = new ArrayList<GameElementLink>();

	private Team team = null;
	private GameElementCapacity gameElementCapacity;
	private GameElementType type;

	private HashMap<Team, Integer> points = new HashMap<Team, Integer>();
	private ArrayList<Team> team_connected = new ArrayList<Team>();
	
	private int max_points = 20;

	private int firewall = 0;
	private int firewall_max = 20;

	private boolean linked = false;
	private boolean protected_ = false;

	public GameElement(GameElementType type, String name, Team team) {
		this.type = type;
		this.name = name;
		this.uuid = UUID.randomUUID();
		this.team = team;

		Random r = new Random();
		while (true) {
			this.address = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
			if (!adress_used.contains(this.address))
				break;
		}
		adress_used.add(address);

		for (Team team_ : Team.values()) {
			setTeamPoint(team_, 0);
		}
		if (team != null) {
			setTeamPoint(team, max_points);
		}

		this.country = Country.randomCountry();

		if (team != null)
			linked = true;

		CACHE.put(getUUID().toString(), this);
	}
	
	/**
	 * @param max_points the max_points to set
	 */
	public void setMaxPoints(int max_points) {
		this.max_points = max_points;
	}
	
	/**
	 * @param protected_ the protected_ to set
	 */
	public void setProtected(boolean protected_) {
		this.protected_ = protected_;
	}
	
	/**
	 * @return the protected_
	 */
	public boolean isProtected() {
		return protected_;
	}
	
	/**
	 * @param firewall the firewall to set
	 */
	public void setFirewall(int firewall) {
		this.firewall = firewall;
	}
	
	/**
	 * @param firewall_max the firewall_max to set
	 */
	public void setFirewallMax(int firewall_max) {
		this.firewall_max = firewall_max;
	}

	public boolean isTeamConnected(Team team) {
		return getTeamConnected().contains(team);
	}

	public GameElementType getType() {
		return type;
	}

	public boolean isLinked() {
		return linked;
	}

	public UUID getUUID() {
		return uuid;
	}

	public boolean hasCapacity() {
		return this.gameElementCapacity != null;
	}

	public GameElementCapacity getCapacity() {
		return gameElementCapacity;
	}

	public void setTeamPoint(Team team, int point) {
		points.put(team, point);
	}

	public Integer getTeamPoint(Team team) {
		return points.get(team);
	}
	
	public void setLinked(boolean linked) {
		this.linked = linked;
	}
	
	public int getMaxPoints() {
		return max_points;
	}

	public Country getCountry() {
		return country;
	}

	public boolean hasTeam() {
		return this.team != null;
	}

	public Team getTeam() {
		return team;
	}

	public String getAddress() {
		return address;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	private boolean offline = false;
	
	/**
	 * @param offline the offline to set
	 */
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	
	/**
	 * @return the offline
	 */
	public boolean isOffline() {
		return offline;
	}
	
	public GameElementStatus getStatus() {
		if(offline)
			return GameElementStatus.OFFLINE;
		if(getTeam() == null) {
			if(team_connected.size() > 0) {
				return GameElementStatus.ATTACKED;
			}
			return GameElementStatus.NEUTRAL;
		} else if (getTeam() != null){
			if(team_connected.contains(getTeam()) && team_connected.size() == 1) {
				return GameElementStatus.CAPTURED;
			}
			return GameElementStatus.CAPTURED;
		}
		return null;
	}

	public void link(GameElement target) {
		this.links.add(new GameElementLink(this.getUUID().toString(), target.getUUID().toString()));
		target.links.add(new GameElementLink(target.getUUID().toString(), this.getUUID().toString()));
	}

	public ArrayList<Team> getTeamConnected() {
		return team_connected;
	}

	public ArrayList<GameElementLink> getLinks() {
		return links;
	}

	public HashMap<Team, Integer> getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}

	public int getMaxPoint() {
		return max_points;
	}

	public int getConnectPrice() {
		return 50;
	}
	
	/**
	 * @return the firewall
	 */
	public int getFirewall() {
		return firewall;
	}
	
	/**
	 * @return the firewall_max
	 */
	public int getFirewallMax() {
		return firewall_max;
	}

	public void setPoints(HashMap<Team, Integer> points) {
		this.points = points;
	}

	private static Gson gson;

	public static Gson getGson() {
		return gson == null ? new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> f) {
				return (f.getDeclaringClass() == GameElement.class && f.getName().equals("connected"));
			}
		}).create() : gson;
	}

	public static GameElement getGameElement(String uuid) {
		return CACHE.get(uuid);
	}
}
