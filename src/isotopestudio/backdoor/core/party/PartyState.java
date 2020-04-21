package isotopestudio.backdoor.core.party;

public enum PartyState {

	START,
	STOP;

	public static PartyState parse(String string) {
		for(PartyState state : PartyState.values()) {
			if(state.toString().equalsIgnoreCase(string))
				return state;
		}
		return null;
	}
}
