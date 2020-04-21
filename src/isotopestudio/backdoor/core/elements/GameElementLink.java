package isotopestudio.backdoor.core.elements;

public class GameElementLink {
	
	private String from;
	private String to;
	
	public GameElementLink(String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
