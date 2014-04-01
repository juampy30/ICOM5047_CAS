package databases;

public class Authorization {
	
	private int authorizationID;
	private String authorizationName;
	private boolean hasUnconditionalEntry;
	
	public Authorization(int id, String name, boolean unconditionalEntry) {
		this.authorizationID = id;
		this.authorizationName = name;
		this.hasUnconditionalEntry = unconditionalEntry;
		
	}
	
	public static Authorization getInstanceFromString() {
		//TODO: Complete this method
		return null;
	}
	
	public int getAuthorizationID() {
		return this.authorizationID;
	}
	
	public String getAuthorizationName() {
		return this.authorizationName;
	}
	
	public boolean hasUnconditionalEntry() {
		return this.hasUnconditionalEntry;
	}
	
	@Override
	public String toString() {
	
		String authorizationString;
		authorizationString= "["+getAuthorizationID()+", "+getAuthorizationName()+", "+hasUnconditionalEntry()+"]";
		return authorizationString;
	}
	
	@Override
	public boolean equals(Object anotherObject) {
		return this.authorizationID == ((Authorization) anotherObject).getAuthorizationID() && this.authorizationName.equals(((Authorization) anotherObject).getAuthorizationName());
	}
}
