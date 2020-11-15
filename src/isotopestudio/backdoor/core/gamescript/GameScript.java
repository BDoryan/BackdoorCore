package isotopestudio.backdoor.core.gamescript;

import doryanbessiere.isotopestudio.commons.lang.Lang;
import isotopestudio.backdoor.core.player.Player;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public class GameScript {

	private String name;
	private GameScriptType type;
	private String description_lang;
	private double price;
	
	private GameScriptExecutor exectutor = new GameScriptExecutor() {
		@Override
		public Object exec(Object... datas) {
			System.err.println("default executor define for game script -> "+name);
			return null;
		}
	};

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
	
	/**
	 * @param exectutor the exectutor to set
	 */
	public void setExectutor(GameScriptExecutor exectutor) {
		this.exectutor = exectutor;
	}
	
	/**
	 * @return the exectutor
	 */
	public GameScriptExecutor getExectutor() {
		return exectutor;
	}

	/**
	 * 
	 * You need a lang file loaded for use this function!
	 * 
	 * @return
	 */
	public String getDescriptionTranslated() {
		return Lang.get(description_lang);
	}

	public double getPrice() {
		return price;
	}
	
	public static enum GameScriptType {
		OFFENSIVE, DEFENSIVE;
	}

	public static enum GameScripts {
		HEALER(new GameScript(GameScriptType.DEFENSIVE, "healer.sh", "script_healer_description", 5)),
		ATTACK(new GameScript(GameScriptType.OFFENSIVE, "attack.sh", "script_attack_description", 5)),
		ATTACKER(new GameScript(GameScriptType.OFFENSIVE, "attacker.sh", "script_attacker_description", 5)),
		SHIELD(new GameScript(GameScriptType.DEFENSIVE, "shield.sh", "script_shield_description", 5)),
		KICKALL(new GameScript(GameScriptType.OFFENSIVE, "kickall.sh", "script_kickall_description", 5)),
		SHUTDOWN(new GameScript(GameScriptType.OFFENSIVE, "shutdown.sh", "script_shutdown_description", 5)),
		FIREWALL(new GameScript(GameScriptType.DEFENSIVE, "firewall.sh", "script_firewall_description", 5)),
		HEAL(new GameScript(GameScriptType.DEFENSIVE, "heal.sh", "script_heal_description", 5));

		private GameScript gameScript;

		private GameScripts(GameScript gameScript) {
			this.gameScript = gameScript;
		}

		public GameScript getGameScript() {
			return gameScript;
		}

		public void setGameScript(GameScript gameScript) {
			this.gameScript = gameScript;
		}

		public static GameScripts fromName(String name) {
			for (GameScripts scripts : GameScripts.values()) {
				if (scripts.getGameScript().getName().equalsIgnoreCase(name)) {
					return scripts;
				}
			}
			return null;
		}
	}
}