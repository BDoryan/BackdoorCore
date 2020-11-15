package isotopestudio.backdoor.core.gamemode;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public enum GameMode {

	DOMINATION;

	/**
	 * @param readString
	 * @return
	 */
	public static GameMode fromString(String string) {
		for(GameMode gameMode : values()) {
			if(gameMode.toString().equalsIgnoreCase(string)) 
				return gameMode;
		}
		return null;
	}
}
