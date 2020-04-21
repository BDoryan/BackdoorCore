package isotopestudio.backdoor.core.player;

import java.util.UUID;

import isotopestudio.backdoor.core.elements.GameElement;
import isotopestudio.backdoor.core.team.Team;

public class Player {

	private UUID uuid;
	
	private String username;
	private Team team;
	
	private int money = 100;
	
	public Player() {
		this.uuid = UUID.randomUUID();
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	private GameElement target;
	
	public GameElement getTarget() {
		return target;
	}
	
	public void connect(GameElement target) {
		this.target = target;
	}
	
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void addMoney(int money) {
		this.money += money;
	}
	
	public void removeMoney(int money) {
		this.money -= money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
