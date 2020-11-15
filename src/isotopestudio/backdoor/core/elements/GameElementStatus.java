package isotopestudio.backdoor.core.elements;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public enum GameElementStatus {
	
	ATTACKED("game_element_attacked"),
	CAPTURED("game_element_captured"),
	NEUTRAL("game_element_neutral"),
	OFFLINE("game_element_offline");
	
	private String lang_path;

	GameElementStatus(String lang_path) {
		this.lang_path = lang_path;
	}
	
	/**
	 * @return the lang path
	 */
	public String getLangPath() {
		return lang_path;
	}
}
