package isotopestudio.backdoor.core.script;

import doryanbessiere.isotopestudio.commons.lang.Lang;

/**
 * @author BESSIERE Doryan
 * @github https://www.github.com/DoryanBessiere/
 */
public class GameScript {

	private String name;
	private GameScriptType type;
	private String description_lang;
	private double price;
	
	public GameScript(GameScriptType type, String name, String description_lang, double price) {
		this.type = type;
		this.name = name;
		this.description_lang = description_lang;
		this.price = price;
	}
	
	public GameScriptType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescriptionLang() {
		return description_lang;
	}

	public String getDescriptionTranslated() {
		return Lang.get(description_lang);
	}

	public double getPrice() {
		return price;
	}
	
	public static enum GameScriptType {
		OFFENSIVE,
		DEFENSIVE;
	}
	
	public static enum GameScripts {
		HEALER(new GameScript(GameScriptType.DEFENSIVE, "healer.sh", "script_healer_description", 0)),
		ATTACK(new GameScript(GameScriptType.OFFENSIVE, "attack.sh", "script_attack_description", 0)),
		ATTACKER(new GameScript(GameScriptType.OFFENSIVE, "attacker.sh", "script_attacker_description", 0)),
		SHIELD(new GameScript(GameScriptType.DEFENSIVE, "shield.sh", "script_shield_description", 0)),
		KICKALL(new GameScript(GameScriptType.OFFENSIVE, "kickall.sh", "script_kickall_description", 0)),
		SHUTDOWN(new GameScript(GameScriptType.OFFENSIVE, "shutdown.sh", "script_shutdown_description", 0)),
		FIREWALL(new GameScript(GameScriptType.DEFENSIVE, "firewall.sh", "script_firewall_description", 0)),
		HEAL(new GameScript(GameScriptType.DEFENSIVE, "heal.sh", "script_heal_description", 0));
		
		private GameScript gameScript;
		
		private GameScripts(GameScript gameScript) {
			this.gameScript = gameScript;
		}

		public GameScript getGameScript() {
			return gameScript;
		}
		
		public static GameScripts fromName(String name) {
			for(GameScripts scripts : GameScripts.values()) {
				if(scripts.getGameScript().getName().equalsIgnoreCase(name)) {
					return scripts;
				}
			}
			return null;
		}
	}
}