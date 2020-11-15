package isotopestudio.backdoor.core.server.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import doryanbessiere.isotopestudio.commons.GsonInstance;
import isotopestudio.backdoor.core.gamemode.GameMode;
import isotopestudio.backdoor.core.team.Team;
import isotopestudio.backdoor.core.versus.Versus;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public class GameServerConfiguration {
	
	private final UUID session = UUID.randomUUID();

	private String ownerUUID;
	
	private int port;
	private String password;
	private boolean useDatabase;
	
	private GameMode gameMode;
	private Versus versus;

	private String mysql_host;
	private String mysql_database;
	private String mysql_username;
	private String mysql_password;
	
	private ArrayList<String> whitelist = new ArrayList<String>();
	
	private boolean randomTeam;
	private HashMap<String, Team> teamAssigned = new HashMap<>();
	
	public GameServerConfiguration(int port, String password, boolean useDatabase, GameMode gameMode, Versus versus,
			String mysql_host, String mysql_database, String mysql_username, String mysql_password, boolean randomTeam) {
		super();
		this.port = port;
		this.password = password;
		this.useDatabase = useDatabase;
		this.gameMode = gameMode;
		this.versus = versus;
		this.mysql_host = mysql_host;
		this.mysql_database = mysql_database;
		this.mysql_username = mysql_username;
		this.mysql_password = mysql_password;
		this.randomTeam = randomTeam;
	}
	
	/**
	 * @param ownerUUID the ownerUUID to set
	 */
	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}
	
	/**
	 * @return the ownerUUID
	 */
	public String getOwnerUUID() {
		return ownerUUID;
	}
	
	/**
	 * @return the sESSION
	 */
	public UUID getSession() {
		return session;
	}
	
	/**
	 * @return the randomTeam
	 */
	public boolean hasRandomTeam() {
		return randomTeam;
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
	 * @return the mysql_database
	 */
	public String getMysqlHost() {
		return mysql_host;
	}
	
	/**
	 * @return the mysql_database
	 */
	public String getMysqlDatabase() {
		return mysql_database;
	}
	
	/**
	 * @return the mysql_database
	 */
	public String getMysqlUsername() {
		return mysql_username;
	}
	
	/**
	 * @return the mysql_database
	 */
	public String getMysqlPassword() {
		return mysql_password;
	}
	
	/**
	 * @return the useDatabase
	 */
	public boolean hasDatabase() {
		return useDatabase;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * @return the teamAssigned
	 */
	public HashMap<String, Team> getTeamAssigned() {
		return teamAssigned;
	}
	
	/**
	 * @return the whitelist
	 */
	public ArrayList<String> getWhitelist() {
		return whitelist;
	}

	/**
	 * @return object from json string
	 */
	public String toJson() {
		return GsonInstance.instance().toJson(this);
	}
}
