package isotopestudio.backdoor.core.player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

import isotopestudio.backdoor.core.elements.GameElement;
import isotopestudio.backdoor.core.gamescript.GameScript;
import isotopestudio.backdoor.core.gamescript.GameScript.GameScriptType;
import isotopestudio.backdoor.core.gamescript.GameScript.GameScripts;
import isotopestudio.backdoor.core.team.Team;

public class Player {

	private UUID uuid;
	
	private String username;
	private Team team;
	
	private double money = 100;
	
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
	
	public void addMoney(double money) {
		this.money += money;
	}
	
	public void removeMoney(double money) {
		this.money -= money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public double getMoney() {
		return money;
	}
	
	private LinkedList<GameScript> scripts = new LinkedList<>();
	
	public boolean buyGameScript(GameScripts gameScripts, int amount) {
		double cost_price = gameScripts.getGameScript().getPrice() * amount;
		
		if(cost_price > getMoney())
			return false;
		
		removeMoney(cost_price);
		
		for(int i = 0;i < amount; i++) 
			addGameScript(gameScripts.getGameScript());	
		
		return true;
	}
	
	public void addGameScript(GameScript gameScript) {
		scripts.add(gameScript);
	}
	
	public void removeGameScript(GameScript gameScript) {
		scripts.remove(gameScript);
	}
	
	public boolean containsScript(String name) {
		for(GameScript gameScript : getScripts()) {
			if(gameScript.getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	public LinkedList<GameScript> getScripts() {
		return scripts;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
