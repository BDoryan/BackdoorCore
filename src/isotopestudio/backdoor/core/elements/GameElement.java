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

import doryanbessiere.isotopestudio.commons.GsonInstance;
import isotopestudio.backdoor.core.team.Team;
import isotopestudio.backdoor.game.utils.country.Country;
import isotopestudio.backdoor.network.packet.packets.PacketSendElementData;
import isotopestudio.backdoor.network.player.NetworkedPlayer;
import isotopestudio.backdoor.network.server.GameServer;

public class GameElement {

	public static Map<String, GameElement> gameElements = Collections.synchronizedMap(new LinkedHashMap<>());
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
	private transient ArrayList<NetworkedPlayer> connected = new ArrayList<NetworkedPlayer>();
	private ArrayList<Team> team_connected = new ArrayList<Team>();
	private int max_points = 1;

	private boolean linked = false;

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

		if (type == GameElementType.SERVER) {
			setTeamPoint(getTeam(), max_points);
		}

		this.country = Country.randomCountry();

		if (team != null)
			linked = true;

		gameElements.put(uuid.toString(), this);
	}

	public void connect(NetworkedPlayer player) {
		connected.add(player);
		if (!getTeamConnected().contains(player.getTeam()))
			getTeamConnected().add(player.getTeam());
		GameServer.gameServer.sendAll(new PacketSendElementData(this));
		if (getType() == GameElementType.SERVER)
			return;
		player.removeMoney(50);
	}

	public void disconnect(NetworkedPlayer player) {
		connected.remove(player);
		getTeamConnected().remove(player.getTeam());
		for (NetworkedPlayer player_ : getConnected()) {
			if (player_.getTeam() == player.getTeam()) {
				getTeamConnected().add(player.getTeam());
				break;
			}
		}
		GameServer.gameServer.sendAll(new PacketSendElementData(this));
	}

	public boolean isTeamConnected(Team team) {
		return getTeamConnected().contains(team);
	}

	public ArrayList<NetworkedPlayer> getConnected() {
		return connected;
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

	public void attack(Team team) {
		if (getType() == GameElementType.SERVER) {
			if (team == getTeam()) {
				int point = getTeamPoint(getTeam()) + 1;
				setTeamPoint(getTeam(), point > max_points ? max_points : point);
			} else {
				int point = getTeamPoint(getTeam()) - 1;
				setTeamPoint(getTeam(), point < 0 ? 0 : point);
			}

			if (getTeamPoint(getTeam()) <= 0) {
				GameServer.gameServer.getParty().lose(getTeam());
				GameServer.gameServer.getParty().stop();
			}
		} else if (getType() == GameElementType.NODE) {
			int point = getTeamPoint(team) + 1;
			point = point > max_points ? max_points : point;
			point = point < 0 ? 0 : point;
			
			setTeamPoint(team, point);

			/**
			 * Quand le noeud est neutre on rajoute un point à chaque joueur
			 * 
			 * Quand le noeud appartient à une équipe on fait un système de duel: quand la
			 * team qui a le serveur attack enlève un point à l'adversaire
			 * 
			 */

			if (getTeam() != null) {
				if(getTeam() == team) // L'équipe possède déjà le serveur
					return;
				for (Team team_ : Team.values()) {
					if (team_ != team) {
						int team_point = getTeamPoint(team_) - 1;
						if (team_point < 0)
							team_point = 0;
						setTeamPoint(team_, team_point);
					}
				}
			}

			if (getTeamPoint(team) >= max_points) {
				if (getTeam() == null) {
					setTeam(team);
					for (Team team_ : Team.values()) {
						if (team_ != team) {
							setTeamPoint(team_, 0);
						} else {
							setTeamPoint(team_, max_points);
						}
					}
					linked = true;
					/*
					 * - On récupère tous les noeuds de l'équipe
					 * - On vérifie si le noeud est défini comme 'linked'
					 * - Si ce n'est pas le cas alors qu'il est désormais 'linked' on lui défini sa variable et on le met à jours pour
					 * les joueurs
					 */
					for (GameElement node : GameServer.gameServer.getParty().getNodes(getTeam())) {
						if (!node.linked && GameServer.gameServer.getParty().isLinked(node, getTeam())) {
							node.linked = true;
							GameServer.gameServer.sendAll(new PacketSendElementData(node));
						}
					}
				} else {
					if (getTeam() == team)
						return;

					for (Team team_ : Team.values()) {
						setTeamPoint(team_, 0);
					}
					linked = false;

					Team old_team = getTeam();

					setTeam(null);

					/*
					 * 
					 * - On récupère tous les noeuds de l'équipe
					 * - On vérifie si le noeud n'est pas 'linked' on lui défini sa variable et on envoie l'informations aux joueurs
					 * et on déconnecte l'équipe 
					 */
					for (GameElement node : GameServer.gameServer.getParty().getNodes(old_team)) {
						if (!GameServer.gameServer.getParty().isLinked(node, old_team)) {
							node.linked = false;
							GameServer.gameServer.sendAll(new PacketSendElementData(node));
							node.disconnectTeam(old_team);
						}
					}

					/*
					 * - On récupère les joueurs de l'ancienne équipe
					 * - On vérifie si ils sont connectés sur une noeuds/serveurs et si il n'est plus 'linked'
					 * - On déconnecte l'équipe
					 */
					ArrayList<NetworkedPlayer> players = new ArrayList<NetworkedPlayer>();
					players.addAll(old_team.getPlayers());
					for (NetworkedPlayer player : players) {
						if (player.getTargetAddress() != null) {
							GameElement node = GameServer.gameServer.getParty().getNodeByAddress(player.getTargetAddress());
							if (/*node.getTeam() != player.getTeam() && */!GameServer.gameServer.getParty().isLinked(node, old_team)) {
								node.disconnect(player);
							}
						}
					}
				}
				disconnectAll();
			}
		}
		GameServer.gameServer.sendAll(new PacketSendElementData(this));
	}

	public void disconnectAll() {
		ArrayList<NetworkedPlayer> connected = new ArrayList<NetworkedPlayer>();
		connected.addAll(getConnected());
		for (NetworkedPlayer player : connected) {
			GameServer.gameServer.getParty().disconnect(player);
		}
	}

	public void disconnectTeam(Team team) {
		ArrayList<NetworkedPlayer> connected = new ArrayList<NetworkedPlayer>();
		for (NetworkedPlayer player : connected) {
			if (player.getTeam() == team) {
				GameServer.gameServer.getParty().disconnect(player);	
			}
		}
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
}
