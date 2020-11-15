package isotopestudio.backdoor.gateway.group;

import java.util.ArrayList;

import doryanbessiere.isotopestudio.api.profile.Profile;
import isotopestudio.backdoor.core.gamemode.GameMode;
import isotopestudio.backdoor.core.versus.Versus;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public class GroupObject {
	
	private String groupUUID;

	private Profile owner;
	private boolean isPrivate = true;

	private ArrayList<Profile> players = new ArrayList<>();
	private ArrayList<String> whitelist = new ArrayList<>();
	private ArrayList<String> playersReady = new ArrayList<>();
	
	private GameMode gameMode;
	private Versus versus;
	
	private boolean inQueue;
	
	public GroupObject(boolean inQueue, GameMode gameMode, Versus versus, String groupUUID, Profile owner, boolean isPrivate) {
		super();
		this.inQueue = inQueue;
		this.gameMode = gameMode;
		this.versus = versus;
		
		this.groupUUID = groupUUID;
		this.owner = owner;
		this.isPrivate = isPrivate;
	}
	
	/**
	 * @return the inQueue
	 */
	public boolean isInQueue() {
		return inQueue;
	}

	/**
	 * @param gameMode the gameMode to set
	 */
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	/**
	 * @return the gameMode
	 */
	public GameMode getGameMode() {
		return gameMode;
	}
	
	/**
	 * @return the versus
	 */
	public Versus getVersus() {
		return versus;
	}
	
	/**
	 * @param versus the versus to set
	 */
	public void setVersus(Versus versus) {
		this.versus = versus;
	}
	
	/**
	 * @return the groupUUID
	 */
	public String getGroupUUID() {
		return groupUUID;
	}
	
	/**
	 * @param groupUUID the groupUUID to set
	 */
	public void setGroupUUID(String groupUUID) {
		this.groupUUID = groupUUID;
	}
	
	/**
	 * @return the owner
	 */
	public Profile getOwner() {
		return owner;
	}
	
	/**
	 * @return the isPrivate
	 */
	public boolean isPrivate() {
		return isPrivate;
	}
	
	/**
	 * @return the playersReady
	 */
	public ArrayList<String> getPlayersReady() {
		return playersReady;
	}
	
	/**
	 * @return the players
	 */
	public ArrayList<Profile> getPlayers() {
		return players;
	}
	
	/**
	 * @return the whitelist
	 */
	public ArrayList<String> getWhitelist() {
		return whitelist;
	}

	/**
	 * @param uuidString
	 * @return
	 */
	public boolean isReady(String uuidString) {
		return getPlayersReady().contains(uuidString);
	}
}