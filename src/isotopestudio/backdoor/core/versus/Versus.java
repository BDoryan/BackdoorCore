package isotopestudio.backdoor.core.versus;

/**
 * @author BDoryan
 * @github https://www.github.com/BDoryan/
 */
public enum Versus {

	ONEvsONE("1vs1", 1),
	TWOvsTWO("2vs2", 2);
	
	private String text;

	private int maximum;
	
	Versus(String text, int maximum){
		this.text =text;
		this.maximum = maximum;
	}
	
	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	public static Versus fromString(String string) {
		for(Versus versus : Versus.values()) 
			if(versus.getText().equalsIgnoreCase(string))
				return versus;
		return null;
	}
}
